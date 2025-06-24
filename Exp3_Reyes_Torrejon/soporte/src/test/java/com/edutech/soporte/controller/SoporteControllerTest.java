package com.edutech.soporte.controller;

import com.edutech.soporte.entity.Soporte;
import com.edutech.soporte.service.SoporteService;
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
class SoporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SoporteService soporteService;

    @Test
    void testObtenerTicketsPorUsuario() throws Exception {
        Soporte s1 = new Soporte();
        Soporte s2 = new Soporte();
        List<Soporte> lista = Arrays.asList(s1, s2);

        when(soporteService.obtenerTicketsPorUsuario(1L)).thenReturn(lista);

        mockMvc.perform(get("/api/soporte/usuario/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
