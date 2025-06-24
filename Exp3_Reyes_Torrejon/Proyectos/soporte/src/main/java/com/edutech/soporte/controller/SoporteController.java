package com.edutech.soporte.controller;

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

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.service.SoporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Soporte", description = "Operaciones relacionadas con tickets de soporte")
@RestController
@RequestMapping("/api/soporte")
@CrossOrigin(origins = "*")
public class SoporteController {

    private final SoporteService soporteService;

    public SoporteController(SoporteService soporteService) {
        this.soporteService = soporteService;
    }

    // SWAGGER: CREAR TICKET
    @Operation(summary = "Crear un nuevo ticket", description = "Registra un nuevo ticket de soporte")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ticket creado exitosamente")
    })
    @PostMapping
    public ResponseEntity<Soporte> crearTicket(@RequestBody Soporte soporte) {
        Soporte nuevoSoporte = soporteService.crearTicket(soporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSoporte);
    }

    // SWAGGER: ACTUALIZAR TICKET
    @Operation(summary = "Actualizar ticket", description = "Modifica los datos de un ticket existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Soporte> actualizar(@PathVariable Long id, @RequestBody Soporte soporte) {
        Optional<Soporte> soporteExistente = soporteService.obtenerPorId(id);
        if (soporteExistente.isPresent()) {
            Soporte actual = soporteExistente.get();
            actual.setUsuarioId(soporte.getUsuarioId());
            actual.setDescripcion(soporte.getDescripcion());
            actual.setEstado(soporte.getEstado());
            actual.setFechaCreacion(soporte.getFechaCreacion());

            Soporte actualizado = soporteService.actualizar(id, actual);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // SWAGGER: ELIMINAR TICKET
    @Operation(summary = "Eliminar ticket", description = "Elimina un ticket de soporte por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Ticket eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable Long id) {
        Optional<Soporte> soporteExistente = soporteService.obtenerPorId(id);
        if (soporteExistente.isPresent()) {
            soporteService.eliminarTicket(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // SWAGGER: LISTAR TODOS
    @Operation(summary = "Listar tickets", description = "Devuelve todos los tickets de soporte registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Soporte>> obtenerTodos() {
        return ResponseEntity.ok(soporteService.obtenerTodos());
    }

    // SWAGGER: VER POR ID
    @Operation(summary = "Obtener ticket por ID", description = "Devuelve un ticket específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket encontrado"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Soporte> soporte = soporteService.obtenerPorId(id);
        return soporte.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
}
