package io.jlyon.movierental.transformer;

import io.jlyon.movierental.exception.MovieRentalException;
import io.jlyon.movierental.tmdb.model.CountryReleaseItem;
import io.jlyon.movierental.tmdb.model.MovieDetail;
import io.jlyon.movierental.tmdb.model.MovieReleasesGetResponse;
import io.jlyon.movierental.tmdb.model.ReleaseItem;
import io.jlyon.movierental.view.MovieDetailView;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MovieDetailToView implements Function<MovieDetail, MovieDetailView> {
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
		mv.setSpokenLanguages(md.getSpokenLanguages());
		mv.setStatus(md.getStatus());
		mv.setTagline(md.getTagline());
		mv.setTitle(md.getTitle());
		mv.setIsVideo(md.getIsVideo());
		mv.setVoteAverage(md.getVoteAverage());
		mv.setVoteCount(md.getVoteCount());
		return mv;
	}

	private LocalDate getReleaseDate(MovieDetail md) {
		MovieReleasesGetResponse releaseResponse = md.getReleaseDates();
		if (releaseResponse == null) {
			return md.getReleaseDate();
		}

		Optional<CountryReleaseItem> possibleCountryReleaseItem = releaseResponse.getResults()
			.stream()
			.filter(countryReleaseItem -> countryReleaseItem.getCountryCode().equals("US"))
			.findFirst();

		if (possibleCountryReleaseItem.isEmpty()) {
			throw new MovieRentalException("No CountryReleaseItem for ISO 3166-1 Country Code: US");
		}

		// Type == 3 is the Theatrical Release Date
		Optional<ReleaseItem> possibleDate = possibleCountryReleaseItem
			.get()
			.getReleaseDates()
			.stream()
			.filter(item -> item.getType() == 3)
			.findFirst();

		if (possibleDate.isEmpty()) {
			throw new MovieRentalException("No Theatrical Release (Type 3) found for Country Code: US");
		}

		return possibleDate.get().getReleaseDate();
	}
}
