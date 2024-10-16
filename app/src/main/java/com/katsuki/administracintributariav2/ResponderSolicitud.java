package com.katsuki.administracintributariav2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResponderSolicitud extends DialogFragment {
  Button responder, agregarDocumento;
  TextView verdni, verapellidos, vernombre ,verrespuesta, verobservacion;
  ImageView documento;

  private FirebaseFirestore mfirestore;
  FirebaseAuth mAuth;

  // VARIABLES PARA ALMACENAR LA RESPUESTA

  private String solicitudId = null;
  private String dni = "", nombre = "", apellidos = "";

  // METODO PARA CREAR UNA INSTANCIA CON PARAMETROS
  public static ResponderSolicitud newInstance(String solicitudId, String dni, String nombre, String apellidos){
    ResponderSolicitud fragment = new ResponderSolicitud();
    Bundle args = new Bundle();
    args.putString("solicitudId", solicitudId);
    args.putString("dni", dni);
    args.putString("nombre", nombre);
    args.putString("apellidos", apellidos);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      solicitudId = getArguments().getString("solicitudId");
      dni = getArguments().getString("dni");
      nombre = getArguments().getString("nombre");
      apellidos = getArguments().getString("apellidos");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_responder_solicitud, container, false);

    mfirestore = FirebaseFirestore.getInstance();
    verdni = v.findViewById(R.id.txtDni);
    vernombre = v.findViewById(R.id.txtNombre);
    verapellidos = v.findViewById(R.id.txtApellidos);
    agregarDocumento = v.findViewById(R.id.btnAgregarDocumento);
    responder = v.findViewById(R.id.btnEnviarRespuesta);

    if (solicitudId != null){
      // AUTOCOMPLETAR LOS TEXTVIEW
      verdni.setText(dni);
      vernombre.setText(nombre);
      verapellidos.setText(apellidos);
    }

    responder.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getContext(), "Estas respondiendo", Toast.LENGTH_SHORT).show();
      }
    });

    return v;
  }
}
