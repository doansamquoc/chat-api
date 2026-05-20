package org.sam.chatapi.dto.request;

import org.sam.chatapi.enums.MessageType;

import java.time.Instant;

public record SendMessageRequest(
	String sender,
	String message,
	MessageType type,
	Instant timestamp
) {
}
