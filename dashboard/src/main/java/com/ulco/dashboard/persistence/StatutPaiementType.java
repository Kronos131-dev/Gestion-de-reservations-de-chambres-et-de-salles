package com.ulco.dashboard.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "statut_paiement_type", schema = "public")
public class StatutPaiementType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statut")
    private Integer id_statut;

    @Column(name = "code")
    private String code;

    // Getters
    public Integer getId_statut() {
        return id_statut;
    }

    public String getCode() {
        return code;
    }
}
