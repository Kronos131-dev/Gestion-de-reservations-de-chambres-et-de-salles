package com.hotel.gestionClients.business.dto;

import java.io.Serializable;

public class AdresseDTO implements Serializable {


    private Long id;
    private String num;
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;

    public AdresseDTO() {
    }

    public AdresseDTO(Long id, String num, String rue, String ville, String codePostal, String pays) {
        this.id = id;
        this.num = num;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}
