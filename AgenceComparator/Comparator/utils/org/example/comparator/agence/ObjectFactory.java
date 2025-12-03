
package org.example.comparator.agence;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.comparator.agence package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConsulterHotel_QNAME = new QName("http://interfaces.service.agence.example.org/", "consulterHotel");
    private final static QName _ReserverChambre_QNAME = new QName("http://interfaces.service.agence.example.org/", "reserverChambre");
    private final static QName _ReserverChambreResponse_QNAME = new QName("http://interfaces.service.agence.example.org/", "reserverChambreResponse");
    private final static QName _ConsulterHotelResponse_QNAME = new QName("http://interfaces.service.agence.example.org/", "consulterHotelResponse");
    private final static QName _GetAgenceName_QNAME = new QName("http://interfaces.service.agence.example.org/", "getAgenceName");
    private final static QName _GetAgenceNameResponse_QNAME = new QName("http://interfaces.service.agence.example.org/", "getAgenceNameResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.comparator.agence
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsulterHotelResponse }
     * 
     */
    public ConsulterHotelResponse createConsulterHotelResponse() {
        return new ConsulterHotelResponse();
    }

    /**
     * Create an instance of {@link GetAgenceName }
     * 
     */
    public GetAgenceName createGetAgenceName() {
        return new GetAgenceName();
    }

    /**
     * Create an instance of {@link GetAgenceNameResponse }
     * 
     */
    public GetAgenceNameResponse createGetAgenceNameResponse() {
        return new GetAgenceNameResponse();
    }

    /**
     * Create an instance of {@link ConsulterHotel }
     * 
     */
    public ConsulterHotel createConsulterHotel() {
        return new ConsulterHotel();
    }

    /**
     * Create an instance of {@link ReserverChambre }
     * 
     */
    public ReserverChambre createReserverChambre() {
        return new ReserverChambre();
    }

    /**
     * Create an instance of {@link ReserverChambreResponse }
     * 
     */
    public ReserverChambreResponse createReserverChambreResponse() {
        return new ReserverChambreResponse();
    }

    /**
     * Create an instance of {@link OffreAgence }
     * 
     */
    public OffreAgence createOffreAgence() {
        return new OffreAgence();
    }

    /**
     * Create an instance of {@link DateTimeFormatter }
     * 
     */
    public DateTimeFormatter createDateTimeFormatter() {
        return new DateTimeFormatter();
    }

    /**
     * Create an instance of {@link ReservationAgence }
     * 
     */
    public ReservationAgence createReservationAgence() {
        return new ReservationAgence();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link AdresseAgence }
     * 
     */
    public AdresseAgence createAdresseAgence() {
        return new AdresseAgence();
    }

    /**
     * Create an instance of {@link ClientAgence }
     * 
     */
    public ClientAgence createClientAgence() {
        return new ClientAgence();
    }

    /**
     * Create an instance of {@link PaiementInfoAgence }
     * 
     */
    public PaiementInfoAgence createPaiementInfoAgence() {
        return new PaiementInfoAgence();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsulterHotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.service.agence.example.org/", name = "consulterHotel")
    public JAXBElement<ConsulterHotel> createConsulterHotel(ConsulterHotel value) {
        return new JAXBElement<ConsulterHotel>(_ConsulterHotel_QNAME, ConsulterHotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserverChambre }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.service.agence.example.org/", name = "reserverChambre")
    public JAXBElement<ReserverChambre> createReserverChambre(ReserverChambre value) {
        return new JAXBElement<ReserverChambre>(_ReserverChambre_QNAME, ReserverChambre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserverChambreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.service.agence.example.org/", name = "reserverChambreResponse")
    public JAXBElement<ReserverChambreResponse> createReserverChambreResponse(ReserverChambreResponse value) {
        return new JAXBElement<ReserverChambreResponse>(_ReserverChambreResponse_QNAME, ReserverChambreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsulterHotelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.service.agence.example.org/", name = "consulterHotelResponse")
    public JAXBElement<ConsulterHotelResponse> createConsulterHotelResponse(ConsulterHotelResponse value) {
        return new JAXBElement<ConsulterHotelResponse>(_ConsulterHotelResponse_QNAME, ConsulterHotelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAgenceName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.service.agence.example.org/", name = "getAgenceName")
    public JAXBElement<GetAgenceName> createGetAgenceName(GetAgenceName value) {
        return new JAXBElement<GetAgenceName>(_GetAgenceName_QNAME, GetAgenceName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAgenceNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.service.agence.example.org/", name = "getAgenceNameResponse")
    public JAXBElement<GetAgenceNameResponse> createGetAgenceNameResponse(GetAgenceNameResponse value) {
        return new JAXBElement<GetAgenceNameResponse>(_GetAgenceNameResponse_QNAME, GetAgenceNameResponse.class, null, value);
    }

}
