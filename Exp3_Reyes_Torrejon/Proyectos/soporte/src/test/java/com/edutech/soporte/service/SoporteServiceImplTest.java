package com.edutech.soporte.service;

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.repository.SoporteRepository;

import java.time.LocalDateTime;
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

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

class SoporteServiceImplTest {

    @InjectMocks
    private SoporteServiceImpl soporteService;

    @Mock
    private SoporteRepository soporteRepository;

    List<Soporte> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.chargeTickets();
    }

    // CARGAR TICKETS DE SOPORTE
    public void chargeTickets() {
        Soporte s1 = new Soporte(1L, 1L, "Error al ingresar", "ABIERTO", LocalDateTime.now());
        Soporte s2 = new Soporte(2L, 2L, "Pantalla en blanco", "EN PROGRESO", LocalDateTime.now());
        Soporte s3 = new Soporte(3L, 3L, "No carga página", "CERRADO", LocalDateTime.now());

        list.add(s1);
        list.add(s2);
        list.add(s3);
    }

    @Test
    void testObtenerTodos() {
        when(soporteRepository.findAll()).thenReturn(list);

        List<Soporte> resultado = soporteService.obtenerTodos();

        assertEquals(3, resultado.size());
        verify(soporteRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        Soporte soporte = list.get(2);
        when(soporteRepository.findById(3L)).thenReturn(Optional.of(soporte));

        Optional<Soporte> resultado = soporteService.obtenerPorId(3L);

        assertTrue(resultado.isPresent());
        assertEquals("No carga página", resultado.get().getDescripcion());
    }

    @Test
    void testCrearTicket() {
        Soporte nuevo = new Soporte(null, 4L, "Problema con acceso", "ABIERTO", LocalDateTime.now());

        when(soporteRepository.save(nuevo)).thenReturn(nuevo);

        Soporte resultado = soporteService.crearTicket(nuevo);

        assertNotNull(resultado);
        assertEquals("Problema con acceso", resultado.getDescripcion());
    }

    @Test
    void testActualizar() {
        Soporte existente = new Soporte(1L, 1L, "Pantalla negra", "EN PROGRESO", LocalDateTime.now());
        Soporte actualizado = new Soporte(1L, 1L, "Pantalla negra", "CERRADO", LocalDateTime.now());

        when(soporteRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(soporteRepository.save(existente)).thenReturn(actualizado);

        Soporte resultado = soporteService.actualizar(1L, actualizado);

        assertNotNull(resultado);
        assertEquals("CERRADO", resultado.getEstado());
    }

    @Test
    void testEliminar() {
        doNothing().when(soporteRepository).deleteById(1L);

        soporteService.eliminarTicket(1L);

        verify(soporteRepository, times(1)).deleteById(1L);
    }
}
