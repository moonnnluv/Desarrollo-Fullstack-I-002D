package com.edutech.inscripciones.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.inscripciones.entity.Inscripciones;
import com.edutech.inscripciones.service.InscripcionesService;


@RestController
@RequestMapping("/inscripciones")
public class InscripcionesController {

    private final InscripcionesService service;

    public InscripcionesController(InscripcionesService service) {
        this.service = service;
    }
    
    // CREAR INSCRIPCION
    @PostMapping
    public ResponseEntity<Inscripciones> crearInscripcion(@RequestBody Inscripciones inscripcion) {
        return ResponseEntity.ok(service.crear(inscripcion));
    }

    // VER TODAS LAS INSCRIPCIONES
    @GetMapping
    public ResponseEntity<List<Inscripciones>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    // VER INSCRIPCIONES POR ID (DE USUARIO)
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Inscripciones>> obtenerPorUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorUsuario(id));
    }

    // ACTUALIZAR INSCRIPCION
    @PutMapping("/{id}")
    public ResponseEntity<Inscripciones> actualizarInscripcion(@PathVariable Long id, @RequestBody Inscripciones inscripcion) {
        Inscripciones inscripcionExistente = service.obtenerTodas().stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (inscripcionExistente != null) {
            inscripcionExistente.setUsuarioId(inscripcion.getUsuarioId());
            inscripcionExistente.setCursoId(inscripcion.getCursoId());
            inscripcionExistente.setFechaInscripcion(inscripcion.getFechaInscripcion());
            return ResponseEntity.ok(service.crear(inscripcionExistente));
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    // ELIMINAR INSCRIPCION
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) {
        Inscripciones inscripcionExistente = service.obtenerTodas().stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (inscripcionExistente != null) {
            service.obtenerTodas().remove(inscripcionExistente); 
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}


