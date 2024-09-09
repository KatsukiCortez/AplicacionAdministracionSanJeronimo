package com.katsuki.administracintributariav2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.katsuki.administracintributariav2.R;
import com.katsuki.administracintributariav2.model.Task;

public class TaskAdapter extends FirestoreRecyclerAdapter<Task, TaskAdapter.ViewHolder> {

  public TaskAdapter(@NonNull FirestoreRecyclerOptions<Task> options) {
    super(options);
  }

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Task Task) {
    holder.tarea.setText(Task.getTarea());
    holder.ubicacion.setText(Task.getUbicacion());
    holder.descripcion.setText(Task.getDescripcion());
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_task_single, parent, false);
    return new ViewHolder(v);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tarea, ubicacion, descripcion;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      tarea = itemView.findViewById(R.id.tviewTarea);
      ubicacion = itemView.findViewById(R.id.tviewUbicacion);
      descripcion = itemView.findViewById(R.id.tviewDescripcion);
    }
  }
}
