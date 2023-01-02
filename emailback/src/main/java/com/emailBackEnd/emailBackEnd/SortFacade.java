package com.emailBackEnd.emailBackEnd;

import java.util.*;

public class SortFacade {

    public static void sortBySender(List<Message> messages,boolean IsAscending){
        // Sort the list using a Comparator
        if(IsAscending){
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                    return m1.getFrom().compareTo(m2.getFrom());
                }
            });
        }else{
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                    return m2.getFrom().compareTo(m1.getFrom());
                }
            });
        }

    }
    public static void sortByReciever(List<Message> messages,boolean IsAscending){
        if(IsAscending){
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                    return m1.getTo().compareTo(m2.getTo());
                }
            });
        }else{
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                    return m2.getTo().compareTo(m1.getTo());
                }
            });
        }
    }
    public static void sortByDate(List<Message> messages,boolean IsAscending){
        if(IsAscending){
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                    return m1.getDate().compareTo(m2.getDate());
                }
            });
        }else{
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                    return m2.getDate().compareTo(m1.getDate());
                }
            });
        }
    }
//    public static void sortByImportance(List<Message> messages,boolean IsAscending){
//
//    }
    public static void sortBySubject(List<Message> messages,boolean IsAscending){
        if(IsAscending){
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                    return m1.getSubject().compareTo(m2.getSubject());
                }
            });
        }else{
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message m1, Message m2) {
                    return m2.getSubject().compareTo(m1.getSubject());
                }
            });
        }
    }
}
