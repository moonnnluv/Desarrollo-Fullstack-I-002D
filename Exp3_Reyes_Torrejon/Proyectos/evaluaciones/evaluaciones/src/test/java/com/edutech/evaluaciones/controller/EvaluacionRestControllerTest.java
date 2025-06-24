package com.edutech.evaluaciones.controller;

import java.util.List;
import java.util.Optional;

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

import com.edutech.evaluaciones.model.Evaluacion;
import com.edutech.evaluaciones.service.EvaluacionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class EvaluacionRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EvaluacionServiceImpl evaluacionServiceImpl;

    private List<Evaluacion> evaluacionLista;

    // LISTA TODAS LAS EVALUACIONES
    @Test
    void testListarEvaluaciones() throws Exception {
        when(evaluacionServiceImpl.listar()).thenReturn(evaluacionLista);
        mockmvc.perform(get("/api/evaluaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // LISTA LAS EVALUACIONES POR ID
    @Test
    void testObtenerEvaluacionPorId() throws Exception {
        Evaluacion evaluacion = new Evaluacion(1L, "Prueba 1", "Curso de Java", 65, 35, "Juan Perez");
        when(evaluacionServiceImpl.obtenerPorId(1L)).thenReturn(Optional.of(evaluacion));

        mockmvc.perform(get("/api/evaluaciones/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // CREA UNA NUEVA EVALUACION
    @Test
    void testCrearEvaluacion() throws Exception {
        Evaluacion nueva = new Evaluacion(4L, "Examen", "Java Básico", 64, 40, "Alonso Reyes");
        when(evaluacionServiceImpl.guardar(any(Evaluacion.class))).thenReturn(nueva);

        mockmvc.perform(post("/api/evaluaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isCreated());
    }

    // MODIFICA UNA EVALUACION
    @Test
    void testActualizarEvaluacion() throws Exception {
        Evaluacion existente = new Evaluacion(1L, "Prueba 1", "Curso de Java", 65, 35, "Juan Perez");
        Evaluacion actualizada = new Evaluacion(1L, "Prueba 1", "Curso de Java", 70, 35, "Juan Peredo");

        when(evaluacionServiceImpl.obtenerPorId(1L)).thenReturn(Optional.of(existente));
        when(evaluacionServiceImpl.guardar(any(Evaluacion.class))).thenReturn(actualizada);

        mockmvc.perform(put("/api/evaluaciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizada)))
                .andExpect(status().isOk());
    }

    // ELIMINA UNA EVALUACION
    @Test
    void testEliminarEvaluacion() throws Exception {
        Evaluacion evaluacion = new Evaluacion(2L, "Control 1", "HTML y CSS", 42, 10, "Jose Jaramillo");

        when(evaluacionServiceImpl.obtenerPorId(2L)).thenReturn(Optional.of(evaluacion));
        doNothing().when(evaluacionServiceImpl).eliminar(2L);

        mockmvc.perform(delete("/api/evaluaciones/2"))
                .andExpect(status().isOk());

        verify(evaluacionServiceImpl, times(1)).eliminar(2L);
    }
}
