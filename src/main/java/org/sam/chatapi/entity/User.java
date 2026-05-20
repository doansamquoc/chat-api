package org.sam.chatapi.entity;

import org.sam.chatapi.enums.Gender;
import org.sam.chatapi.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
	@Column(name = "username")
	String username;

	@Column(name = "email")
	String email;

	@Column(name = "password")
	String password;

	@Column(name = "display_name")
	String displayName;

	@Column(name = "dob")
	LocalDate dob;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	Gender gender;

	@Builder.Default
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	Role role = Role.USER;
}
