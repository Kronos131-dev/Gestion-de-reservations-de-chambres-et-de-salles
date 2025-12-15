package com.hotel.gestionTarifs.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "saison")
public class Saison {
    @Id
    @Column(name = "id_saison")
    private Integer idSaison;

    @Column(name = "coeff_prix")
    private BigDecimal coeffPrix;

    public BigDecimal getCoeffPrix() { return coeffPrix; }
}