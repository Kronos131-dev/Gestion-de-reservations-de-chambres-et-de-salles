package com.yvain.clement.gpaiements.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name ="statut_paiement")
@Getter
@Setter
public class PaiementStatutEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "statut_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatut;


    @Column(name = "code")
    private String code;


    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description, length = 500")
    private String description;

    public Integer getIdSatut() {
        return idStatut;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
}