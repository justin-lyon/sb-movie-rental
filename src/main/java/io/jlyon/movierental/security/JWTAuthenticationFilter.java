package io.jlyon.movierental.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.exception.MovieRentalException;
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

import static io.jlyon.movierental.security.SecurityConstants.SECRET;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authManager;

	public JWTAuthenticationFilter(AuthenticationManager am) {
		this.authManager = am;
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
		// TODO - Move hours (8) to ConfigurationProperties class
		System.out.println("Begin Successful Authentication");
		int millisFromNow = 1000 * 60 * 60 * 8;
		// TODO - Update to use io.jsonwebtoken instead of com.auth0
		String token = JWT.create()
			.withSubject(((UserEntity) auth.getPrincipal()).getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis() + millisFromNow))
			.sign(Algorithm.HMAC512(SECRET.getBytes()));

		String body = ((UserEntity) auth.getPrincipal()).getUsername() + " " + token;
		res.getWriter().write(body);
		res.getWriter().flush();
	}
}
