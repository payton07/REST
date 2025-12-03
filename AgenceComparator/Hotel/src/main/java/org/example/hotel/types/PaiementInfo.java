package org.example.hotel.types;

import javax.persistence.Embeddable;

@Embeddable
public class PaiementInfo {
    private double numeroCarteCredit;
    private int cardBackNumber;

    public PaiementInfo(double numeroCarteCredit,int cardBackNumber) {
        this.numeroCarteCredit = numeroCarteCredit;
        this.cardBackNumber = cardBackNumber;
    }

    public PaiementInfo() {}

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
