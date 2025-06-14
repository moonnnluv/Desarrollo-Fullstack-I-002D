package com.edutech.inscripciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edutech.inscripciones.entity.Inscripciones;
import java.util.List;

public interface InscripcionesRepository extends JpaRepository<Inscripciones, Long> {
    List<Inscripciones> findByUsuarioId(Long usuarioId);
}
