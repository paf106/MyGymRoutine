package com.MyGymRoutine.myapp.data.model;

public class Novedad {

    private String titulo;
    private String descripcion;
    private int tipo;
    private String foto;

    public static final int INFO = 0;
    public static final int WARNING = 1;
    public static final int NEW = 2;


    public Novedad(String titulo, String descripcion, int tipo, String foto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.foto = foto;
    }
    public Novedad(){}

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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
