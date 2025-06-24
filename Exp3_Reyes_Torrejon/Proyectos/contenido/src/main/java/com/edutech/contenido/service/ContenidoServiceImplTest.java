package com.edutech.contenido.service;

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.repository.ContenidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContenidoServiceImplTest {

    @InjectMocks
    private ContenidoServiceImpl contenidoService;

    @Mock
    private ContenidoRepository contenidoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerContenidos() {
        List<Contenido> lista = Arrays.asList(new Contenido(), new Contenido());
        when(contenidoRepository.findAll()).thenReturn(lista);

        List<Contenido> resultado = contenidoService.obtenerContenidos();
        assertEquals(2, resultado.size());
    }
}
