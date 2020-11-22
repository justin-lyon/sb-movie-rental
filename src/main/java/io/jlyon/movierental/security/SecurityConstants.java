package io.jlyon.movierental.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

public class SecurityConstants {
	public static final String BEARER_PREFIX = "Bearer ";
	public static final String SIGN_UP_URL = "/signup";
	public static final String LOGIN_URL = "/auth/login";
}
