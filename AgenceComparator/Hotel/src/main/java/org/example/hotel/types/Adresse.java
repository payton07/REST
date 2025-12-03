package org.example.hotel.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Adresse {
        private String pays;
        private String ville;
        private String rue;
        private String numero;
        private String Codepostal;
        private String lieuDit;

    public Adresse(String pays, String ville, String rue, String numero, String codepostal, String lieuDit) {
        this.pays = pays;
        this.ville = ville;
        this.rue = rue;
        this.numero = numero;
        this.Codepostal = codepostal;
        this.lieuDit = lieuDit;
    }

    public Adresse(){}

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodepostal() {
        return Codepostal;
    }

    public void setCodepostal(String codepostal) {
        Codepostal = codepostal;
    }

    public String getLieuDit() {
        return lieuDit;
    }

    public void setLieuDit(String lieuDit) {
        this.lieuDit = lieuDit;
    }
}
