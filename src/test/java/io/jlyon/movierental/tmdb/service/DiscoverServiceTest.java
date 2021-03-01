package io.jlyon.movierental.tmdb.service;

import io.jlyon.movierental.tmdb.model.DiscoverMovieResponse;
import io.jlyon.movierental.tmdb.model.Genre;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.Collections;
import java.util.List;

import static io.jlyon.movierental.tmdb.service.DiscoverService.PATH;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DiscoverServiceTest {
	@InjectMocks
	@Spy
	private DiscoverService service;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private WebClient.Builder wcb;

	@BeforeEach
	public void setup() {
		initMocks(this);

		when(wcb
				.build()
				.get()
				.uri(uriBuilder -> uriBuilder
					.path(anyString())
					.queryParams(any(MultiValueMap.class))
					.build())
				.retrieve()
				.bodyToMono(DiscoverMovieResponse.class)
				.block())
			.thenReturn(new DiscoverMovieResponse());
	}

	@Test
	public void getDiscoverMovie_default_shouldInvokeOverloaded() {
		doReturn(new DiscoverMovieResponse()).when(service).getDiscoverMovie(any(MultiValueMap.class));
		service.getDiscoverMovie();
		verify(service, times(1)).getDiscoverMovie(new LinkedMultiValueMap<>());
	}

	@Test
	public void getDiscoverMovie_givenGenreIds_shouldInvokeOverloaded() {
		doReturn(new DiscoverMovieResponse()).when(service).getDiscoverMovie(any(MultiValueMap.class));
		List<String> genreIds = Collections.singletonList(String.valueOf(Genre.ACTION.id()));
		MultiValueMap<String, String> withGenres = new LinkedMultiValueMap<>();
		withGenres.addAll("with_genres", genreIds);

		service.getDiscoverMovie(genreIds);

		verify(service, times(1)).getDiscoverMovie(withGenres);
	}

	@Test
	public void getDiscoverMovie_withParams_shouldInvokeWCB() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		service.getDiscoverMovie(params);
		verify(wcb.build(), times(1)).get();
//		verify(wcb
//				.build()
//				.get()
//				.uri(uriBuilder -> uriBuilder
//					.path(PATH)
//					.queryParams(params)
//					.build())
//				.retrieve()
//				.bodyToMono(DiscoverMovieResponse.class)
//			, times(1)).block();
	}
}