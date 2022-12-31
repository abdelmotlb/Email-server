package com.emailBackEnd.emailBackEnd.Controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailBackEnd.emailBackEnd.Message;
import com.emailBackEnd.emailBackEnd.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/")
public class Show {
    @PostMapping("/getMessages")
    public List<Message> getInbox(@RequestBody String activeAndfile){

        System.out.println("in getInbox");

        String[] arr = activeAndfile.split("\\$");
        String active = arr[0];
        String file = arr[1];
        List<Message> inboxMessages = getMessagesList( "./AllUsersFolders/" + active + "/" + active +"_"+file+".json");
        return inboxMessages;
    }


    public static List<Message> getMessagesList(String fileName) {
        try {
            FileInputStream fip = new FileInputStream(fileName);
            ObjectMapper OM = new ObjectMapper();
            TypeReference tr = new TypeReference<List<Message>>() {
            };
            List<Message> all = (List<Message>) OM.readValue(fip, tr);
            return all;

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("can not get");
            return null;
        }
    }
}
