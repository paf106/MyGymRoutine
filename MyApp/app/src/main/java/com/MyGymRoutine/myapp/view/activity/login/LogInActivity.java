package com.MyGymRoutine.myapp.view.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.MyGymRoutine.myapp.data.api.internal.ClientApi;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.data.repository.Api;
import com.MyGymRoutine.myapp.databinding.LogInActivityBinding;
import com.MyGymRoutine.myapp.view.activity.NavigationActivity;
import com.MyGymRoutine.myapp.view.activity.register.RegisterActivity;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LogInActivity extends AppCompatActivity {

    private LogInActivityBinding binding;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LogInActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = new Preferences(getApplicationContext());

        if (preferences.hasCredentials()) {
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
        }

        binding.tvCreateAccount.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));

        binding.btnLogIn.setOnClickListener(this::login);
    }

    private void login(View v) {
        String user = binding.etUser.getText().toString();
        String password = binding.etPassword.getText().toString();

        ClientApi service = Api.getClient().create(ClientApi.class);

        Call<Client> client = service.doLogin(user, password);
        client.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()) {
                    if (binding.etUser.getText().toString().equals("") || binding.etPassword.getText().toString().equals("")) {
                        binding.ilPassword.setError("Debes rellenar los 2 campos");
                    } else {
                        Client sharedClient = response.body();
                        // Credenciales correctas
                        if (sharedClient != null) {
                            // Credenciales correctas, guardamos al usuario y redirigimos a pantalla principal
                            preferences.saveCredentials(sharedClient);
                            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                            finish();
                        } else {
                            binding.ilPassword.setError("Usuario o contraseña inválida");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Snackbar.make(v, "Combrueba la conexión", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}