package com.MyGymRoutine.myapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.databinding.ActivityRegistroBinding;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Objects;

public class Registro extends AppCompatActivity {
    ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ActivityRegistroBinding binding = ActivityRegistroBinding.inflate(getLayoutInflater());

//        String[] options = getResources().getStringArray(R.array.frecuenciasDeportes);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, options);
//        binding.frecuenciaDeporte.setAdapter(arrayAdapter);

        // Build datepicker
        MaterialDatePicker datePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Selecciona la fecha")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

        //When you click on the Birthday input
        binding.etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(),"tag");
            }
        });

        // When the user clicks OK in the dialog
        datePicker.addOnPositiveButtonClickListener(selection -> {
            //  String date = String.format(Locale.getDefault(), "%02d-%02d-%02d", day, month+1, year);
            binding.etBirthday.setText(datePicker.getHeaderText());
        });


        // Primer fragment que se va a mostrar (Es el que pide los datos personales)
//        PersonalDataFragment personalDataFragment = new PersonalDataFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.llFragments, personalDataFragment).commit();
    }
    @Override
    public void onResume() {
        super.onResume();

        // Para el dropdown menu
        String[] options = getResources().getStringArray(R.array.frecuenciasDeportes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, options);
        binding.frecuenciaDeporte.setAdapter(arrayAdapter);
    }
}