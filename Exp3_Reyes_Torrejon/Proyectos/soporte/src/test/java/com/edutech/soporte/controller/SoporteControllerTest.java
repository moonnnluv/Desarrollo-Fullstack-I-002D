package com.edutech.soporte.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.service.SoporteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class SoporteControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockitoBean
    private SoporteServiceImpl soporteService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Soporte> soporteLista;

    // LISTA TODOS LOS TICKETS
    @Test
    void testListarUsuarios() throws Exception {

        when(soporteService.obtenerTodos()).thenReturn(soporteLista);
        mockmvc.perform(get("/api/soporte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // LISTA POR ID
    @Test
    void testObtenerPorId() throws Exception {
        Soporte soporte = new Soporte(1L, 1L, "Error al ingresar", "ABIERTO", LocalDateTime.now());
        try{
        when(soporteService.obtenerPorId(1L)).thenReturn(Optional.of(soporte));
        mockmvc.perform(get("/api/soporte/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzó un error " + ex.getMessage());
        }
        
    }

    // CREAR TICKET
    @Test
    void testCrearTicket() throws Exception {
        Soporte nuevo = new Soporte(null, 1L, "Pantalla en blanco", "ABIERTO", LocalDateTime.now());

        when(soporteService.crearTicket(any(Soporte.class))).thenReturn(nuevo);

        mockmvc.perform(post("/api/soporte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                        .andExpect(status().isCreated());
    }

    // ACTUALIZAR TICKET
    @Test
    void testActualizarTicket() throws Exception {
        Soporte existente = new Soporte(1L, 1L, "Problema de acceso", "ABIERTO", LocalDateTime.now());
        Soporte actualizado = new Soporte(1L, 1L, "Problema de acceso corregido", "CERRADO", LocalDateTime.now());

        when(soporteService.obtenerPorId(1L)).thenReturn(Optional.of(existente));
        when(soporteService.actualizar(1L, existente)).thenReturn(actualizado);

        mockmvc.perform(put("/api/soporte/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk());
    }

    // ELIMINAR TICKET
    @Test
    void testEliminarTicket() throws Exception {
        Soporte soporte = new Soporte(1L, 1L, "Eliminar ticket", "CERRADO", LocalDateTime.now());

        when(soporteService.obtenerPorId(1L)).thenReturn(Optional.of(soporte));
        doNothing().when(soporteService).eliminarTicket(1L);

        mockmvc.perform(delete("/api/soporte/1"))
                .andExpect(status().isNoContent());
    }
}
