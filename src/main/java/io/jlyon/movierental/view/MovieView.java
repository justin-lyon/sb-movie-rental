package io.jlyon.movierental.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.jlyon.movierental.tmdb.model.MovieItem;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class MovieView {
	private String id;
	private String title;
	private String originalTitle;
	private String originalLanguage;
	private String posterPath;
	private String overview;
	private LocalDate releaseDate;
	private String backdropPath;
	private List<Integer> genreIds;
	private Integer voteCount;
	private Double popularity;
	private Double voteAverage;
	private boolean isAdult;
	private boolean hasVideo;

	public MovieView(MovieItem mi) {
		this.setId(mi.getId().toString());
		this.setTitle(mi.getTitle());
		this.setOriginalTitle(mi.getOriginalTitle());
		this.setOriginalLanguage(mi.getOriginalLanguage());
		this.setPosterPath(mi.getPosterPath());
		this.setOverview(mi.getOverview());
		this.setReleaseDate(mi.getReleaseDate() != null ? LocalDate.parse(mi.getReleaseDate()) : null);
		this.setBackdropPath(mi.getBackdropPath());
		this.setGenreIds(mi.getGenreIds());
		this.setVoteCount(mi.getVoteCount());
		this.setPopularity(mi.getPopularity());
		this.setVoteAverage(mi.getVoteAverage());
		this.setIsAdult(mi.isAdult());
		this.setHasVideo(mi.hasVideo());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
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

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
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

	public void setIsAdult(boolean adult) {
		isAdult = adult;
	}

	public boolean hasVideo() {
		return hasVideo;
	}

	public void setHasVideo(boolean hasVideo) {
		this.hasVideo = hasVideo;
	}
}
