# app.py

# --- Importaciones necesarias ---
from flask import Flask, request, jsonify  # Framework web, gestión de peticiones y respuestas JSON
from flask_cors import CORS  # Permite que el frontend en otro origen acceda a la API
import requests  # Para hacer peticiones HTTP a APIs externas
from db import Usuario, session  # Modelos y sesión de la base de datos importados desde db.py

# --- Configuración básica de Flask ---
app = Flask(__name__)
CORS(app)  # Habilita CORS para permitir peticiones desde otros dominios (por ejemplo, desde Spring Boot)

# --- ENDPOINT: Obtener usuarios de la base de datos ---
@app.route("/usuarios")
def obtener_usuarios():
    try:
        usuarios = session.query(Usuario).all()  # Consulta todos los usuarios
        return jsonify([{"id": u.id, "usuario": u.usuario} for u in usuarios])  # Devuelve lista de diccionarios JSON
    except Exception as e:
        return jsonify({"error": "Error al acceder a la base de datos", "detalle": str(e)}), 500

# --- ENDPOINT: Pruebas de errores simulados ---
@app.route("/api/test")
def test_api():
    tipo = request.args.get("tipo")  # Obtiene el parámetro "tipo" desde la URL
    if tipo == "saludo":
        return jsonify({"mensaje": "Hola desde Flask!"})  # Mensaje de saludo
    elif tipo == "notfound":
        return jsonify({"error": "Recurso no encontrado"}), 404  # Simula error 404
    elif tipo == "servererror":
        raise Exception("Error interno simulado")  # Lanza una excepción para simular error 500
    else:
        return jsonify({"error": "Tipo de prueba no reconocido"}), 400  # Error de parámetro inválido

# --- ENDPOINT: Simula una API que devuelve el parámetro recibido ---
@app.route("/mi-api")
def mi_api():
    param = request.args.get("param", "no recibido")  # Lee parámetro de la URL
    return jsonify({"respuesta": f"Parámetro recibido: {param}"})  # Devuelve el parámetro

# --- ENDPOINT: Lee un archivo y devuelve su contenido ---
@app.route("/leer-archivo")
def leer_archivo():
    nombre_archivo = request.args.get("nombre", "inexistente.txt")  # Obtiene el nombre del archivo por parámetro
    try:
        with open(nombre_archivo, "r") as f:
            contenido = f.read()  # Lee el contenido del archivo
        return jsonify({"contenido": contenido})  # Lo devuelve en formato JSON
    except FileNotFoundError:
        return jsonify({"error": f"Archivo '{nombre_archivo}' no encontrado"}), 404  # Error si no existe
    except Exception as e:
        return jsonify({"error": "Error al leer el archivo", "detalle": str(e)}), 500  # Error genérico

# --- ENDPOINT: Consulta la PokeAPI y devuelve algunos datos del Pokémon ---
@app.route("/pokeapi")
def pokeapi():
    nombre = request.args.get("nombre", "pikachu")  # Parámetro "nombre" del Pokémon (por defecto Pikachu)
    try:
        respuesta = requests.get(f"https://pokeapi.co/api/v2/pokemon/{nombre}")  # Consulta a la PokeAPI
        respuesta.raise_for_status()  # Lanza error si la respuesta no es 200 OK
        datos = respuesta.json()  # Convierte la respuesta en JSON
        return jsonify({
            "nombre": datos["name"],
            "altura": datos["height"],
            "peso": datos["weight"],
            "tipo_principal": datos["types"][0]["type"]["name"]
        })  # Devuelve información básica del Pokémon
    except requests.exceptions.HTTPError:
        return jsonify({"error": f"No se encontró el Pokémon '{nombre}'"}), 404
    except Exception as e:
        return jsonify({"error": "Error al consultar la API externa", "detalle": str(e)}), 500

# --- ENDPOINT: Login básico usando la base de datos ---
@app.route("/login", methods=["POST"])
def login():
    datos = request.get_json()  # Extrae el JSON enviado en la petición POST
    usuario = datos.get("usuario")  # Nombre de usuario
    contrasena = datos.get("contrasena")  # Contraseña

    # Validación: campos obligatorios
    if not usuario or not contrasena:
        return jsonify({"error": "Faltan datos"}), 400

    # Consulta el usuario en la base de datos (nota: la contraseña no está cifrada)
    usuario_encontrado = session.query(Usuario).filter_by(usuario=usuario, contrasena=contrasena).first()

    # Verifica si el usuario existe y las credenciales son correctas
    if usuario_encontrado:
        return jsonify({"mensaje": f"Bienvenido, {usuario}!"})
    else:
        return jsonify({"error": "Usuario o contraseña incorrectos"}), 401

# --- Manejador global de errores inesperados ---
@app.errorhandler(Exception)
def manejar_errores(e):
    return jsonify({
        "tipo": type(e).__name__,  # Tipo de excepción (por ejemplo, ValueError)
        "mensaje": str(e)  # Mensaje de error
    }), 500  # Devuelve código HTTP 500

# --- Punto de entrada de la aplicación ---
if __name__ == "__main__":
    app.run(port=5000)  # Inicia el servidor Flask en el puerto 5000

