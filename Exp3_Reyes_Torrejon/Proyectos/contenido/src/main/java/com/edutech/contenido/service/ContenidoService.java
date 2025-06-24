package com.edutech.contenido.service;

import java.util.List;
import java.util.Optional;

import com.edutech.contenido.entity.Contenido;

public interface ContenidoService {
    Contenido crearContenido(Contenido contenido);
    List<Contenido> obtenerTodosLosContenido();
    Optional<Contenido> obtenerContenidoPorId(Long id);
    Contenido actualizarContenido(Long id, Contenido contenido);
    void eliminarContenido(Long id);
}
