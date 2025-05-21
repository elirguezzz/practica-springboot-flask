package com.elisa.frontendbackend.controller;

// Importaciones necesarias para trabajar con Spring y realizar peticiones HTTP
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

// Esta clase es un controlador REST de Spring
@RestController
public class ApiController {

    // URL base de la API Flask que se va a consumir desde este backend
    private final String FLASK_API_URL = "http://localhost:5000";

    /**
     * Endpoint `/api/test`, espera un parámetro `tipo` y lo reenvía a la API Flask
     */
    @GetMapping("/api/test")
    public ResponseEntity<?> testApi(@RequestParam String tipo) {
        // Crea una instancia de RestTemplate para realizar la petición HTTP
        RestTemplate restTemplate = new RestTemplate();

        // Construye la URL con el parámetro recibido
        String url = FLASK_API_URL + "/api/test?tipo=" + tipo;

        try {
            // Realiza la petición GET a la URL de Flask y espera un Map como respuesta
            Map<?, ?> response = restTemplate.getForObject(url, Map.class);

            // Devuelve la respuesta con estado 200 OK
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Si ocurre un error HTTP (cliente o servidor), construye una respuesta de error detallada
            return buildErrorResponse(e.getStatusCode().value(), e.getStatusText(), e.getResponseBodyAsString());
        } catch (Exception e) {
            // Para cualquier otro tipo de error, devuelve error 500 genérico
            return buildErrorResponse(500, "Unknown Error", e.getMessage());
        }
    }

    /**
     * Endpoint `/mi-api`, reenvía el parámetro `param` a un endpoint equivalente en la API Flask
     */
    @GetMapping("/mi-api")
    public ResponseEntity<?> miApi(@RequestParam String param) {
        RestTemplate restTemplate = new RestTemplate();
        String url = FLASK_API_URL + "/mi-api?param=" + param;

        try {
            Map<?, ?> response = restTemplate.getForObject(url, Map.class);
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return buildErrorResponse(e.getStatusCode().value(), e.getStatusText(), e.getResponseBodyAsString());
        } catch (Exception e) {
            return buildErrorResponse(500, "Unknown Error", e.getMessage());
        }
    }

    /**
     * Endpoint `/pokeapi`, reenvía el parámetro `nombre` a un endpoint Flask que probablemente consulta Pokémon
     */
    @GetMapping("/pokeapi")
    public ResponseEntity<?> pokeApi(@RequestParam String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        String url = FLASK_API_URL + "/pokeapi?nombre=" + nombre;

        try {
            Map<?, ?> response = restTemplate.getForObject(url, Map.class);
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return buildErrorResponse(e.getStatusCode().value(), e.getStatusText(), e.getResponseBodyAsString());
        } catch (Exception e) {
            return buildErrorResponse(500, "Unknown Error", e.getMessage());
        }
    }

    /**
     * Método auxiliar privado que construye una respuesta de error estandarizada.
     * Incluye código de estado, tipo de error y mensaje detallado.
     */
    private ResponseEntity<Map<String, Object>> buildErrorResponse(int status, String error, String message) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", status);
        errorBody.put("error", error);
        errorBody.put("message", message);
        return ResponseEntity.status(status).body(errorBody);
    }
}
