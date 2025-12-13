package com.ulco.dashboard.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "prestation", schema = "public")
public class Prestation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestation")
    private Long id_prestation;

    @ManyToOne
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @Column(name = "prix")
    private Float prix;

    // getters
    public Long getId_prestation() { return id_prestation; }
    public Reservation getReservation() { return reservation; }
    public Float getPrix() { return prix; }
}

