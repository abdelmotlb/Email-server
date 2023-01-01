package com.emailBackEnd.emailBackEnd.Filter;

import com.emailBackEnd.emailBackEnd.Message;

import java.util.List;

public class AndCriteria implements ICriteria{
    ICriteria subject;
    ICriteria reciever;
    public AndCriteria( ICriteria subject, ICriteria reciever){
        this.subject = subject;
        this.reciever = reciever;
    }
    @Override
    public List<Message> meetCriteria(List<Message> messages, String target) {
        String[] SubjectThenReciever = target.split("\\$");
        String TargetSubj = SubjectThenReciever[0];
        String TargetReciever = SubjectThenReciever[1];

        List<Message> SubjectFilterred = this.subject.meetCriteria(messages,TargetSubj);
        List<Message> AndFilter = this.reciever.meetCriteria(SubjectFilterred,TargetReciever);

        return AndFilter;

    }
}
