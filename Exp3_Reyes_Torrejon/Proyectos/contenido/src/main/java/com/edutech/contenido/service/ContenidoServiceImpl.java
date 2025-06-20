package com.edutech.contenido.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.repository.ContenidoRepository;

@Service
public class ContenidoServiceImpl implements ContenidoService {

    private final ContenidoRepository contenidoRepository;

    public ContenidoServiceImpl(ContenidoRepository contenidoRepository) {
        this.contenidoRepository = contenidoRepository;
    }

    @Override
    public Contenido crearContenido(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    @Override
    public List<Contenido> obtenerTodosLosContenido() {
        return contenidoRepository.findAll();
    }

    @Override
    public Contenido obtenerContenidoPorId(Long id) {
        return contenidoRepository.findById(id).orElse(null);  
    }

    @Override
    public Contenido actualizarContenido(Long id, Contenido contenido) {
        Contenido contenidoExistente = obtenerContenidoPorId(id);
        if (contenidoExistente != null) {
            contenidoExistente.setNombre(contenido.getNombre());
            contenidoExistente.setDescripcion(contenido.getDescripcion());
            contenidoExistente.setTipo(contenido.getTipo());
            return contenidoRepository.save(contenidoExistente);
        }
        return null;  
    }

    @Override
    public void eliminarContenido(Long id) {
        Contenido contenidoExistente = obtenerContenidoPorId(id);
        if (contenidoExistente != null) {
            contenidoRepository.delete(contenidoExistente);
        }
    }
}
