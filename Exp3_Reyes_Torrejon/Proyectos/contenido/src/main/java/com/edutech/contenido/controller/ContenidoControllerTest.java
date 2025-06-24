package com.edutech.contenido.controller;

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.service.ContenidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContenidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContenidoService contenidoService;

    @Test
    public void testObtenerContenidos() throws Exception {
        when(contenidoService.obtenerContenidos()).thenReturn(Arrays.asList(new Contenido(), new Contenido()));

        mockMvc.perform(get("/api/contenidos"))
               .andExpect(status().isOk());
    }
}
