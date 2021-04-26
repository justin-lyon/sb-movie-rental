package io.jlyon.movierental.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class CountryReleaseItem {
	@JsonProperty("iso_3166_1")
	private String countryCode;
	@JsonProperty("release_dates")
	private List<ReleaseItem> releaseDates;
}
