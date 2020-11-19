package io.jlyon.movierental.exception;

import io.jlyon.movierental.MovieRentalApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MovieRentalApplication.class);

	@ExceptionHandler(MovieRentalException.class)
	public ErrorBody applicationHandler(MovieRentalException mre, HttpServletResponse response) {
		log.warn("MovieRentalException handled {}", mre.getMessage());
		response.setStatus(mre.getStatus().value());
		return new ErrorBody(mre.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorBody catchAll(Exception exc) {
		log.warn("Unhandled Exception in Application {}", exc.getMessage());
		exc.printStackTrace();
		return new ErrorBody("Woops! Sorry, that wasn't supposed to happen.");
	}
}
