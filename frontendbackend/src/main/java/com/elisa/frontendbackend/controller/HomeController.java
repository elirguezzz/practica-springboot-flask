package com.elisa.frontendbackend.controller;

// Importa la anotación @Controller para definir un controlador web MVC
import org.springframework.stereotype.Controller;

// Importa la anotación @GetMapping para mapear una ruta HTTP GET
import org.springframework.web.bind.annotation.GetMapping;

// Marca esta clase como un controlador de tipo MVC (devuelve vistas HTML, no JSON)
@Controller
public class HomeController {

    /**
     * Mapea la URL raíz ("/") a este método.
     * Cuando se accede a la página principal del sitio, se ejecuta este método.
     */
    @GetMapping("/")
    public String mostrarInicio() {
        // Retorna el nombre de la vista que se debe mostrar: "index"
        // Spring buscará una plantilla HTML llamada "index.html" en la carpeta /templates
        return "index";
    }
}
