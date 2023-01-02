package com.emailBackEnd.emailBackEnd;

import java.util.ArrayList;
import java.util.List;

// this class is used to check if a message contains a specific attachment
public class FindInList {
    public static boolean IsFoundInAttachments(Message message,String Search){
        boolean isFound = false;
        List<String> attachments = message.getAttachment();
        for(String s : attachments){
            if(s.contains(Search))
                isFound = true;
        }
        return isFound;
    }
}
