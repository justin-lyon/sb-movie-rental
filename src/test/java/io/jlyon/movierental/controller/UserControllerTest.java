package io.jlyon.movierental.controller;

import io.jlyon.movierental.composer.UserComposer;
import io.jlyon.movierental.view.UserView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserControllerTest {
	@InjectMocks
	private UserController controller;

	@Mock
	private UserComposer composer;

	@BeforeEach
	public void setup() {
		initMocks(this);
		when(composer.getUserById(anyString())).thenReturn(new UserView());
	}

	@Test
	public void getUser_givenId_shouldInvokeComposer() {
		String userId = "this-is-a-unique-user-identifier";
		controller.getUserById(userId);
		verify(composer, times(1)).getUserById(userId);
	}
}