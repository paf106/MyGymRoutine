package com.MyGymRoutine.myapp.view.components.utils.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.model.Novedad;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Novedad> listaNovedades;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListAdapter(List<Novedad> listaNovedades, Context context) {
        this.listaNovedades = listaNovedades;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_view_element,null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
    holder.bindData(listaNovedades.get(position));
    }

    @Override
    public int getItemCount() { return listaNovedades.size(); }

    public void setItems(List<Novedad> items){ listaNovedades = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivStatusCard;
        TextView tvHomeTitle,tvHomeDescription;
        RelativeLayout rlCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivStatusCard = itemView.findViewById(R.id.ivStatusCard);
            tvHomeTitle = itemView.findViewById(R.id.tvHomeTitle);
            tvHomeDescription = itemView.findViewById(R.id.tvHomeDescription);
            rlCard = itemView.findViewById(R.id.rlCard);
        }
        void bindData(Novedad item){
            switch(item.getTipo()){
                case Novedad.INFO:
                    ivStatusCard.setImageResource(R.drawable.ic_baseline_info_24);
                    rlCard.setBackgroundResource(R.color.home_novedad_info_background);
                    break;
                case Novedad.NEW:
                    ivStatusCard.setImageResource(R.drawable.ic_resource_new);
                    rlCard.setBackgroundResource(R.color.home_novedad_new_background);
                    break;
                case Novedad.WARNING:
                    ivStatusCard.setImageResource(R.drawable.round_warning_24);
                    rlCard.setBackgroundResource(R.color.home_novedad_warning_backgound);
                    break;
            }
            tvHomeTitle.setText(item.getTitulo());
            tvHomeDescription.setText(item.getDescripcion());

        }
    }
}
