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
    @Column(name = "id_paiement")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaiement;

    @ManyToOne
    @JoinColumn(name = "id_statut", nullable = false)
    private PaiementStatutEntity statut;

    @ManyToOne
    @JoinColumn(name = "id_prestation", nullable = false)
    private PrestationEntity prestation;

    @Column(name = "prix")
    private BigDecimal prix;

    @Column(name = "date")
    private LocalDate date;
}