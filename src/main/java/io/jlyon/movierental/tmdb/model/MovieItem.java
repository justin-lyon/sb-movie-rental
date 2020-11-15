package io.jlyon.movierental.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieItem {
	private Integer id;
	private String title;
	private String original_title;
	private String original_language;
	private String poster_path;
	private String overview;
	private String release_date;
	private String backdrop_path;
	private List<Integer> genre_ids;
	private int vote_count;
	private Double popularity;
	private Double vote_average;
	private boolean adult;
	private boolean video;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return original_title;
	}

	public void setOriginalTitle(String original_title) {
		this.original_title = original_title;
	}

	public String getOriginalLanguage() {
		return original_language;
	}

	public void setOriginalLanguage(String original_language) {
		this.original_language = original_language;
	}

	public String getPosterPath() {
		return poster_path;
	}

	public void setPosterPath(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getReleaseDate() {
		return release_date;
	}

	public void setReleaseDate(String release_date) {
		this.release_date = release_date;
	}

	public String getBackdropPath() {
		return backdrop_path;
	}

	public void setBackdropPath(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}

	public List<Integer> getGenreIds() {
		return genre_ids;
	}

	public void setGenreIds(List<Integer> genre_ids) {
		this.genre_ids = genre_ids;
	}

	public int getVoteCount() {
		return vote_count;
	}

	public void setVoteCount(int vote_count) {
		this.vote_count = vote_count;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public Double getVoteAverage() {
		return vote_average;
	}

	public void setVoteAverage(Double vote_average) {
		this.vote_average = vote_average;
	}

	public boolean isAdult() {
		return adult;
	}

	public void setIsAdult(boolean adult) {
		this.adult = adult;
	}

	public boolean hasVideo() {
		return video;
	}

	public void setHasVideo(boolean video) {
		this.video = video;
	}
}
