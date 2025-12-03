package org.example.hotel.model;

import org.example.hotel.types.Offre;
import org.example.hotel.types.PaiementInfo;
import org.example.hotel.types.Statut;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Reservation {
    @Id
    private String reservationId ;
    @OneToOne
    private Offre offre;
    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;
    private String dateReservation ;
    @Enumerated(EnumType.STRING)
    private Statut statut ;
    @Embedded
    private PaiementInfo paiement ;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Reservation(String reservationId, Offre offre, Client client, Statut statut, PaiementInfo paiement) {
        this.reservationId = reservationId;
        this.offre = offre;
        this.client = client;
        this.dateReservation = LocalDate.now().format(formatter).toString();
        this.statut = statut;
        this.paiement = paiement;
        pay();
    }
    public Reservation(){pay();}
    private void pay(){

    }

    public void confirmer(){
        this.statut = Statut.CONFIRMEE;
    }

    public void annuler(){
        this.statut = Statut.ANNULEE;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public static void setFormatter(DateTimeFormatter formatter) {
        Reservation.formatter = formatter;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public PaiementInfo getPaiement() {
        return paiement;
    }

    public void setPaiement(PaiementInfo paiement) {
        this.paiement = paiement;
    }
}
