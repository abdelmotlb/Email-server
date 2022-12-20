package com.emailBackEnd.emailBackEnd;

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

    @PostMapping("/Compose")
    public String composeMessage(@RequestBody String from_to_message) {
        String[] message_part = from_to_message.split("\\$");
        Message message = new Message.messageBuilder()
                .setFrom(message_part[0])
                .setTo(message_part[1])
                .setMessage(message_part[2])
                .build();
        // if the message is empty the message moves to the draft of the sender
        // elif the message is not empty it moves to the sent of sender and inbox of the
        // reciever
        if (message.getMessage().length() == 0) {
            return Service.Draft(message);
        } else {
            // check if the user the message is sent to is available or not
            if (Service.isValidName(message.getTo())) {
                Service.sentOfTheSender(message);
                return Service.inboxOfTheReciever(message);
            } else {
                return "The person you try to send to is not registered";
            }
        }
    }

}
