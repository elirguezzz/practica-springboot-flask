# init_db.py
import os
from db import Base, engine, session, Usuario

# Eliminar la base de datos anterior (opcional)
if os.path.exists("usuarios.db"):
    os.remove("usuarios.db")

# Crear la base y la tabla
Base.metadata.create_all(engine)

# Insertar usuarios de prueba
usuarios = [
    Usuario(usuario="admin", contrasena="1234"),
    Usuario(usuario="elisa", contrasena="1234"),
    Usuario(usuario="carla", contrasena="abcd")
]

session.add_all(usuarios)
session.commit()

print("Base de datos 'usuarios.db' creada e inicializada con usuarios de prueba.")
