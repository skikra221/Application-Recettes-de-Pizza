# 🍕 Application Recettes de Pizza

> Application Android Java affichant une liste de pizzas avec écran de détails complet.  
> Réalisée dans le cadre d'un TP de développement mobile Android.

---

## Présentation du projet

**Pizza Recipes** est une application Android native développée en **Java** qui permet aux utilisateurs de parcourir une liste de recettes de pizzas, de consulter les détails de chacune (ingrédients, description, étapes de préparation) et de naviguer entre les écrans avec une expérience fluide.

L'application illustre les concepts fondamentaux du développement Android : gestion des données en mémoire via un service singleton, affichage dans une `ListView` avec un adapter personnalisé, navigation entre activités via des `Intent`, et cycle de vie Android.

---

## Objectifs pédagogiques

- Implémenter une architecture en couches : `classes` / `dao` / `service` / `adapter` / `ui`
- Créer un **singleton** de gestion des données en mémoire
- Utiliser un **adapter personnalisé** (`BaseAdapter`) pour une `ListView`
- Passer des données entre activités via `Intent` extras
- Gérer un **Splash Screen** avec `Handler.postDelayed()`
- Respecter les bonnes pratiques Android (ViewHolder pattern, ScrollView, finish())

---

## Technologies utilisées

| Technologie | Version |
|---|---|
| Java | 8 (source/target compatibility) |
| Android API | minSdk 24 · targetSdk 34 |
| Gradle | 8.4 · AGP 8.2.2 |
| AndroidX AppCompat | 1.6.1 |
| UI Framework | Android Views (XML) |
| Pattern de données | Singleton in-memory |

---

## Structure des packages

```
app/src/main/java/com/example/pizzarecipes/
├── classes/
│   └── Produit.java          ← Entité métier (id, nom, prix, durée, imageRes, ingrédients, description, étapes)
├── dao/
│   └── IDao.java             ← Interface CRUD générique <T>
├── service/
│   └── ProduitService.java   ← Singleton + List<Produit> + seed 10 pizzas + implémente IDao
├── adapter/
│   └── PizzaAdapter.java     ← BaseAdapter + ViewHolder pour la ListView
└── ui/
    ├── SplashActivity.java   ← Écran de démarrage (2s) → ListPizzaActivity
    ├── ListPizzaActivity.java ← Liste des pizzas avec PizzaAdapter
    └── PizzaDetailActivity.java ← Détails complets d'une pizza
```

```
app/src/main/res/
├── layout/
│   ├── activity_splash.xml       ← Fond rouge, logo centré, titre
│   ├── activity_list_pizza.xml   ← LinearLayout + ListView (id=lvPizzas)
│   ├── row_pizza.xml             ← Row 96dp : image 80dp + nom + méta
│   └── activity_pizza_detail.xml ← ScrollView avec toutes les sections
├── drawable/
│   └── pizza1.png … pizza10.png  ← Images des pizzas
└── values/
    ├── strings.xml, colors.xml, themes.xml
```

---

## Modèle de données

### `Produit.java` (classes)
| Champ | Type | Description |
|---|---|---|
| `AUTO_ID` | `static long` | Compteur auto-incrémenté |
| `id` | `long` | Identifiant unique |
| `nom` | `String` | Nom de la pizza |
| `prix` | `double` | Prix en euros |
| `imageRes` | `int` | Référence drawable (`R.drawable.pizzaN`) |
| `duree` | `String` | Durée de préparation |
| `ingredients` | `String` | Liste des ingrédients |
| `description` | `String` | Description de la pizza |
| `etapes` | `String` | Étapes de préparation numérotées |

### `IDao<T>` (dao)
Interface générique exposant : `create`, `update`, `delete(id)`, `findById(id)`, `findAll()`.

### `ProduitService` (service)
- **Singleton** : `private static ProduitService INSTANCE` + `getInstance()`
- Stockage : `List<Produit> data` initialisée par `seed()` avec 10 pizzas
- `findAll()` retourne `Collections.unmodifiableList(data)`
- Implémente complètement `IDao<Produit>`

---

## Écrans & Navigation

```
[SplashActivity]  ──(2 secondes + finish())──►  [ListPizzaActivity]
                                                       │
                                              onItemClick(pizza_id)
                                                       │
                                                       ▼
                                               [PizzaDetailActivity]
                                              (reçoit pizza_id via extra)
```

### SplashActivity
- Thème sans ActionBar, fond rouge vif
- `Handler(Looper.getMainLooper()).postDelayed(runnable, 2000)`
- `finish()` immédiatement après `startActivity()` pour bloquer le retour arrière

### ListPizzaActivity
- `ListView` (id: `lvPizzas`) peuplée par `PizzaAdapter`
- Données : `ProduitService.getInstance().findAll()`
- `setOnItemClickListener` → `Intent` avec `putExtra("pizza_id", id)`

### PizzaDetailActivity
- `getIntent().getLongExtra("pizza_id", -1)`
- `ProduitService.getInstance().findById(id)`
- Si null → `Toast` + `finish()` (gestion d'erreur)
- `ScrollView` pour contenu long (ingrédients, description, étapes)
- Bouton retour ActionBar via `setDisplayHomeAsUpEnabled(true)` + `onSupportNavigateUp()`

---

## Comment exécuter

### Prérequis
- **Android Studio** Hedgehog ou supérieur
- **Android SDK** API 24+ installé
- **JDK 8** ou supérieur

### Étapes
1. Cloner le dépôt :
   ```bash
   git clone https://github.com/skikra221/Application-Recettes-de-Pizza.git
   ```
2. Ouvrir **Android Studio** → `File` → `Open` → sélectionner `Application-Recettes-de-Pizza/`
3. Laisser **Gradle Sync** se terminer
4. Brancher un device Android (API 24+) ou lancer un émulateur
5. Cliquer **Run ▶** (ou `Shift + F10`)

### Variables à vérifier
- Gradle : `Build` → `Clean Project` puis `Rebuild Project` si erreur de sync

---

## Pizzas disponibles (seed)

| # | Nom | Prix | Durée |
|---|---|---|---|
| 1 | Margherita | 8.50 € | 20 min |
| 2 | Pepperoni | 10.90 € | 25 min |
| 3 | Quatre Fromages | 12.50 € | 22 min |
| 4 | Végétarienne | 9.90 € | 30 min |
| 5 | Orientale | 11.50 € | 35 min |
| 6 | Poulet Pesto | 13.00 € | 30 min |
| 7 | Hawaïenne | 10.50 € | 25 min |
| 8 | Capricciosa | 11.90 € | 28 min |
| 9 | Diavola | 11.00 € | 22 min |
| 10 | Truffe & Champignons | 16.00 € | 40 min |

---