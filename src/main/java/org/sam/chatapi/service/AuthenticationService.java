package org.sam.chatapi.service;

import org.sam.chatapi.dto.CustomUserDetails;
import org.sam.chatapi.dto.TokenDto;
import org.sam.chatapi.dto.UserDto;
import org.sam.chatapi.dto.request.LoginRequest;
import org.sam.chatapi.dto.request.UserCreationRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
	UserService userService;
	JwtService jwtService;
	PasswordEncoder passwordEncoder;
	AuthenticationManager authManager;

	private CustomUserDetails authenticate(String identifier, String password) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(identifier, password));
		return (CustomUserDetails) auth.getPrincipal();
	}

	public TokenDto login(LoginRequest request) {
		CustomUserDetails userDetails = authenticate(request.identifier(), request.password());
		UserDto userDto = userDetails.getUser();
		return generateToken(userDto);
	}

	public TokenDto register(UserCreationRequest request) {
		if (userService.existsByEmail(request.email())) {
			throw new DataIntegrityViolationException("Email already exists");
		}
		String hashedPassword = passwordEncoder.encode(request.password());
		UserDto user = userService.create(new UserCreationRequest(request.email(), hashedPassword, request.displayName()));
		return generateToken(user);
	}


	private TokenDto generateToken(UserDto userDto) {
		String accessToken = jwtService.create(userDto);
		return new TokenDto(accessToken);
	}
}
