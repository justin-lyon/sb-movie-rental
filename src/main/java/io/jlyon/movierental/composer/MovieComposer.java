package io.jlyon.movierental.composer;

import com.sun.istack.NotNull;
import io.jlyon.movierental.controller.MoviesController;
import io.jlyon.movierental.tmdb.model.Genre;
import io.jlyon.movierental.tmdb.service.DiscoverService;
import io.jlyon.movierental.tmdb.service.MovieService;
import io.jlyon.movierental.tmdb.service.SearchService;
import io.jlyon.movierental.transformer.MovieItemToView;
import io.jlyon.movierental.view.GenreOption;
import io.jlyon.movierental.view.MovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieComposer {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MoviesController.class);

	@Autowired
	private SearchService searchService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private DiscoverService discoverService;

	private final MovieItemToView toMovieView = new MovieItemToView();

	public List<MovieView> searchMovies(@NotNull final String searchTerm) {
		return searchService.searchMovies(searchTerm)
			.getResults()
			.stream()
			.map(toMovieView)
			.collect(Collectors.toList());
	}

	public List<MovieView> getPopularMovies() {
		return discoverService.getDiscoverMovie()
			.getResults()
			.stream()
			.map(toMovieView)
			.collect(Collectors.toList());
	}

	public List<MovieView> getMoviesByGenre(@NotNull final List<String> genres) {
		List<String> genreIds = genres.stream()
			.map(g -> String.valueOf(Genre.valueOf(g.toUpperCase()).id()))
			.collect(Collectors.toList());

		return discoverService.getDiscoverMovie(genreIds).getResults()
			.stream()
			.map(toMovieView)
			.collect(Collectors.toList());
	}

	public MovieView getMovieById(@NotNull final int movieId) {
		return toMovieView.apply(movieService.getMovieById(movieId));
	}

	public List<GenreOption> getMovieGenres() {
		return Arrays.stream(Genre.values())
			.map(GenreOption::new)
			.collect(Collectors.toList());
	}
}
