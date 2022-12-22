package com.EmailBackEnd.EmailBackEnd;


public class Contact {
    public String username = "";
    public String[] emails = {};

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getEmails() {
        return this.emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public Contact() {
    }
}
