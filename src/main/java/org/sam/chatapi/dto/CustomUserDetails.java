package org.sam.chatapi.dto;

import org.sam.chatapi.entity.User;
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
	User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Set.of(UserUtils.toAuthority(this.user.getRole().name()));
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}
}
