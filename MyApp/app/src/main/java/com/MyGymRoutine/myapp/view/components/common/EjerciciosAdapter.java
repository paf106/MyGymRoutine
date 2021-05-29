package com.MyGymRoutine.myapp.view.components.common;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.model.GrupoMuscular;
import com.MyGymRoutine.myapp.data.model.Novedad;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EjerciciosAdapter extends RecyclerView.Adapter<EjerciciosAdapter.RecyclerHolder> {
    private List<GrupoMuscular> gruposMusculares;

    public EjerciciosAdapter(List<GrupoMuscular> gruposMusculares) {
        this.gruposMusculares = gruposMusculares;
    }

    @NotNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grupo_muscular_element, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {

        GrupoMuscular grupoMuscular = gruposMusculares.get(position);
      //  holder.tvNombreEjercicio.setText(grupoMuscular.getEjercicios().get(position).getNombre());
       // holder.tvHomeDescription.setText(novedad.getDescripcion());

        holder.tvTituloGrupoMuscular.setText(grupoMuscular.getNombre());
        // poner el recycler view aqui

    }

    @Override
    public int getItemCount() {
        return gruposMusculares.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView ivGrupoMuscularImagen;
        private TextView tvNombreEjercicio, tvTituloGrupoMuscular;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreEjercicio = itemView.findViewById(R.id.tvNombreEjercicio);
            tvTituloGrupoMuscular = itemView.findViewById(R.id.tvTituloGrupoMuscular);
        }
    }
    }


