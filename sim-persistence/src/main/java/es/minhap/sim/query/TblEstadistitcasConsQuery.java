/*
 *
 * archivo: TblEstadistitcasConsQuery.java
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
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.sim.model.TblEstadistitcasCons;

/**
 * Clase con criterios de busqueda para la entidad TblEstadistitcasCons
 */
public class TblEstadistitcasConsQuery extends AbstractHibernateQueryEntity<TblEstadistitcasCons> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String ESTADISTICASCONSID = "estadisticasconsid";
    public static final String SERVIDORID = "servidorid";
    public static final String SERVIDORNOMBRE = "servidornombre";
    public static final String APLICACIONID = "aplicacionid";
    public static final String PLANIFICACIONID = "planificacionid";
    public static final String APLICACIONNOMBRE = "aplicacionnombre";
    public static final String SERVICIOID = "servicioid";
    public static final String SERVICIONOMBRE = "servicionombre";
    public static final String CANALID = "canalid";
    public static final String CANALNOMBRE = "canalnombre";
    public static final String ESTADOID = "estadoid";
    public static final String ESTADONOMBRE = "estadonombre";
    public static final String ANNO = "anno";
    public static final String MES = "mes";
    public static final String NUMTOTAL = "numtotal";
    public static final String DOCUSUARIO = "docusuario";
    public static final String CODSIA = "codsia";
    public static final String CODORGANISMO = "codorganismo";
    public static final String CODORGANISMOPAGADOR = "codorganismopagador";


    /**
     * Valor de busqueda de campo estadisticasconsid
     */
    private Long estadisticasconsid;

    /**
     * Lista de valores del campo estadisticasconsid para busquedas tipo IN
     */
    private List<Long> estadisticasconsidIn = new ArrayList<Long>(0);

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
     * Valor de busqueda de campo servidornombre
     */
    private String servidornombre;

    /**
     * Tipo de comparador para la busqueda por campo servidornombre
     */
    private TextComparator servidornombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo servidornombre para busquedas tipo IN
     */
    private List<String> servidornombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo servidornombre es NULL
     */
    private boolean servidornombreIsNull = false;

    /**
     * Permite buscar cuando campo servidornombre es NOT NULL
     */
    private boolean servidornombreIsNotNull = false;

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
     * Valor de busqueda de campo planificacionid
     */
    private Long planificacionid;

    /**
     * Lista de valores del campo planificacionid para busquedas tipo IN
     */
    private List<Long> planificacionidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo planificacionid es NULL
     */
    private boolean planificacionidIsNull = false;

    /**
     * Permite buscar cuando campo planificacionid es NOT NULL
     */
    private boolean planificacionidIsNotNull = false;

    /**
     * Valor de busqueda de campo aplicacionnombre
     */
    private String aplicacionnombre;

    /**
     * Tipo de comparador para la busqueda por campo aplicacionnombre
     */
    private TextComparator aplicacionnombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo aplicacionnombre para busquedas tipo IN
     */
    private List<String> aplicacionnombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo aplicacionnombre es NULL
     */
    private boolean aplicacionnombreIsNull = false;

    /**
     * Permite buscar cuando campo aplicacionnombre es NOT NULL
     */
    private boolean aplicacionnombreIsNotNull = false;

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
     * Valor de busqueda de campo servicionombre
     */
    private String servicionombre;

    /**
     * Tipo de comparador para la busqueda por campo servicionombre
     */
    private TextComparator servicionombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo servicionombre para busquedas tipo IN
     */
    private List<String> servicionombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo servicionombre es NULL
     */
    private boolean servicionombreIsNull = false;

    /**
     * Permite buscar cuando campo servicionombre es NOT NULL
     */
    private boolean servicionombreIsNotNull = false;

    /**
     * Valor de busqueda de campo canalid
     */
    private Long canalid;

    /**
     * Lista de valores del campo canalid para busquedas tipo IN
     */
    private List<Long> canalidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo canalid es NULL
     */
    private boolean canalidIsNull = false;

    /**
     * Permite buscar cuando campo canalid es NOT NULL
     */
    private boolean canalidIsNotNull = false;

    /**
     * Valor de busqueda de campo canalnombre
     */
    private String canalnombre;

    /**
     * Tipo de comparador para la busqueda por campo canalnombre
     */
    private TextComparator canalnombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo canalnombre para busquedas tipo IN
     */
    private List<String> canalnombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo canalnombre es NULL
     */
    private boolean canalnombreIsNull = false;

    /**
     * Permite buscar cuando campo canalnombre es NOT NULL
     */
    private boolean canalnombreIsNotNull = false;

    /**
     * Valor de busqueda de campo estadoid
     */
    private Long estadoid;

    /**
     * Lista de valores del campo estadoid para busquedas tipo IN
     */
    private List<Long> estadoidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo estadoid es NULL
     */
    private boolean estadoidIsNull = false;

    /**
     * Permite buscar cuando campo estadoid es NOT NULL
     */
    private boolean estadoidIsNotNull = false;

    /**
     * Valor de busqueda de campo estadonombre
     */
    private String estadonombre;

    /**
     * Tipo de comparador para la busqueda por campo estadonombre
     */
    private TextComparator estadonombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo estadonombre para busquedas tipo IN
     */
    private List<String> estadonombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo estadonombre es NULL
     */
    private boolean estadonombreIsNull = false;

    /**
     * Permite buscar cuando campo estadonombre es NOT NULL
     */
    private boolean estadonombreIsNotNull = false;

    /**
     * Valor de busqueda de campo anno
     */
    private Integer anno;

    /**
     * Lista de valores del campo anno para busquedas tipo IN
     */
    private List<Integer> annoIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo anno es NULL
     */
    private boolean annoIsNull = false;

    /**
     * Permite buscar cuando campo anno es NOT NULL
     */
    private boolean annoIsNotNull = false;

    /**
     * Valor de busqueda de campo mes
     */
    private String mes;

    /**
     * Tipo de comparador para la busqueda por campo mes
     */
    private TextComparator mesComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo mes para busquedas tipo IN
     */
    private List<String> mesIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo mes es NULL
     */
    private boolean mesIsNull = false;

    /**
     * Permite buscar cuando campo mes es NOT NULL
     */
    private boolean mesIsNotNull = false;

    /**
     * Valor de busqueda de campo numtotal
     */
    private Integer numtotal;

    /**
     * Lista de valores del campo numtotal para busquedas tipo IN
     */
    private List<Integer> numtotalIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo numtotal es NULL
     */
    private boolean numtotalIsNull = false;

    /**
     * Permite buscar cuando campo numtotal es NOT NULL
     */
    private boolean numtotalIsNotNull = false;

    /**
     * Valor de busqueda de campo docusuario
     */
    private String docusuario;

    /**
     * Tipo de comparador para la busqueda por campo docusuario
     */
    private TextComparator docusuarioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo docusuario para busquedas tipo IN
     */
    private List<String> docusuarioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo docusuario es NULL
     */
    private boolean docusuarioIsNull = false;

    /**
     * Permite buscar cuando campo docusuario es NOT NULL
     */
    private boolean docusuarioIsNotNull = false;

    /**
     * Valor de busqueda de campo codsia
     */
    private String codsia;

    /**
     * Tipo de comparador para la busqueda por campo codsia
     */
    private TextComparator codsiaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codsia para busquedas tipo IN
     */
    private List<String> codsiaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codsia es NULL
     */
    private boolean codsiaIsNull = false;

    /**
     * Permite buscar cuando campo codsia es NOT NULL
     */
    private boolean codsiaIsNotNull = false;

    /**
     * Valor de busqueda de campo codorganismo
     */
    private String codorganismo;

    /**
     * Tipo de comparador para la busqueda por campo codorganismo
     */
    private TextComparator codorganismoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codorganismo para busquedas tipo IN
     */
    private List<String> codorganismoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codorganismo es NULL
     */
    private boolean codorganismoIsNull = false;

    /**
     * Permite buscar cuando campo codorganismo es NOT NULL
     */
    private boolean codorganismoIsNotNull = false;

    /**
     * Valor de busqueda de campo codorganismopagador
     */
    private String codorganismopagador;

    /**
     * Tipo de comparador para la busqueda por campo codorganismopagador
     */
    private TextComparator codorganismopagadorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codorganismopagador para busquedas tipo IN
     */
    private List<String> codorganismopagadorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codorganismopagador es NULL
     */
    private boolean codorganismopagadorIsNull = false;

    /**
     * Permite buscar cuando campo codorganismopagador es NOT NULL
     */
    private boolean codorganismopagadorIsNotNull = false;

    /**
     * Constructor default
     */
    public TblEstadistitcasConsQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblEstadistitcasConsQuery(Long estadisticasconsid) {
        setEstadisticasconsid(estadisticasconsid);
    }

    /**
     * Valor de busqueda de campo estadisticasconsid
     * @return Long.
     */
    public Long getEstadisticasconsid() {
        return estadisticasconsid;
    }

    /**
     * Valor de busqueda de campo estadisticasconsid
     * @param estadisticasconsid Valor de seteo.
     */
    public void setEstadisticasconsid(Long estadisticasconsid) {
        this.estadisticasconsid = estadisticasconsid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getEstadisticasconsidIn() {
        return this.estadisticasconsidIn;
    }

    /**
     * @param estadisticasconsid Valor a agregar.
     */
    public void addEstadisticasconsidIn(Long estadisticasconsid) {
        this.estadisticasconsidIn.add(estadisticasconsid);
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
     * Valor de busqueda de campo servidornombre
     * @return String.
     */
    public String getServidornombre() {
        if (servidornombre != null) {
            switch (servidornombreComparator) {
	            case STARTS_WITH:
	                return servidornombre + "%";
	            case CONTAINS:
	                return "%" + servidornombre + "%";
	            case ENDS_WITH:
	                return "%" + servidornombre;
	            case EQUALS:
                	return servidornombre;
              	default:
	            	break;
            }
        }
        return servidornombre;
    }

    /**
     * Valor de busqueda de campo servidornombre
     * @param servidornombre Valor de seteo.
     */
    public void setServidornombre(String servidornombre) {
        this.servidornombre = servidornombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo servidornombre
     * @return servidornombreComparator.
     */
    public TextComparator getServidornombreComparator() {
        return servidornombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo servidornombre
     * @param servidornombreComparator Valor de seteo.
     */
    public void setServidornombreComparator(TextComparator servidornombreComparator) {
        this.servidornombreComparator = servidornombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getServidornombreIn() {
        return this.servidornombreIn;
    }

    /**
     * @param servidornombre Valor a agregar.
     */
    public void addServidornombreIn(String servidornombre) {
        this.servidornombreIn.add(servidornombre);
    }

    /**
     * Permite buscar cuando campo servidornombre es NULL
     * @return boolean.
     */
    public boolean isServidornombreIsNull() {
        return servidornombreIsNull;
    }

    /**
     * Permite buscar cuando campo servidornombre es NULL
     * @param servidornombreIsNull Valor de seteo.
     */
    public void setServidornombreIsNull(boolean servidornombreIsNull) {
        this.servidornombreIsNull = servidornombreIsNull;
    }

    /**
     * Permite buscar cuando campo servidornombre es NOT NULL
     * @return boolean.
     */
    public boolean isServidornombreIsNotNull() {
        return servidornombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo servidornombre es NOT NULL
     * @param servidornombreIsNotNull Valor de seteo.
     */
    public void setServidornombreIsNotNull(boolean servidornombreIsNotNull) {
        this.servidornombreIsNotNull = servidornombreIsNotNull;
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
     * Permite buscar cuando campo planificacionid es NULL
     * @return boolean.
     */
    public boolean isPlanificacionidIsNull() {
        return planificacionidIsNull;
    }

    /**
     * Permite buscar cuando campo planificacionid es NULL
     * @param planificacionidIsNull Valor de seteo.
     */
    public void setPlanificacionidIsNull(boolean planificacionidIsNull) {
        this.planificacionidIsNull = planificacionidIsNull;
    }

    /**
     * Permite buscar cuando campo planificacionid es NOT NULL
     * @return boolean.
     */
    public boolean isPlanificacionidIsNotNull() {
        return planificacionidIsNotNull;
    }

    /**
     * Permite buscar cuando campo planificacionid es NOT NULL
     * @param planificacionidIsNotNull Valor de seteo.
     */
    public void setPlanificacionidIsNotNull(boolean planificacionidIsNotNull) {
        this.planificacionidIsNotNull = planificacionidIsNotNull;
    }

    /**
     * Valor de busqueda de campo aplicacionnombre
     * @return String.
     */
    public String getAplicacionnombre() {
        if (aplicacionnombre != null) {
            switch (aplicacionnombreComparator) {
	            case STARTS_WITH:
	                return aplicacionnombre + "%";
	            case CONTAINS:
	                return "%" + aplicacionnombre + "%";
	            case ENDS_WITH:
	                return "%" + aplicacionnombre;
	            case EQUALS:
                	return aplicacionnombre;
              	default:
	            	break;
            }
        }
        return aplicacionnombre;
    }

    /**
     * Valor de busqueda de campo aplicacionnombre
     * @param aplicacionnombre Valor de seteo.
     */
    public void setAplicacionnombre(String aplicacionnombre) {
        this.aplicacionnombre = aplicacionnombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo aplicacionnombre
     * @return aplicacionnombreComparator.
     */
    public TextComparator getAplicacionnombreComparator() {
        return aplicacionnombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo aplicacionnombre
     * @param aplicacionnombreComparator Valor de seteo.
     */
    public void setAplicacionnombreComparator(TextComparator aplicacionnombreComparator) {
        this.aplicacionnombreComparator = aplicacionnombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getAplicacionnombreIn() {
        return this.aplicacionnombreIn;
    }

    /**
     * @param aplicacionnombre Valor a agregar.
     */
    public void addAplicacionnombreIn(String aplicacionnombre) {
        this.aplicacionnombreIn.add(aplicacionnombre);
    }

    /**
     * Permite buscar cuando campo aplicacionnombre es NULL
     * @return boolean.
     */
    public boolean isAplicacionnombreIsNull() {
        return aplicacionnombreIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacionnombre es NULL
     * @param aplicacionnombreIsNull Valor de seteo.
     */
    public void setAplicacionnombreIsNull(boolean aplicacionnombreIsNull) {
        this.aplicacionnombreIsNull = aplicacionnombreIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacionnombre es NOT NULL
     * @return boolean.
     */
    public boolean isAplicacionnombreIsNotNull() {
        return aplicacionnombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo aplicacionnombre es NOT NULL
     * @param aplicacionnombreIsNotNull Valor de seteo.
     */
    public void setAplicacionnombreIsNotNull(boolean aplicacionnombreIsNotNull) {
        this.aplicacionnombreIsNotNull = aplicacionnombreIsNotNull;
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
     * Valor de busqueda de campo servicionombre
     * @return String.
     */
    public String getServicionombre() {
        if (servicionombre != null) {
            switch (servicionombreComparator) {
	            case STARTS_WITH:
	                return servicionombre + "%";
	            case CONTAINS:
	                return "%" + servicionombre + "%";
	            case ENDS_WITH:
	                return "%" + servicionombre;
	            case EQUALS:
                	return servicionombre;
              	default:
	            	break;
            }
        }
        return servicionombre;
    }

    /**
     * Valor de busqueda de campo servicionombre
     * @param servicionombre Valor de seteo.
     */
    public void setServicionombre(String servicionombre) {
        this.servicionombre = servicionombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo servicionombre
     * @return servicionombreComparator.
     */
    public TextComparator getServicionombreComparator() {
        return servicionombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo servicionombre
     * @param servicionombreComparator Valor de seteo.
     */
    public void setServicionombreComparator(TextComparator servicionombreComparator) {
        this.servicionombreComparator = servicionombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getServicionombreIn() {
        return this.servicionombreIn;
    }

    /**
     * @param servicionombre Valor a agregar.
     */
    public void addServicionombreIn(String servicionombre) {
        this.servicionombreIn.add(servicionombre);
    }

    /**
     * Permite buscar cuando campo servicionombre es NULL
     * @return boolean.
     */
    public boolean isServicionombreIsNull() {
        return servicionombreIsNull;
    }

    /**
     * Permite buscar cuando campo servicionombre es NULL
     * @param servicionombreIsNull Valor de seteo.
     */
    public void setServicionombreIsNull(boolean servicionombreIsNull) {
        this.servicionombreIsNull = servicionombreIsNull;
    }

    /**
     * Permite buscar cuando campo servicionombre es NOT NULL
     * @return boolean.
     */
    public boolean isServicionombreIsNotNull() {
        return servicionombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo servicionombre es NOT NULL
     * @param servicionombreIsNotNull Valor de seteo.
     */
    public void setServicionombreIsNotNull(boolean servicionombreIsNotNull) {
        this.servicionombreIsNotNull = servicionombreIsNotNull;
    }

    /**
     * Valor de busqueda de campo canalid
     * @return Long.
     */
    public Long getCanalid() {
        return canalid;
    }

    /**
     * Valor de busqueda de campo canalid
     * @param canalid Valor de seteo.
     */
    public void setCanalid(Long canalid) {
        this.canalid = canalid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getCanalidIn() {
        return this.canalidIn;
    }

    /**
     * @param canalid Valor a agregar.
     */
    public void addCanalidIn(Long canalid) {
        this.canalidIn.add(canalid);
    }

    /**
     * Permite buscar cuando campo canalid es NULL
     * @return boolean.
     */
    public boolean isCanalidIsNull() {
        return canalidIsNull;
    }

    /**
     * Permite buscar cuando campo canalid es NULL
     * @param canalidIsNull Valor de seteo.
     */
    public void setCanalidIsNull(boolean canalidIsNull) {
        this.canalidIsNull = canalidIsNull;
    }

    /**
     * Permite buscar cuando campo canalid es NOT NULL
     * @return boolean.
     */
    public boolean isCanalidIsNotNull() {
        return canalidIsNotNull;
    }

    /**
     * Permite buscar cuando campo canalid es NOT NULL
     * @param canalidIsNotNull Valor de seteo.
     */
    public void setCanalidIsNotNull(boolean canalidIsNotNull) {
        this.canalidIsNotNull = canalidIsNotNull;
    }

    /**
     * Valor de busqueda de campo canalnombre
     * @return String.
     */
    public String getCanalnombre() {
        if (canalnombre != null) {
            switch (canalnombreComparator) {
	            case STARTS_WITH:
	                return canalnombre + "%";
	            case CONTAINS:
	                return "%" + canalnombre + "%";
	            case ENDS_WITH:
	                return "%" + canalnombre;
	            case EQUALS:
                	return canalnombre;
              	default:
	            	break;
            }
        }
        return canalnombre;
    }

    /**
     * Valor de busqueda de campo canalnombre
     * @param canalnombre Valor de seteo.
     */
    public void setCanalnombre(String canalnombre) {
        this.canalnombre = canalnombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo canalnombre
     * @return canalnombreComparator.
     */
    public TextComparator getCanalnombreComparator() {
        return canalnombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo canalnombre
     * @param canalnombreComparator Valor de seteo.
     */
    public void setCanalnombreComparator(TextComparator canalnombreComparator) {
        this.canalnombreComparator = canalnombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCanalnombreIn() {
        return this.canalnombreIn;
    }

    /**
     * @param canalnombre Valor a agregar.
     */
    public void addCanalnombreIn(String canalnombre) {
        this.canalnombreIn.add(canalnombre);
    }

    /**
     * Permite buscar cuando campo canalnombre es NULL
     * @return boolean.
     */
    public boolean isCanalnombreIsNull() {
        return canalnombreIsNull;
    }

    /**
     * Permite buscar cuando campo canalnombre es NULL
     * @param canalnombreIsNull Valor de seteo.
     */
    public void setCanalnombreIsNull(boolean canalnombreIsNull) {
        this.canalnombreIsNull = canalnombreIsNull;
    }

    /**
     * Permite buscar cuando campo canalnombre es NOT NULL
     * @return boolean.
     */
    public boolean isCanalnombreIsNotNull() {
        return canalnombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo canalnombre es NOT NULL
     * @param canalnombreIsNotNull Valor de seteo.
     */
    public void setCanalnombreIsNotNull(boolean canalnombreIsNotNull) {
        this.canalnombreIsNotNull = canalnombreIsNotNull;
    }

    /**
     * Valor de busqueda de campo estadoid
     * @return Long.
     */
    public Long getEstadoid() {
        return estadoid;
    }

    /**
     * Valor de busqueda de campo estadoid
     * @param estadoid Valor de seteo.
     */
    public void setEstadoid(Long estadoid) {
        this.estadoid = estadoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getEstadoidIn() {
        return this.estadoidIn;
    }

    /**
     * @param estadoid Valor a agregar.
     */
    public void addEstadoidIn(Long estadoid) {
        this.estadoidIn.add(estadoid);
    }

    /**
     * Permite buscar cuando campo estadoid es NULL
     * @return boolean.
     */
    public boolean isEstadoidIsNull() {
        return estadoidIsNull;
    }

    /**
     * Permite buscar cuando campo estadoid es NULL
     * @param estadoidIsNull Valor de seteo.
     */
    public void setEstadoidIsNull(boolean estadoidIsNull) {
        this.estadoidIsNull = estadoidIsNull;
    }

    /**
     * Permite buscar cuando campo estadoid es NOT NULL
     * @return boolean.
     */
    public boolean isEstadoidIsNotNull() {
        return estadoidIsNotNull;
    }

    /**
     * Permite buscar cuando campo estadoid es NOT NULL
     * @param estadoidIsNotNull Valor de seteo.
     */
    public void setEstadoidIsNotNull(boolean estadoidIsNotNull) {
        this.estadoidIsNotNull = estadoidIsNotNull;
    }

    /**
     * Valor de busqueda de campo estadonombre
     * @return String.
     */
    public String getEstadonombre() {
        if (estadonombre != null) {
            switch (estadonombreComparator) {
	            case STARTS_WITH:
	                return estadonombre + "%";
	            case CONTAINS:
	                return "%" + estadonombre + "%";
	            case ENDS_WITH:
	                return "%" + estadonombre;
	            case EQUALS:
                	return estadonombre;
              	default:
	            	break;
            }
        }
        return estadonombre;
    }

    /**
     * Valor de busqueda de campo estadonombre
     * @param estadonombre Valor de seteo.
     */
    public void setEstadonombre(String estadonombre) {
        this.estadonombre = estadonombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo estadonombre
     * @return estadonombreComparator.
     */
    public TextComparator getEstadonombreComparator() {
        return estadonombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo estadonombre
     * @param estadonombreComparator Valor de seteo.
     */
    public void setEstadonombreComparator(TextComparator estadonombreComparator) {
        this.estadonombreComparator = estadonombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEstadonombreIn() {
        return this.estadonombreIn;
    }

    /**
     * @param estadonombre Valor a agregar.
     */
    public void addEstadonombreIn(String estadonombre) {
        this.estadonombreIn.add(estadonombre);
    }

    /**
     * Permite buscar cuando campo estadonombre es NULL
     * @return boolean.
     */
    public boolean isEstadonombreIsNull() {
        return estadonombreIsNull;
    }

    /**
     * Permite buscar cuando campo estadonombre es NULL
     * @param estadonombreIsNull Valor de seteo.
     */
    public void setEstadonombreIsNull(boolean estadonombreIsNull) {
        this.estadonombreIsNull = estadonombreIsNull;
    }

    /**
     * Permite buscar cuando campo estadonombre es NOT NULL
     * @return boolean.
     */
    public boolean isEstadonombreIsNotNull() {
        return estadonombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo estadonombre es NOT NULL
     * @param estadonombreIsNotNull Valor de seteo.
     */
    public void setEstadonombreIsNotNull(boolean estadonombreIsNotNull) {
        this.estadonombreIsNotNull = estadonombreIsNotNull;
    }

    /**
     * Valor de busqueda de campo anno
     * @return Integer.
     */
    public Integer getAnno() {
        return anno;
    }

    /**
     * Valor de busqueda de campo anno
     * @param anno Valor de seteo.
     */
    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getAnnoIn() {
        return this.annoIn;
    }

    /**
     * @param anno Valor a agregar.
     */
    public void addAnnoIn(Integer anno) {
        this.annoIn.add(anno);
    }

    /**
     * Permite buscar cuando campo anno es NULL
     * @return boolean.
     */
    public boolean isAnnoIsNull() {
        return annoIsNull;
    }

    /**
     * Permite buscar cuando campo anno es NULL
     * @param annoIsNull Valor de seteo.
     */
    public void setAnnoIsNull(boolean annoIsNull) {
        this.annoIsNull = annoIsNull;
    }

    /**
     * Permite buscar cuando campo anno es NOT NULL
     * @return boolean.
     */
    public boolean isAnnoIsNotNull() {
        return annoIsNotNull;
    }

    /**
     * Permite buscar cuando campo anno es NOT NULL
     * @param annoIsNotNull Valor de seteo.
     */
    public void setAnnoIsNotNull(boolean annoIsNotNull) {
        this.annoIsNotNull = annoIsNotNull;
    }

    /**
     * Valor de busqueda de campo mes
     * @return String.
     */
    public String getMes() {
        if (mes != null) {
            switch (mesComparator) {
	            case STARTS_WITH:
	                return mes + "%";
	            case CONTAINS:
	                return "%" + mes + "%";
	            case ENDS_WITH:
	                return "%" + mes;
	            case EQUALS:
                	return mes;
              	default:
	            	break;
            }
        }
        return mes;
    }

    /**
     * Valor de busqueda de campo mes
     * @param mes Valor de seteo.
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * Tipo de comparador para la busqueda por campo mes
     * @return mesComparator.
     */
    public TextComparator getMesComparator() {
        return mesComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo mes
     * @param mesComparator Valor de seteo.
     */
    public void setMesComparator(TextComparator mesComparator) {
        this.mesComparator = mesComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMesIn() {
        return this.mesIn;
    }

    /**
     * @param mes Valor a agregar.
     */
    public void addMesIn(String mes) {
        this.mesIn.add(mes);
    }

    /**
     * Permite buscar cuando campo mes es NULL
     * @return boolean.
     */
    public boolean isMesIsNull() {
        return mesIsNull;
    }

    /**
     * Permite buscar cuando campo mes es NULL
     * @param mesIsNull Valor de seteo.
     */
    public void setMesIsNull(boolean mesIsNull) {
        this.mesIsNull = mesIsNull;
    }

    /**
     * Permite buscar cuando campo mes es NOT NULL
     * @return boolean.
     */
    public boolean isMesIsNotNull() {
        return mesIsNotNull;
    }

    /**
     * Permite buscar cuando campo mes es NOT NULL
     * @param mesIsNotNull Valor de seteo.
     */
    public void setMesIsNotNull(boolean mesIsNotNull) {
        this.mesIsNotNull = mesIsNotNull;
    }

    /**
     * Valor de busqueda de campo numtotal
     * @return Integer.
     */
    public Integer getNumtotal() {
        return numtotal;
    }

    /**
     * Valor de busqueda de campo numtotal
     * @param numtotal Valor de seteo.
     */
    public void setNumtotal(Integer numtotal) {
        this.numtotal = numtotal;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getNumtotalIn() {
        return this.numtotalIn;
    }

    /**
     * @param numtotal Valor a agregar.
     */
    public void addNumtotalIn(Integer numtotal) {
        this.numtotalIn.add(numtotal);
    }

    /**
     * Permite buscar cuando campo numtotal es NULL
     * @return boolean.
     */
    public boolean isNumtotalIsNull() {
        return numtotalIsNull;
    }

    /**
     * Permite buscar cuando campo numtotal es NULL
     * @param numtotalIsNull Valor de seteo.
     */
    public void setNumtotalIsNull(boolean numtotalIsNull) {
        this.numtotalIsNull = numtotalIsNull;
    }

    /**
     * Permite buscar cuando campo numtotal es NOT NULL
     * @return boolean.
     */
    public boolean isNumtotalIsNotNull() {
        return numtotalIsNotNull;
    }

    /**
     * Permite buscar cuando campo numtotal es NOT NULL
     * @param numtotalIsNotNull Valor de seteo.
     */
    public void setNumtotalIsNotNull(boolean numtotalIsNotNull) {
        this.numtotalIsNotNull = numtotalIsNotNull;
    }

    /**
     * Valor de busqueda de campo docusuario
     * @return String.
     */
    public String getDocusuario() {
        if (docusuario != null) {
            switch (docusuarioComparator) {
	            case STARTS_WITH:
	                return docusuario + "%";
	            case CONTAINS:
	                return "%" + docusuario + "%";
	            case ENDS_WITH:
	                return "%" + docusuario;
	            case EQUALS:
                	return docusuario;
              	default:
	            	break;
            }
        }
        return docusuario;
    }

    /**
     * Valor de busqueda de campo docusuario
     * @param docusuario Valor de seteo.
     */
    public void setDocusuario(String docusuario) {
        this.docusuario = docusuario;
    }

    /**
     * Tipo de comparador para la busqueda por campo docusuario
     * @return docusuarioComparator.
     */
    public TextComparator getDocusuarioComparator() {
        return docusuarioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo docusuario
     * @param docusuarioComparator Valor de seteo.
     */
    public void setDocusuarioComparator(TextComparator docusuarioComparator) {
        this.docusuarioComparator = docusuarioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDocusuarioIn() {
        return this.docusuarioIn;
    }

    /**
     * @param docusuario Valor a agregar.
     */
    public void addDocusuarioIn(String docusuario) {
        this.docusuarioIn.add(docusuario);
    }

    /**
     * Permite buscar cuando campo docusuario es NULL
     * @return boolean.
     */
    public boolean isDocusuarioIsNull() {
        return docusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo docusuario es NULL
     * @param docusuarioIsNull Valor de seteo.
     */
    public void setDocusuarioIsNull(boolean docusuarioIsNull) {
        this.docusuarioIsNull = docusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo docusuario es NOT NULL
     * @return boolean.
     */
    public boolean isDocusuarioIsNotNull() {
        return docusuarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo docusuario es NOT NULL
     * @param docusuarioIsNotNull Valor de seteo.
     */
    public void setDocusuarioIsNotNull(boolean docusuarioIsNotNull) {
        this.docusuarioIsNotNull = docusuarioIsNotNull;
    }

    /**
     * Valor de busqueda de campo codsia
     * @return String.
     */
    public String getCodsia() {
        if (codsia != null) {
            switch (codsiaComparator) {
	            case STARTS_WITH:
	                return codsia + "%";
	            case CONTAINS:
	                return "%" + codsia + "%";
	            case ENDS_WITH:
	                return "%" + codsia;
	            case EQUALS:
                	return codsia;
              	default:
	            	break;
            }
        }
        return codsia;
    }

    /**
     * Valor de busqueda de campo codsia
     * @param codsia Valor de seteo.
     */
    public void setCodsia(String codsia) {
        this.codsia = codsia;
    }

    /**
     * Tipo de comparador para la busqueda por campo codsia
     * @return codsiaComparator.
     */
    public TextComparator getCodsiaComparator() {
        return codsiaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codsia
     * @param codsiaComparator Valor de seteo.
     */
    public void setCodsiaComparator(TextComparator codsiaComparator) {
        this.codsiaComparator = codsiaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodsiaIn() {
        return this.codsiaIn;
    }

    /**
     * @param codsia Valor a agregar.
     */
    public void addCodsiaIn(String codsia) {
        this.codsiaIn.add(codsia);
    }

    /**
     * Permite buscar cuando campo codsia es NULL
     * @return boolean.
     */
    public boolean isCodsiaIsNull() {
        return codsiaIsNull;
    }

    /**
     * Permite buscar cuando campo codsia es NULL
     * @param codsiaIsNull Valor de seteo.
     */
    public void setCodsiaIsNull(boolean codsiaIsNull) {
        this.codsiaIsNull = codsiaIsNull;
    }

    /**
     * Permite buscar cuando campo codsia es NOT NULL
     * @return boolean.
     */
    public boolean isCodsiaIsNotNull() {
        return codsiaIsNotNull;
    }

    /**
     * Permite buscar cuando campo codsia es NOT NULL
     * @param codsiaIsNotNull Valor de seteo.
     */
    public void setCodsiaIsNotNull(boolean codsiaIsNotNull) {
        this.codsiaIsNotNull = codsiaIsNotNull;
    }

    /**
     * Valor de busqueda de campo codorganismo
     * @return String.
     */
    public String getCodorganismo() {
        if (codorganismo != null) {
            switch (codorganismoComparator) {
	            case STARTS_WITH:
	                return codorganismo + "%";
	            case CONTAINS:
	                return "%" + codorganismo + "%";
	            case ENDS_WITH:
	                return "%" + codorganismo;
	            case EQUALS:
                	return codorganismo;
              	default:
	            	break;
            }
        }
        return codorganismo;
    }

    /**
     * Valor de busqueda de campo codorganismo
     * @param codorganismo Valor de seteo.
     */
    public void setCodorganismo(String codorganismo) {
        this.codorganismo = codorganismo;
    }

    /**
     * Tipo de comparador para la busqueda por campo codorganismo
     * @return codorganismoComparator.
     */
    public TextComparator getCodorganismoComparator() {
        return codorganismoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codorganismo
     * @param codorganismoComparator Valor de seteo.
     */
    public void setCodorganismoComparator(TextComparator codorganismoComparator) {
        this.codorganismoComparator = codorganismoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodorganismoIn() {
        return this.codorganismoIn;
    }

    /**
     * @param codorganismo Valor a agregar.
     */
    public void addCodorganismoIn(String codorganismo) {
        this.codorganismoIn.add(codorganismo);
    }

    /**
     * Permite buscar cuando campo codorganismo es NULL
     * @return boolean.
     */
    public boolean isCodorganismoIsNull() {
        return codorganismoIsNull;
    }

    /**
     * Permite buscar cuando campo codorganismo es NULL
     * @param codorganismoIsNull Valor de seteo.
     */
    public void setCodorganismoIsNull(boolean codorganismoIsNull) {
        this.codorganismoIsNull = codorganismoIsNull;
    }

    /**
     * Permite buscar cuando campo codorganismo es NOT NULL
     * @return boolean.
     */
    public boolean isCodorganismoIsNotNull() {
        return codorganismoIsNotNull;
    }

    /**
     * Permite buscar cuando campo codorganismo es NOT NULL
     * @param codorganismoIsNotNull Valor de seteo.
     */
    public void setCodorganismoIsNotNull(boolean codorganismoIsNotNull) {
        this.codorganismoIsNotNull = codorganismoIsNotNull;
    }

    /**
     * Valor de busqueda de campo codorganismopagador
     * @return String.
     */
    public String getCodorganismopagador() {
        if (codorganismopagador != null) {
            switch (codorganismopagadorComparator) {
	            case STARTS_WITH:
	                return codorganismopagador + "%";
	            case CONTAINS:
	                return "%" + codorganismopagador + "%";
	            case ENDS_WITH:
	                return "%" + codorganismopagador;
	            case EQUALS:
                	return codorganismopagador;
              	default:
	            	break;
            }
        }
        return codorganismopagador;
    }

    /**
     * Valor de busqueda de campo codorganismopagador
     * @param codorganismopagador Valor de seteo.
     */
    public void setCodorganismopagador(String codorganismopagador) {
        this.codorganismopagador = codorganismopagador;
    }

    /**
     * Tipo de comparador para la busqueda por campo codorganismopagador
     * @return codorganismopagadorComparator.
     */
    public TextComparator getCodorganismopagadorComparator() {
        return codorganismopagadorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codorganismopagador
     * @param codorganismopagadorComparator Valor de seteo.
     */
    public void setCodorganismopagadorComparator(TextComparator codorganismopagadorComparator) {
        this.codorganismopagadorComparator = codorganismopagadorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodorganismopagadorIn() {
        return this.codorganismopagadorIn;
    }

    /**
     * @param codorganismopagador Valor a agregar.
     */
    public void addCodorganismopagadorIn(String codorganismopagador) {
        this.codorganismopagadorIn.add(codorganismopagador);
    }

    /**
     * Permite buscar cuando campo codorganismopagador es NULL
     * @return boolean.
     */
    public boolean isCodorganismopagadorIsNull() {
        return codorganismopagadorIsNull;
    }

    /**
     * Permite buscar cuando campo codorganismopagador es NULL
     * @param codorganismopagadorIsNull Valor de seteo.
     */
    public void setCodorganismopagadorIsNull(boolean codorganismopagadorIsNull) {
        this.codorganismopagadorIsNull = codorganismopagadorIsNull;
    }

    /**
     * Permite buscar cuando campo codorganismopagador es NOT NULL
     * @return boolean.
     */
    public boolean isCodorganismopagadorIsNotNull() {
        return codorganismopagadorIsNotNull;
    }

    /**
     * Permite buscar cuando campo codorganismopagador es NOT NULL
     * @param codorganismopagadorIsNotNull Valor de seteo.
     */
    public void setCodorganismopagadorIsNotNull(boolean codorganismopagadorIsNotNull) {
        this.codorganismopagadorIsNotNull = codorganismopagadorIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getEstadisticasconsid() != null) {
            criteria.add(Restrictions.eq(ESTADISTICASCONSID, getEstadisticasconsid()));
        }

        if (getEstadisticasconsidIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADISTICASCONSID, getEstadisticasconsidIn()));
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

        if (getServidornombre() != null) {
            if (getServidornombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SERVIDORNOMBRE, getServidornombre()));
            } 
            else if (getServidornombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SERVIDORNOMBRE, getServidornombre()));
            }
            else if (getServidornombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SERVIDORNOMBRE, getServidornombre())));
            }
            else {
                criteria.add(Restrictions.like(SERVIDORNOMBRE, getServidornombre()));
            }
        }

        if (getServidornombreIn().size() > 0) {
            criteria.add(Restrictions.in(SERVIDORNOMBRE, getServidornombreIn()));
        }

        if (isServidornombreIsNull()) {
            criteria.add(Restrictions.isNull(SERVIDORNOMBRE));
        }

        if (isServidornombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVIDORNOMBRE));
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

        if (getPlanificacionid() != null) {
            criteria.add(Restrictions.eq(PLANIFICACIONID, getPlanificacionid()));
        }

        if (getPlanificacionidIn().size() > 0) {
            criteria.add(Restrictions.in(PLANIFICACIONID, getPlanificacionidIn()));
        }

        if (isPlanificacionidIsNull()) {
            criteria.add(Restrictions.isNull(PLANIFICACIONID));
        }

        if (isPlanificacionidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PLANIFICACIONID));
        }

        if (getAplicacionnombre() != null) {
            if (getAplicacionnombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(APLICACIONNOMBRE, getAplicacionnombre()));
            } 
            else if (getAplicacionnombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(APLICACIONNOMBRE, getAplicacionnombre()));
            }
            else if (getAplicacionnombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APLICACIONNOMBRE, getAplicacionnombre())));
            }
            else {
                criteria.add(Restrictions.like(APLICACIONNOMBRE, getAplicacionnombre()));
            }
        }

        if (getAplicacionnombreIn().size() > 0) {
            criteria.add(Restrictions.in(APLICACIONNOMBRE, getAplicacionnombreIn()));
        }

        if (isAplicacionnombreIsNull()) {
            criteria.add(Restrictions.isNull(APLICACIONNOMBRE));
        }

        if (isAplicacionnombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(APLICACIONNOMBRE));
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

        if (getServicionombre() != null) {
            if (getServicionombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SERVICIONOMBRE, getServicionombre()));
            } 
            else if (getServicionombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SERVICIONOMBRE, getServicionombre()));
            }
            else if (getServicionombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SERVICIONOMBRE, getServicionombre())));
            }
            else {
                criteria.add(Restrictions.like(SERVICIONOMBRE, getServicionombre()));
            }
        }

        if (getServicionombreIn().size() > 0) {
            criteria.add(Restrictions.in(SERVICIONOMBRE, getServicionombreIn()));
        }

        if (isServicionombreIsNull()) {
            criteria.add(Restrictions.isNull(SERVICIONOMBRE));
        }

        if (isServicionombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVICIONOMBRE));
        }

        if (getCanalid() != null) {
            criteria.add(Restrictions.eq(CANALID, getCanalid()));
        }

        if (getCanalidIn().size() > 0) {
            criteria.add(Restrictions.in(CANALID, getCanalidIn()));
        }

        if (isCanalidIsNull()) {
            criteria.add(Restrictions.isNull(CANALID));
        }

        if (isCanalidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CANALID));
        }

        if (getCanalnombre() != null) {
            if (getCanalnombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CANALNOMBRE, getCanalnombre()));
            } 
            else if (getCanalnombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CANALNOMBRE, getCanalnombre()));
            }
            else if (getCanalnombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CANALNOMBRE, getCanalnombre())));
            }
            else {
                criteria.add(Restrictions.like(CANALNOMBRE, getCanalnombre()));
            }
        }

        if (getCanalnombreIn().size() > 0) {
            criteria.add(Restrictions.in(CANALNOMBRE, getCanalnombreIn()));
        }

        if (isCanalnombreIsNull()) {
            criteria.add(Restrictions.isNull(CANALNOMBRE));
        }

        if (isCanalnombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CANALNOMBRE));
        }

        if (getEstadoid() != null) {
            criteria.add(Restrictions.eq(ESTADOID, getEstadoid()));
        }

        if (getEstadoidIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADOID, getEstadoidIn()));
        }

        if (isEstadoidIsNull()) {
            criteria.add(Restrictions.isNull(ESTADOID));
        }

        if (isEstadoidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADOID));
        }

        if (getEstadonombre() != null) {
            if (getEstadonombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ESTADONOMBRE, getEstadonombre()));
            } 
            else if (getEstadonombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ESTADONOMBRE, getEstadonombre()));
            }
            else if (getEstadonombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ESTADONOMBRE, getEstadonombre())));
            }
            else {
                criteria.add(Restrictions.like(ESTADONOMBRE, getEstadonombre()));
            }
        }

        if (getEstadonombreIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADONOMBRE, getEstadonombreIn()));
        }

        if (isEstadonombreIsNull()) {
            criteria.add(Restrictions.isNull(ESTADONOMBRE));
        }

        if (isEstadonombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADONOMBRE));
        }

        if (getAnno() != null) {
            criteria.add(Restrictions.eq(ANNO, getAnno()));
        }

        if (getAnnoIn().size() > 0) {
            criteria.add(Restrictions.in(ANNO, getAnnoIn()));
        }

        if (isAnnoIsNull()) {
            criteria.add(Restrictions.isNull(ANNO));
        }

        if (isAnnoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ANNO));
        }

        if (getMes() != null) {
            if (getMesComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MES, getMes()));
            } 
            else if (getMesComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MES, getMes()));
            }
            else if (getMesComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MES, getMes())));
            }
            else {
                criteria.add(Restrictions.like(MES, getMes()));
            }
        }

        if (getMesIn().size() > 0) {
            criteria.add(Restrictions.in(MES, getMesIn()));
        }

        if (isMesIsNull()) {
            criteria.add(Restrictions.isNull(MES));
        }

        if (isMesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MES));
        }

        if (getNumtotal() != null) {
            criteria.add(Restrictions.eq(NUMTOTAL, getNumtotal()));
        }

        if (getNumtotalIn().size() > 0) {
            criteria.add(Restrictions.in(NUMTOTAL, getNumtotalIn()));
        }

        if (isNumtotalIsNull()) {
            criteria.add(Restrictions.isNull(NUMTOTAL));
        }

        if (isNumtotalIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NUMTOTAL));
        }

        if (getDocusuario() != null) {
            if (getDocusuarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DOCUSUARIO, getDocusuario()));
            } 
            else if (getDocusuarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DOCUSUARIO, getDocusuario()));
            }
            else if (getDocusuarioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DOCUSUARIO, getDocusuario())));
            }
            else {
                criteria.add(Restrictions.like(DOCUSUARIO, getDocusuario()));
            }
        }

        if (getDocusuarioIn().size() > 0) {
            criteria.add(Restrictions.in(DOCUSUARIO, getDocusuarioIn()));
        }

        if (isDocusuarioIsNull()) {
            criteria.add(Restrictions.isNull(DOCUSUARIO));
        }

        if (isDocusuarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DOCUSUARIO));
        }

        if (getCodsia() != null) {
            if (getCodsiaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODSIA, getCodsia()));
            } 
            else if (getCodsiaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODSIA, getCodsia()));
            }
            else if (getCodsiaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODSIA, getCodsia())));
            }
            else {
                criteria.add(Restrictions.like(CODSIA, getCodsia()));
            }
        }

        if (getCodsiaIn().size() > 0) {
            criteria.add(Restrictions.in(CODSIA, getCodsiaIn()));
        }

        if (isCodsiaIsNull()) {
            criteria.add(Restrictions.isNull(CODSIA));
        }

        if (isCodsiaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODSIA));
        }

        if (getCodorganismo() != null) {
            if (getCodorganismoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODORGANISMO, getCodorganismo()));
            } 
            else if (getCodorganismoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODORGANISMO, getCodorganismo()));
            }
            else if (getCodorganismoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODORGANISMO, getCodorganismo())));
            }
            else {
                criteria.add(Restrictions.like(CODORGANISMO, getCodorganismo()));
            }
        }

        if (getCodorganismoIn().size() > 0) {
            criteria.add(Restrictions.in(CODORGANISMO, getCodorganismoIn()));
        }

        if (isCodorganismoIsNull()) {
            criteria.add(Restrictions.isNull(CODORGANISMO));
        }

        if (isCodorganismoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODORGANISMO));
        }

        if (getCodorganismopagador() != null) {
            if (getCodorganismopagadorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODORGANISMOPAGADOR, getCodorganismopagador()));
            } 
            else if (getCodorganismopagadorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODORGANISMOPAGADOR, getCodorganismopagador()));
            }
            else if (getCodorganismopagadorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODORGANISMOPAGADOR, getCodorganismopagador())));
            }
            else {
                criteria.add(Restrictions.like(CODORGANISMOPAGADOR, getCodorganismopagador()));
            }
        }

        if (getCodorganismopagadorIn().size() > 0) {
            criteria.add(Restrictions.in(CODORGANISMOPAGADOR, getCodorganismopagadorIn()));
        }

        if (isCodorganismopagadorIsNull()) {
            criteria.add(Restrictions.isNull(CODORGANISMOPAGADOR));
        }

        if (isCodorganismopagadorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODORGANISMOPAGADOR));
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
 
