package com.emailBackEnd.emailBackEnd;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private String from;
    private String to;
    private String subject;
    private String message;
    private String priorty;
    private ArrayList<String> attachment = new ArrayList<String>();
    private String date;
    private String id;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getAttachment() {
        return attachment;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getPriorty() {
        return priorty;
    }

    public void addAttachment(String attachName) {
        this.attachment.add(attachName);
    }

    private Message(messageBuilder m) {
        this.from = m.from;
        this.to = m.to;
        this.subject = m.subject;
        this.message = m.message;
        this.priorty = m.priorty;
        this.id = m.id;
        this.date = m.date;
    }

    private Message() {
    }

    public static class messageBuilder {
        private String from;
        private String to;
        private String message;
        private String subject;
        private String priorty;
        private ArrayList<String> attachment = new ArrayList<String>();
        private String date;
        private String id;

        // public messageBuilder(String from, String to, String message) {
        // this.from = from;
        // this.to = to;
        // this.message = message;
        // this.subject = subject;
        // }

        public messageBuilder() {
        }

        public messageBuilder setFrom(String from) {
            this.from = from;
            return this;
        }

        public messageBuilder setTo(String to) {
            this.to = to;
            return this;
        }

        public messageBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public messageBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public messageBuilder setPriorty(String priorty) {
            this.priorty = priorty;
            return this;
        }

        public messageBuilder setDate() {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            this.date = myDateObj.format(myFormatObj);
            return this;
        }

        public messageBuilder setId() {
            this.id = Long.toString(System.currentTimeMillis());
            return this;
        }

        public messageBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public messageBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public messageBuilder setAttach(ArrayList<String> attachment) {
            this.attachment = attachment;
            return this;
        }

        public Message build() {

            return new Message(this);

        }
    }
}