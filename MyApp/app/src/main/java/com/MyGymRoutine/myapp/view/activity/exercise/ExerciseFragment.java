package com.MyGymRoutine.myapp.view.activity.exercise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.api.internal.EjercicioApi;
import com.MyGymRoutine.myapp.data.api.internal.NovedadApi;
import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.GrupoMuscular;
import com.MyGymRoutine.myapp.data.model.Novedad;
import com.MyGymRoutine.myapp.databinding.FragmentExerciseBinding;
import com.MyGymRoutine.myapp.view.components.common.EjerciciosAdapter;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;

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
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EjercicioApi service = retrofit.create(EjercicioApi.class);

        Call<List<String>> listado = service.getGruposMusculares();
        listado.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                gruposMuscularesString = response.body();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

       /* Call<List<Ejercicio>> listado2 = service.getEjercicios();

        listado2.enqueue(new Callback<List<Ejercicio>>() {
            @Override
            public void onResponse(Call<List<Ejercicio>> call, Response<List<Ejercicio>> response) {
                ejercicios = response.body();
            }

            @Override
            public void onFailure(Call<List<Ejercicio>> call, Throwable t) {

            }
        });*/


        sortArrays();
        /*if (gruposMusculares != null){
            for (GrupoMuscular a: gruposMusculares) {
                Toast.makeText(getContext(), a.getNombre(), Toast.LENGTH_SHORT).show();
            }
        }*/
        EjerciciosAdapter ejerciciosAdapter = new EjerciciosAdapter(gruposMusculares);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.rvEjercicios.setLayoutManager(manager);
        binding.rvEjercicios.setAdapter(ejerciciosAdapter);
    }

    private void sortArrays() {
        List<Ejercicio> ejerciciosTemp = new ArrayList<>();

        if (gruposMusculares != null && gruposMuscularesString != null){
            for (String s: gruposMuscularesString) {
                GrupoMuscular grupoMuscularTemp = new GrupoMuscular();
                grupoMuscularTemp.setNombre(s);

                for (Ejercicio e:ejercicios) {
                    if (e.getGrupoMuscular().equals(s)){
                        ejerciciosTemp.add(e);
                    }
                }
                grupoMuscularTemp.setEjercicios(ejerciciosTemp);
                gruposMusculares.add(grupoMuscularTemp);
            }
        }

    }
}