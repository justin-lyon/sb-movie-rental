package io.jlyon.movierental.composer;

import io.jlyon.movierental.accessor.UserAccessor;
import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.exception.MovieRentalException;
import io.jlyon.movierental.security.SecurityConfig;
import io.jlyon.movierental.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserComposer {
	@Autowired
	private UserAccessor userAccess;
	@Autowired
	@Qualifier(SecurityConfig.BCRYPT_ENCODER)
	private BCryptPasswordEncoder encoder;

	public String saveNewUser(UserView newUser) {
		UserEntity toSave = newUser.toUserEntity();

		return userAccess
			.createOne(toSave)
			.getId()
			.toString();
	}
}
