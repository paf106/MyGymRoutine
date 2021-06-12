package com.MyGymRoutine.myapp.view.components.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.MyGymRoutine.myapp.data.api.internal.ClientApi;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.data.repository.Api;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Preferences {
    private static final String CLIENT_KEY = "CLIENT_KEY";
    private static final String CLIENT_PHOTO = "CLIENT_PHOTO";
    private final Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public void savePhoto(Bitmap bitmap){
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
        byte[] arr = baos.toByteArray();
        edit.putString(CLIENT_PHOTO, Base64.encodeToString(arr, Base64.DEFAULT));
        edit.apply();
    }
    public Bitmap getPhoto(){
        String json = getSharedPreferences().getString(CLIENT_PHOTO, null);
        Bitmap bitmap = null;
        if (json == null) {
            return null;
        }
        if( !json.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(json, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return bitmap;
    }

    public void saveCredentials(Client client) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        String json = new Gson().toJson(client);
        edit.putString(CLIENT_KEY, json);
        edit.apply();
    }

    public void refreshCurrentUser(int clientId) {

        ClientApi service = Api.getClient().create(ClientApi.class);

        Call<Client> client = service.fetchClient(clientId);

        client.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                saveCredentials(response.body());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });
    }

    public Client getClient() {
        String json = getSharedPreferences().getString(CLIENT_KEY, null);
        if (json == null) {
            return null;
        }
        Client client = new Gson().fromJson(json, Client.class);
        return client;
    }

    public boolean hasCredentials() {
        String json = getSharedPreferences().getString(CLIENT_KEY, null);
        if (json == null) {
            return false;
        } else {
            return true;
        }
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void forgetCredentials() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(CLIENT_KEY,null);
        editor.apply();
    }
}
