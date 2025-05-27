package com.edutech.evaluaciones.service;

import java.util.List;
import java.util.Optional;

import com.edutech.evaluaciones.entity.Evaluacion;

public interface EvaluacionService {
    List<Evaluacion> listar(); // OBTIENE TODOS
    Optional<Evaluacion> obtenerPorId(Long id); // OBTIENE POR ID
    Evaluacion guardar(Evaluacion evaluacion); // GUARDA LA EVALUACION
    Evaluacion actualizar(Long id, Evaluacion evaluacion); // ACTUALIZA LA EVALUACION
    void eliminar(Long id); // ELIMINA LA EVALUACION
}
