/*
 *
 * archivo: TblLotesEnviosHistQuery.java
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
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblServicios;

/**
 * Clase con criterios de busqueda para la entidad TblLotesEnviosHist
 */
public class TblLotesEnviosHistQuery extends AbstractHibernateQueryEntity<TblLotesEnviosHist> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String LOTEENVIOID = "loteenvioid";
    public static final String TBLSERVICIOS = "tblServicios";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String ESTADOENVIOID = "estadoenvioid";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String FECHAHISTORIFICACION = "fechahistorificacion";
    public static final String MULTIDESTINATARIO = "multidestinatario";


    /**
     * Valor de busqueda de campo loteenvioid
     */
    private Long loteenvioid;

    /**
     * Lista de valores del campo loteenvioid para busquedas tipo IN
     */
    private List<Long> loteenvioidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblServicios
     */
    private TblServiciosQuery tblServicios;

    /**
     * Lista de valores del ID del campo tblServicios para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblServicios
     */
    private List<TblServicios> tblServiciosIdIn = new ArrayList<TblServicios>(0);

    /**
     * Permite buscar cuando campo tblServicios es NULL
     */
    private boolean tblServiciosIsNull = false;

    /**
     * Permite buscar cuando campo tblServicios es NOT NULL
     */
    private boolean tblServiciosIsNotNull = false;

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
     * Valor de busqueda de campo estadoenvioid
     */
    private Long estadoenvioid;

    /**
     * Lista de valores del campo estadoenvioid para busquedas tipo IN
     */
    private List<Long> estadoenvioidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo estadoenvioid es NULL
     */
    private boolean estadoenvioidIsNull = false;

    /**
     * Permite buscar cuando campo estadoenvioid es NOT NULL
     */
    private boolean estadoenvioidIsNotNull = false;

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
     * Valor de busqueda de campo multidestinatario
     */
    private Integer multidestinatario;

    /**
     * Lista de valores del campo multidestinatario para busquedas tipo IN
     */
    private List<Integer> multidestinatarioIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo multidestinatario es NULL
     */
    private boolean multidestinatarioIsNull = false;

    /**
     * Permite buscar cuando campo multidestinatario es NOT NULL
     */
    private boolean multidestinatarioIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblServicios
     */
    private boolean innerJoinTblServicios = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblServicios
     */
    private boolean leftJoinTblServicios = false;

    /**
     * Constructor default
     */
    public TblLotesEnviosHistQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblLotesEnviosHistQuery(Long loteenvioid) {
        setLoteenvioid(loteenvioid);
    }

    /**
     * Valor de busqueda de campo loteenvioid
     * @return Long.
     */
    public Long getLoteenvioid() {
        return loteenvioid;
    }

    /**
     * Valor de busqueda de campo loteenvioid
     * @param loteenvioid Valor de seteo.
     */
    public void setLoteenvioid(Long loteenvioid) {
        this.loteenvioid = loteenvioid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getLoteenvioidIn() {
        return this.loteenvioidIn;
    }

    /**
     * @param loteenvioid Valor a agregar.
     */
    public void addLoteenvioidIn(Long loteenvioid) {
        this.loteenvioidIn.add(loteenvioid);
    }

    /**
     * Valor de busqueda de campo tblServicios
     * @return TblServicios.
     */
    public TblServiciosQuery getTblServicios() {
        return tblServicios;
    }

    /**
     * Valor de busqueda de campo tblServicios
     * @param tblServicios Valor de seteo.
     */
    public void setTblServicios(TblServiciosQuery tblServicios) {
        this.tblServicios = tblServicios;
    }

    /**
     * @return List<TblServicios>.
     */
    public List<TblServicios> getTblServiciosIdIn() {
        return this.tblServiciosIdIn;
    }

    /**
     * @param tblServicios Valor a agregar.
     */
    public void addTblServiciosIdIn(TblServicios tblServicios) {
        this.tblServiciosIdIn.add(tblServicios);
    }

    /**
     * Permite buscar cuando campo tblServicios es NULL
     * @return boolean.
     */
    public boolean isTblServiciosIsNull() {
        return tblServiciosIsNull;
    }

    /**
     * Permite buscar cuando campo tblServicios es NULL
     * @param tblServiciosIsNull Valor de seteo.
     */
    public void setTblServiciosIsNull(boolean tblServiciosIsNull) {
        this.tblServiciosIsNull = tblServiciosIsNull;
    }

    /**
     * Permite buscar cuando campo tblServicios es NOT NULL
     * @return boolean.
     */
    public boolean isTblServiciosIsNotNull() {
        return tblServiciosIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblServicios es NOT NULL
     * @param tblServiciosIsNotNull Valor de seteo.
     */
    public void setTblServiciosIsNotNull(boolean tblServiciosIsNotNull) {
        this.tblServiciosIsNotNull = tblServiciosIsNotNull;
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
     * Valor de busqueda de campo estadoenvioid
     * @return Long.
     */
    public Long getEstadoenvioid() {
        return estadoenvioid;
    }

    /**
     * Valor de busqueda de campo estadoenvioid
     * @param estadoenvioid Valor de seteo.
     */
    public void setEstadoenvioid(Long estadoenvioid) {
        this.estadoenvioid = estadoenvioid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getEstadoenvioidIn() {
        return this.estadoenvioidIn;
    }

    /**
     * @param estadoenvioid Valor a agregar.
     */
    public void addEstadoenvioidIn(Long estadoenvioid) {
        this.estadoenvioidIn.add(estadoenvioid);
    }

    /**
     * Permite buscar cuando campo estadoenvioid es NULL
     * @return boolean.
     */
    public boolean isEstadoenvioidIsNull() {
        return estadoenvioidIsNull;
    }

    /**
     * Permite buscar cuando campo estadoenvioid es NULL
     * @param estadoenvioidIsNull Valor de seteo.
     */
    public void setEstadoenvioidIsNull(boolean estadoenvioidIsNull) {
        this.estadoenvioidIsNull = estadoenvioidIsNull;
    }

    /**
     * Permite buscar cuando campo estadoenvioid es NOT NULL
     * @return boolean.
     */
    public boolean isEstadoenvioidIsNotNull() {
        return estadoenvioidIsNotNull;
    }

    /**
     * Permite buscar cuando campo estadoenvioid es NOT NULL
     * @param estadoenvioidIsNotNull Valor de seteo.
     */
    public void setEstadoenvioidIsNotNull(boolean estadoenvioidIsNotNull) {
        this.estadoenvioidIsNotNull = estadoenvioidIsNotNull;
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
     * Valor inferior de rango de busqueda de fecha fechahistorificacion
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
     * @param fechahistorificacionMin Valor de seteo.
     */
    public void setFechahistorificacionMin(Date fechahistorificacionMin) {
        this.fechahistorificacionMin = fechahistorificacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechahistorificacion
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
     * @param fechahistorificacionMax Valor de seteo.
     */
    public void setFechahistorificacionMax(Date fechahistorificacionMax) {
        this.fechahistorificacionMax = fechahistorificacionMax;
    }

    /**
     * Permite buscar cuando campo fechahistorificacion es NULL
     * @return boolean.
     */
    public boolean isFechahistorificacionIsNull() {
        return fechahistorificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechahistorificacion es NULL
     * @param fechahistorificacionIsNull Valor de seteo.
     */
    public void setFechahistorificacionIsNull(boolean fechahistorificacionIsNull) {
        this.fechahistorificacionIsNull = fechahistorificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechahistorificacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechahistorificacionIsNotNull() {
        return fechahistorificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechahistorificacion es NOT NULL
     * @param fechahistorificacionIsNotNull Valor de seteo.
     */
    public void setFechahistorificacionIsNotNull(boolean fechahistorificacionIsNotNull) {
        this.fechahistorificacionIsNotNull = fechahistorificacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo multidestinatario
     * @return Integer.
     */
    public Integer getMultidestinatario() {
        return multidestinatario;
    }

    /**
     * Valor de busqueda de campo multidestinatario
     * @param multidestinatario Valor de seteo.
     */
    public void setMultidestinatario(Integer multidestinatario) {
        this.multidestinatario = multidestinatario;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getMultidestinatarioIn() {
        return this.multidestinatarioIn;
    }

    /**
     * @param multidestinatario Valor a agregar.
     */
    public void addMultidestinatarioIn(Integer multidestinatario) {
        this.multidestinatarioIn.add(multidestinatario);
    }

    /**
     * Permite buscar cuando campo multidestinatario es NULL
     * @return boolean.
     */
    public boolean isMultidestinatarioIsNull() {
        return multidestinatarioIsNull;
    }

    /**
     * Permite buscar cuando campo multidestinatario es NULL
     * @param multidestinatarioIsNull Valor de seteo.
     */
    public void setMultidestinatarioIsNull(boolean multidestinatarioIsNull) {
        this.multidestinatarioIsNull = multidestinatarioIsNull;
    }

    /**
     * Permite buscar cuando campo multidestinatario es NOT NULL
     * @return boolean.
     */
    public boolean isMultidestinatarioIsNotNull() {
        return multidestinatarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo multidestinatario es NOT NULL
     * @param multidestinatarioIsNotNull Valor de seteo.
     */
    public void setMultidestinatarioIsNotNull(boolean multidestinatarioIsNotNull) {
        this.multidestinatarioIsNotNull = multidestinatarioIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblServicios() {
        return innerJoinTblServicios;
    }

    /**
     * @param innerJoinTblServicios Valor de seteo.
     */
    public void setInnerJoinTblServicios(boolean innerJoinTblServicios) {
        this.innerJoinTblServicios = innerJoinTblServicios;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblServicios() {
        return leftJoinTblServicios;
    }

    /**
     * @param leftJoinTblServicios Valor de seteo.
     */
    public void setLeftJoinTblServicios(boolean leftJoinTblServicios) {
        this.leftJoinTblServicios = leftJoinTblServicios;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getLoteenvioid() != null) {
            criteria.add(Restrictions.eq(LOTEENVIOID, getLoteenvioid()));
        }

        if (getLoteenvioidIn().size() > 0) {
            criteria.add(Restrictions.in(LOTEENVIOID, getLoteenvioidIn()));
        }

        // Campo entidad padre tblServicios
        
        // Si se hace join fetch con el padre
        Criteria tblServiciosCriteria = null;
        if (isInnerJoinTblServicios()) {
            tblServiciosCriteria = criteria.createCriteria(TBLSERVICIOS, "a_" + TBLSERVICIOS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblServicios()) {
            tblServiciosCriteria = criteria.createCriteria(TBLSERVICIOS, "a_" + TBLSERVICIOS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblServicios() != null) {
            if (getTblServicios().getServicioid() == null) {
                if (tblServiciosCriteria == null) {
                    tblServiciosCriteria = criteria.createCriteria(TBLSERVICIOS, "a_" + TBLSERVICIOS);
                }
                getTblServicios().addCriteria(tblServiciosCriteria, useOrder);
            } else {
                TblServicios parent = new TblServicios();
                parent.setServicioid(getTblServicios().getServicioid());
                criteria.add(Restrictions.eq(TBLSERVICIOS, parent));
            }
        }

        if (getTblServiciosIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLSERVICIOS, getTblServiciosIdIn()));
        }

        if (isTblServiciosIsNull()) {
            criteria.add(Restrictions.isNull(TBLSERVICIOS));
        }

        if (isTblServiciosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLSERVICIOS));
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

        if (getEstadoenvioid() != null) {
            criteria.add(Restrictions.eq(ESTADOENVIOID, getEstadoenvioid()));
        }

        if (getEstadoenvioidIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADOENVIOID, getEstadoenvioidIn()));
        }

        if (isEstadoenvioidIsNull()) {
            criteria.add(Restrictions.isNull(ESTADOENVIOID));
        }

        if (isEstadoenvioidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADOENVIOID));
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

        if (getFechahistorificacionMin() != null) {
            criteria.add(Restrictions.ge(FECHAHISTORIFICACION, getFechahistorificacionMin()));
        }

        if (getFechahistorificacionMax() != null) {
            criteria.add(Restrictions.le(FECHAHISTORIFICACION, getFechahistorificacionMax()));
        }

        if (isFechahistorificacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAHISTORIFICACION));
        }

        if (isFechahistorificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAHISTORIFICACION));
        }

        if (getMultidestinatario() != null) {
            criteria.add(Restrictions.eq(MULTIDESTINATARIO, getMultidestinatario()));
        }

        if (getMultidestinatarioIn().size() > 0) {
            criteria.add(Restrictions.in(MULTIDESTINATARIO, getMultidestinatarioIn()));
        }

        if (isMultidestinatarioIsNull()) {
            criteria.add(Restrictions.isNull(MULTIDESTINATARIO));
        }

        if (isMultidestinatarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MULTIDESTINATARIO));
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
 
