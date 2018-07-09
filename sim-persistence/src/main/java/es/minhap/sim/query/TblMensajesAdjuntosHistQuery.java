/*
 *
 * archivo: TblMensajesAdjuntosHistQuery.java
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

import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblAdjuntosHist;
import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.minhap.sim.model.TblMensajesHist;

/**
 * Clase con criterios de busqueda para la entidad TblMensajesAdjuntosHist
 */
public class TblMensajesAdjuntosHistQuery extends AbstractHibernateQueryEntity<TblMensajesAdjuntosHist> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String MENSAJEADJUNTOID = "mensajeadjuntoid";
    public static final String TBLMENSAJESHIST = "tblMensajesHist";
    public static final String TBLADJUNTOSHIST = "tblAdjuntosHist";
    public static final String FECHAHISTORIFICACION = "fechahistorificacion";


    /**
     * Valor de busqueda de campo mensajeadjuntoid
     */
    private Long mensajeadjuntoid;

    /**
     * Lista de valores del campo mensajeadjuntoid para busquedas tipo IN
     */
    private List<Long> mensajeadjuntoidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblMensajesHist
     */
    private TblMensajesHistQuery tblMensajesHist;

    /**
     * Lista de valores del ID del campo tblMensajesHist para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblMensajesHist
     */
    private List<TblMensajesHist> tblMensajesHistIdIn = new ArrayList<TblMensajesHist>(0);

    /**
     * Permite buscar cuando campo tblMensajesHist es NULL
     */
    private boolean tblMensajesHistIsNull = false;

    /**
     * Permite buscar cuando campo tblMensajesHist es NOT NULL
     */
    private boolean tblMensajesHistIsNotNull = false;

    /**
     * Valor de busqueda de campo tblAdjuntosHist
     */
    private TblAdjuntosHistQuery tblAdjuntosHist;

    /**
     * Lista de valores del ID del campo tblAdjuntosHist para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblAdjuntosHist
     */
    private List<TblAdjuntosHist> tblAdjuntosHistIdIn = new ArrayList<TblAdjuntosHist>(0);

    /**
     * Permite buscar cuando campo tblAdjuntosHist es NULL
     */
    private boolean tblAdjuntosHistIsNull = false;

    /**
     * Permite buscar cuando campo tblAdjuntosHist es NOT NULL
     */
    private boolean tblAdjuntosHistIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechahistorificacion
     */
    private Date fechahistorificacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechahistorificacion
     */
    private Date fechahistorificacionMax;

    /**
     * Permite buscar cuando campo fechahistorificacion es NULL
     */
    private boolean fechahistorificacionIsNull = false;

    /**
     * Permite buscar cuando campo fechahistorificacion es NOT NULL
     */
    private boolean fechahistorificacionIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblMensajesHist
     */
    private boolean innerJoinTblMensajesHist = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblMensajesHist
     */
    private boolean leftJoinTblMensajesHist = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblAdjuntosHist
     */
    private boolean innerJoinTblAdjuntosHist = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblAdjuntosHist
     */
    private boolean leftJoinTblAdjuntosHist = false;

    /**
     * Constructor default
     */
    public TblMensajesAdjuntosHistQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblMensajesAdjuntosHistQuery(Long mensajeadjuntoid) {
        setMensajeadjuntoid(mensajeadjuntoid);
    }

    /**
     * Valor de busqueda de campo mensajeadjuntoid
     * @return Long.
     */
    public Long getMensajeadjuntoid() {
        return mensajeadjuntoid;
    }

    /**
     * Valor de busqueda de campo mensajeadjuntoid
     * @param mensajeadjuntoid Valor de seteo.
     */
    public void setMensajeadjuntoid(Long mensajeadjuntoid) {
        this.mensajeadjuntoid = mensajeadjuntoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getMensajeadjuntoidIn() {
        return this.mensajeadjuntoidIn;
    }

    /**
     * @param mensajeadjuntoid Valor a agregar.
     */
    public void addMensajeadjuntoidIn(Long mensajeadjuntoid) {
        this.mensajeadjuntoidIn.add(mensajeadjuntoid);
    }

    /**
     * Valor de busqueda de campo tblMensajesHist
     * @return TblMensajesHist.
     */
    public TblMensajesHistQuery getTblMensajesHist() {
        return tblMensajesHist;
    }

    /**
     * Valor de busqueda de campo tblMensajesHist
     * @param tblMensajesHist Valor de seteo.
     */
    public void setTblMensajesHist(TblMensajesHistQuery tblMensajesHist) {
        this.tblMensajesHist = tblMensajesHist;
    }

    /**
     * @return List<TblMensajesHist>.
     */
    public List<TblMensajesHist> getTblMensajesHistIdIn() {
        return this.tblMensajesHistIdIn;
    }

    /**
     * @param tblMensajesHist Valor a agregar.
     */
    public void addTblMensajesHistIdIn(TblMensajesHist tblMensajesHist) {
        this.tblMensajesHistIdIn.add(tblMensajesHist);
    }

    /**
     * Permite buscar cuando campo tblMensajesHist es NULL
     * @return boolean.
     */
    public boolean isTblMensajesHistIsNull() {
        return tblMensajesHistIsNull;
    }

    /**
     * Permite buscar cuando campo tblMensajesHist es NULL
     * @param tblMensajesHistIsNull Valor de seteo.
     */
    public void setTblMensajesHistIsNull(boolean tblMensajesHistIsNull) {
        this.tblMensajesHistIsNull = tblMensajesHistIsNull;
    }

    /**
     * Permite buscar cuando campo tblMensajesHist es NOT NULL
     * @return boolean.
     */
    public boolean isTblMensajesHistIsNotNull() {
        return tblMensajesHistIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblMensajesHist es NOT NULL
     * @param tblMensajesHistIsNotNull Valor de seteo.
     */
    public void setTblMensajesHistIsNotNull(boolean tblMensajesHistIsNotNull) {
        this.tblMensajesHistIsNotNull = tblMensajesHistIsNotNull;
    }

    /**
     * Valor de busqueda de campo tblAdjuntosHist
     * @return TblAdjuntosHist.
     */
    public TblAdjuntosHistQuery getTblAdjuntosHist() {
        return tblAdjuntosHist;
    }

    /**
     * Valor de busqueda de campo tblAdjuntosHist
     * @param tblAdjuntosHist Valor de seteo.
     */
    public void setTblAdjuntosHist(TblAdjuntosHistQuery tblAdjuntosHist) {
        this.tblAdjuntosHist = tblAdjuntosHist;
    }

    /**
     * @return List<TblAdjuntosHist>.
     */
    public List<TblAdjuntosHist> getTblAdjuntosHistIdIn() {
        return this.tblAdjuntosHistIdIn;
    }

    /**
     * @param tblAdjuntosHist Valor a agregar.
     */
    public void addTblAdjuntosHistIdIn(TblAdjuntosHist tblAdjuntosHist) {
        this.tblAdjuntosHistIdIn.add(tblAdjuntosHist);
    }

    /**
     * Permite buscar cuando campo tblAdjuntosHist es NULL
     * @return boolean.
     */
    public boolean isTblAdjuntosHistIsNull() {
        return tblAdjuntosHistIsNull;
    }

    /**
     * Permite buscar cuando campo tblAdjuntosHist es NULL
     * @param tblAdjuntosHistIsNull Valor de seteo.
     */
    public void setTblAdjuntosHistIsNull(boolean tblAdjuntosHistIsNull) {
        this.tblAdjuntosHistIsNull = tblAdjuntosHistIsNull;
    }

    /**
     * Permite buscar cuando campo tblAdjuntosHist es NOT NULL
     * @return boolean.
     */
    public boolean isTblAdjuntosHistIsNotNull() {
        return tblAdjuntosHistIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblAdjuntosHist es NOT NULL
     * @param tblAdjuntosHistIsNotNull Valor de seteo.
     */
    public void setTblAdjuntosHistIsNotNull(boolean tblAdjuntosHistIsNotNull) {
        this.tblAdjuntosHistIsNotNull = tblAdjuntosHistIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechahistorificacion
     * @return ${field.getName()}Min.
     */
    public Date getFechahistorificacionMin() {
        if (fechahistorificacionMin != null) {
            return DateUtil.toDayBegin(fechahistorificacionMin);
        }
        return fechahistorificacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechahistorificacion
     * @param fechahistorificacionMin Valor de seteo.
     */
    public void setFechahistorificacionMin(Date fechahistorificacionMin) {
        this.fechahistorificacionMin = fechahistorificacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechahistorificacion
     * @return fechahistorificacionMax.
     */
    public Date getFechahistorificacionMax() {
        if (fechahistorificacionMax != null) {
            return DateUtil.toDayEnd(fechahistorificacionMax);
        }
        return fechahistorificacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechahistorificacion
     * @param fechahistorificacionMax Valor de seteo.
     */
    public void setFechahistorificacionMax(Date fechahistorificacionMax) {
        this.fechahistorificacionMax = fechahistorificacionMax;
    }

    /**
     * Permite buscar cuando campo fechahistorificacion es NULL
     * @return boolean.
     */
    public boolean isFechahistorificacionIsNull() {
        return fechahistorificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechahistorificacion es NULL
     * @param fechahistorificacionIsNull Valor de seteo.
     */
    public void setFechahistorificacionIsNull(boolean fechahistorificacionIsNull) {
        this.fechahistorificacionIsNull = fechahistorificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechahistorificacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechahistorificacionIsNotNull() {
        return fechahistorificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechahistorificacion es NOT NULL
     * @param fechahistorificacionIsNotNull Valor de seteo.
     */
    public void setFechahistorificacionIsNotNull(boolean fechahistorificacionIsNotNull) {
        this.fechahistorificacionIsNotNull = fechahistorificacionIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblMensajesHist() {
        return innerJoinTblMensajesHist;
    }

    /**
     * @param innerJoinTblMensajesHist Valor de seteo.
     */
    public void setInnerJoinTblMensajesHist(boolean innerJoinTblMensajesHist) {
        this.innerJoinTblMensajesHist = innerJoinTblMensajesHist;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblMensajesHist() {
        return leftJoinTblMensajesHist;
    }

    /**
     * @param leftJoinTblMensajesHist Valor de seteo.
     */
    public void setLeftJoinTblMensajesHist(boolean leftJoinTblMensajesHist) {
        this.leftJoinTblMensajesHist = leftJoinTblMensajesHist;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblAdjuntosHist() {
        return innerJoinTblAdjuntosHist;
    }

    /**
     * @param innerJoinTblAdjuntosHist Valor de seteo.
     */
    public void setInnerJoinTblAdjuntosHist(boolean innerJoinTblAdjuntosHist) {
        this.innerJoinTblAdjuntosHist = innerJoinTblAdjuntosHist;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblAdjuntosHist() {
        return leftJoinTblAdjuntosHist;
    }

    /**
     * @param leftJoinTblAdjuntosHist Valor de seteo.
     */
    public void setLeftJoinTblAdjuntosHist(boolean leftJoinTblAdjuntosHist) {
        this.leftJoinTblAdjuntosHist = leftJoinTblAdjuntosHist;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getMensajeadjuntoid() != null) {
            criteria.add(Restrictions.eq(MENSAJEADJUNTOID, getMensajeadjuntoid()));
        }

        if (getMensajeadjuntoidIn().size() > 0) {
            criteria.add(Restrictions.in(MENSAJEADJUNTOID, getMensajeadjuntoidIn()));
        }

        // Campo entidad padre tblMensajesHist
        
        // Si se hace join fetch con el padre
        Criteria tblMensajesHistCriteria = null;
        if (isInnerJoinTblMensajesHist()) {
            tblMensajesHistCriteria = criteria.createCriteria(TBLMENSAJESHIST, "a_" + TBLMENSAJESHIST, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblMensajesHist()) {
            tblMensajesHistCriteria = criteria.createCriteria(TBLMENSAJESHIST, "a_" + TBLMENSAJESHIST, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblMensajesHist() != null) {
            if (getTblMensajesHist().getMensajeid() == null) {
                if (tblMensajesHistCriteria == null) {
                    tblMensajesHistCriteria = criteria.createCriteria(TBLMENSAJESHIST, "a_" + TBLMENSAJESHIST);
                }
                getTblMensajesHist().addCriteria(tblMensajesHistCriteria, useOrder);
            } else {
                TblMensajesHist parent = new TblMensajesHist();
                parent.setMensajeid(getTblMensajesHist().getMensajeid());
                criteria.add(Restrictions.eq(TBLMENSAJESHIST, parent));
            }
        }

        if (getTblMensajesHistIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLMENSAJESHIST, getTblMensajesHistIdIn()));
        }

        if (isTblMensajesHistIsNull()) {
            criteria.add(Restrictions.isNull(TBLMENSAJESHIST));
        }

        if (isTblMensajesHistIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLMENSAJESHIST));
        }

        // Campo entidad padre tblAdjuntosHist
        
        // Si se hace join fetch con el padre
        Criteria tblAdjuntosHistCriteria = null;
        if (isInnerJoinTblAdjuntosHist()) {
            tblAdjuntosHistCriteria = criteria.createCriteria(TBLADJUNTOSHIST, "a_" + TBLADJUNTOSHIST, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblAdjuntosHist()) {
            tblAdjuntosHistCriteria = criteria.createCriteria(TBLADJUNTOSHIST, "a_" + TBLADJUNTOSHIST, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblAdjuntosHist() != null) {
            if (getTblAdjuntosHist().getAdjuntoid() == null) {
                if (tblAdjuntosHistCriteria == null) {
                    tblAdjuntosHistCriteria = criteria.createCriteria(TBLADJUNTOSHIST, "a_" + TBLADJUNTOSHIST);
                }
                getTblAdjuntosHist().addCriteria(tblAdjuntosHistCriteria, useOrder);
            } else {
                TblAdjuntosHist parent = new TblAdjuntosHist();
                parent.setAdjuntoid(getTblAdjuntosHist().getAdjuntoid());
                criteria.add(Restrictions.eq(TBLADJUNTOSHIST, parent));
            }
        }

        if (getTblAdjuntosHistIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLADJUNTOSHIST, getTblAdjuntosHistIdIn()));
        }

        if (isTblAdjuntosHistIsNull()) {
            criteria.add(Restrictions.isNull(TBLADJUNTOSHIST));
        }

        if (isTblAdjuntosHistIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLADJUNTOSHIST));
        }

        if (getFechahistorificacionMin() != null) {
            criteria.add(Restrictions.ge(FECHAHISTORIFICACION, getFechahistorificacionMin()));
        }

        if (getFechahistorificacionMax() != null) {
            criteria.add(Restrictions.le(FECHAHISTORIFICACION, getFechahistorificacionMax()));
        }

        if (isFechahistorificacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAHISTORIFICACION));
        }

        if (isFechahistorificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAHISTORIFICACION));
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
 
