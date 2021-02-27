package io.jlyon.movierental.repository;

import io.jlyon.movierental.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
	@Query("FROM #{#entityName} WHERE email = :email")
	UserEntity findOneByEmail(@Param("email") String email);
}
