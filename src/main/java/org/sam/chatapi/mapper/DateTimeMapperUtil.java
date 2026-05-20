package org.sam.chatapi.mapper;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class DateTimeMapperUtil {
	public LocalDateTime toLocalDateTime(Instant instant) {
		if (instant == null) return null;
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
}
