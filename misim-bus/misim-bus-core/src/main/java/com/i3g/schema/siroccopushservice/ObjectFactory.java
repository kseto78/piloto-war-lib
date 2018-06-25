
package com.i3g.schema.siroccopushservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.i3g.schema.siroccopushservice package. 
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

    private final static QName _PushMOResponse_QNAME = new QName("http://i3g.com/schema/SiroccoPushService", "PushMOResponse");
    private final static QName _PushMORequest_QNAME = new QName("http://i3g.com/schema/SiroccoPushService", "PushMORequest");
    private final static QName _PushMOResponseTypeId_QNAME = new QName("", "id");
    private final static QName _PushMOResponseTypeReplyTo_QNAME = new QName("", "replyTo");
    private final static QName _PushMOResponseTypeReplyMessage_QNAME = new QName("", "replyMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.i3g.schema.siroccopushservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PingRequest }
     * 
     */
    public PingRequest createPingRequest() {
        return new PingRequest();
    }

    /**
     * Create an instance of {@link PushMORequestArrayType }
     * 
     */
    public PushMORequestArrayType createPushMORequestArrayType() {
        return new PushMORequestArrayType();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link PushMOResponseArrayType }
     * 
     */
    public PushMOResponseArrayType createPushMOResponseArrayType() {
        return new PushMOResponseArrayType();
    }

    /**
     * Create an instance of {@link PushMOResponseType }
     * 
     */
    public PushMOResponseType createPushMOResponseType() {
        return new PushMOResponseType();
    }

    /**
     * Create an instance of {@link PushMORequestType }
     * 
     */
    public PushMORequestType createPushMORequestType() {
        return new PushMORequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PushMOResponseArrayType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://i3g.com/schema/SiroccoPushService", name = "PushMOResponse")
    public JAXBElement<PushMOResponseArrayType> createPushMOResponse(PushMOResponseArrayType value) {
        return new JAXBElement<PushMOResponseArrayType>(_PushMOResponse_QNAME, PushMOResponseArrayType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PushMORequestArrayType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://i3g.com/schema/SiroccoPushService", name = "PushMORequest")
    public JAXBElement<PushMORequestArrayType> createPushMORequest(PushMORequestArrayType value) {
        return new JAXBElement<PushMORequestArrayType>(_PushMORequest_QNAME, PushMORequestArrayType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id", scope = PushMOResponseType.class)
    public JAXBElement<String> createPushMOResponseTypeId(String value) {
        return new JAXBElement<String>(_PushMOResponseTypeId_QNAME, String.class, PushMOResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "replyTo", scope = PushMOResponseType.class)
    public JAXBElement<Integer> createPushMOResponseTypeReplyTo(Integer value) {
        return new JAXBElement<Integer>(_PushMOResponseTypeReplyTo_QNAME, Integer.class, PushMOResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "replyMessage", scope = PushMOResponseType.class)
    public JAXBElement<String> createPushMOResponseTypeReplyMessage(String value) {
        return new JAXBElement<String>(_PushMOResponseTypeReplyMessage_QNAME, String.class, PushMOResponseType.class, value);
    }

}
