package com.gech.demo.Service;

import com.gech.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.Calendar;

@Service
public class DateService {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    AppRoleRepository roleRepository;

    public DateService() {
    }

    @Autowired
    BirthdateRepository birthdateRepository;

    public void saveBirthDate(BirthDate bd){
        birthdateRepository.save(bd);

    }

    public void findDay(BirthDate bd){

//        Calendar c = Calendar.getInstance();
//        c.setTime(bd.getDob());
//        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//        System.out.println(dayOfWeek);

        int dayOfWeek=bd.getDob().getDayOfMonth();

        switch(dayOfWeek){

            case 1:
                System.out.println("Monday");
                bd.setWeekday("Monday");
                bd.setFemalename(FemaleDayName.Monday);
                bd.setMalename(MaleDayName.Monday);
                break;
            case 2:
                System.out.println("Tuesday");
                bd.setWeekday("Tuesday");
                bd.setFemalename(FemaleDayName.Tuesday);
                bd.setMalename(MaleDayName.Tuesday);
                break;
            case 3:
                System.out.println("Wednesday");
                bd.setWeekday("Wednesday");
                bd.setFemalename(FemaleDayName.Wednesday);
                bd.setMalename(MaleDayName.Wednesday);
                break;
            case 4:
                System.out.println("Thursday");
                bd.setWeekday("Thursday");
                bd.setFemalename(FemaleDayName.Thursday);
                bd.setMalename(MaleDayName.Thursday);
                break;
            case 5:
                System.out.println("Friday");
                bd.setWeekday("Friday");
                bd.setFemalename(FemaleDayName.Friday);
                bd.setMalename(MaleDayName.Friday);
                break;
            case 6:
                System.out.println("Saturday");
                bd.setWeekday("Saturday");
                bd.setFemalename(FemaleDayName.Saturday);
                bd.setMalename(MaleDayName.Saturday);
                break;
            case 7:
                System.out.println("Sunday");
                bd.setWeekday("Sunday");
                bd.setFemalename(FemaleDayName.Sunday);
                bd.setMalename(MaleDayName.Sunday);
                break;

        }
        birthdateRepository.save(bd);
    }

    public Iterable<BirthDate> findallBirtdays()
    {
        return birthdateRepository.findAll();
    }
    public AppRole findRole(String role){return roleRepository.findAppRoleByRoleName(role);}

    public void saveUser(AppUser u){userRepository.save(u);}


    public String[] zodiacSigns = new String[]

            {
                    "Capricorn","Aquarius","Pisces","Aries","Taurus","Gemini",
                    "Cancer","Leo","Virgo","Libra",
                    "Scorpio","Sagittarius","none"


            };

    public int getMonth(LocalDate date){ return date.getMonthValue();}
    public int getDay(LocalDate date){return date.getDayOfMonth();}

    public String getZodiac(int month, int day)
    {

        if((month == 11) && ( day>= 22) || (month == 0) && (day <= 19)) {
            return zodiacSigns[0];
        }else if((month == 0) && (day >= 20)  || (month == 1) && (day <= 18)) {
            return zodiacSigns[1];
        } else if((month == 1) && (day >= 19)  || (month == 2) && (day <= 20)) {
            return zodiacSigns[2];
        } else if((month == 2) && (day >= 21)  || (month == 3) && (day <= 19)) {
            return zodiacSigns[3];
        } else if((month == 3) && (day >= 20)  || (month == 4) && (day <= 20)) {
            return zodiacSigns[4];
        } else if((month == 4) && (day >= 21)  || (month == 5) && (day <= 20)) {
            return zodiacSigns[5];
        } else if((month == 5) && (day >= 21)  || (month == 6) && (day <= 22)) {
            return zodiacSigns[6];
        } else if((month == 6) && (day >= 23)  || (month == 7) && (day <= 22)) {
            return zodiacSigns[7];
        } else if((month == 7) && (day >= 23)  || (month == 8) && (day <= 21)) {
            return zodiacSigns[8];
        } else if((month == 8) && (day >= 22) || (month == 9) && (day <= 21)) {
            return zodiacSigns[9];
        } else if((month == 9) && (day >= 24)  || (month == 10) && (day <= 22)) {
            return zodiacSigns[10];
        } else if((month == 10) && (day >= 23)  || (month == 11) && (day <= 21)) {
            return zodiacSigns[11];
        } else {
            return null;

        }

    }





}
