package com.MyGymRoutine.myapp.view.activity.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.api.internal.EjercicioApi;
import com.MyGymRoutine.myapp.data.api.internal.NovedadApi;
import com.MyGymRoutine.myapp.data.model.Client;
import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.Novedad;
import com.MyGymRoutine.myapp.databinding.FragmentHomeBinding;
import com.MyGymRoutine.myapp.view.components.common.NovedadesListAdapter;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Novedad> novedades;
    private Preferences preferences;

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
        preferences = new Preferences(getContext());
        Client sharedCLient = preferences.getClient();
        binding.tvWelcomeUser.setText("¡Hola "+ sharedCLient.getNombre() + "!");
        novedades = new ArrayList<>();
        NovedadesListAdapter adapter = new NovedadesListAdapter(getContext(),novedades);
        binding.lvNovedades.setAdapter(adapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.20:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NovedadApi service = retrofit.create(NovedadApi.class);

        Call<List<Novedad>> listado = service.getNovedades();

        listado.enqueue(new Callback<List<Novedad>>() {
            @Override
            public void onResponse(Call<List<Novedad>> call, Response<List<Novedad>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    List<Novedad> list = response.body();
                    novedades = list;
                }
            }

            @Override
            public void onFailure(Call<List<Novedad>> call, Throwable t) {

            }
        });

    }
}