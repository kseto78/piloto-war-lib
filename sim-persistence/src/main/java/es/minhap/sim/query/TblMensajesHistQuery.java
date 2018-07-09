/*
 *
 * archivo: TblMensajesHistQuery.java
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
import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesHist;

/**
 * Clase con criterios de busqueda para la entidad TblMensajesHist
 */
public class TblMensajesHistQuery extends AbstractHibernateQueryEntity<TblMensajesHist> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String MENSAJEID = "mensajeid";
    public static final String TBLESTADOS = "tblEstados";
    public static final String TBLLOTESENVIOSHIST = "tblLotesEnviosHist";
    public static final String CODIGOEXTERNO = "codigoexterno";
    public static final String CABECERA = "cabecera";
    public static final String ESTADOACTUAL = "estadoactual";
    public static final String NUMEROENVIOS = "numeroenvios";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String ULTIMOENVIO = "ultimoenvio";
    public static final String ULTIMOIDHISTORICO = "ultimoidhistorico";
    public static final String CUERPO = "cuerpo";
    public static final String CUERPOFILE = "cuerpofile";
    public static final String TIPOCUERPO = "tipocuerpo";
    public static final String TIPOCODIFICACION = "tipocodificacion";
    public static final String PRIORIDAD = "prioridad";
    public static final String TIPOMENSAJE = "tipomensaje";
    public static final String TELEFONO = "telefono";
    public static final String UIM = "uim";
    public static final String IDENVIOSSMS = "idenviossms";
    public static final String FECHAHISTORIFICACION = "fechahistorificacion";
    public static final String DOCUSUARIO = "docusuario";
    public static final String CODSIA = "codsia";
    public static final String CODORGANISMO = "codorganismo";
    public static final String CODORGANISMOPAGADOR = "codorganismopagador";
    public static final String NOMBREUSUARIO = "nombreusuario";
    public static final String ICONO = "icono";
    public static final String SONIDO = "sonido";


    /**
     * Valor de busqueda de campo mensajeid
     */
    private Long mensajeid;

    /**
     * Lista de valores del campo mensajeid para busquedas tipo IN
     */
    private List<Long> mensajeidIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo tblLotesEnviosHist
     */
    private TblLotesEnviosHistQuery tblLotesEnviosHist;

    /**
     * Lista de valores del ID del campo tblLotesEnviosHist para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblLotesEnviosHist
     */
    private List<TblLotesEnviosHist> tblLotesEnviosHistIdIn = new ArrayList<TblLotesEnviosHist>(0);

    /**
     * Permite buscar cuando campo tblLotesEnviosHist es NULL
     */
    private boolean tblLotesEnviosHistIsNull = false;

    /**
     * Permite buscar cuando campo tblLotesEnviosHist es NOT NULL
     */
    private boolean tblLotesEnviosHistIsNotNull = false;

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
     * Valor de busqueda de campo cuerpofile
     */
    private String cuerpofile;

    /**
     * Tipo de comparador para la busqueda por campo cuerpofile
     */
    private TextComparator cuerpofileComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo cuerpofile para busquedas tipo IN
     */
    private List<String> cuerpofileIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo cuerpofile es NULL
     */
    private boolean cuerpofileIsNull = false;

    /**
     * Permite buscar cuando campo cuerpofile es NOT NULL
     */
    private boolean cuerpofileIsNotNull = false;
    
    
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
     * Valor de busqueda de campo idenviossms
     */
    private Long idenviossms;

    /**
     * Lista de valores del campo idenviossms para busquedas tipo IN
     */
    private List<Long> idenviossmsIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo idenviossms es NULL
     */
    private boolean idenviossmsIsNull = false;

    /**
     * Permite buscar cuando campo idenviossms es NOT NULL
     */
    private boolean idenviossmsIsNotNull = false;

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
     * Valor de busqueda de campo docusuario
     */
    private String docusuario;

    /**
     * Tipo de comparador para la busqueda por campo docusuario
     */
    private TextComparator docusuarioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo docusuario para busquedas tipo IN
     */
    private List<String> docusuarioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo docusuario es NULL
     */
    private boolean docusuarioIsNull = false;

    /**
     * Permite buscar cuando campo docusuario es NOT NULL
     */
    private boolean docusuarioIsNotNull = false;

    /**
     * Valor de busqueda de campo codsia
     */
    private String codsia;

    /**
     * Tipo de comparador para la busqueda por campo codsia
     */
    private TextComparator codsiaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codsia para busquedas tipo IN
     */
    private List<String> codsiaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codsia es NULL
     */
    private boolean codsiaIsNull = false;

    /**
     * Permite buscar cuando campo codsia es NOT NULL
     */
    private boolean codsiaIsNotNull = false;

    /**
     * Valor de busqueda de campo codorganismo
     */
    private String codorganismo;

    /**
     * Tipo de comparador para la busqueda por campo codorganismo
     */
    private TextComparator codorganismoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codorganismo para busquedas tipo IN
     */
    private List<String> codorganismoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codorganismo es NULL
     */
    private boolean codorganismoIsNull = false;

    /**
     * Permite buscar cuando campo codorganismo es NOT NULL
     */
    private boolean codorganismoIsNotNull = false;

    /**
     * Valor de busqueda de campo codorganismopagador
     */
    private String codorganismopagador;

    /**
     * Tipo de comparador para la busqueda por campo codorganismopagador
     */
    private TextComparator codorganismopagadorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codorganismopagador para busquedas tipo IN
     */
    private List<String> codorganismopagadorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codorganismopagador es NULL
     */
    private boolean codorganismopagadorIsNull = false;

    /**
     * Permite buscar cuando campo codorganismopagador es NOT NULL
     */
    private boolean codorganismopagadorIsNotNull = false;

    /**
     * Valor de busqueda de campo nombreusuario
     */
    private String nombreusuario;

    /**
     * Tipo de comparador para la busqueda por campo nombreusuario
     */
    private TextComparator nombreusuarioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombreusuario para busquedas tipo IN
     */
    private List<String> nombreusuarioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombreusuario es NULL
     */
    private boolean nombreusuarioIsNull = false;

    /**
     * Permite buscar cuando campo nombreusuario es NOT NULL
     */
    private boolean nombreusuarioIsNotNull = false;

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
     * Valor de busqueda de campo sonido
     */
    private String sonido;

    /**
     * Tipo de comparador para la busqueda por campo sonido
     */
    private TextComparator sonidoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo sonido para busquedas tipo IN
     */
    private List<String> sonidoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo sonido es NULL
     */
    private boolean sonidoIsNull = false;

    /**
     * Permite buscar cuando campo sonido es NOT NULL
     */
    private boolean sonidoIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblEstados
     */
    private boolean innerJoinTblEstados = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblEstados
     */
    private boolean leftJoinTblEstados = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblLotesEnviosHist
     */
    private boolean innerJoinTblLotesEnviosHist = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblLotesEnviosHist
     */
    private boolean leftJoinTblLotesEnviosHist = false;

    /**
     * Valor primer resultado
     */
    private Integer firstResult;
    
    
    /**
     * Valor max resultados
     */
    private Integer maxResult;
    
    
    /**
     * Constructor default
     */
    public TblMensajesHistQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblMensajesHistQuery(Long mensajeid) {
        setMensajeid(mensajeid);
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
     * Valor de busqueda de campo tblLotesEnviosHist
     * @return TblLotesEnviosHist.
     */
    public TblLotesEnviosHistQuery getTblLotesEnviosHist() {
        return tblLotesEnviosHist;
    }

    /**
     * Valor de busqueda de campo tblLotesEnviosHist
     * @param tblLotesEnviosHist Valor de seteo.
     */
    public void setTblLotesEnviosHist(TblLotesEnviosHistQuery tblLotesEnviosHist) {
        this.tblLotesEnviosHist = tblLotesEnviosHist;
    }

    /**
     * @return List<TblLotesEnviosHist>.
     */
    public List<TblLotesEnviosHist> getTblLotesEnviosHistIdIn() {
        return this.tblLotesEnviosHistIdIn;
    }

    /**
     * @param tblLotesEnviosHist Valor a agregar.
     */
    public void addTblLotesEnviosHistIdIn(TblLotesEnviosHist tblLotesEnviosHist) {
        this.tblLotesEnviosHistIdIn.add(tblLotesEnviosHist);
    }

    /**
     * Permite buscar cuando campo tblLotesEnviosHist es NULL
     * @return boolean.
     */
    public boolean isTblLotesEnviosHistIsNull() {
        return tblLotesEnviosHistIsNull;
    }

    /**
     * Permite buscar cuando campo tblLotesEnviosHist es NULL
     * @param tblLotesEnviosHistIsNull Valor de seteo.
     */
    public void setTblLotesEnviosHistIsNull(boolean tblLotesEnviosHistIsNull) {
        this.tblLotesEnviosHistIsNull = tblLotesEnviosHistIsNull;
    }

    /**
     * Permite buscar cuando campo tblLotesEnviosHist es NOT NULL
     * @return boolean.
     */
    public boolean isTblLotesEnviosHistIsNotNull() {
        return tblLotesEnviosHistIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblLotesEnviosHist es NOT NULL
     * @param tblLotesEnviosHistIsNotNull Valor de seteo.
     */
    public void setTblLotesEnviosHistIsNotNull(boolean tblLotesEnviosHistIsNotNull) {
        this.tblLotesEnviosHistIsNotNull = tblLotesEnviosHistIsNotNull;
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
     * Valor de busqueda de campo cuerpofile
     * @return String.
     */
    public String getCuerpofile() {
        if (cuerpofile != null) {
            switch (cuerpofileComparator) {
	            case STARTS_WITH:
	                return cuerpofile + "%";
	            case CONTAINS:
	                return "%" + cuerpofile + "%";
	            case ENDS_WITH:
	                return "%" + cuerpofile;
	            case EQUALS:
                	return cuerpofile;
              	default:
	            	break;
            }
        }
        return cuerpofile;
    }

    /**
     * Valor de busqueda de campo cuerpofile
     * @param cuerpofile Valor de seteo.
     */
    public void setCuerpofile(String cuerpofile) {
        this.cuerpofile = cuerpofile;
    }

    /**
     * Tipo de comparador para la busqueda por campo cuerpofile
     * @return cuerpofileComparator.
     */
    public TextComparator getCuerpofileComparator() {
        return cuerpofileComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo cuerpo
     * @param cuerpoComparator Valor de seteo.
     */
    public void setCuerpofileComparator(TextComparator cuerpofileComparator) {
        this.cuerpofileComparator = cuerpofileComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCuerpofileIn() {
        return this.cuerpofileIn;
    }

    /**
     * @param cuerpofile Valor a agregar.
     */
    public void addCuerpofileIn(String cuerpofile) {
        this.cuerpofileIn.add(cuerpofile);
    }

    /**
     * Permite buscar cuando campo cuerpofile es NULL
     * @return boolean.
     */
    public boolean isCuerpofileIsNull() {
        return cuerpofileIsNull;
    }

    /**
     * Permite buscar cuando campo cuerpofile es NULL
     * @param cuerpofileIsNull Valor de seteo.
     */
    public void setCuerpofileIsNull(boolean cuerpofileIsNull) {
        this.cuerpofileIsNull = cuerpofileIsNull;
    }

    /**
     * Permite buscar cuando campo cuerpofile es NOT NULL
     * @return boolean.
     */
    public boolean isCuerpofileIsNotNull() {
        return cuerpofileIsNotNull;
    }

    /**
     * Permite buscar cuando campo cuerpofile es NOT NULL
     * @param cuerpofileIsNotNull Valor de seteo.
     */
    public void setCuerpofileIsNotNull(boolean cuerpofileIsNotNull) {
        this.cuerpofileIsNotNull = cuerpofileIsNotNull;
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
     * Valor de busqueda de campo idenviossms
     * @return Long.
     */
    public Long getIdenviossms() {
        return idenviossms;
    }

    /**
     * Valor de busqueda de campo idenviossms
     * @param idenviossms Valor de seteo.
     */
    public void setIdenviossms(Long idenviossms) {
        this.idenviossms = idenviossms;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdenviossmsIn() {
        return this.idenviossmsIn;
    }

    /**
     * @param idenviossms Valor a agregar.
     */
    public void addIdenviossmsIn(Long idenviossms) {
        this.idenviossmsIn.add(idenviossms);
    }

    /**
     * Permite buscar cuando campo idenviossms es NULL
     * @return boolean.
     */
    public boolean isIdenviossmsIsNull() {
        return idenviossmsIsNull;
    }

    /**
     * Permite buscar cuando campo idenviossms es NULL
     * @param idenviossmsIsNull Valor de seteo.
     */
    public void setIdenviossmsIsNull(boolean idenviossmsIsNull) {
        this.idenviossmsIsNull = idenviossmsIsNull;
    }

    /**
     * Permite buscar cuando campo idenviossms es NOT NULL
     * @return boolean.
     */
    public boolean isIdenviossmsIsNotNull() {
        return idenviossmsIsNotNull;
    }

    /**
     * Permite buscar cuando campo idenviossms es NOT NULL
     * @param idenviossmsIsNotNull Valor de seteo.
     */
    public void setIdenviossmsIsNotNull(boolean idenviossmsIsNotNull) {
        this.idenviossmsIsNotNull = idenviossmsIsNotNull;
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
     * Valor de busqueda de campo docusuario
     * @return String.
     */
    public String getDocusuario() {
        if (docusuario != null) {
            switch (docusuarioComparator) {
	            case STARTS_WITH:
	                return docusuario + "%";
	            case CONTAINS:
	                return "%" + docusuario + "%";
	            case ENDS_WITH:
	                return "%" + docusuario;
	            case EQUALS:
                	return docusuario;
              	default:
	            	break;
            }
        }
        return docusuario;
    }

    /**
     * Valor de busqueda de campo docusuario
     * @param docusuario Valor de seteo.
     */
    public void setDocusuario(String docusuario) {
        this.docusuario = docusuario;
    }

    /**
     * Tipo de comparador para la busqueda por campo docusuario
     * @return docusuarioComparator.
     */
    public TextComparator getDocusuarioComparator() {
        return docusuarioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo docusuario
     * @param docusuarioComparator Valor de seteo.
     */
    public void setDocusuarioComparator(TextComparator docusuarioComparator) {
        this.docusuarioComparator = docusuarioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDocusuarioIn() {
        return this.docusuarioIn;
    }

    /**
     * @param docusuario Valor a agregar.
     */
    public void addDocusuarioIn(String docusuario) {
        this.docusuarioIn.add(docusuario);
    }

    /**
     * Permite buscar cuando campo docusuario es NULL
     * @return boolean.
     */
    public boolean isDocusuarioIsNull() {
        return docusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo docusuario es NULL
     * @param docusuarioIsNull Valor de seteo.
     */
    public void setDocusuarioIsNull(boolean docusuarioIsNull) {
        this.docusuarioIsNull = docusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo docusuario es NOT NULL
     * @return boolean.
     */
    public boolean isDocusuarioIsNotNull() {
        return docusuarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo docusuario es NOT NULL
     * @param docusuarioIsNotNull Valor de seteo.
     */
    public void setDocusuarioIsNotNull(boolean docusuarioIsNotNull) {
        this.docusuarioIsNotNull = docusuarioIsNotNull;
    }

    /**
     * Valor de busqueda de campo codsia
     * @return String.
     */
    public String getCodsia() {
        if (codsia != null) {
            switch (codsiaComparator) {
	            case STARTS_WITH:
	                return codsia + "%";
	            case CONTAINS:
	                return "%" + codsia + "%";
	            case ENDS_WITH:
	                return "%" + codsia;
	            case EQUALS:
                	return codsia;
              	default:
	            	break;
            }
        }
        return codsia;
    }

    /**
     * Valor de busqueda de campo codsia
     * @param codsia Valor de seteo.
     */
    public void setCodsia(String codsia) {
        this.codsia = codsia;
    }

    /**
     * Tipo de comparador para la busqueda por campo codsia
     * @return codsiaComparator.
     */
    public TextComparator getCodsiaComparator() {
        return codsiaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codsia
     * @param codsiaComparator Valor de seteo.
     */
    public void setCodsiaComparator(TextComparator codsiaComparator) {
        this.codsiaComparator = codsiaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodsiaIn() {
        return this.codsiaIn;
    }

    /**
     * @param codsia Valor a agregar.
     */
    public void addCodsiaIn(String codsia) {
        this.codsiaIn.add(codsia);
    }

    /**
     * Permite buscar cuando campo codsia es NULL
     * @return boolean.
     */
    public boolean isCodsiaIsNull() {
        return codsiaIsNull;
    }

    /**
     * Permite buscar cuando campo codsia es NULL
     * @param codsiaIsNull Valor de seteo.
     */
    public void setCodsiaIsNull(boolean codsiaIsNull) {
        this.codsiaIsNull = codsiaIsNull;
    }

    /**
     * Permite buscar cuando campo codsia es NOT NULL
     * @return boolean.
     */
    public boolean isCodsiaIsNotNull() {
        return codsiaIsNotNull;
    }

    /**
     * Permite buscar cuando campo codsia es NOT NULL
     * @param codsiaIsNotNull Valor de seteo.
     */
    public void setCodsiaIsNotNull(boolean codsiaIsNotNull) {
        this.codsiaIsNotNull = codsiaIsNotNull;
    }

    /**
     * Valor de busqueda de campo codorganismo
     * @return String.
     */
    public String getCodorganismo() {
        if (codorganismo != null) {
            switch (codorganismoComparator) {
	            case STARTS_WITH:
	                return codorganismo + "%";
	            case CONTAINS:
	                return "%" + codorganismo + "%";
	            case ENDS_WITH:
	                return "%" + codorganismo;
	            case EQUALS:
                	return codorganismo;
              	default:
	            	break;
            }
        }
        return codorganismo;
    }

    /**
     * Valor de busqueda de campo codorganismo
     * @param codorganismo Valor de seteo.
     */
    public void setCodorganismo(String codorganismo) {
        this.codorganismo = codorganismo;
    }

    /**
     * Tipo de comparador para la busqueda por campo codorganismo
     * @return codorganismoComparator.
     */
    public TextComparator getCodorganismoComparator() {
        return codorganismoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codorganismo
     * @param codorganismoComparator Valor de seteo.
     */
    public void setCodorganismoComparator(TextComparator codorganismoComparator) {
        this.codorganismoComparator = codorganismoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodorganismoIn() {
        return this.codorganismoIn;
    }

    /**
     * @param codorganismo Valor a agregar.
     */
    public void addCodorganismoIn(String codorganismo) {
        this.codorganismoIn.add(codorganismo);
    }

    /**
     * Permite buscar cuando campo codorganismo es NULL
     * @return boolean.
     */
    public boolean isCodorganismoIsNull() {
        return codorganismoIsNull;
    }

    /**
     * Permite buscar cuando campo codorganismo es NULL
     * @param codorganismoIsNull Valor de seteo.
     */
    public void setCodorganismoIsNull(boolean codorganismoIsNull) {
        this.codorganismoIsNull = codorganismoIsNull;
    }

    /**
     * Permite buscar cuando campo codorganismo es NOT NULL
     * @return boolean.
     */
    public boolean isCodorganismoIsNotNull() {
        return codorganismoIsNotNull;
    }

    /**
     * Permite buscar cuando campo codorganismo es NOT NULL
     * @param codorganismoIsNotNull Valor de seteo.
     */
    public void setCodorganismoIsNotNull(boolean codorganismoIsNotNull) {
        this.codorganismoIsNotNull = codorganismoIsNotNull;
    }

    /**
     * Valor de busqueda de campo codorganismopagador
     * @return String.
     */
    public String getCodorganismopagador() {
        if (codorganismopagador != null) {
            switch (codorganismopagadorComparator) {
	            case STARTS_WITH:
	                return codorganismopagador + "%";
	            case CONTAINS:
	                return "%" + codorganismopagador + "%";
	            case ENDS_WITH:
	                return "%" + codorganismopagador;
	            case EQUALS:
                	return codorganismopagador;
              	default:
	            	break;
            }
        }
        return codorganismopagador;
    }

    /**
     * Valor de busqueda de campo codorganismopagador
     * @param codorganismopagador Valor de seteo.
     */
    public void setCodorganismopagador(String codorganismopagador) {
        this.codorganismopagador = codorganismopagador;
    }

    /**
     * Tipo de comparador para la busqueda por campo codorganismopagador
     * @return codorganismopagadorComparator.
     */
    public TextComparator getCodorganismopagadorComparator() {
        return codorganismopagadorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codorganismopagador
     * @param codorganismopagadorComparator Valor de seteo.
     */
    public void setCodorganismopagadorComparator(TextComparator codorganismopagadorComparator) {
        this.codorganismopagadorComparator = codorganismopagadorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodorganismopagadorIn() {
        return this.codorganismopagadorIn;
    }

    /**
     * @param codorganismopagador Valor a agregar.
     */
    public void addCodorganismopagadorIn(String codorganismopagador) {
        this.codorganismopagadorIn.add(codorganismopagador);
    }

    /**
     * Permite buscar cuando campo codorganismopagador es NULL
     * @return boolean.
     */
    public boolean isCodorganismopagadorIsNull() {
        return codorganismopagadorIsNull;
    }

    /**
     * Permite buscar cuando campo codorganismopagador es NULL
     * @param codorganismopagadorIsNull Valor de seteo.
     */
    public void setCodorganismopagadorIsNull(boolean codorganismopagadorIsNull) {
        this.codorganismopagadorIsNull = codorganismopagadorIsNull;
    }

    /**
     * Permite buscar cuando campo codorganismopagador es NOT NULL
     * @return boolean.
     */
    public boolean isCodorganismopagadorIsNotNull() {
        return codorganismopagadorIsNotNull;
    }

    /**
     * Permite buscar cuando campo codorganismopagador es NOT NULL
     * @param codorganismopagadorIsNotNull Valor de seteo.
     */
    public void setCodorganismopagadorIsNotNull(boolean codorganismopagadorIsNotNull) {
        this.codorganismopagadorIsNotNull = codorganismopagadorIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombreusuario
     * @return String.
     */
    public String getNombreusuario() {
        if (nombreusuario != null) {
            switch (nombreusuarioComparator) {
	            case STARTS_WITH:
	                return nombreusuario + "%";
	            case CONTAINS:
	                return "%" + nombreusuario + "%";
	            case ENDS_WITH:
	                return "%" + nombreusuario;
	            case EQUALS:
                	return nombreusuario;
              	default:
	            	break;
            }
        }
        return nombreusuario;
    }

    /**
     * Valor de busqueda de campo nombreusuario
     * @param nombreusuario Valor de seteo.
     */
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreusuario
     * @return nombreusuarioComparator.
     */
    public TextComparator getNombreusuarioComparator() {
        return nombreusuarioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreusuario
     * @param nombreusuarioComparator Valor de seteo.
     */
    public void setNombreusuarioComparator(TextComparator nombreusuarioComparator) {
        this.nombreusuarioComparator = nombreusuarioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreusuarioIn() {
        return this.nombreusuarioIn;
    }

    /**
     * @param nombreusuario Valor a agregar.
     */
    public void addNombreusuarioIn(String nombreusuario) {
        this.nombreusuarioIn.add(nombreusuario);
    }

    /**
     * Permite buscar cuando campo nombreusuario es NULL
     * @return boolean.
     */
    public boolean isNombreusuarioIsNull() {
        return nombreusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo nombreusuario es NULL
     * @param nombreusuarioIsNull Valor de seteo.
     */
    public void setNombreusuarioIsNull(boolean nombreusuarioIsNull) {
        this.nombreusuarioIsNull = nombreusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo nombreusuario es NOT NULL
     * @return boolean.
     */
    public boolean isNombreusuarioIsNotNull() {
        return nombreusuarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombreusuario es NOT NULL
     * @param nombreusuarioIsNotNull Valor de seteo.
     */
    public void setNombreusuarioIsNotNull(boolean nombreusuarioIsNotNull) {
        this.nombreusuarioIsNotNull = nombreusuarioIsNotNull;
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
     * Valor de busqueda de campo sonido
     * @return String.
     */
    public String getSonido() {
        if (sonido != null) {
            switch (sonidoComparator) {
	            case STARTS_WITH:
	                return sonido + "%";
	            case CONTAINS:
	                return "%" + sonido + "%";
	            case ENDS_WITH:
	                return "%" + sonido;
	            case EQUALS:
                	return sonido;
              	default:
	            	break;
            }
        }
        return sonido;
    }

    /**
     * Valor de busqueda de campo sonido
     * @param sonido Valor de seteo.
     */
    public void setSonido(String sonido) {
        this.sonido = sonido;
    }

    /**
     * Tipo de comparador para la busqueda por campo sonido
     * @return sonidoComparator.
     */
    public TextComparator getSonidoComparator() {
        return sonidoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo sonido
     * @param sonidoComparator Valor de seteo.
     */
    public void setSonidoComparator(TextComparator sonidoComparator) {
        this.sonidoComparator = sonidoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getSonidoIn() {
        return this.sonidoIn;
    }

    /**
     * @param sonido Valor a agregar.
     */
    public void addSonidoIn(String sonido) {
        this.sonidoIn.add(sonido);
    }

    /**
     * Permite buscar cuando campo sonido es NULL
     * @return boolean.
     */
    public boolean isSonidoIsNull() {
        return sonidoIsNull;
    }

    /**
     * Permite buscar cuando campo sonido es NULL
     * @param sonidoIsNull Valor de seteo.
     */
    public void setSonidoIsNull(boolean sonidoIsNull) {
        this.sonidoIsNull = sonidoIsNull;
    }

    /**
     * Permite buscar cuando campo sonido es NOT NULL
     * @return boolean.
     */
    public boolean isSonidoIsNotNull() {
        return sonidoIsNotNull;
    }

    /**
     * Permite buscar cuando campo sonido es NOT NULL
     * @param sonidoIsNotNull Valor de seteo.
     */
    public void setSonidoIsNotNull(boolean sonidoIsNotNull) {
        this.sonidoIsNotNull = sonidoIsNotNull;
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
     * @return boolean.
     */
    public boolean isInnerJoinTblLotesEnviosHist() {
        return innerJoinTblLotesEnviosHist;
    }

    /**
     * @param innerJoinTblLotesEnviosHist Valor de seteo.
     */
    public void setInnerJoinTblLotesEnviosHist(boolean innerJoinTblLotesEnviosHist) {
        this.innerJoinTblLotesEnviosHist = innerJoinTblLotesEnviosHist;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblLotesEnviosHist() {
        return leftJoinTblLotesEnviosHist;
    }

    /**
     * @param leftJoinTblLotesEnviosHist Valor de seteo.
     */
    public void setLeftJoinTblLotesEnviosHist(boolean leftJoinTblLotesEnviosHist) {
        this.leftJoinTblLotesEnviosHist = leftJoinTblLotesEnviosHist;
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

                // Campo entidad padre tblLotesEnviosHist
        
        // Si se hace join fetch con el padre
        Criteria tblLotesEnviosHistCriteria = null;
        if (isInnerJoinTblLotesEnviosHist()) {
            tblLotesEnviosHistCriteria = criteria.createCriteria(TBLLOTESENVIOSHIST, "a_" + TBLLOTESENVIOSHIST, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblLotesEnviosHist()) {
            tblLotesEnviosHistCriteria = criteria.createCriteria(TBLLOTESENVIOSHIST, "a_" + TBLLOTESENVIOSHIST, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblLotesEnviosHist() != null) {
            if (getTblLotesEnviosHist().getLoteenvioid() == null) {
                if (tblLotesEnviosHistCriteria == null) {
                    tblLotesEnviosHistCriteria = criteria.createCriteria(TBLLOTESENVIOSHIST, "a_" + TBLLOTESENVIOSHIST);
                }
                getTblLotesEnviosHist().addCriteria(tblLotesEnviosHistCriteria, useOrder);
            } else {
                TblLotesEnviosHist parent = new TblLotesEnviosHist();
                parent.setLoteenvioid(getTblLotesEnviosHist().getLoteenvioid());
                criteria.add(Restrictions.eq(TBLLOTESENVIOSHIST, parent));
            }
        }

        if (getTblLotesEnviosHistIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLLOTESENVIOSHIST, getTblLotesEnviosHistIdIn()));
        }

        if (isTblLotesEnviosHistIsNull()) {
            criteria.add(Restrictions.isNull(TBLLOTESENVIOSHIST));
        }

        if (isTblLotesEnviosHistIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLLOTESENVIOSHIST));
        }

        if (isTblEstadosIsNull()) {
            criteria.add(Restrictions.isNull(TBLESTADOS));
        }

        if (isTblEstadosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLESTADOS));
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
        
        
        if (getCuerpofile() != null) {
            if (getCuerpofileComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CUERPOFILE, getCuerpofile()));
            } 
            else if (getCuerpofileComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CUERPOFILE, getCuerpofile()));
            }
            else if (getCuerpofileComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CUERPOFILE, getCuerpofile())));
            }
            else {
                criteria.add(Restrictions.like(CUERPOFILE, getCuerpofile()));
            }
        }

        if (getCuerpofileIn().size() > 0) {
            criteria.add(Restrictions.in(CUERPOFILE, getCuerpofileIn()));
        }

        if (isCuerpofileIsNull()) {
            criteria.add(Restrictions.isNull(CUERPOFILE));
        }

        if (isCuerpofileIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CUERPOFILE));
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

        if (getIdenviossms() != null) {
            criteria.add(Restrictions.eq(IDENVIOSSMS, getIdenviossms()));
        }

        if (getIdenviossmsIn().size() > 0) {
            criteria.add(Restrictions.in(IDENVIOSSMS, getIdenviossmsIn()));
        }

        if (isIdenviossmsIsNull()) {
            criteria.add(Restrictions.isNull(IDENVIOSSMS));
        }

        if (isIdenviossmsIsNotNull()) {
            criteria.add(Restrictions.isNotNull(IDENVIOSSMS));
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

        if (getDocusuario() != null) {
            if (getDocusuarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DOCUSUARIO, getDocusuario()));
            } 
            else if (getDocusuarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DOCUSUARIO, getDocusuario()));
            }
            else if (getDocusuarioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DOCUSUARIO, getDocusuario())));
            }
            else {
                criteria.add(Restrictions.like(DOCUSUARIO, getDocusuario()));
            }
        }

        if (getDocusuarioIn().size() > 0) {
            criteria.add(Restrictions.in(DOCUSUARIO, getDocusuarioIn()));
        }

        if (isDocusuarioIsNull()) {
            criteria.add(Restrictions.isNull(DOCUSUARIO));
        }

        if (isDocusuarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DOCUSUARIO));
        }

        if (getCodsia() != null) {
            if (getCodsiaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODSIA, getCodsia()));
            } 
            else if (getCodsiaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODSIA, getCodsia()));
            }
            else if (getCodsiaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODSIA, getCodsia())));
            }
            else {
                criteria.add(Restrictions.like(CODSIA, getCodsia()));
            }
        }

        if (getCodsiaIn().size() > 0) {
            criteria.add(Restrictions.in(CODSIA, getCodsiaIn()));
        }

        if (isCodsiaIsNull()) {
            criteria.add(Restrictions.isNull(CODSIA));
        }

        if (isCodsiaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODSIA));
        }

        if (getCodorganismo() != null) {
            if (getCodorganismoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODORGANISMO, getCodorganismo()));
            } 
            else if (getCodorganismoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODORGANISMO, getCodorganismo()));
            }
            else if (getCodorganismoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODORGANISMO, getCodorganismo())));
            }
            else {
                criteria.add(Restrictions.like(CODORGANISMO, getCodorganismo()));
            }
        }

        if (getCodorganismoIn().size() > 0) {
            criteria.add(Restrictions.in(CODORGANISMO, getCodorganismoIn()));
        }

        if (isCodorganismoIsNull()) {
            criteria.add(Restrictions.isNull(CODORGANISMO));
        }

        if (isCodorganismoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODORGANISMO));
        }

        if (getCodorganismopagador() != null) {
            if (getCodorganismopagadorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODORGANISMOPAGADOR, getCodorganismopagador()));
            } 
            else if (getCodorganismopagadorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODORGANISMOPAGADOR, getCodorganismopagador()));
            }
            else if (getCodorganismopagadorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODORGANISMOPAGADOR, getCodorganismopagador())));
            }
            else {
                criteria.add(Restrictions.like(CODORGANISMOPAGADOR, getCodorganismopagador()));
            }
        }

        if (getCodorganismopagadorIn().size() > 0) {
            criteria.add(Restrictions.in(CODORGANISMOPAGADOR, getCodorganismopagadorIn()));
        }

        if (isCodorganismopagadorIsNull()) {
            criteria.add(Restrictions.isNull(CODORGANISMOPAGADOR));
        }

        if (isCodorganismopagadorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODORGANISMOPAGADOR));
        }

        if (getNombreusuario() != null) {
            if (getNombreusuarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBREUSUARIO, getNombreusuario()));
            } 
            else if (getNombreusuarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBREUSUARIO, getNombreusuario()));
            }
            else if (getNombreusuarioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBREUSUARIO, getNombreusuario())));
            }
            else {
                criteria.add(Restrictions.like(NOMBREUSUARIO, getNombreusuario()));
            }
        }

        if (getNombreusuarioIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBREUSUARIO, getNombreusuarioIn()));
        }

        if (isNombreusuarioIsNull()) {
            criteria.add(Restrictions.isNull(NOMBREUSUARIO));
        }

        if (isNombreusuarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBREUSUARIO));
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

        if (getSonido() != null) {
            if (getSonidoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SONIDO, getSonido()));
            } 
            else if (getSonidoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SONIDO, getSonido()));
            }
            else if (getSonidoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SONIDO, getSonido())));
            }
            else {
                criteria.add(Restrictions.like(SONIDO, getSonido()));
            }
        }

        if (getSonidoIn().size() > 0) {
            criteria.add(Restrictions.in(SONIDO, getSonidoIn()));
        }

        if (isSonidoIsNull()) {
            criteria.add(Restrictions.isNull(SONIDO));
        }

        if (isSonidoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SONIDO));
        }
        
        if (null != firstResult){
        	criteria.setFirstResult(firstResult);
        }
        
        if (null != maxResult){
        	criteria.setMaxResults(maxResult);
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
	 * @return the firstResult
	 */
	public Integer getFirstResult() {
		return firstResult;
	}

	/**
	 * @param firstResult the firstResult to set
	 */
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	/**
	 * @return the maxResult
	 */
	public Integer getMaxResult() {
		return maxResult;
	}

	/**
	 * @param maxResult the maxResult to set
	 */
	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}
}
 
