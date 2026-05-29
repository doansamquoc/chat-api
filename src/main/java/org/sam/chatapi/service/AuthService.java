package org.sam.chatapi.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.sam.chatapi.dto.CustomUserDetails;
import org.sam.chatapi.dto.request.LoginRequest;
import org.sam.chatapi.dto.request.UserCreationRequest;
import org.sam.chatapi.dto.response.AuthResponse;
import org.sam.chatapi.entity.User;
import org.sam.chatapi.enums.ErrorCode;
import org.sam.chatapi.exception.BusinessException;
import org.sam.chatapi.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
	UserMapper userMapper;
	JwtService jwtService;
	UserService userService;
	PasswordEncoder passwordEncoder;
	AuthenticationManager authManager;

	private CustomUserDetails authenticate(String identifier, String password) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(identifier, password));
		return (CustomUserDetails) auth.getPrincipal();
	}

	public AuthResponse login(LoginRequest request) {
		CustomUserDetails userDetails = authenticate(request.identifier(), request.password());
		User user = userDetails.getUser();
		return enrichAuthResponse(generateToken(user), user);
	}

	public AuthResponse register(UserCreationRequest request) {
		if (userService.existsByEmail(request.email())) throw new BusinessException(ErrorCode.USER_EMAIL_EXISTS);
		String hashedPassword = passwordEncoder.encode(request.password());
		User user = userService.create(new UserCreationRequest(request.email(), hashedPassword, request.displayName()));
		return enrichAuthResponse(generateToken(user), user);
	}

	private String generateToken(User user) {
		return jwtService.create(user);
	}

	private AuthResponse enrichAuthResponse(String token, User user) {
		return new AuthResponse(token, userMapper.toDto(user));
	}
}
