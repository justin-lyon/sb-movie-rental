package io.jlyon.movierental.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class MovieItem {
	private Integer id;
	private String title;
	@JsonProperty("original_title")
	private String originalTitle;
	@JsonProperty("original_language")
	private String originalLanguage;
	@JsonProperty("poster_path")
	private String posterPath;
	private String overview;
	@JsonProperty("release_date")
	private String releaseDate;
	@JsonProperty("backdrop_path")
	private String backdropPath;
	@JsonProperty("genre_ids")
	private List<Integer> genreIds;
	@JsonProperty("vote_count")
	private int voteCount;
	private Double popularity;
	@JsonProperty("vote_average")
	private Double voteAverage;
	@JsonProperty("adult")
	private boolean isAdult;
	@JsonProperty("video")
	private boolean isVideo;

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
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public void setAdult(boolean adult) {
		isAdult = adult;
	}

	public boolean isVideo() {
		return isVideo;
	}

	public void setIsVideo(boolean video) {
		isVideo = video;
	}
}
