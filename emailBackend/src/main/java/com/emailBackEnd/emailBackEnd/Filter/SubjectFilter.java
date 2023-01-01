package com.emailBackEnd.emailBackEnd.Filter;

import com.emailBackEnd.emailBackEnd.Message;

import java.util.ArrayList;
import java.util.List;

public class SubjectFilter implements ICriteria{
    @Override
    public List<Message> meetCriteria(List<Message> messages, String subject) {
        List<Message> target = new ArrayList<Message>();
        for(Message message : messages){
            if(message.getSubject().equalsIgnoreCase(subject)){
                target.add(message);
            }
        }
        return target;
    }
}
