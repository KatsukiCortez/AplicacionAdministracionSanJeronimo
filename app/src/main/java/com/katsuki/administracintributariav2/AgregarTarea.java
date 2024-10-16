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

  // Variables para almacenar los datos de la tarea
  private String taskId = null;  // ID de la tarea
  private String tarea = "", ubicacion = "", descripcion = "";

  // Método estático para crear una nueva instancia con parámetros
  public static AgregarTarea newInstance(String taskId, String tarea, String ubicacion, String descripcion) {
    AgregarTarea fragment = new AgregarTarea();
    Bundle args = new Bundle();
    args.putString("taskId", taskId);
    args.putString("tarea", tarea);
    args.putString("ubicacion", ubicacion);
    args.putString("descripcion", descripcion);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      taskId = getArguments().getString("taskId");
      tarea = getArguments().getString("tarea");
      ubicacion = getArguments().getString("ubicacion");
      descripcion = getArguments().getString("descripcion");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_agregar_tarea, container, false);

    mfirestore = FirebaseFirestore.getInstance();
    nombreTarea = v.findViewById(R.id.txtTarea);
    ubicacionTarea = v.findViewById(R.id.txtUbicacion);
    descripcionTarea = v.findViewById(R.id.txtDescripcion);
    agregarTarea = v.findViewById(R.id.btnAgregarTarea);

    // Si taskId no es nulo, estamos editando una tarea
    if (taskId != null) {
      nombreTarea.setText(tarea); // Autocompletar los campos
      ubicacionTarea.setText(ubicacion);
      descripcionTarea.setText(descripcion);
      agregarTarea.setText("Actualizar tarea"); // Cambiar el texto del botón
    }

    agregarTarea.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String estadoTarea = "";
        String nameTarea = nombreTarea.getText().toString();
        String placeTarea = ubicacionTarea.getText().toString();
        String descripTarea = descripcionTarea.getText().toString();

        if (nameTarea.isEmpty() || placeTarea.isEmpty() || descripTarea.isEmpty()) {
          Toast.makeText(getContext(), "Debes llenar todos los datos", Toast.LENGTH_SHORT).show();
        } else {
          if (taskId != null) {
            // Si hay un taskId, actualizamos la tarea
            updateTask(estadoTarea, nameTarea, placeTarea, descripTarea, taskId);
          } else {
            // Si no hay taskId, agregamos una nueva tarea
            postTask(estadoTarea, nameTarea, placeTarea, descripTarea);
          }
        }
      }
    });

    return v;
  }

  private void updateTask(String estadoTarea, String nameTarea, String placeTarea, String descripTarea, String id) {
    String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();

    Map<String, Object> map = new HashMap<>();
    map.put("userId", idUsuario);
    map.put("estado", estadoTarea);
    map.put("tarea", nameTarea);
    map.put("ubicacion", placeTarea);
    map.put("descripcion", descripTarea);

    mfirestore.collection("task").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
      @Override
      public void onSuccess(Void unused) {
        Toast.makeText(getContext(), "Tarea actualizada con éxito", Toast.LENGTH_SHORT).show();
        getDialog().dismiss();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getContext(), "ERROR! Tarea no actualizada", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void postTask(String estadoTarea, String nameTarea, String placeTarea, String descripTarea) {
    String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();

    Map<String, Object> map = new HashMap<>();
    map.put("userId", idUsuario);
    map.put("estado", estadoTarea);
    map.put("tarea", nameTarea);
    map.put("ubicacion", placeTarea);
    map.put("descripcion", descripTarea);

    mfirestore.collection("task").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
      @Override
      public void onSuccess(DocumentReference documentReference) {
        Toast.makeText(getContext(), "Tarea agregada con éxito", Toast.LENGTH_SHORT).show();
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
