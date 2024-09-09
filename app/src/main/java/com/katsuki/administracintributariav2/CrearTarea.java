package com.katsuki.administracintributariav2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearTarea extends AppCompatActivity {

  Button agregarTarea;
  EditText nombreTarea, ubicacionTarea, descripcionTarea;
  private FirebaseFirestore mfirestore;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_crear_tarea);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    this.setTitle("Crear tarea");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    mfirestore = FirebaseFirestore.getInstance();
    nombreTarea = findViewById(R.id.txtTarea);
    ubicacionTarea = findViewById(R.id.txtUbicacion);
    descripcionTarea = findViewById(R.id.txtDescripcion);
    agregarTarea = findViewById(R.id.btnAgregarTarea);

    agregarTarea.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String nameTarea = nombreTarea.getText().toString().trim();
        String placeTarea = ubicacionTarea.getText().toString().trim();
        String descripTarea = descripcionTarea.getText().toString().toString();

        if (nameTarea.isEmpty() || placeTarea.isEmpty() || descripTarea.isEmpty()){
          Toast.makeText(CrearTarea.this, "Debes llenar todos los datos", Toast.LENGTH_SHORT).show();
        }else {
          postTask(nameTarea,placeTarea,descripTarea);
        }
      }
    });
  }

  // ESTE METODO SIRVE PARA QUE PUEDA PUBLICAR LAS TAREAS EN LA BASE DE DATOS
  // RECORDAR QUE EN LA BASE DE DATOS SE DEBE PONER EL true EN LAS REGLAS DE LA BD
  private void postTask(String nameTarea, String placeTarea, String descripTarea) {
    Map<String, Object> map = new HashMap<>();
    map.put("tarea", nameTarea);
    map.put("ubicacion", placeTarea);
    map.put("descripcion", descripTarea);

    mfirestore.collection("task").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
      @Override
      public void onSuccess(DocumentReference documentReference) {
        Toast.makeText(CrearTarea.this, "Tarea agregada con exito", Toast.LENGTH_SHORT).show();
        finish();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(CrearTarea.this, "Error al agregar la tarea", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return false;
  }
}