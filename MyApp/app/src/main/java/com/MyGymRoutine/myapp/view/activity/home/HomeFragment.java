package com.MyGymRoutine.myapp.view.activity.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.MyGymRoutine.myapp.data.api.internal.NovedadApi;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.data.model.Novedad;
import com.MyGymRoutine.myapp.data.repository.Api;
import com.MyGymRoutine.myapp.databinding.FragmentHomeBinding;
import com.MyGymRoutine.myapp.view.activity.home.adapters.NovedadesAdapter;

import com.MyGymRoutine.myapp.view.components.utils.Preferences;
import com.google.android.material.snackbar.Snackbar;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Preferences preferences = new Preferences(getContext());
        Client sharedCLient = preferences.getClient();
        binding.tvWelcomeUser.setText("¡Hola " + sharedCLient.getNombre() + "!");

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.rvNovedades.setLayoutManager(manager);
        binding.rvNovedades.setHasFixedSize(true);

        NovedadApi service = Api.getClient().create(NovedadApi.class);

        Call<List<Novedad>> listado = service.getNovedades();

        listado.enqueue(new Callback<List<Novedad>>() {
            @Override
            public void onResponse(Call<List<Novedad>> call, Response<List<Novedad>> response) {
                if (response.isSuccessful()) {
                    // Se asigna el array que devuelve la consulta al adaptador
                    NovedadesAdapter adapter = new NovedadesAdapter(response.body());
                    binding.rvNovedades.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Novedad>> call, Throwable t) {
                Snackbar.make(getView(), "Comprueba la conexión", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}