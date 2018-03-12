/*
 *
 * archivo: ViewPlanificacionesQuery.java
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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

import es.minhap.common.util.DateUtil;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.sim.model.ViewPlanificaciones;

/**
 * Clase con criterios de busqueda para la entidad ViewPlanificaciones
 */
public class ViewPlanificacionesQuery extends AbstractHibernateQueryEntity<ViewPlanificaciones> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String PLANIFICACIONID = "planificacionId";
    public static final String SERVIDORID = "servidorId";
    public static final String SERVICIOID = "servicioId";
    public static final String ORGANISMOID = "organismoId";
    public static final String DIR3ORGANISMO = "dir3Organismo";
    public static final String TIPOPLANIFICACIONID = "tipoPlanificacionId";
    public static final String LUNES = "lunes";
    public static final String MARTES = "martes";
    public static final String MIERCOLES = "miercoles";
    public static final String JUEVES = "jueves";
    public static final String VIERNES = "viernes";
    public static final String SABADO = "sabado";
    public static final String DOMINGO = "domingo";
    public static final String HORADESDE = "horaDesde";
    public static final String HORAHASTA = "horaHasta";
    public static final String ACTIVO = "activo";
    public static final String FECHACREACION = "fechaCreacion";
    public static final String CREADOPOR = "creadoPor";
    public static final String FECHAMODIFICACION = "fechaModificacion";
    public static final String MODIFICADOPOR = "modificadoPor";
    public static final String CANALID = "canalId";
    public static final String APLICACIONID = "aplicacionId";
    public static final String NOMBREAPLICACION = "nombreAplicacion";
    public static final String NOMBRESERVIDOR = "nombreServidor";
    public static final String NOMBRESERVICIO = "nombreServicio";
    public static final String NOMBRETIPOPLANIFICACION = "nombreTipoPlanificacion";
    public static final String ELIMINADO = "eliminado";
    public static final String EXTERNALID = "externalid";


    /**
     * Valor de busqueda de campo planificacionId
     */
    private Long planificacionId;

    /**
     * Lista de valores del campo planificacionId para busquedas tipo IN
     */
    private List<Long> planificacionIdIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo servidorId
     */
    private Long servidorId;

    /**
     * Lista de valores del campo servidorId para busquedas tipo IN
     */
    private List<Long> servidorIdIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo servidorId es NULL
     */
    private boolean servidorIdIsNull = false;

    /**
     * Permite buscar cuando campo servidorId es NOT NULL
     */
    private boolean servidorIdIsNotNull = false;

    /**
     * Valor de busqueda de campo servicioId
     */
    private Long servicioId;

    /**
     * Lista de valores del campo servicioId para busquedas tipo IN
     */
    private List<Long> servicioIdIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo servicioId es NULL
     */
    private boolean servicioIdIsNull = false;

    /**
     * Permite buscar cuando campo servicioId es NOT NULL
     */
    private boolean servicioIdIsNotNull = false;

    /**
     * Valor de busqueda de campo organismoId
     */
    private Long organismoId;

    /**
     * Lista de valores del campo organismoId para busquedas tipo IN
     */
    private List<Long> organismoIdIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo organismoId es NULL
     */
    private boolean organismoIdIsNull = false;

    /**
     * Permite buscar cuando campo organismoId es NOT NULL
     */
    private boolean organismoIdIsNotNull = false;

    /**
     * Valor de busqueda de campo dir3Organismo
     */
    private String dir3Organismo;

    /**
     * Tipo de comparador para la busqueda por campo dir3Organismo
     */
    private TextComparator dir3OrganismoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo dir3Organismo para busquedas tipo IN
     */
    private List<String> dir3OrganismoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo dir3Organismo es NULL
     */
    private boolean dir3OrganismoIsNull = false;

    /**
     * Permite buscar cuando campo dir3Organismo es NOT NULL
     */
    private boolean dir3OrganismoIsNotNull = false;

    /**
     * Valor de busqueda de campo tipoPlanificacionId
     */
    private Long tipoPlanificacionId;

    /**
     * Lista de valores del campo tipoPlanificacionId para busquedas tipo IN
     */
    private List<Long> tipoPlanificacionIdIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo tipoPlanificacionId es NULL
     */
    private boolean tipoPlanificacionIdIsNull = false;

    /**
     * Permite buscar cuando campo tipoPlanificacionId es NOT NULL
     */
    private boolean tipoPlanificacionIdIsNotNull = false;

    /**
     * Valor de busqueda de campo lunes
     */
    private String lunes;

    /**
     * Tipo de comparador para la busqueda por campo lunes
     */
    private TextComparator lunesComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo lunes para busquedas tipo IN
     */
    private List<String> lunesIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo lunes es NULL
     */
    private boolean lunesIsNull = false;

    /**
     * Permite buscar cuando campo lunes es NOT NULL
     */
    private boolean lunesIsNotNull = false;

    /**
     * Valor de busqueda de campo martes
     */
    private String martes;

    /**
     * Tipo de comparador para la busqueda por campo martes
     */
    private TextComparator martesComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo martes para busquedas tipo IN
     */
    private List<String> martesIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo martes es NULL
     */
    private boolean martesIsNull = false;

    /**
     * Permite buscar cuando campo martes es NOT NULL
     */
    private boolean martesIsNotNull = false;

    /**
     * Valor de busqueda de campo miercoles
     */
    private String miercoles;

    /**
     * Tipo de comparador para la busqueda por campo miercoles
     */
    private TextComparator miercolesComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo miercoles para busquedas tipo IN
     */
    private List<String> miercolesIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo miercoles es NULL
     */
    private boolean miercolesIsNull = false;

    /**
     * Permite buscar cuando campo miercoles es NOT NULL
     */
    private boolean miercolesIsNotNull = false;

    /**
     * Valor de busqueda de campo jueves
     */
    private String jueves;

    /**
     * Tipo de comparador para la busqueda por campo jueves
     */
    private TextComparator juevesComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo jueves para busquedas tipo IN
     */
    private List<String> juevesIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo jueves es NULL
     */
    private boolean juevesIsNull = false;

    /**
     * Permite buscar cuando campo jueves es NOT NULL
     */
    private boolean juevesIsNotNull = false;

    /**
     * Valor de busqueda de campo viernes
     */
    private String viernes;

    /**
     * Tipo de comparador para la busqueda por campo viernes
     */
    private TextComparator viernesComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo viernes para busquedas tipo IN
     */
    private List<String> viernesIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo viernes es NULL
     */
    private boolean viernesIsNull = false;

    /**
     * Permite buscar cuando campo viernes es NOT NULL
     */
    private boolean viernesIsNotNull = false;

    /**
     * Valor de busqueda de campo sabado
     */
    private String sabado;

    /**
     * Tipo de comparador para la busqueda por campo sabado
     */
    private TextComparator sabadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo sabado para busquedas tipo IN
     */
    private List<String> sabadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo sabado es NULL
     */
    private boolean sabadoIsNull = false;

    /**
     * Permite buscar cuando campo sabado es NOT NULL
     */
    private boolean sabadoIsNotNull = false;

    /**
     * Valor de busqueda de campo domingo
     */
    private String domingo;

    /**
     * Tipo de comparador para la busqueda por campo domingo
     */
    private TextComparator domingoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo domingo para busquedas tipo IN
     */
    private List<String> domingoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo domingo es NULL
     */
    private boolean domingoIsNull = false;

    /**
     * Permite buscar cuando campo domingo es NOT NULL
     */
    private boolean domingoIsNotNull = false;

    /**
     * Valor de busqueda de campo horaDesde
     */
    private String horaDesde;

    /**
     * Tipo de comparador para la busqueda por campo horaDesde
     */
    private TextComparator horaDesdeComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo horaDesde para busquedas tipo IN
     */
    private List<String> horaDesdeIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo horaDesde es NULL
     */
    private boolean horaDesdeIsNull = false;

    /**
     * Permite buscar cuando campo horaDesde es NOT NULL
     */
    private boolean horaDesdeIsNotNull = false;

    /**
     * Valor de busqueda de campo horaHasta
     */
    private String horaHasta;

    /**
     * Tipo de comparador para la busqueda por campo horaHasta
     */
    private TextComparator horaHastaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo horaHasta para busquedas tipo IN
     */
    private List<String> horaHastaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo horaHasta es NULL
     */
    private boolean horaHastaIsNull = false;

    /**
     * Permite buscar cuando campo horaHasta es NOT NULL
     */
    private boolean horaHastaIsNotNull = false;

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
     * Valor de busqueda de campo creadoPor
     */
    private String creadoPor;

    /**
     * Tipo de comparador para la busqueda por campo creadoPor
     */
    private TextComparator creadoPorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo creadoPor para busquedas tipo IN
     */
    private List<String> creadoPorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo creadoPor es NULL
     */
    private boolean creadoPorIsNull = false;

    /**
     * Permite buscar cuando campo creadoPor es NOT NULL
     */
    private boolean creadoPorIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaModificacion
     */
    private Date fechaModificacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaModificacion
     */
    private Date fechaModificacionMax;

    /**
     * Permite buscar cuando campo fechaModificacion es NULL
     */
    private boolean fechaModificacionIsNull = false;

    /**
     * Permite buscar cuando campo fechaModificacion es NOT NULL
     */
    private boolean fechaModificacionIsNotNull = false;

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
     * Valor de busqueda de campo canalId
     */
    private Long canalId;

    /**
     * Lista de valores del campo canalId para busquedas tipo IN
     */
    private List<Long> canalIdIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo canalId es NULL
     */
    private boolean canalIdIsNull = false;

    /**
     * Permite buscar cuando campo canalId es NOT NULL
     */
    private boolean canalIdIsNotNull = false;

    /**
     * Valor de busqueda de campo aplicacionId
     */
    private Long aplicacionId;

    /**
     * Lista de valores del campo aplicacionId para busquedas tipo IN
     */
    private List<Long> aplicacionIdIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo aplicacionId es NULL
     */
    private boolean aplicacionIdIsNull = false;

    /**
     * Permite buscar cuando campo aplicacionId es NOT NULL
     */
    private boolean aplicacionIdIsNotNull = false;

    /**
     * Valor de busqueda de campo nombreAplicacion
     */
    private String nombreAplicacion;

    /**
     * Tipo de comparador para la busqueda por campo nombreAplicacion
     */
    private TextComparator nombreAplicacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombreAplicacion para busquedas tipo IN
     */
    private List<String> nombreAplicacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombreAplicacion es NULL
     */
    private boolean nombreAplicacionIsNull = false;

    /**
     * Permite buscar cuando campo nombreAplicacion es NOT NULL
     */
    private boolean nombreAplicacionIsNotNull = false;

    /**
     * Valor de busqueda de campo nombreServidor
     */
    private String nombreServidor;

    /**
     * Tipo de comparador para la busqueda por campo nombreServidor
     */
    private TextComparator nombreServidorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombreServidor para busquedas tipo IN
     */
    private List<String> nombreServidorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombreServidor es NULL
     */
    private boolean nombreServidorIsNull = false;

    /**
     * Permite buscar cuando campo nombreServidor es NOT NULL
     */
    private boolean nombreServidorIsNotNull = false;

    /**
     * Valor de busqueda de campo nombreServicio
     */
    private String nombreServicio;

    /**
     * Tipo de comparador para la busqueda por campo nombreServicio
     */
    private TextComparator nombreServicioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombreServicio para busquedas tipo IN
     */
    private List<String> nombreServicioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombreServicio es NULL
     */
    private boolean nombreServicioIsNull = false;

    /**
     * Permite buscar cuando campo nombreServicio es NOT NULL
     */
    private boolean nombreServicioIsNotNull = false;

    /**
     * Valor de busqueda de campo nombreTipoPlanificacion
     */
    private String nombreTipoPlanificacion;

    /**
     * Tipo de comparador para la busqueda por campo nombreTipoPlanificacion
     */
    private TextComparator nombreTipoPlanificacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombreTipoPlanificacion para busquedas tipo IN
     */
    private List<String> nombreTipoPlanificacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombreTipoPlanificacion es NULL
     */
    private boolean nombreTipoPlanificacionIsNull = false;

    /**
     * Permite buscar cuando campo nombreTipoPlanificacion es NOT NULL
     */
    private boolean nombreTipoPlanificacionIsNotNull = false;

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
     * Valor de busqueda de campo externalid
     */
    private String externalid;

    /**
     * Tipo de comparador para la busqueda por campo externalid
     */
    private TextComparator externalidComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo externalid para busquedas tipo IN
     */
    private List<String> externalidIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo externalid es NULL
     */
    private boolean externalidIsNull = false;

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     */
    private boolean externalidIsNotNull = false;
    
    /**
	 * Establece el máximo de resultados
	 */
	private Integer maxResultados;
	
	/**
	 * Establece el primer resultados
	 */
	private Integer primerResultado;
	
	private String horaDesdeIni;
	
	private String horaDesdeFin;
	
	private String horaHastaIni;
	
	private String horaHastaFin;
	
    /**
     * Constructor default
     */
    public ViewPlanificacionesQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ViewPlanificacionesQuery(Long planificacionId) {
        setPlanificacionId(planificacionId);
    }

    /**
     * Valor de busqueda de campo planificacionId
     * @return Long.
     */
    public Long getPlanificacionId() {
        return planificacionId;
    }

    /**
     * Valor de busqueda de campo planificacionId
     * @param planificacionId Valor de seteo.
     */
    public void setPlanificacionId(Long planificacionId) {
        this.planificacionId = planificacionId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getPlanificacionIdIn() {
        return this.planificacionIdIn;
    }

    /**
     * @param planificacionId Valor a agregar.
     */
    public void addPlanificacionIdIn(Long planificacionId) {
        this.planificacionIdIn.add(planificacionId);
    }

    /**
     * Valor de busqueda de campo servidorId
     * @return Long.
     */
    public Long getServidorId() {
        return servidorId;
    }

    /**
     * Valor de busqueda de campo servidorId
     * @param servidorId Valor de seteo.
     */
    public void setServidorId(Long servidorId) {
        this.servidorId = servidorId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getServidorIdIn() {
        return this.servidorIdIn;
    }

    /**
     * @param servidorId Valor a agregar.
     */
    public void addServidorIdIn(Long servidorId) {
        this.servidorIdIn.add(servidorId);
    }

    /**
     * Permite buscar cuando campo servidorId es NULL
     * @return boolean.
     */
    public boolean isServidorIdIsNull() {
        return servidorIdIsNull;
    }

    /**
     * Permite buscar cuando campo servidorId es NULL
     * @param servidorIdIsNull Valor de seteo.
     */
    public void setServidorIdIsNull(boolean servidorIdIsNull) {
        this.servidorIdIsNull = servidorIdIsNull;
    }

    /**
     * Permite buscar cuando campo servidorId es NOT NULL
     * @return boolean.
     */
    public boolean isServidorIdIsNotNull() {
        return servidorIdIsNotNull;
    }

    /**
     * Permite buscar cuando campo servidorId es NOT NULL
     * @param servidorIdIsNotNull Valor de seteo.
     */
    public void setServidorIdIsNotNull(boolean servidorIdIsNotNull) {
        this.servidorIdIsNotNull = servidorIdIsNotNull;
    }

    /**
     * Valor de busqueda de campo servicioId
     * @return Long.
     */
    public Long getServicioId() {
        return servicioId;
    }

    /**
     * Valor de busqueda de campo servicioId
     * @param servicioId Valor de seteo.
     */
    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getServicioIdIn() {
        return this.servicioIdIn;
    }

    /**
     * @param servicioId Valor a agregar.
     */
    public void addServicioIdIn(Long servicioId) {
        this.servicioIdIn.add(servicioId);
    }

    /**
     * Permite buscar cuando campo servicioId es NULL
     * @return boolean.
     */
    public boolean isServicioIdIsNull() {
        return servicioIdIsNull;
    }

    /**
     * Permite buscar cuando campo servicioId es NULL
     * @param servicioIdIsNull Valor de seteo.
     */
    public void setServicioIdIsNull(boolean servicioIdIsNull) {
        this.servicioIdIsNull = servicioIdIsNull;
    }

    /**
     * Permite buscar cuando campo servicioId es NOT NULL
     * @return boolean.
     */
    public boolean isServicioIdIsNotNull() {
        return servicioIdIsNotNull;
    }

    /**
     * Permite buscar cuando campo servicioId es NOT NULL
     * @param servicioIdIsNotNull Valor de seteo.
     */
    public void setServicioIdIsNotNull(boolean servicioIdIsNotNull) {
        this.servicioIdIsNotNull = servicioIdIsNotNull;
    }

    /**
     * Valor de busqueda de campo organismoId
     * @return Long.
     */
    public Long getOrganismoId() {
        return organismoId;
    }

    /**
     * Valor de busqueda de campo organismoId
     * @param organismoId Valor de seteo.
     */
    public void setOrganismoId(Long organismoId) {
        this.organismoId = organismoId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getOrganismoIdIn() {
        return this.organismoIdIn;
    }

    /**
     * @param organismoId Valor a agregar.
     */
    public void addOrganismoIdIn(Long organismoId) {
        this.organismoIdIn.add(organismoId);
    }

    /**
     * Permite buscar cuando campo organismoId es NULL
     * @return boolean.
     */
    public boolean isOrganismoIdIsNull() {
        return organismoIdIsNull;
    }

    /**
     * Permite buscar cuando campo organismoId es NULL
     * @param organismoIdIsNull Valor de seteo.
     */
    public void setOrganismoIdIsNull(boolean organismoIdIsNull) {
        this.organismoIdIsNull = organismoIdIsNull;
    }

    /**
     * Permite buscar cuando campo organismoId es NOT NULL
     * @return boolean.
     */
    public boolean isOrganismoIdIsNotNull() {
        return organismoIdIsNotNull;
    }

    /**
     * Permite buscar cuando campo organismoId es NOT NULL
     * @param organismoIdIsNotNull Valor de seteo.
     */
    public void setOrganismoIdIsNotNull(boolean organismoIdIsNotNull) {
        this.organismoIdIsNotNull = organismoIdIsNotNull;
    }

    /**
     * Valor de busqueda de campo dir3Organismo
     * @return String.
     */
    public String getDir3Organismo() {
        if (dir3Organismo != null) {
            switch (dir3OrganismoComparator) {
	            case STARTS_WITH:
	                return dir3Organismo + "%";
	            case CONTAINS:
	                return "%" + dir3Organismo + "%";
	            case ENDS_WITH:
	                return "%" + dir3Organismo;
	            case EQUALS:
                	return dir3Organismo;
              	default:
	            	break;
            }
        }
        return dir3Organismo;
    }

    /**
     * Valor de busqueda de campo dir3Organismo
     * @param dir3Organismo Valor de seteo.
     */
    public void setDir3Organismo(String dir3Organismo) {
        this.dir3Organismo = dir3Organismo;
    }

    /**
     * Tipo de comparador para la busqueda por campo dir3Organismo
     * @return dir3OrganismoComparator.
     */
    public TextComparator getDir3OrganismoComparator() {
        return dir3OrganismoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo dir3Organismo
     * @param dir3OrganismoComparator Valor de seteo.
     */
    public void setDir3OrganismoComparator(TextComparator dir3OrganismoComparator) {
        this.dir3OrganismoComparator = dir3OrganismoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDir3OrganismoIn() {
        return this.dir3OrganismoIn;
    }

    /**
     * @param dir3Organismo Valor a agregar.
     */
    public void addDir3OrganismoIn(String dir3Organismo) {
        this.dir3OrganismoIn.add(dir3Organismo);
    }

    /**
     * Permite buscar cuando campo dir3Organismo es NULL
     * @return boolean.
     */
    public boolean isDir3OrganismoIsNull() {
        return dir3OrganismoIsNull;
    }

    /**
     * Permite buscar cuando campo dir3Organismo es NULL
     * @param dir3OrganismoIsNull Valor de seteo.
     */
    public void setDir3OrganismoIsNull(boolean dir3OrganismoIsNull) {
        this.dir3OrganismoIsNull = dir3OrganismoIsNull;
    }

    /**
     * Permite buscar cuando campo dir3Organismo es NOT NULL
     * @return boolean.
     */
    public boolean isDir3OrganismoIsNotNull() {
        return dir3OrganismoIsNotNull;
    }

    /**
     * Permite buscar cuando campo dir3Organismo es NOT NULL
     * @param dir3OrganismoIsNotNull Valor de seteo.
     */
    public void setDir3OrganismoIsNotNull(boolean dir3OrganismoIsNotNull) {
        this.dir3OrganismoIsNotNull = dir3OrganismoIsNotNull;
    }

    /**
     * Valor de busqueda de campo tipoPlanificacionId
     * @return Long.
     */
    public Long getTipoPlanificacionId() {
        return tipoPlanificacionId;
    }

    /**
     * Valor de busqueda de campo tipoPlanificacionId
     * @param tipoPlanificacionId Valor de seteo.
     */
    public void setTipoPlanificacionId(Long tipoPlanificacionId) {
        this.tipoPlanificacionId = tipoPlanificacionId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getTipoPlanificacionIdIn() {
        return this.tipoPlanificacionIdIn;
    }

    /**
     * @param tipoPlanificacionId Valor a agregar.
     */
    public void addTipoPlanificacionIdIn(Long tipoPlanificacionId) {
        this.tipoPlanificacionIdIn.add(tipoPlanificacionId);
    }

    /**
     * Permite buscar cuando campo tipoPlanificacionId es NULL
     * @return boolean.
     */
    public boolean isTipoPlanificacionIdIsNull() {
        return tipoPlanificacionIdIsNull;
    }

    /**
     * Permite buscar cuando campo tipoPlanificacionId es NULL
     * @param tipoPlanificacionIdIsNull Valor de seteo.
     */
    public void setTipoPlanificacionIdIsNull(boolean tipoPlanificacionIdIsNull) {
        this.tipoPlanificacionIdIsNull = tipoPlanificacionIdIsNull;
    }

    /**
     * Permite buscar cuando campo tipoPlanificacionId es NOT NULL
     * @return boolean.
     */
    public boolean isTipoPlanificacionIdIsNotNull() {
        return tipoPlanificacionIdIsNotNull;
    }

    /**
     * Permite buscar cuando campo tipoPlanificacionId es NOT NULL
     * @param tipoPlanificacionIdIsNotNull Valor de seteo.
     */
    public void setTipoPlanificacionIdIsNotNull(boolean tipoPlanificacionIdIsNotNull) {
        this.tipoPlanificacionIdIsNotNull = tipoPlanificacionIdIsNotNull;
    }

    /**
     * Valor de busqueda de campo lunes
     * @return String.
     */
    public String getLunes() {
        if (lunes != null) {
            switch (lunesComparator) {
	            case STARTS_WITH:
	                return lunes + "%";
	            case CONTAINS:
	                return "%" + lunes + "%";
	            case ENDS_WITH:
	                return "%" + lunes;
	            case EQUALS:
                	return lunes;
              	default:
	            	break;
            }
        }
        return lunes;
    }

    /**
     * Valor de busqueda de campo lunes
     * @param lunes Valor de seteo.
     */
    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    /**
     * Tipo de comparador para la busqueda por campo lunes
     * @return lunesComparator.
     */
    public TextComparator getLunesComparator() {
        return lunesComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo lunes
     * @param lunesComparator Valor de seteo.
     */
    public void setLunesComparator(TextComparator lunesComparator) {
        this.lunesComparator = lunesComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getLunesIn() {
        return this.lunesIn;
    }

    /**
     * @param lunes Valor a agregar.
     */
    public void addLunesIn(String lunes) {
        this.lunesIn.add(lunes);
    }

    /**
     * Permite buscar cuando campo lunes es NULL
     * @return boolean.
     */
    public boolean isLunesIsNull() {
        return lunesIsNull;
    }

    /**
     * Permite buscar cuando campo lunes es NULL
     * @param lunesIsNull Valor de seteo.
     */
    public void setLunesIsNull(boolean lunesIsNull) {
        this.lunesIsNull = lunesIsNull;
    }

    /**
     * Permite buscar cuando campo lunes es NOT NULL
     * @return boolean.
     */
    public boolean isLunesIsNotNull() {
        return lunesIsNotNull;
    }

    /**
     * Permite buscar cuando campo lunes es NOT NULL
     * @param lunesIsNotNull Valor de seteo.
     */
    public void setLunesIsNotNull(boolean lunesIsNotNull) {
        this.lunesIsNotNull = lunesIsNotNull;
    }

    /**
     * Valor de busqueda de campo martes
     * @return String.
     */
    public String getMartes() {
        if (martes != null) {
            switch (martesComparator) {
	            case STARTS_WITH:
	                return martes + "%";
	            case CONTAINS:
	                return "%" + martes + "%";
	            case ENDS_WITH:
	                return "%" + martes;
	            case EQUALS:
                	return martes;
              	default:
	            	break;
            }
        }
        return martes;
    }

    /**
     * Valor de busqueda de campo martes
     * @param martes Valor de seteo.
     */
    public void setMartes(String martes) {
        this.martes = martes;
    }

    /**
     * Tipo de comparador para la busqueda por campo martes
     * @return martesComparator.
     */
    public TextComparator getMartesComparator() {
        return martesComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo martes
     * @param martesComparator Valor de seteo.
     */
    public void setMartesComparator(TextComparator martesComparator) {
        this.martesComparator = martesComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMartesIn() {
        return this.martesIn;
    }

    /**
     * @param martes Valor a agregar.
     */
    public void addMartesIn(String martes) {
        this.martesIn.add(martes);
    }

    /**
     * Permite buscar cuando campo martes es NULL
     * @return boolean.
     */
    public boolean isMartesIsNull() {
        return martesIsNull;
    }

    /**
     * Permite buscar cuando campo martes es NULL
     * @param martesIsNull Valor de seteo.
     */
    public void setMartesIsNull(boolean martesIsNull) {
        this.martesIsNull = martesIsNull;
    }

    /**
     * Permite buscar cuando campo martes es NOT NULL
     * @return boolean.
     */
    public boolean isMartesIsNotNull() {
        return martesIsNotNull;
    }

    /**
     * Permite buscar cuando campo martes es NOT NULL
     * @param martesIsNotNull Valor de seteo.
     */
    public void setMartesIsNotNull(boolean martesIsNotNull) {
        this.martesIsNotNull = martesIsNotNull;
    }

    /**
     * Valor de busqueda de campo miercoles
     * @return String.
     */
    public String getMiercoles() {
        if (miercoles != null) {
            switch (miercolesComparator) {
	            case STARTS_WITH:
	                return miercoles + "%";
	            case CONTAINS:
	                return "%" + miercoles + "%";
	            case ENDS_WITH:
	                return "%" + miercoles;
	            case EQUALS:
                	return miercoles;
              	default:
	            	break;
            }
        }
        return miercoles;
    }

    /**
     * Valor de busqueda de campo miercoles
     * @param miercoles Valor de seteo.
     */
    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    /**
     * Tipo de comparador para la busqueda por campo miercoles
     * @return miercolesComparator.
     */
    public TextComparator getMiercolesComparator() {
        return miercolesComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo miercoles
     * @param miercolesComparator Valor de seteo.
     */
    public void setMiercolesComparator(TextComparator miercolesComparator) {
        this.miercolesComparator = miercolesComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMiercolesIn() {
        return this.miercolesIn;
    }

    /**
     * @param miercoles Valor a agregar.
     */
    public void addMiercolesIn(String miercoles) {
        this.miercolesIn.add(miercoles);
    }

    /**
     * Permite buscar cuando campo miercoles es NULL
     * @return boolean.
     */
    public boolean isMiercolesIsNull() {
        return miercolesIsNull;
    }

    /**
     * Permite buscar cuando campo miercoles es NULL
     * @param miercolesIsNull Valor de seteo.
     */
    public void setMiercolesIsNull(boolean miercolesIsNull) {
        this.miercolesIsNull = miercolesIsNull;
    }

    /**
     * Permite buscar cuando campo miercoles es NOT NULL
     * @return boolean.
     */
    public boolean isMiercolesIsNotNull() {
        return miercolesIsNotNull;
    }

    /**
     * Permite buscar cuando campo miercoles es NOT NULL
     * @param miercolesIsNotNull Valor de seteo.
     */
    public void setMiercolesIsNotNull(boolean miercolesIsNotNull) {
        this.miercolesIsNotNull = miercolesIsNotNull;
    }

    /**
     * Valor de busqueda de campo jueves
     * @return String.
     */
    public String getJueves() {
        if (jueves != null) {
            switch (juevesComparator) {
	            case STARTS_WITH:
	                return jueves + "%";
	            case CONTAINS:
	                return "%" + jueves + "%";
	            case ENDS_WITH:
	                return "%" + jueves;
	            case EQUALS:
                	return jueves;
              	default:
	            	break;
            }
        }
        return jueves;
    }

    /**
     * Valor de busqueda de campo jueves
     * @param jueves Valor de seteo.
     */
    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    /**
     * Tipo de comparador para la busqueda por campo jueves
     * @return juevesComparator.
     */
    public TextComparator getJuevesComparator() {
        return juevesComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo jueves
     * @param juevesComparator Valor de seteo.
     */
    public void setJuevesComparator(TextComparator juevesComparator) {
        this.juevesComparator = juevesComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getJuevesIn() {
        return this.juevesIn;
    }

    /**
     * @param jueves Valor a agregar.
     */
    public void addJuevesIn(String jueves) {
        this.juevesIn.add(jueves);
    }

    /**
     * Permite buscar cuando campo jueves es NULL
     * @return boolean.
     */
    public boolean isJuevesIsNull() {
        return juevesIsNull;
    }

    /**
     * Permite buscar cuando campo jueves es NULL
     * @param juevesIsNull Valor de seteo.
     */
    public void setJuevesIsNull(boolean juevesIsNull) {
        this.juevesIsNull = juevesIsNull;
    }

    /**
     * Permite buscar cuando campo jueves es NOT NULL
     * @return boolean.
     */
    public boolean isJuevesIsNotNull() {
        return juevesIsNotNull;
    }

    /**
     * Permite buscar cuando campo jueves es NOT NULL
     * @param juevesIsNotNull Valor de seteo.
     */
    public void setJuevesIsNotNull(boolean juevesIsNotNull) {
        this.juevesIsNotNull = juevesIsNotNull;
    }

    /**
     * Valor de busqueda de campo viernes
     * @return String.
     */
    public String getViernes() {
        if (viernes != null) {
            switch (viernesComparator) {
	            case STARTS_WITH:
	                return viernes + "%";
	            case CONTAINS:
	                return "%" + viernes + "%";
	            case ENDS_WITH:
	                return "%" + viernes;
	            case EQUALS:
                	return viernes;
              	default:
	            	break;
            }
        }
        return viernes;
    }

    /**
     * Valor de busqueda de campo viernes
     * @param viernes Valor de seteo.
     */
    public void setViernes(String viernes) {
        this.viernes = viernes;
    }

    /**
     * Tipo de comparador para la busqueda por campo viernes
     * @return viernesComparator.
     */
    public TextComparator getViernesComparator() {
        return viernesComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo viernes
     * @param viernesComparator Valor de seteo.
     */
    public void setViernesComparator(TextComparator viernesComparator) {
        this.viernesComparator = viernesComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getViernesIn() {
        return this.viernesIn;
    }

    /**
     * @param viernes Valor a agregar.
     */
    public void addViernesIn(String viernes) {
        this.viernesIn.add(viernes);
    }

    /**
     * Permite buscar cuando campo viernes es NULL
     * @return boolean.
     */
    public boolean isViernesIsNull() {
        return viernesIsNull;
    }

    /**
     * Permite buscar cuando campo viernes es NULL
     * @param viernesIsNull Valor de seteo.
     */
    public void setViernesIsNull(boolean viernesIsNull) {
        this.viernesIsNull = viernesIsNull;
    }

    /**
     * Permite buscar cuando campo viernes es NOT NULL
     * @return boolean.
     */
    public boolean isViernesIsNotNull() {
        return viernesIsNotNull;
    }

    /**
     * Permite buscar cuando campo viernes es NOT NULL
     * @param viernesIsNotNull Valor de seteo.
     */
    public void setViernesIsNotNull(boolean viernesIsNotNull) {
        this.viernesIsNotNull = viernesIsNotNull;
    }

    /**
     * Valor de busqueda de campo sabado
     * @return String.
     */
    public String getSabado() {
        if (sabado != null) {
            switch (sabadoComparator) {
	            case STARTS_WITH:
	                return sabado + "%";
	            case CONTAINS:
	                return "%" + sabado + "%";
	            case ENDS_WITH:
	                return "%" + sabado;
	            case EQUALS:
                	return sabado;
              	default:
	            	break;
            }
        }
        return sabado;
    }

    /**
     * Valor de busqueda de campo sabado
     * @param sabado Valor de seteo.
     */
    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    /**
     * Tipo de comparador para la busqueda por campo sabado
     * @return sabadoComparator.
     */
    public TextComparator getSabadoComparator() {
        return sabadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo sabado
     * @param sabadoComparator Valor de seteo.
     */
    public void setSabadoComparator(TextComparator sabadoComparator) {
        this.sabadoComparator = sabadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getSabadoIn() {
        return this.sabadoIn;
    }

    /**
     * @param sabado Valor a agregar.
     */
    public void addSabadoIn(String sabado) {
        this.sabadoIn.add(sabado);
    }

    /**
     * Permite buscar cuando campo sabado es NULL
     * @return boolean.
     */
    public boolean isSabadoIsNull() {
        return sabadoIsNull;
    }

    /**
     * Permite buscar cuando campo sabado es NULL
     * @param sabadoIsNull Valor de seteo.
     */
    public void setSabadoIsNull(boolean sabadoIsNull) {
        this.sabadoIsNull = sabadoIsNull;
    }

    /**
     * Permite buscar cuando campo sabado es NOT NULL
     * @return boolean.
     */
    public boolean isSabadoIsNotNull() {
        return sabadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo sabado es NOT NULL
     * @param sabadoIsNotNull Valor de seteo.
     */
    public void setSabadoIsNotNull(boolean sabadoIsNotNull) {
        this.sabadoIsNotNull = sabadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo domingo
     * @return String.
     */
    public String getDomingo() {
        if (domingo != null) {
            switch (domingoComparator) {
	            case STARTS_WITH:
	                return domingo + "%";
	            case CONTAINS:
	                return "%" + domingo + "%";
	            case ENDS_WITH:
	                return "%" + domingo;
	            case EQUALS:
                	return domingo;
              	default:
	            	break;
            }
        }
        return domingo;
    }

    /**
     * Valor de busqueda de campo domingo
     * @param domingo Valor de seteo.
     */
    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }

    /**
     * Tipo de comparador para la busqueda por campo domingo
     * @return domingoComparator.
     */
    public TextComparator getDomingoComparator() {
        return domingoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo domingo
     * @param domingoComparator Valor de seteo.
     */
    public void setDomingoComparator(TextComparator domingoComparator) {
        this.domingoComparator = domingoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDomingoIn() {
        return this.domingoIn;
    }

    /**
     * @param domingo Valor a agregar.
     */
    public void addDomingoIn(String domingo) {
        this.domingoIn.add(domingo);
    }

    /**
     * Permite buscar cuando campo domingo es NULL
     * @return boolean.
     */
    public boolean isDomingoIsNull() {
        return domingoIsNull;
    }

    /**
     * Permite buscar cuando campo domingo es NULL
     * @param domingoIsNull Valor de seteo.
     */
    public void setDomingoIsNull(boolean domingoIsNull) {
        this.domingoIsNull = domingoIsNull;
    }

    /**
     * Permite buscar cuando campo domingo es NOT NULL
     * @return boolean.
     */
    public boolean isDomingoIsNotNull() {
        return domingoIsNotNull;
    }

    /**
     * Permite buscar cuando campo domingo es NOT NULL
     * @param domingoIsNotNull Valor de seteo.
     */
    public void setDomingoIsNotNull(boolean domingoIsNotNull) {
        this.domingoIsNotNull = domingoIsNotNull;
    }

    /**
     * Valor de busqueda de campo horaDesde
     * @return String.
     */
    public String getHoraDesde() {
        if (horaDesde != null) {
            switch (horaDesdeComparator) {
	            case STARTS_WITH:
	                return horaDesde + "%";
	            case CONTAINS:
	                return "%" + horaDesde + "%";
	            case ENDS_WITH:
	                return "%" + horaDesde;
	            case EQUALS:
                	return horaDesde;
              	default:
	            	break;
            }
        }
        return horaDesde;
    }

    /**
     * Valor de busqueda de campo horaDesde
     * @param horaDesde Valor de seteo.
     */
    public void setHoraDesde(String horaDesde) {
        this.horaDesde = horaDesde;
    }

    /**
     * Tipo de comparador para la busqueda por campo horaDesde
     * @return horaDesdeComparator.
     */
    public TextComparator getHoraDesdeComparator() {
        return horaDesdeComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo horaDesde
     * @param horaDesdeComparator Valor de seteo.
     */
    public void setHoraDesdeComparator(TextComparator horaDesdeComparator) {
        this.horaDesdeComparator = horaDesdeComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getHoraDesdeIn() {
        return this.horaDesdeIn;
    }

    /**
     * @param horaDesde Valor a agregar.
     */
    public void addHoraDesdeIn(String horaDesde) {
        this.horaDesdeIn.add(horaDesde);
    }

    /**
     * Permite buscar cuando campo horaDesde es NULL
     * @return boolean.
     */
    public boolean isHoraDesdeIsNull() {
        return horaDesdeIsNull;
    }

    /**
     * Permite buscar cuando campo horaDesde es NULL
     * @param horaDesdeIsNull Valor de seteo.
     */
    public void setHoraDesdeIsNull(boolean horaDesdeIsNull) {
        this.horaDesdeIsNull = horaDesdeIsNull;
    }

    /**
     * Permite buscar cuando campo horaDesde es NOT NULL
     * @return boolean.
     */
    public boolean isHoraDesdeIsNotNull() {
        return horaDesdeIsNotNull;
    }

    /**
     * Permite buscar cuando campo horaDesde es NOT NULL
     * @param horaDesdeIsNotNull Valor de seteo.
     */
    public void setHoraDesdeIsNotNull(boolean horaDesdeIsNotNull) {
        this.horaDesdeIsNotNull = horaDesdeIsNotNull;
    }

    /**
     * Valor de busqueda de campo horaHasta
     * @return String.
     */
    public String getHoraHasta() {
        if (horaHasta != null) {
            switch (horaHastaComparator) {
	            case STARTS_WITH:
	                return horaHasta + "%";
	            case CONTAINS:
	                return "%" + horaHasta + "%";
	            case ENDS_WITH:
	                return "%" + horaHasta;
	            case EQUALS:
                	return horaHasta;
              	default:
	            	break;
            }
        }
        return horaHasta;
    }

    /**
     * Valor de busqueda de campo horaHasta
     * @param horaHasta Valor de seteo.
     */
    public void setHoraHasta(String horaHasta) {
        this.horaHasta = horaHasta;
    }

    /**
     * Tipo de comparador para la busqueda por campo horaHasta
     * @return horaHastaComparator.
     */
    public TextComparator getHoraHastaComparator() {
        return horaHastaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo horaHasta
     * @param horaHastaComparator Valor de seteo.
     */
    public void setHoraHastaComparator(TextComparator horaHastaComparator) {
        this.horaHastaComparator = horaHastaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getHoraHastaIn() {
        return this.horaHastaIn;
    }

    /**
     * @param horaHasta Valor a agregar.
     */
    public void addHoraHastaIn(String horaHasta) {
        this.horaHastaIn.add(horaHasta);
    }

    /**
     * Permite buscar cuando campo horaHasta es NULL
     * @return boolean.
     */
    public boolean isHoraHastaIsNull() {
        return horaHastaIsNull;
    }

    /**
     * Permite buscar cuando campo horaHasta es NULL
     * @param horaHastaIsNull Valor de seteo.
     */
    public void setHoraHastaIsNull(boolean horaHastaIsNull) {
        this.horaHastaIsNull = horaHastaIsNull;
    }

    /**
     * Permite buscar cuando campo horaHasta es NOT NULL
     * @return boolean.
     */
    public boolean isHoraHastaIsNotNull() {
        return horaHastaIsNotNull;
    }

    /**
     * Permite buscar cuando campo horaHasta es NOT NULL
     * @param horaHastaIsNotNull Valor de seteo.
     */
    public void setHoraHastaIsNotNull(boolean horaHastaIsNotNull) {
        this.horaHastaIsNotNull = horaHastaIsNotNull;
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
     * Valor de busqueda de campo creadoPor
     * @return String.
     */
    public String getCreadoPor() {
        if (creadoPor != null) {
            switch (creadoPorComparator) {
	            case STARTS_WITH:
	                return creadoPor + "%";
	            case CONTAINS:
	                return "%" + creadoPor + "%";
	            case ENDS_WITH:
	                return "%" + creadoPor;
	            case EQUALS:
                	return creadoPor;
              	default:
	            	break;
            }
        }
        return creadoPor;
    }

    /**
     * Valor de busqueda de campo creadoPor
     * @param creadoPor Valor de seteo.
     */
    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Tipo de comparador para la busqueda por campo creadoPor
     * @return creadoPorComparator.
     */
    public TextComparator getCreadoPorComparator() {
        return creadoPorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo creadoPor
     * @param creadoPorComparator Valor de seteo.
     */
    public void setCreadoPorComparator(TextComparator creadoPorComparator) {
        this.creadoPorComparator = creadoPorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCreadoPorIn() {
        return this.creadoPorIn;
    }

    /**
     * @param creadoPor Valor a agregar.
     */
    public void addCreadoPorIn(String creadoPor) {
        this.creadoPorIn.add(creadoPor);
    }

    /**
     * Permite buscar cuando campo creadoPor es NULL
     * @return boolean.
     */
    public boolean isCreadoPorIsNull() {
        return creadoPorIsNull;
    }

    /**
     * Permite buscar cuando campo creadoPor es NULL
     * @param creadoPorIsNull Valor de seteo.
     */
    public void setCreadoPorIsNull(boolean creadoPorIsNull) {
        this.creadoPorIsNull = creadoPorIsNull;
    }

    /**
     * Permite buscar cuando campo creadoPor es NOT NULL
     * @return boolean.
     */
    public boolean isCreadoPorIsNotNull() {
        return creadoPorIsNotNull;
    }

    /**
     * Permite buscar cuando campo creadoPor es NOT NULL
     * @param creadoPorIsNotNull Valor de seteo.
     */
    public void setCreadoPorIsNotNull(boolean creadoPorIsNotNull) {
        this.creadoPorIsNotNull = creadoPorIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaModificacion
     * @return ${field.getName()}Min.
     */
    public Date getFechaModificacionMin() {
        if (fechaModificacionMin != null) {
            return DateUtil.toDayBegin(fechaModificacionMin);
        }
        return fechaModificacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaModificacion
     * @param fechaModificacionMin Valor de seteo.
     */
    public void setFechaModificacionMin(Date fechaModificacionMin) {
        this.fechaModificacionMin = fechaModificacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaModificacion
     * @return fechaModificacionMax.
     */
    public Date getFechaModificacionMax() {
        if (fechaModificacionMax != null) {
            return DateUtil.toDayEnd(fechaModificacionMax);
        }
        return fechaModificacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaModificacion
     * @param fechaModificacionMax Valor de seteo.
     */
    public void setFechaModificacionMax(Date fechaModificacionMax) {
        this.fechaModificacionMax = fechaModificacionMax;
    }

    /**
     * Permite buscar cuando campo fechaModificacion es NULL
     * @return boolean.
     */
    public boolean isFechaModificacionIsNull() {
        return fechaModificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaModificacion es NULL
     * @param fechaModificacionIsNull Valor de seteo.
     */
    public void setFechaModificacionIsNull(boolean fechaModificacionIsNull) {
        this.fechaModificacionIsNull = fechaModificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaModificacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaModificacionIsNotNull() {
        return fechaModificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaModificacion es NOT NULL
     * @param fechaModificacionIsNotNull Valor de seteo.
     */
    public void setFechaModificacionIsNotNull(boolean fechaModificacionIsNotNull) {
        this.fechaModificacionIsNotNull = fechaModificacionIsNotNull;
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
     * Valor de busqueda de campo canalId
     * @return Long.
     */
    public Long getCanalId() {
        return canalId;
    }

    /**
     * Valor de busqueda de campo canalId
     * @param canalId Valor de seteo.
     */
    public void setCanalId(Long canalId) {
        this.canalId = canalId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getCanalIdIn() {
        return this.canalIdIn;
    }

    /**
     * @param canalId Valor a agregar.
     */
    public void addCanalIdIn(Long canalId) {
        this.canalIdIn.add(canalId);
    }

    /**
     * Permite buscar cuando campo canalId es NULL
     * @return boolean.
     */
    public boolean isCanalIdIsNull() {
        return canalIdIsNull;
    }

    /**
     * Permite buscar cuando campo canalId es NULL
     * @param canalIdIsNull Valor de seteo.
     */
    public void setCanalIdIsNull(boolean canalIdIsNull) {
        this.canalIdIsNull = canalIdIsNull;
    }

    /**
     * Permite buscar cuando campo canalId es NOT NULL
     * @return boolean.
     */
    public boolean isCanalIdIsNotNull() {
        return canalIdIsNotNull;
    }

    /**
     * Permite buscar cuando campo canalId es NOT NULL
     * @param canalIdIsNotNull Valor de seteo.
     */
    public void setCanalIdIsNotNull(boolean canalIdIsNotNull) {
        this.canalIdIsNotNull = canalIdIsNotNull;
    }

    /**
     * Valor de busqueda de campo aplicacionId
     * @return Long.
     */
    public Long getAplicacionId() {
        return aplicacionId;
    }

    /**
     * Valor de busqueda de campo aplicacionId
     * @param aplicacionId Valor de seteo.
     */
    public void setAplicacionId(Long aplicacionId) {
        this.aplicacionId = aplicacionId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getAplicacionIdIn() {
        return this.aplicacionIdIn;
    }

    /**
     * @param aplicacionId Valor a agregar.
     */
    public void addAplicacionIdIn(Long aplicacionId) {
        this.aplicacionIdIn.add(aplicacionId);
    }

    /**
     * Permite buscar cuando campo aplicacionId es NULL
     * @return boolean.
     */
    public boolean isAplicacionIdIsNull() {
        return aplicacionIdIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacionId es NULL
     * @param aplicacionIdIsNull Valor de seteo.
     */
    public void setAplicacionIdIsNull(boolean aplicacionIdIsNull) {
        this.aplicacionIdIsNull = aplicacionIdIsNull;
    }

    /**
     * Permite buscar cuando campo aplicacionId es NOT NULL
     * @return boolean.
     */
    public boolean isAplicacionIdIsNotNull() {
        return aplicacionIdIsNotNull;
    }

    /**
     * Permite buscar cuando campo aplicacionId es NOT NULL
     * @param aplicacionIdIsNotNull Valor de seteo.
     */
    public void setAplicacionIdIsNotNull(boolean aplicacionIdIsNotNull) {
        this.aplicacionIdIsNotNull = aplicacionIdIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombreAplicacion
     * @return String.
     */
    public String getNombreAplicacion() {
        if (nombreAplicacion != null) {
            switch (nombreAplicacionComparator) {
	            case STARTS_WITH:
	                return nombreAplicacion + "%";
	            case CONTAINS:
	                return "%" + nombreAplicacion + "%";
	            case ENDS_WITH:
	                return "%" + nombreAplicacion;
	            case EQUALS:
                	return nombreAplicacion;
              	default:
	            	break;
            }
        }
        return nombreAplicacion;
    }

    /**
     * Valor de busqueda de campo nombreAplicacion
     * @param nombreAplicacion Valor de seteo.
     */
    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreAplicacion
     * @return nombreAplicacionComparator.
     */
    public TextComparator getNombreAplicacionComparator() {
        return nombreAplicacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreAplicacion
     * @param nombreAplicacionComparator Valor de seteo.
     */
    public void setNombreAplicacionComparator(TextComparator nombreAplicacionComparator) {
        this.nombreAplicacionComparator = nombreAplicacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreAplicacionIn() {
        return this.nombreAplicacionIn;
    }

    /**
     * @param nombreAplicacion Valor a agregar.
     */
    public void addNombreAplicacionIn(String nombreAplicacion) {
        this.nombreAplicacionIn.add(nombreAplicacion);
    }

    /**
     * Permite buscar cuando campo nombreAplicacion es NULL
     * @return boolean.
     */
    public boolean isNombreAplicacionIsNull() {
        return nombreAplicacionIsNull;
    }

    /**
     * Permite buscar cuando campo nombreAplicacion es NULL
     * @param nombreAplicacionIsNull Valor de seteo.
     */
    public void setNombreAplicacionIsNull(boolean nombreAplicacionIsNull) {
        this.nombreAplicacionIsNull = nombreAplicacionIsNull;
    }

    /**
     * Permite buscar cuando campo nombreAplicacion es NOT NULL
     * @return boolean.
     */
    public boolean isNombreAplicacionIsNotNull() {
        return nombreAplicacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombreAplicacion es NOT NULL
     * @param nombreAplicacionIsNotNull Valor de seteo.
     */
    public void setNombreAplicacionIsNotNull(boolean nombreAplicacionIsNotNull) {
        this.nombreAplicacionIsNotNull = nombreAplicacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombreServidor
     * @return String.
     */
    public String getNombreServidor() {
        if (nombreServidor != null) {
            switch (nombreServidorComparator) {
	            case STARTS_WITH:
	                return nombreServidor + "%";
	            case CONTAINS:
	                return "%" + nombreServidor + "%";
	            case ENDS_WITH:
	                return "%" + nombreServidor;
	            case EQUALS:
                	return nombreServidor;
              	default:
	            	break;
            }
        }
        return nombreServidor;
    }

    /**
     * Valor de busqueda de campo nombreServidor
     * @param nombreServidor Valor de seteo.
     */
    public void setNombreServidor(String nombreServidor) {
        this.nombreServidor = nombreServidor;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreServidor
     * @return nombreServidorComparator.
     */
    public TextComparator getNombreServidorComparator() {
        return nombreServidorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreServidor
     * @param nombreServidorComparator Valor de seteo.
     */
    public void setNombreServidorComparator(TextComparator nombreServidorComparator) {
        this.nombreServidorComparator = nombreServidorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreServidorIn() {
        return this.nombreServidorIn;
    }

    /**
     * @param nombreServidor Valor a agregar.
     */
    public void addNombreServidorIn(String nombreServidor) {
        this.nombreServidorIn.add(nombreServidor);
    }

    /**
     * Permite buscar cuando campo nombreServidor es NULL
     * @return boolean.
     */
    public boolean isNombreServidorIsNull() {
        return nombreServidorIsNull;
    }

    /**
     * Permite buscar cuando campo nombreServidor es NULL
     * @param nombreServidorIsNull Valor de seteo.
     */
    public void setNombreServidorIsNull(boolean nombreServidorIsNull) {
        this.nombreServidorIsNull = nombreServidorIsNull;
    }

    /**
     * Permite buscar cuando campo nombreServidor es NOT NULL
     * @return boolean.
     */
    public boolean isNombreServidorIsNotNull() {
        return nombreServidorIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombreServidor es NOT NULL
     * @param nombreServidorIsNotNull Valor de seteo.
     */
    public void setNombreServidorIsNotNull(boolean nombreServidorIsNotNull) {
        this.nombreServidorIsNotNull = nombreServidorIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombreServicio
     * @return String.
     */
    public String getNombreServicio() {
        if (nombreServicio != null) {
            switch (nombreServicioComparator) {
	            case STARTS_WITH:
	                return nombreServicio + "%";
	            case CONTAINS:
	                return "%" + nombreServicio + "%";
	            case ENDS_WITH:
	                return "%" + nombreServicio;
	            case EQUALS:
                	return nombreServicio;
              	default:
	            	break;
            }
        }
        return nombreServicio;
    }

    /**
     * Valor de busqueda de campo nombreServicio
     * @param nombreServicio Valor de seteo.
     */
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreServicio
     * @return nombreServicioComparator.
     */
    public TextComparator getNombreServicioComparator() {
        return nombreServicioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreServicio
     * @param nombreServicioComparator Valor de seteo.
     */
    public void setNombreServicioComparator(TextComparator nombreServicioComparator) {
        this.nombreServicioComparator = nombreServicioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreServicioIn() {
        return this.nombreServicioIn;
    }

    /**
     * @param nombreServicio Valor a agregar.
     */
    public void addNombreServicioIn(String nombreServicio) {
        this.nombreServicioIn.add(nombreServicio);
    }

    /**
     * Permite buscar cuando campo nombreServicio es NULL
     * @return boolean.
     */
    public boolean isNombreServicioIsNull() {
        return nombreServicioIsNull;
    }

    /**
     * Permite buscar cuando campo nombreServicio es NULL
     * @param nombreServicioIsNull Valor de seteo.
     */
    public void setNombreServicioIsNull(boolean nombreServicioIsNull) {
        this.nombreServicioIsNull = nombreServicioIsNull;
    }

    /**
     * Permite buscar cuando campo nombreServicio es NOT NULL
     * @return boolean.
     */
    public boolean isNombreServicioIsNotNull() {
        return nombreServicioIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombreServicio es NOT NULL
     * @param nombreServicioIsNotNull Valor de seteo.
     */
    public void setNombreServicioIsNotNull(boolean nombreServicioIsNotNull) {
        this.nombreServicioIsNotNull = nombreServicioIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombreTipoPlanificacion
     * @return String.
     */
    public String getNombreTipoPlanificacion() {
        if (nombreTipoPlanificacion != null) {
            switch (nombreTipoPlanificacionComparator) {
	            case STARTS_WITH:
	                return nombreTipoPlanificacion + "%";
	            case CONTAINS:
	                return "%" + nombreTipoPlanificacion + "%";
	            case ENDS_WITH:
	                return "%" + nombreTipoPlanificacion;
	            case EQUALS:
                	return nombreTipoPlanificacion;
              	default:
	            	break;
            }
        }
        return nombreTipoPlanificacion;
    }

    /**
     * Valor de busqueda de campo nombreTipoPlanificacion
     * @param nombreTipoPlanificacion Valor de seteo.
     */
    public void setNombreTipoPlanificacion(String nombreTipoPlanificacion) {
        this.nombreTipoPlanificacion = nombreTipoPlanificacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreTipoPlanificacion
     * @return nombreTipoPlanificacionComparator.
     */
    public TextComparator getNombreTipoPlanificacionComparator() {
        return nombreTipoPlanificacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreTipoPlanificacion
     * @param nombreTipoPlanificacionComparator Valor de seteo.
     */
    public void setNombreTipoPlanificacionComparator(TextComparator nombreTipoPlanificacionComparator) {
        this.nombreTipoPlanificacionComparator = nombreTipoPlanificacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreTipoPlanificacionIn() {
        return this.nombreTipoPlanificacionIn;
    }

    /**
     * @param nombreTipoPlanificacion Valor a agregar.
     */
    public void addNombreTipoPlanificacionIn(String nombreTipoPlanificacion) {
        this.nombreTipoPlanificacionIn.add(nombreTipoPlanificacion);
    }

    /**
     * Permite buscar cuando campo nombreTipoPlanificacion es NULL
     * @return boolean.
     */
    public boolean isNombreTipoPlanificacionIsNull() {
        return nombreTipoPlanificacionIsNull;
    }

    /**
     * Permite buscar cuando campo nombreTipoPlanificacion es NULL
     * @param nombreTipoPlanificacionIsNull Valor de seteo.
     */
    public void setNombreTipoPlanificacionIsNull(boolean nombreTipoPlanificacionIsNull) {
        this.nombreTipoPlanificacionIsNull = nombreTipoPlanificacionIsNull;
    }

    /**
     * Permite buscar cuando campo nombreTipoPlanificacion es NOT NULL
     * @return boolean.
     */
    public boolean isNombreTipoPlanificacionIsNotNull() {
        return nombreTipoPlanificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombreTipoPlanificacion es NOT NULL
     * @param nombreTipoPlanificacionIsNotNull Valor de seteo.
     */
    public void setNombreTipoPlanificacionIsNotNull(boolean nombreTipoPlanificacionIsNotNull) {
        this.nombreTipoPlanificacionIsNotNull = nombreTipoPlanificacionIsNotNull;
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
     * Valor de busqueda de campo externalid
     * @return String.
     */
    public String getExternalid() {
        if (externalid != null) {
            switch (externalidComparator) {
	            case STARTS_WITH:
	                return externalid + "%";
	            case CONTAINS:
	                return "%" + externalid + "%";
	            case ENDS_WITH:
	                return "%" + externalid;
	            case EQUALS:
                	return externalid;
              	default:
	            	break;
            }
        }
        return externalid;
    }

    /**
     * Valor de busqueda de campo externalid
     * @param externalid Valor de seteo.
     */
    public void setExternalid(String externalid) {
        this.externalid = externalid;
    }

    /**
     * Tipo de comparador para la busqueda por campo externalid
     * @return externalidComparator.
     */
    public TextComparator getExternalidComparator() {
        return externalidComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo externalid
     * @param externalidComparator Valor de seteo.
     */
    public void setExternalidComparator(TextComparator externalidComparator) {
        this.externalidComparator = externalidComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getExternalidIn() {
        return this.externalidIn;
    }

    /**
     * @param externalid Valor a agregar.
     */
    public void addExternalidIn(String externalid) {
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getPlanificacionId() != null) {
            criteria.add(Restrictions.eq(PLANIFICACIONID, getPlanificacionId()));
        }

        if (getPlanificacionIdIn().size() > 0) {
            criteria.add(Restrictions.in(PLANIFICACIONID, getPlanificacionIdIn()));
        }

        if (getServidorId() != null) {
            criteria.add(Restrictions.eq(SERVIDORID, getServidorId()));
        }

        if (getServidorIdIn().size() > 0) {
            criteria.add(Restrictions.in(SERVIDORID, getServidorIdIn()));
        }

        if (isServidorIdIsNull()) {
            criteria.add(Restrictions.isNull(SERVIDORID));
        }

        if (isServidorIdIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVIDORID));
        }

        if (getServicioId() != null) {
            criteria.add(Restrictions.eq(SERVICIOID, getServicioId()));
        }

        if (getServicioIdIn().size() > 0) {
            criteria.add(Restrictions.in(SERVICIOID, getServicioIdIn()));
        }

        if (isServicioIdIsNull()) {
            criteria.add(Restrictions.isNull(SERVICIOID));
        }

        if (isServicioIdIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SERVICIOID));
        }

        if (getOrganismoId() != null) {
            criteria.add(Restrictions.eq(ORGANISMOID, getOrganismoId()));
        }

        if (getOrganismoIdIn().size() > 0) {
            criteria.add(Restrictions.in(ORGANISMOID, getOrganismoIdIn()));
        }

        if (isOrganismoIdIsNull()) {
            criteria.add(Restrictions.isNull(ORGANISMOID));
        }

        if (isOrganismoIdIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ORGANISMOID));
        }

        if (getDir3Organismo() != null) {
            if (getDir3OrganismoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DIR3ORGANISMO, getDir3Organismo()));
            } 
            else if (getDir3OrganismoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DIR3ORGANISMO, getDir3Organismo()));
            }
            else if (getDir3OrganismoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DIR3ORGANISMO, getDir3Organismo())));
            }
            else {
                criteria.add(Restrictions.like(DIR3ORGANISMO, getDir3Organismo()));
            }
        }

        if (getDir3OrganismoIn().size() > 0) {
            criteria.add(Restrictions.in(DIR3ORGANISMO, getDir3OrganismoIn()));
        }

        if (isDir3OrganismoIsNull()) {
            criteria.add(Restrictions.isNull(DIR3ORGANISMO));
        }

        if (isDir3OrganismoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DIR3ORGANISMO));
        }

        if (getTipoPlanificacionId() != null) {
            criteria.add(Restrictions.eq(TIPOPLANIFICACIONID, getTipoPlanificacionId()));
        }

        if (getTipoPlanificacionIdIn().size() > 0) {
            criteria.add(Restrictions.in(TIPOPLANIFICACIONID, getTipoPlanificacionIdIn()));
        }

        if (isTipoPlanificacionIdIsNull()) {
            criteria.add(Restrictions.isNull(TIPOPLANIFICACIONID));
        }

        if (isTipoPlanificacionIdIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TIPOPLANIFICACIONID));
        }

        if (getLunes() != null) {
            if (getLunesComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(LUNES, getLunes()));
            } 
            else if (getLunesComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(LUNES, getLunes()));
            }
            else if (getLunesComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(LUNES, getLunes())));
            }
            else {
                criteria.add(Restrictions.like(LUNES, getLunes()));
            }
        }

        if (getLunesIn().size() > 0) {
            criteria.add(Restrictions.in(LUNES, getLunesIn()));
        }

        if (isLunesIsNull()) {
            criteria.add(Restrictions.isNull(LUNES));
        }

        if (isLunesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(LUNES));
        }

        if (getMartes() != null) {
            if (getMartesComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MARTES, getMartes()));
            } 
            else if (getMartesComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MARTES, getMartes()));
            }
            else if (getMartesComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MARTES, getMartes())));
            }
            else {
                criteria.add(Restrictions.like(MARTES, getMartes()));
            }
        }

        if (getMartesIn().size() > 0) {
            criteria.add(Restrictions.in(MARTES, getMartesIn()));
        }

        if (isMartesIsNull()) {
            criteria.add(Restrictions.isNull(MARTES));
        }

        if (isMartesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MARTES));
        }

        if (getMiercoles() != null) {
            if (getMiercolesComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MIERCOLES, getMiercoles()));
            } 
            else if (getMiercolesComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MIERCOLES, getMiercoles()));
            }
            else if (getMiercolesComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MIERCOLES, getMiercoles())));
            }
            else {
                criteria.add(Restrictions.like(MIERCOLES, getMiercoles()));
            }
        }

        if (getMiercolesIn().size() > 0) {
            criteria.add(Restrictions.in(MIERCOLES, getMiercolesIn()));
        }

        if (isMiercolesIsNull()) {
            criteria.add(Restrictions.isNull(MIERCOLES));
        }

        if (isMiercolesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MIERCOLES));
        }

        if (getJueves() != null) {
            if (getJuevesComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(JUEVES, getJueves()));
            } 
            else if (getJuevesComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(JUEVES, getJueves()));
            }
            else if (getJuevesComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(JUEVES, getJueves())));
            }
            else {
                criteria.add(Restrictions.like(JUEVES, getJueves()));
            }
        }

        if (getJuevesIn().size() > 0) {
            criteria.add(Restrictions.in(JUEVES, getJuevesIn()));
        }

        if (isJuevesIsNull()) {
            criteria.add(Restrictions.isNull(JUEVES));
        }

        if (isJuevesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(JUEVES));
        }

        if (getViernes() != null) {
            if (getViernesComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(VIERNES, getViernes()));
            } 
            else if (getViernesComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(VIERNES, getViernes()));
            }
            else if (getViernesComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(VIERNES, getViernes())));
            }
            else {
                criteria.add(Restrictions.like(VIERNES, getViernes()));
            }
        }

        if (getViernesIn().size() > 0) {
            criteria.add(Restrictions.in(VIERNES, getViernesIn()));
        }

        if (isViernesIsNull()) {
            criteria.add(Restrictions.isNull(VIERNES));
        }

        if (isViernesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(VIERNES));
        }

        if (getSabado() != null) {
            if (getSabadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SABADO, getSabado()));
            } 
            else if (getSabadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SABADO, getSabado()));
            }
            else if (getSabadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SABADO, getSabado())));
            }
            else {
                criteria.add(Restrictions.like(SABADO, getSabado()));
            }
        }

        if (getSabadoIn().size() > 0) {
            criteria.add(Restrictions.in(SABADO, getSabadoIn()));
        }

        if (isSabadoIsNull()) {
            criteria.add(Restrictions.isNull(SABADO));
        }

        if (isSabadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SABADO));
        }

        if (getDomingo() != null) {
            if (getDomingoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DOMINGO, getDomingo()));
            } 
            else if (getDomingoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DOMINGO, getDomingo()));
            }
            else if (getDomingoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DOMINGO, getDomingo())));
            }
            else {
                criteria.add(Restrictions.like(DOMINGO, getDomingo()));
            }
        }

        if (getDomingoIn().size() > 0) {
            criteria.add(Restrictions.in(DOMINGO, getDomingoIn()));
        }

        if (isDomingoIsNull()) {
            criteria.add(Restrictions.isNull(DOMINGO));
        }

        if (isDomingoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DOMINGO));
        }

        if (getHoraDesde() != null) {
            if (getHoraDesdeComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(HORADESDE, getHoraDesde()));
            } 
            else if (getHoraDesdeComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(HORADESDE, getHoraDesde()));
            }
            else if (getHoraDesdeComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(HORADESDE, getHoraDesde())));
            }
            else {
                criteria.add(Restrictions.like(HORADESDE, getHoraDesde()));
            }
        }

        if (getHoraDesdeIn().size() > 0) {
            criteria.add(Restrictions.in(HORADESDE, getHoraDesdeIn()));
        }

        if (isHoraDesdeIsNull()) {
            criteria.add(Restrictions.isNull(HORADESDE));
        }

        if (isHoraDesdeIsNotNull()) {
            criteria.add(Restrictions.isNotNull(HORADESDE));
        }

        
        if (null != getHoraDesdeIni() && null != getHoraDesdeFin()){
        	criteria.add(Restrictions.between(HORADESDE, getHoraDesdeIni(), getHoraDesdeFin()));
        }
        if (null != getHoraHastaIni() && null != getHoraHastaFin()){
        	criteria.add(Restrictions.between(HORAHASTA, getHoraHastaIni(), getHoraHastaFin()));
        }
        
        
        if (getHoraHasta() != null) {
            if (getHoraHastaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(HORAHASTA, getHoraHasta()));
            } 
            else if (getHoraHastaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(HORAHASTA, getHoraHasta()));
            }
            else if (getHoraHastaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(HORAHASTA, getHoraHasta())));
            }
            else {
                criteria.add(Restrictions.like(HORAHASTA, getHoraHasta()));
            }
        }

        if (getHoraHastaIn().size() > 0) {
            criteria.add(Restrictions.in(HORAHASTA, getHoraHastaIn()));
        }

        if (isHoraHastaIsNull()) {
            criteria.add(Restrictions.isNull(HORAHASTA));
        }

        if (isHoraHastaIsNotNull()) {
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

        if (getCreadoPor() != null) {
            if (getCreadoPorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CREADOPOR, getCreadoPor()));
            } 
            else if (getCreadoPorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CREADOPOR, getCreadoPor()));
            }
            else if (getCreadoPorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CREADOPOR, getCreadoPor())));
            }
            else {
                criteria.add(Restrictions.like(CREADOPOR, getCreadoPor()));
            }
        }

        if (getCreadoPorIn().size() > 0) {
            criteria.add(Restrictions.in(CREADOPOR, getCreadoPorIn()));
        }

        if (isCreadoPorIsNull()) {
            criteria.add(Restrictions.isNull(CREADOPOR));
        }

        if (isCreadoPorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CREADOPOR));
        }

        if (getFechaModificacionMin() != null) {
            criteria.add(Restrictions.ge(FECHAMODIFICACION, getFechaModificacionMin()));
        }

        if (getFechaModificacionMax() != null) {
            criteria.add(Restrictions.le(FECHAMODIFICACION, getFechaModificacionMax()));
        }

        if (isFechaModificacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAMODIFICACION));
        }

        if (isFechaModificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAMODIFICACION));
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

        if (getCanalId() != null) {
            criteria.add(Restrictions.eq(CANALID, getCanalId()));
        }

        if (getCanalIdIn().size() > 0) {
            criteria.add(Restrictions.in(CANALID, getCanalIdIn()));
        }

        if (isCanalIdIsNull()) {
            criteria.add(Restrictions.isNull(CANALID));
        }

        if (isCanalIdIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CANALID));
        }

        if (getAplicacionId() != null) {
            criteria.add(Restrictions.eq(APLICACIONID, getAplicacionId()));
        }

        if (getAplicacionIdIn().size() > 0) {
            criteria.add(Restrictions.in(APLICACIONID, getAplicacionIdIn()));
        }

        if (isAplicacionIdIsNull()) {
            criteria.add(Restrictions.isNull(APLICACIONID));
        }

        if (isAplicacionIdIsNotNull()) {
            criteria.add(Restrictions.isNotNull(APLICACIONID));
        }

        if (getNombreAplicacion() != null) {
            if (getNombreAplicacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBREAPLICACION, getNombreAplicacion()));
            } 
            else if (getNombreAplicacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBREAPLICACION, getNombreAplicacion()));
            }
            else if (getNombreAplicacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBREAPLICACION, getNombreAplicacion())));
            }
            else {
                criteria.add(Restrictions.like(NOMBREAPLICACION, getNombreAplicacion()));
            }
        }

        if (getNombreAplicacionIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBREAPLICACION, getNombreAplicacionIn()));
        }

        if (isNombreAplicacionIsNull()) {
            criteria.add(Restrictions.isNull(NOMBREAPLICACION));
        }

        if (isNombreAplicacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBREAPLICACION));
        }

        if (getNombreServidor() != null) {
            if (getNombreServidorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRESERVIDOR, getNombreServidor()));
            } 
            else if (getNombreServidorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRESERVIDOR, getNombreServidor()));
            }
            else if (getNombreServidorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRESERVIDOR, getNombreServidor())));
            }
            else {
                criteria.add(Restrictions.like(NOMBRESERVIDOR, getNombreServidor()));
            }
        }

        if (getNombreServidorIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBRESERVIDOR, getNombreServidorIn()));
        }

        if (isNombreServidorIsNull()) {
            criteria.add(Restrictions.isNull(NOMBRESERVIDOR));
        }

        if (isNombreServidorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBRESERVIDOR));
        }

        if (getNombreServicio() != null) {
            if (getNombreServicioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRESERVICIO, getNombreServicio()));
            } 
            else if (getNombreServicioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRESERVICIO, getNombreServicio()));
            }
            else if (getNombreServicioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRESERVICIO, getNombreServicio())));
            }
            else {
                criteria.add(Restrictions.like(NOMBRESERVICIO, getNombreServicio()));
            }
        }

        if (getNombreServicioIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBRESERVICIO, getNombreServicioIn()));
        }

        if (isNombreServicioIsNull()) {
            criteria.add(Restrictions.isNull(NOMBRESERVICIO));
        }

        if (isNombreServicioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBRESERVICIO));
        }

        if (getNombreTipoPlanificacion() != null) {
            if (getNombreTipoPlanificacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRETIPOPLANIFICACION, getNombreTipoPlanificacion()));
            } 
            else if (getNombreTipoPlanificacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRETIPOPLANIFICACION, getNombreTipoPlanificacion()));
            }
            else if (getNombreTipoPlanificacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRETIPOPLANIFICACION, getNombreTipoPlanificacion())));
            }
            else {
                criteria.add(Restrictions.like(NOMBRETIPOPLANIFICACION, getNombreTipoPlanificacion()));
            }
        }

        if (getNombreTipoPlanificacionIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBRETIPOPLANIFICACION, getNombreTipoPlanificacionIn()));
        }

        if (isNombreTipoPlanificacionIsNull()) {
            criteria.add(Restrictions.isNull(NOMBRETIPOPLANIFICACION));
        }

        if (isNombreTipoPlanificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBRETIPOPLANIFICACION));
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

        if (getExternalid() != null) {
            if (getExternalidComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(EXTERNALID, getExternalid()));
            } 
            else if (getExternalidComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(EXTERNALID, getExternalid()));
            }
            else if (getExternalidComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(EXTERNALID, getExternalid())));
            }
            else {
                criteria.add(Restrictions.like(EXTERNALID, getExternalid()));
            }
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
        
        if(null != maxResultados && maxResultados > 0){
			criteria.setMaxResults(maxResultados);
		}
		
		if (null != primerResultado && primerResultado > 0){
			criteria.setFirstResult(primerResultado);
		}
		
		 if (isEliminadoIsNull()) {
	        	Criterion c1 = Restrictions.isNull(ELIMINADO);
	        	Criterion c2 = Restrictions.eq("eliminado", "N");
	            criteria.add(Restrictions.or(c1,c2));
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
    
    /**
	 * @return the maxResultados
	 */
	public Integer getMaxResultados() {
		return maxResultados;
	}

	/**
	 * @param maxResultados the maxResultados to set
	 */
	public void setMaxResultados(Integer maxResultados) {
		this.maxResultados = maxResultados;
	}

	/**
	 * @return the primerResultado
	 */
	public Integer getPrimerResultado() {
		return primerResultado;
	}

	/**
	 * @param primerResultado the primerResultado to set
	 */
	public void setPrimerResultado(Integer primerResultado) {
		this.primerResultado = primerResultado;
	}

	/**
	 * @return the horaDesdeIni
	 */
	public String getHoraDesdeIni() {
		return horaDesdeIni;
	}

	/**
	 * @param horaDesdeIni the horaDesdeIni to set
	 */
	public void setHoraDesdeIni(String horaDesdeIni) {
		this.horaDesdeIni = horaDesdeIni;
	}

	/**
	 * @return the horaDesdeFin
	 */
	public String getHoraDesdeFin() {
		return horaDesdeFin;
	}

	/**
	 * @param horaDesdeFin the horaDesdeFin to set
	 */
	public void setHoraDesdeFin(String horaDesdeFin) {
		this.horaDesdeFin = horaDesdeFin;
	}

	/**
	 * @return the horaHastaIni
	 */
	public String getHoraHastaIni() {
		return horaHastaIni;
	}

	/**
	 * @param horaHastaIni the horaHastaIni to set
	 */
	public void setHoraHastaIni(String horaHastaIni) {
		this.horaHastaIni = horaHastaIni;
	}

	/**
	 * @return the horaHastaFin
	 */
	public String getHoraHastaFin() {
		return horaHastaFin;
	}

	/**
	 * @param horaHastaFin the horaHastaFin to set
	 */
	public void setHoraHastaFin(String horaHastaFin) {
		this.horaHastaFin = horaHastaFin;
	}
	
	
}
 
