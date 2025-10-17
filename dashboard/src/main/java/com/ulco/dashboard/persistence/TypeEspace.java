package com.ulco.dashboard.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "typeEspace")
public class TypeEspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_type;

    @Column(nullable = false)
    private String nom_espace;

    @Column(nullable = false)
    private String description;

    public Long getId_type() {
        return id_type;
    }

    public String getNom_espace() {
        return nom_espace;
    }

    public void setNom_espace(String nom_espace) {
        this.nom_espace = nom_espace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}