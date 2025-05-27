package com.edutech.cursos.repository;

import com.edutech.cursos.entity.Curso;

import org.springframework.data.jpa.repository.JpaRepository;   
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
