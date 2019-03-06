/*
 *
 * archivo: TblAplicacionesQuery.java
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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblAplicaciones;

/**
 * Clase con criterios de busqueda para la entidad TblAplicaciones
 */
public class TblAplicacionesQuery extends AbstractHibernateQueryEntity<TblAplicaciones> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String APLICACIONID = "aplicacionid";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String ACTIVO = "activo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    public static final String ELIMINADO = "eliminado";
    public static final String RESPFUNCIONALNOMBRE = "respFuncionalNombre";
    public static final String RESPFUNCIONALEMAIL = "respFuncionalEmail";
    public static final String RESPTECNICONOMBRE = "respTecnicoNombre";
    public static final String RESPTECNICOEMAIL = "respTecnicoEmail";
    public static final String SMSMAXIMOS = "smsMaximos";
    public static final String SMSENVIADOSDIA = "smsEnviadosDia";
    public static final String SMSFECHAULTIMO = "smsFechaUltimo";


    /**
     * Valor de busqueda de campo aplicacionid
     */
    private Long aplicacionid;

    /**
     * Lista de valores del campo aplicacionid para busquedas tipo IN
     */
    private List<Long> aplicacionidIn = new ArrayList<Long>(0);
    
    /**
     * Lista de valores del campo aplicacionid para busquedas tipo NOT IN
     */
    private List<Long> aplicacionidNOTIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo respFuncionalNombre
     */
    private String respFuncionalNombre;

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalNombre
     */
    private TextComparator respFuncionalNombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo respFuncionalNombre para busquedas tipo IN
     */
    private List<String> respFuncionalNombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo respFuncionalNombre es NULL
     */
    private boolean respFuncionalNombreIsNull = false;

    /**
     * Permite buscar cuando campo respFuncionalNombre es NOT NULL
     */
    private boolean respFuncionalNombreIsNotNull = false;

    /**
     * Valor de busqueda de campo respFuncionalEmail
     */
    private String respFuncionalEmail;

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalEmail
     */
    private TextComparator respFuncionalEmailComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo respFuncionalEmail para busquedas tipo IN
     */
    private List<String> respFuncionalEmailIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo respFuncionalEmail es NULL
     */
    private boolean respFuncionalEmailIsNull = false;

    /**
     * Permite buscar cuando campo respFuncionalEmail es NOT NULL
     */
    private boolean respFuncionalEmailIsNotNull = false;

    /**
     * Valor de busqueda de campo respTecnicoNombre
     */
    private String respTecnicoNombre;

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoNombre
     */
    private TextComparator respTecnicoNombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo respTecnicoNombre para busquedas tipo IN
     */
    private List<String> respTecnicoNombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo respTecnicoNombre es NULL
     */
    private boolean respTecnicoNombreIsNull = false;

    /**
     * Permite buscar cuando campo respTecnicoNombre es NOT NULL
     */
    private boolean respTecnicoNombreIsNotNull = false;

    /**
     * Valor de busqueda de campo respTecnicoEmail
     */
    private String respTecnicoEmail;

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoEmail
     */
    private TextComparator respTecnicoEmailComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo respTecnicoEmail para busquedas tipo IN
     */
    private List<String> respTecnicoEmailIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo respTecnicoEmail es NULL
     */
    private boolean respTecnicoEmailIsNull = false;

    /**
     * Permite buscar cuando campo respTecnicoEmail es NOT NULL
     */
    private boolean respTecnicoEmailIsNotNull = false;

    /**
     * Valor de busqueda de campo smsMaximos
     */
    private Integer smsMaximos;

    /**
     * Lista de valores del campo smsMaximos para busquedas tipo IN
     */
    private List<Integer> smsMaximosIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo smsMaximos es NULL
     */
    private boolean smsMaximosIsNull = false;

    /**
     * Permite buscar cuando campo smsMaximos es NOT NULL
     */
    private boolean smsMaximosIsNotNull = false;

    /**
     * Valor de busqueda de campo smsEnviadosDia
     */
    private Integer smsEnviadosDia;

    /**
     * Lista de valores del campo smsEnviadosDia para busquedas tipo IN
     */
    private List<Integer> smsEnviadosDiaIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo smsEnviadosDia es NULL
     */
    private boolean smsEnviadosDiaIsNull = false;

    /**
     * Permite buscar cuando campo smsEnviadosDia es NOT NULL
     */
    private boolean smsEnviadosDiaIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha smsFechaUltimo
     */
    private Date smsFechaUltimoMin;

    /**
     * Valor superior de rango de busqueda de fecha smsFechaUltimo
     */
    private Date smsFechaUltimoMax;

    /**
     * Permite buscar cuando campo smsFechaUltimo es NULL
     */
    private boolean smsFechaUltimoIsNull = false;

    /**
     * Permite buscar cuando campo smsFechaUltimo es NOT NULL
     */
    private boolean smsFechaUltimoIsNotNull = false;

    /**
     * Constructor default
     */
    public TblAplicacionesQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblAplicacionesQuery(Long aplicacionid) {
        setAplicacionid(aplicacionid);
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
     * @return List<Long>.
     */
    public List<Long> getAplicacionidNOTIn() {
        return this.aplicacionidNOTIn;
    }

    /**
     * @param aplicacionid Valor a agregar.
     */
    public void addAplicacionidIn(Long aplicacionid) {
        this.aplicacionidIn.add(aplicacionid);
    }
    
    /**
     * @param aplicacionid Valor a agregar.
     */
    public void addAplicacionidNOTIn(Long aplicacionid) {
        this.aplicacionidNOTIn.add(aplicacionid);
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
     * Valor de busqueda de campo respFuncionalNombre
     * @return String.
     */
    public String getRespFuncionalNombre() {
        if (respFuncionalNombre != null) {
            switch (respFuncionalNombreComparator) {
	            case STARTS_WITH:
	                return respFuncionalNombre + "%";
	            case CONTAINS:
	                return "%" + respFuncionalNombre + "%";
	            case ENDS_WITH:
	                return "%" + respFuncionalNombre;
	            case EQUALS:
                	return respFuncionalNombre;
              	default:
	            	break;
            }
        }
        return respFuncionalNombre;
    }

    /**
     * Valor de busqueda de campo respFuncionalNombre
     * @param respFuncionalNombre Valor de seteo.
     */
    public void setRespFuncionalNombre(String respFuncionalNombre) {
        this.respFuncionalNombre = respFuncionalNombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalNombre
     * @return respFuncionalNombreComparator.
     */
    public TextComparator getRespFuncionalNombreComparator() {
        return respFuncionalNombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalNombre
     * @param respFuncionalNombreComparator Valor de seteo.
     */
    public void setRespFuncionalNombreComparator(TextComparator respFuncionalNombreComparator) {
        this.respFuncionalNombreComparator = respFuncionalNombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getRespFuncionalNombreIn() {
        return this.respFuncionalNombreIn;
    }

    /**
     * @param respFuncionalNombre Valor a agregar.
     */
    public void addRespFuncionalNombreIn(String respFuncionalNombre) {
        this.respFuncionalNombreIn.add(respFuncionalNombre);
    }

    /**
     * Permite buscar cuando campo respFuncionalNombre es NULL
     * @return boolean.
     */
    public boolean isRespFuncionalNombreIsNull() {
        return respFuncionalNombreIsNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalNombre es NULL
     * @param respFuncionalNombreIsNull Valor de seteo.
     */
    public void setRespFuncionalNombreIsNull(boolean respFuncionalNombreIsNull) {
        this.respFuncionalNombreIsNull = respFuncionalNombreIsNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalNombre es NOT NULL
     * @return boolean.
     */
    public boolean isRespFuncionalNombreIsNotNull() {
        return respFuncionalNombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalNombre es NOT NULL
     * @param respFuncionalNombreIsNotNull Valor de seteo.
     */
    public void setRespFuncionalNombreIsNotNull(boolean respFuncionalNombreIsNotNull) {
        this.respFuncionalNombreIsNotNull = respFuncionalNombreIsNotNull;
    }

    /**
     * Valor de busqueda de campo respFuncionalEmail
     * @return String.
     */
    public String getRespFuncionalEmail() {
        if (respFuncionalEmail != null) {
            switch (respFuncionalEmailComparator) {
	            case STARTS_WITH:
	                return respFuncionalEmail + "%";
	            case CONTAINS:
	                return "%" + respFuncionalEmail + "%";
	            case ENDS_WITH:
	                return "%" + respFuncionalEmail;
	            case EQUALS:
                	return respFuncionalEmail;
              	default:
	            	break;
            }
        }
        return respFuncionalEmail;
    }

    /**
     * Valor de busqueda de campo respFuncionalEmail
     * @param respFuncionalEmail Valor de seteo.
     */
    public void setRespFuncionalEmail(String respFuncionalEmail) {
        this.respFuncionalEmail = respFuncionalEmail;
    }

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalEmail
     * @return respFuncionalEmailComparator.
     */
    public TextComparator getRespFuncionalEmailComparator() {
        return respFuncionalEmailComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalEmail
     * @param respFuncionalEmailComparator Valor de seteo.
     */
    public void setRespFuncionalEmailComparator(TextComparator respFuncionalEmailComparator) {
        this.respFuncionalEmailComparator = respFuncionalEmailComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getRespFuncionalEmailIn() {
        return this.respFuncionalEmailIn;
    }

    /**
     * @param respFuncionalEmail Valor a agregar.
     */
    public void addRespFuncionalEmailIn(String respFuncionalEmail) {
        this.respFuncionalEmailIn.add(respFuncionalEmail);
    }

    /**
     * Permite buscar cuando campo respFuncionalEmail es NULL
     * @return boolean.
     */
    public boolean isRespFuncionalEmailIsNull() {
        return respFuncionalEmailIsNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalEmail es NULL
     * @param respFuncionalEmailIsNull Valor de seteo.
     */
    public void setRespFuncionalEmailIsNull(boolean respFuncionalEmailIsNull) {
        this.respFuncionalEmailIsNull = respFuncionalEmailIsNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalEmail es NOT NULL
     * @return boolean.
     */
    public boolean isRespFuncionalEmailIsNotNull() {
        return respFuncionalEmailIsNotNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalEmail es NOT NULL
     * @param respFuncionalEmailIsNotNull Valor de seteo.
     */
    public void setRespFuncionalEmailIsNotNull(boolean respFuncionalEmailIsNotNull) {
        this.respFuncionalEmailIsNotNull = respFuncionalEmailIsNotNull;
    }

    /**
     * Valor de busqueda de campo respTecnicoNombre
     * @return String.
     */
    public String getRespTecnicoNombre() {
        if (respTecnicoNombre != null) {
            switch (respTecnicoNombreComparator) {
	            case STARTS_WITH:
	                return respTecnicoNombre + "%";
	            case CONTAINS:
	                return "%" + respTecnicoNombre + "%";
	            case ENDS_WITH:
	                return "%" + respTecnicoNombre;
	            case EQUALS:
                	return respTecnicoNombre;
              	default:
	            	break;
            }
        }
        return respTecnicoNombre;
    }

    /**
     * Valor de busqueda de campo respTecnicoNombre
     * @param respTecnicoNombre Valor de seteo.
     */
    public void setRespTecnicoNombre(String respTecnicoNombre) {
        this.respTecnicoNombre = respTecnicoNombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoNombre
     * @return respTecnicoNombreComparator.
     */
    public TextComparator getRespTecnicoNombreComparator() {
        return respTecnicoNombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoNombre
     * @param respTecnicoNombreComparator Valor de seteo.
     */
    public void setRespTecnicoNombreComparator(TextComparator respTecnicoNombreComparator) {
        this.respTecnicoNombreComparator = respTecnicoNombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getRespTecnicoNombreIn() {
        return this.respTecnicoNombreIn;
    }

    /**
     * @param respTecnicoNombre Valor a agregar.
     */
    public void addRespTecnicoNombreIn(String respTecnicoNombre) {
        this.respTecnicoNombreIn.add(respTecnicoNombre);
    }

    /**
     * Permite buscar cuando campo respTecnicoNombre es NULL
     * @return boolean.
     */
    public boolean isRespTecnicoNombreIsNull() {
        return respTecnicoNombreIsNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoNombre es NULL
     * @param respTecnicoNombreIsNull Valor de seteo.
     */
    public void setRespTecnicoNombreIsNull(boolean respTecnicoNombreIsNull) {
        this.respTecnicoNombreIsNull = respTecnicoNombreIsNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoNombre es NOT NULL
     * @return boolean.
     */
    public boolean isRespTecnicoNombreIsNotNull() {
        return respTecnicoNombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoNombre es NOT NULL
     * @param respTecnicoNombreIsNotNull Valor de seteo.
     */
    public void setRespTecnicoNombreIsNotNull(boolean respTecnicoNombreIsNotNull) {
        this.respTecnicoNombreIsNotNull = respTecnicoNombreIsNotNull;
    }

    /**
     * Valor de busqueda de campo respTecnicoEmail
     * @return String.
     */
    public String getRespTecnicoEmail() {
        if (respTecnicoEmail != null) {
            switch (respTecnicoEmailComparator) {
	            case STARTS_WITH:
	                return respTecnicoEmail + "%";
	            case CONTAINS:
	                return "%" + respTecnicoEmail + "%";
	            case ENDS_WITH:
	                return "%" + respTecnicoEmail;
	            case EQUALS:
                	return respTecnicoEmail;
              	default:
	            	break;
            }
        }
        return respTecnicoEmail;
    }

    /**
     * Valor de busqueda de campo respTecnicoEmail
     * @param respTecnicoEmail Valor de seteo.
     */
    public void setRespTecnicoEmail(String respTecnicoEmail) {
        this.respTecnicoEmail = respTecnicoEmail;
    }

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoEmail
     * @return respTecnicoEmailComparator.
     */
    public TextComparator getRespTecnicoEmailComparator() {
        return respTecnicoEmailComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoEmail
     * @param respTecnicoEmailComparator Valor de seteo.
     */
    public void setRespTecnicoEmailComparator(TextComparator respTecnicoEmailComparator) {
        this.respTecnicoEmailComparator = respTecnicoEmailComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getRespTecnicoEmailIn() {
        return this.respTecnicoEmailIn;
    }

    /**
     * @param respTecnicoEmail Valor a agregar.
     */
    public void addRespTecnicoEmailIn(String respTecnicoEmail) {
        this.respTecnicoEmailIn.add(respTecnicoEmail);
    }

    /**
     * Permite buscar cuando campo respTecnicoEmail es NULL
     * @return boolean.
     */
    public boolean isRespTecnicoEmailIsNull() {
        return respTecnicoEmailIsNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoEmail es NULL
     * @param respTecnicoEmailIsNull Valor de seteo.
     */
    public void setRespTecnicoEmailIsNull(boolean respTecnicoEmailIsNull) {
        this.respTecnicoEmailIsNull = respTecnicoEmailIsNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoEmail es NOT NULL
     * @return boolean.
     */
    public boolean isRespTecnicoEmailIsNotNull() {
        return respTecnicoEmailIsNotNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoEmail es NOT NULL
     * @param respTecnicoEmailIsNotNull Valor de seteo.
     */
    public void setRespTecnicoEmailIsNotNull(boolean respTecnicoEmailIsNotNull) {
        this.respTecnicoEmailIsNotNull = respTecnicoEmailIsNotNull;
    }

    /**
     * Valor de busqueda de campo smsMaximos
     * @return Integer.
     */
    public Integer getSmsMaximos() {
        return smsMaximos;
    }

    /**
     * Valor de busqueda de campo smsMaximos
     * @param smsMaximos Valor de seteo.
     */
    public void setSmsMaximos(Integer smsMaximos) {
        this.smsMaximos = smsMaximos;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getSmsMaximosIn() {
        return this.smsMaximosIn;
    }

    /**
     * @param smsMaximos Valor a agregar.
     */
    public void addSmsMaximosIn(Integer smsMaximos) {
        this.smsMaximosIn.add(smsMaximos);
    }

    /**
     * Permite buscar cuando campo smsMaximos es NULL
     * @return boolean.
     */
    public boolean isSmsMaximosIsNull() {
        return smsMaximosIsNull;
    }

    /**
     * Permite buscar cuando campo smsMaximos es NULL
     * @param smsMaximosIsNull Valor de seteo.
     */
    public void setSmsMaximosIsNull(boolean smsMaximosIsNull) {
        this.smsMaximosIsNull = smsMaximosIsNull;
    }

    /**
     * Permite buscar cuando campo smsMaximos es NOT NULL
     * @return boolean.
     */
    public boolean isSmsMaximosIsNotNull() {
        return smsMaximosIsNotNull;
    }

    /**
     * Permite buscar cuando campo smsMaximos es NOT NULL
     * @param smsMaximosIsNotNull Valor de seteo.
     */
    public void setSmsMaximosIsNotNull(boolean smsMaximosIsNotNull) {
        this.smsMaximosIsNotNull = smsMaximosIsNotNull;
    }

    /**
     * Valor de busqueda de campo smsEnviadosDia
     * @return Number.
     */
    public Integer getSmsEnviadosDia() {
        return smsEnviadosDia;
    }

    /**
     * Valor de busqueda de campo smsEnviadosDia
     * @param smsEnviadosDia Valor de seteo.
     */
    public void setSmsEnviadosDia(Integer smsEnviadosDia) {
        this.smsEnviadosDia = smsEnviadosDia;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getSmsEnviadosDiaIn() {
        return this.smsEnviadosDiaIn;
    }

    /**
     * @param smsEnviadosDia Valor a agregar.
     */
    public void addSmsEnviadosDiaIn(Integer smsEnviadosDia) {
        this.smsEnviadosDiaIn.add(smsEnviadosDia);
    }

    /**
     * Permite buscar cuando campo smsEnviadosDia es NULL
     * @return boolean.
     */
    public boolean isSmsEnviadosDiaIsNull() {
        return smsEnviadosDiaIsNull;
    }

    /**
     * Permite buscar cuando campo smsEnviadosDia es NULL
     * @param smsEnviadosDiaIsNull Valor de seteo.
     */
    public void setSmsEnviadosDiaIsNull(boolean smsEnviadosDiaIsNull) {
        this.smsEnviadosDiaIsNull = smsEnviadosDiaIsNull;
    }

    /**
     * Permite buscar cuando campo smsEnviadosDia es NOT NULL
     * @return boolean.
     */
    public boolean isSmsEnviadosDiaIsNotNull() {
        return smsEnviadosDiaIsNotNull;
    }

    /**
     * Permite buscar cuando campo smsEnviadosDia es NOT NULL
     * @param smsEnviadosDiaIsNotNull Valor de seteo.
     */
    public void setSmsEnviadosDiaIsNotNull(boolean smsEnviadosDiaIsNotNull) {
        this.smsEnviadosDiaIsNotNull = smsEnviadosDiaIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha smsFechaUltimo
     * @return ${field.getName()}Min.
     */
    public Date getSmsFechaUltimoMin() {
        if (smsFechaUltimoMin != null) {
            return DateUtil.toDayBegin(smsFechaUltimoMin);
        }
        return smsFechaUltimoMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha smsFechaUltimo
     * @param smsFechaUltimoMin Valor de seteo.
     */
    public void setSmsFechaUltimoMin(Date smsFechaUltimoMin) {
        this.smsFechaUltimoMin = smsFechaUltimoMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha smsFechaUltimo
     * @return smsFechaUltimoMax.
     */
    public Date getSmsFechaUltimoMax() {
        if (smsFechaUltimoMax != null) {
            return DateUtil.toDayEnd(smsFechaUltimoMax);
        }
        return smsFechaUltimoMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha smsFechaUltimo
     * @param smsFechaUltimoMax Valor de seteo.
     */
    public void setSmsFechaUltimoMax(Date smsFechaUltimoMax) {
        this.smsFechaUltimoMax = smsFechaUltimoMax;
    }

    /**
     * Permite buscar cuando campo smsFechaUltimo es NULL
     * @return boolean.
     */
    public boolean isSmsFechaUltimoIsNull() {
        return smsFechaUltimoIsNull;
    }

    /**
     * Permite buscar cuando campo smsFechaUltimo es NULL
     * @param smsFechaUltimoIsNull Valor de seteo.
     */
    public void setSmsFechaUltimoIsNull(boolean smsFechaUltimoIsNull) {
        this.smsFechaUltimoIsNull = smsFechaUltimoIsNull;
    }

    /**
     * Permite buscar cuando campo smsFechaUltimo es NOT NULL
     * @return boolean.
     */
    public boolean isSmsFechaUltimoIsNotNull() {
        return smsFechaUltimoIsNotNull;
    }

    /**
     * Permite buscar cuando campo smsFechaUltimo es NOT NULL
     * @param smsFechaUltimoIsNotNull Valor de seteo.
     */
    public void setSmsFechaUltimoIsNotNull(boolean smsFechaUltimoIsNotNull) {
        this.smsFechaUltimoIsNotNull = smsFechaUltimoIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getAplicacionid() != null) {
            criteria.add(Restrictions.eq(APLICACIONID, getAplicacionid()));
        }

        if (!getAplicacionidIn().isEmpty()) {
            criteria.add(Restrictions.in(APLICACIONID, getAplicacionidIn()));
        }
        
        if (!getAplicacionidNOTIn().isEmpty()) {
            criteria.add(Restrictions.not(Restrictions.in(APLICACIONID, getAplicacionidNOTIn())));
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

        if (getActivo() != null) {
            criteria.add(Restrictions.eq(ACTIVO, getActivo()));
        }

        if (isActivoIsNull()) {
            criteria.add(Restrictions.isNull(ACTIVO));
        }

        if (isActivoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ACTIVO));
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
        	Criterion c1 = Restrictions.isNull(ELIMINADO);
        	Criterion c2 = Restrictions.eq("eliminado", "N");
            criteria.add(Restrictions.or(c1,c2));
        }

        if (isEliminadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ELIMINADO));
        }

        if (getRespFuncionalNombre() != null) {
            if (getRespFuncionalNombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(RESPFUNCIONALNOMBRE, getRespFuncionalNombre()));
            } 
            else if (getRespFuncionalNombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(RESPFUNCIONALNOMBRE, getRespFuncionalNombre()));
            }
            else if (getRespFuncionalNombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(RESPFUNCIONALNOMBRE, getRespFuncionalNombre())));
            }
            else {
                criteria.add(Restrictions.like(RESPFUNCIONALNOMBRE, getRespFuncionalNombre()));
            }
        }

        if (getRespFuncionalNombreIn().size() > 0) {
            criteria.add(Restrictions.in(RESPFUNCIONALNOMBRE, getRespFuncionalNombreIn()));
        }

        if (isRespFuncionalNombreIsNull()) {
            criteria.add(Restrictions.isNull(RESPFUNCIONALNOMBRE));
        }

        if (isRespFuncionalNombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(RESPFUNCIONALNOMBRE));
        }

        if (getRespFuncionalEmail() != null) {
            if (getRespFuncionalEmailComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(RESPFUNCIONALEMAIL, getRespFuncionalEmail()));
            } 
            else if (getRespFuncionalEmailComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(RESPFUNCIONALEMAIL, getRespFuncionalEmail()));
            }
            else if (getRespFuncionalEmailComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(RESPFUNCIONALEMAIL, getRespFuncionalEmail())));
            }
            else {
                criteria.add(Restrictions.like(RESPFUNCIONALEMAIL, getRespFuncionalEmail()));
            }
        }

        if (getRespFuncionalEmailIn().size() > 0) {
            criteria.add(Restrictions.in(RESPFUNCIONALEMAIL, getRespFuncionalEmailIn()));
        }

        if (isRespFuncionalEmailIsNull()) {
            criteria.add(Restrictions.isNull(RESPFUNCIONALEMAIL));
        }

        if (isRespFuncionalEmailIsNotNull()) {
            criteria.add(Restrictions.isNotNull(RESPFUNCIONALEMAIL));
        }

        if (getRespTecnicoNombre() != null) {
            if (getRespTecnicoNombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(RESPTECNICONOMBRE, getRespTecnicoNombre()));
            } 
            else if (getRespTecnicoNombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(RESPTECNICONOMBRE, getRespTecnicoNombre()));
            }
            else if (getRespTecnicoNombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(RESPTECNICONOMBRE, getRespTecnicoNombre())));
            }
            else {
                criteria.add(Restrictions.like(RESPTECNICONOMBRE, getRespTecnicoNombre()));
            }
        }

        if (getRespTecnicoNombreIn().size() > 0) {
            criteria.add(Restrictions.in(RESPTECNICONOMBRE, getRespTecnicoNombreIn()));
        }

        if (isRespTecnicoNombreIsNull()) {
            criteria.add(Restrictions.isNull(RESPTECNICONOMBRE));
        }

        if (isRespTecnicoNombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(RESPTECNICONOMBRE));
        }

        if (getRespTecnicoEmail() != null) {
            if (getRespTecnicoEmailComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(RESPTECNICOEMAIL, getRespTecnicoEmail()));
            } 
            else if (getRespTecnicoEmailComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(RESPTECNICOEMAIL, getRespTecnicoEmail()));
            }
            else if (getRespTecnicoEmailComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(RESPTECNICOEMAIL, getRespTecnicoEmail())));
            }
            else {
                criteria.add(Restrictions.like(RESPTECNICOEMAIL, getRespTecnicoEmail()));
            }
        }

        if (getRespTecnicoEmailIn().size() > 0) {
            criteria.add(Restrictions.in(RESPTECNICOEMAIL, getRespTecnicoEmailIn()));
        }

        if (isRespTecnicoEmailIsNull()) {
            criteria.add(Restrictions.isNull(RESPTECNICOEMAIL));
        }

        if (isRespTecnicoEmailIsNotNull()) {
            criteria.add(Restrictions.isNotNull(RESPTECNICOEMAIL));
        }

        if (getSmsMaximos() != null) {
            criteria.add(Restrictions.eq(SMSMAXIMOS, getSmsMaximos()));
        }

        if (isSmsMaximosIsNull()) {
            criteria.add(Restrictions.isNull(SMSMAXIMOS));
        }

        if (isSmsMaximosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SMSMAXIMOS));
        }

        if (getSmsEnviadosDia() != null) {
            criteria.add(Restrictions.eq(SMSENVIADOSDIA, getSmsEnviadosDia()));
        }

        if (isSmsEnviadosDiaIsNull()) {
            criteria.add(Restrictions.isNull(SMSENVIADOSDIA));
        }

        if (isSmsEnviadosDiaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SMSENVIADOSDIA));
        }

        if (getSmsFechaUltimoMin() != null) {
            criteria.add(Restrictions.ge(SMSFECHAULTIMO, getSmsFechaUltimoMin()));
        }

        if (getSmsFechaUltimoMax() != null) {
            criteria.add(Restrictions.le(SMSFECHAULTIMO, getSmsFechaUltimoMax()));
        }

        if (isSmsFechaUltimoIsNull()) {
            criteria.add(Restrictions.isNull(SMSFECHAULTIMO));
        }

        if (isSmsFechaUltimoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SMSFECHAULTIMO));
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
 
