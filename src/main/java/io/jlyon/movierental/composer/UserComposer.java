package io.jlyon.movierental.composer;

import com.sun.istack.NotNull;
import io.jlyon.movierental.accessor.UserAccessor;
import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.security.SecurityConfig;
import io.jlyon.movierental.transformer.NewUserViewToEntity;
import io.jlyon.movierental.transformer.UserEntityToView;
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
	@Autowired
	private UserEntityToView toUserView;
	@Autowired
	private NewUserViewToEntity toUserEntity;

	public UserView getUserById(@NotNull String userId) {
		UserEntity foundUser = userAccess.getOneById(UUID.fromString(userId));
		return toUserView.apply(foundUser);
	}

	public UserView saveNewUser(@NotNull NewUserView newUser) {
		UserEntity toSave = toUserEntity.apply(newUser);
		return toUserView.apply( userAccess.createOne(toSave) );
	}
}
