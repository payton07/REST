package org.example.hotel.model;

public class Partenariat {
    private int pourcentage;
    private String HotelId;

    public Partenariat(String HotelId,int pourcentage) {
        this.HotelId = HotelId;
        this.pourcentage = pourcentage;
    }
    public Partenariat(){}
    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getHotelId() {
        return HotelId;
    }

    public void setHotelId(String hotelId) {
        HotelId = hotelId;
    }
}
