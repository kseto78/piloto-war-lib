/*
 *
 * archivo: TblUsuariosServiciosMovilesQuery.java
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
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblUsuariosPush;
import es.minhap.sim.model.TblUsuariosServiciosMoviles;

/**
 * Clase con criterios de busqueda para la entidad TblUsuariosServiciosMoviles
 */
public class TblUsuariosServiciosMovilesQuery extends AbstractHibernateQueryEntity<TblUsuariosServiciosMoviles> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String USUARIOSSERVICIOSMOVILESID = "usuariosserviciosmovilesid";
    public static final String TBLUSUARIOSPUSH = "tblUsuariosPush";
    public static final String SERVICIOSMOVILESID = "serviciosmovilesid";
    public static final String ESTADOSUSCRIPCION = "estadosuscripcion";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";


    /**
     * Valor de busqueda de campo usuariosserviciosmovilesid
     */
    private Long usuariosserviciosmovilesid;

    /**
     * Valor de busqueda de campo tblUsuariosPush
     */
    private TblUsuariosPushQuery tblUsuariosPush;

    /**
     * Lista de valores del ID del campo tblUsuariosPush para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblUsuariosPush
     */
    private List<TblUsuariosPush> tblUsuariosPushIdIn = new ArrayList<TblUsuariosPush>(0);

    /**
     * Permite buscar cuando campo tblUsuariosPush es NULL
     */
    private boolean tblUsuariosPushIsNull = false;

    /**
     * Permite buscar cuando campo tblUsuariosPush es NOT NULL
     */
    private boolean tblUsuariosPushIsNotNull = false;

    /**
     * Valor de busqueda de campo serviciosmovilesid
     */
    private Long serviciosmovilesid;

    /**
     * Permite buscar cuando campo serviciosmovilesid es NULL
     */
    private boolean serviciosmovilesidIsNull = false;

    /**
     * Permite buscar cuando campo serviciosmovilesid es NOT NULL
     */
    private boolean serviciosmovilesidIsNotNull = false;

    /**
     * Valor de busqueda de campo estadosuscripcion
     */
    private Integer estadosuscripcion;

    /**
     * Lista de valores del campo estadosuscripcion para busquedas tipo IN
     */
    private List<Integer> estadosuscripcionIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo estadosuscripcion es NULL
     */
    private boolean estadosuscripcionIsNull = false;

    /**
     * Permite buscar cuando campo estadosuscripcion es NOT NULL
     */
    private boolean estadosuscripcionIsNotNull = false;

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
     * Indica si en la consulta se hace un inner join con el padre tblUsuariosPush
     */
    private boolean innerJoinTblUsuariosPush = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblUsuariosPush
     */
    private boolean leftJoinTblUsuariosPush = false;

    /**
     * Constructor default
     */
    public TblUsuariosServiciosMovilesQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblUsuariosServiciosMovilesQuery(Long usuariosserviciosmovilesid) {
        setUsuariosserviciosmovilesid(usuariosserviciosmovilesid);
    }

    /**
     * Valor de busqueda de campo usuariosserviciosmovilesid
     * @return BigDecimal.
     */
    public Long getUsuariosserviciosmovilesid() {
        return usuariosserviciosmovilesid;
    }

    /**
     * Valor de busqueda de campo usuariosserviciosmovilesid
     * @param usuariosserviciosmovilesid Valor de seteo.
     */
    public void setUsuariosserviciosmovilesid(Long usuariosserviciosmovilesid) {
        this.usuariosserviciosmovilesid = usuariosserviciosmovilesid;
    }

    /**
     * Valor de busqueda de campo tblUsuariosPush
     * @return TblUsuariosPush.
     */
    public TblUsuariosPushQuery getTblUsuariosPush() {
        return tblUsuariosPush;
    }

    /**
     * Valor de busqueda de campo tblUsuariosPush
     * @param tblUsuariosPush Valor de seteo.
     */
    public void setTblUsuariosPush(TblUsuariosPushQuery tblUsuariosPush) {
        this.tblUsuariosPush = tblUsuariosPush;
    }

    /**
     * @return List<TblUsuariosPush>.
     */
    public List<TblUsuariosPush> getTblUsuariosPushIdIn() {
        return this.tblUsuariosPushIdIn;
    }

    /**
     * @param tblUsuariosPush Valor a agregar.
     */
    public void addTblUsuariosPushIdIn(TblUsuariosPush tblUsuariosPush) {
        this.tblUsuariosPushIdIn.add(tblUsuariosPush);
    }

    /**
     * Permite buscar cuando campo tblUsuariosPush es NULL
     * @return boolean.
     */
    public boolean isTblUsuariosPushIsNull() {
        return tblUsuariosPushIsNull;
    }

    /**
     * Permite buscar cuando campo tblUsuariosPush es NULL
     * @param tblUsuariosPushIsNull Valor de seteo.
     */
    public void setTblUsuariosPushIsNull(boolean tblUsuariosPushIsNull) {
        this.tblUsuariosPushIsNull = tblUsuariosPushIsNull;
    }

    /**
     * Permite buscar cuando campo tblUsuariosPush es NOT NULL
     * @return boolean.
     */
    public boolean isTblUsuariosPushIsNotNull() {
        return tblUsuariosPushIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblUsuariosPush es NOT NULL
     * @param tblUsuariosPushIsNotNull Valor de seteo.
     */
    public void setTblUsuariosPushIsNotNull(boolean tblUsuariosPushIsNotNull) {
        this.tblUsuariosPushIsNotNull = tblUsuariosPushIsNotNull;
    }

    /**
     * Valor de busqueda de campo serviciosmovilesid
     * @return BigDecimal.
     */
    public Long getServiciosmovilesid() {
        return serviciosmovilesid;
    }

    /**
     * Valor de busqueda de campo serviciosmovilesid
     * @param serviciosmovilesid Valor de seteo.
     */
    public void setServiciosmovilesid(Long serviciosmovilesid) {
        this.serviciosmovilesid = serviciosmovilesid;
    }

    /**
     * Permite buscar cuando campo serviciosmovilesid es NULL
     * @return boolean.
     */
    public boolean isServiciosmovilesidIsNull() {
        return serviciosmovilesidIsNull;
    }

    /**
     * Permite buscar cuando campo serviciosmovilesid es NULL
     * @param serviciosmovilesidIsNull Valor de seteo.
     */
    public void setServiciosmovilesidIsNull(boolean serviciosmovilesidIsNull) {
        this.serviciosmovilesidIsNull = serviciosmovilesidIsNull;
    }

    /**
     * Permite buscar cuando campo serviciosmovilesid es NOT NULL
     * @return boolean.
     */
    public boolean isServiciosmovilesidIsNotNull() {
        return serviciosmovilesidIsNotNull;
    }

    /**
     * Permite buscar cuando campo serviciosmovilesid es NOT NULL
     * @param serviciosmovilesidIsNotNull Valor de seteo.
     */
    public void setServiciosmovilesidIsNotNull(boolean serviciosmovilesidIsNotNull) {
        this.serviciosmovilesidIsNotNull = serviciosmovilesidIsNotNull;
    }

    /**
     * Valor de busqueda de campo estadosuscripcion
     * @return Integer.
     */
    public Integer getEstadosuscripcion() {
        return estadosuscripcion;
    }

    /**
     * Valor de busqueda de campo estadosuscripcion
     * @param estadosuscripcion Valor de seteo.
     */
    public void setEstadosuscripcion(Integer estadosuscripcion) {
        this.estadosuscripcion = estadosuscripcion;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getEstadosuscripcionIn() {
        return this.estadosuscripcionIn;
    }

    /**
     * @param estadosuscripcion Valor a agregar.
     */
    public void addEstadosuscripcionIn(Integer estadosuscripcion) {
        this.estadosuscripcionIn.add(estadosuscripcion);
    }

    /**
     * Permite buscar cuando campo estadosuscripcion es NULL
     * @return boolean.
     */
    public boolean isEstadosuscripcionIsNull() {
        return estadosuscripcionIsNull;
    }

    /**
     * Permite buscar cuando campo estadosuscripcion es NULL
     * @param estadosuscripcionIsNull Valor de seteo.
     */
    public void setEstadosuscripcionIsNull(boolean estadosuscripcionIsNull) {
        this.estadosuscripcionIsNull = estadosuscripcionIsNull;
    }

    /**
     * Permite buscar cuando campo estadosuscripcion es NOT NULL
     * @return boolean.
     */
    public boolean isEstadosuscripcionIsNotNull() {
        return estadosuscripcionIsNotNull;
    }

    /**
     * Permite buscar cuando campo estadosuscripcion es NOT NULL
     * @param estadosuscripcionIsNotNull Valor de seteo.
     */
    public void setEstadosuscripcionIsNotNull(boolean estadosuscripcionIsNotNull) {
        this.estadosuscripcionIsNotNull = estadosuscripcionIsNotNull;
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
     * @return boolean.
     */
    public boolean isInnerJoinTblUsuariosPush() {
        return innerJoinTblUsuariosPush;
    }

    /**
     * @param innerJoinTblUsuariosPush Valor de seteo.
     */
    public void setInnerJoinTblUsuariosPush(boolean innerJoinTblUsuariosPush) {
        this.innerJoinTblUsuariosPush = innerJoinTblUsuariosPush;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblUsuariosPush() {
        return leftJoinTblUsuariosPush;
    }

    /**
     * @param leftJoinTblUsuariosPush Valor de seteo.
     */
    public void setLeftJoinTblUsuariosPush(boolean leftJoinTblUsuariosPush) {
        this.leftJoinTblUsuariosPush = leftJoinTblUsuariosPush;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getUsuariosserviciosmovilesid() != null) {
            criteria.add(Restrictions.eq(USUARIOSSERVICIOSMOVILESID, getUsuariosserviciosmovilesid()));
        }

        // Campo entidad padre tblUsuariosPush
        
        // Si se hace join fetch con el padre
        Criteria tblUsuariosPushCriteria = null;
        if (isInnerJoinTblUsuariosPush()) {
            tblUsuariosPushCriteria = criteria.createCriteria(TBLUSUARIOSPUSH, "a_" + TBLUSUARIOSPUSH, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblUsuariosPush()) {
            tblUsuariosPushCriteria = criteria.createCriteria(TBLUSUARIOSPUSH, "a_" + TBLUSUARIOSPUSH, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblUsuariosPush() != null) {
            if (getTblUsuariosPush().getUsuarioid() == null) {
                if (tblUsuariosPushCriteria == null) {
                    tblUsuariosPushCriteria = criteria.createCriteria(TBLUSUARIOSPUSH, "a_" + TBLUSUARIOSPUSH);
                }
                getTblUsuariosPush().addCriteria(tblUsuariosPushCriteria, useOrder);
            } else {
                TblUsuariosPush parent = new TblUsuariosPush();
                parent.setUsuarioid(getTblUsuariosPush().getUsuarioid());
                criteria.add(Restrictions.eq(TBLUSUARIOSPUSH, parent));
            }
        }

        if (getTblUsuariosPushIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLUSUARIOSPUSH, getTblUsuariosPushIdIn()));
        }

        if (isTblUsuariosPushIsNull()) {
            criteria.add(Restrictions.isNull(TBLUSUARIOSPUSH));
        }

        if (isTblUsuariosPushIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLUSUARIOSPUSH));
        }

        if (getServiciosmovilesid() != null) {
            criteria.add(Restrictions.eq(SERVICIOSMOVILESID, getServiciosmovilesid()));
        }

        if (isServiciosmovilesidIsNull()) {
            criteria.add(Restrictions.isNull(SERVICIOSMOVILESID));
        }

        if (isServiciosmovilesidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVICIOSMOVILESID));
        }

        if (getEstadosuscripcion() != null) {
            criteria.add(Restrictions.eq(ESTADOSUSCRIPCION, getEstadosuscripcion()));
        }

        if (getEstadosuscripcionIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADOSUSCRIPCION, getEstadosuscripcionIn()));
        }

        if (isEstadosuscripcionIsNull()) {
            criteria.add(Restrictions.isNull(ESTADOSUSCRIPCION));
        }

        if (isEstadosuscripcionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADOSUSCRIPCION));
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
 
