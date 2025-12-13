package com.ulco.dashboard.persistence;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id_reservation;

    @Column(name = "date_arrivee")
    @Temporal(TemporalType.DATE)
    private Date date_arrivee;

    public Long getId_reservation() { return id_reservation; }
    public Date getDate_arrivee() { return date_arrivee; }
}
