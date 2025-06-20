package com.edutech.pagos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.pagos.entity.Pago;
import com.edutech.pagos.service.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagosController {

    private final PagoService service;

    public PagosController(PagoService service) {
        this.service = service;
    }


    // CREAR UN PAGO
    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        Pago nuevoPago = service.save(pago); 
        return ResponseEntity.ok(nuevoPago); 
    }

    // MUESTRA TODOS LOS PAGOS
    @GetMapping
    public ResponseEntity<List<Pago>> obtenerTodos() {
        List<Pago> pagos = service.findAll(); 
        return ResponseEntity.ok(pagos); 
    }

    // MUESTRA LOS PAGOS SEGUN ID
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Pago>> obtenerPagosPorUsuario(@PathVariable Long id) {
        List<Pago> pagos = service.findByUsuarioId(id); 
        return ResponseEntity.ok(pagos); 
    }

    // ACTUALIZA UN PAGO (ESTADO)
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pago> actualizarEstadoPago(@PathVariable Long id, @RequestBody String nuevoEstado) {
        Pago pagoActualizado = service.actualizarEstadoPago(id, nuevoEstado); 
        return ResponseEntity.ok(pagoActualizado); 
    }

    // ELIMINA UN PAGO SEGUN ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        service.deleteById(id); 
        return ResponseEntity.noContent().build(); 
    }
}
