package com.edutech.inscripciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.inscripciones.entity.Inscripciones;
import com.edutech.inscripciones.repository.InscripcionesRepository;

@Service
public class InscripcionesServiceImpl implements InscripcionesService {

    @Autowired
    private final InscripcionesRepository inscripcionesRepository;

    public InscripcionesServiceImpl(InscripcionesRepository inscripcionesRepository) {
        this.inscripcionesRepository = inscripcionesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Inscripciones> obtenerTodas() {
        return inscripcionesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Inscripciones> obtenerPorId(Long id) {
        return inscripcionesRepository.findById(id);
    }

    @Override
    @Transactional
    public Inscripciones guardar(Inscripciones inscripcion) {
        return inscripcionesRepository.save(inscripcion);
    }

    @Override
    @Transactional
    public Inscripciones actualizar(Long id, Inscripciones inscripcionActualizada) {
        Inscripciones inscripcion = inscripcionesRepository.findById(id).orElseThrow();
        inscripcion.setUsuarioId(inscripcionActualizada.getUsuarioId());
        inscripcion.setCursoId(inscripcionActualizada.getCursoId());
        inscripcion.setFechaInscripcion(inscripcionActualizada.getFechaInscripcion());
        return inscripcionesRepository.save(inscripcion);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        inscripcionesRepository.deleteById(id);
    }
}
