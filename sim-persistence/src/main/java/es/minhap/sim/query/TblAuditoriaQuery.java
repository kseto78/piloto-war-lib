/*
 *
 * archivo: TblAuditoriaQuery.java
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
import es.minhap.sim.model.TblAuditoria;

/**
 * Clase con criterios de busqueda para la entidad TblAuditoria
 */
public class TblAuditoriaQuery extends AbstractHibernateQueryEntity<TblAuditoria> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String AUDITORIAID = "auditoriaid";
    public static final String OPERACION = "operacion";
    public static final String FECHA = "fecha";
    public static final String LOTEENVIOID = "loteenvioid";
    public static final String MENSAJEID = "mensajeid";
    public static final String SERVICIOID = "servicioid";
    public static final String ANEXOID = "anexoid";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    public static final String CODERROR = "coderror";
    public static final String DESCRIPCION = "descripcion";


    /**
     * Valor de busqueda de campo auditoriaid
     */
    private Long auditoriaid;

    /**
     * Lista de valores del campo auditoriaid para busquedas tipo IN
     */
    private List<Long> auditoriaidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo operacion
     */
    private String operacion;

    /**
     * Tipo de comparador para la busqueda por campo operacion
     */
    private TextComparator operacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo operacion para busquedas tipo IN
     */
    private List<String> operacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo operacion es NULL
     */
    private boolean operacionIsNull = false;

    /**
     * Permite buscar cuando campo operacion es NOT NULL
     */
    private boolean operacionIsNotNull = false;

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
     * Valor de busqueda de campo anexoid
     */
    private Long anexoid;

    /**
     * Lista de valores del campo anexoid para busquedas tipo IN
     */
    private List<Long> anexoidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo anexoid es NULL
     */
    private boolean anexoidIsNull = false;

    /**
     * Permite buscar cuando campo anexoid es NOT NULL
     */
    private boolean anexoidIsNotNull = false;

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
     * Valor de busqueda de campo coderror
     */
    private Long coderror;

    /**
     * Lista de valores del campo coderror para busquedas tipo IN
     */
    private List<Long> coderrorIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo coderror es NULL
     */
    private boolean coderrorIsNull = false;

    /**
     * Permite buscar cuando campo coderror es NOT NULL
     */
    private boolean coderrorIsNotNull = false;

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
     * Constructor default
     */
    public TblAuditoriaQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblAuditoriaQuery(Long auditoriaid) {
        setAuditoriaid(auditoriaid);
    }

    /**
     * Valor de busqueda de campo auditoriaid
     * @return Long.
     */
    public Long getAuditoriaid() {
        return auditoriaid;
    }

    /**
     * Valor de busqueda de campo auditoriaid
     * @param auditoriaid Valor de seteo.
     */
    public void setAuditoriaid(Long auditoriaid) {
        this.auditoriaid = auditoriaid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getAuditoriaidIn() {
        return this.auditoriaidIn;
    }

    /**
     * @param auditoriaid Valor a agregar.
     */
    public void addAuditoriaidIn(Long auditoriaid) {
        this.auditoriaidIn.add(auditoriaid);
    }

    /**
     * Valor de busqueda de campo operacion
     * @return String.
     */
    public String getOperacion() {
        if (operacion != null) {
            switch (operacionComparator) {
	            case STARTS_WITH:
	                return operacion + "%";
	            case CONTAINS:
	                return "%" + operacion + "%";
	            case ENDS_WITH:
	                return "%" + operacion;
	            case EQUALS:
                	return operacion;
              	default:
	            	break;
            }
        }
        return operacion;
    }

    /**
     * Valor de busqueda de campo operacion
     * @param operacion Valor de seteo.
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo operacion
     * @return operacionComparator.
     */
    public TextComparator getOperacionComparator() {
        return operacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo operacion
     * @param operacionComparator Valor de seteo.
     */
    public void setOperacionComparator(TextComparator operacionComparator) {
        this.operacionComparator = operacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getOperacionIn() {
        return this.operacionIn;
    }

    /**
     * @param operacion Valor a agregar.
     */
    public void addOperacionIn(String operacion) {
        this.operacionIn.add(operacion);
    }

    /**
     * Permite buscar cuando campo operacion es NULL
     * @return boolean.
     */
    public boolean isOperacionIsNull() {
        return operacionIsNull;
    }

    /**
     * Permite buscar cuando campo operacion es NULL
     * @param operacionIsNull Valor de seteo.
     */
    public void setOperacionIsNull(boolean operacionIsNull) {
        this.operacionIsNull = operacionIsNull;
    }

    /**
     * Permite buscar cuando campo operacion es NOT NULL
     * @return boolean.
     */
    public boolean isOperacionIsNotNull() {
        return operacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo operacion es NOT NULL
     * @param operacionIsNotNull Valor de seteo.
     */
    public void setOperacionIsNotNull(boolean operacionIsNotNull) {
        this.operacionIsNotNull = operacionIsNotNull;
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
     * Valor de busqueda de campo anexoid
     * @return Long.
     */
    public Long getAnexoid() {
        return anexoid;
    }

    /**
     * Valor de busqueda de campo anexoid
     * @param anexoid Valor de seteo.
     */
    public void setAnexoid(Long anexoid) {
        this.anexoid = anexoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getAnexoidIn() {
        return this.anexoidIn;
    }

    /**
     * @param anexoid Valor a agregar.
     */
    public void addAnexoidIn(Long anexoid) {
        this.anexoidIn.add(anexoid);
    }

    /**
     * Permite buscar cuando campo anexoid es NULL
     * @return boolean.
     */
    public boolean isAnexoidIsNull() {
        return anexoidIsNull;
    }

    /**
     * Permite buscar cuando campo anexoid es NULL
     * @param anexoidIsNull Valor de seteo.
     */
    public void setAnexoidIsNull(boolean anexoidIsNull) {
        this.anexoidIsNull = anexoidIsNull;
    }

    /**
     * Permite buscar cuando campo anexoid es NOT NULL
     * @return boolean.
     */
    public boolean isAnexoidIsNotNull() {
        return anexoidIsNotNull;
    }

    /**
     * Permite buscar cuando campo anexoid es NOT NULL
     * @param anexoidIsNotNull Valor de seteo.
     */
    public void setAnexoidIsNotNull(boolean anexoidIsNotNull) {
        this.anexoidIsNotNull = anexoidIsNotNull;
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
     * Valor de busqueda de campo coderror
     * @return Long.
     */
    public Long getCoderror() {
        return coderror;
    }

    /**
     * Valor de busqueda de campo coderror
     * @param coderror Valor de seteo.
     */
    public void setCoderror(Long coderror) {
        this.coderror = coderror;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getCoderrorIn() {
        return this.coderrorIn;
    }

    /**
     * @param coderror Valor a agregar.
     */
    public void addCoderrorIn(Long coderror) {
        this.coderrorIn.add(coderror);
    }

    /**
     * Permite buscar cuando campo coderror es NULL
     * @return boolean.
     */
    public boolean isCoderrorIsNull() {
        return coderrorIsNull;
    }

    /**
     * Permite buscar cuando campo coderror es NULL
     * @param coderrorIsNull Valor de seteo.
     */
    public void setCoderrorIsNull(boolean coderrorIsNull) {
        this.coderrorIsNull = coderrorIsNull;
    }

    /**
     * Permite buscar cuando campo coderror es NOT NULL
     * @return boolean.
     */
    public boolean isCoderrorIsNotNull() {
        return coderrorIsNotNull;
    }

    /**
     * Permite buscar cuando campo coderror es NOT NULL
     * @param coderrorIsNotNull Valor de seteo.
     */
    public void setCoderrorIsNotNull(boolean coderrorIsNotNull) {
        this.coderrorIsNotNull = coderrorIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getAuditoriaid() != null) {
            criteria.add(Restrictions.eq(AUDITORIAID, getAuditoriaid()));
        }

        if (getAuditoriaidIn().size() > 0) {
            criteria.add(Restrictions.in(AUDITORIAID, getAuditoriaidIn()));
        }

        if (getOperacion() != null) {
            if (getOperacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(OPERACION, getOperacion()));
            } 
            else if (getOperacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(OPERACION, getOperacion()));
            }
            else if (getOperacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(OPERACION, getOperacion())));
            }
            else {
                criteria.add(Restrictions.like(OPERACION, getOperacion()));
            }
        }

        if (getOperacionIn().size() > 0) {
            criteria.add(Restrictions.in(OPERACION, getOperacionIn()));
        }

        if (isOperacionIsNull()) {
            criteria.add(Restrictions.isNull(OPERACION));
        }

        if (isOperacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(OPERACION));
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

        if (getAnexoid() != null) {
            criteria.add(Restrictions.eq(ANEXOID, getAnexoid()));
        }

        if (getAnexoidIn().size() > 0) {
            criteria.add(Restrictions.in(ANEXOID, getAnexoidIn()));
        }

        if (isAnexoidIsNull()) {
            criteria.add(Restrictions.isNull(ANEXOID));
        }

        if (isAnexoidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ANEXOID));
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

        if (getCoderror() != null) {
            criteria.add(Restrictions.eq(CODERROR, getCoderror()));
        }

        if (getCoderrorIn().size() > 0) {
            criteria.add(Restrictions.in(CODERROR, getCoderrorIn()));
        }

        if (isCoderrorIsNull()) {
            criteria.add(Restrictions.isNull(CODERROR));
        }

        if (isCoderrorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODERROR));
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
 
