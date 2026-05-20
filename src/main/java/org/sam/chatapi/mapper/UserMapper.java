package org.sam.chatapi.mapper;

import org.sam.chatapi.dto.request.UserCreationRequest;
import org.sam.chatapi.dto.response.UserResponse;
import org.sam.chatapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
	componentModel = "spring",
	uses = {DateTimeMapperUtil.class},
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
	User toEntity(UserCreationRequest request);

	UserResponse toDto(User user);
}
