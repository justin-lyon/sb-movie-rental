package io.jlyon.movierental.security;

import io.jlyon.movierental.MovieRentalApplication;
import io.jlyon.movierental.accessor.UserAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MovieRentalApplication.class);

	@Autowired
	private SecurityConfig securityConfig;
	@Autowired
	private UserAccessor userAccessor;
	@Autowired
	private BCryptPasswordEncoder encoder;


	private static final String[] CSRF_IGNORE = {SecurityConstants.SIGN_UP_URL, SecurityConstants.LOGIN_URL};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL, SecurityConstants.LOGIN_URL).permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager(), securityConfig))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), securityConfig, userAccessor))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.csrf()
			.ignoringAntMatchers(CSRF_IGNORE)
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	@Override
	public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder
			.userDetailsService(this.userAccessor)
			.passwordEncoder(this.encoder);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}
}
