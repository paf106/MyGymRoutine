package com.MyGymRoutine.myapp.view.activity.routine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.GrupoMuscular;
import com.MyGymRoutine.myapp.data.model.GrupoRutina;
import com.MyGymRoutine.myapp.data.model.Rutina;
import com.MyGymRoutine.myapp.databinding.FragmentRoutineBinding;
import com.MyGymRoutine.myapp.view.activity.exercise.adapters.GrupoMuscularAdapter;
import com.MyGymRoutine.myapp.view.activity.routine.adapters.GrupoRutinaAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class RoutineFragment extends Fragment {

    private FragmentRoutineBinding binding;


    public RoutineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRoutineBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<GrupoRutina> grupoRutinas = new ArrayList<>();

        Ejercicio e = new Ejercicio("Cosa rara","10",2,"Descripcion","5 segundos","");
        Ejercicio e2 = new Ejercicio("Cosa rara","10",2,"Descripcion","5 segundos","");
        Ejercicio e3 = new Ejercicio("Cosa rara","10",2,"Descripcion","5 segundos","");
        Ejercicio e4 = new Ejercicio("Cosa rara","10",2,"Descripcion","5 segundos","");
        Ejercicio e5 = new Ejercicio("Cosa rara","10",2,"Descripcion","5 segundos","");
        Ejercicio e6 = new Ejercicio("Cosa rara","10",2,"Descripcion","5 segundos","");

        List<Ejercicio> ejercicios1 = new ArrayList<>();
        List<Ejercicio> ejercicios2 = new ArrayList<>();
        ejercicios1.add(e);
        ejercicios1.add(e2);
        ejercicios1.add(e3);
        ejercicios2.add(e4);
        ejercicios2.add(e5);
        ejercicios2.add(e6);

        List<Rutina> lista1 = new ArrayList<>();
        lista1.add(new Rutina("Perder peso",ejercicios1));

        List<Rutina> lista2 = new ArrayList<>();
        lista2.add(new Rutina("Perder peso",ejercicios2));

        grupoRutinas.add( new GrupoRutina("Perder peso",lista1));
        grupoRutinas.add( new GrupoRutina("Ganar masa",lista2));

        setMainCategoryRecycler(grupoRutinas);

    }
    private void setMainCategoryRecycler(List<GrupoRutina> list){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvRutinasPredeterminadas.setLayoutManager(layoutManager);
        GrupoRutinaAdapter grupoRutinaAdapter = new GrupoRutinaAdapter(getContext(), list);
        binding.rvRutinasPredeterminadas.setAdapter(grupoRutinaAdapter);

    }
}