package com.katsuki.administracintributariav2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.katsuki.administracintributariav2.R;
import com.katsuki.administracintributariav2.model.Solicitud;
import android.widget.ImageView;
import android.widget.TextView;

public class SolicitudAdapter extends FirestoreRecyclerAdapter<Solicitud, SolicitudAdapter.ViewHolder> {

  public SolicitudAdapter(@NonNull FirestoreRecyclerOptions<Solicitud> options) {
    super(options);
  }

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Solicitud solicitud) {
    String solicitudId = getSnapshots().getSnapshot(position).getId();

    holder.tviewDNI.setText(solicitud.getDni());
    holder.tviewNombres.setText(solicitud.getNombre());
    holder.tviewApellidos.setText(solicitud.getApellidos());
    holder.tviewTelefono.setText(solicitud.getTelefono());
    holder.tviewCorreo.setText(solicitud.getCorreo());
    holder.tviewDireccion.setText(solicitud.getDireccion());
    holder.tviewDatoContribuyente.setText(solicitud.getDatoContribuyente());
    holder.tviewReferenciaLlegada.setText(solicitud.getReferencia());

    // Llamar a la función que cargará las imágenes de Firebase Storage
    cargarImagenes(solicitudId, holder);
  }

  private void cargarImagenes(String solicitudId, ViewHolder holder) {
    // Referencia al storage
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();

    // Cargar la imagen 1
    StorageReference imgRef1 = storageReference.child("imagenes/" + solicitudId + "/imagen1.jpg");
    imgRef1.getDownloadUrl().addOnSuccessListener(uri -> {
      // Usar Glide para cargar la imagen desde la URL
      Glide.with(holder.itemView.getContext())
              .load(uri)
              .placeholder(R.drawable.placeholder_image)
              .into(holder.imgDomi1);
    });

    // Cargar la imagen 2
    StorageReference imgRef2 = storageReference.child("imagenes/" + solicitudId + "/imagen2.jpg");
    imgRef2.getDownloadUrl().addOnSuccessListener(uri -> {
      Glide.with(holder.itemView.getContext())
              .load(uri)
              .placeholder(R.drawable.placeholder_image)
              .into(holder.imgDomi2);
    });

    // Cargar la imagen del plano/croquis
    StorageReference imgPlanoRef = storageReference.child("imagenes/" + solicitudId + "/plano.jpg");
    imgPlanoRef.getDownloadUrl().addOnSuccessListener(uri -> {
      Glide.with(holder.itemView.getContext())
              .load(uri)
              .placeholder(R.drawable.placeholder_image)
              .into(holder.imgPlano);
    });
  }


  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_solicitud_single, parent, false);
    return new ViewHolder(view);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView tviewDNI, tviewNombres, tviewApellidos, tviewTelefono, tviewCorreo, tviewDireccion, tviewDatoContribuyente, tviewReferenciaLlegada;
    ImageView imgDomi1, imgDomi2, imgPlano;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      tviewDNI = itemView.findViewById(R.id.tviewDNI);
      tviewNombres = itemView.findViewById(R.id.tviewNombres);
      tviewApellidos = itemView.findViewById(R.id.tviewApellidos);
      tviewTelefono = itemView.findViewById(R.id.tviewTelefono);
      tviewCorreo = itemView.findViewById(R.id.tviewCorreo);
      tviewDireccion = itemView.findViewById(R.id.tviewDireccion);
      tviewDatoContribuyente = itemView.findViewById(R.id.tviewDatoContribuyente);
      tviewReferenciaLlegada = itemView.findViewById(R.id.tviewReferenciaLlegada);
      imgDomi1 = itemView.findViewById(R.id.imgDomi1);
      imgDomi2 = itemView.findViewById(R.id.imgDomi2);
      imgPlano = itemView.findViewById(R.id.imgPlano);
    }
  }
}
