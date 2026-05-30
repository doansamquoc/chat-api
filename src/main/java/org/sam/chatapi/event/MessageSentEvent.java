package org.sam.chatapi.event;

import org.sam.chatapi.entity.Message;

public record MessageSentEvent(Message message) {}
