package org.example.comparator.classes;

public class PaiementInfoComp {
    private double numeroCarteCredit;
    private int cardBackNumber;

    public PaiementInfoComp(double numeroCarteCredit, int cardBackNumber) {
        this.numeroCarteCredit = numeroCarteCredit;
        this.cardBackNumber = cardBackNumber;
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
