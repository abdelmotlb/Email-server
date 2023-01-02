package com.emailBackEnd.emailBackEnd.Filter;

import com.emailBackEnd.emailBackEnd.Message;

import java.util.ArrayList;
import java.util.List;

public class SenderFilter implements ICriteria{

    @Override
    public List<Message> meetCriteria(List<Message> messages,String sender) {
        List<Message> target = new ArrayList<Message>();
        for(Message message : messages){
            if(message.getFrom().contains(sender)){
                target.add(message);
            }
        }
        return target;
    }


}
