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
	public static final String EMAIL_ALREADY_REGISTERED_MSG = "This email is already registered.";
	@Autowired
	private UserAccessor userAccess;
	@Autowired
	@Qualifier(SecurityConfig.BCRYPT_ENCODER)
	private BCryptPasswordEncoder encoder;

	public String saveNewUser(UserView newUser) {
		UserEntity existingUsers = userAccess.getOneByEmail(newUser.getEmail());
		if (existingUsers != null) {
			throw new MovieRentalException(EMAIL_ALREADY_REGISTERED_MSG, HttpStatus.CONFLICT);
		}

		UserEntity toSave = newUser.toUserEntity();
		toSave.setPassword(encoder.encode(newUser.getPassword()));

		return userAccess
			.createOne(toSave)
			.getId()
			.toString();
	}
}
