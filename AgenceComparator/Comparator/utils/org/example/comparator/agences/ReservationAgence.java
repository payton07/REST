
package org.example.comparator.agences;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reservationAgence complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reservationAgence">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="client" type="{http://interfaces.service.agence.example.org/}clientAgence" minOccurs="0"/>
 *         &lt;element name="dateReservation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offre" type="{http://interfaces.service.agence.example.org/}offreAgence" minOccurs="0"/>
 *         &lt;element name="paiement" type="{http://interfaces.service.agence.example.org/}paiementInfoAgence" minOccurs="0"/>
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statut" type="{http://interfaces.service.agence.example.org/}statutAgence" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservationAgence", propOrder = {
    "client",
    "dateReservation",
    "offre",
    "paiement",
    "reservationId",
    "statut"
})
public class ReservationAgence {

    protected ClientAgence client;
    protected String dateReservation;
    protected OffreAgence offre;
    protected PaiementInfoAgence paiement;
    protected String reservationId;
    @XmlSchemaType(name = "string")
    protected StatutAgence statut;

    /**
     * Gets the value of the client property.
     * 
     * @return
     *     possible object is
     *     {@link ClientAgence }
     *     
     */
    public ClientAgence getClient() {
        return client;
    }

    /**
     * Sets the value of the client property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientAgence }
     *     
     */
    public void setClient(ClientAgence value) {
        this.client = value;
    }

    /**
     * Gets the value of the dateReservation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateReservation() {
        return dateReservation;
    }

    /**
     * Sets the value of the dateReservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateReservation(String value) {
        this.dateReservation = value;
    }

    /**
     * Gets the value of the offre property.
     * 
     * @return
     *     possible object is
     *     {@link OffreAgence }
     *     
     */
    public OffreAgence getOffre() {
        return offre;
    }

    /**
     * Sets the value of the offre property.
     * 
     * @param value
     *     allowed object is
     *     {@link OffreAgence }
     *     
     */
    public void setOffre(OffreAgence value) {
        this.offre = value;
    }

    /**
     * Gets the value of the paiement property.
     * 
     * @return
     *     possible object is
     *     {@link PaiementInfoAgence }
     *     
     */
    public PaiementInfoAgence getPaiement() {
        return paiement;
    }

    /**
     * Sets the value of the paiement property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaiementInfoAgence }
     *     
     */
    public void setPaiement(PaiementInfoAgence value) {
        this.paiement = value;
    }

    /**
     * Gets the value of the reservationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationId(String value) {
        this.reservationId = value;
    }

    /**
     * Gets the value of the statut property.
     * 
     * @return
     *     possible object is
     *     {@link StatutAgence }
     *     
     */
    public StatutAgence getStatut() {
        return statut;
    }

    /**
     * Sets the value of the statut property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatutAgence }
     *     
     */
    public void setStatut(StatutAgence value) {
        this.statut = value;
    }

}
