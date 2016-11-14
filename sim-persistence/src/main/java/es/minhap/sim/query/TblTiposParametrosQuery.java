/*
 *
 * archivo: TblTiposParametrosQuery.java
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
import es.minhap.sim.model.TblTiposParametros;

/**
 * Clase con criterios de busqueda para la entidad TblTiposParametros
 */
public class TblTiposParametrosQuery extends AbstractHibernateQueryEntity<TblTiposParametros> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String TIPOPARAMETROID = "tipoparametroid";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String ACTIVO = "activo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String TAGS = "tags";
    public static final String TIPO = "tipo";
    public static final String EXTERNALID = "externalid";
    public static final String TIPOCAMPO = "tipocampo";


    /**
     * Valor de busqueda de campo tipoparametroid
     */
    private Long tipoparametroid;

    /**
     * Lista de valores del campo tipoparametroid para busquedas tipo IN
     */
    private List<Long> tipoparametroidIn = new ArrayList<Long>(0);

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
    private boolean activo;

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
     * Valor de busqueda de campo tags
     */
    private String tags;

    /**
     * Tipo de comparador para la busqueda por campo tags
     */
    private TextComparator tagsComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo tags para busquedas tipo IN
     */
    private List<String> tagsIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo tags es NULL
     */
    private boolean tagsIsNull = false;

    /**
     * Permite buscar cuando campo tags es NOT NULL
     */
    private boolean tagsIsNotNull = false;

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
     * Valor de busqueda de campo tipocampo
     */
    private String tipocampo;

    /**
     * Tipo de comparador para la busqueda por campo tipocampo
     */
    private TextComparator tipocampoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo tipocampo para busquedas tipo IN
     */
    private List<String> tipocampoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo tipocampo es NULL
     */
    private boolean tipocampoIsNull = false;

    /**
     * Permite buscar cuando campo tipocampo es NOT NULL
     */
    private boolean tipocampoIsNotNull = false;

    /**
     * Constructor default
     */
    public TblTiposParametrosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblTiposParametrosQuery(Long tipoparametroid) {
        setTipoparametroid(tipoparametroid);
    }

    /**
     * Valor de busqueda de campo tipoparametroid
     * @return Long.
     */
    public Long getTipoparametroid() {
        return tipoparametroid;
    }

    /**
     * Valor de busqueda de campo tipoparametroid
     * @param tipoparametroid Valor de seteo.
     */
    public void setTipoparametroid(Long tipoparametroid) {
        this.tipoparametroid = tipoparametroid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getTipoparametroidIn() {
        return this.tipoparametroidIn;
    }

    /**
     * @param tipoparametroid Valor a agregar.
     */
    public void addTipoparametroidIn(Long tipoparametroid) {
        this.tipoparametroidIn.add(tipoparametroid);
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
     * @return boolean.
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
     * Valor de busqueda de campo tags
     * @return String.
     */
    public String getTags() {
        if (tags != null) {
            switch (tagsComparator) {
	            case STARTS_WITH:
	                return tags + "%";
	            case CONTAINS:
	                return "%" + tags + "%";
	            case ENDS_WITH:
	                return "%" + tags;
	            case EQUALS:
                	return tags;
              	default:
	            	break;
            }
        }
        return tags;
    }

    /**
     * Valor de busqueda de campo tags
     * @param tags Valor de seteo.
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * Tipo de comparador para la busqueda por campo tags
     * @return tagsComparator.
     */
    public TextComparator getTagsComparator() {
        return tagsComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo tags
     * @param tagsComparator Valor de seteo.
     */
    public void setTagsComparator(TextComparator tagsComparator) {
        this.tagsComparator = tagsComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTagsIn() {
        return this.tagsIn;
    }

    /**
     * @param tags Valor a agregar.
     */
    public void addTagsIn(String tags) {
        this.tagsIn.add(tags);
    }

    /**
     * Permite buscar cuando campo tags es NULL
     * @return boolean.
     */
    public boolean isTagsIsNull() {
        return tagsIsNull;
    }

    /**
     * Permite buscar cuando campo tags es NULL
     * @param tagsIsNull Valor de seteo.
     */
    public void setTagsIsNull(boolean tagsIsNull) {
        this.tagsIsNull = tagsIsNull;
    }

    /**
     * Permite buscar cuando campo tags es NOT NULL
     * @return boolean.
     */
    public boolean isTagsIsNotNull() {
        return tagsIsNotNull;
    }

    /**
     * Permite buscar cuando campo tags es NOT NULL
     * @param tagsIsNotNull Valor de seteo.
     */
    public void setTagsIsNotNull(boolean tagsIsNotNull) {
        this.tagsIsNotNull = tagsIsNotNull;
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
     * Valor de busqueda de campo tipocampo
     * @return String.
     */
    public String getTipocampo() {
        if (tipocampo != null) {
            switch (tipocampoComparator) {
	            case STARTS_WITH:
	                return tipocampo + "%";
	            case CONTAINS:
	                return "%" + tipocampo + "%";
	            case ENDS_WITH:
	                return "%" + tipocampo;
	            case EQUALS:
                	return tipocampo;
              	default:
	            	break;
            }
        }
        return tipocampo;
    }

    /**
     * Valor de busqueda de campo tipocampo
     * @param tipocampo Valor de seteo.
     */
    public void setTipocampo(String tipocampo) {
        this.tipocampo = tipocampo;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipocampo
     * @return tipocampoComparator.
     */
    public TextComparator getTipocampoComparator() {
        return tipocampoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipocampo
     * @param tipocampoComparator Valor de seteo.
     */
    public void setTipocampoComparator(TextComparator tipocampoComparator) {
        this.tipocampoComparator = tipocampoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTipocampoIn() {
        return this.tipocampoIn;
    }

    /**
     * @param tipocampo Valor a agregar.
     */
    public void addTipocampoIn(String tipocampo) {
        this.tipocampoIn.add(tipocampo);
    }

    /**
     * Permite buscar cuando campo tipocampo es NULL
     * @return boolean.
     */
    public boolean isTipocampoIsNull() {
        return tipocampoIsNull;
    }

    /**
     * Permite buscar cuando campo tipocampo es NULL
     * @param tipocampoIsNull Valor de seteo.
     */
    public void setTipocampoIsNull(boolean tipocampoIsNull) {
        this.tipocampoIsNull = tipocampoIsNull;
    }

    /**
     * Permite buscar cuando campo tipocampo es NOT NULL
     * @return boolean.
     */
    public boolean isTipocampoIsNotNull() {
        return tipocampoIsNotNull;
    }

    /**
     * Permite buscar cuando campo tipocampo es NOT NULL
     * @param tipocampoIsNotNull Valor de seteo.
     */
    public void setTipocampoIsNotNull(boolean tipocampoIsNotNull) {
        this.tipocampoIsNotNull = tipocampoIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getTipoparametroid() != null) {
            criteria.add(Restrictions.eq(TIPOPARAMETROID, getTipoparametroid()));
        }

        if (getTipoparametroidIn().size() > 0) {
            criteria.add(Restrictions.in(TIPOPARAMETROID, getTipoparametroidIn()));
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

        if (getTags() != null) {
            if (getTagsComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TAGS, getTags()));
            } 
            else if (getTagsComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TAGS, getTags()));
            }
            else if (getTagsComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TAGS, getTags())));
            }
            else {
                criteria.add(Restrictions.like(TAGS, getTags()));
            }
        }

        if (getTagsIn().size() > 0) {
            criteria.add(Restrictions.in(TAGS, getTagsIn()));
        }

        if (isTagsIsNull()) {
            criteria.add(Restrictions.isNull(TAGS));
        }

        if (isTagsIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TAGS));
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

        if (getTipocampo() != null) {
            if (getTipocampoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TIPOCAMPO, getTipocampo()));
            } 
            else if (getTipocampoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TIPOCAMPO, getTipocampo()));
            }
            else if (getTipocampoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TIPOCAMPO, getTipocampo())));
            }
            else {
                criteria.add(Restrictions.like(TIPOCAMPO, getTipocampo()));
            }
        }

        if (getTipocampoIn().size() > 0) {
            criteria.add(Restrictions.in(TIPOCAMPO, getTipocampoIn()));
        }

        if (isTipocampoIsNull()) {
            criteria.add(Restrictions.isNull(TIPOCAMPO));
        }

        if (isTipocampoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TIPOCAMPO));
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
 
