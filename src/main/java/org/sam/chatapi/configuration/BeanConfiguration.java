package org.sam.chatapi.configuration;

import org.sam.chatapi.property.AppProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BeanConfiguration {
	AppProperties props;

	@Bean
	SecretKey secretKey() {
		byte[] bytes = props.getSecretKey().getBytes(StandardCharsets.UTF_8);
		if (bytes.length < 32) throw new IllegalArgumentException("Secret key must be at least 32 characters");
		return new SecretKeySpec(bytes, MacAlgorithm.HS256.getName());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
		try {
			return configuration.getAuthenticationManager();
		} catch (Exception e) {
			throw new RuntimeException("AuthenticationManager not found", e);
		}
	}
}
