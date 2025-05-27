package com.edutech.cursos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String instructor;
    private int duracion;

    public Curso() {}

    public Curso(String nombre, String descripcion, String instructor, int duracion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.instructor = instructor;
        this.duracion = duracion;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getInstructor() { return instructor; }
    public int getDuracion() { return duracion; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
}
