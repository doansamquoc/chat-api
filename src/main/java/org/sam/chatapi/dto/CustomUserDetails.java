package org.sam.chatapi.dto;

import org.sam.chatapi.util.UserUtils;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomUserDetails implements UserDetails {
	Long id;
	String username;
	String password;
	String email;
	Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(UserDto user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.authorities = Set.of(UserUtils.toAuthority(user.getRole().name()));
	}

	public UserDto getUser() {
		return UserDto.builder().id(id).username(username).email(email).role(UserUtils.toRole(authorities)).build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
}
