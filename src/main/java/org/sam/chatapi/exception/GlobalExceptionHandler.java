package org.sam.chatapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.sam.chatapi.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GlobalExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e, HttpServletRequest request) {
		ErrorCode error = e.getErrorCode();
		// TODO: Do translate here
		String message = error.getCode();
		ErrorResponse response = ErrorResponse.of(error.name(), message, request.getRequestURI());
		return ResponseEntity.status(error.getStatus()).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
		log.error("Exception From {}", e.getClass());
		ErrorCode error = ErrorCode.SERVER_INTERNAL;
		// TODO: Do translate here
		String message = error.getCode();
		ErrorResponse response = ErrorResponse.of(error.name(), message, request.getRequestURI());
		return ResponseEntity.status(error.getStatus()).body(response);
	}
}
