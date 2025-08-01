package com.example.chat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChatServiceTest {
    @Test
    void historyLimitedTo100() {
        ChatService service = new ChatService();
        for (int i = 0; i < 120; i++) {
            service.addMessage(new ChatMessage("u", "m" + i));
        }
        assertEquals(100, service.getHistory().size());
        assertEquals("m20", service.getHistory().get(0).getContent());
    }

    @Test
    void userJoinAndLeave() {
        ChatService service = new ChatService();
        assertTrue(service.addUser("alice"));
        assertTrue(service.getUsers().contains("alice"));
        assertTrue(service.removeUser("alice"));
        assertFalse(service.getUsers().contains("alice"));
    }
}
