
package es.telefonica.mi.interfazsimplificado.schemas;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para deliveryReportType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="deliveryReportType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="Failure"/>
 *     &lt;enumeration value="Success"/>
 *     &lt;enumeration value="None"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "deliveryReportType")
@XmlEnum
public enum DeliveryReportType {

    @XmlEnumValue("All")
    ALL("All"),
    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("None")
    NONE("None");
    private final String value;

    DeliveryReportType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DeliveryReportType fromValue(String v) {
        for (DeliveryReportType c: DeliveryReportType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
