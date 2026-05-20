package org.sam.chatapi.dto.response;

public record AuthResponse(String accessToken, UserResponse user) {}
