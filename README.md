# 📋 GestiPresence — Système de Gestion des Présences

> Application desktop Java 17 pour la gestion des présences du personnel : services, employés et horaires d'entrée/sortie.

---

## 📌 Table des matières

- [Aperçu](#aperçu)
- [Fonctionnalités](#fonctionnalités)
- [Technologies](#technologies)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration de la base de données](#configuration-de-la-base-de-données)
- [Structure du projet](#structure-du-projet)
- [Modèle de données](#modèle-de-données)
- [Utilisation](#utilisation)
- [Captures d'écran](#captures-décran)
- [Auteur](#auteur)

---

## Aperçu

**GestiPresence** est une application desktop développée en Java 17 avec une interface graphique Swing. Elle permet à une entreprise de gérer ses **services**, son **personnel** et les **horaires de présence** (pointages d'arrivée et de sortie) de chaque employé.

---

## Fonctionnalités

### 🏢 Gestion des Services
- Ajouter, modifier et supprimer des services (départements)
- Consulter la liste des services avec le nombre d'employés rattachés
- Recherche et filtrage des services

### 👤 Gestion du Personnel
- Enregistrement des employés (nom, prénom, matricule, poste, service)
- Modification et désactivation d'un employé
- Affichage du personnel par service
- Recherche par nom, matricule ou service

### ⏱️ Gestion des Horaires (Pointages)
- Enregistrement de l'heure d'arrivée
- Enregistrement de l'heure de départ
- Calcul automatique de la durée de présence
- Historique des pointages par employé et par date
- Filtrage par date, service ou employé
- Export des données (CSV)

### 📊 Tableau de bord
- Vue d'ensemble des présences du jour
- Statistiques par service
- Alertes sur les absences et retards

---

## Technologies

| Composant | Technologie |
|-----------|-------------|
| Langage | Java 17 (LTS) |
| Interface graphique | Java Swing |
| Base de données | MySQL 8.x |
| Connecteur BDD | MySQL Connector/J 8.x |
| Build | Maven 3.x |
| Modèle architectural | MVC (Model-View-Controller) |

---

## Prérequis

Avant de lancer l'application, assurez-vous d'avoir installé :

- **JDK 17** ou supérieur → [Télécharger OpenJDK 17](https://adoptium.net/)
- **MySQL 8.0** ou supérieur → [Télécharger MySQL](https://dev.mysql.com/downloads/)
- **Maven 3.8+** → [Télécharger Maven](https://maven.apache.org/download.cgi)

Vérifiez les versions :

```bash
java -version
mvn -version
mysql --version
```

---

## Installation

### 1. Cloner le dépôt

```bash
git clone https://github.com/votre-utilisateur/gestipresence.git
cd gestipresence
```

### 2. Configurer la base de données

Connectez-vous à MySQL et exécutez le script d'initialisation :

```bash
mysql -u root -p < sql/init.sql
```

### 3. Configurer la connexion

Éditez le fichier `src/main/resources/config.properties` :

```properties
db.host=localhost
db.port=3306
db.name=gestipresence
db.user=root
db.password=votre_mot_de_passe
```

### 4. Compiler et lancer

```bash
mvn clean package
java -jar target/gestipresence-1.0.jar
```

---

## Configuration de la base de données

Le script SQL `sql/init.sql` crée la base de données et les tables nécessaires :

```sql
CREATE DATABASE IF NOT EXISTS gestipresence
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE gestipresence;

-- Table des services
CREATE TABLE services (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    code        VARCHAR(10)  NOT NULL UNIQUE,
    nom         VARCHAR(100) NOT NULL,
    description TEXT,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table du personnel
CREATE TABLE personnel (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    matricule   VARCHAR(20)  NOT NULL UNIQUE,
    nom         VARCHAR(100) NOT NULL,
    prenom      VARCHAR(100) NOT NULL,
    poste       VARCHAR(100),
    email       VARCHAR(150),
    telephone   VARCHAR(20),
    service_id  INT NOT NULL,
    actif       TINYINT(1) DEFAULT 1,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE RESTRICT
);

-- Table des pointages (horaires)
CREATE TABLE pointages (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    personnel_id    INT     NOT NULL,
    date_pointage   DATE    NOT NULL,
    heure_arrivee   TIME,
    heure_depart    TIME,
    duree_minutes   INT GENERATED ALWAYS AS (
        CASE
            WHEN heure_arrivee IS NOT NULL AND heure_depart IS NOT NULL
            THEN TIMESTAMPDIFF(MINUTE,
                    TIMESTAMP(date_pointage, heure_arrivee),
                    TIMESTAMP(date_pointage, heure_depart))
            ELSE NULL
        END
    ) STORED,
    observation     TEXT,
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (personnel_id) REFERENCES personnel(id) ON DELETE CASCADE,
    UNIQUE KEY uk_personnel_date (personnel_id, date_pointage)
);
```

---

## Structure du projet

```
gestipresence/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/gestipresence/
│       │       ├── Main.java                   # Point d'entrée
│       │       ├── config/
│       │       │   └── DatabaseConfig.java     # Connexion MySQL (Singleton)
│       │       ├── model/
│       │       │   ├── Service.java            # Entité Service
│       │       │   ├── Personnel.java          # Entité Personnel
│       │       │   └── Pointage.java           # Entité Pointage
│       │       ├── dao/
│       │       │   ├── ServiceDAO.java         # CRUD Services
│       │       │   ├── PersonnelDAO.java       # CRUD Personnel
│       │       │   └── PointageDAO.java        # CRUD Pointages
│       │       ├── service/
│       │       │   ├── ServiceManager.java     # Logique métier services
│       │       │   ├── PersonnelManager.java   # Logique métier personnel
│       │       │   └── PointageManager.java    # Logique métier pointages
│       │       └── view/
│       │           ├── MainFrame.java          # Fenêtre principale
│       │           ├── DashboardPanel.java     # Tableau de bord
│       │           ├── ServicePanel.java       # Gestion services
│       │           ├── PersonnelPanel.java     # Gestion personnel
│       │           └── PointagePanel.java      # Gestion pointages
│       └── resources/
│           ├── config.properties               # Configuration BDD
│           └── icons/                          # Icônes de l'interface
├── sql/
│   └── init.sql                                # Script d'initialisation BDD
├── pom.xml                                     # Dépendances Maven
└── README.md
```

---

## Modèle de données

```
┌──────────────┐         ┌───────────────┐         ┌──────────────────┐
│   services   │ 1     N │   personnel   │ 1     N │    pointages     │
├──────────────┤         ├───────────────┤         ├──────────────────┤
│ id (PK)      │◄────────│ service_id(FK)│◄────────│ personnel_id(FK) │
│ code         │         │ id (PK)       │         │ id (PK)          │
│ nom          │         │ matricule     │         │ date_pointage    │
│ description  │         │ nom           │         │ heure_arrivee    │
│ created_at   │         │ prenom        │         │ heure_depart     │
│ updated_at   │         │ poste         │         │ duree_minutes    │
└──────────────┘         │ email         │         │ observation      │
                         │ telephone     │         │ created_at       │
                         │ actif         │         └──────────────────┘
                         │ created_at    │
                         │ updated_at    │
                         └───────────────┘
```

---

## Utilisation

### Démarrage

Au premier lancement, l'application ouvre la fenêtre principale avec le tableau de bord.

### Gérer les services

1. Aller dans **Menu > Services** ou cliquer sur l'onglet **Services**
2. Cliquer sur **"Nouveau"** pour ajouter un service
3. Renseigner le code, le nom et la description
4. Cliquer sur **"Enregistrer"**

### Gérer le personnel

1. Aller dans l'onglet **Personnel**
2. Cliquer sur **"Ajouter un employé"**
3. Renseigner les informations et choisir le service rattaché
4. Cliquer sur **"Enregistrer"**

### Enregistrer un pointage

1. Aller dans l'onglet **Pointages**
2. Rechercher l'employé par matricule ou nom
3. Cliquer sur **"Arrivée"** pour enregistrer l'heure d'entrée
4. En fin de journée, cliquer sur **"Départ"** pour enregistrer la sortie
5. La durée de présence est calculée automatiquement

### Consulter l'historique

1. Dans l'onglet **Pointages**, sélectionner une plage de dates
2. Filtrer par service ou par employé si nécessaire
3. Cliquer sur **"Exporter CSV"** pour télécharger les données

---

## Captures d'écran

> *À ajouter après la première version de l'interface.*

```
[Tableau de bord]     [Gestion Personnel]     [Pointages du jour]
     📊                      👥                      ⏱️
```

---

## Auteur

Développé par **Vincent Gérard SALABANZI**
📧 vincent@vincentsalabanzi.com
🔗 [GitHub](https://github.com/20cent)

---

## Licence

Ce projet est sous licence **MIT** — voir le fichier [LICENSE](LICENSE) pour plus de détails.

---

*GestiPresence v1.0 — Java 17 · Swing · MySQL*
