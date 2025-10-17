package com.hotel.gestionTarifs.persistence;

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

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }
}

