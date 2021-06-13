package com.MyGymRoutine.myapp.view.activity.exercise;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.MyGymRoutine.myapp.data.api.internal.EjercicioApi;
import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.GrupoMuscular;
import com.MyGymRoutine.myapp.data.repository.Api;
import com.MyGymRoutine.myapp.databinding.FragmentExerciseBinding;
import com.MyGymRoutine.myapp.view.activity.exercise.adapters.GrupoMuscularAdapter;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExerciseFragment extends Fragment {

    private FragmentExerciseBinding binding;
    private List<String> gruposMuscularesString;
    private List<Ejercicio> ejercicios;
    private List<GrupoMuscular> gruposMusculares;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gruposMusculares = new ArrayList<>();
        gruposMuscularesString = new ArrayList<>();
        ejercicios = new ArrayList<>();

        pedirEjercicios();

        binding.srlEjercicios.setOnRefreshListener(this::pedirEjercicios);
    }

    private void setMainCategoryRecycler(List<GrupoMuscular> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvEjercicios.setLayoutManager(layoutManager);
        GrupoMuscularAdapter grupoMuscularAdapter = new GrupoMuscularAdapter(getContext(), list);
        binding.rvEjercicios.setAdapter(grupoMuscularAdapter);
    }
    private void pedirEjercicios(){
        EjercicioApi service = Api.getClient().create(EjercicioApi.class);

        Call<List<String>> listado = service.getGruposMusculares();

        listado.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                gruposMuscularesString = response.body();

                Call<List<Ejercicio>> listado2 = service.getEjercicios();

                listado2.enqueue(new Callback<List<Ejercicio>>() {
                    @Override
                    public void onResponse(Call<List<Ejercicio>> call, Response<List<Ejercicio>> response) {
                        ejercicios = response.body();
                        sortArrays();
                        setMainCategoryRecycler(gruposMusculares);
                    }

                    @Override
                    public void onFailure(Call<List<Ejercicio>> call, Throwable t) {
                        Snackbar.make(getView(), "Comprueba la conexión", Snackbar.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Snackbar.make(getView(), "Comprueba la conexión", Snackbar.LENGTH_LONG).show();
            }
        });
        binding.srlEjercicios.setRefreshing(false);
    }

    private void sortArrays() {
        if (gruposMusculares != null && gruposMuscularesString != null) {
            for (String s : gruposMuscularesString) {
                List<Ejercicio> ejerciciosTemp = new ArrayList<>();
                GrupoMuscular grupoMuscularTemp = new GrupoMuscular();
                grupoMuscularTemp.setNombre(s);

                for (Ejercicio e : ejercicios) {
                    if (e.getGrupoMuscular().equals(s)) {
                        ejerciciosTemp.add(e);
                    }
                }
                grupoMuscularTemp.setEjercicios(ejerciciosTemp);
                gruposMusculares.add(grupoMuscularTemp);
            }
        }
    }
}