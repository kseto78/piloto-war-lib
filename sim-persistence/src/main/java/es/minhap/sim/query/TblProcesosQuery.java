/*
 *
 * archivo: TblProcesosQuery.java
 *
 * Proyecto: Administracion PAG
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

import java.util.Date;

import es.minhap.common.util.DateUtil;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.sim.model.TblProcesos;

/**
 * Clase con criterios de busqueda para la entidad TblProcesos
 */
public class TblProcesosQuery extends AbstractHibernateQueryEntity<TblProcesos> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String PROCESOSID = "procesosid";
    public static final String NOMBRE = "nombre";
    public static final String NOMBRECLASE = "nombreClase";
    public static final String INICIOULTIMAEJECUCION = "inicioUltimaEjecucion";
    public static final String FINULTIMAEJECUCION = "finUltimaEjecucion";
    public static final String ESTADO = "estado";
    public static final String HORAINICIO = "horaInicio";
    public static final String PROXIMAEJECUCION = "proximaEjecucion";
    public static final String TIPO = "tipo";
    public static final String PARAMETRO1 = "parametro1";
    public static final String PARAMETRO2 = "parametro2";
    public static final String ACTIVO = "activo";
    public static final String FECHAMODIFICACION = "fechaModificacion";
    public static final String MODIFICADOPOR = "modificadoPor";
    public static final String LUNES = "lunes";
    public static final String MARTES = "martes";
    public static final String MIERCOLES = "miercoles";
    public static final String JUEVES = "jueves";
    public static final String VIERNES = "viernes";
    public static final String SABADO = "sabado";
    public static final String DOMINGO = "domingo";


    /**
     * Valor de busqueda de campo procesosid
     */
    private Long procesosid;

    /**
     * Lista de valores del campo procesosid para busquedas tipo IN
     */
    private List<Long> procesosidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo nombre
     */
    private String nombre;

    /**
     * Tipo de comparador para la busqueda por campo nombre
     */
    private TextComparator nombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombre para busquedas tipo IN
     */
    private List<String> nombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombre es NULL
     */
    private boolean nombreIsNull = false;

    /**
     * Permite buscar cuando campo nombre es NOT NULL
     */
    private boolean nombreIsNotNull = false;

    /**
     * Valor de busqueda de campo nombreClase
     */
    private String nombreClase;

    /**
     * Tipo de comparador para la busqueda por campo nombreClase
     */
    private TextComparator nombreClaseComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombreClase para busquedas tipo IN
     */
    private List<String> nombreClaseIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombreClase es NULL
     */
    private boolean nombreClaseIsNull = false;

    /**
     * Permite buscar cuando campo nombreClase es NOT NULL
     */
    private boolean nombreClaseIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha inicioUltimaEjecucion
     */
    private Date inicioUltimaEjecucionMin;

    /**
     * Valor superior de rango de busqueda de fecha inicioUltimaEjecucion
     */
    private Date inicioUltimaEjecucionMax;

    /**
     * Permite buscar cuando campo inicioUltimaEjecucion es NULL
     */
    private boolean inicioUltimaEjecucionIsNull = false;

    /**
     * Permite buscar cuando campo inicioUltimaEjecucion es NOT NULL
     */
    private boolean inicioUltimaEjecucionIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha finUltimaEjecucion
     */
    private Date finUltimaEjecucionMin;

    /**
     * Valor superior de rango de busqueda de fecha finUltimaEjecucion
     */
    private Date finUltimaEjecucionMax;

    /**
     * Permite buscar cuando campo finUltimaEjecucion es NULL
     */
    private boolean finUltimaEjecucionIsNull = false;

    /**
     * Permite buscar cuando campo finUltimaEjecucion es NOT NULL
     */
    private boolean finUltimaEjecucionIsNotNull = false;

    /**
     * Valor de busqueda de campo estado
     */
    private String estado;

    /**
     * Tipo de comparador para la busqueda por campo estado
     */
    private TextComparator estadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo estado para busquedas tipo IN
     */
    private List<String> estadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo estado es NULL
     */
    private boolean estadoIsNull = false;

    /**
     * Permite buscar cuando campo estado es NOT NULL
     */
    private boolean estadoIsNotNull = false;

    /**
     * Valor de busqueda de campo horaInicio
     */
    private String horaInicio;

    /**
     * Tipo de comparador para la busqueda por campo horaInicio
     */
    private TextComparator horaInicioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo horaInicio para busquedas tipo IN
     */
    private List<String> horaInicioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo horaInicio es NULL
     */
    private boolean horaInicioIsNull = false;

    /**
     * Permite buscar cuando campo horaInicio es NOT NULL
     */
    private boolean horaInicioIsNotNull = false;

    /**
     * Valor de busqueda de campo proximaEjecucion
     */
    private String proximaEjecucion;

    /**
     * Tipo de comparador para la busqueda por campo proximaEjecucion
     */
    private TextComparator proximaEjecucionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo proximaEjecucion para busquedas tipo IN
     */
    private List<String> proximaEjecucionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo proximaEjecucion es NULL
     */
    private boolean proximaEjecucionIsNull = false;

    /**
     * Permite buscar cuando campo proximaEjecucion es NOT NULL
     */
    private boolean proximaEjecucionIsNotNull = false;

    /**
     * Valor de busqueda de campo tipo
     */
    private String tipo;

    /**
     * Tipo de comparador para la busqueda por campo tipo
     */
    private TextComparator tipoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo tipo para busquedas tipo IN
     */
    private List<String> tipoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo tipo es NULL
     */
    private boolean tipoIsNull = false;

    /**
     * Permite buscar cuando campo tipo es NOT NULL
     */
    private boolean tipoIsNotNull = false;

    /**
     * Valor de busqueda de campo parametro1
     */
    private String parametro1;

    /**
     * Tipo de comparador para la busqueda por campo parametro1
     */
    private TextComparator parametro1Comparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo parametro1 para busquedas tipo IN
     */
    private List<String> parametro1In = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo parametro1 es NULL
     */
    private boolean parametro1IsNull = false;

    /**
     * Permite buscar cuando campo parametro1 es NOT NULL
     */
    private boolean parametro1IsNotNull = false;

    /**
     * Valor de busqueda de campo parametro2
     */
    private String parametro2;

    /**
     * Tipo de comparador para la busqueda por campo parametro2
     */
    private TextComparator parametro2Comparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo parametro2 para busquedas tipo IN
     */
    private List<String> parametro2In = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo parametro2 es NULL
     */
    private boolean parametro2IsNull = false;

    /**
     * Permite buscar cuando campo parametro2 es NOT NULL
     */
    private boolean parametro2IsNotNull = false;

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
     * Valor de busqueda de campo lunes
     */
    private Boolean lunes;

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
    private Boolean martes;

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
    private Boolean miercoles;

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
    private Boolean jueves;

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
    private Boolean viernes;

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
    private Boolean sabado;

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
    private Boolean domingo;

    /**
     * Permite buscar cuando campo domingo es NULL
     */
    private boolean domingoIsNull = false;

    /**
     * Permite buscar cuando campo domingo es NOT NULL
     */
    private boolean domingoIsNotNull = false;

    /**
     * Constructor default
     */
    public TblProcesosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblProcesosQuery(Long procesosid) {
        setProcesosid(procesosid);
    }

    /**
     * Valor de busqueda de campo procesosid
     * @return Long.
     */
    public Long getProcesosid() {
        return procesosid;
    }

    /**
     * Valor de busqueda de campo procesosid
     * @param procesosid Valor de seteo.
     */
    public void setProcesosid(Long procesosid) {
        this.procesosid = procesosid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getProcesosidIn() {
        return this.procesosidIn;
    }

    /**
     * @param procesosid Valor a agregar.
     */
    public void addProcesosidIn(Long procesosid) {
        this.procesosidIn.add(procesosid);
    }

    /**
     * Valor de busqueda de campo nombre
     * @return String.
     */
    public String getNombre() {
        if (nombre != null) {
            switch (nombreComparator) {
	            case STARTS_WITH:
	                return nombre + "%";
	            case CONTAINS:
	                return "%" + nombre + "%";
	            case ENDS_WITH:
	                return "%" + nombre;
	            case EQUALS:
                	return nombre;
              	default:
	            	break;
            }
        }
        return nombre;
    }

    /**
     * Valor de busqueda de campo nombre
     * @param nombre Valor de seteo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombre
     * @return nombreComparator.
     */
    public TextComparator getNombreComparator() {
        return nombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombre
     * @param nombreComparator Valor de seteo.
     */
    public void setNombreComparator(TextComparator nombreComparator) {
        this.nombreComparator = nombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreIn() {
        return this.nombreIn;
    }

    /**
     * @param nombre Valor a agregar.
     */
    public void addNombreIn(String nombre) {
        this.nombreIn.add(nombre);
    }

    /**
     * Permite buscar cuando campo nombre es NULL
     * @return boolean.
     */
    public boolean isNombreIsNull() {
        return nombreIsNull;
    }

    /**
     * Permite buscar cuando campo nombre es NULL
     * @param nombreIsNull Valor de seteo.
     */
    public void setNombreIsNull(boolean nombreIsNull) {
        this.nombreIsNull = nombreIsNull;
    }

    /**
     * Permite buscar cuando campo nombre es NOT NULL
     * @return boolean.
     */
    public boolean isNombreIsNotNull() {
        return nombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombre es NOT NULL
     * @param nombreIsNotNull Valor de seteo.
     */
    public void setNombreIsNotNull(boolean nombreIsNotNull) {
        this.nombreIsNotNull = nombreIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombreClase
     * @return String.
     */
    public String getNombreClase() {
        if (nombreClase != null) {
            switch (nombreClaseComparator) {
	            case STARTS_WITH:
	                return nombreClase + "%";
	            case CONTAINS:
	                return "%" + nombreClase + "%";
	            case ENDS_WITH:
	                return "%" + nombreClase;
	            case EQUALS:
                	return nombreClase;
              	default:
	            	break;
            }
        }
        return nombreClase;
    }

    /**
     * Valor de busqueda de campo nombreClase
     * @param nombreClase Valor de seteo.
     */
    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreClase
     * @return nombreClaseComparator.
     */
    public TextComparator getNombreClaseComparator() {
        return nombreClaseComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreClase
     * @param nombreClaseComparator Valor de seteo.
     */
    public void setNombreClaseComparator(TextComparator nombreClaseComparator) {
        this.nombreClaseComparator = nombreClaseComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreClaseIn() {
        return this.nombreClaseIn;
    }

    /**
     * @param nombreClase Valor a agregar.
     */
    public void addNombreClaseIn(String nombreClase) {
        this.nombreClaseIn.add(nombreClase);
    }

    /**
     * Permite buscar cuando campo nombreClase es NULL
     * @return boolean.
     */
    public boolean isNombreClaseIsNull() {
        return nombreClaseIsNull;
    }

    /**
     * Permite buscar cuando campo nombreClase es NULL
     * @param nombreClaseIsNull Valor de seteo.
     */
    public void setNombreClaseIsNull(boolean nombreClaseIsNull) {
        this.nombreClaseIsNull = nombreClaseIsNull;
    }

    /**
     * Permite buscar cuando campo nombreClase es NOT NULL
     * @return boolean.
     */
    public boolean isNombreClaseIsNotNull() {
        return nombreClaseIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombreClase es NOT NULL
     * @param nombreClaseIsNotNull Valor de seteo.
     */
    public void setNombreClaseIsNotNull(boolean nombreClaseIsNotNull) {
        this.nombreClaseIsNotNull = nombreClaseIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha inicioUltimaEjecucion
     * @return ${field.getName()}Min.
     */
    public Date getInicioUltimaEjecucionMin() {
        if (inicioUltimaEjecucionMin != null) {
            return DateUtil.toDayBegin(inicioUltimaEjecucionMin);
        }
        return inicioUltimaEjecucionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha inicioUltimaEjecucion
     * @param inicioUltimaEjecucionMin Valor de seteo.
     */
    public void setInicioUltimaEjecucionMin(Date inicioUltimaEjecucionMin) {
        this.inicioUltimaEjecucionMin = inicioUltimaEjecucionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha inicioUltimaEjecucion
     * @return inicioUltimaEjecucionMax.
     */
    public Date getInicioUltimaEjecucionMax() {
        if (inicioUltimaEjecucionMax != null) {
            return DateUtil.toDayEnd(inicioUltimaEjecucionMax);
        }
        return inicioUltimaEjecucionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha inicioUltimaEjecucion
     * @param inicioUltimaEjecucionMax Valor de seteo.
     */
    public void setInicioUltimaEjecucionMax(Date inicioUltimaEjecucionMax) {
        this.inicioUltimaEjecucionMax = inicioUltimaEjecucionMax;
    }

    /**
     * Permite buscar cuando campo inicioUltimaEjecucion es NULL
     * @return boolean.
     */
    public boolean isInicioUltimaEjecucionIsNull() {
        return inicioUltimaEjecucionIsNull;
    }

    /**
     * Permite buscar cuando campo inicioUltimaEjecucion es NULL
     * @param inicioUltimaEjecucionIsNull Valor de seteo.
     */
    public void setInicioUltimaEjecucionIsNull(boolean inicioUltimaEjecucionIsNull) {
        this.inicioUltimaEjecucionIsNull = inicioUltimaEjecucionIsNull;
    }

    /**
     * Permite buscar cuando campo inicioUltimaEjecucion es NOT NULL
     * @return boolean.
     */
    public boolean isInicioUltimaEjecucionIsNotNull() {
        return inicioUltimaEjecucionIsNotNull;
    }

    /**
     * Permite buscar cuando campo inicioUltimaEjecucion es NOT NULL
     * @param inicioUltimaEjecucionIsNotNull Valor de seteo.
     */
    public void setInicioUltimaEjecucionIsNotNull(boolean inicioUltimaEjecucionIsNotNull) {
        this.inicioUltimaEjecucionIsNotNull = inicioUltimaEjecucionIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha finUltimaEjecucion
     * @return ${field.getName()}Min.
     */
    public Date getFinUltimaEjecucionMin() {
        if (finUltimaEjecucionMin != null) {
            return DateUtil.toDayBegin(finUltimaEjecucionMin);
        }
        return finUltimaEjecucionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha finUltimaEjecucion
     * @param finUltimaEjecucionMin Valor de seteo.
     */
    public void setFinUltimaEjecucionMin(Date finUltimaEjecucionMin) {
        this.finUltimaEjecucionMin = finUltimaEjecucionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha finUltimaEjecucion
     * @return finUltimaEjecucionMax.
     */
    public Date getFinUltimaEjecucionMax() {
        if (finUltimaEjecucionMax != null) {
            return DateUtil.toDayEnd(finUltimaEjecucionMax);
        }
        return finUltimaEjecucionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha finUltimaEjecucion
     * @param finUltimaEjecucionMax Valor de seteo.
     */
    public void setFinUltimaEjecucionMax(Date finUltimaEjecucionMax) {
        this.finUltimaEjecucionMax = finUltimaEjecucionMax;
    }

    /**
     * Permite buscar cuando campo finUltimaEjecucion es NULL
     * @return boolean.
     */
    public boolean isFinUltimaEjecucionIsNull() {
        return finUltimaEjecucionIsNull;
    }

    /**
     * Permite buscar cuando campo finUltimaEjecucion es NULL
     * @param finUltimaEjecucionIsNull Valor de seteo.
     */
    public void setFinUltimaEjecucionIsNull(boolean finUltimaEjecucionIsNull) {
        this.finUltimaEjecucionIsNull = finUltimaEjecucionIsNull;
    }

    /**
     * Permite buscar cuando campo finUltimaEjecucion es NOT NULL
     * @return boolean.
     */
    public boolean isFinUltimaEjecucionIsNotNull() {
        return finUltimaEjecucionIsNotNull;
    }

    /**
     * Permite buscar cuando campo finUltimaEjecucion es NOT NULL
     * @param finUltimaEjecucionIsNotNull Valor de seteo.
     */
    public void setFinUltimaEjecucionIsNotNull(boolean finUltimaEjecucionIsNotNull) {
        this.finUltimaEjecucionIsNotNull = finUltimaEjecucionIsNotNull;
    }

    /**
     * Valor de busqueda de campo estado
     * @return String.
     */
    public String getEstado() {
        if (estado != null) {
            switch (estadoComparator) {
	            case STARTS_WITH:
	                return estado + "%";
	            case CONTAINS:
	                return "%" + estado + "%";
	            case ENDS_WITH:
	                return "%" + estado;
	            case EQUALS:
                	return estado;
              	default:
	            	break;
            }
        }
        return estado;
    }

    /**
     * Valor de busqueda de campo estado
     * @param estado Valor de seteo.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Tipo de comparador para la busqueda por campo estado
     * @return estadoComparator.
     */
    public TextComparator getEstadoComparator() {
        return estadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo estado
     * @param estadoComparator Valor de seteo.
     */
    public void setEstadoComparator(TextComparator estadoComparator) {
        this.estadoComparator = estadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEstadoIn() {
        return this.estadoIn;
    }

    /**
     * @param estado Valor a agregar.
     */
    public void addEstadoIn(String estado) {
        this.estadoIn.add(estado);
    }

    /**
     * Permite buscar cuando campo estado es NULL
     * @return boolean.
     */
    public boolean isEstadoIsNull() {
        return estadoIsNull;
    }

    /**
     * Permite buscar cuando campo estado es NULL
     * @param estadoIsNull Valor de seteo.
     */
    public void setEstadoIsNull(boolean estadoIsNull) {
        this.estadoIsNull = estadoIsNull;
    }

    /**
     * Permite buscar cuando campo estado es NOT NULL
     * @return boolean.
     */
    public boolean isEstadoIsNotNull() {
        return estadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo estado es NOT NULL
     * @param estadoIsNotNull Valor de seteo.
     */
    public void setEstadoIsNotNull(boolean estadoIsNotNull) {
        this.estadoIsNotNull = estadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo horaInicio
     * @return String.
     */
    public String getHoraInicio() {
        if (horaInicio != null) {
            switch (horaInicioComparator) {
	            case STARTS_WITH:
	                return horaInicio + "%";
	            case CONTAINS:
	                return "%" + horaInicio + "%";
	            case ENDS_WITH:
	                return "%" + horaInicio;
	            case EQUALS:
                	return horaInicio;
              	default:
	            	break;
            }
        }
        return horaInicio;
    }

    /**
     * Valor de busqueda de campo horaInicio
     * @param horaInicio Valor de seteo.
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Tipo de comparador para la busqueda por campo horaInicio
     * @return horaInicioComparator.
     */
    public TextComparator getHoraInicioComparator() {
        return horaInicioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo horaInicio
     * @param horaInicioComparator Valor de seteo.
     */
    public void setHoraInicioComparator(TextComparator horaInicioComparator) {
        this.horaInicioComparator = horaInicioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getHoraInicioIn() {
        return this.horaInicioIn;
    }

    /**
     * @param horaInicio Valor a agregar.
     */
    public void addHoraInicioIn(String horaInicio) {
        this.horaInicioIn.add(horaInicio);
    }

    /**
     * Permite buscar cuando campo horaInicio es NULL
     * @return boolean.
     */
    public boolean isHoraInicioIsNull() {
        return horaInicioIsNull;
    }

    /**
     * Permite buscar cuando campo horaInicio es NULL
     * @param horaInicioIsNull Valor de seteo.
     */
    public void setHoraInicioIsNull(boolean horaInicioIsNull) {
        this.horaInicioIsNull = horaInicioIsNull;
    }

    /**
     * Permite buscar cuando campo horaInicio es NOT NULL
     * @return boolean.
     */
    public boolean isHoraInicioIsNotNull() {
        return horaInicioIsNotNull;
    }

    /**
     * Permite buscar cuando campo horaInicio es NOT NULL
     * @param horaInicioIsNotNull Valor de seteo.
     */
    public void setHoraInicioIsNotNull(boolean horaInicioIsNotNull) {
        this.horaInicioIsNotNull = horaInicioIsNotNull;
    }

    /**
     * Valor de busqueda de campo proximaEjecucion
     * @return String.
     */
    public String getProximaEjecucion() {
        if (proximaEjecucion != null) {
            switch (proximaEjecucionComparator) {
	            case STARTS_WITH:
	                return proximaEjecucion + "%";
	            case CONTAINS:
	                return "%" + proximaEjecucion + "%";
	            case ENDS_WITH:
	                return "%" + proximaEjecucion;
	            case EQUALS:
                	return proximaEjecucion;
              	default:
	            	break;
            }
        }
        return proximaEjecucion;
    }

    /**
     * Valor de busqueda de campo proximaEjecucion
     * @param proximaEjecucion Valor de seteo.
     */
    public void setProximaEjecucion(String proximaEjecucion) {
        this.proximaEjecucion = proximaEjecucion;
    }

    /**
     * Tipo de comparador para la busqueda por campo proximaEjecucion
     * @return proximaEjecucionComparator.
     */
    public TextComparator getProximaEjecucionComparator() {
        return proximaEjecucionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo proximaEjecucion
     * @param proximaEjecucionComparator Valor de seteo.
     */
    public void setProximaEjecucionComparator(TextComparator proximaEjecucionComparator) {
        this.proximaEjecucionComparator = proximaEjecucionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getProximaEjecucionIn() {
        return this.proximaEjecucionIn;
    }

    /**
     * @param proximaEjecucion Valor a agregar.
     */
    public void addProximaEjecucionIn(String proximaEjecucion) {
        this.proximaEjecucionIn.add(proximaEjecucion);
    }

    /**
     * Permite buscar cuando campo proximaEjecucion es NULL
     * @return boolean.
     */
    public boolean isProximaEjecucionIsNull() {
        return proximaEjecucionIsNull;
    }

    /**
     * Permite buscar cuando campo proximaEjecucion es NULL
     * @param proximaEjecucionIsNull Valor de seteo.
     */
    public void setProximaEjecucionIsNull(boolean proximaEjecucionIsNull) {
        this.proximaEjecucionIsNull = proximaEjecucionIsNull;
    }

    /**
     * Permite buscar cuando campo proximaEjecucion es NOT NULL
     * @return boolean.
     */
    public boolean isProximaEjecucionIsNotNull() {
        return proximaEjecucionIsNotNull;
    }

    /**
     * Permite buscar cuando campo proximaEjecucion es NOT NULL
     * @param proximaEjecucionIsNotNull Valor de seteo.
     */
    public void setProximaEjecucionIsNotNull(boolean proximaEjecucionIsNotNull) {
        this.proximaEjecucionIsNotNull = proximaEjecucionIsNotNull;
    }

    /**
     * Valor de busqueda de campo tipo
     * @return String.
     */
    public String getTipo() {
        if (tipo != null) {
            switch (tipoComparator) {
	            case STARTS_WITH:
	                return tipo + "%";
	            case CONTAINS:
	                return "%" + tipo + "%";
	            case ENDS_WITH:
	                return "%" + tipo;
	            case EQUALS:
                	return tipo;
              	default:
	            	break;
            }
        }
        return tipo;
    }

    /**
     * Valor de busqueda de campo tipo
     * @param tipo Valor de seteo.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipo
     * @return tipoComparator.
     */
    public TextComparator getTipoComparator() {
        return tipoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipo
     * @param tipoComparator Valor de seteo.
     */
    public void setTipoComparator(TextComparator tipoComparator) {
        this.tipoComparator = tipoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTipoIn() {
        return this.tipoIn;
    }

    /**
     * @param tipo Valor a agregar.
     */
    public void addTipoIn(String tipo) {
        this.tipoIn.add(tipo);
    }

    /**
     * Permite buscar cuando campo tipo es NULL
     * @return boolean.
     */
    public boolean isTipoIsNull() {
        return tipoIsNull;
    }

    /**
     * Permite buscar cuando campo tipo es NULL
     * @param tipoIsNull Valor de seteo.
     */
    public void setTipoIsNull(boolean tipoIsNull) {
        this.tipoIsNull = tipoIsNull;
    }

    /**
     * Permite buscar cuando campo tipo es NOT NULL
     * @return boolean.
     */
    public boolean isTipoIsNotNull() {
        return tipoIsNotNull;
    }

    /**
     * Permite buscar cuando campo tipo es NOT NULL
     * @param tipoIsNotNull Valor de seteo.
     */
    public void setTipoIsNotNull(boolean tipoIsNotNull) {
        this.tipoIsNotNull = tipoIsNotNull;
    }

    /**
     * Valor de busqueda de campo parametro1
     * @return String.
     */
    public String getParametro1() {
        if (parametro1 != null) {
            switch (parametro1Comparator) {
	            case STARTS_WITH:
	                return parametro1 + "%";
	            case CONTAINS:
	                return "%" + parametro1 + "%";
	            case ENDS_WITH:
	                return "%" + parametro1;
	            case EQUALS:
                	return parametro1;
              	default:
	            	break;
            }
        }
        return parametro1;
    }

    /**
     * Valor de busqueda de campo parametro1
     * @param parametro1 Valor de seteo.
     */
    public void setParametro1(String parametro1) {
        this.parametro1 = parametro1;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametro1
     * @return parametro1Comparator.
     */
    public TextComparator getParametro1Comparator() {
        return parametro1Comparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametro1
     * @param parametro1Comparator Valor de seteo.
     */
    public void setParametro1Comparator(TextComparator parametro1Comparator) {
        this.parametro1Comparator = parametro1Comparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getParametro1In() {
        return this.parametro1In;
    }

    /**
     * @param parametro1 Valor a agregar.
     */
    public void addParametro1In(String parametro1) {
        this.parametro1In.add(parametro1);
    }

    /**
     * Permite buscar cuando campo parametro1 es NULL
     * @return boolean.
     */
    public boolean isParametro1IsNull() {
        return parametro1IsNull;
    }

    /**
     * Permite buscar cuando campo parametro1 es NULL
     * @param parametro1IsNull Valor de seteo.
     */
    public void setParametro1IsNull(boolean parametro1IsNull) {
        this.parametro1IsNull = parametro1IsNull;
    }

    /**
     * Permite buscar cuando campo parametro1 es NOT NULL
     * @return boolean.
     */
    public boolean isParametro1IsNotNull() {
        return parametro1IsNotNull;
    }

    /**
     * Permite buscar cuando campo parametro1 es NOT NULL
     * @param parametro1IsNotNull Valor de seteo.
     */
    public void setParametro1IsNotNull(boolean parametro1IsNotNull) {
        this.parametro1IsNotNull = parametro1IsNotNull;
    }

    /**
     * Valor de busqueda de campo parametro2
     * @return String.
     */
    public String getParametro2() {
        if (parametro2 != null) {
            switch (parametro2Comparator) {
	            case STARTS_WITH:
	                return parametro2 + "%";
	            case CONTAINS:
	                return "%" + parametro2 + "%";
	            case ENDS_WITH:
	                return "%" + parametro2;
	            case EQUALS:
                	return parametro2;
              	default:
	            	break;
            }
        }
        return parametro2;
    }

    /**
     * Valor de busqueda de campo parametro2
     * @param parametro2 Valor de seteo.
     */
    public void setParametro2(String parametro2) {
        this.parametro2 = parametro2;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametro2
     * @return parametro2Comparator.
     */
    public TextComparator getParametro2Comparator() {
        return parametro2Comparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametro2
     * @param parametro2Comparator Valor de seteo.
     */
    public void setParametro2Comparator(TextComparator parametro2Comparator) {
        this.parametro2Comparator = parametro2Comparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getParametro2In() {
        return this.parametro2In;
    }

    /**
     * @param parametro2 Valor a agregar.
     */
    public void addParametro2In(String parametro2) {
        this.parametro2In.add(parametro2);
    }

    /**
     * Permite buscar cuando campo parametro2 es NULL
     * @return boolean.
     */
    public boolean isParametro2IsNull() {
        return parametro2IsNull;
    }

    /**
     * Permite buscar cuando campo parametro2 es NULL
     * @param parametro2IsNull Valor de seteo.
     */
    public void setParametro2IsNull(boolean parametro2IsNull) {
        this.parametro2IsNull = parametro2IsNull;
    }

    /**
     * Permite buscar cuando campo parametro2 es NOT NULL
     * @return boolean.
     */
    public boolean isParametro2IsNotNull() {
        return parametro2IsNotNull;
    }

    /**
     * Permite buscar cuando campo parametro2 es NOT NULL
     * @param parametro2IsNotNull Valor de seteo.
     */
    public void setParametro2IsNotNull(boolean parametro2IsNotNull) {
        this.parametro2IsNotNull = parametro2IsNotNull;
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
     * Valor de busqueda de campo lunes
     * @return Boolean.
     */
    public Boolean getLunes() {
        return lunes;
    }

    /**
     * Valor de busqueda de campo lunes
     * @param lunes Valor de seteo.
     */
    public void setLunes(Boolean lunes) {
        this.lunes = lunes;
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
     * @return Boolean.
     */
    public Boolean getMartes() {
        return martes;
    }

    /**
     * Valor de busqueda de campo martes
     * @param martes Valor de seteo.
     */
    public void setMartes(Boolean martes) {
        this.martes = martes;
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
     * @return Boolean.
     */
    public Boolean getMiercoles() {
        return miercoles;
    }

    /**
     * Valor de busqueda de campo miercoles
     * @param miercoles Valor de seteo.
     */
    public void setMiercoles(Boolean miercoles) {
        this.miercoles = miercoles;
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
     * @return Boolean.
     */
    public Boolean getJueves() {
        return jueves;
    }

    /**
     * Valor de busqueda de campo jueves
     * @param jueves Valor de seteo.
     */
    public void setJueves(Boolean jueves) {
        this.jueves = jueves;
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
     * @return Boolean.
     */
    public Boolean getViernes() {
        return viernes;
    }

    /**
     * Valor de busqueda de campo viernes
     * @param viernes Valor de seteo.
     */
    public void setViernes(Boolean viernes) {
        this.viernes = viernes;
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
     * @return Boolean.
     */
    public Boolean getSabado() {
        return sabado;
    }

    /**
     * Valor de busqueda de campo sabado
     * @param sabado Valor de seteo.
     */
    public void setSabado(Boolean sabado) {
        this.sabado = sabado;
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
     * @return Boolean.
     */
    public Boolean getDomingo() {
        return domingo;
    }

    /**
     * Valor de busqueda de campo domingo
     * @param domingo Valor de seteo.
     */
    public void setDomingo(Boolean domingo) {
        this.domingo = domingo;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getProcesosid() != null) {
            criteria.add(Restrictions.eq(PROCESOSID, getProcesosid()));
        }

        if (getProcesosidIn().size() > 0) {
            criteria.add(Restrictions.in(PROCESOSID, getProcesosidIn()));
        }

        if (getNombre() != null) {
            if (getNombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRE, getNombre()));
            } 
            else if (getNombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRE, getNombre()));
            }
            else if (getNombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRE, getNombre())));
            }
            else {
                criteria.add(Restrictions.like(NOMBRE, getNombre()));
            }
        }

        if (getNombreIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBRE, getNombreIn()));
        }

        if (isNombreIsNull()) {
            criteria.add(Restrictions.isNull(NOMBRE));
        }

        if (isNombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBRE));
        }

        if (getNombreClase() != null) {
            if (getNombreClaseComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBRECLASE, getNombreClase()));
            } 
            else if (getNombreClaseComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRECLASE, getNombreClase()));
            }
            else if (getNombreClaseComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRECLASE, getNombreClase())));
            }
            else {
                criteria.add(Restrictions.like(NOMBRECLASE, getNombreClase()));
            }
        }

        if (getNombreClaseIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBRECLASE, getNombreClaseIn()));
        }

        if (isNombreClaseIsNull()) {
            criteria.add(Restrictions.isNull(NOMBRECLASE));
        }

        if (isNombreClaseIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBRECLASE));
        }

        if (getInicioUltimaEjecucionMin() != null) {
            criteria.add(Restrictions.ge(INICIOULTIMAEJECUCION, getInicioUltimaEjecucionMin()));
        }

        if (getInicioUltimaEjecucionMax() != null) {
            criteria.add(Restrictions.le(INICIOULTIMAEJECUCION, getInicioUltimaEjecucionMax()));
        }

        if (isInicioUltimaEjecucionIsNull()) {
            criteria.add(Restrictions.isNull(INICIOULTIMAEJECUCION));
        }

        if (isInicioUltimaEjecucionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(INICIOULTIMAEJECUCION));
        }

        if (getFinUltimaEjecucionMin() != null) {
            criteria.add(Restrictions.ge(FINULTIMAEJECUCION, getFinUltimaEjecucionMin()));
        }

        if (getFinUltimaEjecucionMax() != null) {
            criteria.add(Restrictions.le(FINULTIMAEJECUCION, getFinUltimaEjecucionMax()));
        }

        if (isFinUltimaEjecucionIsNull()) {
            criteria.add(Restrictions.isNull(FINULTIMAEJECUCION));
        }

        if (isFinUltimaEjecucionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FINULTIMAEJECUCION));
        }

        if (getEstado() != null) {
            if (getEstadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ESTADO, getEstado()));
            } 
            else if (getEstadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ESTADO, getEstado()));
            }
            else if (getEstadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ESTADO, getEstado())));
            }
            else {
                criteria.add(Restrictions.like(ESTADO, getEstado()));
            }
        }

        if (getEstadoIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADO, getEstadoIn()));
        }

        if (isEstadoIsNull()) {
            criteria.add(Restrictions.isNull(ESTADO));
        }

        if (isEstadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADO));
        }

        if (getHoraInicio() != null) {
            if (getHoraInicioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(HORAINICIO, getHoraInicio()));
            } 
            else if (getHoraInicioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(HORAINICIO, getHoraInicio()));
            }
            else if (getHoraInicioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(HORAINICIO, getHoraInicio())));
            }
            else {
                criteria.add(Restrictions.like(HORAINICIO, getHoraInicio()));
            }
        }

        if (getHoraInicioIn().size() > 0) {
            criteria.add(Restrictions.in(HORAINICIO, getHoraInicioIn()));
        }

        if (isHoraInicioIsNull()) {
            criteria.add(Restrictions.isNull(HORAINICIO));
        }

        if (isHoraInicioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(HORAINICIO));
        }

        if (getProximaEjecucion() != null) {
            if (getProximaEjecucionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PROXIMAEJECUCION, getProximaEjecucion()));
            } 
            else if (getProximaEjecucionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PROXIMAEJECUCION, getProximaEjecucion()));
            }
            else if (getProximaEjecucionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PROXIMAEJECUCION, getProximaEjecucion())));
            }
            else {
                criteria.add(Restrictions.like(PROXIMAEJECUCION, getProximaEjecucion()));
            }
        }

        if (getProximaEjecucionIn().size() > 0) {
            criteria.add(Restrictions.in(PROXIMAEJECUCION, getProximaEjecucionIn()));
        }

        if (isProximaEjecucionIsNull()) {
            criteria.add(Restrictions.isNull(PROXIMAEJECUCION));
        }

        if (isProximaEjecucionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PROXIMAEJECUCION));
        }

        if (getTipo() != null) {
            if (getTipoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TIPO, getTipo()));
            } 
            else if (getTipoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TIPO, getTipo()));
            }
            else if (getTipoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TIPO, getTipo())));
            }
            else {
                criteria.add(Restrictions.like(TIPO, getTipo()));
            }
        }

        if (getTipoIn().size() > 0) {
            criteria.add(Restrictions.in(TIPO, getTipoIn()));
        }

        if (isTipoIsNull()) {
            criteria.add(Restrictions.isNull(TIPO));
        }

        if (isTipoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TIPO));
        }

        if (getParametro1() != null) {
            if (getParametro1Comparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PARAMETRO1, getParametro1()));
            } 
            else if (getParametro1Comparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PARAMETRO1, getParametro1()));
            }
            else if (getParametro1Comparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PARAMETRO1, getParametro1())));
            }
            else {
                criteria.add(Restrictions.like(PARAMETRO1, getParametro1()));
            }
        }

        if (getParametro1In().size() > 0) {
            criteria.add(Restrictions.in(PARAMETRO1, getParametro1In()));
        }

        if (isParametro1IsNull()) {
            criteria.add(Restrictions.isNull(PARAMETRO1));
        }

        if (isParametro1IsNotNull()) {
            criteria.add(Restrictions.isNotNull(PARAMETRO1));
        }

        if (getParametro2() != null) {
            if (getParametro2Comparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PARAMETRO2, getParametro2()));
            } 
            else if (getParametro2Comparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PARAMETRO2, getParametro2()));
            }
            else if (getParametro2Comparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PARAMETRO2, getParametro2())));
            }
            else {
                criteria.add(Restrictions.like(PARAMETRO2, getParametro2()));
            }
        }

        if (getParametro2In().size() > 0) {
            criteria.add(Restrictions.in(PARAMETRO2, getParametro2In()));
        }

        if (isParametro2IsNull()) {
            criteria.add(Restrictions.isNull(PARAMETRO2));
        }

        if (isParametro2IsNotNull()) {
            criteria.add(Restrictions.isNotNull(PARAMETRO2));
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

        if (getLunes() != null) {
            criteria.add(Restrictions.eq(LUNES, getLunes()));
        }

        if (isLunesIsNull()) {
            criteria.add(Restrictions.isNull(LUNES));
        }

        if (isLunesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(LUNES));
        }

        if (getMartes() != null) {
            criteria.add(Restrictions.eq(MARTES, getMartes()));
        }

        if (isMartesIsNull()) {
            criteria.add(Restrictions.isNull(MARTES));
        }

        if (isMartesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MARTES));
        }

        if (getMiercoles() != null) {
            criteria.add(Restrictions.eq(MIERCOLES, getMiercoles()));
        }

        if (isMiercolesIsNull()) {
            criteria.add(Restrictions.isNull(MIERCOLES));
        }

        if (isMiercolesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MIERCOLES));
        }

        if (getJueves() != null) {
            criteria.add(Restrictions.eq(JUEVES, getJueves()));
        }

        if (isJuevesIsNull()) {
            criteria.add(Restrictions.isNull(JUEVES));
        }

        if (isJuevesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(JUEVES));
        }

        if (getViernes() != null) {
            criteria.add(Restrictions.eq(VIERNES, getViernes()));
        }

        if (isViernesIsNull()) {
            criteria.add(Restrictions.isNull(VIERNES));
        }

        if (isViernesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(VIERNES));
        }

        if (getSabado() != null) {
            criteria.add(Restrictions.eq(SABADO, getSabado()));
        }

        if (isSabadoIsNull()) {
            criteria.add(Restrictions.isNull(SABADO));
        }

        if (isSabadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SABADO));
        }

        if (getDomingo() != null) {
            criteria.add(Restrictions.eq(DOMINGO, getDomingo()));
        }

        if (isDomingoIsNull()) {
            criteria.add(Restrictions.isNull(DOMINGO));
        }

        if (isDomingoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DOMINGO));
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
 
