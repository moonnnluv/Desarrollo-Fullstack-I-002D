package com.edutech.pagos.service;

import com.edutech.pagos.entity.Pago;
import com.edutech.pagos.repository.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagoServiceImplTest {

    @InjectMocks
    private PagosServiceImpl pagosService;

    @Mock
    private PagoRepository pagoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerPagos() {
        List<Pago> lista = Arrays.asList(new Pago(), new Pago());
        when(pagoRepository.findAll()).thenReturn(lista);

        List<Pago> resultado = pagosService.obtenerPagos(); 
        assertEquals(2, resultado.size());
    }
}
