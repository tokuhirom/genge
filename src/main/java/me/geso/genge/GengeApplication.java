package me.geso.genge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GengeApplication {
	public static void main(String[] args) {
		SpringApplication.run(GengeApplication.class, args);
	}
}
