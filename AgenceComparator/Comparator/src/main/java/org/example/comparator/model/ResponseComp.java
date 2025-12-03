package org.example.comparator.model;

import org.example.comparator.classes.AdresseComp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Lob;

@Entity
public class ResponseComp {
    @Id
    private String id;
    private String agenceName;
    private String hotelName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_hotel_id")
    private AdresseComp adresseHotel;
    private int nbStar ;
    private double price ;
    private int numberOfBeds ;
    private String numBedroom;
    private String OffreId;
    private int nbJour;
    @Lob
    private String imageBase64;
    private String imageType;

    public ResponseComp(String id,String agenceName,String hotelName, AdresseComp adresseHotel, int nbStar, double price, int numberOfBeds, String OffreId, String numBedroom,int nbJour,String imageBase64,String imageType) {
        this.agenceName = agenceName;
        this.id = id;
        this.hotelName = hotelName;
        this.adresseHotel = adresseHotel;
        this.nbStar = nbStar;
        this.price = price;
        this.numberOfBeds = numberOfBeds;
        this.OffreId = OffreId;
        this.numBedroom = numBedroom;
        this.nbJour = nbJour;
        this.imageBase64 = imageBase64;
        this.imageType = imageType;
    }

    public ResponseComp() {
    }

    public String getAgenceName() {
        return agenceName;
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

    public void setAgenceName(String agenceName) {
        this.agenceName = agenceName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public AdresseComp getAdresseHotel() {
        return adresseHotel;
    }

    public void setAdresseHotel(AdresseComp adresseHotel) {
        this.adresseHotel = adresseHotel;
    }

    public int getNbStar() {
        return nbStar;
    }

    public void setNbStar(int nbStar) {
        this.nbStar = nbStar;
    }

    public double getPrice() {
        return price;
    }

    public int getNbJour() {
        return nbJour;
    }

    public void setNbJour(int nbJour) {
        this.nbJour = nbJour;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getNumBedroom() {
        return numBedroom;
    }

    public void setNumBedroom(String numBedroom) {
        this.numBedroom = numBedroom;
    }

    public String getOffreId() {
        return OffreId;
    }

    public void setOffreId(String offreId) {
        OffreId = offreId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPriceTotal() {
        return price * nbJour;
    }

}
