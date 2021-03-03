package io.jlyon.movierental.tmdb.service;

import io.jlyon.movierental.tmdb.model.GenreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static io.jlyon.movierental.tmdb.TmdbWebClientConfig.WEB_CLIENT_NAME;

@Service
public class GenreService {
	public static final String PATH = "/genre/movie/list";
	@Autowired
	@Qualifier(WEB_CLIENT_NAME)
	private WebClient.Builder wcb;

	public GenreResponse getAllGenres() {
		return wcb.build()
			.get()
			.uri(uriBuilder -> uriBuilder
				.path(PATH)
				.build())
			.retrieve()
			.bodyToMono(GenreResponse.class)
			.block();
	}
}
