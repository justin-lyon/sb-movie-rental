package io.jlyon.movierental.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.jlyon.movierental.entity.UserEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewUserView {
	private String username;
	private String email;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "NewUserView{" +
			"username='" + username + '\'' +
			", email='" + email + '\'' +
			'}';
	}
}
