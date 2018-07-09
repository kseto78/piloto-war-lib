/*
 *
 * archivo: TblErrorMensajeLogQuery.java
 *
 * Proyecto: Administracion SIM
 *
 * Generated file! Do not modify.
 *
 * Developed by:
 *     everis S.A.
 *     www.everis.com
 */

package es.minhap.sim.query;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblErrorMensajeLog;

/**
 * Clase con criterios de busqueda para la entidad TblErrorMensajeLog
 */
public class TblErrorMensajeLogQuery extends AbstractHibernateQueryEntity<TblErrorMensajeLog> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String ERRORMENSAJELOGID = "errormensajelogid";
    public static final String FECHA = "fecha";
    public static final String OPERACION = "operacion";
    public static final String CODIGOERROR = "codigoerror";
    public static final String DESCRIPCIONERROR = "descripcionerror";


    /**
     * Valor de busqueda de campo errormensajelogid
     */
    private Long errormensajelogid;

    /**
     * Lista de valores del campo errormensajelogid para busquedas tipo IN
     */
    private List<Long> errormensajelogidIn = new ArrayList<Long>(0);

    /**
     * Valor inferior de rango de busqueda de fecha fecha
     */
    private Date fechaMin;

    /**
     * Valor superior de rango de busqueda de fecha fecha
     */
    private Date fechaMax;

    /**
     * Permite buscar cuando campo fecha es NULL
     */
    private boolean fechaIsNull = false;

    /**
     * Permite buscar cuando campo fecha es NOT NULL
     */
    private boolean fechaIsNotNull = false;

    /**
     * Valor de busqueda de campo operacion
     */
    private String operacion;

    /**
     * Tipo de comparador para la busqueda por campo operacion
     */
    private TextComparator operacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo operacion para busquedas tipo IN
     */
    private List<String> operacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo operacion es NULL
     */
    private boolean operacionIsNull = false;

    /**
     * Permite buscar cuando campo operacion es NOT NULL
     */
    private boolean operacionIsNotNull = false;

    /**
     * Valor de busqueda de campo codigoerror
     */
    private Long codigoerror;

    /**
     * Lista de valores del campo codigoerror para busquedas tipo IN
     */
    private List<Long> codigoerrorIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo codigoerror es NULL
     */
    private boolean codigoerrorIsNull = false;

    /**
     * Permite buscar cuando campo codigoerror es NOT NULL
     */
    private boolean codigoerrorIsNotNull = false;

    /**
     * Valor de busqueda de campo descripcionerror
     */
    private String descripcionerror;

    /**
     * Tipo de comparador para la busqueda por campo descripcionerror
     */
    private TextComparator descripcionerrorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo descripcionerror para busquedas tipo IN
     */
    private List<String> descripcionerrorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo descripcionerror es NULL
     */
    private boolean descripcionerrorIsNull = false;

    /**
     * Permite buscar cuando campo descripcionerror es NOT NULL
     */
    private boolean descripcionerrorIsNotNull = false;

    /**
     * Constructor default
     */
    public TblErrorMensajeLogQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblErrorMensajeLogQuery(Long errormensajelogid) {
        setErrormensajelogid(errormensajelogid);
    }

    /**
     * Valor de busqueda de campo errormensajelogid
     * @return Long.
     */
    public Long getErrormensajelogid() {
        return errormensajelogid;
    }

    /**
     * Valor de busqueda de campo errormensajelogid
     * @param errormensajelogid Valor de seteo.
     */
    public void setErrormensajelogid(Long errormensajelogid) {
        this.errormensajelogid = errormensajelogid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getErrormensajelogidIn() {
        return this.errormensajelogidIn;
    }

    /**
     * @param errormensajelogid Valor a agregar.
     */
    public void addErrormensajelogidIn(Long errormensajelogid) {
        this.errormensajelogidIn.add(errormensajelogid);
    }

    /**
     * Valor inferior de rango de busqueda de fecha fecha
     * @return ${field.getName()}Min.
     */
    public Date getFechaMin() {
        if (fechaMin != null) {
            return DateUtil.toDayBegin(fechaMin);
        }
        return fechaMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fecha
     * @param fechaMin Valor de seteo.
     */
    public void setFechaMin(Date fechaMin) {
        this.fechaMin = fechaMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fecha
     * @return fechaMax.
     */
    public Date getFechaMax() {
        if (fechaMax != null) {
            return DateUtil.toDayEnd(fechaMax);
        }
        return fechaMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fecha
     * @param fechaMax Valor de seteo.
     */
    public void setFechaMax(Date fechaMax) {
        this.fechaMax = fechaMax;
    }

    /**
     * Permite buscar cuando campo fecha es NULL
     * @return boolean.
     */
    public boolean isFechaIsNull() {
        return fechaIsNull;
    }

    /**
     * Permite buscar cuando campo fecha es NULL
     * @param fechaIsNull Valor de seteo.
     */
    public void setFechaIsNull(boolean fechaIsNull) {
        this.fechaIsNull = fechaIsNull;
    }

    /**
     * Permite buscar cuando campo fecha es NOT NULL
     * @return boolean.
     */
    public boolean isFechaIsNotNull() {
        return fechaIsNotNull;
    }

    /**
     * Permite buscar cuando campo fecha es NOT NULL
     * @param fechaIsNotNull Valor de seteo.
     */
    public void setFechaIsNotNull(boolean fechaIsNotNull) {
        this.fechaIsNotNull = fechaIsNotNull;
    }

    /**
     * Valor de busqueda de campo operacion
     * @return String.
     */
    public String getOperacion() {
        if (operacion != null) {
            switch (operacionComparator) {
	            case STARTS_WITH:
	                return operacion + "%";
	            case CONTAINS:
	                return "%" + operacion + "%";
	            case ENDS_WITH:
	                return "%" + operacion;
	            case EQUALS:
                	return operacion;
              	default:
	            	break;
            }
        }
        return operacion;
    }

    /**
     * Valor de busqueda de campo operacion
     * @param operacion Valor de seteo.
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo operacion
     * @return operacionComparator.
     */
    public TextComparator getOperacionComparator() {
        return operacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo operacion
     * @param operacionComparator Valor de seteo.
     */
    public void setOperacionComparator(TextComparator operacionComparator) {
        this.operacionComparator = operacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getOperacionIn() {
        return this.operacionIn;
    }

    /**
     * @param operacion Valor a agregar.
     */
    public void addOperacionIn(String operacion) {
        this.operacionIn.add(operacion);
    }

    /**
     * Permite buscar cuando campo operacion es NULL
     * @return boolean.
     */
    public boolean isOperacionIsNull() {
        return operacionIsNull;
    }

    /**
     * Permite buscar cuando campo operacion es NULL
     * @param operacionIsNull Valor de seteo.
     */
    public void setOperacionIsNull(boolean operacionIsNull) {
        this.operacionIsNull = operacionIsNull;
    }

    /**
     * Permite buscar cuando campo operacion es NOT NULL
     * @return boolean.
     */
    public boolean isOperacionIsNotNull() {
        return operacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo operacion es NOT NULL
     * @param operacionIsNotNull Valor de seteo.
     */
    public void setOperacionIsNotNull(boolean operacionIsNotNull) {
        this.operacionIsNotNull = operacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo codigoerror
     * @return Long.
     */
    public Long getCodigoerror() {
        return codigoerror;
    }

    /**
     * Valor de busqueda de campo codigoerror
     * @param codigoerror Valor de seteo.
     */
    public void setCodigoerror(Long codigoerror) {
        this.codigoerror = codigoerror;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getCodigoerrorIn() {
        return this.codigoerrorIn;
    }

    /**
     * @param codigoerror Valor a agregar.
     */
    public void addCodigoerrorIn(Long codigoerror) {
        this.codigoerrorIn.add(codigoerror);
    }

    /**
     * Permite buscar cuando campo codigoerror es NULL
     * @return boolean.
     */
    public boolean isCodigoerrorIsNull() {
        return codigoerrorIsNull;
    }

    /**
     * Permite buscar cuando campo codigoerror es NULL
     * @param codigoerrorIsNull Valor de seteo.
     */
    public void setCodigoerrorIsNull(boolean codigoerrorIsNull) {
        this.codigoerrorIsNull = codigoerrorIsNull;
    }

    /**
     * Permite buscar cuando campo codigoerror es NOT NULL
     * @return boolean.
     */
    public boolean isCodigoerrorIsNotNull() {
        return codigoerrorIsNotNull;
    }

    /**
     * Permite buscar cuando campo codigoerror es NOT NULL
     * @param codigoerrorIsNotNull Valor de seteo.
     */
    public void setCodigoerrorIsNotNull(boolean codigoerrorIsNotNull) {
        this.codigoerrorIsNotNull = codigoerrorIsNotNull;
    }

    /**
     * Valor de busqueda de campo descripcionerror
     * @return String.
     */
    public String getDescripcionerror() {
        if (descripcionerror != null) {
            switch (descripcionerrorComparator) {
	            case STARTS_WITH:
	                return descripcionerror + "%";
	            case CONTAINS:
	                return "%" + descripcionerror + "%";
	            case ENDS_WITH:
	                return "%" + descripcionerror;
	            case EQUALS:
                	return descripcionerror;
              	default:
	            	break;
            }
        }
        return descripcionerror;
    }

    /**
     * Valor de busqueda de campo descripcionerror
     * @param descripcionerror Valor de seteo.
     */
    public void setDescripcionerror(String descripcionerror) {
        this.descripcionerror = descripcionerror;
    }

    /**
     * Tipo de comparador para la busqueda por campo descripcionerror
     * @return descripcionerrorComparator.
     */
    public TextComparator getDescripcionerrorComparator() {
        return descripcionerrorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo descripcionerror
     * @param descripcionerrorComparator Valor de seteo.
     */
    public void setDescripcionerrorComparator(TextComparator descripcionerrorComparator) {
        this.descripcionerrorComparator = descripcionerrorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDescripcionerrorIn() {
        return this.descripcionerrorIn;
    }

    /**
     * @param descripcionerror Valor a agregar.
     */
    public void addDescripcionerrorIn(String descripcionerror) {
        this.descripcionerrorIn.add(descripcionerror);
    }

    /**
     * Permite buscar cuando campo descripcionerror es NULL
     * @return boolean.
     */
    public boolean isDescripcionerrorIsNull() {
        return descripcionerrorIsNull;
    }

    /**
     * Permite buscar cuando campo descripcionerror es NULL
     * @param descripcionerrorIsNull Valor de seteo.
     */
    public void setDescripcionerrorIsNull(boolean descripcionerrorIsNull) {
        this.descripcionerrorIsNull = descripcionerrorIsNull;
    }

    /**
     * Permite buscar cuando campo descripcionerror es NOT NULL
     * @return boolean.
     */
    public boolean isDescripcionerrorIsNotNull() {
        return descripcionerrorIsNotNull;
    }

    /**
     * Permite buscar cuando campo descripcionerror es NOT NULL
     * @param descripcionerrorIsNotNull Valor de seteo.
     */
    public void setDescripcionerrorIsNotNull(boolean descripcionerrorIsNotNull) {
        this.descripcionerrorIsNotNull = descripcionerrorIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getErrormensajelogid() != null) {
            criteria.add(Restrictions.eq(ERRORMENSAJELOGID, getErrormensajelogid()));
        }

        if (getErrormensajelogidIn().size() > 0) {
            criteria.add(Restrictions.in(ERRORMENSAJELOGID, getErrormensajelogidIn()));
        }

        if (getFechaMin() != null) {
            criteria.add(Restrictions.ge(FECHA, getFechaMin()));
        }

        if (getFechaMax() != null) {
            criteria.add(Restrictions.le(FECHA, getFechaMax()));
        }

        if (isFechaIsNull()) {
            criteria.add(Restrictions.isNull(FECHA));
        }

        if (isFechaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHA));
        }

        if (getOperacion() != null) {
            if (getOperacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(OPERACION, getOperacion()));
            } 
            else if (getOperacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(OPERACION, getOperacion()));
            }
            else if (getOperacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(OPERACION, getOperacion())));
            }
            else {
                criteria.add(Restrictions.like(OPERACION, getOperacion()));
            }
        }

        if (getOperacionIn().size() > 0) {
            criteria.add(Restrictions.in(OPERACION, getOperacionIn()));
        }

        if (isOperacionIsNull()) {
            criteria.add(Restrictions.isNull(OPERACION));
        }

        if (isOperacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(OPERACION));
        }

        if (getCodigoerror() != null) {
            criteria.add(Restrictions.eq(CODIGOERROR, getCodigoerror()));
        }

        if (getCodigoerrorIn().size() > 0) {
            criteria.add(Restrictions.in(CODIGOERROR, getCodigoerrorIn()));
        }

        if (isCodigoerrorIsNull()) {
            criteria.add(Restrictions.isNull(CODIGOERROR));
        }

        if (isCodigoerrorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODIGOERROR));
        }

        if (getDescripcionerror() != null) {
            if (getDescripcionerrorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DESCRIPCIONERROR, getDescripcionerror()));
            } 
            else if (getDescripcionerrorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DESCRIPCIONERROR, getDescripcionerror()));
            }
            else if (getDescripcionerrorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DESCRIPCIONERROR, getDescripcionerror())));
            }
            else {
                criteria.add(Restrictions.like(DESCRIPCIONERROR, getDescripcionerror()));
            }
        }

        if (getDescripcionerrorIn().size() > 0) {
            criteria.add(Restrictions.in(DESCRIPCIONERROR, getDescripcionerrorIn()));
        }

        if (isDescripcionerrorIsNull()) {
            criteria.add(Restrictions.isNull(DESCRIPCIONERROR));
        }

        if (isDescripcionerrorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DESCRIPCIONERROR));
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
    
    /**
     * Crea la sentencia SQL para las busquedas de tipo TextComparator.UPPERCASE_TRANSLATE
     */
    private String createUpperTranslateSQL(String column, String value){
    	return "UPPER(TRANSLATE("+columnHQLToSQL(column)+",'"+UPPER_TRANSLATE_FROM+"','"+UPPER_TRANSLATE_TO+"')) LIKE '%" + normalizeParam(value).toUpperCase() + "%'";
    }
    
    /**
     * Convierte el nombrado de una columna en formato HQL a formato SQL
     */
    private String columnHQLToSQL(String column){
    	StringBuilder columnSQL = new StringBuilder();
    	for (int i=0;i<column.length(); i++)
    	{
    	   if (Character.isUpperCase(column.charAt(i))){
    		   columnSQL.append("_" + Character.toLowerCase(column.charAt(i)));
    	   } else {
    		   columnSQL.append(column.charAt(i));
    	   }
    	}
    	return columnSQL.toString();
    }
    
    /**
     * Normaliza el valdor de un parametro eliminado los acentos
     */
    private String normalizeParam(String param){
    	return Normalizer.normalize(param, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
 
