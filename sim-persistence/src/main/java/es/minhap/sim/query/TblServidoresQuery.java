/*
 *
 * archivo: TblServidoresQuery.java
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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblServidores;

/**
 * Clase con criterios de busqueda para la entidad TblServidores
 */
public class TblServidoresQuery extends AbstractHibernateQueryEntity<TblServidores> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String SERVIDORID = "servidorid";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String PORDEFECTO = "pordefecto";
    public static final String ACTIVO = "activo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String URLDESTINO = "urldestino";
    public static final String TIPO = "tipo";
    public static final String EXTERNALID = "externalid";
    public static final String ELIMINADO = "eliminado";
    public static final String PLATAFORMA = "plataforma";
    public static final String URLFEEDBACK = "urlfeedback";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    public static final String METODOCONSULTA = "metodoconsulta";


    /**
     * Valor de busqueda de campo servidorid
     */
    private Long servidorid;

    /**
     * Lista de valores del campo servidorid para busquedas tipo IN
     */
    private List<Long> servidoridIn = new ArrayList<Long>(0);
    
    /**
     * Lista de valores del campo servidorid para busquedas tipo NOT IN
     */
    private List<Long> servidoridNotIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo pordefecto
     */
    private Boolean pordefecto;

    /**
     * Permite buscar cuando campo pordefecto es NULL
     */
    private boolean pordefectoIsNull = false;

    /**
     * Permite buscar cuando campo pordefecto es NOT NULL
     */
    private boolean pordefectoIsNotNull = false;

    /**
     * Valor de busqueda de campo activo
     */
    private Boolean activo;

    /**
     * Permite buscar cuando campo activo es NULL
     */
    private boolean activoIsNull = false;

    /**
     * Permite buscar cuando campo activo es NOT NULL
     */
    private boolean activoIsNotNull = false;

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
     * Valor inferior de rango de busqueda de fecha fechamodificacion
     */
    private Date fechamodificacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechamodificacion
     */
    private Date fechamodificacionMax;

    /**
     * Permite buscar cuando campo fechamodificacion es NULL
     */
    private boolean fechamodificacionIsNull = false;

    /**
     * Permite buscar cuando campo fechamodificacion es NOT NULL
     */
    private boolean fechamodificacionIsNotNull = false;

    /**
     * Valor de busqueda de campo modificadopor
     */
    private String modificadopor;

    /**
     * Tipo de comparador para la busqueda por campo modificadopor
     */
    private TextComparator modificadoporComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo modificadopor para busquedas tipo IN
     */
    private List<String> modificadoporIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo modificadopor es NULL
     */
    private boolean modificadoporIsNull = false;

    /**
     * Permite buscar cuando campo modificadopor es NOT NULL
     */
    private boolean modificadoporIsNotNull = false;

    /**
     * Valor de busqueda de campo urldestino
     */
    private String urldestino;

    /**
     * Tipo de comparador para la busqueda por campo urldestino
     */
    private TextComparator urldestinoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo urldestino para busquedas tipo IN
     */
    private List<String> urldestinoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo urldestino es NULL
     */
    private boolean urldestinoIsNull = false;

    /**
     * Permite buscar cuando campo urldestino es NOT NULL
     */
    private boolean urldestinoIsNotNull = false;

    /**
     * Valor de busqueda de campo tipo
     */
    private Integer tipo;

    /**
     * Lista de valores del campo tipo para busquedas tipo IN
     */
    private List<Integer> tipoIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo tipo es NULL
     */
    private boolean tipoIsNull = false;

    /**
     * Permite buscar cuando campo tipo es NOT NULL
     */
    private boolean tipoIsNotNull = false;

    /**
     * Valor de busqueda de campo externalid
     */
    private String externalid;

    /**
     * Tipo de comparador para la busqueda por campo externalid
     */
    private TextComparator externalidComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo externalid para busquedas tipo IN
     */
    private List<String> externalidIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo externalid es NULL
     */
    private boolean externalidIsNull = false;

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     */
    private boolean externalidIsNotNull = false;

    /**
     * Valor de busqueda de campo eliminado
     */
    private String eliminado;

    /**
     * Tipo de comparador para la busqueda por campo eliminado
     */
    private TextComparator eliminadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo eliminado para busquedas tipo IN
     */
    private List<String> eliminadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo eliminado es NULL
     */
    private boolean eliminadoIsNull = false;

    /**
     * Permite buscar cuando campo eliminado es NOT NULL
     */
    private boolean eliminadoIsNotNull = false;

    /**
     * Valor de busqueda de campo plataforma
     */
    private Integer plataforma;

    /**
     * Lista de valores del campo plataforma para busquedas tipo IN
     */
    private List<Integer> plataformaIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo plataforma es NULL
     */
    private boolean plataformaIsNull = false;

    /**
     * Permite buscar cuando campo plataforma es NOT NULL
     */
    private boolean plataformaIsNotNull = false;

    /**
     * Valor de busqueda de campo urlfeedback
     */
    private String urlfeedback;

    /**
     * Tipo de comparador para la busqueda por campo urlfeedback
     */
    private TextComparator urlfeedbackComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo urlfeedback para busquedas tipo IN
     */
    private List<String> urlfeedbackIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo urlfeedback es NULL
     */
    private boolean urlfeedbackIsNull = false;

    /**
     * Permite buscar cuando campo urlfeedback es NOT NULL
     */
    private boolean urlfeedbackIsNotNull = false;

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
     * Valor de busqueda de campo metodoconsulta
     */
    private Boolean metodoconsulta;

    /**
     * Permite buscar cuando campo metodoconsulta es NULL
     */
    private boolean metodoconsultaIsNull = false;

    /**
     * Permite buscar cuando campo metodoconsulta es NOT NULL
     */
    private boolean metodoconsultaIsNotNull = false;

    
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
    public TblServidoresQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblServidoresQuery(Long servidorid) {
        setServidorid(servidorid);
    }

    /**
     * Valor de busqueda de campo servidorid
     * @return Long.
     */
    public Long getServidorid() {
        return servidorid;
    }

    /**
     * Valor de busqueda de campo servidorid
     * @param servidorid Valor de seteo.
     */
    public void setServidorid(Long servidorid) {
        this.servidorid = servidorid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getServidoridIn() {
        return this.servidoridIn;
    }

    /**
     * @param servidorid Valor a agregar.
     */
    public void addServidoridIn(Long servidorid) {
        this.servidoridIn.add(servidorid);
    }
    
    /**
     * @return List<Long>.
     */
    public List<Long> getServidoridNotIn() {
        return this.servidoridNotIn;
    }

    /**
     * @param servidorid Valor a agregar.
     */
    public void addServidoridNotIn(Long servidorid) {
        this.servidoridNotIn.add(servidorid);
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
              	default:
	            	break;
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
     * Valor de busqueda de campo pordefecto
     * @return Boolean.
     */
    public Boolean getPordefecto() {
        return pordefecto;
    }

    /**
     * Valor de busqueda de campo pordefecto
     * @param pordefecto Valor de seteo.
     */
    public void setPordefecto(Boolean pordefecto) {
        this.pordefecto = pordefecto;
    }

    /**
     * Permite buscar cuando campo pordefecto es NULL
     * @return boolean.
     */
    public boolean isPordefectoIsNull() {
        return pordefectoIsNull;
    }

    /**
     * Permite buscar cuando campo pordefecto es NULL
     * @param pordefectoIsNull Valor de seteo.
     */
    public void setPordefectoIsNull(boolean pordefectoIsNull) {
        this.pordefectoIsNull = pordefectoIsNull;
    }

    /**
     * Permite buscar cuando campo pordefecto es NOT NULL
     * @return boolean.
     */
    public boolean isPordefectoIsNotNull() {
        return pordefectoIsNotNull;
    }

    /**
     * Permite buscar cuando campo pordefecto es NOT NULL
     * @param pordefectoIsNotNull Valor de seteo.
     */
    public void setPordefectoIsNotNull(boolean pordefectoIsNotNull) {
        this.pordefectoIsNotNull = pordefectoIsNotNull;
    }

    /**
     * Valor de busqueda de campo activo
     * @return Boolean.
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * Valor de busqueda de campo activo
     * @param activo Valor de seteo.
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * Permite buscar cuando campo activo es NULL
     * @return boolean.
     */
    public boolean isActivoIsNull() {
        return activoIsNull;
    }

    /**
     * Permite buscar cuando campo activo es NULL
     * @param activoIsNull Valor de seteo.
     */
    public void setActivoIsNull(boolean activoIsNull) {
        this.activoIsNull = activoIsNull;
    }

    /**
     * Permite buscar cuando campo activo es NOT NULL
     * @return boolean.
     */
    public boolean isActivoIsNotNull() {
        return activoIsNotNull;
    }

    /**
     * Permite buscar cuando campo activo es NOT NULL
     * @param activoIsNotNull Valor de seteo.
     */
    public void setActivoIsNotNull(boolean activoIsNotNull) {
        this.activoIsNotNull = activoIsNotNull;
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
     * Valor inferior de rango de busqueda de fecha fechamodificacion
     * @return ${field.getName()}Min.
     */
    public Date getFechamodificacionMin() {
        if (fechamodificacionMin != null) {
            return DateUtil.toDayBegin(fechamodificacionMin);
        }
        return fechamodificacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechamodificacion
     * @param fechamodificacionMin Valor de seteo.
     */
    public void setFechamodificacionMin(Date fechamodificacionMin) {
        this.fechamodificacionMin = fechamodificacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechamodificacion
     * @return fechamodificacionMax.
     */
    public Date getFechamodificacionMax() {
        if (fechamodificacionMax != null) {
            return DateUtil.toDayEnd(fechamodificacionMax);
        }
        return fechamodificacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechamodificacion
     * @param fechamodificacionMax Valor de seteo.
     */
    public void setFechamodificacionMax(Date fechamodificacionMax) {
        this.fechamodificacionMax = fechamodificacionMax;
    }

    /**
     * Permite buscar cuando campo fechamodificacion es NULL
     * @return boolean.
     */
    public boolean isFechamodificacionIsNull() {
        return fechamodificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechamodificacion es NULL
     * @param fechamodificacionIsNull Valor de seteo.
     */
    public void setFechamodificacionIsNull(boolean fechamodificacionIsNull) {
        this.fechamodificacionIsNull = fechamodificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechamodificacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechamodificacionIsNotNull() {
        return fechamodificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechamodificacion es NOT NULL
     * @param fechamodificacionIsNotNull Valor de seteo.
     */
    public void setFechamodificacionIsNotNull(boolean fechamodificacionIsNotNull) {
        this.fechamodificacionIsNotNull = fechamodificacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo modificadopor
     * @return String.
     */
    public String getModificadopor() {
        if (modificadopor != null) {
            switch (modificadoporComparator) {
	            case STARTS_WITH:
	                return modificadopor + "%";
	            case CONTAINS:
	                return "%" + modificadopor + "%";
	            case ENDS_WITH:
	                return "%" + modificadopor;
	            case EQUALS:
                	return modificadopor;
              	default:
	            	break;
            }
        }
        return modificadopor;
    }

    /**
     * Valor de busqueda de campo modificadopor
     * @param modificadopor Valor de seteo.
     */
    public void setModificadopor(String modificadopor) {
        this.modificadopor = modificadopor;
    }

    /**
     * Tipo de comparador para la busqueda por campo modificadopor
     * @return modificadoporComparator.
     */
    public TextComparator getModificadoporComparator() {
        return modificadoporComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo modificadopor
     * @param modificadoporComparator Valor de seteo.
     */
    public void setModificadoporComparator(TextComparator modificadoporComparator) {
        this.modificadoporComparator = modificadoporComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getModificadoporIn() {
        return this.modificadoporIn;
    }

    /**
     * @param modificadopor Valor a agregar.
     */
    public void addModificadoporIn(String modificadopor) {
        this.modificadoporIn.add(modificadopor);
    }

    /**
     * Permite buscar cuando campo modificadopor es NULL
     * @return boolean.
     */
    public boolean isModificadoporIsNull() {
        return modificadoporIsNull;
    }

    /**
     * Permite buscar cuando campo modificadopor es NULL
     * @param modificadoporIsNull Valor de seteo.
     */
    public void setModificadoporIsNull(boolean modificadoporIsNull) {
        this.modificadoporIsNull = modificadoporIsNull;
    }

    /**
     * Permite buscar cuando campo modificadopor es NOT NULL
     * @return boolean.
     */
    public boolean isModificadoporIsNotNull() {
        return modificadoporIsNotNull;
    }

    /**
     * Permite buscar cuando campo modificadopor es NOT NULL
     * @param modificadoporIsNotNull Valor de seteo.
     */
    public void setModificadoporIsNotNull(boolean modificadoporIsNotNull) {
        this.modificadoporIsNotNull = modificadoporIsNotNull;
    }

    /**
     * Valor de busqueda de campo urldestino
     * @return String.
     */
    public String getUrldestino() {
        if (urldestino != null) {
            switch (urldestinoComparator) {
	            case STARTS_WITH:
	                return urldestino + "%";
	            case CONTAINS:
	                return "%" + urldestino + "%";
	            case ENDS_WITH:
	                return "%" + urldestino;
	            case EQUALS:
                	return urldestino;
              	default:
	            	break;
            }
        }
        return urldestino;
    }

    /**
     * Valor de busqueda de campo urldestino
     * @param urldestino Valor de seteo.
     */
    public void setUrldestino(String urldestino) {
        this.urldestino = urldestino;
    }

    /**
     * Tipo de comparador para la busqueda por campo urldestino
     * @return urldestinoComparator.
     */
    public TextComparator getUrldestinoComparator() {
        return urldestinoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo urldestino
     * @param urldestinoComparator Valor de seteo.
     */
    public void setUrldestinoComparator(TextComparator urldestinoComparator) {
        this.urldestinoComparator = urldestinoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUrldestinoIn() {
        return this.urldestinoIn;
    }

    /**
     * @param urldestino Valor a agregar.
     */
    public void addUrldestinoIn(String urldestino) {
        this.urldestinoIn.add(urldestino);
    }

    /**
     * Permite buscar cuando campo urldestino es NULL
     * @return boolean.
     */
    public boolean isUrldestinoIsNull() {
        return urldestinoIsNull;
    }

    /**
     * Permite buscar cuando campo urldestino es NULL
     * @param urldestinoIsNull Valor de seteo.
     */
    public void setUrldestinoIsNull(boolean urldestinoIsNull) {
        this.urldestinoIsNull = urldestinoIsNull;
    }

    /**
     * Permite buscar cuando campo urldestino es NOT NULL
     * @return boolean.
     */
    public boolean isUrldestinoIsNotNull() {
        return urldestinoIsNotNull;
    }

    /**
     * Permite buscar cuando campo urldestino es NOT NULL
     * @param urldestinoIsNotNull Valor de seteo.
     */
    public void setUrldestinoIsNotNull(boolean urldestinoIsNotNull) {
        this.urldestinoIsNotNull = urldestinoIsNotNull;
    }

    /**
     * Valor de busqueda de campo tipo
     * @return Integer.
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * Valor de busqueda de campo tipo
     * @param tipo Valor de seteo.
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getTipoIn() {
        return this.tipoIn;
    }

    /**
     * @param tipo Valor a agregar.
     */
    public void addTipoIn(Integer tipo) {
        this.tipoIn.add(tipo);
    }

    /**
     * Permite buscar cuando campo tipo es NULL
     * @return boolean.
     */
    public boolean isTipoIsNull() {
        return tipoIsNull;
    }

    /**
     * Permite buscar cuando campo tipo es NULL
     * @param tipoIsNull Valor de seteo.
     */
    public void setTipoIsNull(boolean tipoIsNull) {
        this.tipoIsNull = tipoIsNull;
    }

    /**
     * Permite buscar cuando campo tipo es NOT NULL
     * @return boolean.
     */
    public boolean isTipoIsNotNull() {
        return tipoIsNotNull;
    }

    /**
     * Permite buscar cuando campo tipo es NOT NULL
     * @param tipoIsNotNull Valor de seteo.
     */
    public void setTipoIsNotNull(boolean tipoIsNotNull) {
        this.tipoIsNotNull = tipoIsNotNull;
    }

    /**
     * Valor de busqueda de campo externalid
     * @return String.
     */
    public String getExternalid() {
        if (externalid != null) {
            switch (externalidComparator) {
	            case STARTS_WITH:
	                return externalid + "%";
	            case CONTAINS:
	                return "%" + externalid + "%";
	            case ENDS_WITH:
	                return "%" + externalid;
	            case EQUALS:
                	return externalid;
              	default:
	            	break;
            }
        }
        return externalid;
    }

    /**
     * Valor de busqueda de campo externalid
     * @param externalid Valor de seteo.
     */
    public void setExternalid(String externalid) {
        this.externalid = externalid;
    }

    /**
     * Tipo de comparador para la busqueda por campo externalid
     * @return externalidComparator.
     */
    public TextComparator getExternalidComparator() {
        return externalidComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo externalid
     * @param externalidComparator Valor de seteo.
     */
    public void setExternalidComparator(TextComparator externalidComparator) {
        this.externalidComparator = externalidComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getExternalidIn() {
        return this.externalidIn;
    }

    /**
     * @param externalid Valor a agregar.
     */
    public void addExternalidIn(String externalid) {
        this.externalidIn.add(externalid);
    }

    /**
     * Permite buscar cuando campo externalid es NULL
     * @return boolean.
     */
    public boolean isExternalidIsNull() {
        return externalidIsNull;
    }

    /**
     * Permite buscar cuando campo externalid es NULL
     * @param externalidIsNull Valor de seteo.
     */
    public void setExternalidIsNull(boolean externalidIsNull) {
        this.externalidIsNull = externalidIsNull;
    }

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     * @return boolean.
     */
    public boolean isExternalidIsNotNull() {
        return externalidIsNotNull;
    }

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     * @param externalidIsNotNull Valor de seteo.
     */
    public void setExternalidIsNotNull(boolean externalidIsNotNull) {
        this.externalidIsNotNull = externalidIsNotNull;
    }

    /**
     * Valor de busqueda de campo eliminado
     * @return String.
     */
    public String getEliminado() {
        if (eliminado != null) {
            switch (eliminadoComparator) {
	            case STARTS_WITH:
	                return eliminado + "%";
	            case CONTAINS:
	                return "%" + eliminado + "%";
	            case ENDS_WITH:
	                return "%" + eliminado;
	            case EQUALS:
                	return eliminado;
              	default:
	            	break;
            }
        }
        return eliminado;
    }

    /**
     * Valor de busqueda de campo eliminado
     * @param eliminado Valor de seteo.
     */
    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * Tipo de comparador para la busqueda por campo eliminado
     * @return eliminadoComparator.
     */
    public TextComparator getEliminadoComparator() {
        return eliminadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo eliminado
     * @param eliminadoComparator Valor de seteo.
     */
    public void setEliminadoComparator(TextComparator eliminadoComparator) {
        this.eliminadoComparator = eliminadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEliminadoIn() {
        return this.eliminadoIn;
    }

    /**
     * @param eliminado Valor a agregar.
     */
    public void addEliminadoIn(String eliminado) {
        this.eliminadoIn.add(eliminado);
    }

    /**
     * Permite buscar cuando campo eliminado es NULL
     * @return boolean.
     */
    public boolean isEliminadoIsNull() {
        return eliminadoIsNull;
    }

    /**
     * Permite buscar cuando campo eliminado es NULL
     * @param eliminadoIsNull Valor de seteo.
     */
    public void setEliminadoIsNull(boolean eliminadoIsNull) {
        this.eliminadoIsNull = eliminadoIsNull;
    }

    /**
     * Permite buscar cuando campo eliminado es NOT NULL
     * @return boolean.
     */
    public boolean isEliminadoIsNotNull() {
        return eliminadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo eliminado es NOT NULL
     * @param eliminadoIsNotNull Valor de seteo.
     */
    public void setEliminadoIsNotNull(boolean eliminadoIsNotNull) {
        this.eliminadoIsNotNull = eliminadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo plataforma
     * @return Integer.
     */
    public Integer getPlataforma() {
        return plataforma;
    }

    /**
     * Valor de busqueda de campo plataforma
     * @param plataforma Valor de seteo.
     */
    public void setPlataforma(Integer plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getPlataformaIn() {
        return this.plataformaIn;
    }

    /**
     * @param plataforma Valor a agregar.
     */
    public void addPlataformaIn(Integer plataforma) {
        this.plataformaIn.add(plataforma);
    }

    /**
     * Permite buscar cuando campo plataforma es NULL
     * @return boolean.
     */
    public boolean isPlataformaIsNull() {
        return plataformaIsNull;
    }

    /**
     * Permite buscar cuando campo plataforma es NULL
     * @param plataformaIsNull Valor de seteo.
     */
    public void setPlataformaIsNull(boolean plataformaIsNull) {
        this.plataformaIsNull = plataformaIsNull;
    }

    /**
     * Permite buscar cuando campo plataforma es NOT NULL
     * @return boolean.
     */
    public boolean isPlataformaIsNotNull() {
        return plataformaIsNotNull;
    }

    /**
     * Permite buscar cuando campo plataforma es NOT NULL
     * @param plataformaIsNotNull Valor de seteo.
     */
    public void setPlataformaIsNotNull(boolean plataformaIsNotNull) {
        this.plataformaIsNotNull = plataformaIsNotNull;
    }

    /**
     * Valor de busqueda de campo urlfeedback
     * @return String.
     */
    public String getUrlfeedback() {
        if (urlfeedback != null) {
            switch (urlfeedbackComparator) {
	            case STARTS_WITH:
	                return urlfeedback + "%";
	            case CONTAINS:
	                return "%" + urlfeedback + "%";
	            case ENDS_WITH:
	                return "%" + urlfeedback;
	            case EQUALS:
                	return urlfeedback;
              	default:
	            	break;
            }
        }
        return urlfeedback;
    }

    /**
     * Valor de busqueda de campo urlfeedback
     * @param urlfeedback Valor de seteo.
     */
    public void setUrlfeedback(String urlfeedback) {
        this.urlfeedback = urlfeedback;
    }

    /**
     * Tipo de comparador para la busqueda por campo urlfeedback
     * @return urlfeedbackComparator.
     */
    public TextComparator getUrlfeedbackComparator() {
        return urlfeedbackComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo urlfeedback
     * @param urlfeedbackComparator Valor de seteo.
     */
    public void setUrlfeedbackComparator(TextComparator urlfeedbackComparator) {
        this.urlfeedbackComparator = urlfeedbackComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUrlfeedbackIn() {
        return this.urlfeedbackIn;
    }

    /**
     * @param urlfeedback Valor a agregar.
     */
    public void addUrlfeedbackIn(String urlfeedback) {
        this.urlfeedbackIn.add(urlfeedback);
    }

    /**
     * Permite buscar cuando campo urlfeedback es NULL
     * @return boolean.
     */
    public boolean isUrlfeedbackIsNull() {
        return urlfeedbackIsNull;
    }

    /**
     * Permite buscar cuando campo urlfeedback es NULL
     * @param urlfeedbackIsNull Valor de seteo.
     */
    public void setUrlfeedbackIsNull(boolean urlfeedbackIsNull) {
        this.urlfeedbackIsNull = urlfeedbackIsNull;
    }

    /**
     * Permite buscar cuando campo urlfeedback es NOT NULL
     * @return boolean.
     */
    public boolean isUrlfeedbackIsNotNull() {
        return urlfeedbackIsNotNull;
    }

    /**
     * Permite buscar cuando campo urlfeedback es NOT NULL
     * @param urlfeedbackIsNotNull Valor de seteo.
     */
    public void setUrlfeedbackIsNotNull(boolean urlfeedbackIsNotNull) {
        this.urlfeedbackIsNotNull = urlfeedbackIsNotNull;
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
              	default:
	            	break;
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
              	default:
	            	break;
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
     * Valor de busqueda de campo metodoconsulta
     * @return Boolean.
     */
    public Boolean getMetodoconsulta() {
        return metodoconsulta;
    }

    /**
     * Valor de busqueda de campo metodoconsulta
     * @param metodoconsulta Valor de seteo.
     */
    public void setMetodoconsulta(Boolean metodoconsulta) {
        this.metodoconsulta = metodoconsulta;
    }

    /**
     * Permite buscar cuando campo metodoconsulta es NULL
     * @return boolean.
     */
    public boolean isMetodoconsultaIsNull() {
        return metodoconsultaIsNull;
    }

    /**
     * Permite buscar cuando campo metodoconsulta es NULL
     * @param metodoconsultaIsNull Valor de seteo.
     */
    public void setMetodoconsultaIsNull(boolean metodoconsultaIsNull) {
        this.metodoconsultaIsNull = metodoconsultaIsNull;
    }

    /**
     * Permite buscar cuando campo metodoconsulta es NOT NULL
     * @return boolean.
     */
    public boolean isMetodoconsultaIsNotNull() {
        return metodoconsultaIsNotNull;
    }

    /**
     * Permite buscar cuando campo metodoconsulta es NOT NULL
     * @param metodoconsultaIsNotNull Valor de seteo.
     */
    public void setMetodoconsultaIsNotNull(boolean metodoconsultaIsNotNull) {
        this.metodoconsultaIsNotNull = metodoconsultaIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getServidorid() != null) {
            criteria.add(Restrictions.eq(SERVIDORID, getServidorid()));
        }

        if (getServidoridIn().size() > 0) {
            criteria.add(Restrictions.in(SERVIDORID, getServidoridIn()));
        }
        
        if (getServidoridNotIn().size() > 0) {
            criteria.add(Restrictions.not(Restrictions.in(SERVIDORID, getServidoridNotIn())));
        }

        if (getNombre() != null) {
            if (getNombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRE, getNombre()));
            } 
            else if (getNombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRE, getNombre()));
            }
            else if (getNombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRE, getNombre())));
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

        if (getPordefecto() != null) {
            criteria.add(Restrictions.eq(PORDEFECTO, getPordefecto()));
        }

        if (isPordefectoIsNull()) {
            criteria.add(Restrictions.isNull(PORDEFECTO));
        }

        if (isPordefectoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PORDEFECTO));
        }

        if (getActivo() != null) {
            criteria.add(Restrictions.eq(ACTIVO, getActivo()));
        }

        if (isActivoIsNull()) {
            criteria.add(Restrictions.isNull(ACTIVO));
        }

        if (isActivoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ACTIVO));
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

        if (getFechamodificacionMin() != null) {
            criteria.add(Restrictions.ge(FECHAMODIFICACION, getFechamodificacionMin()));
        }

        if (getFechamodificacionMax() != null) {
            criteria.add(Restrictions.le(FECHAMODIFICACION, getFechamodificacionMax()));
        }

        if (isFechamodificacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAMODIFICACION));
        }

        if (isFechamodificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAMODIFICACION));
        }

        if (getModificadopor() != null) {
            if (getModificadoporComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MODIFICADOPOR, getModificadopor()));
            } 
            else if (getModificadoporComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MODIFICADOPOR, getModificadopor()));
            }
            else if (getModificadoporComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MODIFICADOPOR, getModificadopor())));
            }
            else {
                criteria.add(Restrictions.like(MODIFICADOPOR, getModificadopor()));
            }
        }

        if (getModificadoporIn().size() > 0) {
            criteria.add(Restrictions.in(MODIFICADOPOR, getModificadoporIn()));
        }

        if (isModificadoporIsNull()) {
            criteria.add(Restrictions.isNull(MODIFICADOPOR));
        }

        if (isModificadoporIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MODIFICADOPOR));
        }

        if (getUrldestino() != null) {
            if (getUrldestinoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(URLDESTINO, getUrldestino()));
            } 
            else if (getUrldestinoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(URLDESTINO, getUrldestino()));
            }
            else if (getUrldestinoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(URLDESTINO, getUrldestino())));
            }
            else {
                criteria.add(Restrictions.like(URLDESTINO, getUrldestino()));
            }
        }

        if (getUrldestinoIn().size() > 0) {
            criteria.add(Restrictions.in(URLDESTINO, getUrldestinoIn()));
        }

        if (isUrldestinoIsNull()) {
            criteria.add(Restrictions.isNull(URLDESTINO));
        }

        if (isUrldestinoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(URLDESTINO));
        }

        if (getTipo() != null) {
            criteria.add(Restrictions.eq(TIPO, getTipo()));
        }

        if (getTipoIn().size() > 0) {
            criteria.add(Restrictions.in(TIPO, getTipoIn()));
        }

        if (isTipoIsNull()) {
            criteria.add(Restrictions.isNull(TIPO));
        }

        if (isTipoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TIPO));
        }

        if (getExternalid() != null) {
            if (getExternalidComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(EXTERNALID, getExternalid()));
            } 
            else if (getExternalidComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(EXTERNALID, getExternalid()));
            }
            else if (getExternalidComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(EXTERNALID, getExternalid())));
            }
            else {
                criteria.add(Restrictions.like(EXTERNALID, getExternalid()));
            }
        }

        if (getExternalidIn().size() > 0) {
            criteria.add(Restrictions.in(EXTERNALID, getExternalidIn()));
        }

        if (isExternalidIsNull()) {
            criteria.add(Restrictions.isNull(EXTERNALID));
        }

        if (isExternalidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(EXTERNALID));
        }

        if (getEliminado() != null) {
            if (getEliminadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ELIMINADO, getEliminado()));
            } 
            else if (getEliminadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ELIMINADO, getEliminado()));
            }
            else if (getEliminadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ELIMINADO, getEliminado())));
            }
            else {
                criteria.add(Restrictions.like(ELIMINADO, getEliminado()));
            }
        }

        if (getEliminadoIn().size() > 0) {
            criteria.add(Restrictions.in(ELIMINADO, getEliminadoIn()));
        }

        if (isEliminadoIsNull()) {
//        	criteria.add(Restrictions.isNull(ELIMINADO));
        	Criterion c1 = Restrictions.isNull(ELIMINADO);
        	Criterion c2 = Restrictions.eq("eliminado", "N");
            criteria.add(Restrictions.or(c1,c2));
        }

        if (isEliminadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ELIMINADO));
        }

        if (getPlataforma() != null) {
            criteria.add(Restrictions.eq(PLATAFORMA, getPlataforma()));
        }

        if (getPlataformaIn().size() > 0) {
            criteria.add(Restrictions.in(PLATAFORMA, getPlataformaIn()));
        }

        if (isPlataformaIsNull()) {
            criteria.add(Restrictions.isNull(PLATAFORMA));
        }

        if (isPlataformaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PLATAFORMA));
        }

        if (getUrlfeedback() != null) {
            if (getUrlfeedbackComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(URLFEEDBACK, getUrlfeedback()));
            } 
            else if (getUrlfeedbackComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(URLFEEDBACK, getUrlfeedback()));
            }
            else if (getUrlfeedbackComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(URLFEEDBACK, getUrlfeedback())));
            }
            else {
                criteria.add(Restrictions.like(URLFEEDBACK, getUrlfeedback()));
            }
        }

        if (getUrlfeedbackIn().size() > 0) {
            criteria.add(Restrictions.in(URLFEEDBACK, getUrlfeedbackIn()));
        }

        if (isUrlfeedbackIsNull()) {
            criteria.add(Restrictions.isNull(URLFEEDBACK));
        }

        if (isUrlfeedbackIsNotNull()) {
            criteria.add(Restrictions.isNotNull(URLFEEDBACK));
        }

        if (getUsuario() != null) {
            if (getUsuarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(USUARIO, getUsuario()));
            } 
            else if (getUsuarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(USUARIO, getUsuario()));
            }
            else if (getUsuarioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(USUARIO, getUsuario())));
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
            else if (getPasswordComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PASSWORD, getPassword())));
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

        if (getMetodoconsulta() != null) {
            criteria.add(Restrictions.eq(METODOCONSULTA, getMetodoconsulta()));
        }

        if (isMetodoconsultaIsNull()) {
            criteria.add(Restrictions.isNull(METODOCONSULTA));
        }

        if (isMetodoconsultaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(METODOCONSULTA));
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
 
