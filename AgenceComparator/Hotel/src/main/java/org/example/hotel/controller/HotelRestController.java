package org.example.hotel.controller;

import org.example.hotel.model.Client;
import org.example.hotel.model.Reservation;
import org.example.hotel.model.Partenariat;
import org.example.hotel.service.Hotel;
import org.example.hotel.types.Offre;
import org.example.hotel.types.HotelInfo;
import org.example.hotel.types.Adresse;
import org.example.hotel.repository.ChambreRepository;
import org.example.hotel.repository.ReservationRepository;
import org.example.hotel.repository.ClientRepository;
import org.example.hotel.repository.PartenaireRepository;
import org.example.hotel.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

@RestController
@RequestMapping("/api/hotel")
public class HotelRestController {

    private Hotel hotel;

    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PartenaireRepository partenaireRepository;
    @Autowired
    private OffreRepository offreRepository;

    @PostConstruct
    public void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("INITIALISATION DE L'HOTEL (REST CONTROLLER)");
        System.out.println("Veuillez choisir l'instance d'hotel a creer :");
        System.out.println("1. Belaroia (Predefini)");
        System.out.println("2. Ibis (Predefini)");
        System.out.println("3. Personnalise (Entrer les parametres)");
        System.out.print("Votre choix : ");

        int choice = 1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        } else {
            scanner.nextLine();
            System.out.println("Entree invalide, utilisation de Belaroia par defaut.");
        }

        String nom;
        Adresse adresse;
        int nbEtoiles;
        String imagePath;

        switch (choice) {
            case 2:
                // IBIS
                System.out.println("Creation de l'hotel Ibis...");
                nom = "Ibis";
                adresse = new Adresse("france", "montpellier", "albert", "321", "34090", "-");
                nbEtoiles = 4;
                imagePath = "src/main/resources/static/images/image3.jpg";
                break;
            case 3:
                // CUSTOM
                System.out.println("Creation d'un hotel personnalise.");
                System.out.print("Nom de l'hotel : ");
                nom = scanner.nextLine();

                System.out.print("Nombre d'etoiles : ");
                nbEtoiles = 0;
                if (scanner.hasNextInt()) {
                    nbEtoiles = scanner.nextInt();
                    scanner.nextLine();
                }

                System.out.println("--- Adresse ---");
                System.out.print("Numero de rue : ");
                String numero = scanner.nextLine();
                System.out.print("Nom de rue : ");
                String rue = scanner.nextLine();
                System.out.print("Code Postal : ");
                String codePostal = scanner.nextLine();
                System.out.print("Ville : ");
                String ville = scanner.nextLine();
                System.out.print("Pays : ");
                String pays = scanner.nextLine();

                adresse = new Adresse(pays, ville, rue, numero, codePostal, "-");
                // Image par defaut pour CUSTOM
                imagePath = "src/main/resources/static/images/image.jpg"; 
                break;
            case 1:
            default:
                // BELAROIA
                System.out.println("Creation de l'hotel Belaroia...");
                nom = "Belaroia";
                adresse = new Adresse("france", "montpellier", "albert", "78", "34000", "-");
                nbEtoiles = 4;
                imagePath = "src/main/resources/static/images/image.jpg";
                break;
        }

        File imgFile = new File(imagePath);
        String absPath = imgFile.getAbsolutePath();
        
        this.hotel = new Hotel(nom, 
                adresse, 
                nbEtoiles, 
                absPath, 
                "image/jpeg", 
                chambreRepository, 
                reservationRepository, 
                clientRepository, 
                partenaireRepository, 
                offreRepository);
        
        System.out.println("Hotel " + this.hotel.getNom() + " initialise avec succes !");
        System.out.println("--------------------------------------------------");
    }

    @GetMapping("/search")
    public ArrayList<Offre> search(@RequestParam String idAgence,
                                   @RequestParam String mdpAgence,
                                   @RequestParam String debut,
                                   @RequestParam String fin,
                                   @RequestParam int nbPersonnes,
                                   @RequestParam double minPrice,
                                   @RequestParam double maxPrice) {
        return hotel.rechercherOffres(idAgence, mdpAgence, debut, fin, nbPersonnes, minPrice, maxPrice);
    }

    public static class ReservationRequest {
        public String idAgence;
        public String login;
        public String mdpAgence;
        public String offreId;
        public Client client;
        public String date;
    }

    @PostMapping("/reserver")
    public Reservation reserver(@RequestBody ReservationRequest req) {
        return hotel.reserver(req.idAgence, req.login, req.mdpAgence, req.offreId, req.client, req.date);
    }

    @GetMapping("/info")
    public HotelInfo info(@RequestParam String idAgence, @RequestParam String login, @RequestParam String mdpAgence) {
        return hotel.getHotelInfo(idAgence, login, mdpAgence);
    }

    @PostMapping("/partenaire")
    public Partenariat devenirPartenaire(@RequestParam String idAgence, @RequestParam String login, @RequestParam String mdpAgence) {
        return hotel.devenirPartenaire(idAgence, login, mdpAgence);
    }
}
