package io.jlyon.movierental.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class GenreResponse {
	private List<GenreItem> genres;

	public List<GenreItem> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreItem> genres) {
		this.genres = genres;
	}
}
