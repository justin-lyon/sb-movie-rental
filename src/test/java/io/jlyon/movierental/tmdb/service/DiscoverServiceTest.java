package io.jlyon.movierental.tmdb.service;

import io.jlyon.movierental.tmdb.model.DiscoverMovieResponse;
import io.jlyon.movierental.tmdb.model.Genre;
import io.jlyon.movierental.tmdb.model.MovieItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DiscoverServiceTest {
	@InjectMocks
	@Spy
	private DiscoverService service;

	@Mock
	private WebClient.Builder wcb;
	@Mock
	private WebClient wc;
	@Mock
	private WebClient.RequestHeadersUriSpec rhus;
	@Mock
	private WebClient.RequestHeadersSpec reqSpec;
	@Mock
	private WebClient.ResponseSpec resSpec;
	@Mock
	private Mono<DiscoverMovieResponse> monoResponse;

	private final MovieItem theGoonies = new MovieItem();
	private final DiscoverMovieResponse response = new DiscoverMovieResponse();

	@BeforeEach
	public void setup() {
		theGoonies.setId(1337);
		theGoonies.setTitle("The Goonies");
		response.setResults(Collections.singletonList(theGoonies));

		initMocks(this);
		when(wcb.build()).thenReturn(wc);
		when(wc.get()).thenReturn(rhus);
		when(rhus.uri(any(Function.class))).thenReturn(reqSpec);
		when(reqSpec.retrieve()).thenReturn(resSpec);
		when(resSpec.bodyToMono(DiscoverMovieResponse.class)).thenReturn(monoResponse);
		when(monoResponse.block()).thenReturn(response);
	}

	@Test
	public void getDiscoverMovieOmnibus_givenParams_shouldInvokeWCB() {
		service.getDiscoverMovie(new LinkedMultiValueMap<>());

		verify(wcb, times(1)).build();
		verify(wc, times(1)).get();
		verify(rhus, times(1)).uri(any(Function.class));
		verify(reqSpec, times(1)).retrieve();
		verify(resSpec, times(1)).bodyToMono(DiscoverMovieResponse.class);
		verify(monoResponse, times(1)).block();
	}

	@Test
	public void getDiscoverMovie_shouldInvokeOmnibus() {
		doReturn(null).when(service).getDiscoverMovie(any(LinkedMultiValueMap.class));
		service.getDiscoverMovie();
		verify(service, times(1)).getDiscoverMovie(any(LinkedMultiValueMap.class));
	}

	@Test
	public void getDiscoverMovie_givenGenres_shouldInvokeOmnibus() {
		List<String> genreIds = Collections.singletonList(String.valueOf(Genre.ACTION.id()));
		MultiValueMap withGenres = new LinkedMultiValueMap<>();
		withGenres.addAll("with_genres", genreIds);

		doReturn(null).when(service).getDiscoverMovie(any(LinkedMultiValueMap.class));

		service.getDiscoverMovie(genreIds);

		verify(service, times(1)).getDiscoverMovie(withGenres);
	}
}