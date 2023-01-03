package com.emailBackEnd.emailBackEnd.Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import java.util.*;

import com.emailBackEnd.emailBackEnd.Contact;
import com.emailBackEnd.emailBackEnd.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/")
public class Contacts {

    public static List<Contact> contacts;
    public static List<User> users;
    public int location;
    LogIn log = new LogIn();

    @PostMapping("/add")
    String add(@RequestParam(value = "username") String usernameAndActive, @RequestBody String email) {
        // check in database
        String[] twoParams = usernameAndActive.split("\\$");
        String username = twoParams[0];
        String active = twoParams[1];

        if (email.equals(active)) {
            return "You can't Add yourself !";
        }

        boolean found = isValid(email);
        if (!found) {
            return "Email doesn't exist";
        }
        // check in contacts
        boolean exists = isExist(username, active);
        if (exists) {
            // append to list of emails
            return "Choose another name for the contact";
        }
        contacts = readContacts(active);
        Contact newContact = new Contact();
        newContact.username = username;
        String[] tempEmail = { email.toString() };
        newContact.emails = tempEmail;
        contacts.add(newContact);
        store(contacts, active);

        return "Added Successfully";
    }

    @PostMapping("/addEmail")
    String addEmail(@RequestParam(value = "username") String username, @RequestBody String emailAndActive) {
        String[] twoParams = emailAndActive.split("\\$");
        String email = twoParams[0];
        String active = twoParams[1];
        boolean emailFlag = isValid(email);
        if (email.equals(active)) {
            return "You can't Add yourself !";
        }
        if (emailFlag) {
            System.out.println(email);
            contacts = readContacts(active);
            addNewEmail(username, email, active);
            System.out.println("HERE");
            store(contacts, active);
            System.out.println("HERE");
            return "Added";
        }
        return "Invalid";

    }

