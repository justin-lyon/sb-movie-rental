package io.jlyon.movierental.tmdb.model;

import java.util.List;

public class MovieSearchResponse extends PaginatedResponse {
	private List<MovieItem> results;

	public List<MovieItem> getResults() {
		return results;
	}

	public void setResults(List<MovieItem> results) {
		this.results = results;
	}
}
