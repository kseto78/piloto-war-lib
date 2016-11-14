/*
 *
 * archivo: PeticionQuery.java
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
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.model.Estado;
import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Proveedor;

/**
 * Clase con criterios de busqueda para la entidad Peticion
 */
public class PeticionQuery extends AbstractHibernateQueryEntity<Peticion> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDPETICION = "idPeticion";
    public static final String PROVEEDOR = "proveedor";
    public static final String ESTADO = "estado";
    public static final String APLICACION = "aplicacion";
    public static final String PRODUCTO = "producto";
    public static final String IDMENSAJE = "idMensaje";
    public static final String MENSAJEPETICION = "mensajePeticion";
    public static final String MENSAJERESPUESTA = "mensajeRespuesta";


    /**
     * Valor de busqueda de campo idPeticion
     */
    private Long idPeticion;

    /**
     * Lista de valores del campo idPeticion para busquedas tipo IN
     */
    private List<Long> idPeticionIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo proveedor
     */
    private ProveedorQuery proveedor;

    /**
     * Lista de valores del ID del campo proveedor para busquedas tipo IN
     * Solo se consideran los ID dentro de los Proveedor
     */
    private List<Proveedor> proveedorIdIn = new ArrayList<Proveedor>(0);

    /**
     * Permite buscar cuando campo proveedor es NULL
     */
    private boolean proveedorIsNull = false;

    /**
     * Permite buscar cuando campo proveedor es NOT NULL
     */
    private boolean proveedorIsNotNull = false;

    /**
     * Valor de busqueda de campo estado
     */
    private EstadoQuery estado;

    /**
     * Lista de valores del ID del campo estado para busquedas tipo IN
     * Solo se consideran los ID dentro de los Estado
     */
    private List<Estado> estadoIdIn = new ArrayList<Estado>(0);

    /**
     * Permite buscar cuando campo estado es NULL
     */
    private boolean estadoIsNull = false;

    /**
     * Permite buscar cuando campo estado es NOT NULL
     */
    private boolean estadoIsNotNull = false;

    /**
     * Valor de busqueda de campo aplicacion
     */
    private AplicacionQuery aplicacion;

    /**
     * Lista de valores del ID del campo aplicacion para busquedas tipo IN
     * Solo se consideran los ID dentro de los Aplicacion
     */
    private List<Aplicacion> aplicacionIdIn = new ArrayList<Aplicacion>(0);

    /**
     * Permite buscar cuando campo aplicacion es NULL
     */
    private boolean aplicacionIsNull = false;

    /**
     * Permite buscar cuando campo aplicacion es NOT NULL
     */
    private boolean aplicacionIsNotNull = false;

    /**
     * Valor de busqueda de campo producto
     */
    private ProductoQuery producto;

    /**
     * Lista de valores del ID del campo producto para busquedas tipo IN
     * Solo se consideran los ID dentro de los Producto
     */
    private List<Producto> productoIdIn = new ArrayList<Producto>(0);

    /**
     * Permite buscar cuando campo producto es NULL
     */
    private boolean productoIsNull = false;

    /**
     * Permite buscar cuando campo producto es NOT NULL
     */
    private boolean productoIsNotNull = false;

    /**
     * Valor de busqueda de campo idMensaje
     */
    private Long idMensaje;

    /**
     * Lista de valores del campo idMensaje para busquedas tipo IN
     */
    private List<Long> idMensajeIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo idMensaje es NULL
     */
    private boolean idMensajeIsNull = false;

    /**
     * Permite buscar cuando campo idMensaje es NOT NULL
     */
    private boolean idMensajeIsNotNull = false;

    /**
     * Valor de busqueda de campo mensajePeticion
     */
    private String mensajePeticion;

    /**
     * Tipo de comparador para la busqueda por campo mensajePeticion
     */
    private TextComparator mensajePeticionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo mensajePeticion para busquedas tipo IN
     */
    private List<String> mensajePeticionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo mensajePeticion es NULL
     */
    private boolean mensajePeticionIsNull = false;

    /**
     * Permite buscar cuando campo mensajePeticion es NOT NULL
     */
    private boolean mensajePeticionIsNotNull = false;

    /**
     * Valor de busqueda de campo mensajeRespuesta
     */
    private String mensajeRespuesta;

    /**
     * Tipo de comparador para la busqueda por campo mensajeRespuesta
     */
    private TextComparator mensajeRespuestaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo mensajeRespuesta para busquedas tipo IN
     */
    private List<String> mensajeRespuestaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo mensajeRespuesta es NULL
     */
    private boolean mensajeRespuestaIsNull = false;

    /**
     * Permite buscar cuando campo mensajeRespuesta es NOT NULL
     */
    private boolean mensajeRespuestaIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre proveedor
     */
    private boolean innerJoinProveedor = false;

    /**
     * Indica si en la consulta se hace un left join con el padre proveedor
     */
    private boolean leftJoinProveedor = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre estado
     */
    private boolean innerJoinEstado = false;

    /**
     * Indica si en la consulta se hace un left join con el padre estado
     */
    private boolean leftJoinEstado = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre aplicacion
     */
    private boolean innerJoinAplicacion = false;

    /**
     * Indica si en la consulta se hace un left join con el padre aplicacion
     */
    private boolean leftJoinAplicacion = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre producto
     */
    private boolean innerJoinProducto = false;

    /**
     * Indica si en la consulta se hace un left join con el padre producto
     */
    private boolean leftJoinProducto = false;

    /**
     * Constructor default
     */
    public PeticionQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public PeticionQuery(Long idPeticion) {
        setIdPeticion(idPeticion);
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
     * Valor de busqueda de campo proveedor
     * @return Proveedor.
     */
    public ProveedorQuery getProveedor() {
        return proveedor;
    }

    /**
     * Valor de busqueda de campo proveedor
     * @param proveedor Valor de seteo.
     */
    public void setProveedor(ProveedorQuery proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * @return List<Proveedor>.
     */
    public List<Proveedor> getProveedorIdIn() {
        return this.proveedorIdIn;
    }

    /**
     * @param proveedor Valor a agregar.
     */
    public void addProveedorIdIn(Proveedor proveedor) {
        this.proveedorIdIn.add(proveedor);
    }

    /**
     * Permite buscar cuando campo proveedor es NULL
     * @return boolean.
     */
    public boolean isProveedorIsNull() {
        return proveedorIsNull;
    }

    /**
     * Permite buscar cuando campo proveedor es NULL
     * @param proveedorIsNull Valor de seteo.
     */
    public void setProveedorIsNull(boolean proveedorIsNull) {
        this.proveedorIsNull = proveedorIsNull;
    }

    /**
     * Permite buscar cuando campo proveedor es NOT NULL
     * @return boolean.
     */
    public boolean isProveedorIsNotNull() {
        return proveedorIsNotNull;
    }

    /**
     * Permite buscar cuando campo proveedor es NOT NULL
     * @param proveedorIsNotNull Valor de seteo.
     */
    public void setProveedorIsNotNull(boolean proveedorIsNotNull) {
        this.proveedorIsNotNull = proveedorIsNotNull;
    }

    /**
     * Valor de busqueda de campo estado
     * @return Estado.
     */
    public EstadoQuery getEstado() {
        return estado;
    }

    /**
     * Valor de busqueda de campo estado
     * @param estado Valor de seteo.
     */
    public void setEstado(EstadoQuery estado) {
        this.estado = estado;
    }

    /**
     * @return List<Estado>.
     */
    public List<Estado> getEstadoIdIn() {
        return this.estadoIdIn;
    }

    /**
     * @param estado Valor a agregar.
     */
    public void addEstadoIdIn(Estado estado) {
        this.estadoIdIn.add(estado);
    }

    /**
     * Permite buscar cuando campo estado es NULL
     * @return boolean.
     */
    public boolean isEstadoIsNull() {
        return estadoIsNull;
    }

    /**
     * Permite buscar cuando campo estado es NULL
     * @param estadoIsNull Valor de seteo.
     */
    public void setEstadoIsNull(boolean estadoIsNull) {
        this.estadoIsNull = estadoIsNull;
    }

    /**
     * Permite buscar cuando campo estado es NOT NULL
     * @return boolean.
     */
    public boolean isEstadoIsNotNull() {
        return estadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo estado es NOT NULL
     * @param estadoIsNotNull Valor de seteo.
     */
    public void setEstadoIsNotNull(boolean estadoIsNotNull) {
        this.estadoIsNotNull = estadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo aplicacion
     * @return Aplicacion.
     */
    public AplicacionQuery getAplicacion() {
        return aplicacion;
    }

    /**
     * Valor de busqueda de campo aplicacion
     * @param aplicacion Valor de seteo.
     */
    public void setAplicacion(AplicacionQuery aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * @return List<Aplicacion>.
     */
    public List<Aplicacion> getAplicacionIdIn() {
        return this.aplicacionIdIn;
    }

    /**
     * @param aplicacion Valor a agregar.
     */
    public void addAplicacionIdIn(Aplicacion aplicacion) {
        this.aplicacionIdIn.add(aplicacion);
    }

    /**
     * Permite buscar cuando campo aplicacion es NULL
     * @return boolean.
     */
    public boolean isAplicacionIsNull() {
        return aplicacionIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacion es NULL
     * @param aplicacionIsNull Valor de seteo.
     */
    public void setAplicacionIsNull(boolean aplicacionIsNull) {
        this.aplicacionIsNull = aplicacionIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacion es NOT NULL
     * @return boolean.
     */
    public boolean isAplicacionIsNotNull() {
        return aplicacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo aplicacion es NOT NULL
     * @param aplicacionIsNotNull Valor de seteo.
     */
    public void setAplicacionIsNotNull(boolean aplicacionIsNotNull) {
        this.aplicacionIsNotNull = aplicacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo producto
     * @return Producto.
     */
    public ProductoQuery getProducto() {
        return producto;
    }

    /**
     * Valor de busqueda de campo producto
     * @param producto Valor de seteo.
     */
    public void setProducto(ProductoQuery producto) {
        this.producto = producto;
    }

    /**
     * @return List<Producto>.
     */
    public List<Producto> getProductoIdIn() {
        return this.productoIdIn;
    }

    /**
     * @param producto Valor a agregar.
     */
    public void addProductoIdIn(Producto producto) {
        this.productoIdIn.add(producto);
    }

    /**
     * Permite buscar cuando campo producto es NULL
     * @return boolean.
     */
    public boolean isProductoIsNull() {
        return productoIsNull;
    }

    /**
     * Permite buscar cuando campo producto es NULL
     * @param productoIsNull Valor de seteo.
     */
    public void setProductoIsNull(boolean productoIsNull) {
        this.productoIsNull = productoIsNull;
    }

    /**
     * Permite buscar cuando campo producto es NOT NULL
     * @return boolean.
     */
    public boolean isProductoIsNotNull() {
        return productoIsNotNull;
    }

    /**
     * Permite buscar cuando campo producto es NOT NULL
     * @param productoIsNotNull Valor de seteo.
     */
    public void setProductoIsNotNull(boolean productoIsNotNull) {
        this.productoIsNotNull = productoIsNotNull;
    }

    /**
     * Valor de busqueda de campo idMensaje
     * @return Long.
     */
    public Long getIdMensaje() {
        return idMensaje;
    }

    /**
     * Valor de busqueda de campo idMensaje
     * @param idMensaje Valor de seteo.
     */
    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdMensajeIn() {
        return this.idMensajeIn;
    }

    /**
     * @param idMensaje Valor a agregar.
     */
    public void addIdMensajeIn(Long idMensaje) {
        this.idMensajeIn.add(idMensaje);
    }

    /**
     * Permite buscar cuando campo idMensaje es NULL
     * @return boolean.
     */
    public boolean isIdMensajeIsNull() {
        return idMensajeIsNull;
    }

    /**
     * Permite buscar cuando campo idMensaje es NULL
     * @param idMensajeIsNull Valor de seteo.
     */
    public void setIdMensajeIsNull(boolean idMensajeIsNull) {
        this.idMensajeIsNull = idMensajeIsNull;
    }

    /**
     * Permite buscar cuando campo idMensaje es NOT NULL
     * @return boolean.
     */
    public boolean isIdMensajeIsNotNull() {
        return idMensajeIsNotNull;
    }

    /**
     * Permite buscar cuando campo idMensaje es NOT NULL
     * @param idMensajeIsNotNull Valor de seteo.
     */
    public void setIdMensajeIsNotNull(boolean idMensajeIsNotNull) {
        this.idMensajeIsNotNull = idMensajeIsNotNull;
    }

    /**
     * Valor de busqueda de campo mensajePeticion
     * @return String.
     */
    public String getMensajePeticion() {
        if (mensajePeticion != null) {
            switch (mensajePeticionComparator) {
	            case STARTS_WITH:
	                return mensajePeticion + "%";
	            case CONTAINS:
	                return "%" + mensajePeticion + "%";
	            case ENDS_WITH:
	                return "%" + mensajePeticion;
	            case EQUALS:
                	return mensajePeticion;
            }
        }
        return mensajePeticion;
    }

    /**
     * Valor de busqueda de campo mensajePeticion
     * @param mensajePeticion Valor de seteo.
     */
    public void setMensajePeticion(String mensajePeticion) {
        this.mensajePeticion = mensajePeticion;
    }

    /**
     * Tipo de comparador para la busqueda por campo mensajePeticion
     * @return mensajePeticionComparator.
     */
    public TextComparator getMensajePeticionComparator() {
        return mensajePeticionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo mensajePeticion
     * @param mensajePeticionComparator Valor de seteo.
     */
    public void setMensajePeticionComparator(TextComparator mensajePeticionComparator) {
        this.mensajePeticionComparator = mensajePeticionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMensajePeticionIn() {
        return this.mensajePeticionIn;
    }

    /**
     * @param mensajePeticion Valor a agregar.
     */
    public void addMensajePeticionIn(String mensajePeticion) {
        this.mensajePeticionIn.add(mensajePeticion);
    }

    /**
     * Permite buscar cuando campo mensajePeticion es NULL
     * @return boolean.
     */
    public boolean isMensajePeticionIsNull() {
        return mensajePeticionIsNull;
    }

    /**
     * Permite buscar cuando campo mensajePeticion es NULL
     * @param mensajePeticionIsNull Valor de seteo.
     */
    public void setMensajePeticionIsNull(boolean mensajePeticionIsNull) {
        this.mensajePeticionIsNull = mensajePeticionIsNull;
    }

    /**
     * Permite buscar cuando campo mensajePeticion es NOT NULL
     * @return boolean.
     */
    public boolean isMensajePeticionIsNotNull() {
        return mensajePeticionIsNotNull;
    }

    /**
     * Permite buscar cuando campo mensajePeticion es NOT NULL
     * @param mensajePeticionIsNotNull Valor de seteo.
     */
    public void setMensajePeticionIsNotNull(boolean mensajePeticionIsNotNull) {
        this.mensajePeticionIsNotNull = mensajePeticionIsNotNull;
    }

    /**
     * Valor de busqueda de campo mensajeRespuesta
     * @return String.
     */
    public String getMensajeRespuesta() {
        if (mensajeRespuesta != null) {
            switch (mensajeRespuestaComparator) {
	            case STARTS_WITH:
	                return mensajeRespuesta + "%";
	            case CONTAINS:
	                return "%" + mensajeRespuesta + "%";
	            case ENDS_WITH:
	                return "%" + mensajeRespuesta;
	            case EQUALS:
                	return mensajeRespuesta;
            }
        }
        return mensajeRespuesta;
    }

    /**
     * Valor de busqueda de campo mensajeRespuesta
     * @param mensajeRespuesta Valor de seteo.
     */
    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    /**
     * Tipo de comparador para la busqueda por campo mensajeRespuesta
     * @return mensajeRespuestaComparator.
     */
    public TextComparator getMensajeRespuestaComparator() {
        return mensajeRespuestaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo mensajeRespuesta
     * @param mensajeRespuestaComparator Valor de seteo.
     */
    public void setMensajeRespuestaComparator(TextComparator mensajeRespuestaComparator) {
        this.mensajeRespuestaComparator = mensajeRespuestaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMensajeRespuestaIn() {
        return this.mensajeRespuestaIn;
    }

    /**
     * @param mensajeRespuesta Valor a agregar.
     */
    public void addMensajeRespuestaIn(String mensajeRespuesta) {
        this.mensajeRespuestaIn.add(mensajeRespuesta);
    }

    /**
     * Permite buscar cuando campo mensajeRespuesta es NULL
     * @return boolean.
     */
    public boolean isMensajeRespuestaIsNull() {
        return mensajeRespuestaIsNull;
    }

    /**
     * Permite buscar cuando campo mensajeRespuesta es NULL
     * @param mensajeRespuestaIsNull Valor de seteo.
     */
    public void setMensajeRespuestaIsNull(boolean mensajeRespuestaIsNull) {
        this.mensajeRespuestaIsNull = mensajeRespuestaIsNull;
    }

    /**
     * Permite buscar cuando campo mensajeRespuesta es NOT NULL
     * @return boolean.
     */
    public boolean isMensajeRespuestaIsNotNull() {
        return mensajeRespuestaIsNotNull;
    }

    /**
     * Permite buscar cuando campo mensajeRespuesta es NOT NULL
     * @param mensajeRespuestaIsNotNull Valor de seteo.
     */
    public void setMensajeRespuestaIsNotNull(boolean mensajeRespuestaIsNotNull) {
        this.mensajeRespuestaIsNotNull = mensajeRespuestaIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinProveedor() {
        return innerJoinProveedor;
    }

    /**
     * @param innerJoinProveedor Valor de seteo.
     */
    public void setInnerJoinProveedor(boolean innerJoinProveedor) {
        this.innerJoinProveedor = innerJoinProveedor;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinProveedor() {
        return leftJoinProveedor;
    }

    /**
     * @param leftJoinProveedor Valor de seteo.
     */
    public void setLeftJoinProveedor(boolean leftJoinProveedor) {
        this.leftJoinProveedor = leftJoinProveedor;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinEstado() {
        return innerJoinEstado;
    }

    /**
     * @param innerJoinEstado Valor de seteo.
     */
    public void setInnerJoinEstado(boolean innerJoinEstado) {
        this.innerJoinEstado = innerJoinEstado;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinEstado() {
        return leftJoinEstado;
    }

    /**
     * @param leftJoinEstado Valor de seteo.
     */
    public void setLeftJoinEstado(boolean leftJoinEstado) {
        this.leftJoinEstado = leftJoinEstado;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinAplicacion() {
        return innerJoinAplicacion;
    }

    /**
     * @param innerJoinAplicacion Valor de seteo.
     */
    public void setInnerJoinAplicacion(boolean innerJoinAplicacion) {
        this.innerJoinAplicacion = innerJoinAplicacion;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinAplicacion() {
        return leftJoinAplicacion;
    }

    /**
     * @param leftJoinAplicacion Valor de seteo.
     */
    public void setLeftJoinAplicacion(boolean leftJoinAplicacion) {
        this.leftJoinAplicacion = leftJoinAplicacion;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinProducto() {
        return innerJoinProducto;
    }

    /**
     * @param innerJoinProducto Valor de seteo.
     */
    public void setInnerJoinProducto(boolean innerJoinProducto) {
        this.innerJoinProducto = innerJoinProducto;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinProducto() {
        return leftJoinProducto;
    }

    /**
     * @param leftJoinProducto Valor de seteo.
     */
    public void setLeftJoinProducto(boolean leftJoinProducto) {
        this.leftJoinProducto = leftJoinProducto;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdPeticion() != null) {
            criteria.add(Restrictions.eq(IDPETICION, getIdPeticion()));
        }

        if (getIdPeticionIn().size() > 0) {
            criteria.add(Restrictions.in(IDPETICION, getIdPeticionIn()));
        }

        // Campo entidad padre proveedor
        
        // Si se hace join fetch con el padre
        Criteria proveedorCriteria = null;
        if (isInnerJoinProveedor()) {
            proveedorCriteria = criteria.createCriteria(PROVEEDOR, "a_" + PROVEEDOR, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinProveedor()) {
            proveedorCriteria = criteria.createCriteria(PROVEEDOR, "a_" + PROVEEDOR, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getProveedor() != null) {
            if (getProveedor().getIdProveedor() == null) {
                if (proveedorCriteria == null) {
                    proveedorCriteria = criteria.createCriteria(PROVEEDOR, "a_" + PROVEEDOR);
                }
                getProveedor().addCriteria(proveedorCriteria, useOrder);
            } else {
                Proveedor parent = new Proveedor();
                parent.setIdProveedor(getProveedor().getIdProveedor());
                criteria.add(Restrictions.eq(PROVEEDOR, parent));
            }
        }

        if (getProveedorIdIn().size() > 0) {
            criteria.add(Restrictions.in(PROVEEDOR, getProveedorIdIn()));
        }

        if (isProveedorIsNull()) {
            criteria.add(Restrictions.isNull(PROVEEDOR));
        }

        if (isProveedorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PROVEEDOR));
        }

        // Campo entidad padre estado
        
        // Si se hace join fetch con el padre
        Criteria estadoCriteria = null;
        if (isInnerJoinEstado()) {
            estadoCriteria = criteria.createCriteria(ESTADO, "a_" + ESTADO, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinEstado()) {
            estadoCriteria = criteria.createCriteria(ESTADO, "a_" + ESTADO, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getEstado() != null) {
            if (getEstado().getIdEstado() == null) {
                if (estadoCriteria == null) {
                    estadoCriteria = criteria.createCriteria(ESTADO, "a_" + ESTADO);
                }
                getEstado().addCriteria(estadoCriteria, useOrder);
            } else {
                Estado parent = new Estado();
                parent.setIdEstado(getEstado().getIdEstado());
                criteria.add(Restrictions.eq(ESTADO, parent));
            }
        }

        if (getEstadoIdIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADO, getEstadoIdIn()));
        }

        if (isEstadoIsNull()) {
            criteria.add(Restrictions.isNull(ESTADO));
        }

        if (isEstadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADO));
        }

        // Campo entidad padre aplicacion
        
        // Si se hace join fetch con el padre
        Criteria aplicacionCriteria = null;
        if (isInnerJoinAplicacion()) {
            aplicacionCriteria = criteria.createCriteria(APLICACION, "a_" + APLICACION, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinAplicacion()) {
            aplicacionCriteria = criteria.createCriteria(APLICACION, "a_" + APLICACION, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getAplicacion() != null) {
            if (getAplicacion().getIdAplicacion() == null) {
                if (aplicacionCriteria == null) {
                    aplicacionCriteria = criteria.createCriteria(APLICACION, "a_" + APLICACION);
                }
                getAplicacion().addCriteria(aplicacionCriteria, useOrder);
            } else {
                Aplicacion parent = new Aplicacion();
                parent.setIdAplicacion(getAplicacion().getIdAplicacion());
                criteria.add(Restrictions.eq(APLICACION, parent));
            }
        }

        if (getAplicacionIdIn().size() > 0) {
            criteria.add(Restrictions.in(APLICACION, getAplicacionIdIn()));
        }

        if (isAplicacionIsNull()) {
            criteria.add(Restrictions.isNull(APLICACION));
        }

        if (isAplicacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(APLICACION));
        }

        // Campo entidad padre producto
        
        // Si se hace join fetch con el padre
        Criteria productoCriteria = null;
        if (isInnerJoinProducto()) {
            productoCriteria = criteria.createCriteria(PRODUCTO, "a_" + PRODUCTO, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinProducto()) {
            productoCriteria = criteria.createCriteria(PRODUCTO, "a_" + PRODUCTO, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getProducto() != null) {
            if (getProducto().getIdProducto() == null) {
                if (productoCriteria == null) {
                    productoCriteria = criteria.createCriteria(PRODUCTO, "a_" + PRODUCTO);
                }
                getProducto().addCriteria(productoCriteria, useOrder);
            } else {
                Producto parent = new Producto();
                parent.setIdProducto(getProducto().getIdProducto());
                criteria.add(Restrictions.eq(PRODUCTO, parent));
            }
        }

        if (getProductoIdIn().size() > 0) {
            criteria.add(Restrictions.in(PRODUCTO, getProductoIdIn()));
        }

        if (isProductoIsNull()) {
            criteria.add(Restrictions.isNull(PRODUCTO));
        }

        if (isProductoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PRODUCTO));
        }

        if (getIdMensaje() != null) {
            criteria.add(Restrictions.eq(IDMENSAJE, getIdMensaje()));
        }

        if (getIdMensajeIn().size() > 0) {
            criteria.add(Restrictions.in(IDMENSAJE, getIdMensajeIn()));
        }

        if (isIdMensajeIsNull()) {
            criteria.add(Restrictions.isNull(IDMENSAJE));
        }

        if (isIdMensajeIsNotNull()) {
            criteria.add(Restrictions.isNotNull(IDMENSAJE));
        }

        if (getMensajePeticion() != null) {
            if (getMensajePeticionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MENSAJEPETICION, getMensajePeticion()));
            } 
            else if (getMensajePeticionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MENSAJEPETICION, getMensajePeticion()));
            }
            else {
                criteria.add(Restrictions.like(MENSAJEPETICION, getMensajePeticion()));
            }
        }

        if (getMensajePeticionIn().size() > 0) {
            criteria.add(Restrictions.in(MENSAJEPETICION, getMensajePeticionIn()));
        }

        if (isMensajePeticionIsNull()) {
            criteria.add(Restrictions.isNull(MENSAJEPETICION));
        }

        if (isMensajePeticionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MENSAJEPETICION));
        }

        if (getMensajeRespuesta() != null) {
            if (getMensajeRespuestaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MENSAJERESPUESTA, getMensajeRespuesta()));
            } 
            else if (getMensajeRespuestaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MENSAJERESPUESTA, getMensajeRespuesta()));
            }
            else {
                criteria.add(Restrictions.like(MENSAJERESPUESTA, getMensajeRespuesta()));
            }
        }

        if (getMensajeRespuestaIn().size() > 0) {
            criteria.add(Restrictions.in(MENSAJERESPUESTA, getMensajeRespuestaIn()));
        }

        if (isMensajeRespuestaIsNull()) {
            criteria.add(Restrictions.isNull(MENSAJERESPUESTA));
        }

        if (isMensajeRespuestaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MENSAJERESPUESTA));
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
 
