package org.sam.chatapi.controller;

import org.sam.chatapi.dto.request.SendMessageRequest;
import org.sam.chatapi.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
	ChatService chatService;

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public void sendMessage(@Payload SendMessageRequest request) {
		chatService.processAndBroadcastMessage(request);
	}
}
