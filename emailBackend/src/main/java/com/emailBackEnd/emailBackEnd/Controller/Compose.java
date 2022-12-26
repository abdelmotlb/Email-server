package com.emailBackEnd.emailBackEnd.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailBackEnd.emailBackEnd.Message;
import com.emailBackEnd.emailBackEnd.Service;

//home
public class Compose {
    LogIn log = new LogIn();
    String activeUser = log.User();
    @PostMapping("/Compose")
    public String composeMessage(@RequestBody String from_to_message) {
        System.out.println("in compose in controller " + from_to_message + "  active: " + activeUser);
        String[] message_part = from_to_message.split("\\$");
        Message message = new Message.messageBuilder()
                .setFrom(activeUser)
                .setTo(message_part[0])
                .setSubject(message_part[1])
                .setMessage(message_part[2])
                .build();
        // if the message is empty the message moves to the draft of the sender
        // elif the message is not empty it moves to the sent of sender and inbox of the
        // reciever
        if (message.getMessage().length() == 0) {
            return Service.Draft(message);
        } else {
            // check if the user the message is sent to is available or not
            if (!Service.isValidName(message.getTo())) {
                Service.sentOfTheSender(message);

                return Service.inboxOfTheReciever(message);
            } else {
                return "The person you try to send to is not registered";
            }
        }
        // return "message recieved";
    }
}
