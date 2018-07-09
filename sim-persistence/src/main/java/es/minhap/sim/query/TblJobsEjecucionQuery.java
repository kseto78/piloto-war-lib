/*
 *
 * archivo: TblJobsEjecucionQuery.java
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
import es.minhap.sim.model.TblJobsEjecucion;

/**
 * Clase con criterios de busqueda para la entidad TblJobsEjecucion
 */
public class TblJobsEjecucionQuery extends AbstractHibernateQueryEntity<TblJobsEjecucion> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String JOBEJECUCIONID = "jobejecucionid";
    public static final String TBLJOBS = "tblJobs";
    public static final String FECHA = "fecha";
    public static final String SALIDA = "salida";
    public static final String CODIGOERROR = "codigoerror";


    /**
     * Valor de busqueda de campo jobejecucionid
     */
    private Long jobejecucionid;

    /**
     * Lista de valores del campo jobejecucionid para busquedas tipo IN
     */
    private List<Long> jobejecucionidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblJobs
     */
    private TblJobsQuery tblJobs;

    /**
     * Lista de valores del ID del campo tblJobs para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblJobs
     */
    private List<TblJobs> tblJobsIdIn = new ArrayList<TblJobs>(0);

    /**
     * Permite buscar cuando campo tblJobs es NULL
     */
    private boolean tblJobsIsNull = false;

    /**
     * Permite buscar cuando campo tblJobs es NOT NULL
     */
    private boolean tblJobsIsNotNull = false;

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
     * Valor de busqueda de campo codigoerror
     */
    private String codigoerror;

    /**
     * Tipo de comparador para la busqueda por campo codigoerror
     */
    private TextComparator codigoerrorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codigoerror para busquedas tipo IN
     */
    private List<String> codigoerrorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codigoerror es NULL
     */
    private boolean codigoerrorIsNull = false;

    /**
     * Permite buscar cuando campo codigoerror es NOT NULL
     */
    private boolean codigoerrorIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblJobs
     */
    private boolean innerJoinTblJobs = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblJobs
     */
    private boolean leftJoinTblJobs = false;

    /**
     * Constructor default
     */
    public TblJobsEjecucionQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblJobsEjecucionQuery(Long jobejecucionid) {
        setJobejecucionid(jobejecucionid);
    }

    /**
     * Valor de busqueda de campo jobejecucionid
     * @return Long.
     */
    public Long getJobejecucionid() {
        return jobejecucionid;
    }

    /**
     * Valor de busqueda de campo jobejecucionid
     * @param jobejecucionid Valor de seteo.
     */
    public void setJobejecucionid(Long jobejecucionid) {
        this.jobejecucionid = jobejecucionid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getJobejecucionidIn() {
        return this.jobejecucionidIn;
    }

    /**
     * @param jobejecucionid Valor a agregar.
     */
    public void addJobejecucionidIn(Long jobejecucionid) {
        this.jobejecucionidIn.add(jobejecucionid);
    }

    /**
     * Valor de busqueda de campo tblJobs
     * @return TblJobs.
     */
    public TblJobsQuery getTblJobs() {
        return tblJobs;
    }

    /**
     * Valor de busqueda de campo tblJobs
     * @param tblJobs Valor de seteo.
     */
    public void setTblJobs(TblJobsQuery tblJobs) {
        this.tblJobs = tblJobs;
    }

    /**
     * @return List<TblJobs>.
     */
    public List<TblJobs> getTblJobsIdIn() {
        return this.tblJobsIdIn;
    }

    /**
     * @param tblJobs Valor a agregar.
     */
    public void addTblJobsIdIn(TblJobs tblJobs) {
        this.tblJobsIdIn.add(tblJobs);
    }

    /**
     * Permite buscar cuando campo tblJobs es NULL
     * @return boolean.
     */
    public boolean isTblJobsIsNull() {
        return tblJobsIsNull;
    }

    /**
     * Permite buscar cuando campo tblJobs es NULL
     * @param tblJobsIsNull Valor de seteo.
     */
    public void setTblJobsIsNull(boolean tblJobsIsNull) {
        this.tblJobsIsNull = tblJobsIsNull;
    }

    /**
     * Permite buscar cuando campo tblJobs es NOT NULL
     * @return boolean.
     */
    public boolean isTblJobsIsNotNull() {
        return tblJobsIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblJobs es NOT NULL
     * @param tblJobsIsNotNull Valor de seteo.
     */
    public void setTblJobsIsNotNull(boolean tblJobsIsNotNull) {
        this.tblJobsIsNotNull = tblJobsIsNotNull;
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
     * Valor de busqueda de campo codigoerror
     * @return String.
     */
    public String getCodigoerror() {
        if (codigoerror != null) {
            switch (codigoerrorComparator) {
	            case STARTS_WITH:
	                return codigoerror + "%";
	            case CONTAINS:
	                return "%" + codigoerror + "%";
	            case ENDS_WITH:
	                return "%" + codigoerror;
	            case EQUALS:
                	return codigoerror;
              	default:
	            	break;
            }
        }
        return codigoerror;
    }

    /**
     * Valor de busqueda de campo codigoerror
     * @param codigoerror Valor de seteo.
     */
    public void setCodigoerror(String codigoerror) {
        this.codigoerror = codigoerror;
    }

    /**
     * Tipo de comparador para la busqueda por campo codigoerror
     * @return codigoerrorComparator.
     */
    public TextComparator getCodigoerrorComparator() {
        return codigoerrorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codigoerror
     * @param codigoerrorComparator Valor de seteo.
     */
    public void setCodigoerrorComparator(TextComparator codigoerrorComparator) {
        this.codigoerrorComparator = codigoerrorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodigoerrorIn() {
        return this.codigoerrorIn;
    }

    /**
     * @param codigoerror Valor a agregar.
     */
    public void addCodigoerrorIn(String codigoerror) {
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
     * @return boolean.
     */
    public boolean isInnerJoinTblJobs() {
        return innerJoinTblJobs;
    }

    /**
     * @param innerJoinTblJobs Valor de seteo.
     */
    public void setInnerJoinTblJobs(boolean innerJoinTblJobs) {
        this.innerJoinTblJobs = innerJoinTblJobs;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblJobs() {
        return leftJoinTblJobs;
    }

    /**
     * @param leftJoinTblJobs Valor de seteo.
     */
    public void setLeftJoinTblJobs(boolean leftJoinTblJobs) {
        this.leftJoinTblJobs = leftJoinTblJobs;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getJobejecucionid() != null) {
            criteria.add(Restrictions.eq(JOBEJECUCIONID, getJobejecucionid()));
        }

        if (getJobejecucionidIn().size() > 0) {
            criteria.add(Restrictions.in(JOBEJECUCIONID, getJobejecucionidIn()));
        }

        // Campo entidad padre tblJobs
        
        // Si se hace join fetch con el padre
        Criteria tblJobsCriteria = null;
        if (isInnerJoinTblJobs()) {
            tblJobsCriteria = criteria.createCriteria(TBLJOBS, "a_" + TBLJOBS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblJobs()) {
            tblJobsCriteria = criteria.createCriteria(TBLJOBS, "a_" + TBLJOBS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblJobs() != null) {
            if (getTblJobs().getJobid() == null) {
                if (tblJobsCriteria == null) {
                    tblJobsCriteria = criteria.createCriteria(TBLJOBS, "a_" + TBLJOBS);
                }
                getTblJobs().addCriteria(tblJobsCriteria, useOrder);
            } else {
                TblJobs parent = new TblJobs();
                parent.setJobid(getTblJobs().getJobid());
                criteria.add(Restrictions.eq(TBLJOBS, parent));
            }
        }

        if (getTblJobsIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLJOBS, getTblJobsIdIn()));
        }

        if (isTblJobsIsNull()) {
            criteria.add(Restrictions.isNull(TBLJOBS));
        }

        if (isTblJobsIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLJOBS));
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

        if (getCodigoerror() != null) {
            if (getCodigoerrorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODIGOERROR, getCodigoerror()));
            } 
            else if (getCodigoerrorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODIGOERROR, getCodigoerror()));
            }
            else if (getCodigoerrorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODIGOERROR, getCodigoerror())));
            }
            else {
                criteria.add(Restrictions.like(CODIGOERROR, getCodigoerror()));
            }
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
 
