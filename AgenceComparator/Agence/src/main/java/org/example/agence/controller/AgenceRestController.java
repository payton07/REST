package org.example.agence.controller;

import org.example.agence.classes.ClientAgence;
import org.example.agence.classes.ReservationAgence;
import org.example.agence.config.AgenceConfig;
import org.example.agence.model.Response;
import org.example.agence.service.impl.Agence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

@RestController
@RequestMapping("/api/agence")
public class AgenceRestController {

    private Agence agence;

    @Autowired
    private AgenceConfig agenceConfig;

    @PostConstruct
    public void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("INITIALISATION DE L'AGENCE (REST CONTROLLER)");
        System.out.println("Veuillez choisir l'instance d'agence a creer :");
        System.out.println("1. StreetMan (Predefini)");
        System.out.println("2. Expedia (Predefini)");
        System.out.println("3. Personnalise (Entrer les parametres)");
        System.out.print("Votre choix : ");

        int choice = 1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        } else {
            scanner.nextLine();
            System.out.println("Entree invalide, utilisation de StreetMan par defaut.");
        }

        String nom;
        String password;
        String description;

        switch (choice) {
            case 2:
                // Expedia
                System.out.println("Creation de l'agence Expedia...");
                nom = "Expedia";
                password = "admin";
                description = "Agence Expedia";
                break;
            case 3:
                // CUSTOM
                System.out.println("Creation d'une agence personnalisee.");
                System.out.print("Nom de l'agence : ");
                nom = scanner.nextLine();

                System.out.print("Mot de passe : ");
                password = scanner.nextLine();

                System.out.print("Description : ");
                description = scanner.nextLine();
                break;
            case 1:
            default:
                // StreetMan
                System.out.println("Creation de l'agence StreetMan...");
                nom = "StreetMan";
                password = "admin";
                description = "Agence StreetMan";
                break;
        }

        Map<String, String> hotelPorts = agenceConfig.getHotels();
        this.agence = new Agence(nom, password, description, hotelPorts);
        
        System.out.println("Agence " + this.agence.getName() + " initialisee avec succes !");
        System.out.println("--------------------------------------------------");
    }

    @GetMapping("/consulter")
    public ArrayList<Response> consulter(@RequestParam String city,
                                         @RequestParam String arrivalDate,
                                         @RequestParam String departureDate,
                                         @RequestParam(required = false, defaultValue = "0") int minPrice,
                                         @RequestParam(required = false, defaultValue = "999999") int maxPrice,
                                         @RequestParam(required = false, defaultValue = "0") int nbStars,
                                         @RequestParam(required = false, defaultValue = "1") int nbPeople) {
        if (agence == null) throw new RuntimeException("Agence service not available");
        return agence.consulterHotel(city, arrivalDate, departureDate, minPrice, maxPrice, nbStars, nbPeople);
    }

    public static class ReservationRequest {
        public Response response;
        public ClientAgence client;
        public String arrivalDate;
        public String departureDate;
    }

    @PostMapping("/reserver")
    public ReservationAgence reserver(@RequestBody ReservationRequest req) {
        if (agence == null) throw new RuntimeException("Agence service not available");
        return agence.reserverChambre(req.response, req.client, req.arrivalDate, req.departureDate);
    }

    @GetMapping("/name")
    public String getName() {
        if (agence == null) throw new RuntimeException("Agence service not available");
        return agence.getAgenceName();
    }
}