/*
 *
 * archivo: TblSubestadosQuery.java
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

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.sim.model.TblEstados;
import es.minhap.sim.model.TblSubestados;

/**
 * Clase con criterios de busqueda para la entidad TblSubestados
 */
public class TblSubestadosQuery extends AbstractHibernateQueryEntity<TblSubestados> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String SUBESTADOID = "subestadoid";
    public static final String TBLESTADOS = "tblEstados";
    public static final String DESCRIPCION = "descripcion";


    /**
     * Valor de busqueda de campo subestadoid
     */
    private Long subestadoid;

    /**
     * Lista de valores del campo subestadoid para busquedas tipo IN
     */
    private List<Long> subestadoidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblEstados
     */
    private TblEstadosQuery tblEstados;

    /**
     * Lista de valores del ID del campo tblEstados para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblEstados
     */
    private List<TblEstados> tblEstadosIdIn = new ArrayList<TblEstados>(0);

    /**
     * Permite buscar cuando campo tblEstados es NULL
     */
    private boolean tblEstadosIsNull = false;

    /**
     * Permite buscar cuando campo tblEstados es NOT NULL
     */
    private boolean tblEstadosIsNotNull = false;

    /**
     * Valor de busqueda de campo descripcion
     */
    private String descripcion;

    /**
     * Tipo de comparador para la busqueda por campo descripcion
     */
    private TextComparator descripcionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo descripcion para busquedas tipo IN
     */
    private List<String> descripcionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo descripcion es NULL
     */
    private boolean descripcionIsNull = false;

    /**
     * Permite buscar cuando campo descripcion es NOT NULL
     */
    private boolean descripcionIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblEstados
     */
    private boolean innerJoinTblEstados = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblEstados
     */
    private boolean leftJoinTblEstados = false;

    /**
     * Constructor default
     */
    public TblSubestadosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblSubestadosQuery(Long subestadoid) {
        setSubestadoid(subestadoid);
    }

    /**
     * Valor de busqueda de campo subestadoid
     * @return Long.
     */
    public Long getSubestadoid() {
        return subestadoid;
    }

    /**
     * Valor de busqueda de campo subestadoid
     * @param subestadoid Valor de seteo.
     */
    public void setSubestadoid(Long subestadoid) {
        this.subestadoid = subestadoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getSubestadoidIn() {
        return this.subestadoidIn;
    }

    /**
     * @param subestadoid Valor a agregar.
     */
    public void addSubestadoidIn(Long subestadoid) {
        this.subestadoidIn.add(subestadoid);
    }

    /**
     * Valor de busqueda de campo tblEstados
     * @return TblEstados.
     */
    public TblEstadosQuery getTblEstados() {
        return tblEstados;
    }

    /**
     * Valor de busqueda de campo tblEstados
     * @param tblEstados Valor de seteo.
     */
    public void setTblEstados(TblEstadosQuery tblEstados) {
        this.tblEstados = tblEstados;
    }

    /**
     * @return List<TblEstados>.
     */
    public List<TblEstados> getTblEstadosIdIn() {
        return this.tblEstadosIdIn;
    }

    /**
     * @param tblEstados Valor a agregar.
     */
    public void addTblEstadosIdIn(TblEstados tblEstados) {
        this.tblEstadosIdIn.add(tblEstados);
    }

    /**
     * Permite buscar cuando campo tblEstados es NULL
     * @return boolean.
     */
    public boolean isTblEstadosIsNull() {
        return tblEstadosIsNull;
    }

    /**
     * Permite buscar cuando campo tblEstados es NULL
     * @param tblEstadosIsNull Valor de seteo.
     */
    public void setTblEstadosIsNull(boolean tblEstadosIsNull) {
        this.tblEstadosIsNull = tblEstadosIsNull;
    }

    /**
     * Permite buscar cuando campo tblEstados es NOT NULL
     * @return boolean.
     */
    public boolean isTblEstadosIsNotNull() {
        return tblEstadosIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblEstados es NOT NULL
     * @param tblEstadosIsNotNull Valor de seteo.
     */
    public void setTblEstadosIsNotNull(boolean tblEstadosIsNotNull) {
        this.tblEstadosIsNotNull = tblEstadosIsNotNull;
    }

    /**
     * Valor de busqueda de campo descripcion
     * @return String.
     */
    public String getDescripcion() {
        if (descripcion != null) {
            switch (descripcionComparator) {
	            case STARTS_WITH:
	                return descripcion + "%";
	            case CONTAINS:
	                return "%" + descripcion + "%";
	            case ENDS_WITH:
	                return "%" + descripcion;
	            case EQUALS:
                	return descripcion;
              	default:
	            	break;
            }
        }
        return descripcion;
    }

    /**
     * Valor de busqueda de campo descripcion
     * @param descripcion Valor de seteo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Tipo de comparador para la busqueda por campo descripcion
     * @return descripcionComparator.
     */
    public TextComparator getDescripcionComparator() {
        return descripcionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo descripcion
     * @param descripcionComparator Valor de seteo.
     */
    public void setDescripcionComparator(TextComparator descripcionComparator) {
        this.descripcionComparator = descripcionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDescripcionIn() {
        return this.descripcionIn;
    }

    /**
     * @param descripcion Valor a agregar.
     */
    public void addDescripcionIn(String descripcion) {
        this.descripcionIn.add(descripcion);
    }

    /**
     * Permite buscar cuando campo descripcion es NULL
     * @return boolean.
     */
    public boolean isDescripcionIsNull() {
        return descripcionIsNull;
    }

    /**
     * Permite buscar cuando campo descripcion es NULL
     * @param descripcionIsNull Valor de seteo.
     */
    public void setDescripcionIsNull(boolean descripcionIsNull) {
        this.descripcionIsNull = descripcionIsNull;
    }

    /**
     * Permite buscar cuando campo descripcion es NOT NULL
     * @return boolean.
     */
    public boolean isDescripcionIsNotNull() {
        return descripcionIsNotNull;
    }

    /**
     * Permite buscar cuando campo descripcion es NOT NULL
     * @param descripcionIsNotNull Valor de seteo.
     */
    public void setDescripcionIsNotNull(boolean descripcionIsNotNull) {
        this.descripcionIsNotNull = descripcionIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblEstados() {
        return innerJoinTblEstados;
    }

    /**
     * @param innerJoinTblEstados Valor de seteo.
     */
    public void setInnerJoinTblEstados(boolean innerJoinTblEstados) {
        this.innerJoinTblEstados = innerJoinTblEstados;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblEstados() {
        return leftJoinTblEstados;
    }

    /**
     * @param leftJoinTblEstados Valor de seteo.
     */
    public void setLeftJoinTblEstados(boolean leftJoinTblEstados) {
        this.leftJoinTblEstados = leftJoinTblEstados;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getSubestadoid() != null) {
            criteria.add(Restrictions.eq(SUBESTADOID, getSubestadoid()));
        }

        if (getSubestadoidIn().size() > 0) {
            criteria.add(Restrictions.in(SUBESTADOID, getSubestadoidIn()));
        }

        // Campo entidad padre tblEstados
        
        // Si se hace join fetch con el padre
        Criteria tblEstadosCriteria = null;
        if (isInnerJoinTblEstados()) {
            tblEstadosCriteria = criteria.createCriteria(TBLESTADOS, "a_" + TBLESTADOS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblEstados()) {
            tblEstadosCriteria = criteria.createCriteria(TBLESTADOS, "a_" + TBLESTADOS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblEstados() != null) {
            if (getTblEstados().getEstadoid() == null) {
                if (tblEstadosCriteria == null) {
                    tblEstadosCriteria = criteria.createCriteria(TBLESTADOS, "a_" + TBLESTADOS);
                }
                getTblEstados().addCriteria(tblEstadosCriteria, useOrder);
            } else {
                TblEstados parent = new TblEstados();
                parent.setEstadoid(getTblEstados().getEstadoid());
                criteria.add(Restrictions.eq(TBLESTADOS, parent));
            }
        }

        if (getTblEstadosIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLESTADOS, getTblEstadosIdIn()));
        }

        if (isTblEstadosIsNull()) {
            criteria.add(Restrictions.isNull(TBLESTADOS));
        }

        if (isTblEstadosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLESTADOS));
        }

        if (getDescripcion() != null) {
            if (getDescripcionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DESCRIPCION, getDescripcion()));
            } 
            else if (getDescripcionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DESCRIPCION, getDescripcion()));
            }
            else if (getDescripcionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DESCRIPCION, getDescripcion())));
            }
            else {
                criteria.add(Restrictions.like(DESCRIPCION, getDescripcion()));
            }
        }

        if (getDescripcionIn().size() > 0) {
            criteria.add(Restrictions.in(DESCRIPCION, getDescripcionIn()));
        }

        if (isDescripcionIsNull()) {
            criteria.add(Restrictions.isNull(DESCRIPCION));
        }

        if (isDescripcionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DESCRIPCION));
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
 
