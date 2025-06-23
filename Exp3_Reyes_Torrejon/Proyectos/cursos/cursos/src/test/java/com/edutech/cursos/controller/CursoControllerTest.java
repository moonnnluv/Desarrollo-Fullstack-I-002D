package com.edutech.cursos.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when; 

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.edutech.cursos.entity.Curso;
import com.edutech.cursos.service.CursoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.mockito.ArgumentMatchers.any;



@SpringBootTest
@AutoConfigureMockMvc
public class CursoControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CursoServiceImpl cursoServiceImpl;

    private List<Curso> cursoLista;

    // LISTA TODOS LOS CURSOS
    @Test
    void testListarCursos() throws Exception {

        when(cursoServiceImpl.findAll()).thenReturn(cursoLista);
        mockmvc.perform(get("/api/cursos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // LISTA LOS CURSOS POR ID
    @Test
    void testObtenerCursoPorId() throws Exception {

        Curso curso = new Curso(1L, "Curso de Java", "Nivel intermedio","Juanita Perez", 50);
        try{
        when(cursoServiceImpl.findById(1L)).thenReturn(Optional.of(curso));
        mockmvc.perform(get("/api/cursos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzó un error " + ex.getMessage());
        }
    }

    // CREA UN NUEVO CURSO
    @Test
    void testCrearCurso() throws Exception {

        Curso curso = new Curso(1L, "Curso de Java", "Nivel intermedio","Juanita Perez", 50);
        
        when(cursoServiceImpl.save(any(Curso.class))).thenReturn(curso);
                mockmvc.perform(post("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curso)))
                        .andExpect(status().isCreated());
    }

    // MODIFICA UN CURSO
    @Test
    void testActualizarCurso() throws Exception {
        Curso curso = new Curso(1L, "Curso de Java", "Nivel intermedio","Juanita Perez", 50);
        Curso cursoActualizado = new Curso(1L, "Curso de Java", "Nivel intermedio","Jose Perez", 40);

        when(cursoServiceImpl.findById(1L)).thenReturn(Optional.of(curso));
        when(cursoServiceImpl.save(any(Curso.class))).thenReturn(cursoActualizado);

        mockmvc.perform(put("/api/cursos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cursoActualizado)))
                .andExpect(status().isOk());
    }

    // ELIMINA UN CURSO
    @Test
    void testEliminarCurso() throws Exception {
        Curso curso = new Curso(1L, "Curso de Java", "Nivel intermedio", "Juanita Perez", 50);
        when(cursoServiceImpl.findById(1L)).thenReturn(Optional.of(curso));
        doNothing().when(cursoServiceImpl).deleteById(1L);

        mockmvc.perform(delete("/api/cursos/1"))
                .andExpect(status().isOk());

        verify(cursoServiceImpl, times(1)).deleteById(1L);
    }
}
