package com.edutech.evaluaciones.repository;

import com.edutech.evaluaciones.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
}
