package com.elisa.frontendbackend.controller;

// Importa la anotación @Controller para indicar que esta clase es un controlador MVC
import org.springframework.stereotype.Controller;

// Importa la anotación @GetMapping para mapear una ruta HTTP GET a un método
import org.springframework.web.bind.annotation.GetMapping;

// Esta clase define un controlador para manejar la vista de login.
@Controller
public class LoginController {

    /**
     * Mapea la URL "/login" al método mostrarLogin().
     * Cuando el usuario accede a esa URL, se muestra la plantilla "login.html".
     */
    @GetMapping("/login")
    public String mostrarLogin() {
        // Retorna el nombre de la plantilla que se debe mostrar: "login"
        // Spring buscará el archivo login.html en la carpeta src/main/resources/templates
        return "login"; // muestra login.html
    }
}
