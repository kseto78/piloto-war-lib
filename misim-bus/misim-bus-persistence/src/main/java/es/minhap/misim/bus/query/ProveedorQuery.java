/*
 *
 * archivo: ProveedorQuery.java
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
import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.Transformacion;

/**
 * Clase con criterios de busqueda para la entidad Proveedor
 */
public class ProveedorQuery extends AbstractHibernateQueryEntity<Proveedor> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDPROVEEDOR = "idProveedor";
    public static final String ENDPOINT = "endpoint";
    public static final String PRODUCTO = "producto";
    public static final String TRANSFORMACION = "transformacion";
    public static final String NOMBRE = "nombre";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    public static final String COMPANY = "company";
    public static final String HEADER = "header";
    public static final String TYPE = "type";


    /**
     * Valor de busqueda de campo idProveedor
     */
    private Long idProveedor;

    /**
     * Lista de valores del campo idProveedor para busquedas tipo IN
     */
    private List<Long> idProveedorIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo endpoint
     */
    private EndpointQuery endpoint;

    /**
     * Lista de valores del ID del campo endpoint para busquedas tipo IN
     * Solo se consideran los ID dentro de los Endpoint
     */
    private List<Endpoint> endpointIdIn = new ArrayList<Endpoint>(0);

    /**
     * Permite buscar cuando campo endpoint es NULL
     */
    private boolean endpointIsNull = false;

    /**
     * Permite buscar cuando campo endpoint es NOT NULL
     */
    private boolean endpointIsNotNull = false;

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
     * Valor de busqueda de campo transformacion
     */
    private TransformacionQuery transformacion;

    /**
     * Lista de valores del ID del campo transformacion para busquedas tipo IN
     * Solo se consideran los ID dentro de los Transformacion
     */
    private List<Transformacion> transformacionIdIn = new ArrayList<Transformacion>(0);

    /**
     * Permite buscar cuando campo transformacion es NULL
     */
    private boolean transformacionIsNull = false;

    /**
     * Permite buscar cuando campo transformacion es NOT NULL
     */
    private boolean transformacionIsNotNull = false;

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
     * Valor de busqueda de campo usuario
     */
    private String usuario;

    /**
     * Tipo de comparador para la busqueda por campo usuario
     */
    private TextComparator usuarioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo usuario para busquedas tipo IN
     */
    private List<String> usuarioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo usuario es NULL
     */
    private boolean usuarioIsNull = false;

    /**
     * Permite buscar cuando campo usuario es NOT NULL
     */
    private boolean usuarioIsNotNull = false;

    /**
     * Valor de busqueda de campo password
     */
    private String password;

    /**
     * Tipo de comparador para la busqueda por campo password
     */
    private TextComparator passwordComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo password para busquedas tipo IN
     */
    private List<String> passwordIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo password es NULL
     */
    private boolean passwordIsNull = false;

    /**
     * Permite buscar cuando campo password es NOT NULL
     */
    private boolean passwordIsNotNull = false;

    /**
     * Valor de busqueda de campo company
     */
    private String company;

    /**
     * Tipo de comparador para la busqueda por campo company
     */
    private TextComparator companyComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo company para busquedas tipo IN
     */
    private List<String> companyIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo company es NULL
     */
    private boolean companyIsNull = false;

    /**
     * Permite buscar cuando campo company es NOT NULL
     */
    private boolean companyIsNotNull = false;

    /**
     * Valor de busqueda de campo header
     */
    private String header;

    /**
     * Tipo de comparador para la busqueda por campo header
     */
    private TextComparator headerComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo header para busquedas tipo IN
     */
    private List<String> headerIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo header es NULL
     */
    private boolean headerIsNull = false;

    /**
     * Permite buscar cuando campo header es NOT NULL
     */
    private boolean headerIsNotNull = false;

    /**
     * Valor de busqueda de campo type
     */
    private String type;

    /**
     * Tipo de comparador para la busqueda por campo type
     */
    private TextComparator typeComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo type para busquedas tipo IN
     */
    private List<String> typeIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo type es NULL
     */
    private boolean typeIsNull = false;

    /**
     * Permite buscar cuando campo type es NOT NULL
     */
    private boolean typeIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre endpoint
     */
    private boolean innerJoinEndpoint = false;

    /**
     * Indica si en la consulta se hace un left join con el padre endpoint
     */
    private boolean leftJoinEndpoint = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre producto
     */
    private boolean innerJoinProducto = false;

    /**
     * Indica si en la consulta se hace un left join con el padre producto
     */
    private boolean leftJoinProducto = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre transformacion
     */
    private boolean innerJoinTransformacion = false;

    /**
     * Indica si en la consulta se hace un left join con el padre transformacion
     */
    private boolean leftJoinTransformacion = false;

    /**
     * Constructor default
     */
    public ProveedorQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ProveedorQuery(Long idProveedor) {
        setIdProveedor(idProveedor);
    }

    /**
     * Valor de busqueda de campo idProveedor
     * @return Long.
     */
    public Long getIdProveedor() {
        return idProveedor;
    }

    /**
     * Valor de busqueda de campo idProveedor
     * @param idProveedor Valor de seteo.
     */
    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdProveedorIn() {
        return this.idProveedorIn;
    }

    /**
     * @param idProveedor Valor a agregar.
     */
    public void addIdProveedorIn(Long idProveedor) {
        this.idProveedorIn.add(idProveedor);
    }

    /**
     * Valor de busqueda de campo endpoint
     * @return Endpoint.
     */
    public EndpointQuery getEndpoint() {
        return endpoint;
    }

    /**
     * Valor de busqueda de campo endpoint
     * @param endpoint Valor de seteo.
     */
    public void setEndpoint(EndpointQuery endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return List<Endpoint>.
     */
    public List<Endpoint> getEndpointIdIn() {
        return this.endpointIdIn;
    }

    /**
     * @param endpoint Valor a agregar.
     */
    public void addEndpointIdIn(Endpoint endpoint) {
        this.endpointIdIn.add(endpoint);
    }

    /**
     * Permite buscar cuando campo endpoint es NULL
     * @return boolean.
     */
    public boolean isEndpointIsNull() {
        return endpointIsNull;
    }

    /**
     * Permite buscar cuando campo endpoint es NULL
     * @param endpointIsNull Valor de seteo.
     */
    public void setEndpointIsNull(boolean endpointIsNull) {
        this.endpointIsNull = endpointIsNull;
    }

    /**
     * Permite buscar cuando campo endpoint es NOT NULL
     * @return boolean.
     */
    public boolean isEndpointIsNotNull() {
        return endpointIsNotNull;
    }

    /**
     * Permite buscar cuando campo endpoint es NOT NULL
     * @param endpointIsNotNull Valor de seteo.
     */
    public void setEndpointIsNotNull(boolean endpointIsNotNull) {
        this.endpointIsNotNull = endpointIsNotNull;
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
     * Valor de busqueda de campo transformacion
     * @return Transformacion.
     */
    public TransformacionQuery getTransformacion() {
        return transformacion;
    }

    /**
     * Valor de busqueda de campo transformacion
     * @param transformacion Valor de seteo.
     */
    public void setTransformacion(TransformacionQuery transformacion) {
        this.transformacion = transformacion;
    }

    /**
     * @return List<Transformacion>.
     */
    public List<Transformacion> getTransformacionIdIn() {
        return this.transformacionIdIn;
    }

    /**
     * @param transformacion Valor a agregar.
     */
    public void addTransformacionIdIn(Transformacion transformacion) {
        this.transformacionIdIn.add(transformacion);
    }

    /**
     * Permite buscar cuando campo transformacion es NULL
     * @return boolean.
     */
    public boolean isTransformacionIsNull() {
        return transformacionIsNull;
    }

    /**
     * Permite buscar cuando campo transformacion es NULL
     * @param transformacionIsNull Valor de seteo.
     */
    public void setTransformacionIsNull(boolean transformacionIsNull) {
        this.transformacionIsNull = transformacionIsNull;
    }

    /**
     * Permite buscar cuando campo transformacion es NOT NULL
     * @return boolean.
     */
    public boolean isTransformacionIsNotNull() {
        return transformacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo transformacion es NOT NULL
     * @param transformacionIsNotNull Valor de seteo.
     */
    public void setTransformacionIsNotNull(boolean transformacionIsNotNull) {
        this.transformacionIsNotNull = transformacionIsNotNull;
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
     * Valor de busqueda de campo usuario
     * @return String.
     */
    public String getUsuario() {
        if (usuario != null) {
            switch (usuarioComparator) {
	            case STARTS_WITH:
	                return usuario + "%";
	            case CONTAINS:
	                return "%" + usuario + "%";
	            case ENDS_WITH:
	                return "%" + usuario;
	            case EQUALS:
                	return usuario;
            }
        }
        return usuario;
    }

    /**
     * Valor de busqueda de campo usuario
     * @param usuario Valor de seteo.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Tipo de comparador para la busqueda por campo usuario
     * @return usuarioComparator.
     */
    public TextComparator getUsuarioComparator() {
        return usuarioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo usuario
     * @param usuarioComparator Valor de seteo.
     */
    public void setUsuarioComparator(TextComparator usuarioComparator) {
        this.usuarioComparator = usuarioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUsuarioIn() {
        return this.usuarioIn;
    }

    /**
     * @param usuario Valor a agregar.
     */
    public void addUsuarioIn(String usuario) {
        this.usuarioIn.add(usuario);
    }

    /**
     * Permite buscar cuando campo usuario es NULL
     * @return boolean.
     */
    public boolean isUsuarioIsNull() {
        return usuarioIsNull;
    }

    /**
     * Permite buscar cuando campo usuario es NULL
     * @param usuarioIsNull Valor de seteo.
     */
    public void setUsuarioIsNull(boolean usuarioIsNull) {
        this.usuarioIsNull = usuarioIsNull;
    }

    /**
     * Permite buscar cuando campo usuario es NOT NULL
     * @return boolean.
     */
    public boolean isUsuarioIsNotNull() {
        return usuarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo usuario es NOT NULL
     * @param usuarioIsNotNull Valor de seteo.
     */
    public void setUsuarioIsNotNull(boolean usuarioIsNotNull) {
        this.usuarioIsNotNull = usuarioIsNotNull;
    }

    /**
     * Valor de busqueda de campo password
     * @return String.
     */
    public String getPassword() {
        if (password != null) {
            switch (passwordComparator) {
	            case STARTS_WITH:
	                return password + "%";
	            case CONTAINS:
	                return "%" + password + "%";
	            case ENDS_WITH:
	                return "%" + password;
	            case EQUALS:
                	return password;
            }
        }
        return password;
    }

    /**
     * Valor de busqueda de campo password
     * @param password Valor de seteo.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Tipo de comparador para la busqueda por campo password
     * @return passwordComparator.
     */
    public TextComparator getPasswordComparator() {
        return passwordComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo password
     * @param passwordComparator Valor de seteo.
     */
    public void setPasswordComparator(TextComparator passwordComparator) {
        this.passwordComparator = passwordComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getPasswordIn() {
        return this.passwordIn;
    }

    /**
     * @param password Valor a agregar.
     */
    public void addPasswordIn(String password) {
        this.passwordIn.add(password);
    }

    /**
     * Permite buscar cuando campo password es NULL
     * @return boolean.
     */
    public boolean isPasswordIsNull() {
        return passwordIsNull;
    }

    /**
     * Permite buscar cuando campo password es NULL
     * @param passwordIsNull Valor de seteo.
     */
    public void setPasswordIsNull(boolean passwordIsNull) {
        this.passwordIsNull = passwordIsNull;
    }

    /**
     * Permite buscar cuando campo password es NOT NULL
     * @return boolean.
     */
    public boolean isPasswordIsNotNull() {
        return passwordIsNotNull;
    }

    /**
     * Permite buscar cuando campo password es NOT NULL
     * @param passwordIsNotNull Valor de seteo.
     */
    public void setPasswordIsNotNull(boolean passwordIsNotNull) {
        this.passwordIsNotNull = passwordIsNotNull;
    }

    /**
     * Valor de busqueda de campo company
     * @return String.
     */
    public String getCompany() {
        if (company != null) {
            switch (companyComparator) {
	            case STARTS_WITH:
	                return company + "%";
	            case CONTAINS:
	                return "%" + company + "%";
	            case ENDS_WITH:
	                return "%" + company;
	            case EQUALS:
                	return company;
            }
        }
        return company;
    }

    /**
     * Valor de busqueda de campo company
     * @param company Valor de seteo.
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Tipo de comparador para la busqueda por campo company
     * @return companyComparator.
     */
    public TextComparator getCompanyComparator() {
        return companyComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo company
     * @param companyComparator Valor de seteo.
     */
    public void setCompanyComparator(TextComparator companyComparator) {
        this.companyComparator = companyComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCompanyIn() {
        return this.companyIn;
    }

    /**
     * @param company Valor a agregar.
     */
    public void addCompanyIn(String company) {
        this.companyIn.add(company);
    }

    /**
     * Permite buscar cuando campo company es NULL
     * @return boolean.
     */
    public boolean isCompanyIsNull() {
        return companyIsNull;
    }

    /**
     * Permite buscar cuando campo company es NULL
     * @param companyIsNull Valor de seteo.
     */
    public void setCompanyIsNull(boolean companyIsNull) {
        this.companyIsNull = companyIsNull;
    }

    /**
     * Permite buscar cuando campo company es NOT NULL
     * @return boolean.
     */
    public boolean isCompanyIsNotNull() {
        return companyIsNotNull;
    }

    /**
     * Permite buscar cuando campo company es NOT NULL
     * @param companyIsNotNull Valor de seteo.
     */
    public void setCompanyIsNotNull(boolean companyIsNotNull) {
        this.companyIsNotNull = companyIsNotNull;
    }

    /**
     * Valor de busqueda de campo header
     * @return String.
     */
    public String getHeader() {
        if (header != null) {
            switch (headerComparator) {
	            case STARTS_WITH:
	                return header + "%";
	            case CONTAINS:
	                return "%" + header + "%";
	            case ENDS_WITH:
	                return "%" + header;
	            case EQUALS:
                	return header;
            }
        }
        return header;
    }

    /**
     * Valor de busqueda de campo header
     * @param header Valor de seteo.
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Tipo de comparador para la busqueda por campo header
     * @return headerComparator.
     */
    public TextComparator getHeaderComparator() {
        return headerComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo header
     * @param headerComparator Valor de seteo.
     */
    public void setHeaderComparator(TextComparator headerComparator) {
        this.headerComparator = headerComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getHeaderIn() {
        return this.headerIn;
    }

    /**
     * @param header Valor a agregar.
     */
    public void addHeaderIn(String header) {
        this.headerIn.add(header);
    }

    /**
     * Permite buscar cuando campo header es NULL
     * @return boolean.
     */
    public boolean isHeaderIsNull() {
        return headerIsNull;
    }

    /**
     * Permite buscar cuando campo header es NULL
     * @param headerIsNull Valor de seteo.
     */
    public void setHeaderIsNull(boolean headerIsNull) {
        this.headerIsNull = headerIsNull;
    }

    /**
     * Permite buscar cuando campo header es NOT NULL
     * @return boolean.
     */
    public boolean isHeaderIsNotNull() {
        return headerIsNotNull;
    }

    /**
     * Permite buscar cuando campo header es NOT NULL
     * @param headerIsNotNull Valor de seteo.
     */
    public void setHeaderIsNotNull(boolean headerIsNotNull) {
        this.headerIsNotNull = headerIsNotNull;
    }

    /**
     * Valor de busqueda de campo type
     * @return String.
     */
    public String getType() {
        if (type != null) {
            switch (typeComparator) {
	            case STARTS_WITH:
	                return type + "%";
	            case CONTAINS:
	                return "%" + type + "%";
	            case ENDS_WITH:
	                return "%" + type;
	            case EQUALS:
                	return type;
            }
        }
        return type;
    }

    /**
     * Valor de busqueda de campo type
     * @param type Valor de seteo.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Tipo de comparador para la busqueda por campo type
     * @return typeComparator.
     */
    public TextComparator getTypeComparator() {
        return typeComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo type
     * @param typeComparator Valor de seteo.
     */
    public void setTypeComparator(TextComparator typeComparator) {
        this.typeComparator = typeComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTypeIn() {
        return this.typeIn;
    }

    /**
     * @param type Valor a agregar.
     */
    public void addTypeIn(String type) {
        this.typeIn.add(type);
    }

    /**
     * Permite buscar cuando campo type es NULL
     * @return boolean.
     */
    public boolean isTypeIsNull() {
        return typeIsNull;
    }

    /**
     * Permite buscar cuando campo type es NULL
     * @param typeIsNull Valor de seteo.
     */
    public void setTypeIsNull(boolean typeIsNull) {
        this.typeIsNull = typeIsNull;
    }

    /**
     * Permite buscar cuando campo type es NOT NULL
     * @return boolean.
     */
    public boolean isTypeIsNotNull() {
        return typeIsNotNull;
    }

    /**
     * Permite buscar cuando campo type es NOT NULL
     * @param typeIsNotNull Valor de seteo.
     */
    public void setTypeIsNotNull(boolean typeIsNotNull) {
        this.typeIsNotNull = typeIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinEndpoint() {
        return innerJoinEndpoint;
    }

    /**
     * @param innerJoinEndpoint Valor de seteo.
     */
    public void setInnerJoinEndpoint(boolean innerJoinEndpoint) {
        this.innerJoinEndpoint = innerJoinEndpoint;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinEndpoint() {
        return leftJoinEndpoint;
    }

    /**
     * @param leftJoinEndpoint Valor de seteo.
     */
    public void setLeftJoinEndpoint(boolean leftJoinEndpoint) {
        this.leftJoinEndpoint = leftJoinEndpoint;
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
    public boolean isInnerJoinTransformacion() {
        return innerJoinTransformacion;
    }

    /**
     * @param innerJoinTransformacion Valor de seteo.
     */
    public void setInnerJoinTransformacion(boolean innerJoinTransformacion) {
        this.innerJoinTransformacion = innerJoinTransformacion;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTransformacion() {
        return leftJoinTransformacion;
    }

    /**
     * @param leftJoinTransformacion Valor de seteo.
     */
    public void setLeftJoinTransformacion(boolean leftJoinTransformacion) {
        this.leftJoinTransformacion = leftJoinTransformacion;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdProveedor() != null) {
            criteria.add(Restrictions.eq(IDPROVEEDOR, getIdProveedor()));
        }

        if (getIdProveedorIn().size() > 0) {
            criteria.add(Restrictions.in(IDPROVEEDOR, getIdProveedorIn()));
        }

        // Campo entidad padre endpoint
        
        // Si se hace join fetch con el padre
        Criteria endpointCriteria = null;
        if (isInnerJoinEndpoint()) {
            endpointCriteria = criteria.createCriteria(ENDPOINT, "a_" + ENDPOINT, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinEndpoint()) {
            endpointCriteria = criteria.createCriteria(ENDPOINT, "a_" + ENDPOINT, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getEndpoint() != null) {
            if (getEndpoint().getIdEndpoint() == null) {
                if (endpointCriteria == null) {
                    endpointCriteria = criteria.createCriteria(ENDPOINT, "a_" + ENDPOINT);
                }
                getEndpoint().addCriteria(endpointCriteria, useOrder);
            } else {
                Endpoint parent = new Endpoint();
                parent.setIdEndpoint(getEndpoint().getIdEndpoint());
                criteria.add(Restrictions.eq(ENDPOINT, parent));
            }
        }

        if (getEndpointIdIn().size() > 0) {
            criteria.add(Restrictions.in(ENDPOINT, getEndpointIdIn()));
        }

        if (isEndpointIsNull()) {
            criteria.add(Restrictions.isNull(ENDPOINT));
        }

        if (isEndpointIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ENDPOINT));
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

        // Campo entidad padre transformacion
        
        // Si se hace join fetch con el padre
        Criteria transformacionCriteria = null;
        if (isInnerJoinTransformacion()) {
            transformacionCriteria = criteria.createCriteria(TRANSFORMACION, "a_" + TRANSFORMACION, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTransformacion()) {
            transformacionCriteria = criteria.createCriteria(TRANSFORMACION, "a_" + TRANSFORMACION, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTransformacion() != null) {
            if (getTransformacion().getIdTransformacion() == null) {
                if (transformacionCriteria == null) {
                    transformacionCriteria = criteria.createCriteria(TRANSFORMACION, "a_" + TRANSFORMACION);
                }
                getTransformacion().addCriteria(transformacionCriteria, useOrder);
            } else {
                Transformacion parent = new Transformacion();
                parent.setIdTransformacion(getTransformacion().getIdTransformacion());
                criteria.add(Restrictions.eq(TRANSFORMACION, parent));
            }
        }

        if (getTransformacionIdIn().size() > 0) {
            criteria.add(Restrictions.in(TRANSFORMACION, getTransformacionIdIn()));
        }

        if (isTransformacionIsNull()) {
            criteria.add(Restrictions.isNull(TRANSFORMACION));
        }

        if (isTransformacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TRANSFORMACION));
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

        if (getUsuario() != null) {
            if (getUsuarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(USUARIO, getUsuario()));
            } 
            else if (getUsuarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(USUARIO, getUsuario()));
            }
            else {
                criteria.add(Restrictions.like(USUARIO, getUsuario()));
            }
        }

        if (getUsuarioIn().size() > 0) {
            criteria.add(Restrictions.in(USUARIO, getUsuarioIn()));
        }

        if (isUsuarioIsNull()) {
            criteria.add(Restrictions.isNull(USUARIO));
        }

        if (isUsuarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(USUARIO));
        }

        if (getPassword() != null) {
            if (getPasswordComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PASSWORD, getPassword()));
            } 
            else if (getPasswordComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PASSWORD, getPassword()));
            }
            else {
                criteria.add(Restrictions.like(PASSWORD, getPassword()));
            }
        }

        if (getPasswordIn().size() > 0) {
            criteria.add(Restrictions.in(PASSWORD, getPasswordIn()));
        }

        if (isPasswordIsNull()) {
            criteria.add(Restrictions.isNull(PASSWORD));
        }

        if (isPasswordIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PASSWORD));
        }

        if (getCompany() != null) {
            if (getCompanyComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(COMPANY, getCompany()));
            } 
            else if (getCompanyComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(COMPANY, getCompany()));
            }
            else {
                criteria.add(Restrictions.like(COMPANY, getCompany()));
            }
        }

        if (getCompanyIn().size() > 0) {
            criteria.add(Restrictions.in(COMPANY, getCompanyIn()));
        }

        if (isCompanyIsNull()) {
            criteria.add(Restrictions.isNull(COMPANY));
        }

        if (isCompanyIsNotNull()) {
            criteria.add(Restrictions.isNotNull(COMPANY));
        }

        if (getHeader() != null) {
            if (getHeaderComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(HEADER, getHeader()));
            } 
            else if (getHeaderComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(HEADER, getHeader()));
            }
            else {
                criteria.add(Restrictions.like(HEADER, getHeader()));
            }
        }

        if (getHeaderIn().size() > 0) {
            criteria.add(Restrictions.in(HEADER, getHeaderIn()));
        }

        if (isHeaderIsNull()) {
            criteria.add(Restrictions.isNull(HEADER));
        }

        if (isHeaderIsNotNull()) {
            criteria.add(Restrictions.isNotNull(HEADER));
        }

        if (getType() != null) {
            if (getTypeComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TYPE, getType()));
            } 
            else if (getTypeComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TYPE, getType()));
            }
            else {
                criteria.add(Restrictions.like(TYPE, getType()));
            }
        }

        if (getTypeIn().size() > 0) {
            criteria.add(Restrictions.in(TYPE, getTypeIn()));
        }

        if (isTypeIsNull()) {
            criteria.add(Restrictions.isNull(TYPE));
        }

        if (isTypeIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TYPE));
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
 
