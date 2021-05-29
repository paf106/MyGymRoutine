package com.MyGymRoutine.myapp.view.activity.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.databinding.FragmentProfileBinding;
import com.MyGymRoutine.myapp.view.activity.login.LogInActivity;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Preferences preferences;
    private Client sharedCLient;

    private static final int REQUEST_PERMISSION_CODE = 100;
    private static final int REQUEST_IMAGE_GALLERY = 101;


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
        binding.ivPhotoProfile.setOnClickListener(v -> {
            //Check permission
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    openGallery();
                }else{
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSION_CODE);
                }
            }else{
                openGallery();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION_CODE){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else{
                Snackbar.make(getView(), "Tienes que habilitar los permisos", Snackbar.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_GALLERY){
            if (resultCode == Activity.RESULT_OK && data != null){
                Uri photo = data.getData();
                binding.ivPhotoProfile.setImageURI(photo);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void openGallery(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i,REQUEST_IMAGE_GALLERY);
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