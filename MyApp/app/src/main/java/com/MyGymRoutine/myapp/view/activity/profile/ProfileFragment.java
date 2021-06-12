package com.MyGymRoutine.myapp.view.activity.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import androidx.fragment.app.Fragment;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.api.internal.ClientApi;

import com.MyGymRoutine.myapp.data.model.Client;

import com.MyGymRoutine.myapp.data.repository.Api;
import com.MyGymRoutine.myapp.databinding.FragmentProfileBinding;
import com.MyGymRoutine.myapp.view.activity.login.LogInActivity;

import com.MyGymRoutine.myapp.view.components.utils.FileUtils;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Preferences preferences;
    private Client sharedClient;

    private static final int REQUEST_PERMISSION_CODE = 100;
    private static final int REQUEST_IMAGE_GALLERY = 101;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = new Preferences(getContext());
        sharedClient = preferences.getClient();
        preferences.refreshCurrentUser(sharedClient.getIdCliente());

        binding.tvFullnameProfile.setText(sharedClient.getNombre());
        binding.tvEmailProfile.setText(sharedClient.getCorreoElectronico());

        // Poner imagen de perfil
        if (preferences.getPhoto() != null) {
            Glide.with(this)
                    .load(preferences.getPhoto())
                    .placeholder(R.drawable.ic_user_default_profile)
                    .error(R.drawable.ic_user_default_profile)
                    .circleCrop()
                    .into(binding.ivPhotoProfile);
        }

        binding.personalButton.setOnClickListener(v -> startActivity(new Intent(getContext(), ProfileDataActivity.class)));
        binding.passwordButton.setOnClickListener(v -> startActivity(new Intent(getContext(), ModifyPasswordActivity.class)));
        binding.aboutButton.setOnClickListener(v -> startActivity(new Intent(getContext(), AboutActivity.class)));

        binding.logoutButton.setOnClickListener(v -> {
            preferences.forgetCredentials();
            getActivity().finish();
            startActivity(new Intent(getContext(), LogInActivity.class));
        });
        binding.ivPhotoProfile.setOnClickListener(v -> {
            //Check permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                }
            } else {
                openGallery();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Snackbar.make(getView(), "Tienes que habilitar los permisos", Snackbar.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_GALLERY) {
            if (resultCode == Activity.RESULT_OK && data != null) {

                Uri photo = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photo);
                    Glide.with(this)
                            .load(preferences.getPhoto())
                            .placeholder(R.drawable.ic_user_default_profile)
                            .error(R.drawable.ic_user_default_profile)
                            .circleCrop()
                            .into(binding.ivPhotoProfile);
                    preferences.savePhoto(bitmap);
                    //uploadImage(FileUtils.bitmapToByteArray(bitmap));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void openGallery() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i, REQUEST_IMAGE_GALLERY);
    }

    private void uploadImage(byte[] arrayBytes) {

        ClientApi service = Api.getClient().create(ClientApi.class);

        service.updateImagen(arrayBytes, sharedClient.getIdCliente()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Cascado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUser();
    }

    private void refreshUser() {
        preferences.refreshCurrentUser(sharedClient.getIdCliente());
        sharedClient = preferences.getClient();

        binding.tvFullnameProfile.setText(sharedClient.getNombre());
        binding.tvEmailProfile.setText(sharedClient.getCorreoElectronico());

        if (preferences.getPhoto() != null) {
            Glide.with(this)
                    .load(preferences.getPhoto())
                    .placeholder(R.drawable.ic_user_default_profile)
                    .error(R.drawable.ic_user_default_profile)
                    .circleCrop()
                    .into(binding.ivPhotoProfile);
        }
    }
}