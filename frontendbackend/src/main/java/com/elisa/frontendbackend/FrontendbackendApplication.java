package com.elisa.frontendbackend;

// Importa la clase SpringApplication para lanzar la aplicación
import org.springframework.boot.SpringApplication;
// Importa la anotación @SpringBootApplication que configura el proyecto como una aplicación Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Marca esta clase como el punto de entrada de una aplicación Spring Boot
@SpringBootApplication
public class FrontendbackendApplication {

	/**
	 * Método main: punto de entrada de la aplicación Java.
	 * Spring Boot arrancará automáticamente el servidor embebido (por defecto, Tomcat)
	 * y configurará todos los componentes del proyecto.
	 */
	public static void main(String[] args) {
		SpringApplication.run(FrontendbackendApplication.class, args);
	}

}
