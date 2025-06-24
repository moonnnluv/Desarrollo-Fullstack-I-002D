package com.edutech.evaluaciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.evaluaciones.model.Evaluacion;
import com.edutech.evaluaciones.repository.EvaluacionRepository;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionServiceImpl(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Evaluacion> obtenerPorId(Long id) {
        return evaluacionRepository.findById(id);
    }

    @Override
    @Transactional
    public Evaluacion guardar(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    @Override
    @Transactional
    public Evaluacion actualizar(Long id, Evaluacion evaluacionActualizada) {
        Evaluacion evaluacion = evaluacionRepository.findById(id).orElseThrow();
        evaluacion.setNombre(evaluacionActualizada.getNombre());
        evaluacion.setCurso(evaluacionActualizada.getCurso());
        evaluacion.setNota(evaluacionActualizada.getNota());
        evaluacion.setPonderacion(evaluacionActualizada.getPonderacion());
        evaluacion.setEstudiante(evaluacionActualizada.getEstudiante());
        return evaluacionRepository.save(evaluacion);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }
}
