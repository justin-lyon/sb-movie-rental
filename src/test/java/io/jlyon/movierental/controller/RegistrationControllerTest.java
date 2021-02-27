package io.jlyon.movierental.controller;

import io.jlyon.movierental.composer.UserComposer;
import io.jlyon.movierental.view.NewUserView;
import io.jlyon.movierental.view.UserView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegistrationControllerTest {
	@InjectMocks
	private RegistrationController controller;

	@Mock
	private UserComposer composer;

	@BeforeEach
	public void setup() {
		initMocks(this);
		when(composer.saveNewUser(any(NewUserView.class))).thenReturn(new UserView());
	}

	@Test
	public void signup_givenNewUser_shouldInvokeComposer() {
		controller.signup(new NewUserView());
		verify(composer, times(1)).saveNewUser(any(NewUserView.class));
	}
}