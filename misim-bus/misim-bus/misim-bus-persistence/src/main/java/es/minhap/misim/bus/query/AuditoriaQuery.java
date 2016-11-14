/*
 *
 * archivo: AuditoriaQuery.java
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
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.model.Auditoria;
import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Proveedor;

/**
 * Clase con criterios de busqueda para la entidad Auditoria
 */
public class AuditoriaQuery extends AbstractHibernateQueryEntity<Auditoria> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDAUDITORIA = "idAuditoria";
    public static final String PROVEEDOR = "proveedor";
    public static final String APLICACION = "aplicacion";
    public static final String PRODUCTO = "producto";
    public static final String PETICION = "peticion";
    public static final String IDMENSAJE = "idMensaje";
    public static final String FECHACREACION = "fechaCreacion";
    public static final String FECHAACTUALIZACION = "fechaActualizacion";


    /**
     * Valor de busqueda de campo idAuditoria
     */
    private Long idAuditoria;

    /**
     * Lista de valores del campo idAuditoria para busquedas tipo IN
     */
    private List<Long> idAuditoriaIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo peticion
     */
    private PeticionQuery peticion;

    /**
     * Lista de valores del ID del campo peticion para busquedas tipo IN
     * Solo se consideran los ID dentro de los Peticion
     */
    private List<Peticion> peticionIdIn = new ArrayList<Peticion>(0);

    /**
     * Permite buscar cuando campo peticion es NULL
     */
    private boolean peticionIsNull = false;

    /**
     * Permite buscar cuando campo peticion es NOT NULL
     */
    private boolean peticionIsNotNull = false;

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
     * Valor inferior de rango de busqueda de fecha fechaActualizacion
     */
    private Date fechaActualizacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaActualizacion
     */
    private Date fechaActualizacionMax;

    /**
     * Permite buscar cuando campo fechaActualizacion es NULL
     */
    private boolean fechaActualizacionIsNull = false;

    /**
     * Permite buscar cuando campo fechaActualizacion es NOT NULL
     */
    private boolean fechaActualizacionIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre proveedor
     */
    private boolean innerJoinProveedor = false;

    /**
     * Indica si en la consulta se hace un left join con el padre proveedor
     */
    private boolean leftJoinProveedor = false;

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
     * Indica si en la consulta se hace un inner join con el padre peticion
     */
    private boolean innerJoinPeticion = false;

    /**
     * Indica si en la consulta se hace un left join con el padre peticion
     */
    private boolean leftJoinPeticion = false;

    /**
     * Constructor default
     */
    public AuditoriaQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public AuditoriaQuery(Long idAuditoria) {
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
     * Valor de busqueda de campo peticion
     * @return Peticion.
     */
    public PeticionQuery getPeticion() {
        return peticion;
    }

    /**
     * Valor de busqueda de campo peticion
     * @param peticion Valor de seteo.
     */
    public void setPeticion(PeticionQuery peticion) {
        this.peticion = peticion;
    }

    /**
     * @return List<Peticion>.
     */
    public List<Peticion> getPeticionIdIn() {
        return this.peticionIdIn;
    }

    /**
     * @param peticion Valor a agregar.
     */
    public void addPeticionIdIn(Peticion peticion) {
        this.peticionIdIn.add(peticion);
    }

    /**
     * Permite buscar cuando campo peticion es NULL
     * @return boolean.
     */
    public boolean isPeticionIsNull() {
        return peticionIsNull;
    }

    /**
     * Permite buscar cuando campo peticion es NULL
     * @param peticionIsNull Valor de seteo.
     */
    public void setPeticionIsNull(boolean peticionIsNull) {
        this.peticionIsNull = peticionIsNull;
    }

    /**
     * Permite buscar cuando campo peticion es NOT NULL
     * @return boolean.
     */
    public boolean isPeticionIsNotNull() {
        return peticionIsNotNull;
    }

    /**
     * Permite buscar cuando campo peticion es NOT NULL
     * @param peticionIsNotNull Valor de seteo.
     */
    public void setPeticionIsNotNull(boolean peticionIsNotNull) {
        this.peticionIsNotNull = peticionIsNotNull;
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
     * Valor inferior de rango de busqueda de fecha fechaActualizacion
     * @return ${field.getName()}Min.
     */
    public Date getFechaActualizacionMin() {
        if (fechaActualizacionMin != null) {
            return DateUtil.toDayBegin(fechaActualizacionMin);
        }
        return fechaActualizacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaActualizacion
     * @param fechaActualizacionMin Valor de seteo.
     */
    public void setFechaActualizacionMin(Date fechaActualizacionMin) {
        this.fechaActualizacionMin = fechaActualizacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaActualizacion
     * @return fechaActualizacionMax.
     */
    public Date getFechaActualizacionMax() {
        if (fechaActualizacionMax != null) {
            return DateUtil.toDayEnd(fechaActualizacionMax);
        }
        return fechaActualizacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaActualizacion
     * @param fechaActualizacionMax Valor de seteo.
     */
    public void setFechaActualizacionMax(Date fechaActualizacionMax) {
        this.fechaActualizacionMax = fechaActualizacionMax;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NULL
     * @return boolean.
     */
    public boolean isFechaActualizacionIsNull() {
        return fechaActualizacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NULL
     * @param fechaActualizacionIsNull Valor de seteo.
     */
    public void setFechaActualizacionIsNull(boolean fechaActualizacionIsNull) {
        this.fechaActualizacionIsNull = fechaActualizacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaActualizacionIsNotNull() {
        return fechaActualizacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NOT NULL
     * @param fechaActualizacionIsNotNull Valor de seteo.
     */
    public void setFechaActualizacionIsNotNull(boolean fechaActualizacionIsNotNull) {
        this.fechaActualizacionIsNotNull = fechaActualizacionIsNotNull;
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
     * @return boolean.
     */
    public boolean isInnerJoinPeticion() {
        return innerJoinPeticion;
    }

    /**
     * @param innerJoinPeticion Valor de seteo.
     */
    public void setInnerJoinPeticion(boolean innerJoinPeticion) {
        this.innerJoinPeticion = innerJoinPeticion;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinPeticion() {
        return leftJoinPeticion;
    }

    /**
     * @param leftJoinPeticion Valor de seteo.
     */
    public void setLeftJoinPeticion(boolean leftJoinPeticion) {
        this.leftJoinPeticion = leftJoinPeticion;
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

        // Campo entidad padre peticion
        
        // Si se hace join fetch con el padre
        Criteria peticionCriteria = null;
        if (isInnerJoinPeticion()) {
            peticionCriteria = criteria.createCriteria(PETICION, "a_" + PETICION, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinPeticion()) {
            peticionCriteria = criteria.createCriteria(PETICION, "a_" + PETICION, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getPeticion() != null) {
            if (getPeticion().getIdPeticion() == null) {
                if (peticionCriteria == null) {
                    peticionCriteria = criteria.createCriteria(PETICION, "a_" + PETICION);
                }
                getPeticion().addCriteria(peticionCriteria, useOrder);
            } else {
                Peticion parent = new Peticion();
                parent.setIdPeticion(getPeticion().getIdPeticion());
                criteria.add(Restrictions.eq(PETICION, parent));
            }
        }

        if (getPeticionIdIn().size() > 0) {
            criteria.add(Restrictions.in(PETICION, getPeticionIdIn()));
        }

        if (isPeticionIsNull()) {
            criteria.add(Restrictions.isNull(PETICION));
        }

        if (isPeticionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PETICION));
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

        if (getFechaActualizacionMin() != null) {
            criteria.add(Restrictions.ge(FECHAACTUALIZACION, getFechaActualizacionMin()));
        }

        if (getFechaActualizacionMax() != null) {
            criteria.add(Restrictions.le(FECHAACTUALIZACION, getFechaActualizacionMax()));
        }

        if (isFechaActualizacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAACTUALIZACION));
        }

        if (isFechaActualizacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAACTUALIZACION));
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
 
