package com.MyGymRoutine.myapp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.view.activity.login.LogInActivity;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);

        Preferences preferences = new Preferences(getApplicationContext());

        if (preferences.hasCredentials()) {
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        }
        finish();
        super.onCreate(savedInstanceState);



    }
}