package com.MyGymRoutine.myapp.view.activity.exercise.adapters;

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
import com.MyGymRoutine.myapp.data.api.internal.EjercicioApi;
import com.MyGymRoutine.myapp.data.api.internal.RoutineApi;
import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.Imagen;
import com.MyGymRoutine.myapp.view.activity.exercise.DetailExerciseActivity;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;
import com.MyGymRoutine.myapp.view.components.utils.FileUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(context, DetailExerciseActivity.class);
            intent.putExtra("ejercicioDetail",ejercicios.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EjercicioApi service = retrofit.create(EjercicioApi.class);
        service.getImagenEjercicio(ejercicios.get(position).getIdEjercicio()).enqueue(new Callback<Imagen>() {
            @Override
            public void onResponse(Call<Imagen> call, Response<Imagen> response) {
                // Asignar imagen al ejercicio para posteriormente el onclick
                ejercicios.get(position).setImagen(response.body());
                holder.ivExerciseCard.setImageBitmap(FileUtils.ByteArrayToBitmap(ejercicios.get(position).getImagen().getData()));
            }

            @Override
            public void onFailure(Call<Imagen> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
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
