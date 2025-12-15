package com.hotel.gestionTarifs.persistence.entity;

import jakarta.persistence.*;


/**
 * Classe fictive créée juste pour respecter la structure de la base de données
 * et garder les relations JPA avec la table Prestation.
 */
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @Column(name = "id_reservation")
    private Integer idReservation;

    @Column(name = "duree_jours")
    private Integer dureeJours;

    @ManyToOne
    @JoinColumn(name = "id_espace", referencedColumnName = "id_espace")
    private Espace espace;

    @ManyToOne
    @JoinColumn(name = "id_saison", referencedColumnName = "id_saison")
    private Saison saison;

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Integer getDureeJours() {
        return dureeJours;
    }

    public void setDureeJours(Integer dureeJours) {
        this.dureeJours = dureeJours;
    }

    public Espace getEspace() {
        return espace;
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
    }
}

