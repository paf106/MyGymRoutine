
package com.MyGymRoutine.myapp.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Ejercicio {

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

    /**
     * No args constructor for use in serialization
     * 
     */
    public Ejercicio() {
    }

    /**
     * 
     * @param descripcion
     * @param grupoMuscular
     * @param series
     * @param repeticiones
     * @param nombre
     */
    public Ejercicio(String nombre, String repeticiones, int series, String grupoMuscular, String descripcion) {
        this.nombre = nombre;
        this.repeticiones = repeticiones;
        this.series = series;
        this.grupoMuscular = grupoMuscular;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ejercicio withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Ejercicio withRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
        return this;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public Ejercicio withSeries(int series) {
        this.series = series;
        return this;
    }

    public String getgrupoMuscular() {
        return grupoMuscular;
    }

    public void setgrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public Ejercicio withgrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ejercicio withDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

}
