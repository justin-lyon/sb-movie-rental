package io.jlyon.movierental.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class MovieSearchResponse extends PaginatedResponse {
	private List<MovieItem> results = new ArrayList<>();

	public List<MovieItem> getResults() {
		return results;
	}

	public void setResults(List<MovieItem> results) {
		this.results = results;
	}
}
