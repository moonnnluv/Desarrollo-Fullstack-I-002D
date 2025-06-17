package com.edutech.pagos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edutech.pagos.entity.Pago;
import com.edutech.pagos.repository.PagoRepository;

@Service
public class PagosServiceImpl implements PagoService {

    private final PagoRepository repository;

    public PagosServiceImpl(PagoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pago> findAll() {
        return repository.findAll(); 
    }

    @Override
    public Optional<Pago> findById(Long id) {
        return repository.findById(id); 
    }

    @Override
    public Pago save(Pago pago) {
    
        if (pago.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser mayor a cero.");
        }
        pago.setFechaPago(LocalDateTime.now()); 
        return repository.save(pago); 
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No existe un pago con el ID: " + id);
        }
        repository.deleteById(id); 
    }

    @Override
    public List<Pago> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId); 
    }

    @Override
    public Pago actualizarEstadoPago(Long id, String nuevoEstado) {
       
        if (nuevoEstado == null || nuevoEstado.isEmpty()) {
            throw new IllegalArgumentException("El estado no puede ser vacío.");
        }
        Pago pago = repository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));
        pago.setEstado(nuevoEstado); 
        return repository.save(pago); 
    }
}
