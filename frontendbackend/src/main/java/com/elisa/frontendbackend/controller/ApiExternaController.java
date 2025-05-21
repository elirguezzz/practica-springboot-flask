package com.elisa.frontendbackend.controller;

// Importa la anotación @Controller para definir un controlador MVC en Spring
import org.springframework.stereotype.Controller;
// Importa la anotación @GetMapping para mapear peticiones GET
import org.springframework.web.bind.annotation.GetMapping;

// Esta clase es un controlador MVC (no REST)
// Es decir, sirve para devolver vistas (plantillas HTML), no datos en JSON
@Controller
public class ApiExternaController {

    /**
     * Mapea una petición GET a la ruta "/apiexterna".
     * Cuando se accede a esta URL, devuelve el nombre de una vista.
     */
    @GetMapping("/apiexterna")
    public String apiExterna() {
        // Devuelve el nombre de la vista (apiexterna.html porque uso Thymeleaf)
        return "apiexterna";
    }
}
