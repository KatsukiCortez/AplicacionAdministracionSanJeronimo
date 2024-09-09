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
import com.katsuki.administracintributariav2.model.Solicitud;

public class SolicitudAdapter extends FirestoreRecyclerAdapter<Solicitud, SolicitudAdapter.ViewHolder> {

  public SolicitudAdapter(@NonNull FirestoreRecyclerOptions<Solicitud> options) {
    super(options);
  }

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Solicitud solicitud) {
    holder.dni.setText(solicitud.getDni());
    holder.nombres.setText(solicitud.getNombres());
    holder.apellidos.setText(solicitud.getApellidos());
    holder.telefono.setText(solicitud.getTelefono());
    holder.correo.setText(solicitud.getCorreo());
    holder.direccion.setText(solicitud.getDireccion());
    holder.datoContribuyente.setText(solicitud.getDatoContribuyente());
    holder.referenciaLlegada.setText(solicitud.getReferenciaLlegada());
    // No mostramos las imágenes por ahora: imgDomi1, imgDomi2, imgCroquis
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_solicitud_single, parent, false);
    return new ViewHolder(v);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView dni, nombres, apellidos, telefono, correo, direccion, datoContribuyente, referenciaLlegada;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      dni = itemView.findViewById(R.id.tviewDNI);
      nombres = itemView.findViewById(R.id.tviewNombres);
      apellidos = itemView.findViewById(R.id.tviewApellidos);
      telefono = itemView.findViewById(R.id.tviewTelefono);
      correo = itemView.findViewById(R.id.tviewCorreo);
      direccion = itemView.findViewById(R.id.tviewDireccion);
      datoContribuyente = itemView.findViewById(R.id.tviewDatoContribuyente);
      referenciaLlegada = itemView.findViewById(R.id.tviewReferenciaLlegada);
    }
  }
}