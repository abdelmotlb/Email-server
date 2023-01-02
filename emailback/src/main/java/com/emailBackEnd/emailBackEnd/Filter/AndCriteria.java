package com.emailBackEnd.emailBackEnd.Filter;

import com.emailBackEnd.emailBackEnd.Message;

import java.util.List;

public class AndCriteria implements ICriteria{
    ICriteria subject;
    ICriteria sender;
    public AndCriteria( ICriteria subject, ICriteria sender){
        this.subject = subject;
        this.sender = sender;
    }
    @Override
    public List<Message> meetCriteria(List<Message> messages, String target) {
        String[] SubjectThenSender = target.split("\\$");
        String TargetSubj = SubjectThenSender[0];
        String TargetSender = SubjectThenSender[1];

        List<Message> SubjectFilterred = this.subject.meetCriteria(messages,TargetSubj);
        List<Message> AndFilter = this.sender.meetCriteria(SubjectFilterred,TargetSender);

        return AndFilter;

    }
}
