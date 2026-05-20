package org.sam.chatapi.util;

import java.util.UUID;

public class UUIDUtils {
	public static String rand() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
