package org.sam.chatapi.dto.api;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(boolean success, String message, T data) {
	public static <T> ApiResponse<T> of(T data, String message) {
		return new ApiResponse<>(true, message, data);
	}

	public static <T> ApiResponse<T> of(T data) {
		return new ApiResponse<>(true, "Successfully", data);
	}

	public static <T> ApiResponse<String> of(String message) {
		return new ApiResponse<>(true, message, null);
	}

	/**
	 * No content
	 *
	 * @return ApiResponse
	 */
	public static <T> ApiResponse<?> of() {
		return new ApiResponse<>(true, null, null);
	}
}
