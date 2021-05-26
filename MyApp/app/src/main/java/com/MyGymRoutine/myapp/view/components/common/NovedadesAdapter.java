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
import com.MyGymRoutine.myapp.data.model.Novedad;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NovedadesAdapter extends RecyclerView.Adapter<NovedadesAdapter.RecyclerHolder> {
    private List<Novedad> listaNovedades;

    public NovedadesAdapter(List<Novedad> listaNovedades) {
        this.listaNovedades = listaNovedades;
    }

    @NotNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.novedad_element, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {

        Novedad novedad = listaNovedades.get(position);
        //holder.ivStatus.setImageResource(novedad.getImgResource());
        holder.tvHomeTitle.setText(novedad.getTitulo());
        holder.tvHomeDescription.setText(novedad.getDescripcion());

        switch(novedad.getTipo()){
            case Novedad.INFO:
                holder.ivStatus.setImageResource(R.drawable.ic_info);
                holder.llNovedadElement.setBackgroundResource(R.color.home_novedad_info_background);
                break;
            case Novedad.NEW:
                holder.ivStatus.setImageResource(R.drawable.ic_new);
                holder.llNovedadElement.setBackgroundResource(R.color.home_novedad_new_background);
                break;
            case Novedad.WARNING:
                holder.ivStatus.setImageResource(R.drawable.round_warning_24);
                holder.llNovedadElement.setBackgroundResource(R.color.home_novedad_warning_backgound);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listaNovedades.size();
    }

        /*
        }*/



    /*@NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(resource,parent,false);

        Novedad novedad = listaNovedades.get(position);

        TextView tvHomeTitle, tvHomeDescription;
        LinearLayout llNovedadElement;
        ImageView ivStatus;

        tvHomeTitle = v.findViewById(R.id.tvHomeTitle);
        tvHomeDescription = v.findViewById(R.id.tvHomeDescription);
        ivStatus = v.findViewById(R.id.ivStatus);
        llNovedadElement = v.findViewById(R.id.llNovedadElement);

        tvHomeTitle.setText(novedad.getTitulo());
        tvHomeDescription.setText(novedad.getDescripcion());

        switch(novedad.getTipo()){
            case "info":
                ivStatus.setImageResource(R.drawable.ic_info);
                llNovedadElement.setBackgroundResource(R.color.home_novedad_info_background);
                break;
            case "new":
                ivStatus.setImageResource(R.drawable.ic_new);
                llNovedadElement.setBackgroundResource(R.color.home_novedad_new_background);
                break;
            case "warning":
                ivStatus.setImageResource(R.drawable.round_warning_24);
                llNovedadElement.setBackgroundResource(R.color.home_novedad_warning_backgound);
                break;
        }
        return v;*/
    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView ivStatus;
        private TextView tvHomeTitle;
        private TextView tvHomeDescription;
        private LinearLayout llNovedadElement;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            ivStatus = itemView.findViewById(R.id.ivStatus);
            tvHomeTitle = itemView.findViewById(R.id.tvHomeTitle);
            tvHomeDescription = itemView.findViewById(R.id.tvHomeDescription);
            llNovedadElement = itemView.findViewById(R.id.llNovedadElement);
        }
    }
    }


