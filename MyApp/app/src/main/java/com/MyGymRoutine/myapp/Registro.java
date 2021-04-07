package com.MyGymRoutine.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();

        // Primer fragment que se va a mostrar (Es el que pide los datos personales)
        PersonalData personalData = new PersonalData();
        getSupportFragmentManager().beginTransaction().add(R.id.llFragments,personalData).commit();


    }
}