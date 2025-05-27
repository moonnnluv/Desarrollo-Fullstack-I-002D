package com.edutech.evaluaciones.service;

import com.edutech.evaluaciones.model.Evaluacion;

import java.util.List;
import java.util.Optional;

public interface EvaluacionService {
    List<Evaluacion> listar();
    Optional<Evaluacion> obtenerPorId(Long id);
    Evaluacion guardar(Evaluacion evaluacion);
    Evaluacion actualizar(Long id, Evaluacion evaluacion);
    void eliminar(Long id);
}
