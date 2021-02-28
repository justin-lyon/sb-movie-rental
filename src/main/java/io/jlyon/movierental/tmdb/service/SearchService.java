package io.jlyon.movierental.tmdb.service;

import com.sun.istack.NotNull;
import io.jlyon.movierental.tmdb.model.MovieSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

import static io.jlyon.movierental.tmdb.TmdbWebClientConfig.WEB_CLIENT_NAME;

@Service
public class SearchService {
	public static final String PATH = "/search";

	@Autowired
	@Qualifier(WEB_CLIENT_NAME)
	private WebClient.Builder wcb;

	public MovieSearchResponse searchMovies(@NotNull String queryString) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", queryString);
		params.add("page", String.valueOf(1));
		params.add("includeAdult", String.valueOf(false));
		params.add("region", "US");
		return this.searchMovies(queryString, params);
	}

	public MovieSearchResponse searchMovies(@NotNull String queryString, @NotNull MultiValueMap<String, String> queryParams) {
		queryParams.putIfAbsent("query", Collections.singletonList(queryString));

		return wcb.build()
			.get()
			.uri(uriBuilder -> uriBuilder
				.path(PATH + "/movie")
				.queryParams(queryParams)
				.build())
			.retrieve()
			.bodyToMono(MovieSearchResponse.class)
			.block();
	}
}
