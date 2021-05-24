package com.MyGymRoutine.myapp.view.activity.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.databinding.FragmentProfileBinding;
import com.MyGymRoutine.myapp.view.activity.login.LogInActivity;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Preferences preferences;
    private Client sharedCLient;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = new Preferences(getContext());
        sharedCLient = preferences.getClient();
        preferences.refreshCurrentUser(sharedCLient.getIdCliente());

        binding.tvFullnameProfile.setText(sharedCLient.getNombre());
        binding.tvEmailProfile.setText(sharedCLient.getCorreoElectronico());

        binding.personalButton.setOnClickListener(v -> startActivity(new Intent(getContext(),ProfileDataActivity.class)));
        binding.passwordButton.setOnClickListener(v -> startActivity(new Intent(getContext(),ModifyPasswordActivity.class)));
        binding.logoutButton.setOnClickListener(v -> {
            preferences.forgetCredentials();
            getActivity().finish();
            startActivity(new Intent(getContext(), LogInActivity.class));
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        preferences.refreshCurrentUser(sharedCLient.getIdCliente());
        sharedCLient = preferences.getClient();

        binding.tvFullnameProfile.setText(sharedCLient.getNombre());
        binding.tvEmailProfile.setText(sharedCLient.getCorreoElectronico());
    }
}