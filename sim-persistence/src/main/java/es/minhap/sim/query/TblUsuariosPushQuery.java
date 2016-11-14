/*
 *
 * archivo: TblUsuariosPushQuery.java
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
import es.minhap.sim.model.TblUsuariosPush;

/**
 * Clase con criterios de busqueda para la entidad TblUsuariosPush
 */
public class TblUsuariosPushQuery extends AbstractHibernateQueryEntity<TblUsuariosPush> {

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
    public static final String SERVICIOID = "servicioid";
    public static final String PLATAFORMAID = "plataformaid";
    public static final String FECHACREACION = "fechacreacion";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String TOKENUSUARIO = "tokenusuario";
    public static final String DISPOSITIVOID = "dispositivoid";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDO1 = "apellido1";
    public static final String APELLIDO2 = "apellido2";
    public static final String ELIMINADO = "eliminado";
    public static final String NOTEQUAL = "nEqual";


    /**
     * Valor de busqueda de campo usuarioid
     */
    private Long usuarioid;

    /**
     * Lista de valores del campo usuarioid para busquedas tipo IN
     */
    private List<Long> usuarioidIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo plataformaid
     */
    private Long plataformaid;

    /**
     * Lista de valores del campo plataformaid para busquedas tipo IN
     */
    private List<Long> plataformaidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo plataformaid es NULL
     */
    private boolean plataformaidIsNull = false;

    /**
     * Permite buscar cuando campo plataformaid es NOT NULL
     */
    private boolean plataformaidIsNotNull = false;

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
     * Valor de busqueda de campo apellido1
     */
    private String apellido1;

    /**
     * Tipo de comparador para la busqueda por campo apellido1
     */
    private TextComparator apellido1Comparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo apellido1 para busquedas tipo IN
     */
    private List<String> apellido1In = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo apellido1 es NULL
     */
    private boolean apellido1IsNull = false;

    /**
     * Permite buscar cuando campo apellido1 es NOT NULL
     */
    private boolean apellido1IsNotNull = false;

    /**
     * Valor de busqueda de campo apellido2
     */
    private String apellido2;

    /**
     * Tipo de comparador para la busqueda por campo apellido2
     */
    private TextComparator apellido2Comparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo apellido2 para busquedas tipo IN
     */
    private List<String> apellido2In = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo apellido2 es NULL
     */
    private boolean apellido2IsNull = false;

    /**
     * Permite buscar cuando campo apellido2 es NOT NULL
     */
    private boolean apellido2IsNotNull = false;

    /**
     * Valor de busqueda de campo eliminado
     */
    private String eliminado;

    /**
     * Tipo de comparador para la busqueda por campo eliminado
     */
    private TextComparator eliminadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo eliminado para busquedas tipo IN
     */
    private List<String> eliminadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo eliminado es NULL
     */
    private boolean eliminadoIsNull = false;

    /**
     * Permite buscar cuando campo eliminado es NOT NULL
     */
    private boolean eliminadoIsNotNull = false;

