package com.edutech.cursos.controller;

// IMPORTACION DE CLASE CURSO Y SERVICE CORRESPONDIENTE
import com.edutech.cursos.entity.Curso;
import com.edutech.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos") // RUTA BASE
public class CursoController {

    @Autowired
    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    

    @GetMapping
    public List<Curso> listar() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Curso> detalle(@PathVariable Long id) {
        return cursoService.findById(id);
    }

    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

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

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cursoService.deleteById(id);
    }
}