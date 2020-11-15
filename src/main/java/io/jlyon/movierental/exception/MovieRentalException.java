package io.jlyon.movierental.exception;

import org.springframework.http.HttpStatus;

public class MovieRentalException extends RuntimeException {
	private HttpStatus status;

	public MovieRentalException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public MovieRentalException(String message) {
		this(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
