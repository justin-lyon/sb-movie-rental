package io.jlyon.movierental.accessor;

import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.exception.MovieRentalException;
import io.jlyon.movierental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAccessor implements UserDetailsService {
	public static final String USER_EMAIL_NOT_FOUND_MSG = "That email is not registered.";
	public static final String EMAIL_ALREADY_REGISTERED_MSG = "This email is already registered.";

	@Autowired
	private UserRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String s) {
		UserEntity user = this.getOneByEmail(s);
		if (user == null) {
			throw new MovieRentalException(USER_EMAIL_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
		}
		return user;
	}

	public UserEntity createOne(UserEntity newUser) {
		UserEntity existingUsers = this.getOneByEmail(newUser.getEmail());
		if (existingUsers != null) {
			throw new MovieRentalException(EMAIL_ALREADY_REGISTERED_MSG, HttpStatus.CONFLICT);
		}
		newUser.setPassword(encoder.encode(newUser.getPassword()));
		return repository.save(newUser);
	}

	public List<UserEntity> getAll() {
		return repository.findAll();
	}

	public UserEntity getOneById(UUID id) {
		return repository.getOne(id);
	}

	public UserEntity getOneByEmail(String email) {
		return repository.findOneByEmail(email);
	}

	public UserEntity updateUser(UserEntity newUser) {
		return repository.getOne(newUser.getId());
	}
}
