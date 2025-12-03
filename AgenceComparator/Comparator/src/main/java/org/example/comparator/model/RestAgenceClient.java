package org.example.comparator.model;

import org.example.comparator.classes.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestAgenceClient implements IAgenceClient {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public RestAgenceClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String getAgenceName() {
        String url = baseUrl + "/api/agence/name";
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public ArrayList<ResponseComp> consulterHotel(String identifiant, String password, String city, String arrivalDate, String departureDate, int minPrice, int maxPrice, int nbStars, int nbPeople) {
        String url = String.format("%s/api/agence/consulter?city=%s&arrivalDate=%s&departureDate=%s&minPrice=%d&maxPrice=%d&nbStars=%d&nbPeople=%d",
                baseUrl, city, arrivalDate, departureDate, minPrice, maxPrice, nbStars, nbPeople);
        
        ArrayList<Map<String, Object>> body = restTemplate.getForObject(url, ArrayList.class);
        ArrayList<ResponseComp> result = new ArrayList<>();
        
        if(body != null) {
            for (Map<String, Object> r : body) {
                // Extract address
                Map<String, String> adrMap = (Map<String, String>) r.get("address"); // field is 'address' in Agence Response
                AdresseComp adr = null;
                if (adrMap != null) {
                    adr = new AdresseComp(
                        adrMap.get("pays"),
                        adrMap.get("ville"),
                        adrMap.get("rue"),
                        adrMap.get("numero"),
                        adrMap.get("codepostal"),
                        adrMap.get("lieuDit")
                    );
                }

                ResponseComp rc = new ResponseComp(
                        (String) r.get("offreId"),
                        (String) r.get("agenceName"),
                        (String) r.get("hotelName"),
                        adr,
                        (Integer) r.get("nbStar"),
                        ((Number) r.get("price")).doubleValue(),
                        (Integer) r.get("numberOfBeds"),
                        (String) r.get("offreId"),
                        (String) r.get("numBedroom"),
                        (Integer) r.get("nbJour"),
                        (String) r.get("imageBase64"),
                        (String) r.get("imageType")
                );
                result.add(rc);
            }
        }
        return result;
    }

    @Override
    public ReservationComp reserverChambre(ResponseComp response, ClientComp client, String arrivalDate, String departureDate) {
        String url = baseUrl + "/api/agence/reserver";
        Map<String,Object> payload = new HashMap<>();


        // Construction response pour la demande
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("offreId", response.getOffreId());
        responseMap.put("hotelName", response.getHotelName());
        responseMap.put("agenceName", response.getAgenceName());
        responseMap.put("imageBase64", response.getImageBase64());
        responseMap.put("imageType", response.getImageType());
        responseMap.put("nbJour", response.getNbJour());
        responseMap.put("numBedroom", response.getNumBedroom());
        responseMap.put("price", response.getPrice());
        responseMap.put("numberOfBeds", response.getNumberOfBeds());
        responseMap.put("nbStar", response.getNbStar());

        
        // Construction client pour la demande
        Map<String, Object> clientMap = new HashMap<>();
        clientMap.put("id", client.getId());
        clientMap.put("nom", client.getNom());
        clientMap.put("prenom", client.getPrenom());
        clientMap.put("email", client.getEmail());
        clientMap.put("telephone", client.getTelephone());
        clientMap.put("numeroCarteCredit", client.getNumeroCarteCredit());
        clientMap.put("cardBackNumber", client.getCardBackNumber());

        payload.put("response", responseMap);
        payload.put("client", clientMap);
        payload.put("arrivalDate", arrivalDate);
        payload.put("departureDate", departureDate);

        Map<String, Object> res = restTemplate.postForObject(url, payload, Map.class);
        
        if (res == null) return null;
        
        // Map to ReservationComp
        String resId = String.valueOf(res.get("reservationId"));
        
        // Extraire  l'Offre
        Map<String, Object> offMap = (Map<String, Object>) res.get("offre");
        OffreComp offre = new OffreComp(
            (String) offMap.get("offreId"),
            (String) offMap.get("hotelNom"),
            (String) offMap.get("chambreId"),
            (String) offMap.get("dateDebut"),
            (String) offMap.get("dateFin"),
            ((Number) offMap.get("prixTotal")).doubleValue(),
            (Integer) offMap.get("nbLits"),
            (String) offMap.get("imageBase64"),
            (String) offMap.get("imageType")
        );

        // Extraire le Client
        Map<String, Object> cliMap = (Map<String, Object>) res.get("client");
        ClientComp cli = new ClientComp(
            (String) cliMap.get("id"),
            (String) cliMap.get("nom"),
            (String) cliMap.get("prenom"),
            (String) cliMap.get("email"),
            (String) cliMap.get("telephone"),
            ((Number) cliMap.get("numeroCarteCredit")).doubleValue(),
            (Integer) cliMap.get("cardBackNumber")
        );
        
        return new ReservationComp(resId, offre, cli, (String) res.get("dateReservation"), StatutComp.CONFIRMEE, null);
    }
}
