package com.gech.demo.Controller;



import com.gech.demo.Model.*;
import com.gech.demo.Service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


@Controller
public class HomeController {


    @Autowired
    DateService myservice;




    @GetMapping("/addbirthday")
    public String loadBirthDayForm(Model model){

        model.addAttribute("message", "Enter a date in the past(dd/mm/yyyy)");
        model.addAttribute("bdate", new BirthDate());
        return "birthdayform";
    }

    @PostMapping("/addbirthday")
    public String processBirthDay(@Valid @ModelAttribute("bdate") BirthDate birthDate, BindingResult result,
                                  HttpServletRequest request, Model model){
        if(result.hasErrors()){
            return "birthdayform";
        }

        System.out.println(birthDate.getDob());

        myservice.findDay(birthDate);

        int m=myservice.getMonth(birthDate.getDob());
        int d=myservice.getDay(birthDate.getDob());

        myservice.saveBirthDate(birthDate);

//        model.addAttribute("bd", myservice.saveBirthDate(birthDate));
        String myZodiac= myservice.getZodiac(m,d);

        System.out.println(myZodiac);

//        do{
//            System.out.println("Enter a date in the past(dd/mm/yyyy)");
//            try{
//
//                 userinput.setDob(LocalDate.parse(request.getParameter("userInput"),dTF));
//                // bdate.getDob();
//
//            }catch(Exception e)
//            {
//                if(userinput.getDob().isAfter(Date.now())&&userinput.getDob()!=null)
//                    System.out.println("The date must be in the past");
//
//                System.out.println("Unable to convert the string you entered to date. Please try again!");
//
//            }
//
//        }while(userinput==null);

        //Display the date entered
//        System.out.println(userinput.getDob());
////        System.out.println(userinput.getDayOfWeek());
//

    return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model){

        model.addAttribute("bdate",myservice.findallBirtdays());
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

        user.addRole(myservice.findRole("USER"));
        myservice.saveUser(user);
        model.addAttribute("message", "User account Successfully Created");
        return "redirect:/login";
    }



    @GetMapping("/libra")
    public @ResponseBody String showLibra(){


        RestTemplate restTemplate= new RestTemplate();

        Libra libra=restTemplate.getForObject("http://horoscope-api.herokuapp.com/horoscope/today/Libra",Libra.class);
        return libra.getHoroscope() ;

    }


}
