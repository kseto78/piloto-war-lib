/*
 *
 * archivo: ViewUsuariosAplicacionesQuery.java
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
import es.minhap.sim.model.ViewUsuariosAplicaciones;

/**
 * Clase con criterios de busqueda para la entidad ViewUsuariosAplicaciones
 */
public class ViewUsuariosAplicacionesQuery extends AbstractHibernateQueryEntity<ViewUsuariosAplicaciones> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String USUARIOAPLICACIONID = "usuarioaplicacionid";
    public static final String USUARIOID = "usuarioid";
    public static final String APLICACIONID = "aplicacionid";
    public static final String MODO = "modo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String NOMBREAPLICACION = "nombreaplicacion";
    public static final String NOMBREUSUARIO = "nombreusuario";
    public static final String ROLID = "rolid";
    public static final String ACTIVO = "activo";
    public static final String ELIMINADO = "eliminado";
    public static final String ROLUSUARIO = "rolusuario";


    /**
     * Valor de busqueda de campo usuarioaplicacionid
     */
    private Long usuarioaplicacionid;

    /**
     * Lista de valores del campo usuarioaplicacionid para busquedas tipo IN
     */
    private List<Long> usuarioaplicacionidIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo modo
     */
    private Integer modo;

    /**
     * Lista de valores del campo modo para busquedas tipo IN
     */
    private List<Integer> modoIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo modo es NULL
     */
    private boolean modoIsNull = false;

    /**
     * Permite buscar cuando campo modo es NOT NULL
     */
    private boolean modoIsNotNull = false;

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
     * Valor de busqueda de campo nombreaplicacion
     */
    private String nombreaplicacion;

    /**
     * Tipo de comparador para la busqueda por campo nombreaplicacion
     */
    private TextComparator nombreaplicacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombreaplicacion para busquedas tipo IN
     */
    private List<String> nombreaplicacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombreaplicacion es NULL
     */
    private boolean nombreaplicacionIsNull = false;

    /**
     * Permite buscar cuando campo nombreaplicacion es NOT NULL
     */
    private boolean nombreaplicacionIsNotNull = false;

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
     * Valor de busqueda de campo rolid
     */
    private Long rolid;

    /**
     * Lista de valores del campo rolid para busquedas tipo IN
     */
    private List<Long> rolidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo rolid es NULL
     */
    private boolean rolidIsNull = false;

    /**
     * Permite buscar cuando campo rolid es NOT NULL
     */
    private boolean rolidIsNotNull = false;

    /**
     * Valor de busqueda de campo activo
     */
    private Integer activo;

    /**
     * Lista de valores del campo activo para busquedas tipo IN
     */
    private List<Integer> activoIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo activo es NULL
     */
    private boolean activoIsNull = false;

    /**
     * Permite buscar cuando campo activo es NOT NULL
     */
    private boolean activoIsNotNull = false;

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
     * Valor de busqueda de campo rolusuario
     */
    private String rolusuario;

    /**
     * Tipo de comparador para la busqueda por campo rolusuario
     */
    private TextComparator rolusuarioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo rolusuario para busquedas tipo IN
     */
    private List<String> rolusuarioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo rolusuario es NULL
     */
    private boolean rolusuarioIsNull = false;

    /**
     * Permite buscar cuando campo rolusuario es NOT NULL
     */
    private boolean rolusuarioIsNotNull = false;

    /**
     * Constructor default
     */
    public ViewUsuariosAplicacionesQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ViewUsuariosAplicacionesQuery(Long usuarioaplicacionid) {
        setUsuarioaplicacionid(usuarioaplicacionid);
    }

    /**
     * Valor de busqueda de campo usuarioaplicacionid
     * @return Long.
     */
    public Long getUsuarioaplicacionid() {
        return usuarioaplicacionid;
    }

    /**
     * Valor de busqueda de campo usuarioaplicacionid
     * @param usuarioaplicacionid Valor de seteo.
     */
    public void setUsuarioaplicacionid(Long usuarioaplicacionid) {
        this.usuarioaplicacionid = usuarioaplicacionid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getUsuarioaplicacionidIn() {
        return this.usuarioaplicacionidIn;
    }

    /**
     * @param usuarioaplicacionid Valor a agregar.
     */
    public void addUsuarioaplicacionidIn(Long usuarioaplicacionid) {
        this.usuarioaplicacionidIn.add(usuarioaplicacionid);
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
     * Valor de busqueda de campo modo
     * @return Integer.
     */
    public Integer getModo() {
        return modo;
    }

    /**
     * Valor de busqueda de campo modo
     * @param modo Valor de seteo.
     */
    public void setModo(Integer modo) {
        this.modo = modo;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getModoIn() {
        return this.modoIn;
    }

    /**
     * @param modo Valor a agregar.
     */
    public void addModoIn(Integer modo) {
        this.modoIn.add(modo);
    }

    /**
     * Permite buscar cuando campo modo es NULL
     * @return boolean.
     */
    public boolean isModoIsNull() {
        return modoIsNull;
    }

    /**
     * Permite buscar cuando campo modo es NULL
     * @param modoIsNull Valor de seteo.
     */
    public void setModoIsNull(boolean modoIsNull) {
        this.modoIsNull = modoIsNull;
    }

    /**
     * Permite buscar cuando campo modo es NOT NULL
     * @return boolean.
     */
    public boolean isModoIsNotNull() {
        return modoIsNotNull;
    }

    /**
     * Permite buscar cuando campo modo es NOT NULL
     * @param modoIsNotNull Valor de seteo.
     */
    public void setModoIsNotNull(boolean modoIsNotNull) {
        this.modoIsNotNull = modoIsNotNull;
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
     * Valor de busqueda de campo nombreaplicacion
     * @return String.
     */
    public String getNombreaplicacion() {
        if (nombreaplicacion != null) {
            switch (nombreaplicacionComparator) {
	            case STARTS_WITH:
	                return nombreaplicacion + "%";
	            case CONTAINS:
	                return "%" + nombreaplicacion + "%";
	            case ENDS_WITH:
	                return "%" + nombreaplicacion;
	            case EQUALS:
                	return nombreaplicacion;
              	default:
	            	break;
            }
        }
        return nombreaplicacion;
    }

    /**
     * Valor de busqueda de campo nombreaplicacion
     * @param nombreaplicacion Valor de seteo.
     */
    public void setNombreaplicacion(String nombreaplicacion) {
        this.nombreaplicacion = nombreaplicacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreaplicacion
     * @return nombreaplicacionComparator.
     */
    public TextComparator getNombreaplicacionComparator() {
        return nombreaplicacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreaplicacion
     * @param nombreaplicacionComparator Valor de seteo.
     */
    public void setNombreaplicacionComparator(TextComparator nombreaplicacionComparator) {
        this.nombreaplicacionComparator = nombreaplicacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreaplicacionIn() {
        return this.nombreaplicacionIn;
    }

    /**
     * @param nombreaplicacion Valor a agregar.
     */
    public void addNombreaplicacionIn(String nombreaplicacion) {
        this.nombreaplicacionIn.add(nombreaplicacion);
    }

    /**
     * Permite buscar cuando campo nombreaplicacion es NULL
     * @return boolean.
     */
    public boolean isNombreaplicacionIsNull() {
        return nombreaplicacionIsNull;
    }

    /**
     * Permite buscar cuando campo nombreaplicacion es NULL
     * @param nombreaplicacionIsNull Valor de seteo.
     */
    public void setNombreaplicacionIsNull(boolean nombreaplicacionIsNull) {
        this.nombreaplicacionIsNull = nombreaplicacionIsNull;
    }

    /**
     * Permite buscar cuando campo nombreaplicacion es NOT NULL
     * @return boolean.
     */
    public boolean isNombreaplicacionIsNotNull() {
        return nombreaplicacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombreaplicacion es NOT NULL
     * @param nombreaplicacionIsNotNull Valor de seteo.
     */
    public void setNombreaplicacionIsNotNull(boolean nombreaplicacionIsNotNull) {
        this.nombreaplicacionIsNotNull = nombreaplicacionIsNotNull;
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
     * Valor de busqueda de campo rolid
     * @return Long.
     */
    public Long getRolid() {
        return rolid;
    }

    /**
     * Valor de busqueda de campo rolid
     * @param rolid Valor de seteo.
     */
    public void setRolid(Long rolid) {
        this.rolid = rolid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getRolidIn() {
        return this.rolidIn;
    }

    /**
     * @param rolid Valor a agregar.
     */
    public void addRolidIn(Long rolid) {
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
     * Valor de busqueda de campo activo
     * @return Integer.
     */
    public Integer getActivo() {
        return activo;
    }

    /**
     * Valor de busqueda de campo activo
     * @param activo Valor de seteo.
     */
    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getActivoIn() {
        return this.activoIn;
    }

    /**
     * @param activo Valor a agregar.
     */
    public void addActivoIn(Integer activo) {
        this.activoIn.add(activo);
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
     * Valor de busqueda de campo rolusuario
     * @return String.
     */
    public String getRolusuario() {
        if (rolusuario != null) {
            switch (rolusuarioComparator) {
	            case STARTS_WITH:
	                return rolusuario + "%";
	            case CONTAINS:
	                return "%" + rolusuario + "%";
	            case ENDS_WITH:
	                return "%" + rolusuario;
	            case EQUALS:
                	return rolusuario;
              	default:
	            	break;
            }
        }
        return rolusuario;
    }

    /**
     * Valor de busqueda de campo rolusuario
     * @param rolusuario Valor de seteo.
     */
    public void setRolusuario(String rolusuario) {
        this.rolusuario = rolusuario;
    }

    /**
     * Tipo de comparador para la busqueda por campo rolusuario
     * @return rolusuarioComparator.
     */
    public TextComparator getRolusuarioComparator() {
        return rolusuarioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo rolusuario
     * @param rolusuarioComparator Valor de seteo.
     */
    public void setRolusuarioComparator(TextComparator rolusuarioComparator) {
        this.rolusuarioComparator = rolusuarioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getRolusuarioIn() {
        return this.rolusuarioIn;
    }

    /**
     * @param rolusuario Valor a agregar.
     */
    public void addRolusuarioIn(String rolusuario) {
        this.rolusuarioIn.add(rolusuario);
    }

    /**
     * Permite buscar cuando campo rolusuario es NULL
     * @return boolean.
     */
    public boolean isRolusuarioIsNull() {
        return rolusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo rolusuario es NULL
     * @param rolusuarioIsNull Valor de seteo.
     */
    public void setRolusuarioIsNull(boolean rolusuarioIsNull) {
        this.rolusuarioIsNull = rolusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo rolusuario es NOT NULL
     * @return boolean.
     */
    public boolean isRolusuarioIsNotNull() {
        return rolusuarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo rolusuario es NOT NULL
     * @param rolusuarioIsNotNull Valor de seteo.
     */
    public void setRolusuarioIsNotNull(boolean rolusuarioIsNotNull) {
        this.rolusuarioIsNotNull = rolusuarioIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getUsuarioaplicacionid() != null) {
            criteria.add(Restrictions.eq(USUARIOAPLICACIONID, getUsuarioaplicacionid()));
        }

        if (getUsuarioaplicacionidIn().size() > 0) {
            criteria.add(Restrictions.in(USUARIOAPLICACIONID, getUsuarioaplicacionidIn()));
        }

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

        if (getModo() != null) {
            criteria.add(Restrictions.eq(MODO, getModo()));
        }

        if (getModoIn().size() > 0) {
            criteria.add(Restrictions.in(MODO, getModoIn()));
        }

        if (isModoIsNull()) {
            criteria.add(Restrictions.isNull(MODO));
        }

        if (isModoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MODO));
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

        if (getNombreaplicacion() != null) {
            if (getNombreaplicacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBREAPLICACION, getNombreaplicacion()));
            } 
            else if (getNombreaplicacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBREAPLICACION, getNombreaplicacion()));
            }
            else if (getNombreaplicacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBREAPLICACION, getNombreaplicacion())));
            }
            else {
                criteria.add(Restrictions.like(NOMBREAPLICACION, getNombreaplicacion()));
            }
        }

        if (getNombreaplicacionIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBREAPLICACION, getNombreaplicacionIn()));
        }

        if (isNombreaplicacionIsNull()) {
            criteria.add(Restrictions.isNull(NOMBREAPLICACION));
        }

        if (isNombreaplicacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBREAPLICACION));
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

        if (getActivo() != null) {
            criteria.add(Restrictions.eq(ACTIVO, getActivo()));
        }

        if (getActivoIn().size() > 0) {
            criteria.add(Restrictions.in(ACTIVO, getActivoIn()));
        }

        if (isActivoIsNull()) {
            criteria.add(Restrictions.isNull(ACTIVO));
        }

        if (isActivoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ACTIVO));
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

        if (getRolusuario() != null) {
            if (getRolusuarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ROLUSUARIO, getRolusuario()));
            } 
            else if (getRolusuarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ROLUSUARIO, getRolusuario()));
            }
            else if (getRolusuarioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ROLUSUARIO, getRolusuario())));
            }
            else {
                criteria.add(Restrictions.like(ROLUSUARIO, getRolusuario()));
            }
        }

        if (getRolusuarioIn().size() > 0) {
            criteria.add(Restrictions.in(ROLUSUARIO, getRolusuarioIn()));
        }

        if (isRolusuarioIsNull()) {
            criteria.add(Restrictions.isNull(ROLUSUARIO));
        }

        if (isRolusuarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ROLUSUARIO));
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
 
