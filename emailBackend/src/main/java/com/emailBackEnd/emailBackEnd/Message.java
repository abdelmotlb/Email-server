package com.emailBackEnd.emailBackEnd;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String from;
    private String to;
    private String subject;
    private String message;
    private ArrayList<String> attachment = new ArrayList<String>();

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

    public List<String> getAttachment() {
        return attachment;
    }

    public void addAttachment(String attachName) {
        this.attachment.add(attachName);
    }

    private Message(messageBuilder m) {
        this.from = m.from;
        this.to = m.to;
        this.subject = m.subject;
        this.message = m.message;
    }

    private Message() {
    }

    public static class messageBuilder {
        private String from;
        private String to;
        private String message;
        private String subject;

        public messageBuilder(String from, String to, String message) {
            this.from = from;
            this.to = to;
            this.message = message;
            this.subject = subject;
        }

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

        public Message build() {

            return new Message(this);

        }
    }
}
