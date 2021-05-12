package io.jlyon.movierental.transformer;

import io.jlyon.movierental.tmdb.model.CountryReleaseItem;
import io.jlyon.movierental.tmdb.model.MovieDetail;
import io.jlyon.movierental.tmdb.model.MovieReleasesGetResponse;
import io.jlyon.movierental.tmdb.model.ReleaseItem;
import io.jlyon.movierental.view.MovieDetailView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.util.Collections;

import static io.jlyon.movierental.transformer.MovieDetailToView.DEFAULT_COUNTRY_CODE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class MovieDetailToViewTest {
	@InjectMocks
	private MovieDetailToView transformer;

	private MovieDetail md;

	@BeforeEach
	public void setup() {
		initMocks(this);
		initMovieDetail();
	}

	@Test
	public void apply_givenDetail_shouldGetView() {
		MovieDetailView mv = transformer.apply(md);
		assertEquals(md.getIsAdult(), mv.getIsAdult());
		assertEquals(md.getBackdropPath(), mv.getBackdropPath());
		assertEquals(md.getBelongsToCollection(), mv.getBelongsToCollection());
		assertEquals(md.getBudget(), mv.getBudget());
		assertEquals(md.getGenres(), mv.getGenres());
		assertEquals(md.getHomepage(), mv.getHomepage());
		assertEquals(md.getId(), mv.getId());
		assertEquals(md.getImdbId(), mv.getImdbId());
		assertEquals(md.getOriginalLanguage(), mv.getOriginalLanguage());
		assertEquals(md.getOriginalTitle(), mv.getOriginalTitle());
		assertEquals(md.getOverview(), mv.getOverview());
		assertEquals(md.getPopularity(), mv.getPopularity());
		assertEquals(md.getPosterPath(), mv.getPosterPath());
		assertEquals(md.getProductionCompanies(), mv.getProductionCompanies());
		assertEquals(md.getProductionCountries(), mv.getProductionCountries());
		assertEquals(md.getReleaseDate(), mv.getReleaseDate());
		assertEquals(md.getRevenue(), mv.getRevenue());
		assertEquals(md.getRuntime(), mv.getRuntime());
		assertEquals(md.getSpokenLanguages(), mv.getSpokenLanguages());
		assertEquals(md.getStatus(), mv.getStatus());
		assertEquals(md.getTagline(), mv.getTagline());
		assertEquals(md.getTitle(), mv.getTitle());
		assertEquals(md.getIsVideo(), mv.getIsVideo());
		assertEquals(md.getVoteAverage(), mv.getVoteAverage());
		assertEquals(md.getVoteCount(), mv.getVoteCount());
	}

	@Test
	public void apply_givenDetailWithReleaseDates_shouldSetReleaseDateByISOCode() {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		md.setReleaseDates(setAppendedReleaseDateResponse(yesterday));

		MovieDetailView mv = transformer.apply(md);
		assertEquals(yesterday, mv.getReleaseDate());
	}

	private void initMovieDetail() {
		md = new MovieDetail();
		md.setIsAdult(false);
		md.setBackdropPath("url/to/backdrop.jpg");
		md.setBelongsToCollection(null);
		md.setBudget(100000);
		md.setGenres(Collections.emptyList());
		md.setHomepage("url/to/homepage.html");
		md.setId(2);
		md.setImdbId("3-imdbs");
		md.setOriginalLanguage("en");
		md.setOriginalTitle("much-original-so-title-wow");
		md.setOverview("Goodnight Cow jumping over the moon.");
		md.setPopularity(9000.01);
		md.setPosterPath("url/to/posterpath.jpg");
		md.setProductionCompanies(Collections.emptyList());
		md.setProductionCountries(Collections.emptyList());
		md.setReleaseDate(LocalDate.now());
		md.setRevenue(1000000);
		md.setRuntime(120);
		md.setSpokenLanguages(Collections.emptyList());
		md.setStatus("Released");
		md.setTagline("Goodnight bears. Goodnight chairs.");
		md.setTitle("Goodnight Moon");
		md.setIsVideo(false);
		md.setVoteAverage(9.0);
		md.setVoteCount(2000);
	}

	private MovieReleasesGetResponse setAppendedReleaseDateResponse(LocalDate expected) {
		ReleaseItem ri = new ReleaseItem();
		ri.setType(3);
		ri.setReleaseDate(expected);

		CountryReleaseItem cri = new CountryReleaseItem();
		cri.setCountryCode(DEFAULT_COUNTRY_CODE);
		cri.setReleaseDates(Collections.singletonList(ri));

		MovieReleasesGetResponse response = new MovieReleasesGetResponse();
		response.setResults(Collections.singletonList(cri));
		return response;
	}
}