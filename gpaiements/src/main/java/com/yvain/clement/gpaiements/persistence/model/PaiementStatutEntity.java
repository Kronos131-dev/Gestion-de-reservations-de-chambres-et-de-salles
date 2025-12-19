package com.yvain.clement.gpaiements.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name ="statut_paiement_type")
@Getter
@Setter
public class PaiementStatutEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_statut")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatut;


    @Column(name = "code", length = 50)
    private String code;


    @Column(name = "description", length = 500)
    private String description;
}