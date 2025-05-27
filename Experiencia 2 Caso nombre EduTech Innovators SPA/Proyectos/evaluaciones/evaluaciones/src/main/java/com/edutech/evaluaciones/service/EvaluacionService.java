package com.edutech.evaluaciones.service;

// IMPORTACION DE CLASE EVALUACION, JAVA LIST Y OPTIONAL
import com.edutech.evaluaciones.model.Evaluacion;
import java.util.List;
import java.util.Optional;

public interface EvaluacionService {
    List<Evaluacion> listar(); // OBTIENE TODOS
    Optional<Evaluacion> obtenerPorId(Long id); // OBTIENE POR ID
    Evaluacion guardar(Evaluacion evaluacion); // GUARDA LA EVALUACION
    Evaluacion actualizar(Long id, Evaluacion evaluacion); // ACTUALIZA LA EVALUACION
    void eliminar(Long id); // ELIMINA LA EVALUACION
}
