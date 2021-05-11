
package com.MyGymRoutine.myapp.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseListaEjercicios {

    @SerializedName("ejercicios")
    @Expose
    private List<Ejercicio> ejercicios = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseListaEjercicios() {
    }

    /**
     * 
     * @param ejercicios
     */
    public ResponseListaEjercicios(List<Ejercicio> ejercicios) {
        super();
        this.ejercicios = ejercicios;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public ResponseListaEjercicios withEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
        return this;
    }

}
