package org.example.agence.service.impl;

import org.example.agence.classes.*;
import org.example.agence.model.Response;
import org.example.agence.service.interfaces.IAgence;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class Agence implements IAgence {
    private String name;
    private String description;
    private Map<String, String> hotelPorts; // Name -> Port
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String password;
    private final String Reservation_date = LocalDate.now().format(formatter).toString();
    private RestTemplate restTemplate;


    public Agence(String name, String password, String description, Map<String, String> hotelPorts) {
        this.name = name;
        this.description = description;
        this.password = password;
        this.hotelPorts = hotelPorts;
        
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(30000); // 30s
        factory.setReadTimeout(30000); // 30s
        this.restTemplate = new RestTemplate(factory);
    }
    
    public Agence() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(30000);
        factory.setReadTimeout(30000);
        this.restTemplate = new RestTemplate(factory);
    }

    @Override
    public ArrayList<Response> consulterHotel(String city, String arrivalDate, String departureDate, int minPrice, int maxPrice, int nbStars, int nbPeople) {
        LocalDate arrival = LocalDate.parse(arrivalDate, formatter);
        LocalDate departure = LocalDate.parse(departureDate, formatter);
        int nbJour = (int) DAYS.between(arrival, departure);

        List<Response> allResponses = new ArrayList<>();

        hotelPorts.entrySet().parallelStream().forEach(entry -> {
            String hotelName = entry.getKey();
            String port = entry.getValue();
            String url = String.format("http://localhost:%s/api/hotel/search?idAgence=%s&mdpAgence=%s&debut=%s&fin=%s&nbPersonnes=%d&minPrice=%d&maxPrice=%d",
                    port, this.name, this.password, arrivalDate, departureDate, nbPeople, minPrice, maxPrice);

            try {
                ArrayList<Map<String, Object>> hotelOffers = restTemplate.getForObject(url, ArrayList.class);

                if (hotelOffers != null) {
                    Map<String, Object> hotelInfo = getHotelInfoREST(port);
                    for (Map<String, Object> offerData : hotelOffers) {
                        if (hotelInfo != null) {
                            Map<String, Object> adresse = (Map<String, Object>) hotelInfo.get("adresse");
                            int stars = (Integer) hotelInfo.get("nbEtoile");
                            String ville = (String) adresse.get("ville");

                            if (ville.equalsIgnoreCase(city) && stars >= nbStars) {
                                Response res = mapToResponse(offerData, hotelInfo, nbJour, hotelName);
                                allResponses.add(res);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                System.err.println("Error contacting hotel " + hotelName + " on port " + port + ": " + e.toString());
                e.printStackTrace(); 
            }
        });
        
        return new ArrayList<>(allResponses);
    }

    private Map<String, Object> getHotelInfoREST(String port) {
        String url = String.format("http://localhost:%s/api/hotel/info?idAgence=%s&login=%s&mdpAgence=%s", port, this.name, "admin", this.password);
        System.out.println("URL pour info "+url);
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            return null;
        }
    }

    private Response mapToResponse(Map<String, Object> offerData, Map<String, Object> hotelInfo, int nbJour, String hotelName) {
        Map<String, Object> adresseMap = (Map<String, Object>) hotelInfo.get("adresse");
        AdresseAgence adr = new AdresseAgence();
        adr.setCodepostal((String) adresseMap.get("codePostal"));
        adr.setNumero((String) adresseMap.get("numero"));
        adr.setLieuDit((String) adresseMap.get("lieuDit"));
        adr.setVille((String) adresseMap.get("ville"));
        adr.setPays((String) adresseMap.get("pays"));
        adr.setRue((String) adresseMap.get("rue"));


        String offreId = (String) offerData.get("offreId");
        String chambreId = (String) offerData.get("chambreId");
        double prix = ((Number) offerData.get("prixTotal")).doubleValue();
        int lits = (Integer) offerData.get("nbLits");
        String image = (String) offerData.get("imageBase64");
        String type = (String) offerData.get("imageType");

        Response res = new Response(this.name, (String) hotelInfo.get("nom"), adr, (Integer) hotelInfo.get("nbEtoile"), prix, lits, offreId, chambreId, nbJour, image, type);
        return res;
    }

    @Override
    public ReservationAgence reserverChambre(Response response, ClientAgence client, String arrivalDate, String departureDate) {
        String hotelName = response.getHotelName();
        String port = hotelPorts.get(hotelName);
        if (port == null) {
             for (Map.Entry<String, String> entry : hotelPorts.entrySet()) {
                 if (entry.getKey().equalsIgnoreCase(hotelName)) {
                     port = entry.getValue();
                     break;
                 }
             }
             if(port == null) return null;
        }

        String url = String.format("http://localhost:%s/api/hotel/reserver", port);

        Map<String, Object> payload = new HashMap<>();
        payload.put("idAgence", this.name);
        payload.put("login", "admin");
        payload.put("mdpAgence", this.password);
        payload.put("offreId", response.getOffreId());
        payload.put("date", Reservation_date);
        
        Map<String, Object> clientMap = new HashMap<>();
        clientMap.put("id", client.getId());
        clientMap.put("nom", client.getNom());
        clientMap.put("prenom", client.getPrenom());
        clientMap.put("email", client.getEmail());
        clientMap.put("telephone", client.getTelephone());
        clientMap.put("carteCredit", client.getNumeroCarteCredit());
        
        payload.put("client", clientMap);

        try {
            Map<String, Object> reservationData = restTemplate.postForObject(url, payload, Map.class);
            if (reservationData != null) {
                 String resId = String.valueOf(reservationData.get("reservationId"));
                 
                 StatutAgence statut = StatutAgence.CONFIRMEE;
                 PaiementInfoAgence pInfo = new PaiementInfoAgence(client.getNumeroCarteCredit(), client.getCardBackNumber());
                 
                 OffreAgence off = new OffreAgence(response.getOffreId(), response.getHotelName(), response.getNumBedroom(), arrivalDate, departureDate, response.getPrice(), response.getNumberOfBeds(), response.getImageBase64(), response.getImageType());
                 
                 return new ReservationAgence(resId, off, client, Reservation_date, statut, pInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAgenceName() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}