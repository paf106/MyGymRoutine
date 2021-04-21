package com.MyGymRoutine.myapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.MyGymRoutine.myapp.databinding.FragmentPersonalDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class PersonalData extends Fragment {

    private FragmentPersonalDataBinding binding;

    public PersonalData() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPersonalDataBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Saves the form data and forward to GymData
        binding.btnSiguiente.setOnClickListener(v -> {

           // saveData(); Que guarde los datos en un bundle

            GymData gymData = new GymData();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.llFragments,gymData).commit();
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
                validateInput(binding.etName, binding.ilName);
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
                validateInput(binding.etSurname, binding.ilSurname);
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
                validateInput(binding.etPassword, binding.ilPassword);
            }
        });
    }

    private void validateInput(TextInputEditText editText,TextInputLayout inputLayout) {
        Toast.makeText(getActivity(), editText.getInputType()+"", Toast.LENGTH_SHORT).show();
        if (editText.getInputType() == InputType.TYPE_CLASS_TEXT){

            // Si son de tipo texto
            if(editText.length() != 0){
                // Tiene texto escrito
                inputLayout.setBoxStrokeColor(Color.parseColor("#004AAD"));
            }else{
                // Está vacío
                editText.setError("Campo incompleto");
                inputLayout.setBoxStrokeColor(Color.RED);
            }
        }else if(editText.getInputType() == 129){

            if (binding.etPassword.getText().toString().equals(binding.etRepeatPassword.getText().toString())){
                // Contraseñas iguales
                inputLayout.setBoxStrokeColor(Color.parseColor("#004AAD"));
            }else{
                // Contraseñas diferentes
                editText.setError("Contraseñas diferentes");
                inputLayout.setBoxStrokeColor(Color.RED);
            }
        }


    }

    private void saveData() {
    String text = binding.etPhone.getText().toString();
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
       // Toast.makeText(getActivity(), "He empezado", Toast.LENGTH_SHORT).show();

    }
}