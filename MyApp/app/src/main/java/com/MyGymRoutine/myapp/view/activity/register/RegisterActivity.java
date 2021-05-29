package com.MyGymRoutine.myapp.view.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.api.internal.ClientApi;
import com.MyGymRoutine.myapp.data.api.internal.NovedadApi;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.data.model.Novedad;
import com.MyGymRoutine.myapp.databinding.ActivityRegisterBinding;
import com.MyGymRoutine.myapp.view.activity.NavigationActivity;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;
import com.MyGymRoutine.myapp.view.components.utils.ValidateInput;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.commonHeader.commonHeaderTitleText.setText("Crear cuenta");
        binding.commonHeader.commonHeaderBackButton.setOnClickListener( v -> onBackPressed());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClientApi service = retrofit.create(ClientApi.class);

        //Dropdown menu
        String[] options = getResources().getStringArray(R.array.frecuenciasDeportes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, options);
        binding.frecuenciaDeporte.setAdapter(arrayAdapter);

        // Build Date picker
        MaterialDatePicker<Long> datePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Selecciona la fecha")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

        //When you click on the Birthday input
        binding.etBirthday.setOnClickListener(v -> datePicker.show(getSupportFragmentManager(),"tag"));

        // When the user clicks OK in the dialog
        datePicker.addOnPositiveButtonClickListener(selection -> {
            // Get the offset from our timezone and UTC.
            TimeZone timeZoneUTC = TimeZone.getDefault();
            // It will be negative, so that's the -1
            int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
            // Create a date format, then a date object with our offset
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = new Date(selection + offsetFromUTC);

            binding.etBirthday.setText(simpleFormat.format(date));
        });

        // PROVISIONAL go to main app
        binding.btnRegistrar.setOnClickListener(v -> {

            Call<Void> registro = service.doRegister(
                    binding.etName.getText().toString(),
                    binding.etSurname.getText().toString(),
                    binding.etUsername.getText().toString(),
                    binding.etRepeatPassword.getText().toString(),
                    binding.etEmail.getText().toString(),
                    binding.etBirthday.getText().toString(),
                    binding.etPhone.getText().toString(),
                    Double.parseDouble(binding.etUserWeight.getText().toString()),
                    Double.parseDouble(binding.etUserHeight.getText().toString()),
                    binding.frecuenciaDeporte.getText().toString(),
                    binding.etPathology.getText().toString()
            );

            Call<String> checkUser = service.checkUsername(binding.etUsername.getText().toString());
            checkUser.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(RegisterActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    if (response.body()!=null){
                        Snackbar.make(v, "Usuario en uso", Snackbar.LENGTH_LONG).show();
                        binding.ilUsername.setError("Elija otro nombre de usuario");

                    }else {
                        registro.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                binding.ilUsername.setErrorEnabled(false);
                                //clearInputs();
                                Snackbar.make(v, "¡Ya estás registrado!", Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Snackbar.make(v, "Algo ha salido mal :(", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });



        });

        /*binding.etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (binding.etUsername.getText().toString().equals("")){

                }
            }
        });*/


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

    private void clearInputs() {
        binding.etName.setText("");
        binding.etSurname.setText("");
        binding.etUsername.setText("");
        binding.etRepeatPassword.getText().clear();
        binding.etPassword.getText().clear();
        binding.etEmail.getText().clear();
        binding.etBirthday.getText().clear();
        binding.etPhone.getText().clear();
        binding.etUserWeight.getText().clear();
        binding.etUserHeight.getText().clear();
        binding.frecuenciaDeporte.getText().clear();
        binding.etPathology.getText().clear();
    }
}