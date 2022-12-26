package com.emailBackEnd.emailBackEnd;

public class User {
    private String email;
    private String pass;

    private User() {
    }

    private static User instance;

    public static User getInstance(String email, String pass) {
        if (instance == null)
            return instance = new User(email, pass);
        else {
            instance.setEmail(email);
            instance.setPass(pass);
            return instance;
        }

    }

    private User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
