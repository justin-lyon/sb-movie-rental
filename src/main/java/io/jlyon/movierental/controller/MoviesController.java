package io.jlyon.movierental.controller;

import com.sun.istack.NotNull;
import io.jlyon.movierental.composer.MovieComposer;
import io.jlyon.movierental.view.GenreOption;
import io.jlyon.movierental.view.MovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MoviesController.class);

	@Autowired
	private MovieComposer composer;

	@GetMapping
	public List<MovieView> getPopularMovies() {
		log.info("Getting Popular Movies...");
		return composer.getPopularMovies();
	}

	@GetMapping(params = "searchTerm")
	public List<MovieView> searchMovies(@RequestParam @NotNull final String searchTerm) {
		log.info("Searching Movies for: {}...", searchTerm);
		return composer.searchMovies(searchTerm);
	}

	@GetMapping(params = "genres")
	public List<MovieView> getMoviesByGenres(
		@RequestParam final List<String> genres) {
		log.info("Getting Movies by Genre(s): {}...", genres);
		return composer.getMoviesByGenre(genres);
	}

//	@GetMapping("/filter")
//	public List<MovieView> filterMovies(
//		@RequestParam(required = false) final List<String> genres,
//		@RequestParam(required = false) final Date minReleaseDate,
//		@RequestParam(required = false) final Date maxReleaseDate
//	) {
//
//		return this.filterMovies(20, genres, minReleaseDate, maxReleaseDate);
//	}

	@GetMapping("/filter")
	public List<MovieView> filterMovies(
		@RequestParam(required = false) final int limit,
		@RequestParam(required = false) final List<String> genres,
		@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date minReleaseDate,
		@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date maxReleaseDate
	) {

		return composer.filterMovies(limit, genres, minReleaseDate, maxReleaseDate);
	}

//	@GetMapping(params = "movieIds")
//	public List<MovieView> getMoviesByIds(@RequestParam @NotNull final List<Integer> movieIds) {
//		return null;
//	}

	@GetMapping("/{movieId}")
	public MovieView getMovieById(@PathVariable final int movieId) {
		log.info("Get movie: {}...", movieId);
		return composer.getMovieById(movieId);
	}

	@GetMapping("/genres")
	public List<GenreOption> getGenres() {
		log.info("Getting Movie Genres...");
		return composer.getMovieGenres();
	}
}
