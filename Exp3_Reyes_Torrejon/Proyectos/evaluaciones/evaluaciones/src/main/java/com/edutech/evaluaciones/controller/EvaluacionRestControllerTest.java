package com.edutech.evaluaciones.controller;

import com.edutech.evaluaciones.model.Evaluacion;
import com.edutech.evaluaciones.service.EvaluacionService;
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
public class EvaluacionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluacionService evaluacionService;

    @Test
    public void testObtenerEvaluaciones() throws Exception {
        when(evaluacionService.obtenerEvaluaciones())
                .thenReturn(Arrays.asList(new Evaluacion(), new Evaluacion()));

        mockMvc.perform(get("/api/evaluaciones"))
                .andExpect(status().isOk());
    }
}
