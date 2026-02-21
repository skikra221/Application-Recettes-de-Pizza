package com.example.pizzarecipes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pizzarecipes.R;
import com.example.pizzarecipes.classes.Produit;

import java.util.List;

/**
 * Adapter personnalisé pour afficher les pizzas dans une ListView.
 * Chaque ligne affiche : image, nom, durée et prix.
 */
public class PizzaAdapter extends BaseAdapter {

    private final Context context;
    private final List<Produit> pizzas;

    public PizzaAdapter(Context context, List<Produit> pizzas) {
        this.context = context;
        this.pizzas = pizzas;
    }

    @Override
    public int getCount() {
        return pizzas.size();
    }

    @Override
    public Object getItem(int position) {
        return pizzas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pizzas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_pizza, parent, false);
            holder = new ViewHolder();
            holder.imgPizza = convertView.findViewById(R.id.imgPizza);
            holder.tvNom = convertView.findViewById(R.id.tvNom);
            holder.tvMeta = convertView.findViewById(R.id.tvMeta);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Produit pizza = pizzas.get(position);
        holder.imgPizza.setImageResource(pizza.getImageRes());
        holder.tvNom.setText(pizza.getNom());
        holder.tvMeta.setText(pizza.getDuree() + "  •  " + pizza.getPrix() + " €");

        return convertView;
    }

    /** ViewHolder pattern pour optimiser le recycling */
    private static class ViewHolder {
        ImageView imgPizza;
        TextView tvNom;
        TextView tvMeta;
    }
}
