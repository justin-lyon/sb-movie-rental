package io.jlyon.movierental.transformer;

import io.jlyon.movierental.tmdb.model.MovieDetail;
import io.jlyon.movierental.view.MovieDetailView;

import java.util.function.Function;

public class MovieDetailToView implements Function<MovieDetail, MovieDetailView> {
	@Override
	public MovieDetailView apply(MovieDetail md) {
		MovieDetailView mv = new MovieDetailView();
		mv.setIsAdult(md.getIsAdult());
		mv.setBackdropPath(md.getBackdropPath());
		mv.setBelongsToCollection(md.getBelongsToCollection());
		mv.setBudget(md.getBudget());
		mv.setGenres(md.getGenres());
		mv.setHomepage(md.getHomepage());
		mv.setId(md.getId());
		mv.setImdbId(md.getImdbId());
		mv.setOriginalLanguage(md.getOriginalLanguage());
		mv.setOriginalTitle(md.getOriginalTitle());
		mv.setOverview(md.getOverview());
		mv.setPopularity(md.getPopularity());
		mv.setPosterPath(md.getPosterPath());
		mv.setProductionCompanies(md.getProductionCompanies());
		mv.setProductionCountries(md.getProductionCountries());
		mv.setReleaseDate(md.getReleaseDate());
		mv.setReleaseDates(md.getReleaseDates());
		mv.setRevenue(md.getRevenue());
		mv.setRuntime(md.getRuntime());
		mv.setSpokenLanguages(md.getSpokenLanguages());
		mv.setStatus(md.getStatus());
		mv.setTagline(md.getTagline());
		mv.setTitle(md.getTitle());
		mv.setIsVideo(md.getIsVideo());
		mv.setVoteAverage(md.getVoteAverage());
		mv.setVoteCount(md.getVoteCount());
		return mv;
	}
}
