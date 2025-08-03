package com.revil.app.projectapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectappApplication {

	public static void main(String[] args) {
		// Carga las variables del archivo .env
		Dotenv dotenv = Dotenv.configure()
				.directory("./")
				.load();

		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(ProjectappApplication.class, args);
	}

}
