package io.jlyon.movierental.tmdb.service;

import com.sun.istack.NotNull;
import io.jlyon.movierental.tmdb.model.MovieDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

import static io.jlyon.movierental.tmdb.TmdbWebClientConfig.WEB_CLIENT_NAME;

@Service
public class MovieService {
	public static final String PATH = "/movie";
	@Autowired
	@Qualifier(WEB_CLIENT_NAME)
	private WebClient.Builder wcb;

	public MovieDetail getMovieById(@NotNull int movieId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.put("append_to_response", Collections.singletonList("release_dates"));

		return wcb.build()
			.get()
			.uri(uriBuilder -> uriBuilder
				.path(PATH + "/" + movieId)
				.queryParams(params)
				.build())
			.retrieve()
			.bodyToMono(MovieDetail.class)
			.block();
	}
}
