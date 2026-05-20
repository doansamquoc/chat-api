package org.sam.chatapi.controller;

import org.sam.chatapi.dto.api.ApiResponse;
import org.sam.chatapi.dto.request.LoginRequest;
import org.sam.chatapi.dto.request.UserCreationRequest;
import org.sam.chatapi.dto.response.AuthResponse;
import org.sam.chatapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
	AuthenticationService service;

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
		return ApiResponse.of(service.login(request));
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	ApiResponse<AuthResponse> register(@Valid @RequestBody UserCreationRequest request) {
		return ApiResponse.of(service.register(request));
	}
}
