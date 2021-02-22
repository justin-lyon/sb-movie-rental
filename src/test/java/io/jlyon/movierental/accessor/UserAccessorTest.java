package io.jlyon.movierental.accessor;

import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.exception.MovieRentalException;
import io.jlyon.movierental.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.UUID;

import static io.jlyon.movierental.accessor.UserAccessor.EMAIL_ALREADY_REGISTERED_MSG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserAccessorTest {
	@InjectMocks
	@Spy
	private UserAccessor accessor;

	@Mock
	private UserRepository repo;
	@Mock
	private BCryptPasswordEncoder encoder;

	@BeforeEach
	public void setup() {
		initMocks(this);
		when(repo.save(any(UserEntity.class))).thenReturn(new UserEntity());
		when(encoder.encode(anyString())).thenReturn("hashed-password");
		when(repo.findAll()).thenReturn(Collections.emptyList());
		when(repo.getOne(any(UUID.class))).thenReturn(new UserEntity());
		when(repo.findOneByEmail(anyString())).thenReturn(new UserEntity());
	}

	@Test
	public void loadUserByName_shouldInvokeSelf() {
		doReturn(new UserEntity()).when(accessor).getOneByEmail(anyString());
		String thisAppUsesEmailNotUsername = "burgerbob@hotmail.net";
		accessor.loadUserByUsername(thisAppUsesEmailNotUsername);
		verify(accessor, times(1)).getOneByEmail(thisAppUsesEmailNotUsername);
	}

	@Test
	public void loadUserByName_shouldThrowException() {
		doReturn(null).when(accessor).getOneByEmail(anyString());
		String thisAppUsesEmailNotUsername = "burgerbob@hotmail.net";

		MovieRentalException mre = assertThrows(
			MovieRentalException.class,
			() -> accessor.loadUserByUsername(thisAppUsesEmailNotUsername));

		verify(accessor, times(1)).getOneByEmail(thisAppUsesEmailNotUsername);
		assertNotNull(mre);
		assertEquals(UserAccessor.USER_EMAIL_NOT_FOUND_MSG, mre.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, mre.getStatus());
	}

	@Test
	public void createOne_shouldInvokeRepoSave() {
		doReturn(null).when(accessor).getOneByEmail(anyString());
		String password = "goudagoudagumdrops";
		UserEntity bob = new UserEntity();
		bob.setPassword(password);

		UserEntity savedBob = accessor.createOne(bob);

		verify(repo, times(1)).save(bob);
		assertNotEquals(bob.getPassword(), savedBob.getPassword());
	}

	@Test
	public void createOne_givenEmailAlreadyExists_shouldThrow() {
		doReturn(new UserEntity()).when(accessor).getOneByEmail(anyString());
		UserEntity bob = new UserEntity();
		bob.setEmail("burgerbob@hotmail.net");
		MovieRentalException mre = assertThrows(
			MovieRentalException.class,
			() -> accessor.createOne(bob));

		assertEquals(EMAIL_ALREADY_REGISTERED_MSG, mre.getMessage());
		assertEquals(HttpStatus.CONFLICT, mre.getStatus());
	}

	@Test
	public void getAll_shouldInvokeRepo() {
		accessor.getAll();
		verify(repo, times(1)).findAll();
	}

	@Test
	public void getOneById_shouldInvokeRepo() {
		UUID userId = UUID.randomUUID();
		accessor.getOneById(userId);
		verify(repo, times(1)).getOne(userId);
	}

	@Test
	public void getOneByEmail_shouldInvokeRepo() {
		String email = "burgerbob@hotmail.com";
		accessor.getOneByEmail(email);
		verify(repo, times(1)).findOneByEmail(email);
	}
}