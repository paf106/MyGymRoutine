package com.MyGymRoutine.myapp.view.components.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.MyGymRoutine.myapp.data.model.Client;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class Preferences {
    private static final String CLIENT_KEY = "CLIENT_KEY";
    private final Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public void saveCredentials(Client client) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        String json = new Gson().toJson(client);
        edit.putString(CLIENT_KEY,json);
        edit.apply();
    }

    public Client getClient() {
        String json = getSharedPreferences().getString(CLIENT_KEY,null);
        if(json == null){
            return null;
        }
        Client client = new Gson().fromJson(json, Client.class);
        return client;
    }
    public boolean hasCredentials(){
        String json = getSharedPreferences().getString(CLIENT_KEY,null);
        if(json == null){
            return false;
        }else{
            return true;
        }
    }

    private SharedPreferences getSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void forgetCredentials(){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear().apply();

    }
}
