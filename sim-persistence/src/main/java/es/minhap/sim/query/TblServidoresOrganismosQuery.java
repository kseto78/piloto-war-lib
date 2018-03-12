/*
 *
 * archivo: TblServidoresOrganismosQuery.java
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
import es.minhap.sim.model.TblServidoresOrganismos;

/**
 * Clase con criterios de busqueda para la entidad TblServidoresOrganismos
 */
public class TblServidoresOrganismosQuery extends AbstractHibernateQueryEntity<TblServidoresOrganismos> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String SERVIDORORGANISMOID = "servidororganismoid";
    public static final String SERVIDORID = "servidorid";
    public static final String ORGANISMOID = "organismoid";
    public static final String NUMINTENTOS = "numintentos";
    public static final String HEADERSMS = "headersms";
    public static final String PROVEEDORUSUARIOSMS = "proveedorusuariosms";
    public static final String PROVEEDORPASSWORDSMS = "proveedorpasswordsms";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHACREACION = "fechacreacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";


    /**
     * Valor de busqueda de campo servidororganismoid
     */
    private Long servidororganismoid;

    /**
     * Lista de valores del campo servidororganismoid para busquedas tipo IN
     */
    private List<Long> servidororganismoidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo servidororganismoid es NULL
     */
    private boolean servidororganismoidIsNull = false;

    /**
     * Permite buscar cuando campo servidororganismoid es NOT NULL
     */
    private boolean servidororganismoidIsNotNull = false;

    /**
     * Valor de busqueda de campo servidorid
     */
    private Long servidorid;

    /**
     * Lista de valores del campo servidorid para busquedas tipo IN
     */
    private List<Long> servidoridIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo servidorid es NULL
     */
    private boolean servidoridIsNull = false;

    /**
     * Permite buscar cuando campo servidorid es NOT NULL
     */
    private boolean servidoridIsNotNull = false;

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
     * Constructor default
     */
    public TblServidoresOrganismosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblServidoresOrganismosQuery(Long servidorOrganismoId) {
        setServidororganismoid(servidorOrganismoId);
    }

    /**
     * Valor de busqueda de campo servidororganismoid
     * @return Long.
     */
    public Long getServidororganismoid() {
        return servidororganismoid;
    }

    /**
     * Valor de busqueda de campo servidororganismoid
     * @param servidororganismoid Valor de seteo.
     */
    public void setServidororganismoid(Long servidororganismoid) {
        this.servidororganismoid = servidororganismoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getServidororganismoidIn() {
        return this.servidororganismoidIn;
    }

    /**
     * @param servidororganismoid Valor a agregar.
     */
    public void addServidororganismoidIn(Long servidororganismoid) {
        this.servidororganismoidIn.add(servidororganismoid);
    }

    /**
     * Permite buscar cuando campo servidororganismoid es NULL
     * @return boolean.
     */
    public boolean isServidororganismoidIsNull() {
        return servidororganismoidIsNull;
    }

    /**
     * Permite buscar cuando campo servidororganismoid es NULL
     * @param servidororganismoidIsNull Valor de seteo.
     */
    public void setServidororganismoidIsNull(boolean servidororganismoidIsNull) {
        this.servidororganismoidIsNull = servidororganismoidIsNull;
    }

    /**
     * Permite buscar cuando campo servidororganismoid es NOT NULL
     * @return boolean.
     */
    public boolean isServidororganismoidIsNotNull() {
        return servidororganismoidIsNotNull;
    }

    /**
     * Permite buscar cuando campo servidororganismoid es NOT NULL
     * @param servidororganismoidIsNotNull Valor de seteo.
     */
    public void setServidororganismoidIsNotNull(boolean servidororganismoidIsNotNull) {
        this.servidororganismoidIsNotNull = servidororganismoidIsNotNull;
    }

    /**
     * Valor de busqueda de campo servidorid
     * @return Long.
     */
    public Long getServidorid() {
        return servidorid;
    }

    /**
     * Valor de busqueda de campo servidorid
     * @param servidorid Valor de seteo.
     */
    public void setServidorid(Long servidorid) {
        this.servidorid = servidorid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getServidoridIn() {
        return this.servidoridIn;
    }

    /**
     * @param servidorid Valor a agregar.
     */
    public void addServidoridIn(Long servidorid) {
        this.servidoridIn.add(servidorid);
    }

    /**
     * Permite buscar cuando campo servidorid es NULL
     * @return boolean.
     */
    public boolean isServidoridIsNull() {
        return servidoridIsNull;
    }

    /**
     * Permite buscar cuando campo servidorid es NULL
     * @param servidoridIsNull Valor de seteo.
     */
    public void setServidoridIsNull(boolean servidoridIsNull) {
        this.servidoridIsNull = servidoridIsNull;
    }

    /**
     * Permite buscar cuando campo servidorid es NOT NULL
     * @return boolean.
     */
    public boolean isServidoridIsNotNull() {
        return servidoridIsNotNull;
    }

    /**
     * Permite buscar cuando campo servidorid es NOT NULL
     * @param servidoridIsNotNull Valor de seteo.
     */
    public void setServidoridIsNotNull(boolean servidoridIsNotNull) {
        this.servidoridIsNotNull = servidoridIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getServidororganismoid() != null) {
            criteria.add(Restrictions.eq(SERVIDORORGANISMOID, getServidororganismoid()));
        }

        if (getServidororganismoidIn().size() > 0) {
            criteria.add(Restrictions.in(SERVIDORORGANISMOID, getServidororganismoidIn()));
        }

        if (isServidororganismoidIsNull()) {
            criteria.add(Restrictions.isNull(SERVIDORORGANISMOID));
        }

        if (isServidororganismoidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVIDORORGANISMOID));
        }

        if (getServidorid() != null) {
            criteria.add(Restrictions.eq(SERVIDORID, getServidorid()));
        }

        if (getServidoridIn().size() > 0) {
            criteria.add(Restrictions.in(SERVIDORID, getServidoridIn()));
        }

        if (isServidoridIsNull()) {
            criteria.add(Restrictions.isNull(SERVIDORID));
        }

        if (isServidoridIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVIDORID));
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
 
