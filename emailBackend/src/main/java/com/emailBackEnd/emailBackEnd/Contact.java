package com.emailBackEnd.emailBackEnd;


public class Contact {
    public String username = "";
    public String[] emails = {};
    public boolean edit = false;

    public boolean isEdit() {
        return this.edit;
    }

    public boolean getEdit() {
        return this.edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

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
