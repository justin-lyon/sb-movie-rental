package io.jlyon.movierental.security;

import com.sun.istack.NotNull;
import io.jlyon.movierental.accessor.UserAccessor;
import io.jlyon.movierental.entity.UserEntity;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class JWTAuthorizationFilterTest {
	@InjectMocks
	@Spy
	private JWTAuthorizationFilter filter;

	@Mock
	private AuthenticationManager authManager;
	@Mock
	private SecurityConfig config;
	@Mock
	private HttpServletRequest req;
	@Mock
	private HttpServletResponse res;
	@Mock
	private FilterChain chain;
	@Mock
	private SecurityContext securityContext;
	@Mock
	private UserAccessor userAccessor;

	private final String email = "bobsburgers@aol.net";
	private final SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	private final String MOCK_TOKEN = mockJws(email, 1, secret);
	private final UserEntity user = initUser(email);

	@BeforeEach
	public void setup() throws IOException, ServletException {
		initMocks(this);

		when(req.getHeader(anyString())).thenReturn("Bearer " + MOCK_TOKEN);
		doNothing().when(chain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

		when(config.getSecretKey()).thenReturn(secret);
		SecurityContextHolder.setContext(securityContext);
		doNothing().when(securityContext).setAuthentication(any(UsernamePasswordAuthenticationToken.class));

		when(userAccessor.getOneByEmail(anyString())).thenReturn(user);
	}

	@Test
	public void doFilterInternal_givenNoHeader_shouldSkipAuth() {
		when(req.getHeader(anyString())).thenReturn(null);

		assertDoesNotThrow(() -> filter.doFilterInternal(req, res, chain));

		verify(securityContext, times(0)).setAuthentication(any());
	}

	@Test
	public void doFilterInternal_givenHeader_shouldInvokeAuth() {
		assertDoesNotThrow(() -> filter.doFilterInternal(req, res, chain));

		verify(securityContext, times(1)).setAuthentication(any(UsernamePasswordAuthenticationToken.class));
	}

	private static String mockJws(String email, int hoursFromNow, SecretKey key) {
		return Jwts.builder()
			.setHeaderParam(JwsHeader.KEY_ID, 1)
			.setIssuer("io.jlyon.movierental")
			.setSubject(email)
			.setExpiration(new Date(System.currentTimeMillis() + hoursToMillis(hoursFromNow)))
			.signWith(key)
			.compact();
	}

	private static UserEntity initUser(String email) {
		UserEntity u = new UserEntity();
		u.setId(UUID.randomUUID());
		u.setEmail(email);
		u.setUsername("bob");
		return u;
	}

	private static int hoursToMillis(@NotNull int hours) {
		return 1000 * 60 * 60 * hours;
	}
}