package com.example.pizzarecipes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarecipes.R;

/**
 * SplashActivity — Premier écran affiché pendant 2 secondes.
 * Redirige automatiquement vers ListPizzaActivity.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY_MS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Navigation après 2 secondes — Handler.postDelayed
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, ListPizzaActivity.class);
            startActivity(intent);
            finish(); // Empêche le retour arrière vers le splash
        }, SPLASH_DELAY_MS);
    }
}
