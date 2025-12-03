
package org.example.comparator.agence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paiementInfoAgence complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paiementInfoAgence">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cardBackNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numeroCarteCredit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paiementInfoAgence", propOrder = {
    "cardBackNumber",
    "numeroCarteCredit"
})
public class PaiementInfoAgence {

    protected int cardBackNumber;
    protected double numeroCarteCredit;

    /**
     * Gets the value of the cardBackNumber property.
     * 
     */
    public int getCardBackNumber() {
        return cardBackNumber;
    }

    /**
     * Sets the value of the cardBackNumber property.
     * 
     */
    public void setCardBackNumber(int value) {
        this.cardBackNumber = value;
    }

    /**
     * Gets the value of the numeroCarteCredit property.
     * 
     */
    public double getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    /**
     * Sets the value of the numeroCarteCredit property.
     * 
     */
    public void setNumeroCarteCredit(double value) {
        this.numeroCarteCredit = value;
    }

}
