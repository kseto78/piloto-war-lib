/*
 *
 * archivo: ViewGestionenviosDetalladaQuery.java
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
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

import es.minhap.common.util.DateUtil;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.sim.model.ViewGestionenviosDetallada;

/**
 * Clase con criterios de busqueda para la entidad ViewGestionenviosDetallada
 */
public class ViewGestionenviosDetalladaQuery extends AbstractHibernateQueryEntity<ViewGestionenviosDetallada> {

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
    public static final String APLICACIONID = "aplicacionid";
    public static final String SERVICIOID = "servicioid";
    public static final String CANALID = "canalid";
    public static final String LOTEENVIOID = "loteenvioid";
    public static final String LOTEENVIO = "loteenvio";
    public static final String ULTIMOENVIO = "ultimoenvio";
    public static final String ESTADOID = "estadoid";
    public static final String IDEXTERNO = "idexterno";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    public static final String NUMEROENVIOS = "numeroenvios";


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
     * Valor de busqueda de campo loteenvio
     */
    private String loteenvio;

    /**
     * Tipo de comparador para la busqueda por campo loteenvio
     */
    private TextComparator loteenvioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo loteenvio para busquedas tipo IN
     */
    private List<String> loteenvioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo loteenvio es NULL
     */
    private boolean loteenvioIsNull = false;

    /**
     * Permite buscar cuando campo loteenvio es NOT NULL
     */
    private boolean loteenvioIsNotNull = false;

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
     * Valor de busqueda de campo idexterno
     */
    private String idexterno;

    /**
     * Tipo de comparador para la busqueda por campo idexterno
     */
    private TextComparator idexternoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo idexterno para busquedas tipo IN
     */
    private List<String> idexternoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo idexterno es NULL
     */
    private boolean idexternoIsNull = false;

