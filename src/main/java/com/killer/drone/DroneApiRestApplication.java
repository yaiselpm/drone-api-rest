package com.killer.drone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DroneApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneApiRestApplication.class, args);
	}

}
