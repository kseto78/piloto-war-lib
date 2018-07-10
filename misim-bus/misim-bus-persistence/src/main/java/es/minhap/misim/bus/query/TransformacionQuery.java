/*
 *
 * archivo: TransformacionQuery.java
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
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.misim.bus.model.Transformacion;

/**
 * Clase con criterios de busqueda para la entidad Transformacion
 */
public class TransformacionQuery extends AbstractHibernateQueryEntity<Transformacion> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDTRANSFORMACION = "idTransformacion";
    public static final String NOMBRE = "nombre";
    public static final String XSLPETICION = "xslPeticion";
    public static final String XSLRESPUESTA = "xslRespuesta";
    public static final String XSLFAULT = "xslFault";
    public static final String FECHACREACION = "fechaCreacion";
    public static final String FECHAACTUALIZACION = "fechaActualizacion";


    /**
     * Valor de busqueda de campo idTransformacion
     */
    private Long idTransformacion;

    /**
     * Lista de valores del campo idTransformacion para busquedas tipo IN
     */
    private List<Long> idTransformacionIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo xslPeticion
     */
    private String xslPeticion;

    /**
     * Tipo de comparador para la busqueda por campo xslPeticion
     */
    private TextComparator xslPeticionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo xslPeticion para busquedas tipo IN
     */
    private List<String> xslPeticionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo xslPeticion es NULL
     */
    private boolean xslPeticionIsNull = false;

    /**
     * Permite buscar cuando campo xslPeticion es NOT NULL
     */
    private boolean xslPeticionIsNotNull = false;

    /**
     * Valor de busqueda de campo xslRespuesta
     */
    private String xslRespuesta;

    /**
     * Tipo de comparador para la busqueda por campo xslRespuesta
     */
    private TextComparator xslRespuestaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo xslRespuesta para busquedas tipo IN
     */
    private List<String> xslRespuestaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo xslRespuesta es NULL
     */
    private boolean xslRespuestaIsNull = false;

    /**
     * Permite buscar cuando campo xslRespuesta es NOT NULL
     */
    private boolean xslRespuestaIsNotNull = false;

    /**
     * Valor de busqueda de campo xslFault
     */
    private String xslFault;

    /**
     * Tipo de comparador para la busqueda por campo xslFault
     */
    private TextComparator xslFaultComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo xslFault para busquedas tipo IN
     */
    private List<String> xslFaultIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo xslFault es NULL
     */
    private boolean xslFaultIsNull = false;

    /**
     * Permite buscar cuando campo xslFault es NOT NULL
     */
    private boolean xslFaultIsNotNull = false;

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
     * Constructor default
     */
    public TransformacionQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TransformacionQuery(Long idTransformacion) {
        setIdTransformacion(idTransformacion);
    }

    /**
     * Valor de busqueda de campo idTransformacion
     * @return Long.
     */
    public Long getIdTransformacion() {
        return idTransformacion;
    }

    /**
     * Valor de busqueda de campo idTransformacion
     * @param idTransformacion Valor de seteo.
     */
    public void setIdTransformacion(Long idTransformacion) {
        this.idTransformacion = idTransformacion;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdTransformacionIn() {
        return this.idTransformacionIn;
    }

    /**
     * @param idTransformacion Valor a agregar.
     */
    public void addIdTransformacionIn(Long idTransformacion) {
        this.idTransformacionIn.add(idTransformacion);
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
     * Valor de busqueda de campo xslPeticion
     * @return String.
     */
    public String getXslPeticion() {
        if (xslPeticion != null) {
            switch (xslPeticionComparator) {
	            case STARTS_WITH:
	                return xslPeticion + "%";
	            case CONTAINS:
	                return "%" + xslPeticion + "%";
	            case ENDS_WITH:
	                return "%" + xslPeticion;
	            case EQUALS:
                	return xslPeticion;
            }
        }
        return xslPeticion;
    }

    /**
     * Valor de busqueda de campo xslPeticion
     * @param xslPeticion Valor de seteo.
     */
    public void setXslPeticion(String xslPeticion) {
        this.xslPeticion = xslPeticion;
    }

    /**
     * Tipo de comparador para la busqueda por campo xslPeticion
     * @return xslPeticionComparator.
     */
    public TextComparator getXslPeticionComparator() {
        return xslPeticionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo xslPeticion
     * @param xslPeticionComparator Valor de seteo.
     */
    public void setXslPeticionComparator(TextComparator xslPeticionComparator) {
        this.xslPeticionComparator = xslPeticionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getXslPeticionIn() {
        return this.xslPeticionIn;
    }

    /**
     * @param xslPeticion Valor a agregar.
     */
    public void addXslPeticionIn(String xslPeticion) {
        this.xslPeticionIn.add(xslPeticion);
    }

    /**
     * Permite buscar cuando campo xslPeticion es NULL
     * @return boolean.
     */
    public boolean isXslPeticionIsNull() {
        return xslPeticionIsNull;
    }

    /**
     * Permite buscar cuando campo xslPeticion es NULL
     * @param xslPeticionIsNull Valor de seteo.
     */
    public void setXslPeticionIsNull(boolean xslPeticionIsNull) {
        this.xslPeticionIsNull = xslPeticionIsNull;
    }

    /**
     * Permite buscar cuando campo xslPeticion es NOT NULL
     * @return boolean.
     */
    public boolean isXslPeticionIsNotNull() {
        return xslPeticionIsNotNull;
    }

    /**
     * Permite buscar cuando campo xslPeticion es NOT NULL
     * @param xslPeticionIsNotNull Valor de seteo.
     */
    public void setXslPeticionIsNotNull(boolean xslPeticionIsNotNull) {
        this.xslPeticionIsNotNull = xslPeticionIsNotNull;
    }

    /**
     * Valor de busqueda de campo xslRespuesta
     * @return String.
     */
    public String getXslRespuesta() {
        if (xslRespuesta != null) {
            switch (xslRespuestaComparator) {
	            case STARTS_WITH:
	                return xslRespuesta + "%";
	            case CONTAINS:
	                return "%" + xslRespuesta + "%";
	            case ENDS_WITH:
	                return "%" + xslRespuesta;
	            case EQUALS:
                	return xslRespuesta;
            }
        }
        return xslRespuesta;
    }

    /**
     * Valor de busqueda de campo xslRespuesta
     * @param xslRespuesta Valor de seteo.
     */
    public void setXslRespuesta(String xslRespuesta) {
        this.xslRespuesta = xslRespuesta;
    }

    /**
     * Tipo de comparador para la busqueda por campo xslRespuesta
     * @return xslRespuestaComparator.
     */
    public TextComparator getXslRespuestaComparator() {
        return xslRespuestaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo xslRespuesta
     * @param xslRespuestaComparator Valor de seteo.
     */
    public void setXslRespuestaComparator(TextComparator xslRespuestaComparator) {
        this.xslRespuestaComparator = xslRespuestaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getXslRespuestaIn() {
        return this.xslRespuestaIn;
    }

    /**
     * @param xslRespuesta Valor a agregar.
     */
    public void addXslRespuestaIn(String xslRespuesta) {
        this.xslRespuestaIn.add(xslRespuesta);
    }

    /**
     * Permite buscar cuando campo xslRespuesta es NULL
     * @return boolean.
     */
    public boolean isXslRespuestaIsNull() {
        return xslRespuestaIsNull;
    }

    /**
     * Permite buscar cuando campo xslRespuesta es NULL
     * @param xslRespuestaIsNull Valor de seteo.
     */
    public void setXslRespuestaIsNull(boolean xslRespuestaIsNull) {
        this.xslRespuestaIsNull = xslRespuestaIsNull;
    }

    /**
     * Permite buscar cuando campo xslRespuesta es NOT NULL
     * @return boolean.
     */
    public boolean isXslRespuestaIsNotNull() {
        return xslRespuestaIsNotNull;
    }

    /**
     * Permite buscar cuando campo xslRespuesta es NOT NULL
     * @param xslRespuestaIsNotNull Valor de seteo.
     */
    public void setXslRespuestaIsNotNull(boolean xslRespuestaIsNotNull) {
        this.xslRespuestaIsNotNull = xslRespuestaIsNotNull;
    }

    /**
     * Valor de busqueda de campo xslFault
     * @return String.
     */
    public String getXslFault() {
        if (xslFault != null) {
            switch (xslFaultComparator) {
	            case STARTS_WITH:
	                return xslFault + "%";
	            case CONTAINS:
	                return "%" + xslFault + "%";
	            case ENDS_WITH:
	                return "%" + xslFault;
	            case EQUALS:
                	return xslFault;
            }
        }
        return xslFault;
    }

    /**
     * Valor de busqueda de campo xslFault
     * @param xslFault Valor de seteo.
     */
    public void setXslFault(String xslFault) {
        this.xslFault = xslFault;
    }

    /**
     * Tipo de comparador para la busqueda por campo xslFault
     * @return xslFaultComparator.
     */
    public TextComparator getXslFaultComparator() {
        return xslFaultComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo xslFault
     * @param xslFaultComparator Valor de seteo.
     */
    public void setXslFaultComparator(TextComparator xslFaultComparator) {
        this.xslFaultComparator = xslFaultComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getXslFaultIn() {
        return this.xslFaultIn;
    }

    /**
     * @param xslFault Valor a agregar.
     */
    public void addXslFaultIn(String xslFault) {
        this.xslFaultIn.add(xslFault);
    }

    /**
     * Permite buscar cuando campo xslFault es NULL
     * @return boolean.
     */
    public boolean isXslFaultIsNull() {
        return xslFaultIsNull;
    }

    /**
     * Permite buscar cuando campo xslFault es NULL
     * @param xslFaultIsNull Valor de seteo.
     */
    public void setXslFaultIsNull(boolean xslFaultIsNull) {
        this.xslFaultIsNull = xslFaultIsNull;
    }

    /**
     * Permite buscar cuando campo xslFault es NOT NULL
     * @return boolean.
     */
    public boolean isXslFaultIsNotNull() {
        return xslFaultIsNotNull;
    }

    /**
     * Permite buscar cuando campo xslFault es NOT NULL
     * @param xslFaultIsNotNull Valor de seteo.
     */
    public void setXslFaultIsNotNull(boolean xslFaultIsNotNull) {
        this.xslFaultIsNotNull = xslFaultIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdTransformacion() != null) {
            criteria.add(Restrictions.eq(IDTRANSFORMACION, getIdTransformacion()));
        }

        if (getIdTransformacionIn().size() > 0) {
            criteria.add(Restrictions.in(IDTRANSFORMACION, getIdTransformacionIn()));
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

        if (getXslPeticion() != null) {
            if (getXslPeticionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(XSLPETICION, getXslPeticion()));
            } 
            else if (getXslPeticionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(XSLPETICION, getXslPeticion()));
            }
            else {
                criteria.add(Restrictions.like(XSLPETICION, getXslPeticion()));
            }
        }

        if (getXslPeticionIn().size() > 0) {
            criteria.add(Restrictions.in(XSLPETICION, getXslPeticionIn()));
        }

        if (isXslPeticionIsNull()) {
            criteria.add(Restrictions.isNull(XSLPETICION));
        }

        if (isXslPeticionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(XSLPETICION));
        }

        if (getXslRespuesta() != null) {
            if (getXslRespuestaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(XSLRESPUESTA, getXslRespuesta()));
            } 
            else if (getXslRespuestaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(XSLRESPUESTA, getXslRespuesta()));
            }
            else {
                criteria.add(Restrictions.like(XSLRESPUESTA, getXslRespuesta()));
            }
        }

        if (getXslRespuestaIn().size() > 0) {
            criteria.add(Restrictions.in(XSLRESPUESTA, getXslRespuestaIn()));
        }

        if (isXslRespuestaIsNull()) {
            criteria.add(Restrictions.isNull(XSLRESPUESTA));
        }

        if (isXslRespuestaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(XSLRESPUESTA));
        }

        if (getXslFault() != null) {
            if (getXslFaultComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(XSLFAULT, getXslFault()));
            } 
            else if (getXslFaultComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(XSLFAULT, getXslFault()));
            }
            else {
                criteria.add(Restrictions.like(XSLFAULT, getXslFault()));
            }
        }

        if (getXslFaultIn().size() > 0) {
            criteria.add(Restrictions.in(XSLFAULT, getXslFaultIn()));
        }

        if (isXslFaultIsNull()) {
            criteria.add(Restrictions.isNull(XSLFAULT));
        }

        if (isXslFaultIsNotNull()) {
            criteria.add(Restrictions.isNotNull(XSLFAULT));
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
 
