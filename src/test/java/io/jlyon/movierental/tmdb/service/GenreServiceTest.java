package io.jlyon.movierental.tmdb.service;

import io.jlyon.movierental.tmdb.model.DiscoverMovieResponse;
import io.jlyon.movierental.tmdb.model.GenreResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class GenreServiceTest {
	@InjectMocks
	private GenreService service;

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
	private Mono<GenreResponse> monoResponse;

	private GenreResponse response;

	@BeforeEach
	public void setup() {
		response = new GenreResponse();
		monoResponse = Mono.just(response);

		initMocks(this);
		when(wcb.build()).thenReturn(wc);
		when(wc.get()).thenReturn(rhus);
		when(rhus.uri(any(Function.class))).thenReturn(reqSpec);
		when(reqSpec.retrieve()).thenReturn(resSpec);
		when(resSpec.bodyToMono(GenreResponse.class)).thenReturn(monoResponse);
		when(monoResponse.block()).thenReturn(response);
	}

	@Test
	public void getAllGenres_shouldInvokeWCB() {
		GenreResponse result = service.getAllGenres();

		verify(wcb, times(1)).build();
		verify(wc, times(1)).get();
		verify(rhus, times(1)).uri(any(Function.class));
		verify(reqSpec, times(1)).retrieve();
		verify(resSpec, times(1)).bodyToMono(GenreResponse.class);
		verify(monoResponse, times(1)).block();

		assertEquals(response, result);
	}
}