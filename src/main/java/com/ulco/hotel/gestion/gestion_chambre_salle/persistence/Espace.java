package com.ulco.hotel.gestion.gestion_chambre_salle.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspace;


@Entity
@Table(name = "espace")
public class Espace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_espace;

    @Column(nullable = false)
    @Min(value = 1, message = "Le nombre de places doit être au moins 1")
    private Long nb_place;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    @Positive(message = "Le prix doit être positif")
    private Float prix_base;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private TypeEspace typeEspace;


    public enum Status {
        DISPONIBLE,
        OCCUPE,
        EN_MAINTENANCE
    }

    // Getters/Setters

    public Long getid_espace() {
        return id_espace;
    }

    public Long getNb_place() {
        return nb_place;
    }

    public void setNb_place(Long nb_place) {
        this.nb_place = nb_place;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Float getPrix_base() {
        return prix_base;
    }

    public void setPrix_base(Float prix) {
        this.prix_base = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeEspace getTypeEspace() {
        return typeEspace;
    }

    public void setTypeEspace(TypeEspace typeEspace) {
        this.typeEspace = typeEspace;
    }

}






