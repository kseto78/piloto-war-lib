/*
 *
 * archivo: TblGestionEnviosQuery.java
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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblGestionEnvios;

/**
 * Clase con criterios de busqueda para la entidad TblGestionEnvios
 */
public class TblGestionEnviosQuery extends AbstractHibernateQueryEntity<TblGestionEnvios> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String MENSAJEID = "mensajeid";
    public static final String APLICACION = "aplicacion";
    public static final String CANAL = "canal";
    public static final String SERVICIO = "servicio";
    public static final String ESTADO = "estado";
    public static final String DESTINATARIO = "destinatario";
    public static final String APLICACIONID = "aplicacionid";
    public static final String SERVICIOID = "servicioid";
    public static final String CANALID = "canalid";
    public static final String LOTEENVIOID = "loteenvioid";
    public static final String NOMBRE = "nombre";
    public static final String ULTIMOENVIO = "ultimoenvio";
    public static final String ESTADOID = "estadoid";
    public static final String SERVIDORID = "servidorid";
    public static final String CODIGOEXTERNO = "codigoexterno";
    public static final String ANIO = "anio";
    public static final String MES = "mes";
    public static final String DOCUSUARIO = "docusuario";
    public static final String CODSIA = "codsia";
    public static final String CODORGANISMO = "codorganismo";
    public static final String CODORGANISMOPAGADOR = "codorganismopagador";
    public static final String NOMBREUSUARIO = "nombreusuario";
    public static final String ICONO = "icono";
    public static final String SONIDO = "sonido";
    public static final String ESTADOLOTE = "estadolote";


    /**
     * Valor de busqueda de campo mensajeid
     */
    private Long mensajeid;

    /**
     * Lista de valores del campo mensajeid para busquedas tipo IN
     */
    private List<Long> mensajeidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo aplicacion
     */
    private String aplicacion;

    /**
     * Tipo de comparador para la busqueda por campo aplicacion
     */
    private TextComparator aplicacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo aplicacion para busquedas tipo IN
     */
    private List<String> aplicacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo aplicacion es NULL
     */
    private boolean aplicacionIsNull = false;

    /**
     * Permite buscar cuando campo aplicacion es NOT NULL
     */
    private boolean aplicacionIsNotNull = false;

    /**
     * Valor de busqueda de campo canal
     */
    private String canal;

    /**
     * Tipo de comparador para la busqueda por campo canal
     */
    private TextComparator canalComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo canal para busquedas tipo IN
     */
    private List<String> canalIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo canal es NULL
     */
    private boolean canalIsNull = false;

    /**
     * Permite buscar cuando campo canal es NOT NULL
     */
    private boolean canalIsNotNull = false;

    /**
     * Valor de busqueda de campo servicio
     */
    private String servicio;

    /**
     * Tipo de comparador para la busqueda por campo servicio
     */
    private TextComparator servicioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo servicio para busquedas tipo IN
     */
    private List<String> servicioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo servicio es NULL
     */
    private boolean servicioIsNull = false;

    /**
     * Permite buscar cuando campo servicio es NOT NULL
     */
    private boolean servicioIsNotNull = false;

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
     * Valor de busqueda de campo aplicacionid
     */
    private Long aplicacionid;

    /**
     * Lista de valores del campo aplicacionid para busquedas tipo IN
     */
    private List<Long> aplicacionidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo aplicacionid es NULL
     */
    private boolean aplicacionidIsNull = false;

    /**
     * Permite buscar cuando campo aplicacionid es NOT NULL
     */
    private boolean aplicacionidIsNotNull = false;

    /**
     * Valor de busqueda de campo servicioid
     */
    private Long servicioid;

    /**
     * Lista de valores del campo servicioid para busquedas tipo IN
     */
    private List<Long> servicioidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo servicioid es NULL
     */
    private boolean servicioidIsNull = false;

    /**
     * Permite buscar cuando campo servicioid es NOT NULL
     */
    private boolean servicioidIsNotNull = false;

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
     * Valor de busqueda de campo anio
     */
    private Integer anio;

    /**
     * Lista de valores del campo anio para busquedas tipo IN
     */
    private List<Integer> anioIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo anio es NULL
     */
    private boolean anioIsNull = false;

    /**
     * Permite buscar cuando campo anio es NOT NULL
     */
    private boolean anioIsNotNull = false;

    /**
     * Valor de busqueda de campo mes
     */
    private Integer mes;

    /**
     * Lista de valores del campo mes para busquedas tipo IN
     */
    private List<Integer> mesIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo mes es NULL
     */
    private boolean mesIsNull = false;

    /**
     * Permite buscar cuando campo mes es NOT NULL
     */
    private boolean mesIsNotNull = false;

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
     * Valor de busqueda de campo estadolote
     */
    private String estadolote;

    /**
     * Tipo de comparador para la busqueda por campo estadolote
     */
    private TextComparator estadoloteComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo estadolote para busquedas tipo IN
     */
    private List<String> estadoloteIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo estadolote es NULL
     */
    private boolean estadoloteIsNull = false;

    /**
     * Permite buscar cuando campo estadolote es NOT NULL
     */
    private boolean estadoloteIsNotNull = false;

    private Integer primerResultado;
    
    private Integer maxResultados;
    
    
    
    /**
     * Constructor default
     */
    public TblGestionEnviosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblGestionEnviosQuery(Long mensajeid) {
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
     * Valor de busqueda de campo aplicacion
     * @return String.
     */
    public String getAplicacion() {
        if (aplicacion != null) {
            switch (aplicacionComparator) {
	            case STARTS_WITH:
	                return aplicacion + "%";
	            case CONTAINS:
	                return "%" + aplicacion + "%";
	            case ENDS_WITH:
	                return "%" + aplicacion;
	            case EQUALS:
                	return aplicacion;
              	default:
	            	break;
            }
        }
        return aplicacion;
    }

    /**
     * Valor de busqueda de campo aplicacion
     * @param aplicacion Valor de seteo.
     */
    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo aplicacion
     * @return aplicacionComparator.
     */
    public TextComparator getAplicacionComparator() {
        return aplicacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo aplicacion
     * @param aplicacionComparator Valor de seteo.
     */
    public void setAplicacionComparator(TextComparator aplicacionComparator) {
        this.aplicacionComparator = aplicacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getAplicacionIn() {
        return this.aplicacionIn;
    }

    /**
     * @param aplicacion Valor a agregar.
     */
    public void addAplicacionIn(String aplicacion) {
        this.aplicacionIn.add(aplicacion);
    }

    /**
     * Permite buscar cuando campo aplicacion es NULL
     * @return boolean.
     */
    public boolean isAplicacionIsNull() {
        return aplicacionIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacion es NULL
     * @param aplicacionIsNull Valor de seteo.
     */
    public void setAplicacionIsNull(boolean aplicacionIsNull) {
        this.aplicacionIsNull = aplicacionIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacion es NOT NULL
     * @return boolean.
     */
    public boolean isAplicacionIsNotNull() {
        return aplicacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo aplicacion es NOT NULL
     * @param aplicacionIsNotNull Valor de seteo.
     */
    public void setAplicacionIsNotNull(boolean aplicacionIsNotNull) {
        this.aplicacionIsNotNull = aplicacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo canal
     * @return String.
     */
    public String getCanal() {
        if (canal != null) {
            switch (canalComparator) {
	            case STARTS_WITH:
	                return canal + "%";
	            case CONTAINS:
	                return "%" + canal + "%";
	            case ENDS_WITH:
	                return "%" + canal;
	            case EQUALS:
                	return canal;
              	default:
	            	break;
            }
        }
        return canal;
    }

    /**
     * Valor de busqueda de campo canal
     * @param canal Valor de seteo.
     */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
     * Tipo de comparador para la busqueda por campo canal
     * @return canalComparator.
     */
    public TextComparator getCanalComparator() {
        return canalComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo canal
     * @param canalComparator Valor de seteo.
     */
    public void setCanalComparator(TextComparator canalComparator) {
        this.canalComparator = canalComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCanalIn() {
        return this.canalIn;
    }

    /**
     * @param canal Valor a agregar.
     */
    public void addCanalIn(String canal) {
        this.canalIn.add(canal);
    }

    /**
     * Permite buscar cuando campo canal es NULL
     * @return boolean.
     */
    public boolean isCanalIsNull() {
        return canalIsNull;
    }

    /**
     * Permite buscar cuando campo canal es NULL
     * @param canalIsNull Valor de seteo.
     */
    public void setCanalIsNull(boolean canalIsNull) {
        this.canalIsNull = canalIsNull;
    }

    /**
     * Permite buscar cuando campo canal es NOT NULL
     * @return boolean.
     */
    public boolean isCanalIsNotNull() {
        return canalIsNotNull;
    }

    /**
     * Permite buscar cuando campo canal es NOT NULL
     * @param canalIsNotNull Valor de seteo.
     */
    public void setCanalIsNotNull(boolean canalIsNotNull) {
        this.canalIsNotNull = canalIsNotNull;
    }

    /**
     * Valor de busqueda de campo servicio
     * @return String.
     */
    public String getServicio() {
        if (servicio != null) {
            switch (servicioComparator) {
	            case STARTS_WITH:
	                return servicio + "%";
	            case CONTAINS:
	                return "%" + servicio + "%";
	            case ENDS_WITH:
	                return "%" + servicio;
	            case EQUALS:
                	return servicio;
              	default:
	            	break;
            }
        }
        return servicio;
    }

    /**
     * Valor de busqueda de campo servicio
     * @param servicio Valor de seteo.
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * Tipo de comparador para la busqueda por campo servicio
     * @return servicioComparator.
     */
    public TextComparator getServicioComparator() {
        return servicioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo servicio
     * @param servicioComparator Valor de seteo.
     */
    public void setServicioComparator(TextComparator servicioComparator) {
        this.servicioComparator = servicioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getServicioIn() {
        return this.servicioIn;
    }

    /**
     * @param servicio Valor a agregar.
     */
    public void addServicioIn(String servicio) {
        this.servicioIn.add(servicio);
    }

    /**
     * Permite buscar cuando campo servicio es NULL
     * @return boolean.
     */
    public boolean isServicioIsNull() {
        return servicioIsNull;
    }

    /**
     * Permite buscar cuando campo servicio es NULL
     * @param servicioIsNull Valor de seteo.
     */
    public void setServicioIsNull(boolean servicioIsNull) {
        this.servicioIsNull = servicioIsNull;
    }

    /**
     * Permite buscar cuando campo servicio es NOT NULL
     * @return boolean.
     */
    public boolean isServicioIsNotNull() {
        return servicioIsNotNull;
    }

    /**
     * Permite buscar cuando campo servicio es NOT NULL
     * @param servicioIsNotNull Valor de seteo.
     */
    public void setServicioIsNotNull(boolean servicioIsNotNull) {
        this.servicioIsNotNull = servicioIsNotNull;
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
     * Valor de busqueda de campo aplicacionid
     * @return Long.
     */
    public Long getAplicacionid() {
        return aplicacionid;
    }

    /**
     * Valor de busqueda de campo aplicacionid
     * @param aplicacionid Valor de seteo.
     */
    public void setAplicacionid(Long aplicacionid) {
        this.aplicacionid = aplicacionid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getAplicacionidIn() {
        return this.aplicacionidIn;
    }

    /**
     * @param aplicacionid Valor a agregar.
     */
    public void addAplicacionidIn(Long aplicacionid) {
        this.aplicacionidIn.add(aplicacionid);
    }

    /**
     * Permite buscar cuando campo aplicacionid es NULL
     * @return boolean.
     */
    public boolean isAplicacionidIsNull() {
        return aplicacionidIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacionid es NULL
     * @param aplicacionidIsNull Valor de seteo.
     */
    public void setAplicacionidIsNull(boolean aplicacionidIsNull) {
        this.aplicacionidIsNull = aplicacionidIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacionid es NOT NULL
     * @return boolean.
     */
    public boolean isAplicacionidIsNotNull() {
        return aplicacionidIsNotNull;
    }

    /**
     * Permite buscar cuando campo aplicacionid es NOT NULL
     * @param aplicacionidIsNotNull Valor de seteo.
     */
    public void setAplicacionidIsNotNull(boolean aplicacionidIsNotNull) {
        this.aplicacionidIsNotNull = aplicacionidIsNotNull;
    }

    /**
     * Valor de busqueda de campo servicioid
     * @return Long.
     */
    public Long getServicioid() {
        return servicioid;
    }

    /**
     * Valor de busqueda de campo servicioid
     * @param servicioid Valor de seteo.
     */
    public void setServicioid(Long servicioid) {
        this.servicioid = servicioid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getServicioidIn() {
        return this.servicioidIn;
    }

    /**
     * @param servicioid Valor a agregar.
     */
    public void addServicioidIn(Long servicioid) {
        this.servicioidIn.add(servicioid);
    }

    /**
     * Permite buscar cuando campo servicioid es NULL
     * @return boolean.
     */
    public boolean isServicioidIsNull() {
        return servicioidIsNull;
    }

    /**
     * Permite buscar cuando campo servicioid es NULL
     * @param servicioidIsNull Valor de seteo.
     */
    public void setServicioidIsNull(boolean servicioidIsNull) {
        this.servicioidIsNull = servicioidIsNull;
    }

    /**
     * Permite buscar cuando campo servicioid es NOT NULL
     * @return boolean.
     */
    public boolean isServicioidIsNotNull() {
        return servicioidIsNotNull;
    }

    /**
     * Permite buscar cuando campo servicioid es NOT NULL
     * @param servicioidIsNotNull Valor de seteo.
     */
    public void setServicioidIsNotNull(boolean servicioidIsNotNull) {
        this.servicioidIsNotNull = servicioidIsNotNull;
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
     * Valor de busqueda de campo anio
     * @return Integer.
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * Valor de busqueda de campo anio
     * @param anio Valor de seteo.
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getAnioIn() {
        return this.anioIn;
    }

    /**
     * @param anio Valor a agregar.
     */
    public void addAnioIn(Integer anio) {
        this.anioIn.add(anio);
    }

    /**
     * Permite buscar cuando campo anio es NULL
     * @return boolean.
     */
    public boolean isAnioIsNull() {
        return anioIsNull;
    }

    /**
     * Permite buscar cuando campo anio es NULL
     * @param anioIsNull Valor de seteo.
     */
    public void setAnioIsNull(boolean anioIsNull) {
        this.anioIsNull = anioIsNull;
    }

    /**
     * Permite buscar cuando campo anio es NOT NULL
     * @return boolean.
     */
    public boolean isAnioIsNotNull() {
        return anioIsNotNull;
    }

    /**
     * Permite buscar cuando campo anio es NOT NULL
     * @param anioIsNotNull Valor de seteo.
     */
    public void setAnioIsNotNull(boolean anioIsNotNull) {
        this.anioIsNotNull = anioIsNotNull;
    }

    /**
     * Valor de busqueda de campo mes
     * @return Integer.
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * Valor de busqueda de campo mes
     * @param mes Valor de seteo.
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getMesIn() {
        return this.mesIn;
    }

    /**
     * @param mes Valor a agregar.
     */
    public void addMesIn(Integer mes) {
        this.mesIn.add(mes);
    }

    /**
     * Permite buscar cuando campo mes es NULL
     * @return boolean.
     */
    public boolean isMesIsNull() {
        return mesIsNull;
    }

    /**
     * Permite buscar cuando campo mes es NULL
     * @param mesIsNull Valor de seteo.
     */
    public void setMesIsNull(boolean mesIsNull) {
        this.mesIsNull = mesIsNull;
    }

    /**
     * Permite buscar cuando campo mes es NOT NULL
     * @return boolean.
     */
    public boolean isMesIsNotNull() {
        return mesIsNotNull;
    }

    /**
     * Permite buscar cuando campo mes es NOT NULL
     * @param mesIsNotNull Valor de seteo.
     */
    public void setMesIsNotNull(boolean mesIsNotNull) {
        this.mesIsNotNull = mesIsNotNull;
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
     * Valor de busqueda de campo estadolote
     * @return String.
     */
    public String getEstadolote() {
        if (estadolote != null) {
            switch (estadoloteComparator) {
	            case STARTS_WITH:
	                return estadolote + "%";
	            case CONTAINS:
	                return "%" + estadolote + "%";
	            case ENDS_WITH:
	                return "%" + estadolote;
	            case EQUALS:
                	return estadolote;
              	default:
	            	break;
            }
        }
        return estadolote;
    }

    /**
     * Valor de busqueda de campo estadolote
     * @param estadolote Valor de seteo.
     */
    public void setEstadolote(String estadolote) {
        this.estadolote = estadolote;
    }

    /**
     * Tipo de comparador para la busqueda por campo estadolote
     * @return estadoloteComparator.
     */
    public TextComparator getEstadoloteComparator() {
        return estadoloteComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo estadolote
     * @param estadoloteComparator Valor de seteo.
     */
    public void setEstadoloteComparator(TextComparator estadoloteComparator) {
        this.estadoloteComparator = estadoloteComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEstadoloteIn() {
        return this.estadoloteIn;
    }

    /**
     * @param estadolote Valor a agregar.
     */
    public void addEstadoloteIn(String estadolote) {
        this.estadoloteIn.add(estadolote);
    }

    /**
     * Permite buscar cuando campo estadolote es NULL
     * @return boolean.
     */
    public boolean isEstadoloteIsNull() {
        return estadoloteIsNull;
    }

    /**
     * Permite buscar cuando campo estadolote es NULL
     * @param estadoloteIsNull Valor de seteo.
     */
    public void setEstadoloteIsNull(boolean estadoloteIsNull) {
        this.estadoloteIsNull = estadoloteIsNull;
    }

    /**
     * Permite buscar cuando campo estadolote es NOT NULL
     * @return boolean.
     */
    public boolean isEstadoloteIsNotNull() {
        return estadoloteIsNotNull;
    }

    /**
     * Permite buscar cuando campo estadolote es NOT NULL
     * @param estadoloteIsNotNull Valor de seteo.
     */
    public void setEstadoloteIsNotNull(boolean estadoloteIsNotNull) {
        this.estadoloteIsNotNull = estadoloteIsNotNull;
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

        if (getAplicacion() != null) {
            if (getAplicacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(APLICACION, getAplicacion()));
            } 
            else if (getAplicacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(APLICACION, getAplicacion()));
            }
            else if (getAplicacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APLICACION, getAplicacion())));
            }
            else {
                criteria.add(Restrictions.like(APLICACION, getAplicacion()));
            }
        }

        if (getAplicacionIn().size() > 0) {
            criteria.add(Restrictions.in(APLICACION, getAplicacionIn()));
        }

        if (isAplicacionIsNull()) {
            criteria.add(Restrictions.isNull(APLICACION));
        }

        if (isAplicacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(APLICACION));
        }

        if (getCanal() != null) {
            if (getCanalComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CANAL, getCanal()));
            } 
            else if (getCanalComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CANAL, getCanal()));
            }
            else if (getCanalComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CANAL, getCanal())));
            }
            else {
                criteria.add(Restrictions.like(CANAL, getCanal()));
            }
        }

        if (getCanalIn().size() > 0) {
            criteria.add(Restrictions.in(CANAL, getCanalIn()));
        }

        if (isCanalIsNull()) {
            criteria.add(Restrictions.isNull(CANAL));
        }

        if (isCanalIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CANAL));
        }

        if (getServicio() != null) {
            if (getServicioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SERVICIO, getServicio()));
            } 
            else if (getServicioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SERVICIO, getServicio()));
            }
            else if (getServicioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SERVICIO, getServicio())));
            }
            else {
                criteria.add(Restrictions.like(SERVICIO, getServicio()));
            }
        }

        if (getServicioIn().size() > 0) {
            criteria.add(Restrictions.in(SERVICIO, getServicioIn()));
        }

        if (isServicioIsNull()) {
            criteria.add(Restrictions.isNull(SERVICIO));
        }

        if (isServicioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVICIO));
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

        if (getAplicacionid() != null) {
            criteria.add(Restrictions.eq(APLICACIONID, getAplicacionid()));
        }

        if (getAplicacionidIn().size() > 0) {
            criteria.add(Restrictions.in(APLICACIONID, getAplicacionidIn()));
        }

        if (isAplicacionidIsNull()) {
            criteria.add(Restrictions.isNull(APLICACIONID));
        }

        if (isAplicacionidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(APLICACIONID));
        }

        if (getServicioid() != null) {
            criteria.add(Restrictions.eq(SERVICIOID, getServicioid()));
        }

        if (getServicioidIn().size() > 0) {
            criteria.add(Restrictions.in(SERVICIOID, getServicioidIn()));
        }

        if (isServicioidIsNull()) {
            criteria.add(Restrictions.isNull(SERVICIOID));
        }

        if (isServicioidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVICIOID));
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

        if (getAnio() != null) {
            criteria.add(Restrictions.eq(ANIO, getAnio()));
        }

        if (getAnioIn().size() > 0) {
            criteria.add(Restrictions.in(ANIO, getAnioIn()));
        }

        if (isAnioIsNull()) {
            criteria.add(Restrictions.isNull(ANIO));
        }

        if (isAnioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ANIO));
        }

        if (getMes() != null) {
            criteria.add(Restrictions.eq(MES, getMes()));
        }

        if (getMesIn().size() > 0) {
            criteria.add(Restrictions.in(MES, getMesIn()));
        }

        if (isMesIsNull()) {
            criteria.add(Restrictions.isNull(MES));
        }

        if (isMesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MES));
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

        if (getEstadolote() != null) {
            if (getEstadoloteComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ESTADOLOTE, getEstadolote()));
            } 
            else if (getEstadoloteComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ESTADOLOTE, getEstadolote()));
            }
            else if (getEstadoloteComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ESTADOLOTE, getEstadolote())));
            }
            else {
                criteria.add(Restrictions.like(ESTADOLOTE, getEstadolote()));
            }
        }

        if (getEstadoloteIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADOLOTE, getEstadoloteIn()));
        }

        if (isEstadoloteIsNull()) {
            criteria.add(Restrictions.isNull(ESTADOLOTE));
        }

        if (isEstadoloteIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADOLOTE));
        }
        
        if(null != maxResultados && maxResultados > 0){
			criteria.setMaxResults(maxResultados);
		}
		
		if (null != primerResultado && primerResultado > 0){
			criteria.setFirstResult(primerResultado);
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
	 * @return the primerResultado
	 */
	public Integer getPrimerResultado() {
		return primerResultado;
	}

	/**
	 * @param primerResultado the primerResultado to set
	 */
	public void setPrimerResultado(Integer primerResultado) {
		this.primerResultado = primerResultado;
	}

	/**
	 * @return the maxResultados
	 */
	public Integer getMaxResultados() {
		return maxResultados;
	}

	/**
	 * @param maxResultados the maxResultados to set
	 */
	public void setMaxResultados(Integer maxResultados) {
		this.maxResultados = maxResultados;
	}
}
 
