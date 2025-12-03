package org.example.agence.service.interfaces;

import org.example.agence.classes.ClientAgence;
import org.example.agence.classes.ReservationAgence;
import org.example.agence.model.Response;
import java.util.ArrayList;

public interface IAgence {

    ArrayList<Response> consulterHotel(String city, String arrivalDate, String departureDate, int minPrice, int maxPrice, int nbStars, int nbPeople);

    ReservationAgence reserverChambre(Response response, ClientAgence client, String arrivalDate, String departureDate);

    String getAgenceName();
}