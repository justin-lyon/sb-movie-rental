package io.jlyon.movierental.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class DiscoverMovieResponse extends Paginateable {
	private List<MovieItem> results;

	public List<MovieItem> getResults() {
		return results;
	}

	public void setResults(List<MovieItem> results) {
		this.results = results;
	}
}
