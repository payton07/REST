package org.example.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Partenaire {
    @Id
    private String idAgence;
    private int pourcentage;

    public Partenaire(String idAgence, int pourcentage) {
        this.idAgence = idAgence;
        this.pourcentage = pourcentage;
    }
    public Partenaire(){}
    public String getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(String idAgence) {
        this.idAgence = idAgence;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }


    @Override
    public boolean equals(Object p) {
        if (this == p)
            return true;
        if (p == null || getClass() != p.getClass())
            return false;
        Partenaire partenaire = (Partenaire) p;
        return this.idAgence.equals(partenaire.getIdAgence()) && this.pourcentage == partenaire.getPourcentage();
    }
}
