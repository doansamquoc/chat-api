package org.sam.chatapi.mapper;

import org.sam.chatapi.dto.UserDto;
import org.sam.chatapi.dto.request.UserCreationRequest;
import org.sam.chatapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
	User toEntity(UserDto dto);

	User toEntity(UserCreationRequest request);

	UserDto toDto(User user);
}
