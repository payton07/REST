
package org.example.comparator.agences;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://interfaces.service.agence.example.org/}adresseAgence" minOccurs="0"/>
 *         &lt;element name="agenceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hotelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nbJour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nbStar" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numBedroom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfBeds" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="offreId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {
    "address",
    "agenceName",
    "hotelName",
    "imageBase64",
    "imageType",
    "nbJour",
    "nbStar",
    "numBedroom",
    "numberOfBeds",
    "offreId",
    "price"
})
public class Response {

    protected AdresseAgence address;
    protected String agenceName;
    protected String hotelName;
    protected String imageBase64;
    protected String imageType;
    protected int nbJour;
    protected int nbStar;
    protected String numBedroom;
    protected int numberOfBeds;
    protected String offreId;
    protected double price;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AdresseAgence }
     *     
     */
    public AdresseAgence getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdresseAgence }
     *     
     */
    public void setAddress(AdresseAgence value) {
        this.address = value;
    }

    /**
     * Gets the value of the agenceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgenceName() {
        return agenceName;
    }

    /**
     * Sets the value of the agenceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgenceName(String value) {
        this.agenceName = value;
    }

    /**
     * Gets the value of the hotelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Sets the value of the hotelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotelName(String value) {
        this.hotelName = value;
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
     * Gets the value of the nbJour property.
     * 
     */
    public int getNbJour() {
        return nbJour;
    }

    /**
     * Sets the value of the nbJour property.
     * 
     */
    public void setNbJour(int value) {
        this.nbJour = value;
    }

    /**
     * Gets the value of the nbStar property.
     * 
     */
    public int getNbStar() {
        return nbStar;
    }

    /**
     * Sets the value of the nbStar property.
     * 
     */
    public void setNbStar(int value) {
        this.nbStar = value;
    }

    /**
     * Gets the value of the numBedroom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumBedroom() {
        return numBedroom;
    }

    /**
     * Sets the value of the numBedroom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumBedroom(String value) {
        this.numBedroom = value;
    }

    /**
     * Gets the value of the numberOfBeds property.
     * 
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Sets the value of the numberOfBeds property.
     * 
     */
    public void setNumberOfBeds(int value) {
        this.numberOfBeds = value;
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
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

}
