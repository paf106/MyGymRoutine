package com.MyGymRoutine.myapp.data.model;

import java.io.Serializable;

public class DiaRutina implements Serializable {
    private int dia;
    private String nombre;
    private String repeticiones;
    private int series;
    private String descanso;
    private String descripcion;

    public DiaRutina(){}

    public DiaRutina(int dia, String nombre, String repeticiones, int series, String descanso, String descripcion) {
        this.dia = dia;
        this.nombre = nombre;
        this.repeticiones = repeticiones;
        this.series = series;
        this.descanso = descanso;
        this.descripcion = descripcion;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getDescanso() {
        return descanso;
    }

    public void setDescanso(String descanso) {
        this.descanso = descanso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
