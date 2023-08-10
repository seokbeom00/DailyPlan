package com.springboot.week.dailyplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DailyplanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyplanApplication.class, args);
	}

}
