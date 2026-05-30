package org.sam.chatapi.dto.request;

import org.sam.chatapi.enums.MessageType;

import java.time.Instant;

public record SendMessageRequest(
    MessageType type,
    String content,
    Long replyToId,
    Instant timestamp
) {}
