package com.edutech.contenido.controller;

import java.util.Arrays;
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

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.service.ContenidoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ContenidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContenidoServiceImpl contenidoServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Contenido> contenidoLista;

    // LISTA TODOS LOS CONTENIDOS
    @Test
    void testListarContenidos() throws Exception {
        Contenido c1 = new Contenido("Descripción 1", 1L, "Contenido 1", "VIDEO");
        Contenido c2 = new Contenido("Descripción 2", 2L, "Contenido 2", "PDF");
        contenidoLista = Arrays.asList(c1, c2);

        when(contenidoServiceImpl.obtenerTodosLosContenido()).thenReturn(contenidoLista);

        mockMvc.perform(get("/api/contenido")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // OBTIENE CONTENIDO POR ID
   @Test
    void testObtenerContenidoPorId() throws Exception {
    Contenido contenido = new Contenido("Descripción 1", 1L, "Contenido 1", "VIDEO");

    try {
        when(contenidoServiceImpl.obtenerContenidoPorId(1L)).thenReturn(Optional.of(contenido));

        mockMvc.perform(get("/api/contenido/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    } catch (Exception ex) {
        fail("El testing lanzó un error " + ex.getMessage());
    }
}


    // CREA NUEVO CONTENIDO
    @Test
    void testCrearContenido() throws Exception {
        Contenido contenido = new Contenido("Descripción nueva", 3L, "Nuevo Contenido", "TEST");

        when(contenidoServiceImpl.crearContenido(any(Contenido.class))).thenReturn(contenido);
                mockMvc.perform(post("/api/contenido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contenido)))
                        .andExpect(status().isCreated());
    }

    // MODIFICA CONTENIDO
    @Test
    void testActualizarContenido() throws Exception {
        Contenido contenido = new Contenido("Descripción original", 4L, "Contenido Original", "PDF");
        Contenido actualizado = new Contenido("Descripción actualizada", 4L, "Contenido Actualizado", "PDF");

        when(contenidoServiceImpl.obtenerContenidoPorId(4L)).thenReturn(Optional.of(contenido));
        when(contenidoServiceImpl.crearContenido(any(Contenido.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/contenido/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk());
    }
    

    // ELIMINA CONTENIDO
    @Test
    void testEliminarContenido() throws Exception {
        Contenido c2 = new Contenido("Descripción 2", 2L, "Contenido 2", "PDF");
        when(contenidoServiceImpl.obtenerContenidoPorId(2L)).thenReturn(Optional.of(c2));
        doNothing().when(contenidoServiceImpl).eliminarContenido(2L);

        mockMvc.perform(delete("/api/contenido/2"))
                .andExpect(status().isNoContent());

        verify(contenidoServiceImpl, times(1)).eliminarContenido(2L);
    }
    
}
