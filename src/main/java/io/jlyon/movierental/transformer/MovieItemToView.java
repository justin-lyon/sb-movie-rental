package io.jlyon.movierental.transformer;

import io.jlyon.movierental.tmdb.model.MovieItem;
import io.jlyon.movierental.view.MovieView;

import java.time.LocalDate;

public class MovieItemToView implements Transformer<MovieItem, MovieView> {
	public MovieView transform(MovieItem item) {
		MovieView view = new MovieView();
		view.setId(item.getId().toString());
		view.setTitle(item.getTitle());
		view.setOriginalTitle(item.getOriginalTitle());
		view.setOriginalLanguage(item.getOriginalLanguage());
		view.setPosterPath(item.getPosterPath());
		view.setOverview(item.getOverview());
		view.setReleaseDate(item.getReleaseDate() != null && !item.getReleaseDate().isEmpty() ? LocalDate.parse(item.getReleaseDate()) : null);
		view.setBackdropPath(item.getBackdropPath());
		view.setGenreIds(item.getGenreIds());
		view.setVoteCount(item.getVoteCount());
		view.setPopularity(item.getPopularity());
		view.setVoteAverage(item.getVoteAverage());
		view.setIsAdult(item.isAdult());
		view.setIsVideo(item.isVideo());
		return view;
	}
}
