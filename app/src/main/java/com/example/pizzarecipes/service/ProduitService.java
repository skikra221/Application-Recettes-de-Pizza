package com.example.pizzarecipes.service;

import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.dao.IDao;
import com.example.pizzarecipes.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service singleton gérant la liste des pizzas en mémoire.
 * Implémente IDao<Produit> avec un seed de 10 pizzas.
 */
public class ProduitService implements IDao<Produit> {

    private static ProduitService INSTANCE;
    private final List<Produit> data = new ArrayList<>();

    /** Constructeur privé — Singleton */
    private ProduitService() {
        seed();
    }

    /** Retourne l'unique instance du service */
    public static ProduitService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProduitService();
        }
        return INSTANCE;
    }

    // ─── IDao Implementation ─────────────────────────────────────────────────

    @Override
    public Produit create(Produit produit) {
        data.add(produit);
        return produit;
    }

    @Override
    public Produit update(Produit produit) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == produit.getId()) {
                data.set(i, produit);
                return produit;
            }
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        return data.removeIf(p -> p.getId() == id);
    }

    @Override
    public Produit findById(long id) {
        for (Produit p : data) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }

    @Override
    public List<Produit> findAll() {
        return Collections.unmodifiableList(data);
    }

    // ─── Seed Data (10 Pizzas) ────────────────────────────────────────────────

    private void seed() {

        data.add(new Produit(
                "Margherita",
                8.50,
                R.drawable.pizza1,
                "20 min",
                "- Pâte à pizza\n- Sauce tomate\n- Mozzarella fraîche\n- Basilic frais\n- Huile d'olive\n- Sel, poivre",
                "La Margherita est la pizza italienne par excellence. Simple, savoureuse et généreuse en mozzarella, elle est parfaite pour tous les gourmands qui apprécient la cuisine italienne authentique.",
                "1. Préchauffez le four à 250°C.\n2. Étalez la pâte sur une plaque farinée.\n3. Étalez la sauce tomate uniformément.\n4. Répartissez la mozzarella en morceaux.\n5. Enfournez 12 minutes jusqu'à ce que le fromage soit doré.\n6. Ajoutez le basilic frais et un filet d'huile d'olive avant de servir."));

        data.add(new Produit(
                "Pepperoni",
                10.90,
                R.drawable.pizza2,
                "25 min",
                "- Pâte à pizza\n- Sauce tomate\n- Mozzarella\n- Pepperoni en tranches\n- Origan\n- Huile d'olive",
                "La pizza Pepperoni est un classique américain adoré dans le monde entier. La charcuterie épicée se marie parfaitement avec la mozzarella fondante et la sauce tomate savoureuse.",
                "1. Préchauffez le four à 240°C.\n2. Étalez la pâte et nappez-la de sauce tomate.\n3. Ajoutez la mozzarella râpée.\n4. Disposez les rondelles de pepperoni sur toute la surface.\n5. Saupoudrez d'origan.\n6. Enfournez 15 minutes. Servez chaud."));

        data.add(new Produit(
                "Quatre Fromages",
                12.50,
                R.drawable.pizza3,
                "22 min",
                "- Pâte à pizza\n- Sauce béchamel\n- Mozzarella\n- Gorgonzola\n- Emmental\n- Parmesan râpé\n- Poivre noir",
                "Un délice pour les amateurs de fromage ! La pizza Quatre Fromages associe la douceur de la mozzarella, la puissance du gorgonzola, la générosité de l'emmental et la finesse du parmesan.",
                "1. Préchauffez le four à 245°C.\n2. Étalez la pâte et nappez de béchamel.\n3. Répartissez les quatre fromages en parts égales.\n4. Poivrez généreusement.\n5. Enfournez 14 minutes jusqu'à dorure.\n6. Laissez reposer 2 minutes avant de découper."));

        data.add(new Produit(
                "Pizza Végétarienne",
                9.90,
                R.drawable.pizza4,
                "30 min",
                "- Pâte à pizza\n- Sauce tomate\n- Mozzarella\n- Poivrons (rouge, vert, jaune)\n- Champignons\n- Olives noires\n- Oignon rouge\n- Courgette",
                "Colorée et savoureuse, la pizza végétarienne est une explosion de légumes frais. Elle ravit les végétariens et les amateurs de saveurs légères et équilibrées.",
                "1. Préchauffez le four à 240°C.\n2. Faites revenir les légumes à la poêle 5 minutes.\n3. Étalez la pâte, ajoutez la sauce tomate.\n4. Ajoutez la mozzarella et les légumes sautés.\n5. Parsemez d'olives noires.\n6. Enfournez 15 minutes. Servez avec un filet d'huile d'olive."));

        data.add(new Produit(
                "Pizza Orientale",
                11.50,
                R.drawable.pizza5,
                "35 min",
                "- Pâte à pizza\n- Sauce harissa\n- Mozzarella\n- Merguez\n- Oignon\n- Poivron rouge\n- Coriandre fraîche\n- Cumin",
                "Inspirée des saveurs du Maghreb, la pizza orientale combine la tomate et le piment avec des épices chaleureuses. Les merguez apportent un caractère unique et savoureux.",
                "1. Préchauffez le four à 240°C.\n2. Mélangez sauce tomate et harissa.\n3. Étalez la pâte, nappez de sauce épicée.\n4. Ajoutez la mozzarella, les rondelles de merguez, l'oignon et le poivron.\n5. Saupoudrez de cumin.\n6. Enfournez 18 minutes. Servez avec de la coriandre fraîche."));

        data.add(new Produit(
                "Pizza Poulet Pesto",
                13.00,
                R.drawable.pizza6,
                "30 min",
                "- Pâte à pizza\n- Sauce pesto maison\n- Mozzarella\n- Blanc de poulet grillé\n- Tomates cerises\n- Roquette\n- Parmesan\n- Pignons de pin",
                "Une pizza raffinée avec un pesto basilic généreux, du poulet grillé tendre et des roquettes fraîches. Un mélange de saveurs méditerranéennes qui impressionne à chaque bouchée.",
                "1. Préparez le pesto : mixez basilic, parmesan, pignons, ail et huile d'olive.\n2. Préchauffez le four à 240°C.\n3. Étalez la pâte, ajoutez le pesto et la mozzarella.\n4. Ajoutez le poulet et les tomates cerises.\n5. Enfournez 15 minutes.\n6. Ajoutez la roquette et le parmesan après cuisson."));

        data.add(new Produit(
                "Pizza Hawaïenne",
                10.50,
                R.drawable.pizza7,
                "25 min",
                "- Pâte à pizza\n- Sauce tomate\n- Mozzarella\n- Jambon blanc\n- Ananas en morceaux\n- Poivron rouge\n- Origan",
                "La pizza hawaïenne est une pizza controversée mais très populaire ! La douceur de l'ananas se marie étonnamment bien avec le jambon salé et la mozzarella fondante.",
                "1. Préchauffez le four à 240°C.\n2. Étalez la pâte, ajoutez la sauce tomate.\n3. Ajoutez la mozzarella râpée.\n4. Déposez le jambon et les morceaux d'ananas.\n5. Ajoutez les lamelles de poivron et l'origan.\n6. Enfournez 15 minutes. Servez chaud."));

        data.add(new Produit(
                "Pizza Capricciosa",
                11.90,
                R.drawable.pizza8,
                "28 min",
                "- Pâte à pizza\n- Sauce tomate\n- Mozzarella\n- Jambon cuit\n- Champignons\n- Artichauts\n- Olives noires\n- Anchois",
                "La Capricciosa est une pizza riche et généreuse, assemblant charcuterie, légumes et anchois. Un classique italien qui offre une grande variété de saveurs en une seule part.",
                "1. Préchauffez le four à 245°C.\n2. Étalez la pâte et nappez de sauce tomate.\n3. Répartissez la mozzarella.\n4. Ajoutez le jambon, les champignons, les artichauts, les olives et les anchois.\n5. Enfournez 15 minutes.\n6. Laissez reposer 2 minutes avant de servir."));

        data.add(new Produit(
                "Pizza Diavola",
                11.00,
                R.drawable.pizza9,
                "22 min",
                "- Pâte à pizza\n- Sauce tomate épicée\n- Mozzarella\n- Salami piquant\n- Piments rouges\n- Ail\n- Huile pimentée",
                "Pour les amateurs de sensations fortes ! La Diavola est une pizza très épicée avec du salami piquant et des piments qui réchauffent le palais. Un pur délice pour les courageux.",
                "1. Préchauffez le four à 240°C.\n2. Ajoutez de l'ail et du piment à la sauce tomate.\n3. Étalez la pâte, nappez de sauce pimentée.\n4. Ajoutez la mozzarella et les tranches de salami.\n5. Parsemez de piments frais.\n6. Enfournez 14 minutes. Arrosez d'huile pimentée avant de servir."));

        data.add(new Produit(
                "Pizza Truffe & Champignons",
                16.00,
                R.drawable.pizza10,
                "40 min",
                "- Pâte à pizza\n- Crème de truffe\n- Mozzarella di bufala\n- Champignons sauvages (cèpes, girolles)\n- Parmesan râpé\n- Thym frais\n- Huile de truffe",
                "La pizza de luxe par excellence. La crème de truffe et les champignons sauvages offrent une expérience gastronomique incomparable. Cette pizza est parfaite pour une occasion spéciale.",
                "1. Préchauffez le four à 250°C.\n2. Faites revenir les champignons au beurre avec du thym.\n3. Étalez la pâte, nappez de crème de truffe.\n4. Ajoutez la mozzarella di bufala et les champignons.\n5. Saupoudrez de parmesan.\n6. Enfournez 14 minutes.\n7. Arrosez d'huile de truffe avant de servir."));
    }
}
