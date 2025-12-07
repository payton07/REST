package org.example.hotel.service;
import org.example.hotel.model.*;
import org.example.hotel.repository.*;
import org.example.hotel.types.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;

public class Hotel implements IHotel {
    private String id;
    private String nom;
    private Adresse adresse;
    private int nbEtoiles;
    private final OffreRepository offreRepository;
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final PartenaireRepository partenaireRepository;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ArrayList<Integer> pourcentages;
    private String imageBase64;
    private String imageType;
    private final ChambreRepository chambreRepository;
    private final Random rand = new Random();


    public Hotel(String nom, Adresse adresse, int nbEtoiles,String imagePath,String imageType, ChambreRepository chambreRepository, ReservationRepository reservationRepository, ClientRepository clientRepository, PartenaireRepository partenaireRepository, OffreRepository offreRepository) {
        this.id = IdGenerator.generateHotelId();
        this.nom = nom;
        this.adresse = adresse;
        this.nbEtoiles= nbEtoiles;
        this.pourcentages = new ArrayList<>();
        this.imageBase64 = this.encodeImageToBase64(imagePath);
        this.imageType = imageType;
        this.chambreRepository = chambreRepository;
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.partenaireRepository = partenaireRepository;
        this.offreRepository = offreRepository;
        setUp();
    }


    @Override
    public ArrayList<Offre> rechercherOffres(String idAgence,String mdpAgence,String debut, String fin, int nbPersonnes, double minPrice, double maxPrice) {
        System.out.println("rechercherOffres");
        int reduction = 0;
        Optional<Partenaire> partn = partenaireRepository.findById(idAgence);
        if(partn.isPresent()) {
            reduction = partn.get().getPourcentage();
        }
        else {
            System.out.println("Pas partenaire : "+idAgence);
        }
        ArrayList<Offre> result = new ArrayList<>();
        for (Chambre c : chambreRepository.findAll()) {
            if (c.getNbLits() >= nbPersonnes && c.estDisponible(debut, fin,nbPersonnes)) {
                double prix = (c.getPrixBaseParNuit()*(100-reduction))/100 ;
                if (prix >= minPrice && prix <= maxPrice) {

                    Optional<Offre> existingOffre = offreRepository.findByChambreIdAndDateDebutAndDateFinAndPrixTotalAndNbLits(c.getId(), debut, fin, prix, c.getNbLits());

                    if (existingOffre.isPresent()) {
                        result.add(existingOffre.get());
                    } else {
                        Offre newOffre = new Offre(IdGenerator.generateOfferId(this.nom), this.nom, c.getId(), debut, fin, prix, c.getNbLits(), this.imageBase64, this.imageType);
                        offreRepository.save(newOffre);
                        result.add(newOffre);
                    }
                }
            }
        }
        return result;
    }

    private Partenaire findAgencePartenaireById(String AgenceId){
        return this.partenaireRepository.findById(AgenceId).orElse(null);
    }
    @Override
    public Reservation reserver(String idAgence,String login,String mdpAgence,String offreId, Client client,String date) {
        System.out.println("reserver : "+offreId);
        Offre offre = findOffreById(offreId);
        System.out.println("reserver : "+offre);
        if (offre != null) {
            Chambre chambre = findChambreById(offre.getChambreId());
            if(chambre !=null){
                chambre.reserve(offre.getDateDebut(),offre.getDateFin());
                 chambreRepository.save(chambre); // on re-save apres la modification

                Optional<Client> existingClient = clientRepository.findByEmail(client.getEmail());
                Client clientToSave;
                clientToSave = existingClient.orElseGet(() -> clientRepository.save(client));

                PaiementInfo paiementInfo = new PaiementInfo(clientToSave.getNumeroCarteCredit(), clientToSave.getCardBackNumber());
                Reservation reservation = new Reservation(IdGenerator.generateReservationId(),offre,clientToSave,Statut.CONFIRMEE,paiementInfo);
                reservationRepository.save(reservation);
                return reservation;
            }
            return null;
        }
        return  null;
    }

    private Offre findOffreById(String id  ) {
        return offreRepository.findById(id).orElse(null);
    }

    private Chambre findChambreById(String id) {
        return chambreRepository.findById(id).orElse(null);
    }

    public HotelInfo getHotelInfo(String idAgence, String login, String mdpAgence){
        return new HotelInfo(this.nom,this.adresse,this.nbEtoiles);
    }

    private void ajoutChambre(Chambre chambre) {
        if(!this.chambreRepository.existsById(chambre.getId())) {
            this.chambreRepository.save(chambre);
        }
    }

    private int getAnyPourcentage(){
        return pourcentages.get(rand.nextInt(pourcentages.size()));
    }

    public Partenariat devenirPartenaire(String idAgence, String login, String mdpAgence){
        int pourcentage = getAnyPourcentage();
        Partenaire p = new Partenaire(idAgence,pourcentage);
        if(!this.partenaireRepository.existsById(idAgence)) {
            this.partenaireRepository.save(p);
            System.out.println("Ajout partenaire");
            return new Partenariat(this.id,pourcentage);
        }
        return null;
    }

    public String encodeImageToBase64(String imagePath){
        try{
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            return Base64.getEncoder().encodeToString(imageBytes);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private void setUp(){
        Chambre bed1 = new Chambre("1",1,25);
        Chambre bed2 = new Chambre("2",2,35);
        Chambre bed3 = new Chambre("3",1,20);
        Chambre bed4 = new Chambre("4",2,40);
        this.ajoutChambre(bed1);
        this.ajoutChambre(bed2);
        this.ajoutChambre(bed3);
        this.ajoutChambre(bed4);

        for(int i=5;i<20;i++){
            int reste = i%5;
            if(reste == 0){reste++;}
            this.ajoutChambre(new Chambre(""+i,reste,(double)10+(i*5)));
        }
        this.pourcentages.add(10);
        this.pourcentages.add(15);
        this.pourcentages.add(20);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

    public ArrayList<Integer> getPourcentages() {
        return pourcentages;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public String getImageType() {
        return imageType;
    }

    public ReservationRepository getReservationRepository() {
        return reservationRepository;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}

