package com.MyGymRoutine.myapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.databinding.ActivityNavigationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

public class NavigationActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    RoutineFragment routineFragment = new RoutineFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    SettingsFragment settingsFragment = new SettingsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNavigationBinding binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Default fragment
        loadFragment(homeFragment);

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homeFragment:
                    loadFragment(homeFragment);
                    return true;
                case R.id.routinesFragment:
                    loadFragment(routineFragment);
                    return true;
                case R.id.settingsFragment:
                    loadFragment(settingsFragment);
                    return true;
                case R.id.profileFragment:
                    loadFragment(profileFragment);
                    return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();
    }
}