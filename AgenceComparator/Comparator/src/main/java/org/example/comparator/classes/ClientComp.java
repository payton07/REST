package org.example.comparator.classes;

public class ClientComp {
    private String id ;
    private String nom;
    private String prenom ;
    private String email;
    private String telephone;
    private double numeroCarteCredit;
    private int cardBackNumber;

    public ClientComp(String id , String nom, String prenom, String email, String telephone, double numeroCarteCredit, int cardBackNumber) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.numeroCarteCredit = numeroCarteCredit;
        this.cardBackNumber = cardBackNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setNumeroCarteCredit(double numeroCarteCredit) {
        this.numeroCarteCredit = numeroCarteCredit;
    }

    public void setCardBackNumber(int cardBackNumber) {
        this.cardBackNumber = cardBackNumber;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public double getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    public int getCardBackNumber() {
        return cardBackNumber;
    }

    public PaiementInfoComp getPaimentInfo(){
        return new PaiementInfoComp(this.numeroCarteCredit, this.cardBackNumber);
    }
}
