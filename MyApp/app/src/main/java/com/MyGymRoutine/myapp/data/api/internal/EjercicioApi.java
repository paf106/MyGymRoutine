package com.MyGymRoutine.myapp.data.api.internal;


import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.Imagen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EjercicioApi {

    @GET("/ejercicio/lista")
    Call<List<Ejercicio>> getEjercicios();

    @GET("/ejercicio/grupoMuscular/lista")
    Call<List<String>> getGruposMusculares();

    @GET("/ejercicio/imagen/{idEjercicio}")
    Call<Imagen> getImagenEjercicio(
            @Path("idEjercicio") int idEjercicio
    );


}
