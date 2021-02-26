package io.jlyon.movierental.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.exception.MovieRentalException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class JWTAuthenticationFilterTest {
	public static final int DURATION_HOURS = 1;

	@InjectMocks
	private JWTAuthenticationFilter filter;

	@Mock
	private SecurityConfig config;
	@Mock
	private AuthenticationManager manager;
	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse res;
	@Mock
	private FilterChain chain;
	@Mock
	private Authentication auth;

	private UserEntity bob;
	private final SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	@Spy
	private DelegatingServletInputStream sis;

	@BeforeEach
	public void setup() throws IOException {
		bob = new UserEntity();
		bob.setEmail("burgerbob@hotmail.com");
		bob.setPassword("turkeylover");
		bob.setUsername("burgerbob");
		bob.setId(UUID.randomUUID());

		ObjectMapper om = new ObjectMapper();
		String bobString = om.writeValueAsString(bob);
		sis = new DelegatingServletInputStream(
			new ByteArrayInputStream(
				bobString.getBytes(StandardCharsets.UTF_8)));

		initMocks(this);
		when(auth.getPrincipal()).thenReturn(bob);
		when(manager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(new UsernamePasswordAuthenticationToken("something", "anything", Collections.emptyList()));
		when(req.getInputStream()).thenReturn(sis);
		when(config.getDurationHours()).thenReturn(DURATION_HOURS);
		when(config.getSecretKey()).thenReturn(secret);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);
	}

	@Test
	public void attemptAuthentication_shouldAuthenticate() {
		Authentication auth = filter.attemptAuthentication(req, res);
		assertTrue(auth.isAuthenticated(), "Should now be authenticated.");
	}

	@Test
	public void attemptAuthentication_shouldRethrowIOtoMREfor500() throws IOException {
		String message = "Forced IOException.";
		when(req.getInputStream()).thenThrow(new IOException(message));

		MovieRentalException mre = assertThrows(MovieRentalException.class,
			() -> filter.attemptAuthentication(req, res));

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, mre.getStatus());
		assertEquals(message, mre.getMessage());
	}

	@Test
	public void attemptAuthentication_shouldRethrowAuthToMREfor403() {
		String message = "Forced AuthenticationException.";
		when(manager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new UsernameNotFoundException(message));

		MovieRentalException mre = assertThrows(MovieRentalException.class,
			() -> filter.attemptAuthentication(req, res));

		assertEquals(HttpStatus.FORBIDDEN, mre.getStatus());
		assertEquals(message, mre.getMessage());
	}

	@Test
	public void successfulAuthentication_shouldBeTestedNEXT() throws IOException {
		assertDoesNotThrow(() -> filter.successfulAuthentication(req, res, chain, auth));
		verify(config, times(1)).getDurationHours();
		verify(config, times(1)).getSecretKey();
		verify(auth, times(1)).getPrincipal();
		verify(res, times(2)).getWriter();

	}


}