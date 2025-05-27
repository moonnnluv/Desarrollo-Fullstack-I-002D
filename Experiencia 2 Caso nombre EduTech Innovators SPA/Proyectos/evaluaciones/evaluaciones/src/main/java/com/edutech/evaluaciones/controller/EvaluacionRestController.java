package com.edutech.evaluaciones.controller;

import com.edutech.evaluaciones.entity.Evaluacion;
import com.edutech.evaluaciones.service.EvaluacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones") // RUTA BASE
@CrossOrigin(origins = "*") // Para permitir llamadas desde frontend externo
public class EvaluacionRestController {

    @Autowired
    private EvaluacionService evaluacionService;

    public EvaluacionRestController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> obtenerPorId(@PathVariable Long id) {
        return evaluacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        Evaluacion nueva = evaluacionService.guardar(evaluacion);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        Evaluacion actualizada = evaluacionService.actualizar(id, evaluacion);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        evaluacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
