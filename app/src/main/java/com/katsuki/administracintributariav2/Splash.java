package com.katsuki.administracintributariav2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_splash);
    getSupportActionBar().hide();

    // TIMER
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent pantallaLogin = new Intent(Splash.this,Login.class);
        startActivity(pantallaLogin);

        // NO VOLVER A MOSTRAR EL SPLASH
        finish();
      }
    },1000);
  }
}