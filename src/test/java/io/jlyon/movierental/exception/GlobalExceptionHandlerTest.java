package io.jlyon.movierental.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

import static io.jlyon.movierental.exception.GlobalExceptionHandler.DEFAULT_ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class GlobalExceptionHandlerTest {
	public static final String ERROR_MESSAGE = "I've giv'n her all she's got captain, an' I canna give her no more.";

	@InjectMocks
	private GlobalExceptionHandler handler;

	@Mock
	private HttpServletResponse httpResponse;

	@BeforeEach
	public void setup() {
		initMocks(this);
	}

	@Test
	public void applicationHandler_shouldReturnStatus400() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		MovieRentalException mre = new MovieRentalException(ERROR_MESSAGE, status);

		ErrorBody error = handler.applicationHandler(mre, httpResponse);

		verify(httpResponse, times(1)).setStatus(status.value());
		assertNotNull(error, "Should have some body (to love).");
	}

	@Test
	public void applicationHandler_shouldReturnStatus402() {
		HttpStatus status = HttpStatus.PAYMENT_REQUIRED;
		MovieRentalException mre = new MovieRentalException(ERROR_MESSAGE, status);

		ErrorBody error = handler.applicationHandler(mre, httpResponse);

		verify(httpResponse, times(1)).setStatus(status.value());
		assertNotNull(error, "Should have some body (to love).");
	}

	@Test
	public void catchAll_shouldHaveDefaultMessage() {
		ErrorBody error = handler.catchAll(new TestException(ERROR_MESSAGE));
		assertEquals(DEFAULT_ERROR_MESSAGE, error.getMessage());
	}

	static class TestException extends Exception {
		public TestException(String message) {
			super(message);
		}
	}
}