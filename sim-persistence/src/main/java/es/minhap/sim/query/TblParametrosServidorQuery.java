/*
 *
 * archivo: TblParametrosServidorQuery.java
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
import es.minhap.sim.model.TblParametrosServidor;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.model.TblTiposParametros;

/**
 * Clase con criterios de busqueda para la entidad TblParametrosServidor
 */
public class TblParametrosServidorQuery extends AbstractHibernateQueryEntity<TblParametrosServidor> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String PARAMETROSERVIDORID = "parametroservidorid";
    public static final String TBLSERVIDORES = "tblServidores";
    public static final String TBLTIPOSPARAMETROS = "tblTiposParametros";
    public static final String VALOR = "valor";
    public static final String ACTIVO = "activo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String EXTERNALID = "externalid";


    /**
     * Valor de busqueda de campo parametroservidorid
     */
    private Long parametroservidorid;

    /**
     * Lista de valores del campo parametroservidorid para busquedas tipo IN
     */
    private List<Long> parametroservidoridIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo tblTiposParametros
     */
    private TblTiposParametrosQuery tblTiposParametros;

    /**
     * Lista de valores del ID del campo tblTiposParametros para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblTiposParametros
     */
    private List<TblTiposParametros> tblTiposParametrosIdIn = new ArrayList<TblTiposParametros>(0);

    /**
     * Permite buscar cuando campo tblTiposParametros es NULL
     */
    private boolean tblTiposParametrosIsNull = false;

    /**
     * Permite buscar cuando campo tblTiposParametros es NOT NULL
     */
    private boolean tblTiposParametrosIsNotNull = false;

    /**
     * Valor de busqueda de campo valor
     */
    private String valor;

    /**
     * Tipo de comparador para la busqueda por campo valor
     */
    private TextComparator valorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo valor para busquedas tipo IN
     */
    private List<String> valorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo valor es NULL
     */
    private boolean valorIsNull = false;

    /**
     * Permite buscar cuando campo valor es NOT NULL
     */
    private boolean valorIsNotNull = false;

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
     * Indica si en la consulta se hace un inner join con el padre tblServidores
     */
    private boolean innerJoinTblServidores = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblServidores
     */
    private boolean leftJoinTblServidores = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblTiposParametros
     */
    private boolean innerJoinTblTiposParametros = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblTiposParametros
     */
    private boolean leftJoinTblTiposParametros = false;

    /**
     * Constructor default
     */
    public TblParametrosServidorQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblParametrosServidorQuery(Long parametroservidorid) {
        setParametroservidorid(parametroservidorid);
    }

    /**
     * Valor de busqueda de campo parametroservidorid
     * @return Long.
     */
    public Long getParametroservidorid() {
        return parametroservidorid;
    }

    /**
     * Valor de busqueda de campo parametroservidorid
     * @param parametroservidorid Valor de seteo.
     */
    public void setParametroservidorid(Long parametroservidorid) {
        this.parametroservidorid = parametroservidorid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getParametroservidoridIn() {
        return this.parametroservidoridIn;
    }

    /**
     * @param parametroservidorid Valor a agregar.
     */
    public void addParametroservidoridIn(Long parametroservidorid) {
        this.parametroservidoridIn.add(parametroservidorid);
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
     * Valor de busqueda de campo tblTiposParametros
     * @return TblTiposParametros.
     */
    public TblTiposParametrosQuery getTblTiposParametros() {
        return tblTiposParametros;
    }

    /**
     * Valor de busqueda de campo tblTiposParametros
     * @param tblTiposParametros Valor de seteo.
     */
    public void setTblTiposParametros(TblTiposParametrosQuery tblTiposParametros) {
        this.tblTiposParametros = tblTiposParametros;
    }

    /**
     * @return List<TblTiposParametros>.
     */
    public List<TblTiposParametros> getTblTiposParametrosIdIn() {
        return this.tblTiposParametrosIdIn;
    }

    /**
     * @param tblTiposParametros Valor a agregar.
     */
    public void addTblTiposParametrosIdIn(TblTiposParametros tblTiposParametros) {
        this.tblTiposParametrosIdIn.add(tblTiposParametros);
    }

    /**
     * Permite buscar cuando campo tblTiposParametros es NULL
     * @return boolean.
     */
    public boolean isTblTiposParametrosIsNull() {
        return tblTiposParametrosIsNull;
    }

    /**
     * Permite buscar cuando campo tblTiposParametros es NULL
     * @param tblTiposParametrosIsNull Valor de seteo.
     */
    public void setTblTiposParametrosIsNull(boolean tblTiposParametrosIsNull) {
        this.tblTiposParametrosIsNull = tblTiposParametrosIsNull;
    }

    /**
     * Permite buscar cuando campo tblTiposParametros es NOT NULL
     * @return boolean.
     */
    public boolean isTblTiposParametrosIsNotNull() {
        return tblTiposParametrosIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblTiposParametros es NOT NULL
     * @param tblTiposParametrosIsNotNull Valor de seteo.
     */
    public void setTblTiposParametrosIsNotNull(boolean tblTiposParametrosIsNotNull) {
        this.tblTiposParametrosIsNotNull = tblTiposParametrosIsNotNull;
    }

    /**
     * Valor de busqueda de campo valor
     * @return String.
     */
    public String getValor() {
        if (valor != null) {
            switch (valorComparator) {
	            case STARTS_WITH:
	                return valor + "%";
	            case CONTAINS:
	                return "%" + valor + "%";
	            case ENDS_WITH:
	                return "%" + valor;
	            case EQUALS:
                	return valor;
              	default:
	            	break;
            }
        }
        return valor;
    }

    /**
     * Valor de busqueda de campo valor
     * @param valor Valor de seteo.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Tipo de comparador para la busqueda por campo valor
     * @return valorComparator.
     */
    public TextComparator getValorComparator() {
        return valorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo valor
     * @param valorComparator Valor de seteo.
     */
    public void setValorComparator(TextComparator valorComparator) {
        this.valorComparator = valorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getValorIn() {
        return this.valorIn;
    }

    /**
     * @param valor Valor a agregar.
     */
    public void addValorIn(String valor) {
        this.valorIn.add(valor);
    }

    /**
     * Permite buscar cuando campo valor es NULL
     * @return boolean.
     */
    public boolean isValorIsNull() {
        return valorIsNull;
    }

    /**
     * Permite buscar cuando campo valor es NULL
     * @param valorIsNull Valor de seteo.
     */
    public void setValorIsNull(boolean valorIsNull) {
        this.valorIsNull = valorIsNull;
    }

    /**
     * Permite buscar cuando campo valor es NOT NULL
     * @return boolean.
     */
    public boolean isValorIsNotNull() {
        return valorIsNotNull;
    }

    /**
     * Permite buscar cuando campo valor es NOT NULL
     * @param valorIsNotNull Valor de seteo.
     */
    public void setValorIsNotNull(boolean valorIsNotNull) {
        this.valorIsNotNull = valorIsNotNull;
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
    public boolean isInnerJoinTblTiposParametros() {
        return innerJoinTblTiposParametros;
    }

    /**
     * @param innerJoinTblTiposParametros Valor de seteo.
     */
    public void setInnerJoinTblTiposParametros(boolean innerJoinTblTiposParametros) {
        this.innerJoinTblTiposParametros = innerJoinTblTiposParametros;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblTiposParametros() {
        return leftJoinTblTiposParametros;
    }

    /**
     * @param leftJoinTblTiposParametros Valor de seteo.
     */
    public void setLeftJoinTblTiposParametros(boolean leftJoinTblTiposParametros) {
        this.leftJoinTblTiposParametros = leftJoinTblTiposParametros;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getParametroservidorid() != null) {
            criteria.add(Restrictions.eq(PARAMETROSERVIDORID, getParametroservidorid()));
        }

        if (getParametroservidoridIn().size() > 0) {
            criteria.add(Restrictions.in(PARAMETROSERVIDORID, getParametroservidoridIn()));
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

        // Campo entidad padre tblTiposParametros
        
        // Si se hace join fetch con el padre
        Criteria tblTiposParametrosCriteria = null;
        if (isInnerJoinTblTiposParametros()) {
            tblTiposParametrosCriteria = criteria.createCriteria(TBLTIPOSPARAMETROS, "a_" + TBLTIPOSPARAMETROS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblTiposParametros()) {
            tblTiposParametrosCriteria = criteria.createCriteria(TBLTIPOSPARAMETROS, "a_" + TBLTIPOSPARAMETROS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblTiposParametros() != null) {
            if (getTblTiposParametros().getTipoparametroid() == null) {
                if (tblTiposParametrosCriteria == null) {
                    tblTiposParametrosCriteria = criteria.createCriteria(TBLTIPOSPARAMETROS, "a_" + TBLTIPOSPARAMETROS);
                }
                getTblTiposParametros().addCriteria(tblTiposParametrosCriteria, useOrder);
            } else {
                TblTiposParametros parent = new TblTiposParametros();
                parent.setTipoparametroid(getTblTiposParametros().getTipoparametroid());
                criteria.add(Restrictions.eq(TBLTIPOSPARAMETROS, parent));
            }
        }

        if (getTblTiposParametrosIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLTIPOSPARAMETROS, getTblTiposParametrosIdIn()));
        }

        if (isTblTiposParametrosIsNull()) {
            criteria.add(Restrictions.isNull(TBLTIPOSPARAMETROS));
        }

        if (isTblTiposParametrosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLTIPOSPARAMETROS));
        }

        if (getValor() != null) {
            if (getValorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(VALOR, getValor()));
            } 
            else if (getValorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(VALOR, getValor()));
            }
            else if (getValorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(VALOR, getValor())));
            }
            else {
                criteria.add(Restrictions.like(VALOR, getValor()));
            }
        }

        if (getValorIn().size() > 0) {
            criteria.add(Restrictions.in(VALOR, getValorIn()));
        }

        if (isValorIsNull()) {
            criteria.add(Restrictions.isNull(VALOR));
        }

        if (isValorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(VALOR));
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
 
