package com.edutech.usuarios.service;

import com.edutech.usuarios.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
}