package io.jlyon.movierental.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class AppConfig {
	private Integer jwtDurationHours;

	public Integer getJwtDurationHours() {
		return jwtDurationHours;
	}

	public void setJwtDurationHours(Integer jwtDurationHours) {
		this.jwtDurationHours = jwtDurationHours;
	}
}
