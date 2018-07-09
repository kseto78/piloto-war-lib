/*
 *
 * archivo: TblSubestadoMensajeQuery.java
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
import es.minhap.sim.model.TblSubestadoMensaje;
import es.minhap.sim.model.TblSubestados;

/**
 * Clase con criterios de busqueda para la entidad TblSubestadoMensaje
 */
public class TblSubestadoMensajeQuery extends AbstractHibernateQueryEntity<TblSubestadoMensaje> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String SUBESTADOMENSAJEID = "subestadomensajeid";
    public static final String TBLSUBESTADOS = "tblSubestados";
    public static final String DESCRIPCION = "descripcion";
    public static final String CODIGO = "codigo";


    /**
     * Valor de busqueda de campo subestadomensajeid
     */
    private Long subestadomensajeid;

    /**
     * Lista de valores del campo subestadomensajeid para busquedas tipo IN
     */
    private List<Long> subestadomensajeidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblSubestados
     */
    private TblSubestadosQuery tblSubestados;

    /**
     * Lista de valores del ID del campo tblSubestados para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblSubestados
     */
    private List<TblSubestados> tblSubestadosIdIn = new ArrayList<TblSubestados>(0);

    /**
     * Permite buscar cuando campo tblSubestados es NULL
     */
    private boolean tblSubestadosIsNull = false;

    /**
     * Permite buscar cuando campo tblSubestados es NOT NULL
     */
    private boolean tblSubestadosIsNotNull = false;

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
     * Valor de busqueda de campo codigo
     */
    private String codigo;

    /**
     * Tipo de comparador para la busqueda por campo codigo
     */
    private TextComparator codigoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codigo para busquedas tipo IN
     */
    private List<String> codigoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codigo es NULL
     */
    private boolean codigoIsNull = false;

    /**
     * Permite buscar cuando campo codigo es NOT NULL
     */
    private boolean codigoIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblSubestados
     */
    private boolean innerJoinTblSubestados = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblSubestados
     */
    private boolean leftJoinTblSubestados = false;

    /**
     * Constructor default
     */
    public TblSubestadoMensajeQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblSubestadoMensajeQuery(Long subestadomensajeid) {
        setSubestadomensajeid(subestadomensajeid);
    }

    /**
     * Valor de busqueda de campo subestadomensajeid
     * @return Long.
     */
    public Long getSubestadomensajeid() {
        return subestadomensajeid;
    }

    /**
     * Valor de busqueda de campo subestadomensajeid
     * @param subestadomensajeid Valor de seteo.
     */
    public void setSubestadomensajeid(Long subestadomensajeid) {
        this.subestadomensajeid = subestadomensajeid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getSubestadomensajeidIn() {
        return this.subestadomensajeidIn;
    }

    /**
     * @param subestadomensajeid Valor a agregar.
     */
    public void addSubestadomensajeidIn(Long subestadomensajeid) {
        this.subestadomensajeidIn.add(subestadomensajeid);
    }

    /**
     * Valor de busqueda de campo tblSubestados
     * @return TblSubestados.
     */
    public TblSubestadosQuery getTblSubestados() {
        return tblSubestados;
    }

    /**
     * Valor de busqueda de campo tblSubestados
     * @param tblSubestados Valor de seteo.
     */
    public void setTblSubestados(TblSubestadosQuery tblSubestados) {
        this.tblSubestados = tblSubestados;
    }

    /**
     * @return List<TblSubestados>.
     */
    public List<TblSubestados> getTblSubestadosIdIn() {
        return this.tblSubestadosIdIn;
    }

    /**
     * @param tblSubestados Valor a agregar.
     */
    public void addTblSubestadosIdIn(TblSubestados tblSubestados) {
        this.tblSubestadosIdIn.add(tblSubestados);
    }

    /**
     * Permite buscar cuando campo tblSubestados es NULL
     * @return boolean.
     */
    public boolean isTblSubestadosIsNull() {
        return tblSubestadosIsNull;
    }

    /**
     * Permite buscar cuando campo tblSubestados es NULL
     * @param tblSubestadosIsNull Valor de seteo.
     */
    public void setTblSubestadosIsNull(boolean tblSubestadosIsNull) {
        this.tblSubestadosIsNull = tblSubestadosIsNull;
    }

    /**
     * Permite buscar cuando campo tblSubestados es NOT NULL
     * @return boolean.
     */
    public boolean isTblSubestadosIsNotNull() {
        return tblSubestadosIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblSubestados es NOT NULL
     * @param tblSubestadosIsNotNull Valor de seteo.
     */
    public void setTblSubestadosIsNotNull(boolean tblSubestadosIsNotNull) {
        this.tblSubestadosIsNotNull = tblSubestadosIsNotNull;
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
     * Valor de busqueda de campo codigo
     * @return String.
     */
    public String getCodigo() {
        if (codigo != null) {
            switch (codigoComparator) {
	            case STARTS_WITH:
	                return codigo + "%";
	            case CONTAINS:
	                return "%" + codigo + "%";
	            case ENDS_WITH:
	                return "%" + codigo;
	            case EQUALS:
                	return codigo;
              	default:
	            	break;
            }
        }
        return codigo;
    }

    /**
     * Valor de busqueda de campo codigo
     * @param codigo Valor de seteo.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Tipo de comparador para la busqueda por campo codigo
     * @return codigoComparator.
     */
    public TextComparator getCodigoComparator() {
        return codigoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codigo
     * @param codigoComparator Valor de seteo.
     */
    public void setCodigoComparator(TextComparator codigoComparator) {
        this.codigoComparator = codigoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodigoIn() {
        return this.codigoIn;
    }

    /**
     * @param codigo Valor a agregar.
     */
    public void addCodigoIn(String codigo) {
        this.codigoIn.add(codigo);
    }

    /**
     * Permite buscar cuando campo codigo es NULL
     * @return boolean.
     */
    public boolean isCodigoIsNull() {
        return codigoIsNull;
    }

    /**
     * Permite buscar cuando campo codigo es NULL
     * @param codigoIsNull Valor de seteo.
     */
    public void setCodigoIsNull(boolean codigoIsNull) {
        this.codigoIsNull = codigoIsNull;
    }

    /**
     * Permite buscar cuando campo codigo es NOT NULL
     * @return boolean.
     */
    public boolean isCodigoIsNotNull() {
        return codigoIsNotNull;
    }

    /**
     * Permite buscar cuando campo codigo es NOT NULL
     * @param codigoIsNotNull Valor de seteo.
     */
    public void setCodigoIsNotNull(boolean codigoIsNotNull) {
        this.codigoIsNotNull = codigoIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblSubestados() {
        return innerJoinTblSubestados;
    }

    /**
     * @param innerJoinTblSubestados Valor de seteo.
     */
    public void setInnerJoinTblSubestados(boolean innerJoinTblSubestados) {
        this.innerJoinTblSubestados = innerJoinTblSubestados;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblSubestados() {
        return leftJoinTblSubestados;
    }

    /**
     * @param leftJoinTblSubestados Valor de seteo.
     */
    public void setLeftJoinTblSubestados(boolean leftJoinTblSubestados) {
        this.leftJoinTblSubestados = leftJoinTblSubestados;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getSubestadomensajeid() != null) {
            criteria.add(Restrictions.eq(SUBESTADOMENSAJEID, getSubestadomensajeid()));
        }

        if (getSubestadomensajeidIn().size() > 0) {
            criteria.add(Restrictions.in(SUBESTADOMENSAJEID, getSubestadomensajeidIn()));
        }

        // Campo entidad padre tblSubestados
        
        // Si se hace join fetch con el padre
        Criteria tblSubestadosCriteria = null;
        if (isInnerJoinTblSubestados()) {
            tblSubestadosCriteria = criteria.createCriteria(TBLSUBESTADOS, "a_" + TBLSUBESTADOS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblSubestados()) {
            tblSubestadosCriteria = criteria.createCriteria(TBLSUBESTADOS, "a_" + TBLSUBESTADOS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblSubestados() != null) {
            if (getTblSubestados().getSubestadoid() == null) {
                if (tblSubestadosCriteria == null) {
                    tblSubestadosCriteria = criteria.createCriteria(TBLSUBESTADOS, "a_" + TBLSUBESTADOS);
                }
                getTblSubestados().addCriteria(tblSubestadosCriteria, useOrder);
            } else {
                TblSubestados parent = new TblSubestados();
                parent.setSubestadoid(getTblSubestados().getSubestadoid());
                criteria.add(Restrictions.eq(TBLSUBESTADOS, parent));
            }
        }

        if (getTblSubestadosIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLSUBESTADOS, getTblSubestadosIdIn()));
        }

        if (isTblSubestadosIsNull()) {
            criteria.add(Restrictions.isNull(TBLSUBESTADOS));
        }

        if (isTblSubestadosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLSUBESTADOS));
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

        if (getCodigo() != null) {
            if (getCodigoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODIGO, getCodigo()));
            } 
            else if (getCodigoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODIGO, getCodigo()));
            }
            else if (getCodigoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODIGO, getCodigo())));
            }
            else {
                criteria.add(Restrictions.like(CODIGO, getCodigo()));
            }
        }

        if (getCodigoIn().size() > 0) {
            criteria.add(Restrictions.in(CODIGO, getCodigoIn()));
        }

        if (isCodigoIsNull()) {
            criteria.add(Restrictions.isNull(CODIGO));
        }

        if (isCodigoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODIGO));
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
 
