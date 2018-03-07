package com.gech.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class BirthDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

//    DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String  weekday;
   private String femalename;
    private String malename;

    public BirthDate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getFemalename() {
        return femalename;
    }

    public void setFemalename(String femalename) {
        this.femalename = femalename;
    }

    public String getMalename() {
        return malename;
    }

    public void setMalename(String malename) {
        this.malename = malename;
    }
}
