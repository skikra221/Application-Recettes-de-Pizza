package com.example.pizzarecipes.classes;

/**
 * Entité métier représentant une pizza (Produit).
 * AUTO_ID assure l'auto-incrémentation en mémoire.
 */
public class Produit {

    // Auto-incrément statique
    private static long AUTO_ID = 1;

    private long id;
    private String nom;
    private double prix;
    private int imageRes;
    private String duree;
    private String ingredients;
    private String description;
    private String etapes;

    /** Constructeur vide — attribue automatiquement un id */
    public Produit() {
        this.id = AUTO_ID++;
    }

    /** Constructeur complet — attribue id + initialise tous les champs */
    public Produit(String nom, double prix, int imageRes, String duree,
            String ingredients, String description, String etapes) {
        this.id = AUTO_ID++;
        this.nom = nom;
        this.prix = prix;
        this.imageRes = imageRes;
        this.duree = duree;
        this.ingredients = ingredients;
        this.description = description;
        this.etapes = etapes;
    }

    // ─── Getters & Setters ────────────────────────────────────────────────────

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtapes() {
        return etapes;
    }

    public void setEtapes(String etapes) {
        this.etapes = etapes;
    }

    @Override
    public String toString() {
        return nom + " - " + prix + " €";
    }
}
