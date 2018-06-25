
package es.minhap.plataformamensajeria.iop.beans;



public class ResponseStatusType {

   
    protected String idMensaje;   
    protected String statusCode;    
    protected String statusText;    
    protected String details;

   
    public String getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(String value) {
        this.idMensaje = value;
    }

   
    public String getStatusCode() {
        return statusCode;
    }
   
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String value) {
        this.statusText = value;
    }

    public String getDetails() {
        return details;
    }

    /**
     * Define el valor de la propiedad details.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetails(String value) {
        this.details = value;
    }

}
