package com.MyGymRoutine.myapp.view.activity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MyGymRoutine.myapp.data.api.internal.ClientApi;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.databinding.ActivityModifyPasswordBinding;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModifyPasswordActivity extends AppCompatActivity {

    private Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityModifyPasswordBinding binding = ActivityModifyPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = new Preferences(getApplicationContext());

        Client sharedClient = preferences.getClient();

        binding.commonHeader.commonHeaderTitleText.setText("Modificar contraseña");
        binding.commonHeader.commonHeaderBackButton.setOnClickListener( v -> finish());
        binding.btnModifyPassword.setOnClickListener(v->{

            String currentPassword = binding.etCurrentPassword.getText().toString();
            String newPassword = binding.etNewPassword.getText().toString();
            String repeatNewPassword = binding.etRepeatNewPassword.getText().toString();

            //Comprobar que la contraseña actual realmente es la misma que la que ha escrito
            if (currentPassword.equals(sharedClient.getContrasena())){
                if (!newPassword.equals("") || !repeatNewPassword.equals("")){
                    binding.ilCurrentPassword.setErrorEnabled(false);
                    if (newPassword.equals(repeatNewPassword)){

                        binding.ilNewPassword.setErrorEnabled(false);
                        binding.ilRepeatNewPassword.setErrorEnabled(false);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Constantes.BASE_API)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ClientApi service = retrofit.create(ClientApi.class);

                        Call<Void> client = service.updatePassword(newPassword,sharedClient.getIdCliente());

                        client.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                preferences.refreshCurrentUser(sharedClient.getIdCliente());
                                binding.etCurrentPassword.getText().clear();
                                binding.etRepeatNewPassword.getText().clear();
                                binding.etNewPassword.getText().clear();
                                Snackbar.make(v, "Contraseña cambiada", Snackbar.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Snackbar.make(v, "Comprueba la conexión", Snackbar.LENGTH_LONG).show();
                            }
                        });

                    }else{
                        binding.ilNewPassword.setError("Las contraseñas no son iguales");
                        binding.ilRepeatNewPassword.setError("Las contraseñas no son iguales");
                    }
                }else{
                    binding.ilCurrentPassword.setErrorEnabled(false);
                    binding.ilNewPassword.setError("No se puede quedar vacío");
                    binding.ilRepeatNewPassword.setError("No se puede quedar vacío");
                }

            }else {
                binding.ilCurrentPassword.setError("La contraseña no coincide");
            }
        });
    }
}