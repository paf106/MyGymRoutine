package com.MyGymRoutine.myapp.view.activity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;


import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.api.internal.ClientApi;
import com.MyGymRoutine.myapp.data.api.internal.NovedadApi;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.data.model.Novedad;
import com.MyGymRoutine.myapp.databinding.ActivityProfileDataBinding;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileDataActivity extends AppCompatActivity {

    private ActivityProfileDataBinding binding;
    private Client sharedClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Preferences preferences = new Preferences(getApplicationContext());
        sharedClient = preferences.getClient();

        binding.commonHeader.commonHeaderTitleText.setText("Mi perfil");
        binding.commonHeader.commonHeaderBackButton.setOnClickListener( (v) -> finish());

        initData();

        binding.btnGuardar.setOnClickListener(v -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ClientApi service = retrofit.create(ClientApi.class);

            Call<Void> listado = service.updateUser(
                    binding.etName.getText().toString(),
                    binding.etSurname.getText().toString(),
                    binding.etEmail.getText().toString(),
                    binding.etPhone.getText().toString(),
                    Double.parseDouble(binding.etUserWeight.getText().toString()),
                    Double.parseDouble(binding.etUserHeight.getText().toString()),
                    binding.etPathology.getText().toString(),
                    binding.frecuenciaDeporte.getText().toString(),
                    sharedClient.getIdCliente()
            );
            listado.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    preferences.refreshCurrentUser(sharedClient.getIdCliente());
                    Snackbar.make(v, "Cambios guardados", Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Snackbar.make(v, "Comprueba la conexión", Snackbar.LENGTH_LONG).show();
                }
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Dropdown menu
        String[] options = getResources().getStringArray(R.array.frecuenciasDeportes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, options);
        binding.frecuenciaDeporte.setAdapter(arrayAdapter);
    }

    private void initData(){
        binding.etName.setText(sharedClient.getNombre());
        binding.etSurname.setText(sharedClient.getApellidos());
        binding.etEmail.setText(sharedClient.getCorreoElectronico());
        binding.etPhone.setText(sharedClient.getTelefono());
        binding.etUserHeight.setText(String.valueOf(sharedClient.getAltura()));
        binding.etUserWeight.setText(String.valueOf(sharedClient.getPeso()));
        binding.frecuenciaDeporte.setText(sharedClient.getFrecuenciaDeporte());
        binding.etPathology.setText(sharedClient.getPatologias());
    }
}