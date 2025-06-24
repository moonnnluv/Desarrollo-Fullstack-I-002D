package com.edutech.soporte.controller;

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.service.SoporteService;
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
public class SoporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SoporteService soporteService;

    @Test
    public void testObtenerMensajes() throws Exception {
        when(soporteService.obtenerMensajes()).thenReturn(Arrays.asList(new Soporte(), new Soporte()));

        mockMvc.perform(get("/api/soporte"))
               .andExpect(status().isOk());
    }
}
