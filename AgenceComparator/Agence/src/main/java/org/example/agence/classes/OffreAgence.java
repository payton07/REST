package org.example.agence.classes;
import java.time.format.DateTimeFormatter;

public class OffreAgence {    private String offreId ;
    private String hotelNom;
    private String chambreId;
    private String dateDebut;
    private String dateFin;
    private double prixTotal;
    private int nbLits;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");;
    private String imageBase64;
    private String imageType;

    public OffreAgence(String offreId, String hotelNom, String chambreId, String dateDebut, String dateFin, double prixTotal, int nbLits, String imageBase64, String imageType) {
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
    public OffreAgence(){}
    public String getOffreId() {
        return offreId;
    }

    public String getHotelNom() {
        return hotelNom;
    }

    public String getChambreId() {
        return chambreId;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public int getNbLits() {
        return nbLits;
    }

    public void setOffreId(String offreId) {
        this.offreId = offreId;
    }

    public void setHotelNom(String hotelNom) {
        this.hotelNom = hotelNom;
    }

    public void setChambreId(String chambreId) {
        this.chambreId = chambreId;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setNbLits(int nbLits) {
        this.nbLits = nbLits;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String toString() {
        return hotelNom + " - " + chambreId + " : " + prixTotal + "€ du " + dateDebut + " au " + dateFin;
    }
}