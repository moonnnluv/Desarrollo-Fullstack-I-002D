package com.edutech.pagos.service;

import com.edutech.pagos.entity.Pago;
import com.edutech.pagos.repository.PagoRepository;

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class PagoServiceImplTest {

    @InjectMocks
    private PagosServiceImpl pagosService;

    @Mock
    private PagoRepository pagoRepository;

    List<Pago> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.chargePagos();
    }

    // CARGAR PAGOS
    public void chargePagos() {
        Pago p1 = new Pago("APROBADO", LocalDateTime.of(2024, 6, 24, 10, 0), 1L, 5000.0, 1L);
        Pago p2 = new Pago("PENDIENTE", LocalDateTime.of(2024, 6, 25, 11, 0), 2L, 3000.0, 2L);
        Pago p3 = new Pago("RECHAZADO", LocalDateTime.of(2024, 6, 26, 9, 30), 3L, 4500.0, 3L);

        list.add(p1);
        list.add(p2);
        list.add(p3);
    }

    // PROBAR MÉTODO findAll()
    @Test
    void testObtenerTodos() {
        when(pagoRepository.findAll()).thenReturn(list);
        List<Pago> respuesta = pagosService.findAll();
        assertEquals(3, respuesta.size());
        verify(pagoRepository, times(1)).findAll();
    }

    // PROBAR MÉTODO findById()
    @Test
    void testObtenerPorId() {
        Pago pago = list.get(2);
        when(pagoRepository.findById(3L)).thenReturn(Optional.of(pago));
        Optional<Pago> resultado = pagosService.findById(3L);
        assertTrue(resultado.isPresent());
        assertEquals("RECHAZADO", resultado.get().getEstado());
    }

    // PROBAR MÉTODO save()
    @Test
    void testGuardar() {
        Pago original = new Pago("PENDIENTE", LocalDateTime.of(2024, 6, 20, 10, 0), 4L, 3000.0, 2L);
        when(pagoRepository.save(original)).thenReturn(original);
        Pago resultado = pagosService.save(original);
        assertNotNull(resultado);
        assertEquals("PENDIENTE", resultado.getEstado());
    }

    // PROBAR MÉTODO actualizar()
    @Test
    void testActualizar() {
        Pago existente = new Pago("PENDIENTE", LocalDateTime.of(2024, 6, 20, 10, 0), 2L, 3000.0, 2L);
        Pago actualizado = new Pago("APROBADO", LocalDateTime.of(2024, 6, 21, 12, 0), 2L, 3200.0, 2L);

        when(pagoRepository.findById(2L)).thenReturn(Optional.of(existente));
        when(pagoRepository.save(existente)).thenReturn(actualizado);

        Pago resultado = pagosService.actualizar(2L, actualizado);

        assertNotNull(resultado);
        assertEquals("APROBADO", resultado.getEstado());
        assertEquals(3200.0, resultado.getMonto());
    }

    // PROBAR MÉTODO deleteById()
    @Test
    void testEliminar() {
        doNothing().when(pagoRepository).deleteById(1L);
        pagosService.deleteById(1L);
        verify(pagoRepository, times(1)).deleteById(1L);
    }
}
