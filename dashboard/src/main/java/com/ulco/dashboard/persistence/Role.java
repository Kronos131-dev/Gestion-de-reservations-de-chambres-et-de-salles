package com.ulco.dashboard.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "role", schema = "public")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id_role;

    @Column(name = "nom")
    private String nom_role;

    // getters
    public Long getId_role() { return id_role; }
    public String getNom_role() { return nom_role; }
}
