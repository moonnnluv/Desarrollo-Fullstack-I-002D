package com.edutech.inscripciones.service;

import java.util.List;
import java.util.Optional;

import com.edutech.inscripciones.entity.Inscripciones;

public interface InscripcionesService {
    List<Inscripciones> obtenerTodas();
    Inscripciones guardar(Inscripciones usuario);
    Inscripciones actualizar(Long id, Inscripciones usuario);
    void eliminar(Long id);
    Optional<Inscripciones> obtenerPorId(Long id);
}

