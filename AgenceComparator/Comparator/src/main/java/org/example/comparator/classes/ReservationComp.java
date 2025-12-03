package org.example.comparator.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationComp {
    private String reservationId ;
    private OffreComp offre;
    private ClientComp client;
    private LocalDate dateReservation ;
    private StatutComp statut ;
    private PaiementInfoComp paiement ;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public ReservationComp(String reservationId, OffreComp offre, ClientComp client, String dateReservation, StatutComp statut, PaiementInfoComp paiement) {
        this.reservationId = reservationId;
        this.offre = offre;
        this.client = client;
        this.dateReservation = LocalDate.parse(dateReservation, formatter);
        this.statut = statut;
        this.paiement = paiement;
        pay();
    }

    // public ReservationAgence(){}
    private void pay(){

    }

    public void confirmer(){
        this.statut = StatutComp.CONFIRMEE;
    }

    public void annuler(){
        this.statut = StatutComp.ANNULEE;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public OffreComp getOffre() {
        return offre;
    }

    public void setOffre(OffreComp offre) {
        this.offre = offre;
    }

    public ClientComp getClient() {
        return client;
    }

    public void setClient(ClientComp client) {
        this.client = client;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public StatutComp getStatut() {
        return statut;
    }

    public void setStatut(StatutComp statut) {
        this.statut = statut;
    }

    public PaiementInfoComp getPaiement() {
        return paiement;
    }

    public void setPaiement(PaiementInfoComp paiement) {
        this.paiement = paiement;
    }

    public String toString(){
        return "Hotel : "+offre.getHotelNom()+"\nLa chambre : "+offre.getChambreId()+"\nPour "+offre.getNbLits()+" personnes\n"+"Periode "+offre.getDateDebut()+" au "+offre.getDateFin();
    }

}
