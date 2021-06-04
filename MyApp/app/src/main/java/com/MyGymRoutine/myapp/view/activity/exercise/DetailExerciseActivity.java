package com.MyGymRoutine.myapp.view.activity.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MyGymRoutine.myapp.data.model.Ejercicio;

import com.MyGymRoutine.myapp.databinding.ActivityDetailExerciseBinding;


public class DetailExerciseActivity extends AppCompatActivity {

    private ActivityDetailExerciseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ejercicio temporal recuperado del adaptador EjercicioAdapter
        Ejercicio ejercicioTemp = (Ejercicio) getIntent().getExtras().getSerializable("ejercicioDetail");

        // Nombre del ejercicio
        binding.commonHeader.commonHeaderTitleText.setText(ejercicioTemp.getNombre());

        binding.commonHeader.commonHeaderBackButton.setOnClickListener( v -> finish());

        // Foto del ejercicio

        // Repeticiones y series del ejercicio
        binding.tvRepeticionesSeries.setText(ejercicioTemp.getRepeticiones() + " x " +ejercicioTemp.getSeries());

        // Descanso del ejercicio
        binding.tvDescanso.setText("Descanso: " + ejercicioTemp.getDescanso());

        // Descripcion del ejercicio
        binding.tvDescripcionEjercicio.setText(ejercicioTemp.getDescripcion());

    }
}