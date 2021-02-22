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

import static org.junit.jupiter.api.Assertions.*;
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
		doReturn(new UserEntity()).when(accessor).getOneByEmail(anyString());
		when(repo.save(any(UserEntity.class))).thenReturn(new UserEntity());
		when(encoder.encode(anyString())).thenReturn("hashed-password");
	}

	@Test
	public void loadUserByName_shouldInvokeSelf() {
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
	public void createOne_shouldInsertToDB() {
		doReturn(null).when(accessor).getOneByEmail(anyString());
		String password = "goudagoudagumdrops";
		UserEntity bob = new UserEntity();
		bob.setPassword(password);

		UserEntity savedBob = accessor.createOne(bob);

		verify(repo, times(1)).save(bob);
		assertNotEquals(bob.getPassword(), savedBob.getPassword());
	}
}