    /**
     * Constructor default
     */
    public TblUsuariosPushQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblUsuariosPushQuery(Long usuarioid) {
        setUsuarioid(usuarioid);
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
     * Valor de busqueda de campo plataformaid
     * @return Long.
     */
    public Long getPlataformaid() {
        return plataformaid;
    }

    /**
     * Valor de busqueda de campo plataformaid
     * @param plataformaid Valor de seteo.
     */
    public void setPlataformaid(Long plataformaid) {
        this.plataformaid = plataformaid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getPlataformaidIn() {
        return this.plataformaidIn;
    }

    /**
     * @param plataformaid Valor a agregar.
     */
    public void addPlataformaidIn(Long plataformaid) {
        this.plataformaidIn.add(plataformaid);
    }

    /**
     * Permite buscar cuando campo plataformaid es NULL
     * @return boolean.
     */
    public boolean isPlataformaidIsNull() {
        return plataformaidIsNull;
    }

    /**
     * Permite buscar cuando campo plataformaid es NULL
     * @param plataformaidIsNull Valor de seteo.
     */
    public void setPlataformaidIsNull(boolean plataformaidIsNull) {
        this.plataformaidIsNull = plataformaidIsNull;
    }

    /**
     * Permite buscar cuando campo plataformaid es NOT NULL
     * @return boolean.
     */
    public boolean isPlataformaidIsNotNull() {
        return plataformaidIsNotNull;
    }

    /**
     * Permite buscar cuando campo plataformaid es NOT NULL
     * @param plataformaidIsNotNull Valor de seteo.
     */
    public void setPlataformaidIsNotNull(boolean plataformaidIsNotNull) {
        this.plataformaidIsNotNull = plataformaidIsNotNull;
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
     * Valor de busqueda de campo apellido1
     * @return String.
     */
    public String getApellido1() {
        if (apellido1 != null) {
            switch (apellido1Comparator) {
	            case STARTS_WITH:
	                return apellido1 + "%";
	            case CONTAINS:
	                return "%" + apellido1 + "%";
	            case ENDS_WITH:
	                return "%" + apellido1;
	            case EQUALS:
                	return apellido1;
              	default:
	            	break;
            }
        }
        return apellido1;
    }

    /**
     * Valor de busqueda de campo apellido1
     * @param apellido1 Valor de seteo.
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Tipo de comparador para la busqueda por campo apellido1
     * @return apellido1Comparator.
     */
    public TextComparator getApellido1Comparator() {
        return apellido1Comparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo apellido1
     * @param apellido1Comparator Valor de seteo.
     */
    public void setApellido1Comparator(TextComparator apellido1Comparator) {
        this.apellido1Comparator = apellido1Comparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getApellido1In() {
        return this.apellido1In;
    }

    /**
     * @param apellido1 Valor a agregar.
     */
    public void addApellido1In(String apellido1) {
        this.apellido1In.add(apellido1);
    }

    /**
     * Permite buscar cuando campo apellido1 es NULL
     * @return boolean.
     */
    public boolean isApellido1IsNull() {
        return apellido1IsNull;
    }

    /**
     * Permite buscar cuando campo apellido1 es NULL
     * @param apellido1IsNull Valor de seteo.
     */
    public void setApellido1IsNull(boolean apellido1IsNull) {
        this.apellido1IsNull = apellido1IsNull;
    }

    /**
     * Permite buscar cuando campo apellido1 es NOT NULL
     * @return boolean.
     */
    public boolean isApellido1IsNotNull() {
        return apellido1IsNotNull;
    }

    /**
     * Permite buscar cuando campo apellido1 es NOT NULL
     * @param apellido1IsNotNull Valor de seteo.
     */
    public void setApellido1IsNotNull(boolean apellido1IsNotNull) {
        this.apellido1IsNotNull = apellido1IsNotNull;
    }

    /**
     * Valor de busqueda de campo apellido2
     * @return String.
     */
    public String getApellido2() {
        if (apellido2 != null) {
            switch (apellido2Comparator) {
	            case STARTS_WITH:
	                return apellido2 + "%";
	            case CONTAINS:
	                return "%" + apellido2 + "%";
	            case ENDS_WITH:
	                return "%" + apellido2;
	            case EQUALS:
                	return apellido2;
              	default:
	            	break;
            }
        }
        return apellido2;
    }

    /**
     * Valor de busqueda de campo apellido2
     * @param apellido2 Valor de seteo.
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Tipo de comparador para la busqueda por campo apellido2
     * @return apellido2Comparator.
     */
    public TextComparator getApellido2Comparator() {
        return apellido2Comparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo apellido2
     * @param apellido2Comparator Valor de seteo.
     */
    public void setApellido2Comparator(TextComparator apellido2Comparator) {
        this.apellido2Comparator = apellido2Comparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getApellido2In() {
        return this.apellido2In;
    }

    /**
     * @param apellido2 Valor a agregar.
     */
    public void addApellido2In(String apellido2) {
        this.apellido2In.add(apellido2);
    }

    /**
     * Permite buscar cuando campo apellido2 es NULL
     * @return boolean.
     */
    public boolean isApellido2IsNull() {
        return apellido2IsNull;
    }

    /**
     * Permite buscar cuando campo apellido2 es NULL
     * @param apellido2IsNull Valor de seteo.
     */
    public void setApellido2IsNull(boolean apellido2IsNull) {
        this.apellido2IsNull = apellido2IsNull;
    }

    /**
     * Permite buscar cuando campo apellido2 es NOT NULL
     * @return boolean.
     */
    public boolean isApellido2IsNotNull() {
        return apellido2IsNotNull;
    }

    /**
     * Permite buscar cuando campo apellido2 es NOT NULL
     * @param apellido2IsNotNull Valor de seteo.
     */
    public void setApellido2IsNotNull(boolean apellido2IsNotNull) {
        this.apellido2IsNotNull = apellido2IsNotNull;
    }

    /**
     * Valor de busqueda de campo eliminado
     * @return String.
     */
    public String getEliminado() {
        if (eliminado != null) {
            switch (eliminadoComparator) {
	            case STARTS_WITH:
	                return eliminado + "%";
	            case CONTAINS:
	                return "%" + eliminado + "%";
	            case ENDS_WITH:
	                return "%" + eliminado;
	            case EQUALS:
                	return eliminado;
              	default:
	            	break;
            }
        }
        return eliminado;
    }

    /**
     * Valor de busqueda de campo eliminado
     * @param eliminado Valor de seteo.
     */
    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * Tipo de comparador para la busqueda por campo eliminado
     * @return eliminadoComparator.
     */
    public TextComparator getEliminadoComparator() {
        return eliminadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo eliminado
     * @param eliminadoComparator Valor de seteo.
     */
    public void setEliminadoComparator(TextComparator eliminadoComparator) {
        this.eliminadoComparator = eliminadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEliminadoIn() {
        return this.eliminadoIn;
    }

    /**
     * @param eliminado Valor a agregar.
     */
    public void addEliminadoIn(String eliminado) {
        this.eliminadoIn.add(eliminado);
    }

    /**
     * Permite buscar cuando campo eliminado es NULL
     * @return boolean.
     */
    public boolean isEliminadoIsNull() {
        return eliminadoIsNull;
    }

    /**
     * Permite buscar cuando campo eliminado es NULL
     * @param eliminadoIsNull Valor de seteo.
     */
    public void setEliminadoIsNull(boolean eliminadoIsNull) {
        this.eliminadoIsNull = eliminadoIsNull;
    }

    /**
     * Permite buscar cuando campo eliminado es NOT NULL
     * @return boolean.
     */
    public boolean isEliminadoIsNotNull() {
        return eliminadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo eliminado es NOT NULL
     * @param eliminadoIsNotNull Valor de seteo.
     */
    public void setEliminadoIsNotNull(boolean eliminadoIsNotNull) {
        this.eliminadoIsNotNull = eliminadoIsNotNull;
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
            else if (getNombreusuarioComparator() == TextComparator.IEQUALS){
            	criteria.add(Restrictions.ne(NOMBREUSUARIO, getNombreusuario()));
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

        if (getPlataformaid() != null) {
            criteria.add(Restrictions.eq(PLATAFORMAID, getPlataformaid()));
        }

        if (getPlataformaidIn().size() > 0) {
            criteria.add(Restrictions.in(PLATAFORMAID, getPlataformaidIn()));
        }

        if (isPlataformaidIsNull()) {
            criteria.add(Restrictions.isNull(PLATAFORMAID));
        }

        if (isPlataformaidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PLATAFORMAID));
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

        if (getApellido1() != null) {
            if (getApellido1Comparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(APELLIDO1, getApellido1()));
            } 
            else if (getApellido1Comparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(APELLIDO1, getApellido1()));
            }
            else if (getApellido1Comparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APELLIDO1, getApellido1())));
            }
            else {
                criteria.add(Restrictions.like(APELLIDO1, getApellido1()));
            }
        }

        if (getApellido1In().size() > 0) {
            criteria.add(Restrictions.in(APELLIDO1, getApellido1In()));
        }

        if (isApellido1IsNull()) {
            criteria.add(Restrictions.isNull(APELLIDO1));
        }

        if (isApellido1IsNotNull()) {
            criteria.add(Restrictions.isNotNull(APELLIDO1));
        }

        if (getApellido2() != null) {
            if (getApellido2Comparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(APELLIDO2, getApellido2()));
            } 
            else if (getApellido2Comparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(APELLIDO2, getApellido2()));
            }
            else if (getApellido2Comparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APELLIDO2, getApellido2())));
            }
            else {
                criteria.add(Restrictions.like(APELLIDO2, getApellido2()));
            }
        }

        if (getApellido2In().size() > 0) {
            criteria.add(Restrictions.in(APELLIDO2, getApellido2In()));
        }

        if (isApellido2IsNull()) {
            criteria.add(Restrictions.isNull(APELLIDO2));
        }

        if (isApellido2IsNotNull()) {
            criteria.add(Restrictions.isNotNull(APELLIDO2));
        }

        if (getEliminado() != null) {
            if (getEliminadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ELIMINADO, getEliminado()));
            } 
            else if (getEliminadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ELIMINADO, getEliminado()));
            }
            else if (getEliminadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ELIMINADO, getEliminado())));
            }
            else {
                criteria.add(Restrictions.like(ELIMINADO, getEliminado()));
            }
        }

        if (getEliminadoIn().size() > 0) {
            criteria.add(Restrictions.in(ELIMINADO, getEliminadoIn()));
        }

        if (isEliminadoIsNull()) {
            criteria.add(Restrictions.isNull(ELIMINADO));
        }

        if (isEliminadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ELIMINADO));
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
 
