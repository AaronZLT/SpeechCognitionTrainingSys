package com.sc.SpeechCognition.common.model;

public class PatientInformation {
    private String paccount;
    private String name;
    private String birthday;
    private String gender;
    private String department;
    private String bednumber;

    public String getAccount() {
        return paccount;
    }

    public String getName() {
        return name;
    }

    public void setAccount(String account) {
        this.paccount = account;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setBed_number(String bed_number) {
        this.bednumber = bed_number;
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

    public String getDepartment() {
        return department;
    }

    public String getBed_number() {
        return bednumber;
    }
}
