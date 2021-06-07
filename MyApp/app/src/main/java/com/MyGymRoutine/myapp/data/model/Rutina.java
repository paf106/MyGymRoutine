package com.MyGymRoutine.myapp.data.model;

import java.io.Serializable;
import java.util.List;

public class Rutina implements Serializable {

    private int idRutina;
    private String nombre;
    private String descripcion;
    private String tipoRutina;
    private int idPersonal;
    private List<Ejercicio> ejercicios;

    public Rutina(){}

    public Rutina(String nombre, List<Ejercicio> ejercicios) {
        this.nombre = nombre;
        this.ejercicios = ejercicios;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoRutina() {
        return tipoRutina;
    }

    public void setTipoRutina(String tipoRutina) {
        this.tipoRutina = tipoRutina;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
