package com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.config;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.entities.User;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.entities.enums.OrderStatus;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456", LocalDate.now(), LocalDate.now(), LocalDate.now());
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456", LocalDate.now(), LocalDate.now(), LocalDate.now());

		userRepository.saveAll(Arrays.asList(u1, u2));


	}
}
