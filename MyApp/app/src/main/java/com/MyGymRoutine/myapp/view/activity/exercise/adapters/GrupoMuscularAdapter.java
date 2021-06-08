package com.MyGymRoutine.myapp.view.activity.exercise.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.GrupoMuscular;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GrupoMuscularAdapter extends RecyclerView.Adapter<GrupoMuscularAdapter.RecyclerHolder> {

    private Context context;
    private List<GrupoMuscular> gruposMusculares;

    public GrupoMuscularAdapter(Context context, List<GrupoMuscular> gruposMusculares) {
        this.context = context;
        this.gruposMusculares = gruposMusculares;
    }

    @NotNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.grupo_muscular_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {

        holder.tvTituloGrupoMuscular.setText(gruposMusculares.get(position).getNombre());
        setEjercicioItemRecycler(holder.itemRecycler,gruposMusculares.get(position).getEjercicios());

    }

    @Override
    public int getItemCount() {
        return gruposMusculares.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tvTituloGrupoMuscular;
        private RecyclerView itemRecycler;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvTituloGrupoMuscular = itemView.findViewById(R.id.tvTituloGrupoMuscular);
            itemRecycler = itemView.findViewById(R.id.rvGrupoMuscularEjercicios);
        }
    }
    private void setEjercicioItemRecycler(RecyclerView recycler, List<Ejercicio> list){
        EjercicioAdapter ejercicioAdapter = new EjercicioAdapter(context,list);
        recycler.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        recycler.setAdapter(ejercicioAdapter);
    }
    }


