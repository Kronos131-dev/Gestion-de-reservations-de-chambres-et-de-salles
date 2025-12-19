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
    @Column(name = "id_prestation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestation;

    @Column(name = "id_reservation", nullable = false)
    private Integer idReservation;

    @OneToOne
    @JoinColumn(name = "id_paiement", nullable = false)
    private PaiementEntity paiement;

    @Column(name = "prix")
    private BigDecimal prix;
}