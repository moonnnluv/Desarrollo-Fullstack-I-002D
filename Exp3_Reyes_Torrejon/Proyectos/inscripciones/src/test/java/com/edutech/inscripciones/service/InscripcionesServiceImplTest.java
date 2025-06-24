package com.edutech.inscripciones.service;
import java.time.LocalDate;
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

import com.edutech.inscripciones.entity.Inscripciones;
import com.edutech.inscripciones.repository.InscripcionesRepository;

public class InscripcionesServiceImplTest {
    @InjectMocks
    private InscripcionesServiceImpl inscripcionesServiceImpl;

    @Mock
    private InscripcionesRepository inscripcionesRepository;

    List<Inscripciones> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.chargeInscripciones();
    }

    // CARGAR INSCRIPCIONES
    public void chargeInscripciones() {
        
        Inscripciones i1 = new Inscripciones(1L, 1L, 1L, LocalDate.of(2024, 6, 24));
        Inscripciones i2 = new Inscripciones(2L, 2L, 1L, LocalDate.of(2024, 6, 25));
        Inscripciones i3 = new Inscripciones(3L, 1L, 2L, LocalDate.of(2024, 6, 26));

        list.add(i1);
        list.add(i2);
        list.add(i3);
    }
    

    // PROBAR EL MÉTODO findAll()
    @Test
    void testObtenerTodas() {

        when(inscripcionesRepository.findAll()).thenReturn(list);

        List<Inscripciones> respuesta = inscripcionesServiceImpl.obtenerTodas();

        assertEquals(3, respuesta.size());
        verify(inscripcionesRepository, times(1)).findAll();
    }

    // PROBAR EL MÉTODO findById()
    @Test
    void testObtenerPorId() {

        Inscripciones usuario = list.get(2);
        when(inscripcionesRepository.findById(3L)).thenReturn(Optional.of(usuario));

        Optional<Inscripciones> resultado = inscripcionesServiceImpl.obtenerPorId(3L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getUsuarioId());
    }

    // PROBAR EL MÉTODO save()
    @Test
    void testGuardar() {
        Inscripciones original = new Inscripciones(4L, 3L, 2L, LocalDate.of(2024, 6, 18));

        when(inscripcionesRepository.save(original)).thenReturn(original);

        Inscripciones resultado = inscripcionesServiceImpl.guardar(original);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getCursoId());
    }

    // PROBAR EL MÉTODO update()
    @Test
    void testActualizar() {
    Inscripciones existente = new Inscripciones(1L, 1L, 1L, LocalDate.of(2024, 6, 24));
    Inscripciones actualizado = new Inscripciones(1L, 1L, 1L, LocalDate.of(2024, 6, 10));

    when(inscripcionesRepository.findById(2L)).thenReturn(Optional.of(existente));
    when(inscripcionesRepository.save(existente)).thenReturn(actualizado);

    Inscripciones resultado = inscripcionesServiceImpl.actualizar(2L, actualizado);

    assertNotNull(resultado);
    assertEquals(1L, resultado.getUsuarioId());
    assertEquals(LocalDate.of(2024, 6, 10), resultado.getFechaInscripcion());
    }

   // PROBAR EL MÉTODO deleteById()
    @Test
    void testEliminar() {
        doNothing().when(inscripcionesRepository).deleteById(1L);

        inscripcionesServiceImpl.eliminar(1L);

        verify(inscripcionesRepository, times(1)).deleteById(1L);
    }

}
