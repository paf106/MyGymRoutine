package com.MyGymRoutine.myapp.view.activity.exercise.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.model.Ejercicio;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.RecyclerHolder> {

    private Context context;
    private List<Ejercicio> ejercicios;

    public EjercicioAdapter(Context context, List<Ejercicio> ejercicios) {
        this.context = context;
        this.ejercicios = ejercicios;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.exercise_element,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {

        holder.tvTituloEjercicio.setText(ejercicios.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView tvTituloEjercicio;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            tvTituloEjercicio = itemView.findViewById(R.id.tvTituloEjercicio);
        }
    }
}
