package org.sam.chatapi.service;

import org.sam.chatapi.dto.UserDto;
import org.sam.chatapi.dto.request.UserCreationRequest;
import org.sam.chatapi.entity.User;
import org.sam.chatapi.mapper.UserMapper;
import org.sam.chatapi.repository.UserRepository;
import org.sam.chatapi.util.UserUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
	UserMapper mapper;
	UserRepository repository;

	public UserDto create(UserCreationRequest request) {
		User user = mapper.toEntity(request);
		user.setUsername(UserUtils.generateUsername(request.email()));
		return save(user);
	}

	public UserDto save(User user) {
		return mapper.toDto(repository.save(user));
	}

	public Optional<UserDto> findByIdentifier(String identifier) {
		return repository.findByUsernameOrEmail(identifier, identifier).map(mapper::toDto);
	}

	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}
}
