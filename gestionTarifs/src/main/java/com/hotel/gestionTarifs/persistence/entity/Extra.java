package com.hotel.gestionTarifs.persistence.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "extra")
public class Extra implements Serializable {

    @EmbeddedId
    private ExtraId id;

    @ManyToOne
    @MapsId("idService")
    @JoinColumn(name = "id_service")
    private ServiceExtra serviceExtra;

    @ManyToOne
    @MapsId("idPrestation")
    @JoinColumn(name = "id_prestation")
    private Prestation prestation;

    public ExtraId getId() {
        return id;
    }

    public void setId(ExtraId id) {
        this.id = id;
    }

    public ServiceExtra getServiceExtra() { return serviceExtra; }
    public void setServiceExtra(ServiceExtra serviceExtra) { this.serviceExtra = serviceExtra; }

    public Prestation getPrestation() { return prestation; }
    public void setPrestation(Prestation prestation) { this.prestation = prestation; }
}