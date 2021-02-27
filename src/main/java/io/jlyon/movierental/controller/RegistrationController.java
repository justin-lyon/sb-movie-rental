package io.jlyon.movierental.controller;

import io.jlyon.movierental.composer.UserComposer;
import io.jlyon.movierental.view.NewUserView;
import io.jlyon.movierental.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private UserComposer composer;

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional(rollbackFor = Exception.class)
	public UserView signup(@RequestBody NewUserView newUser) {
		log.info("Signup new User: {}", newUser.getUsername());
		return composer.saveNewUser(newUser);
	}
}
