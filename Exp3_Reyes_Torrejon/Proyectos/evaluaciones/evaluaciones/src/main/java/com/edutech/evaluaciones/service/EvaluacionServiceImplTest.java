package com.edutech.evaluaciones.service;

import com.edutech.evaluaciones.model.Evaluacion;
import com.edutech.evaluaciones.repository.EvaluacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EvaluacionServiceImplTest {

    @InjectMocks
    private EvaluacionServiceImpl evaluacionService;

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerEvaluaciones() {
        List<Evaluacion> listaFalsa = Arrays.asList(new Evaluacion(), new Evaluacion());
        when(evaluacionRepository.findAll()).thenReturn(listaFalsa);

        List<Evaluacion> resultado = evaluacionService.obtenerEvaluaciones();
        assertEquals(2, resultado.size());
    }
}
