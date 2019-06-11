package com.bettinghouse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BettingHouseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BettingHouseApiApplication.class, args);
	}

}
