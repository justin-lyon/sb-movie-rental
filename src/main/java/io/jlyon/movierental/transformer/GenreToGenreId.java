package io.jlyon.movierental.transformer;

import io.jlyon.movierental.tmdb.model.Genre;

import java.util.function.Function;

public class GenreToGenreId implements Function<String, String> {

	public String apply(String genre) {
		return String.valueOf(Genre.valueOf(genre.toUpperCase()).id());
	}
}
