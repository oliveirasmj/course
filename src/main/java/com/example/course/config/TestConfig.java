package com.example.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Mike", 123456789, "Aveiro", 912375483);
		User u2 = new User(null, "Ana", 987654321, "Lisboa", 928536453);
		User u3 = new User(null, "Mike", 123, "Porto", 456);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}
	
}
