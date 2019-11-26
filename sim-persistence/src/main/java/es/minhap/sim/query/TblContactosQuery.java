/*
 *
 * archivo: TblContactosQuery.java
 *
 * Proyecto: Administracion PAG
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
import es.minhap.sim.model.TblContactos;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;

/**
 * Clase con criterios de busqueda para la entidad TblContactos
 */
public class TblContactosQuery extends AbstractHibernateQueryEntity<TblContactos> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String CONTACTOID = "contactoid";
    public static final String ORGANISMO = "organismo";
    public static final String APLICACIONID = "aplicacionid";
    public static final String SERVICIOID = "servicioid";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDOS = "apellidos";
    public static final String EMAIL = "email";
    public static final String TELEFONO = "telefono";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String ELIMINADO = "eliminado";


    /**
     * Valor de busqueda de campo contactoid
     */
    private Long contactoid;

    /**
     * Lista de valores del campo contactoid para busquedas tipo IN
     */
    private List<Long> contactoidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo organismo
     */
    private String organismo;

    /**
     * Tipo de comparador para la busqueda por campo organismo
     */
    private TextComparator organismoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo organismo para busquedas tipo IN
     */
    private List<String> organismoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo organismo es NULL
     */
    private boolean organismoIsNull = false;

    /**
     * Permite buscar cuando campo organismo es NOT NULL
     */
    private boolean organismoIsNotNull = false;

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
     * Valor de busqueda de campo apellidos
     */
    private String apellidos;

    /**
     * Tipo de comparador para la busqueda por campo apellidos
     */
    private TextComparator apellidosComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo apellidos para busquedas tipo IN
     */
    private List<String> apellidosIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo apellidos es NULL
     */
    private boolean apellidosIsNull = false;

    /**
     * Permite buscar cuando campo apellidos es NOT NULL
     */
    private boolean apellidosIsNotNull = false;

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
    public TblContactosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblContactosQuery(Long contactoid) {
        setContactoid(contactoid);
    }

    /**
     * Valor de busqueda de campo contactoid
     * @return Long.
     */
    public Long getContactoid() {
        return contactoid;
    }

    /**
     * Valor de busqueda de campo contactoid
     * @param contactoid Valor de seteo.
     */
    public void setContactoid(Long contactoid) {
        this.contactoid = contactoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getContactoidIn() {
        return this.contactoidIn;
    }

    /**
     * @param contactoid Valor a agregar.
     */
    public void addContactoidIn(Long contactoid) {
        this.contactoidIn.add(contactoid);
    }

    /**
     * Valor de busqueda de campo organismo
     * @return String.
     */
    public String getOrganismo() {
        if (organismo != null) {
            switch (organismoComparator) {
	            case STARTS_WITH:
	                return organismo + "%";
	            case CONTAINS:
	                return "%" + organismo + "%";
	            case ENDS_WITH:
	                return "%" + organismo;
	            case EQUALS:
                	return organismo;
              	default:
	            	break;
            }
        }
        return organismo;
    }

    /**
     * Valor de busqueda de campo organismo
     * @param organismo Valor de seteo.
     */
    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    /**
     * Tipo de comparador para la busqueda por campo organismo
     * @return organismoComparator.
     */
    public TextComparator getOrganismoComparator() {
        return organismoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo organismo
     * @param organismoComparator Valor de seteo.
     */
    public void setOrganismoComparator(TextComparator organismoComparator) {
        this.organismoComparator = organismoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getOrganismoIn() {
        return this.organismoIn;
    }

    /**
     * @param organismo Valor a agregar.
     */
    public void addOrganismoIn(String organismo) {
        this.organismoIn.add(organismo);
    }

    /**
     * Permite buscar cuando campo organismo es NULL
     * @return boolean.
     */
    public boolean isOrganismoIsNull() {
        return organismoIsNull;
    }

    /**
     * Permite buscar cuando campo organismo es NULL
     * @param organismoIsNull Valor de seteo.
     */
    public void setOrganismoIsNull(boolean organismoIsNull) {
        this.organismoIsNull = organismoIsNull;
    }

    /**
     * Permite buscar cuando campo organismo es NOT NULL
     * @return boolean.
     */
    public boolean isOrganismoIsNotNull() {
        return organismoIsNotNull;
    }

    /**
     * Permite buscar cuando campo organismo es NOT NULL
     * @param organismoIsNotNull Valor de seteo.
     */
    public void setOrganismoIsNotNull(boolean organismoIsNotNull) {
        this.organismoIsNotNull = organismoIsNotNull;
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
     * Valor de busqueda de campo apellidos
     * @return String.
     */
    public String getApellidos() {
        if (apellidos != null) {
            switch (apellidosComparator) {
	            case STARTS_WITH:
	                return apellidos + "%";
	            case CONTAINS:
	                return "%" + apellidos + "%";
	            case ENDS_WITH:
	                return "%" + apellidos;
	            case EQUALS:
                	return apellidos;
              	default:
	            	break;
            }
        }
        return apellidos;
    }

    /**
     * Valor de busqueda de campo apellidos
     * @param apellidos Valor de seteo.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Tipo de comparador para la busqueda por campo apellidos
     * @return apellidosComparator.
     */
    public TextComparator getApellidosComparator() {
        return apellidosComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo apellidos
     * @param apellidosComparator Valor de seteo.
     */
    public void setApellidosComparator(TextComparator apellidosComparator) {
        this.apellidosComparator = apellidosComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getApellidosIn() {
        return this.apellidosIn;
    }

    /**
     * @param apellidos Valor a agregar.
     */
    public void addApellidosIn(String apellidos) {
        this.apellidosIn.add(apellidos);
    }

    /**
     * Permite buscar cuando campo apellidos es NULL
     * @return boolean.
     */
    public boolean isApellidosIsNull() {
        return apellidosIsNull;
    }

    /**
     * Permite buscar cuando campo apellidos es NULL
     * @param apellidosIsNull Valor de seteo.
     */
    public void setApellidosIsNull(boolean apellidosIsNull) {
        this.apellidosIsNull = apellidosIsNull;
    }

    /**
     * Permite buscar cuando campo apellidos es NOT NULL
     * @return boolean.
     */
    public boolean isApellidosIsNotNull() {
        return apellidosIsNotNull;
    }

    /**
     * Permite buscar cuando campo apellidos es NOT NULL
     * @param apellidosIsNotNull Valor de seteo.
     */
    public void setApellidosIsNotNull(boolean apellidosIsNotNull) {
        this.apellidosIsNotNull = apellidosIsNotNull;
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

        if (getContactoid() != null) {
            criteria.add(Restrictions.eq(CONTACTOID, getContactoid()));
        }

        if (getContactoidIn().size() > 0) {
            criteria.add(Restrictions.in(CONTACTOID, getContactoidIn()));
        }

        if (getOrganismo() != null) {
            if (getOrganismoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ORGANISMO, getOrganismo()));
            } 
            else if (getOrganismoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ORGANISMO, getOrganismo()));
            }
            else if (getOrganismoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ORGANISMO, getOrganismo())));
            }
            else {
                criteria.add(Restrictions.like(ORGANISMO, getOrganismo()));
            }
        }

        if (getOrganismoIn().size() > 0) {
            criteria.add(Restrictions.in(ORGANISMO, getOrganismoIn()));
        }

        if (isOrganismoIsNull()) {
            criteria.add(Restrictions.isNull(ORGANISMO));
        }

        if (isOrganismoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ORGANISMO));
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

        if (getApellidos() != null) {
            if (getApellidosComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(APELLIDOS, getApellidos()));
            } 
            else if (getApellidosComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(APELLIDOS, getApellidos()));
            }
            else if (getApellidosComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APELLIDOS, getApellidos())));
            }
            else {
                criteria.add(Restrictions.like(APELLIDOS, getApellidos()));
            }
        }

        if (getApellidosIn().size() > 0) {
            criteria.add(Restrictions.in(APELLIDOS, getApellidosIn()));
        }

        if (isApellidosIsNull()) {
            criteria.add(Restrictions.isNull(APELLIDOS));
        }

        if (isApellidosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(APELLIDOS));
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
 
