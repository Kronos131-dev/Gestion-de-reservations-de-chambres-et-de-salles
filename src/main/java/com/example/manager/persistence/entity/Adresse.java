package com.example.manager.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Entité représentant une adresse
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "adresse")
public class Adresse {

    // Identifiant unique de l'adresse
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_adresse")
    private Long id;

    // Numéro de rue
    @Column(nullable = false)
    private String num;

    // Nom de la rue
    @Column(nullable = false)
    private String rue;

    // Ville
    @Column(nullable = false)
    private String ville;

    // Code postal
    @Column(name = "code_postal", nullable = false)
    private String codePostal;

    // Pays
    @Column(nullable = false)
    private String pays;

    // Liste des utilisateurs associés à cette adresse
    @OneToMany(mappedBy = "adresse")
    @JsonIgnore  // Pour éviter la boucle infinie lors de la sérialisation JSON
    private List<Utilisateur> utilisateurs = new ArrayList<>();
}
