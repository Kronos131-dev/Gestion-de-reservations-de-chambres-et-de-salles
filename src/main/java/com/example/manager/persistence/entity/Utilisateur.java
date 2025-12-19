package com.example.manager.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

// Entité représentant un utilisateur
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    // Identifiant unique de l'utilisateur
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Long id;

    // Nom de l'utilisateur
    @Column(nullable = false)
    private String nom;

    // Prénom de l'utilisateur
    @Column(nullable = false)
    private String prenom;

    // Email unique de l'utilisateur
    @Column(nullable = false, unique = true)
    private String email;

    // Mot de passe hashé
    @Column(nullable = false)
    private String password;

    // Numéro de téléphone
    @Column(nullable = false)
    private String tel;

    // Date de naissance
    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    // Rôle associé à l'utilisateur
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    // Adresse associée à l'utilisateur
    @ManyToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;
}
