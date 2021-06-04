package com.MyGymRoutine.myapp.data.api.internal;

import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.Rutina;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RoutineApi {

    @GET("/rutina/lista")
    Call<List<Rutina>> getRutinas();

    @GET("/rutina/tipoRutina/lista")
    Call<List<String>> getTiposRutinas();
}
