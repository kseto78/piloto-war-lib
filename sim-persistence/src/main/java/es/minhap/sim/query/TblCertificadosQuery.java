/*
 *
 * archivo: TblCertificadosQuery.java
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
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.CriteriaSpecification;

import es.minhap.sim.model.TblCertificados;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblServicios;

import java.util.Date;

import es.minhap.common.util.DateUtil;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;

/**
 * Clase con criterios de busqueda para la entidad TblCertificados
 */
public class TblCertificadosQuery extends AbstractHibernateQueryEntity<TblCertificados> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "����������������";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String CERTIFICADOID = "certificadoid";
    public static final String TBLAPLICACIONES = "tblAplicaciones";
    public static final String TBLSERVICIOS = "tblServicios";
    public static final String ISSUER = "issuer";
    public static final String SERIAL = "serial";
    public static final String FECHACREACION = "fechaCreacion";
    public static final String FECHAACTUALIZACION = "fechaActualizacion";
    public static final String MODIFICADOPOR = "modificadoPor";
    public static final String CADUCADO = "caducado";


    /**
     * Valor de busqueda de campo certificadoid
     */
    private Long certificadoid;

    /**
     * Lista de valores del campo certificadoid para busquedas tipo IN
     */
    private List<Long> certificadoidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblAplicaciones
     */
    private TblAplicacionesQuery tblAplicaciones;

    /**
     * Lista de valores del ID del campo tblAplicaciones para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblAplicaciones
     */
    private List<TblAplicaciones> tblAplicacionesIdIn = new ArrayList<TblAplicaciones>(0);

    /**
     * Permite buscar cuando campo tblAplicaciones es NULL
     */
    private boolean tblAplicacionesIsNull = false;

    /**
     * Permite buscar cuando campo tblAplicaciones es NOT NULL
     */
    private boolean tblAplicacionesIsNotNull = false;

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
     * Valor de busqueda de campo issuer
     */
    private String issuer;

    /**
     * Tipo de comparador para la busqueda por campo issuer
     */
    private TextComparator issuerComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo issuer para busquedas tipo IN
     */
    private List<String> issuerIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo issuer es NULL
     */
    private boolean issuerIsNull = false;

    /**
     * Permite buscar cuando campo issuer es NOT NULL
     */
    private boolean issuerIsNotNull = false;

    /**
     * Valor de busqueda de campo serial
     */
    private String serial;

    /**
     * Tipo de comparador para la busqueda por campo serial
     */
    private TextComparator serialComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo serial para busquedas tipo IN
     */
    private List<String> serialIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo serial es NULL
     */
    private boolean serialIsNull = false;

    /**
     * Permite buscar cuando campo serial es NOT NULL
     */
    private boolean serialIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaCreacion
     */
    private Date fechaCreacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaCreacion
     */
    private Date fechaCreacionMax;

    /**
     * Permite buscar cuando campo fechaCreacion es NULL
     */
    private boolean fechaCreacionIsNull = false;

    /**
     * Permite buscar cuando campo fechaCreacion es NOT NULL
     */
    private boolean fechaCreacionIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaActualizacion
     */
    private Date fechaActualizacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaActualizacion
     */
    private Date fechaActualizacionMax;

    /**
     * Permite buscar cuando campo fechaActualizacion es NULL
     */
    private boolean fechaActualizacionIsNull = false;

    /**
     * Permite buscar cuando campo fechaActualizacion es NOT NULL
     */
    private boolean fechaActualizacionIsNotNull = false;

    /**
     * Valor de busqueda de campo modificadoPor
     */
    private String modificadoPor;

    /**
     * Tipo de comparador para la busqueda por campo modificadoPor
     */
    private TextComparator modificadoPorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo modificadoPor para busquedas tipo IN
     */
    private List<String> modificadoPorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo modificadoPor es NULL
     */
    private boolean modificadoPorIsNull = false;

    /**
     * Permite buscar cuando campo modificadoPor es NOT NULL
     */
    private boolean modificadoPorIsNotNull = false;

    /**
     * Valor de busqueda de campo caducado
     */
    private Boolean caducado;

    /**
     * Permite buscar cuando campo caducado es NULL
     */
    private boolean caducadoIsNull = false;

    /**
     * Permite buscar cuando campo caducado es NOT NULL
     */
    private boolean caducadoIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblAplicaciones
     */
    private boolean innerJoinTblAplicaciones = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblAplicaciones
     */
    private boolean leftJoinTblAplicaciones = false;

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
    public TblCertificadosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblCertificadosQuery(Long certificadoid) {
        setCertificadoid(certificadoid);
    }

    /**
     * Valor de busqueda de campo certificadoid
     * @return Long.
     */
    public Long getCertificadoid() {
        return certificadoid;
    }

    /**
     * Valor de busqueda de campo certificadoid
     * @param certificadoid Valor de seteo.
     */
    public void setCertificadoid(Long certificadoid) {
        this.certificadoid = certificadoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getCertificadoidIn() {
        return this.certificadoidIn;
    }

    /**
     * @param certificadoid Valor a agregar.
     */
    public void addCertificadoidIn(Long certificadoid) {
        this.certificadoidIn.add(certificadoid);
    }

    /**
     * Valor de busqueda de campo tblAplicaciones
     * @return TblAplicaciones.
     */
    public TblAplicacionesQuery getTblAplicaciones() {
        return tblAplicaciones;
    }

    /**
     * Valor de busqueda de campo tblAplicaciones
     * @param tblAplicaciones Valor de seteo.
     */
    public void setTblAplicaciones(TblAplicacionesQuery tblAplicaciones) {
        this.tblAplicaciones = tblAplicaciones;
    }

    /**
     * @return List<TblAplicaciones>.
     */
    public List<TblAplicaciones> getTblAplicacionesIdIn() {
        return this.tblAplicacionesIdIn;
    }

    /**
     * @param tblAplicaciones Valor a agregar.
     */
    public void addTblAplicacionesIdIn(TblAplicaciones tblAplicaciones) {
        this.tblAplicacionesIdIn.add(tblAplicaciones);
    }

    /**
     * Permite buscar cuando campo tblAplicaciones es NULL
     * @return boolean.
     */
    public boolean isTblAplicacionesIsNull() {
        return tblAplicacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblAplicaciones es NULL
     * @param tblAplicacionesIsNull Valor de seteo.
     */
    public void setTblAplicacionesIsNull(boolean tblAplicacionesIsNull) {
        this.tblAplicacionesIsNull = tblAplicacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblAplicaciones es NOT NULL
     * @return boolean.
     */
    public boolean isTblAplicacionesIsNotNull() {
        return tblAplicacionesIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblAplicaciones es NOT NULL
     * @param tblAplicacionesIsNotNull Valor de seteo.
     */
    public void setTblAplicacionesIsNotNull(boolean tblAplicacionesIsNotNull) {
        this.tblAplicacionesIsNotNull = tblAplicacionesIsNotNull;
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
     * Valor de busqueda de campo issuer
     * @return String.
     */
    public String getIssuer() {
        if (issuer != null) {
            switch (issuerComparator) {
	            case STARTS_WITH:
	                return issuer + "%";
	            case CONTAINS:
	                return "%" + issuer + "%";
	            case ENDS_WITH:
	                return "%" + issuer;
	            case EQUALS:
                	return issuer;
              	default:
	            	break;
            }
        }
        return issuer;
    }

    /**
     * Valor de busqueda de campo issuer
     * @param issuer Valor de seteo.
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * Tipo de comparador para la busqueda por campo issuer
     * @return issuerComparator.
     */
    public TextComparator getIssuerComparator() {
        return issuerComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo issuer
     * @param issuerComparator Valor de seteo.
     */
    public void setIssuerComparator(TextComparator issuerComparator) {
        this.issuerComparator = issuerComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getIssuerIn() {
        return this.issuerIn;
    }

    /**
     * @param issuer Valor a agregar.
     */
    public void addIssuerIn(String issuer) {
        this.issuerIn.add(issuer);
    }

    /**
     * Permite buscar cuando campo issuer es NULL
     * @return boolean.
     */
    public boolean isIssuerIsNull() {
        return issuerIsNull;
    }

    /**
     * Permite buscar cuando campo issuer es NULL
     * @param issuerIsNull Valor de seteo.
     */
    public void setIssuerIsNull(boolean issuerIsNull) {
        this.issuerIsNull = issuerIsNull;
    }

    /**
     * Permite buscar cuando campo issuer es NOT NULL
     * @return boolean.
     */
    public boolean isIssuerIsNotNull() {
        return issuerIsNotNull;
    }

    /**
     * Permite buscar cuando campo issuer es NOT NULL
     * @param issuerIsNotNull Valor de seteo.
     */
    public void setIssuerIsNotNull(boolean issuerIsNotNull) {
        this.issuerIsNotNull = issuerIsNotNull;
    }

    /**
     * Valor de busqueda de campo serial
     * @return String.
     */
    public String getSerial() {
        if (serial != null) {
            switch (serialComparator) {
	            case STARTS_WITH:
	                return serial + "%";
	            case CONTAINS:
	                return "%" + serial + "%";
	            case ENDS_WITH:
	                return "%" + serial;
	            case EQUALS:
                	return serial;
              	default:
	            	break;
            }
        }
        return serial;
    }

    /**
     * Valor de busqueda de campo serial
     * @param serial Valor de seteo.
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * Tipo de comparador para la busqueda por campo serial
     * @return serialComparator.
     */
    public TextComparator getSerialComparator() {
        return serialComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo serial
     * @param serialComparator Valor de seteo.
     */
    public void setSerialComparator(TextComparator serialComparator) {
        this.serialComparator = serialComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getSerialIn() {
        return this.serialIn;
    }

    /**
     * @param serial Valor a agregar.
     */
    public void addSerialIn(String serial) {
        this.serialIn.add(serial);
    }

    /**
     * Permite buscar cuando campo serial es NULL
     * @return boolean.
     */
    public boolean isSerialIsNull() {
        return serialIsNull;
    }

    /**
     * Permite buscar cuando campo serial es NULL
     * @param serialIsNull Valor de seteo.
     */
    public void setSerialIsNull(boolean serialIsNull) {
        this.serialIsNull = serialIsNull;
    }

    /**
     * Permite buscar cuando campo serial es NOT NULL
     * @return boolean.
     */
    public boolean isSerialIsNotNull() {
        return serialIsNotNull;
    }

    /**
     * Permite buscar cuando campo serial es NOT NULL
     * @param serialIsNotNull Valor de seteo.
     */
    public void setSerialIsNotNull(boolean serialIsNotNull) {
        this.serialIsNotNull = serialIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaCreacion
     * @return ${field.getName()}Min.
     */
    public Date getFechaCreacionMin() {
        if (fechaCreacionMin != null) {
            return DateUtil.toDayBegin(fechaCreacionMin);
        }
        return fechaCreacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaCreacion
     * @param fechaCreacionMin Valor de seteo.
     */
    public void setFechaCreacionMin(Date fechaCreacionMin) {
        this.fechaCreacionMin = fechaCreacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaCreacion
     * @return fechaCreacionMax.
     */
    public Date getFechaCreacionMax() {
        if (fechaCreacionMax != null) {
            return DateUtil.toDayEnd(fechaCreacionMax);
        }
        return fechaCreacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaCreacion
     * @param fechaCreacionMax Valor de seteo.
     */
    public void setFechaCreacionMax(Date fechaCreacionMax) {
        this.fechaCreacionMax = fechaCreacionMax;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NULL
     * @return boolean.
     */
    public boolean isFechaCreacionIsNull() {
        return fechaCreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NULL
     * @param fechaCreacionIsNull Valor de seteo.
     */
    public void setFechaCreacionIsNull(boolean fechaCreacionIsNull) {
        this.fechaCreacionIsNull = fechaCreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaCreacionIsNotNull() {
        return fechaCreacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaCreacion es NOT NULL
     * @param fechaCreacionIsNotNull Valor de seteo.
     */
    public void setFechaCreacionIsNotNull(boolean fechaCreacionIsNotNull) {
        this.fechaCreacionIsNotNull = fechaCreacionIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaActualizacion
     * @return ${field.getName()}Min.
     */
    public Date getFechaActualizacionMin() {
        if (fechaActualizacionMin != null) {
            return DateUtil.toDayBegin(fechaActualizacionMin);
        }
        return fechaActualizacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaActualizacion
     * @param fechaActualizacionMin Valor de seteo.
     */
    public void setFechaActualizacionMin(Date fechaActualizacionMin) {
        this.fechaActualizacionMin = fechaActualizacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaActualizacion
     * @return fechaActualizacionMax.
     */
    public Date getFechaActualizacionMax() {
        if (fechaActualizacionMax != null) {
            return DateUtil.toDayEnd(fechaActualizacionMax);
        }
        return fechaActualizacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaActualizacion
     * @param fechaActualizacionMax Valor de seteo.
     */
    public void setFechaActualizacionMax(Date fechaActualizacionMax) {
        this.fechaActualizacionMax = fechaActualizacionMax;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NULL
     * @return boolean.
     */
    public boolean isFechaActualizacionIsNull() {
        return fechaActualizacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NULL
     * @param fechaActualizacionIsNull Valor de seteo.
     */
    public void setFechaActualizacionIsNull(boolean fechaActualizacionIsNull) {
        this.fechaActualizacionIsNull = fechaActualizacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaActualizacionIsNotNull() {
        return fechaActualizacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaActualizacion es NOT NULL
     * @param fechaActualizacionIsNotNull Valor de seteo.
     */
    public void setFechaActualizacionIsNotNull(boolean fechaActualizacionIsNotNull) {
        this.fechaActualizacionIsNotNull = fechaActualizacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo modificadoPor
     * @return String.
     */
    public String getModificadoPor() {
        if (modificadoPor != null) {
            switch (modificadoPorComparator) {
	            case STARTS_WITH:
	                return modificadoPor + "%";
	            case CONTAINS:
	                return "%" + modificadoPor + "%";
	            case ENDS_WITH:
	                return "%" + modificadoPor;
	            case EQUALS:
                	return modificadoPor;
              	default:
	            	break;
            }
        }
        return modificadoPor;
    }

    /**
     * Valor de busqueda de campo modificadoPor
     * @param modificadoPor Valor de seteo.
     */
    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    /**
     * Tipo de comparador para la busqueda por campo modificadoPor
     * @return modificadoPorComparator.
     */
    public TextComparator getModificadoPorComparator() {
        return modificadoPorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo modificadoPor
     * @param modificadoPorComparator Valor de seteo.
     */
    public void setModificadoPorComparator(TextComparator modificadoPorComparator) {
        this.modificadoPorComparator = modificadoPorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getModificadoPorIn() {
        return this.modificadoPorIn;
    }

    /**
     * @param modificadoPor Valor a agregar.
     */
    public void addModificadoPorIn(String modificadoPor) {
        this.modificadoPorIn.add(modificadoPor);
    }

    /**
     * Permite buscar cuando campo modificadoPor es NULL
     * @return boolean.
     */
    public boolean isModificadoPorIsNull() {
        return modificadoPorIsNull;
    }

    /**
     * Permite buscar cuando campo modificadoPor es NULL
     * @param modificadoPorIsNull Valor de seteo.
     */
    public void setModificadoPorIsNull(boolean modificadoPorIsNull) {
        this.modificadoPorIsNull = modificadoPorIsNull;
    }

    /**
     * Permite buscar cuando campo modificadoPor es NOT NULL
     * @return boolean.
     */
    public boolean isModificadoPorIsNotNull() {
        return modificadoPorIsNotNull;
    }

    /**
     * Permite buscar cuando campo modificadoPor es NOT NULL
     * @param modificadoPorIsNotNull Valor de seteo.
     */
    public void setModificadoPorIsNotNull(boolean modificadoPorIsNotNull) {
        this.modificadoPorIsNotNull = modificadoPorIsNotNull;
    }

    /**
     * Valor de busqueda de campo caducado
     * @return Boolean.
     */
    public Boolean getCaducado() {
        return caducado;
    }

    /**
     * Valor de busqueda de campo caducado
     * @param caducado Valor de seteo.
     */
    public void setCaducado(Boolean caducado) {
        this.caducado = caducado;
    }

    /**
     * Permite buscar cuando campo caducado es NULL
     * @return boolean.
     */
    public boolean isCaducadoIsNull() {
        return caducadoIsNull;
    }

    /**
     * Permite buscar cuando campo caducado es NULL
     * @param caducadoIsNull Valor de seteo.
     */
    public void setCaducadoIsNull(boolean caducadoIsNull) {
        this.caducadoIsNull = caducadoIsNull;
    }

    /**
     * Permite buscar cuando campo caducado es NOT NULL
     * @return boolean.
     */
    public boolean isCaducadoIsNotNull() {
        return caducadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo caducado es NOT NULL
     * @param caducadoIsNotNull Valor de seteo.
     */
    public void setCaducadoIsNotNull(boolean caducadoIsNotNull) {
        this.caducadoIsNotNull = caducadoIsNotNull;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblAplicaciones() {
        return innerJoinTblAplicaciones;
    }

    /**
     * @param innerJoinTblAplicaciones Valor de seteo.
     */
    public void setInnerJoinTblAplicaciones(boolean innerJoinTblAplicaciones) {
        this.innerJoinTblAplicaciones = innerJoinTblAplicaciones;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblAplicaciones() {
        return leftJoinTblAplicaciones;
    }

    /**
     * @param leftJoinTblAplicaciones Valor de seteo.
     */
    public void setLeftJoinTblAplicaciones(boolean leftJoinTblAplicaciones) {
        this.leftJoinTblAplicaciones = leftJoinTblAplicaciones;
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

        if (getCertificadoid() != null) {
            criteria.add(Restrictions.eq(CERTIFICADOID, getCertificadoid()));
        }

        if (getCertificadoidIn().size() > 0) {
            criteria.add(Restrictions.in(CERTIFICADOID, getCertificadoidIn()));
        }

        // Campo entidad padre tblAplicaciones
        
        // Si se hace join fetch con el padre
        Criteria tblAplicacionesCriteria = null;
        if (isInnerJoinTblAplicaciones()) {
            tblAplicacionesCriteria = criteria.createCriteria(TBLAPLICACIONES, "a_" + TBLAPLICACIONES, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblAplicaciones()) {
            tblAplicacionesCriteria = criteria.createCriteria(TBLAPLICACIONES, "a_" + TBLAPLICACIONES, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblAplicaciones() != null) {
            if (getTblAplicaciones().getAplicacionid() == null) {
                if (tblAplicacionesCriteria == null) {
                    tblAplicacionesCriteria = criteria.createCriteria(TBLAPLICACIONES, "a_" + TBLAPLICACIONES);
                }
                getTblAplicaciones().addCriteria(tblAplicacionesCriteria, useOrder);
            } else {
                TblAplicaciones parent = new TblAplicaciones();
                parent.setAplicacionid(getTblAplicaciones().getAplicacionid());
                criteria.add(Restrictions.eq(TBLAPLICACIONES, parent));
            }
        }

        if (getTblAplicacionesIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLAPLICACIONES, getTblAplicacionesIdIn()));
        }

        if (isTblAplicacionesIsNull()) {
            criteria.add(Restrictions.isNull(TBLAPLICACIONES));
        }

        if (isTblAplicacionesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLAPLICACIONES));
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

        if (getIssuer() != null) {
            if (getIssuerComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ISSUER, getIssuer()));
            } 
            else if (getIssuerComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ISSUER, getIssuer()));
            }
            else if (getIssuerComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ISSUER, getIssuer())));
            }
            else {
                criteria.add(Restrictions.like(ISSUER, getIssuer()));
            }
        }

        if (getIssuerIn().size() > 0) {
            criteria.add(Restrictions.in(ISSUER, getIssuerIn()));
        }

        if (isIssuerIsNull()) {
            criteria.add(Restrictions.isNull(ISSUER));
        }

        if (isIssuerIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ISSUER));
        }

        if (getSerial() != null) {
            if (getSerialComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SERIAL, getSerial()));
            } 
            else if (getSerialComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SERIAL, getSerial()));
            }
            else if (getSerialComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SERIAL, getSerial())));
            }
            else {
                criteria.add(Restrictions.like(SERIAL, getSerial()));
            }
        }

        if (getSerialIn().size() > 0) {
            criteria.add(Restrictions.in(SERIAL, getSerialIn()));
        }

        if (isSerialIsNull()) {
            criteria.add(Restrictions.isNull(SERIAL));
        }

        if (isSerialIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERIAL));
        }

        if (getFechaCreacionMin() != null) {
            criteria.add(Restrictions.ge(FECHACREACION, getFechaCreacionMin()));
        }

        if (getFechaCreacionMax() != null) {
            criteria.add(Restrictions.le(FECHACREACION, getFechaCreacionMax()));
        }

        if (isFechaCreacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHACREACION));
        }

        if (isFechaCreacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHACREACION));
        }

        if (getFechaActualizacionMin() != null) {
            criteria.add(Restrictions.ge(FECHAACTUALIZACION, getFechaActualizacionMin()));
        }

        if (getFechaActualizacionMax() != null) {
            criteria.add(Restrictions.le(FECHAACTUALIZACION, getFechaActualizacionMax()));
        }

        if (isFechaActualizacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAACTUALIZACION));
        }

        if (isFechaActualizacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAACTUALIZACION));
        }

        if (getModificadoPor() != null) {
            if (getModificadoPorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MODIFICADOPOR, getModificadoPor()));
            } 
            else if (getModificadoPorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MODIFICADOPOR, getModificadoPor()));
            }
            else if (getModificadoPorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MODIFICADOPOR, getModificadoPor())));
            }
            else {
                criteria.add(Restrictions.like(MODIFICADOPOR, getModificadoPor()));
            }
        }

        if (getModificadoPorIn().size() > 0) {
            criteria.add(Restrictions.in(MODIFICADOPOR, getModificadoPorIn()));
        }

        if (isModificadoPorIsNull()) {
            criteria.add(Restrictions.isNull(MODIFICADOPOR));
        }

        if (isModificadoPorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MODIFICADOPOR));
        }

        if (getCaducado() != null) {
            criteria.add(Restrictions.eq(CADUCADO, getCaducado()));
        }

        if (isCaducadoIsNull()) {
            criteria.add(Restrictions.isNull(CADUCADO));
        }

        if (isCaducadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CADUCADO));
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
 
