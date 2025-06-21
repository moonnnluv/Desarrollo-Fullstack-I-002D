package com.edutech.usuarios.service;

import java.util.List;

import com.edutech.usuarios.entity.Usuario;

public interface UsuarioService {
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
    Usuario obtenerPorId(Long id);
}