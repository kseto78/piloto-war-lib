/*
 *
 * archivo: TblProcesoHistQuery.java
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
import es.minhap.sim.model.TblProcesoHist;

/**
 * Clase con criterios de busqueda para la entidad TblProcesoHist
 */
public class TblProcesoHistQuery extends AbstractHibernateQueryEntity<TblProcesoHist> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String PROCESOHISTID = "procesohistid";
    public static final String CODIGOESTADO = "codigoestado";
    public static final String DESCRIPCIONESTADO = "descripcionestado";
    public static final String FECHACREACION = "fechacreacion";
    public static final String FECHAINICIO = "fechainicio";
    public static final String FECHAFIN = "fechafin";


    /**
     * Valor de busqueda de campo procesohistid
     */
    private Long procesohistid;

    /**
     * Lista de valores del campo procesohistid para busquedas tipo IN
     */
    private List<Long> procesohistidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo codigoestado
     */
    private String codigoestado;

    /**
     * Tipo de comparador para la busqueda por campo codigoestado
     */
    private TextComparator codigoestadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codigoestado para busquedas tipo IN
     */
    private List<String> codigoestadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codigoestado es NULL
     */
    private boolean codigoestadoIsNull = false;

    /**
     * Permite buscar cuando campo codigoestado es NOT NULL
     */
    private boolean codigoestadoIsNotNull = false;

    /**
     * Valor de busqueda de campo descripcionestado
     */
    private String descripcionestado;

    /**
     * Tipo de comparador para la busqueda por campo descripcionestado
     */
    private TextComparator descripcionestadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo descripcionestado para busquedas tipo IN
     */
    private List<String> descripcionestadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo descripcionestado es NULL
     */
    private boolean descripcionestadoIsNull = false;

    /**
     * Permite buscar cuando campo descripcionestado es NOT NULL
     */
    private boolean descripcionestadoIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechacreacion
     */
    private Date fechacreacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechacreacion
     */
    private Date fechacreacionMax;

    /**
     * Permite buscar cuando campo fechacreacion es NULL
     */
    private boolean fechacreacionIsNull = false;

    /**
     * Permite buscar cuando campo fechacreacion es NOT NULL
     */
    private boolean fechacreacionIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechainicio
     */
    private Date fechainicioMin;

    /**
     * Valor superior de rango de busqueda de fecha fechainicio
     */
    private Date fechainicioMax;

    /**
     * Permite buscar cuando campo fechainicio es NULL
     */
    private boolean fechainicioIsNull = false;

    /**
     * Permite buscar cuando campo fechainicio es NOT NULL
     */
    private boolean fechainicioIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechafin
     */
    private Date fechafinMin;

    /**
     * Valor superior de rango de busqueda de fecha fechafin
     */
    private Date fechafinMax;

    /**
     * Permite buscar cuando campo fechafin es NULL
     */
    private boolean fechafinIsNull = false;

    /**
     * Permite buscar cuando campo fechafin es NOT NULL
     */
    private boolean fechafinIsNotNull = false;

    /**
     * Constructor default
     */
    public TblProcesoHistQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblProcesoHistQuery(Long procesohistid) {
        setProcesohistid(procesohistid);
    }

    /**
     * Valor de busqueda de campo procesohistid
     * @return Long.
     */
    public Long getProcesohistid() {
        return procesohistid;
    }

    /**
     * Valor de busqueda de campo procesohistid
     * @param procesohistid Valor de seteo.
     */
    public void setProcesohistid(Long procesohistid) {
        this.procesohistid = procesohistid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getProcesohistidIn() {
        return this.procesohistidIn;
    }

    /**
     * @param procesohistid Valor a agregar.
     */
    public void addProcesohistidIn(Long procesohistid) {
        this.procesohistidIn.add(procesohistid);
    }

    /**
     * Valor de busqueda de campo codigoestado
     * @return String.
     */
    public String getCodigoestado() {
        if (codigoestado != null) {
            switch (codigoestadoComparator) {
	            case STARTS_WITH:
	                return codigoestado + "%";
	            case CONTAINS:
	                return "%" + codigoestado + "%";
	            case ENDS_WITH:
	                return "%" + codigoestado;
	            case EQUALS:
                	return codigoestado;
              	default:
	            	break;
            }
        }
        return codigoestado;
    }

    /**
     * Valor de busqueda de campo codigoestado
     * @param codigoestado Valor de seteo.
     */
    public void setCodigoestado(String codigoestado) {
        this.codigoestado = codigoestado;
    }

    /**
     * Tipo de comparador para la busqueda por campo codigoestado
     * @return codigoestadoComparator.
     */
    public TextComparator getCodigoestadoComparator() {
        return codigoestadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codigoestado
     * @param codigoestadoComparator Valor de seteo.
     */
    public void setCodigoestadoComparator(TextComparator codigoestadoComparator) {
        this.codigoestadoComparator = codigoestadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodigoestadoIn() {
        return this.codigoestadoIn;
    }

    /**
     * @param codigoestado Valor a agregar.
     */
    public void addCodigoestadoIn(String codigoestado) {
        this.codigoestadoIn.add(codigoestado);
    }

    /**
     * Permite buscar cuando campo codigoestado es NULL
     * @return boolean.
     */
    public boolean isCodigoestadoIsNull() {
        return codigoestadoIsNull;
    }

    /**
     * Permite buscar cuando campo codigoestado es NULL
     * @param codigoestadoIsNull Valor de seteo.
     */
    public void setCodigoestadoIsNull(boolean codigoestadoIsNull) {
        this.codigoestadoIsNull = codigoestadoIsNull;
    }

    /**
     * Permite buscar cuando campo codigoestado es NOT NULL
     * @return boolean.
     */
    public boolean isCodigoestadoIsNotNull() {
        return codigoestadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo codigoestado es NOT NULL
     * @param codigoestadoIsNotNull Valor de seteo.
     */
    public void setCodigoestadoIsNotNull(boolean codigoestadoIsNotNull) {
        this.codigoestadoIsNotNull = codigoestadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo descripcionestado
     * @return String.
     */
    public String getDescripcionestado() {
        if (descripcionestado != null) {
            switch (descripcionestadoComparator) {
	            case STARTS_WITH:
	                return descripcionestado + "%";
	            case CONTAINS:
	                return "%" + descripcionestado + "%";
	            case ENDS_WITH:
	                return "%" + descripcionestado;
	            case EQUALS:
                	return descripcionestado;
              	default:
	            	break;
            }
        }
        return descripcionestado;
    }

    /**
     * Valor de busqueda de campo descripcionestado
     * @param descripcionestado Valor de seteo.
     */
    public void setDescripcionestado(String descripcionestado) {
        this.descripcionestado = descripcionestado;
    }

    /**
     * Tipo de comparador para la busqueda por campo descripcionestado
     * @return descripcionestadoComparator.
     */
    public TextComparator getDescripcionestadoComparator() {
        return descripcionestadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo descripcionestado
     * @param descripcionestadoComparator Valor de seteo.
     */
    public void setDescripcionestadoComparator(TextComparator descripcionestadoComparator) {
        this.descripcionestadoComparator = descripcionestadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDescripcionestadoIn() {
        return this.descripcionestadoIn;
    }

    /**
     * @param descripcionestado Valor a agregar.
     */
    public void addDescripcionestadoIn(String descripcionestado) {
        this.descripcionestadoIn.add(descripcionestado);
    }

    /**
     * Permite buscar cuando campo descripcionestado es NULL
     * @return boolean.
     */
    public boolean isDescripcionestadoIsNull() {
        return descripcionestadoIsNull;
    }

    /**
     * Permite buscar cuando campo descripcionestado es NULL
     * @param descripcionestadoIsNull Valor de seteo.
     */
    public void setDescripcionestadoIsNull(boolean descripcionestadoIsNull) {
        this.descripcionestadoIsNull = descripcionestadoIsNull;
    }

    /**
     * Permite buscar cuando campo descripcionestado es NOT NULL
     * @return boolean.
     */
    public boolean isDescripcionestadoIsNotNull() {
        return descripcionestadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo descripcionestado es NOT NULL
     * @param descripcionestadoIsNotNull Valor de seteo.
     */
    public void setDescripcionestadoIsNotNull(boolean descripcionestadoIsNotNull) {
        this.descripcionestadoIsNotNull = descripcionestadoIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechacreacion
     * @return ${field.getName()}Min.
     */
    public Date getFechacreacionMin() {
        if (fechacreacionMin != null) {
            return DateUtil.toDayBegin(fechacreacionMin);
        }
        return fechacreacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechacreacion
     * @param fechacreacionMin Valor de seteo.
     */
    public void setFechacreacionMin(Date fechacreacionMin) {
        this.fechacreacionMin = fechacreacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechacreacion
     * @return fechacreacionMax.
     */
    public Date getFechacreacionMax() {
        if (fechacreacionMax != null) {
            return DateUtil.toDayEnd(fechacreacionMax);
        }
        return fechacreacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechacreacion
     * @param fechacreacionMax Valor de seteo.
     */
    public void setFechacreacionMax(Date fechacreacionMax) {
        this.fechacreacionMax = fechacreacionMax;
    }

    /**
     * Permite buscar cuando campo fechacreacion es NULL
     * @return boolean.
     */
    public boolean isFechacreacionIsNull() {
        return fechacreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechacreacion es NULL
     * @param fechacreacionIsNull Valor de seteo.
     */
    public void setFechacreacionIsNull(boolean fechacreacionIsNull) {
        this.fechacreacionIsNull = fechacreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechacreacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechacreacionIsNotNull() {
        return fechacreacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechacreacion es NOT NULL
     * @param fechacreacionIsNotNull Valor de seteo.
     */
    public void setFechacreacionIsNotNull(boolean fechacreacionIsNotNull) {
        this.fechacreacionIsNotNull = fechacreacionIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechainicio
     * @return ${field.getName()}Min.
     */
    public Date getFechainicioMin() {
        if (fechainicioMin != null) {
            return DateUtil.toDayBegin(fechainicioMin);
        }
        return fechainicioMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechainicio
     * @param fechainicioMin Valor de seteo.
     */
    public void setFechainicioMin(Date fechainicioMin) {
        this.fechainicioMin = fechainicioMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechainicio
     * @return fechainicioMax.
     */
    public Date getFechainicioMax() {
        if (fechainicioMax != null) {
            return DateUtil.toDayEnd(fechainicioMax);
        }
        return fechainicioMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechainicio
     * @param fechainicioMax Valor de seteo.
     */
    public void setFechainicioMax(Date fechainicioMax) {
        this.fechainicioMax = fechainicioMax;
    }

    /**
     * Permite buscar cuando campo fechainicio es NULL
     * @return boolean.
     */
    public boolean isFechainicioIsNull() {
        return fechainicioIsNull;
    }

    /**
     * Permite buscar cuando campo fechainicio es NULL
     * @param fechainicioIsNull Valor de seteo.
     */
    public void setFechainicioIsNull(boolean fechainicioIsNull) {
        this.fechainicioIsNull = fechainicioIsNull;
    }

    /**
     * Permite buscar cuando campo fechainicio es NOT NULL
     * @return boolean.
     */
    public boolean isFechainicioIsNotNull() {
        return fechainicioIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechainicio es NOT NULL
     * @param fechainicioIsNotNull Valor de seteo.
     */
    public void setFechainicioIsNotNull(boolean fechainicioIsNotNull) {
        this.fechainicioIsNotNull = fechainicioIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechafin
     * @return ${field.getName()}Min.
     */
    public Date getFechafinMin() {
        if (fechafinMin != null) {
            return DateUtil.toDayBegin(fechafinMin);
        }
        return fechafinMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechafin
     * @param fechafinMin Valor de seteo.
     */
    public void setFechafinMin(Date fechafinMin) {
        this.fechafinMin = fechafinMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechafin
     * @return fechafinMax.
     */
    public Date getFechafinMax() {
        if (fechafinMax != null) {
            return DateUtil.toDayEnd(fechafinMax);
        }
        return fechafinMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechafin
     * @param fechafinMax Valor de seteo.
     */
    public void setFechafinMax(Date fechafinMax) {
        this.fechafinMax = fechafinMax;
    }

    /**
     * Permite buscar cuando campo fechafin es NULL
     * @return boolean.
     */
    public boolean isFechafinIsNull() {
        return fechafinIsNull;
    }

    /**
     * Permite buscar cuando campo fechafin es NULL
     * @param fechafinIsNull Valor de seteo.
     */
    public void setFechafinIsNull(boolean fechafinIsNull) {
        this.fechafinIsNull = fechafinIsNull;
    }

    /**
     * Permite buscar cuando campo fechafin es NOT NULL
     * @return boolean.
     */
    public boolean isFechafinIsNotNull() {
        return fechafinIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechafin es NOT NULL
     * @param fechafinIsNotNull Valor de seteo.
     */
    public void setFechafinIsNotNull(boolean fechafinIsNotNull) {
        this.fechafinIsNotNull = fechafinIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getProcesohistid() != null) {
            criteria.add(Restrictions.eq(PROCESOHISTID, getProcesohistid()));
        }

        if (getProcesohistidIn().size() > 0) {
            criteria.add(Restrictions.in(PROCESOHISTID, getProcesohistidIn()));
        }

        if (getCodigoestado() != null) {
            if (getCodigoestadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODIGOESTADO, getCodigoestado()));
            } 
            else if (getCodigoestadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODIGOESTADO, getCodigoestado()));
            }
            else if (getCodigoestadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODIGOESTADO, getCodigoestado())));
            }
            else {
                criteria.add(Restrictions.like(CODIGOESTADO, getCodigoestado()));
            }
        }

        if (getCodigoestadoIn().size() > 0) {
            criteria.add(Restrictions.in(CODIGOESTADO, getCodigoestadoIn()));
        }

        if (isCodigoestadoIsNull()) {
            criteria.add(Restrictions.isNull(CODIGOESTADO));
        }

        if (isCodigoestadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODIGOESTADO));
        }

        if (getDescripcionestado() != null) {
            if (getDescripcionestadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DESCRIPCIONESTADO, getDescripcionestado()));
            } 
            else if (getDescripcionestadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DESCRIPCIONESTADO, getDescripcionestado()));
            }
            else if (getDescripcionestadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DESCRIPCIONESTADO, getDescripcionestado())));
            }
            else {
                criteria.add(Restrictions.like(DESCRIPCIONESTADO, getDescripcionestado()));
            }
        }

        if (getDescripcionestadoIn().size() > 0) {
            criteria.add(Restrictions.in(DESCRIPCIONESTADO, getDescripcionestadoIn()));
        }

        if (isDescripcionestadoIsNull()) {
            criteria.add(Restrictions.isNull(DESCRIPCIONESTADO));
        }

        if (isDescripcionestadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DESCRIPCIONESTADO));
        }

        if (getFechacreacionMin() != null) {
            criteria.add(Restrictions.ge(FECHACREACION, getFechacreacionMin()));
        }

        if (getFechacreacionMax() != null) {
            criteria.add(Restrictions.le(FECHACREACION, getFechacreacionMax()));
        }

        if (isFechacreacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHACREACION));
        }

        if (isFechacreacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHACREACION));
        }

        if (getFechainicioMin() != null) {
            criteria.add(Restrictions.ge(FECHAINICIO, getFechainicioMin()));
        }

        if (getFechainicioMax() != null) {
            criteria.add(Restrictions.le(FECHAINICIO, getFechainicioMax()));
        }

        if (isFechainicioIsNull()) {
            criteria.add(Restrictions.isNull(FECHAINICIO));
        }

        if (isFechainicioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAINICIO));
        }

        if (getFechafinMin() != null) {
            criteria.add(Restrictions.ge(FECHAFIN, getFechafinMin()));
        }

        if (getFechafinMax() != null) {
            criteria.add(Restrictions.le(FECHAFIN, getFechafinMax()));
        }

        if (isFechafinIsNull()) {
            criteria.add(Restrictions.isNull(FECHAFIN));
        }

        if (isFechafinIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAFIN));
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
 
