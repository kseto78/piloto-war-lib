/*
 *
 * archivo: TblDestinatariosMensHistQuery.java
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
import es.minhap.sim.model.TblDestinatariosMensHist;

/**
 * Clase con criterios de busqueda para la entidad TblDestinatariosMensHist
 */
public class TblDestinatariosMensHistQuery extends AbstractHibernateQueryEntity<TblDestinatariosMensHist> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String DESTINATARIOSMENSAJES = "destinatariosmensajes";
    public static final String MENSAJEID = "mensajeid";
    public static final String DESTINATARIO = "destinatario";
    public static final String ESTADO = "estado";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String CODIGOEXTERNO = "codigoexterno";
    public static final String UIM = "uim";
    public static final String NODO = "nodo";
    public static final String FECHAHISTORIFICACION = "fechahistorificacion";
    public static final String ULTIMOENVIO = "ultimoenvio";


    /**
     * Valor de busqueda de campo destinatariosmensajes
     */
    private Long destinatariosmensajes;

    /**
     * Lista de valores del campo destinatariosmensajes para busquedas tipo IN
     */
    private List<Long> destinatariosmensajesIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo destinatario
     */
    private String destinatario;

    /**
     * Tipo de comparador para la busqueda por campo destinatario
     */
    private TextComparator destinatarioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo destinatario para busquedas tipo IN
     */
    private List<String> destinatarioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo destinatario es NULL
     */
    private boolean destinatarioIsNull = false;

    /**
     * Permite buscar cuando campo destinatario es NOT NULL
     */
    private boolean destinatarioIsNotNull = false;

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
     * Valor de busqueda de campo uim
     */
    private String uim;

    /**
     * Tipo de comparador para la busqueda por campo uim
     */
    private TextComparator uimComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo uim para busquedas tipo IN
     */
    private List<String> uimIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo uim es NULL
     */
    private boolean uimIsNull = false;

    /**
     * Permite buscar cuando campo uim es NOT NULL
     */
    private boolean uimIsNotNull = false;

    /**
     * Valor de busqueda de campo nodo
     */
    private Integer nodo;

    /**
     * Lista de valores del campo nodo para busquedas tipo IN
     */
    private List<Integer> nodoIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo nodo es NULL
     */
    private boolean nodoIsNull = false;

    /**
     * Permite buscar cuando campo nodo es NOT NULL
     */
    private boolean nodoIsNotNull = false;

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
     * Valor inferior de rango de busqueda de fecha ultimoenvio
     */
    private Date ultimoenvioMin;

    /**
     * Valor superior de rango de busqueda de fecha ultimoenvio
     */
    private Date ultimoenvioMax;

    /**
     * Permite buscar cuando campo ultimoenvio es NULL
     */
    private boolean ultimoenvioIsNull = false;

    /**
     * Permite buscar cuando campo ultimoenvio es NOT NULL
     */
    private boolean ultimoenvioIsNotNull = false;

    /**
     * Constructor default
     */
    public TblDestinatariosMensHistQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblDestinatariosMensHistQuery(Long destinatariosmensajes) {
        setDestinatariosmensajes(destinatariosmensajes);
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
     * Valor de busqueda de campo destinatario
     * @return String.
     */
    public String getDestinatario() {
        if (destinatario != null) {
            switch (destinatarioComparator) {
	            case STARTS_WITH:
	                return destinatario + "%";
	            case CONTAINS:
	                return "%" + destinatario + "%";
	            case ENDS_WITH:
	                return "%" + destinatario;
	            case EQUALS:
                	return destinatario;
              	default:
	            	break;
            }
        }
        return destinatario;
    }

    /**
     * Valor de busqueda de campo destinatario
     * @param destinatario Valor de seteo.
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * Tipo de comparador para la busqueda por campo destinatario
     * @return destinatarioComparator.
     */
    public TextComparator getDestinatarioComparator() {
        return destinatarioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo destinatario
     * @param destinatarioComparator Valor de seteo.
     */
    public void setDestinatarioComparator(TextComparator destinatarioComparator) {
        this.destinatarioComparator = destinatarioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDestinatarioIn() {
        return this.destinatarioIn;
    }

    /**
     * @param destinatario Valor a agregar.
     */
    public void addDestinatarioIn(String destinatario) {
        this.destinatarioIn.add(destinatario);
    }

    /**
     * Permite buscar cuando campo destinatario es NULL
     * @return boolean.
     */
    public boolean isDestinatarioIsNull() {
        return destinatarioIsNull;
    }

    /**
     * Permite buscar cuando campo destinatario es NULL
     * @param destinatarioIsNull Valor de seteo.
     */
    public void setDestinatarioIsNull(boolean destinatarioIsNull) {
        this.destinatarioIsNull = destinatarioIsNull;
    }

    /**
     * Permite buscar cuando campo destinatario es NOT NULL
     * @return boolean.
     */
    public boolean isDestinatarioIsNotNull() {
        return destinatarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo destinatario es NOT NULL
     * @param destinatarioIsNotNull Valor de seteo.
     */
    public void setDestinatarioIsNotNull(boolean destinatarioIsNotNull) {
        this.destinatarioIsNotNull = destinatarioIsNotNull;
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
     * Valor de busqueda de campo uim
     * @return String.
     */
    public String getUim() {
        if (uim != null) {
            switch (uimComparator) {
	            case STARTS_WITH:
	                return uim + "%";
	            case CONTAINS:
	                return "%" + uim + "%";
	            case ENDS_WITH:
	                return "%" + uim;
	            case EQUALS:
                	return uim;
              	default:
	            	break;
            }
        }
        return uim;
    }

    /**
     * Valor de busqueda de campo uim
     * @param uim Valor de seteo.
     */
    public void setUim(String uim) {
        this.uim = uim;
    }

    /**
     * Tipo de comparador para la busqueda por campo uim
     * @return uimComparator.
     */
    public TextComparator getUimComparator() {
        return uimComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo uim
     * @param uimComparator Valor de seteo.
     */
    public void setUimComparator(TextComparator uimComparator) {
        this.uimComparator = uimComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUimIn() {
        return this.uimIn;
    }

    /**
     * @param uim Valor a agregar.
     */
    public void addUimIn(String uim) {
        this.uimIn.add(uim);
    }

    /**
     * Permite buscar cuando campo uim es NULL
     * @return boolean.
     */
    public boolean isUimIsNull() {
        return uimIsNull;
    }

    /**
     * Permite buscar cuando campo uim es NULL
     * @param uimIsNull Valor de seteo.
     */
    public void setUimIsNull(boolean uimIsNull) {
        this.uimIsNull = uimIsNull;
    }

    /**
     * Permite buscar cuando campo uim es NOT NULL
     * @return boolean.
     */
    public boolean isUimIsNotNull() {
        return uimIsNotNull;
    }

    /**
     * Permite buscar cuando campo uim es NOT NULL
     * @param uimIsNotNull Valor de seteo.
     */
    public void setUimIsNotNull(boolean uimIsNotNull) {
        this.uimIsNotNull = uimIsNotNull;
    }

    /**
     * Valor de busqueda de campo nodo
     * @return Integer.
     */
    public Integer getNodo() {
        return nodo;
    }

    /**
     * Valor de busqueda de campo nodo
     * @param nodo Valor de seteo.
     */
    public void setNodo(Integer nodo) {
        this.nodo = nodo;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getNodoIn() {
        return this.nodoIn;
    }

    /**
     * @param nodo Valor a agregar.
     */
    public void addNodoIn(Integer nodo) {
        this.nodoIn.add(nodo);
    }

    /**
     * Permite buscar cuando campo nodo es NULL
     * @return boolean.
     */
    public boolean isNodoIsNull() {
        return nodoIsNull;
    }

    /**
     * Permite buscar cuando campo nodo es NULL
     * @param nodoIsNull Valor de seteo.
     */
    public void setNodoIsNull(boolean nodoIsNull) {
        this.nodoIsNull = nodoIsNull;
    }

    /**
     * Permite buscar cuando campo nodo es NOT NULL
     * @return boolean.
     */
    public boolean isNodoIsNotNull() {
        return nodoIsNotNull;
    }

    /**
     * Permite buscar cuando campo nodo es NOT NULL
     * @param nodoIsNotNull Valor de seteo.
     */
    public void setNodoIsNotNull(boolean nodoIsNotNull) {
        this.nodoIsNotNull = nodoIsNotNull;
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
     * Valor inferior de rango de busqueda de fecha ultimoenvio
     * @return ${field.getName()}Min.
     */
    public Date getUltimoenvioMin() {
        if (ultimoenvioMin != null) {
            return DateUtil.toDayBegin(ultimoenvioMin);
        }
        return ultimoenvioMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha ultimoenvio
     * @param ultimoenvioMin Valor de seteo.
     */
    public void setUltimoenvioMin(Date ultimoenvioMin) {
        this.ultimoenvioMin = ultimoenvioMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha ultimoenvio
     * @return ultimoenvioMax.
     */
    public Date getUltimoenvioMax() {
        if (ultimoenvioMax != null) {
            return DateUtil.toDayEnd(ultimoenvioMax);
        }
        return ultimoenvioMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha ultimoenvio
     * @param ultimoenvioMax Valor de seteo.
     */
    public void setUltimoenvioMax(Date ultimoenvioMax) {
        this.ultimoenvioMax = ultimoenvioMax;
    }

    /**
     * Permite buscar cuando campo ultimoenvio es NULL
     * @return boolean.
     */
    public boolean isUltimoenvioIsNull() {
        return ultimoenvioIsNull;
    }

    /**
     * Permite buscar cuando campo ultimoenvio es NULL
     * @param ultimoenvioIsNull Valor de seteo.
     */
    public void setUltimoenvioIsNull(boolean ultimoenvioIsNull) {
        this.ultimoenvioIsNull = ultimoenvioIsNull;
    }

    /**
     * Permite buscar cuando campo ultimoenvio es NOT NULL
     * @return boolean.
     */
    public boolean isUltimoenvioIsNotNull() {
        return ultimoenvioIsNotNull;
    }

    /**
     * Permite buscar cuando campo ultimoenvio es NOT NULL
     * @param ultimoenvioIsNotNull Valor de seteo.
     */
    public void setUltimoenvioIsNotNull(boolean ultimoenvioIsNotNull) {
        this.ultimoenvioIsNotNull = ultimoenvioIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getDestinatariosmensajes() != null) {
            criteria.add(Restrictions.eq(DESTINATARIOSMENSAJES, getDestinatariosmensajes()));
        }

        if (getDestinatariosmensajesIn().size() > 0) {
            criteria.add(Restrictions.in(DESTINATARIOSMENSAJES, getDestinatariosmensajesIn()));
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

        if (getDestinatario() != null) {
            if (getDestinatarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DESTINATARIO, getDestinatario()));
            } 
            else if (getDestinatarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DESTINATARIO, getDestinatario()));
            }
            else if (getDestinatarioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DESTINATARIO, getDestinatario())));
            }
            else {
                criteria.add(Restrictions.like(DESTINATARIO, getDestinatario()));
            }
        }

        if (getDestinatarioIn().size() > 0) {
            criteria.add(Restrictions.in(DESTINATARIO, getDestinatarioIn()));
        }

        if (isDestinatarioIsNull()) {
            criteria.add(Restrictions.isNull(DESTINATARIO));
        }

        if (isDestinatarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DESTINATARIO));
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

        if (getUim() != null) {
            if (getUimComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(UIM, getUim()));
            } 
            else if (getUimComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(UIM, getUim()));
            }
            else if (getUimComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(UIM, getUim())));
            }
            else {
                criteria.add(Restrictions.like(UIM, getUim()));
            }
        }

        if (getUimIn().size() > 0) {
            criteria.add(Restrictions.in(UIM, getUimIn()));
        }

        if (isUimIsNull()) {
            criteria.add(Restrictions.isNull(UIM));
        }

        if (isUimIsNotNull()) {
            criteria.add(Restrictions.isNotNull(UIM));
        }

        if (getNodo() != null) {
            criteria.add(Restrictions.eq(NODO, getNodo()));
        }

        if (getNodoIn().size() > 0) {
            criteria.add(Restrictions.in(NODO, getNodoIn()));
        }

        if (isNodoIsNull()) {
            criteria.add(Restrictions.isNull(NODO));
        }

        if (isNodoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NODO));
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

        if (getUltimoenvioMin() != null) {
            criteria.add(Restrictions.ge(ULTIMOENVIO, getUltimoenvioMin()));
        }

        if (getUltimoenvioMax() != null) {
            criteria.add(Restrictions.le(ULTIMOENVIO, getUltimoenvioMax()));
        }

        if (isUltimoenvioIsNull()) {
            criteria.add(Restrictions.isNull(ULTIMOENVIO));
        }

        if (isUltimoenvioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ULTIMOENVIO));
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
 
