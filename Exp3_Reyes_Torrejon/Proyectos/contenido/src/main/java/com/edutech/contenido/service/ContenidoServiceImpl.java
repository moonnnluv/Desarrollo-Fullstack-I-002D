package com.edutech.contenido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.repository.ContenidoRepository;

@Service
public class ContenidoServiceImpl implements ContenidoService {

    @Autowired
    private final ContenidoRepository contenidoRepository;

    public ContenidoServiceImpl(ContenidoRepository contenidoRepository) {
        this.contenidoRepository = contenidoRepository;
    }

    @Override
    @Transactional
    public Contenido crearContenido(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contenido> obtenerTodosLosContenido() {
        return (List<Contenido>) contenidoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contenido> obtenerContenidoPorId(Long id) {
        return contenidoRepository.findById(id);
    }

    @Override
    @Transactional
    public Contenido actualizarContenido(Long id, Contenido contenidoActualizado) {
        Contenido contenido = contenidoRepository.findById(id).orElseThrow();
        contenido.setNombre(contenidoActualizado.getNombre());
        contenido.setDescripcion(contenidoActualizado.getDescripcion());
        contenido.setTipo(contenidoActualizado.getTipo());
        return contenidoRepository.save(contenido);
    }

    @Override
    @Transactional
    public void eliminarContenido(Long id) {
        contenidoRepository.deleteById(id);
    }
}
