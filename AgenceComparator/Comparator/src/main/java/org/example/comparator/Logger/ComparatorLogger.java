package org.example.comparator.Logger;

import org.example.comparator.classes.ClientComp;
import org.example.comparator.classes.ReservationComp;
import org.example.comparator.model.AgenceComparator;
import org.example.comparator.model.ResponseComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Scanner;

@Configuration
public class ComparatorLogger implements CommandLineRunner {

    private static int compteur = 0;
    private String arrivalDate;
    private String departureDate;

    @Autowired
    private AgenceComparator comparator;

    @Override
    public void run(String... args) {
        System.out.println("=== Application de comparaison d’agences lancée ===");

        System.out.println("Allez sur lz site suivant :  http://localhost:9100/\n");
/*
        Scanner sc = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            List<ResponseComp> hotels = getListeHotel(sc, comparator);
            if (hotels != null) {
                hotels.sort(Comparator.comparingDouble(ResponseComp::getPrice)); // Tri croissant par prix
                display(hotels);

                if (!hotels.isEmpty()) {
                    getReponseAndBook(sc, hotels, comparator);
                }
            } else {
                System.out.println("Erreur lors de la récupération des hôtels (liste nulle).");
            }

            System.out.print("\nSouhaitez-vous effectuer une autre recherche ? (o/n) : ");
            String reponse = sc.next().trim().toLowerCase();
            continuer = reponse.equals("o");
        }

        System.out.println("\nMerci d’avoir utilisé notre application ! Bon voyage 🌍");

 */
    }

    // Lecture sécurisée d'un entier (utilise sc.next() et parse)
    private int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message + " ");
            try {
                return Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Entrée invalide. Veuillez saisir un nombre valide.");
            }
        }
    }

    // Lecture sécurisée d'une chaîne
    private String readString(Scanner sc, String message) {
        System.out.print(message + " ");
        return sc.next();
    }

    // Récupération des critères utilisateur (avec corrections)
    private List<ResponseComp> getListeHotel(Scanner sc, AgenceComparator comparator) {
        // Remis aux mêmes valeurs par défaut que ton code original
        String city = "montpellier"; // <-- retour à la minuscule par défaut
        String dateArrivee = "2025-10-11";
        String dateDepart = "2025-10-20";
        int min = 10;
        int max = 50;
        int nbStars = 4;
        int nbPeople = 1;

        System.out.println("\n=== Recherche d’hôtel ===");
        System.out.println("1️⃣  Utiliser les paramètres prédéfinis");
        System.out.println("2️⃣  Saisir vos propres paramètres");
        int choix = readInt(sc, "Votre choix :"); // utilisation de readInt pour éviter les problèmes Scanner

        if (choix == 2) {
            city = readString(sc, "🏙️  Ville du séjour :");
            dateArrivee = readString(sc, "📅 Date d’arrivée (JJ-MM-AAAA) :");
            dateDepart = readString(sc, "📅 Date de départ (JJ-MM-AAAA) :");
            min = readInt(sc, "💰 Prix minimum par nuit :");
            max = readInt(sc, "💰 Prix maximum par nuit :");
            nbStars = readInt(sc, "⭐ Nombre d’étoiles souhaité :");
            nbPeople = readInt(sc, "👥 Nombre de personnes à héberger :");
        }

        this.arrivalDate = dateArrivee;
        this.departureDate = dateDepart;

        // Normalisation: trim + toLowerCase pour éviter les problèmes de casse
        String cityNormalized = city.trim().toLowerCase();

        System.out.println("\n🔎 Recherche en cours...");
        return comparator.consulterAgence(cityNormalized,dateArrivee, dateDepart, min, max, nbStars, nbPeople);
    }

    // Affichage formaté des résultats
    private void display(List<ResponseComp> liste) {
        if (liste.isEmpty()) {
            System.out.println("\n❌ Aucun hôtel trouvé pour ces critères !");
            return;
        }

        System.out.println("\n=== Résultats de la recherche ===");
        System.out.printf("%-4s %-20s %-10s %-12s %-12s %-10s%n",
                "N°", "Hôtel", "Étoiles", "Prix/Nuit (€)", "Prix Total (€)", "Lits");
        System.out.println("--------------------------------------------------------------------------");

        int index = 0;
        for (ResponseComp r : liste) {
            System.out.printf("%-4d %-20s %-10d %-12.2f %-12.2f %-10d%n",
                    index++, r.getHotelName(), r.getNbStar(), r.getPrice(), r.getPriceTotal(), r.getNumberOfBeds());
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    // Gestion de la réservation
    private ReservationComp getReponseAndBook(Scanner sc, List<ResponseComp> responses, AgenceComparator comparator) {
        int numero;
        do {
            numero = readInt(sc, "\nEntrez le numéro de la chambre choisi :");
        } while (numero < 0 || numero >= responses.size());

        ResponseComp response = responses.get(numero);

        System.out.println("\n=== Détails de la chambre sélectionné ===");
        System.out.println("🏨 " + response.getHotelName());
        System.out.println("🛏️  Chambre : " + response.getNumBedroom());
        System.out.println("🔖 Offre ID : " + response.getOffreId());
        System.out.println("💰 Prix total : " + response.getPriceTotal() + " €");

        // Informations client
        System.out.println("\n=== Informations client ===");
        String nom = readString(sc, "Nom :");
        String prenom = readString(sc, "Prénom :");
        double numeroBancaire;
        try {
            numeroBancaire = Double.parseDouble(readCardNumber(sc, "Numéro de carte bancaire :"));
        } catch (NumberFormatException e) {
            // Si l'utilisateur a saisi quelque chose d'impossible, on redemande proprement
            System.out.println("Numéro de carte invalide, valeur par défaut 0 utilisée.");
            numeroBancaire = 0.0;
        }
        int code3Chiffres = Integer.parseInt(readSecurityCode3(sc, "Code de sécurité (3 chiffres) :"));

        compteur++;
        String id = String.valueOf(compteur);
        ClientComp client = new ClientComp(id, nom, prenom, "", "", numeroBancaire, code3Chiffres);

        ReservationComp reservation = comparator.reserverChambre(response, client, this.arrivalDate, this.departureDate);

        System.out.println("\n✅ Réservation confirmée !");
        System.out.println("Numéro de réservation : " + reservation.getReservationId());
        System.out.println(reservation.toString());
        return reservation;
    }

    private String readCardNumber(Scanner sc, String message) {
        while (true) {
            System.out.print(message + " ");
            String input = sc.next().trim().replaceAll("\\s+", ""); // supprime les espaces éventuels
            if (!input.matches("\\d+")) {
                System.out.println("⚠️  Le numéro de carte doit contenir uniquement des chiffres. Réessayez.");
                continue;
            }
            int len = input.length();
            if (len < 13 || len > 19) {
                System.out.println("⚠️  Le numéro de carte doit comporter entre 13 et 19 chiffres. Réessayez.");
                continue;
            }
            return input;
        }
    }
    private String readSecurityCode3(Scanner sc, String message) {
        while (true) {
            System.out.print(message + " ");
            String input = sc.next().trim();
            if (!input.matches("\\d{3}")) {
                System.out.println("⚠️  Le code de sécurité doit contenir exactement 3 chiffres. Réessayez.");
                continue;
            }
            return input;
        }
    }
}
