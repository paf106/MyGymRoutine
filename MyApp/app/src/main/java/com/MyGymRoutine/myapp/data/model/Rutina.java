package com.MyGymRoutine.myapp.data.model;

import java.util.List;

public class Rutina {

    private String nombre;
    private List<Ejercicio> ejercicios;

    public Rutina(){}

    public Rutina(String nombre, List<Ejercicio> ejercicios) {
        this.nombre = nombre;
        this.ejercicios = ejercicios;
    }
    public Rutina(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
