package com.edutech.evaluaciones.service;

// IMPORTAMOS CLASE EVALUACION Y REPOSITORIO CORRESPONDIENTE
import com.edutech.evaluaciones.model.Evaluacion;
import com.edutech.evaluaciones.repository.EvaluacionRepository;

// SPRING
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// JAVA LIST Y OPTIONAL
import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

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
    public Evaluacion actualizar(Long id, Evaluacion nuevaEvaluacion) {
        return evaluacionRepository.findById(id).map(e -> {
            e.setNombre(nuevaEvaluacion.getNombre());
            e.setCurso(nuevaEvaluacion.getCurso());
            e.setNota(nuevaEvaluacion.getNota());
            e.setPonderacion(nuevaEvaluacion.getPonderacion());
            e.setEstudiante(nuevaEvaluacion.getEstudiante());
            return evaluacionRepository.save(e);
        }).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }
}
