package com.edutech.pagos.controller;

import com.edutech.pagos.entity.Pago;
import com.edutech.pagos.service.PagoService;
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
public class PagosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagoService pagoService;

    @Test
    public void testObtenerPagos() throws Exception {
        when(pagoService.obtenerPagos()).thenReturn(Arrays.asList(new Pago(), new Pago()));

        mockMvc.perform(get("/api/pagos"))
               .andExpect(status().isOk());
    }
}
