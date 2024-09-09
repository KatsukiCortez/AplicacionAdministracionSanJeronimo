package com.katsuki.administracintributariav2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import android.content.SharedPreferences;

public class Login extends AppCompatActivity {

  // DECLARAMOS LAS VARIABLES A USAR
  Button ingresar, irRegistrar;
  EditText email, password;
  CheckBox recuerdame;
  FirebaseAuth mAuth;
  SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_login);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    mAuth = FirebaseAuth.getInstance();
    sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
    ingresar = findViewById(R.id.btnLogin);
    irRegistrar = findViewById(R.id.btnRegister);
    email = findViewById(R.id.txtCorreo);
    password = findViewById(R.id.txtPassword);
    recuerdame = findViewById(R.id.chbRemember);

    irRegistrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent pantallaRegistro = new Intent(Login.this, Register.class);
        startActivity(pantallaRegistro);
      }
    });

    ingresar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String emailUser = email.getText().toString().trim();
        String passUser = password.getText().toString().trim();

        if (emailUser.isEmpty() && passUser.isEmpty()) {
          Toast.makeText(Login.this, "Llene los datos por favor", Toast.LENGTH_SHORT).show();
        } else {
          loginUser(emailUser, passUser);
        }
      }
    });

    // Revisar si el usuario marcó "recuerdame" en una sesión anterior
    checkRememberedUser();
  }

  private void loginUser(String emailUser, String passUser) {
    mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        // Guardar estado de "recuerdame" en SharedPreferences
        if (recuerdame.isChecked()) {
          sharedPreferences.edit().putBoolean("rememberMe", true).apply();
        } else {
          sharedPreferences.edit().putBoolean("rememberMe", false).apply();
        }
        finish();

        //MOSTRAMOS MENSAJE DE BIENVENIDA
        Toast.makeText(Login.this, "Bienvenido ", Toast.LENGTH_SHORT).show();


        Intent pantallaPrincipal = new Intent(Login.this, MainActivity.class);
        startActivity(pantallaPrincipal);


      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void checkRememberedUser() {
    // Si el usuario ha marcado "recuerdame", pasamos automáticamente a la pantalla principal
    if (sharedPreferences.getBoolean("rememberMe", false)) {
      FirebaseUser user = mAuth.getCurrentUser();
      if (user != null) {
        startActivity(new Intent(Login.this, MainActivity.class));
        finish();
      }
    }
  }

}