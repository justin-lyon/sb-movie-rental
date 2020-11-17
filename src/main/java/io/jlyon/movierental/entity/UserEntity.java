package io.jlyon.movierental.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class UserEntity implements UserDetails {
	@Id
	@GeneratedValue
	@Column(updatable = false, nullable = false, unique=true, columnDefinition = "BINARY(16)")
	private UUID id;
	@Column(nullable = false, length = 30)
	private String username;
	@Column(nullable = false, length = 50, unique = true)
	private String email;
	@Column(nullable = false, length = 255)
	private String password;
//	@Column(columnDefinition = "default 1")
//	private boolean isActive;
//	@Column(columnDefinition = "default 0")
//	private boolean isLocked;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
//		return this.isActive();
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
//		return this.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
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

//	public boolean isActive() {
//		return isActive;
//	}
//
//	public void setActive(boolean active) {
//		isActive = active;
//	}

//	public boolean isLocked() {
//		return isLocked;
//	}
//
//	public void setLocked(boolean locked) {
//		isLocked = locked;
//	}

	@Override
	public String toString() {
		return "UserEntity{" +
			"id=" + id +
			", username='" + username + '\'' +
			", email='" + email + '\'' +
			'}';
	}
}
