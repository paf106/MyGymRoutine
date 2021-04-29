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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class PersonalData extends Fragment{

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

           saveData();
           //Que guarde los datos en un bundle

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
                validateInput(binding.etPassword, binding.ilRepeatPassword);
            }
        });
    }

    private void validateInput(TextInputEditText editText,TextInputLayout inputLayout) {
        if (editText.getInputType() == InputType.TYPE_CLASS_TEXT){

            // Si son de tipo texto
            if(editText.length() != 0){
                // Tiene texto escrito
                inputLayout.setErrorEnabled(false);
            }else{
                // Está vacío
                inputLayout.setError("Campo incompleto");
                //inputLayout.setBoxStrokeColor(Color.RED);
            }
            // Si son el campo contraseña
        }else if(editText.getId()==binding.etPassword.getId()){

            // Comprobamos que las contraseñas sean iguales
            if (binding.etPassword.getText().toString().equals(binding.etRepeatPassword.getText().toString())){
                // Contraseñas iguales
                inputLayout.setErrorEnabled(false);
                // Si contraseña menor de 8 cifras
                if(binding.etPassword.getText().length()<8){
                    inputLayout.setError("Contraseña más de 8 caracteres");
                }
            }else{
                // Contraseñas diferentes
                inputLayout.setError("Contraseñas diferentes");
            }
        }else if(editText.getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS){
            //code
        }else if(editText.getInputType() == InputType.TYPE_CLASS_PHONE){
            //code
        }


    }

    private void saveData() {
    String text = binding.etPhone.getText().toString();
        //Snackbar.make(getView(),"Hola",Snackbar.LENGTH_SHORT).show();
        //Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
       // Toast.makeText(getActivity(), "He empezado", Toast.LENGTH_SHORT).show();

    }
}