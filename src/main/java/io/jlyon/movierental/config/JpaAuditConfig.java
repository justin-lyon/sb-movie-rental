package io.jlyon.movierental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Setup based on the blog at springbootdev.com
 * https://springbootdev.com/2018/03/13/spring-data-jpa-auditing-with-createdby-createddate-lastmodifiedby-and-lastmodifieddate/
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditConfig {
	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
