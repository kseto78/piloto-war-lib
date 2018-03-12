/*
 *
 * archivo: TblUsuariosQuery.java
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
import es.minhap.sim.model.TblUsuarios;

/**
 * Clase con criterios de busqueda para la entidad TblUsuarios
 */
public class TblUsuariosQuery extends AbstractHibernateQueryEntity<TblUsuarios> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String USUARIOID = "usuarioid";
    public static final String NOMBRE = "nombre";
    public static final String LOGIN = "login";
    public static final String EMAIL = "email";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String ACTIVO = "activo";
    public static final String ROLID = "rolid";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";


    /**
     * Valor de busqueda de campo usuarioid
     */
    private Long usuarioid;

    /**
     * Lista de valores del campo usuarioid para busquedas tipo IN
     */
    private List<Long> usuarioidIn = new ArrayList<Long>(0);
    
    /**
     * Lista de valores del campo usuarioid para busquedas tipo NOT IN
     */
    private List<Long> usuarioidNotIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo login
     */
    private String login;

    /**
     * Tipo de comparador para la busqueda por campo login
     */
    private TextComparator loginComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo login para busquedas tipo IN
     */
    private List<String> loginIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo login es NULL
     */
    private boolean loginIsNull = false;

    /**
     * Permite buscar cuando campo login es NOT NULL
     */
    private boolean loginIsNotNull = false;

    /**
     * Valor de busqueda de campo email
     */
    private String email;

    /**
     * Tipo de comparador para la busqueda por campo email
     */
    private TextComparator emailComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo email para busquedas tipo IN
     */
    private List<String> emailIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo email es NULL
     */
    private boolean emailIsNull = false;

    /**
     * Permite buscar cuando campo email es NOT NULL
     */
    private boolean emailIsNotNull = false;

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
     * Valor de busqueda de campo activo
     */
    private Boolean activo;

    /**
     * Permite buscar cuando campo activo es NULL
     */
    private boolean activoIsNull = false;

    /**
     * Permite buscar cuando campo activo es NOT NULL
     */
    private boolean activoIsNotNull = false;

    /**
     * Valor de busqueda de campo rolid
     */
    private Integer rolid;

    /**
     * Lista de valores del campo rolid para busquedas tipo IN
     */
    private List<Integer> rolidIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo rolid es NULL
     */
    private boolean rolidIsNull = false;

    /**
     * Permite buscar cuando campo rolid es NOT NULL
     */
    private boolean rolidIsNotNull = false;

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
     * Constructor default
     */
    public TblUsuariosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblUsuariosQuery(Long usuarioid) {
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
     * @return List<Long>.
     */
    public List<Long> getUsuarioidNotIn() {
        return this.usuarioidNotIn;
    }

    /**
     * @param usuarioid Valor a agregar.
     */
    public void addUsuarioidNotIn(Long usuarioid) {
        this.usuarioidNotIn.add(usuarioid);
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
     * Valor de busqueda de campo login
     * @return String.
     */
    public String getLogin() {
        if (login != null) {
            switch (loginComparator) {
	            case STARTS_WITH:
	                return login + "%";
	            case CONTAINS:
	                return "%" + login + "%";
	            case ENDS_WITH:
	                return "%" + login;
	            case EQUALS:
                	return login;
              	default:
	            	break;
            }
        }
        return login;
    }

    /**
     * Valor de busqueda de campo login
     * @param login Valor de seteo.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Tipo de comparador para la busqueda por campo login
     * @return loginComparator.
     */
    public TextComparator getLoginComparator() {
        return loginComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo login
     * @param loginComparator Valor de seteo.
     */
    public void setLoginComparator(TextComparator loginComparator) {
        this.loginComparator = loginComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getLoginIn() {
        return this.loginIn;
    }

    /**
     * @param login Valor a agregar.
     */
    public void addLoginIn(String login) {
        this.loginIn.add(login);
    }

    /**
     * Permite buscar cuando campo login es NULL
     * @return boolean.
     */
    public boolean isLoginIsNull() {
        return loginIsNull;
    }

    /**
     * Permite buscar cuando campo login es NULL
     * @param loginIsNull Valor de seteo.
     */
    public void setLoginIsNull(boolean loginIsNull) {
        this.loginIsNull = loginIsNull;
    }

    /**
     * Permite buscar cuando campo login es NOT NULL
     * @return boolean.
     */
    public boolean isLoginIsNotNull() {
        return loginIsNotNull;
    }

    /**
     * Permite buscar cuando campo login es NOT NULL
     * @param loginIsNotNull Valor de seteo.
     */
    public void setLoginIsNotNull(boolean loginIsNotNull) {
        this.loginIsNotNull = loginIsNotNull;
    }

    /**
     * Valor de busqueda de campo email
     * @return String.
     */
    public String getEmail() {
        if (email != null) {
            switch (emailComparator) {
	            case STARTS_WITH:
	                return email + "%";
	            case CONTAINS:
	                return "%" + email + "%";
	            case ENDS_WITH:
	                return "%" + email;
	            case EQUALS:
                	return email;
              	default:
	            	break;
            }
        }
        return email;
    }

    /**
     * Valor de busqueda de campo email
     * @param email Valor de seteo.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Tipo de comparador para la busqueda por campo email
     * @return emailComparator.
     */
    public TextComparator getEmailComparator() {
        return emailComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo email
     * @param emailComparator Valor de seteo.
     */
    public void setEmailComparator(TextComparator emailComparator) {
        this.emailComparator = emailComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEmailIn() {
        return this.emailIn;
    }

    /**
     * @param email Valor a agregar.
     */
    public void addEmailIn(String email) {
        this.emailIn.add(email);
    }

    /**
     * Permite buscar cuando campo email es NULL
     * @return boolean.
     */
    public boolean isEmailIsNull() {
        return emailIsNull;
    }

    /**
     * Permite buscar cuando campo email es NULL
     * @param emailIsNull Valor de seteo.
     */
    public void setEmailIsNull(boolean emailIsNull) {
        this.emailIsNull = emailIsNull;
    }

    /**
     * Permite buscar cuando campo email es NOT NULL
     * @return boolean.
     */
    public boolean isEmailIsNotNull() {
        return emailIsNotNull;
    }

    /**
     * Permite buscar cuando campo email es NOT NULL
     * @param emailIsNotNull Valor de seteo.
     */
    public void setEmailIsNotNull(boolean emailIsNotNull) {
        this.emailIsNotNull = emailIsNotNull;
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
     * Valor de busqueda de campo activo
     * @return Boolean.
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * Valor de busqueda de campo activo
     * @param activo Valor de seteo.
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * Permite buscar cuando campo activo es NULL
     * @return boolean.
     */
    public boolean isActivoIsNull() {
        return activoIsNull;
    }

    /**
     * Permite buscar cuando campo activo es NULL
     * @param activoIsNull Valor de seteo.
     */
    public void setActivoIsNull(boolean activoIsNull) {
        this.activoIsNull = activoIsNull;
    }

    /**
     * Permite buscar cuando campo activo es NOT NULL
     * @return boolean.
     */
    public boolean isActivoIsNotNull() {
        return activoIsNotNull;
    }

    /**
     * Permite buscar cuando campo activo es NOT NULL
     * @param activoIsNotNull Valor de seteo.
     */
    public void setActivoIsNotNull(boolean activoIsNotNull) {
        this.activoIsNotNull = activoIsNotNull;
    }

    /**
     * Valor de busqueda de campo rolid
     * @return Integer.
     */
    public Integer getRolid() {
        return rolid;
    }

    /**
     * Valor de busqueda de campo rolid
     * @param rolid Valor de seteo.
     */
    public void setRolid(Integer rolid) {
        this.rolid = rolid;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getRolidIn() {
        return this.rolidIn;
    }

    /**
     * @param rolid Valor a agregar.
     */
    public void addRolidIn(Integer rolid) {
        this.rolidIn.add(rolid);
    }

    /**
     * Permite buscar cuando campo rolid es NULL
     * @return boolean.
     */
    public boolean isRolidIsNull() {
        return rolidIsNull;
    }

    /**
     * Permite buscar cuando campo rolid es NULL
     * @param rolidIsNull Valor de seteo.
     */
    public void setRolidIsNull(boolean rolidIsNull) {
        this.rolidIsNull = rolidIsNull;
    }

    /**
     * Permite buscar cuando campo rolid es NOT NULL
     * @return boolean.
     */
    public boolean isRolidIsNotNull() {
        return rolidIsNotNull;
    }

    /**
     * Permite buscar cuando campo rolid es NOT NULL
     * @param rolidIsNotNull Valor de seteo.
     */
    public void setRolidIsNotNull(boolean rolidIsNotNull) {
        this.rolidIsNotNull = rolidIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getUsuarioid() != null) {
            criteria.add(Restrictions.eq(USUARIOID, getUsuarioid()));
        }

        if (getUsuarioidIn().size() > 0) {
            criteria.add(Restrictions.in(USUARIOID, getUsuarioidIn()));
        }
        
        if (getUsuarioidNotIn().size() > 0) {
            criteria.add(Restrictions.not(Restrictions.in(USUARIOID, getUsuarioidNotIn())));
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

        if (getLogin() != null) {
            if (getLoginComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(LOGIN, getLogin()));
            } 
            else if (getLoginComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(LOGIN, getLogin()));
            }
            else if (getLoginComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(LOGIN, getLogin())));
            }
            else {
                criteria.add(Restrictions.like(LOGIN, getLogin()));
            }
        }

        if (getLoginIn().size() > 0) {
            criteria.add(Restrictions.in(LOGIN, getLoginIn()));
        }

        if (isLoginIsNull()) {
            criteria.add(Restrictions.isNull(LOGIN));
        }

        if (isLoginIsNotNull()) {
            criteria.add(Restrictions.isNotNull(LOGIN));
        }

        if (getEmail() != null) {
            if (getEmailComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(EMAIL, getEmail()));
            } 
            else if (getEmailComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(EMAIL, getEmail()));
            }
            else if (getEmailComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(EMAIL, getEmail())));
            }
            else {
                criteria.add(Restrictions.like(EMAIL, getEmail()));
            }
        }

        if (getEmailIn().size() > 0) {
            criteria.add(Restrictions.in(EMAIL, getEmailIn()));
        }

        if (isEmailIsNull()) {
            criteria.add(Restrictions.isNull(EMAIL));
        }

        if (isEmailIsNotNull()) {
            criteria.add(Restrictions.isNotNull(EMAIL));
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

        if (getActivo() != null) {
            criteria.add(Restrictions.eq(ACTIVO, getActivo()));
        }

        if (isActivoIsNull()) {
            criteria.add(Restrictions.isNull(ACTIVO));
        }

        if (isActivoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ACTIVO));
        }

        if (getRolid() != null) {
            criteria.add(Restrictions.eq(ROLID, getRolid()));
        }

        if (getRolidIn().size() > 0) {
            criteria.add(Restrictions.in(ROLID, getRolidIn()));
        }

        if (isRolidIsNull()) {
            criteria.add(Restrictions.isNull(ROLID));
        }

        if (isRolidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ROLID));
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
 
