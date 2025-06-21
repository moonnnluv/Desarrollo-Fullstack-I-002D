package com.edutech.usuarios.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.edutech.usuarios.entity.Usuario;
import com.edutech.usuarios.repository.UsuarioRepository;

public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodos() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan Pérez");
        usuario.setEmail("juan@example.com");
        usuario.setRol("Estudiante");

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioService.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Pedro Soto");
        usuario.setEmail("pedro@example.com");
        usuario.setRol("Gestor de Cursos");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Pedro Soto", resultado.getNombre());
        assertEquals("pedro@example.com", resultado.getEmail());
        assertEquals("Gestor de Cursos", resultado.getRol());
    }

    @Test
    void testGuardar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Ana Torres");
        usuario.setEmail("ana@example.com");
        usuario.setRol("Administrador");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.guardar(usuario);

        assertNotNull(resultado);
        assertEquals("ana@example.com", resultado.getEmail());
        assertEquals("Administrador", resultado.getRol());
    }

    @Test
    void testActualizar() {
        Usuario original = new Usuario();
        original.setId(1L);
        original.setNombre("Original");

        Usuario actualizado = new Usuario();
        actualizado.setNombre("Camila Herrera");
        actualizado.setEmail("camila@example.com");
        actualizado.setRol("Gestor de Cursos");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(original));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(i -> i.getArgument(0));

        Usuario resultado = usuarioService.actualizar(1L, actualizado);

        assertEquals("Camila Herrera", resultado.getNombre());
        assertEquals("camila@example.com", resultado.getEmail());
        assertEquals("Gestor de Cursos", resultado.getRol());
    }

    @Test
    void testEliminar() {
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.eliminar(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }
}
