package io.jlyon.movierental.composer;

import io.jlyon.movierental.tmdb.model.DiscoverMovieResponse;
import io.jlyon.movierental.tmdb.model.Genre;
import io.jlyon.movierental.tmdb.model.MovieItem;
import io.jlyon.movierental.tmdb.model.MovieSearchResponse;
import io.jlyon.movierental.tmdb.service.DiscoverService;
import io.jlyon.movierental.tmdb.service.MovieService;
import io.jlyon.movierental.tmdb.service.SearchService;
import io.jlyon.movierental.view.GenreOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static io.jlyon.movierental.tmdb.model.Genre.ACTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MovieComposerTest {
	@InjectMocks
	private MovieComposer composer;

	@Mock
	private SearchService searchService;
	@Mock
	private MovieService movieService;
	@Mock
	private DiscoverService discoverService;

	private final DiscoverMovieResponse discoverResponse = new DiscoverMovieResponse();
	private final MovieSearchResponse searchResponse = new MovieSearchResponse();
	private final MovieItem theGoonies = new MovieItem();

	@BeforeEach
	public void setup() {
		theGoonies.setId(1337);
		theGoonies.setTitle("The Goonies");

		discoverResponse.setResults(Collections.singletonList(theGoonies));
		searchResponse.setResults(Collections.singletonList(theGoonies));

		initMocks(this);
		when(searchService.searchMovies(anyString())).thenReturn(searchResponse);
		when(discoverService.getDiscoverMovie()).thenReturn(discoverResponse);
		when(discoverService.getDiscoverMovie(any(List.class))).thenReturn(discoverResponse);
		when(movieService.queryMovieById(anyInt())).thenReturn(theGoonies);
	}

	@Test
	public void searchMovies_givenTerm_shouldInvokeSearchService() {
		String term = "goonies";
		composer.searchMovies(term);
		verify(searchService, times(1)).searchMovies(term);
	}

	@Test
	public void getPopularMovies_shouldInvokeDiscoverService() {
		composer.getPopularMovies();
		verify(discoverService, times(1)).getDiscoverMovie();
	}

	@Test
	public void getMoviesByGenre_givenGenres_shouldInvokeDiscoverService() {
		List<String> genres = Collections.singletonList(ACTION.label());
		List<String> genreIds = Collections.singletonList(String.valueOf(ACTION.id()));
		composer.getMoviesByGenre(genres);
		verify(discoverService, times(1)).getDiscoverMovie(genreIds);
	}

	@Test
	public void getMovieById_givenId_shouldInvokeMovieService() {
		int movieId = 1337;
		composer.getMovieById(movieId);
		verify(movieService, times(1)).queryMovieById(movieId);
	}

	@Test
	public void getMovieGenres_shouldReturnOptions() {
		List<GenreOption> options = composer.getMovieGenres();
		assertEquals(Genre.values().length, options.size());
	}
}