package io.jlyon.movierental.tmdb.service;

import io.jlyon.movierental.tmdb.model.MovieItem;
import io.jlyon.movierental.tmdb.model.MovieSearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class SearchServiceTest {
	@InjectMocks
	@Spy
	private SearchService service;

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
	private Mono<MovieSearchResponse> monoResponse;

	private final MovieItem emojiMovie = new MovieItem();
	private final MovieSearchResponse response = new MovieSearchResponse();

	@BeforeEach
	public void setup() {
		emojiMovie.setId(8338);
		emojiMovie.setTitle("The Emoji Movie");
		response.setResults(Collections.singletonList(emojiMovie));

		initMocks(this);
		when(wcb.build()).thenReturn(wc);
		when(wc.get()).thenReturn(rhus);
		when(rhus.uri(any(Function.class))).thenReturn(reqSpec);
		when(reqSpec.retrieve()).thenReturn(resSpec);
		when(resSpec.bodyToMono(MovieSearchResponse.class)).thenReturn(monoResponse);
		when(monoResponse.block()).thenReturn(response);
	}

	@Test
	public void searchMoviesOmnibus_shouldInvokeWCB() {
		String searchTerm = "emoji movie";
		MovieSearchResponse actual = service.searchMovies(searchTerm, new LinkedMultiValueMap<>());

		verify(wcb, times(1)).build();
		verify(wc, times(1)).get();
		verify(rhus, times(1)).uri(any(Function.class));
		verify(reqSpec, times(1)).retrieve();
		verify(resSpec, times(1)).bodyToMono(MovieSearchResponse.class);
		verify(monoResponse, times(1)).block();

		assertEquals(response, actual);
	}

	@Test
	public void searchMovies_shouldInvokeOmnibus() {
		String searchTerm = "emoji movie";
		doReturn(response).when(service).searchMovies(anyString(), any(LinkedMultiValueMap.class));

		MovieSearchResponse actual = service.searchMovies(searchTerm);
		verify(service, times(1)).searchMovies(eq(searchTerm), any(LinkedMultiValueMap.class));

		assertEquals(response, actual);
	}
}