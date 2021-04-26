package io.jlyon.movierental.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ReleaseItem {
	private String certification;
	@JsonProperty("iso_639_1")
	private String languageCode;
	@JsonProperty("release_date")
	private LocalDate releaseDate;
	private int type;
	private String note;
}
