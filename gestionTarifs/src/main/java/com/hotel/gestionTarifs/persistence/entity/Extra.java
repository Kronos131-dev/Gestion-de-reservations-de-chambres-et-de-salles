package com.hotel.gestionTarifs.persistence.entity;

import com.hotel.gestionTarifs.persistence.entity.ExtraId;
import com.hotel.gestionTarifs.persistence.entity.Prestation;
import com.hotel.gestionTarifs.persistence.entity.ServiceExtra;
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
}