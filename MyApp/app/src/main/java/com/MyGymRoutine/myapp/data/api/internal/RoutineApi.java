package com.MyGymRoutine.myapp.data.api.internal;

import com.MyGymRoutine.myapp.data.model.DiaRutina;
import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.Rutina;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RoutineApi {

    @GET("/rutina/lista")
    Call<List<Rutina>> getRutinas();

    @GET("/rutina/tipoRutina/lista")
    Call<List<String>> getTiposRutinas();

    @GET("/rutina/dias/{idRutina}")
    Call<Integer> getDias(
            @Path("idRutina") int idRutina
    );

    @GET("/rutina/diasYejercicios/{idRutina}")
    Call<List<DiaRutina>> getDiasYejercicios(
            @Path("idRutina") int idRutina
    );

    @GET("/rutina/{idCliente}")
    Call<List<Rutina>> getRutinasCliente(
            @Path("idCliente") int idCliente
    );
}
