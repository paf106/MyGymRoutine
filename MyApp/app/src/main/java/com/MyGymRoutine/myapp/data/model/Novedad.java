package com.MyGymRoutine.myapp.data.model;

public class Novedad {

    private String titulo;
    private String descripcion;
    private String tipo;

    public static final int INFO = 0;
    public static final int WARNING = 1;
    public static final int NEW = 2;

    public Novedad(String titulo, String descripcion, String tipo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
