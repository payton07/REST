package org.example.agence.model;

import org.example.agence.classes.AdresseAgence;

public class Response {
    private String AgenceName;
    private String HotelName;
    private AdresseAgence address;
    private int nbStar ;
    private double price ;
    private int numberOfBeds ;
    private String numBedroom;
    private String OffreId;
    private int nbJour;
    private String imageBase64;
    private String imageType;

    public Response(String AgenceName, String HotelName, AdresseAgence address, int nbStar, double price, int numberOfBeds, String OffreId, String numBedroom, int nbJour, String imageBase64, String imageType) {
        this.AgenceName = AgenceName;
        this.HotelName = HotelName;
        this.address = address;
        this.nbStar = nbStar;
        this.price = price;
        this.numberOfBeds = numberOfBeds;
        this.OffreId = OffreId;
        this.numBedroom = numBedroom;
        this.nbJour = nbJour;
        this.imageBase64 = imageBase64;
        this.imageType = imageType;
    }

    public Response(){}
    public String getHotelName() {
        return HotelName;
    }


    public int getNbStar() {
        return nbStar;
    }

    public double getPrice() {
        return price;
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

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public String getNumBedroom() {
        return numBedroom;
    }

    public String getOffreId() {
        return OffreId;
    }

    public String getAgenceName() {
        return AgenceName;
    }

    public void setAgenceName(String agenceName) {
        AgenceName = agenceName;
    }

    public void setHotelName(String name) {
        this.HotelName = name;
    }

    public AdresseAgence getAddress() {
        return address;
    }

    public void setAddress(AdresseAgence address) {
        this.address = address;
    }

    public void setNbStar(int nbStar) {
        this.nbStar = nbStar;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getNbJour() {
        return nbJour;
    }

    public void setNbJour(int nbJour) {
        this.nbJour = nbJour;
    }

    public void setNumBedroom(String numBedroom) {
        this.numBedroom = numBedroom;
    }

    public void setOffreId(String offreId) {
        OffreId = offreId;
    }

    @Override
    public boolean equals(Object p) {
        if (this == p)
            return true;
        if (p == null || getClass() != p.getClass())
            return false;
        Response res = (Response) p;
        return res.AgenceName.equals(AgenceName)
                && res.getHotelName().equals(HotelName)
                && res.getAddress().equals(address)
                && res.getNbStar() == nbStar
                && res.getPrice() == price
                && res.getNumberOfBeds() == numberOfBeds
                && res.getNumBedroom().equals(numBedroom)
                && res.getOffreId().equals(OffreId)
                && res.getNbJour() == nbJour;
    }
}