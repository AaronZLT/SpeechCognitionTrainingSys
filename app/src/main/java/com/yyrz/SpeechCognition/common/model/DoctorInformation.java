package com.yyrz.SpeechCognition.common.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class DoctorInformation {
    private String daccount;
    private String name;
    private String birthday;
    private String gender;
    private String department;
    private String hospital;

    public DoctorInformation(){
        daccount=null;
        name=null;
        birthday=null;
        gender=null;
        department=null;
        hospital=null;
    }

    public String getAccount() {
        return daccount;
    }

    public void setAccount(String account) {
        this.daccount = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String[] getKey(){
        return daccount==null?null:new String[]{"账号","姓名","年龄","性别","科室","医院"};
    }

    public String[] getValue(){
        if(name==null)
            return null;

        LocalDate date1 = LocalDate.of(Integer.valueOf(birthday.substring(0,4)),Integer.valueOf(birthday.substring(5,7)), Integer.valueOf(birthday.substring(8,10)));
        String now= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
        LocalDate date2 = LocalDate.of(Integer.valueOf(now.substring(0,4)),Integer.valueOf(now.substring(5,7)), Integer.valueOf(now.substring(8,10)));
        int age = date1.until(date2).getYears();

        return new String []{daccount,name,String.valueOf(age),gender,department,hospital};
    }
}
