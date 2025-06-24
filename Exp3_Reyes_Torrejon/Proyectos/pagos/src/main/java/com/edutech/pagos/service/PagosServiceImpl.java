package com.edutech.pagos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.pagos.entity.Pago;
import com.edutech.pagos.repository.PagoRepository;



@Service
public class PagosServiceImpl implements PagoService {

    @Autowired
    private final PagoRepository repository;

    public PagosServiceImpl(PagoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> findAll() {
        return (List<Pago>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pago> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Pago save(Pago pago) {
        return repository.save(pago);
    }

    @Override
    @Transactional
    public Pago actualizar(Long id, Pago pagoActualizado) {
        Pago pago = repository.findById(id).orElseThrow();
        pago.setEstado(pagoActualizado.getEstado());
        pago.setMonto(pagoActualizado.getMonto());
        pago.setUsuarioId(pagoActualizado.getUsuarioId());
        pago.setFechaPago(LocalDateTime.now());
        return repository.save(pago);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    
}
