package com.edutech.evaluaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.evaluaciones.model.Evaluacion;
import com.edutech.evaluaciones.service.EvaluacionService;

@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin(origins = "*") // Para permitir llamadas desde frontend externo
public class EvaluacionRestController {

    @Autowired
    private EvaluacionService evaluacionService;

    // MUESTRA TODAS LAS EVALUACIONES
    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }

    // MUESTRA LAS EVALUACIONES POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> obtenerPorId(@PathVariable Long id) {
        return evaluacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREA UNA NUEVA EVALUACION
    @PostMapping
    public ResponseEntity<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        Evaluacion nueva = evaluacionService.guardar(evaluacion);
        return ResponseEntity.status(201).body(nueva);
    }

    // ACTUALIZA UNA EVALUACION
    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        Evaluacion actualizada = evaluacionService.actualizar(id, evaluacion);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ELIMINA UNA EVALUACION
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        evaluacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
