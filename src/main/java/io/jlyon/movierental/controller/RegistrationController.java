package io.jlyon.movierental.controller;

import io.jlyon.movierental.view.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class RegistrationController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String signUp(@RequestBody UserView newUser) {
		System.out.println("test hit user " + newUser);
		return newUser.getEmail();
	}
}
