package com.MyGymRoutine.myapp.data.api.internal;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileApi {

    @Multipart
    @POST("/upload/image")
    Call<RequestBody> uploadImage(
            @Part MultipartBody.Part image

    );
}
