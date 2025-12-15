package com.hotel.gestionTarifs.business.dto;

import java.math.BigDecimal;
import java.io.Serializable;

public class ServiceExtraDTO implements Serializable {

    private Integer id;
    private String nom;
    private String description;
    private BigDecimal prix;

    public ServiceExtraDTO() {}

    public ServiceExtraDTO(Integer id, String nom, String description, BigDecimal prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }
}
