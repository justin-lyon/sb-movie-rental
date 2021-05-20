package io.jlyon.movierental.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class MovieReleasesGetResponse {
	private int id;
	private List<CountryReleaseItem> results;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CountryReleaseItem> getResults() {
		return results;
	}

	public void setResults(List<CountryReleaseItem> results) {
		this.results = results;
	}
}
