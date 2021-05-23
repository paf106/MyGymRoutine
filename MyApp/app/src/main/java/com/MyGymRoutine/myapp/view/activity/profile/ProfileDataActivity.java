package com.MyGymRoutine.myapp.view.activity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.databinding.ActivityProfileDataBinding;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;

public class ProfileDataActivity extends AppCompatActivity {

    private ActivityProfileDataBinding binding;
    private Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = new Preferences(getApplicationContext());

        binding.commonHeader.commonHeaderTitleText.setText("Mi perfil");
        binding.commonHeader.commonHeaderBackButton.setOnClickListener( (v) -> finish());

        //Dropdown menu
        String[] options = getResources().getStringArray(R.array.frecuenciasDeportes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, options);
        binding.frecuenciaDeporte.setAdapter(arrayAdapter);

        initData();
    }

    private void initData(){
        Client sharedClient = preferences.getClient();
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