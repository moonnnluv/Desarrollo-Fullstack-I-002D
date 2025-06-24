package com.edutech.soporte.service;

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.repository.SoporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SoporteServiceImplTest {

    @InjectMocks
    private SoporteServiceImpl soporteService;

    @Mock
    private SoporteRepository soporteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerMensajes() {
        List<Soporte> lista = Arrays.asList(new Soporte(), new Soporte());
        when(soporteRepository.findAll()).thenReturn(lista);

        List<Soporte> resultado = soporteService.obtenerMensajes();
        assertEquals(2, resultado.size());
    }
}
