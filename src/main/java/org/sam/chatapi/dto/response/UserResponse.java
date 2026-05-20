package org.sam.chatapi.dto.response;

import org.sam.chatapi.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
	Long id,
	String username,
	String email,
	String displayName,
	LocalDate dob,
	Gender gender,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
