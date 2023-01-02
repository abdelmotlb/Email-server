package com.emailBackEnd.emailBackEnd.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emailBackEnd.emailBackEnd.Message;
import com.emailBackEnd.emailBackEnd.Service;

@CrossOrigin
@RestController
@RequestMapping("/")
public class Compose {
    private Message message;
    private String todraft = "";

    @PostMapping("/Compose")
    public String composeMessage(@RequestBody String from_to_message) {
        String[] message_part = from_to_message.split("\\$");
        todraft = message_part[5];
        System.out.println("in compose in controller " + from_to_message + "  active: " + message_part[0]);
        message = new Message.messageBuilder()
                .setId()
                .setDate()
                .setFrom(message_part[0])
                .setTo(message_part[1])
                .setSubject(message_part[2])
                .setMessage(message_part[3])
                .setPriorty(message_part[4])
                .build();
        // if the message is empty the message moves to the draft of the sender
        // elif the message is not empty it moves to the sent of sender and inbox of the
        // reciever
        if (message_part[6].equals("no")) {
            if (message.getMessage().length() == 0 || todraft.equals("draft")) {
                return Service.Draft(message);
            } else {
                // check if the user the message is sent to is available or not
                String[] recievers = message.getTo().split("\\,");
                boolean chck = true;
                String notFound = "";
                for (String i : recievers) {
                    if (Service.isValidName(i)) {
                        chck = false;
                        notFound = i;
                        break;
                    }
                }
                if (chck) {
                    Service.sentOfTheSender(message);
                    return Service.inboxOfTheReciever(message);
                } else {
                    return notFound + " is not registered";
                }
            }
        }
        return "wait attach";

    }

    @PostMapping("/Attach")
    public String Attach(@RequestParam("file") List<MultipartFile> sentFiles) {
        try {
            for (MultipartFile file : sentFiles) {
                File newAttach = new File(
                        "./AllUsersFolders/attaches/" + System.currentTimeMillis() + "$" + file.getOriginalFilename());
                FileOutputStream fout = new FileOutputStream(newAttach);
                fout.write(file.getBytes());
                fout.close();
                message.addAttachment(newAttach.getName());
            }

        } catch (Exception e) {
            System.out.println("cannot send");
            return "cannot send";
        }

        // if the message is empty the message moves to the draft of the sender
        // elif the message is not empty it moves to the sent of sender and inbox of the
        // reciever

        if (message.getMessage().length() == 0 || todraft.equals("draft")) {
            return Service.Draft(message);
        } else {
            // check if the user the message is sent to is available or not
            String[] recievers = message.getTo().split("\\,");
            boolean chck = true;
            String notFound = "";
            for (String i : recievers) {
                if (Service.isValidName(i)) {
                    chck = false;
                    notFound = i;
                    break;
                }
            }
            if (chck) {
                Service.sentOfTheSender(message);
                return Service.inboxOfTheReciever(message);
            } else {
                return notFound + " is not registered";
            }
        }
    }
}
