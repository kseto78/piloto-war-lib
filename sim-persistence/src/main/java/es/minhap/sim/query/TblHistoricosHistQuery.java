/*
 *
o * archivo: TblHistoricosHistQuery.java
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
import es.minhap.sim.model.TblEstados;
import es.minhap.sim.model.TblHistoricosHist;
import es.minhap.sim.model.TblPlanificaciones;

/**
 * Clase con criterios de busqueda para la entidad TblHistoricosHist
 */
public class TblHistoricosHistQuery extends AbstractHibernateQueryEntity<TblHistoricosHist> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String HISTORICOID = "historicoid";
    public static final String TBLPLANIFICACIONES = "tblPlanificaciones";
    public static final String TBLESTADOS = "tblEstados";
    public static final String FECHA = "fecha";
    public static final String MENSAJEID = "mensajeid";
    public static final String SERVIDORID = "servidorid";
    public static final String DESCRIPCION = "descripcion";
    public static final String SUBESTADOID = "subestadoid";
    public static final String FECHAHISTORIFICACION = "fechahistorificacion";
    public static final String DESTINATARIOSMENSAJES = "destinatariosmensajes";


    /**
     * Valor de busqueda de campo historicoid
     */
    private Long historicoid;

    /**
     * Lista de valores del campo historicoid para busquedas tipo IN
     */
    private List<Long> historicoidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblPlanificaciones
     */
    private TblPlanificacionesQuery tblPlanificaciones;

    /**
     * Lista de valores del ID del campo tblPlanificaciones para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblPlanificaciones
     */
    private List<TblPlanificaciones> tblPlanificacionesIdIn = new ArrayList<TblPlanificaciones>(0);

    /**
     * Permite buscar cuando campo tblPlanificaciones es NULL
     */
    private boolean tblPlanificacionesIsNull = false;

    /**
     * Permite buscar cuando campo tblPlanificaciones es NOT NULL
     */
    private boolean tblPlanificacionesIsNotNull = false;

    /**
     * Valor de busqueda de campo tblEstados
     */
    private TblEstadosQuery tblEstados;

    /**
     * Lista de valores del ID del campo tblEstados para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblEstados
     */
    private List<TblEstados> tblEstadosIdIn = new ArrayList<TblEstados>(0);

    /**
     * Permite buscar cuando campo tblEstados es NULL
     */
    private boolean tblEstadosIsNull = false;

    /**
     * Permite buscar cuando campo tblEstados es NOT NULL
     */
    private boolean tblEstadosIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fecha
     */
    private Date fechaMin;

    /**
     * Valor superior de rango de busqueda de fecha fecha
     */
    private Date fechaMax;

    /**
     * Permite buscar cuando campo fecha es NULL
     */
    private boolean fechaIsNull = false;

    /**
     * Permite buscar cuando campo fecha es NOT NULL
     */
    private boolean fechaIsNotNull = false;

    /**
     * Valor de busqueda de campo mensajeid
     */
    private Long mensajeid;

    /**
     * Lista de valores del campo mensajeid para busquedas tipo IN
     */
    private List<Long> mensajeidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo mensajeid es NULL
     */
    private boolean mensajeidIsNull = false;

    /**
     * Permite buscar cuando campo mensajeid es NOT NULL
     */
    private boolean mensajeidIsNotNull = false;

    /**
     * Valor de busqueda de campo servidorid
     */
    private Long servidorid;

    /**
     * Lista de valores del campo servidorid para busquedas tipo IN
     */
    private List<Long> servidoridIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo servidorid es NULL
     */
    private boolean servidoridIsNull = false;

    /**
     * Permite buscar cuando campo servidorid es NOT NULL
     */
    private boolean servidoridIsNotNull = false;

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
     * Valor de busqueda de campo subestadoid
     */
    private Long subestadoid;

    /**
     * Lista de valores del campo subestadoid para busquedas tipo IN
     */
    private List<Long> subestadoidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo subestadoid es NULL
     */
    private boolean subestadoidIsNull = false;

    /**
     * Permite buscar cuando campo subestadoid es NOT NULL
     */
    private boolean subestadoidIsNotNull = false;

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
     * Valor de busqueda de campo destinatariosmensajes
     */
    private Long destinatariosmensajes;

    /**
     * Lista de valores del campo destinatariosmensajes para busquedas tipo IN
     */
    private List<Long> destinatariosmensajesIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo destinatariosmensajes es NULL
     */
    private boolean destinatariosmensajesIsNull = false;

    /**
     * Permite buscar cuando campo destinatariosmensajes es NOT NULL
     */
    private boolean destinatariosmensajesIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblPlanificaciones
     */
    private boolean innerJoinTblPlanificaciones = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblPlanificaciones
     */
    private boolean leftJoinTblPlanificaciones = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblEstados
     */
    private boolean innerJoinTblEstados = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblEstados
     */
    private boolean leftJoinTblEstados = false;

    /**
     * Constructor default
     */
    public TblHistoricosHistQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblHistoricosHistQuery(Long historicoid) {
        setHistoricoid(historicoid);
    }

    /**
     * Valor de busqueda de campo historicoid
     * @return Long.
     */
    public Long getHistoricoid() {
        return historicoid;
    }

    /**
     * Valor de busqueda de campo historicoid
     * @param historicoid Valor de seteo.
     */
    public void setHistoricoid(Long historicoid) {
        this.historicoid = historicoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getHistoricoidIn() {
        return this.historicoidIn;
    }

    /**
     * @param historicoid Valor a agregar.
     */
    public void addHistoricoidIn(Long historicoid) {
        this.historicoidIn.add(historicoid);
    }

    /**
     * Valor de busqueda de campo tblPlanificaciones
     * @return TblPlanificaciones.
     */
    public TblPlanificacionesQuery getTblPlanificaciones() {
        return tblPlanificaciones;
    }

    /**
     * Valor de busqueda de campo tblPlanificaciones
     * @param tblPlanificaciones Valor de seteo.
     */
    public void setTblPlanificaciones(TblPlanificacionesQuery tblPlanificaciones) {
        this.tblPlanificaciones = tblPlanificaciones;
    }

    /**
     * @return List<TblPlanificaciones>.
     */
    public List<TblPlanificaciones> getTblPlanificacionesIdIn() {
        return this.tblPlanificacionesIdIn;
    }

    /**
     * @param tblPlanificaciones Valor a agregar.
     */
    public void addTblPlanificacionesIdIn(TblPlanificaciones tblPlanificaciones) {
        this.tblPlanificacionesIdIn.add(tblPlanificaciones);
    }

    /**
     * Permite buscar cuando campo tblPlanificaciones es NULL
     * @return boolean.
     */
    public boolean isTblPlanificacionesIsNull() {
        return tblPlanificacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblPlanificaciones es NULL
     * @param tblPlanificacionesIsNull Valor de seteo.
     */
    public void setTblPlanificacionesIsNull(boolean tblPlanificacionesIsNull) {
        this.tblPlanificacionesIsNull = tblPlanificacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblPlanificaciones es NOT NULL
     * @return boolean.
     */
    public boolean isTblPlanificacionesIsNotNull() {
        return tblPlanificacionesIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblPlanificaciones es NOT NULL
     * @param tblPlanificacionesIsNotNull Valor de seteo.
     */
    public void setTblPlanificacionesIsNotNull(boolean tblPlanificacionesIsNotNull) {
        this.tblPlanificacionesIsNotNull = tblPlanificacionesIsNotNull;
    }

    /**
     * Valor de busqueda de campo tblEstados
     * @return TblEstados.
     */
    public TblEstadosQuery getTblEstados() {
        return tblEstados;
    }

    /**
     * Valor de busqueda de campo tblEstados
     * @param tblEstados Valor de seteo.
     */
    public void setTblEstados(TblEstadosQuery tblEstados) {
        this.tblEstados = tblEstados;
    }

    /**
     * @return List<TblEstados>.
     */
    public List<TblEstados> getTblEstadosIdIn() {
        return this.tblEstadosIdIn;
    }

    /**
     * @param tblEstados Valor a agregar.
     */
    public void addTblEstadosIdIn(TblEstados tblEstados) {
        this.tblEstadosIdIn.add(tblEstados);
    }

    /**
     * Permite buscar cuando campo tblEstados es NULL
     * @return boolean.
     */
    public boolean isTblEstadosIsNull() {
        return tblEstadosIsNull;
    }

    /**
     * Permite buscar cuando campo tblEstados es NULL
     * @param tblEstadosIsNull Valor de seteo.
     */
    public void setTblEstadosIsNull(boolean tblEstadosIsNull) {
        this.tblEstadosIsNull = tblEstadosIsNull;
    }

    /**
     * Permite buscar cuando campo tblEstados es NOT NULL
     * @return boolean.
     */
    public boolean isTblEstadosIsNotNull() {
        return tblEstadosIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblEstados es NOT NULL
     * @param tblEstadosIsNotNull Valor de seteo.
     */
    public void setTblEstadosIsNotNull(boolean tblEstadosIsNotNull) {
        this.tblEstadosIsNotNull = tblEstadosIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fecha
     * @return ${field.getName()}Min.
     */
    public Date getFechaMin() {
        if (fechaMin != null) {
            return DateUtil.toDayBegin(fechaMin);
        }
        return fechaMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fecha
     * @param fechaMin Valor de seteo.
     */
    public void setFechaMin(Date fechaMin) {
        this.fechaMin = fechaMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fecha
     * @return fechaMax.
     */
    public Date getFechaMax() {
        if (fechaMax != null) {
            return DateUtil.toDayEnd(fechaMax);
        }
        return fechaMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fecha
     * @param fechaMax Valor de seteo.
     */
    public void setFechaMax(Date fechaMax) {
        this.fechaMax = fechaMax;
    }

    /**
     * Permite buscar cuando campo fecha es NULL
     * @return boolean.
     */
    public boolean isFechaIsNull() {
        return fechaIsNull;
    }

    /**
     * Permite buscar cuando campo fecha es NULL
     * @param fechaIsNull Valor de seteo.
     */
    public void setFechaIsNull(boolean fechaIsNull) {
        this.fechaIsNull = fechaIsNull;
    }

    /**
     * Permite buscar cuando campo fecha es NOT NULL
     * @return boolean.
     */
    public boolean isFechaIsNotNull() {
        return fechaIsNotNull;
    }

    /**
     * Permite buscar cuando campo fecha es NOT NULL
     * @param fechaIsNotNull Valor de seteo.
     */
    public void setFechaIsNotNull(boolean fechaIsNotNull) {
        this.fechaIsNotNull = fechaIsNotNull;
    }

    /**
     * Valor de busqueda de campo mensajeid
     * @return Long.
     */
    public Long getMensajeid() {
        return mensajeid;
    }

    /**
     * Valor de busqueda de campo mensajeid
     * @param mensajeid Valor de seteo.
     */
    public void setMensajeid(Long mensajeid) {
        this.mensajeid = mensajeid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getMensajeidIn() {
        return this.mensajeidIn;
    }

    /**
     * @param mensajeid Valor a agregar.
     */
    public void addMensajeidIn(Long mensajeid) {
        this.mensajeidIn.add(mensajeid);
    }

    /**
     * Permite buscar cuando campo mensajeid es NULL
     * @return boolean.
     */
    public boolean isMensajeidIsNull() {
        return mensajeidIsNull;
    }

    /**
     * Permite buscar cuando campo mensajeid es NULL
     * @param mensajeidIsNull Valor de seteo.
     */
    public void setMensajeidIsNull(boolean mensajeidIsNull) {
        this.mensajeidIsNull = mensajeidIsNull;
    }

    /**
     * Permite buscar cuando campo mensajeid es NOT NULL
     * @return boolean.
     */
    public boolean isMensajeidIsNotNull() {
        return mensajeidIsNotNull;
    }

    /**
     * Permite buscar cuando campo mensajeid es NOT NULL
     * @param mensajeidIsNotNull Valor de seteo.
     */
    public void setMensajeidIsNotNull(boolean mensajeidIsNotNull) {
        this.mensajeidIsNotNull = mensajeidIsNotNull;
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
     * Permite buscar cuando campo servidorid es NULL
     * @return boolean.
     */
    public boolean isServidoridIsNull() {
        return servidoridIsNull;
    }

    /**
     * Permite buscar cuando campo servidorid es NULL
     * @param servidoridIsNull Valor de seteo.
     */
    public void setServidoridIsNull(boolean servidoridIsNull) {
        this.servidoridIsNull = servidoridIsNull;
    }

    /**
     * Permite buscar cuando campo servidorid es NOT NULL
     * @return boolean.
     */
    public boolean isServidoridIsNotNull() {
        return servidoridIsNotNull;
    }

    /**
     * Permite buscar cuando campo servidorid es NOT NULL
     * @param servidoridIsNotNull Valor de seteo.
     */
    public void setServidoridIsNotNull(boolean servidoridIsNotNull) {
        this.servidoridIsNotNull = servidoridIsNotNull;
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
     * Valor de busqueda de campo subestadoid
     * @return Long.
     */
    public Long getSubestadoid() {
        return subestadoid;
    }

    /**
     * Valor de busqueda de campo subestadoid
     * @param subestadoid Valor de seteo.
     */
    public void setSubestadoid(Long subestadoid) {
        this.subestadoid = subestadoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getSubestadoidIn() {
        return this.subestadoidIn;
    }

    /**
     * @param subestadoid Valor a agregar.
     */
    public void addSubestadoidIn(Long subestadoid) {
        this.subestadoidIn.add(subestadoid);
    }

    /**
     * Permite buscar cuando campo subestadoid es NULL
     * @return boolean.
     */
    public boolean isSubestadoidIsNull() {
        return subestadoidIsNull;
    }

    /**
     * Permite buscar cuando campo subestadoid es NULL
     * @param subestadoidIsNull Valor de seteo.
     */
    public void setSubestadoidIsNull(boolean subestadoidIsNull) {
        this.subestadoidIsNull = subestadoidIsNull;
    }

    /**
     * Permite buscar cuando campo subestadoid es NOT NULL
     * @return boolean.
     */
    public boolean isSubestadoidIsNotNull() {
        return subestadoidIsNotNull;
    }

    /**
     * Permite buscar cuando campo subestadoid es NOT NULL
     * @param subestadoidIsNotNull Valor de seteo.
     */
    public void setSubestadoidIsNotNull(boolean subestadoidIsNotNull) {
        this.subestadoidIsNotNull = subestadoidIsNotNull;
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
     * Valor de busqueda de campo destinatariosmensajes
     * @return Long.
     */
    public Long getDestinatariosmensajes() {
        return destinatariosmensajes;
    }

    /**
     * Valor de busqueda de campo destinatariosmensajes
     * @param destinatariosmensajes Valor de seteo.
     */
    public void setDestinatariosmensajes(Long destinatariosmensajes) {
        this.destinatariosmensajes = destinatariosmensajes;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getDestinatariosmensajesIn() {
        return this.destinatariosmensajesIn;
    }

    /**
     * @param destinatariosmensajes Valor a agregar.
     */
    public void addDestinatariosmensajesIn(Long destinatariosmensajes) {
        this.destinatariosmensajesIn.add(destinatariosmensajes);
    }

    /**
     * Permite buscar cuando campo destinatariosmensajes es NULL
     * @return boolean.
     */
    public boolean isDestinatariosmensajesIsNull() {
        return destinatariosmensajesIsNull;
    }

    /**
     * Permite buscar cuando campo destinatariosmensajes es NULL
     * @param destinatariosmensajesIsNull Valor de seteo.
     */
    public void setDestinatariosmensajesIsNull(boolean destinatariosmensajesIsNull) {
        this.destinatariosmensajesIsNull = destinatariosmensajesIsNull;
    }

    /**
     * Permite buscar cuando campo destinatariosmensajes es NOT NULL
     * @return boolean.
     */
    public boolean isDestinatariosmensajesIsNotNull() {
        return destinatariosmensajesIsNotNull;
    }

    /**
     * Permite buscar cuando campo destinatariosmensajes es NOT NULL
     * @param destinatariosmensajesIsNotNull Valor de seteo.
     */
    public void setDestinatariosmensajesIsNotNull(boolean destinatariosmensajesIsNotNull) {
        this.destinatariosmensajesIsNotNull = destinatariosmensajesIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblPlanificaciones() {
        return innerJoinTblPlanificaciones;
    }

    /**
     * @param innerJoinTblPlanificaciones Valor de seteo.
     */
    public void setInnerJoinTblPlanificaciones(boolean innerJoinTblPlanificaciones) {
        this.innerJoinTblPlanificaciones = innerJoinTblPlanificaciones;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblPlanificaciones() {
        return leftJoinTblPlanificaciones;
    }

    /**
     * @param leftJoinTblPlanificaciones Valor de seteo.
     */
    public void setLeftJoinTblPlanificaciones(boolean leftJoinTblPlanificaciones) {
        this.leftJoinTblPlanificaciones = leftJoinTblPlanificaciones;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblEstados() {
        return innerJoinTblEstados;
    }

    /**
     * @param innerJoinTblEstados Valor de seteo.
     */
    public void setInnerJoinTblEstados(boolean innerJoinTblEstados) {
        this.innerJoinTblEstados = innerJoinTblEstados;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblEstados() {
        return leftJoinTblEstados;
    }

    /**
     * @param leftJoinTblEstados Valor de seteo.
     */
    public void setLeftJoinTblEstados(boolean leftJoinTblEstados) {
        this.leftJoinTblEstados = leftJoinTblEstados;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getHistoricoid() != null) {
            criteria.add(Restrictions.eq(HISTORICOID, getHistoricoid()));
        }

        if (getHistoricoidIn().size() > 0) {
            criteria.add(Restrictions.in(HISTORICOID, getHistoricoidIn()));
        }

        // Campo entidad padre tblPlanificaciones
        
        // Si se hace join fetch con el padre
        Criteria tblPlanificacionesCriteria = null;
        if (isInnerJoinTblPlanificaciones()) {
            tblPlanificacionesCriteria = criteria.createCriteria(TBLPLANIFICACIONES, "a_" + TBLPLANIFICACIONES, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblPlanificaciones()) {
            tblPlanificacionesCriteria = criteria.createCriteria(TBLPLANIFICACIONES, "a_" + TBLPLANIFICACIONES, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblPlanificaciones() != null) {
            if (getTblPlanificaciones().getPlanificacionid() == null) {
                if (tblPlanificacionesCriteria == null) {
                    tblPlanificacionesCriteria = criteria.createCriteria(TBLPLANIFICACIONES, "a_" + TBLPLANIFICACIONES);
                }
                getTblPlanificaciones().addCriteria(tblPlanificacionesCriteria, useOrder);
            } else {
                TblPlanificaciones parent = new TblPlanificaciones();
                parent.setPlanificacionid(getTblPlanificaciones().getPlanificacionid());
                criteria.add(Restrictions.eq(TBLPLANIFICACIONES, parent));
            }
        }

        if (getTblPlanificacionesIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLPLANIFICACIONES, getTblPlanificacionesIdIn()));
        }

        if (isTblPlanificacionesIsNull()) {
            criteria.add(Restrictions.isNull(TBLPLANIFICACIONES));
        }

        if (isTblPlanificacionesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLPLANIFICACIONES));
        }

        // Campo entidad padre tblEstados
        
        // Si se hace join fetch con el padre
        Criteria tblEstadosCriteria = null;
        if (isInnerJoinTblEstados()) {
            tblEstadosCriteria = criteria.createCriteria(TBLESTADOS, "a_" + TBLESTADOS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblEstados()) {
            tblEstadosCriteria = criteria.createCriteria(TBLESTADOS, "a_" + TBLESTADOS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblEstados() != null) {
            if (getTblEstados().getEstadoid() == null) {
                if (tblEstadosCriteria == null) {
                    tblEstadosCriteria = criteria.createCriteria(TBLESTADOS, "a_" + TBLESTADOS);
                }
                getTblEstados().addCriteria(tblEstadosCriteria, useOrder);
            } else {
                TblEstados parent = new TblEstados();
                parent.setEstadoid(getTblEstados().getEstadoid());
                criteria.add(Restrictions.eq(TBLESTADOS, parent));
            }
        }

        if (getTblEstadosIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLESTADOS, getTblEstadosIdIn()));
        }

        if (isTblEstadosIsNull()) {
            criteria.add(Restrictions.isNull(TBLESTADOS));
        }

        if (isTblEstadosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLESTADOS));
        }

        if (getFechaMin() != null) {
            criteria.add(Restrictions.ge(FECHA, getFechaMin()));
        }

        if (getFechaMax() != null) {
            criteria.add(Restrictions.le(FECHA, getFechaMax()));
        }

        if (isFechaIsNull()) {
            criteria.add(Restrictions.isNull(FECHA));
        }

        if (isFechaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHA));
        }

        if (getMensajeid() != null) {
            criteria.add(Restrictions.eq(MENSAJEID, getMensajeid()));
        }

        if (getMensajeidIn().size() > 0) {
            criteria.add(Restrictions.in(MENSAJEID, getMensajeidIn()));
        }

        if (isMensajeidIsNull()) {
            criteria.add(Restrictions.isNull(MENSAJEID));
        }

        if (isMensajeidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MENSAJEID));
        }

        if (getServidorid() != null) {
            criteria.add(Restrictions.eq(SERVIDORID, getServidorid()));
        }

        if (getServidoridIn().size() > 0) {
            criteria.add(Restrictions.in(SERVIDORID, getServidoridIn()));
        }

        if (isServidoridIsNull()) {
            criteria.add(Restrictions.isNull(SERVIDORID));
        }

        if (isServidoridIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVIDORID));
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

        if (getSubestadoid() != null) {
            criteria.add(Restrictions.eq(SUBESTADOID, getSubestadoid()));
        }

        if (getSubestadoidIn().size() > 0) {
            criteria.add(Restrictions.in(SUBESTADOID, getSubestadoidIn()));
        }

        if (isSubestadoidIsNull()) {
            criteria.add(Restrictions.isNull(SUBESTADOID));
        }

        if (isSubestadoidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SUBESTADOID));
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

        if (getDestinatariosmensajes() != null) {
            criteria.add(Restrictions.eq(DESTINATARIOSMENSAJES, getDestinatariosmensajes()));
        }

        if (getDestinatariosmensajesIn().size() > 0) {
            criteria.add(Restrictions.in(DESTINATARIOSMENSAJES, getDestinatariosmensajesIn()));
        }

        if (isDestinatariosmensajesIsNull()) {
            criteria.add(Restrictions.isNull(DESTINATARIOSMENSAJES));
        }

        if (isDestinatariosmensajesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DESTINATARIOSMENSAJES));
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
 
