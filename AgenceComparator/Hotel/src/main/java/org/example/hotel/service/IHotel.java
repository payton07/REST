package org.example.hotel.service;

import org.example.hotel.model.Client;
import org.example.hotel.model.Partenariat;
import org.example.hotel.types.HotelInfo;
import org.example.hotel.types.Offre;
import org.example.hotel.model.Reservation;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

public interface IHotel {

    ArrayList<Offre> rechercherOffres(String idAgence, String mdpAgence, String debut, String fin, int nbPersonnes, double minPrice, double maxPrice);

    Reservation reserver(String idAgence, String login, String mdpAgence, String offreId, Client client, String dateReservation);

    HotelInfo getHotelInfo(String idAgence, String login, String mdpAgence);

    Partenariat devenirPartenaire(String idAgence, String login, String mdpAgence);
}
