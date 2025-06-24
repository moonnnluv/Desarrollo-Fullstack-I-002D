package com.edutech.contenido.controller;

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

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.service.ContenidoService;

@RestController
@RequestMapping("/api/contenido")
public class ContenidoController {

    private final ContenidoService contenidoService;

    public ContenidoController(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    // CREA UN NUEVO CONTENIDO PARA UN CURSO
    @PostMapping
    public ResponseEntity<Contenido> crearContenido(@RequestBody Contenido contenido) {
        Contenido nuevoContenido = contenidoService.crearContenido(contenido);
        return ResponseEntity.ok(nuevoContenido);
    }

    // OBTIENE TODOS LOS CONTENIDOS
    @GetMapping
    public ResponseEntity<List<Contenido>> obtenerTodosLosContenido() {
        List<Contenido> contenidos = contenidoService.obtenerTodosLosContenido();
        return ResponseEntity.ok(contenidos);
    }

    // OBTIENE UN CONTENIDO PARA UN CURSO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Contenido> obtenerContenidoPorId(@PathVariable Long id) {
        Contenido contenido = contenidoService.obtenerContenidoPorId(id);
        if (contenido != null) {
            return ResponseEntity.ok(contenido);
        }
        return ResponseEntity.notFound().build();
    }

    // ACTUALIZA UN CONTENIDO
    @PutMapping("/{id}")
    public ResponseEntity<Contenido> actualizarContenido(@PathVariable Long id, @RequestBody Contenido contenido) {
        Contenido contenidoActualizado = contenidoService.actualizarContenido(id, contenido);
        if (contenidoActualizado != null) {
            return ResponseEntity.ok(contenidoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // ELIMINA UN CONTENIDO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContenido(@PathVariable Long id) {
        contenidoService.eliminarContenido(id);
        return ResponseEntity.noContent().build();
    }
}
