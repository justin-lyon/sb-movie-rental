package io.jlyon.movierental.transformer;

import io.jlyon.movierental.tmdb.model.Genre;
import io.jlyon.movierental.view.GenreOption;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GenreToOption implements Function<Genre, GenreOption> {
	@Override
	public GenreOption apply(Genre genre) {
		GenreOption option = new GenreOption();
		option.setLabel(genre.label());
		option.setValue(String.valueOf(genre.id()));
		return option;
	}
}
