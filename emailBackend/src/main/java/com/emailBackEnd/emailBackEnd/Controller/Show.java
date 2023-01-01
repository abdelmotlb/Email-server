package com.emailBackEnd.emailBackEnd.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
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

    // downloading attachments parts:
    @PostMapping("/downloadAttach")
    public String download(@RequestBody String fileName) {

        System.out.println("file name in download attach " +fileName);

        String[] filesAndPath = fileName.split("\\#");
        for (int i = 0; i < filesAndPath.length - 1; i++) {
            File src = new File("./AllUsersFolders/" + "attaches/" + filesAndPath[i]);
            String name = filesAndPath[i].split("\\$")[1];
            File dest = new File(filesAndPath[filesAndPath.length - 1] + "/" + name);
            try {
                Files.copy(src.toPath(), dest.toPath());
                System.out.println("downed");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("cannot down");
            }

        }
        return "downloaded";
    }

}

