package com.hotel.gestionTarifs.persistence.entity;

import jakarta.persistence.*;

/**
 * Classe fictive créée juste pour respecter la structure de la base de données
 * et garder les relations JPA avec la table Prestation.
 */

@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @Column(name = "id_paiement")
    private Integer idpaiement;

    public Integer getIdpaiement() {
        return idpaiement;
    }

    public void setIdpaiement(Integer idpaiement) {
        this.idpaiement = idpaiement;
    }
}
