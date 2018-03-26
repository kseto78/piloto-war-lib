/*
 *
 * archivo: ViewMisimQuery.java
 *
 * Proyecto: Administracion SIM
 *
 * Generated file! Do not modify.
 *
 * Developed by:
 *     everis S.A.
 *     www.everis.com
 */

package es.minhap.misim.bus.query;

import java.text.Normalizer;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

import es.minhap.common.util.DateUtil;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.misim.bus.model.ViewMisim;

/**
 * Clase con criterios de busqueda para la entidad ViewMisim
 */
public class ViewMisimQuery extends AbstractHibernateQueryEntity<ViewMisim> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDAUDITORIA = "idAuditoria";
    public static final String FECHACREACION = "fechaCreacion";
    public static final String IDLOTE = "idLote";
    public static final String PROVEEDORPRODUCTO = "proveedorProducto";
    public static final String IDPETICION = "idPeticion";


    /**
     * Valor de busqueda de campo idAuditoria
     */
    private Long idAuditoria;

    /**
     * Lista de valores del campo idAuditoria para busquedas tipo IN
     */
    private List<Long> idAuditoriaIn = new ArrayList<Long>(0);

    /**
     * Valor inferior de rango de busqueda de fecha fechaCreacion
     */
    private Date fechaCreacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaCreacion
     */
    private Date fechaCreacionMax;

    /**
     * Permite buscar cuando campo fechaCreacion es NULL
     */
    private boolean fechaCreacionIsNull = false;

    /**
     * Permite buscar cuando campo fechaCreacion es NOT NULL
     */
    private boolean fechaCreacionIsNotNull = false;

    /**
     * Valor de busqueda de campo idLote
     */
    private Long idLote;

    /**
     * Lista de valores del campo idLote para busquedas tipo IN
     */
    private List<Long> idLoteIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo idLote es NULL
     */
    private boolean idLoteIsNull = false;

    /**
     * Permite buscar cuando campo idLote es NOT NULL
     */
    private boolean idLoteIsNotNull = false;

    /**
     * Valor de busqueda de campo proveedorProducto
     */
    private String proveedorProducto;

    /**
     * Tipo de comparador para la busqueda por campo proveedorProducto
     */
    private TextComparator proveedorProductoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo proveedorProducto para busquedas tipo IN
     */
    private List<String> proveedorProductoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo proveedorProducto es NULL
     */
    private boolean proveedorProductoIsNull = false;

    /**
     * Permite buscar cuando campo proveedorProducto es NOT NULL
     */
    private boolean proveedorProductoIsNotNull = false;

    /**
     * Valor de busqueda de campo idPeticion
     */
    private Long idPeticion;

    /**
     * Lista de valores del campo idPeticion para busquedas tipo IN
     */
    private List<Long> idPeticionIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo idPeticion es NULL
     */
    private boolean idPeticionIsNull = false;

    /**
     * Permite buscar cuando campo idPeticion es NOT NULL
     */
    private boolean idPeticionIsNotNull = false;
    
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
    public ViewMisimQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ViewMisimQuery(Long idAuditoria) {
        setIdAuditoria(idAuditoria);
    }

    /**
     * Valor de busqueda de campo idAuditoria
     * @return Long.
     */
    public Long getIdAuditoria() {
        return idAuditoria;
    }

    /**
     * Valor de busqueda de campo idAuditoria
     * @param idAuditoria Valor de seteo.
     */
    public void setIdAuditoria(Long idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdAuditoriaIn() {
        return this.idAuditoriaIn;
    }

    /**
     * @param idAuditoria Valor a agregar.
     */
    public void addIdAuditoriaIn(Long idAuditoria) {
        this.idAuditoriaIn.add(idAuditoria);
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaCreacion
     * @return ${field.getName()}Min.
     */
    public Date getFechaCreacionMin() {
        if (fechaCreacionMin != null) {
            return DateUtil.toDayBegin(fechaCreacionMin);
        }
        return fechaCreacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaCreacion
     * @param fechaCreacionMin Valor de seteo.
     */
    public void setFechaCreacionMin(Date fechaCreacionMin) {
        this.fechaCreacionMin = fechaCreacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaCreacion
     * @return fechaCreacionMax.
     */
    public Date getFechaCreacionMax() {
        if (fechaCreacionMax != null) {
            return DateUtil.toDayEnd(fechaCreacionMax);
        }
        return fechaCreacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaCreacion
     * @param fechaCreacionMax Valor de seteo.
     */
    public void setFechaCreacionMax(Date fechaCreacionMax) {
        this.fechaCreacionMax = fechaCreacionMax;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NULL
     * @return boolean.
     */
    public boolean isFechaCreacionIsNull() {
        return fechaCreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NULL
     * @param fechaCreacionIsNull Valor de seteo.
     */
    public void setFechaCreacionIsNull(boolean fechaCreacionIsNull) {
        this.fechaCreacionIsNull = fechaCreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaCreacionIsNotNull() {
        return fechaCreacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NOT NULL
     * @param fechaCreacionIsNotNull Valor de seteo.
     */
    public void setFechaCreacionIsNotNull(boolean fechaCreacionIsNotNull) {
        this.fechaCreacionIsNotNull = fechaCreacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo idLote
     * @return Long.
     */
    public Long getIdLote() {
        return idLote;
    }

    /**
     * Valor de busqueda de campo idLote
     * @param idLote Valor de seteo.
     */
    public void setIdLote(Long idLote) {
        this.idLote = idLote;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdLoteIn() {
        return this.idLoteIn;
    }

    /**
     * @param idLote Valor a agregar.
     */
    public void addIdLoteIn(Long idLote) {
        this.idLoteIn.add(idLote);
    }

    /**
     * Permite buscar cuando campo idLote es NULL
     * @return boolean.
     */
    public boolean isIdLoteIsNull() {
        return idLoteIsNull;
    }

    /**
     * Permite buscar cuando campo idLote es NULL
     * @param idLoteIsNull Valor de seteo.
     */
    public void setIdLoteIsNull(boolean idLoteIsNull) {
        this.idLoteIsNull = idLoteIsNull;
    }

    /**
     * Permite buscar cuando campo idLote es NOT NULL
     * @return boolean.
     */
    public boolean isIdLoteIsNotNull() {
        return idLoteIsNotNull;
    }

    /**
     * Permite buscar cuando campo idLote es NOT NULL
     * @param idLoteIsNotNull Valor de seteo.
     */
    public void setIdLoteIsNotNull(boolean idLoteIsNotNull) {
        this.idLoteIsNotNull = idLoteIsNotNull;
    }

    /**
     * Valor de busqueda de campo proveedorProducto
     * @return String.
     */
    public String getProveedorProducto() {
        if (proveedorProducto != null) {
            switch (proveedorProductoComparator) {
	            case STARTS_WITH:
	                return proveedorProducto + "%";
	            case CONTAINS:
	                return "%" + proveedorProducto + "%";
	            case ENDS_WITH:
	                return "%" + proveedorProducto;
	            case EQUALS:
                	return proveedorProducto;
              	default:
	            	break;
            }
        }
        return proveedorProducto;
    }

    /**
     * Valor de busqueda de campo proveedorProducto
     * @param proveedorProducto Valor de seteo.
     */
    public void setProveedorProducto(String proveedorProducto) {
        this.proveedorProducto = proveedorProducto;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorProducto
     * @return proveedorProductoComparator.
     */
    public TextComparator getProveedorProductoComparator() {
        return proveedorProductoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorProducto
     * @param proveedorProductoComparator Valor de seteo.
     */
    public void setProveedorProductoComparator(TextComparator proveedorProductoComparator) {
        this.proveedorProductoComparator = proveedorProductoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getProveedorProductoIn() {
        return this.proveedorProductoIn;
    }

    /**
     * @param proveedorProducto Valor a agregar.
     */
    public void addProveedorProductoIn(String proveedorProducto) {
        this.proveedorProductoIn.add(proveedorProducto);
    }

    /**
     * Permite buscar cuando campo proveedorProducto es NULL
     * @return boolean.
     */
    public boolean isProveedorProductoIsNull() {
        return proveedorProductoIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorProducto es NULL
     * @param proveedorProductoIsNull Valor de seteo.
     */
    public void setProveedorProductoIsNull(boolean proveedorProductoIsNull) {
        this.proveedorProductoIsNull = proveedorProductoIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorProducto es NOT NULL
     * @return boolean.
     */
    public boolean isProveedorProductoIsNotNull() {
        return proveedorProductoIsNotNull;
    }

    /**
     * Permite buscar cuando campo proveedorProducto es NOT NULL
     * @param proveedorProductoIsNotNull Valor de seteo.
     */
    public void setProveedorProductoIsNotNull(boolean proveedorProductoIsNotNull) {
        this.proveedorProductoIsNotNull = proveedorProductoIsNotNull;
    }

    /**
     * Valor de busqueda de campo idPeticion
     * @return Long.
     */
    public Long getIdPeticion() {
        return idPeticion;
    }

    /**
     * Valor de busqueda de campo idPeticion
     * @param idPeticion Valor de seteo.
     */
    public void setIdPeticion(Long idPeticion) {
        this.idPeticion = idPeticion;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdPeticionIn() {
        return this.idPeticionIn;
    }

    /**
     * @param idPeticion Valor a agregar.
     */
    public void addIdPeticionIn(Long idPeticion) {
        this.idPeticionIn.add(idPeticion);
    }

    /**
     * Permite buscar cuando campo idPeticion es NULL
     * @return boolean.
     */
    public boolean isIdPeticionIsNull() {
        return idPeticionIsNull;
    }

    /**
     * Permite buscar cuando campo idPeticion es NULL
     * @param idPeticionIsNull Valor de seteo.
     */
    public void setIdPeticionIsNull(boolean idPeticionIsNull) {
        this.idPeticionIsNull = idPeticionIsNull;
    }

    /**
     * Permite buscar cuando campo idPeticion es NOT NULL
     * @return boolean.
     */
    public boolean isIdPeticionIsNotNull() {
        return idPeticionIsNotNull;
    }

    /**
     * Permite buscar cuando campo idPeticion es NOT NULL
     * @param idPeticionIsNotNull Valor de seteo.
     */
    public void setIdPeticionIsNotNull(boolean idPeticionIsNotNull) {
        this.idPeticionIsNotNull = idPeticionIsNotNull;
    }
    
    public Integer getMaxResultados() {
		return maxResultados;
	}

	public void setMaxResultados(Integer maxResultados) {
		this.maxResultados = maxResultados;
	}

	public Integer getPrimerResultado() {
		return primerResultado;
	}

	public void setPrimerResultado(Integer primerResultado) {
		this.primerResultado = primerResultado;
	}

	/**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdAuditoria() != null) {
            criteria.add(Restrictions.eq(IDAUDITORIA, getIdAuditoria()));
        }

        if (getIdAuditoriaIn().size() > 0) {
            criteria.add(Restrictions.in(IDAUDITORIA, getIdAuditoriaIn()));
        }

        if (getFechaCreacionMin() != null) {
            criteria.add(Restrictions.ge(FECHACREACION, getFechaCreacionMin()));
        }

        if (getFechaCreacionMax() != null) {
            criteria.add(Restrictions.le(FECHACREACION, getFechaCreacionMax()));
        }

        if (isFechaCreacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHACREACION));
        }

        if (isFechaCreacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHACREACION));
        }

        if (getIdLote() != null) {
            criteria.add(Restrictions.eq(IDLOTE, getIdLote()));
        }

        if (getIdLoteIn().size() > 0) {
            criteria.add(Restrictions.in(IDLOTE, getIdLoteIn()));
        }

        if (isIdLoteIsNull()) {
            criteria.add(Restrictions.isNull(IDLOTE));
        }

        if (isIdLoteIsNotNull()) {
            criteria.add(Restrictions.isNotNull(IDLOTE));
        }

        if (getProveedorProducto() != null) {
            if (getProveedorProductoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PROVEEDORPRODUCTO, getProveedorProducto()));
            } 
            else if (getProveedorProductoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PROVEEDORPRODUCTO, getProveedorProducto()));
            }
            else if (getProveedorProductoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PROVEEDORPRODUCTO, getProveedorProducto())));
            }
            else {
                criteria.add(Restrictions.like(PROVEEDORPRODUCTO, getProveedorProducto()));
            }
        }

        if (getProveedorProductoIn().size() > 0) {
            criteria.add(Restrictions.in(PROVEEDORPRODUCTO, getProveedorProductoIn()));
        }

        if (isProveedorProductoIsNull()) {
            criteria.add(Restrictions.isNull(PROVEEDORPRODUCTO));
        }

        if (isProveedorProductoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PROVEEDORPRODUCTO));
        }

        if (getIdPeticion() != null) {
            criteria.add(Restrictions.eq(IDPETICION, getIdPeticion()));
        }

        if (getIdPeticionIn().size() > 0) {
            criteria.add(Restrictions.in(IDPETICION, getIdPeticionIn()));
        }

        if (isIdPeticionIsNull()) {
            criteria.add(Restrictions.isNull(IDPETICION));
        }

        if (isIdPeticionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(IDPETICION));
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
}
 
