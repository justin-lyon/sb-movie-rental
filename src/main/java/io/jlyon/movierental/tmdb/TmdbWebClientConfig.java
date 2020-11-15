package io.jlyon.movierental.tmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TmdbWebClientConfig {
	public static final String WEB_CLIENT_NAME = "tmbd_web_client";
	public static final String BASE_URL = "https://api.themoviedb.org";

	@Autowired
	private TmdbConfig config;

	@Bean(name = WEB_CLIENT_NAME)
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder()
			.baseUrl(BASE_URL + "/" + config.getVersion())
			.defaultHeader(HttpHeaders.AUTHORIZATION, config.getToken());
	}
}
