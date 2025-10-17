package com.ulco.dashboard.persistence;

import jakarta.persistence.*;


@Entity
@Table(name = "espace")
public class Espace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_espace;

    @Column(nullable = false)
    private Long nb_place;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private float prix;

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


    public Status getStatus() {
        return status;
    }


    public float getPrix() {
        return prix;
    }


    public String getDescription() {
        return description;
    }


    public TypeEspace getTypeEspace() {
        return typeEspace;
    }


}