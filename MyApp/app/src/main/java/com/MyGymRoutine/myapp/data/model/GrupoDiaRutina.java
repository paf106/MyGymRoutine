package com.MyGymRoutine.myapp.data.model;

import java.util.List;

public class GrupoDiaRutina {
    private String nombre;
    private List<DiaRutina> diaRutinas;

    public GrupoDiaRutina(){}

    public GrupoDiaRutina(String nombre, List<DiaRutina> diaRutinas) {
        this.nombre = nombre;
        this.diaRutinas = diaRutinas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DiaRutina> getDiaRutinas() {
        return diaRutinas;
    }

    public void setDiaRutinas(List<DiaRutina> diaRutinas) {
        this.diaRutinas = diaRutinas;
    }
}
