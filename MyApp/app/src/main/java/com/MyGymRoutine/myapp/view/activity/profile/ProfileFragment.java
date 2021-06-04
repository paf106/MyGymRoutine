package com.MyGymRoutine.myapp.view.activity.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.api.internal.FileApi;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.databinding.FragmentProfileBinding;
import com.MyGymRoutine.myapp.view.activity.login.LogInActivity;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;
import com.MyGymRoutine.myapp.view.components.utils.FileUtils;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URISyntaxException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Preferences preferences;
    private Client sharedCLient;
    private String photoName;
    private String photoPath;

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

        // Poner imagen de perfil
        Glide.with(this)
                .load(sharedCLient.getImagenRuta())
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_account_circle_24)
                .circleCrop()
                .into(binding.ivPhotoProfile);

        binding.personalButton.setOnClickListener(v -> startActivity(new Intent(getContext(), ProfileDataActivity.class)));
        binding.passwordButton.setOnClickListener(v -> startActivity(new Intent(getContext(), ModifyPasswordActivity.class)));
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

        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Snackbar.make(getView(), "Tienes que habilitar los permisos", Snackbar.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_GALLERY) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                // Snackbar.make(getView(), data.getDataString(), Snackbar.LENGTH_LONG).show();
                Uri photo = data.getData();

                Uri filePathFromActivity = (Uri) photo.get(Intent.EXTRA_STREAM);
                filePathFromActivity = Uri.parse(getRealPathFromURI( filePathFromActivity));
                File imageFile = new File(filePathFromActivity.getPath());
                //Log.i(TAG, getRealPathFromURI(photo));
                //uploadImage(photo);
                //String nombreArchivo = getName(photo);
                //String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
               // Log.i(TAG, "extension: " + extension);

                //    Toast.makeText(getContext(), FileUtils.getPath(getContext(), photo), Toast.LENGTH_SHORT).show();

                //photoName = getName(getContext(),photo);
                //photoPath = getPath(getContext(),photo);
                //Snackbar.make(getView(), photoPath, Snackbar.LENGTH_LONG).show();

                binding.ivPhotoProfile.setImageURI(photo);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContext().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    private void openGallery() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i, REQUEST_IMAGE_GALLERY);
    }

    public String getName(Uri uri) {
        String displayName = "";

        // https://developer.android.com/training/data-storage/shared/documents-files?hl=es-419

        Cursor cursor = getActivity().getContentResolver()
                .query(uri, null, null, null, null, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Log.i(TAG, "Display Name: " + displayName);

            }
        } finally {
            cursor.close();
        }
        return displayName;
    }

    private void uploadImage(Uri uri) {
        String nombreArchivo = getName(uri);
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FileApi service = retrofit.create(FileApi.class);
        File file = new File(FileUtils.getPath(getContext(), uri));

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", sharedCLient.getIdCliente()+extension, requestFile);

        service.uploadImage(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "Subido");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "Error con la imagen");
            }
        });
        //Toast.makeText(getContext(), file.getPath(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUser();
    }

    private void refreshUser() {
        preferences.refreshCurrentUser(sharedCLient.getIdCliente());
        sharedCLient = preferences.getClient();

        binding.tvFullnameProfile.setText(sharedCLient.getNombre());
        binding.tvEmailProfile.setText(sharedCLient.getCorreoElectronico());

        Glide.with(this)
                .load(sharedCLient.getImagenRuta())
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_account_circle_24)
                .circleCrop()
                .into(binding.ivPhotoProfile);
    }
}