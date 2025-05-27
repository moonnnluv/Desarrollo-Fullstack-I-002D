package com.edutech.usuarios.service;

// IMPORTACION DE CLASE USUARIO, JAVA LIST Y OPTIONAL
import com.edutech.usuarios.entity.Usuario;
import java.util.List;
import java.util.Optional;


// INTERFAZ PARA GESTIONAR USUARIOS
public interface UsuarioService {
    List<Usuario> obtenerTodos(); // OBTIENE TODOS
    Optional<Usuario> findById(Long id); // OBTIENE POR ID
    Usuario guardar(Usuario usuario); // GUARDA EL USUARIO
    Usuario actualizar(Long id, Usuario usuario); // ACTUALIZA AL USUARIO
    void eliminar(Long id); // ELIMINA AL USUARIO
}