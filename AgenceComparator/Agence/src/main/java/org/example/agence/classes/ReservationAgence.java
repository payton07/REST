package org.example.agence.classes;
public class ReservationAgence {
    private String reservationId ;
    private OffreAgence offre;
    private ClientAgence client;
    private String dateReservation ;
    private StatutAgence statut ;
    private PaiementInfoAgence paiement ;

    public ReservationAgence(String reservationId, OffreAgence offre, ClientAgence client, String dateReservation, StatutAgence statut, PaiementInfoAgence paiement) {
        this.reservationId = reservationId;
        this.offre = offre;
        this.client = client;
        this.dateReservation = dateReservation;
        this.statut = statut;
        this.paiement = paiement;
        pay();
    }
    public ReservationAgence(){pay();}

    private void pay(){

    }

    public void confirmer(){
        this.statut = StatutAgence.CONFIRMEE;
    }

    public void annuler(){
        this.statut = StatutAgence.ANNULEE;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public OffreAgence getOffre() {
        return offre;
    }

    public void setOffre(OffreAgence offre) {
        this.offre = offre;
    }

    public ClientAgence getClient() {
        return client;
    }

    public void setClient(ClientAgence client) {
        this.client = client;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public StatutAgence getStatut() {
        return statut;
    }

    public void setStatut(StatutAgence statut) {
        this.statut = statut;
    }

    public PaiementInfoAgence getPaiement() {
        return paiement;
    }

    public void setPaiement(PaiementInfoAgence paiement) {
        this.paiement = paiement;
    }

    public String toString(){
        return "Hotel : "+offre.getHotelNom()+"\nLa chambre : "+offre.getChambreId()+"\nPour "+offre.getNbLits()+" personnes\n"+"Periode "+offre.getDateDebut()+" au "+offre.getDateFin();
    }

}