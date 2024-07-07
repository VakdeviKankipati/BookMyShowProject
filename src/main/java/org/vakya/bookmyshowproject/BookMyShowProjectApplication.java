package org.vakya.bookmyshowproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vakya.bookmyshowproject.controller.UserController;
import org.vakya.bookmyshowproject.dtos.SignUpUserRequestDTO;
import org.vakya.bookmyshowproject.dtos.SignupUserResponseDTO;

@SpringBootApplication
public class BookMyShowProjectApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;

	public static void main(String[] args) {

		SpringApplication.run(BookMyShowProjectApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		SignUpUserRequestDTO requestDto = new SignUpUserRequestDTO();
		requestDto.setName("vakya");
		requestDto.setEmail("vakya@gmail.com");
		requestDto.setPassword("Vaky@123");

		SignupUserResponseDTO responseDto = userController.signUp(requestDto);

	}
}
