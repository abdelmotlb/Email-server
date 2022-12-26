package com.emailBackEnd.emailBackEnd.Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.emailBackEnd.emailBackEnd.Contact;
import com.emailBackEnd.emailBackEnd.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
    String add(@RequestParam(value = "username") String username, @RequestBody String email){
        //check in database
        boolean found = isValid(email);
        if(!found){
            return "Email doesn't exist";
        }
        //check in contacts
        boolean exists = isExist(username);
        if(exists){
            //append to list of emails
            return "Choose another name for the contact";
        }
        contacts = readContacts();
        Contact newContact = new Contact();
        newContact.username = username;
        String[] tempEmail = {email.toString()};
        newContact.emails = tempEmail;
        contacts.add(newContact);
        store(contacts);
          
        return "Added Successfully";
    }


    @PostMapping("/addEmail")
    String addEmail(@RequestParam(value = "username") String username, @RequestBody String email){
        boolean emailFlag = isValid(email);
        if(emailFlag){
        System.out.println(email);
        contacts = readContacts();
        addNewEmail(username, email);
        System.out.println("HERE"); 
        store(contacts);
        System.out.println("HERE"); 
        return "Added";
        }
        return "Invalid";

    }

    void addNewEmail(String username, String email){
        String currentUser = LogIn.activeUser;
        System.out.println(currentUser);
        for(int i = 0; i < users.size(); i++){
            if(currentUser.equals(contacts.get(i).getUsername())){
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


    //checks if contact is already there
    boolean isExist(String username){
        contacts = readContacts();
        if(contacts.size() == 0){
            return false;
        }

        //iterates over contact list to see if contact already exists
        for(int i = 0; i < contacts.size(); i++){
            if(username.equals(contacts.get(i).getUsername())){
             location = i;
             return true;
            } 
          }
          return false;   
    }

    //Checks if user is in database
    boolean isValid(String email){
            users = readUsers(); 
            if(users.size() == 0){
                return false;
            }
   
            //true -> valid  false -> invalid
            for(int i = 0; i < users.size(); i++){
              if(email.equals(users.get(i).getEmail())){
                location = i;
               return true;
              } 
            }
            return false;
    }   
           
    //reads the contact json file and returns it as a list
    List<User> readUsers(){
        List<User> temp = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("allUsers.json");
            ObjectMapper om = new ObjectMapper();
            TypeReference tr = new TypeReference<List<User>>() {};
            temp = (List<User>) om.readValue(fis, tr); 
            fis.close(); 
            
        }catch(Exception e){
            
      }
        return temp;
    }

    List<Contact> readContacts(){
        List<Contact> temp = new ArrayList<>();
        try {
            String currentUser = log.User();
            FileInputStream fis = new FileInputStream("./AllUsersFolders/" + currentUser + "/" + currentUser + "_cntacts.json");
            ObjectMapper om = new ObjectMapper();
            TypeReference tr = new TypeReference<List<Contact>>() {};
            temp = (List<Contact>) om.readValue(fis, tr); 
            fis.close(); 
            
        }catch(Exception e){
            
      }
        return temp;
    }
    
    //stores new list in contacts
    void store (List<Contact> contacts){
        try{
            String currentUser = log.User();
            System.out.println(currentUser);
            FileOutputStream fos = new FileOutputStream("./AllUsersFolders/" + currentUser + "/" + currentUser + "_cntacts.json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(fos, contacts);
            fos.close();
        }catch(Exception e){

      }
   }

    @PostMapping("/display")
    List<Contact> displayContacts(@RequestBody String username){
        List<Contact> dis = new ArrayList<>();
        dis = readContacts();
        System.out.println(dis.size());
        return dis;
   }

}
