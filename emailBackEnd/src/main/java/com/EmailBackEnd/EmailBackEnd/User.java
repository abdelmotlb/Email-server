package com.EmailBackEnd.EmailBackEnd;

public class User {
    private String name;
    private String pass;

    private User() {
    }

    private static User instance;

    public static User getInstance(String name, String pass) {
        if (instance == null)
            return instance = new User(name, pass);
        else {
            instance.setName(name);
            instance.setPass(pass);
            return instance;
        }

    }

    private User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
