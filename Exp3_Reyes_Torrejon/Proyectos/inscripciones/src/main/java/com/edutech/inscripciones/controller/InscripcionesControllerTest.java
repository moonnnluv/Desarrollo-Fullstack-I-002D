package com.edutech.inscripciones.controller;

import com.edutech.inscripciones.entity.Inscripciones;
import com.edutech.inscripciones.service.InscripcionesService;
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
public class InscripcionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InscripcionesService inscripcionesService;

    @Test
    public void testObtenerInscripciones() throws Exception {
        when(inscripcionesService.obtenerInscripciones()).thenReturn(Arrays.asList(new Inscripciones(), new Inscripciones()));

        mockMvc.perform(get("/api/inscripciones"))
               .andExpect(status().isOk());
    }
}
