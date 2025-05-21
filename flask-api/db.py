# db.py
from sqlalchemy import create_engine, Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

# Configuración de SQLAlchemy
Base = declarative_base()
engine = create_engine("sqlite:///usuarios.db", echo=True)
Session = sessionmaker(bind=engine)
session = Session()

# Modelo de usuario
class Usuario(Base):
    __tablename__ = 'usuarios'
    id = Column(Integer, primary_key=True)
    usuario = Column(String, unique=True)
    contrasena = Column(String)
