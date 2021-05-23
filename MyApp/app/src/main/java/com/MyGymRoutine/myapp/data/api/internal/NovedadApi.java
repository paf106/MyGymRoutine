package com.MyGymRoutine.myapp.data.api.internal;


import com.MyGymRoutine.myapp.data.model.Novedad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NovedadApi {

    @GET("/novedad/lista")
    Call<List<Novedad>> getNovedades();
}
