package io.jlyon.movierental.controller;

import io.jlyon.movierental.composer.UserComposer;
import io.jlyon.movierental.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserComposer composer;

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional(rollbackFor = Exception.class)
	public String signUp(@RequestBody UserView newUser) {
		return composer.saveNewUser(newUser);
	}
}
