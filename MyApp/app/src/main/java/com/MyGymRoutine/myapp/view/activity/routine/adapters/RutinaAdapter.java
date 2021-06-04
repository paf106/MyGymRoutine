package com.MyGymRoutine.myapp.view.activity.routine.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.model.Rutina;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RutinaAdapter extends RecyclerView.Adapter<RutinaAdapter.RecyclerHolder> {

    private Context context;
    private List<Rutina> rutinas;

    public RutinaAdapter(Context context, List<Rutina> rutinas) {
        this.context = context;
        this.rutinas = rutinas;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.exercise_element,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {

        holder.tvTituloEjercicio.setText(rutinas.get(position).getNombre());

       /* holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("ejercicioDetail",rutinas.get(position));
            holder.itemView.getContext().startActivity(intent);
        });*/
    }

    @Override
    public int getItemCount() {
        return rutinas.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView tvTituloEjercicio;
        ImageView ivExerciseCard;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            tvTituloEjercicio = itemView.findViewById(R.id.tvTituloEjercicio);
            ivExerciseCard = itemView.findViewById(R.id.ivExerciseCard);
        }
    }
}
