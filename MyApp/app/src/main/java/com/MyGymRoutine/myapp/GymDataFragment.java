package com.MyGymRoutine.myapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.MyGymRoutine.myapp.databinding.FragmentGymDataBinding;
import com.google.android.material.datepicker.MaterialDatePicker;


public class GymData extends Fragment {

    private FragmentGymDataBinding binding;

    public GymData() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGymDataBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                datePicker.show(getFragmentManager(),"tag");
            }
        });

        // When the user clicks OK in the dialog
        datePicker.addOnPositiveButtonClickListener(selection -> {
          //  String date = String.format(Locale.getDefault(), "%02d-%02d-%02d", day, month+1, year);
            binding.etBirthday.setText(datePicker.getHeaderText());
        });

        binding.btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainScreen.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        // Para el dropdown menu
        String[] options = getResources().getStringArray(R.array.frecuenciasDeportes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(),R.layout.dropdown_item,options);
        binding.frecuenciaDeporte.setAdapter(arrayAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}