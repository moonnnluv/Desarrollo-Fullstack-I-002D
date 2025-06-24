package com.edutech.usuarios.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    List<Usuario> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.chargeUsuario();
    }

     // CARGAR USUARIOS
    public void chargeUsuario() {
        Usuario u1 = new Usuario("pedro@test.com", 1L, "Pedro Soto", "Gestor de Cursos");
        Usuario u2 = new Usuario("ana@test.com", 2L, "Ana Torres", "Administrador");
        Usuario u3 = new Usuario("jose@test.com", 3L, "Jose Bravo", "Estudiante");

        list.add(u1);
        list.add(u2);
        list.add(u3);
    }

    // PROBAR EL MÉTODO findAll()
    @Test
    void testObtenerTodos() {

        when(usuarioRepository.findAll()).thenReturn(list);

        List<Usuario> respuesta = usuarioService.obtenerTodos();

        assertEquals(3, respuesta.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    // PROBAR EL MÉTODO findById()
    @Test
    void testObtenerPorId() {

        Usuario usuario = list.get(2);
        when(usuarioRepository.findById(3L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.obtenerPorId(3L);

        assertTrue(resultado.isPresent());
        assertEquals("Jose Bravo", resultado.get().getNombre());
    }

    // PROBAR EL MÉTODO save()
    @Test
    void testGuardar() {

        Usuario original = new Usuario("ana@test.com", 2L, "Ana Torres", "Administrador");

        when(usuarioRepository.save(original)).thenReturn(original);

        Usuario resultado = usuarioService.guardar(original);

        assertNotNull(resultado);
        assertEquals("Ana Torres", resultado.getNombre());
    }

    // PROBAR EL MÉTODO update()
    @Test
    void testActualizar() {
    Usuario existente = new Usuario("ana@test.com", 2L, "Ana Torres", "Administrador");
    Usuario actualizado = new Usuario("ana@test.com", 2L, "Ana Torres", "Estudiante");

    when(usuarioRepository.findById(2L)).thenReturn(Optional.of(existente));
    when(usuarioRepository.save(existente)).thenReturn(actualizado);

    Usuario resultado = usuarioService.actualizar(2L, actualizado);

    assertNotNull(resultado);
    assertEquals("Ana Torres", resultado.getNombre());
    assertEquals("Estudiante", resultado.getRol());
    }

   // PROBAR EL MÉTODO deleteById()
    @Test
    void testEliminar() {
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.eliminar(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }
}
