package com.edutech.evaluaciones.controller;

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

import com.edutech.evaluaciones.model.Evaluacion;
import com.edutech.evaluaciones.service.EvaluacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con evaluaciones")
@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin(origins = "*") // Para permitir llamadas desde frontend externo
public class EvaluacionRestController {

    private EvaluacionService evaluacionService;

    public EvaluacionRestController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    // SWAGGER LISTAR
    @Operation(summary = "Obtener lista de evaluaciones", description = "Devuelve todas las evaluaciones registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de evaluaciones obtenida correctamente")
    })
    // MUESTRA TODAS LAS EVALUACIONES
    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }

    // SWAGGER VER POR ID
    @Operation(summary = "Obtener evaluación por ID", description = "Devuelve una evaluación específica según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    // MUESTRA LAS EVALUACIONES POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Evaluacion> evaluacionOptional = evaluacionService.obtenerPorId(id);
        if (evaluacionOptional.isPresent()) {
            return ResponseEntity.ok(evaluacionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // SWAGGER CREAR
    @Operation(summary = "Crear una nueva evaluación", description = "Registra una nueva evaluación en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Evaluación creada exitosamente")
    })
    // CREA UNA NUEVA EVALUACION
    @PostMapping
    public ResponseEntity<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacionService.guardar(evaluacion));
    }

    // SWAGGER MODIFICAR
    @Operation(summary = "Actualizar evaluación", description = "Modifica los datos de una evaluación existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evaluación actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    // ACTUALIZA UNA EVALUACION
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEvaluacion(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
    Optional<Evaluacion> evaluacionOptional = evaluacionService.obtenerPorId(id);
    
    if (evaluacionOptional.isPresent()) {
        Evaluacion evaluacionExistente = evaluacionOptional.get();
        evaluacionExistente.setNombre(evaluacion.getNombre());
        evaluacionExistente.setCurso(evaluacion.getCurso());
        evaluacionExistente.setNota(evaluacion.getNota());
        evaluacionExistente.setPonderacion(evaluacion.getPonderacion());
        evaluacionExistente.setEstudiante(evaluacion.getEstudiante());

        Evaluacion actualizada = evaluacionService.guardar(evaluacionExistente);
        return ResponseEntity.ok(actualizada);
    }

    return ResponseEntity.notFound().build();
}

    // SWAGGER ELIMINAR
    @Operation(summary = "Eliminar evaluación", description = "Elimina una evaluación por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Evaluación eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    // ELIMINA UNA EVALUACION
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
    Optional<Evaluacion> evaluacionOptional = evaluacionService.obtenerPorId(id);
    if (evaluacionOptional.isPresent()) {
        evaluacionService.eliminar(id);
        return ResponseEntity.ok(evaluacionOptional.get());
    }
    return ResponseEntity.notFound().build();
    }
}
