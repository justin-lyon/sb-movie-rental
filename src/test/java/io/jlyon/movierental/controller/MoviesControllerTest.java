package io.jlyon.movierental.controller;

import io.jlyon.movierental.composer.MovieComposer;
import io.jlyon.movierental.tmdb.model.MovieDetail;
import io.jlyon.movierental.tmdb.model.MovieItem;
import io.jlyon.movierental.transformer.MovieDetailToView;
import io.jlyon.movierental.transformer.MovieItemToView;
import io.jlyon.movierental.view.MovieView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MoviesControllerTest {
	@InjectMocks
	private MoviesController controller;

	@Mock
	private MovieComposer composer;

	private MovieItemToView toMovieView = new MovieItemToView();
	private MovieDetailToView toMovieDetailView = new MovieDetailToView();

	private MovieItem theGoonies = new MovieItem();
	private MovieDetail mortalKombat = new MovieDetail();
	private List<MovieView> movies = new ArrayList<>();

	@BeforeEach
	public void setup() {
		theGoonies = new MovieItem();
		theGoonies.setId(1337);
		theGoonies.setTitle("The Goonies");
		movies.add(toMovieView.apply(theGoonies));

		mortalKombat.setId(460465);
		mortalKombat.setOriginalTitle("Mortal Kombat");

		initMocks(this);
		when(composer.searchMovies(anyString())).thenReturn(movies);
		when(composer.getPopularMovies()).thenReturn(movies);
		when(composer.getMoviesByGenre(any(List.class))).thenReturn(movies);
		when(composer.getMovieById(1337)).thenReturn(toMovieDetailView.apply(mortalKombat));
		when(composer.getMovieGenres()).thenReturn(new ArrayList<>());
	}

	@Test
	public void getPopularMovies_shouldInvokeComposer() {
		controller.getPopularMovies();
		verify(composer, times(1)).getPopularMovies();
	}

	@Test
	public void searchMovies_givenTerm_shouldInvokeComposer() {
		String term = "James Bond";
		controller.searchMovies(term);
		verify(composer, times(1)).searchMovies(term);
	}

	@Test
	public void getMoviesByGenres_givenGenres_shouldInvokeComposer() {
		List<String> genres = Collections.singletonList("Spaghetti");
		controller.getMoviesByGenres(genres);
		verify(composer, times(1)).getMoviesByGenre(genres);
	}

	@Test
	public void getMoviesById_givenId_shouldInvokeComposer() {
		int movieId = 1337;
		controller.getMovieById(movieId);
		verify(composer, times(1)).getMovieById(movieId);
	}

	@Test
	public void getGenres_shouldInvokeComposer() {
		controller.getGenres();
		verify(composer, times(1)).getMovieGenres();
	}
}