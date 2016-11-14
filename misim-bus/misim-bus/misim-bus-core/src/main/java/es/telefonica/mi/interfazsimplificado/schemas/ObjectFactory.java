
package es.telefonica.mi.interfazsimplificado.schemas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.telefonica.mi.interfazsimplificado.schemas package. 
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

    private final static QName _MmsDeliverRes_QNAME = new QName("http://www.telefonica.es/MI/InterfazSimplificado/schemas", "mmsDeliverRes");
    private final static QName _DeliveryReportRes_QNAME = new QName("http://www.telefonica.es/MI/InterfazSimplificado/schemas", "deliveryReportRes");
    private final static QName _SmsDeliverRes_QNAME = new QName("http://www.telefonica.es/MI/InterfazSimplificado/schemas", "smsDeliverRes");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.telefonica.mi.interfazsimplificado.schemas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeliveryReportReq }
     * 
     */
    public DeliveryReportReq createDeliveryReportReq() {
        return new DeliveryReportReq();
    }

    /**
     * Create an instance of {@link MmsSubmitReq }
     * 
     */
    public MmsSubmitReq createMmsSubmitReq() {
        return new MmsSubmitReq();
    }

    /**
     * Create an instance of {@link RecipientsType }
     * 
     */
    public RecipientsType createRecipientsType() {
        return new RecipientsType();
    }

    /**
     * Create an instance of {@link MmsContentsType }
     * 
     */
    public MmsContentsType createMmsContentsType() {
        return new MmsContentsType();
    }

    /**
     * Create an instance of {@link DeliverRes }
     * 
     */
    public DeliverRes createDeliverRes() {
        return new DeliverRes();
    }

    /**
     * Create an instance of {@link MassivePersonalSMSSubmitReq }
     * 
     */
    public MassivePersonalSMSSubmitReq createMassivePersonalSMSSubmitReq() {
        return new MassivePersonalSMSSubmitReq();
    }

    /**
     * Create an instance of {@link MassivePersonalRecipientsType }
     * 
     */
    public MassivePersonalRecipientsType createMassivePersonalRecipientsType() {
        return new MassivePersonalRecipientsType();
    }

    /**
     * Create an instance of {@link SmsDeliverReq }
     * 
     */
    public SmsDeliverReq createSmsDeliverReq() {
        return new SmsDeliverReq();
    }

    /**
     * Create an instance of {@link MmsDeliverReq }
     * 
     */
    public MmsDeliverReq createMmsDeliverReq() {
        return new MmsDeliverReq();
    }

    /**
     * Create an instance of {@link SubmitRes }
     * 
     */
    public SubmitRes createSubmitRes() {
        return new SubmitRes();
    }

    /**
     * Create an instance of {@link ResponseStatusType }
     * 
     */
    public ResponseStatusType createResponseStatusType() {
        return new ResponseStatusType();
    }

    /**
     * Create an instance of {@link WappushSubmitReq }
     * 
     */
    public WappushSubmitReq createWappushSubmitReq() {
        return new WappushSubmitReq();
    }

    /**
     * Create an instance of {@link DeliveryReportRes }
     * 
     */
    public DeliveryReportRes createDeliveryReportRes() {
        return new DeliveryReportRes();
    }

    /**
     * Create an instance of {@link SmsBinarySubmitReq }
     * 
     */
    public SmsBinarySubmitReq createSmsBinarySubmitReq() {
        return new SmsBinarySubmitReq();
    }

    /**
     * Create an instance of {@link SmsTextSubmitReq }
     * 
     */
    public SmsTextSubmitReq createSmsTextSubmitReq() {
        return new SmsTextSubmitReq();
    }

    /**
     * Create an instance of {@link MassivePersonalRecipientType }
     * 
     */
    public MassivePersonalRecipientType createMassivePersonalRecipientType() {
        return new MassivePersonalRecipientType();
    }

    /**
     * Create an instance of {@link ContentType }
     * 
     */
    public ContentType createContentType() {
        return new ContentType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeliverRes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telefonica.es/MI/InterfazSimplificado/schemas", name = "mmsDeliverRes")
    public JAXBElement<DeliverRes> createMmsDeliverRes(DeliverRes value) {
        return new JAXBElement<DeliverRes>(_MmsDeliverRes_QNAME, DeliverRes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeliveryReportRes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telefonica.es/MI/InterfazSimplificado/schemas", name = "deliveryReportRes")
    public JAXBElement<DeliveryReportRes> createDeliveryReportRes(DeliveryReportRes value) {
        return new JAXBElement<DeliveryReportRes>(_DeliveryReportRes_QNAME, DeliveryReportRes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeliverRes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telefonica.es/MI/InterfazSimplificado/schemas", name = "smsDeliverRes")
    public JAXBElement<DeliverRes> createSmsDeliverRes(DeliverRes value) {
        return new JAXBElement<DeliverRes>(_SmsDeliverRes_QNAME, DeliverRes.class, null, value);
    }

}
