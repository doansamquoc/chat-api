package org.sam.chatapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreationRequest(
	@NotBlank(message = "user.email.required")
	@Email(message = "user.email.invalid")
	String email,

	@NotBlank(message = "user.password.required")
	@Size(min = 6, max = 255, message = "user.password.size")
	String password,

	@NotBlank(message = "user.dispay_name.required")
	@Size(min = 2, max = 255, message = "user.display_name.size")
	String displayName
) {}
