package io.jlyon.movierental.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

// TODO - I hate this, get rid of it. Key Vault?
public class SecurityConstants {

	public static final String SECRET = "Some Secret String";
	public static final String BEARER_PREFIX = "Bearer ";
	public static final String SIGN_UP_URL = "/signup";
	public static final String LOGIN_URL = "/auth/login";
}
