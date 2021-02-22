package io.jlyon.movierental.controller;

import io.jlyon.movierental.tmdb.model.MovieItem;
import io.jlyon.movierental.tmdb.model.MovieSearchResponse;
import io.jlyon.movierental.tmdb.service.MovieService;
import io.jlyon.movierental.tmdb.service.SearchService;
import io.jlyon.movierental.view.MovieView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MoviesControllerTest {
	@InjectMocks
	private MoviesController controller;

	@Mock
	private SearchService searchService;
	@Mock
	private MovieService movieService;

	private MovieItem theGoonies = new MovieItem();

	@BeforeEach
	public void setup() {
		theGoonies = new MovieItem();
		theGoonies.setId(1337);
		theGoonies.setTitle("The Goonies");

		initMocks(this);
		when(searchService.searchMovies(anyString())).thenReturn(new MovieSearchResponse());
		when(movieService.queryMovieById(anyInt())).thenReturn(theGoonies);
	}

	@Test
	public void searchMovies_givenSearchTerm_shouldInvokeSearchService() {
		String term = "The Goonies";
		controller.searchMovies(term);
		verify(searchService, times(1)).searchMovies(term);
	}

	@Test
	public void getMovieById_givenId_shouldInvokeMovieService() {
		int movieId = 1337;
		controller.getMovieById(movieId);
		verify(movieService, times(1)).queryMovieById(movieId);
	}
}