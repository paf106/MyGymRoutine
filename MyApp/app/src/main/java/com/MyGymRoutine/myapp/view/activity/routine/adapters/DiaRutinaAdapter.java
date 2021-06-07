package com.MyGymRoutine.myapp.view.activity.routine.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.model.DiaRutina;
import com.MyGymRoutine.myapp.view.activity.exercise.DetailExerciseActivity;
import com.MyGymRoutine.myapp.view.activity.routine.DetailDiaRutinaActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DiaRutinaAdapter extends RecyclerView.Adapter<DiaRutinaAdapter.RecyclerHolder> {

    private Context context;
    private List<DiaRutina> diaRutinasList;

    public DiaRutinaAdapter(Context context, List<DiaRutina> diaRutinasList) {
        this.context = context;
        this.diaRutinasList = diaRutinasList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new DiaRutinaAdapter.RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.exercise_element,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {

        holder.tvTituloEjercicio.setText(diaRutinasList.get(position).getNombre());

        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(context, DetailDiaRutinaActivity.class);
            intent.putExtra("diaRutinaDetail",diaRutinasList.get(position));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return diaRutinasList.size();
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
