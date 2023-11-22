package com.example.asm01;
import com.example.asm01.dao.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DonationManagementSystem {
	public static void main(String[] args) {
		SpringApplication.run(DonationManagementSystem.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner (UserRepository userRepository) {
		return runner -> {
		};
	}
}
