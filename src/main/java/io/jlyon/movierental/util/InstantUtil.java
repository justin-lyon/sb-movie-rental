package io.jlyon.movierental.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InstantUtil {
	public static Instant iso8601StringToInstant(String isoDateString) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
		return LocalDateTime
			.parse(isoDateString, f)
			.atZone(ZoneId.of("America/Chicago"))
			.toInstant();
	}
}
