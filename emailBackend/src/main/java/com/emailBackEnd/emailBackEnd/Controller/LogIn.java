package com.EmailBackEnd.EmailBackEnd.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmailBackEnd.EmailBackEnd.Service;
import com.EmailBackEnd.EmailBackEnd.User;


@CrossOrigin
@RestController
@RequestMapping("/")
public class LogIn {
    public String activeUser = "";

    // sign up
    @PostMapping("/signUp")
    public boolean signUp(@RequestBody String input) {

        String[] arr = input.split("\\$");
        String email = arr[0];
        String pass = arr[1];

        if (Service.isValidName(email)) {
            Service.addUser(User.getInstance(email, pass));
            Service.creatUserFiles(email);
            activeUser = email;
            return true;
        } else
            return false;
    }

    @PostMapping("/logIn")
    public boolean logIn(@RequestBody String input) {

        String[] arr = input.split("\\$");
        String email = arr[0];
        String pass = arr[1];

        Service.all = Service.getJasonList("allUsers.json");
        if (Service.all == null)
            return false;
        for (int i = 0; i < Service.all.size(); i++) {
            if (Service.all.get(i).getEmail().equals(email)) {
                if (Service.all.get(i).getPass().equals(pass)) {
                    activeUser = email;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }
}
