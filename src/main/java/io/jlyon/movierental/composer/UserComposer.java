package io.jlyon.movierental.composer;

import io.jlyon.movierental.accessor.UserAccessor;
import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.security.SecurityConfig;
import io.jlyon.movierental.view.NewUserView;
import io.jlyon.movierental.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserComposer {
	@Autowired
	private UserAccessor userAccess;
	@Autowired
	@Qualifier(SecurityConfig.BCRYPT_ENCODER)
	private BCryptPasswordEncoder encoder;

	public UserView getUserById(String userId) {
		UserEntity foundUser = userAccess.getOneById(UUID.fromString(userId));
		return new UserView(foundUser);
	}

	public UserView saveNewUser(NewUserView newUser) {
		UserEntity toSave = newUser.toUserEntity();

		return new UserView( userAccess.createOne(toSave) );
	}
}
