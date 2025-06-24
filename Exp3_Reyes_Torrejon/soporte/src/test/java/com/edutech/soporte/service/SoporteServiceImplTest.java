package com.edutech.soporte.service;

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.repository.SoporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SoporteServiceImplTest {

    @Mock
    private SoporteRepository soporteRepository;

    @InjectMocks
    private SoporteServiceImpl soporteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearTicket() {
        Soporte soporte = new Soporte();
        when(soporteRepository.save(soporte)).thenReturn(soporte);

        Soporte resultado = soporteService.crearTicket(soporte);

        assertEquals(soporte, resultado);
        verify(soporteRepository, times(1)).save(soporte);
    }

    @Test
    void testObtenerTicketsPorUsuario() {
        Soporte s1 = new Soporte();
        Soporte s2 = new Soporte();
        List<Soporte> lista = Arrays.asList(s1, s2);

        when(soporteRepository.findByUsuarioId(1L)).thenReturn(lista);

        List<Soporte> resultado = soporteService.obtenerTicketsPorUsuario(1L);

        assertEquals(2, resultado.size());
        verify(soporteRepository, times(1)).findByUsuarioId(1L);
    }
}
