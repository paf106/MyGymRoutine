package com.MyGymRoutine.myapp.view.activity.exercise;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.GrupoMuscular;
import com.MyGymRoutine.myapp.databinding.FragmentExerciseBinding;
import com.MyGymRoutine.myapp.view.activity.exercise.adapters.GrupoMuscularAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

        List<Ejercicio> lista1 = new ArrayList<>();
        lista1.add(new Ejercicio("Mancuerna"));
        lista1.add(new Ejercicio("Cosa rara"));
        lista1.add(new Ejercicio("Otro ejercicio más"));

        List<Ejercicio> lista2 = new ArrayList<>();
        lista2.add(new Ejercicio("Peso muerto"));
        lista2.add(new Ejercicio("Curl biceps"));
        lista2.add(new Ejercicio("Abdominales oblcuos"));

        List<Ejercicio> lista3 = new ArrayList<>();
        lista3.add(new Ejercicio("Press de banca"));
        lista3.add(new Ejercicio("Pájaros"));
        lista3.add(new Ejercicio("Ejercicio++"));

        List<Ejercicio> lista4 = new ArrayList<>();
        lista4.add(new Ejercicio("Press de banca"));
        lista4.add(new Ejercicio("Pájaros"));
        lista4.add(new Ejercicio("Ejercicio++"));

        gruposMusculares.add( new GrupoMuscular("Brazaco",lista1));
        gruposMusculares.add( new GrupoMuscular("Abdominales",lista2));
        gruposMusculares.add( new GrupoMuscular("Pierna",lista3));
        gruposMusculares.add( new GrupoMuscular("Patadas",lista4));

        setMainCategoryRecycler(gruposMusculares);

        /*Retrofit retrofit = new Retrofit.Builder()
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
        });*/

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


        //sortArrays();
        /*if (gruposMusculares != null){
            for (GrupoMuscular a: gruposMusculares) {
                Toast.makeText(getContext(), a.getNombre(), Toast.LENGTH_SHORT).show();
            }
        }*/
       /* EjerciciosAdapter ejerciciosAdapter = new EjerciciosAdapter(gruposMusculares);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.rvEjercicios.setLayoutManager(manager);
        binding.rvEjercicios.setAdapter(ejerciciosAdapter);*/
    }
    private void setMainCategoryRecycler(List<GrupoMuscular> list){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvEjercicios.setLayoutManager(layoutManager);
        GrupoMuscularAdapter grupoMuscularAdapter = new GrupoMuscularAdapter(getContext(), list);
        binding.rvEjercicios.setAdapter(grupoMuscularAdapter);

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