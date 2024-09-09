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
import com.katsuki.administracintributariav2.model.Contribuyente;

public class ContribuyenteAdapter extends FirestoreRecyclerAdapter<Contribuyente, ContribuyenteAdapter.ViewHolder> {

  public ContribuyenteAdapter(@NonNull FirestoreRecyclerOptions<Contribuyente> options) {
    super(options);
  }

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Contribuyente contribuyente) {
    holder.actEconomica.setText(contribuyente.getActEconomica());
    holder.adeudados.setText(contribuyente.getAdeudados());
    holder.area.setText(contribuyente.getArea());
    holder.correo.setText(contribuyente.getCorreo());
    holder.direccion.setText(contribuyente.getDireccion());
    holder.documento.setText(contribuyente.getDocumento());
    holder.estCivil.setText(contribuyente.getEstCivil());
    holder.fiscalizacion.setText(contribuyente.getFiscalizacion());
    holder.historial.setText(contribuyente.getHistorial());
    holder.multa.setText(contribuyente.getMulta());
    holder.nombre.setText(contribuyente.getNombre());
    holder.pagos.setText(contribuyente.getPagos());
    holder.predios.setText(contribuyente.getPredios());
    holder.propiedad.setText(contribuyente.getPropiedad());
    holder.telefono.setText(contribuyente.getTelefono());
    holder.ubicacion.setText(contribuyente.getUbicacion());
    holder.uso.setText(contribuyente.getUso());
    holder.valor.setText(contribuyente.getValor());
    holder.vencimiento.setText(contribuyente.getVencimiento());
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_contribuyente_single, parent, false);
    return new ViewHolder(v);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView actEconomica, adeudados, area, correo, direccion, documento, estCivil, fiscalizacion, historial, multa, nombre, pagos, predios, propiedad, telefono, ubicacion, uso, valor, vencimiento;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      actEconomica = itemView.findViewById(R.id.tviewActEconomica);
      adeudados = itemView.findViewById(R.id.tviewAdeudados);
      area = itemView.findViewById(R.id.tviewArea);
      correo = itemView.findViewById(R.id.tviewCorreo);
      direccion = itemView.findViewById(R.id.tviewDireccion);
      documento = itemView.findViewById(R.id.tviewDocumento);
      estCivil = itemView.findViewById(R.id.tviewEstCivil);
      fiscalizacion = itemView.findViewById(R.id.tviewFiscalizacion);
      historial = itemView.findViewById(R.id.tviewHistorial);
      multa = itemView.findViewById(R.id.tviewMulta);
      nombre = itemView.findViewById(R.id.tviewNombre);
      pagos = itemView.findViewById(R.id.tviewPagos);
      predios = itemView.findViewById(R.id.tviewPredios);
      propiedad = itemView.findViewById(R.id.tviewPropiedad);
      telefono = itemView.findViewById(R.id.tviewTelefono);
      ubicacion = itemView.findViewById(R.id.tviewUbicacion);
      uso = itemView.findViewById(R.id.tviewUso);
      valor = itemView.findViewById(R.id.tviewValor);
      vencimiento = itemView.findViewById(R.id.tviewVencimiento);
    }
  }
}
