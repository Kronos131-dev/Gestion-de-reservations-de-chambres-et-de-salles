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
    private Integer code;


    @Column(name = "description", length = 500)
    private String description;

    public Integer getIdStatut() {
        return idStatut;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }
}