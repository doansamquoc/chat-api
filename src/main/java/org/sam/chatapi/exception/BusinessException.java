package org.sam.chatapi.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.sam.chatapi.enums.ErrorCode;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusinessException extends RuntimeException {
	ErrorCode errorCode;
}
