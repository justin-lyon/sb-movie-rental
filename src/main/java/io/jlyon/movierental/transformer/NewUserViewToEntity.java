package io.jlyon.movierental.transformer;

import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.view.NewUserView;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class NewUserViewToEntity implements Function<NewUserView, UserEntity> {
	@Override
	public UserEntity apply(NewUserView v) {
		UserEntity e = new UserEntity();
		e.setUsername(v.getUsername());
		e.setEmail(v.getEmail());
		e.setPassword(v.getPassword());
		return e;
	}
}
