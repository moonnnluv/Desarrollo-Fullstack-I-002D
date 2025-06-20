package com.edutech.soporte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.soporte.entity.Soporte;

public interface SoporteRepository extends JpaRepository<Soporte, Long> {
    List<Soporte> findByUsuarioId(Long usuarioId);
}
