package com.emailBackEnd.emailBackEnd.Filter;

import com.emailBackEnd.emailBackEnd.Message;

import java.util.List;

public interface ICriteria {
    public List<Message> meetCriteria(List<Message> messages,String target);
}
