package com.MyGymRoutine.myapp.view.activity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MyGymRoutine.myapp.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {
private ActivityAboutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.commonHeader.commonHeaderTitleText.setText("Acerca de");
        binding.commonHeader.commonHeaderBackButton.setOnClickListener(v -> finish());
    }
}