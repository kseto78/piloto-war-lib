/*
 *
 * archivo: ComunicacionQuery.java
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
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.misim.bus.model.Comunicacion;

/**
 * Clase con criterios de busqueda para la entidad Comunicacion
 */
public class ComunicacionQuery extends AbstractHibernateQueryEntity<Comunicacion> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDCOMUNICACION = "idComunicacion";
    public static final String NOMBRE = "nombre";


    /**
     * Valor de busqueda de campo idComunicacion
     */
    private Long idComunicacion;

    /**
     * Lista de valores del campo idComunicacion para busquedas tipo IN
     */
    private List<Long> idComunicacionIn = new ArrayList<Long>(0);

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
     * Constructor default
     */
    public ComunicacionQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ComunicacionQuery(Long idComunicacion) {
        setIdComunicacion(idComunicacion);
    }

    /**
     * Valor de busqueda de campo idComunicacion
     * @return Long.
     */
    public Long getIdComunicacion() {
        return idComunicacion;
    }

    /**
     * Valor de busqueda de campo idComunicacion
     * @param idComunicacion Valor de seteo.
     */
    public void setIdComunicacion(Long idComunicacion) {
        this.idComunicacion = idComunicacion;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdComunicacionIn() {
        return this.idComunicacionIn;
    }

    /**
     * @param idComunicacion Valor a agregar.
     */
    public void addIdComunicacionIn(Long idComunicacion) {
        this.idComunicacionIn.add(idComunicacion);
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdComunicacion() != null) {
            criteria.add(Restrictions.eq(IDCOMUNICACION, getIdComunicacion()));
        }

        if (getIdComunicacionIn().size() > 0) {
            criteria.add(Restrictions.in(IDCOMUNICACION, getIdComunicacionIn()));
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
 
