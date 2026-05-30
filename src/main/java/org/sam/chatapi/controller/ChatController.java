package org.sam.chatapi.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.sam.chatapi.dto.request.SendMessageRequest;
import org.sam.chatapi.service.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
    ChatService chatService;

    @SendTo("/topic/conversations/{conversationId}")
    @MessageMapping("/chat.sendMessage/{conversationId}")
    public void sendMessage(
        @DestinationVariable Long conversationId,
        @Payload SendMessageRequest request,
        Jwt jwt
    ) {
        log.info("User ID: {}", jwt.getSubject());
        chatService.sendMessage(Long.valueOf(jwt.getSubject()), conversationId, request);
    }
}
