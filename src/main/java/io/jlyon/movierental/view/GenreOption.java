package io.jlyon.movierental.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.jlyon.movierental.tmdb.model.Genre;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class GenreOption {
	private String label;
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
