package com.MyGymRoutine.myapp.view.activity.routine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MyGymRoutine.myapp.data.model.DiaRutina;
import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.databinding.ActivityDetailDiaRutinaBinding;
import com.MyGymRoutine.myapp.databinding.ActivityRoutineDetailBinding;

public class DetailDiaRutinaActivity extends AppCompatActivity {

    private ActivityDetailDiaRutinaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailDiaRutinaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // DiaRutina recuperado del adaptador DiaRutinAdapter
        DiaRutina diaRutinaTemp = (DiaRutina) getIntent().getExtras().getSerializable("diaRutinaDetail");

        // Nombre del ejercicio
        binding.commonHeader.commonHeaderTitleText.setText(diaRutinaTemp.getNombre());

        binding.commonHeader.commonHeaderBackButton.setOnClickListener( v -> finish());

        // Foto del ejercicio

        // Repeticiones y series del ejercicio
        binding.tvRepeticionesSeries.setText(diaRutinaTemp.getRepeticiones() + " x " +diaRutinaTemp.getSeries());

        // Descanso del ejercicio
        binding.tvDescanso.setText("Descanso: " + diaRutinaTemp.getDescanso());

        // Descripcion del ejercicio
        binding.tvDescripcionEjercicio.setText(diaRutinaTemp.getDescripcion());
    }
}