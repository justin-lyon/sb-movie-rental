package io.jlyon.movierental.exception;

import java.time.Instant;

public class ErrorBody {
	private Instant timestamp;
	private String message;

	public ErrorBody(String message) {
		this.timestamp = Instant.now();
		this.message = message;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorBody{" +
			"timestamp=" + timestamp +
			", message='" + message + '\'' +
			'}';
	}
}
