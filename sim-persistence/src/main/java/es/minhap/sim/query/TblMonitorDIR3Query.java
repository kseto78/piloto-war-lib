/*
 *
 * archivo: TblMonitorDIR3Query.java
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
import es.minhap.sim.model.TblMonitorDIR3;

/**
 * Clase con criterios de busqueda para la entidad TblMonitorDIR3
 */
public class TblMonitorDIR3Query extends AbstractHibernateQueryEntity<TblMonitorDIR3> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDMONITOR = "idMonitor";
    public static final String CODESTADO = "codEstado";
    public static final String DESCESTADO = "descEstado";
    public static final String FECHAEJECUCION = "fechaEjecucion";


    /**
     * Valor de busqueda de campo idMonitor
     */
    private Long idMonitor;

    /**
     * Lista de valores del campo idMonitor para busquedas tipo IN
     */
    private List<Long> idMonitorIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo codEstado
     */
    private String codEstado;

    /**
     * Tipo de comparador para la busqueda por campo codEstado
     */
    private TextComparator codEstadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codEstado para busquedas tipo IN
     */
    private List<String> codEstadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codEstado es NULL
     */
    private boolean codEstadoIsNull = false;

    /**
     * Permite buscar cuando campo codEstado es NOT NULL
     */
    private boolean codEstadoIsNotNull = false;

    /**
     * Valor de busqueda de campo descEstado
     */
    private String descEstado;

    /**
     * Tipo de comparador para la busqueda por campo descEstado
     */
    private TextComparator descEstadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo descEstado para busquedas tipo IN
     */
    private List<String> descEstadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo descEstado es NULL
     */
    private boolean descEstadoIsNull = false;

    /**
     * Permite buscar cuando campo descEstado es NOT NULL
     */
    private boolean descEstadoIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaEjecucion
     */
    private Date fechaEjecucionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaEjecucion
     */
    private Date fechaEjecucionMax;

    /**
     * Permite buscar cuando campo fechaEjecucion es NULL
     */
    private boolean fechaEjecucionIsNull = false;

    /**
     * Permite buscar cuando campo fechaEjecucion es NOT NULL
     */
    private boolean fechaEjecucionIsNotNull = false;

    /**
     * Constructor default
     */
    public TblMonitorDIR3Query() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblMonitorDIR3Query(Long idMonitor) {
        setIdMonitor(idMonitor);
    }

    /**
     * Valor de busqueda de campo idMonitor
     * @return Long.
     */
    public Long getIdMonitor() {
        return idMonitor;
    }

    /**
     * Valor de busqueda de campo idMonitor
     * @param idMonitor Valor de seteo.
     */
    public void setIdMonitor(Long idMonitor) {
        this.idMonitor = idMonitor;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdMonitorIn() {
        return this.idMonitorIn;
    }

    /**
     * @param idMonitor Valor a agregar.
     */
    public void addIdMonitorIn(Long idMonitor) {
        this.idMonitorIn.add(idMonitor);
    }

    /**
     * Valor de busqueda de campo codEstado
     * @return String.
     */
    public String getCodEstado() {
        if (codEstado != null) {
            switch (codEstadoComparator) {
	            case STARTS_WITH:
	                return codEstado + "%";
	            case CONTAINS:
	                return "%" + codEstado + "%";
	            case ENDS_WITH:
	                return "%" + codEstado;
	            case EQUALS:
                	return codEstado;
              	default:
	            	break;
            }
        }
        return codEstado;
    }

    /**
     * Valor de busqueda de campo codEstado
     * @param codEstado Valor de seteo.
     */
    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    /**
     * Tipo de comparador para la busqueda por campo codEstado
     * @return codEstadoComparator.
     */
    public TextComparator getCodEstadoComparator() {
        return codEstadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codEstado
     * @param codEstadoComparator Valor de seteo.
     */
    public void setCodEstadoComparator(TextComparator codEstadoComparator) {
        this.codEstadoComparator = codEstadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodEstadoIn() {
        return this.codEstadoIn;
    }

    /**
     * @param codEstado Valor a agregar.
     */
    public void addCodEstadoIn(String codEstado) {
        this.codEstadoIn.add(codEstado);
    }

    /**
     * Permite buscar cuando campo codEstado es NULL
     * @return boolean.
     */
    public boolean isCodEstadoIsNull() {
        return codEstadoIsNull;
    }

    /**
     * Permite buscar cuando campo codEstado es NULL
     * @param codEstadoIsNull Valor de seteo.
     */
    public void setCodEstadoIsNull(boolean codEstadoIsNull) {
        this.codEstadoIsNull = codEstadoIsNull;
    }

    /**
     * Permite buscar cuando campo codEstado es NOT NULL
     * @return boolean.
     */
    public boolean isCodEstadoIsNotNull() {
        return codEstadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo codEstado es NOT NULL
     * @param codEstadoIsNotNull Valor de seteo.
     */
    public void setCodEstadoIsNotNull(boolean codEstadoIsNotNull) {
        this.codEstadoIsNotNull = codEstadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo descEstado
     * @return String.
     */
    public String getDescEstado() {
        if (descEstado != null) {
            switch (descEstadoComparator) {
	            case STARTS_WITH:
	                return descEstado + "%";
	            case CONTAINS:
	                return "%" + descEstado + "%";
	            case ENDS_WITH:
	                return "%" + descEstado;
	            case EQUALS:
                	return descEstado;
              	default:
	            	break;
            }
        }
        return descEstado;
    }

    /**
     * Valor de busqueda de campo descEstado
     * @param descEstado Valor de seteo.
     */
    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    /**
     * Tipo de comparador para la busqueda por campo descEstado
     * @return descEstadoComparator.
     */
    public TextComparator getDescEstadoComparator() {
        return descEstadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo descEstado
     * @param descEstadoComparator Valor de seteo.
     */
    public void setDescEstadoComparator(TextComparator descEstadoComparator) {
        this.descEstadoComparator = descEstadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDescEstadoIn() {
        return this.descEstadoIn;
    }

    /**
     * @param descEstado Valor a agregar.
     */
    public void addDescEstadoIn(String descEstado) {
        this.descEstadoIn.add(descEstado);
    }

    /**
     * Permite buscar cuando campo descEstado es NULL
     * @return boolean.
     */
    public boolean isDescEstadoIsNull() {
        return descEstadoIsNull;
    }

    /**
     * Permite buscar cuando campo descEstado es NULL
     * @param descEstadoIsNull Valor de seteo.
     */
    public void setDescEstadoIsNull(boolean descEstadoIsNull) {
        this.descEstadoIsNull = descEstadoIsNull;
    }

    /**
     * Permite buscar cuando campo descEstado es NOT NULL
     * @return boolean.
     */
    public boolean isDescEstadoIsNotNull() {
        return descEstadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo descEstado es NOT NULL
     * @param descEstadoIsNotNull Valor de seteo.
     */
    public void setDescEstadoIsNotNull(boolean descEstadoIsNotNull) {
        this.descEstadoIsNotNull = descEstadoIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaEjecucion
     * @return ${field.getName()}Min.
     */
    public Date getFechaEjecucionMin() {
        if (fechaEjecucionMin != null) {
            return DateUtil.toDayBegin(fechaEjecucionMin);
        }
        return fechaEjecucionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaEjecucion
     * @param fechaEjecucionMin Valor de seteo.
     */
    public void setFechaEjecucionMin(Date fechaEjecucionMin) {
        this.fechaEjecucionMin = fechaEjecucionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaEjecucion
     * @return fechaEjecucionMax.
     */
    public Date getFechaEjecucionMax() {
        if (fechaEjecucionMax != null) {
            return DateUtil.toDayEnd(fechaEjecucionMax);
        }
        return fechaEjecucionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaEjecucion
     * @param fechaEjecucionMax Valor de seteo.
     */
    public void setFechaEjecucionMax(Date fechaEjecucionMax) {
        this.fechaEjecucionMax = fechaEjecucionMax;
    }

    /**
     * Permite buscar cuando campo fechaEjecucion es NULL
     * @return boolean.
     */
    public boolean isFechaEjecucionIsNull() {
        return fechaEjecucionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaEjecucion es NULL
     * @param fechaEjecucionIsNull Valor de seteo.
     */
    public void setFechaEjecucionIsNull(boolean fechaEjecucionIsNull) {
        this.fechaEjecucionIsNull = fechaEjecucionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaEjecucion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaEjecucionIsNotNull() {
        return fechaEjecucionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaEjecucion es NOT NULL
     * @param fechaEjecucionIsNotNull Valor de seteo.
     */
    public void setFechaEjecucionIsNotNull(boolean fechaEjecucionIsNotNull) {
        this.fechaEjecucionIsNotNull = fechaEjecucionIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdMonitor() != null) {
            criteria.add(Restrictions.eq(IDMONITOR, getIdMonitor()));
        }

        if (getIdMonitorIn().size() > 0) {
            criteria.add(Restrictions.in(IDMONITOR, getIdMonitorIn()));
        }

        if (getCodEstado() != null) {
            if (getCodEstadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODESTADO, getCodEstado()));
            } 
            else if (getCodEstadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODESTADO, getCodEstado()));
            }
            else if (getCodEstadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODESTADO, getCodEstado())));
            }
            else {
                criteria.add(Restrictions.like(CODESTADO, getCodEstado()));
            }
        }

        if (getCodEstadoIn().size() > 0) {
            criteria.add(Restrictions.in(CODESTADO, getCodEstadoIn()));
        }

        if (isCodEstadoIsNull()) {
            criteria.add(Restrictions.isNull(CODESTADO));
        }

        if (isCodEstadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODESTADO));
        }

        if (getDescEstado() != null) {
            if (getDescEstadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DESCESTADO, getDescEstado()));
            } 
            else if (getDescEstadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DESCESTADO, getDescEstado()));
            }
            else if (getDescEstadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DESCESTADO, getDescEstado())));
            }
            else {
                criteria.add(Restrictions.like(DESCESTADO, getDescEstado()));
            }
        }

        if (getDescEstadoIn().size() > 0) {
            criteria.add(Restrictions.in(DESCESTADO, getDescEstadoIn()));
        }

        if (isDescEstadoIsNull()) {
            criteria.add(Restrictions.isNull(DESCESTADO));
        }

        if (isDescEstadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DESCESTADO));
        }

        if (getFechaEjecucionMin() != null) {
            criteria.add(Restrictions.ge(FECHAEJECUCION, getFechaEjecucionMin()));
        }

        if (getFechaEjecucionMax() != null) {
            criteria.add(Restrictions.le(FECHAEJECUCION, getFechaEjecucionMax()));
        }

        if (isFechaEjecucionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAEJECUCION));
        }

        if (isFechaEjecucionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAEJECUCION));
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
 
