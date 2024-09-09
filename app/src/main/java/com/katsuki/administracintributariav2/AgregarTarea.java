package com.katsuki.administracintributariav2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

// ESTE ES UN DIALOG FRAGMENT
public class AgregarTarea extends DialogFragment {

  Button agregarTarea;
  EditText nombreTarea, ubicacionTarea, descripcionTarea;
  private FirebaseFirestore mfirestore;
  FirebaseAuth mAuth;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.fragment_agregar_tarea, container, false);
    mfirestore = FirebaseFirestore.getInstance();
    nombreTarea = v.findViewById(R.id.txtTarea);
    ubicacionTarea = v.findViewById(R.id.txtUbicacion);
    descripcionTarea = v.findViewById(R.id.txtDescripcion);
    agregarTarea = v.findViewById(R.id.btnAgregarTarea);


    agregarTarea.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String nameTarea = nombreTarea.getText().toString().trim();
        String placeTarea = ubicacionTarea.getText().toString().trim();
        String descripTarea = descripcionTarea.getText().toString().toString();

        if (nameTarea.isEmpty() || placeTarea.isEmpty() || descripTarea.isEmpty()){
          Toast.makeText(getContext(), "Debes llenar todos los datos", Toast.LENGTH_SHORT).show();
        }else {
          postTask(nameTarea,placeTarea,descripTarea);
        }
      }
    });

    return v;
  }

  // ESTE METODO SIRVE PARA QUE PUEDA PUBLICAR LAS TAREAS EN LA BASE DE DATOS
  // RECORDAR QUE EN LA BASE DE DATOS SE DEBE PONER EL true EN LAS REGLAS DE LA BD
  private void postTask(String nameTarea, String placeTarea, String descripTarea) {
    // VAMOS A OBTENER EL ID DEL USUARIO LOGEADO
    String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();

    Map<String, Object> map = new HashMap<>();
    map.put("userId", idUsuario);
    map.put("tarea", nameTarea);
    map.put("ubicacion", placeTarea);
    map.put("descripcion", descripTarea);

    mfirestore.collection("task").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
      @Override
      public void onSuccess(DocumentReference documentReference) {
        Toast.makeText(getContext(), "Tarea agregada con exito", Toast.LENGTH_SHORT).show();
        getDialog().dismiss();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getContext(), "Error al agregar la tarea", Toast.LENGTH_SHORT).show();
      }
    });
  }
}