    /**
     * Permite buscar cuando campo idexterno es NOT NULL
     */
    private boolean idexternoIsNotNull = false;

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
     * Constructor default
     */
    public ViewGestionenviosDetalladaQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ViewGestionenviosDetalladaQuery(Long mensajeid) {
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
     * Valor de busqueda de campo loteenvio
     * @return String.
     */
    public String getLoteenvio() {
        if (loteenvio != null) {
            switch (loteenvioComparator) {
	            case STARTS_WITH:
	                return loteenvio + "%";
	            case CONTAINS:
	                return "%" + loteenvio + "%";
	            case ENDS_WITH:
	                return "%" + loteenvio;
	            case EQUALS:
                	return loteenvio;
              	default:
	            	break;
            }
        }
        return loteenvio;
    }

    /**
     * Valor de busqueda de campo loteenvio
     * @param loteenvio Valor de seteo.
     */
    public void setLoteenvio(String loteenvio) {
        this.loteenvio = loteenvio;
    }

    /**
     * Tipo de comparador para la busqueda por campo loteenvio
     * @return loteenvioComparator.
     */
    public TextComparator getLoteenvioComparator() {
        return loteenvioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo loteenvio
     * @param loteenvioComparator Valor de seteo.
     */
    public void setLoteenvioComparator(TextComparator loteenvioComparator) {
        this.loteenvioComparator = loteenvioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getLoteenvioIn() {
        return this.loteenvioIn;
    }

    /**
     * @param loteenvio Valor a agregar.
     */
    public void addLoteenvioIn(String loteenvio) {
        this.loteenvioIn.add(loteenvio);
    }

    /**
     * Permite buscar cuando campo loteenvio es NULL
     * @return boolean.
     */
    public boolean isLoteenvioIsNull() {
        return loteenvioIsNull;
    }

    /**
     * Permite buscar cuando campo loteenvio es NULL
     * @param loteenvioIsNull Valor de seteo.
     */
    public void setLoteenvioIsNull(boolean loteenvioIsNull) {
        this.loteenvioIsNull = loteenvioIsNull;
    }

    /**
     * Permite buscar cuando campo loteenvio es NOT NULL
     * @return boolean.
     */
    public boolean isLoteenvioIsNotNull() {
        return loteenvioIsNotNull;
    }

    /**
     * Permite buscar cuando campo loteenvio es NOT NULL
     * @param loteenvioIsNotNull Valor de seteo.
     */
    public void setLoteenvioIsNotNull(boolean loteenvioIsNotNull) {
        this.loteenvioIsNotNull = loteenvioIsNotNull;
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
     * Valor de busqueda de campo idexterno
     * @return String.
     */
    public String getIdexterno() {
        if (idexterno != null) {
            switch (idexternoComparator) {
	            case STARTS_WITH:
	                return idexterno + "%";
	            case CONTAINS:
	                return "%" + idexterno + "%";
	            case ENDS_WITH:
	                return "%" + idexterno;
	            case EQUALS:
                	return idexterno;
              	default:
	            	break;
            }
        }
        return idexterno;
    }

    /**
     * Valor de busqueda de campo idexterno
     * @param idexterno Valor de seteo.
     */
    public void setIdexterno(String idexterno) {
        this.idexterno = idexterno;
    }

    /**
     * Tipo de comparador para la busqueda por campo idexterno
     * @return idexternoComparator.
     */
    public TextComparator getIdexternoComparator() {
        return idexternoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo idexterno
     * @param idexternoComparator Valor de seteo.
     */
    public void setIdexternoComparator(TextComparator idexternoComparator) {
        this.idexternoComparator = idexternoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getIdexternoIn() {
        return this.idexternoIn;
    }

    /**
     * @param idexterno Valor a agregar.
     */
    public void addIdexternoIn(String idexterno) {
        this.idexternoIn.add(idexterno);
    }

    /**
     * Permite buscar cuando campo idexterno es NULL
     * @return boolean.
     */
    public boolean isIdexternoIsNull() {
        return idexternoIsNull;
    }

    /**
     * Permite buscar cuando campo idexterno es NULL
     * @param idexternoIsNull Valor de seteo.
     */
    public void setIdexternoIsNull(boolean idexternoIsNull) {
        this.idexternoIsNull = idexternoIsNull;
    }

    /**
     * Permite buscar cuando campo idexterno es NOT NULL
     * @return boolean.
     */
    public boolean isIdexternoIsNotNull() {
        return idexternoIsNotNull;
    }

    /**
     * Permite buscar cuando campo idexterno es NOT NULL
     * @param idexternoIsNotNull Valor de seteo.
     */
    public void setIdexternoIsNotNull(boolean idexternoIsNotNull) {
        this.idexternoIsNotNull = idexternoIsNotNull;
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

        if (getLoteenvio() != null) {
            if (getLoteenvioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(LOTEENVIO, getLoteenvio()));
            } 
            else if (getLoteenvioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(LOTEENVIO, getLoteenvio()));
            }
            else if (getLoteenvioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(LOTEENVIO, getLoteenvio())));
            }
            else {
                criteria.add(Restrictions.like(LOTEENVIO, getLoteenvio()));
            }
        }

        if (getLoteenvioIn().size() > 0) {
            criteria.add(Restrictions.in(LOTEENVIO, getLoteenvioIn()));
        }

        if (isLoteenvioIsNull()) {
            criteria.add(Restrictions.isNull(LOTEENVIO));
        }

        if (isLoteenvioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(LOTEENVIO));
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

        if (getIdexterno() != null) {
            if (getIdexternoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(IDEXTERNO, getIdexterno()));
            } 
            else if (getIdexternoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(IDEXTERNO, getIdexterno()));
            }
            else if (getIdexternoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(IDEXTERNO, getIdexterno())));
            }
            else {
                criteria.add(Restrictions.like(IDEXTERNO, getIdexterno()));
            }
        }

        if (getIdexternoIn().size() > 0) {
            criteria.add(Restrictions.in(IDEXTERNO, getIdexternoIn()));
        }

        if (isIdexternoIsNull()) {
            criteria.add(Restrictions.isNull(IDEXTERNO));
        }

        if (isIdexternoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(IDEXTERNO));
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
 
