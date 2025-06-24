package com.edutech.inscripciones.controller;

import java.time.LocalDate;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edutech.inscripciones.entity.Inscripciones;
import com.edutech.inscripciones.service.InscripcionesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class InscripcionesControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockitoBean
    private InscripcionesServiceImpl inscripcionesServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Inscripciones> inscripcionesLista;

    // LISTA TODAS LAS INSCRIPCIONES
    @Test
    void testListarInscripciones() throws Exception {
        when(inscripcionesServiceImpl.obtenerTodas()).thenReturn(inscripcionesLista);

        mockmvc.perform(get("/api/inscripciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // OBTIENE INSCRIPCIÓN POR ID
    @Test
    void testObtenerInscripcionPorId() throws Exception {
        Inscripciones inscripcion = new Inscripciones(1L, 1L, 1L, LocalDate.of(2024, 6, 24));

        try {
            when(inscripcionesServiceImpl.obtenerPorId(1L)).thenReturn(Optional.of(inscripcion));
            mockmvc.perform(get("/api/inscripciones/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El testing lanzó un error: " + ex.getMessage());
        }
    }

    // CREA UNA INSCRIPCIÓN
    @Test
    void testCrearInscripcion() throws Exception {
        Inscripciones nueva = new Inscripciones(4L, 3L, 2L, LocalDate.of(2024, 6, 18));

        when(inscripcionesServiceImpl.guardar(any(Inscripciones.class))).thenReturn(nueva);

        mockmvc.perform(post("/api/inscripciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isCreated());
    }

    // ACTUALIZA UNA INSCRIPCIÓN
    @Test
    void testActualizarInscripcion() throws Exception {
        Inscripciones existente = new Inscripciones(1L, 1L, 1L, LocalDate.of(2024, 6, 24));
        Inscripciones actualizado = new Inscripciones(1L, 1L, 1L, LocalDate.of(2024, 6, 10));

        when(inscripcionesServiceImpl.obtenerPorId(1L)).thenReturn(Optional.of(existente));
        when(inscripcionesServiceImpl.guardar(any(Inscripciones.class))).thenReturn(actualizado);

        mockmvc.perform(put("/api/inscripciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk());
    }

    // ELIMINA UNA INSCRIPCIÓN
    @Test
    void testEliminarInscripcion() throws Exception {
        Inscripciones inscripcion = new Inscripciones(3L, 1L, 2L, LocalDate.of(2024, 6, 26));

        when(inscripcionesServiceImpl.obtenerPorId(3L)).thenReturn(Optional.of(inscripcion));
        doNothing().when(inscripcionesServiceImpl).eliminar(3L);

        mockmvc.perform(delete("/api/inscripciones/3"))
                .andExpect(status().isNoContent());


        verify(inscripcionesServiceImpl, times(1)).eliminar(3L);
    }

}
