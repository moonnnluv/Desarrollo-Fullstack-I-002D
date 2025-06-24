package com.edutech.contenido.controller;

import com.edutech.contenido.entity.Contenido;
import com.edutech.contenido.service.ContenidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContenidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContenidoService contenidoService;

    @Test
    void testObtenerTodosLosContenidos() throws Exception {
        Contenido c1 = new Contenido();
        Contenido c2 = new Contenido();
        List<Contenido> lista = Arrays.asList(c1, c2);

        when(contenidoService.obtenerTodosLosContenido()).thenReturn(lista);

        mockMvc.perform(get("/api/contenido")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
