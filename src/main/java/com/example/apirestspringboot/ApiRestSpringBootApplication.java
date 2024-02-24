package com.example.apirestspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicacion Spring Boot para la API REST
 */
@SpringBootApplication
public class ApiRestSpringBootApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot
	 * @param args Los argumentos de línea de comandos (no se utilizan en este caso)
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiRestSpringBootApplication.class, args);
	}

}
