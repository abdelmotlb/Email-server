package com.emailBackEnd.emailBackEnd;

public class Message {
    private String from;
    private String to;
    private String message;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    private Message(messageBuilder m) {
        this.from = m.from;
        this.to = m.to;
        this.message = m.message;
    }

    public static class messageBuilder {
        private String from;
        private String to;
        private String message;

        public messageBuilder(String from, String to, String message) {
            this.from = from;
            this.to = to;
            this.message = message;
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

        public Message build() {

            return new Message(this);

        }
    }
}
