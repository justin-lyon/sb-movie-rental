package io.jlyon.movierental.transformer;

import io.jlyon.movierental.tmdb.model.MovieDetail;
import io.jlyon.movierental.view.MovieView;

import java.util.function.Function;

public class MovieDetailToView implements Function<MovieDetail, MovieView> {
	@Override
	public MovieView apply(MovieDetail md) {
		MovieView mv = new MovieView();
		mv.setIsAdult(md.getIsAdult());
		mv.setBackdropPath(md.getBackdropPath());
		// collection
		// budget
		// genres
		// homepage
		mv.setId(String.valueOf(md.getId()));
		// imdbId
		mv.setOriginalLanguage(md.getOriginalLanguage());
		mv.setOriginalTitle(md.getOriginalTitle());
		mv.setOverview(md.getOverview());
		mv.setPopularity(md.getPopularity());
		mv.setPosterPath(md.getPosterPath());
		// productionCompanies
		// productionCountries
		mv.setReleaseDate(md.getReleaseDate());
		// revenue
		// runtime
		// spoken languages
		// status
		mv.setIsVideo(md.getIsVideo());
		mv.setVoteAverage(md.getVoteAverage());
		mv.setVoteCount(md.getVoteCount());
		return mv;
	}
}
