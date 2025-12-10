package com.yvain.clement.gpaiements.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name ="paiement")
@Getter
@Setter
public class PaiementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "paiement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaiement;

    @OneToOne
    @JoinColumn(name = "statut_id", nullable = false)
    private PaiementStatutEntity Statut;

    @OneToOne
    @JoinColumn(name = "prestation_id", nullable = false)
    private PrestationEntity Prestation;

    @Column(name = "prix")
    private BigDecimal prix;

    @Column(name = "date")
    private LocalDate date;

    public Integer getIdPaiement() {
        return idPaiement;
    }

    public PaiementStatutEntity getStatut() {
        return Statut;
    }

    public PrestationEntity getPrestation() {
        return Prestation;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public LocalDate getDate() {
        return date;
    }
}