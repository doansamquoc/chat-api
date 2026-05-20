package org.sam.chatapi.event;

import org.sam.chatapi.dto.request.SendMessageRequest;
import org.sam.chatapi.enums.MessageType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebSocketEventListener {
	SimpMessagingTemplate template;

	@EventListener
	public void handleWebSocketDisconnect(SessionDisconnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		if (accessor.getSessionAttributes() != null) {
			String username = (String) accessor.getSessionAttributes().get("username");
			if (username != null) {
				SendMessageRequest request = new SendMessageRequest(username, "Leave room", MessageType.LEAVE, Instant.now());
				template.convertAndSend("/topic/public", request);
			}
		}
	}

	@EventListener
	public void handleWebSocketConnected(SessionConnectedEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		if (accessor.getSessionAttributes() != null) {
			String username = (String) accessor.getSessionAttributes().get("username");
			if (username != null) {
				SendMessageRequest request = new SendMessageRequest(username, "Join room", MessageType.JOIN, Instant.now());
				template.convertAndSend("/topic/public", request);
			}
		}
	}
}
