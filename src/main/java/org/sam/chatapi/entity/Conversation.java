package org.sam.chatapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sam.chatapi.enums.ConversationType;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conversations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conversation extends BaseEntity {
	@JoinColumn(name = "creator_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	User creator;

	@Column(name = "name")
	String name;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	ConversationType type = ConversationType.DIRECT;

	@Column(name = "avatar_url")
	String avatarUrl;

	@OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
	Set<ConversationMember> members = new LinkedHashSet<>();

	@OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
	Set<Message> messages = new LinkedHashSet<>();
}