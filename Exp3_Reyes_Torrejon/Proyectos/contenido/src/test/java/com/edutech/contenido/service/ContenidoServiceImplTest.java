package com.edutech.contenido.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.repository.ContenidoRepository;

class ContenidoServiceImplTest {

    @Mock
    private ContenidoRepository contenidoRepository;

    @InjectMocks
    private ContenidoServiceImpl contenidoService;

    List<Contenido> lista = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cargarContenidos();
    }

    public void cargarContenidos() {
        Contenido c1 = new Contenido("Video introductorio", 1L, "Intro", "VIDEO");
        Contenido c2 = new Contenido("Lectura PDF", 2L, "Guía", "PDF");
        Contenido c3 = new Contenido("Evaluación final", 3L, "Test Final", "TEST");

        lista.add(c1);
        lista.add(c2);
        lista.add(c3);
    }

    @Test
    void testGuardarContenido() {
        Contenido contenido = new Contenido("Contenido nuevo", 4L, "Nuevo", "PDF");

        when(contenidoRepository.save(contenido)).thenReturn(contenido);

        Contenido resultado = contenidoService.crearContenido(contenido);

        assertEquals("Nuevo", resultado.getNombre());
        verify(contenidoRepository, times(1)).save(contenido);
    }

    @Test
    void testObtenerTodosLosContenidos() {
        when(contenidoRepository.findAll()).thenReturn(lista);

        List<Contenido> resultado = contenidoService.obtenerTodosLosContenido();

        assertEquals(3, resultado.size());
        verify(contenidoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerContenidoPorId() {
        Contenido contenido = lista.get(0);
        when(contenidoRepository.findById(1L)).thenReturn(Optional.of(contenido));

        Optional<Contenido> resultado = contenidoService.obtenerContenidoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Intro", resultado.get().getNombre());
    }

    @Test
    void testActualizarContenido() {
        Contenido existente = new Contenido("Descripción antigua", 1L, "Intro", "VIDEO");
        Contenido actualizado = new Contenido("Nueva descripción", 1L, "Intro Actualizado", "PDF");

        when(contenidoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(contenidoRepository.save(any(Contenido.class))).thenReturn(actualizado);

        Contenido resultado = contenidoService.actualizarContenido(1L, actualizado);

        assertNotNull(resultado);
        assertEquals("Intro Actualizado", resultado.getNombre());
        assertEquals("PDF", resultado.getTipo());
    }

    @Test
    void testEliminarContenido() {
        doNothing().when(contenidoRepository).deleteById(2L);

        contenidoService.eliminarContenido(2L);

        verify(contenidoRepository, times(1)).deleteById(2L);
    }
}
