package com.edutech.pagos.controller;

import com.edutech.pagos.entity.Pago;
import com.edutech.pagos.service.PagosServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PagosControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockitoBean
    private PagosServiceImpl pagoServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    // LISTAR TODOS LOS PAGOS
    @Test
    void testListarPagos() throws Exception {
        when(pagoServiceImpl.findAll()).thenReturn(List.of());
        mockmvc.perform(get("/api/pagos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // OBTENER UN PAGO POR ID
    @Test
    void testObtenerPagoPorId() throws Exception {
        Pago pago = new Pago("APROBADO", LocalDateTime.now(), 1L, 5000.0, 1L);
        try{
        when(pagoServiceImpl.findById(1L)).thenReturn(Optional.of(pago));
        mockmvc.perform(get("/api/pagos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzó un error " + ex.getMessage());
        }
    
    }

    // CREAR UN NUEVO PAGO
    @Test
    void testCrearPago() throws Exception {
        Pago nuevo = new Pago("APROBADO", LocalDateTime.now(), 2L, 4500.0, 2L);

        when(pagoServiceImpl.save(any(Pago.class))).thenReturn(nuevo);
                mockmvc.perform(post("/api/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                        .andExpect(status().isCreated());
    }

    // ACTUALIZAR EL ESTADO DE UN PAGO
    @Test
    void testActualizarPago() throws Exception {
        Pago existente = new Pago("PENDIENTE", LocalDateTime.of(2024, 6, 20, 10, 0), 2L, 3000.0, 2L);
        Pago actualizado = new Pago("APROBADO", LocalDateTime.of(2024, 6, 21, 12, 0), 2L, 3200.0, 2L);
        when(pagoServiceImpl.findById(2L)).thenReturn(Optional.of(existente));
        when(pagoServiceImpl.save(any(Pago.class))).thenReturn(actualizado);

        mockmvc.perform(put("/api/pagos/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk());
    }

    // ELIMINAR UN PAGO
    @Test
    void testEliminarPago() throws Exception {
       Pago pago = new Pago("RECHAZADO", LocalDateTime.of(2024, 6, 26, 9, 30), 3L, 4500.0, 3L);

       when(pagoServiceImpl.findById(3L)).thenReturn(Optional.of(pago));
        doNothing().when(pagoServiceImpl).deleteById(3L);

        mockmvc.perform(delete("/api/pagos/3"))
                .andExpect(status().isOk());

        verify(pagoServiceImpl, times(1)).deleteById(3L);
    }
}
