package com.MyGymRoutine.myapp.view.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.api.internal.ClientApi;
import com.MyGymRoutine.myapp.data.api.internal.NovedadApi;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.data.model.Novedad;
import com.MyGymRoutine.myapp.databinding.LogInActivityBinding;
import com.MyGymRoutine.myapp.view.activity.NavigationActivity;
import com.MyGymRoutine.myapp.view.activity.register.RegisterActivity;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity{

    private LogInActivityBinding binding;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LogInActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = new Preferences(getApplicationContext());

        if (preferences.hasCredentials()){
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
        }

        binding.tvCreateAccount.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));

        binding.btnLogIn.setOnClickListener(v -> login());
    }

    private void login() {

        String user = binding.etUser.getText().toString();
        String password = binding.etPassword.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.74:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClientApi service = retrofit.create(ClientApi.class);

        Call<Client> client = service.doLogin(user,password);
        client.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client sharedCLient = response.body();
                // Credenciales incorrectas
                if (sharedCLient != null){
                    // Credenciales correctas, guardamos al usuario y redirigimos a pantalla principal
                    preferences.saveCredentials(sharedCLient);
                    startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                    finish();

                }else{
                    binding.etPassword.setError("Usuario o contraseña inválida");
                }

            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });


    }
}