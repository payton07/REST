#!/bin/zsh
#sdk use java 8.0.392-zulu
if (($# ==2))
then
cd ../src/main/java/ &&
wsimport -keep -p org.example.comparator.agences.$1 $2 &&
echo "package org.example.comparator.Clients;

      import org.example.comparator.agences.$1.*;
      import org.example.comparator.classes.*;
      import org.example.comparator.model.IAgenceClient;
      import org.example.comparator.model.ResponseComp;

      import java.util.ArrayList;
      import java.util.List;
      import java.util.stream.Collectors;

      public class $1Client implements IAgenceClient {
          private IAgence port;

          public $1Client() {
              AgenceService service = new AgenceService();
              port = service.getAgencePort();
          }

          @Override
          public String getAgenceName() {
              return port.getAgenceName();
          }

          @Override
          public ArrayList<ResponseComp> consulterHotel(String identifiant, String password, String city, String arrivalDate, String departureDate, int minPrice, int maxPrice, int nbStars, int nbPeople) {
              List<Response> responses = port.consulterHotel(city, arrivalDate, departureDate, minPrice, maxPrice, nbStars, nbPeople);
              return responses.stream()
                      .map(a-> new ResponseComp(a.getAgenceName(),a.getHotelName(),
                              new AdresseComp(a.getAddress().getPays(),
                                      a.getAddress().getVille(),
                                      a.getAddress().getRue(),
                                      a.getAddress().getNumero(),
                                      a.getAddress().getCodepostal(),
                                      a.getAddress().getLieuDit()
                              ),a.getNbStar(),a.getPrice(),a.getNumberOfBeds(),a.getOffreId(),a.getNumBedroom(),a.getNbJour(),a.getImageBase64(),a.getImageType()))
                      .collect(Collectors.toCollection(ArrayList::new));
          }

          @Override
          public ReservationComp reserverChambre(ResponseComp response, ClientComp client, String arrivalDate, String departureDate) {
              AdresseComp adComp = response.getAdresseHotel(); // Adresse actu

              AdresseAgence adr = new AdresseAgence(); // Adresse convertie
              adr.setCodepostal(adComp.getCodepostal());
              adr.setLieuDit(adComp.getLieuDit());
              adr.setNumero(adComp.getNumero());
              adr.setPays(adComp.getPays());
              adr.setRue(adComp.getRue());
              adr.setVille(adComp.getVille());
              adr.setRue(adComp.getRue());

              // Creation de la version agence service de response

              Response resp = new Response();
              resp.setNbStar(response.getNbStar());
              resp.setOffreId(response.getOffreId());
              resp.setNumBedroom(response.getNumBedroom());
              resp.setNbJour(response.getNbJour());
              resp.setPrice(response.getPrice());
              resp.setAgenceName(response.getAgenceName());
              resp.setHotelName(response.getHotelName());
              resp.setNumberOfBeds(response.getNumberOfBeds());
              resp.setAddress(adr);

              // Creation de la version agence service de client

              ClientAgence cli = new ClientAgence();
              cli.setId(client.getId());
              cli.setEmail(client.getEmail());
              cli.setNom(client.getNom());
              cli.setPrenom(client.getPrenom());
              cli.setCardBackNumber(client.getCardBackNumber());
              cli.setNumeroCarteCredit(client.getNumeroCarteCredit());
              cli.setTelephone(client.getTelephone());

              /*
              * Sans les commentaires sais plus où j'en suis
              * */

              // Recuperation de la reservation version Agence client
              ReservationAgence resr = port.reserverChambre(resp,cli,arrivalDate,departureDate);
              ClientAgence cliAg = resr.getClient();
              OffreAgence offAg = resr.getOffre();
              PaiementInfoAgence paieAg = resr.getPaiement();

              // Creation de la version Comparator de la reservation

              ClientComp cliCmp = new ClientComp(cliAg.getId(),cliAg.getNom(),cliAg.getPrenom(),cliAg.getEmail(),cliAg.getTelephone(),cliAg.getNumeroCarteCredit(),cliAg.getCardBackNumber());
              OffreComp offCmp = new OffreComp(offAg.getOffreId(),offAg.getHotelNom(),offAg.getChambreId(),offAg.getDateDebut(),offAg.getDateFin(),offAg.getPrixTotal(),offAg.getNbLits(),offAg.getImageBase64(),offAg.getImageType());
              PaiementInfoComp paieCmp = new PaiementInfoComp(paieAg.getNumeroCarteCredit(),paieAg.getCardBackNumber());

              return new ReservationComp(resr.getReservationId(),offCmp,cliCmp,resr.getDateReservation(),StatutComp.CONFIRMEE,paieCmp);
          }

      }
" > org/example/comparator/Clients/$1Client.java &&
cd ../../../utils/ &&
 chmod +x insererLigne.sh &&
 ./insererLigne.sh ../src/main/java/org/example/comparator/model/AgenceComparator.java $1
else
  echo "Usage : ./script Nom-Agence Lien-WSDL"
fi