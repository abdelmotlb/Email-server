package com.emailBackEnd.emailBackEnd.Filter;

import com.emailBackEnd.emailBackEnd.Message;

import java.util.ArrayList;
import java.util.List;

public class RecieverFilter implements ICriteria{

    @Override
    public List<Message> meetCriteria(List<Message> messages,String reciever) {
        List<Message> target = new ArrayList<Message>();
        for(Message message : messages){
            if(message.getTo().equalsIgnoreCase(reciever)){
                target.add(message);
            }
        }
        return target;
    }


}
