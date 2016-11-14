/*
 *
 * archivo: TblServiciosMovilesQuery.java
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
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblServiciosMoviles;

/**
 * Clase con criterios de busqueda para la entidad TblServiciosMoviles
 */
public class TblServiciosMovilesQuery extends AbstractHibernateQueryEntity<TblServiciosMoviles> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String SERVICIOSMOVILESID = "serviciosmovilesid";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String TIPO = "tipo";
    public static final String URLSERVICIO = "urlServicio";
    public static final String NOMBRECONTACTO = "nombrecontacto";
    public static final String TELEFONOCONTACTO = "telefonocontacto";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String ICONO = "icono";
    public static final String IMAGEN = "imagen";
    public static final String ESTADO = "estado";


    /**
     * Valor de busqueda de campo serviciosmovilesid
     */
    private Long serviciosmovilesid;

    /**
     * Lista de valores del campo serviciosmovilesid para busquedas tipo IN
     */
    private List<Long> serviciosmovilesidIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo urlServicio
     */
    private String urlServicio;

    /**
     * Tipo de comparador para la busqueda por campo urlServicio
     */
    private TextComparator urlServicioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo urlServicio para busquedas tipo IN
     */
    private List<String> urlServicioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo urlServicio es NULL
     */
    private boolean urlServicioIsNull = false;

    /**
     * Permite buscar cuando campo urlServicio es NOT NULL
     */
    private boolean urlServicioIsNotNull = false;

    /**
     * Valor de busqueda de campo nombrecontacto
     */
    private String nombrecontacto;

    /**
     * Tipo de comparador para la busqueda por campo nombrecontacto
     */
    private TextComparator nombrecontactoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombrecontacto para busquedas tipo IN
     */
    private List<String> nombrecontactoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombrecontacto es NULL
     */
    private boolean nombrecontactoIsNull = false;

    /**
     * Permite buscar cuando campo nombrecontacto es NOT NULL
     */
    private boolean nombrecontactoIsNotNull = false;

    /**
     * Valor de busqueda de campo telefonocontacto
     */
    private String telefonocontacto;

    /**
     * Tipo de comparador para la busqueda por campo telefonocontacto
     */
    private TextComparator telefonocontactoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo telefonocontacto para busquedas tipo IN
     */
    private List<String> telefonocontactoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo telefonocontacto es NULL
     */
    private boolean telefonocontactoIsNull = false;

    /**
     * Permite buscar cuando campo telefonocontacto es NOT NULL
     */
    private boolean telefonocontactoIsNotNull = false;

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
     * Valor de busqueda de campo icono
     */
    private String icono;

    /**
     * Tipo de comparador para la busqueda por campo icono
     */
    private TextComparator iconoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo icono para busquedas tipo IN
     */
    private List<String> iconoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo icono es NULL
     */
    private boolean iconoIsNull = false;

    /**
     * Permite buscar cuando campo icono es NOT NULL
     */
    private boolean iconoIsNotNull = false;

    /**
     * Valor de busqueda de campo imagen
     */
    private String imagen;

    /**
     * Tipo de comparador para la busqueda por campo imagen
     */
    private TextComparator imagenComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo imagen para busquedas tipo IN
     */
    private List<String> imagenIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo imagen es NULL
     */
    private boolean imagenIsNull = false;

    /**
     * Permite buscar cuando campo imagen es NOT NULL
     */
    private boolean imagenIsNotNull = false;

    /**
     * Valor de busqueda de campo estado
     */
    private Integer estado;

    /**
     * Lista de valores del campo estado para busquedas tipo IN
     */
    private List<Integer> estadoIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo estado es NULL
     */
    private boolean estadoIsNull = false;

    /**
     * Permite buscar cuando campo estado es NOT NULL
     */
    private boolean estadoIsNotNull = false;

    /**
     * Constructor default
     */
    public TblServiciosMovilesQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblServiciosMovilesQuery(Long serviciosmovilesid) {
        setServiciosmovilesid(serviciosmovilesid);
    }

    /**
     * Valor de busqueda de campo serviciosmovilesid
     * @return Long.
     */
    public Long getServiciosmovilesid() {
        return serviciosmovilesid;
    }

    /**
     * Valor de busqueda de campo serviciosmovilesid
     * @param serviciosmovilesid Valor de seteo.
     */
    public void setServiciosmovilesid(Long serviciosmovilesid) {
        this.serviciosmovilesid = serviciosmovilesid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getServiciosmovilesidIn() {
        return this.serviciosmovilesidIn;
    }

    /**
     * @param serviciosmovilesid Valor a agregar.
     */
    public void addServiciosmovilesidIn(Long serviciosmovilesid) {
        this.serviciosmovilesidIn.add(serviciosmovilesid);
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
     * Valor de busqueda de campo urlServicio
     * @return String.
     */
    public String getUrlServicio() {
        if (urlServicio != null) {
            switch (urlServicioComparator) {
	            case STARTS_WITH:
	                return urlServicio + "%";
	            case CONTAINS:
	                return "%" + urlServicio + "%";
	            case ENDS_WITH:
	                return "%" + urlServicio;
	            case EQUALS:
                	return urlServicio;
              	default:
	            	break;
            }
        }
        return urlServicio;
    }

    /**
     * Valor de busqueda de campo urlServicio
     * @param urlServicio Valor de seteo.
     */
    public void setUrlServicio(String urlServicio) {
        this.urlServicio = urlServicio;
    }

    /**
     * Tipo de comparador para la busqueda por campo urlServicio
     * @return urlServicioComparator.
     */
    public TextComparator getUrlServicioComparator() {
        return urlServicioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo urlServicio
     * @param urlServicioComparator Valor de seteo.
     */
    public void setUrlServicioComparator(TextComparator urlServicioComparator) {
        this.urlServicioComparator = urlServicioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUrlServicioIn() {
        return this.urlServicioIn;
    }

    /**
     * @param urlServicio Valor a agregar.
     */
    public void addUrlServicioIn(String urlServicio) {
        this.urlServicioIn.add(urlServicio);
    }

    /**
     * Permite buscar cuando campo urlServicio es NULL
     * @return boolean.
     */
    public boolean isUrlServicioIsNull() {
        return urlServicioIsNull;
    }

    /**
     * Permite buscar cuando campo urlServicio es NULL
     * @param urlServicioIsNull Valor de seteo.
     */
    public void setUrlServicioIsNull(boolean urlServicioIsNull) {
        this.urlServicioIsNull = urlServicioIsNull;
    }

    /**
     * Permite buscar cuando campo urlServicio es NOT NULL
     * @return boolean.
     */
    public boolean isUrlServicioIsNotNull() {
        return urlServicioIsNotNull;
    }

    /**
     * Permite buscar cuando campo urlServicio es NOT NULL
     * @param urlServicioIsNotNull Valor de seteo.
     */
    public void setUrlServicioIsNotNull(boolean urlServicioIsNotNull) {
        this.urlServicioIsNotNull = urlServicioIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombrecontacto
     * @return String.
     */
    public String getNombrecontacto() {
        if (nombrecontacto != null) {
            switch (nombrecontactoComparator) {
	            case STARTS_WITH:
	                return nombrecontacto + "%";
	            case CONTAINS:
	                return "%" + nombrecontacto + "%";
	            case ENDS_WITH:
	                return "%" + nombrecontacto;
	            case EQUALS:
                	return nombrecontacto;
              	default:
	            	break;
            }
        }
        return nombrecontacto;
    }

    /**
     * Valor de busqueda de campo nombrecontacto
     * @param nombrecontacto Valor de seteo.
     */
    public void setNombrecontacto(String nombrecontacto) {
        this.nombrecontacto = nombrecontacto;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombrecontacto
     * @return nombrecontactoComparator.
     */
    public TextComparator getNombrecontactoComparator() {
        return nombrecontactoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombrecontacto
     * @param nombrecontactoComparator Valor de seteo.
     */
    public void setNombrecontactoComparator(TextComparator nombrecontactoComparator) {
        this.nombrecontactoComparator = nombrecontactoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombrecontactoIn() {
        return this.nombrecontactoIn;
    }

    /**
     * @param nombrecontacto Valor a agregar.
     */
    public void addNombrecontactoIn(String nombrecontacto) {
        this.nombrecontactoIn.add(nombrecontacto);
    }

    /**
     * Permite buscar cuando campo nombrecontacto es NULL
     * @return boolean.
     */
    public boolean isNombrecontactoIsNull() {
        return nombrecontactoIsNull;
    }

    /**
     * Permite buscar cuando campo nombrecontacto es NULL
     * @param nombrecontactoIsNull Valor de seteo.
     */
    public void setNombrecontactoIsNull(boolean nombrecontactoIsNull) {
        this.nombrecontactoIsNull = nombrecontactoIsNull;
    }

    /**
     * Permite buscar cuando campo nombrecontacto es NOT NULL
     * @return boolean.
     */
    public boolean isNombrecontactoIsNotNull() {
        return nombrecontactoIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombrecontacto es NOT NULL
     * @param nombrecontactoIsNotNull Valor de seteo.
     */
    public void setNombrecontactoIsNotNull(boolean nombrecontactoIsNotNull) {
        this.nombrecontactoIsNotNull = nombrecontactoIsNotNull;
    }

    /**
     * Valor de busqueda de campo telefonocontacto
     * @return String.
     */
    public String getTelefonocontacto() {
        if (telefonocontacto != null) {
            switch (telefonocontactoComparator) {
	            case STARTS_WITH:
	                return telefonocontacto + "%";
	            case CONTAINS:
	                return "%" + telefonocontacto + "%";
	            case ENDS_WITH:
	                return "%" + telefonocontacto;
	            case EQUALS:
                	return telefonocontacto;
              	default:
	            	break;
            }
        }
        return telefonocontacto;
    }

    /**
     * Valor de busqueda de campo telefonocontacto
     * @param telefonocontacto Valor de seteo.
     */
    public void setTelefonocontacto(String telefonocontacto) {
        this.telefonocontacto = telefonocontacto;
    }

    /**
     * Tipo de comparador para la busqueda por campo telefonocontacto
     * @return telefonocontactoComparator.
     */
    public TextComparator getTelefonocontactoComparator() {
        return telefonocontactoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo telefonocontacto
     * @param telefonocontactoComparator Valor de seteo.
     */
    public void setTelefonocontactoComparator(TextComparator telefonocontactoComparator) {
        this.telefonocontactoComparator = telefonocontactoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTelefonocontactoIn() {
        return this.telefonocontactoIn;
    }

    /**
     * @param telefonocontacto Valor a agregar.
     */
    public void addTelefonocontactoIn(String telefonocontacto) {
        this.telefonocontactoIn.add(telefonocontacto);
    }

    /**
     * Permite buscar cuando campo telefonocontacto es NULL
     * @return boolean.
     */
    public boolean isTelefonocontactoIsNull() {
        return telefonocontactoIsNull;
    }

    /**
     * Permite buscar cuando campo telefonocontacto es NULL
     * @param telefonocontactoIsNull Valor de seteo.
     */
    public void setTelefonocontactoIsNull(boolean telefonocontactoIsNull) {
        this.telefonocontactoIsNull = telefonocontactoIsNull;
    }

    /**
     * Permite buscar cuando campo telefonocontacto es NOT NULL
     * @return boolean.
     */
    public boolean isTelefonocontactoIsNotNull() {
        return telefonocontactoIsNotNull;
    }

    /**
     * Permite buscar cuando campo telefonocontacto es NOT NULL
     * @param telefonocontactoIsNotNull Valor de seteo.
     */
    public void setTelefonocontactoIsNotNull(boolean telefonocontactoIsNotNull) {
        this.telefonocontactoIsNotNull = telefonocontactoIsNotNull;
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
     * Valor de busqueda de campo icono
     * @return String.
     */
    public String getIcono() {
        if (icono != null) {
            switch (iconoComparator) {
	            case STARTS_WITH:
	                return icono + "%";
	            case CONTAINS:
	                return "%" + icono + "%";
	            case ENDS_WITH:
	                return "%" + icono;
	            case EQUALS:
                	return icono;
              	default:
	            	break;
            }
        }
        return icono;
    }

    /**
     * Valor de busqueda de campo icono
     * @param icono Valor de seteo.
     */
    public void setIcono(String icono) {
        this.icono = icono;
    }

    /**
     * Tipo de comparador para la busqueda por campo icono
     * @return iconoComparator.
     */
    public TextComparator getIconoComparator() {
        return iconoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo icono
     * @param iconoComparator Valor de seteo.
     */
    public void setIconoComparator(TextComparator iconoComparator) {
        this.iconoComparator = iconoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getIconoIn() {
        return this.iconoIn;
    }

    /**
     * @param icono Valor a agregar.
     */
    public void addIconoIn(String icono) {
        this.iconoIn.add(icono);
    }

    /**
     * Permite buscar cuando campo icono es NULL
     * @return boolean.
     */
    public boolean isIconoIsNull() {
        return iconoIsNull;
    }

    /**
     * Permite buscar cuando campo icono es NULL
     * @param iconoIsNull Valor de seteo.
     */
    public void setIconoIsNull(boolean iconoIsNull) {
        this.iconoIsNull = iconoIsNull;
    }

    /**
     * Permite buscar cuando campo icono es NOT NULL
     * @return boolean.
     */
    public boolean isIconoIsNotNull() {
        return iconoIsNotNull;
    }

    /**
     * Permite buscar cuando campo icono es NOT NULL
     * @param iconoIsNotNull Valor de seteo.
     */
    public void setIconoIsNotNull(boolean iconoIsNotNull) {
        this.iconoIsNotNull = iconoIsNotNull;
    }

    /**
     * Valor de busqueda de campo imagen
     * @return String.
     */
    public String getImagen() {
        if (imagen != null) {
            switch (imagenComparator) {
	            case STARTS_WITH:
	                return imagen + "%";
	            case CONTAINS:
	                return "%" + imagen + "%";
	            case ENDS_WITH:
	                return "%" + imagen;
	            case EQUALS:
                	return imagen;
              	default:
	            	break;
            }
        }
        return imagen;
    }

    /**
     * Valor de busqueda de campo imagen
     * @param imagen Valor de seteo.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Tipo de comparador para la busqueda por campo imagen
     * @return imagenComparator.
     */
    public TextComparator getImagenComparator() {
        return imagenComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo imagen
     * @param imagenComparator Valor de seteo.
     */
    public void setImagenComparator(TextComparator imagenComparator) {
        this.imagenComparator = imagenComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getImagenIn() {
        return this.imagenIn;
    }

    /**
     * @param imagen Valor a agregar.
     */
    public void addImagenIn(String imagen) {
        this.imagenIn.add(imagen);
    }

    /**
     * Permite buscar cuando campo imagen es NULL
     * @return boolean.
     */
    public boolean isImagenIsNull() {
        return imagenIsNull;
    }

    /**
     * Permite buscar cuando campo imagen es NULL
     * @param imagenIsNull Valor de seteo.
     */
    public void setImagenIsNull(boolean imagenIsNull) {
        this.imagenIsNull = imagenIsNull;
    }

    /**
     * Permite buscar cuando campo imagen es NOT NULL
     * @return boolean.
     */
    public boolean isImagenIsNotNull() {
        return imagenIsNotNull;
    }

    /**
     * Permite buscar cuando campo imagen es NOT NULL
     * @param imagenIsNotNull Valor de seteo.
     */
    public void setImagenIsNotNull(boolean imagenIsNotNull) {
        this.imagenIsNotNull = imagenIsNotNull;
    }

    /**
     * Valor de busqueda de campo estado
     * @return Integer.
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * Valor de busqueda de campo estado
     * @param estado Valor de seteo.
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getEstadoIn() {
        return this.estadoIn;
    }

    /**
     * @param estado Valor a agregar.
     */
    public void addEstadoIn(Integer estado) {
        this.estadoIn.add(estado);
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getServiciosmovilesid() != null) {
            criteria.add(Restrictions.eq(SERVICIOSMOVILESID, getServiciosmovilesid()));
        }

        if (getServiciosmovilesidIn().size() > 0) {
            criteria.add(Restrictions.in(SERVICIOSMOVILESID, getServiciosmovilesidIn()));
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

        if (getUrlServicio() != null) {
            if (getUrlServicioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(URLSERVICIO, getUrlServicio()));
            } 
            else if (getUrlServicioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(URLSERVICIO, getUrlServicio()));
            }
            else if (getUrlServicioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(URLSERVICIO, getUrlServicio())));
            }
            else {
                criteria.add(Restrictions.like(URLSERVICIO, getUrlServicio()));
            }
        }

        if (getUrlServicioIn().size() > 0) {
            criteria.add(Restrictions.in(URLSERVICIO, getUrlServicioIn()));
        }

        if (isUrlServicioIsNull()) {
            criteria.add(Restrictions.isNull(URLSERVICIO));
        }

        if (isUrlServicioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(URLSERVICIO));
        }

        if (getNombrecontacto() != null) {
            if (getNombrecontactoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRECONTACTO, getNombrecontacto()));
            } 
            else if (getNombrecontactoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRECONTACTO, getNombrecontacto()));
            }
            else if (getNombrecontactoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRECONTACTO, getNombrecontacto())));
            }
            else {
                criteria.add(Restrictions.like(NOMBRECONTACTO, getNombrecontacto()));
            }
        }

        if (getNombrecontactoIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBRECONTACTO, getNombrecontactoIn()));
        }

        if (isNombrecontactoIsNull()) {
            criteria.add(Restrictions.isNull(NOMBRECONTACTO));
        }

        if (isNombrecontactoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBRECONTACTO));
        }

        if (getTelefonocontacto() != null) {
            if (getTelefonocontactoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TELEFONOCONTACTO, getTelefonocontacto()));
            } 
            else if (getTelefonocontactoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TELEFONOCONTACTO, getTelefonocontacto()));
            }
            else if (getTelefonocontactoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TELEFONOCONTACTO, getTelefonocontacto())));
            }
            else {
                criteria.add(Restrictions.like(TELEFONOCONTACTO, getTelefonocontacto()));
            }
        }

        if (getTelefonocontactoIn().size() > 0) {
            criteria.add(Restrictions.in(TELEFONOCONTACTO, getTelefonocontactoIn()));
        }

        if (isTelefonocontactoIsNull()) {
            criteria.add(Restrictions.isNull(TELEFONOCONTACTO));
        }

        if (isTelefonocontactoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TELEFONOCONTACTO));
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

        if (getIcono() != null) {
            if (getIconoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ICONO, getIcono()));
            } 
            else if (getIconoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ICONO, getIcono()));
            }
            else if (getIconoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ICONO, getIcono())));
            }
            else {
                criteria.add(Restrictions.like(ICONO, getIcono()));
            }
        }

        if (getIconoIn().size() > 0) {
            criteria.add(Restrictions.in(ICONO, getIconoIn()));
        }

        if (isIconoIsNull()) {
            criteria.add(Restrictions.isNull(ICONO));
        }

        if (isIconoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ICONO));
        }

        if (getImagen() != null) {
            if (getImagenComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(IMAGEN, getImagen()));
            } 
            else if (getImagenComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(IMAGEN, getImagen()));
            }
            else if (getImagenComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(IMAGEN, getImagen())));
            }
            else {
                criteria.add(Restrictions.like(IMAGEN, getImagen()));
            }
        }

        if (getImagenIn().size() > 0) {
            criteria.add(Restrictions.in(IMAGEN, getImagenIn()));
        }

        if (isImagenIsNull()) {
            criteria.add(Restrictions.isNull(IMAGEN));
        }

        if (isImagenIsNotNull()) {
            criteria.add(Restrictions.isNotNull(IMAGEN));
        }

        if (getEstado() != null) {
            criteria.add(Restrictions.eq(ESTADO, getEstado()));
        }

        if (getEstadoIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADO, getEstadoIn()));
        }

        if (isEstadoIsNull()) {
            criteria.add(Restrictions.isNull(ESTADO));
        }

        if (isEstadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADO));
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
 
