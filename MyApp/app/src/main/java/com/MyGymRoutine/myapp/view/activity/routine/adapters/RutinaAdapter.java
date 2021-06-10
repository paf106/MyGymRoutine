package com.MyGymRoutine.myapp.view.activity.routine.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.data.api.internal.RoutineApi;
import com.MyGymRoutine.myapp.data.model.DiaRutina;
import com.MyGymRoutine.myapp.data.model.Ejercicio;
import com.MyGymRoutine.myapp.data.model.Imagen;
import com.MyGymRoutine.myapp.data.model.Rutina;
import com.MyGymRoutine.myapp.data.repository.Api;
import com.MyGymRoutine.myapp.view.activity.routine.RoutineDetailActivity;
import com.MyGymRoutine.myapp.view.components.utils.Constantes;
import com.MyGymRoutine.myapp.view.components.utils.FileUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.rutina_imagenes_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {

        holder.tvTituloEjercicio.setText(rutinas.get(position).getNombre());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RoutineDetailActivity.class);
            intent.putExtra("rutinaDetail", rutinas.get(position));
            holder.itemView.getContext().startActivity(intent);
        });

        RoutineApi service = Api.getClient().create(RoutineApi.class);
        service.getImagenesRutina(rutinas.get(position).getIdRutina()).enqueue(new Callback<List<Imagen>>() {
            @Override
            public void onResponse(Call<List<Imagen>> call, Response<List<Imagen>> response) {
                holder.ivExerciseCard1.setImageBitmap(FileUtils.ByteArrayToBitmap(response.body().get(0).getData()));
                holder.ivExerciseCard2.setImageBitmap(FileUtils.ByteArrayToBitmap(response.body().get(1).getData()));
                holder.ivExerciseCard3.setImageBitmap(FileUtils.ByteArrayToBitmap(response.body().get(2).getData()));
                holder.ivExerciseCard4.setImageBitmap(FileUtils.ByteArrayToBitmap(response.body().get(3).getData()));
            }

            @Override
            public void onFailure(Call<List<Imagen>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return rutinas.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView tvTituloEjercicio;
        ImageView ivExerciseCard1, ivExerciseCard2, ivExerciseCard3, ivExerciseCard4;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            tvTituloEjercicio = itemView.findViewById(R.id.tvTituloEjercicio);
            ivExerciseCard1 = itemView.findViewById(R.id.ivExerciseCard1);
            ivExerciseCard2 = itemView.findViewById(R.id.ivExerciseCard2);
            ivExerciseCard3 = itemView.findViewById(R.id.ivExerciseCard3);
            ivExerciseCard4 = itemView.findViewById(R.id.ivExerciseCard4);
        }
    }
}
