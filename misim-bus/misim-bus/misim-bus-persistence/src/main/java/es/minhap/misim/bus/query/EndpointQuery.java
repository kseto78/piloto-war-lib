/*
 *
 * archivo: EndpointQuery.java
 *
 * Proyecto: Carpeta Ciudadana
 *
 * Generated file! Do not modify.
 *
 * Developed by:
 *     everis S.A.
 *     www.everis.com
 */

package es.minhap.misim.bus.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.misim.bus.model.Comunicacion;
import es.minhap.misim.bus.model.Endpoint;

/**
 * Clase con criterios de busqueda para la entidad Endpoint
 */
public class EndpointQuery extends AbstractHibernateQueryEntity<Endpoint> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDENDPOINT = "idEndpoint";
    public static final String COMUNICACION = "comunicacion";
    public static final String NOMBRE = "nombre";
    public static final String URLENDPOINT = "urlEndpoint";
    public static final String SERVICENAME = "serviceName";
    public static final String PORTNAME = "portName";
    public static final String TARGETNAME = "targetName";
    public static final String OPERATION = "operation";
    public static final String TIMEOUT = "timeout";
    public static final String FECHACREACION = "fechaCreacion";
    public static final String FECHAACTUALIZACION = "fechaActualizacion";


    /**
     * Valor de busqueda de campo idEndpoint
     */
    private Long idEndpoint;

    /**
     * Lista de valores del campo idEndpoint para busquedas tipo IN
     */
    private List<Long> idEndpointIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo comunicacion
     */
    private ComunicacionQuery comunicacion;

    /**
     * Lista de valores del ID del campo comunicacion para busquedas tipo IN
     * Solo se consideran los ID dentro de los Comunicacion
     */
    private List<Comunicacion> comunicacionIdIn = new ArrayList<Comunicacion>(0);

    /**
     * Permite buscar cuando campo comunicacion es NULL
     */
    private boolean comunicacionIsNull = false;

    /**
     * Permite buscar cuando campo comunicacion es NOT NULL
     */
    private boolean comunicacionIsNotNull = false;

    /**
     * Valor de busqueda de campo nombre
     */
    private String nombre;

    /**
     * Tipo de comparador para la busqueda por campo nombre
     */
    private TextComparator nombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombre para busquedas tipo IN
     */
    private List<String> nombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombre es NULL
     */
    private boolean nombreIsNull = false;

    /**
     * Permite buscar cuando campo nombre es NOT NULL
     */
    private boolean nombreIsNotNull = false;

    /**
     * Valor de busqueda de campo urlEndpoint
     */
    private String urlEndpoint;

    /**
     * Tipo de comparador para la busqueda por campo urlEndpoint
     */
    private TextComparator urlEndpointComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo urlEndpoint para busquedas tipo IN
     */
    private List<String> urlEndpointIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo urlEndpoint es NULL
     */
    private boolean urlEndpointIsNull = false;

    /**
     * Permite buscar cuando campo urlEndpoint es NOT NULL
     */
    private boolean urlEndpointIsNotNull = false;

    /**
     * Valor de busqueda de campo serviceName
     */
    private String serviceName;

    /**
     * Tipo de comparador para la busqueda por campo serviceName
     */
    private TextComparator serviceNameComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo serviceName para busquedas tipo IN
     */
    private List<String> serviceNameIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo serviceName es NULL
     */
    private boolean serviceNameIsNull = false;

    /**
     * Permite buscar cuando campo serviceName es NOT NULL
     */
    private boolean serviceNameIsNotNull = false;

    /**
     * Valor de busqueda de campo portName
     */
    private String portName;

    /**
     * Tipo de comparador para la busqueda por campo portName
     */
    private TextComparator portNameComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo portName para busquedas tipo IN
     */
    private List<String> portNameIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo portName es NULL
     */
    private boolean portNameIsNull = false;

    /**
     * Permite buscar cuando campo portName es NOT NULL
     */
    private boolean portNameIsNotNull = false;

    /**
     * Valor de busqueda de campo targetName
     */
    private String targetName;

    /**
     * Tipo de comparador para la busqueda por campo targetName
     */
    private TextComparator targetNameComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo targetName para busquedas tipo IN
     */
    private List<String> targetNameIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo targetName es NULL
     */
    private boolean targetNameIsNull = false;

    /**
     * Permite buscar cuando campo targetName es NOT NULL
     */
    private boolean targetNameIsNotNull = false;

    /**
     * Valor de busqueda de campo operation
     */
    private String operation;

    /**
     * Tipo de comparador para la busqueda por campo operation
     */
    private TextComparator operationComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo operation para busquedas tipo IN
     */
    private List<String> operationIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo operation es NULL
     */
    private boolean operationIsNull = false;

    /**
     * Permite buscar cuando campo operation es NOT NULL
     */
    private boolean operationIsNotNull = false;

    /**
     * Valor de busqueda de campo timeout
     */
    private Long timeout;

    /**
     * Lista de valores del campo timeout para busquedas tipo IN
     */
    private List<Long> timeoutIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo timeout es NULL
     */
    private boolean timeoutIsNull = false;

    /**
     * Permite buscar cuando campo timeout es NOT NULL
     */
    private boolean timeoutIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaCreacion
     */
    private Date fechaCreacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaCreacion
     */
    private Date fechaCreacionMax;

    /**
     * Permite buscar cuando campo fechaCreacion es NULL
     */
    private boolean fechaCreacionIsNull = false;

    /**
     * Permite buscar cuando campo fechaCreacion es NOT NULL
     */
    private boolean fechaCreacionIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaActualizacion
     */
    private Date fechaActualizacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaActualizacion
     */
    private Date fechaActualizacionMax;

    /**
     * Permite buscar cuando campo fechaActualizacion es NULL
     */
    private boolean fechaActualizacionIsNull = false;

    /**
     * Permite buscar cuando campo fechaActualizacion es NOT NULL
     */
    private boolean fechaActualizacionIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre comunicacion
     */
    private boolean innerJoinComunicacion = false;

    /**
     * Indica si en la consulta se hace un left join con el padre comunicacion
     */
    private boolean leftJoinComunicacion = false;

    /**
     * Constructor default
     */
    public EndpointQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public EndpointQuery(Long idEndpoint) {
        setIdEndpoint(idEndpoint);
    }

    /**
     * Valor de busqueda de campo idEndpoint
     * @return Long.
     */
    public Long getIdEndpoint() {
        return idEndpoint;
    }

    /**
     * Valor de busqueda de campo idEndpoint
     * @param idEndpoint Valor de seteo.
     */
    public void setIdEndpoint(Long idEndpoint) {
        this.idEndpoint = idEndpoint;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdEndpointIn() {
        return this.idEndpointIn;
    }

    /**
     * @param idEndpoint Valor a agregar.
     */
    public void addIdEndpointIn(Long idEndpoint) {
        this.idEndpointIn.add(idEndpoint);
    }

    /**
     * Valor de busqueda de campo comunicacion
     * @return Comunicacion.
     */
    public ComunicacionQuery getComunicacion() {
        return comunicacion;
    }

    /**
     * Valor de busqueda de campo comunicacion
     * @param comunicacion Valor de seteo.
     */
    public void setComunicacion(ComunicacionQuery comunicacion) {
        this.comunicacion = comunicacion;
    }

    /**
     * @return List<Comunicacion>.
     */
    public List<Comunicacion> getComunicacionIdIn() {
        return this.comunicacionIdIn;
    }

    /**
     * @param comunicacion Valor a agregar.
     */
    public void addComunicacionIdIn(Comunicacion comunicacion) {
        this.comunicacionIdIn.add(comunicacion);
    }

    /**
     * Permite buscar cuando campo comunicacion es NULL
     * @return boolean.
     */
    public boolean isComunicacionIsNull() {
        return comunicacionIsNull;
    }

    /**
     * Permite buscar cuando campo comunicacion es NULL
     * @param comunicacionIsNull Valor de seteo.
     */
    public void setComunicacionIsNull(boolean comunicacionIsNull) {
        this.comunicacionIsNull = comunicacionIsNull;
    }

    /**
     * Permite buscar cuando campo comunicacion es NOT NULL
     * @return boolean.
     */
    public boolean isComunicacionIsNotNull() {
        return comunicacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo comunicacion es NOT NULL
     * @param comunicacionIsNotNull Valor de seteo.
     */
    public void setComunicacionIsNotNull(boolean comunicacionIsNotNull) {
        this.comunicacionIsNotNull = comunicacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombre
     * @return String.
     */
    public String getNombre() {
        if (nombre != null) {
            switch (nombreComparator) {
	            case STARTS_WITH:
	                return nombre + "%";
	            case CONTAINS:
	                return "%" + nombre + "%";
	            case ENDS_WITH:
	                return "%" + nombre;
	            case EQUALS:
                	return nombre;
            }
        }
        return nombre;
    }

    /**
     * Valor de busqueda de campo nombre
     * @param nombre Valor de seteo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombre
     * @return nombreComparator.
     */
    public TextComparator getNombreComparator() {
        return nombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombre
     * @param nombreComparator Valor de seteo.
     */
    public void setNombreComparator(TextComparator nombreComparator) {
        this.nombreComparator = nombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreIn() {
        return this.nombreIn;
    }

    /**
     * @param nombre Valor a agregar.
     */
    public void addNombreIn(String nombre) {
        this.nombreIn.add(nombre);
    }

    /**
     * Permite buscar cuando campo nombre es NULL
     * @return boolean.
     */
    public boolean isNombreIsNull() {
        return nombreIsNull;
    }

    /**
     * Permite buscar cuando campo nombre es NULL
     * @param nombreIsNull Valor de seteo.
     */
    public void setNombreIsNull(boolean nombreIsNull) {
        this.nombreIsNull = nombreIsNull;
    }

    /**
     * Permite buscar cuando campo nombre es NOT NULL
     * @return boolean.
     */
    public boolean isNombreIsNotNull() {
        return nombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombre es NOT NULL
     * @param nombreIsNotNull Valor de seteo.
     */
    public void setNombreIsNotNull(boolean nombreIsNotNull) {
        this.nombreIsNotNull = nombreIsNotNull;
    }

    /**
     * Valor de busqueda de campo urlEndpoint
     * @return String.
     */
    public String getUrlEndpoint() {
        if (urlEndpoint != null) {
            switch (urlEndpointComparator) {
	            case STARTS_WITH:
	                return urlEndpoint + "%";
	            case CONTAINS:
	                return "%" + urlEndpoint + "%";
	            case ENDS_WITH:
	                return "%" + urlEndpoint;
	            case EQUALS:
                	return urlEndpoint;
            }
        }
        return urlEndpoint;
    }

    /**
     * Valor de busqueda de campo urlEndpoint
     * @param urlEndpoint Valor de seteo.
     */
    public void setUrlEndpoint(String urlEndpoint) {
        this.urlEndpoint = urlEndpoint;
    }

    /**
     * Tipo de comparador para la busqueda por campo urlEndpoint
     * @return urlEndpointComparator.
     */
    public TextComparator getUrlEndpointComparator() {
        return urlEndpointComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo urlEndpoint
     * @param urlEndpointComparator Valor de seteo.
     */
    public void setUrlEndpointComparator(TextComparator urlEndpointComparator) {
        this.urlEndpointComparator = urlEndpointComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUrlEndpointIn() {
        return this.urlEndpointIn;
    }

    /**
     * @param urlEndpoint Valor a agregar.
     */
    public void addUrlEndpointIn(String urlEndpoint) {
        this.urlEndpointIn.add(urlEndpoint);
    }

    /**
     * Permite buscar cuando campo urlEndpoint es NULL
     * @return boolean.
     */
    public boolean isUrlEndpointIsNull() {
        return urlEndpointIsNull;
    }

    /**
     * Permite buscar cuando campo urlEndpoint es NULL
     * @param urlEndpointIsNull Valor de seteo.
     */
    public void setUrlEndpointIsNull(boolean urlEndpointIsNull) {
        this.urlEndpointIsNull = urlEndpointIsNull;
    }

    /**
     * Permite buscar cuando campo urlEndpoint es NOT NULL
     * @return boolean.
     */
    public boolean isUrlEndpointIsNotNull() {
        return urlEndpointIsNotNull;
    }

    /**
     * Permite buscar cuando campo urlEndpoint es NOT NULL
     * @param urlEndpointIsNotNull Valor de seteo.
     */
    public void setUrlEndpointIsNotNull(boolean urlEndpointIsNotNull) {
        this.urlEndpointIsNotNull = urlEndpointIsNotNull;
    }

    /**
     * Valor de busqueda de campo serviceName
     * @return String.
     */
    public String getServiceName() {
        if (serviceName != null) {
            switch (serviceNameComparator) {
	            case STARTS_WITH:
	                return serviceName + "%";
	            case CONTAINS:
	                return "%" + serviceName + "%";
	            case ENDS_WITH:
	                return "%" + serviceName;
	            case EQUALS:
                	return serviceName;
            }
        }
        return serviceName;
    }

    /**
     * Valor de busqueda de campo serviceName
     * @param serviceName Valor de seteo.
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Tipo de comparador para la busqueda por campo serviceName
     * @return serviceNameComparator.
     */
    public TextComparator getServiceNameComparator() {
        return serviceNameComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo serviceName
     * @param serviceNameComparator Valor de seteo.
     */
    public void setServiceNameComparator(TextComparator serviceNameComparator) {
        this.serviceNameComparator = serviceNameComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getServiceNameIn() {
        return this.serviceNameIn;
    }

    /**
     * @param serviceName Valor a agregar.
     */
    public void addServiceNameIn(String serviceName) {
        this.serviceNameIn.add(serviceName);
    }

    /**
     * Permite buscar cuando campo serviceName es NULL
     * @return boolean.
     */
    public boolean isServiceNameIsNull() {
        return serviceNameIsNull;
    }

    /**
     * Permite buscar cuando campo serviceName es NULL
     * @param serviceNameIsNull Valor de seteo.
     */
    public void setServiceNameIsNull(boolean serviceNameIsNull) {
        this.serviceNameIsNull = serviceNameIsNull;
    }

    /**
     * Permite buscar cuando campo serviceName es NOT NULL
     * @return boolean.
     */
    public boolean isServiceNameIsNotNull() {
        return serviceNameIsNotNull;
    }

    /**
     * Permite buscar cuando campo serviceName es NOT NULL
     * @param serviceNameIsNotNull Valor de seteo.
     */
    public void setServiceNameIsNotNull(boolean serviceNameIsNotNull) {
        this.serviceNameIsNotNull = serviceNameIsNotNull;
    }

    /**
     * Valor de busqueda de campo portName
     * @return String.
     */
    public String getPortName() {
        if (portName != null) {
            switch (portNameComparator) {
	            case STARTS_WITH:
	                return portName + "%";
	            case CONTAINS:
	                return "%" + portName + "%";
	            case ENDS_WITH:
	                return "%" + portName;
	            case EQUALS:
                	return portName;
            }
        }
        return portName;
    }

    /**
     * Valor de busqueda de campo portName
     * @param portName Valor de seteo.
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

    /**
     * Tipo de comparador para la busqueda por campo portName
     * @return portNameComparator.
     */
    public TextComparator getPortNameComparator() {
        return portNameComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo portName
     * @param portNameComparator Valor de seteo.
     */
    public void setPortNameComparator(TextComparator portNameComparator) {
        this.portNameComparator = portNameComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getPortNameIn() {
        return this.portNameIn;
    }

    /**
     * @param portName Valor a agregar.
     */
    public void addPortNameIn(String portName) {
        this.portNameIn.add(portName);
    }

    /**
     * Permite buscar cuando campo portName es NULL
     * @return boolean.
     */
    public boolean isPortNameIsNull() {
        return portNameIsNull;
    }

    /**
     * Permite buscar cuando campo portName es NULL
     * @param portNameIsNull Valor de seteo.
     */
    public void setPortNameIsNull(boolean portNameIsNull) {
        this.portNameIsNull = portNameIsNull;
    }

    /**
     * Permite buscar cuando campo portName es NOT NULL
     * @return boolean.
     */
    public boolean isPortNameIsNotNull() {
        return portNameIsNotNull;
    }

    /**
     * Permite buscar cuando campo portName es NOT NULL
     * @param portNameIsNotNull Valor de seteo.
     */
    public void setPortNameIsNotNull(boolean portNameIsNotNull) {
        this.portNameIsNotNull = portNameIsNotNull;
    }

    /**
     * Valor de busqueda de campo targetName
     * @return String.
     */
    public String getTargetName() {
        if (targetName != null) {
            switch (targetNameComparator) {
	            case STARTS_WITH:
	                return targetName + "%";
	            case CONTAINS:
	                return "%" + targetName + "%";
	            case ENDS_WITH:
	                return "%" + targetName;
	            case EQUALS:
                	return targetName;
            }
        }
        return targetName;
    }

    /**
     * Valor de busqueda de campo targetName
     * @param targetName Valor de seteo.
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * Tipo de comparador para la busqueda por campo targetName
     * @return targetNameComparator.
     */
    public TextComparator getTargetNameComparator() {
        return targetNameComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo targetName
     * @param targetNameComparator Valor de seteo.
     */
    public void setTargetNameComparator(TextComparator targetNameComparator) {
        this.targetNameComparator = targetNameComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTargetNameIn() {
        return this.targetNameIn;
    }

    /**
     * @param targetName Valor a agregar.
     */
    public void addTargetNameIn(String targetName) {
        this.targetNameIn.add(targetName);
    }

    /**
     * Permite buscar cuando campo targetName es NULL
     * @return boolean.
     */
    public boolean isTargetNameIsNull() {
        return targetNameIsNull;
    }

    /**
     * Permite buscar cuando campo targetName es NULL
     * @param targetNameIsNull Valor de seteo.
     */
    public void setTargetNameIsNull(boolean targetNameIsNull) {
        this.targetNameIsNull = targetNameIsNull;
    }

    /**
     * Permite buscar cuando campo targetName es NOT NULL
     * @return boolean.
     */
    public boolean isTargetNameIsNotNull() {
        return targetNameIsNotNull;
    }

    /**
     * Permite buscar cuando campo targetName es NOT NULL
     * @param targetNameIsNotNull Valor de seteo.
     */
    public void setTargetNameIsNotNull(boolean targetNameIsNotNull) {
        this.targetNameIsNotNull = targetNameIsNotNull;
    }

    /**
     * Valor de busqueda de campo operation
     * @return String.
     */
    public String getOperation() {
        if (operation != null) {
            switch (operationComparator) {
	            case STARTS_WITH:
	                return operation + "%";
	            case CONTAINS:
	                return "%" + operation + "%";
	            case ENDS_WITH:
	                return "%" + operation;
	            case EQUALS:
                	return operation;
            }
        }
        return operation;
    }

    /**
     * Valor de busqueda de campo operation
     * @param operation Valor de seteo.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Tipo de comparador para la busqueda por campo operation
     * @return operationComparator.
     */
    public TextComparator getOperationComparator() {
        return operationComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo operation
     * @param operationComparator Valor de seteo.
     */
    public void setOperationComparator(TextComparator operationComparator) {
        this.operationComparator = operationComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getOperationIn() {
        return this.operationIn;
    }

    /**
     * @param operation Valor a agregar.
     */
    public void addOperationIn(String operation) {
        this.operationIn.add(operation);
    }

    /**
     * Permite buscar cuando campo operation es NULL
     * @return boolean.
     */
    public boolean isOperationIsNull() {
        return operationIsNull;
    }

    /**
     * Permite buscar cuando campo operation es NULL
     * @param operationIsNull Valor de seteo.
     */
    public void setOperationIsNull(boolean operationIsNull) {
        this.operationIsNull = operationIsNull;
    }

    /**
     * Permite buscar cuando campo operation es NOT NULL
     * @return boolean.
     */
    public boolean isOperationIsNotNull() {
        return operationIsNotNull;
    }

    /**
     * Permite buscar cuando campo operation es NOT NULL
     * @param operationIsNotNull Valor de seteo.
     */
    public void setOperationIsNotNull(boolean operationIsNotNull) {
        this.operationIsNotNull = operationIsNotNull;
    }

    /**
     * Valor de busqueda de campo timeout
     * @return Long.
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * Valor de busqueda de campo timeout
     * @param timeout Valor de seteo.
     */
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getTimeoutIn() {
        return this.timeoutIn;
    }

    /**
     * @param timeout Valor a agregar.
     */
    public void addTimeoutIn(Long timeout) {
        this.timeoutIn.add(timeout);
    }

    /**
     * Permite buscar cuando campo timeout es NULL
     * @return boolean.
     */
    public boolean isTimeoutIsNull() {
        return timeoutIsNull;
    }

    /**
     * Permite buscar cuando campo timeout es NULL
     * @param timeoutIsNull Valor de seteo.
     */
    public void setTimeoutIsNull(boolean timeoutIsNull) {
        this.timeoutIsNull = timeoutIsNull;
    }

    /**
     * Permite buscar cuando campo timeout es NOT NULL
     * @return boolean.
     */
    public boolean isTimeoutIsNotNull() {
        return timeoutIsNotNull;
    }

    /**
     * Permite buscar cuando campo timeout es NOT NULL
     * @param timeoutIsNotNull Valor de seteo.
     */
    public void setTimeoutIsNotNull(boolean timeoutIsNotNull) {
        this.timeoutIsNotNull = timeoutIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaCreacion
     * @return ${field.getName()}Min.
     */
    public Date getFechaCreacionMin() {
        if (fechaCreacionMin != null) {
            return DateUtil.toDayBegin(fechaCreacionMin);
        }
        return fechaCreacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaCreacion
     * @param fechaCreacionMin Valor de seteo.
     */
    public void setFechaCreacionMin(Date fechaCreacionMin) {
        this.fechaCreacionMin = fechaCreacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaCreacion
     * @return fechaCreacionMax.
     */
    public Date getFechaCreacionMax() {
        if (fechaCreacionMax != null) {
            return DateUtil.toDayEnd(fechaCreacionMax);
        }
        return fechaCreacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaCreacion
     * @param fechaCreacionMax Valor de seteo.
     */
    public void setFechaCreacionMax(Date fechaCreacionMax) {
        this.fechaCreacionMax = fechaCreacionMax;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NULL
     * @return boolean.
     */
    public boolean isFechaCreacionIsNull() {
        return fechaCreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NULL
     * @param fechaCreacionIsNull Valor de seteo.
     */
    public void setFechaCreacionIsNull(boolean fechaCreacionIsNull) {
        this.fechaCreacionIsNull = fechaCreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaCreacionIsNotNull() {
        return fechaCreacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NOT NULL
     * @param fechaCreacionIsNotNull Valor de seteo.
     */
    public void setFechaCreacionIsNotNull(boolean fechaCreacionIsNotNull) {
        this.fechaCreacionIsNotNull = fechaCreacionIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaActualizacion
     * @return ${field.getName()}Min.
     */
    public Date getFechaActualizacionMin() {
        if (fechaActualizacionMin != null) {
            return DateUtil.toDayBegin(fechaActualizacionMin);
        }
        return fechaActualizacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaActualizacion
     * @param fechaActualizacionMin Valor de seteo.
     */
    public void setFechaActualizacionMin(Date fechaActualizacionMin) {
        this.fechaActualizacionMin = fechaActualizacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaActualizacion
     * @return fechaActualizacionMax.
     */
    public Date getFechaActualizacionMax() {
        if (fechaActualizacionMax != null) {
            return DateUtil.toDayEnd(fechaActualizacionMax);
        }
        return fechaActualizacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaActualizacion
     * @param fechaActualizacionMax Valor de seteo.
     */
    public void setFechaActualizacionMax(Date fechaActualizacionMax) {
        this.fechaActualizacionMax = fechaActualizacionMax;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NULL
     * @return boolean.
     */
    public boolean isFechaActualizacionIsNull() {
        return fechaActualizacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NULL
     * @param fechaActualizacionIsNull Valor de seteo.
     */
    public void setFechaActualizacionIsNull(boolean fechaActualizacionIsNull) {
        this.fechaActualizacionIsNull = fechaActualizacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaActualizacionIsNotNull() {
        return fechaActualizacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NOT NULL
     * @param fechaActualizacionIsNotNull Valor de seteo.
     */
    public void setFechaActualizacionIsNotNull(boolean fechaActualizacionIsNotNull) {
        this.fechaActualizacionIsNotNull = fechaActualizacionIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinComunicacion() {
        return innerJoinComunicacion;
    }

    /**
     * @param innerJoinComunicacion Valor de seteo.
     */
    public void setInnerJoinComunicacion(boolean innerJoinComunicacion) {
        this.innerJoinComunicacion = innerJoinComunicacion;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinComunicacion() {
        return leftJoinComunicacion;
    }

    /**
     * @param leftJoinComunicacion Valor de seteo.
     */
    public void setLeftJoinComunicacion(boolean leftJoinComunicacion) {
        this.leftJoinComunicacion = leftJoinComunicacion;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdEndpoint() != null) {
            criteria.add(Restrictions.eq(IDENDPOINT, getIdEndpoint()));
        }

        if (getIdEndpointIn().size() > 0) {
            criteria.add(Restrictions.in(IDENDPOINT, getIdEndpointIn()));
        }

        // Campo entidad padre comunicacion
        
        // Si se hace join fetch con el padre
        Criteria comunicacionCriteria = null;
        if (isInnerJoinComunicacion()) {
            comunicacionCriteria = criteria.createCriteria(COMUNICACION, "a_" + COMUNICACION, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinComunicacion()) {
            comunicacionCriteria = criteria.createCriteria(COMUNICACION, "a_" + COMUNICACION, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getComunicacion() != null) {
            if (getComunicacion().getIdComunicacion() == null) {
                if (comunicacionCriteria == null) {
                    comunicacionCriteria = criteria.createCriteria(COMUNICACION, "a_" + COMUNICACION);
                }
                getComunicacion().addCriteria(comunicacionCriteria, useOrder);
            } else {
                Comunicacion parent = new Comunicacion();
                parent.setIdComunicacion(getComunicacion().getIdComunicacion());
                criteria.add(Restrictions.eq(COMUNICACION, parent));
            }
        }

        if (getComunicacionIdIn().size() > 0) {
            criteria.add(Restrictions.in(COMUNICACION, getComunicacionIdIn()));
        }

        if (isComunicacionIsNull()) {
            criteria.add(Restrictions.isNull(COMUNICACION));
        }

        if (isComunicacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(COMUNICACION));
        }

        if (getNombre() != null) {
            if (getNombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRE, getNombre()));
            } 
            else if (getNombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRE, getNombre()));
            }
            else {
                criteria.add(Restrictions.like(NOMBRE, getNombre()));
            }
        }

        if (getNombreIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBRE, getNombreIn()));
        }

        if (isNombreIsNull()) {
            criteria.add(Restrictions.isNull(NOMBRE));
        }

        if (isNombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBRE));
        }

        if (getUrlEndpoint() != null) {
            if (getUrlEndpointComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(URLENDPOINT, getUrlEndpoint()));
            } 
            else if (getUrlEndpointComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(URLENDPOINT, getUrlEndpoint()));
            }
            else {
                criteria.add(Restrictions.like(URLENDPOINT, getUrlEndpoint()));
            }
        }

        if (getUrlEndpointIn().size() > 0) {
            criteria.add(Restrictions.in(URLENDPOINT, getUrlEndpointIn()));
        }

        if (isUrlEndpointIsNull()) {
            criteria.add(Restrictions.isNull(URLENDPOINT));
        }

        if (isUrlEndpointIsNotNull()) {
            criteria.add(Restrictions.isNotNull(URLENDPOINT));
        }

        if (getServiceName() != null) {
            if (getServiceNameComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SERVICENAME, getServiceName()));
            } 
            else if (getServiceNameComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SERVICENAME, getServiceName()));
            }
            else {
                criteria.add(Restrictions.like(SERVICENAME, getServiceName()));
            }
        }

        if (getServiceNameIn().size() > 0) {
            criteria.add(Restrictions.in(SERVICENAME, getServiceNameIn()));
        }

        if (isServiceNameIsNull()) {
            criteria.add(Restrictions.isNull(SERVICENAME));
        }

        if (isServiceNameIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVICENAME));
        }

        if (getPortName() != null) {
            if (getPortNameComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PORTNAME, getPortName()));
            } 
            else if (getPortNameComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PORTNAME, getPortName()));
            }
            else {
                criteria.add(Restrictions.like(PORTNAME, getPortName()));
            }
        }

        if (getPortNameIn().size() > 0) {
            criteria.add(Restrictions.in(PORTNAME, getPortNameIn()));
        }

        if (isPortNameIsNull()) {
            criteria.add(Restrictions.isNull(PORTNAME));
        }

        if (isPortNameIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PORTNAME));
        }

        if (getTargetName() != null) {
            if (getTargetNameComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TARGETNAME, getTargetName()));
            } 
            else if (getTargetNameComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TARGETNAME, getTargetName()));
            }
            else {
                criteria.add(Restrictions.like(TARGETNAME, getTargetName()));
            }
        }

        if (getTargetNameIn().size() > 0) {
            criteria.add(Restrictions.in(TARGETNAME, getTargetNameIn()));
        }

        if (isTargetNameIsNull()) {
            criteria.add(Restrictions.isNull(TARGETNAME));
        }

        if (isTargetNameIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TARGETNAME));
        }

        if (getOperation() != null) {
            if (getOperationComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(OPERATION, getOperation()));
            } 
            else if (getOperationComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(OPERATION, getOperation()));
            }
            else {
                criteria.add(Restrictions.like(OPERATION, getOperation()));
            }
        }

        if (getOperationIn().size() > 0) {
            criteria.add(Restrictions.in(OPERATION, getOperationIn()));
        }

        if (isOperationIsNull()) {
            criteria.add(Restrictions.isNull(OPERATION));
        }

        if (isOperationIsNotNull()) {
            criteria.add(Restrictions.isNotNull(OPERATION));
        }

        if (getTimeout() != null) {
            criteria.add(Restrictions.eq(TIMEOUT, getTimeout()));
        }

        if (getTimeoutIn().size() > 0) {
            criteria.add(Restrictions.in(TIMEOUT, getTimeoutIn()));
        }

        if (isTimeoutIsNull()) {
            criteria.add(Restrictions.isNull(TIMEOUT));
        }

        if (isTimeoutIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TIMEOUT));
        }

        if (getFechaCreacionMin() != null) {
            criteria.add(Restrictions.ge(FECHACREACION, getFechaCreacionMin()));
        }

        if (getFechaCreacionMax() != null) {
            criteria.add(Restrictions.le(FECHACREACION, getFechaCreacionMax()));
        }

        if (isFechaCreacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHACREACION));
        }

        if (isFechaCreacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHACREACION));
        }

        if (getFechaActualizacionMin() != null) {
            criteria.add(Restrictions.ge(FECHAACTUALIZACION, getFechaActualizacionMin()));
        }

        if (getFechaActualizacionMax() != null) {
            criteria.add(Restrictions.le(FECHAACTUALIZACION, getFechaActualizacionMax()));
        }

        if (isFechaActualizacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAACTUALIZACION));
        }

        if (isFechaActualizacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAACTUALIZACION));
        }
        //Aplica ordenamiento solo si corresponde. En count y searchUnique no se utiliza.
        if (useOrder) {
            applyOrder(criteria);
        }
    }
    
    /**
     * Convierte el bean query a String, en representacion tipo arbol
     */
    public String toString() {
        return new BeanFormatter().format(this);
    }
    
}
 
