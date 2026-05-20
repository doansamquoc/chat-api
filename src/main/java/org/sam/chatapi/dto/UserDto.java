package org.sam.chatapi.dto;

import org.sam.chatapi.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
	Long id;
	String username;
	String password;
	String email;
	Role role;
}
