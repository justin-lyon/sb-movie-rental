package io.jlyon.movierental.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.jlyon.movierental.tmdb.model.Genre;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class GenreOption {
	private String label;
	private String value;
	private Genre genre;

	public GenreOption(Genre g) {
		this.label = g.label();
		this.value = String.valueOf(g.id());
		this.genre = g;
	}

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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
}
