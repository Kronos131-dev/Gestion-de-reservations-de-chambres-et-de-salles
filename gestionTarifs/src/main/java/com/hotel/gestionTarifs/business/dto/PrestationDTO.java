package com.hotel.gestionTarifs.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrestationDTO implements Serializable {

    private Integer idPrestation;
    private BigDecimal prix;

    private Integer idReservation;
    private Integer idPaiement;


    public PrestationDTO() {}

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

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Integer getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(Integer idPaiement) {
        this.idPaiement = idPaiement;
    }
}
