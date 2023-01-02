package com.emailBackEnd.emailBackEnd;

public class User {
    private String email;
    private String pass;
    private String fname;
    private String lname;
    private String gender;

    private User() {
    }

    private static User instance;

    public static User getInstance(String email, String pass, String fname, String lname, String gender) {
        if (instance == null)
            return instance = new User(email, pass, fname, lname, gender);
        else {
            instance.setEmail(email);
            instance.setPass(pass);
            instance.setFname(fname);
            instance.setLname(lname);
            instance.setGender(gender);
            return instance;
        }

    }

    private User(String email, String pass, String fname, String lname, String gender) {
        this.email = email;
        this.pass = pass;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return this.pass;
    }

    private void setPass(String pass) {
        this.pass = pass;
    }

    public String getFname() {
        return this.fname;
    }

    private void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    private void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return this.gender;
    }

    private void setGender(String gender) {
        this.gender = gender;
    }

}