package com.hotel.gestionClients.business.dto;


import java.io.Serializable;

public class RoleDTO implements Serializable {

    private Long id;
    private String nom;
    private String description;
    private Integer nivAutorisation;

    public RoleDTO() {
    }

    public RoleDTO(Long id, String nom, String description, Integer nivAutorisation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.nivAutorisation = nivAutorisation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNivAutorisation() {
        return nivAutorisation;
    }

    public void setNivAutorisation(Integer nivAutorisation) {
        this.nivAutorisation = nivAutorisation;
    }
}
