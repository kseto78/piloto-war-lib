/*
 *
 * archivo: ViewUsuariosPushQuery.java
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
import es.minhap.sim.model.ViewUsuariosPush;

/**
 * Clase con criterios de busqueda para la entidad ViewUsuariosPush
 */
public class ViewUsuariosPushQuery extends AbstractHibernateQueryEntity<ViewUsuariosPush> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String USUARIOID = "usuarioid";
    public static final String NOMBREUSUARIO = "nombreusuario";
    public static final String PLATAFORMA = "plataforma";
    public static final String TOKENUSUARIO = "tokenusuario";
    public static final String APLICACIONID = "aplicacionid";
    public static final String APLICACION = "aplicacion";
    public static final String SERVICIOID = "servicioid";
    public static final String SERVICIO = "servicio";
    public static final String DISPOSITIVOID = "dispositivoid";
    public static final String FECHA = "fecha";


    /**
     * Valor de busqueda de campo usuarioid
     */
    private Long usuarioid;

    /**
     * Lista de valores del campo usuarioid para busquedas tipo IN
     */
    private List<Long> usuarioidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo usuarioid es NULL
     */
    private boolean usuarioidIsNull = false;

    /**
     * Permite buscar cuando campo usuarioid es NOT NULL
     */
    private boolean usuarioidIsNotNull = false;

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
     * Valor de busqueda de campo plataforma
     */
    private String plataforma;

    /**
     * Tipo de comparador para la busqueda por campo plataforma
     */
    private TextComparator plataformaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo plataforma para busquedas tipo IN
     */
    private List<String> plataformaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo plataforma es NULL
     */
    private boolean plataformaIsNull = false;

    /**
     * Permite buscar cuando campo plataforma es NOT NULL
     */
    private boolean plataformaIsNotNull = false;

    /**
     * Valor de busqueda de campo tokenusuario
     */
    private String tokenusuario;

    /**
     * Tipo de comparador para la busqueda por campo tokenusuario
     */
    private TextComparator tokenusuarioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo tokenusuario para busquedas tipo IN
     */
    private List<String> tokenusuarioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo tokenusuario es NULL
     */
    private boolean tokenusuarioIsNull = false;

    /**
     * Permite buscar cuando campo tokenusuario es NOT NULL
     */
    private boolean tokenusuarioIsNotNull = false;

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
     * Valor de busqueda de campo dispositivoid
     */
    private String dispositivoid;

    /**
     * Tipo de comparador para la busqueda por campo dispositivoid
     */
    private TextComparator dispositivoidComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo dispositivoid para busquedas tipo IN
     */
    private List<String> dispositivoidIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo dispositivoid es NULL
     */
    private boolean dispositivoidIsNull = false;

    /**
     * Permite buscar cuando campo dispositivoid es NOT NULL
     */
    private boolean dispositivoidIsNotNull = false;

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
	 * Establece el máximo de resultados
	 */
	private Integer maxResultados;
	
	/**
	 * Establece el primer resultados
	 */
	private Integer primerResultado;
    
    /**
     * Constructor default
     */
    public ViewUsuariosPushQuery() {
    
    }

    /**
     * Valor de busqueda de campo usuarioid
     * @return Long.
     */
    public Long getUsuarioid() {
        return usuarioid;
    }

    /**
     * Valor de busqueda de campo usuarioid
     * @param usuarioid Valor de seteo.
     */
    public void setUsuarioid(Long usuarioid) {
        this.usuarioid = usuarioid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getUsuarioidIn() {
        return this.usuarioidIn;
    }

    /**
     * @param usuarioid Valor a agregar.
     */
    public void addUsuarioidIn(Long usuarioid) {
        this.usuarioidIn.add(usuarioid);
    }

    /**
     * Permite buscar cuando campo usuarioid es NULL
     * @return boolean.
     */
    public boolean isUsuarioidIsNull() {
        return usuarioidIsNull;
    }

    /**
     * Permite buscar cuando campo usuarioid es NULL
     * @param usuarioidIsNull Valor de seteo.
     */
    public void setUsuarioidIsNull(boolean usuarioidIsNull) {
        this.usuarioidIsNull = usuarioidIsNull;
    }

    /**
     * Permite buscar cuando campo usuarioid es NOT NULL
     * @return boolean.
     */
    public boolean isUsuarioidIsNotNull() {
        return usuarioidIsNotNull;
    }

    /**
     * Permite buscar cuando campo usuarioid es NOT NULL
     * @param usuarioidIsNotNull Valor de seteo.
     */
    public void setUsuarioidIsNotNull(boolean usuarioidIsNotNull) {
        this.usuarioidIsNotNull = usuarioidIsNotNull;
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
     * Valor de busqueda de campo plataforma
     * @return String.
     */
    public String getPlataforma() {
        if (plataforma != null) {
            switch (plataformaComparator) {
	            case STARTS_WITH:
	                return plataforma + "%";
	            case CONTAINS:
	                return "%" + plataforma + "%";
	            case ENDS_WITH:
	                return "%" + plataforma;
	            case EQUALS:
                	return plataforma;
              	default:
	            	break;
            }
        }
        return plataforma;
    }

    /**
     * Valor de busqueda de campo plataforma
     * @param plataforma Valor de seteo.
     */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * Tipo de comparador para la busqueda por campo plataforma
     * @return plataformaComparator.
     */
    public TextComparator getPlataformaComparator() {
        return plataformaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo plataforma
     * @param plataformaComparator Valor de seteo.
     */
    public void setPlataformaComparator(TextComparator plataformaComparator) {
        this.plataformaComparator = plataformaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getPlataformaIn() {
        return this.plataformaIn;
    }

    /**
     * @param plataforma Valor a agregar.
     */
    public void addPlataformaIn(String plataforma) {
        this.plataformaIn.add(plataforma);
    }

    /**
     * Permite buscar cuando campo plataforma es NULL
     * @return boolean.
     */
    public boolean isPlataformaIsNull() {
        return plataformaIsNull;
    }

    /**
     * Permite buscar cuando campo plataforma es NULL
     * @param plataformaIsNull Valor de seteo.
     */
    public void setPlataformaIsNull(boolean plataformaIsNull) {
        this.plataformaIsNull = plataformaIsNull;
    }

    /**
     * Permite buscar cuando campo plataforma es NOT NULL
     * @return boolean.
     */
    public boolean isPlataformaIsNotNull() {
        return plataformaIsNotNull;
    }

    /**
     * Permite buscar cuando campo plataforma es NOT NULL
     * @param plataformaIsNotNull Valor de seteo.
     */
    public void setPlataformaIsNotNull(boolean plataformaIsNotNull) {
        this.plataformaIsNotNull = plataformaIsNotNull;
    }

    /**
     * Valor de busqueda de campo tokenusuario
     * @return String.
     */
    public String getTokenusuario() {
        if (tokenusuario != null) {
            switch (tokenusuarioComparator) {
	            case STARTS_WITH:
	                return tokenusuario + "%";
	            case CONTAINS:
	                return "%" + tokenusuario + "%";
	            case ENDS_WITH:
	                return "%" + tokenusuario;
	            case EQUALS:
                	return tokenusuario;
              	default:
	            	break;
            }
        }
        return tokenusuario;
    }

    /**
     * Valor de busqueda de campo tokenusuario
     * @param tokenusuario Valor de seteo.
     */
    public void setTokenusuario(String tokenusuario) {
        this.tokenusuario = tokenusuario;
    }

    /**
     * Tipo de comparador para la busqueda por campo tokenusuario
     * @return tokenusuarioComparator.
     */
    public TextComparator getTokenusuarioComparator() {
        return tokenusuarioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo tokenusuario
     * @param tokenusuarioComparator Valor de seteo.
     */
    public void setTokenusuarioComparator(TextComparator tokenusuarioComparator) {
        this.tokenusuarioComparator = tokenusuarioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTokenusuarioIn() {
        return this.tokenusuarioIn;
    }

    /**
     * @param tokenusuario Valor a agregar.
     */
    public void addTokenusuarioIn(String tokenusuario) {
        this.tokenusuarioIn.add(tokenusuario);
    }

    /**
     * Permite buscar cuando campo tokenusuario es NULL
     * @return boolean.
     */
    public boolean isTokenusuarioIsNull() {
        return tokenusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo tokenusuario es NULL
     * @param tokenusuarioIsNull Valor de seteo.
     */
    public void setTokenusuarioIsNull(boolean tokenusuarioIsNull) {
        this.tokenusuarioIsNull = tokenusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo tokenusuario es NOT NULL
     * @return boolean.
     */
    public boolean isTokenusuarioIsNotNull() {
        return tokenusuarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo tokenusuario es NOT NULL
     * @param tokenusuarioIsNotNull Valor de seteo.
     */
    public void setTokenusuarioIsNotNull(boolean tokenusuarioIsNotNull) {
        this.tokenusuarioIsNotNull = tokenusuarioIsNotNull;
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
     * Valor de busqueda de campo dispositivoid
     * @return String.
     */
    public String getDispositivoid() {
        if (dispositivoid != null) {
            switch (dispositivoidComparator) {
	            case STARTS_WITH:
	                return dispositivoid + "%";
	            case CONTAINS:
	                return "%" + dispositivoid + "%";
	            case ENDS_WITH:
	                return "%" + dispositivoid;
	            case EQUALS:
                	return dispositivoid;
              	default:
	            	break;
            }
        }
        return dispositivoid;
    }

    /**
     * Valor de busqueda de campo dispositivoid
     * @param dispositivoid Valor de seteo.
     */
    public void setDispositivoid(String dispositivoid) {
        this.dispositivoid = dispositivoid;
    }

    /**
     * Tipo de comparador para la busqueda por campo dispositivoid
     * @return dispositivoidComparator.
     */
    public TextComparator getDispositivoidComparator() {
        return dispositivoidComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo dispositivoid
     * @param dispositivoidComparator Valor de seteo.
     */
    public void setDispositivoidComparator(TextComparator dispositivoidComparator) {
        this.dispositivoidComparator = dispositivoidComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDispositivoidIn() {
        return this.dispositivoidIn;
    }

    /**
     * @param dispositivoid Valor a agregar.
     */
    public void addDispositivoidIn(String dispositivoid) {
        this.dispositivoidIn.add(dispositivoid);
    }

    /**
     * Permite buscar cuando campo dispositivoid es NULL
     * @return boolean.
     */
    public boolean isDispositivoidIsNull() {
        return dispositivoidIsNull;
    }

    /**
     * Permite buscar cuando campo dispositivoid es NULL
     * @param dispositivoidIsNull Valor de seteo.
     */
    public void setDispositivoidIsNull(boolean dispositivoidIsNull) {
        this.dispositivoidIsNull = dispositivoidIsNull;
    }

    /**
     * Permite buscar cuando campo dispositivoid es NOT NULL
     * @return boolean.
     */
    public boolean isDispositivoidIsNotNull() {
        return dispositivoidIsNotNull;
    }

    /**
     * Permite buscar cuando campo dispositivoid es NOT NULL
     * @param dispositivoidIsNotNull Valor de seteo.
     */
    public void setDispositivoidIsNotNull(boolean dispositivoidIsNotNull) {
        this.dispositivoidIsNotNull = dispositivoidIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getUsuarioid() != null) {
            criteria.add(Restrictions.eq(USUARIOID, getUsuarioid()));
        }

        if (getUsuarioidIn().size() > 0) {
            criteria.add(Restrictions.in(USUARIOID, getUsuarioidIn()));
        }

        if (isUsuarioidIsNull()) {
            criteria.add(Restrictions.isNull(USUARIOID));
        }

        if (isUsuarioidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(USUARIOID));
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

        if (getPlataforma() != null) {
            if (getPlataformaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PLATAFORMA, getPlataforma()));
            } 
            else if (getPlataformaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PLATAFORMA, getPlataforma()));
            }
            else if (getPlataformaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PLATAFORMA, getPlataforma())));
            }
            else {
                criteria.add(Restrictions.like(PLATAFORMA, getPlataforma()));
            }
        }

        if (getPlataformaIn().size() > 0) {
            criteria.add(Restrictions.in(PLATAFORMA, getPlataformaIn()));
        }

        if (isPlataformaIsNull()) {
            criteria.add(Restrictions.isNull(PLATAFORMA));
        }

        if (isPlataformaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PLATAFORMA));
        }

        if (getTokenusuario() != null) {
            if (getTokenusuarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TOKENUSUARIO, getTokenusuario()));
            } 
            else if (getTokenusuarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TOKENUSUARIO, getTokenusuario()));
            }
            else if (getTokenusuarioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TOKENUSUARIO, getTokenusuario())));
            }
            else {
                criteria.add(Restrictions.like(TOKENUSUARIO, getTokenusuario()));
            }
        }

        if (getTokenusuarioIn().size() > 0) {
            criteria.add(Restrictions.in(TOKENUSUARIO, getTokenusuarioIn()));
        }

        if (isTokenusuarioIsNull()) {
            criteria.add(Restrictions.isNull(TOKENUSUARIO));
        }

        if (isTokenusuarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TOKENUSUARIO));
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

        if (getDispositivoid() != null) {
            if (getDispositivoidComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DISPOSITIVOID, getDispositivoid()));
            } 
            else if (getDispositivoidComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DISPOSITIVOID, getDispositivoid()));
            }
            else if (getDispositivoidComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DISPOSITIVOID, getDispositivoid())));
            }
            else {
                criteria.add(Restrictions.like(DISPOSITIVOID, getDispositivoid()));
            }
        }

        if (getDispositivoidIn().size() > 0) {
            criteria.add(Restrictions.in(DISPOSITIVOID, getDispositivoidIn()));
        }

        if (isDispositivoidIsNull()) {
            criteria.add(Restrictions.isNull(DISPOSITIVOID));
        }

        if (isDispositivoidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DISPOSITIVOID));
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
 
