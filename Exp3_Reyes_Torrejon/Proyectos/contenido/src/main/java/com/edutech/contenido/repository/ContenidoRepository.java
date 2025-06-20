package com.edutech.contenido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.contenido.entity.Contenido;

public interface ContenidoRepository extends JpaRepository<Contenido, Long> {
}
