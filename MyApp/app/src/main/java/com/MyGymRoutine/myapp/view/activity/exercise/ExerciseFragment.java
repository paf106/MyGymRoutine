package com.MyGymRoutine.myapp.view.activity.exercise;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.MyGymRoutine.myapp.view.components.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExerciseFragment extends Fragment {

    private FragmentExerciseBinding binding;
    private String[] gruposMuscularesString;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExerciseBinding.inflate(inflater, container, false);


        ejercicios = new ArrayList<>();
        gruposMusculares = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EjercicioApi service = retrofit.create(EjercicioApi.class);

        Call<String[]> listado = service.getGruposMusculares();
        listado.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                gruposMuscularesString = new String[2];
                gruposMuscularesString = response.body();
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {

            }
        });
        for (String a: gruposMuscularesString
             ) {
            Toast.makeText(getContext(), a, Toast.LENGTH_SHORT).show();
        }

        Call<List<Ejercicio>> listado2 = service.getEjercicios();

        listado2.enqueue(new Callback<List<Ejercicio>>() {
            @Override
            public void onResponse(Call<List<Ejercicio>> call, Response<List<Ejercicio>> response) {
                ejercicios = response.body();
            }

            @Override
            public void onFailure(Call<List<Ejercicio>> call, Throwable t) {

            }
        });

        sortArrays();

        return binding.getRoot();
    }

    private void sortArrays() {
        for (String grupoMuscular: gruposMuscularesString) {

            List<Ejercicio> ejerciciosTemp = new ArrayList<>();
            GrupoMuscular grupoMuscularTemp = new GrupoMuscular();
            grupoMuscularTemp.setNombre(grupoMuscular);
            grupoMuscularTemp.setEjercicios(ejerciciosTemp);

            for (Ejercicio e:ejercicios) {
                if (e.getGrupoMuscular().equals(grupoMuscular)){
                    ejerciciosTemp.add(e);
                }
            }
            gruposMusculares.add(grupoMuscularTemp);
        }
    }
}