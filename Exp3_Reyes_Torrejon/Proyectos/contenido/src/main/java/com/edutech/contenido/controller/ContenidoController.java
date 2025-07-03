package com.edutech.contenido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.service.ContenidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Contenido", description = "Operaciones relacionadas con el contenido educativo")
@RestController
@RequestMapping("/api/contenido")
public class ContenidoController {

    private final ContenidoService contenidoService;

    public ContenidoController(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    // SWAGGER: LISTAR 
    @Operation(summary = "Obtener lista de contenidos", description = "Devuelve todos los contenidos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public List<Contenido> listarContenidos() {
        return contenidoService.obtenerTodosLosContenido();
    }

    // SWAGGER: VER POR ID
    @Operation(summary = "Obtener contenido por ID", description = "Devuelve un contenido específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido encontrado"),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Contenido> contenidoOptional = contenidoService.obtenerContenidoPorId(id);
        if (contenidoOptional.isPresent()) {
            return ResponseEntity.ok(contenidoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // SWAGGER: CREAR
    @Operation(summary = "Crear un nuevo contenido", description = "Registra un nuevo contenido educativo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contenido creado exitosamente")
    })
    @PostMapping
    public ResponseEntity<Contenido> crear(@RequestBody Contenido contenido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contenidoService.crearContenido(contenido));
    }

    // SWAGGER: ACTUALIZAR
    @Operation(summary = "Actualizar contenido", description = "Actualiza los datos de un contenido existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarContenido(@PathVariable Long id, @RequestBody Contenido contenido) {
        Optional<Contenido> contenidoOptional = contenidoService.obtenerContenidoPorId(id);
        
        if (contenidoOptional.isPresent()) {
            Contenido existente = contenidoOptional.get();
            existente.setNombre(contenido.getNombre());
            existente.setDescripcion(contenido.getDescripcion());
            existente.setTipo(contenido.getTipo());

            Contenido actualizado = contenidoService.actualizarContenido(id, existente);
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    // SWAGGER: ELIMINAR
    @Operation(summary = "Eliminar contenido", description = "Elimina un contenido por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contenido eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Contenido> contenidoOptional = contenidoService.obtenerContenidoPorId(id);
        if (contenidoOptional.isPresent()) {
            contenidoService.eliminarContenido(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
