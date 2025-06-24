package com.edutech.soporte.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.repository.SoporteRepository;

@Service
public class SoporteServiceImpl implements SoporteService {

    @Autowired
    private final SoporteRepository soporteRepository;

    public SoporteServiceImpl(SoporteRepository soporteRepository) {
        this.soporteRepository = soporteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Soporte> obtenerTodos() {
        return (List<Soporte>) soporteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Soporte> obtenerPorId(Long id) {
        return soporteRepository.findById(id);
    }

    @Override
    @Transactional
    public Soporte crearTicket(Soporte soporte) {
        soporte.setFechaCreacion(LocalDateTime.now());
        soporte.setEstado("ABIERTO"); 
        return soporteRepository.save(soporte);
    }

    @Override
    @Transactional
    public Soporte actualizar(Long id, Soporte soporteActualizado) {
        Soporte soporte = soporteRepository.findById(id).orElseThrow();
        soporte.setDescripcion(soporteActualizado.getDescripcion());
        soporte.setEstado(soporteActualizado.getEstado());
        return soporteRepository.save(soporte);
    }

    @Override
    @Transactional
    public void eliminarTicket(Long id) {
        soporteRepository.deleteById(id);
    }

    
}
