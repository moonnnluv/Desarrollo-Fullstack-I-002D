package com.edutech.inscripciones.service;

import com.edutech.inscripciones.entity.Inscripciones;
import com.edutech.inscripciones.repository.InscripcionesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InscripcionesServiceImplTest {

    @InjectMocks
    private InscripcionesServiceImpl inscripcionesService;

    @Mock
    private InscripcionesRepository inscripcionesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerInscripciones() {
        List<Inscripciones> lista = Arrays.asList(new Inscripciones(), new Inscripciones());
        when(inscripcionesRepository.findAll()).thenReturn(lista);

        List<Inscripciones> resultado = inscripcionesService.obtenerInscripciones();
        assertEquals(2, resultado.size());
    }
}
