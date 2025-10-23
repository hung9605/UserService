package com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.NotificationMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/notify")
    public ResponseEntity<String> notifyAll(@RequestBody NotificationMessage message) {
        messagingTemplate.convertAndSend("/topic/notifications", message);
        return ResponseEntity.ok("sent");
    }
    
    public void sendNotificationToUser(String username, String message) {
        messagingTemplate.convertAndSendToUser(username, "/queue/notify", message);
    }
}
