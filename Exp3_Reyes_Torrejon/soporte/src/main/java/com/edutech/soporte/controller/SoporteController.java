package com.edutech.soporte.controller;

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.service.SoporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    private final SoporteService soporteService;

    public SoporteController(SoporteService soporteService) {
        this.soporteService = soporteService;
    }

    // CREAR TICKET
    @PostMapping
    public ResponseEntity<Soporte> crearTicket(@RequestBody Soporte soporte) {
        Soporte nuevoSoporte = soporteService.crearTicket(soporte);
        return ResponseEntity.ok(nuevoSoporte);
    }

    // BUSCAR TICKET POR ID DE USUARIO
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Soporte>> obtenerTicketsPorUsuario(@PathVariable Long usuarioId) {
        List<Soporte> tickets = soporteService.obtenerTicketsPorUsuario(usuarioId);
        return ResponseEntity.ok(tickets);
    }

    // VER ESTADO DEL TICKET
    @PutMapping("/{id}")
    public ResponseEntity<Soporte> actualizar(@PathVariable Long id, @RequestBody Soporte soporte) {
        Soporte soporteExistente = soporteService.obtenerPorId(id);
        if (soporteExistente != null) {
            soporteExistente.setUsuarioId(soporte.getUsuarioId());
            soporteExistente.setDescripcion(soporte.getDescripcion());
            soporteExistente.setEstado(soporte.getEstado());
            soporteExistente.setFechaCreacion(soporte.getFechaCreacion());
            soporteService.guardar(soporteExistente);
            return ResponseEntity.ok(soporteExistente);
        }
        return ResponseEntity.notFound().build();
    }

    // ELIMINAR TICKET
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable Long id) {
        Soporte soporteExistente = soporteService.obtenerPorId(id);
        if (soporteExistente != null) {
            soporteService.eliminarTicket(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
