package com.MyGymRoutine.myapp.view.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.databinding.FragmentGymDataBinding;
import com.MyGymRoutine.myapp.view.components.utils.ValidateInput;
import com.google.android.material.datepicker.MaterialDatePicker;

import org.jetbrains.annotations.NotNull;


public class GymDataFragment extends Fragment {

    private FragmentGymDataBinding binding;

    public GymDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
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
                datePicker.show(getParentFragmentManager(),"tag");
            }
        });

        // When the user clicks OK in the dialog
        datePicker.addOnPositiveButtonClickListener(selection -> {
          //  String date = String.format(Locale.getDefault(), "%02d-%02d-%02d", day, month+1, year);
            binding.etBirthday.setText(datePicker.getHeaderText());
        });

//        binding.btnFinalizar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),MainScreen.class));
//            }
//        });

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