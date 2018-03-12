/*
 *
 * archivo: TblUsuariosAplicacionesQuery.java
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
import es.minhap.sim.model.TblUsuarios;
import es.minhap.sim.model.TblUsuariosAplicaciones;

/**
 * Clase con criterios de busqueda para la entidad TblUsuariosAplicaciones
 */
public class TblUsuariosAplicacionesQuery extends AbstractHibernateQueryEntity<TblUsuariosAplicaciones> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String USUARIOAPLICACIONID = "usuarioaplicacionid";
    public static final String TBLUSUARIOS = "tblUsuarios";
    public static final String APLICACIONID = "aplicacionid";
    public static final String MODO = "modo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";


    /**
     * Valor de busqueda de campo usuarioaplicacionid
     */
    private Long usuarioaplicacionid;

    /**
     * Lista de valores del campo usuarioaplicacionid para busquedas tipo IN
     */
    private List<Long> usuarioaplicacionidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblUsuarios
     */
    private TblUsuariosQuery tblUsuarios;

    /**
     * Lista de valores del ID del campo tblUsuarios para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblUsuarios
     */
    private List<TblUsuarios> tblUsuariosIdIn = new ArrayList<TblUsuarios>(0);

    /**
     * Permite buscar cuando campo tblUsuarios es NULL
     */
    private boolean tblUsuariosIsNull = false;

    /**
     * Permite buscar cuando campo tblUsuarios es NOT NULL
     */
    private boolean tblUsuariosIsNotNull = false;

    /**
     * Valor de busqueda de campo aplicacionid
     */
    private Long aplicacionid;

    /**
     * Lista de valores del campo aplicacionid para busquedas tipo IN
     */
    private List<Long> aplicacionidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo aplicacionid es NULL
     */
    private boolean aplicacionidIsNull = false;

    /**
     * Permite buscar cuando campo aplicacionid es NOT NULL
     */
    private boolean aplicacionidIsNotNull = false;

    /**
     * Valor de busqueda de campo modo
     */
    private Integer modo;

    /**
     * Lista de valores del campo modo para busquedas tipo IN
     */
    private List<Integer> modoIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo modo es NULL
     */
    private boolean modoIsNull = false;

    /**
     * Permite buscar cuando campo modo es NOT NULL
     */
    private boolean modoIsNotNull = false;

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
     * Valor de busqueda de campo creadopor
     */
    private String creadopor;

    /**
     * Tipo de comparador para la busqueda por campo creadopor
     */
    private TextComparator creadoporComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo creadopor para busquedas tipo IN
     */
    private List<String> creadoporIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo creadopor es NULL
     */
    private boolean creadoporIsNull = false;

    /**
     * Permite buscar cuando campo creadopor es NOT NULL
     */
    private boolean creadoporIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblUsuarios
     */
    private boolean innerJoinTblUsuarios = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblUsuarios
     */
    private boolean leftJoinTblUsuarios = false;

    /**
   	 * Establece el máximo de resultados
   	 */
   	private Integer maxResultados;
   	
   	/**
   	 * Establece el primer resultados
   	 */
   	private Integer primerResultado;
    
    /**
     * Constructor default
     */
    public TblUsuariosAplicacionesQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblUsuariosAplicacionesQuery(Long usuarioaplicacionid) {
        setUsuarioaplicacionid(usuarioaplicacionid);
    }

    /**
     * Valor de busqueda de campo usuarioaplicacionid
     * @return Long.
     */
    public Long getUsuarioaplicacionid() {
        return usuarioaplicacionid;
    }

    /**
     * Valor de busqueda de campo usuarioaplicacionid
     * @param usuarioaplicacionid Valor de seteo.
     */
    public void setUsuarioaplicacionid(Long usuarioaplicacionid) {
        this.usuarioaplicacionid = usuarioaplicacionid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getUsuarioaplicacionidIn() {
        return this.usuarioaplicacionidIn;
    }

    /**
     * @param usuarioaplicacionid Valor a agregar.
     */
    public void addUsuarioaplicacionidIn(Long usuarioaplicacionid) {
        this.usuarioaplicacionidIn.add(usuarioaplicacionid);
    }

    /**
     * Valor de busqueda de campo tblUsuarios
     * @return TblUsuarios.
     */
    public TblUsuariosQuery getTblUsuarios() {
        return tblUsuarios;
    }

    /**
     * Valor de busqueda de campo tblUsuarios
     * @param tblUsuarios Valor de seteo.
     */
    public void setTblUsuarios(TblUsuariosQuery tblUsuarios) {
        this.tblUsuarios = tblUsuarios;
    }

    /**
     * @return List<TblUsuarios>.
     */
    public List<TblUsuarios> getTblUsuariosIdIn() {
        return this.tblUsuariosIdIn;
    }

    /**
     * @param tblUsuarios Valor a agregar.
     */
    public void addTblUsuariosIdIn(TblUsuarios tblUsuarios) {
        this.tblUsuariosIdIn.add(tblUsuarios);
    }

    /**
     * Permite buscar cuando campo tblUsuarios es NULL
     * @return boolean.
     */
    public boolean isTblUsuariosIsNull() {
        return tblUsuariosIsNull;
    }

    /**
     * Permite buscar cuando campo tblUsuarios es NULL
     * @param tblUsuariosIsNull Valor de seteo.
     */
    public void setTblUsuariosIsNull(boolean tblUsuariosIsNull) {
        this.tblUsuariosIsNull = tblUsuariosIsNull;
    }

    /**
     * Permite buscar cuando campo tblUsuarios es NOT NULL
     * @return boolean.
     */
    public boolean isTblUsuariosIsNotNull() {
        return tblUsuariosIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblUsuarios es NOT NULL
     * @param tblUsuariosIsNotNull Valor de seteo.
     */
    public void setTblUsuariosIsNotNull(boolean tblUsuariosIsNotNull) {
        this.tblUsuariosIsNotNull = tblUsuariosIsNotNull;
    }

    /**
     * Valor de busqueda de campo aplicacionid
     * @return Long.
     */
    public Long getAplicacionid() {
        return aplicacionid;
    }

    /**
     * Valor de busqueda de campo aplicacionid
     * @param aplicacionid Valor de seteo.
     */
    public void setAplicacionid(Long aplicacionid) {
        this.aplicacionid = aplicacionid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getAplicacionidIn() {
        return this.aplicacionidIn;
    }

    /**
     * @param aplicacionid Valor a agregar.
     */
    public void addAplicacionidIn(Long aplicacionid) {
        this.aplicacionidIn.add(aplicacionid);
    }

    /**
     * Permite buscar cuando campo aplicacionid es NULL
     * @return boolean.
     */
    public boolean isAplicacionidIsNull() {
        return aplicacionidIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacionid es NULL
     * @param aplicacionidIsNull Valor de seteo.
     */
    public void setAplicacionidIsNull(boolean aplicacionidIsNull) {
        this.aplicacionidIsNull = aplicacionidIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacionid es NOT NULL
     * @return boolean.
     */
    public boolean isAplicacionidIsNotNull() {
        return aplicacionidIsNotNull;
    }

    /**
     * Permite buscar cuando campo aplicacionid es NOT NULL
     * @param aplicacionidIsNotNull Valor de seteo.
     */
    public void setAplicacionidIsNotNull(boolean aplicacionidIsNotNull) {
        this.aplicacionidIsNotNull = aplicacionidIsNotNull;
    }

    /**
     * Valor de busqueda de campo modo
     * @return Integer.
     */
    public Integer getModo() {
        return modo;
    }

    /**
     * Valor de busqueda de campo modo
     * @param modo Valor de seteo.
     */
    public void setModo(Integer modo) {
        this.modo = modo;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getModoIn() {
        return this.modoIn;
    }

    /**
     * @param modo Valor a agregar.
     */
    public void addModoIn(Integer modo) {
        this.modoIn.add(modo);
    }

    /**
     * Permite buscar cuando campo modo es NULL
     * @return boolean.
     */
    public boolean isModoIsNull() {
        return modoIsNull;
    }

    /**
     * Permite buscar cuando campo modo es NULL
     * @param modoIsNull Valor de seteo.
     */
    public void setModoIsNull(boolean modoIsNull) {
        this.modoIsNull = modoIsNull;
    }

    /**
     * Permite buscar cuando campo modo es NOT NULL
     * @return boolean.
     */
    public boolean isModoIsNotNull() {
        return modoIsNotNull;
    }

    /**
     * Permite buscar cuando campo modo es NOT NULL
     * @param modoIsNotNull Valor de seteo.
     */
    public void setModoIsNotNull(boolean modoIsNotNull) {
        this.modoIsNotNull = modoIsNotNull;
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
     * Valor de busqueda de campo creadopor
     * @return String.
     */
    public String getCreadopor() {
        if (creadopor != null) {
            switch (creadoporComparator) {
	            case STARTS_WITH:
	                return creadopor + "%";
	            case CONTAINS:
	                return "%" + creadopor + "%";
	            case ENDS_WITH:
	                return "%" + creadopor;
	            case EQUALS:
                	return creadopor;
              	default:
	            	break;
            }
        }
        return creadopor;
    }

    /**
     * Valor de busqueda de campo creadopor
     * @param creadopor Valor de seteo.
     */
    public void setCreadopor(String creadopor) {
        this.creadopor = creadopor;
    }

    /**
     * Tipo de comparador para la busqueda por campo creadopor
     * @return creadoporComparator.
     */
    public TextComparator getCreadoporComparator() {
        return creadoporComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo creadopor
     * @param creadoporComparator Valor de seteo.
     */
    public void setCreadoporComparator(TextComparator creadoporComparator) {
        this.creadoporComparator = creadoporComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCreadoporIn() {
        return this.creadoporIn;
    }

    /**
     * @param creadopor Valor a agregar.
     */
    public void addCreadoporIn(String creadopor) {
        this.creadoporIn.add(creadopor);
    }

    /**
     * Permite buscar cuando campo creadopor es NULL
     * @return boolean.
     */
    public boolean isCreadoporIsNull() {
        return creadoporIsNull;
    }

    /**
     * Permite buscar cuando campo creadopor es NULL
     * @param creadoporIsNull Valor de seteo.
     */
    public void setCreadoporIsNull(boolean creadoporIsNull) {
        this.creadoporIsNull = creadoporIsNull;
    }

    /**
     * Permite buscar cuando campo creadopor es NOT NULL
     * @return boolean.
     */
    public boolean isCreadoporIsNotNull() {
        return creadoporIsNotNull;
    }

    /**
     * Permite buscar cuando campo creadopor es NOT NULL
     * @param creadoporIsNotNull Valor de seteo.
     */
    public void setCreadoporIsNotNull(boolean creadoporIsNotNull) {
        this.creadoporIsNotNull = creadoporIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblUsuarios() {
        return innerJoinTblUsuarios;
    }

    /**
     * @param innerJoinTblUsuarios Valor de seteo.
     */
    public void setInnerJoinTblUsuarios(boolean innerJoinTblUsuarios) {
        this.innerJoinTblUsuarios = innerJoinTblUsuarios;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblUsuarios() {
        return leftJoinTblUsuarios;
    }

    /**
     * @param leftJoinTblUsuarios Valor de seteo.
     */
    public void setLeftJoinTblUsuarios(boolean leftJoinTblUsuarios) {
        this.leftJoinTblUsuarios = leftJoinTblUsuarios;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getUsuarioaplicacionid() != null) {
            criteria.add(Restrictions.eq(USUARIOAPLICACIONID, getUsuarioaplicacionid()));
        }

        if (getUsuarioaplicacionidIn().size() > 0) {
            criteria.add(Restrictions.in(USUARIOAPLICACIONID, getUsuarioaplicacionidIn()));
        }

        // Campo entidad padre tblUsuarios
        
        // Si se hace join fetch con el padre
        Criteria tblUsuariosCriteria = null;
        if (isInnerJoinTblUsuarios()) {
            tblUsuariosCriteria = criteria.createCriteria(TBLUSUARIOS, "a_" + TBLUSUARIOS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblUsuarios()) {
            tblUsuariosCriteria = criteria.createCriteria(TBLUSUARIOS, "a_" + TBLUSUARIOS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblUsuarios() != null) {
            if (getTblUsuarios().getUsuarioid() == null) {
                if (tblUsuariosCriteria == null) {
                    tblUsuariosCriteria = criteria.createCriteria(TBLUSUARIOS, "a_" + TBLUSUARIOS);
                }
                getTblUsuarios().addCriteria(tblUsuariosCriteria, useOrder);
            } else {
                TblUsuarios parent = new TblUsuarios();
                parent.setUsuarioid(getTblUsuarios().getUsuarioid());
                criteria.add(Restrictions.eq(TBLUSUARIOS, parent));
            }
        }

        if (getTblUsuariosIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLUSUARIOS, getTblUsuariosIdIn()));
        }

        if (isTblUsuariosIsNull()) {
            criteria.add(Restrictions.isNull(TBLUSUARIOS));
        }

        if (isTblUsuariosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLUSUARIOS));
        }

        if (getAplicacionid() != null) {
            criteria.add(Restrictions.eq(APLICACIONID, getAplicacionid()));
        }

        if (getAplicacionidIn().size() > 0) {
            criteria.add(Restrictions.in(APLICACIONID, getAplicacionidIn()));
        }

        if (isAplicacionidIsNull()) {
            criteria.add(Restrictions.isNull(APLICACIONID));
        }

        if (isAplicacionidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(APLICACIONID));
        }

        if (getModo() != null) {
            criteria.add(Restrictions.eq(MODO, getModo()));
        }

        if (getModoIn().size() > 0) {
            criteria.add(Restrictions.in(MODO, getModoIn()));
        }

        if (isModoIsNull()) {
            criteria.add(Restrictions.isNull(MODO));
        }

        if (isModoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MODO));
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

        if (getCreadopor() != null) {
            if (getCreadoporComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CREADOPOR, getCreadopor()));
            } 
            else if (getCreadoporComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CREADOPOR, getCreadopor()));
            }
            else if (getCreadoporComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CREADOPOR, getCreadopor())));
            }
            else {
                criteria.add(Restrictions.like(CREADOPOR, getCreadopor()));
            }
        }

        if (getCreadoporIn().size() > 0) {
            criteria.add(Restrictions.in(CREADOPOR, getCreadoporIn()));
        }

        if (isCreadoporIsNull()) {
            criteria.add(Restrictions.isNull(CREADOPOR));
        }

        if (isCreadoporIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CREADOPOR));
        }
        
        if(null != maxResultados && maxResultados > 0){
			criteria.setMaxResults(maxResultados);
		}
		
		if (null != primerResultado && primerResultado > 0){
			criteria.setFirstResult(primerResultado);
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
    
    /**
   	 * @return the maxResultados
   	 */
   	public Integer getMaxResultados() {
   		return maxResultados;
   	}

   	/**
   	 * @param maxResultados the maxResultados to set
   	 */
   	public void setMaxResultados(Integer maxResultados) {
   		this.maxResultados = maxResultados;
   	}

   	/**
   	 * @return the primerResultado
   	 */
   	public Integer getPrimerResultado() {
   		return primerResultado;
   	}

   	/**
   	 * @param primerResultado the primerResultado to set
   	 */
   	public void setPrimerResultado(Integer primerResultado) {
   		this.primerResultado = primerResultado;
   	}
}
 
