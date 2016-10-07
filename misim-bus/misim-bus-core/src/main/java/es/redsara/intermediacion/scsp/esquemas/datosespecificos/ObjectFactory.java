
package es.redsara.intermediacion.scsp.esquemas.datosespecificos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.redsara.intermediacion.scsp.esquemas.datosespecificos package. 
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

    private final static QName _DatosEspecificos_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosEspecificos");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.redsara.intermediacion.scsp.esquemas.datosespecificos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DatosEspecificos }
     * 
     */
    public DatosEspecificos createDatosEspecificos() {
        return new DatosEspecificos();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatosEspecificos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "DatosEspecificos")
    public JAXBElement<DatosEspecificos> createDatosEspecificos(DatosEspecificos value) {
        return new JAXBElement<DatosEspecificos>(_DatosEspecificos_QNAME, DatosEspecificos.class, null, value);
    }

}
