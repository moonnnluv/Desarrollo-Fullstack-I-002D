package com.edutech.pagos.service;

import java.util.List;
import java.util.Optional;

import com.edutech.pagos.entity.Pago;

public interface PagoService {
    List<Pago> findAll();
    Optional<Pago> findById(Long id);
    Pago save(Pago pago);
    void deleteById(Long id);
    List<Pago> findByUsuarioId(Long usuarioId); 
    Pago actualizarEstadoPago(Long id, String nuevoEstado);
}
