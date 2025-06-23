package com.edutech.cursos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.cursos.entity.Curso;
import com.edutech.cursos.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Cursos", description = "Operaciones relacionadas con cursos")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // SWAGGER LISTAR
    @Operation(summary = "Obtener lista de cursos", description = "Devuelve todos los cursos disponibles")
    @ApiResponse(responseCode= "200", description="Lista de cursos retornada correctamente",
                    content=@Content(mediaType="application/json",
                    schema= @Schema(implementation=Curso.class)))

    // LISTA TODOS LOS CURSOS
    @GetMapping
    public List<Curso> List() {
        return cursoService.findAll();
    }


    // SWAGGER VER POR ID
    @Operation(summary = "Obtener curso por ID", description = "Obtiene el detalle de un curso especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode= "200", description="Curso encontrado",
                    content=@Content(mediaType="application/json", schema= @Schema(implementation=Curso.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })     
    // LISTA LOS CURSOS POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle (@PathVariable Long id){
        Optional<Curso> cursoOptional = cursoService.findById(id);
        if (cursoOptional.isPresent()) {
            return ResponseEntity.ok(cursoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    // SWAGGER CREAR
    @Operation(summary = "Crear un nuevo curso", description = "Crea un curso con los datos proporcionados")
    @ApiResponse(responseCode= "201", description="Curso creado correctamente",
                    content=@Content(mediaType="application/json",
                    schema= @Schema(implementation=Curso.class)))
    // CREA UN NUEVO CURSO
    @PostMapping
    public ResponseEntity<Curso> crear(@RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    // SWAGGER MODIFICAR
    @Operation(summary = "Modificar un curso por ID", description = "Modifica un curso en particular")
    @ApiResponses(value = {
        @ApiResponse(responseCode= "200", description="Curso modificado correctamente",
                    content=@Content(mediaType="application/json",
                    schema= @Schema(implementation=Curso.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })  
    // MODIFICA UN CURSO
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Curso curso) {
    Optional<Curso> cursoOptional = cursoService.findById(id);
    if (cursoOptional.isPresent()) {
        Curso cursoExistente = cursoOptional.get();
        cursoExistente.setNombre(curso.getNombre());
        cursoExistente.setDescripcion(curso.getDescripcion());
        cursoExistente.setInstructor(curso.getInstructor());
        cursoExistente.setDuracion(curso.getDuracion());

        Curso cursoModificado = cursoService.save(cursoExistente);
        return ResponseEntity.ok(cursoModificado);
    }
    return ResponseEntity.notFound().build();
    }

    // SWAGGER ELIMINAR
    @Operation(summary = "Elimina un curso", description = "Elimina un curso según ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode= "200", description="Curso eliminado correctamente",
                    content=@Content(mediaType="application/json",
                    schema= @Schema(implementation=Curso.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })  
    // ELIMINA UN CURSO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
    Optional<Curso> cursoOptional = cursoService.findById(id);
    if (cursoOptional.isPresent()) {
        cursoService.deleteById(id);
        return ResponseEntity.ok(cursoOptional.get());
    }
    return ResponseEntity.notFound().build();
    }   

}