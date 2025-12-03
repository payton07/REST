package org.example.agence.classes;

public class PaiementInfoAgence {
    private double numeroCarteCredit;
    private int cardBackNumber;

    public PaiementInfoAgence(double numeroCarteCredit, int cardBackNumber) {
        this.numeroCarteCredit = numeroCarteCredit;
        this.cardBackNumber = cardBackNumber;
    }
    public PaiementInfoAgence(){}
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