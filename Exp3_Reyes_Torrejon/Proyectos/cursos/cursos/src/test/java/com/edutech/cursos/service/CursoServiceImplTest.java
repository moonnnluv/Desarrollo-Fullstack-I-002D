package com.edutech.cursos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edutech.cursos.entity.Curso;
import com.edutech.cursos.repository.CursoRepository;

public class CursoServiceImplTest {

    @InjectMocks
    private CursoServiceImpl service;

    @Mock
    private CursoRepository repository;

    List<Curso> list = new ArrayList<>();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.chargeCurso();
    }

    // CARGAR CURSOS
    public void chargeCurso() {
        Curso c1 = new Curso(1L, "Curso de Java", "Nivel intermedio", "Juanita Perez", 50);
        Curso c2 = new Curso(2L, "HTML y CSS", "Web", "Jose Perez", 20);
        Curso c3 = new Curso(3L, "Spring Boot", "Backend", "Pedro Perez", 40);

        list.add(c1);
        list.add(c2);
        list.add(c3);
    }

     // PROBAR EL MÉTODO findAll()
    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(list);

        List<Curso> respuesta = service.findAll();

        assertEquals(3, respuesta.size());
        verify(repository, times(1)).findAll();
    }

    // PROBAR EL MÉTODO findById()
    @Test
    public void findByIdTest() {
        Curso curso = list.get(0);
        when(repository.findById(1L)).thenReturn(Optional.of(curso));

        Optional<Curso> resultado = service.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Java Básico", resultado.get().getNombre());
    }

    // PROBAR EL MÉTODO save()
    @Test
    public void saveTest() {
        Curso nuevo = new Curso(4L, "ReactJS", "Frontend moderno", "Andrea", 35);
        when(repository.save(nuevo)).thenReturn(nuevo);

        Curso resultado = service.save(nuevo);

        assertNotNull(resultado);
        assertEquals("ReactJS", resultado.getNombre());
    }

    // PROBAR EL MÉTODO deleteById()
    @Test
    public void deleteByIdTest() {
        doNothing().when(repository).deleteById(1L);

        service.deleteById(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    
}
