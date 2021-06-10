
package com.MyGymRoutine.myapp.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Ejercicio implements Serializable {

    private int idEjercicio;
    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("repeticiones")
    @Expose
    private String repeticiones;

    @SerializedName("series")
    @Expose
    private int series;

    @SerializedName("grupoMuscular")
    @Expose
    private String grupoMuscular;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("descanso")
    @Expose
    private String descanso;

    @SerializedName("musculo")
    @Expose
    private String musculo;

    @SerializedName("imagen")
    @Expose
    private Imagen imagen;

    public Ejercicio() {
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
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

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescanso() {
        return descanso;
    }

    public void setDescanso(String descanso) {
        this.descanso = descanso;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }
}
