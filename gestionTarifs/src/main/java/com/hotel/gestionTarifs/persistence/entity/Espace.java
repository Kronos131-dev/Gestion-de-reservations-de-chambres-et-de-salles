package com.hotel.gestionTarifs.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "espace")
public class Espace {
    @Id
    @Column(name = "id_espace")
    private Integer idEspace;

    @Column(name = "prix_base")
    private BigDecimal prixBase;

    public BigDecimal getPrixBase() { return prixBase; }
}