package org.example.comparator.classes;

public class HotelInfoComp {
    private String nom;
    private AdresseComp Adresse;
    private int nbEtoile;

    public HotelInfoComp(String nom, AdresseComp Adresse,int nbEtoile) {
        this.nom = nom;
        this.Adresse = Adresse;
        this.nbEtoile = nbEtoile;
    }

    //public HotelInfo() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public AdresseComp getAdresse() {
        return Adresse;
    }

    public void setAdresse(AdresseComp adresse) {
        Adresse = adresse;
    }

    public int getNbEtoile() {
        return nbEtoile;
    }

    public void setNbEtoile(int nbEtoile) {
        this.nbEtoile = nbEtoile;
    }
}
