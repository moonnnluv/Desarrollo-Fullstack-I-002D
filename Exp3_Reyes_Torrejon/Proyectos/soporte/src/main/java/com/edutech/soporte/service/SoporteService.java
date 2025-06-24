package com.edutech.soporte.service;

import java.util.List;
import java.util.Optional;

import com.edutech.soporte.entity.Soporte;

public interface SoporteService {  
    List<Soporte> obtenerTodos();
    Soporte crearTicket(Soporte soporte);
    Soporte actualizar(Long id, Soporte soporte);
    void eliminarTicket(Long id);
    Optional<Soporte> obtenerPorId(Long id);
}
