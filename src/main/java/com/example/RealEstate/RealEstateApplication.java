package com.example.RealEstate;

import com.example.RealEstate.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RealEstateApplication implements CommandLineRunner {

	private final UserService userService; // Inject UserService

	public static void main(String[] args) {
		SpringApplication.run(RealEstateApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Create default admin if not exists
		userService.createDefaultAdmin();
	}
}