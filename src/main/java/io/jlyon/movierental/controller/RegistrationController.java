package io.jlyon.movierental.controller;

import io.jlyon.movierental.composer.UserComposer;
import io.jlyon.movierental.security.SecurityConstants;
import io.jlyon.movierental.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

	@Autowired
	private UserComposer composer;

	@PostMapping
	@RequestMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional(rollbackFor = Exception.class)
	public String signUp(@RequestBody UserView newUser) {
		System.out.println("new user coming " + newUser);
		return composer.saveNewUser(newUser);
	}
}
