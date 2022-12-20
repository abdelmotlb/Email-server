package com.emailBackEnd.emailBackEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Service {
    public static List<User> all;
    public static List<Message> messages;

    // get an array of users in json
    public static List<User> getJasonList(String fileName) {
        try {
            FileInputStream fip = new FileInputStream(fileName);
            ObjectMapper OM = new ObjectMapper();
            TypeReference tr = new TypeReference<List<User>>() {
            };
            List<User> all = (List<User>) OM.readValue(fip, tr);
            return all;

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("can not get");
            return null;
        }
    }

    // get an array of Messages in json
    public static List<Message> getJasonMessageList(String userfolder, String typeOfFile) {
        try {
            FileInputStream fip = new FileInputStream(
                    "allUsersFolders/" + userfolder + "/" + userfolder + "_" + typeOfFile + ".json");
            ObjectMapper OM = new ObjectMapper();
            TypeReference tr = new TypeReference<List<Message>>() {
            };
            List<Message> messages = (List<Message>) OM.readValue(fip, tr);
            return messages;

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("can not get");
            return null;
        }
    }

    // checks if the entered name is not used
    public static boolean isValidName(String name) {
        all = getJasonList("allUsers.json");
        if (all == null)
            return true;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getName().equals(name))
                return false;
        }
        return true;
    }

    // add a user to all usders json file
    public static void addUser(User newUser) {
        try {
            all = getJasonList("allUsers.json");
            all.add(newUser);
            ObjectMapper OM = new ObjectMapper();
            FileOutputStream fos = new FileOutputStream("allUsers.json");
            OM.writeValue(fos, all);
        } catch (Exception e) {
            System.out.println("not found file");
        }
    }

    // creat user folder and in it 5 user files
    public static void creatUserFiles(String filename) {
        try {
            File f1 = new File("./allUsersFolders/" + filename);
            f1.mkdir();
            String path = f1.getAbsolutePath();
            String[] arr = {};
            ObjectMapper map = new ObjectMapper();
            FileOutputStream fos = new FileOutputStream(path + "/" + filename + "_inbox.json");
            map.writeValue(fos, arr);
            fos = new FileOutputStream(path + "/" + filename + "_sent.json");
            map.writeValue(fos, arr);
            fos = new FileOutputStream(path + "/" + filename + "_trash.json");
            map.writeValue(fos, arr);
            fos = new FileOutputStream(path + "/" + filename + "_draft.json");
            map.writeValue(fos, arr);
            fos = new FileOutputStream(path + "/" + filename + "_cntacts.json");
            map.writeValue(fos, arr);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // Send a message to draft of the sender
    public static String Draft(Message message) {
        try {
            messages = getJasonMessageList(message.getFrom(), "draft");
            messages.add(message);
            ObjectMapper OM = new ObjectMapper();
            FileOutputStream fos = new FileOutputStream(
                    "allUsersFolders/" + message.getFrom() + "/" + message.getFrom() + "_" +
                            "draft.json");
            OM.writeValue(fos, messages);
        } catch (Exception e) {
            System.out.println("Error in Draft");
        }
        return "Draft";
    }

    public static String inboxOfTheReciever(Message message) {
        // if the the user who the message is sent to is available on the system then
        // put the message in his inbox else say the user is not found
        try {
            messages = getJasonMessageList(message.getTo(), "inbox");
            messages.add(message);
            ObjectMapper OM = new ObjectMapper();
            FileOutputStream fos = new FileOutputStream(
                    "allUsersFolders/" + message.getTo() + "/" + message.getTo() + "_" +
                            "inbox.json");
            OM.writeValue(fos, messages);
        } catch (Exception e) {
            System.out.println("Error in inboxOfTheReciever");
        }
        return "inboxOfTheReciever";
    }

    public static void sentOfTheSender(Message message) {
        try {
            messages = getJasonMessageList(message.getFrom(), "sent");
            messages.add(message);
            ObjectMapper OM = new ObjectMapper();
            FileOutputStream fos = new FileOutputStream(
                    "allUsersFolders/" + message.getFrom() + "/" + message.getFrom() + "_" +
                            "sent.json");
            OM.writeValue(fos, messages);
        } catch (Exception e) {
            System.out.println("Error in sentOfTheSender");
        }
    }
}
