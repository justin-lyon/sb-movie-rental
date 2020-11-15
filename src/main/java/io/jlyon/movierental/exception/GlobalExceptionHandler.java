package io.jlyon.movierental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MovieRentalException.class)
	public ErrorBody applicationHandler(MovieRentalException mre, HttpServletResponse response) {
		response.setStatus(mre.getStatus().value());
		return new ErrorBody(mre.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorBody catchAll(Exception exc) {
		return new ErrorBody("Woops! Sorry, that wasn't supposed to happen.");
	}
}
