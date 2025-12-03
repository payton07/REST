package org.example.hotel.model;

import org.example.hotel.types.IdGenerator;
import org.example.hotel.types.PaiementInfo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    private String id ;
    private String nom;
    private String prenom ;
    private String email;
    private String telephone;
    private double numeroCarteCredit;
    private int cardBackNumber;

    public Client(String id,String nom, String prenom, String email, String telephone, double numeroCarteCredit, int cardBackNumber) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.numeroCarteCredit = numeroCarteCredit;
        this.cardBackNumber = cardBackNumber;
    }

    public Client() {}

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    public void setNumeroCarteCredit(double numeroCarteCredit) {
        this.numeroCarteCredit = numeroCarteCredit;
    }

    public int getCardBackNumber() {
        return cardBackNumber;
    }

    public void setCardBackNumber(int cardBackNumber) {
        this.cardBackNumber = cardBackNumber;
    }
}
