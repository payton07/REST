package org.example.comparator.model;
import org.example.comparator.classes.*;
import org.example.comparator.config.ComparatorConfig;
import org.example.comparator.repository.OffreRepository;
import org.example.comparator.repository.ResponseCompRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AgenceComparator {
    private ArrayList<IAgenceClient> agences;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private ResponseCompRepository responseCompRepository;

    @Autowired
    private ComparatorConfig config;

    public AgenceComparator() {
        agences = new ArrayList<>();
    }

    @PostConstruct
    public void setUp(){
        Map<String, String> agencePorts = config.getAgences();
        if (agencePorts != null) {
            for (Map.Entry<String, String> entry : agencePorts.entrySet()) {
                String port = entry.getValue();
                String url = "http://localhost:" + port;
                this.agences.add(new RestAgenceClient(url));
            }
        }
    }

    public ArrayList<ResponseComp> consulterAgence(String city, String arrivalDate, String departureDate, int minPrice, int maxPrice, int nbStars, int nbPeople) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate arrival = LocalDate.parse(arrivalDate, inputFormatter);
        LocalDate departure = LocalDate.parse(departureDate, inputFormatter);

        String formattedArrival = arrival.format(formatter);
        String formattedDeparture = departure.format(formatter);

        ArrayList<ResponseComp> responses = agences.stream()
                .flatMap(o -> o.consulterHotel("", "", city, formattedArrival, formattedDeparture, minPrice, maxPrice, nbStars, nbPeople).stream())
                .sorted(Comparator.comparingDouble(ResponseComp::getPrice)
                        .thenComparing(ResponseComp::getNbStar))
                .collect(Collectors.toCollection(ArrayList::new));

        for (ResponseComp response : responses) {
            OffreComp offre = new OffreComp(
                    response.getOffreId(),
                    response.getHotelName(),
                    response.getNumBedroom(),
                    arrivalDate,
                    departureDate,
                    response.getPriceTotal(),
                    response.getNumberOfBeds(),
                    response.getImageBase64(),
                    response.getImageType()
            );
            offreRepository.save(offre); 
            responseCompRepository.save(response);
        }
        return responses;
    }

    public ReservationComp reserverChambre(ResponseComp response, ClientComp client, String arrivalDate, String departureDate) {
        IAgenceClient agence = getAgenceByName(response.getAgenceName());
        if(agence == null) {
            return null;
        }
        
        ReservationComp reservation = agence.reserverChambre(response, client, arrivalDate, departureDate);

        if (reservation != null) {
             try {
                offreRepository.deleteById(reservation.getOffre().getOffreId());
                responseCompRepository.deleteById(reservation.getOffre().getOffreId());
             } catch (Exception e) {
                 System.err.println("Could not delete temp offer/response: " + e.getMessage());
             }
        }
        return reservation;
    }

    private IAgenceClient getAgenceByName(String name){
        return agences.stream()
                .filter(o -> {
                    try {
                        return o.getAgenceName().equals(name);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
    }

    public ArrayList<IAgenceClient> getAgences() {
        return agences;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setAgences(ArrayList<IAgenceClient> agences) {
        this.agences = agences;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public OffreRepository getOffreRepository() {
        return offreRepository;
    }

}
