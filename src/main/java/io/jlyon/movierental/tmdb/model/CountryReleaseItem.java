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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<ReleaseItem> getReleaseDates() {
		return releaseDates;
	}

	public void setReleaseDates(List<ReleaseItem> releaseDates) {
		this.releaseDates = releaseDates;
	}
}
