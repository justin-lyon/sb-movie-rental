package io.jlyon.movierental.composer;

import com.sun.istack.NotNull;
import io.jlyon.movierental.controller.MoviesController;
import io.jlyon.movierental.tmdb.model.Genre;
import io.jlyon.movierental.tmdb.model.MovieItem;
import io.jlyon.movierental.tmdb.service.DiscoverService;
import io.jlyon.movierental.tmdb.service.MovieService;
import io.jlyon.movierental.tmdb.service.SearchService;
import io.jlyon.movierental.transformer.GenreToGenreId;
import io.jlyon.movierental.transformer.MovieItemToView;
import io.jlyon.movierental.view.GenreOption;
import io.jlyon.movierental.view.MovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
	private final GenreToGenreId toGenreId = new GenreToGenreId();
	private final SimpleDateFormat isoDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	public List<MovieView> searchMovies(@NotNull final String searchTerm) {
		return searchService.searchMovies(searchTerm)
			.getResults()
			.stream()
			.map(toMovieView)
			.collect(Collectors.toList());
	}

	public List<MovieView> filterMovies(@NotNull int limit, List<String> genres, Date minReleaseDate, Date maxReleaseDate) {
		MultiValueMap params = new LinkedMultiValueMap<String, String>();
		if (genres != null && !genres.isEmpty()) {
			List<String> genreIds = getGenreIds(genres);
			params.put("with_genres", genreIds);
		}

		if (minReleaseDate != null) {
			params.put("release_date.gte", Collections.singletonList(isoDateFormatter.format(minReleaseDate)));
		}

		if (maxReleaseDate != null) {
			params.put("release_date.lte", Collections.singletonList(isoDateFormatter.format(maxReleaseDate)));
		}

		List<MovieView> movies = discoverService.getDiscoverMovie(params)
			.getResults()
			.stream()
			.map(toMovieView)
			.collect(Collectors.toList());

		if (limit < movies.size()) {
			return movies.subList(0, limit);
		}
		return movies;
	}

	public List<MovieView> getPopularMovies() {
		return discoverService.getDiscoverMovie()
			.getResults()
			.stream()
			.map(toMovieView)
			.collect(Collectors.toList());
	}

	public List<MovieView> getMoviesByGenre(@NotNull final List<String> genres) {
		List<String> genreIds = getGenreIds(genres);

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

	private List<String> getGenreIds(List<String> genres) {
		return genres
			.stream()
			.map(toGenreId)
			.collect(Collectors.toList());
	}
}
