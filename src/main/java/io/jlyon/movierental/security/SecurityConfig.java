package io.jlyon.movierental.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@Configuration
@ConfigurationProperties("jwt")
public class SecurityConfig {
	public static final String BCRYPT_ENCODER = "bcrypt_encoder";
	public static final String HMAC_SHA512 = "HmacSHA512";

	@Bean(name = BCRYPT_ENCODER)
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private int durationHours;
	private String secret;

	public int getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(int durationHours) {
		this.durationHours = durationHours;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public SecretKey getSecretKey() {
		byte[] decodedKey = Base64.getDecoder().decode(this.secret);
		return new SecretKeySpec(decodedKey, HMAC_SHA512);
	}
}
