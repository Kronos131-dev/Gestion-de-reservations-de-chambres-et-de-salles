package com.hotel.gestionTarifs.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
//import java.util.Set;

@Entity
@Table(name ="prestation")
public class Prestation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestation")
    private Integer idPrestation;

    @Column(name = "prix", precision = 10, scale = 4, nullable = false)
    private BigDecimal prix;

    @ManyToOne
    @JoinColumn(name = "id_réservation", referencedColumnName = "id_reservation")
    private Reservation reservation;
    @ManyToOne
    @JoinColumn(name = "id_paiement", referencedColumnName = "id_paiement")
    private Paiement paiement;

   // @OneToMany(mappedBy = "prestation")
   // private Set<Extra> extras ;
   public Prestation() {}


    public Integer getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(Integer idPrestation) {
        this.idPrestation = idPrestation;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

   /* public Set<Extra> getExtras() {
        return extras;
    }

    public void setExtras(Set<Extra> extras) {
        this.extras = extras;
    } */
}
