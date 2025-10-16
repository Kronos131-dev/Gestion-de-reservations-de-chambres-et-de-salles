package com.hotel.gestionClients.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    private Long idAdresse;

    @Column(name = "num", length = 10)
    private String num;

    @Column(name = "rue", length = 100)
    private String rue;

    @Column(name = "ville", length = 50)
    private String ville;

    @Column(name = "code_postal", length = 5)
    private String codePostal;

    @Column(name = "pays", length = 50)
    private String pays;

    public Adresse() {}

    public Adresse(String num, String rue, String ville, String codePostal, String pays) {
        this.num = num;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
    }

    public Long getIdAdresse() { return idAdresse; }
    public void setIdAdresse(Long idAdresse) { this.idAdresse = idAdresse; }

    public String getNum() { return num; }
    public void setNum(String num) { this.num = num; }

    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }
}
