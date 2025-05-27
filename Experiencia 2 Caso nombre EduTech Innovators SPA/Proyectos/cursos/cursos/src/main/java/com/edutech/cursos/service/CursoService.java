package com.edutech.cursos.service;

// IMPORTACION DE CLASE CURSO, JAVA LIST Y OPTIONAL
import com.edutech.cursos.entity.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> findAll(); // OBTIENE TODOS
    Optional<Curso> findById(Long id); // OBTIENE POR ID
    Curso save(Curso curso); // GUARDA EL CURSO
    Curso actualizar(Long id, Curso curso);// ACTUALIZA EL CURSO
    void deleteById(Long id); // ELIMINA EL CURSO
}
