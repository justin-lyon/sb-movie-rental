package io.jlyon.movierental.tmdb.service;

import com.sun.istack.NotNull;
import io.jlyon.movierental.tmdb.model.DiscoverMovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static io.jlyon.movierental.tmdb.TmdbWebClientConfig.WEB_CLIENT_NAME;

@Service
public class DiscoverService {
	public static final String PATH = "/discover/movie";
	@Autowired
	@Qualifier(WEB_CLIENT_NAME)
	private WebClient.Builder wcb;

	public DiscoverMovieResponse getDiscoverMovie() {
		return this.getDiscoverMovie(new LinkedMultiValueMap<>());
	}

	public DiscoverMovieResponse getDiscoverMovie(List<String> genreIds) {
		MultiValueMap<String, String> withGenres = new LinkedMultiValueMap<>();
		withGenres.addAll("with_genres", genreIds);
		return this.getDiscoverMovie(withGenres);
	}

	public DiscoverMovieResponse getDiscoverMovie(@NotNull MultiValueMap<String, String> params) {
		return wcb.build()
			.get()
			.uri(uriBuilder -> uriBuilder
				.path(PATH)
				.queryParams(params)
				.build())
			.retrieve()
			.bodyToMono(DiscoverMovieResponse.class)
			.block();
	}
}
