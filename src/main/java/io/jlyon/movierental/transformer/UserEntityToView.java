package io.jlyon.movierental.transformer;

import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.view.UserView;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserEntityToView implements Function<UserEntity, UserView> {
	@Override
	public UserView apply(UserEntity e) {
		UserView v = new UserView();
		v.setId(e.getId());
		v.setUsername(e.getUsername());
		v.setEmail(e.getEmail());
		return v;
	}
}
