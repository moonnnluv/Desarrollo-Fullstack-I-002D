package com.edutech.inscripciones.service;

import com.edutech.inscripciones.entity.Inscripciones;
import com.edutech.inscripciones.repository.InscripcionesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscripcionesServiceImpl implements InscripcionesService {

    private final InscripcionesRepository repository;

    public InscripcionesServiceImpl(InscripcionesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Inscripciones crear(Inscripciones inscripcion) {
        inscripcion.setFechaInscripcion(LocalDate.now());
        return repository.save(inscripcion);
    }

    @Override
    public List<Inscripciones> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public List<Inscripciones> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}
