package es.minhap.plataformamensajeria.iop.beans;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.minhap.plataformamensaferia.iop.beans.envioPremium.Peticion;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usuario",
    "password",
    "mensaje"
})
@XmlRootElement(name = "PeticionHistorico", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class ConsultaHistoricoXMLBean {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String password;
//    @XmlElement(name = "Filtro", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
//    protected ConsultaHistoricoXMLBean.Filtro filtro;
    @XmlElement(name = "Mensaje", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected ConsultaHistoricoXMLBean.Mensaje mensaje;

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define el valor de la propiedad password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

//    /**
//     * Obtiene el valor de la propiedad filtro.
//     * 
//     * @return
//     *     possible object is
//     *     {@link Peticion.Filtro }
//     *     
//     */
//    public ConsultaHistoricoXMLBean.Filtro getFiltro() {
//        return filtro;
//    }
//
//    /**
//     * Define el valor de la propiedad filtro.
//     * 
//     * @param value
//     *     allowed object is
//     *     {@link Peticion.Filtro }
//     *     
//     */
//    public void setFiltro(ConsultaHistoricoXMLBean.Filtro value) {
//        this.filtro = value;
//    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link Peticion.SeguimientoMensaje }
     *     
     */
    public ConsultaHistoricoXMLBean.Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link Peticion.SeguimientoMensaje }
     *     
     */
    public void setMensaje(ConsultaHistoricoXMLBean.Mensaje value) {
        this.mensaje = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="IdAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="IdServicio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="IdLote" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Mensaje"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="IdMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="IdExterno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="IdEstado" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="FechaDesde" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="FechaHasta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="DocUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="CodSia" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="CodOrganismo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="CodOrganismoPagador" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idAplicacion",
        "idServicio",
        "idCanal",
        "idLote",
        "mensaje",
        "idEstado",
        "fechaDesde",
        "fechaHasta",
        "docUsuario",
        "codSia",
        "codOrganismo",
        "codOrganismoPagador"
    })
    @XmlRootElement(name = "Filtro", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    public static class Filtro {

        @XmlElement(name = "IdAplicacion", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String idAplicacion;
        @XmlElement(name = "IdServicio", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String idServicio;
        @XmlElement(name = "IdCanal", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String idCanal;
        @XmlElement(name = "IdLote", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String idLote;
        @XmlElement(name = "Mensaje", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected ConsultaHistoricoXMLBean.Filtro.Mensaje mensaje;
        @XmlElement(name = "IdEstado", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String idEstado;
        @XmlElement(name = "FechaDesde", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String fechaDesde;
        @XmlElement(name = "FechaHasta", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String fechaHasta;
        @XmlElement(name = "DocUsuario", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String docUsuario;
        @XmlElement(name = "CodSia", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String codSia;
        @XmlElement(name = "CodOrganismo", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String codOrganismo;
        @XmlElement(name = "CodOrganismoPagador", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String codOrganismoPagador;

        /**
         * Obtiene el valor de la propiedad idAplicacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdAplicacion() {
            return idAplicacion;
        }

        /**
         * Define el valor de la propiedad idAplicacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdAplicacion(String value) {
            this.idAplicacion = value;
        }

        /**
         * Obtiene el valor de la propiedad idServicio.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdServicio() {
            return idServicio;
        }

        /**
         * Define el valor de la propiedad idServicio.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdServicio(String value) {
            this.idServicio = value;
        }

        /**
         * Obtiene el valor de la propiedad idLote.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdLote() {
            return idLote;
        }

        /**
         * Define el valor de la propiedad idLote.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdLote(String value) {
            this.idLote = value;
        }

        /**
         * Obtiene el valor de la propiedad mensaje.
         * 
         * @return
         *     possible object is
         *     {@link Peticion.Filtro.SeguimientoMensaje }
         *     
         */
        public ConsultaHistoricoXMLBean.Filtro.Mensaje getMensaje() {
            return mensaje;
        }

        /**
         * Define el valor de la propiedad mensaje.
         * 
         * @param value
         *     allowed object is
         *     {@link Peticion.Filtro.SeguimientoMensaje }
         *     
         */
        public void setMensaje(ConsultaHistoricoXMLBean.Filtro.Mensaje value) {
            this.mensaje = value;
        }

        /**
         * Obtiene el valor de la propiedad idEstado.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdEstado() {
            return idEstado;
        }

        /**
         * Define el valor de la propiedad idEstado.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdEstado(String value) {
            this.idEstado = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaDesde.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaDesde() {
            return fechaDesde;
        }

        /**
         * Define el valor de la propiedad fechaDesde.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaDesde(String value) {
            this.fechaDesde = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaHasta.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaHasta() {
            return fechaHasta;
        }

        /**
         * Define el valor de la propiedad fechaHasta.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaHasta(String value) {
            this.fechaHasta = value;
        }

        /**
         * Obtiene el valor de la propiedad docUsuario.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDocUsuario() {
            return docUsuario;
        }

        /**
         * Define el valor de la propiedad docUsuario.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDocUsuario(String value) {
            this.docUsuario = value;
        }

        /**
         * Obtiene el valor de la propiedad codSia.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodSia() {
            return codSia;
        }

        /**
         * Define el valor de la propiedad codSia.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodSia(String value) {
            this.codSia = value;
        }

        /**
         * Obtiene el valor de la propiedad codOrganismo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodOrganismo() {
            return codOrganismo;
        }

        /**
         * Define el valor de la propiedad codOrganismo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodOrganismo(String value) {
            this.codOrganismo = value;
        }

        /**
         * Obtiene el valor de la propiedad codOrganismoPagador.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodOrganismoPagador() {
            return codOrganismoPagador;
        }

        /**
         * Define el valor de la propiedad codOrganismoPagador.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodOrganismoPagador(String value) {
            this.codOrganismoPagador = value;
        }
        
        /**
         * Obtiene el valor de la propiedad idCanal.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdCanal() {
            return idCanal;
        }

        /**
         * Define el valor de la propiedad idCanal.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdCanal(String value) {
            this.idCanal = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="IdMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="IdExterno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "idMensaje",
            "idExterno"
        })
        @XmlRootElement(name = "Mensaje", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        public static class Mensaje {

            @XmlElement(name = "IdMensaje", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
            protected String idMensaje;
            @XmlElement(name = "IdExterno", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
            protected String idExterno;

            /**
             * Obtiene el valor de la propiedad idMensaje.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIdMensaje() {
                return idMensaje;
            }

            /**
             * Define el valor de la propiedad idMensaje.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIdMensaje(String value) {
                this.idMensaje = value;
            }

            /**
             * Obtiene el valor de la propiedad idExterno.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIdExterno() {
                return idExterno;
            }

            /**
             * Define el valor de la propiedad idExterno.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIdExterno(String value) {
                this.idExterno = value;
            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="IdMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="IdExterno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idMensaje",
        "idExterno"
    })
    @XmlRootElement(name = "Mensaje", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    public static class Mensaje {

        @XmlElement(name = "IdMensaje", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion" )
        protected String idMensaje;
        @XmlElement(name = "IdExterno", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
        protected String idExterno;

        /**
         * Obtiene el valor de la propiedad idMensaje.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdMensaje() {
            return idMensaje;
        }

        /**
         * Define el valor de la propiedad idMensaje.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdMensaje(String value) {
            this.idMensaje = value;
        }

        /**
         * Obtiene el valor de la propiedad idExterno.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdExterno() {
            return idExterno;
        }

        /**
         * Define el valor de la propiedad idExterno.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdExterno(String value) {
            this.idExterno = value;
        }

    }
	
	
	public void loadObjectFromXML (String xmlConsultaHistorico)throws PlataformaBusinessException {
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ConsultaHistoricoXMLBean.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlConsultaHistorico);
		ConsultaHistoricoXMLBean consultaHistorico = (ConsultaHistoricoXMLBean) unmarshaller.unmarshal(reader);
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(this, consultaHistorico);
		
		
		
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlConsultaHistorico);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlConsultaHistorico);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlConsultaHistorico);
		}
	}
	
	public String toXML() throws PlataformaBusinessException{
		
		ConsultaHistoricoXMLBean consultaHistorico = this;
		
		try {
        JAXBContext jaxbContext = JAXBContext.newInstance(ConsultaHistoricoXMLBean.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(consultaHistorico, writer);
//		jaxbMarshaller.marshal(consultaHistorico, System.out);
		
		return writer.toString();
		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
		}
		
	}
	
}
