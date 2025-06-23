package com.edutech.usuarios.controller;

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

import com.edutech.usuarios.entity.Usuario;
import com.edutech.usuarios.service.UsuarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockitoBean
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Usuario> usuarioLista;

    // LISTA TODOS LOS USUARIOS
    @Test
    void testListarUsuarios() throws Exception {

        when(usuarioServiceImpl.obtenerTodos()).thenReturn(usuarioLista);
        mockmvc.perform(get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // LISTA LOS USUARIOS POR ID
    @Test
    void testObtenerUsuarioPorId() throws Exception {

        Usuario usuario = new Usuario("pedro@test.com", 1L, "Pedro Soto", "Gestor de Cursos");
        try{
        when(usuarioServiceImpl.obtenerPorId(1L)).thenReturn(Optional.of(usuario));
        mockmvc.perform(get("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzó un error " + ex.getMessage());
        }
    }


    // CREA UN NUEVO USUARIO
    @Test
    void testCrearUsuario() throws Exception {

        Usuario usuario = new Usuario("pedro@test.com", 1L, "Pedro Soto", "Gestor de Cursos");
        
        when(usuarioServiceImpl.guardar(any(Usuario.class))).thenReturn(usuario);
                mockmvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                        .andExpect(status().isCreated());
    }

    // MODIFICA UN USUARIO
    @Test
    void testActualizarUsuario() throws Exception {
        Usuario usuario = new Usuario("ana@test.com", 2L, "Ana Torres", "Administrador");
        Usuario usuarioActualizado = new Usuario("ana@test.com", 2L, "Ana Torres", "Estudiante");

        when(usuarioServiceImpl.obtenerPorId(2L)).thenReturn(Optional.of(usuario));
        when(usuarioServiceImpl.guardar(any(Usuario.class))).thenReturn(usuarioActualizado);

        mockmvc.perform(put("/api/usuarios/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioActualizado)))
                .andExpect(status().isOk());
    }

    // ELIMINA UN USUARIO
    @Test
    void testEliminarUsuario() throws Exception {
        Usuario usuario = new Usuario("jose@test.com", 3L, "Jose Bravo", "Estudiante");
        when(usuarioServiceImpl.obtenerPorId(3L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioServiceImpl).eliminar(3L);

        mockmvc.perform(delete("/api/usuarios/3"))
                .andExpect(status().isOk());

        verify(usuarioServiceImpl, times(1)).eliminar(3L);
    }

}
