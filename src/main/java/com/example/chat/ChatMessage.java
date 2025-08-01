package com.example.chat;

import java.time.Instant;

public class ChatMessage {
    private String from;
    private String content;
    private Instant timestamp;

    public ChatMessage() {}

    public ChatMessage(String from, String content) {
        this.from = from;
        this.content = content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
