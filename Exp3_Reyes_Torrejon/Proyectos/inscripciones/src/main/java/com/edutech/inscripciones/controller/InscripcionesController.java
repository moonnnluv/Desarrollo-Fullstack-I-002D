package com.edutech.inscripciones.controller;

import com.edutech.inscripciones.entity.Inscripciones;
import com.edutech.inscripciones.service.InscripcionesService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
}


