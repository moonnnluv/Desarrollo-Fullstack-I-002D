package com.edutech.contenido.controller;

import com.edutech.contenido.service.ContenidoServiceImpl;

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.repository.ContenidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContenidoServiceImplTest {

    @Mock
    private ContenidoRepository contenidoRepository;

    @InjectMocks
    private ContenidoServiceImpl contenidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarContenido() {
        Contenido contenido = new Contenido();
        when(contenidoRepository.save(contenido)).thenReturn(contenido);

        Contenido resultado = contenidoService.crearContenido(contenido);

        assertEquals(contenido, resultado);
        verify(contenidoRepository, times(1)).save(contenido);
    }

    @Test
    void testObtenerTodosLosContenidos() {
        Contenido c1 = new Contenido();
        Contenido c2 = new Contenido();
        List<Contenido> lista = Arrays.asList(c1, c2);

        when(contenidoRepository.findAll()).thenReturn(lista);

        List<Contenido> resultado = contenidoService.obtenerTodosLosContenido();

        assertEquals(2, resultado.size());
        verify(contenidoRepository, times(1)).findAll();
    }
}
