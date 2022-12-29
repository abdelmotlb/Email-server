package com.emailBackEnd.emailBackEnd.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailBackEnd.emailBackEnd.Service;
import com.emailBackEnd.emailBackEnd.User;

@CrossOrigin
@RestController
@RequestMapping("/")
public class LogIn {

    // public static String activeUser = "";

    // sign up
    @PostMapping("/signUp")
    public boolean signUp(@RequestBody String input) {

        String[] arr = input.split("\\$");
        String email = arr[0];
        String pass = arr[1];

        if (Service.isValidName(email)) {
            Service.addUser(User.getInstance(email, pass));
            Service.creatUserFiles(email);
            // activeUser = email;
            return true;
        } else
            return false;
    }

    @PostMapping("/logIn")
    public boolean logIn(@RequestBody String input) {

        String[] arr = input.split("\\$");
        String email = arr[0];
        // activeUser = arr[0];
        String pass = arr[1];

        Service.all = Service.getJasonList("allUsers.json");
        if (Service.all == null)
            return false;
        for (int i = 0; i < Service.all.size(); i++) {
            if (Service.all.get(i).getEmail().equals(email)) {
                if (Service.all.get(i).getPass().equals(pass)) {
                    // activeUser = email;
                    // System.out.println(activeUser +" LOGIN");
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

    // return the activeUser for the other classes
    // public String User(){
    // return activeUser;
    // }

    // @PostMapping("/signOut")
    // public void signOut(@RequestBody String username){
    // activeUser = "";
    // }

}
