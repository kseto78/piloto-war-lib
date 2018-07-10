/*
 *
 * archivo: ProductoQuery.java
 *
 * Proyecto: Carpeta Ciudadana
 *
 * Generated file! Do not modify.
 *
 * Developed by:
 *     everis S.A.
 *     www.everis.com
 */

package es.minhap.misim.bus.query;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.misim.bus.model.Producto;

/**
 * Clase con criterios de busqueda para la entidad Producto
 */
public class ProductoQuery extends AbstractHibernateQueryEntity<Producto> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDPRODUCTO = "idProducto";
    public static final String NOMBRE = "nombre";
    public static final String CODIGO = "codigo";


    /**
     * Valor de busqueda de campo idProducto
     */
    private Long idProducto;

    /**
     * Lista de valores del campo idProducto para busquedas tipo IN
     */
    private List<Long> idProductoIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo nombre
     */
    private String nombre;

    /**
     * Tipo de comparador para la busqueda por campo nombre
     */
    private TextComparator nombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombre para busquedas tipo IN
     */
    private List<String> nombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombre es NULL
     */
    private boolean nombreIsNull = false;

    /**
     * Permite buscar cuando campo nombre es NOT NULL
     */
    private boolean nombreIsNotNull = false;

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
     * Constructor default
     */
    public ProductoQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ProductoQuery(Long idProducto) {
        setIdProducto(idProducto);
    }

    /**
     * Valor de busqueda de campo idProducto
     * @return Long.
     */
    public Long getIdProducto() {
        return idProducto;
    }

    /**
     * Valor de busqueda de campo idProducto
     * @param idProducto Valor de seteo.
     */
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdProductoIn() {
        return this.idProductoIn;
    }

    /**
     * @param idProducto Valor a agregar.
     */
    public void addIdProductoIn(Long idProducto) {
        this.idProductoIn.add(idProducto);
    }

    /**
     * Valor de busqueda de campo nombre
     * @return String.
     */
    public String getNombre() {
        if (nombre != null) {
            switch (nombreComparator) {
	            case STARTS_WITH:
	                return nombre + "%";
	            case CONTAINS:
	                return "%" + nombre + "%";
	            case ENDS_WITH:
	                return "%" + nombre;
	            case EQUALS:
                	return nombre;
            }
        }
        return nombre;
    }

    /**
     * Valor de busqueda de campo nombre
     * @param nombre Valor de seteo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombre
     * @return nombreComparator.
     */
    public TextComparator getNombreComparator() {
        return nombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombre
     * @param nombreComparator Valor de seteo.
     */
    public void setNombreComparator(TextComparator nombreComparator) {
        this.nombreComparator = nombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreIn() {
        return this.nombreIn;
    }

    /**
     * @param nombre Valor a agregar.
     */
    public void addNombreIn(String nombre) {
        this.nombreIn.add(nombre);
    }

    /**
     * Permite buscar cuando campo nombre es NULL
     * @return boolean.
     */
    public boolean isNombreIsNull() {
        return nombreIsNull;
    }

    /**
     * Permite buscar cuando campo nombre es NULL
     * @param nombreIsNull Valor de seteo.
     */
    public void setNombreIsNull(boolean nombreIsNull) {
        this.nombreIsNull = nombreIsNull;
    }

    /**
     * Permite buscar cuando campo nombre es NOT NULL
     * @return boolean.
     */
    public boolean isNombreIsNotNull() {
        return nombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombre es NOT NULL
     * @param nombreIsNotNull Valor de seteo.
     */
    public void setNombreIsNotNull(boolean nombreIsNotNull) {
        this.nombreIsNotNull = nombreIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdProducto() != null) {
            criteria.add(Restrictions.eq(IDPRODUCTO, getIdProducto()));
        }

        if (getIdProductoIn().size() > 0) {
            criteria.add(Restrictions.in(IDPRODUCTO, getIdProductoIn()));
        }

        if (getNombre() != null) {
            if (getNombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRE, getNombre()));
            } 
            else if (getNombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRE, getNombre()));
            }
            else {
                criteria.add(Restrictions.like(NOMBRE, getNombre()));
            }
        }

        if (getNombreIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBRE, getNombreIn()));
        }

        if (isNombreIsNull()) {
            criteria.add(Restrictions.isNull(NOMBRE));
        }

        if (isNombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBRE));
        }

        if (getCodigo() != null) {
            if (getCodigoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODIGO, getCodigo()));
            } 
            else if (getCodigoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODIGO, getCodigo()));
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
    
}
 
