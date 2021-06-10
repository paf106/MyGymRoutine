package com.MyGymRoutine.myapp.data.model;


import java.util.List;

public class GrupoRutina {
    private String nombre;
    private List<Rutina> rutinas;

    public GrupoRutina(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<Rutina> rutinas) {
        this.rutinas = rutinas;
    }
}