    void addNewEmail(String username, String email, String active) {
        System.out.println(active + "  ADD new Email");
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(contacts.get(i).getUsername())) {
                location = i;
                break;
            }
        }
        List<String> tempEmails = new LinkedList<String>(Arrays.asList(contacts.get(location).getEmails()));
        tempEmails.add(email);
        String[] newEmails = new String[tempEmails.size()];
        tempEmails.toArray(newEmails);
        contacts.get(location).emails = newEmails;

    }

    // checks if contact is already there
    boolean isExist(String username, String active) {
        contacts = readContacts(active);
        if (contacts.size() == 0) {
            return false;
        }

        // iterates over contact list to see if contact already exists
        for (int i = 0; i < contacts.size(); i++) {
            if (username.equals(contacts.get(i).getUsername())) {
                location = i;
                return true;
            }
        }
        return false;
    }

    // Checks if user is in database
    boolean isValid(String email) {
        users = readUsers();
        if (users.size() == 0) {
            return false;
        }

        // true -> valid false -> invalid
        for (int i = 0; i < users.size(); i++) {
            if (email.equals(users.get(i).getEmail())) {
                location = i;
                return true;
            }
        }
        return false;
    }

    // reads the contact json file and returns it as a list
    List<User> readUsers() {
        List<User> temp = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("allUsers.json");
            ObjectMapper om = new ObjectMapper();
            TypeReference tr = new TypeReference<List<User>>() {
            };
            temp = (List<User>) om.readValue(fis, tr);
            fis.close();

        } catch (Exception e) {

        }
        return temp;
    }

    List<Contact> readContacts(String active) {
        List<Contact> temp = new ArrayList<>();
        try {

            FileInputStream fis = new FileInputStream("./AllUsersFolders/" + active + "/" + active + "_cntacts.json");
            ObjectMapper om = new ObjectMapper();
            TypeReference tr = new TypeReference<List<Contact>>() {
            };
            temp = (List<Contact>) om.readValue(fis, tr);
            fis.close();

        } catch (Exception e) {

        }
        return temp;
    }

    // stores new list in contacts
    void store(List<Contact> contacts, String active) {
        try {

            System.out.println(active);
            FileOutputStream fos = new FileOutputStream(
                    "./AllUsersFolders/" + active + "/" + active + "_cntacts.json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(fos, contacts);
            fos.close();
        } catch (Exception e) {

        }
    }

    @PostMapping("/display")
    List<Contact> displayContacts(@RequestBody String active) {
        System.out.println(active + "In display");
        List<Contact> dis = new ArrayList<>();
        dis = readContacts(active);
        for (int i = 0; i < dis.size(); i++) {
            Contact temp = new Contact();
            temp.setUsername(dis.get(i).getUsername());
            temp.setEmails(dis.get(i).getEmails());
            temp.setEdit(false);
            dis.set(i, temp);
            store(dis, active);
        }
        System.out.println(dis.size());
        return dis;
    }

    @PostMapping("/edit")
    public List<Contact> edit(@RequestBody String usernameAndActive) {
        String[] twoParams = usernameAndActive.split("\\$");
        String username = twoParams[0];
        String active = twoParams[1];
        contacts = readContacts(active);
        // find the contact with specified username and make it editable
        for (int i = 0; i < contacts.size(); i++) {
            if (username.equals(contacts.get(i).getUsername())) {
                Contact temp = new Contact();
                temp.setUsername(contacts.get(i).getUsername());
                temp.setEmails(contacts.get(i).getEmails());
                temp.setEdit(true);
                contacts.set(i, temp);
                store(contacts, active);
            }
        }
        return contacts;
    }

    @PostMapping("/cancelEdit")
    public List<Contact> cancel(@RequestBody String usernameAndActive) {
        String[] twoParams = usernameAndActive.split("\\$");
        String username = twoParams[0];
        String active = twoParams[1];
        contacts = readContacts(active);
        for (int i = 0; i < contacts.size(); i++) {
            if (username.equals(contacts.get(i).getUsername())) {
                Contact temp = new Contact();
                temp.setUsername(contacts.get(i).getUsername());
                temp.setEmails(contacts.get(i).getEmails());
                temp.setEdit(false);
                contacts.set(i, temp);
                store(contacts, active);
            }
        }
        return contacts;
    }

    @PostMapping("/update")
    public List<Contact> update(@RequestParam(value = "username") String usernameAndActive, @RequestBody String[] obj) {
        String[] arr = usernameAndActive.split("\\$");
        // String username = twoParams[0];
        String active = arr[arr.length - 1];
        contacts = readContacts(active);
        users = readUsers();
        if (!checkAllEmails(obj, active)) {
            return contacts;
        }
        // String[] arr = username.split("\\$");
        String newUser = arr[0];
        String oldUser = arr[1];
        for (int i = 0; i < contacts.size(); i++) {
            if (oldUser.equals(contacts.get(i).getUsername())) {
                Contact temp = new Contact();
                temp.setUsername(newUser);
                temp.setEmails(obj);
                temp.setEdit(false);
                contacts.set(i, temp);
            }
        }
        store(contacts, active);
        displayContacts("");
        return contacts;
    }

    public boolean checkAllEmails(String[] emails, String active) {
        int checks = 0;
        for (int i = 0; i < emails.length; i++) {
            for (int j = 0; j < users.size(); j++) {
                if (emails[i].equals(users.get(j).getEmail())) {
                    if (!emails[i].equals(active)) {
                        checks++;
                    }
                }
            }
        }
        if (checks == emails.length) {
            return true;
        }
        return false;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "username") String usernameAndActive) {
        String[] twoParams = usernameAndActive.split("\\$");
        String username = twoParams[0];
        String active = twoParams[1];
        contacts = readContacts(active);
        System.out.println(username);
        for (int i = 0; i < contacts.size(); i++) {
            if (username.equals(contacts.get(i).getUsername())) {
                contacts.remove(i);
                break;
            }
        }
        store(contacts, active);
        System.out.println(contacts.size());
    }


    @PostMapping("/get")
    public List<Contact> get(String active) {
        contacts = readContacts(active);
        return contacts;
    }

    @PostMapping("/sort") //sorting is a string containng first letter A or D + $ + active user
    public List<Contact> sort(@RequestBody String sorting){
        String[] twoParams = sorting.split("\\$");
        String sort = twoParams[0];
        String username = twoParams[1];
        contacts = readContacts(username);
        if(sort.equals("A")){
        Collections.sort(contacts, new Comparator<Contact>(){
            @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getUsername().compareTo(c2.getUsername());
                }
        });}else{
            Collections.sort(contacts, new Comparator<Contact>(){
                @Override
                    public int compare(Contact c1, Contact c2) {
                        return c2.getUsername().compareTo(c1.getUsername());
                    }
            });
        }
        return contacts;
    }

    @PostMapping("/search") //searchString = activeUser + username to be searched
    public List<Contact> search(@RequestBody String searchString){
        String[] twoParam = searchString.split("\\$");
        String username = twoParam[0];
        String toSearch = twoParam[1];
        contacts = readContacts(username);
        List<Contact> searchResults = new ArrayList<Contact>();
        for(Contact contact : contacts){
            if(contact.getUsername().contains(toSearch)){
               searchResults.add(contact);
            }
        }
        return searchResults;

    }

}