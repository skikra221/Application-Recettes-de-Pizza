package com.example.pizzarecipes.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarecipes.R;
import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.service.ProduitService;

/**
 * PizzaDetailActivity — Affiche les détails complets d'une pizza.
 * Reçoit l'identifiant via l'extra "pizza_id".
 */
public class PizzaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        // Récupère l'id transmis par ListPizzaActivity
        long pizzaId = getIntent().getLongExtra("pizza_id", -1);

        if (pizzaId == -1) {
            Toast.makeText(this, getString(R.string.pizza_not_found), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Charge la pizza depuis le service
        Produit pizza = ProduitService.getInstance().findById(pizzaId);

        if (pizza == null) {
            Toast.makeText(this, getString(R.string.pizza_not_found), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Peuple les vues
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(pizza.getNom());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView img = findViewById(R.id.imgDetail);
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvMeta = findViewById(R.id.tvDetailMeta);
        TextView tvIngredients = findViewById(R.id.tvDetailIngredients);
        TextView tvDescription = findViewById(R.id.tvDetailDescription);
        TextView tvEtapes = findViewById(R.id.tvDetailEtapes);

        img.setImageResource(pizza.getImageRes());
        tvTitle.setText(pizza.getNom());
        tvMeta.setText(pizza.getDuree() + "  •  " + pizza.getPrix() + " €");
        tvIngredients.setText(pizza.getIngredients());
        tvDescription.setText(pizza.getDescription());
        tvEtapes.setText(pizza.getEtapes());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
