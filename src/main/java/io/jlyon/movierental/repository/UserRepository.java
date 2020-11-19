package io.jlyon.movierental.repository;

import io.jlyon.movierental.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

// TODO - Persist Docker MySQL Container Data outside the container.
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
	@Query("FROM UserEntity u WHERE u.email = :email")
	UserEntity findOneByEmail(@Param("email") String email);
	@Query("FROM UserEntity u WHERE u.username = :username")
	UserEntity findOneByUsername(@Param("username") String username);
}
