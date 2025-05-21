package com.elisa.frontendbackend.controller;

// Anotación @Controller para definir un controlador de tipo MVC (que devuelve vistas HTML)
import org.springframework.stereotype.Controller;

// Importa la clase Model, que permite pasar datos desde el controlador a la vista
import org.springframework.ui.Model;

// Importa la anotación @GetMapping para mapear una ruta HTTP GET
import org.springframework.web.bind.annotation.GetMapping;

// Declara la clase SimulacionController como un controlador web
@Controller
public class SimulacionController {

    /**
     * Mapea la URL "/simulacion" a este método.
     * Este método puede pasar datos a la vista mediante el parámetro Model.
     */
    @GetMapping("/simulacion")
    public String simulacion(Model model) {

        return "simulacion";
    }
}
