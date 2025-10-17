package com.hotel.gestionClients.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "nom", length = 30)
    private String nom;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "niv_autorisation")
    private Integer nivAutorisation;

    public Role() {}

    public Role(String nom, String description, Integer nivAutorisation) {
        this.nom = nom;
        this.description = description;
        this.nivAutorisation = nivAutorisation;
    }

    public Long getIdRole() { return idRole; }
    public void setIdRole(Long idRole) { this.idRole = idRole; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getNivAutorisation() { return nivAutorisation; }
    public void setNivAutorisation(Integer nivAutorisation) { this.nivAutorisation = nivAutorisation; }
}