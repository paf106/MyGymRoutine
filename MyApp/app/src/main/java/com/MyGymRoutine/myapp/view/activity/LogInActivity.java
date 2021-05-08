package com.MyGymRoutine.myapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.MyGymRoutine.myapp.databinding.LogInActivityBinding;

public class LogInActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogInActivityBinding binding = LogInActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvCreateAccount.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),Registro.class));
        });
    }
}