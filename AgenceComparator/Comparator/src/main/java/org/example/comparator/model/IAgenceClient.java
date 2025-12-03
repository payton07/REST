package org.example.comparator.model;

import org.example.comparator.classes.ClientComp;
import org.example.comparator.classes.ReservationComp;

import java.util.ArrayList;

public interface IAgenceClient {

    String getAgenceName();

    ArrayList<ResponseComp> consulterHotel(String identifiant, String password, String city, String arrivalDate, String departureDate, int minPrice, int maxPrice, int nbStars, int nbPeople);

    ReservationComp reserverChambre(ResponseComp response, ClientComp client, String arrivalDate, String departureDate);
}
