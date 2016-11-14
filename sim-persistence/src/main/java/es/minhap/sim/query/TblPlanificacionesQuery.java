/*
 *
 * archivo: TblPlanificacionesQuery.java
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
import es.minhap.sim.model.TblPlanificaciones;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.model.TblTipoPlanificaciones;

/**
 * Clase con criterios de busqueda para la entidad TblPlanificaciones
 */
public class TblPlanificacionesQuery extends AbstractHibernateQueryEntity<TblPlanificaciones> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String PLANIFICACIONID = "planificacionid";
    public static final String TBLSERVIDORES = "tblServidores";
    public static final String TBLSERVICIOS = "tblServicios";
    public static final String TBLTIPOPLANIFICACIONES = "tblTipoPlanificaciones";
    public static final String L = "l";
    public static final String M = "m";
    public static final String X = "x";
    public static final String J = "j";
    public static final String V = "v";
    public static final String S = "s";
    public static final String D = "d";
    public static final String HORADESDE = "horadesde";
    public static final String HORAHASTA = "horahasta";
    public static final String ACTIVO = "activo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String EXTERNALID = "externalid";
    public static final String ELIMINADO = "eliminado";
    public static final String ORGANISMOID = "organismoid";


    /**
     * Valor de busqueda de campo planificacionid
     */
    private Long planificacionid;

    /**
     * Lista de valores del campo planificacionid para busquedas tipo IN
     */
    private List<Long> planificacionidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblServidores
     */
    private TblServidoresQuery tblServidores;

    /**
     * Lista de valores del ID del campo tblServidores para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblServidores
     */
    private List<TblServidores> tblServidoresIdIn = new ArrayList<TblServidores>(0);

    /**
     * Permite buscar cuando campo tblServidores es NULL
     */
    private boolean tblServidoresIsNull = false;

    /**
     * Permite buscar cuando campo tblServidores es NOT NULL
     */
    private boolean tblServidoresIsNotNull = false;

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
     * Valor de busqueda de campo tblTipoPlanificaciones
     */
    private TblTipoPlanificacionesQuery tblTipoPlanificaciones;

    /**
     * Lista de valores del ID del campo tblTipoPlanificaciones para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblTipoPlanificaciones
     */
    private List<TblTipoPlanificaciones> tblTipoPlanificacionesIdIn = new ArrayList<TblTipoPlanificaciones>(0);

    /**
     * Permite buscar cuando campo tblTipoPlanificaciones es NULL
     */
    private boolean tblTipoPlanificacionesIsNull = false;

    /**
     * Permite buscar cuando campo tblTipoPlanificaciones es NOT NULL
     */
    private boolean tblTipoPlanificacionesIsNotNull = false;

    /**
     * Valor de busqueda de campo l
     */
    private String l;

    /**
     * Tipo de comparador para la busqueda por campo l
     */
    private TextComparator lComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo l para busquedas tipo IN
     */
    private List<String> lIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo l es NULL
     */
    private boolean lIsNull = false;

    /**
     * Permite buscar cuando campo l es NOT NULL
     */
    private boolean lIsNotNull = false;

    /**
     * Valor de busqueda de campo m
     */
    private String m;

    /**
     * Tipo de comparador para la busqueda por campo m
     */
    private TextComparator mComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo m para busquedas tipo IN
     */
    private List<String> mIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo m es NULL
     */
    private boolean mIsNull = false;

    /**
     * Permite buscar cuando campo m es NOT NULL
     */
    private boolean mIsNotNull = false;

    /**
     * Valor de busqueda de campo x
     */
    private String x;

    /**
     * Tipo de comparador para la busqueda por campo x
     */
    private TextComparator xComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo x para busquedas tipo IN
     */
    private List<String> xIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo x es NULL
     */
    private boolean xIsNull = false;

    /**
     * Permite buscar cuando campo x es NOT NULL
     */
    private boolean xIsNotNull = false;

    /**
     * Valor de busqueda de campo j
     */
    private String j;

    /**
     * Tipo de comparador para la busqueda por campo j
     */
    private TextComparator jComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo j para busquedas tipo IN
     */
    private List<String> jIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo j es NULL
     */
    private boolean jIsNull = false;

    /**
     * Permite buscar cuando campo j es NOT NULL
     */
    private boolean jIsNotNull = false;

    /**
     * Valor de busqueda de campo v
     */
    private String v;

    /**
     * Tipo de comparador para la busqueda por campo v
     */
    private TextComparator vComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo v para busquedas tipo IN
     */
    private List<String> vIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo v es NULL
     */
    private boolean vIsNull = false;

    /**
     * Permite buscar cuando campo v es NOT NULL
     */
    private boolean vIsNotNull = false;

    /**
     * Valor de busqueda de campo s
     */
    private String s;

    /**
     * Tipo de comparador para la busqueda por campo s
     */
    private TextComparator sComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo s para busquedas tipo IN
     */
    private List<String> sIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo s es NULL
     */
    private boolean sIsNull = false;

    /**
     * Permite buscar cuando campo s es NOT NULL
     */
    private boolean sIsNotNull = false;

    /**
     * Valor de busqueda de campo d
     */
    private String d;

    /**
     * Tipo de comparador para la busqueda por campo d
     */
    private TextComparator dComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo d para busquedas tipo IN
     */
    private List<String> dIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo d es NULL
     */
    private boolean dIsNull = false;

    /**
     * Permite buscar cuando campo d es NOT NULL
     */
    private boolean dIsNotNull = false;

    /**
     * Valor de busqueda de campo horadesde
     */
    private String horadesde;

    /**
     * Tipo de comparador para la busqueda por campo horadesde
     */
    private TextComparator horadesdeComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo horadesde para busquedas tipo IN
     */
    private List<String> horadesdeIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo horadesde es NULL
     */
    private boolean horadesdeIsNull = false;

    /**
     * Permite buscar cuando campo horadesde es NOT NULL
     */
    private boolean horadesdeIsNotNull = false;

    /**
     * Valor de busqueda de campo horahasta
     */
    private String horahasta;

    /**
     * Tipo de comparador para la busqueda por campo horahasta
     */
    private TextComparator horahastaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo horahasta para busquedas tipo IN
     */
    private List<String> horahastaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo horahasta es NULL
     */
    private boolean horahastaIsNull = false;

    /**
     * Permite buscar cuando campo horahasta es NOT NULL
     */
    private boolean horahastaIsNotNull = false;

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
     * Valor de busqueda de campo externalid
     */
    private Long externalid;

    /**
     * Lista de valores del campo externalid para busquedas tipo IN
     */
    private List<Long> externalidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo externalid es NULL
     */
    private boolean externalidIsNull = false;

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     */
    private boolean externalidIsNotNull = false;

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
     * Valor de busqueda de campo organismoid
     */
    private Long organismoid;

    /**
     * Lista de valores del campo organismoid para busquedas tipo IN
     */
    private List<Long> organismoidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo organismoid es NULL
     */
    private boolean organismoidIsNull = false;

    /**
     * Permite buscar cuando campo organismoid es NOT NULL
     */
    private boolean organismoidIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblServidores
     */
    private boolean innerJoinTblServidores = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblServidores
     */
    private boolean leftJoinTblServidores = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblServicios
     */
    private boolean innerJoinTblServicios = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblServicios
     */
    private boolean leftJoinTblServicios = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblTipoPlanificaciones
     */
    private boolean innerJoinTblTipoPlanificaciones = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblTipoPlanificaciones
     */
    private boolean leftJoinTblTipoPlanificaciones = false;

    /**
     * Constructor default
     */
    public TblPlanificacionesQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblPlanificacionesQuery(Long planificacionid) {
        setPlanificacionid(planificacionid);
    }

    /**
     * Valor de busqueda de campo planificacionid
     * @return Long.
     */
    public Long getPlanificacionid() {
        return planificacionid;
    }

    /**
     * Valor de busqueda de campo planificacionid
     * @param planificacionid Valor de seteo.
     */
    public void setPlanificacionid(Long planificacionid) {
        this.planificacionid = planificacionid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getPlanificacionidIn() {
        return this.planificacionidIn;
    }

    /**
     * @param planificacionid Valor a agregar.
     */
    public void addPlanificacionidIn(Long planificacionid) {
        this.planificacionidIn.add(planificacionid);
    }

    /**
     * Valor de busqueda de campo tblServidores
     * @return TblServidores.
     */
    public TblServidoresQuery getTblServidores() {
        return tblServidores;
    }

    /**
     * Valor de busqueda de campo tblServidores
     * @param tblServidores Valor de seteo.
     */
    public void setTblServidores(TblServidoresQuery tblServidores) {
        this.tblServidores = tblServidores;
    }

    /**
     * @return List<TblServidores>.
     */
    public List<TblServidores> getTblServidoresIdIn() {
        return this.tblServidoresIdIn;
    }

    /**
     * @param tblServidores Valor a agregar.
     */
    public void addTblServidoresIdIn(TblServidores tblServidores) {
        this.tblServidoresIdIn.add(tblServidores);
    }

    /**
     * Permite buscar cuando campo tblServidores es NULL
     * @return boolean.
     */
    public boolean isTblServidoresIsNull() {
        return tblServidoresIsNull;
    }

    /**
     * Permite buscar cuando campo tblServidores es NULL
     * @param tblServidoresIsNull Valor de seteo.
     */
    public void setTblServidoresIsNull(boolean tblServidoresIsNull) {
        this.tblServidoresIsNull = tblServidoresIsNull;
    }

    /**
     * Permite buscar cuando campo tblServidores es NOT NULL
     * @return boolean.
     */
    public boolean isTblServidoresIsNotNull() {
        return tblServidoresIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblServidores es NOT NULL
     * @param tblServidoresIsNotNull Valor de seteo.
     */
    public void setTblServidoresIsNotNull(boolean tblServidoresIsNotNull) {
        this.tblServidoresIsNotNull = tblServidoresIsNotNull;
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
     * Valor de busqueda de campo tblTipoPlanificaciones
     * @return TblTipoPlanificaciones.
     */
    public TblTipoPlanificacionesQuery getTblTipoPlanificaciones() {
        return tblTipoPlanificaciones;
    }

    /**
     * Valor de busqueda de campo tblTipoPlanificaciones
     * @param tblTipoPlanificaciones Valor de seteo.
     */
    public void setTblTipoPlanificaciones(TblTipoPlanificacionesQuery tblTipoPlanificaciones) {
        this.tblTipoPlanificaciones = tblTipoPlanificaciones;
    }

    /**
     * @return List<TblTipoPlanificaciones>.
     */
    public List<TblTipoPlanificaciones> getTblTipoPlanificacionesIdIn() {
        return this.tblTipoPlanificacionesIdIn;
    }

    /**
     * @param tblTipoPlanificaciones Valor a agregar.
     */
    public void addTblTipoPlanificacionesIdIn(TblTipoPlanificaciones tblTipoPlanificaciones) {
        this.tblTipoPlanificacionesIdIn.add(tblTipoPlanificaciones);
    }

    /**
     * Permite buscar cuando campo tblTipoPlanificaciones es NULL
     * @return boolean.
     */
    public boolean isTblTipoPlanificacionesIsNull() {
        return tblTipoPlanificacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblTipoPlanificaciones es NULL
     * @param tblTipoPlanificacionesIsNull Valor de seteo.
     */
    public void setTblTipoPlanificacionesIsNull(boolean tblTipoPlanificacionesIsNull) {
        this.tblTipoPlanificacionesIsNull = tblTipoPlanificacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblTipoPlanificaciones es NOT NULL
     * @return boolean.
     */
    public boolean isTblTipoPlanificacionesIsNotNull() {
        return tblTipoPlanificacionesIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblTipoPlanificaciones es NOT NULL
     * @param tblTipoPlanificacionesIsNotNull Valor de seteo.
     */
    public void setTblTipoPlanificacionesIsNotNull(boolean tblTipoPlanificacionesIsNotNull) {
        this.tblTipoPlanificacionesIsNotNull = tblTipoPlanificacionesIsNotNull;
    }

    /**
     * Valor de busqueda de campo l
     * @return String.
     */
    public String getL() {
        if (l != null) {
            switch (lComparator) {
	            case STARTS_WITH:
	                return l + "%";
	            case CONTAINS:
	                return "%" + l + "%";
	            case ENDS_WITH:
	                return "%" + l;
	            case EQUALS:
                	return l;
              	default:
	            	break;
            }
        }
        return l;
    }

    /**
     * Valor de busqueda de campo l
     * @param l Valor de seteo.
     */
    public void setL(String l) {
        this.l = l;
    }

    /**
     * Tipo de comparador para la busqueda por campo l
     * @return lComparator.
     */
    public TextComparator getLComparator() {
        return lComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo l
     * @param lComparator Valor de seteo.
     */
    public void setLComparator(TextComparator lComparator) {
        this.lComparator = lComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getLIn() {
        return this.lIn;
    }

    /**
     * @param l Valor a agregar.
     */
    public void addLIn(String l) {
        this.lIn.add(l);
    }

    /**
     * Permite buscar cuando campo l es NULL
     * @return boolean.
     */
    public boolean isLIsNull() {
        return lIsNull;
    }

    /**
     * Permite buscar cuando campo l es NULL
     * @param lIsNull Valor de seteo.
     */
    public void setLIsNull(boolean lIsNull) {
        this.lIsNull = lIsNull;
    }

    /**
     * Permite buscar cuando campo l es NOT NULL
     * @return boolean.
     */
    public boolean isLIsNotNull() {
        return lIsNotNull;
    }

    /**
     * Permite buscar cuando campo l es NOT NULL
     * @param lIsNotNull Valor de seteo.
     */
    public void setLIsNotNull(boolean lIsNotNull) {
        this.lIsNotNull = lIsNotNull;
    }

    /**
     * Valor de busqueda de campo m
     * @return String.
     */
    public String getM() {
        if (m != null) {
            switch (mComparator) {
	            case STARTS_WITH:
	                return m + "%";
	            case CONTAINS:
	                return "%" + m + "%";
	            case ENDS_WITH:
	                return "%" + m;
	            case EQUALS:
                	return m;
              	default:
	            	break;
            }
        }
        return m;
    }

    /**
     * Valor de busqueda de campo m
     * @param m Valor de seteo.
     */
    public void setM(String m) {
        this.m = m;
    }

    /**
     * Tipo de comparador para la busqueda por campo m
     * @return mComparator.
     */
    public TextComparator getMComparator() {
        return mComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo m
     * @param mComparator Valor de seteo.
     */
    public void setMComparator(TextComparator mComparator) {
        this.mComparator = mComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMIn() {
        return this.mIn;
    }

    /**
     * @param m Valor a agregar.
     */
    public void addMIn(String m) {
        this.mIn.add(m);
    }

    /**
     * Permite buscar cuando campo m es NULL
     * @return boolean.
     */
    public boolean isMIsNull() {
        return mIsNull;
    }

    /**
     * Permite buscar cuando campo m es NULL
     * @param mIsNull Valor de seteo.
     */
    public void setMIsNull(boolean mIsNull) {
        this.mIsNull = mIsNull;
    }

    /**
     * Permite buscar cuando campo m es NOT NULL
     * @return boolean.
     */
    public boolean isMIsNotNull() {
        return mIsNotNull;
    }

    /**
     * Permite buscar cuando campo m es NOT NULL
     * @param mIsNotNull Valor de seteo.
     */
    public void setMIsNotNull(boolean mIsNotNull) {
        this.mIsNotNull = mIsNotNull;
    }

    /**
     * Valor de busqueda de campo x
     * @return String.
     */
    public String getX() {
        if (x != null) {
            switch (xComparator) {
	            case STARTS_WITH:
	                return x + "%";
	            case CONTAINS:
	                return "%" + x + "%";
	            case ENDS_WITH:
	                return "%" + x;
	            case EQUALS:
                	return x;
              	default:
	            	break;
            }
        }
        return x;
    }

    /**
     * Valor de busqueda de campo x
     * @param x Valor de seteo.
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * Tipo de comparador para la busqueda por campo x
     * @return xComparator.
     */
    public TextComparator getXComparator() {
        return xComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo x
     * @param xComparator Valor de seteo.
     */
    public void setXComparator(TextComparator xComparator) {
        this.xComparator = xComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getXIn() {
        return this.xIn;
    }

    /**
     * @param x Valor a agregar.
     */
    public void addXIn(String x) {
        this.xIn.add(x);
    }

    /**
     * Permite buscar cuando campo x es NULL
     * @return boolean.
     */
    public boolean isXIsNull() {
        return xIsNull;
    }

    /**
     * Permite buscar cuando campo x es NULL
     * @param xIsNull Valor de seteo.
     */
    public void setXIsNull(boolean xIsNull) {
        this.xIsNull = xIsNull;
    }

    /**
     * Permite buscar cuando campo x es NOT NULL
     * @return boolean.
     */
    public boolean isXIsNotNull() {
        return xIsNotNull;
    }

    /**
     * Permite buscar cuando campo x es NOT NULL
     * @param xIsNotNull Valor de seteo.
     */
    public void setXIsNotNull(boolean xIsNotNull) {
        this.xIsNotNull = xIsNotNull;
    }

    /**
     * Valor de busqueda de campo j
     * @return String.
     */
    public String getJ() {
        if (j != null) {
            switch (jComparator) {
	            case STARTS_WITH:
	                return j + "%";
	            case CONTAINS:
	                return "%" + j + "%";
	            case ENDS_WITH:
	                return "%" + j;
	            case EQUALS:
                	return j;
              	default:
	            	break;
            }
        }
        return j;
    }

    /**
     * Valor de busqueda de campo j
     * @param j Valor de seteo.
     */
    public void setJ(String j) {
        this.j = j;
    }

    /**
     * Tipo de comparador para la busqueda por campo j
     * @return jComparator.
     */
    public TextComparator getJComparator() {
        return jComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo j
     * @param jComparator Valor de seteo.
     */
    public void setJComparator(TextComparator jComparator) {
        this.jComparator = jComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getJIn() {
        return this.jIn;
    }

    /**
     * @param j Valor a agregar.
     */
    public void addJIn(String j) {
        this.jIn.add(j);
    }

    /**
     * Permite buscar cuando campo j es NULL
     * @return boolean.
     */
    public boolean isJIsNull() {
        return jIsNull;
    }

    /**
     * Permite buscar cuando campo j es NULL
     * @param jIsNull Valor de seteo.
     */
    public void setJIsNull(boolean jIsNull) {
        this.jIsNull = jIsNull;
    }

    /**
     * Permite buscar cuando campo j es NOT NULL
     * @return boolean.
     */
    public boolean isJIsNotNull() {
        return jIsNotNull;
    }

    /**
     * Permite buscar cuando campo j es NOT NULL
     * @param jIsNotNull Valor de seteo.
     */
    public void setJIsNotNull(boolean jIsNotNull) {
        this.jIsNotNull = jIsNotNull;
    }

    /**
     * Valor de busqueda de campo v
     * @return String.
     */
    public String getV() {
        if (v != null) {
            switch (vComparator) {
	            case STARTS_WITH:
	                return v + "%";
	            case CONTAINS:
	                return "%" + v + "%";
	            case ENDS_WITH:
	                return "%" + v;
	            case EQUALS:
                	return v;
              	default:
	            	break;
            }
        }
        return v;
    }

    /**
     * Valor de busqueda de campo v
     * @param v Valor de seteo.
     */
    public void setV(String v) {
        this.v = v;
    }

    /**
     * Tipo de comparador para la busqueda por campo v
     * @return vComparator.
     */
    public TextComparator getVComparator() {
        return vComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo v
     * @param vComparator Valor de seteo.
     */
    public void setVComparator(TextComparator vComparator) {
        this.vComparator = vComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getVIn() {
        return this.vIn;
    }

    /**
     * @param v Valor a agregar.
     */
    public void addVIn(String v) {
        this.vIn.add(v);
    }

    /**
     * Permite buscar cuando campo v es NULL
     * @return boolean.
     */
    public boolean isVIsNull() {
        return vIsNull;
    }

    /**
     * Permite buscar cuando campo v es NULL
     * @param vIsNull Valor de seteo.
     */
    public void setVIsNull(boolean vIsNull) {
        this.vIsNull = vIsNull;
    }

    /**
     * Permite buscar cuando campo v es NOT NULL
     * @return boolean.
     */
    public boolean isVIsNotNull() {
        return vIsNotNull;
    }

    /**
     * Permite buscar cuando campo v es NOT NULL
     * @param vIsNotNull Valor de seteo.
     */
    public void setVIsNotNull(boolean vIsNotNull) {
        this.vIsNotNull = vIsNotNull;
    }

    /**
     * Valor de busqueda de campo s
     * @return String.
     */
    public String getS() {
        if (s != null) {
            switch (sComparator) {
	            case STARTS_WITH:
	                return s + "%";
	            case CONTAINS:
	                return "%" + s + "%";
	            case ENDS_WITH:
	                return "%" + s;
	            case EQUALS:
                	return s;
              	default:
	            	break;
            }
        }
        return s;
    }

    /**
     * Valor de busqueda de campo s
     * @param s Valor de seteo.
     */
    public void setS(String s) {
        this.s = s;
    }

    /**
     * Tipo de comparador para la busqueda por campo s
     * @return sComparator.
     */
    public TextComparator getSComparator() {
        return sComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo s
     * @param sComparator Valor de seteo.
     */
    public void setSComparator(TextComparator sComparator) {
        this.sComparator = sComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getSIn() {
        return this.sIn;
    }

    /**
     * @param s Valor a agregar.
     */
    public void addSIn(String s) {
        this.sIn.add(s);
    }

    /**
     * Permite buscar cuando campo s es NULL
     * @return boolean.
     */
    public boolean isSIsNull() {
        return sIsNull;
    }

    /**
     * Permite buscar cuando campo s es NULL
     * @param sIsNull Valor de seteo.
     */
    public void setSIsNull(boolean sIsNull) {
        this.sIsNull = sIsNull;
    }

    /**
     * Permite buscar cuando campo s es NOT NULL
     * @return boolean.
     */
    public boolean isSIsNotNull() {
        return sIsNotNull;
    }

    /**
     * Permite buscar cuando campo s es NOT NULL
     * @param sIsNotNull Valor de seteo.
     */
    public void setSIsNotNull(boolean sIsNotNull) {
        this.sIsNotNull = sIsNotNull;
    }

    /**
     * Valor de busqueda de campo d
     * @return String.
     */
    public String getD() {
        if (d != null) {
            switch (dComparator) {
	            case STARTS_WITH:
	                return d + "%";
	            case CONTAINS:
	                return "%" + d + "%";
	            case ENDS_WITH:
	                return "%" + d;
	            case EQUALS:
                	return d;
              	default:
	            	break;
            }
        }
        return d;
    }

    /**
     * Valor de busqueda de campo d
     * @param d Valor de seteo.
     */
    public void setD(String d) {
        this.d = d;
    }

    /**
     * Tipo de comparador para la busqueda por campo d
     * @return dComparator.
     */
    public TextComparator getDComparator() {
        return dComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo d
     * @param dComparator Valor de seteo.
     */
    public void setDComparator(TextComparator dComparator) {
        this.dComparator = dComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDIn() {
        return this.dIn;
    }

    /**
     * @param d Valor a agregar.
     */
    public void addDIn(String d) {
        this.dIn.add(d);
    }

    /**
     * Permite buscar cuando campo d es NULL
     * @return boolean.
     */
    public boolean isDIsNull() {
        return dIsNull;
    }

    /**
     * Permite buscar cuando campo d es NULL
     * @param dIsNull Valor de seteo.
     */
    public void setDIsNull(boolean dIsNull) {
        this.dIsNull = dIsNull;
    }

    /**
     * Permite buscar cuando campo d es NOT NULL
     * @return boolean.
     */
    public boolean isDIsNotNull() {
        return dIsNotNull;
    }

    /**
     * Permite buscar cuando campo d es NOT NULL
     * @param dIsNotNull Valor de seteo.
     */
    public void setDIsNotNull(boolean dIsNotNull) {
        this.dIsNotNull = dIsNotNull;
    }

    /**
     * Valor de busqueda de campo horadesde
     * @return String.
     */
    public String getHoradesde() {
        if (horadesde != null) {
            switch (horadesdeComparator) {
	            case STARTS_WITH:
	                return horadesde + "%";
	            case CONTAINS:
	                return "%" + horadesde + "%";
	            case ENDS_WITH:
	                return "%" + horadesde;
	            case EQUALS:
                	return horadesde;
              	default:
	            	break;
            }
        }
        return horadesde;
    }

    /**
     * Valor de busqueda de campo horadesde
     * @param horadesde Valor de seteo.
     */
    public void setHoradesde(String horadesde) {
        this.horadesde = horadesde;
    }

    /**
     * Tipo de comparador para la busqueda por campo horadesde
     * @return horadesdeComparator.
     */
    public TextComparator getHoradesdeComparator() {
        return horadesdeComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo horadesde
     * @param horadesdeComparator Valor de seteo.
     */
    public void setHoradesdeComparator(TextComparator horadesdeComparator) {
        this.horadesdeComparator = horadesdeComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getHoradesdeIn() {
        return this.horadesdeIn;
    }

    /**
     * @param horadesde Valor a agregar.
     */
    public void addHoradesdeIn(String horadesde) {
        this.horadesdeIn.add(horadesde);
    }

    /**
     * Permite buscar cuando campo horadesde es NULL
     * @return boolean.
     */
    public boolean isHoradesdeIsNull() {
        return horadesdeIsNull;
    }

    /**
     * Permite buscar cuando campo horadesde es NULL
     * @param horadesdeIsNull Valor de seteo.
     */
    public void setHoradesdeIsNull(boolean horadesdeIsNull) {
        this.horadesdeIsNull = horadesdeIsNull;
    }

    /**
     * Permite buscar cuando campo horadesde es NOT NULL
     * @return boolean.
     */
    public boolean isHoradesdeIsNotNull() {
        return horadesdeIsNotNull;
    }

    /**
     * Permite buscar cuando campo horadesde es NOT NULL
     * @param horadesdeIsNotNull Valor de seteo.
     */
    public void setHoradesdeIsNotNull(boolean horadesdeIsNotNull) {
        this.horadesdeIsNotNull = horadesdeIsNotNull;
    }

    /**
     * Valor de busqueda de campo horahasta
     * @return String.
     */
    public String getHorahasta() {
        if (horahasta != null) {
            switch (horahastaComparator) {
	            case STARTS_WITH:
	                return horahasta + "%";
	            case CONTAINS:
	                return "%" + horahasta + "%";
	            case ENDS_WITH:
	                return "%" + horahasta;
	            case EQUALS:
                	return horahasta;
              	default:
	            	break;
            }
        }
        return horahasta;
    }

    /**
     * Valor de busqueda de campo horahasta
     * @param horahasta Valor de seteo.
     */
    public void setHorahasta(String horahasta) {
        this.horahasta = horahasta;
    }

    /**
     * Tipo de comparador para la busqueda por campo horahasta
     * @return horahastaComparator.
     */
    public TextComparator getHorahastaComparator() {
        return horahastaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo horahasta
     * @param horahastaComparator Valor de seteo.
     */
    public void setHorahastaComparator(TextComparator horahastaComparator) {
        this.horahastaComparator = horahastaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getHorahastaIn() {
        return this.horahastaIn;
    }

    /**
     * @param horahasta Valor a agregar.
     */
    public void addHorahastaIn(String horahasta) {
        this.horahastaIn.add(horahasta);
    }

    /**
     * Permite buscar cuando campo horahasta es NULL
     * @return boolean.
     */
    public boolean isHorahastaIsNull() {
        return horahastaIsNull;
    }

    /**
     * Permite buscar cuando campo horahasta es NULL
     * @param horahastaIsNull Valor de seteo.
     */
    public void setHorahastaIsNull(boolean horahastaIsNull) {
        this.horahastaIsNull = horahastaIsNull;
    }

    /**
     * Permite buscar cuando campo horahasta es NOT NULL
     * @return boolean.
     */
    public boolean isHorahastaIsNotNull() {
        return horahastaIsNotNull;
    }

    /**
     * Permite buscar cuando campo horahasta es NOT NULL
     * @param horahastaIsNotNull Valor de seteo.
     */
    public void setHorahastaIsNotNull(boolean horahastaIsNotNull) {
        this.horahastaIsNotNull = horahastaIsNotNull;
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
     * Valor de busqueda de campo externalid
     * @return Long.
     */
    public Long getExternalid() {
        return externalid;
    }

    /**
     * Valor de busqueda de campo externalid
     * @param externalid Valor de seteo.
     */
    public void setExternalid(Long externalid) {
        this.externalid = externalid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getExternalidIn() {
        return this.externalidIn;
    }

    /**
     * @param externalid Valor a agregar.
     */
    public void addExternalidIn(Long externalid) {
        this.externalidIn.add(externalid);
    }

    /**
     * Permite buscar cuando campo externalid es NULL
     * @return boolean.
     */
    public boolean isExternalidIsNull() {
        return externalidIsNull;
    }

    /**
     * Permite buscar cuando campo externalid es NULL
     * @param externalidIsNull Valor de seteo.
     */
    public void setExternalidIsNull(boolean externalidIsNull) {
        this.externalidIsNull = externalidIsNull;
    }

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     * @return boolean.
     */
    public boolean isExternalidIsNotNull() {
        return externalidIsNotNull;
    }

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     * @param externalidIsNotNull Valor de seteo.
     */
    public void setExternalidIsNotNull(boolean externalidIsNotNull) {
        this.externalidIsNotNull = externalidIsNotNull;
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
     * Valor de busqueda de campo organismoid
     * @return Long.
     */
    public Long getOrganismoid() {
        return organismoid;
    }

    /**
     * Valor de busqueda de campo organismoid
     * @param organismoid Valor de seteo.
     */
    public void setOrganismoid(Long organismoid) {
        this.organismoid = organismoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getOrganismoidIn() {
        return this.organismoidIn;
    }

    /**
     * @param organismoid Valor a agregar.
     */
    public void addOrganismoidIn(Long organismoid) {
        this.organismoidIn.add(organismoid);
    }

    /**
     * Permite buscar cuando campo organismoid es NULL
     * @return boolean.
     */
    public boolean isOrganismoidIsNull() {
        return organismoidIsNull;
    }

    /**
     * Permite buscar cuando campo organismoid es NULL
     * @param organismoidIsNull Valor de seteo.
     */
    public void setOrganismoidIsNull(boolean organismoidIsNull) {
        this.organismoidIsNull = organismoidIsNull;
    }

    /**
     * Permite buscar cuando campo organismoid es NOT NULL
     * @return boolean.
     */
    public boolean isOrganismoidIsNotNull() {
        return organismoidIsNotNull;
    }

    /**
     * Permite buscar cuando campo organismoid es NOT NULL
     * @param organismoidIsNotNull Valor de seteo.
     */
    public void setOrganismoidIsNotNull(boolean organismoidIsNotNull) {
        this.organismoidIsNotNull = organismoidIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblServidores() {
        return innerJoinTblServidores;
    }

    /**
     * @param innerJoinTblServidores Valor de seteo.
     */
    public void setInnerJoinTblServidores(boolean innerJoinTblServidores) {
        this.innerJoinTblServidores = innerJoinTblServidores;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblServidores() {
        return leftJoinTblServidores;
    }

    /**
     * @param leftJoinTblServidores Valor de seteo.
     */
    public void setLeftJoinTblServidores(boolean leftJoinTblServidores) {
        this.leftJoinTblServidores = leftJoinTblServidores;
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
     * @return boolean.
     */
    public boolean isInnerJoinTblTipoPlanificaciones() {
        return innerJoinTblTipoPlanificaciones;
    }

    /**
     * @param innerJoinTblTipoPlanificaciones Valor de seteo.
     */
    public void setInnerJoinTblTipoPlanificaciones(boolean innerJoinTblTipoPlanificaciones) {
        this.innerJoinTblTipoPlanificaciones = innerJoinTblTipoPlanificaciones;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblTipoPlanificaciones() {
        return leftJoinTblTipoPlanificaciones;
    }

    /**
     * @param leftJoinTblTipoPlanificaciones Valor de seteo.
     */
    public void setLeftJoinTblTipoPlanificaciones(boolean leftJoinTblTipoPlanificaciones) {
        this.leftJoinTblTipoPlanificaciones = leftJoinTblTipoPlanificaciones;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getPlanificacionid() != null) {
            criteria.add(Restrictions.eq(PLANIFICACIONID, getPlanificacionid()));
        }

        if (getPlanificacionidIn().size() > 0) {
            criteria.add(Restrictions.in(PLANIFICACIONID, getPlanificacionidIn()));
        }

        // Campo entidad padre tblServidores
        
        // Si se hace join fetch con el padre
        Criteria tblServidoresCriteria = null;
        if (isInnerJoinTblServidores()) {
            tblServidoresCriteria = criteria.createCriteria(TBLSERVIDORES, "a_" + TBLSERVIDORES, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblServidores()) {
            tblServidoresCriteria = criteria.createCriteria(TBLSERVIDORES, "a_" + TBLSERVIDORES, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblServidores() != null) {
            if (getTblServidores().getServidorid() == null) {
                if (tblServidoresCriteria == null) {
                    tblServidoresCriteria = criteria.createCriteria(TBLSERVIDORES, "a_" + TBLSERVIDORES);
                }
                getTblServidores().addCriteria(tblServidoresCriteria, useOrder);
            } else {
                TblServidores parent = new TblServidores();
                parent.setServidorid(getTblServidores().getServidorid());
                criteria.add(Restrictions.eq(TBLSERVIDORES, parent));
            }
        }

        if (getTblServidoresIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLSERVIDORES, getTblServidoresIdIn()));
        }

        if (isTblServidoresIsNull()) {
            criteria.add(Restrictions.isNull(TBLSERVIDORES));
        }

        if (isTblServidoresIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLSERVIDORES));
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

        // Campo entidad padre tblTipoPlanificaciones
        
        // Si se hace join fetch con el padre
        Criteria tblTipoPlanificacionesCriteria = null;
        if (isInnerJoinTblTipoPlanificaciones()) {
            tblTipoPlanificacionesCriteria = criteria.createCriteria(TBLTIPOPLANIFICACIONES, "a_" + TBLTIPOPLANIFICACIONES, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblTipoPlanificaciones()) {
            tblTipoPlanificacionesCriteria = criteria.createCriteria(TBLTIPOPLANIFICACIONES, "a_" + TBLTIPOPLANIFICACIONES, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblTipoPlanificaciones() != null) {
            if (getTblTipoPlanificaciones().getTipoplanificacionid() == null) {
                if (tblTipoPlanificacionesCriteria == null) {
                    tblTipoPlanificacionesCriteria = criteria.createCriteria(TBLTIPOPLANIFICACIONES, "a_" + TBLTIPOPLANIFICACIONES);
                }
                getTblTipoPlanificaciones().addCriteria(tblTipoPlanificacionesCriteria, useOrder);
            } else {
                TblTipoPlanificaciones parent = new TblTipoPlanificaciones();
                parent.setTipoplanificacionid(getTblTipoPlanificaciones().getTipoplanificacionid());
                criteria.add(Restrictions.eq(TBLTIPOPLANIFICACIONES, parent));
            }
        }

        if (getTblTipoPlanificacionesIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLTIPOPLANIFICACIONES, getTblTipoPlanificacionesIdIn()));
        }

        if (isTblTipoPlanificacionesIsNull()) {
            criteria.add(Restrictions.isNull(TBLTIPOPLANIFICACIONES));
        }

        if (isTblTipoPlanificacionesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLTIPOPLANIFICACIONES));
        }

        if (getL() != null) {
            if (getLComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(L, getL()));
            } 
            else if (getLComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(L, getL()));
            }
            else if (getLComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(L, getL())));
            }
            else {
                criteria.add(Restrictions.like(L, getL()));
            }
        }

        if (getLIn().size() > 0) {
            criteria.add(Restrictions.in(L, getLIn()));
        }

        if (isLIsNull()) {
            criteria.add(Restrictions.isNull(L));
        }

        if (isLIsNotNull()) {
            criteria.add(Restrictions.isNotNull(L));
        }

        if (getM() != null) {
            if (getMComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(M, getM()));
            } 
            else if (getMComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(M, getM()));
            }
            else if (getMComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(M, getM())));
            }
            else {
                criteria.add(Restrictions.like(M, getM()));
            }
        }

        if (getMIn().size() > 0) {
            criteria.add(Restrictions.in(M, getMIn()));
        }

        if (isMIsNull()) {
            criteria.add(Restrictions.isNull(M));
        }

        if (isMIsNotNull()) {
            criteria.add(Restrictions.isNotNull(M));
        }

        if (getX() != null) {
            if (getXComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(X, getX()));
            } 
            else if (getXComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(X, getX()));
            }
            else if (getXComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(X, getX())));
            }
            else {
                criteria.add(Restrictions.like(X, getX()));
            }
        }

        if (getXIn().size() > 0) {
            criteria.add(Restrictions.in(X, getXIn()));
        }

        if (isXIsNull()) {
            criteria.add(Restrictions.isNull(X));
        }

        if (isXIsNotNull()) {
            criteria.add(Restrictions.isNotNull(X));
        }

        if (getJ() != null) {
            if (getJComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(J, getJ()));
            } 
            else if (getJComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(J, getJ()));
            }
            else if (getJComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(J, getJ())));
            }
            else {
                criteria.add(Restrictions.like(J, getJ()));
            }
        }

        if (getJIn().size() > 0) {
            criteria.add(Restrictions.in(J, getJIn()));
        }

        if (isJIsNull()) {
            criteria.add(Restrictions.isNull(J));
        }

        if (isJIsNotNull()) {
            criteria.add(Restrictions.isNotNull(J));
        }

        if (getV() != null) {
            if (getVComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(V, getV()));
            } 
            else if (getVComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(V, getV()));
            }
            else if (getVComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(V, getV())));
            }
            else {
                criteria.add(Restrictions.like(V, getV()));
            }
        }

        if (getVIn().size() > 0) {
            criteria.add(Restrictions.in(V, getVIn()));
        }

        if (isVIsNull()) {
            criteria.add(Restrictions.isNull(V));
        }

        if (isVIsNotNull()) {
            criteria.add(Restrictions.isNotNull(V));
        }

        if (getS() != null) {
            if (getSComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(S, getS()));
            } 
            else if (getSComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(S, getS()));
            }
            else if (getSComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(S, getS())));
            }
            else {
                criteria.add(Restrictions.like(S, getS()));
            }
        }

        if (getSIn().size() > 0) {
            criteria.add(Restrictions.in(S, getSIn()));
        }

        if (isSIsNull()) {
            criteria.add(Restrictions.isNull(S));
        }

        if (isSIsNotNull()) {
            criteria.add(Restrictions.isNotNull(S));
        }

        if (getD() != null) {
            if (getDComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(D, getD()));
            } 
            else if (getDComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(D, getD()));
            }
            else if (getDComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(D, getD())));
            }
            else {
                criteria.add(Restrictions.like(D, getD()));
            }
        }

        if (getDIn().size() > 0) {
            criteria.add(Restrictions.in(D, getDIn()));
        }

        if (isDIsNull()) {
            criteria.add(Restrictions.isNull(D));
        }

        if (isDIsNotNull()) {
            criteria.add(Restrictions.isNotNull(D));
        }

        if (getHoradesde() != null) {
            if (getHoradesdeComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(HORADESDE, getHoradesde()));
            } 
            else if (getHoradesdeComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(HORADESDE, getHoradesde()));
            }
            else if (getHoradesdeComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(HORADESDE, getHoradesde())));
            }
            else {
                criteria.add(Restrictions.like(HORADESDE, getHoradesde()));
            }
        }

        if (getHoradesdeIn().size() > 0) {
            criteria.add(Restrictions.in(HORADESDE, getHoradesdeIn()));
        }

        if (isHoradesdeIsNull()) {
            criteria.add(Restrictions.isNull(HORADESDE));
        }

        if (isHoradesdeIsNotNull()) {
            criteria.add(Restrictions.isNotNull(HORADESDE));
        }

        if (getHorahasta() != null) {
            if (getHorahastaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(HORAHASTA, getHorahasta()));
            } 
            else if (getHorahastaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(HORAHASTA, getHorahasta()));
            }
            else if (getHorahastaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(HORAHASTA, getHorahasta())));
            }
            else {
                criteria.add(Restrictions.like(HORAHASTA, getHorahasta()));
            }
        }

        if (getHorahastaIn().size() > 0) {
            criteria.add(Restrictions.in(HORAHASTA, getHorahastaIn()));
        }

        if (isHorahastaIsNull()) {
            criteria.add(Restrictions.isNull(HORAHASTA));
        }

        if (isHorahastaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(HORAHASTA));
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

        if (getExternalid() != null) {
            criteria.add(Restrictions.eq(EXTERNALID, getExternalid()));
        }

        if (getExternalidIn().size() > 0) {
            criteria.add(Restrictions.in(EXTERNALID, getExternalidIn()));
        }

        if (isExternalidIsNull()) {
            criteria.add(Restrictions.isNull(EXTERNALID));
        }

        if (isExternalidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(EXTERNALID));
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

        if (getOrganismoid() != null) {
            criteria.add(Restrictions.eq(ORGANISMOID, getOrganismoid()));
        }

        if (getOrganismoidIn().size() > 0) {
            criteria.add(Restrictions.in(ORGANISMOID, getOrganismoidIn()));
        }

        if (isOrganismoidIsNull()) {
            criteria.add(Restrictions.isNull(ORGANISMOID));
        }

        if (isOrganismoidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ORGANISMOID));
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
 
