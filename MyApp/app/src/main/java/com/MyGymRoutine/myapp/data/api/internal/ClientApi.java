package com.MyGymRoutine.myapp.data.api.internal;


import com.MyGymRoutine.myapp.data.model.Client;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ClientApi {

    @FormUrlEncoded
    @POST("/register")
    Call<Void> doRegister(
            @Field("nombre") String nombre,
            @Field("apellidos") String apellidos,
            @Field("usuario") String usuario,
            @Field("contrasena") String contrasena,
            @Field("correoElectronico") String correoElectronico,
            @Field("fechaNacimiento") String fechaNacimiento,
            @Field("telefono") String telefono,
            @Field("peso") double peso,
            @Field("altura") double altura,
            @Field("frecuenciaDeporte") String frecuenciaDeporte,
            @Field("patologias") String patologias
    );
    @FormUrlEncoded
    @POST("/fetch/cliente")
    Call<Client> fetchClient(
            @Field("idCliente") int idCliente
    );

    @FormUrlEncoded
    @POST("/login")
    Call<Client> doLogin(
            @Field("usuario") String usuario,
            @Field("contrasena") String contrasena
    );

    @FormUrlEncoded
    @POST("/update/password")
    Call<Void> updatePassword(
            @Field("contrasena") String contrasena,
            @Field("idCliente") int idCliente
    );

    @FormUrlEncoded
    @POST("/update/user")
    Call<Void> updateUser(
            @Field("nombre") String nombre,
            @Field("apellidos") String apellidos,
            @Field("correoElectronico") String correoElectronico,
            @Field("telefono") String telefono,
            @Field("peso") double peso,
            @Field("altura") double altura,
            @Field("patologias") String patologias,
            @Field("frecuenciaDeporte") String frecuenciaDeporte,
            @Field("idCliente") int idCliente
    );

    @FormUrlEncoded
    @POST("/checkUsername")
    Call<String> checkUsername(
            @Field("usuario") String usuario
    );

    /*@Multipart
    @POST("/update/imagen")
    Call<String> updateImagen(
            @Part MultipartBody.Part bytes,
            @Part("idCliente") RequestBody requestBody
    );*/
    @FormUrlEncoded
    @POST("/update/imagen")
    Call<String> updateImagen(
            @Field("imagen") byte[] imagen,
            @Field("idCliente") int idCliente

    );






}
