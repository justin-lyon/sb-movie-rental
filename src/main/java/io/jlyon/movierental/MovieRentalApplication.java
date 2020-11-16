package io.jlyon.movierental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;
import org.springframework.security.core.SpringSecurityCoreVersion;

@SpringBootApplication
public class MovieRentalApplication {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MovieRentalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieRentalApplication.class, args);
		System.out.println("Springboot Version: " + SpringBootVersion.getVersion());
		System.out.println("Spring Version: " +  SpringVersion.getVersion());
		System.out.println("Spring Security Version: " + SpringSecurityCoreVersion.getVersion());
		System.out.println("\n\n\u001b[32m Movie Rental Application Started!\n\u001b[0m");
	}
}
