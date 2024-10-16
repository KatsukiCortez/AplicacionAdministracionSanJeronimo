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
import com.katsuki.administracintributariav2.model.Evidencia;

public class EvidenciaAdapter extends FirestoreRecyclerAdapter<Evidencia, EvidenciaAdapter.ViewHolder> {

  public EvidenciaAdapter(@NonNull FirestoreRecyclerOptions<Evidencia> options) {
    super(options);
  }

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Evidencia Evidencia) {
    holder.tarea.setText(Evidencia.getTarea());
    holder.ubicacion.setText(Evidencia.getUbicacion());
    holder.descripcion.setText(Evidencia.getDescripcion());
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_tarea_evidencia, parent, false);
    return new ViewHolder(v);
  }


  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView tarea, ubicacion, descripcion;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      tarea = itemView.findViewById(R.id.tviewTarea);
      ubicacion = itemView.findViewById(R.id.txtNombre);
      descripcion = itemView.findViewById(R.id.txtApellidos);
    }
  }
}
