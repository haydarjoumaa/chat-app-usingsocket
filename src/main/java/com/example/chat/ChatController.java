package com.example.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.chat.dto.UserRequest;

import java.util.List;

@Controller
public class ChatController {

    private final ChatService service;
    private final SimpMessagingTemplate template;

    public ChatController(ChatService service, SimpMessagingTemplate template) {
        this.service = service;
        this.template = template;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage websocket(ChatMessage message) {
        return service.addMessage(message);
    }

    @PostMapping("/send")
    @ResponseBody
    public ChatMessage send(@RequestBody ChatMessage message) {
        ChatMessage saved = service.addMessage(message);
        template.convertAndSend("/topic/messages", saved);
        return saved;
    }

    @GetMapping("/history")
    @ResponseBody
    public List<ChatMessage> history() {
        return service.getHistory();
    }

    @PostMapping("/join")
    @ResponseBody
    public List<String> join(@RequestBody UserRequest user) {
        service.addUser(user.getName());
        List<String> users = List.copyOf(service.getUsers());
        template.convertAndSend("/topic/users", users);
        return users;
    }

    @PostMapping("/leave")
    @ResponseBody
    public List<String> leave(@RequestBody UserRequest user) {
        service.removeUser(user.getName());
        List<String> users = List.copyOf(service.getUsers());
        template.convertAndSend("/topic/users", users);
        return users;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<String> users() {
        return List.copyOf(service.getUsers());
    }
}
