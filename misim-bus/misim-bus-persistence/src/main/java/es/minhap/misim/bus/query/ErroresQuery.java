/*
 *
 * archivo: ErroresQuery.java
 *
 * Proyecto: Comunidades
 *
 * Generated file! Do not modify.
 *
 * Developed by:
 *     everis S.A.
 *     www.everis.com
 */

package es.minhap.misim.bus.query;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

import es.minhap.common.util.DateUtil;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.misim.bus.model.Errores;

/**
 * Clase con criterios de busqueda para la entidad Errores
 */
public class ErroresQuery extends AbstractHibernateQueryEntity<Errores> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDERROR = "idError";
    public static final String NOMBRE = "nombre";
    public static final String ESTADO = "estado";
    public static final String FECHAACTUALIZACION = "fechaActualizacion";


    /**
     * Valor de busqueda de campo idError
     */
    private Long idError;

    /**
     * Lista de valores del campo idError para busquedas tipo IN
     */
    private List<Long> idErrorIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo estado
     */
    private Boolean estado;

    /**
     * Permite buscar cuando campo estado es NULL
     */
    private boolean estadoIsNull = false;

    /**
     * Permite buscar cuando campo estado es NOT NULL
     */
    private boolean estadoIsNotNull = false;

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
    public ErroresQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ErroresQuery(Long idError) {
        setIdError(idError);
    }

    /**
     * Valor de busqueda de campo idError
     * @return Long.
     */
    public Long getIdError() {
        return idError;
    }

    /**
     * Valor de busqueda de campo idError
     * @param idError Valor de seteo.
     */
    public void setIdError(Long idError) {
        this.idError = idError;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdErrorIn() {
        return this.idErrorIn;
    }

    /**
     * @param idError Valor a agregar.
     */
    public void addIdErrorIn(Long idError) {
        this.idErrorIn.add(idError);
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
     * Valor de busqueda de campo estado
     * @return Boolean.
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * Valor de busqueda de campo estado
     * @param estado Valor de seteo.
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     * Permite buscar cuando campo estado es NULL
     * @return boolean.
     */
    public boolean isEstadoIsNull() {
        return estadoIsNull;
    }

    /**
     * Permite buscar cuando campo estado es NULL
     * @param estadoIsNull Valor de seteo.
     */
    public void setEstadoIsNull(boolean estadoIsNull) {
        this.estadoIsNull = estadoIsNull;
    }

    /**
     * Permite buscar cuando campo estado es NOT NULL
     * @return boolean.
     */
    public boolean isEstadoIsNotNull() {
        return estadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo estado es NOT NULL
     * @param estadoIsNotNull Valor de seteo.
     */
    public void setEstadoIsNotNull(boolean estadoIsNotNull) {
        this.estadoIsNotNull = estadoIsNotNull;
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

        if (getIdError() != null) {
            criteria.add(Restrictions.eq(IDERROR, getIdError()));
        }

        if (getIdErrorIn().size() > 0) {
            criteria.add(Restrictions.in(IDERROR, getIdErrorIn()));
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

        if (getEstado() != null) {
            criteria.add(Restrictions.eq(ESTADO, getEstado()));
        }

        if (isEstadoIsNull()) {
            criteria.add(Restrictions.isNull(ESTADO));
        }

        if (isEstadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADO));
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
 
