/*
 *
 * archivo: TblAdjuntosHistQuery.java
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
import es.minhap.sim.model.TblAdjuntosHist;

/**
 * Clase con criterios de busqueda para la entidad TblAdjuntosHist
 */
public class TblAdjuntosHistQuery extends
		AbstractHibernateQueryEntity<TblAdjuntosHist> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
	private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";

	// Constantes para ser utilizadas como nombres de campos, para evitar
	// problemas de compilacion
	public static final String ADJUNTOID = "adjuntoid";
	public static final String NOMBRE = "nombre";
	public static final String FECHACREACION = "fechacreacion";
	public static final String CREADOPOR = "creadopor";
	public static final String FECHAMODIFICACION = "fechamodificacion";
	public static final String MODIFICADOPOR = "modificadopor";
	public static final String IMAGEN = "imagen";
	public static final String FECHAHISTORIFICACION = "fechahistorificacion";
	 public static final String CONTENIDOFILE = "contenidofile";
	/**
	 * Valor de busqueda de campo adjuntoid
	 */
	private Long adjuntoid;

	/**
	 * Lista de valores del campo adjuntoid para busquedas tipo IN
	 */
	private List<Long> adjuntoidIn = new ArrayList<Long>(0);

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
	 * Valor de busqueda de campo imagen
	 */
	private Long imagen;

	/**
	 * Lista de valores del campo imagen para busquedas tipo IN
	 */
	private List<Long> imagenIn = new ArrayList<Long>(0);

	/**
	 * Permite buscar cuando campo imagen es NULL
	 */
	private boolean imagenIsNull = false;

	/**
	 * Permite buscar cuando campo imagen es NOT NULL
	 */
	private boolean imagenIsNotNull = false;

	/**
	 * Valor inferior de rango de busqueda de fecha fechahistorificacion
	 */
	private Date fechahistorificacionMin;

	/**
	 * Valor superior de rango de busqueda de fecha fechahistorificacion
	 */
	private Date fechahistorificacionMax;

	/**
	 * Permite buscar cuando campo fechahistorificacion es NULL
	 */
	private boolean fechahistorificacionIsNull = false;

	/**
	 * Permite buscar cuando campo fechahistorificacion es NOT NULL
	 */
	private boolean fechahistorificacionIsNotNull = false;

	 /**
     * Valor de busqueda de campo contenidofile
     */
    private String contenidofile;

    /**
     * Tipo de comparador para la busqueda por campo contenidofile
     */
    private TextComparator contenidofileComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo contenidofile para busquedas tipo IN
     */
    private List<String> contenidofileIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo contenidofile es NULL
     */
    private boolean contenidofileIsNull = false;

    /**
     * Permite buscar cuando campo contenidofile es NOT NULL
     */
    private boolean contenidofileIsNotNull = false;
	
	/**
	 * Constructor default
	 */
	public TblAdjuntosHistQuery() {

	}

	/**
	 * Constructor usando identificador
	 */
	public TblAdjuntosHistQuery(Long adjuntoid) {
		setAdjuntoid(adjuntoid);
	}

	/**
	 * Valor de busqueda de campo adjuntoid
	 * 
	 * @return Long.
	 */
	public Long getAdjuntoid() {
		return adjuntoid;
	}

	/**
	 * Valor de busqueda de campo adjuntoid
	 * 
	 * @param adjuntoid
	 *            Valor de seteo.
	 */
	public void setAdjuntoid(Long adjuntoid) {
		this.adjuntoid = adjuntoid;
	}

	/**
	 * @return List<Long>.
	 */
	public List<Long> getAdjuntoidIn() {
		return this.adjuntoidIn;
	}

	/**
	 * @param adjuntoid
	 *            Valor a agregar.
	 */
	public void addAdjuntoidIn(Long adjuntoid) {
		this.adjuntoidIn.add(adjuntoid);
	}

	/**
	 * Valor de busqueda de campo nombre
	 * 
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
	 * 
	 * @param nombre
	 *            Valor de seteo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Tipo de comparador para la busqueda por campo nombre
	 * 
	 * @return nombreComparator.
	 */
	public TextComparator getNombreComparator() {
		return nombreComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo nombre
	 * 
	 * @param nombreComparator
	 *            Valor de seteo.
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
	 * @param nombre
	 *            Valor a agregar.
	 */
	public void addNombreIn(String nombre) {
		this.nombreIn.add(nombre);
	}

	/**
	 * Permite buscar cuando campo nombre es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNombreIsNull() {
		return nombreIsNull;
	}

	/**
	 * Permite buscar cuando campo nombre es NULL
	 * 
	 * @param nombreIsNull
	 *            Valor de seteo.
	 */
	public void setNombreIsNull(boolean nombreIsNull) {
		this.nombreIsNull = nombreIsNull;
	}

	/**
	 * Permite buscar cuando campo nombre es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNombreIsNotNull() {
		return nombreIsNotNull;
	}

	/**
	 * Permite buscar cuando campo nombre es NOT NULL
	 * 
	 * @param nombreIsNotNull
	 *            Valor de seteo.
	 */
	public void setNombreIsNotNull(boolean nombreIsNotNull) {
		this.nombreIsNotNull = nombreIsNotNull;
	}

	/**
	 * Valor inferior de rango de busqueda de fecha fechacreacion
	 * 
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
	 * 
	 * @param fechacreacionMin
	 *            Valor de seteo.
	 */
	public void setFechacreacionMin(Date fechacreacionMin) {
		this.fechacreacionMin = fechacreacionMin;
	}

	/**
	 * Valor superior de rango de busqueda de fecha fechacreacion
	 * 
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
	 * 
	 * @param fechacreacionMax
	 *            Valor de seteo.
	 */
	public void setFechacreacionMax(Date fechacreacionMax) {
		this.fechacreacionMax = fechacreacionMax;
	}

	/**
	 * Permite buscar cuando campo fechacreacion es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechacreacionIsNull() {
		return fechacreacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechacreacion es NULL
	 * 
	 * @param fechacreacionIsNull
	 *            Valor de seteo.
	 */
	public void setFechacreacionIsNull(boolean fechacreacionIsNull) {
		this.fechacreacionIsNull = fechacreacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechacreacion es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechacreacionIsNotNull() {
		return fechacreacionIsNotNull;
	}

	/**
	 * Permite buscar cuando campo fechacreacion es NOT NULL
	 * 
	 * @param fechacreacionIsNotNull
	 *            Valor de seteo.
	 */
	public void setFechacreacionIsNotNull(boolean fechacreacionIsNotNull) {
		this.fechacreacionIsNotNull = fechacreacionIsNotNull;
	}

	/**
	 * Valor de busqueda de campo creadopor
	 * 
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
	 * 
	 * @param creadopor
	 *            Valor de seteo.
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * Tipo de comparador para la busqueda por campo creadopor
	 * 
	 * @return creadoporComparator.
	 */
	public TextComparator getCreadoporComparator() {
		return creadoporComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo creadopor
	 * 
	 * @param creadoporComparator
	 *            Valor de seteo.
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
	 * @param creadopor
	 *            Valor a agregar.
	 */
	public void addCreadoporIn(String creadopor) {
		this.creadoporIn.add(creadopor);
	}

	/**
	 * Permite buscar cuando campo creadopor es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCreadoporIsNull() {
		return creadoporIsNull;
	}

	/**
	 * Permite buscar cuando campo creadopor es NULL
	 * 
	 * @param creadoporIsNull
	 *            Valor de seteo.
	 */
	public void setCreadoporIsNull(boolean creadoporIsNull) {
		this.creadoporIsNull = creadoporIsNull;
	}

	/**
	 * Permite buscar cuando campo creadopor es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCreadoporIsNotNull() {
		return creadoporIsNotNull;
	}

	/**
	 * Permite buscar cuando campo creadopor es NOT NULL
	 * 
	 * @param creadoporIsNotNull
	 *            Valor de seteo.
	 */
	public void setCreadoporIsNotNull(boolean creadoporIsNotNull) {
		this.creadoporIsNotNull = creadoporIsNotNull;
	}

	/**
	 * Valor inferior de rango de busqueda de fecha fechamodificacion
	 * 
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
	 * 
	 * @param fechamodificacionMin
	 *            Valor de seteo.
	 */
	public void setFechamodificacionMin(Date fechamodificacionMin) {
		this.fechamodificacionMin = fechamodificacionMin;
	}

	/**
	 * Valor superior de rango de busqueda de fecha fechamodificacion
	 * 
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
	 * 
	 * @param fechamodificacionMax
	 *            Valor de seteo.
	 */
	public void setFechamodificacionMax(Date fechamodificacionMax) {
		this.fechamodificacionMax = fechamodificacionMax;
	}

	/**
	 * Permite buscar cuando campo fechamodificacion es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechamodificacionIsNull() {
		return fechamodificacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechamodificacion es NULL
	 * 
	 * @param fechamodificacionIsNull
	 *            Valor de seteo.
	 */
	public void setFechamodificacionIsNull(boolean fechamodificacionIsNull) {
		this.fechamodificacionIsNull = fechamodificacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechamodificacion es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechamodificacionIsNotNull() {
		return fechamodificacionIsNotNull;
	}

	/**
	 * Permite buscar cuando campo fechamodificacion es NOT NULL
	 * 
	 * @param fechamodificacionIsNotNull
	 *            Valor de seteo.
	 */
	public void setFechamodificacionIsNotNull(boolean fechamodificacionIsNotNull) {
		this.fechamodificacionIsNotNull = fechamodificacionIsNotNull;
	}

	/**
	 * Valor de busqueda de campo modificadopor
	 * 
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
	 * 
	 * @param modificadopor
	 *            Valor de seteo.
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * Tipo de comparador para la busqueda por campo modificadopor
	 * 
	 * @return modificadoporComparator.
	 */
	public TextComparator getModificadoporComparator() {
		return modificadoporComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo modificadopor
	 * 
	 * @param modificadoporComparator
	 *            Valor de seteo.
	 */
	public void setModificadoporComparator(
			TextComparator modificadoporComparator) {
		this.modificadoporComparator = modificadoporComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getModificadoporIn() {
		return this.modificadoporIn;
	}

	/**
	 * @param modificadopor
	 *            Valor a agregar.
	 */
	public void addModificadoporIn(String modificadopor) {
		this.modificadoporIn.add(modificadopor);
	}

	/**
	 * Permite buscar cuando campo modificadopor es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isModificadoporIsNull() {
		return modificadoporIsNull;
	}

	/**
	 * Permite buscar cuando campo modificadopor es NULL
	 * 
	 * @param modificadoporIsNull
	 *            Valor de seteo.
	 */
	public void setModificadoporIsNull(boolean modificadoporIsNull) {
		this.modificadoporIsNull = modificadoporIsNull;
	}

	/**
	 * Permite buscar cuando campo modificadopor es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isModificadoporIsNotNull() {
		return modificadoporIsNotNull;
	}

	/**
	 * Permite buscar cuando campo modificadopor es NOT NULL
	 * 
	 * @param modificadoporIsNotNull
	 *            Valor de seteo.
	 */
	public void setModificadoporIsNotNull(boolean modificadoporIsNotNull) {
		this.modificadoporIsNotNull = modificadoporIsNotNull;
	}

	/**
	 * Valor de busqueda de campo imagen
	 * 
	 * @return Long.
	 */
	public Long getImagen() {
		return imagen;
	}

	/**
	 * Valor de busqueda de campo imagen
	 * 
	 * @param imagen
	 *            Valor de seteo.
	 */
	public void setImagen(Long imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return List<Long>.
	 */
	public List<Long> getImagenIn() {
		return this.imagenIn;
	}

	/**
	 * @param imagen
	 *            Valor a agregar.
	 */
	public void addImagenIn(Long imagen) {
		this.imagenIn.add(imagen);
	}

	/**
	 * Permite buscar cuando campo imagen es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isImagenIsNull() {
		return imagenIsNull;
	}

	/**
	 * Permite buscar cuando campo imagen es NULL
	 * 
	 * @param imagenIsNull
	 *            Valor de seteo.
	 */
	public void setImagenIsNull(boolean imagenIsNull) {
		this.imagenIsNull = imagenIsNull;
	}

	/**
	 * Permite buscar cuando campo imagen es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isImagenIsNotNull() {
		return imagenIsNotNull;
	}

	/**
	 * Permite buscar cuando campo imagen es NOT NULL
	 * 
	 * @param imagenIsNotNull
	 *            Valor de seteo.
	 */
	public void setImagenIsNotNull(boolean imagenIsNotNull) {
		this.imagenIsNotNull = imagenIsNotNull;
	}

	/**
	 * Valor inferior de rango de busqueda de fecha fechahistorificacion
	 * 
	 * @return ${field.getName()}Min.
	 */
	public Date getFechahistorificacionMin() {
		if (fechahistorificacionMin != null) {
			return DateUtil.toDayBegin(fechahistorificacionMin);
		}
		return fechahistorificacionMin;
	}

	/**
	 * Valor inferior de rango de busqueda de fecha fechahistorificacion
	 * 
	 * @param fechahistorificacionMin
	 *            Valor de seteo.
	 */
	public void setFechahistorificacionMin(Date fechahistorificacionMin) {
		this.fechahistorificacionMin = fechahistorificacionMin;
	}

	/**
	 * Valor superior de rango de busqueda de fecha fechahistorificacion
	 * 
	 * @return fechahistorificacionMax.
	 */
	public Date getFechahistorificacionMax() {
		if (fechahistorificacionMax != null) {
			return DateUtil.toDayEnd(fechahistorificacionMax);
		}
		return fechahistorificacionMax;
	}

	/**
	 * Valor superior de rango de busqueda de fecha fechahistorificacion
	 * 
	 * @param fechahistorificacionMax
	 *            Valor de seteo.
	 */
	public void setFechahistorificacionMax(Date fechahistorificacionMax) {
		this.fechahistorificacionMax = fechahistorificacionMax;
	}

	/**
	 * Permite buscar cuando campo fechahistorificacion es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechahistorificacionIsNull() {
		return fechahistorificacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechahistorificacion es NULL
	 * 
	 * @param fechahistorificacionIsNull
	 *            Valor de seteo.
	 */
	public void setFechahistorificacionIsNull(boolean fechahistorificacionIsNull) {
		this.fechahistorificacionIsNull = fechahistorificacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechahistorificacion es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechahistorificacionIsNotNull() {
		return fechahistorificacionIsNotNull;
	}

	/**
	 * Permite buscar cuando campo fechahistorificacion es NOT NULL
	 * 
	 * @param fechahistorificacionIsNotNull
	 *            Valor de seteo.
	 */
	public void setFechahistorificacionIsNotNull(
			boolean fechahistorificacionIsNotNull) {
		this.fechahistorificacionIsNotNull = fechahistorificacionIsNotNull;
	}

	/**
	 * Valor de busqueda de campo contenidofile
	 * @return String.
	 */
	public String getContenidofile() {
	    if (contenidofile != null) {
	        switch (contenidofileComparator) {
	            case STARTS_WITH:
	                return contenidofile + "%";
	            case CONTAINS:
	                return "%" + contenidofile + "%";
	            case ENDS_WITH:
	                return "%" + contenidofile;
	            case EQUALS:
	            	return contenidofile;
	          	default:
	            	break;
	        }
	    }
	    return contenidofile;
	}

	/**
	 * Valor de busqueda de campo contenidofile
	 * @param cuerpo Valor de seteo.
	 */
	public void setContenidofile(String contenidofile) {
	    this.contenidofile = contenidofile;
	}

	/**
	 * Tipo de comparador para la busqueda por campo contenidofile
	 * @return contenidofileComparator.
	 */
	public TextComparator getContenidofileComparator() {
	    return contenidofileComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo contenidofile
	 * @param contenidofileComparator Valor de seteo.
	 */
	public void setContenidofileComparator(TextComparator contenidofileComparator) {
	    this.contenidofileComparator = contenidofileComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getContenidofileIn() {
	    return this.contenidofileIn;
	}

	/**
	 * @param contenidofile Valor a agregar.
	 */
	public void addContenidofileIn(String contenidofile) {
	    this.contenidofileIn.add(contenidofile);
	}

	/**
	 * Permite buscar cuando campo contenidofile es NULL
	 * @return boolean.
	 */
	public boolean isContenidofileIsNull() {
	    return contenidofileIsNull;
	}

	/**
	 * Permite buscar cuando campo contenidofile es NULL
	 * @param contenidofileIsNull Valor de seteo.
	 */
	public void setContenidofileIsNull(boolean contenidofileIsNull) {
	    this.contenidofileIsNull = contenidofileIsNull;
	}

	/**
	 * Permite buscar cuando campo contenidofile es NOT NULL
	 * @return boolean.
	 */
	public boolean isContenidofileIsNotNull() {
	    return contenidofileIsNotNull;
	}

	/**
	 * Permite buscar cuando campo contenidofile es NOT NULL
	 * @param contenidofileIsNotNull Valor de seteo.
	 */
	public void setContenidofileIsNotNull(boolean contenidofileIsNotNull) {
	    this.contenidofileIsNotNull = contenidofileIsNotNull;
	}
	
	/**
	 * Agrega recursivamente criterios al Criteria de Hibernate para la
	 * utilizacion en busquedas
	 */
	public void addCriteria(Criteria criteria, boolean useOrder) {

		if (getAdjuntoid() != null) {
			criteria.add(Restrictions.eq(ADJUNTOID, getAdjuntoid()));
		}

		if (getAdjuntoidIn().size() > 0) {
			criteria.add(Restrictions.in(ADJUNTOID, getAdjuntoidIn()));
		}

		if (getNombre() != null) {
			if (getNombreComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(NOMBRE, getNombre()));
			} else if (getNombreComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(NOMBRE, getNombre()));
			} else if (getNombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions
						.sqlRestriction(createUpperTranslateSQL(NOMBRE,
								getNombre())));
			} else {
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
			} else if (getCreadoporComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(CREADOPOR, getCreadopor()));
			} else if (getCreadoporComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions
						.sqlRestriction(createUpperTranslateSQL(CREADOPOR,
								getCreadopor())));
			} else {
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
			criteria.add(Restrictions.ge(FECHAMODIFICACION,
					getFechamodificacionMin()));
		}

		if (getFechamodificacionMax() != null) {
			criteria.add(Restrictions.le(FECHAMODIFICACION,
					getFechamodificacionMax()));
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
			} else if (getModificadoporComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(MODIFICADOPOR,
						getModificadopor()));
			} else if (getModificadoporComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions
						.sqlRestriction(createUpperTranslateSQL(MODIFICADOPOR,
								getModificadopor())));
			} else {
				criteria.add(Restrictions.like(MODIFICADOPOR,
						getModificadopor()));
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

		if (getImagen() != null) {
			criteria.add(Restrictions.eq(IMAGEN, getImagen()));
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

		if (getFechahistorificacionMin() != null) {
			criteria.add(Restrictions.ge(FECHAHISTORIFICACION,
					getFechahistorificacionMin()));
		}

		if (getFechahistorificacionMax() != null) {
			criteria.add(Restrictions.le(FECHAHISTORIFICACION,
					getFechahistorificacionMax()));
		}

		if (isFechahistorificacionIsNull()) {
			criteria.add(Restrictions.isNull(FECHAHISTORIFICACION));
		}

		if (isFechahistorificacionIsNotNull()) {
			criteria.add(Restrictions.isNotNull(FECHAHISTORIFICACION));
		}
		
		if (getContenidofile() != null) {
            if (getContenidofileComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CONTENIDOFILE, getContenidofile()));
            } 
            else if (getContenidofileComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CONTENIDOFILE, getContenidofile()));
            }
            else if (getContenidofileComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CONTENIDOFILE, getContenidofile())));
            }
            else {
                criteria.add(Restrictions.like(CONTENIDOFILE, getContenidofile()));
            }
        }

        if (getContenidofileIn().size() > 0) {
            criteria.add(Restrictions.in(CONTENIDOFILE, getContenidofileIn()));
        }

        if (isContenidofileIsNull()) {
            criteria.add(Restrictions.isNull(CONTENIDOFILE));
        }

        if (isContenidofileIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CONTENIDOFILE));
        }
		
		// Aplica ordenamiento solo si corresponde. En count y searchUnique no
		// se utiliza.
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
	 * Crea la sentencia SQL para las busquedas de tipo
	 * TextComparator.UPPERCASE_TRANSLATE
	 */
	private String createUpperTranslateSQL(String column, String value) {
		return "UPPER(TRANSLATE(" + columnHQLToSQL(column) + ",'"
				+ UPPER_TRANSLATE_FROM + "','" + UPPER_TRANSLATE_TO
				+ "')) LIKE '%" + normalizeParam(value).toUpperCase() + "%'";
	}

	/**
	 * Convierte el nombrado de una columna en formato HQL a formato SQL
	 */
	private String columnHQLToSQL(String column) {
		StringBuilder columnSQL = new StringBuilder();
		for (int i = 0; i < column.length(); i++) {
			if (Character.isUpperCase(column.charAt(i))) {
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
	private String normalizeParam(String param) {
		return Normalizer.normalize(param, Normalizer.Form.NFD).replaceAll(
				"\\p{InCombiningDiacriticalMarks}+", "");
	}
}
