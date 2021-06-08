package com.MyGymRoutine.myapp.view.activity.routine.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.model.GrupoRutina;
import com.MyGymRoutine.myapp.data.model.Rutina;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GrupoRutinaAdapter extends RecyclerView.Adapter<GrupoRutinaAdapter.RecyclerHolder>{

    Context context;
    List<GrupoRutina> gruposRutinas;

    public GrupoRutinaAdapter(Context context, List<GrupoRutina> gruposRutinas) {
        this.context = context;
        this.gruposRutinas = gruposRutinas;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.grupo_muscular_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {

        holder.tvTituloGrupoMuscular.setText(gruposRutinas.get(position).getNombre());
        setRutinaItemRecycler(holder.itemRecycler,gruposRutinas.get(position).getRutinas());
    }

    @Override
    public int getItemCount() {
        return gruposRutinas.size();
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
    private void setRutinaItemRecycler(RecyclerView recycler, List<Rutina> list){
        RutinaAdapter ejercicioAdapter = new RutinaAdapter(context,list);
        recycler.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        recycler.setAdapter(ejercicioAdapter);
    }

}
