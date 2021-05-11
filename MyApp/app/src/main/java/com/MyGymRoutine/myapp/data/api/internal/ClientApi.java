package com.MyGymRoutine.myapp.data.api.internal;


import com.MyGymRoutine.myapp.data.model.responses.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ClientApi {
    @POST("save.php")
    Call<ResponseRegister> doRegister(
            @Field("nombre") String nombre,
            @Field("apellidos") String apellidos,
            @Field("usuario") String usuario,
            @Field("contrasena") String contrasena,
            @Field("correoElectronico") String correoElectronico,
            @Field("fechaNacimiento") String fechaNacimiento,
            @Field("telefono") String telefono,
            @Field("peso") String peso,
            @Field("altura") String altura,
            @Field("frecuencaDeporte") String frecuencaDeporte,
            @Field("imagenRuta") String imagenRuta,
            @Field("patologias") String patologias,
            @Field("idPersonal") String idPersonal
    );
}
