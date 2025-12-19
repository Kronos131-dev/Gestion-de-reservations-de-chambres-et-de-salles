package com.example.manager.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Entité représentant un rôle utilisateur
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    // Identifiant unique du rôle
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_role")
    private Long id;

    // Nom du rôle (ex: ADMIN, USER)
    @Column(nullable = false)
    private String nom;

    // Description optionnelle du rôle
    @Column(nullable = true)
    private String description;

    // Liste des utilisateurs ayant ce rôle
    @OneToMany(mappedBy = "role")
    @JsonIgnore  // Pour éviter la boucle infinie lors de la sérialisation JSON
    private List<Utilisateur> utilisateurs = new ArrayList<>();
}
