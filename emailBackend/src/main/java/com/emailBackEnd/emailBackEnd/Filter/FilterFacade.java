package com.emailBackEnd.emailBackEnd.Filter;

import com.emailBackEnd.emailBackEnd.Message;
import com.emailBackEnd.emailBackEnd.Service;

import java.util.List;

public class FilterFacade {
    //Filter type means if he wants to filer by subject or reciever or both
    public static List<Message> filter(String UserName_FolderName_FilterType_Subj_Reciever){
        String[] partition = UserName_FolderName_FilterType_Subj_Reciever.split("\\$");
        String UserName = partition[0];
        String FolderName = partition[1];
        String FilterType = partition[2];
        String Subject = partition[3];
        String Reciever = partition[4];

        ICriteria filter;

        List<Message> messages = Service.getJasonMessageList(UserName,FolderName);

        if(FilterType.equalsIgnoreCase("Both")){
            filter = new AndCriteria(new SubjectFilter(),new RecieverFilter());
            return filter.meetCriteria(messages,Subject+"$"+Reciever);
        }
        else if(FilterType.equalsIgnoreCase("Subject")){
            filter = new SubjectFilter();
            return filter.meetCriteria(messages,Subject);
        }else if(FilterType.equalsIgnoreCase("Reciever")){
            filter = new RecieverFilter();
            return filter.meetCriteria(messages,Reciever);
        }
        return null;
    }
}
