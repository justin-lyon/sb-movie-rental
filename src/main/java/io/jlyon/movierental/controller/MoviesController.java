package io.jlyon.movierental.controller;

import com.sun.istack.NotNull;
import io.jlyon.movierental.tmdb.model.Genre;
import io.jlyon.movierental.tmdb.model.MovieSearchResponse;
import io.jlyon.movierental.tmdb.service.DiscoverService;
import io.jlyon.movierental.tmdb.service.MovieService;
import io.jlyon.movierental.tmdb.service.SearchService;
import io.jlyon.movierental.view.GenreOption;
import io.jlyon.movierental.view.MovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MoviesController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MoviesController.class);

	@Autowired
	private SearchService searchService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private DiscoverService discoverService;

	@GetMapping
	public List<MovieView> getPopularMovies() {
		return discoverService.getDiscoverMovie().getResults()
			.stream()
			.map(MovieView::new)
			.collect(Collectors.toList());
	}

	@GetMapping(params = "searchString")
	public List<MovieView> searchMovies(@RequestParam @NotNull final String searchString) {
		log.info("Searching Movies for: {}", searchString);
		MovieSearchResponse response = searchService.searchMovies(searchString);
		return response
			.getResults()
			.stream()
			.map(MovieView::new)
			.collect(Collectors.toList());
	}

	@GetMapping(params = "genres")
	public List<MovieView> getMoviesByGenres(
		@RequestParam final List<String> genres) {

		List<String> genreIds = genres.stream()
			.map(g -> String.valueOf(Genre.valueOf(g.toUpperCase()).id()))
			.collect(Collectors.toList());

		return discoverService.getDiscoverMovie(genreIds).getResults()
			.stream()
			.map(MovieView::new)
			.collect(Collectors.toList());
	}

	@GetMapping("/{movieId}")
	public MovieView getMovieById(@PathVariable final int movieId) {
		log.info("Get movie: {}", movieId);
		return new MovieView(movieService.queryMovieById(movieId));
	}

	@GetMapping("/genres")
	public List<GenreOption> getGenre() {
		return Arrays.stream(Genre.values())
			.map(GenreOption::new)
			.collect(Collectors.toList());
	}
}
