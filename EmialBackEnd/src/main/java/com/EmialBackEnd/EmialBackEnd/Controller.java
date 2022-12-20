package com.EmialBackEnd.EmialBackEnd;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/")
public class Controller {
    // sign up
    @PostMapping("/signUp")
    public boolean signUp(@RequestBody String input) {

        String[] arr = input.split("\\$");
        String name = arr[0];
        String pass = arr[1];

        if (Service.isValidName(name)) {
            Service.addUser(new User(name, pass));
            Service.creatUserFiles(name);
            return true;
        } else
            return false;
    }

    @PostMapping("/logIn")
    public boolean logIn(@RequestBody String input) {

        String[] arr = input.split("\\$");
        String name = arr[0];
        String pass = arr[1];

        Service.all = Service.getJasonList("allUsers.json");
        if (Service.all == null)
            return false;
        for (int i = 0; i < Service.all.size(); i++) {
            if (Service.all.get(i).getName().equals(name)) {
                if (Service.all.get(i).getPass().equals(pass))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

}
