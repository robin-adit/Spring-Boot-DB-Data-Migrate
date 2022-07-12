package com.migrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DbDataMigrateApplication {
	public static void main(String[] args) {
		SpringApplication.run(DbDataMigrateApplication.class, args);
	}

}
