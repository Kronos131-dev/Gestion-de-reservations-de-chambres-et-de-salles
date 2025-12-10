package com.yvain.clement.gpaiements.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name ="prestation")
@Getter
@Setter
public class PrestationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "prestation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestation;

    @Column(name = "reservation_id", nullable = false)
    private Integer idReservation;

    @OneToOne
    @JoinColumn(name = "paiement_id", nullable = false)
    private PaiementEntity Paiement;

    @Column(name = "prix")
    private BigDecimal prix;

    public Integer getIdPrestation() {
        return idPrestation;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public PaiementEntity getPaiement() {
        return Paiement;
    }
}