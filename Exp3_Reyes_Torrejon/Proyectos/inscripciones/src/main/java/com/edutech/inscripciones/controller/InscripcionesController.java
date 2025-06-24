package com.edutech.inscripciones.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
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

import com.edutech.inscripciones.entity.Inscripciones;
import com.edutech.inscripciones.service.InscripcionesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Inscripciones", description = "Operaciones relacionadas con inscripciones")
@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionesController {

    private final InscripcionesService service;

    public InscripcionesController(InscripcionesService service) {
        this.service = service;
    }

    // SWAGGER: LISTAR TODAS LAS INSCRIPCIONES
    @Operation(summary = "Obtener lista de inscripciones", description = "Devuelve todas las inscripciones registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<Inscripciones>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    // SWAGGER: OBTENER POR ID
    @Operation(summary = "Obtener inscripción por ID", description = "Devuelve una inscripción específica por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscripción encontrada"),
        @ApiResponse(responseCode = "404", description = "Inscripción no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Inscripciones> inscripcion = service.obtenerPorId(id);
        return inscripcion.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    // SWAGGER: CREAR INSCRIPCIÓN
    @Operation(summary = "Crear una nueva inscripción", description = "Registra una nueva inscripción")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Inscripción creada exitosamente")
    })
    @PostMapping
    public ResponseEntity<Inscripciones> crear(@RequestBody Inscripciones inscripcion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(inscripcion));
    }

    // SWAGGER: ACTUALIZAR INSCRIPCIÓN
    @Operation(summary = "Actualizar inscripción", description = "Actualiza los datos de una inscripción existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscripción actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Inscripción no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Inscripciones inscripcion) {
        Optional<Inscripciones> existente = service.obtenerPorId(id);
        if (existente.isPresent()) {
            Inscripciones i = existente.get();
            i.setUsuarioId(inscripcion.getUsuarioId());
            i.setCursoId(inscripcion.getCursoId());
            i.setFechaInscripcion(inscripcion.getFechaInscripcion());
            return ResponseEntity.ok(service.actualizar(id, i));
        }
        return ResponseEntity.notFound().build();
    }

    // SWAGGER: ELIMINAR INSCRIPCIÓN
    @Operation(summary = "Eliminar inscripción", description = "Elimina una inscripción por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Inscripción eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Inscripción no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Inscripciones> existente = service.obtenerPorId(id);
        if (existente.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
