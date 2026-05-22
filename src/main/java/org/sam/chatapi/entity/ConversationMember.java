package org.sam.chatapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sam.chatapi.enums.Role;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conversation_members")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationMember extends BaseEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	Conversation conversation;

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	User user;

	@Column(name = "nickname")
	String nickname;

	@Builder.Default
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	Role role = Role.MEMBER;
}