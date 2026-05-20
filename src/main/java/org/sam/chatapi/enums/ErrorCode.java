package org.sam.chatapi.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
	SERVER_INTERNAL(HttpStatus.INTERNAL_SERVER_ERROR, "server.internal"),

	TOKEN_GENERATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "toke.generate_failed"),

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user.not_found"),
	USER_EMAIL_EXISTS(HttpStatus.BAD_REQUEST, "user.email.exists"),
	;

	HttpStatus status;
	String code;
}
