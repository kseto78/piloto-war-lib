/*
 *
 * archivo: TblJobsQuery.java
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
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblJobs;
import es.minhap.sim.model.TblPlanificaciones;

/**
 * Clase con criterios de busqueda para la entidad TblJobs
 */
public class TblJobsQuery extends AbstractHibernateQueryEntity<TblJobs> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String JOBID = "jobid";
    public static final String TBLPLANIFICACIONES = "tblPlanificaciones";
    public static final String FECHA = "fecha";
    public static final String PARAMETROS = "parametros";
    public static final String SALIDA = "salida";


    /**
     * Valor de busqueda de campo jobid
     */
    private Long jobid;

    /**
     * Lista de valores del campo jobid para busquedas tipo IN
     */
    private List<Long> jobidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblPlanificaciones
     */
    private TblPlanificacionesQuery tblPlanificaciones;

    /**
     * Lista de valores del ID del campo tblPlanificaciones para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblPlanificaciones
     */
    private List<TblPlanificaciones> tblPlanificacionesIdIn = new ArrayList<TblPlanificaciones>(0);

    /**
     * Permite buscar cuando campo tblPlanificaciones es NULL
     */
    private boolean tblPlanificacionesIsNull = false;

    /**
     * Permite buscar cuando campo tblPlanificaciones es NOT NULL
     */
    private boolean tblPlanificacionesIsNotNull = false;

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
     * Valor de busqueda de campo parametros
     */
    private String parametros;

    /**
     * Tipo de comparador para la busqueda por campo parametros
     */
    private TextComparator parametrosComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo parametros para busquedas tipo IN
     */
    private List<String> parametrosIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo parametros es NULL
     */
    private boolean parametrosIsNull = false;

    /**
     * Permite buscar cuando campo parametros es NOT NULL
     */
    private boolean parametrosIsNotNull = false;

    /**
     * Valor de busqueda de campo salida
     */
    private String salida;

    /**
     * Tipo de comparador para la busqueda por campo salida
     */
    private TextComparator salidaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo salida para busquedas tipo IN
     */
    private List<String> salidaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo salida es NULL
     */
    private boolean salidaIsNull = false;

    /**
     * Permite buscar cuando campo salida es NOT NULL
     */
    private boolean salidaIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblPlanificaciones
     */
    private boolean innerJoinTblPlanificaciones = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblPlanificaciones
     */
    private boolean leftJoinTblPlanificaciones = false;

    /**
     * Constructor default
     */
    public TblJobsQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblJobsQuery(Long jobid) {
        setJobid(jobid);
    }

    /**
     * Valor de busqueda de campo jobid
     * @return Long.
     */
    public Long getJobid() {
        return jobid;
    }

    /**
     * Valor de busqueda de campo jobid
     * @param jobid Valor de seteo.
     */
    public void setJobid(Long jobid) {
        this.jobid = jobid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getJobidIn() {
        return this.jobidIn;
    }

    /**
     * @param jobid Valor a agregar.
     */
    public void addJobidIn(Long jobid) {
        this.jobidIn.add(jobid);
    }

    /**
     * Valor de busqueda de campo tblPlanificaciones
     * @return TblPlanificaciones.
     */
    public TblPlanificacionesQuery getTblPlanificaciones() {
        return tblPlanificaciones;
    }

    /**
     * Valor de busqueda de campo tblPlanificaciones
     * @param tblPlanificaciones Valor de seteo.
     */
    public void setTblPlanificaciones(TblPlanificacionesQuery tblPlanificaciones) {
        this.tblPlanificaciones = tblPlanificaciones;
    }

    /**
     * @return List<TblPlanificaciones>.
     */
    public List<TblPlanificaciones> getTblPlanificacionesIdIn() {
        return this.tblPlanificacionesIdIn;
    }

    /**
     * @param tblPlanificaciones Valor a agregar.
     */
    public void addTblPlanificacionesIdIn(TblPlanificaciones tblPlanificaciones) {
        this.tblPlanificacionesIdIn.add(tblPlanificaciones);
    }

    /**
     * Permite buscar cuando campo tblPlanificaciones es NULL
     * @return boolean.
     */
    public boolean isTblPlanificacionesIsNull() {
        return tblPlanificacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblPlanificaciones es NULL
     * @param tblPlanificacionesIsNull Valor de seteo.
     */
    public void setTblPlanificacionesIsNull(boolean tblPlanificacionesIsNull) {
        this.tblPlanificacionesIsNull = tblPlanificacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblPlanificaciones es NOT NULL
     * @return boolean.
     */
    public boolean isTblPlanificacionesIsNotNull() {
        return tblPlanificacionesIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblPlanificaciones es NOT NULL
     * @param tblPlanificacionesIsNotNull Valor de seteo.
     */
    public void setTblPlanificacionesIsNotNull(boolean tblPlanificacionesIsNotNull) {
        this.tblPlanificacionesIsNotNull = tblPlanificacionesIsNotNull;
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
     * Valor de busqueda de campo parametros
     * @return String.
     */
    public String getParametros() {
        if (parametros != null) {
            switch (parametrosComparator) {
	            case STARTS_WITH:
	                return parametros + "%";
	            case CONTAINS:
	                return "%" + parametros + "%";
	            case ENDS_WITH:
	                return "%" + parametros;
	            case EQUALS:
                	return parametros;
              	default:
	            	break;
            }
        }
        return parametros;
    }

    /**
     * Valor de busqueda de campo parametros
     * @param parametros Valor de seteo.
     */
    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametros
     * @return parametrosComparator.
     */
    public TextComparator getParametrosComparator() {
        return parametrosComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametros
     * @param parametrosComparator Valor de seteo.
     */
    public void setParametrosComparator(TextComparator parametrosComparator) {
        this.parametrosComparator = parametrosComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getParametrosIn() {
        return this.parametrosIn;
    }

    /**
     * @param parametros Valor a agregar.
     */
    public void addParametrosIn(String parametros) {
        this.parametrosIn.add(parametros);
    }

    /**
     * Permite buscar cuando campo parametros es NULL
     * @return boolean.
     */
    public boolean isParametrosIsNull() {
        return parametrosIsNull;
    }

    /**
     * Permite buscar cuando campo parametros es NULL
     * @param parametrosIsNull Valor de seteo.
     */
    public void setParametrosIsNull(boolean parametrosIsNull) {
        this.parametrosIsNull = parametrosIsNull;
    }

    /**
     * Permite buscar cuando campo parametros es NOT NULL
     * @return boolean.
     */
    public boolean isParametrosIsNotNull() {
        return parametrosIsNotNull;
    }

    /**
     * Permite buscar cuando campo parametros es NOT NULL
     * @param parametrosIsNotNull Valor de seteo.
     */
    public void setParametrosIsNotNull(boolean parametrosIsNotNull) {
        this.parametrosIsNotNull = parametrosIsNotNull;
    }

    /**
     * Valor de busqueda de campo salida
     * @return String.
     */
    public String getSalida() {
        if (salida != null) {
            switch (salidaComparator) {
	            case STARTS_WITH:
	                return salida + "%";
	            case CONTAINS:
	                return "%" + salida + "%";
	            case ENDS_WITH:
	                return "%" + salida;
	            case EQUALS:
                	return salida;
              	default:
	            	break;
            }
        }
        return salida;
    }

    /**
     * Valor de busqueda de campo salida
     * @param salida Valor de seteo.
     */
    public void setSalida(String salida) {
        this.salida = salida;
    }

    /**
     * Tipo de comparador para la busqueda por campo salida
     * @return salidaComparator.
     */
    public TextComparator getSalidaComparator() {
        return salidaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo salida
     * @param salidaComparator Valor de seteo.
     */
    public void setSalidaComparator(TextComparator salidaComparator) {
        this.salidaComparator = salidaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getSalidaIn() {
        return this.salidaIn;
    }

    /**
     * @param salida Valor a agregar.
     */
    public void addSalidaIn(String salida) {
        this.salidaIn.add(salida);
    }

    /**
     * Permite buscar cuando campo salida es NULL
     * @return boolean.
     */
    public boolean isSalidaIsNull() {
        return salidaIsNull;
    }

    /**
     * Permite buscar cuando campo salida es NULL
     * @param salidaIsNull Valor de seteo.
     */
    public void setSalidaIsNull(boolean salidaIsNull) {
        this.salidaIsNull = salidaIsNull;
    }

    /**
     * Permite buscar cuando campo salida es NOT NULL
     * @return boolean.
     */
    public boolean isSalidaIsNotNull() {
        return salidaIsNotNull;
    }

    /**
     * Permite buscar cuando campo salida es NOT NULL
     * @param salidaIsNotNull Valor de seteo.
     */
    public void setSalidaIsNotNull(boolean salidaIsNotNull) {
        this.salidaIsNotNull = salidaIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblPlanificaciones() {
        return innerJoinTblPlanificaciones;
    }

    /**
     * @param innerJoinTblPlanificaciones Valor de seteo.
     */
    public void setInnerJoinTblPlanificaciones(boolean innerJoinTblPlanificaciones) {
        this.innerJoinTblPlanificaciones = innerJoinTblPlanificaciones;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblPlanificaciones() {
        return leftJoinTblPlanificaciones;
    }

    /**
     * @param leftJoinTblPlanificaciones Valor de seteo.
     */
    public void setLeftJoinTblPlanificaciones(boolean leftJoinTblPlanificaciones) {
        this.leftJoinTblPlanificaciones = leftJoinTblPlanificaciones;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getJobid() != null) {
            criteria.add(Restrictions.eq(JOBID, getJobid()));
        }

        if (getJobidIn().size() > 0) {
            criteria.add(Restrictions.in(JOBID, getJobidIn()));
        }

        // Campo entidad padre tblPlanificaciones
        
        // Si se hace join fetch con el padre
        Criteria tblPlanificacionesCriteria = null;
        if (isInnerJoinTblPlanificaciones()) {
            tblPlanificacionesCriteria = criteria.createCriteria(TBLPLANIFICACIONES, "a_" + TBLPLANIFICACIONES, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblPlanificaciones()) {
            tblPlanificacionesCriteria = criteria.createCriteria(TBLPLANIFICACIONES, "a_" + TBLPLANIFICACIONES, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblPlanificaciones() != null) {
            if (getTblPlanificaciones().getPlanificacionid() == null) {
                if (tblPlanificacionesCriteria == null) {
                    tblPlanificacionesCriteria = criteria.createCriteria(TBLPLANIFICACIONES, "a_" + TBLPLANIFICACIONES);
                }
                getTblPlanificaciones().addCriteria(tblPlanificacionesCriteria, useOrder);
            } else {
                TblPlanificaciones parent = new TblPlanificaciones();
                parent.setPlanificacionid(getTblPlanificaciones().getPlanificacionid());
                criteria.add(Restrictions.eq(TBLPLANIFICACIONES, parent));
            }
        }

        if (getTblPlanificacionesIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLPLANIFICACIONES, getTblPlanificacionesIdIn()));
        }

        if (isTblPlanificacionesIsNull()) {
            criteria.add(Restrictions.isNull(TBLPLANIFICACIONES));
        }

        if (isTblPlanificacionesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLPLANIFICACIONES));
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

        if (getParametros() != null) {
            if (getParametrosComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PARAMETROS, getParametros()));
            } 
            else if (getParametrosComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PARAMETROS, getParametros()));
            }
            else if (getParametrosComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PARAMETROS, getParametros())));
            }
            else {
                criteria.add(Restrictions.like(PARAMETROS, getParametros()));
            }
        }

        if (getParametrosIn().size() > 0) {
            criteria.add(Restrictions.in(PARAMETROS, getParametrosIn()));
        }

        if (isParametrosIsNull()) {
            criteria.add(Restrictions.isNull(PARAMETROS));
        }

        if (isParametrosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PARAMETROS));
        }

        if (getSalida() != null) {
            if (getSalidaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SALIDA, getSalida()));
            } 
            else if (getSalidaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SALIDA, getSalida()));
            }
            else if (getSalidaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SALIDA, getSalida())));
            }
            else {
                criteria.add(Restrictions.like(SALIDA, getSalida()));
            }
        }

        if (getSalidaIn().size() > 0) {
            criteria.add(Restrictions.in(SALIDA, getSalidaIn()));
        }

        if (isSalidaIsNull()) {
            criteria.add(Restrictions.isNull(SALIDA));
        }

        if (isSalidaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SALIDA));
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
 
