package com.edutech.soporte.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.repository.SoporteRepository;

@Service
public class SoporteServiceImpl implements SoporteService {

    private final SoporteRepository soporteRepository;

    public SoporteServiceImpl(SoporteRepository soporteRepository) {
        this.soporteRepository = soporteRepository;
    }

    @Override
    public Soporte crearTicket(Soporte soporte) {
        return soporteRepository.save(soporte);  
    }

    @Override
    public List<Soporte> obtenerTicketsPorUsuario(Long usuarioId) {
        return soporteRepository.findByUsuarioId(usuarioId);  
    }

    @Override
    public Soporte actualizarEstado(Long id, String estado) {
        Soporte soporte = obtenerPorId(id);  
        soporte.setEstado(estado);  
        return soporteRepository.save(soporte);  
    }

    @Override
    public void eliminarTicket(Long id) {
        Soporte soporte = obtenerPorId(id);  
        soporteRepository.delete(soporte);  
    }

    @Override
    public Soporte obtenerPorId(Long id) {
        return soporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + id));  
    }

    @Override
    public void guardar(Soporte soporte) {
        soporteRepository.save(soporte);  
    }
}
