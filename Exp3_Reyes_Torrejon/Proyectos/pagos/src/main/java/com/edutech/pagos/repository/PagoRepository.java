package com.edutech.pagos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.pagos.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByUsuarioId(Long usuarioId);
}
