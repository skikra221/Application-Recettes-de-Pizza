package com.example.pizzarecipes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarecipes.R;
import com.example.pizzarecipes.adapter.PizzaAdapter;
import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.service.ProduitService;

import java.util.List;

/**
 * ListPizzaActivity — Affiche la liste complète des pizzas.
 * Un clic sur un item ouvre PizzaDetailActivity avec l'id de la pizza.
 */
public class ListPizzaActivity extends AppCompatActivity {

    private ListView lvPizzas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pizza);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Pizza Recipes");
        }

        lvPizzas = findViewById(R.id.lvPizzas);

        // Récupère les données via le service singleton
        List<Produit> pizzas = ProduitService.getInstance().findAll();
        PizzaAdapter adapter = new PizzaAdapter(this, pizzas);
        lvPizzas.setAdapter(adapter);

        // Navigation vers l'écran de détail au clic
        lvPizzas.setOnItemClickListener((parent, view, position, id) -> {
            Produit selected = (Produit) adapter.getItem(position);
            Intent intent = new Intent(ListPizzaActivity.this, PizzaDetailActivity.class);
            intent.putExtra("pizza_id", selected.getId());
            startActivity(intent);
        });
    }
}
