package com.hotel.gestionTarifs.persistence.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "service_extra")
public class ServiceExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Integer idService;

    @Column(length = 50, nullable = false)
    private String nom;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private Integer prix;

    @OneToMany(mappedBy = "serviceExtra")
    private Set<Extra> extras;

    public ServiceExtra() {
    }
    public ServiceExtra(String nom, String description, Integer prix) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
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

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
}