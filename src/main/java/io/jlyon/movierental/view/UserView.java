package io.jlyon.movierental.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.jlyon.movierental.entity.UserEntity;

import java.util.UUID;

@JsonInclude(Include.NON_NULL)
public class UserView {
	private UUID id;
	private String username;
	private String email;
	@JsonIgnore
	private String password;

	public UserView() {}

	public UserView(UserEntity ue) {
		this.setId(ue.getId());
		this.setUsername(ue.getUsername());
		this.setEmail(ue.getEmail());
	}

	public UserEntity toUserEntity() {
		UserEntity ue = new UserEntity();
		ue.setUsername(this.getUsername());
		ue.setEmail(this.getEmail());
		ue.setId(this.getId());
		return ue;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
