package com.hotel.gestionTarifs.persistence;

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
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private Integer prix;

    @OneToMany(mappedBy = "serviceExtra")
    private Set<Extra> extras;

    public ServiceExtra() {
    }
    public ServiceExtra(String name, String description, Integer prix) {
        this.name = name;
        this.description = description;
        this.prix = prix;
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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