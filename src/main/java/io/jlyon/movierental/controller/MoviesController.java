package io.jlyon.movierental.controller;

import com.sun.istack.NotNull;
import io.jlyon.movierental.tmdb.model.MovieSearchResponse;
import io.jlyon.movierental.tmdb.service.SearchService;
import io.jlyon.movierental.view.MovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	private SearchService searchService;

	@GetMapping
	public List<MovieView> searchMovies(@RequestParam @NotNull final String searchString) {
		MovieSearchResponse response = searchService.searchMovies(searchString);
		return response
			.getResults()
			.stream()
			.map(MovieView::new)
			.collect(Collectors.toList());
	}
}
