package com.hotel.gestionTarifs.persistence;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ExtraId implements Serializable {

    @Column(name = "id_service")
    private Integer idService;

    @Column(name = "id_prestation")
    private Integer idPrestation;

    public ExtraId() {}

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public Integer getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(Integer idPrestation) {
        this.idPrestation = idPrestation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExtraId)) return false;
        ExtraId extraId = (ExtraId) o;
        return Objects.equals(idService, extraId.idService) && Objects.equals(idPrestation, extraId.idPrestation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idService, idPrestation);
    }
}
