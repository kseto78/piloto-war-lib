/*
 *
 * archivo: ViewMensajesDetalladaQuery.java
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
import es.minhap.sim.model.ViewMensajesDetallada;

/**
 * Clase con criterios de busqueda para la entidad ViewMensajesDetallada
 */
public class ViewMensajesDetalladaQuery extends AbstractHibernateQueryEntity<ViewMensajesDetallada> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String MENSAJEID = "mensajeid";
    public static final String LOTEENVIOID = "loteenvioid";
    public static final String CODIGOEXTERNO = "codigoexterno";
    public static final String CUERPO = "cuerpo";
    public static final String CABECERA = "cabecera";
    public static final String ESTADOACTUAL = "estadoactual";
    public static final String NUMEROENVIOS = "numeroenvios";
    public static final String ULTIMOENVIO = "ultimoenvio";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String ULTIMOIDHISTORICO = "ultimoidhistorico";
    public static final String TIPOCUERPO = "tipocuerpo";
    public static final String TIPOCODIFICACION = "tipocodificacion";
    public static final String PRIORIDAD = "prioridad";
    public static final String TIPOMENSAJE = "tipomensaje";
    public static final String TELEFONO = "telefono";
    public static final String CANALID = "canalid";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";


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
     * Valor de busqueda de campo loteenvioid
     */
    private Long loteenvioid;

    /**
     * Lista de valores del campo loteenvioid para busquedas tipo IN
     */
    private List<Long> loteenvioidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo loteenvioid es NULL
     */
    private boolean loteenvioidIsNull = false;

    /**
     * Permite buscar cuando campo loteenvioid es NOT NULL
     */
    private boolean loteenvioidIsNotNull = false;

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
     * Valor de busqueda de campo cuerpo
     */
    private String cuerpo;

    /**
     * Tipo de comparador para la busqueda por campo cuerpo
     */
    private TextComparator cuerpoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo cuerpo para busquedas tipo IN
     */
    private List<String> cuerpoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo cuerpo es NULL
     */
    private boolean cuerpoIsNull = false;

    /**
     * Permite buscar cuando campo cuerpo es NOT NULL
     */
    private boolean cuerpoIsNotNull = false;

    /**
     * Valor de busqueda de campo cabecera
     */
    private String cabecera;

    /**
     * Tipo de comparador para la busqueda por campo cabecera
     */
    private TextComparator cabeceraComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo cabecera para busquedas tipo IN
     */
    private List<String> cabeceraIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo cabecera es NULL
     */
    private boolean cabeceraIsNull = false;

    /**
     * Permite buscar cuando campo cabecera es NOT NULL
     */
    private boolean cabeceraIsNotNull = false;

    /**
     * Valor de busqueda de campo estadoactual
     */
    private String estadoactual;

    /**
     * Tipo de comparador para la busqueda por campo estadoactual
     */
    private TextComparator estadoactualComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo estadoactual para busquedas tipo IN
     */
    private List<String> estadoactualIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo estadoactual es NULL
     */
    private boolean estadoactualIsNull = false;

    /**
     * Permite buscar cuando campo estadoactual es NOT NULL
     */
    private boolean estadoactualIsNotNull = false;

    /**
     * Valor de busqueda de campo numeroenvios
     */
    private Integer numeroenvios;

    /**
     * Lista de valores del campo numeroenvios para busquedas tipo IN
     */
    private List<Integer> numeroenviosIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo numeroenvios es NULL
     */
    private boolean numeroenviosIsNull = false;

    /**
     * Permite buscar cuando campo numeroenvios es NOT NULL
     */
    private boolean numeroenviosIsNotNull = false;

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
     * Valor de busqueda de campo ultimoidhistorico
     */
    private Long ultimoidhistorico;

    /**
     * Lista de valores del campo ultimoidhistorico para busquedas tipo IN
     */
    private List<Long> ultimoidhistoricoIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo ultimoidhistorico es NULL
     */
    private boolean ultimoidhistoricoIsNull = false;

    /**
     * Permite buscar cuando campo ultimoidhistorico es NOT NULL
     */
    private boolean ultimoidhistoricoIsNotNull = false;

    /**
     * Valor de busqueda de campo tipocuerpo
     */
    private String tipocuerpo;

    /**
     * Tipo de comparador para la busqueda por campo tipocuerpo
     */
    private TextComparator tipocuerpoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo tipocuerpo para busquedas tipo IN
     */
    private List<String> tipocuerpoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo tipocuerpo es NULL
     */
    private boolean tipocuerpoIsNull = false;

    /**
     * Permite buscar cuando campo tipocuerpo es NOT NULL
     */
    private boolean tipocuerpoIsNotNull = false;

    /**
     * Valor de busqueda de campo tipocodificacion
     */
    private String tipocodificacion;

    /**
     * Tipo de comparador para la busqueda por campo tipocodificacion
     */
    private TextComparator tipocodificacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo tipocodificacion para busquedas tipo IN
     */
    private List<String> tipocodificacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo tipocodificacion es NULL
     */
    private boolean tipocodificacionIsNull = false;

    /**
     * Permite buscar cuando campo tipocodificacion es NOT NULL
     */
    private boolean tipocodificacionIsNotNull = false;

    /**
     * Valor de busqueda de campo prioridad
     */
    private Integer prioridad;

    /**
     * Lista de valores del campo prioridad para busquedas tipo IN
     */
    private List<Integer> prioridadIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo prioridad es NULL
     */
    private boolean prioridadIsNull = false;

    /**
     * Permite buscar cuando campo prioridad es NOT NULL
     */
    private boolean prioridadIsNotNull = false;

    /**
     * Valor de busqueda de campo tipomensaje
     */
    private String tipomensaje;

    /**
     * Tipo de comparador para la busqueda por campo tipomensaje
     */
    private TextComparator tipomensajeComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo tipomensaje para busquedas tipo IN
     */
    private List<String> tipomensajeIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo tipomensaje es NULL
     */
    private boolean tipomensajeIsNull = false;

    /**
     * Permite buscar cuando campo tipomensaje es NOT NULL
     */
    private boolean tipomensajeIsNotNull = false;

    /**
     * Valor de busqueda de campo telefono
     */
    private String telefono;

    /**
     * Tipo de comparador para la busqueda por campo telefono
     */
    private TextComparator telefonoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo telefono para busquedas tipo IN
     */
    private List<String> telefonoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo telefono es NULL
     */
    private boolean telefonoIsNull = false;

    /**
     * Permite buscar cuando campo telefono es NOT NULL
     */
    private boolean telefonoIsNotNull = false;

    /**
     * Valor de busqueda de campo canalid
     */
    private Long canalid;

    /**
     * Lista de valores del campo canalid para busquedas tipo IN
     */
    private List<Long> canalidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo canalid es NULL
     */
    private boolean canalidIsNull = false;

    /**
     * Permite buscar cuando campo canalid es NOT NULL
     */
    private boolean canalidIsNotNull = false;

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
     * Constructor default
     */
    public ViewMensajesDetalladaQuery() {
    
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
     * Permite buscar cuando campo loteenvioid es NULL
     * @return boolean.
     */
    public boolean isLoteenvioidIsNull() {
        return loteenvioidIsNull;
    }

    /**
     * Permite buscar cuando campo loteenvioid es NULL
     * @param loteenvioidIsNull Valor de seteo.
     */
    public void setLoteenvioidIsNull(boolean loteenvioidIsNull) {
        this.loteenvioidIsNull = loteenvioidIsNull;
    }

    /**
     * Permite buscar cuando campo loteenvioid es NOT NULL
     * @return boolean.
     */
    public boolean isLoteenvioidIsNotNull() {
        return loteenvioidIsNotNull;
    }

    /**
     * Permite buscar cuando campo loteenvioid es NOT NULL
     * @param loteenvioidIsNotNull Valor de seteo.
     */
    public void setLoteenvioidIsNotNull(boolean loteenvioidIsNotNull) {
        this.loteenvioidIsNotNull = loteenvioidIsNotNull;
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
     * Valor de busqueda de campo cuerpo
     * @return String.
     */
    public String getCuerpo() {
        if (cuerpo != null) {
            switch (cuerpoComparator) {
	            case STARTS_WITH:
	                return cuerpo + "%";
	            case CONTAINS:
	                return "%" + cuerpo + "%";
	            case ENDS_WITH:
	                return "%" + cuerpo;
	            case EQUALS:
                	return cuerpo;
              	default:
	            	break;
            }
        }
        return cuerpo;
    }

    /**
     * Valor de busqueda de campo cuerpo
     * @param cuerpo Valor de seteo.
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    /**
     * Tipo de comparador para la busqueda por campo cuerpo
     * @return cuerpoComparator.
     */
    public TextComparator getCuerpoComparator() {
        return cuerpoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo cuerpo
     * @param cuerpoComparator Valor de seteo.
     */
    public void setCuerpoComparator(TextComparator cuerpoComparator) {
        this.cuerpoComparator = cuerpoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCuerpoIn() {
        return this.cuerpoIn;
    }

    /**
     * @param cuerpo Valor a agregar.
     */
    public void addCuerpoIn(String cuerpo) {
        this.cuerpoIn.add(cuerpo);
    }

    /**
     * Permite buscar cuando campo cuerpo es NULL
     * @return boolean.
     */
    public boolean isCuerpoIsNull() {
        return cuerpoIsNull;
    }

    /**
     * Permite buscar cuando campo cuerpo es NULL
     * @param cuerpoIsNull Valor de seteo.
     */
    public void setCuerpoIsNull(boolean cuerpoIsNull) {
        this.cuerpoIsNull = cuerpoIsNull;
    }

    /**
     * Permite buscar cuando campo cuerpo es NOT NULL
     * @return boolean.
     */
    public boolean isCuerpoIsNotNull() {
        return cuerpoIsNotNull;
    }

    /**
     * Permite buscar cuando campo cuerpo es NOT NULL
     * @param cuerpoIsNotNull Valor de seteo.
     */
    public void setCuerpoIsNotNull(boolean cuerpoIsNotNull) {
        this.cuerpoIsNotNull = cuerpoIsNotNull;
    }

    /**
     * Valor de busqueda de campo cabecera
     * @return String.
     */
    public String getCabecera() {
        if (cabecera != null) {
            switch (cabeceraComparator) {
	            case STARTS_WITH:
	                return cabecera + "%";
	            case CONTAINS:
	                return "%" + cabecera + "%";
	            case ENDS_WITH:
	                return "%" + cabecera;
	            case EQUALS:
                	return cabecera;
              	default:
	            	break;
            }
        }
        return cabecera;
    }

    /**
     * Valor de busqueda de campo cabecera
     * @param cabecera Valor de seteo.
     */
    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    /**
     * Tipo de comparador para la busqueda por campo cabecera
     * @return cabeceraComparator.
     */
    public TextComparator getCabeceraComparator() {
        return cabeceraComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo cabecera
     * @param cabeceraComparator Valor de seteo.
     */
    public void setCabeceraComparator(TextComparator cabeceraComparator) {
        this.cabeceraComparator = cabeceraComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCabeceraIn() {
        return this.cabeceraIn;
    }

    /**
     * @param cabecera Valor a agregar.
     */
    public void addCabeceraIn(String cabecera) {
        this.cabeceraIn.add(cabecera);
    }

    /**
     * Permite buscar cuando campo cabecera es NULL
     * @return boolean.
     */
    public boolean isCabeceraIsNull() {
        return cabeceraIsNull;
    }

    /**
     * Permite buscar cuando campo cabecera es NULL
     * @param cabeceraIsNull Valor de seteo.
     */
    public void setCabeceraIsNull(boolean cabeceraIsNull) {
        this.cabeceraIsNull = cabeceraIsNull;
    }

    /**
     * Permite buscar cuando campo cabecera es NOT NULL
     * @return boolean.
     */
    public boolean isCabeceraIsNotNull() {
        return cabeceraIsNotNull;
    }

    /**
     * Permite buscar cuando campo cabecera es NOT NULL
     * @param cabeceraIsNotNull Valor de seteo.
     */
    public void setCabeceraIsNotNull(boolean cabeceraIsNotNull) {
        this.cabeceraIsNotNull = cabeceraIsNotNull;
    }

    /**
     * Valor de busqueda de campo estadoactual
     * @return String.
     */
    public String getEstadoactual() {
        if (estadoactual != null) {
            switch (estadoactualComparator) {
	            case STARTS_WITH:
	                return estadoactual + "%";
	            case CONTAINS:
	                return "%" + estadoactual + "%";
	            case ENDS_WITH:
	                return "%" + estadoactual;
	            case EQUALS:
                	return estadoactual;
              	default:
	            	break;
            }
        }
        return estadoactual;
    }

    /**
     * Valor de busqueda de campo estadoactual
     * @param estadoactual Valor de seteo.
     */
    public void setEstadoactual(String estadoactual) {
        this.estadoactual = estadoactual;
    }

    /**
     * Tipo de comparador para la busqueda por campo estadoactual
     * @return estadoactualComparator.
     */
    public TextComparator getEstadoactualComparator() {
        return estadoactualComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo estadoactual
     * @param estadoactualComparator Valor de seteo.
     */
    public void setEstadoactualComparator(TextComparator estadoactualComparator) {
        this.estadoactualComparator = estadoactualComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEstadoactualIn() {
        return this.estadoactualIn;
    }

    /**
     * @param estadoactual Valor a agregar.
     */
    public void addEstadoactualIn(String estadoactual) {
        this.estadoactualIn.add(estadoactual);
    }

    /**
     * Permite buscar cuando campo estadoactual es NULL
     * @return boolean.
     */
    public boolean isEstadoactualIsNull() {
        return estadoactualIsNull;
    }

    /**
     * Permite buscar cuando campo estadoactual es NULL
     * @param estadoactualIsNull Valor de seteo.
     */
    public void setEstadoactualIsNull(boolean estadoactualIsNull) {
        this.estadoactualIsNull = estadoactualIsNull;
    }

    /**
     * Permite buscar cuando campo estadoactual es NOT NULL
     * @return boolean.
     */
    public boolean isEstadoactualIsNotNull() {
        return estadoactualIsNotNull;
    }

    /**
     * Permite buscar cuando campo estadoactual es NOT NULL
     * @param estadoactualIsNotNull Valor de seteo.
     */
    public void setEstadoactualIsNotNull(boolean estadoactualIsNotNull) {
        this.estadoactualIsNotNull = estadoactualIsNotNull;
    }

    /**
     * Valor de busqueda de campo numeroenvios
     * @return Integer.
     */
    public Integer getNumeroenvios() {
        return numeroenvios;
    }

    /**
     * Valor de busqueda de campo numeroenvios
     * @param numeroenvios Valor de seteo.
     */
    public void setNumeroenvios(Integer numeroenvios) {
        this.numeroenvios = numeroenvios;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getNumeroenviosIn() {
        return this.numeroenviosIn;
    }

    /**
     * @param numeroenvios Valor a agregar.
     */
    public void addNumeroenviosIn(Integer numeroenvios) {
        this.numeroenviosIn.add(numeroenvios);
    }

    /**
     * Permite buscar cuando campo numeroenvios es NULL
     * @return boolean.
     */
    public boolean isNumeroenviosIsNull() {
        return numeroenviosIsNull;
    }

    /**
     * Permite buscar cuando campo numeroenvios es NULL
     * @param numeroenviosIsNull Valor de seteo.
     */
    public void setNumeroenviosIsNull(boolean numeroenviosIsNull) {
        this.numeroenviosIsNull = numeroenviosIsNull;
    }

    /**
     * Permite buscar cuando campo numeroenvios es NOT NULL
     * @return boolean.
     */
    public boolean isNumeroenviosIsNotNull() {
        return numeroenviosIsNotNull;
    }

    /**
     * Permite buscar cuando campo numeroenvios es NOT NULL
     * @param numeroenviosIsNotNull Valor de seteo.
     */
    public void setNumeroenviosIsNotNull(boolean numeroenviosIsNotNull) {
        this.numeroenviosIsNotNull = numeroenviosIsNotNull;
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
     * Valor de busqueda de campo ultimoidhistorico
     * @return Long.
     */
    public Long getUltimoidhistorico() {
        return ultimoidhistorico;
    }

    /**
     * Valor de busqueda de campo ultimoidhistorico
     * @param ultimoidhistorico Valor de seteo.
     */
    public void setUltimoidhistorico(Long ultimoidhistorico) {
        this.ultimoidhistorico = ultimoidhistorico;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getUltimoidhistoricoIn() {
        return this.ultimoidhistoricoIn;
    }

    /**
     * @param ultimoidhistorico Valor a agregar.
     */
    public void addUltimoidhistoricoIn(Long ultimoidhistorico) {
        this.ultimoidhistoricoIn.add(ultimoidhistorico);
    }

    /**
     * Permite buscar cuando campo ultimoidhistorico es NULL
     * @return boolean.
     */
    public boolean isUltimoidhistoricoIsNull() {
        return ultimoidhistoricoIsNull;
    }

    /**
     * Permite buscar cuando campo ultimoidhistorico es NULL
     * @param ultimoidhistoricoIsNull Valor de seteo.
     */
    public void setUltimoidhistoricoIsNull(boolean ultimoidhistoricoIsNull) {
        this.ultimoidhistoricoIsNull = ultimoidhistoricoIsNull;
    }

    /**
     * Permite buscar cuando campo ultimoidhistorico es NOT NULL
     * @return boolean.
     */
    public boolean isUltimoidhistoricoIsNotNull() {
        return ultimoidhistoricoIsNotNull;
    }

    /**
     * Permite buscar cuando campo ultimoidhistorico es NOT NULL
     * @param ultimoidhistoricoIsNotNull Valor de seteo.
     */
    public void setUltimoidhistoricoIsNotNull(boolean ultimoidhistoricoIsNotNull) {
        this.ultimoidhistoricoIsNotNull = ultimoidhistoricoIsNotNull;
    }

    /**
     * Valor de busqueda de campo tipocuerpo
     * @return String.
     */
    public String getTipocuerpo() {
        if (tipocuerpo != null) {
            switch (tipocuerpoComparator) {
	            case STARTS_WITH:
	                return tipocuerpo + "%";
	            case CONTAINS:
	                return "%" + tipocuerpo + "%";
	            case ENDS_WITH:
	                return "%" + tipocuerpo;
	            case EQUALS:
                	return tipocuerpo;
              	default:
	            	break;
            }
        }
        return tipocuerpo;
    }

    /**
     * Valor de busqueda de campo tipocuerpo
     * @param tipocuerpo Valor de seteo.
     */
    public void setTipocuerpo(String tipocuerpo) {
        this.tipocuerpo = tipocuerpo;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipocuerpo
     * @return tipocuerpoComparator.
     */
    public TextComparator getTipocuerpoComparator() {
        return tipocuerpoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipocuerpo
     * @param tipocuerpoComparator Valor de seteo.
     */
    public void setTipocuerpoComparator(TextComparator tipocuerpoComparator) {
        this.tipocuerpoComparator = tipocuerpoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTipocuerpoIn() {
        return this.tipocuerpoIn;
    }

    /**
     * @param tipocuerpo Valor a agregar.
     */
    public void addTipocuerpoIn(String tipocuerpo) {
        this.tipocuerpoIn.add(tipocuerpo);
    }

    /**
     * Permite buscar cuando campo tipocuerpo es NULL
     * @return boolean.
     */
    public boolean isTipocuerpoIsNull() {
        return tipocuerpoIsNull;
    }

    /**
     * Permite buscar cuando campo tipocuerpo es NULL
     * @param tipocuerpoIsNull Valor de seteo.
     */
    public void setTipocuerpoIsNull(boolean tipocuerpoIsNull) {
        this.tipocuerpoIsNull = tipocuerpoIsNull;
    }

    /**
     * Permite buscar cuando campo tipocuerpo es NOT NULL
     * @return boolean.
     */
    public boolean isTipocuerpoIsNotNull() {
        return tipocuerpoIsNotNull;
    }

    /**
     * Permite buscar cuando campo tipocuerpo es NOT NULL
     * @param tipocuerpoIsNotNull Valor de seteo.
     */
    public void setTipocuerpoIsNotNull(boolean tipocuerpoIsNotNull) {
        this.tipocuerpoIsNotNull = tipocuerpoIsNotNull;
    }

    /**
     * Valor de busqueda de campo tipocodificacion
     * @return String.
     */
    public String getTipocodificacion() {
        if (tipocodificacion != null) {
            switch (tipocodificacionComparator) {
	            case STARTS_WITH:
	                return tipocodificacion + "%";
	            case CONTAINS:
	                return "%" + tipocodificacion + "%";
	            case ENDS_WITH:
	                return "%" + tipocodificacion;
	            case EQUALS:
                	return tipocodificacion;
              	default:
	            	break;
            }
        }
        return tipocodificacion;
    }

    /**
     * Valor de busqueda de campo tipocodificacion
     * @param tipocodificacion Valor de seteo.
     */
    public void setTipocodificacion(String tipocodificacion) {
        this.tipocodificacion = tipocodificacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipocodificacion
     * @return tipocodificacionComparator.
     */
    public TextComparator getTipocodificacionComparator() {
        return tipocodificacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipocodificacion
     * @param tipocodificacionComparator Valor de seteo.
     */
    public void setTipocodificacionComparator(TextComparator tipocodificacionComparator) {
        this.tipocodificacionComparator = tipocodificacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTipocodificacionIn() {
        return this.tipocodificacionIn;
    }

    /**
     * @param tipocodificacion Valor a agregar.
     */
    public void addTipocodificacionIn(String tipocodificacion) {
        this.tipocodificacionIn.add(tipocodificacion);
    }

    /**
     * Permite buscar cuando campo tipocodificacion es NULL
     * @return boolean.
     */
    public boolean isTipocodificacionIsNull() {
        return tipocodificacionIsNull;
    }

    /**
     * Permite buscar cuando campo tipocodificacion es NULL
     * @param tipocodificacionIsNull Valor de seteo.
     */
    public void setTipocodificacionIsNull(boolean tipocodificacionIsNull) {
        this.tipocodificacionIsNull = tipocodificacionIsNull;
    }

    /**
     * Permite buscar cuando campo tipocodificacion es NOT NULL
     * @return boolean.
     */
    public boolean isTipocodificacionIsNotNull() {
        return tipocodificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo tipocodificacion es NOT NULL
     * @param tipocodificacionIsNotNull Valor de seteo.
     */
    public void setTipocodificacionIsNotNull(boolean tipocodificacionIsNotNull) {
        this.tipocodificacionIsNotNull = tipocodificacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo prioridad
     * @return Integer.
     */
    public Integer getPrioridad() {
        return prioridad;
    }

    /**
     * Valor de busqueda de campo prioridad
     * @param prioridad Valor de seteo.
     */
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getPrioridadIn() {
        return this.prioridadIn;
    }

    /**
     * @param prioridad Valor a agregar.
     */
    public void addPrioridadIn(Integer prioridad) {
        this.prioridadIn.add(prioridad);
    }

    /**
     * Permite buscar cuando campo prioridad es NULL
     * @return boolean.
     */
    public boolean isPrioridadIsNull() {
        return prioridadIsNull;
    }

    /**
     * Permite buscar cuando campo prioridad es NULL
     * @param prioridadIsNull Valor de seteo.
     */
    public void setPrioridadIsNull(boolean prioridadIsNull) {
        this.prioridadIsNull = prioridadIsNull;
    }

    /**
     * Permite buscar cuando campo prioridad es NOT NULL
     * @return boolean.
     */
    public boolean isPrioridadIsNotNull() {
        return prioridadIsNotNull;
    }

    /**
     * Permite buscar cuando campo prioridad es NOT NULL
     * @param prioridadIsNotNull Valor de seteo.
     */
    public void setPrioridadIsNotNull(boolean prioridadIsNotNull) {
        this.prioridadIsNotNull = prioridadIsNotNull;
    }

    /**
     * Valor de busqueda de campo tipomensaje
     * @return String.
     */
    public String getTipomensaje() {
        if (tipomensaje != null) {
            switch (tipomensajeComparator) {
	            case STARTS_WITH:
	                return tipomensaje + "%";
	            case CONTAINS:
	                return "%" + tipomensaje + "%";
	            case ENDS_WITH:
	                return "%" + tipomensaje;
	            case EQUALS:
                	return tipomensaje;
              	default:
	            	break;
            }
        }
        return tipomensaje;
    }

    /**
     * Valor de busqueda de campo tipomensaje
     * @param tipomensaje Valor de seteo.
     */
    public void setTipomensaje(String tipomensaje) {
        this.tipomensaje = tipomensaje;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipomensaje
     * @return tipomensajeComparator.
     */
    public TextComparator getTipomensajeComparator() {
        return tipomensajeComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipomensaje
     * @param tipomensajeComparator Valor de seteo.
     */
    public void setTipomensajeComparator(TextComparator tipomensajeComparator) {
        this.tipomensajeComparator = tipomensajeComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTipomensajeIn() {
        return this.tipomensajeIn;
    }

    /**
     * @param tipomensaje Valor a agregar.
     */
    public void addTipomensajeIn(String tipomensaje) {
        this.tipomensajeIn.add(tipomensaje);
    }

    /**
     * Permite buscar cuando campo tipomensaje es NULL
     * @return boolean.
     */
    public boolean isTipomensajeIsNull() {
        return tipomensajeIsNull;
    }

    /**
     * Permite buscar cuando campo tipomensaje es NULL
     * @param tipomensajeIsNull Valor de seteo.
     */
    public void setTipomensajeIsNull(boolean tipomensajeIsNull) {
        this.tipomensajeIsNull = tipomensajeIsNull;
    }

    /**
     * Permite buscar cuando campo tipomensaje es NOT NULL
     * @return boolean.
     */
    public boolean isTipomensajeIsNotNull() {
        return tipomensajeIsNotNull;
    }

    /**
     * Permite buscar cuando campo tipomensaje es NOT NULL
     * @param tipomensajeIsNotNull Valor de seteo.
     */
    public void setTipomensajeIsNotNull(boolean tipomensajeIsNotNull) {
        this.tipomensajeIsNotNull = tipomensajeIsNotNull;
    }

    /**
     * Valor de busqueda de campo telefono
     * @return String.
     */
    public String getTelefono() {
        if (telefono != null) {
            switch (telefonoComparator) {
	            case STARTS_WITH:
	                return telefono + "%";
	            case CONTAINS:
	                return "%" + telefono + "%";
	            case ENDS_WITH:
	                return "%" + telefono;
	            case EQUALS:
                	return telefono;
              	default:
	            	break;
            }
        }
        return telefono;
    }

    /**
     * Valor de busqueda de campo telefono
     * @param telefono Valor de seteo.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Tipo de comparador para la busqueda por campo telefono
     * @return telefonoComparator.
     */
    public TextComparator getTelefonoComparator() {
        return telefonoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo telefono
     * @param telefonoComparator Valor de seteo.
     */
    public void setTelefonoComparator(TextComparator telefonoComparator) {
        this.telefonoComparator = telefonoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTelefonoIn() {
        return this.telefonoIn;
    }

    /**
     * @param telefono Valor a agregar.
     */
    public void addTelefonoIn(String telefono) {
        this.telefonoIn.add(telefono);
    }

    /**
     * Permite buscar cuando campo telefono es NULL
     * @return boolean.
     */
    public boolean isTelefonoIsNull() {
        return telefonoIsNull;
    }

    /**
     * Permite buscar cuando campo telefono es NULL
     * @param telefonoIsNull Valor de seteo.
     */
    public void setTelefonoIsNull(boolean telefonoIsNull) {
        this.telefonoIsNull = telefonoIsNull;
    }

    /**
     * Permite buscar cuando campo telefono es NOT NULL
     * @return boolean.
     */
    public boolean isTelefonoIsNotNull() {
        return telefonoIsNotNull;
    }

    /**
     * Permite buscar cuando campo telefono es NOT NULL
     * @param telefonoIsNotNull Valor de seteo.
     */
    public void setTelefonoIsNotNull(boolean telefonoIsNotNull) {
        this.telefonoIsNotNull = telefonoIsNotNull;
    }

    /**
     * Valor de busqueda de campo canalid
     * @return Long.
     */
    public Long getCanalid() {
        return canalid;
    }

    /**
     * Valor de busqueda de campo canalid
     * @param canalid Valor de seteo.
     */
    public void setCanalid(Long canalid) {
        this.canalid = canalid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getCanalidIn() {
        return this.canalidIn;
    }

    /**
     * @param canalid Valor a agregar.
     */
    public void addCanalidIn(Long canalid) {
        this.canalidIn.add(canalid);
    }

    /**
     * Permite buscar cuando campo canalid es NULL
     * @return boolean.
     */
    public boolean isCanalidIsNull() {
        return canalidIsNull;
    }

    /**
     * Permite buscar cuando campo canalid es NULL
     * @param canalidIsNull Valor de seteo.
     */
    public void setCanalidIsNull(boolean canalidIsNull) {
        this.canalidIsNull = canalidIsNull;
    }

    /**
     * Permite buscar cuando campo canalid es NOT NULL
     * @return boolean.
     */
    public boolean isCanalidIsNotNull() {
        return canalidIsNotNull;
    }

    /**
     * Permite buscar cuando campo canalid es NOT NULL
     * @param canalidIsNotNull Valor de seteo.
     */
    public void setCanalidIsNotNull(boolean canalidIsNotNull) {
        this.canalidIsNotNull = canalidIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

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

        if (getLoteenvioid() != null) {
            criteria.add(Restrictions.eq(LOTEENVIOID, getLoteenvioid()));
        }

        if (getLoteenvioidIn().size() > 0) {
            criteria.add(Restrictions.in(LOTEENVIOID, getLoteenvioidIn()));
        }

        if (isLoteenvioidIsNull()) {
            criteria.add(Restrictions.isNull(LOTEENVIOID));
        }

        if (isLoteenvioidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(LOTEENVIOID));
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

        if (getCuerpo() != null) {
            if (getCuerpoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CUERPO, getCuerpo()));
            } 
            else if (getCuerpoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CUERPO, getCuerpo()));
            }
            else if (getCuerpoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CUERPO, getCuerpo())));
            }
            else {
                criteria.add(Restrictions.like(CUERPO, getCuerpo()));
            }
        }

        if (getCuerpoIn().size() > 0) {
            criteria.add(Restrictions.in(CUERPO, getCuerpoIn()));
        }

        if (isCuerpoIsNull()) {
            criteria.add(Restrictions.isNull(CUERPO));
        }

        if (isCuerpoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CUERPO));
        }

        if (getCabecera() != null) {
            if (getCabeceraComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CABECERA, getCabecera()));
            } 
            else if (getCabeceraComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CABECERA, getCabecera()));
            }
            else if (getCabeceraComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CABECERA, getCabecera())));
            }
            else {
                criteria.add(Restrictions.like(CABECERA, getCabecera()));
            }
        }

        if (getCabeceraIn().size() > 0) {
            criteria.add(Restrictions.in(CABECERA, getCabeceraIn()));
        }

        if (isCabeceraIsNull()) {
            criteria.add(Restrictions.isNull(CABECERA));
        }

        if (isCabeceraIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CABECERA));
        }

        if (getEstadoactual() != null) {
            if (getEstadoactualComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ESTADOACTUAL, getEstadoactual()));
            } 
            else if (getEstadoactualComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ESTADOACTUAL, getEstadoactual()));
            }
            else if (getEstadoactualComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ESTADOACTUAL, getEstadoactual())));
            }
            else {
                criteria.add(Restrictions.like(ESTADOACTUAL, getEstadoactual()));
            }
        }

        if (getEstadoactualIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADOACTUAL, getEstadoactualIn()));
        }

        if (isEstadoactualIsNull()) {
            criteria.add(Restrictions.isNull(ESTADOACTUAL));
        }

        if (isEstadoactualIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADOACTUAL));
        }

        if (getNumeroenvios() != null) {
            criteria.add(Restrictions.eq(NUMEROENVIOS, getNumeroenvios()));
        }

        if (getNumeroenviosIn().size() > 0) {
            criteria.add(Restrictions.in(NUMEROENVIOS, getNumeroenviosIn()));
        }

        if (isNumeroenviosIsNull()) {
            criteria.add(Restrictions.isNull(NUMEROENVIOS));
        }

        if (isNumeroenviosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NUMEROENVIOS));
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

        if (getUltimoidhistorico() != null) {
            criteria.add(Restrictions.eq(ULTIMOIDHISTORICO, getUltimoidhistorico()));
        }

        if (getUltimoidhistoricoIn().size() > 0) {
            criteria.add(Restrictions.in(ULTIMOIDHISTORICO, getUltimoidhistoricoIn()));
        }

        if (isUltimoidhistoricoIsNull()) {
            criteria.add(Restrictions.isNull(ULTIMOIDHISTORICO));
        }

        if (isUltimoidhistoricoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ULTIMOIDHISTORICO));
        }

        if (getTipocuerpo() != null) {
            if (getTipocuerpoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TIPOCUERPO, getTipocuerpo()));
            } 
            else if (getTipocuerpoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TIPOCUERPO, getTipocuerpo()));
            }
            else if (getTipocuerpoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TIPOCUERPO, getTipocuerpo())));
            }
            else {
                criteria.add(Restrictions.like(TIPOCUERPO, getTipocuerpo()));
            }
        }

        if (getTipocuerpoIn().size() > 0) {
            criteria.add(Restrictions.in(TIPOCUERPO, getTipocuerpoIn()));
        }

        if (isTipocuerpoIsNull()) {
            criteria.add(Restrictions.isNull(TIPOCUERPO));
        }

        if (isTipocuerpoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TIPOCUERPO));
        }

        if (getTipocodificacion() != null) {
            if (getTipocodificacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TIPOCODIFICACION, getTipocodificacion()));
            } 
            else if (getTipocodificacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TIPOCODIFICACION, getTipocodificacion()));
            }
            else if (getTipocodificacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TIPOCODIFICACION, getTipocodificacion())));
            }
            else {
                criteria.add(Restrictions.like(TIPOCODIFICACION, getTipocodificacion()));
            }
        }

        if (getTipocodificacionIn().size() > 0) {
            criteria.add(Restrictions.in(TIPOCODIFICACION, getTipocodificacionIn()));
        }

        if (isTipocodificacionIsNull()) {
            criteria.add(Restrictions.isNull(TIPOCODIFICACION));
        }

        if (isTipocodificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TIPOCODIFICACION));
        }

        if (getPrioridad() != null) {
            criteria.add(Restrictions.eq(PRIORIDAD, getPrioridad()));
        }

        if (getPrioridadIn().size() > 0) {
            criteria.add(Restrictions.in(PRIORIDAD, getPrioridadIn()));
        }

        if (isPrioridadIsNull()) {
            criteria.add(Restrictions.isNull(PRIORIDAD));
        }

        if (isPrioridadIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PRIORIDAD));
        }

        if (getTipomensaje() != null) {
            if (getTipomensajeComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TIPOMENSAJE, getTipomensaje()));
            } 
            else if (getTipomensajeComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TIPOMENSAJE, getTipomensaje()));
            }
            else if (getTipomensajeComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TIPOMENSAJE, getTipomensaje())));
            }
            else {
                criteria.add(Restrictions.like(TIPOMENSAJE, getTipomensaje()));
            }
        }

        if (getTipomensajeIn().size() > 0) {
            criteria.add(Restrictions.in(TIPOMENSAJE, getTipomensajeIn()));
        }

        if (isTipomensajeIsNull()) {
            criteria.add(Restrictions.isNull(TIPOMENSAJE));
        }

        if (isTipomensajeIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TIPOMENSAJE));
        }

        if (getTelefono() != null) {
            if (getTelefonoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TELEFONO, getTelefono()));
            } 
            else if (getTelefonoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TELEFONO, getTelefono()));
            }
            else if (getTelefonoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TELEFONO, getTelefono())));
            }
            else {
                criteria.add(Restrictions.like(TELEFONO, getTelefono()));
            }
        }

        if (getTelefonoIn().size() > 0) {
            criteria.add(Restrictions.in(TELEFONO, getTelefonoIn()));
        }

        if (isTelefonoIsNull()) {
            criteria.add(Restrictions.isNull(TELEFONO));
        }

        if (isTelefonoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TELEFONO));
        }

        if (getCanalid() != null) {
            criteria.add(Restrictions.eq(CANALID, getCanalid()));
        }

        if (getCanalidIn().size() > 0) {
            criteria.add(Restrictions.in(CANALID, getCanalidIn()));
        }

        if (isCanalidIsNull()) {
            criteria.add(Restrictions.isNull(CANALID));
        }

        if (isCanalidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CANALID));
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
 