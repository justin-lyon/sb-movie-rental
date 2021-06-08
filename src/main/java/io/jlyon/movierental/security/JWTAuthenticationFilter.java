package io.jlyon.movierental.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.exception.MovieRentalException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final SecurityConfig securityConfig;
	private final AuthenticationManager authManager;

	public JWTAuthenticationFilter(AuthenticationManager am, SecurityConfig sc) {
		this.authManager = am;
		this.securityConfig = sc;
		setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
		try {
			UserEntity currentUser = new ObjectMapper()
				.readValue(
					req.getInputStream(),
					UserEntity.class);

			return authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					currentUser.getEmail(),
					currentUser.getPassword(),
					new ArrayList<>())
			);
		} catch(IOException exc) {
			throw new MovieRentalException(exc.getMessage());
		} catch(AuthenticationException aex) {
			throw new MovieRentalException(aex.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
		int millisFromNow = 1000 * 60 * 60 * securityConfig.getDurationHours();
		String email = ((UserEntity) auth.getPrincipal()).getEmail();
		String jws = Jwts.builder()
			.setHeaderParam(JwsHeader.KEY_ID, 1)
			.setIssuer("io.jlyon.movierental")
			.setSubject(email)
			.setExpiration(new Date(System.currentTimeMillis() + millisFromNow))
			.signWith(securityConfig.getSecretKey())
			.compact();

		res.getWriter().write(jws);
		res.getWriter().flush();
	}
}
