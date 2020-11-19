package io.jlyon.movierental.tmdb.service;

import io.jlyon.movierental.tmdb.TmdbWebClientConfig;
import io.jlyon.movierental.tmdb.model.MovieItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieService {
	public static final String PATH = "/movie";
	@Autowired
	@Qualifier(TmdbWebClientConfig.WEB_CLIENT_NAME)
	private WebClient.Builder wcb;

	public MovieItem queryMovieById(int movieId) {
		return wcb.build()
			.get()
			.uri(uriBuilder -> uriBuilder
				.path(PATH + "/" + movieId)
				.build())
			.retrieve()
			.bodyToMono(MovieItem.class)
			.block();
	}
}
