package com.ulco.dashboard.persistence;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "paiement", schema = "public")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    private Long id_paiement;

    @ManyToOne
    @JoinColumn(name = "id_statut")
    private StatutPaiementType statut;

    @ManyToOne
    @JoinColumn(name = "id_prestation")
    private Prestation prestation;

    @Column(name = "prix")
    private Float prix;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    // Getters
    public Long getId_paiement() { return id_paiement; }
    public StatutPaiementType getStatut() { return statut; }
    public Prestation getPrestation() { return prestation; }
    public Float getPrix() { return prix; }
    public Date getDate() { return date; }
}
