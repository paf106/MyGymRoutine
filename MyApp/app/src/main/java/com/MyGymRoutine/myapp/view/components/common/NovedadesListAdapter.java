package com.MyGymRoutine.myapp.view.components.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.model.Novedad;

import java.util.List;

public class NovedadesListAdapter extends BaseAdapter {
    private List<Novedad> listaNovedades;
    private Context context;

    public NovedadesListAdapter(Context context, List<Novedad> novedadList){
        this.context = context;
        this.listaNovedades = novedadList;
    }

    @Override
    public int getCount() {
        return listaNovedades.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView tvHomeTitle, tvHomeDescription;
        LinearLayout llNovedadElement;
        ImageView ivStatus;

        Novedad novedad = listaNovedades.get(position);

        if (view == null) {
           view = LayoutInflater.from(context).inflate(R.layout.novedad_element,parent,false);
        }

        tvHomeTitle = view.findViewById(R.id.tvHomeTitle);
        tvHomeDescription = view.findViewById(R.id.tvHomeDescription);
        ivStatus = view.findViewById(R.id.ivStatus);
        llNovedadElement = view.findViewById(R.id.llNovedadElement);

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
        return view;
    }

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
    }

