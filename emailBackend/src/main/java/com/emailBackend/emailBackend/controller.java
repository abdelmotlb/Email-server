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

        if (isValidName(name)) {
            addUser(new User(name, pass));
            return true;
        } else
            return false;
    }

    @PostMapping("/logIn")
    public boolean logIn(@RequestBody String input) {

        String[] arr = input.split("\\$");
        String name = arr[0];
        String pass = arr[1];

        List<User> all = getJasonList("allUsers.json");
        if (all == null)
            return false;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getName().equals(name)) {
                if (all.get(i).getPass().equals(pass))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    // additional functions

    // get an array of users in json
    private List<User> getJasonList(String fileName) {
        try {
            FileInputStream fip = new FileInputStream(fileName);
            ObjectMapper OM = new ObjectMapper();
            TypeReference tr = new TypeReference<List<User>>() {
            };
            List<User> all = (List<User>) OM.readValue(fip, tr);
            return all;

        } catch (Exception e) {
            System.out.println("can not get");
            return null;
        }
    }

    // checks if the entered name is not used
    private boolean isValidName(String name) {
        List<User> all = getJasonList("allUsers.json");
        if (all == null)
            return true;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getName().equals(name))
                return false;
        }
        return true;
    }

    // add a user to all usders json file
    private void addUser(User newUser) {
        try {
            List<User> all = getJasonList("allUsers.json");
            all.add(newUser);
            ObjectMapper OM = new ObjectMapper();
            FileOutputStream fos = new FileOutputStream("allUsers.json");
            OM.writeValue(fos, all);
        } catch (Exception e) {
            System.out.println("not found file");
        }
    }

}
