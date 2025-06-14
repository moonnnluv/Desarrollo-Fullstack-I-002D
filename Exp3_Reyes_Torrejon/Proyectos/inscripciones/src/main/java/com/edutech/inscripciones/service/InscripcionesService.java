package com.edutech.inscripciones.service;

import com.edutech.inscripciones.entity.Inscripciones;
import java.util.List;

public interface InscripcionesService {
    Inscripciones crear(Inscripciones inscripcion);
    List<Inscripciones> obtenerTodas();
    List<Inscripciones> obtenerPorUsuario(Long usuarioId);
}

