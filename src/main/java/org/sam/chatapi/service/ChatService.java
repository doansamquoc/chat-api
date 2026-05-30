package org.sam.chatapi.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.sam.chatapi.dto.request.SendMessageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatService {
    SimpMessagingTemplate template;

    public void sendMessage(Long userId, Long conversationId, SendMessageRequest request) {
        template.convertAndSend("/topic/public", request);
    }
}
