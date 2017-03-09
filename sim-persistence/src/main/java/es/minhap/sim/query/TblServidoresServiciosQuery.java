/*
 *
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
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.model.TblServidoresServicios;

/**
 * Clase con criterios de busqueda para la entidad TblServidoresServicios
 */
public class TblServidoresServiciosQuery extends AbstractHibernateQueryEntity<TblServidoresServicios> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String SERVIDORSERVICIOID = "servidorservicioid";
    public static final String TBLSERVIDORES = "tblServidores";
    public static final String TBLSERVICIOS = "tblServicios";
    public static final String NUMINTENTOS = "numintentos";
    public static final String HEADERSMS = "headersms";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHACREACION = "fechacreacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String PROVEEDORUSUARIOSMS = "proveedorusuariosms";
    public static final String PROVEEDORPASSWORDSMS = "proveedorpasswordsms";
    public static final String PREFIJOSMS = "prefijosms";

    /**
     * Valor de busqueda de campo servidorservicioid
     */
    private Long servidorservicioid;

    /**
     * Lista de valores del campo servidorservicioid para busquedas tipo IN
     */
    private List<Long> servidorservicioidIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo numintentos
     */
    private Integer numintentos;

    /**
     * Lista de valores del campo numintentos para busquedas tipo IN
     */
    private List<Integer> numintentosIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo numintentos es NULL
     */
    private boolean numintentosIsNull = false;

    /**
     * Permite buscar cuando campo numintentos es NOT NULL
     */
    private boolean numintentosIsNotNull = false;

    /**
     * Valor de busqueda de campo headersms
     */
    private String headersms;

    /**
     * Tipo de comparador para la busqueda por campo headersms
     */
    private TextComparator headersmsComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo headersms para busquedas tipo IN
     */
    private List<String> headersmsIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo headersms es NULL
     */
    private boolean headersmsIsNull = false;

    /**
     * Permite buscar cuando campo headersms es NOT NULL
     */
    private boolean headersmsIsNotNull = false;

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
     * Valor de busqueda de campo proveedorusuariosms
     */
    private String proveedorusuariosms;

    /**
     * Tipo de comparador para la busqueda por campo proveedorusuariosms
     */
    private TextComparator proveedorusuariosmsComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo proveedorusuariosms para busquedas tipo IN
     */
    private List<String> proveedorusuariosmsIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo proveedorusuariosms es NULL
     */
    private boolean proveedorusuariosmsIsNull = false;

    /**
     * Permite buscar cuando campo proveedorusuariosms es NOT NULL
     */
    private boolean proveedorusuariosmsIsNotNull = false;

    /**
     * Valor de busqueda de campo proveedorpasswordsms
     */
    private String proveedorpasswordsms;

    /**
     * Tipo de comparador para la busqueda por campo proveedorpasswordsms
     */
    private TextComparator proveedorpasswordsmsComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo proveedorpasswordsms para busquedas tipo IN
     */
    private List<String> proveedorpasswordsmsIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NULL
     */
    private boolean proveedorpasswordsmsIsNull = false;

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NOT NULL
     */
    private boolean proveedorpasswordsmsIsNotNull = false;

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
     * Valor de busqueda de campo prefijosms
     */
    private String prefijosms;

    /**
     * Tipo de comparador para la busqueda por campo prefijosms
     */
    private TextComparator prefijosmsComparator = TextComparator.CONTAINS;

    /**
     * Constructor default
     */
    public TblServidoresServiciosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblServidoresServiciosQuery(Long servidorservicioid) {
        setServidorservicioid(servidorservicioid);
    }

    /**
     * Valor de busqueda de campo servidorservicioid
     * @return Long.
     */
    public Long getServidorservicioid() {
        return servidorservicioid;
    }

    /**
     * Valor de busqueda de campo servidorservicioid
     * @param servidorservicioid Valor de seteo.
     */
    public void setServidorservicioid(Long servidorservicioid) {
        this.servidorservicioid = servidorservicioid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getServidorservicioidIn() {
        return this.servidorservicioidIn;
    }

    /**
     * @param servidorservicioid Valor a agregar.
     */
    public void addServidorservicioidIn(Long servidorservicioid) {
        this.servidorservicioidIn.add(servidorservicioid);
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
     * Valor de busqueda de campo numintentos
     * @return Integer.
     */
    public Integer getNumintentos() {
        return numintentos;
    }

    /**
     * Valor de busqueda de campo numintentos
     * @param numintentos Valor de seteo.
     */
    public void setNumintentos(Integer numintentos) {
        this.numintentos = numintentos;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getNumintentosIn() {
        return this.numintentosIn;
    }

    /**
     * @param numintentos Valor a agregar.
     */
    public void addNumintentosIn(Integer numintentos) {
        this.numintentosIn.add(numintentos);
    }

    /**
     * Permite buscar cuando campo numintentos es NULL
     * @return boolean.
     */
    public boolean isNumintentosIsNull() {
        return numintentosIsNull;
    }

    /**
     * Permite buscar cuando campo numintentos es NULL
     * @param numintentosIsNull Valor de seteo.
     */
    public void setNumintentosIsNull(boolean numintentosIsNull) {
        this.numintentosIsNull = numintentosIsNull;
    }

    /**
     * Permite buscar cuando campo numintentos es NOT NULL
     * @return boolean.
     */
    public boolean isNumintentosIsNotNull() {
        return numintentosIsNotNull;
    }

    /**
     * Permite buscar cuando campo numintentos es NOT NULL
     * @param numintentosIsNotNull Valor de seteo.
     */
    public void setNumintentosIsNotNull(boolean numintentosIsNotNull) {
        this.numintentosIsNotNull = numintentosIsNotNull;
    }

    /**
     * Valor de busqueda de campo headersms
     * @return String.
     */
    public String getHeadersms() {
        if (headersms != null) {
            switch (headersmsComparator) {
	            case STARTS_WITH:
	                return headersms + "%";
	            case CONTAINS:
	                return "%" + headersms + "%";
	            case ENDS_WITH:
	                return "%" + headersms;
	            case EQUALS:
                	return headersms;
              	default:
	            	break;
            }
        }
        return headersms;
    }

    /**
     * Valor de busqueda de campo headersms
     * @param headersms Valor de seteo.
     */
    public void setHeadersms(String headersms) {
        this.headersms = headersms;
    }

    /**
     * Tipo de comparador para la busqueda por campo headersms
     * @return headersmsComparator.
     */
    public TextComparator getHeadersmsComparator() {
        return headersmsComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo headersms
     * @param headersmsComparator Valor de seteo.
     */
    public void setHeadersmsComparator(TextComparator headersmsComparator) {
        this.headersmsComparator = headersmsComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getHeadersmsIn() {
        return this.headersmsIn;
    }

    /**
     * @param headersms Valor a agregar.
     */
    public void addHeadersmsIn(String headersms) {
        this.headersmsIn.add(headersms);
    }

    /**
     * Permite buscar cuando campo headersms es NULL
     * @return boolean.
     */
    public boolean isHeadersmsIsNull() {
        return headersmsIsNull;
    }

    /**
     * Permite buscar cuando campo headersms es NULL
     * @param headersmsIsNull Valor de seteo.
     */
    public void setHeadersmsIsNull(boolean headersmsIsNull) {
        this.headersmsIsNull = headersmsIsNull;
    }

    /**
     * Permite buscar cuando campo headersms es NOT NULL
     * @return boolean.
     */
    public boolean isHeadersmsIsNotNull() {
        return headersmsIsNotNull;
    }

    /**
     * Permite buscar cuando campo headersms es NOT NULL
     * @param headersmsIsNotNull Valor de seteo.
     */
    public void setHeadersmsIsNotNull(boolean headersmsIsNotNull) {
        this.headersmsIsNotNull = headersmsIsNotNull;
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
     * Valor de busqueda de campo proveedorusuariosms
     * @return String.
     */
    public String getProveedorusuariosms() {
        if (proveedorusuariosms != null) {
            switch (proveedorusuariosmsComparator) {
	            case STARTS_WITH:
	                return proveedorusuariosms + "%";
	            case CONTAINS:
	                return "%" + proveedorusuariosms + "%";
	            case ENDS_WITH:
	                return "%" + proveedorusuariosms;
	            case EQUALS:
                	return proveedorusuariosms;
              	default:
	            	break;
            }
        }
        return proveedorusuariosms;
    }

    /**
     * Valor de busqueda de campo proveedorusuariosms
     * @param proveedorusuariosms Valor de seteo.
     */
    public void setProveedorusuariosms(String proveedorusuariosms) {
        this.proveedorusuariosms = proveedorusuariosms;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorusuariosms
     * @return proveedorusuariosmsComparator.
     */
    public TextComparator getProveedorusuariosmsComparator() {
        return proveedorusuariosmsComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorusuariosms
     * @param proveedorusuariosmsComparator Valor de seteo.
     */
    public void setProveedorusuariosmsComparator(TextComparator proveedorusuariosmsComparator) {
        this.proveedorusuariosmsComparator = proveedorusuariosmsComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getProveedorusuariosmsIn() {
        return this.proveedorusuariosmsIn;
    }

    /**
     * @param proveedorusuariosms Valor a agregar.
     */
    public void addProveedorusuariosmsIn(String proveedorusuariosms) {
        this.proveedorusuariosmsIn.add(proveedorusuariosms);
    }

    /**
     * Permite buscar cuando campo proveedorusuariosms es NULL
     * @return boolean.
     */
    public boolean isProveedorusuariosmsIsNull() {
        return proveedorusuariosmsIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorusuariosms es NULL
     * @param proveedorusuariosmsIsNull Valor de seteo.
     */
    public void setProveedorusuariosmsIsNull(boolean proveedorusuariosmsIsNull) {
        this.proveedorusuariosmsIsNull = proveedorusuariosmsIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorusuariosms es NOT NULL
     * @return boolean.
     */
    public boolean isProveedorusuariosmsIsNotNull() {
        return proveedorusuariosmsIsNotNull;
    }

    /**
     * Permite buscar cuando campo proveedorusuariosms es NOT NULL
     * @param proveedorusuariosmsIsNotNull Valor de seteo.
     */
    public void setProveedorusuariosmsIsNotNull(boolean proveedorusuariosmsIsNotNull) {
        this.proveedorusuariosmsIsNotNull = proveedorusuariosmsIsNotNull;
    }

    /**
     * Valor de busqueda de campo proveedorpasswordsms
     * @return String.
     */
    public String getProveedorpasswordsms() {
        if (proveedorpasswordsms != null) {
            switch (proveedorpasswordsmsComparator) {
	            case STARTS_WITH:
	                return proveedorpasswordsms + "%";
	            case CONTAINS:
	                return "%" + proveedorpasswordsms + "%";
	            case ENDS_WITH:
	                return "%" + proveedorpasswordsms;
	            case EQUALS:
                	return proveedorpasswordsms;
              	default:
	            	break;
            }
        }
        return proveedorpasswordsms;
    }

    /**
     * Valor de busqueda de campo proveedorpasswordsms
     * @param proveedorpasswordsms Valor de seteo.
     */
    public void setProveedorpasswordsms(String proveedorpasswordsms) {
        this.proveedorpasswordsms = proveedorpasswordsms;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorpasswordsms
     * @return proveedorpasswordsmsComparator.
     */
    public TextComparator getProveedorpasswordsmsComparator() {
        return proveedorpasswordsmsComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorpasswordsms
     * @param proveedorpasswordsmsComparator Valor de seteo.
     */
    public void setProveedorpasswordsmsComparator(TextComparator proveedorpasswordsmsComparator) {
        this.proveedorpasswordsmsComparator = proveedorpasswordsmsComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getProveedorpasswordsmsIn() {
        return this.proveedorpasswordsmsIn;
    }

    /**
     * @param proveedorpasswordsms Valor a agregar.
     */
    public void addProveedorpasswordsmsIn(String proveedorpasswordsms) {
        this.proveedorpasswordsmsIn.add(proveedorpasswordsms);
    }

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NULL
     * @return boolean.
     */
    public boolean isProveedorpasswordsmsIsNull() {
        return proveedorpasswordsmsIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NULL
     * @param proveedorpasswordsmsIsNull Valor de seteo.
     */
    public void setProveedorpasswordsmsIsNull(boolean proveedorpasswordsmsIsNull) {
        this.proveedorpasswordsmsIsNull = proveedorpasswordsmsIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NOT NULL
     * @return boolean.
     */
    public boolean isProveedorpasswordsmsIsNotNull() {
        return proveedorpasswordsmsIsNotNull;
    }

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NOT NULL
     * @param proveedorpasswordsmsIsNotNull Valor de seteo.
     */
    public void setProveedorpasswordsmsIsNotNull(boolean proveedorpasswordsmsIsNotNull) {
        this.proveedorpasswordsmsIsNotNull = proveedorpasswordsmsIsNotNull;
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
     * Valor de busqueda de campo prefijosms
     * @return String.
     */
    public String getPrefijosms() {
        if (prefijosms != null) {
            switch (prefijosmsComparator) {
	            case STARTS_WITH:
	                return prefijosms + "%";
	            case CONTAINS:
	                return "%" + prefijosms + "%";
	            case ENDS_WITH:
	                return "%" + prefijosms;
	            case EQUALS:
                	return prefijosms;
              	default:
	            	break;
            }
        }
        return prefijosms;
    }

    /**
     * Valor de busqueda de campo prefijosms
     * @param prefijosms Valor de seteo.
     */
    public void setPrefijosms(String prefijosms) {
        this.prefijosms = prefijosms;
    }

    /**
     * Tipo de comparador para la busqueda por campo prefijosmsComparator
     * @return prefijosmsComparator.
     */
    public TextComparator getPrefijosmsComparator() {
        return prefijosmsComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo prefijosmsComparator
     * @param prefijosmsComparator Valor de seteo.
     */
    public void setPrefijosmsComparator(TextComparator prefijosmsComparator) {
        this.prefijosmsComparator = prefijosmsComparator;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getServidorservicioid() != null) {
            criteria.add(Restrictions.eq(SERVIDORSERVICIOID, getServidorservicioid()));
        }

        if (getServidorservicioidIn().size() > 0) {
            criteria.add(Restrictions.in(SERVIDORSERVICIOID, getServidorservicioidIn()));
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

        if (getNumintentos() != null) {
            criteria.add(Restrictions.eq(NUMINTENTOS, getNumintentos()));
        }

        if (getNumintentosIn().size() > 0) {
            criteria.add(Restrictions.in(NUMINTENTOS, getNumintentosIn()));
        }

        if (isNumintentosIsNull()) {
            criteria.add(Restrictions.isNull(NUMINTENTOS));
        }

        if (isNumintentosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NUMINTENTOS));
        }

        if (getHeadersms() != null) {
            if (getHeadersmsComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(HEADERSMS, getHeadersms()));
            } 
            else if (getHeadersmsComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(HEADERSMS, getHeadersms()));
            }
            else if (getHeadersmsComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(HEADERSMS, getHeadersms())));
            }
            else {
                criteria.add(Restrictions.like(HEADERSMS, getHeadersms()));
            }
        }

        if (getHeadersmsIn().size() > 0) {
            criteria.add(Restrictions.in(HEADERSMS, getHeadersmsIn()));
        }

        if (isHeadersmsIsNull()) {
            criteria.add(Restrictions.isNull(HEADERSMS));
        }

        if (isHeadersmsIsNotNull()) {
            criteria.add(Restrictions.isNotNull(HEADERSMS));
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

        if (getProveedorusuariosms() != null) {
            if (getProveedorusuariosmsComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PROVEEDORUSUARIOSMS, getProveedorusuariosms()));
            } 
            else if (getProveedorusuariosmsComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PROVEEDORUSUARIOSMS, getProveedorusuariosms()));
            }
            else if (getProveedorusuariosmsComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PROVEEDORUSUARIOSMS, getProveedorusuariosms())));
            }
            else {
                criteria.add(Restrictions.like(PROVEEDORUSUARIOSMS, getProveedorusuariosms()));
            }
        }

        if (getProveedorusuariosmsIn().size() > 0) {
            criteria.add(Restrictions.in(PROVEEDORUSUARIOSMS, getProveedorusuariosmsIn()));
        }

        if (isProveedorusuariosmsIsNull()) {
            criteria.add(Restrictions.isNull(PROVEEDORUSUARIOSMS));
        }

        if (isProveedorusuariosmsIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PROVEEDORUSUARIOSMS));
        }

        if (getProveedorpasswordsms() != null) {
            if (getProveedorpasswordsmsComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PROVEEDORPASSWORDSMS, getProveedorpasswordsms()));
            } 
            else if (getProveedorpasswordsmsComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PROVEEDORPASSWORDSMS, getProveedorpasswordsms()));
            }
            else if (getProveedorpasswordsmsComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PROVEEDORPASSWORDSMS, getProveedorpasswordsms())));
            }
            else {
                criteria.add(Restrictions.like(PROVEEDORPASSWORDSMS, getProveedorpasswordsms()));
            }
        }

        if (getProveedorpasswordsmsIn().size() > 0) {
            criteria.add(Restrictions.in(PROVEEDORPASSWORDSMS, getProveedorpasswordsmsIn()));
        }

        if (isProveedorpasswordsmsIsNull()) {
            criteria.add(Restrictions.isNull(PROVEEDORPASSWORDSMS));
        }

        if (isProveedorpasswordsmsIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PROVEEDORPASSWORDSMS));
        }
        
        if (getPrefijosms() != null) {
            if (getPrefijosmsComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PREFIJOSMS, getPrefijosms()));
            } 
            else if (getPrefijosmsComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PREFIJOSMS, getPrefijosms()));
            }
            else if (getPrefijosmsComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PREFIJOSMS, getPrefijosms())));
            }
            else {
                criteria.add(Restrictions.like(PREFIJOSMS, getPrefijosms()));
            }
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
 
