/*
 *
 * archivo: TblProcesosManualesQuery.java
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
import es.minhap.sim.model.TblProcesosManuales;

/**
 * Clase con criterios de busqueda para la entidad TblProcesosManuales
 */
public class TblProcesosManualesQuery extends AbstractHibernateQueryEntity<TblProcesosManuales> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String PROCESOSMANUALESID = "procesosManualesId";
    public static final String PROCESOID = "procesoId";
    public static final String NOMBRE = "nombre";
    public static final String PARAMETRO1 = "parametro1";
    public static final String PARAMETRO2 = "parametro2";
    public static final String PARAMETRO3 = "parametro3";
    public static final String ELIMINADO = "eliminado";
    public static final String FECHACREACION = "fechaCreacion";
    public static final String CREADOPOR = "creadoPor";
    public static final String FECHAMODIFICACION = "fechaModificacion";
    public static final String MODIFICADOPOR = "modificadoPor";


    /**
     * Valor de busqueda de campo procesosManualesId
     */
    private Long procesosManualesId;

    /**
     * Lista de valores del campo procesosManualesId para busquedas tipo IN
     */
    private List<Long> procesosManualesIdIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo procesoId
     */
    private Long procesoId;

    /**
     * Lista de valores del campo procesoId para busquedas tipo IN
     */
    private List<Long> procesoIdIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo procesoId es NULL
     */
    private boolean procesoIdIsNull = false;

    /**
     * Permite buscar cuando campo procesoId es NOT NULL
     */
    private boolean procesoIdIsNotNull = false;

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
     * Valor de busqueda de campo parametro3
     */
    private String parametro3;

    /**
     * Tipo de comparador para la busqueda por campo parametro3
     */
    private TextComparator parametro3Comparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo parametro3 para busquedas tipo IN
     */
    private List<String> parametro3In = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo parametro3 es NULL
     */
    private boolean parametro3IsNull = false;

    /**
     * Permite buscar cuando campo parametro3 es NOT NULL
     */
    private boolean parametro3IsNotNull = false;

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
     * Constructor default
     */
    public TblProcesosManualesQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblProcesosManualesQuery(Long procesosManualesId) {
        setProcesosManualesId(procesosManualesId);
    }

    /**
     * Valor de busqueda de campo procesosManualesId
     * @return Long.
     */
    public Long getProcesosManualesId() {
        return procesosManualesId;
    }

    /**
     * Valor de busqueda de campo procesosManualesId
     * @param procesosManualesId Valor de seteo.
     */
    public void setProcesosManualesId(Long procesosManualesId) {
        this.procesosManualesId = procesosManualesId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getProcesosManualesIdIn() {
        return this.procesosManualesIdIn;
    }

    /**
     * @param procesosManualesId Valor a agregar.
     */
    public void addProcesosManualesIdIn(Long procesosManualesId) {
        this.procesosManualesIdIn.add(procesosManualesId);
    }

    /**
     * Valor de busqueda de campo procesoId
     * @return Long.
     */
    public Long getProcesoId() {
        return procesoId;
    }

    /**
     * Valor de busqueda de campo procesoId
     * @param procesoId Valor de seteo.
     */
    public void setProcesoId(Long procesoId) {
        this.procesoId = procesoId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getProcesoIdIn() {
        return this.procesoIdIn;
    }

    /**
     * @param procesoId Valor a agregar.
     */
    public void addProcesoIdIn(Long procesoId) {
        this.procesoIdIn.add(procesoId);
    }

    /**
     * Permite buscar cuando campo procesoId es NULL
     * @return boolean.
     */
    public boolean isProcesoIdIsNull() {
        return procesoIdIsNull;
    }

    /**
     * Permite buscar cuando campo procesoId es NULL
     * @param procesoIdIsNull Valor de seteo.
     */
    public void setProcesoIdIsNull(boolean procesoIdIsNull) {
        this.procesoIdIsNull = procesoIdIsNull;
    }

    /**
     * Permite buscar cuando campo procesoId es NOT NULL
     * @return boolean.
     */
    public boolean isProcesoIdIsNotNull() {
        return procesoIdIsNotNull;
    }

    /**
     * Permite buscar cuando campo procesoId es NOT NULL
     * @param procesoIdIsNotNull Valor de seteo.
     */
    public void setProcesoIdIsNotNull(boolean procesoIdIsNotNull) {
        this.procesoIdIsNotNull = procesoIdIsNotNull;
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
     * Valor de busqueda de campo parametro3
     * @return String.
     */
    public String getParametro3() {
        if (parametro3 != null) {
            switch (parametro3Comparator) {
	            case STARTS_WITH:
	                return parametro3 + "%";
	            case CONTAINS:
	                return "%" + parametro3 + "%";
	            case ENDS_WITH:
	                return "%" + parametro3;
	            case EQUALS:
                	return parametro3;
              	default:
	            	break;
            }
        }
        return parametro3;
    }

    /**
     * Valor de busqueda de campo parametro3
     * @param parametro3 Valor de seteo.
     */
    public void setParametro3(String parametro3) {
        this.parametro3 = parametro3;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametro3
     * @return parametro3Comparator.
     */
    public TextComparator getParametro3Comparator() {
        return parametro3Comparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametro3
     * @param parametro3Comparator Valor de seteo.
     */
    public void setParametro3Comparator(TextComparator parametro3Comparator) {
        this.parametro3Comparator = parametro3Comparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getParametro3In() {
        return this.parametro3In;
    }

    /**
     * @param parametro3 Valor a agregar.
     */
    public void addParametro3In(String parametro3) {
        this.parametro3In.add(parametro3);
    }

    /**
     * Permite buscar cuando campo parametro3 es NULL
     * @return boolean.
     */
    public boolean isParametro3IsNull() {
        return parametro3IsNull;
    }

    /**
     * Permite buscar cuando campo parametro3 es NULL
     * @param parametro3IsNull Valor de seteo.
     */
    public void setParametro3IsNull(boolean parametro3IsNull) {
        this.parametro3IsNull = parametro3IsNull;
    }

    /**
     * Permite buscar cuando campo parametro3 es NOT NULL
     * @return boolean.
     */
    public boolean isParametro3IsNotNull() {
        return parametro3IsNotNull;
    }

    /**
     * Permite buscar cuando campo parametro3 es NOT NULL
     * @param parametro3IsNotNull Valor de seteo.
     */
    public void setParametro3IsNotNull(boolean parametro3IsNotNull) {
        this.parametro3IsNotNull = parametro3IsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getProcesosManualesId() != null) {
            criteria.add(Restrictions.eq(PROCESOSMANUALESID, getProcesosManualesId()));
        }

        if (getProcesosManualesIdIn().size() > 0) {
            criteria.add(Restrictions.in(PROCESOSMANUALESID, getProcesosManualesIdIn()));
        }

        if (getProcesoId() != null) {
            criteria.add(Restrictions.eq(PROCESOID, getProcesoId()));
        }

        if (getProcesoIdIn().size() > 0) {
            criteria.add(Restrictions.in(PROCESOID, getProcesoIdIn()));
        }

        if (isProcesoIdIsNull()) {
            criteria.add(Restrictions.isNull(PROCESOID));
        }

        if (isProcesoIdIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PROCESOID));
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

        if (getParametro3() != null) {
            if (getParametro3Comparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PARAMETRO3, getParametro3()));
            } 
            else if (getParametro3Comparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PARAMETRO3, getParametro3()));
            }
            else if (getParametro3Comparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PARAMETRO3, getParametro3())));
            }
            else {
                criteria.add(Restrictions.like(PARAMETRO3, getParametro3()));
            }
        }

        if (getParametro3In().size() > 0) {
            criteria.add(Restrictions.in(PARAMETRO3, getParametro3In()));
        }

        if (isParametro3IsNull()) {
            criteria.add(Restrictions.isNull(PARAMETRO3));
        }

        if (isParametro3IsNotNull()) {
            criteria.add(Restrictions.isNotNull(PARAMETRO3));
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
 
