package com.katsuki.administracintributariav2;

import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

  EditText nuevoCorreo, nuevoPass, nuevoNombre;
  Button registrar, volver;

  // LLAMAR A FIRESTORE Y AUTENTICATOR
  private FirebaseFirestore mfirestore;
  FirebaseAuth mAuth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_register);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    // APUNTAR A LA BASE DE DATOS Y USAR EL AUNTENTICADOR
    mfirestore = FirebaseFirestore.getInstance();
    mAuth = FirebaseAuth.getInstance();

    nuevoNombre = findViewById(R.id.txtNuevoNombre);
    nuevoCorreo =  findViewById(R.id.txtNewEmail);
    nuevoPass = findViewById(R.id.txtNewPass);

    registrar = findViewById(R.id.btnRegistrarme);
    volver = findViewById(R.id.btnVolver);

    registrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String name = nuevoNombre.getText().toString();
        String correo = nuevoCorreo.getText().toString().trim();
        String password = nuevoPass.getText().toString().trim();

        if (name.isEmpty() || correo.isEmpty() || password.isEmpty()){
          Toast.makeText(Register.this, "Los campos no deben estar vacios", Toast.LENGTH_SHORT).show();
        }else {
          registerUser(name, correo, password);
          nuevoNombre.setText("");
          nuevoCorreo.setText("");
          nuevoPass.setText("");
        }
      }
    });

    volver.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent pantallaLogin = new Intent(Register.this, Login.class);
        finish();
      }
    });
  }

  private void registerUser(String name, String correo, String password) {
    // VAMOS A CREAR UN USUARIO CON EMAIL Y PASSWORD EN FIREBASE AUTENTICACION
    mAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        // VAMOS A MOSTRAR UN TOAST PORQUE TOMA TIEMPO REGISTRAR EN FIREBASE
        Toast.makeText(Register.this, "Cargando... Por favor espere", Toast.LENGTH_SHORT).show();

        // VAMOS A OBTENER EL ID
        String id = mAuth.getCurrentUser().getUid();

        // VAMOS A AGREGAR EL ID, NOMBRE, EMAIL Y PASSWORD A UNA COLECCION LLAMADA user EN FIRESTORE
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("nombreUsuario", name);
        map.put("correoUsuario",correo);
        map.put("passwordUsuario",password);

        mfirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
          // CUANDO SE AGREGA A LA COLECCION USUARIO
          @Override
          public void onSuccess(Void unused) {
            Toast.makeText(Register.this, "Usuario registrado con exito", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(Register.this,MainActivity.class));
//            finish();
          }
        }).addOnFailureListener(new OnFailureListener() {
          // CUANDO HAY FALLA AL AGREGAR A LA COLECCION
          @Override
          public void onFailure(@NonNull Exception e) {
            Toast.makeText(Register.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
          }
        });
      }
    }).addOnFailureListener(new OnFailureListener() {
      // SI HAY FALLA AL MOMENTO DE CREAR EL USUARIO
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(Register.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
      }
    });
  }


}