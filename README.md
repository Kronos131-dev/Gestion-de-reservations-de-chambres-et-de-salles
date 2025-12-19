# **Gestion de réservations de chambres et de salles**

## **Filtres & Notifications**

### **Équipe**

- DAVID Johann - johann.david@etu.univ-littoral.fr
- FOURNIER Axel - axel.fournier001@etu.univ-littoral.fr

### **Présentation générale**

Cette partie présente une implémentation de filtres et de notifications pour le projet de gestion de réservations.

Il est ainsi possible de filtrer et de trier les espaces selon différents critères (décrits ci-dessous).  
De plus, les bases d'un système de notifications ont été créées, permettant à un utilisateur de recevoir des messages lors de certains événements.

### **Filtres**

D'abord conçu pour l'utilisation d'une requête SQL dans un `EspaceRepository`, nous avons finalement choisi d'opter pour l'approche par [Spécifications](https://docs.spring.io/spring-data/jpa/reference/jpa/specifications.html), permettant un contrôle plus poussé sans jamais écrire une seule requête SQL.

Cette approche a également permis à faire les différents [tris](https://www.baeldung.com/jpa-sort) disponibles.

### **Notifications**

Nous avons choisi d'implémenter le patron de conception [Observer](https://refactoring.guru/fr/design-patterns/observer) pour permettre de simplifier et de séparer les différents cas possibles.  
Ainsi, le code a été fait de telle manière à ajouter d'éventuels autres **observateurs** facilement à l'avenir.

Il existe ainsi 2 interfaces d'**observateurs**, `EspaceObserver` et `UtilisateurObserver`, qui permettent la création de notifications lors d'une modification respectivement d'un espace et d'un utilisateur.

Différentes implémentations de ces classes ont été codées :
- `EspaceDisponibleObserver` : créer une notification à tous les utilisateurs lorsqu'un espace devient disponible.
- `UtilisateurUpdateObserver` : classe abstraite qui permet de notifier un utilisateur lorsqu'il a modifié un de ses attributs.  
        - `UtilisateurNomObserver` : modification du nom de l'utilisateur.  
        -  `UtilisateurEmailObserver` : modification de l'email de l'utilisateur.  

Il existe également 2 **observables**, `EspaceStatusSubject` (implémentant `EspaceSubject`) et `UtilisateurUpdateSubject`, utilisés dans les différents **services** lors d'un appel aux routes **PUT** (décrites ci-dessous), notifiant ses **observateurs** associés.

### **Partie Web - Thymeleaf**

Le contrôleur `EspaceWebController` permet de récupérer les attributs `espaces` et `filter`, basés respectivement sur `EspaceDTO` et `EspaceFilterDTO`.  
La classe `UtilisateurWebControllerAdvice` permet d'envoyer à ce contrôleur un attribut `utilisateur`, basé sur `UtilisateurDTO`, afin d'éviter l'utilisation de différents services dans un même contrôleur.

Une fois le projet lancé, il est possible de récupérer la page HTML à cette adresse : https://localhost:8080/espaces.

La première partie est un formulaire permettant de choisir les différents filtres à appliquer.  

La deuxième partie affiche tous les espaces obtenus, selon l'utilisation ou non de filtres.  
Il est également possible de modifier le statut d'un espace, dans le but d'ajouter de nouvelles notifications aux utilisateurs.

La troisième partie affiche les différentes notifications du premier utilisateur qui a été obtenu dans la base de données.

### **Routes API**

### **Espaces**

- `GET: /api/espaces`

Permet de récupérer tous les espaces de la base de données.

<u>**Corps (optionel) :**</u>  un `EspaceFilterDTO` pour filtrer les résultats

```java
public record EspaceFilterDTO (
        Long minNbPlaces,               // nombre minimum de places
        Long maxNbPlaces,               // nombre maximum de places
        Float minPrixBase,              // prix de base minimum
        Float maxPrixBase,              // prix de base maximum
        List<Long> typeEspaceIds,       // n'afficher que les espaces ayant un certain typeEspace
        Boolean onlyDisponible,         // n'afficher que les espaces avec le statut DISPONIBLE  

        SortAttribute sortAttribute,    // attribut sur lequel se baser pour le tri
        SortOrder sortOrder             // tri croissant ou décroissant
) {
    public enum SortAttribute { NB_PLACES, PRIX_BASE };
    public enum SortOrder { ASCENDING, DESCENDING };
}
```

<u>**Retour :**</u> une liste de `EspaceDTO`

```java
public record EspaceDTO (
        Long idEspace,          // identifiant de l'espace
        Long nbPlaces,          // nombre de places disponibles dans l'espace
        Float prixBase,         // prix de l'espace (sans modifications tarifaires)
        Espace.Status status,   // statut actuel de l'espace (DISPONIBLE, EN_MAINTENANCE, OCCUPE)
        Long idTypeEspace,      // identifiant du type de l'espace
        String nomEspace        // nom attribué au type de l'espace
){ }
```

- `PUT: /api/espaces/{id}`

Permet de modifier le statut d'un espace.

<u>**Corps :**</u> un `EspaceUpdateDTO`

```java
public record EspaceUpdateDTO (
        Espace.Status status    // nouveau statut de l'espace (DISPONIBLE, EN_MAINTENANCE, OCCUPE)
) { }
```

<u>**Retour :**</u> un `EspaceDTO` de l'espace d'identifiant `id` modifié

Dans le cas où l'attribut `status` contenu dans le `EspaceUpdateDTO` contient `DISPONIBLE`, tous les utilisateurs recevront une notification :

> L'espace {`id`} est désormais disponible.

### **Utilisateurs**

- `GET: /api/utilisateurs`

Permet de récupérer tous les utilisateurs de la base de données.

<u>**Retour :**</u> une liste de `UtilisateurDTO`

```java
public record UtilisateurDTO (
        Long idUtilisateur,                     // identifiant de l'utilisateur
        List<NotificationDTO> notifications     // notifications reçues par l'utilisateur
){ }


public record NotificationDTO (
        String contenu,                         // message contenu dans la notification
        LocalDateTime dateCreation              // date de création de la notification
) { }
```

- `PUT: /api/utilisateurs/{id}`

Permet de modifier le nom et/ou l'email d'un utilisateur.

<u>**Corps :**</u> un `UtilisateurUpdateDTO`

```java
public record UtilisateurUpdateDTO (
        String nom,     // nouveau nom de l'utilisateur
        String email    // nouvel email de l'utilisateur
) { }
```

<u>**Retour :**</u> un `UtilisateurUpdateDTO` de l'utilisateur d'identifiant `id` modifié

En cas de succès, l'utilisateur concerné recevra une ou plusieurs notifications selon les changements apportés :

Si l'email n'est pas `null`
> Votre email a bien été modifié.  

Si le nom n'est pas `null`
> Votre nom a bien été modifié.

### Tests

Des tests unitaires `EspaceServiceTest` et `UtilisateurServiceTest` ont été écrits pour essayer les différentes méthodes décrites dans ces **services**.

De plus, des tests d'intégration `EspaceControllerIntegrationTest` et `UtilisateurControllerIntegrationTest` ont été écrits pour vérifier le bon fonctionnement lors d'appels à l'API.  
Ces derniers sont réalisés sur une **base de données H2** en mémoire.