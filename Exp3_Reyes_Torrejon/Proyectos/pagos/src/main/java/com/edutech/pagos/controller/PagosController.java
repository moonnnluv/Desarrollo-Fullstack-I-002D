package com.edutech.pagos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.pagos.entity.Pago;
import com.edutech.pagos.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pagos", description = "Operaciones relacionadas con pagos")
@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagosController {

    private final PagoService service;

    public PagosController(PagoService service) {
        this.service = service;
    }

    // SWAGGER: LISTAR TODOS LOS PAGOS
    @Operation(summary = "Obtener lista de pagos", description = "Devuelve todos los pagos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public List<Pago> obtenerTodos() {
        return service.findAll();
    }

    // SWAGGER: VER POR ID
    @Operation(summary = "Obtener pago por ID", description = "Devuelve un pago específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Pago> pagoOptional = service.findById(id);
        if (pagoOptional.isPresent()) {
            return ResponseEntity.ok(pagoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // SWAGGER: CREAR UN PAGO
    @Operation(summary = "Crear un nuevo pago", description = "Registra un nuevo pago en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago creado exitosamente")
    })
    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pago));
    }

    // SWAGGER: ACTUALIZAR UN PAGO
    @Operation(summary = "Actualizar un pago", description = "Modifica los datos de un pago existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPago(@PathVariable Long id, @RequestBody Pago pago) {
        Optional<Pago> pagoOptional = service.findById(id);

        if (pagoOptional.isPresent()) {
            Pago existente = pagoOptional.get();
            existente.setUsuarioId(pago.getUsuarioId());
            existente.setMonto(pago.getMonto());
            existente.setFechaPago(pago.getFechaPago());
            existente.setEstado(pago.getEstado());

            return ResponseEntity.ok(service.save(existente));
        }

        return ResponseEntity.notFound().build();
    }

    // SWAGGER: ELIMINAR UN PAGO
    @Operation(summary = "Eliminar un pago", description = "Elimina un pago específico por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pago eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPago(@PathVariable Long id) {
        Optional<Pago> pagoOptional = service.findById(id);
        if (pagoOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok(pagoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
