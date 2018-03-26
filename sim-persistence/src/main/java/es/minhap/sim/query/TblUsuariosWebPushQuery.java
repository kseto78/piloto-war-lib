/*
 *
 * archivo: TblUsuariosWebPushQuery.java
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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUsuariosWebPush;

/**
 * Clase con criterios de busqueda para la entidad TblUsuariosWebPush
 */
public class TblUsuariosWebPushQuery extends AbstractHibernateQueryEntity<TblUsuariosWebPush> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String USUARIOWEBPUSHID = "usuariowebpushid";
    public static final String USUARIOID = "usuarioid";
    public static final String TBLSERVICIOS = "tblServicios";
    public static final String ENDPOINT = "endpoint";
    public static final String AUTH = "auth";
    public static final String PDH = "pdh";
    public static final String FECHACREACION = "fechacreacion";
    public static final String FECHAMEDIFICACION = "fechamedificacion";
    public static final String ELIMINADO = "eliminado";


    /**
     * Valor de busqueda de campo usuariowebpushid
     */
    private Long usuariowebpushid;

    /**
     * Lista de valores del campo usuariowebpushid para busquedas tipo IN
     */
    private List<Long> usuariowebpushidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo usuarioid
     */
    private String usuarioid;

    /**
     * Tipo de comparador para la busqueda por campo usuarioid
     */
    private TextComparator usuarioidComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo usuarioid para busquedas tipo IN
     */
    private List<String> usuarioidIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo usuarioid es NULL
     */
    private boolean usuarioidIsNull = false;

    /**
     * Permite buscar cuando campo usuarioid es NOT NULL
     */
    private boolean usuarioidIsNotNull = false;

    /**
     * Valor de busqueda de campo tblServicios
     */
    private TblServiciosQuery tblServicios;

    /**
     * Lista de valores del ID del campo tblServicios para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblServicios
     */
    private List<TblServicios> tblServiciosIdIn = new ArrayList<TblServicios>(0);

    /**
     * Permite buscar cuando campo tblServicios es NULL
     */
    private boolean tblServiciosIsNull = false;

    /**
     * Permite buscar cuando campo tblServicios es NOT NULL
     */
    private boolean tblServiciosIsNotNull = false;

    /**
     * Valor de busqueda de campo endpoint
     */
    private String endpoint;

    /**
     * Tipo de comparador para la busqueda por campo endpoint
     */
    private TextComparator endpointComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo endpoint para busquedas tipo IN
     */
    private List<String> endpointIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo endpoint es NULL
     */
    private boolean endpointIsNull = false;

    /**
     * Permite buscar cuando campo endpoint es NOT NULL
     */
    private boolean endpointIsNotNull = false;

    /**
     * Valor de busqueda de campo auth
     */
    private String auth;

    /**
     * Tipo de comparador para la busqueda por campo auth
     */
    private TextComparator authComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo auth para busquedas tipo IN
     */
    private List<String> authIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo auth es NULL
     */
    private boolean authIsNull = false;

    /**
     * Permite buscar cuando campo auth es NOT NULL
     */
    private boolean authIsNotNull = false;

    /**
     * Valor de busqueda de campo pdh
     */
    private String pdh;

    /**
     * Tipo de comparador para la busqueda por campo pdh
     */
    private TextComparator pdhComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo pdh para busquedas tipo IN
     */
    private List<String> pdhIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo pdh es NULL
     */
    private boolean pdhIsNull = false;

    /**
     * Permite buscar cuando campo pdh es NOT NULL
     */
    private boolean pdhIsNotNull = false;

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
     * Valor inferior de rango de busqueda de fecha fechamedificacion
     */
    private Date fechamedificacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechamedificacion
     */
    private Date fechamedificacionMax;

    /**
     * Permite buscar cuando campo fechamedificacion es NULL
     */
    private boolean fechamedificacionIsNull = false;

    /**
     * Permite buscar cuando campo fechamedificacion es NOT NULL
     */
    private boolean fechamedificacionIsNotNull = false;

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
     * Indica si en la consulta se hace un inner join con el padre tblServicios
     */
    private boolean innerJoinTblServicios = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblServicios
     */
    private boolean leftJoinTblServicios = false;

    /**
     * Constructor default
     */
    public TblUsuariosWebPushQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblUsuariosWebPushQuery(Long usuariowebpushid) {
        setUsuariowebpushid(usuariowebpushid);
    }

    /**
     * Valor de busqueda de campo usuariowebpushid
     * @return Long.
     */
    public Long getUsuariowebpushid() {
        return usuariowebpushid;
    }

    /**
     * Valor de busqueda de campo usuariowebpushid
     * @param usuariowebpushid Valor de seteo.
     */
    public void setUsuariowebpushid(Long usuariowebpushid) {
        this.usuariowebpushid = usuariowebpushid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getUsuariowebpushidIn() {
        return this.usuariowebpushidIn;
    }

    /**
     * @param usuariowebpushid Valor a agregar.
     */
    public void addUsuariowebpushidIn(Long usuariowebpushid) {
        this.usuariowebpushidIn.add(usuariowebpushid);
    }

    /**
     * Valor de busqueda de campo usuarioid
     * @return String.
     */
    public String getUsuarioid() {
        if (usuarioid != null) {
            switch (usuarioidComparator) {
	            case STARTS_WITH:
	                return usuarioid + "%";
	            case CONTAINS:
	                return "%" + usuarioid + "%";
	            case ENDS_WITH:
	                return "%" + usuarioid;
	            case EQUALS:
                	return usuarioid;
              	default:
	            	break;
            }
        }
        return usuarioid;
    }

    /**
     * Valor de busqueda de campo usuarioid
     * @param usuarioid Valor de seteo.
     */
    public void setUsuarioid(String usuarioid) {
        this.usuarioid = usuarioid;
    }

    /**
     * Tipo de comparador para la busqueda por campo usuarioid
     * @return usuarioidComparator.
     */
    public TextComparator getUsuarioidComparator() {
        return usuarioidComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo usuarioid
     * @param usuarioidComparator Valor de seteo.
     */
    public void setUsuarioidComparator(TextComparator usuarioidComparator) {
        this.usuarioidComparator = usuarioidComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUsuarioidIn() {
        return this.usuarioidIn;
    }

    /**
     * @param usuarioid Valor a agregar.
     */
    public void addUsuarioidIn(String usuarioid) {
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
     * Valor de busqueda de campo tblServicios
     * @return TblServicios.
     */
    public TblServiciosQuery getTblServicios() {
        return tblServicios;
    }

    /**
     * Valor de busqueda de campo tblServicios
     * @param tblServicios Valor de seteo.
     */
    public void setTblServicios(TblServiciosQuery tblServicios) {
        this.tblServicios = tblServicios;
    }

    /**
     * @return List<TblServicios>.
     */
    public List<TblServicios> getTblServiciosIdIn() {
        return this.tblServiciosIdIn;
    }

    /**
     * @param tblServicios Valor a agregar.
     */
    public void addTblServiciosIdIn(TblServicios tblServicios) {
        this.tblServiciosIdIn.add(tblServicios);
    }

    /**
     * Permite buscar cuando campo tblServicios es NULL
     * @return boolean.
     */
    public boolean isTblServiciosIsNull() {
        return tblServiciosIsNull;
    }

    /**
     * Permite buscar cuando campo tblServicios es NULL
     * @param tblServiciosIsNull Valor de seteo.
     */
    public void setTblServiciosIsNull(boolean tblServiciosIsNull) {
        this.tblServiciosIsNull = tblServiciosIsNull;
    }

    /**
     * Permite buscar cuando campo tblServicios es NOT NULL
     * @return boolean.
     */
    public boolean isTblServiciosIsNotNull() {
        return tblServiciosIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblServicios es NOT NULL
     * @param tblServiciosIsNotNull Valor de seteo.
     */
    public void setTblServiciosIsNotNull(boolean tblServiciosIsNotNull) {
        this.tblServiciosIsNotNull = tblServiciosIsNotNull;
    }

    /**
     * Valor de busqueda de campo endpoint
     * @return String.
     */
    public String getEndpoint() {
        if (endpoint != null) {
            switch (endpointComparator) {
	            case STARTS_WITH:
	                return endpoint + "%";
	            case CONTAINS:
	                return "%" + endpoint + "%";
	            case ENDS_WITH:
	                return "%" + endpoint;
	            case EQUALS:
                	return endpoint;
              	default:
	            	break;
            }
        }
        return endpoint;
    }

    /**
     * Valor de busqueda de campo endpoint
     * @param endpoint Valor de seteo.
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Tipo de comparador para la busqueda por campo endpoint
     * @return endpointComparator.
     */
    public TextComparator getEndpointComparator() {
        return endpointComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo endpoint
     * @param endpointComparator Valor de seteo.
     */
    public void setEndpointComparator(TextComparator endpointComparator) {
        this.endpointComparator = endpointComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEndpointIn() {
        return this.endpointIn;
    }

    /**
     * @param endpoint Valor a agregar.
     */
    public void addEndpointIn(String endpoint) {
        this.endpointIn.add(endpoint);
    }

    /**
     * Permite buscar cuando campo endpoint es NULL
     * @return boolean.
     */
    public boolean isEndpointIsNull() {
        return endpointIsNull;
    }

    /**
     * Permite buscar cuando campo endpoint es NULL
     * @param endpointIsNull Valor de seteo.
     */
    public void setEndpointIsNull(boolean endpointIsNull) {
        this.endpointIsNull = endpointIsNull;
    }

    /**
     * Permite buscar cuando campo endpoint es NOT NULL
     * @return boolean.
     */
    public boolean isEndpointIsNotNull() {
        return endpointIsNotNull;
    }

    /**
     * Permite buscar cuando campo endpoint es NOT NULL
     * @param endpointIsNotNull Valor de seteo.
     */
    public void setEndpointIsNotNull(boolean endpointIsNotNull) {
        this.endpointIsNotNull = endpointIsNotNull;
    }

    /**
     * Valor de busqueda de campo auth
     * @return String.
     */
    public String getAuth() {
        if (auth != null) {
            switch (authComparator) {
	            case STARTS_WITH:
	                return auth + "%";
	            case CONTAINS:
	                return "%" + auth + "%";
	            case ENDS_WITH:
	                return "%" + auth;
	            case EQUALS:
                	return auth;
              	default:
	            	break;
            }
        }
        return auth;
    }

    /**
     * Valor de busqueda de campo auth
     * @param auth Valor de seteo.
     */
    public void setAuth(String auth) {
        this.auth = auth;
    }

    /**
     * Tipo de comparador para la busqueda por campo auth
     * @return authComparator.
     */
    public TextComparator getAuthComparator() {
        return authComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo auth
     * @param authComparator Valor de seteo.
     */
    public void setAuthComparator(TextComparator authComparator) {
        this.authComparator = authComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getAuthIn() {
        return this.authIn;
    }

    /**
     * @param auth Valor a agregar.
     */
    public void addAuthIn(String auth) {
        this.authIn.add(auth);
    }

    /**
     * Permite buscar cuando campo auth es NULL
     * @return boolean.
     */
    public boolean isAuthIsNull() {
        return authIsNull;
    }

    /**
     * Permite buscar cuando campo auth es NULL
     * @param authIsNull Valor de seteo.
     */
    public void setAuthIsNull(boolean authIsNull) {
        this.authIsNull = authIsNull;
    }

    /**
     * Permite buscar cuando campo auth es NOT NULL
     * @return boolean.
     */
    public boolean isAuthIsNotNull() {
        return authIsNotNull;
    }

    /**
     * Permite buscar cuando campo auth es NOT NULL
     * @param authIsNotNull Valor de seteo.
     */
    public void setAuthIsNotNull(boolean authIsNotNull) {
        this.authIsNotNull = authIsNotNull;
    }

    /**
     * Valor de busqueda de campo pdh
     * @return String.
     */
    public String getPdh() {
        if (pdh != null) {
            switch (pdhComparator) {
	            case STARTS_WITH:
	                return pdh + "%";
	            case CONTAINS:
	                return "%" + pdh + "%";
	            case ENDS_WITH:
	                return "%" + pdh;
	            case EQUALS:
                	return pdh;
              	default:
	            	break;
            }
        }
        return pdh;
    }

    /**
     * Valor de busqueda de campo pdh
     * @param pdh Valor de seteo.
     */
    public void setPdh(String pdh) {
        this.pdh = pdh;
    }

    /**
     * Tipo de comparador para la busqueda por campo pdh
     * @return pdhComparator.
     */
    public TextComparator getPdhComparator() {
        return pdhComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo pdh
     * @param pdhComparator Valor de seteo.
     */
    public void setPdhComparator(TextComparator pdhComparator) {
        this.pdhComparator = pdhComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getPdhIn() {
        return this.pdhIn;
    }

    /**
     * @param pdh Valor a agregar.
     */
    public void addPdhIn(String pdh) {
        this.pdhIn.add(pdh);
    }

    /**
     * Permite buscar cuando campo pdh es NULL
     * @return boolean.
     */
    public boolean isPdhIsNull() {
        return pdhIsNull;
    }

    /**
     * Permite buscar cuando campo pdh es NULL
     * @param pdhIsNull Valor de seteo.
     */
    public void setPdhIsNull(boolean pdhIsNull) {
        this.pdhIsNull = pdhIsNull;
    }

    /**
     * Permite buscar cuando campo pdh es NOT NULL
     * @return boolean.
     */
    public boolean isPdhIsNotNull() {
        return pdhIsNotNull;
    }

    /**
     * Permite buscar cuando campo pdh es NOT NULL
     * @param pdhIsNotNull Valor de seteo.
     */
    public void setPdhIsNotNull(boolean pdhIsNotNull) {
        this.pdhIsNotNull = pdhIsNotNull;
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
     * Valor inferior de rango de busqueda de fecha fechamedificacion
     * @return ${field.getName()}Min.
     */
    public Date getFechamedificacionMin() {
        if (fechamedificacionMin != null) {
            return DateUtil.toDayBegin(fechamedificacionMin);
        }
        return fechamedificacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechamedificacion
     * @param fechamedificacionMin Valor de seteo.
     */
    public void setFechamedificacionMin(Date fechamedificacionMin) {
        this.fechamedificacionMin = fechamedificacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechamedificacion
     * @return fechamedificacionMax.
     */
    public Date getFechamedificacionMax() {
        if (fechamedificacionMax != null) {
            return DateUtil.toDayEnd(fechamedificacionMax);
        }
        return fechamedificacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechamedificacion
     * @param fechamedificacionMax Valor de seteo.
     */
    public void setFechamedificacionMax(Date fechamedificacionMax) {
        this.fechamedificacionMax = fechamedificacionMax;
    }

    /**
     * Permite buscar cuando campo fechamedificacion es NULL
     * @return boolean.
     */
    public boolean isFechamedificacionIsNull() {
        return fechamedificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechamedificacion es NULL
     * @param fechamedificacionIsNull Valor de seteo.
     */
    public void setFechamedificacionIsNull(boolean fechamedificacionIsNull) {
        this.fechamedificacionIsNull = fechamedificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechamedificacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechamedificacionIsNotNull() {
        return fechamedificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechamedificacion es NOT NULL
     * @param fechamedificacionIsNotNull Valor de seteo.
     */
    public void setFechamedificacionIsNotNull(boolean fechamedificacionIsNotNull) {
        this.fechamedificacionIsNotNull = fechamedificacionIsNotNull;
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
     * @return boolean.
     */
    public boolean isInnerJoinTblServicios() {
        return innerJoinTblServicios;
    }

    /**
     * @param innerJoinTblServicios Valor de seteo.
     */
    public void setInnerJoinTblServicios(boolean innerJoinTblServicios) {
        this.innerJoinTblServicios = innerJoinTblServicios;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblServicios() {
        return leftJoinTblServicios;
    }

    /**
     * @param leftJoinTblServicios Valor de seteo.
     */
    public void setLeftJoinTblServicios(boolean leftJoinTblServicios) {
        this.leftJoinTblServicios = leftJoinTblServicios;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getUsuariowebpushid() != null) {
            criteria.add(Restrictions.eq(USUARIOWEBPUSHID, getUsuariowebpushid()));
        }

        if (getUsuariowebpushidIn().size() > 0) {
            criteria.add(Restrictions.in(USUARIOWEBPUSHID, getUsuariowebpushidIn()));
        }

        if (getUsuarioid() != null) {
            if (getUsuarioidComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(USUARIOID, getUsuarioid()));
            } 
            else if (getUsuarioidComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(USUARIOID, getUsuarioid()));
            }
            else if (getUsuarioidComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(USUARIOID, getUsuarioid())));
            }
            else {
                criteria.add(Restrictions.like(USUARIOID, getUsuarioid()));
            }
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

        // Campo entidad padre tblServicios
        
        // Si se hace join fetch con el padre
        Criteria tblServiciosCriteria = null;
        if (isInnerJoinTblServicios()) {
            tblServiciosCriteria = criteria.createCriteria(TBLSERVICIOS, "a_" + TBLSERVICIOS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblServicios()) {
            tblServiciosCriteria = criteria.createCriteria(TBLSERVICIOS, "a_" + TBLSERVICIOS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblServicios() != null) {
            if (getTblServicios().getServicioid() == null) {
                if (tblServiciosCriteria == null) {
                    tblServiciosCriteria = criteria.createCriteria(TBLSERVICIOS, "a_" + TBLSERVICIOS);
                }
                getTblServicios().addCriteria(tblServiciosCriteria, useOrder);
            } else {
                TblServicios parent = new TblServicios();
                parent.setServicioid(getTblServicios().getServicioid());
                criteria.add(Restrictions.eq(TBLSERVICIOS, parent));
            }
        }

        if (getTblServiciosIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLSERVICIOS, getTblServiciosIdIn()));
        }

        if (isTblServiciosIsNull()) {
            criteria.add(Restrictions.isNull(TBLSERVICIOS));
        }

        if (isTblServiciosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLSERVICIOS));
        }

        if (getEndpoint() != null) {
            if (getEndpointComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ENDPOINT, getEndpoint()));
            } 
            else if (getEndpointComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ENDPOINT, getEndpoint()));
            }
            else if (getEndpointComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ENDPOINT, getEndpoint())));
            }
            else {
                criteria.add(Restrictions.like(ENDPOINT, getEndpoint()));
            }
        }

        if (getEndpointIn().size() > 0) {
            criteria.add(Restrictions.in(ENDPOINT, getEndpointIn()));
        }

        if (isEndpointIsNull()) {
            criteria.add(Restrictions.isNull(ENDPOINT));
        }

        if (isEndpointIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ENDPOINT));
        }

        if (getAuth() != null) {
            if (getAuthComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(AUTH, getAuth()));
            } 
            else if (getAuthComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(AUTH, getAuth()));
            }
            else if (getAuthComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(AUTH, getAuth())));
            }
            else {
                criteria.add(Restrictions.like(AUTH, getAuth()));
            }
        }

        if (getAuthIn().size() > 0) {
            criteria.add(Restrictions.in(AUTH, getAuthIn()));
        }

        if (isAuthIsNull()) {
            criteria.add(Restrictions.isNull(AUTH));
        }

        if (isAuthIsNotNull()) {
            criteria.add(Restrictions.isNotNull(AUTH));
        }

        if (getPdh() != null) {
            if (getPdhComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PDH, getPdh()));
            } 
            else if (getPdhComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PDH, getPdh()));
            }
            else if (getPdhComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PDH, getPdh())));
            }
            else {
                criteria.add(Restrictions.like(PDH, getPdh()));
            }
        }

        if (getPdhIn().size() > 0) {
            criteria.add(Restrictions.in(PDH, getPdhIn()));
        }

        if (isPdhIsNull()) {
            criteria.add(Restrictions.isNull(PDH));
        }

        if (isPdhIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PDH));
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

        if (getFechamedificacionMin() != null) {
            criteria.add(Restrictions.ge(FECHAMEDIFICACION, getFechamedificacionMin()));
        }

        if (getFechamedificacionMax() != null) {
            criteria.add(Restrictions.le(FECHAMEDIFICACION, getFechamedificacionMax()));
        }

        if (isFechamedificacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAMEDIFICACION));
        }

        if (isFechamedificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAMEDIFICACION));
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
 
