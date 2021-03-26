package com.MyGymRoutine.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);
        getSupportActionBar().hide();

        tvCreateAccount = findViewById(R.id.tvCreateAccount);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCreateAccount:

                break;
        }
    }
}