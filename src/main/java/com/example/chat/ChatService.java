package com.example.chat;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class ChatService {
    private final List<ChatMessage> history = new CopyOnWriteArrayList<>();
    private final Set<String> users = new CopyOnWriteArraySet<>();

    public ChatMessage addMessage(ChatMessage message) {
        message.setTimestamp(Instant.now());
        history.add(message);
        if (history.size() > 100) {
            history.remove(0);
        }
        return message;
    }

    public List<ChatMessage> getHistory() {
        return List.copyOf(history);
    }

    public boolean addUser(String name) {
        return users.add(name);
    }

    public boolean removeUser(String name) {
        return users.remove(name);
    }

    public Set<String> getUsers() {
        return Set.copyOf(users);
    }
}
