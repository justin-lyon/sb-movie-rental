package io.jlyon.movierental.tmdb.service;

import io.jlyon.movierental.tmdb.TmdbWebClientConfig;
import io.jlyon.movierental.tmdb.model.MovieSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class Search {
	public static final String PATH = "search";

	@Autowired
	@Qualifier(TmdbWebClientConfig.WEB_CLIENT_NAME)
	private WebClient.Builder wcb;

	public MovieSearchResponse searchMovies(String queryString) {
		return searchMovies(queryString, 1, false);
	}

	public MovieSearchResponse searchMovies(String queryString, int page, boolean includeAdult) {
		return wcb.build()
			.get()
			.uri(uriBuilder -> uriBuilder
				.path(PATH + "/movies")
				.queryParam("query", queryString)
				.queryParam("page", page)
				.queryParam("include_adult", includeAdult)
				.queryParam("region", "US")
				.build())
			.retrieve().bodyToMono(MovieSearchResponse.class).block();
	}
}
