package org.sam.chatapi.entity;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.sam.chatapi.enums.MessageStatus;
import org.sam.chatapi.enums.MessageType;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message extends BaseEntity {
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	Conversation conversation;

	@JoinColumn(name = "sender_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	User sender;

	@Builder.Default
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	MessageType type = MessageType.TEXT;

	@Column(name = "content")
	String content;

	@Column(name = "deleted_at")
	Instant deletedAt;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	MessageStatus status;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reply_to_message_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	Message replyTo;

	@OneToMany(mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	Set<Attachment> attachments = new LinkedHashSet<>();
}