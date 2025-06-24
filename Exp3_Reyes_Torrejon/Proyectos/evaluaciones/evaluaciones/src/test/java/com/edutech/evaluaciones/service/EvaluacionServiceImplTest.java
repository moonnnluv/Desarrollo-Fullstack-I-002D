package com.edutech.evaluaciones.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.edutech.evaluaciones.model.Evaluacion;
import com.edutech.evaluaciones.repository.EvaluacionRepository;

public class EvaluacionServiceImplTest {

    @InjectMocks
    private EvaluacionServiceImpl evaluacionService;

    @Mock
    private EvaluacionRepository evaluacionRepository;

     @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.chargeEvaluaciones();
    }

    List<Evaluacion> list = new ArrayList<>();

    // CARGAR EVALUACIONES
    public void chargeEvaluaciones() {
        Evaluacion e1 = new Evaluacion(1L, "Prueba 1", "Curso de Java", 65, 35, "Juan Perez");
        Evaluacion e2 = new Evaluacion(2L, "Control 1", "HTML y CSS", 42, 10, "Jose Jaramillo");
        Evaluacion e3 = new Evaluacion(3L, "Examen", "Spring Boot", 70, 40, "Emilia Bello");
        
        list.add(e1);
        list.add(e2);
        list.add(e3);
    }


    // PROBAR EL MÉTODO findAll()
    @Test
    public void listarTest() {
        when(evaluacionRepository.findAll()).thenReturn(list);

        List<Evaluacion> respuesta = evaluacionService.listar();

        assertEquals(3, respuesta.size());
        verify(evaluacionRepository, times(1)).findAll();
    }

    // PROBAR EL MÉTODO findById()
    @Test
    public void obtenerPorIdTest() {
        Evaluacion evaluacion = list.get(0);
        when(evaluacionRepository.findById(1L)).thenReturn(Optional.of(evaluacion));

        Optional<Evaluacion> resultado = evaluacionService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Prueba 1", resultado.get().getNombre());
    }

    // PROBAR EL MÉTODO save()
    @Test
    public void guardarTest() {
        Evaluacion nuevo = new Evaluacion(4L, "Examen", "Java Básico", 64, 40, "Alonso Reyes");
        
        when(evaluacionRepository.save(nuevo)).thenReturn(nuevo);

        Evaluacion resultado = evaluacionService.guardar(nuevo);

        assertNotNull(resultado);
        assertEquals("Examen", resultado.getNombre());
    }

    // PROBAR EL MÉTODO update()
    @Test
    void testActualizar() {
    Evaluacion existente = new Evaluacion(1L, "Prueba 1", "Curso de Java", 65, 35, "Juan Perez");
    Evaluacion actualizado = new Evaluacion(1L, "Prueba 1", "Curso de Java", 70, 35, "Juan Peredo");

    when(evaluacionRepository.findById(2L)).thenReturn(Optional.of(existente));
    when(evaluacionRepository.save(existente)).thenReturn(actualizado);

    Evaluacion resultado = evaluacionService.actualizar(2L, actualizado);

    assertNotNull(resultado);
    assertEquals(70, resultado.getNota());
    assertEquals("Juan Peredo", resultado.getEstudiante());
    }


    // PROBAR EL MÉTODO deleteById()
    @Test
    public void eliminarTest() {
        doNothing().when(evaluacionRepository).deleteById(1L);

        evaluacionService.eliminar(1L);

        verify(evaluacionRepository, times(1)).deleteById(1L);
    }
}

