package io.jlyon.movierental.transformer;

import io.jlyon.movierental.tmdb.model.MovieItem;
import io.jlyon.movierental.view.MovieView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class MovieItemToViewTest {
	private final MovieItemToView transformer = new MovieItemToView();

	private final MovieItem item = new MovieItem();

	@BeforeEach
	public void setup() {
		item.setId(2292);
		item.setTitle("Clerks");
		item.setAdult(false);
		item.setBackdropPath("relative/backdrop/url.jpg");
		item.setGenreIds(Collections.singletonList(35));
		item.setOriginalLanguage("en");
		item.setOriginalTitle("Clerks");
		item.setOverview("blah blah blah long text");
		item.setPopularity(12.4);
		item.setPosterPath("relative/poster/url.jpg");
		item.setReleaseDate("1994-10-19");
		item.setIsVideo(false);
		item.setVoteAverage(7.4);
		item.setVoteCount(1717);
	}

	@Test
	public void apply_shouldCreateMovieViewFromItem() {
		MovieView view = transformer.apply(item);

		assertEquals(String.valueOf(item.getId()), view.getId());
		assertEquals(item.getTitle(), view.getTitle());
		assertEquals(item.isAdult(), view.getIsAdult());
		assertEquals(item.getBackdropPath(), view.getBackdropPath());
		assertEquals(item.getGenreIds(), view.getGenreIds());
		assertEquals(item.getOriginalLanguage(), view.getOriginalLanguage());
		assertEquals(item.getOriginalTitle(), view.getOriginalTitle());
		assertEquals(item.getOverview(), view.getOverview());
		assertEquals(item.getPopularity(), view.getPopularity());
		assertEquals(item.getPosterPath(), view.getPosterPath());
		assertEquals(LocalDate.parse(item.getReleaseDate()), view.getReleaseDate());
		assertEquals(item.isVideo(), view.getIsVideo());
		assertEquals(item.getVoteAverage(), view.getVoteAverage());
		assertEquals(item.getVoteCount(), view.getVoteCount());
	}
}