package com.ulco.dashboard.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "adresse", schema = "public")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    private Long id_adresse;

    @Column(name = "num")
    private String num;

    @Column(name = "rue")
    private String rue;

    @Column(name = "ville")
    private String ville;

    // getters
    public Long getId_adresse() { return id_adresse; }
    public String getNum() { return num; }
    public String getRue() { return rue; }
    public String getVille() { return ville; }
}
