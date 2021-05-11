package com.MyGymRoutine.myapp.data.api.internal;


import com.MyGymRoutine.myapp.data.model.Ejercicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EjercicioApi {

    @GET("/ejercicio/lista")
    Call<List<Ejercicio>> getEjercicios();

}
