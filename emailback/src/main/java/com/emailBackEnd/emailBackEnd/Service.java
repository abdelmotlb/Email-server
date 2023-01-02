package com.emailBackEnd.emailBackEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
        System.out.println("in isValid");
        all = getJasonList("allUsers.json");
        if (all == null)
            return true;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getEmail().equals(name))
                return false;
        }
        return true;
    }

    // Send a message to draft of the sender
    public static String Draft(Message message) {
        try {
            messages = getJasonMessageList(message.getFrom(), "draft");
            messages.add(0, message);
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
            messages.add(0, message);
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
            String s = message.getFrom();
            messages = getJasonMessageList(message.getFrom(), "sent");
            messages.add(0, message);
            ObjectMapper OM = new ObjectMapper();
            FileOutputStream fos = new FileOutputStream(
                    "allUsersFolders/" + message.getFrom() + "/" + message.getFrom() + "_" +
                            "sent.json");
            OM.writeValue(fos, messages);
        } catch (Exception e) {
            System.out.println("Error in sentOfTheSender");
        }
    }

    public static List<Message> Search(String ActiveUserName_FolderName_Search) {

        String[] SearchInfo = ActiveUserName_FolderName_Search.split("\\$");
        String ActiveUserName = SearchInfo[0];
        String FolderName = SearchInfo[1];
        String SearchTo = SearchInfo[2];

        List<Message> messages = getJasonMessageList(ActiveUserName, FolderName);
        List<Message> SearchResults = new ArrayList<Message>();

        for (Message message : messages) {
            if (message.getSubject().contains(SearchTo) ||
                    message.getMessage().contains(SearchTo) ||
                    message.getFrom().equalsIgnoreCase(SearchTo) ||
                    message.getTo().equalsIgnoreCase(SearchTo) ||
                    message.getAttachment().contains(SearchTo)) {
                SearchResults.add(0, message);
            }
        }
        return SearchResults;
    }

    // ActiveUser name is the the signed in person, Folder name the of the folder he
    // wants to sort SortBy is sorting criteria IsAscending is obvious
    // SortBy has the arguments of Date, Sender, Reciever, Importance, Subject
    public static List<Message> Sort(String ActiveUserName_FolderName_SortBy_IsAscending) {
        String[] SortInfo = ActiveUserName_FolderName_SortBy_IsAscending.split("\\$");
        String ActiveUserName = SortInfo[0];
        String FolderName = SortInfo[1];
        String SortBy = SortInfo[2];
        boolean IsAscending = SortInfo[3].equalsIgnoreCase("1");

        List<Message> messages = getJasonMessageList(ActiveUserName, FolderName);

        if (SortBy.equalsIgnoreCase("Date")) {
            SortFacade.sortByDate(messages, IsAscending);
        } else if (SortBy.equalsIgnoreCase("Sender")) { // Sender means From
            SortFacade.sortBySender(messages, IsAscending);
        } else if (SortBy.equalsIgnoreCase("Reciever")) { // Reciever means To
            SortFacade.sortByReciever(messages, IsAscending);
        } else if (SortBy.equalsIgnoreCase("Subject")) {
            SortFacade.sortBySubject(messages, IsAscending);
        }
        // else if(SortBy.equalsIgnoreCase("Importance"){}
        return messages;

    }
}
