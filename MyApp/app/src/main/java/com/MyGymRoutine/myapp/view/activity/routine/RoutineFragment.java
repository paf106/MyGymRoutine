package com.MyGymRoutine.myapp.view.activity.routine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.api.internal.RoutineApi;

import com.MyGymRoutine.myapp.data.model.GrupoRutina;
import com.MyGymRoutine.myapp.data.model.Rutina;

import com.MyGymRoutine.myapp.data.repository.Api;
import com.MyGymRoutine.myapp.databinding.FragmentRoutineBinding;
import com.MyGymRoutine.myapp.view.activity.routine.adapters.GrupoRutinaAdapter;
import com.MyGymRoutine.myapp.view.activity.routine.adapters.RutinaAdapter;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;
import com.MyGymRoutine.myapp.view.components.utils.Preferences;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RoutineFragment extends Fragment {

    private FragmentRoutineBinding binding;
    private Preferences preferences;

    private List<String> gruposRutinaString;
    private List<Rutina> rutinas;
    private List<GrupoRutina> gruposRutina;

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
        gruposRutinaString = new ArrayList<>();
        rutinas = new ArrayList<>();
        gruposRutina = new ArrayList<>();
        preferences = new Preferences(getContext());

        pedirRutinas();

        binding.srlRutinas.setOnRefreshListener(this::pedirRutinas);

    }

    private void pedirRutinas() {
        List<GrupoRutina> falso = new ArrayList<>();
        List<String> falsostring = new ArrayList<>();
        gruposRutina = falso;
        gruposRutinaString = falsostring;
        RoutineApi service = Api.getClient().create(RoutineApi.class);

        service.getRutinasCliente(preferences.getClient().getIdCliente()).enqueue(new Callback<List<Rutina>>() {
            @Override
            public void onResponse(Call<List<Rutina>> call, Response<List<Rutina>> response) {
                if (response.body() != null) {
                    binding.llMisRutinas.setBackgroundResource(R.drawable.background_mis_rutinas);
                    setRutinaRecycler(response.body());
                } else {
                    // Informar de que no tiene rutinas todavía
                    binding.llMisRutinas.setBackgroundResource(R.drawable.background_sin_rutinas);
                    List<Rutina> arrayFalso = new ArrayList<>();
                    setRutinaRecycler(arrayFalso);
                }
            }

            @Override
            public void onFailure(Call<List<Rutina>> call, Throwable t) {

            }
        });

        Call<List<String>> tipoRutinas = service.getTiposRutinas();
        tipoRutinas.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                gruposRutinaString = null;
                gruposRutinaString = response.body();

                Call<List<Rutina>> rutinasArray = service.getRutinas();

                rutinasArray.enqueue(new Callback<List<Rutina>>() {
                    @Override
                    public void onResponse(Call<List<Rutina>> call, Response<List<Rutina>> response) {
                        rutinas = null;
                        rutinas = response.body();
                        sortArray();
                        setMainCategoryRecycler(gruposRutina);
                    }

                    @Override
                    public void onFailure(Call<List<Rutina>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
        binding.srlRutinas.setRefreshing(false);
    }

    private void setMainCategoryRecycler(List<GrupoRutina> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvRutinasPredeterminadas.setLayoutManager(layoutManager);
        GrupoRutinaAdapter grupoRutinaAdapter = new GrupoRutinaAdapter(getContext(), list);
        binding.rvRutinasPredeterminadas.setAdapter(grupoRutinaAdapter);
    }

    private void setRutinaRecycler(List<Rutina> list) {
        binding.rvMisRutinas.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        RutinaAdapter rutinaAdapter = new RutinaAdapter(getContext(), list);
        binding.rvMisRutinas.setAdapter(rutinaAdapter);
    }

    private void sortArray() {

        for (String s : gruposRutinaString) {
            List<Rutina> rutinasTemp = new ArrayList<>();
            GrupoRutina grupoRutinaTemp = new GrupoRutina();
            grupoRutinaTemp.setNombre(s);

            for (Rutina r : rutinas) {
                if (r.getTipoRutina().equals(s)) {
                    rutinasTemp.add(r);
                }
            }
            grupoRutinaTemp.setRutinas(rutinasTemp);
            gruposRutina.add(grupoRutinaTemp);
        }
    }
}