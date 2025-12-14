# Système de Gestion de Réservations Hôtelières

> *Projet Académique J2EE / Spring Boot*
>
> Ce projet est une application web complète permettant la gestion des clients et des accès sécurisés pour un hôtel. Elle respecte une architecture N-Tiers stricte et utilise une base de données distante pour faciliter la correction et le déploiement.

##  Table des Matières
- [Auteurs](#-auteurs)
- [Fonctionnalités Clés](#-fonctionnalités-clés)
- [Architecture Technique](#-architecture-technique)
- [Configuration & Base de Données](#-configuration--base-de-données)
- [Identifiants de Test](#-identifiants-de-test)
- [Structure du Projet](#-structure-du-projet)
- [Lancement](#-lancement)

---

## Auteurs
* **BERAZA Larbi**
* **EL ASSALI Achraf Zakaria**

##  Fonctionnalités Clés

###  Sécurité & Authentification (Spring Security)
- *Authentification Robuste :* Système de Login/Logout basé sur les sessions.
- *Redirection par Rôle :*
    - Les *Administrateurs* sont redirigés vers le tableau de bord de gestion (Back-Office).
    - Les *Clients* sont redirigés vers leur espace personnel (Front-Office).
- *Protection des Données :* Mots de passe hashés via *BCrypt*.
- *Sécurisation des URL :* Les pages d'administration (/clients/**) sont inaccessibles aux utilisateurs non-admin.

### Gestion des Clients (Back-Office)
- *CRUD Complet :* Ajout, Modification, Suppression de clients.
- *Moteur de Recherche :* Filtrage dynamique par Nom, Prénom ou Ville.
- *Validation des Données :* Vérification de l'unicité de l'email et formats obligatoires.
- *Interface Soignée :* Design responsive avec messages de confirmation (Flash Attributes).

###  Initialisation Automatique
- *Data Seeding :* Au démarrage, l'application vérifie et crée automatiquement les rôles nécessaires et un compte Administrateur par défaut si la base est vide.

---

##  Architecture Technique

Le projet suit l'architecture standard *MVC (Modèle-Vue-Contrôleur)* couplée à une architecture en couches :

1.  **Presentation (controller)** : Gestion des requêtes HTTP et des vues Thymeleaf.
2.  **Business (service)** : Logique métier, validation et conversion DTO/Entity.
3.  **Persistence (repository)** : Interaction avec la base de données via Spring Data JPA.
4.  **Security (config)** : Configuration de Spring Security et gestionnaires d'authentification.

---

##  Identifiants de Test

Voici les comptes pré-configurés pour tester les différents niveaux d'accès de l'application :

*1. Compte Administrateur (Accès Back-Office)*
* *Email :* admin@hotel.com
* *Mot de passe :* admin123
* Fonctionnalité : Accès complet à la gestion des clients et à l'administration.

*2. Compte Client (Accès Front-Office)*
* *Email :* test@gmail.com
* *Mot de passe :* test123
* Fonctionnalité : Accès à l'espace personnel et à l'accueil du site.

> *Note :* Le compte Administrateur est recréé automatiquement au démarrage s'il n'existe pas. Vous pouvez également créer vos propres clients via le formulaire d'inscription.

##  Lancement

1.  Ouvrir le projet dans un IDE (IntelliJ IDEA, Eclipse) ou un terminal.
2.  S'assurer que le JDK 17 (ou supérieur) et Maven sont installés.
3.  Lancer la commande suivante à la racine du projet :
    bash
    mvn spring-boot:run

4.  Accéder à l'application via votre navigateur :
    http://localhost:8080/login

---

##  Configuration & Base de Données

L'application est configurée pour se connecter à une base de données *PostgreSQL distante* (hébergée sur AlwaysData).

Le fichier src/main/resources/application.properties est configuré comme suit :

```properties
spring.application.name=gestionClients
server.port=8080

# Connexion Base de Données Distante (AlwaysData)
spring.datasource.url=jdbc:postgresql://postgresql-gestion-chambres-salles.alwaysdata.net:5432/gestion-chambres-salles_bdd
spring.datasource.username=gestion-chambres-salles
spring.datasource.password=Yq4ye!K7RjSxK4S
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuration JPA
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect