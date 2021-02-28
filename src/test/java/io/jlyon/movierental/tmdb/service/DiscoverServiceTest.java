package io.jlyon.movierental.tmdb.service;

import io.jlyon.movierental.tmdb.model.DiscoverMovieResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.doReturn;
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

	@BeforeEach
	public void setup() {
		initMocks(this);

		when(wcb.build()).thenReturn(wc);
		when(wc)
	}

	@Test
	public void getDiscoverMovie_default_shouldInvokeOverloaded() {
		doReturn(new DiscoverMovieResponse()).when(service).getDiscoverMovie(any(MultiValueMap.class));
	}
}