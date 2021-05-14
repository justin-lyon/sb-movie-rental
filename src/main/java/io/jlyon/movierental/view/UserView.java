package io.jlyon.movierental.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.jlyon.movierental.entity.UserEntity;

import java.util.UUID;

@JsonInclude(Include.NON_NULL)
public class UserView {
	private UUID id;
	private String username;
	private String email;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "UserView{" +
			"id=" + id +
			", username='" + username + '\'' +
			", email='" + email + '\'' +
			'}';
	}
}
