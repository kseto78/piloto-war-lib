/*
 *
 * archivo: ViewHistoricoHistQuery.java
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
import es.minhap.sim.model.ViewHistoricoHist;

/**
 * Clase con criterios de busqueda para la entidad ViewHistoricoHist
 */
public class ViewHistoricoHistQuery extends AbstractHibernateQueryEntity<ViewHistoricoHist> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String HISTORICOID = "historicoid";
    public static final String MENSAJEID = "mensajeid";
    public static final String DESTINATARIOSMENSAJES = "destinatariosmensajes";
    public static final String ESTADOID = "estadoid";
    public static final String SERVIDORID = "servidorid";
    public static final String PLANIFICACIONID = "planificacionid";
    public static final String ESTADO = "estado";
    public static final String FECHA = "fecha";
    public static final String SERVIDOR = "servidor";
    public static final String CODIGOEXTERNO = "codigoexterno";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    public static final String DESCRIPCION = "descripcion";
    public static final String FECHAHISTORIFICACION = "fechahistorificacion";


    /**
     * Valor de busqueda de campo historicoid
     */
    private Long historicoid;

    /**
     * Lista de valores del campo historicoid para busquedas tipo IN
     */
    private List<Long> historicoidIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo estadoid
     */
    private Long estadoid;

    /**
     * Lista de valores del campo estadoid para busquedas tipo IN
     */
    private List<Long> estadoidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo estadoid es NULL
     */
    private boolean estadoidIsNull = false;

    /**
     * Permite buscar cuando campo estadoid es NOT NULL
     */
    private boolean estadoidIsNotNull = false;

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
     * Valor de busqueda de campo planificacionid
     */
    private Long planificacionid;

    /**
     * Lista de valores del campo planificacionid para busquedas tipo IN
     */
    private List<Long> planificacionidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo planificacionid es NULL
     */
    private boolean planificacionidIsNull = false;

    /**
     * Permite buscar cuando campo planificacionid es NOT NULL
     */
    private boolean planificacionidIsNotNull = false;

    /**
     * Valor de busqueda de campo estado
     */
    private String estado;

    /**
     * Tipo de comparador para la busqueda por campo estado
     */
    private TextComparator estadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo estado para busquedas tipo IN
     */
    private List<String> estadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo estado es NULL
     */
    private boolean estadoIsNull = false;

    /**
     * Permite buscar cuando campo estado es NOT NULL
     */
    private boolean estadoIsNotNull = false;

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
     * Valor de busqueda de campo servidor
     */
    private String servidor;

    /**
     * Tipo de comparador para la busqueda por campo servidor
     */
    private TextComparator servidorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo servidor para busquedas tipo IN
     */
    private List<String> servidorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo servidor es NULL
     */
    private boolean servidorIsNull = false;

    /**
     * Permite buscar cuando campo servidor es NOT NULL
     */
    private boolean servidorIsNotNull = false;

    /**
     * Valor de busqueda de campo codigoexterno
     */
    private String codigoexterno;

    /**
     * Tipo de comparador para la busqueda por campo codigoexterno
     */
    private TextComparator codigoexternoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codigoexterno para busquedas tipo IN
     */
    private List<String> codigoexternoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codigoexterno es NULL
     */
    private boolean codigoexternoIsNull = false;

    /**
     * Permite buscar cuando campo codigoexterno es NOT NULL
     */
    private boolean codigoexternoIsNotNull = false;

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
     * Constructor default
     */
    public ViewHistoricoHistQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ViewHistoricoHistQuery(Long historicoid) {
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
     * Valor de busqueda de campo estadoid
     * @return Long.
     */
    public Long getEstadoid() {
        return estadoid;
    }

    /**
     * Valor de busqueda de campo estadoid
     * @param estadoid Valor de seteo.
     */
    public void setEstadoid(Long estadoid) {
        this.estadoid = estadoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getEstadoidIn() {
        return this.estadoidIn;
    }

    /**
     * @param estadoid Valor a agregar.
     */
    public void addEstadoidIn(Long estadoid) {
        this.estadoidIn.add(estadoid);
    }

    /**
     * Permite buscar cuando campo estadoid es NULL
     * @return boolean.
     */
    public boolean isEstadoidIsNull() {
        return estadoidIsNull;
    }

    /**
     * Permite buscar cuando campo estadoid es NULL
     * @param estadoidIsNull Valor de seteo.
     */
    public void setEstadoidIsNull(boolean estadoidIsNull) {
        this.estadoidIsNull = estadoidIsNull;
    }

    /**
     * Permite buscar cuando campo estadoid es NOT NULL
     * @return boolean.
     */
    public boolean isEstadoidIsNotNull() {
        return estadoidIsNotNull;
    }

    /**
     * Permite buscar cuando campo estadoid es NOT NULL
     * @param estadoidIsNotNull Valor de seteo.
     */
    public void setEstadoidIsNotNull(boolean estadoidIsNotNull) {
        this.estadoidIsNotNull = estadoidIsNotNull;
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
     * Valor de busqueda de campo planificacionid
     * @return Long.
     */
    public Long getPlanificacionid() {
        return planificacionid;
    }

    /**
     * Valor de busqueda de campo planificacionid
     * @param planificacionid Valor de seteo.
     */
    public void setPlanificacionid(Long planificacionid) {
        this.planificacionid = planificacionid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getPlanificacionidIn() {
        return this.planificacionidIn;
    }

    /**
     * @param planificacionid Valor a agregar.
     */
    public void addPlanificacionidIn(Long planificacionid) {
        this.planificacionidIn.add(planificacionid);
    }

    /**
     * Permite buscar cuando campo planificacionid es NULL
     * @return boolean.
     */
    public boolean isPlanificacionidIsNull() {
        return planificacionidIsNull;
    }

    /**
     * Permite buscar cuando campo planificacionid es NULL
     * @param planificacionidIsNull Valor de seteo.
     */
    public void setPlanificacionidIsNull(boolean planificacionidIsNull) {
        this.planificacionidIsNull = planificacionidIsNull;
    }

    /**
     * Permite buscar cuando campo planificacionid es NOT NULL
     * @return boolean.
     */
    public boolean isPlanificacionidIsNotNull() {
        return planificacionidIsNotNull;
    }

    /**
     * Permite buscar cuando campo planificacionid es NOT NULL
     * @param planificacionidIsNotNull Valor de seteo.
     */
    public void setPlanificacionidIsNotNull(boolean planificacionidIsNotNull) {
        this.planificacionidIsNotNull = planificacionidIsNotNull;
    }

    /**
     * Valor de busqueda de campo estado
     * @return String.
     */
    public String getEstado() {
        if (estado != null) {
            switch (estadoComparator) {
	            case STARTS_WITH:
	                return estado + "%";
	            case CONTAINS:
	                return "%" + estado + "%";
	            case ENDS_WITH:
	                return "%" + estado;
	            case EQUALS:
                	return estado;
              	default:
	            	break;
            }
        }
        return estado;
    }

    /**
     * Valor de busqueda de campo estado
     * @param estado Valor de seteo.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Tipo de comparador para la busqueda por campo estado
     * @return estadoComparator.
     */
    public TextComparator getEstadoComparator() {
        return estadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo estado
     * @param estadoComparator Valor de seteo.
     */
    public void setEstadoComparator(TextComparator estadoComparator) {
        this.estadoComparator = estadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEstadoIn() {
        return this.estadoIn;
    }

    /**
     * @param estado Valor a agregar.
     */
    public void addEstadoIn(String estado) {
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
     * Valor de busqueda de campo servidor
     * @return String.
     */
    public String getServidor() {
        if (servidor != null) {
            switch (servidorComparator) {
	            case STARTS_WITH:
	                return servidor + "%";
	            case CONTAINS:
	                return "%" + servidor + "%";
	            case ENDS_WITH:
	                return "%" + servidor;
	            case EQUALS:
                	return servidor;
              	default:
	            	break;
            }
        }
        return servidor;
    }

    /**
     * Valor de busqueda de campo servidor
     * @param servidor Valor de seteo.
     */
    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    /**
     * Tipo de comparador para la busqueda por campo servidor
     * @return servidorComparator.
     */
    public TextComparator getServidorComparator() {
        return servidorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo servidor
     * @param servidorComparator Valor de seteo.
     */
    public void setServidorComparator(TextComparator servidorComparator) {
        this.servidorComparator = servidorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getServidorIn() {
        return this.servidorIn;
    }

    /**
     * @param servidor Valor a agregar.
     */
    public void addServidorIn(String servidor) {
        this.servidorIn.add(servidor);
    }

    /**
     * Permite buscar cuando campo servidor es NULL
     * @return boolean.
     */
    public boolean isServidorIsNull() {
        return servidorIsNull;
    }

    /**
     * Permite buscar cuando campo servidor es NULL
     * @param servidorIsNull Valor de seteo.
     */
    public void setServidorIsNull(boolean servidorIsNull) {
        this.servidorIsNull = servidorIsNull;
    }

    /**
     * Permite buscar cuando campo servidor es NOT NULL
     * @return boolean.
     */
    public boolean isServidorIsNotNull() {
        return servidorIsNotNull;
    }

    /**
     * Permite buscar cuando campo servidor es NOT NULL
     * @param servidorIsNotNull Valor de seteo.
     */
    public void setServidorIsNotNull(boolean servidorIsNotNull) {
        this.servidorIsNotNull = servidorIsNotNull;
    }

    /**
     * Valor de busqueda de campo codigoexterno
     * @return String.
     */
    public String getCodigoexterno() {
        if (codigoexterno != null) {
            switch (codigoexternoComparator) {
	            case STARTS_WITH:
	                return codigoexterno + "%";
	            case CONTAINS:
	                return "%" + codigoexterno + "%";
	            case ENDS_WITH:
	                return "%" + codigoexterno;
	            case EQUALS:
                	return codigoexterno;
              	default:
	            	break;
            }
        }
        return codigoexterno;
    }

    /**
     * Valor de busqueda de campo codigoexterno
     * @param codigoexterno Valor de seteo.
     */
    public void setCodigoexterno(String codigoexterno) {
        this.codigoexterno = codigoexterno;
    }

    /**
     * Tipo de comparador para la busqueda por campo codigoexterno
     * @return codigoexternoComparator.
     */
    public TextComparator getCodigoexternoComparator() {
        return codigoexternoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codigoexterno
     * @param codigoexternoComparator Valor de seteo.
     */
    public void setCodigoexternoComparator(TextComparator codigoexternoComparator) {
        this.codigoexternoComparator = codigoexternoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodigoexternoIn() {
        return this.codigoexternoIn;
    }

    /**
     * @param codigoexterno Valor a agregar.
     */
    public void addCodigoexternoIn(String codigoexterno) {
        this.codigoexternoIn.add(codigoexterno);
    }

    /**
     * Permite buscar cuando campo codigoexterno es NULL
     * @return boolean.
     */
    public boolean isCodigoexternoIsNull() {
        return codigoexternoIsNull;
    }

    /**
     * Permite buscar cuando campo codigoexterno es NULL
     * @param codigoexternoIsNull Valor de seteo.
     */
    public void setCodigoexternoIsNull(boolean codigoexternoIsNull) {
        this.codigoexternoIsNull = codigoexternoIsNull;
    }

    /**
     * Permite buscar cuando campo codigoexterno es NOT NULL
     * @return boolean.
     */
    public boolean isCodigoexternoIsNotNull() {
        return codigoexternoIsNotNull;
    }

    /**
     * Permite buscar cuando campo codigoexterno es NOT NULL
     * @param codigoexternoIsNotNull Valor de seteo.
     */
    public void setCodigoexternoIsNotNull(boolean codigoexternoIsNotNull) {
        this.codigoexternoIsNotNull = codigoexternoIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getHistoricoid() != null) {
            criteria.add(Restrictions.eq(HISTORICOID, getHistoricoid()));
        }

        if (getHistoricoidIn().size() > 0) {
            criteria.add(Restrictions.in(HISTORICOID, getHistoricoidIn()));
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

        if (getEstadoid() != null) {
            criteria.add(Restrictions.eq(ESTADOID, getEstadoid()));
        }

        if (getEstadoidIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADOID, getEstadoidIn()));
        }

        if (isEstadoidIsNull()) {
            criteria.add(Restrictions.isNull(ESTADOID));
        }

        if (isEstadoidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADOID));
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

        if (getPlanificacionid() != null) {
            criteria.add(Restrictions.eq(PLANIFICACIONID, getPlanificacionid()));
        }

        if (getPlanificacionidIn().size() > 0) {
            criteria.add(Restrictions.in(PLANIFICACIONID, getPlanificacionidIn()));
        }

        if (isPlanificacionidIsNull()) {
            criteria.add(Restrictions.isNull(PLANIFICACIONID));
        }

        if (isPlanificacionidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PLANIFICACIONID));
        }

        if (getEstado() != null) {
            if (getEstadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ESTADO, getEstado()));
            } 
            else if (getEstadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ESTADO, getEstado()));
            }
            else if (getEstadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ESTADO, getEstado())));
            }
            else {
                criteria.add(Restrictions.like(ESTADO, getEstado()));
            }
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

        if (getServidor() != null) {
            if (getServidorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SERVIDOR, getServidor()));
            } 
            else if (getServidorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SERVIDOR, getServidor()));
            }
            else if (getServidorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SERVIDOR, getServidor())));
            }
            else {
                criteria.add(Restrictions.like(SERVIDOR, getServidor()));
            }
        }

        if (getServidorIn().size() > 0) {
            criteria.add(Restrictions.in(SERVIDOR, getServidorIn()));
        }

        if (isServidorIsNull()) {
            criteria.add(Restrictions.isNull(SERVIDOR));
        }

        if (isServidorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVIDOR));
        }

        if (getCodigoexterno() != null) {
            if (getCodigoexternoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODIGOEXTERNO, getCodigoexterno()));
            } 
            else if (getCodigoexternoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODIGOEXTERNO, getCodigoexterno()));
            }
            else if (getCodigoexternoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODIGOEXTERNO, getCodigoexterno())));
            }
            else {
                criteria.add(Restrictions.like(CODIGOEXTERNO, getCodigoexterno()));
            }
        }

        if (getCodigoexternoIn().size() > 0) {
            criteria.add(Restrictions.in(CODIGOEXTERNO, getCodigoexternoIn()));
        }

        if (isCodigoexternoIsNull()) {
            criteria.add(Restrictions.isNull(CODIGOEXTERNO));
        }

        if (isCodigoexternoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODIGOEXTERNO));
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
 
