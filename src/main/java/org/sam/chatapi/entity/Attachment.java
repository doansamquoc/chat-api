package org.sam.chatapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attachments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attachment extends BaseEntity {
	@JoinColumn(name = "message_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	Message message;

	@Column(name = "file_url")
	String fileUrl;

	@Column(name = "file_type")
	String fileType;

	@Column(name = "file_size")
	Long fileSize;

	@Column(name = "thumbnail_url")
	String thumbnailUrl;
}