package com.edutech.evaluaciones.entity;

import jakarta.persistence.*;

@Entity
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ATRIBUTOS
    private String nombre;
    private String curso;
    private double nota;
    private int ponderacion;
    private String estudiante;

    // CONSTRUCTOR
    public Evaluacion() {
    }

    public Evaluacion(String nombre, String curso, double nota, int ponderacion, String estudiante) {
        this.nombre = nombre;
        this.curso = curso;
        this.nota = nota;
        this.ponderacion = ponderacion;
        this.estudiante = estudiante;
    }

    // GETTER Y SETTER
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(int ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }
}
