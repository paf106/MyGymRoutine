package com.MyGymRoutine.myapp.view.activity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MyGymRoutine.myapp.databinding.ActivityModifyPasswordBinding;

public class ModifyPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityModifyPasswordBinding binding = ActivityModifyPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.commonHeader.commonHeaderTitleText.setText("Modificar contraseña");
        binding.commonHeader.commonHeaderBackButton.setOnClickListener( (v) -> finish());
    }
}