package com.MyGymRoutine.myapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.MyGymRoutine.myapp.R;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);
        Objects.requireNonNull(getSupportActionBar()).hide();

        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        tvCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCreateAccount:
                Intent i = new Intent(getApplicationContext(),Registro.class);
                startActivity(i);
                break;
        }
    }
}