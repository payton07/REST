package org.example.comparator.classes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import static java.time.temporal.ChronoUnit.DAYS;

@Entity
public class OffreComp {
    @Id
    private String offreId ;
    private String hotelNom;
    private String chambreId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;
    private int nbLits;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Lob
    private String imageBase64;
    private String imageType;

    public OffreComp() {
    }

    public OffreComp(String offreId, String hotelNom, String chambreId, String dateDebut, String dateFin, double prix, int nbLits,String imageBase64,String imageType) {
        this.offreId = offreId;
        this.hotelNom = hotelNom;
        this.chambreId = chambreId;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateDebut = LocalDate.parse(dateDebut,formatter);
        this.dateFin = LocalDate.parse(dateFin,formatter);
        this.prix = prix;
        this.nbLits = nbLits;
        this.imageBase64 = imageBase64;
        this.imageType = imageType;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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

    public String getOffreId() {
        return offreId;
    }

    public String getHotelNom() {
        return hotelNom;
    }

    public String getChambreId() {
        return chambreId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
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

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }


    public void setNbLits(int nbLits) {
        this.nbLits = nbLits;
    }

    public long getNbJour(){
        return DAYS.between(dateDebut,dateFin);
    }

    @Override
    public String toString() {
        return hotelNom + " - " + chambreId + " : " + prix*getNbJour() + "€ du " + dateDebut + " au " + dateFin;
    }
}
