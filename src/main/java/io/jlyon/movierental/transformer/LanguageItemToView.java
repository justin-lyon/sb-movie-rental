package io.jlyon.movierental.transformer;

import io.jlyon.movierental.tmdb.model.LanguageItem;
import io.jlyon.movierental.view.LanguageView;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class LanguageItemToView implements Function<LanguageItem, LanguageView> {
	@Override
	public LanguageView apply(LanguageItem li) {
		LanguageView lv = new LanguageView();
		lv.setLanguageCode(li.getLanguageCode());
		lv.setName(li.getName());
		return lv;
	}
}
