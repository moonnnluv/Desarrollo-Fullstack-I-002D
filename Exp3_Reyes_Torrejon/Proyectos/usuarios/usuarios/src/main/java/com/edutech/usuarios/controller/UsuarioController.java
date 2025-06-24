package com.edutech.usuarios.controller;

import java.util.List;
import java.util.Optional;

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

import com.edutech.usuarios.entity.Usuario;
import com.edutech.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // SWAGGER LISTAR
    @Operation(summary = "Obtener lista de usuarios", description = "Devuelve todos los usuarios registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    // LISTA TODOS LOS USUARIOS
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodos();
    }

    // SWAGGER VER POR ID    
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    // MUESTRA LOS USUARIOS POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle (@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.obtenerPorId(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // SWAGGER CREAR
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    })
    // CREA UN NUEVO USUARIO
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
    }

    // SWAGGER MODIFICAR
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    })
    // MODIFICA UN USUARIO
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioService.obtenerPorId(id);
        
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setRol(usuario.getRol());

            Usuario actualizado = usuarioService.guardar(usuarioExistente);
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }
    

    // SWAGGER ELIMINAR
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente")
    })
    // ELIMINA UN CURSO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
    Optional<Usuario> usuarioOptional = usuarioService.obtenerPorId(id);
    if (usuarioOptional.isPresent()) {
        usuarioService.eliminar(id);
        return ResponseEntity.ok(usuarioOptional.get());
    }
    return ResponseEntity.notFound().build();
    }
}
