package io.jlyon.movierental.tmdb.service;

import io.jlyon.movierental.tmdb.model.MovieDetail;
import io.jlyon.movierental.tmdb.model.MovieItem;
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

class MovieServiceTest {
	@InjectMocks
	private MovieService service;

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
	private Mono<MovieDetail> monoMovieDetail;

	private final MovieDetail mortalKombat = new MovieDetail();

	@BeforeEach
	public void setup() {
		mortalKombat.setId(460465);
		mortalKombat.setOriginalTitle("Mortal Kombat");

		initMocks(this);

		when(wcb.build()).thenReturn(wc);
		when(wc.get()).thenReturn(rhus);
		when(rhus.uri(any(Function.class))).thenReturn(reqSpec);
		when(reqSpec.retrieve()).thenReturn(resSpec);
		when(resSpec.bodyToMono(MovieDetail.class)).thenReturn(monoMovieDetail);
		when(monoMovieDetail.block()).thenReturn(mortalKombat);
	}

	@Test
	public void getMovieById_givenId_shouldInvokeWCB() {
		MovieDetail actual = service.getMovieById(9009);

		verify(wcb, times(1)).build();
		verify(wc, times(1)).get();
		verify(rhus, times(1)).uri(any(Function.class));
		verify(reqSpec, times(1)).retrieve();
		verify(resSpec, times(1)).bodyToMono(MovieDetail.class);
		verify(monoMovieDetail, times(1)).block();

		assertEquals(mortalKombat, actual);
	}
}