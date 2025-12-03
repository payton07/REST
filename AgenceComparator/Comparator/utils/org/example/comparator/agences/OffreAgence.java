
package org.example.comparator.agences;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for offreAgence complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="offreAgence">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chambreId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateDebut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatter" type="{http://interfaces.service.agence.example.org/}dateTimeFormatter" minOccurs="0"/>
 *         &lt;element name="hotelNom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nbLits" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="offreId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prixTotal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "offreAgence", propOrder = {
    "chambreId",
    "dateDebut",
    "dateFin",
    "formatter",
    "hotelNom",
    "imageBase64",
    "imageType",
    "nbLits",
    "offreId",
    "prixTotal"
})
public class OffreAgence {

    protected String chambreId;
    protected String dateDebut;
    protected String dateFin;
    protected DateTimeFormatter formatter;
    protected String hotelNom;
    protected String imageBase64;
    protected String imageType;
    protected int nbLits;
    protected String offreId;
    protected double prixTotal;

    /**
     * Gets the value of the chambreId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChambreId() {
        return chambreId;
    }

    /**
     * Sets the value of the chambreId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChambreId(String value) {
        this.chambreId = value;
    }

    /**
     * Gets the value of the dateDebut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Sets the value of the dateDebut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateDebut(String value) {
        this.dateDebut = value;
    }

    /**
     * Gets the value of the dateFin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Sets the value of the dateFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateFin(String value) {
        this.dateFin = value;
    }

    /**
     * Gets the value of the formatter property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeFormatter }
     *     
     */
    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    /**
     * Sets the value of the formatter property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeFormatter }
     *     
     */
    public void setFormatter(DateTimeFormatter value) {
        this.formatter = value;
    }

    /**
     * Gets the value of the hotelNom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotelNom() {
        return hotelNom;
    }

    /**
     * Sets the value of the hotelNom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotelNom(String value) {
        this.hotelNom = value;
    }

    /**
     * Gets the value of the imageBase64 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageBase64() {
        return imageBase64;
    }

    /**
     * Sets the value of the imageBase64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageBase64(String value) {
        this.imageBase64 = value;
    }

    /**
     * Gets the value of the imageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * Sets the value of the imageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageType(String value) {
        this.imageType = value;
    }

    /**
     * Gets the value of the nbLits property.
     * 
     */
    public int getNbLits() {
        return nbLits;
    }

    /**
     * Sets the value of the nbLits property.
     * 
     */
    public void setNbLits(int value) {
        this.nbLits = value;
    }

    /**
     * Gets the value of the offreId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffreId() {
        return offreId;
    }

    /**
     * Sets the value of the offreId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffreId(String value) {
        this.offreId = value;
    }

    /**
     * Gets the value of the prixTotal property.
     * 
     */
    public double getPrixTotal() {
        return prixTotal;
    }

    /**
     * Sets the value of the prixTotal property.
     * 
     */
    public void setPrixTotal(double value) {
        this.prixTotal = value;
    }

}
