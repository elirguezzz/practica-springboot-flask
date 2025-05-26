# Sistema Spring Boot + Flask – Práctica Obligatoria 2

Curso: Sistemas Distribuidos 2024-2025
Autor: Elisa Rodríguez Domínguez

Este proyecto consiste en el desarrollo de un sistema completo que combina:

- 🧩 **Frontend y Backend con Spring Boot**
- 🐍 **API REST en Flask (Python)**
- 🗃️ **Base de datos SQLite (para la API)**
- 🎯 **Simulación de excepciones y manejo de errores**


## 🧪 Funcionalidades

### ✅ Frontend / Backend (Spring Boot)
- Página principal (`/`)
- Página de login con validación de usuario y contraseña
- Página para simular llamada a APIs externas (por ejemplo, Pokémon)
- Gestión de errores con mensajes legibles para el usuario

### ✅ API (Flask)
- Acceso a base de datos SQLite (`usuarios.db`)
- Autenticación de usuarios
- Endpoint `/pokeapi` que consume la PokeAPI
- Simulación de errores:
  - Error de lectura de archivo
  - Error de acceso a base de datos
  - Error en llamada a API externa

## ⚙️ Tecnologías utilizadas

### Backend (Java)
- Spring Boot
- JPA + Hibernate
- Thymeleaf
- Maven

### API (Python)
- Flask
- SQLAlchemy
- SQLite
- Requests

## 🚀 Cómo ejecutar el sistema

### 1. Iniciar la API Flask

```bash
cd flask_api
pip install -r requirements.txt
python app.py
La API se ejecutará en: http://localhost:5000

