/*
 *
 * archivo: TblOrganismosQuery.java
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
import es.minhap.sim.model.TblOrganismos;

/**
 * Clase con criterios de busqueda para la entidad TblOrganismos
 */
public class TblOrganismosQuery extends AbstractHibernateQueryEntity<TblOrganismos> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String ORGANISMOID = "organismoid";
    public static final String DIR3 = "dir3";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String ACTIVO = "activo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String EXTERNALID = "externalid";
    public static final String NOMBRECUENTAENVIO = "nombrecuentaenvio";
    public static final String HISTORIFICACION = "historificacion";
    public static final String MOTIVOHISTORIFICACION = "motivohistorificacion";
    public static final String CONSERVACION = "conservacion";
    public static final String MOTIVOCONSERVACION = "motivoconservacion";
    public static final String ELIMINADO = "eliminado";


    /**
     * Valor de busqueda de campo organismoid
     */
    private Long organismoid;

    /**
     * Lista de valores del campo organismoid para busquedas tipo IN
     */
    private List<Long> organismoidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo dir3
     */
    private String dir3;

    /**
     * Tipo de comparador para la busqueda por campo dir3
     */
    private TextComparator dir3Comparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo dir3 para busquedas tipo IN
     */
    private List<String> dir3In = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo dir3 es NULL
     */
    private boolean dir3IsNull = false;

    /**
     * Permite buscar cuando campo dir3 es NOT NULL
     */
    private boolean dir3IsNotNull = false;

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
     * Valor de busqueda de campo externalid
     */
    private Long externalid;

    /**
     * Lista de valores del campo externalid para busquedas tipo IN
     */
    private List<Long> externalidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo externalid es NULL
     */
    private boolean externalidIsNull = false;

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     */
    private boolean externalidIsNotNull = false;

    /**
     * Valor de busqueda de campo nombrecuentaenvio
     */
    private String nombrecuentaenvio;

    /**
     * Tipo de comparador para la busqueda por campo nombrecuentaenvio
     */
    private TextComparator nombrecuentaenvioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombrecuentaenvio para busquedas tipo IN
     */
    private List<String> nombrecuentaenvioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NULL
     */
    private boolean nombrecuentaenvioIsNull = false;

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NOT NULL
     */
    private boolean nombrecuentaenvioIsNotNull = false;

    /**
     * Valor de busqueda de campo historificacion
     */
    private Integer historificacion;

    /**
     * Lista de valores del campo historificacion para busquedas tipo IN
     */
    private List<Integer> historificacionIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo historificacion es NULL
     */
    private boolean historificacionIsNull = false;

    /**
     * Permite buscar cuando campo historificacion es NOT NULL
     */
    private boolean historificacionIsNotNull = false;

    /**
     * Valor de busqueda de campo motivohistorificacion
     */
    private String motivohistorificacion;

    /**
     * Tipo de comparador para la busqueda por campo motivohistorificacion
     */
    private TextComparator motivohistorificacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo motivohistorificacion para busquedas tipo IN
     */
    private List<String> motivohistorificacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo motivohistorificacion es NULL
     */
    private boolean motivohistorificacionIsNull = false;

    /**
     * Permite buscar cuando campo motivohistorificacion es NOT NULL
     */
    private boolean motivohistorificacionIsNotNull = false;

    /**
     * Valor de busqueda de campo conservacion
     */
    private Integer conservacion;

    /**
     * Lista de valores del campo conservacion para busquedas tipo IN
     */
    private List<Integer> conservacionIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo conservacion es NULL
     */
    private boolean conservacionIsNull = false;

    /**
     * Permite buscar cuando campo conservacion es NOT NULL
     */
    private boolean conservacionIsNotNull = false;

    /**
     * Valor de busqueda de campo motivoconservacion
     */
    private String motivoconservacion;

    /**
     * Tipo de comparador para la busqueda por campo motivoconservacion
     */
    private TextComparator motivoconservacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo motivoconservacion para busquedas tipo IN
     */
    private List<String> motivoconservacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo motivoconservacion es NULL
     */
    private boolean motivoconservacionIsNull = false;

    /**
     * Permite buscar cuando campo motivoconservacion es NOT NULL
     */
    private boolean motivoconservacionIsNotNull = false;

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
     * Constructor default
     */
    public TblOrganismosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblOrganismosQuery(Long organismoid) {
        setOrganismoid(organismoid);
    }

    /**
     * Valor de busqueda de campo organismoid
     * @return Long.
     */
    public Long getOrganismoid() {
        return organismoid;
    }

    /**
     * Valor de busqueda de campo organismoid
     * @param organismoid Valor de seteo.
     */
    public void setOrganismoid(Long organismoid) {
        this.organismoid = organismoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getOrganismoidIn() {
        return this.organismoidIn;
    }

    /**
     * @param organismoid Valor a agregar.
     */
    public void addOrganismoidIn(Long organismoid) {
        this.organismoidIn.add(organismoid);
    }

    /**
     * Valor de busqueda de campo dir3
     * @return String.
     */
    public String getDir3() {
        if (dir3 != null) {
            switch (dir3Comparator) {
	            case STARTS_WITH:
	                return dir3 + "%";
	            case CONTAINS:
	                return "%" + dir3 + "%";
	            case ENDS_WITH:
	                return "%" + dir3;
	            case EQUALS:
                	return dir3;
              	default:
	            	break;
            }
        }
        return dir3;
    }

    /**
     * Valor de busqueda de campo dir3
     * @param dir3 Valor de seteo.
     */
    public void setDir3(String dir3) {
        this.dir3 = dir3;
    }

    /**
     * Tipo de comparador para la busqueda por campo dir3
     * @return dir3Comparator.
     */
    public TextComparator getDir3Comparator() {
        return dir3Comparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo dir3
     * @param dir3Comparator Valor de seteo.
     */
    public void setDir3Comparator(TextComparator dir3Comparator) {
        this.dir3Comparator = dir3Comparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDir3In() {
        return this.dir3In;
    }

    /**
     * @param dir3 Valor a agregar.
     */
    public void addDir3In(String dir3) {
        this.dir3In.add(dir3);
    }

    /**
     * Permite buscar cuando campo dir3 es NULL
     * @return boolean.
     */
    public boolean isDir3IsNull() {
        return dir3IsNull;
    }

    /**
     * Permite buscar cuando campo dir3 es NULL
     * @param dir3IsNull Valor de seteo.
     */
    public void setDir3IsNull(boolean dir3IsNull) {
        this.dir3IsNull = dir3IsNull;
    }

    /**
     * Permite buscar cuando campo dir3 es NOT NULL
     * @return boolean.
     */
    public boolean isDir3IsNotNull() {
        return dir3IsNotNull;
    }

    /**
     * Permite buscar cuando campo dir3 es NOT NULL
     * @param dir3IsNotNull Valor de seteo.
     */
    public void setDir3IsNotNull(boolean dir3IsNotNull) {
        this.dir3IsNotNull = dir3IsNotNull;
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
     * Valor de busqueda de campo externalid
     * @return Long.
     */
    public Long getExternalid() {
        return externalid;
    }

    /**
     * Valor de busqueda de campo externalid
     * @param externalid Valor de seteo.
     */
    public void setExternalid(Long externalid) {
        this.externalid = externalid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getExternalidIn() {
        return this.externalidIn;
    }

    /**
     * @param externalid Valor a agregar.
     */
    public void addExternalidIn(Long externalid) {
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
     * Valor de busqueda de campo nombrecuentaenvio
     * @return String.
     */
    public String getNombrecuentaenvio() {
        if (nombrecuentaenvio != null) {
            switch (nombrecuentaenvioComparator) {
	            case STARTS_WITH:
	                return nombrecuentaenvio + "%";
	            case CONTAINS:
	                return "%" + nombrecuentaenvio + "%";
	            case ENDS_WITH:
	                return "%" + nombrecuentaenvio;
	            case EQUALS:
                	return nombrecuentaenvio;
              	default:
	            	break;
            }
        }
        return nombrecuentaenvio;
    }

    /**
     * Valor de busqueda de campo nombrecuentaenvio
     * @param nombrecuentaenvio Valor de seteo.
     */
    public void setNombrecuentaenvio(String nombrecuentaenvio) {
        this.nombrecuentaenvio = nombrecuentaenvio;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombrecuentaenvio
     * @return nombrecuentaenvioComparator.
     */
    public TextComparator getNombrecuentaenvioComparator() {
        return nombrecuentaenvioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombrecuentaenvio
     * @param nombrecuentaenvioComparator Valor de seteo.
     */
    public void setNombrecuentaenvioComparator(TextComparator nombrecuentaenvioComparator) {
        this.nombrecuentaenvioComparator = nombrecuentaenvioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombrecuentaenvioIn() {
        return this.nombrecuentaenvioIn;
    }

    /**
     * @param nombrecuentaenvio Valor a agregar.
     */
    public void addNombrecuentaenvioIn(String nombrecuentaenvio) {
        this.nombrecuentaenvioIn.add(nombrecuentaenvio);
    }

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NULL
     * @return boolean.
     */
    public boolean isNombrecuentaenvioIsNull() {
        return nombrecuentaenvioIsNull;
    }

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NULL
     * @param nombrecuentaenvioIsNull Valor de seteo.
     */
    public void setNombrecuentaenvioIsNull(boolean nombrecuentaenvioIsNull) {
        this.nombrecuentaenvioIsNull = nombrecuentaenvioIsNull;
    }

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NOT NULL
     * @return boolean.
     */
    public boolean isNombrecuentaenvioIsNotNull() {
        return nombrecuentaenvioIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NOT NULL
     * @param nombrecuentaenvioIsNotNull Valor de seteo.
     */
    public void setNombrecuentaenvioIsNotNull(boolean nombrecuentaenvioIsNotNull) {
        this.nombrecuentaenvioIsNotNull = nombrecuentaenvioIsNotNull;
    }

    /**
     * Valor de busqueda de campo historificacion
     * @return Integer.
     */
    public Integer getHistorificacion() {
        return historificacion;
    }

    /**
     * Valor de busqueda de campo historificacion
     * @param historificacion Valor de seteo.
     */
    public void setHistorificacion(Integer historificacion) {
        this.historificacion = historificacion;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getHistorificacionIn() {
        return this.historificacionIn;
    }

    /**
     * @param historificacion Valor a agregar.
     */
    public void addHistorificacionIn(Integer historificacion) {
        this.historificacionIn.add(historificacion);
    }

    /**
     * Permite buscar cuando campo historificacion es NULL
     * @return boolean.
     */
    public boolean isHistorificacionIsNull() {
        return historificacionIsNull;
    }

    /**
     * Permite buscar cuando campo historificacion es NULL
     * @param historificacionIsNull Valor de seteo.
     */
    public void setHistorificacionIsNull(boolean historificacionIsNull) {
        this.historificacionIsNull = historificacionIsNull;
    }

    /**
     * Permite buscar cuando campo historificacion es NOT NULL
     * @return boolean.
     */
    public boolean isHistorificacionIsNotNull() {
        return historificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo historificacion es NOT NULL
     * @param historificacionIsNotNull Valor de seteo.
     */
    public void setHistorificacionIsNotNull(boolean historificacionIsNotNull) {
        this.historificacionIsNotNull = historificacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo motivohistorificacion
     * @return String.
     */
    public String getMotivohistorificacion() {
        if (motivohistorificacion != null) {
            switch (motivohistorificacionComparator) {
	            case STARTS_WITH:
	                return motivohistorificacion + "%";
	            case CONTAINS:
	                return "%" + motivohistorificacion + "%";
	            case ENDS_WITH:
	                return "%" + motivohistorificacion;
	            case EQUALS:
                	return motivohistorificacion;
              	default:
	            	break;
            }
        }
        return motivohistorificacion;
    }

    /**
     * Valor de busqueda de campo motivohistorificacion
     * @param motivohistorificacion Valor de seteo.
     */
    public void setMotivohistorificacion(String motivohistorificacion) {
        this.motivohistorificacion = motivohistorificacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo motivohistorificacion
     * @return motivohistorificacionComparator.
     */
    public TextComparator getMotivohistorificacionComparator() {
        return motivohistorificacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo motivohistorificacion
     * @param motivohistorificacionComparator Valor de seteo.
     */
    public void setMotivohistorificacionComparator(TextComparator motivohistorificacionComparator) {
        this.motivohistorificacionComparator = motivohistorificacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMotivohistorificacionIn() {
        return this.motivohistorificacionIn;
    }

    /**
     * @param motivohistorificacion Valor a agregar.
     */
    public void addMotivohistorificacionIn(String motivohistorificacion) {
        this.motivohistorificacionIn.add(motivohistorificacion);
    }

    /**
     * Permite buscar cuando campo motivohistorificacion es NULL
     * @return boolean.
     */
    public boolean isMotivohistorificacionIsNull() {
        return motivohistorificacionIsNull;
    }

    /**
     * Permite buscar cuando campo motivohistorificacion es NULL
     * @param motivohistorificacionIsNull Valor de seteo.
     */
    public void setMotivohistorificacionIsNull(boolean motivohistorificacionIsNull) {
        this.motivohistorificacionIsNull = motivohistorificacionIsNull;
    }

    /**
     * Permite buscar cuando campo motivohistorificacion es NOT NULL
     * @return boolean.
     */
    public boolean isMotivohistorificacionIsNotNull() {
        return motivohistorificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo motivohistorificacion es NOT NULL
     * @param motivohistorificacionIsNotNull Valor de seteo.
     */
    public void setMotivohistorificacionIsNotNull(boolean motivohistorificacionIsNotNull) {
        this.motivohistorificacionIsNotNull = motivohistorificacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo conservacion
     * @return Integer.
     */
    public Integer getConservacion() {
        return conservacion;
    }

    /**
     * Valor de busqueda de campo conservacion
     * @param conservacion Valor de seteo.
     */
    public void setConservacion(Integer conservacion) {
        this.conservacion = conservacion;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getConservacionIn() {
        return this.conservacionIn;
    }

    /**
     * @param conservacion Valor a agregar.
     */
    public void addConservacionIn(Integer conservacion) {
        this.conservacionIn.add(conservacion);
    }

    /**
     * Permite buscar cuando campo conservacion es NULL
     * @return boolean.
     */
    public boolean isConservacionIsNull() {
        return conservacionIsNull;
    }

    /**
     * Permite buscar cuando campo conservacion es NULL
     * @param conservacionIsNull Valor de seteo.
     */
    public void setConservacionIsNull(boolean conservacionIsNull) {
        this.conservacionIsNull = conservacionIsNull;
    }

    /**
     * Permite buscar cuando campo conservacion es NOT NULL
     * @return boolean.
     */
    public boolean isConservacionIsNotNull() {
        return conservacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo conservacion es NOT NULL
     * @param conservacionIsNotNull Valor de seteo.
     */
    public void setConservacionIsNotNull(boolean conservacionIsNotNull) {
        this.conservacionIsNotNull = conservacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo motivoconservacion
     * @return String.
     */
    public String getMotivoconservacion() {
        if (motivoconservacion != null) {
            switch (motivoconservacionComparator) {
	            case STARTS_WITH:
	                return motivoconservacion + "%";
	            case CONTAINS:
	                return "%" + motivoconservacion + "%";
	            case ENDS_WITH:
	                return "%" + motivoconservacion;
	            case EQUALS:
                	return motivoconservacion;
              	default:
	            	break;
            }
        }
        return motivoconservacion;
    }

    /**
     * Valor de busqueda de campo motivoconservacion
     * @param motivoconservacion Valor de seteo.
     */
    public void setMotivoconservacion(String motivoconservacion) {
        this.motivoconservacion = motivoconservacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo motivoconservacion
     * @return motivoconservacionComparator.
     */
    public TextComparator getMotivoconservacionComparator() {
        return motivoconservacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo motivoconservacion
     * @param motivoconservacionComparator Valor de seteo.
     */
    public void setMotivoconservacionComparator(TextComparator motivoconservacionComparator) {
        this.motivoconservacionComparator = motivoconservacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMotivoconservacionIn() {
        return this.motivoconservacionIn;
    }

    /**
     * @param motivoconservacion Valor a agregar.
     */
    public void addMotivoconservacionIn(String motivoconservacion) {
        this.motivoconservacionIn.add(motivoconservacion);
    }

    /**
     * Permite buscar cuando campo motivoconservacion es NULL
     * @return boolean.
     */
    public boolean isMotivoconservacionIsNull() {
        return motivoconservacionIsNull;
    }

    /**
     * Permite buscar cuando campo motivoconservacion es NULL
     * @param motivoconservacionIsNull Valor de seteo.
     */
    public void setMotivoconservacionIsNull(boolean motivoconservacionIsNull) {
        this.motivoconservacionIsNull = motivoconservacionIsNull;
    }

    /**
     * Permite buscar cuando campo motivoconservacion es NOT NULL
     * @return boolean.
     */
    public boolean isMotivoconservacionIsNotNull() {
        return motivoconservacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo motivoconservacion es NOT NULL
     * @param motivoconservacionIsNotNull Valor de seteo.
     */
    public void setMotivoconservacionIsNotNull(boolean motivoconservacionIsNotNull) {
        this.motivoconservacionIsNotNull = motivoconservacionIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getOrganismoid() != null) {
            criteria.add(Restrictions.eq(ORGANISMOID, getOrganismoid()));
        }

        if (getOrganismoidIn().size() > 0) {
            criteria.add(Restrictions.in(ORGANISMOID, getOrganismoidIn()));
        }

        if (getDir3() != null) {
            if (getDir3Comparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DIR3, getDir3()));
            } 
            else if (getDir3Comparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DIR3, getDir3()));
            }
            else if (getDir3Comparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DIR3, getDir3())));
            }
            else {
                criteria.add(Restrictions.like(DIR3, getDir3()));
            }
        }

        if (getDir3In().size() > 0) {
            criteria.add(Restrictions.in(DIR3, getDir3In()));
        }

        if (isDir3IsNull()) {
            criteria.add(Restrictions.isNull(DIR3));
        }

        if (isDir3IsNotNull()) {
            criteria.add(Restrictions.isNotNull(DIR3));
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

        if (getExternalid() != null) {
            criteria.add(Restrictions.eq(EXTERNALID, getExternalid()));
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

        if (getNombrecuentaenvio() != null) {
            if (getNombrecuentaenvioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRECUENTAENVIO, getNombrecuentaenvio()));
            } 
            else if (getNombrecuentaenvioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRECUENTAENVIO, getNombrecuentaenvio()));
            }
            else if (getNombrecuentaenvioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRECUENTAENVIO, getNombrecuentaenvio())));
            }
            else {
                criteria.add(Restrictions.like(NOMBRECUENTAENVIO, getNombrecuentaenvio()));
            }
        }

        if (getNombrecuentaenvioIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBRECUENTAENVIO, getNombrecuentaenvioIn()));
        }

        if (isNombrecuentaenvioIsNull()) {
            criteria.add(Restrictions.isNull(NOMBRECUENTAENVIO));
        }

        if (isNombrecuentaenvioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBRECUENTAENVIO));
        }

        if (getHistorificacion() != null) {
            criteria.add(Restrictions.eq(HISTORIFICACION, getHistorificacion()));
        }

        if (getHistorificacionIn().size() > 0) {
            criteria.add(Restrictions.in(HISTORIFICACION, getHistorificacionIn()));
        }

        if (isHistorificacionIsNull()) {
            criteria.add(Restrictions.isNull(HISTORIFICACION));
        }

        if (isHistorificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(HISTORIFICACION));
        }

        if (getMotivohistorificacion() != null) {
            if (getMotivohistorificacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MOTIVOHISTORIFICACION, getMotivohistorificacion()));
            } 
            else if (getMotivohistorificacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MOTIVOHISTORIFICACION, getMotivohistorificacion()));
            }
            else if (getMotivohistorificacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MOTIVOHISTORIFICACION, getMotivohistorificacion())));
            }
            else {
                criteria.add(Restrictions.like(MOTIVOHISTORIFICACION, getMotivohistorificacion()));
            }
        }

        if (getMotivohistorificacionIn().size() > 0) {
            criteria.add(Restrictions.in(MOTIVOHISTORIFICACION, getMotivohistorificacionIn()));
        }

        if (isMotivohistorificacionIsNull()) {
            criteria.add(Restrictions.isNull(MOTIVOHISTORIFICACION));
        }

        if (isMotivohistorificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MOTIVOHISTORIFICACION));
        }

        if (getConservacion() != null) {
            criteria.add(Restrictions.eq(CONSERVACION, getConservacion()));
        }

        if (getConservacionIn().size() > 0) {
            criteria.add(Restrictions.in(CONSERVACION, getConservacionIn()));
        }

        if (isConservacionIsNull()) {
            criteria.add(Restrictions.isNull(CONSERVACION));
        }

        if (isConservacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CONSERVACION));
        }

        if (getMotivoconservacion() != null) {
            if (getMotivoconservacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MOTIVOCONSERVACION, getMotivoconservacion()));
            } 
            else if (getMotivoconservacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MOTIVOCONSERVACION, getMotivoconservacion()));
            }
            else if (getMotivoconservacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MOTIVOCONSERVACION, getMotivoconservacion())));
            }
            else {
                criteria.add(Restrictions.like(MOTIVOCONSERVACION, getMotivoconservacion()));
            }
        }

        if (getMotivoconservacionIn().size() > 0) {
            criteria.add(Restrictions.in(MOTIVOCONSERVACION, getMotivoconservacionIn()));
        }

        if (isMotivoconservacionIsNull()) {
            criteria.add(Restrictions.isNull(MOTIVOCONSERVACION));
        }

        if (isMotivoconservacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MOTIVOCONSERVACION));
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
            criteria.add(Restrictions.isNull(ELIMINADO));
        }

        if (isEliminadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ELIMINADO));
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
 
