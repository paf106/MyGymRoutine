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
import com.MyGymRoutine.myapp.data.model.DiaRutina;
import com.MyGymRoutine.myapp.data.model.GrupoDiaRutina;
import com.MyGymRoutine.myapp.data.model.Rutina;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GrupoDiaRutinaAdapter extends RecyclerView.Adapter<GrupoDiaRutinaAdapter.RecyclerHolder> {

    private Context context;
    private List<GrupoDiaRutina> grupoDiaRutinaList;

    public GrupoDiaRutinaAdapter(Context context, List<GrupoDiaRutina> grupoDiaRutinaList) {
        this.context = context;
        this.grupoDiaRutinaList = grupoDiaRutinaList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new GrupoDiaRutinaAdapter.RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.grupo_muscular_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {
        holder.tvTituloGrupoMuscular.setText(grupoDiaRutinaList.get(position).getNombre());
        setDiaRutinaItemRecycler(holder.itemRecycler,grupoDiaRutinaList.get(position).getDiaRutinas());
    }

    @Override
    public int getItemCount() {
        return grupoDiaRutinaList.size();
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
    private void setDiaRutinaItemRecycler(RecyclerView recycler, List<DiaRutina> list){
        DiaRutinaAdapter diaRutinaAdapter = new DiaRutinaAdapter(context,list);
        recycler.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        recycler.setAdapter(diaRutinaAdapter);
    }
}
