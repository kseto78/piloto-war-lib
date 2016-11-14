/*
 *
 * archivo: TblQueryLogQuery.java
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
import es.minhap.sim.model.TblQueryLog;

/**
 * Clase con criterios de busqueda para la entidad TblQueryLog
 */
public class TblQueryLogQuery extends AbstractHibernateQueryEntity<TblQueryLog> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String ID = "id";
    public static final String QUERYSTRING = "querystring";
    public static final String FECHA = "fecha";


    /**
     * Valor de busqueda de campo id
     */
    private Long id;

    /**
     * Lista de valores del campo id para busquedas tipo IN
     */
    private List<Long> idIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo querystring
     */
    private String querystring;

    /**
     * Tipo de comparador para la busqueda por campo querystring
     */
    private TextComparator querystringComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo querystring para busquedas tipo IN
     */
    private List<String> querystringIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo querystring es NULL
     */
    private boolean querystringIsNull = false;

    /**
     * Permite buscar cuando campo querystring es NOT NULL
     */
    private boolean querystringIsNotNull = false;

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
     * Constructor default
     */
    public TblQueryLogQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblQueryLogQuery(Long id) {
        setId(id);
    }

    /**
     * Valor de busqueda de campo id
     * @return Long.
     */
    public Long getId() {
        return id;
    }

    /**
     * Valor de busqueda de campo id
     * @param id Valor de seteo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdIn() {
        return this.idIn;
    }

    /**
     * @param id Valor a agregar.
     */
    public void addIdIn(Long id) {
        this.idIn.add(id);
    }

    /**
     * Valor de busqueda de campo querystring
     * @return String.
     */
    public String getQuerystring() {
        if (querystring != null) {
            switch (querystringComparator) {
	            case STARTS_WITH:
	                return querystring + "%";
	            case CONTAINS:
	                return "%" + querystring + "%";
	            case ENDS_WITH:
	                return "%" + querystring;
	            case EQUALS:
                	return querystring;
              	default:
	            	break;
            }
        }
        return querystring;
    }

    /**
     * Valor de busqueda de campo querystring
     * @param querystring Valor de seteo.
     */
    public void setQuerystring(String querystring) {
        this.querystring = querystring;
    }

    /**
     * Tipo de comparador para la busqueda por campo querystring
     * @return querystringComparator.
     */
    public TextComparator getQuerystringComparator() {
        return querystringComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo querystring
     * @param querystringComparator Valor de seteo.
     */
    public void setQuerystringComparator(TextComparator querystringComparator) {
        this.querystringComparator = querystringComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getQuerystringIn() {
        return this.querystringIn;
    }

    /**
     * @param querystring Valor a agregar.
     */
    public void addQuerystringIn(String querystring) {
        this.querystringIn.add(querystring);
    }

    /**
     * Permite buscar cuando campo querystring es NULL
     * @return boolean.
     */
    public boolean isQuerystringIsNull() {
        return querystringIsNull;
    }

    /**
     * Permite buscar cuando campo querystring es NULL
     * @param querystringIsNull Valor de seteo.
     */
    public void setQuerystringIsNull(boolean querystringIsNull) {
        this.querystringIsNull = querystringIsNull;
    }

    /**
     * Permite buscar cuando campo querystring es NOT NULL
     * @return boolean.
     */
    public boolean isQuerystringIsNotNull() {
        return querystringIsNotNull;
    }

    /**
     * Permite buscar cuando campo querystring es NOT NULL
     * @param querystringIsNotNull Valor de seteo.
     */
    public void setQuerystringIsNotNull(boolean querystringIsNotNull) {
        this.querystringIsNotNull = querystringIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getId() != null) {
            criteria.add(Restrictions.eq(ID, getId()));
        }

        if (getIdIn().size() > 0) {
            criteria.add(Restrictions.in(ID, getIdIn()));
        }

        if (getQuerystring() != null) {
            if (getQuerystringComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(QUERYSTRING, getQuerystring()));
            } 
            else if (getQuerystringComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(QUERYSTRING, getQuerystring()));
            }
            else if (getQuerystringComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(QUERYSTRING, getQuerystring())));
            }
            else {
                criteria.add(Restrictions.like(QUERYSTRING, getQuerystring()));
            }
        }

        if (getQuerystringIn().size() > 0) {
            criteria.add(Restrictions.in(QUERYSTRING, getQuerystringIn()));
        }

        if (isQuerystringIsNull()) {
            criteria.add(Restrictions.isNull(QUERYSTRING));
        }

        if (isQuerystringIsNotNull()) {
            criteria.add(Restrictions.isNotNull(QUERYSTRING));
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
 
