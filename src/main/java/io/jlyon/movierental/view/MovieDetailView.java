package io.jlyon.movierental.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.jlyon.movierental.tmdb.model.CollectionItem;
import io.jlyon.movierental.tmdb.model.GenreItem;
import io.jlyon.movierental.tmdb.model.LanguageItem;
import io.jlyon.movierental.tmdb.model.MovieReleasesGetResponse;
import io.jlyon.movierental.tmdb.model.ProductionCompany;
import io.jlyon.movierental.tmdb.model.ProductionCountry;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonPropertyOrder({"id", "title"})
public class MovieDetailView {
	private boolean isAdult;
	private String backdropPath;
	private CollectionItem belongsToCollection;
	private int budget;
	private List<GenreItem> genres;
	private String homepage;
	private int id;
	private String imdbId;
	private String originalLanguage;
	private String originalTitle;
	private String overview;
	private Double popularity;
	private String posterPath;
	private List<ProductionCompany> productionCompanies;
	private List<ProductionCountry> productionCountries;
	private LocalDate releaseDate;
	private int revenue;
	private Integer runtime;
	private List<LanguageItem> spokenLanguages;
	private String status;
	private String tagline;
	private String title;
	private boolean isVideo;
	private Double voteAverage;
	private int voteCount;

	public boolean getIsAdult() {
		return isAdult;
	}

	public void setIsAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public CollectionItem getBelongsToCollection() {
		return belongsToCollection;
	}

	public void setBelongsToCollection(CollectionItem belongsToCollection) {
		this.belongsToCollection = belongsToCollection;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public List<GenreItem> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreItem> genres) {
		this.genres = genres;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public List<ProductionCompany> getProductionCompanies() {
		return productionCompanies;
	}

	public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}

	public List<ProductionCountry> getProductionCountries() {
		return productionCountries;
	}

	public void setProductionCountries(List<ProductionCountry> productionCountries) {
		this.productionCountries = productionCountries;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public List<LanguageItem> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(List<LanguageItem> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(boolean isVideo) {
		this.isVideo = isVideo;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
}
