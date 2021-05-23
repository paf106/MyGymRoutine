package com.MyGymRoutine.myapp.data.api.internal;


import com.MyGymRoutine.myapp.data.model.Client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ClientApi {

    @POST("/register")
    Call<Void> doRegister(
            @Body String nombre,
            @Body String apellidos,
            @Body String usuario,
            @Body String contrasena,
            @Body String correoElectronico,
            @Body String fechaNacimiento,
            @Body String telefono,
            @Body double peso,
            @Body double altura,
            @Body String patologias
    );

    @FormUrlEncoded
    @POST("/login")
    Call<Client> doLogin(
            @Field("usuario") String usuario,
            @Field("contrasena") String contrasena
    );

    @POST("/update/password")
    Call<Void> updatePassword(
            @Body String contrasena,
            @Body int idCliente
    );






}
