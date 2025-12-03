package org.example.hotel.types;

public class HotelInfo {
    private String nom;
    private Adresse Adresse;
    private int nbEtoile;

    public HotelInfo(String nom, Adresse Adresse,int nbEtoile) {
        this.nom = nom;
        this.Adresse = Adresse;
        this.nbEtoile = nbEtoile;
    }

    public HotelInfo() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public org.example.hotel.types.Adresse getAdresse() {
        return Adresse;
    }

    public void setAdresse(org.example.hotel.types.Adresse adresse) {
        Adresse = adresse;
    }

    public int getNbEtoile() {
        return nbEtoile;
    }

    public void setNbEtoile(int nbEtoile) {
        this.nbEtoile = nbEtoile;
    }
}
