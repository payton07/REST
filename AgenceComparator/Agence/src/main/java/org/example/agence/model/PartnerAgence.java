package org.example.agence.model;

public class PartnerAgence {
    private String hotelId;
    private int pourcentage;

    public PartnerAgence(String hotelId,int pourcentage) {
        this.hotelId = hotelId;
        this.pourcentage = pourcentage;
    }
    public PartnerAgence(){}
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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
        PartnerAgence partenaire = (PartnerAgence) p;
        return this.hotelId.equals(partenaire.getHotelId()) && this.pourcentage == partenaire.getPourcentage();
    }
}