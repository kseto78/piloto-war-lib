
package com.i3g.schema.siroccopushstatusservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.i3g.schema.siroccopushstatusservice package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.i3g.schema.siroccopushstatusservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PushStatusResponse }
     * 
     */
    public PushStatusResponse createPushStatusResponse() {
        return new PushStatusResponse();
    }

    /**
     * Create an instance of {@link PingRequest }
     * 
     */
    public PingRequest createPingRequest() {
        return new PingRequest();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link PushStatusRequest }
     * 
     */
    public PushStatusRequest createPushStatusRequest() {
        return new PushStatusRequest();
    }

    /**
     * Create an instance of {@link PushStatusMessageHeaderArrayType }
     * 
     */
    public PushStatusMessageHeaderArrayType createPushStatusMessageHeaderArrayType() {
        return new PushStatusMessageHeaderArrayType();
    }

    /**
     * Create an instance of {@link PushStatusMessageDetailType }
     * 
     */
    public PushStatusMessageDetailType createPushStatusMessageDetailType() {
        return new PushStatusMessageDetailType();
    }

    /**
     * Create an instance of {@link PushStatusMessageDetailArrayType }
     * 
     */
    public PushStatusMessageDetailArrayType createPushStatusMessageDetailArrayType() {
        return new PushStatusMessageDetailArrayType();
    }

    /**
     * Create an instance of {@link PushStatusMessageHeaderType }
     * 
     */
    public PushStatusMessageHeaderType createPushStatusMessageHeaderType() {
        return new PushStatusMessageHeaderType();
    }

}
