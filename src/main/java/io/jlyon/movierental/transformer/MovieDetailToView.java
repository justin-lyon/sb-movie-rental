package io.jlyon.movierental.transformer;

import io.jlyon.movierental.tmdb.model.MovieDetail;
import io.jlyon.movierental.view.MovieView;

import java.util.function.Function;

public class MovieDetailToView implements Function<MovieDetail, MovieView> {
	@Override
	public MovieView apply(MovieDetail md) {
		MovieView mv = new MovieView();
		mv.setId(String.valueOf(md.getId()));
		return mv;
	}
}
