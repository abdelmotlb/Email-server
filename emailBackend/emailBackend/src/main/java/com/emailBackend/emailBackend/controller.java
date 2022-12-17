package com.emailBackend.emailBackend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class controller {

    // sign up
    @PostMapping("/signUp")
    public boolean signUp(@RequestBody String input) {

        String[] arr = input.split("\\$");
        String name = arr[0];
        String pass = arr[1];

        if (helpFunction.isValidName(name)) {
            helpFunction.addUser(new User(name, pass));
            return true;
        } else
            return false;
    }

    @PostMapping("/logIn")
    public boolean logIn(@RequestBody String input) {

        String[] arr = input.split("\\$");
        String name = arr[0];
        String pass = arr[1];

        helpFunction.all = helpFunction.getJasonList("allUsers.json");
        if (helpFunction.all == null)
            return false;
        for (int i = 0; i < helpFunction.all.size(); i++) {
            if (helpFunction.all.get(i).getName().equals(name)) {
                if (helpFunction.all.get(i).getPass().equals(pass))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

}
