/*
 *
 * archivo: TblMensajesAdjuntosQuery.java
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
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.sim.model.TblAdjuntos;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblMensajesAdjuntos;

/**
 * Clase con criterios de busqueda para la entidad TblMensajesAdjuntos
 */
public class TblMensajesAdjuntosQuery extends AbstractHibernateQueryEntity<TblMensajesAdjuntos> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String MENSAJEADJUNTOID = "mensajeadjuntoid";
    public static final String TBLMENSAJES = "tblMensajes";
    public static final String TBLADJUNTOS = "tblAdjuntos";


    /**
     * Valor de busqueda de campo mensajeadjuntoid
     */
    private Long mensajeadjuntoid;

    /**
     * Lista de valores del campo mensajeadjuntoid para busquedas tipo IN
     */
    private List<Long> mensajeadjuntoidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblMensajes
     */
    private TblMensajesQuery tblMensajes;

    /**
     * Lista de valores del ID del campo tblMensajes para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblMensajes
     */
    private List<TblMensajes> tblMensajesIdIn = new ArrayList<TblMensajes>(0);

    /**
     * Permite buscar cuando campo tblMensajes es NULL
     */
    private boolean tblMensajesIsNull = false;

    /**
     * Permite buscar cuando campo tblMensajes es NOT NULL
     */
    private boolean tblMensajesIsNotNull = false;

    /**
     * Valor de busqueda de campo tblAdjuntos
     */
    private TblAdjuntosQuery tblAdjuntos;

    /**
     * Lista de valores del ID del campo tblAdjuntos para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblAdjuntos
     */
    private List<TblAdjuntos> tblAdjuntosIdIn = new ArrayList<TblAdjuntos>(0);

    /**
     * Permite buscar cuando campo tblAdjuntos es NULL
     */
    private boolean tblAdjuntosIsNull = false;

    /**
     * Permite buscar cuando campo tblAdjuntos es NOT NULL
     */
    private boolean tblAdjuntosIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblMensajes
     */
    private boolean innerJoinTblMensajes = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblMensajes
     */
    private boolean leftJoinTblMensajes = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblAdjuntos
     */
    private boolean innerJoinTblAdjuntos = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblAdjuntos
     */
    private boolean leftJoinTblAdjuntos = false;

    /**
     * Constructor default
     */
    public TblMensajesAdjuntosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblMensajesAdjuntosQuery(Long mensajeadjuntoid) {
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
     * Valor de busqueda de campo tblMensajes
     * @return TblMensajes.
     */
    public TblMensajesQuery getTblMensajes() {
        return tblMensajes;
    }

    /**
     * Valor de busqueda de campo tblMensajes
     * @param tblMensajes Valor de seteo.
     */
    public void setTblMensajes(TblMensajesQuery tblMensajes) {
        this.tblMensajes = tblMensajes;
    }

    /**
     * @return List<TblMensajes>.
     */
    public List<TblMensajes> getTblMensajesIdIn() {
        return this.tblMensajesIdIn;
    }

    /**
     * @param tblMensajes Valor a agregar.
     */
    public void addTblMensajesIdIn(TblMensajes tblMensajes) {
        this.tblMensajesIdIn.add(tblMensajes);
    }

    /**
     * Permite buscar cuando campo tblMensajes es NULL
     * @return boolean.
     */
    public boolean isTblMensajesIsNull() {
        return tblMensajesIsNull;
    }

    /**
     * Permite buscar cuando campo tblMensajes es NULL
     * @param tblMensajesIsNull Valor de seteo.
     */
    public void setTblMensajesIsNull(boolean tblMensajesIsNull) {
        this.tblMensajesIsNull = tblMensajesIsNull;
    }

    /**
     * Permite buscar cuando campo tblMensajes es NOT NULL
     * @return boolean.
     */
    public boolean isTblMensajesIsNotNull() {
        return tblMensajesIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblMensajes es NOT NULL
     * @param tblMensajesIsNotNull Valor de seteo.
     */
    public void setTblMensajesIsNotNull(boolean tblMensajesIsNotNull) {
        this.tblMensajesIsNotNull = tblMensajesIsNotNull;
    }

    /**
     * Valor de busqueda de campo tblAdjuntos
     * @return TblAdjuntos.
     */
    public TblAdjuntosQuery getTblAdjuntos() {
        return tblAdjuntos;
    }

    /**
     * Valor de busqueda de campo tblAdjuntos
     * @param tblAdjuntos Valor de seteo.
     */
    public void setTblAdjuntos(TblAdjuntosQuery tblAdjuntos) {
        this.tblAdjuntos = tblAdjuntos;
    }

    /**
     * @return List<TblAdjuntos>.
     */
    public List<TblAdjuntos> getTblAdjuntosIdIn() {
        return this.tblAdjuntosIdIn;
    }

    /**
     * @param tblAdjuntos Valor a agregar.
     */
    public void addTblAdjuntosIdIn(TblAdjuntos tblAdjuntos) {
        this.tblAdjuntosIdIn.add(tblAdjuntos);
    }

    /**
     * Permite buscar cuando campo tblAdjuntos es NULL
     * @return boolean.
     */
    public boolean isTblAdjuntosIsNull() {
        return tblAdjuntosIsNull;
    }

    /**
     * Permite buscar cuando campo tblAdjuntos es NULL
     * @param tblAdjuntosIsNull Valor de seteo.
     */
    public void setTblAdjuntosIsNull(boolean tblAdjuntosIsNull) {
        this.tblAdjuntosIsNull = tblAdjuntosIsNull;
    }

    /**
     * Permite buscar cuando campo tblAdjuntos es NOT NULL
     * @return boolean.
     */
    public boolean isTblAdjuntosIsNotNull() {
        return tblAdjuntosIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblAdjuntos es NOT NULL
     * @param tblAdjuntosIsNotNull Valor de seteo.
     */
    public void setTblAdjuntosIsNotNull(boolean tblAdjuntosIsNotNull) {
        this.tblAdjuntosIsNotNull = tblAdjuntosIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblMensajes() {
        return innerJoinTblMensajes;
    }

    /**
     * @param innerJoinTblMensajes Valor de seteo.
     */
    public void setInnerJoinTblMensajes(boolean innerJoinTblMensajes) {
        this.innerJoinTblMensajes = innerJoinTblMensajes;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblMensajes() {
        return leftJoinTblMensajes;
    }

    /**
     * @param leftJoinTblMensajes Valor de seteo.
     */
    public void setLeftJoinTblMensajes(boolean leftJoinTblMensajes) {
        this.leftJoinTblMensajes = leftJoinTblMensajes;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblAdjuntos() {
        return innerJoinTblAdjuntos;
    }

    /**
     * @param innerJoinTblAdjuntos Valor de seteo.
     */
    public void setInnerJoinTblAdjuntos(boolean innerJoinTblAdjuntos) {
        this.innerJoinTblAdjuntos = innerJoinTblAdjuntos;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblAdjuntos() {
        return leftJoinTblAdjuntos;
    }

    /**
     * @param leftJoinTblAdjuntos Valor de seteo.
     */
    public void setLeftJoinTblAdjuntos(boolean leftJoinTblAdjuntos) {
        this.leftJoinTblAdjuntos = leftJoinTblAdjuntos;
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

        // Campo entidad padre tblMensajes
        
        // Si se hace join fetch con el padre
        Criteria tblMensajesCriteria = null;
        if (isInnerJoinTblMensajes()) {
            tblMensajesCriteria = criteria.createCriteria(TBLMENSAJES, "a_" + TBLMENSAJES, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblMensajes()) {
            tblMensajesCriteria = criteria.createCriteria(TBLMENSAJES, "a_" + TBLMENSAJES, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblMensajes() != null) {
            if (getTblMensajes().getMensajeid() == null) {
                if (tblMensajesCriteria == null) {
                    tblMensajesCriteria = criteria.createCriteria(TBLMENSAJES, "a_" + TBLMENSAJES);
                }
                getTblMensajes().addCriteria(tblMensajesCriteria, useOrder);
            } else {
                TblMensajes parent = new TblMensajes();
                parent.setMensajeid(getTblMensajes().getMensajeid());
                criteria.add(Restrictions.eq(TBLMENSAJES, parent));
            }
        }

        if (getTblMensajesIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLMENSAJES, getTblMensajesIdIn()));
        }

        if (isTblMensajesIsNull()) {
            criteria.add(Restrictions.isNull(TBLMENSAJES));
        }

        if (isTblMensajesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLMENSAJES));
        }

        // Campo entidad padre tblAdjuntos
        
        // Si se hace join fetch con el padre
        Criteria tblAdjuntosCriteria = null;
        if (isInnerJoinTblAdjuntos()) {
            tblAdjuntosCriteria = criteria.createCriteria(TBLADJUNTOS, "a_" + TBLADJUNTOS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblAdjuntos()) {
            tblAdjuntosCriteria = criteria.createCriteria(TBLADJUNTOS, "a_" + TBLADJUNTOS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblAdjuntos() != null) {
            if (getTblAdjuntos().getAdjuntoid() == null) {
                if (tblAdjuntosCriteria == null) {
                    tblAdjuntosCriteria = criteria.createCriteria(TBLADJUNTOS, "a_" + TBLADJUNTOS);
                }
                getTblAdjuntos().addCriteria(tblAdjuntosCriteria, useOrder);
            } else {
                TblAdjuntos parent = new TblAdjuntos();
                parent.setAdjuntoid(getTblAdjuntos().getAdjuntoid());
                criteria.add(Restrictions.eq(TBLADJUNTOS, parent));
            }
        }

        if (getTblAdjuntosIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLADJUNTOS, getTblAdjuntosIdIn()));
        }

        if (isTblAdjuntosIsNull()) {
            criteria.add(Restrictions.isNull(TBLADJUNTOS));
        }

        if (isTblAdjuntosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLADJUNTOS));
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
 
