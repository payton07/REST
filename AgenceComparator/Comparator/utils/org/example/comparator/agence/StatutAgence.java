
package org.example.comparator.agence;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statutAgence.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="statutAgence">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CONFIRMEE"/>
 *     &lt;enumeration value="ANNULEE"/>
 *     &lt;enumeration value="EN_ATTENTE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "statutAgence")
@XmlEnum
public enum StatutAgence {

    CONFIRMEE,
    ANNULEE,
    EN_ATTENTE;

    public String value() {
        return name();
    }

    public static StatutAgence fromValue(String v) {
        return valueOf(v);
    }

}
