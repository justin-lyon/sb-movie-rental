package io.jlyon.movierental.tmdb.model;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

/**
 * Each Genre is hard coded to correspond with results from the
 * /genre/movie/list
 */
public enum Genre {
	ACTION(28),
	ADVENTURE(12),
	ANIMATION(16),
	COMEDY(35),
	CRIME(80),
	DOCUMENTARY(99),
	DRAMA(18),
	FAMILY(10751),
	FANTASY(14),
	HISTORY(36),
	HORROR(27),
	MUSIC(10402),
	MYSTERY(9648),
	ROMANCE(10749),
	SCIENCE_FICTION(878),
	TV_MOVIE(10770),
	THRILLER(53),
	WAR(10752),
	WESTERN(72);

	private final int id;

	private Genre(int id) {
		this.id = id;
	}

	public int id() {
		return this.id;
	}

	public String label() {
		String rmSnake = this.name().replace("_", " ");
		return capitalizeFully(rmSnake.toLowerCase());
	}
}
