package org.example.hotel.model;

import org.example.hotel.types.PeriodeDisponible;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chambre{
    @Id
    private String id ;
    private int nbLits;
    private double prixBaseParNuit;
    private boolean reserved;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PeriodeDisponible> periode ;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Chambre(String id,int nbLits, double prixBaseParNuit) {
        this.nbLits = nbLits;
        this.prixBaseParNuit = prixBaseParNuit;
        this.reserved = false;
        this.periode = new ArrayList<>();
        this.id = id;
    }

    public Chambre(){}

    public boolean estDisponible(String d1 , String d2, int nbLits){
        LocalDate debut = LocalDate.parse(d1,formatter);
        LocalDate fin = LocalDate.parse(d2,formatter);
        if(nbLits == this.nbLits && !isReserved(debut ,fin)){
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public int getNbLits() {
        return nbLits;
    }

    public double getPrixBaseParNuit() {
        return prixBaseParNuit;
    }

    public boolean isReserved(LocalDate debut , LocalDate fin) {
        for(PeriodeDisponible periode : this.periode){
            if(debut.isBefore(periode.getDateDebut()) && fin.isBefore(periode.getDateFin())){
                return true;
            }
            if(debut.isAfter(periode.getDateDebut()) && fin.isAfter(periode.getDateFin())){
                return true;
            }
            if(debut.isEqual(periode.getDateDebut()) && fin.isEqual(periode.getDateFin())){
                return true;
            }
        }
        return false;
    }
    public void reserve(String d1 , String d2){
        LocalDate debut = LocalDate.parse(d1,formatter);
        LocalDate fin = LocalDate.parse(d2,formatter);
        if(!isReserved(debut ,fin)){
            this.periode.add(new PeriodeDisponible(debut,fin));
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNbLits(int nbLits) {
        this.nbLits = nbLits;
    }

    public void setPrixBaseParNuit(double prixBaseParNuit) {
        this.prixBaseParNuit = prixBaseParNuit;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public List<PeriodeDisponible> getPeriode() {
        return periode;
    }

    public void setPeriode(List<PeriodeDisponible> periode) {
        this.periode = periode;
    }
}
