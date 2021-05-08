package com.MyGymRoutine.myapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.databinding.ActivityRegistroBinding;
import com.MyGymRoutine.myapp.view.components.utils.ValidateInput;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Objects;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegistroBinding binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Arrow back (Back to login page)
        binding.ivBacktoLogin.setOnClickListener(v -> {
            startActivity(new Intent(this,LogInActivity.class));
        });

        //Dropdown menu
        String[] options = getResources().getStringArray(R.array.frecuenciasDeportes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, options);
        binding.frecuenciaDeporte.setAdapter(arrayAdapter);

        // Build Date picker
        MaterialDatePicker datePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Selecciona la fecha")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

        //When you click on the Birthday input
        binding.etBirthday.setOnClickListener(v -> datePicker.show(getSupportFragmentManager(),"tag"));

        // When the user clicks OK in the dialog
        datePicker.addOnPositiveButtonClickListener(selection -> {
            //  String date = String.format(Locale.getDefault(), "%02d-%02d-%02d", day, month+1, year);
            Toast.makeText(this, selection.toString(), Toast.LENGTH_SHORT).show();
            binding.etBirthday.setText(datePicker.getHeaderText());
        });

        // PROVISIONAL go to main app
        binding.btnRegistrar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
        });

        binding.etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValidateInput.typePhoneNumber(binding.ilPhone);
            }
        });

        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValidateInput.typeText(binding.ilName);
            }
        });

        binding.etSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValidateInput.typeText(binding.ilSurname);
            }
        });

        binding.etRepeatPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValidateInput.typePassword(binding.ilPassword, binding.ilRepeatPassword);
            }
        });

        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValidateInput.typeEmail(binding.ilEmail);
            }
        });

        binding.etUserHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValidateInput.typeUserHeight(binding.ilUserHeight);
            }
        });
    }
}