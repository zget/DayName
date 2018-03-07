package com.gech.demo.Controller;



import com.gech.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;

import static com.gech.demo.Model.BirthDate.dTF;


@Controller
public class HomeController {


@Autowired
AppUserRepository userRepository;

@Autowired
AppRoleRepository roleRepository;


@Autowired
    BirthdateRepository dobRepo;


    @GetMapping("/addbirthday")
    public String loadBirthDayForm(Model model){

        model.addAttribute("message", "Enter a date in the past(dd/mm/yyyy)");
        model.addAttribute("bdate", new BirthDate());
        return "birthdayform";
    }

    @PostMapping("/addbirthday")
    public String processBirthDay(@Valid @ModelAttribute("bdate") BirthDate userinput, BindingResult result,
                                  HttpServletRequest request){
        if(result.hasErrors()){
            return "birthdayform";
        }


        do{
            System.out.println("Enter a date in the past(dd/mm/yyyy)");
            try{

                 userinput.setDob(LocalDate.parse(request.getParameter("userInput"),dTF));
                // bdate.getDob();

            }catch(Exception e)
            {
                if(userinput.getDob().isAfter(LocalDate.now())&&userinput.getDob()!=null)
                    System.out.println("The date must be in the past");

                System.out.println("Unable to convert the string you entered to date. Please try again!");

            }

        }while(userinput==null);

        //Display the date entered
        System.out.println(userinput.getDob().format(dTF));
        System.out.println(userinput.getDob().getDayOfWeek());

        dobRepo.save(userinput);
    return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model){

        model.addAttribute("bdate", dobRepo.findAll());
        return "list";
    }



    @RequestMapping("/")
    public String home(){

        return "myLoginPage";
    }

    @RequestMapping("/login")
    public String login(){

        return "myLoginPage";
    }
    @RequestMapping("/home")
    public String homePage(){

        return "HOME";
    }
    @RequestMapping("/access-denied")
    public String accessDenied(){

        return "accessDenied";
    }

    @GetMapping("/register")
    public String newUser(Model model){
        model.addAttribute("user", new AppUser());
        return "Registration";
    }



    @PostMapping("/register")
    public String processUser(@Valid @ModelAttribute("user") AppUser user,  BindingResult result, Model model){
        if(result.hasErrors()){
            return "Registration";
        }

        user.addRole(roleRepository.findAppRoleByRoleName("USER"));
        userRepository.save(user);
        model.addAttribute("message", "User account Successfully Created");
        return "redirect:/login";
    }




}
