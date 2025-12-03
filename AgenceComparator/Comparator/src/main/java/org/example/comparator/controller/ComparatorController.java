package org.example.comparator.controller;
import org.example.comparator.classes.ClientComp;
import org.example.comparator.classes.OffreComp;
import org.example.comparator.classes.ReservationComp;
import org.example.comparator.model.AgenceComparator;
import org.example.comparator.model.ResponseComp;
import org.example.comparator.repository.OffreRepository;
import org.example.comparator.repository.ResponseCompRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ComparatorController {

    @Autowired
    private AgenceComparator agenceComparator;

    @Autowired
    private ResponseCompRepository ResComprepository;

    @Autowired
    private OffreRepository offreRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/hotels")
    public String showHotels(Model model,
                             @RequestParam(name = "ville", required = false) String ville,
                             @RequestParam(name = "dateDebut", required = false) String dateDebut,
                             @RequestParam(name = "dateFin", required = false) String dateFin,
                             @RequestParam(name = "prixMin", required = false) Integer prixMin,
                             @RequestParam(name = "prixMax", required = false) Integer prixMax,
                             @RequestParam(name = "etoiles", required = false) Integer etoiles,
                             @RequestParam(name = "nombrePersonnes", required = false) Integer nombrePersonnes) throws IOException {

        System.out.println("Search criteria: ville=" + ville + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prixMin=" + prixMin + ", prixMax=" + prixMax + ", etoiles=" + etoiles + ", nombrePersonnes=" + nombrePersonnes);

        if (ville != null && dateDebut != null && dateFin != null && nombrePersonnes != null) {
            ArrayList<ResponseComp> offreAgence = agenceComparator.consulterAgence(ville,dateDebut,dateFin,prixMin,prixMax,etoiles,nombrePersonnes);
            model.addAttribute("hotels", offreAgence);
            model.addAttribute("debut", dateDebut);
            model.addAttribute("fin", dateFin);
        }
        return "hotels";
    }

    @GetMapping("/reserver")
    public String showReservationPage(@RequestParam("id") String id, Model model) {
        ResponseComp ResComp = ResComprepository.findById(id).orElse(null);
        if (ResComp == null) {
            System.out.println("Offer for reservation not found");
            return "error";
        }
        model.addAttribute("hotel", ResComp);
        return "reservation";
    }

    @PostMapping("/confirmer-reservation")
    public String confirmReservation(@RequestParam("hotelId") String ResCompId,
                                     @RequestParam("nom") String nom,
                                     @RequestParam("prenom") String prenom,
                                     @RequestParam("email") String email,
                                     @RequestParam("telephone") String telephone,
                                     @RequestParam("numeroCarteCredit") String numeroCarteCredit,
                                     @RequestParam("cardBackNumber") String cardBackNumber,
                                     Model model) {
        ResponseComp ResComp = ResComprepository.findById(ResCompId).orElse(null);
        if (ResComp == null) {
            System.out.println("Hotel not found for confirmation");
            return "error";
        }

        OffreComp offre = offreRepository.findById(ResComp.getOffreId()).orElse(null);
        if (offre == null) {
            System.out.println("Offre not found for confirmation");
            return "error";
        }

        String clientId = java.util.UUID.randomUUID().toString();
        ClientComp client = new ClientComp(clientId, nom, prenom, email, telephone, Double.parseDouble(numeroCarteCredit), Integer.parseInt(cardBackNumber));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String arrivalDate = offre.getDateDebut().format(formatter);
        String departureDate = offre.getDateFin().format(formatter);

        ReservationComp reservation = agenceComparator.reserverChambre(ResComp, client, arrivalDate, departureDate);

        model.addAttribute("reservation", reservation);
        return "confirmation";
    }
}
