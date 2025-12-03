package org.example.hotel.types;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Offre {
    @Id
    private String offreId ;
    private String hotelNom;
    private String chambreId;
    private String dateDebut;
    private String dateFin;
    private double prixTotal;
    private int nbLits;
    @Lob
    private String imageBase64;
    private String imageType;

    public Offre(String offreId, String hotelNom, String chambreId, String dateDebut, String dateFin, double prixTotal, int nbLits,String imageBase64,String imageType) {
        this.offreId = offreId;
        this.hotelNom = hotelNom;
        this.chambreId = chambreId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.nbLits = nbLits;
        this.imageBase64 = imageBase64;
        this.imageType = imageType;
    }

    public Offre() {}

    public String getOffreId() {
        return offreId;
    }

    public void setOffreId(String offreId) {
        this.offreId = offreId;
    }

    public String getHotelNom() {
        return hotelNom;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setHotelNom(String hotelNom) {
        this.hotelNom = hotelNom;
    }

    public String getChambreId() {
        return chambreId;
    }

    public void setChambreId(String chambreId) {
        this.chambreId = chambreId;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getNbLits() {
        return nbLits;
    }

    public void setNbLits(int nbLits) {
        this.nbLits = nbLits;
    }
}
