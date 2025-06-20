package com.edutech.cursos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.cursos.entity.Curso;
import com.edutech.cursos.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // LISTA TODOS LOS CURSOS
    @GetMapping
    public List<Curso> listar() {
        return cursoService.findAll();
    }

    // LISTA LOS CURSOS POR ID
    @GetMapping("/{id}")
    public Optional<Curso> detalle(@PathVariable Long id) {
        return cursoService.findById(id);
    }

    // CREA UN NUEVO CURSO
    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

    // MODIFICA UN CURSO
    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        Optional<Curso> existente = cursoService.findById(id);
        if (existente.isPresent()) {
            Curso c = existente.get();
            c.setNombre(curso.getNombre());
            c.setDescripcion(curso.getDescripcion());
            c.setInstructor(curso.getInstructor());
            c.setDuracion(curso.getDuracion());
            return cursoService.save(c);
        }
        return null;
    }

    // ELIMINA UN CURSO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}