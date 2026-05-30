package org.sam.chatapi.event.listener;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebSocketEventListener {
    SimpMessagingTemplate template;

//    @EventListener
//    public void handleWebSocketDisconnect(SessionDisconnectEvent event) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
//        if (accessor.getSessionAttributes() != null) {
//            Long username = (Long) accessor.getSessionAttributes().get("username");
//            if (username != null) {
//                SendMessageRequest request = new SendMessageRequest(username, "Leave room", MessageType.SYSTEM, Instant.now());
//                template.convertAndSend("/topic/public", request);
//            }
//        }
//    }
//
//    @EventListener
//    public void handleWebSocketConnected(SessionConnectedEvent event) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
//        if (accessor.getSessionAttributes() != null) {
//            Long username = (Long) accessor.getSessionAttributes().get("username");
//            if (username != null) {
//                SendMessageRequest request = new SendMessageRequest(username, "Join room", MessageType.SYSTEM, Instant.now());
//                template.convertAndSend("/topic/public", request);
//            }
//        }
//    }
}
