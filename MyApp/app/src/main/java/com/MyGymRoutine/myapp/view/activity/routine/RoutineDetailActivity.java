package com.MyGymRoutine.myapp.view.activity.routine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.MyGymRoutine.myapp.data.api.internal.RoutineApi;
import com.MyGymRoutine.myapp.data.model.DiaRutina;
import com.MyGymRoutine.myapp.data.model.GrupoDiaRutina;
import com.MyGymRoutine.myapp.data.model.GrupoRutina;
import com.MyGymRoutine.myapp.data.model.Rutina;
import com.MyGymRoutine.myapp.databinding.ActivityRoutineDetailBinding;
import com.MyGymRoutine.myapp.view.activity.routine.adapters.GrupoDiaRutinaAdapter;
import com.MyGymRoutine.myapp.view.activity.routine.adapters.GrupoRutinaAdapter;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoutineDetailActivity extends AppCompatActivity {

    private ActivityRoutineDetailBinding binding;
    private List<DiaRutina> diaRutinaList;
    private int diasRutina;
    private List<GrupoDiaRutina> grupoDiaRutinas;

    public RoutineDetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoutineDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        diaRutinaList = new ArrayList<>();
        grupoDiaRutinas = new ArrayList<>();
        diasRutina = 0;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RoutineApi service = retrofit.create(RoutineApi.class);

        // Rutina recuperado del adaptador RutinaAdapter
        Rutina rutinaTemp = (Rutina) getIntent().getExtras().getSerializable("rutinaDetail");

        service.getDias(rutinaTemp.getIdRutina()).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                diasRutina = response.body();
                service.getDiasYejercicios(rutinaTemp.getIdRutina()).enqueue(new Callback<List<DiaRutina>>() {
                    @Override
                    public void onResponse(Call<List<DiaRutina>> call, Response<List<DiaRutina>> response) {
                        diaRutinaList = response.body();
                        sortArray();
                        setMainCategoryRecycler(grupoDiaRutinas);
                    }

                    @Override
                    public void onFailure(Call<List<DiaRutina>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

        // Nombre de la rutina
        binding.commonHeader.commonHeaderTitleText.setText(rutinaTemp.getNombre());
        binding.commonHeader.commonHeaderBackButton.setOnClickListener(v -> finish());

        // Descripción de la rutina
        binding.tvDescripcionRutina.setText(rutinaTemp.getDescripcion());


    }
    private void setMainCategoryRecycler(List<GrupoDiaRutina> list) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.rvDetalleRutina.setLayoutManager(layoutManager);
        GrupoDiaRutinaAdapter grupoDiaRutinaAdapter = new GrupoDiaRutinaAdapter(getBaseContext(), list);
        binding.rvDetalleRutina.setAdapter(grupoDiaRutinaAdapter);

    }

    private void sortArray() {
        for (int i = 1; i <= diasRutina; i++) {
            List<DiaRutina> diaRutinasTemp = new ArrayList<>();
            GrupoDiaRutina grupoDiaRutina = new GrupoDiaRutina();
            grupoDiaRutina.setNombre("Día "+i);

            for (DiaRutina d : diaRutinaList) {
                if (d.getDia() == i) {
                    diaRutinasTemp.add(d);
                }
            }
            grupoDiaRutina.setDiaRutinas(diaRutinasTemp);
            grupoDiaRutinas.add(grupoDiaRutina);
        }

    }
}