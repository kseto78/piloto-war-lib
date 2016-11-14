
package com.i3g.schema.siroccopushstatusservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PushStatusMessageDetailType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PushStatusMessageDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="st" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ts" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="synthetic" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushStatusMessageDetailType", propOrder = {
    "st",
    "ts",
    "result",
    "code",
    "synthetic"
})
public class PushStatusMessageDetailType {

    protected int st;
    protected long ts;
    protected int result;
    protected int code;
    protected boolean synthetic;

    /**
     * Obtiene el valor de la propiedad st.
     * 
     */
    public int getSt() {
        return st;
    }

    /**
     * Define el valor de la propiedad st.
     * 
     */
    public void setSt(int value) {
        this.st = value;
    }

    /**
     * Obtiene el valor de la propiedad ts.
     * 
     */
    public long getTs() {
        return ts;
    }

    /**
     * Define el valor de la propiedad ts.
     * 
     */
    public void setTs(long value) {
        this.ts = value;
    }

    /**
     * Obtiene el valor de la propiedad result.
     * 
     */
    public int getResult() {
        return result;
    }

    /**
     * Define el valor de la propiedad result.
     * 
     */
    public void setResult(int value) {
        this.result = value;
    }

    /**
     * Obtiene el valor de la propiedad code.
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * Define el valor de la propiedad code.
     * 
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Obtiene el valor de la propiedad synthetic.
     * 
     */
    public boolean isSynthetic() {
        return synthetic;
    }

    /**
     * Define el valor de la propiedad synthetic.
     * 
     */
    public void setSynthetic(boolean value) {
        this.synthetic = value;
    }

}
