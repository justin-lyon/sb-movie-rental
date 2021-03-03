package io.jlyon.movierental.tmdb.service;

import com.sun.istack.NotNull;
import io.jlyon.movierental.tmdb.model.MovieItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static io.jlyon.movierental.tmdb.TmdbWebClientConfig.WEB_CLIENT_NAME;

@Service
public class MovieService {
	public static final String PATH = "/movie";
	@Autowired
	@Qualifier(WEB_CLIENT_NAME)
	private WebClient.Builder wcb;

	public MovieItem getMovieById(@NotNull int movieId) {
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
