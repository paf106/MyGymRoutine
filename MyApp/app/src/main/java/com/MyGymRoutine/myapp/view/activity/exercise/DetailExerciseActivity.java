package com.MyGymRoutine.myapp.view.activity.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MyGymRoutine.myapp.data.model.DiaRutina;
import com.MyGymRoutine.myapp.data.model.Ejercicio;

import com.MyGymRoutine.myapp.databinding.ActivityDetailExerciseBinding;
import com.MyGymRoutine.myapp.view.components.utils.FileUtils;


public class DetailExerciseActivity extends AppCompatActivity {

    private ActivityDetailExerciseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ejercicio recuperado del adaptador EjercicioAdapter
        Ejercicio ejercicioTemp = (Ejercicio) getIntent().getExtras().getSerializable("ejercicioDetail");
        DiaRutina diaRutinaTemp = (DiaRutina) getIntent().getExtras().getSerializable("diaRutinaDetail");

        binding.commonHeader.commonHeaderBackButton.setOnClickListener( v -> finish());

        if (ejercicioTemp != null){
            // Nombre del ejercicio
            binding.commonHeader.commonHeaderTitleText.setText(ejercicioTemp.getNombre());

            // Foto del ejercicio
            binding.ivFotoEjercicio.setImageBitmap(FileUtils.ByteArrayToBitmap(ejercicioTemp.getImagen().getData()));

            // Repeticiones y series del ejercicio
            binding.tvRepeticionesSeries.setText(ejercicioTemp.getRepeticiones() + " x " +ejercicioTemp.getSeries());

            // Descanso del ejercicio
            binding.tvDescanso.setText("Descanso: " + ejercicioTemp.getDescanso());

            // Descripcion del ejercicio
            binding.tvDescripcionEjercicio.setText(ejercicioTemp.getDescripcion());

            // Musculo del ejercicio
            binding.tvMusculo.setText("Músculo: "+ejercicioTemp.getMusculo());
        }else{
            // Nombre del ejercicio
            binding.commonHeader.commonHeaderTitleText.setText(diaRutinaTemp.getNombre());

            // Foto del ejercicio
            binding.ivFotoEjercicio.setImageBitmap(FileUtils.ByteArrayToBitmap(diaRutinaTemp.getImagen().getData()));
            // Repeticiones y series del ejercicio
            binding.tvRepeticionesSeries.setText(diaRutinaTemp.getRepeticiones() + " x " +diaRutinaTemp.getSeries());

            // Descanso del ejercicio
            binding.tvDescanso.setText("Descanso: " + diaRutinaTemp.getDescanso());

            // Descripcion del ejercicio
            binding.tvDescripcionEjercicio.setText(diaRutinaTemp.getDescripcion());

            // Musculo del ejercicio
            binding.tvMusculo.setText("Músculo: "+diaRutinaTemp.getMusculo());
        }



    }
}