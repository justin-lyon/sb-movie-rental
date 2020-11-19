package io.jlyon.movierental.controller;

import com.sun.istack.NotNull;
import io.jlyon.movierental.tmdb.model.MovieSearchResponse;
import io.jlyon.movierental.tmdb.service.MovieService;
import io.jlyon.movierental.tmdb.service.SearchService;
import io.jlyon.movierental.view.MovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	private SearchService searchService;
	@Autowired
	private MovieService movieService;

	@GetMapping
	public List<MovieView> searchMovies(@RequestParam @NotNull final String searchString) {
		// TODO - UNDERSTAND - Why do I get less data than the API Gives?
		MovieSearchResponse response = searchService.searchMovies(searchString);
		return response
			.getResults()
			.stream()
			.map(MovieView::new)
			.collect(Collectors.toList());
	}

	@GetMapping("/{movieId}")
	public MovieView getMovieById(@PathVariable int movieId) {
		return new MovieView(movieService.queryMovieById(movieId));
	}
}
