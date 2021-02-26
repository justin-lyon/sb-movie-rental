package io.jlyon.movierental.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@Configuration
@ConfigurationProperties("jwt")
@Slf4j
public class SecurityConfig {
	public static final String BCRYPT_ENCODER = "bcrypt_encoder";

	@Bean(name = BCRYPT_ENCODER)
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private int durationHours;
	private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // HS256, HS384, or HS512;

	public int getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(int durationHours) {
		this.durationHours = durationHours;
	}

	public SecretKey getSecretKey() {
		return this.key;
	}
}
