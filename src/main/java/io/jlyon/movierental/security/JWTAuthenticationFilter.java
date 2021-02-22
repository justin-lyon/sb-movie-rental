package io.jlyon.movierental.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.exception.MovieRentalException;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private final SecurityConfig securityConfig;
	private final AuthenticationManager authManager;

	public JWTAuthenticationFilter(AuthenticationManager am, SecurityConfig sc) {
		this.authManager = am;
		this.securityConfig = sc;
		setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			UserEntity currentUser = new ObjectMapper()
				.readValue(req.getInputStream(), UserEntity.class);

			return authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					currentUser.getEmail(),
					currentUser.getPassword(),
					new ArrayList<>())
			);
		} catch(IOException exc) {
			throw new MovieRentalException(exc.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
		int millisFromNow = 1000 * 60 * 60 * securityConfig.getDurationHours();
		// TODO - Update to use io.jsonwebtoken instead of com.auth0
		String token = JWT.create()
			.withSubject(((UserEntity) auth.getPrincipal()).getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis() + millisFromNow))
			.sign(Algorithm.HMAC512(securityConfig.getSecret().getBytes()));

		String body = ((UserEntity) auth.getPrincipal()).getUsername() + " " + token;
		res.getWriter().write(body);
		res.getWriter().flush();
	}
}
