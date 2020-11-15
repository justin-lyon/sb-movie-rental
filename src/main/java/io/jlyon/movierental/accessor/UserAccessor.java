package io.jlyon.movierental.accessor;

import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAccessor {
	@Autowired
	private UserRepository repository;

	public UserEntity createOne(UserEntity newUser) {
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
