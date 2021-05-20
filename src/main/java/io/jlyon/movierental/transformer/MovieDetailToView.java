package io.jlyon.movierental.transformer;

import io.jlyon.movierental.exception.MovieRentalException;
import io.jlyon.movierental.tmdb.model.CountryReleaseItem;
import io.jlyon.movierental.tmdb.model.LanguageItem;
import io.jlyon.movierental.tmdb.model.MovieDetail;
import io.jlyon.movierental.tmdb.model.MovieReleasesGetResponse;
import io.jlyon.movierental.tmdb.model.ReleaseItem;
import io.jlyon.movierental.view.LanguageView;
import io.jlyon.movierental.view.MovieDetailView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
public class MovieDetailToView implements Function<MovieDetail, MovieDetailView> {
	public static final String ERROR_CANNOT_FIND_COUNTRY = "No CountryReleaseItem for ISO 3166-1 Country Code: %s";
	public static final String DEFAULT_COUNTRY_CODE = "US";

	@Autowired
	private LanguageItemToView toLanguageView;

	@Override
	public MovieDetailView apply(MovieDetail md) {
		MovieDetailView mv = new MovieDetailView();
		mv.setIsAdult(md.getIsAdult());
		mv.setBackdropPath(md.getBackdropPath());
		mv.setBelongsToCollection(md.getBelongsToCollection());
		mv.setBudget(md.getBudget());
		mv.setGenres(md.getGenres());
		mv.setHomepage(md.getHomepage());
		mv.setId(md.getId());
		mv.setImdbId(md.getImdbId());
		mv.setOriginalLanguage(md.getOriginalLanguage());
		mv.setOriginalTitle(md.getOriginalTitle());
		mv.setOverview(md.getOverview());
		mv.setPopularity(md.getPopularity());
		mv.setPosterPath(md.getPosterPath());
		mv.setProductionCompanies(md.getProductionCompanies());
		mv.setProductionCountries(md.getProductionCountries());

		// Read Release Date from MovieDetail
		mv.setReleaseDate(getReleaseDate(md));

		mv.setRevenue(md.getRevenue());
		mv.setRuntime(md.getRuntime());
		mv.setSpokenLanguages(
			this.getSpokenLanguages(md.getSpokenLanguages())
		);
		mv.setStatus(md.getStatus());
		mv.setTagline(md.getTagline());
		mv.setTitle(md.getTitle());
		mv.setIsVideo(md.getIsVideo());
		mv.setVoteAverage(md.getVoteAverage());
		mv.setVoteCount(md.getVoteCount());
		return mv;
	}

	private List<LanguageView> getSpokenLanguages(List<LanguageItem> items) {
		return defaultIfNull(items, Collections.<LanguageItem>emptyList())
			.stream()
			.map(toLanguageView)
			.collect(Collectors.toList());
	}

	private LocalDate getReleaseDate(MovieDetail md) {
		MovieReleasesGetResponse releaseResponse = md.getReleaseDates();
		if (releaseResponse == null) {
			return md.getReleaseDate();
		}

		Optional<CountryReleaseItem> possibleCountryReleaseItem = releaseResponse.getResults()
			.stream()
			.filter(countryReleaseItem -> countryReleaseItem.getCountryCode().equals(DEFAULT_COUNTRY_CODE))
			.findFirst();

		if (possibleCountryReleaseItem.isEmpty()) {
			throw new MovieRentalException(format(ERROR_CANNOT_FIND_COUNTRY, DEFAULT_COUNTRY_CODE));
		}

		// Type == 3 is the Theatrical Release Date
		Optional<ReleaseItem> possibleTheatricalRelease = possibleCountryReleaseItem
			.get()
			.getReleaseDates()
			.stream()
			.filter(item -> item.getType() == 3)
			.findFirst();

		Optional<ReleaseItem> possibleNextBestRelease = possibleCountryReleaseItem
			.get()
			.getReleaseDates()
			.stream()
			.findFirst();

		if (possibleTheatricalRelease.isEmpty()) {
//			throw new MovieRentalException(format(ERROR_CANNOT_FIND_THEATRICAL_RELEASE, DEFAULT_COUNTRY_CODE));
			return possibleNextBestRelease.get().getReleaseDate();
		}

		return possibleTheatricalRelease.get().getReleaseDate();
	}
}
