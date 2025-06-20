package com.edutech.soporte.service;

import java.util.List;

import com.edutech.soporte.entity.Soporte;

public interface SoporteService {
    Soporte crearTicket(Soporte soporte);
    List<Soporte> obtenerTicketsPorUsuario(Long usuarioId);
    Soporte actualizarEstado(Long id, String estado);
    void eliminarTicket(Long id);
    
    Soporte obtenerPorId(Long id);  
    void guardar(Soporte soporte);  
}
