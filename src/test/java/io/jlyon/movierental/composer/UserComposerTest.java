package io.jlyon.movierental.composer;

import io.jlyon.movierental.accessor.UserAccessor;
import io.jlyon.movierental.entity.UserEntity;
import io.jlyon.movierental.view.NewUserView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserComposerTest {
	@InjectMocks
	private UserComposer composer;

	@Mock
	private UserAccessor accessor;

	@BeforeEach
	public void setup() {
		initMocks(this);
		when(accessor.getOneById(any(UUID.class))).thenReturn(new UserEntity());
		when(accessor.createOne(any(UserEntity.class))).thenReturn(new UserEntity());
	}

	@Test
	public void getUserById_givenId_shouldInvokeAccessor() {
		String userId = UUID.randomUUID().toString();
		composer.getUserById(userId);
		verify(accessor, times(1)).getOneById(UUID.fromString(userId));
	}

	@Test
	public void saveNewUser_givenNewUser_shouldInvokeAccessor() {
		NewUserView newBob = new NewUserView();
		composer.saveNewUser(newBob);
		verify(accessor, times(1)).createOne(any(UserEntity.class));
	}
}