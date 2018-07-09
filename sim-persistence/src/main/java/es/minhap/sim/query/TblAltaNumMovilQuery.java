/*
 *
 * archivo: TblAltaNumMovilQuery.java
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

import java.util.Date;

import es.minhap.common.util.DateUtil;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.sim.model.TblAltaNumMovil;

/**
 * Clase con criterios de busqueda para la entidad TblAltaNumMovil
 */
public class TblAltaNumMovilQuery extends AbstractHibernateQueryEntity<TblAltaNumMovil> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDALTANUMMOVIL = "idAltaNumMovil";
    public static final String NUMMOVIL = "numMovil";
    public static final String IDSERVICIOMOVIL = "idServicioMovil";
    public static final String CODCONFIRMACION = "codConfirmacion";
    public static final String FECHACREACION = "fechaCreacion";
    public static final String NUMREINTENTOS = "numReintentos";
    public static final String UIDDISPOSITIVO = "uidDispositivo";


    /**
     * Valor de busqueda de campo idAltaNumMovil
     */
    private Long idAltaNumMovil;

    /**
     * Lista de valores del campo idAltaNumMovil para busquedas tipo IN
     */
    private List<Long> idAltaNumMovilIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo numMovil
     */
    private String numMovil;

    /**
     * Tipo de comparador para la busqueda por campo numMovil
     */
    private TextComparator numMovilComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo numMovil para busquedas tipo IN
     */
    private List<String> numMovilIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo numMovil es NULL
     */
    private boolean numMovilIsNull = false;

    /**
     * Permite buscar cuando campo numMovil es NOT NULL
     */
    private boolean numMovilIsNotNull = false;

    /**
     * Valor de busqueda de campo idServicioMovil
     */
    private Long idServicioMovil;

    /**
     * Lista de valores del campo idServicioMovil para busquedas tipo IN
     */
    private List<Long> idServicioMovilIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo idServicioMovil es NULL
     */
    private boolean idServicioMovilIsNull = false;

    /**
     * Permite buscar cuando campo idServicioMovil es NOT NULL
     */
    private boolean idServicioMovilIsNotNull = false;

    /**
     * Valor de busqueda de campo codConfirmacion
     */
    private String codConfirmacion;

    /**
     * Tipo de comparador para la busqueda por campo codConfirmacion
     */
    private TextComparator codConfirmacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codConfirmacion para busquedas tipo IN
     */
    private List<String> codConfirmacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codConfirmacion es NULL
     */
    private boolean codConfirmacionIsNull = false;

    /**
     * Permite buscar cuando campo codConfirmacion es NOT NULL
     */
    private boolean codConfirmacionIsNotNull = false;

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
     * Valor de busqueda de campo numReintentos
     */
    private Integer numReintentos;

    /**
     * Lista de valores del campo numReintentos para busquedas tipo IN
     */
    private List<Integer> numReintentosIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo numReintentos es NULL
     */
    private boolean numReintentosIsNull = false;

    /**
     * Permite buscar cuando campo numReintentos es NOT NULL
     */
    private boolean numReintentosIsNotNull = false;

    /**
     * Valor de busqueda de campo uidDispositivo
     */
    private String uidDispositivo;

    /**
     * Tipo de comparador para la busqueda por campo uidDispositivo
     */
    private TextComparator uidDispositivoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo uidDispositivo para busquedas tipo IN
     */
    private List<String> uidDispositivoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo uidDispositivo es NULL
     */
    private boolean uidDispositivoIsNull = false;

    /**
     * Permite buscar cuando campo uidDispositivo es NOT NULL
     */
    private boolean uidDispositivoIsNotNull = false;

    /**
     * Constructor default
     */
    public TblAltaNumMovilQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblAltaNumMovilQuery(Long idAltaNumMovil) {
        setIdAltaNumMovil(idAltaNumMovil);
    }

    /**
     * Valor de busqueda de campo idAltaNumMovil
     * @return Long.
     */
    public Long getIdAltaNumMovil() {
        return idAltaNumMovil;
    }

    /**
     * Valor de busqueda de campo idAltaNumMovil
     * @param idAltaNumMovil Valor de seteo.
     */
    public void setIdAltaNumMovil(Long idAltaNumMovil) {
        this.idAltaNumMovil = idAltaNumMovil;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdAltaNumMovilIn() {
        return this.idAltaNumMovilIn;
    }

    /**
     * @param idAltaNumMovil Valor a agregar.
     */
    public void addIdAltaNumMovilIn(Long idAltaNumMovil) {
        this.idAltaNumMovilIn.add(idAltaNumMovil);
    }

    /**
     * Valor de busqueda de campo numMovil
     * @return String.
     */
    public String getNumMovil() {
        if (numMovil != null) {
            switch (numMovilComparator) {
	            case STARTS_WITH:
	                return numMovil + "%";
	            case CONTAINS:
	                return "%" + numMovil + "%";
	            case ENDS_WITH:
	                return "%" + numMovil;
	            case EQUALS:
                	return numMovil;
              	default:
	            	break;
            }
        }
        return numMovil;
    }

    /**
     * Valor de busqueda de campo numMovil
     * @param numMovil Valor de seteo.
     */
    public void setNumMovil(String numMovil) {
        this.numMovil = numMovil;
    }

    /**
     * Tipo de comparador para la busqueda por campo numMovil
     * @return numMovilComparator.
     */
    public TextComparator getNumMovilComparator() {
        return numMovilComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo numMovil
     * @param numMovilComparator Valor de seteo.
     */
    public void setNumMovilComparator(TextComparator numMovilComparator) {
        this.numMovilComparator = numMovilComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNumMovilIn() {
        return this.numMovilIn;
    }

    /**
     * @param numMovil Valor a agregar.
     */
    public void addNumMovilIn(String numMovil) {
        this.numMovilIn.add(numMovil);
    }

    /**
     * Permite buscar cuando campo numMovil es NULL
     * @return boolean.
     */
    public boolean isNumMovilIsNull() {
        return numMovilIsNull;
    }

    /**
     * Permite buscar cuando campo numMovil es NULL
     * @param numMovilIsNull Valor de seteo.
     */
    public void setNumMovilIsNull(boolean numMovilIsNull) {
        this.numMovilIsNull = numMovilIsNull;
    }

    /**
     * Permite buscar cuando campo numMovil es NOT NULL
     * @return boolean.
     */
    public boolean isNumMovilIsNotNull() {
        return numMovilIsNotNull;
    }

    /**
     * Permite buscar cuando campo numMovil es NOT NULL
     * @param numMovilIsNotNull Valor de seteo.
     */
    public void setNumMovilIsNotNull(boolean numMovilIsNotNull) {
        this.numMovilIsNotNull = numMovilIsNotNull;
    }

    /**
     * Valor de busqueda de campo idServicioMovil
     * @return Long.
     */
    public Long getIdServicioMovil() {
        return idServicioMovil;
    }

    /**
     * Valor de busqueda de campo idServicioMovil
     * @param idServicioMovil Valor de seteo.
     */
    public void setIdServicioMovil(Long idServicioMovil) {
        this.idServicioMovil = idServicioMovil;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdServicioMovilIn() {
        return this.idServicioMovilIn;
    }

    /**
     * @param idServicioMovil Valor a agregar.
     */
    public void addIdServicioMovilIn(Long idServicioMovil) {
        this.idServicioMovilIn.add(idServicioMovil);
    }

    /**
     * Permite buscar cuando campo idServicioMovil es NULL
     * @return boolean.
     */
    public boolean isIdServicioMovilIsNull() {
        return idServicioMovilIsNull;
    }

    /**
     * Permite buscar cuando campo idServicioMovil es NULL
     * @param idServicioMovilIsNull Valor de seteo.
     */
    public void setIdServicioMovilIsNull(boolean idServicioMovilIsNull) {
        this.idServicioMovilIsNull = idServicioMovilIsNull;
    }

    /**
     * Permite buscar cuando campo idServicioMovil es NOT NULL
     * @return boolean.
     */
    public boolean isIdServicioMovilIsNotNull() {
        return idServicioMovilIsNotNull;
    }

    /**
     * Permite buscar cuando campo idServicioMovil es NOT NULL
     * @param idServicioMovilIsNotNull Valor de seteo.
     */
    public void setIdServicioMovilIsNotNull(boolean idServicioMovilIsNotNull) {
        this.idServicioMovilIsNotNull = idServicioMovilIsNotNull;
    }

    /**
     * Valor de busqueda de campo codConfirmacion
     * @return String.
     */
    public String getCodConfirmacion() {
        if (codConfirmacion != null) {
            switch (codConfirmacionComparator) {
	            case STARTS_WITH:
	                return codConfirmacion + "%";
	            case CONTAINS:
	                return "%" + codConfirmacion + "%";
	            case ENDS_WITH:
	                return "%" + codConfirmacion;
	            case EQUALS:
                	return codConfirmacion;
              	default:
	            	break;
            }
        }
        return codConfirmacion;
    }

    /**
     * Valor de busqueda de campo codConfirmacion
     * @param codConfirmacion Valor de seteo.
     */
    public void setCodConfirmacion(String codConfirmacion) {
        this.codConfirmacion = codConfirmacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo codConfirmacion
     * @return codConfirmacionComparator.
     */
    public TextComparator getCodConfirmacionComparator() {
        return codConfirmacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codConfirmacion
     * @param codConfirmacionComparator Valor de seteo.
     */
    public void setCodConfirmacionComparator(TextComparator codConfirmacionComparator) {
        this.codConfirmacionComparator = codConfirmacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodConfirmacionIn() {
        return this.codConfirmacionIn;
    }

    /**
     * @param codConfirmacion Valor a agregar.
     */
    public void addCodConfirmacionIn(String codConfirmacion) {
        this.codConfirmacionIn.add(codConfirmacion);
    }

    /**
     * Permite buscar cuando campo codConfirmacion es NULL
     * @return boolean.
     */
    public boolean isCodConfirmacionIsNull() {
        return codConfirmacionIsNull;
    }

    /**
     * Permite buscar cuando campo codConfirmacion es NULL
     * @param codConfirmacionIsNull Valor de seteo.
     */
    public void setCodConfirmacionIsNull(boolean codConfirmacionIsNull) {
        this.codConfirmacionIsNull = codConfirmacionIsNull;
    }

    /**
     * Permite buscar cuando campo codConfirmacion es NOT NULL
     * @return boolean.
     */
    public boolean isCodConfirmacionIsNotNull() {
        return codConfirmacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo codConfirmacion es NOT NULL
     * @param codConfirmacionIsNotNull Valor de seteo.
     */
    public void setCodConfirmacionIsNotNull(boolean codConfirmacionIsNotNull) {
        this.codConfirmacionIsNotNull = codConfirmacionIsNotNull;
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
     * Valor de busqueda de campo numReintentos
     * @return Integer.
     */
    public Integer getNumReintentos() {
        return numReintentos;
    }

    /**
     * Valor de busqueda de campo numReintentos
     * @param numReintentos Valor de seteo.
     */
    public void setNumReintentos(Integer numReintentos) {
        this.numReintentos = numReintentos;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getNumReintentosIn() {
        return this.numReintentosIn;
    }

    /**
     * @param numReintentos Valor a agregar.
     */
    public void addNumReintentosIn(Integer numReintentos) {
        this.numReintentosIn.add(numReintentos);
    }

    /**
     * Permite buscar cuando campo numReintentos es NULL
     * @return boolean.
     */
    public boolean isNumReintentosIsNull() {
        return numReintentosIsNull;
    }

    /**
     * Permite buscar cuando campo numReintentos es NULL
     * @param numReintentosIsNull Valor de seteo.
     */
    public void setNumReintentosIsNull(boolean numReintentosIsNull) {
        this.numReintentosIsNull = numReintentosIsNull;
    }

    /**
     * Permite buscar cuando campo numReintentos es NOT NULL
     * @return boolean.
     */
    public boolean isNumReintentosIsNotNull() {
        return numReintentosIsNotNull;
    }

    /**
     * Permite buscar cuando campo numReintentos es NOT NULL
     * @param numReintentosIsNotNull Valor de seteo.
     */
    public void setNumReintentosIsNotNull(boolean numReintentosIsNotNull) {
        this.numReintentosIsNotNull = numReintentosIsNotNull;
    }

    /**
     * Valor de busqueda de campo uidDispositivo
     * @return String.
     */
    public String getUidDispositivo() {
        if (uidDispositivo != null) {
            switch (uidDispositivoComparator) {
	            case STARTS_WITH:
	                return uidDispositivo + "%";
	            case CONTAINS:
	                return "%" + uidDispositivo + "%";
	            case ENDS_WITH:
	                return "%" + uidDispositivo;
	            case EQUALS:
                	return uidDispositivo;
              	default:
	            	break;
            }
        }
        return uidDispositivo;
    }

    /**
     * Valor de busqueda de campo uidDispositivo
     * @param uidDispositivo Valor de seteo.
     */
    public void setUidDispositivo(String uidDispositivo) {
        this.uidDispositivo = uidDispositivo;
    }

    /**
     * Tipo de comparador para la busqueda por campo uidDispositivo
     * @return uidDispositivoComparator.
     */
    public TextComparator getUidDispositivoComparator() {
        return uidDispositivoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo uidDispositivo
     * @param uidDispositivoComparator Valor de seteo.
     */
    public void setUidDispositivoComparator(TextComparator uidDispositivoComparator) {
        this.uidDispositivoComparator = uidDispositivoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUidDispositivoIn() {
        return this.uidDispositivoIn;
    }

    /**
     * @param uidDispositivo Valor a agregar.
     */
    public void addUidDispositivoIn(String uidDispositivo) {
        this.uidDispositivoIn.add(uidDispositivo);
    }

    /**
     * Permite buscar cuando campo uidDispositivo es NULL
     * @return boolean.
     */
    public boolean isUidDispositivoIsNull() {
        return uidDispositivoIsNull;
    }

    /**
     * Permite buscar cuando campo uidDispositivo es NULL
     * @param uidDispositivoIsNull Valor de seteo.
     */
    public void setUidDispositivoIsNull(boolean uidDispositivoIsNull) {
        this.uidDispositivoIsNull = uidDispositivoIsNull;
    }

    /**
     * Permite buscar cuando campo uidDispositivo es NOT NULL
     * @return boolean.
     */
    public boolean isUidDispositivoIsNotNull() {
        return uidDispositivoIsNotNull;
    }

    /**
     * Permite buscar cuando campo uidDispositivo es NOT NULL
     * @param uidDispositivoIsNotNull Valor de seteo.
     */
    public void setUidDispositivoIsNotNull(boolean uidDispositivoIsNotNull) {
        this.uidDispositivoIsNotNull = uidDispositivoIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdAltaNumMovil() != null) {
            criteria.add(Restrictions.eq(IDALTANUMMOVIL, getIdAltaNumMovil()));
        }

        if (getIdAltaNumMovilIn().size() > 0) {
            criteria.add(Restrictions.in(IDALTANUMMOVIL, getIdAltaNumMovilIn()));
        }

        if (getNumMovil() != null) {
            if (getNumMovilComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NUMMOVIL, getNumMovil()));
            } 
            else if (getNumMovilComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NUMMOVIL, getNumMovil()));
            }
            else if (getNumMovilComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NUMMOVIL, getNumMovil())));
            }
            else {
                criteria.add(Restrictions.like(NUMMOVIL, getNumMovil()));
            }
        }

        if (getNumMovilIn().size() > 0) {
            criteria.add(Restrictions.in(NUMMOVIL, getNumMovilIn()));
        }

        if (isNumMovilIsNull()) {
            criteria.add(Restrictions.isNull(NUMMOVIL));
        }

        if (isNumMovilIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NUMMOVIL));
        }

        if (getIdServicioMovil() != null) {
            criteria.add(Restrictions.eq(IDSERVICIOMOVIL, getIdServicioMovil()));
        }

        if (getIdServicioMovilIn().size() > 0) {
            criteria.add(Restrictions.in(IDSERVICIOMOVIL, getIdServicioMovilIn()));
        }

        if (isIdServicioMovilIsNull()) {
            criteria.add(Restrictions.isNull(IDSERVICIOMOVIL));
        }

        if (isIdServicioMovilIsNotNull()) {
            criteria.add(Restrictions.isNotNull(IDSERVICIOMOVIL));
        }

        if (getCodConfirmacion() != null) {
            if (getCodConfirmacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODCONFIRMACION, getCodConfirmacion()));
            } 
            else if (getCodConfirmacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODCONFIRMACION, getCodConfirmacion()));
            }
            else if (getCodConfirmacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODCONFIRMACION, getCodConfirmacion())));
            }
            else {
                criteria.add(Restrictions.like(CODCONFIRMACION, getCodConfirmacion()));
            }
        }

        if (getCodConfirmacionIn().size() > 0) {
            criteria.add(Restrictions.in(CODCONFIRMACION, getCodConfirmacionIn()));
        }

        if (isCodConfirmacionIsNull()) {
            criteria.add(Restrictions.isNull(CODCONFIRMACION));
        }

        if (isCodConfirmacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODCONFIRMACION));
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

        if (getNumReintentos() != null) {
            criteria.add(Restrictions.eq(NUMREINTENTOS, getNumReintentos()));
        }

        if (getNumReintentosIn().size() > 0) {
            criteria.add(Restrictions.in(NUMREINTENTOS, getNumReintentosIn()));
        }

        if (isNumReintentosIsNull()) {
            criteria.add(Restrictions.isNull(NUMREINTENTOS));
        }

        if (isNumReintentosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NUMREINTENTOS));
        }

        if (getUidDispositivo() != null) {
            if (getUidDispositivoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(UIDDISPOSITIVO, getUidDispositivo()));
            } 
            else if (getUidDispositivoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(UIDDISPOSITIVO, getUidDispositivo()));
            }
            else if (getUidDispositivoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(UIDDISPOSITIVO, getUidDispositivo())));
            }
            else {
                criteria.add(Restrictions.like(UIDDISPOSITIVO, getUidDispositivo()));
            }
        }

        if (getUidDispositivoIn().size() > 0) {
            criteria.add(Restrictions.in(UIDDISPOSITIVO, getUidDispositivoIn()));
        }

        if (isUidDispositivoIsNull()) {
            criteria.add(Restrictions.isNull(UIDDISPOSITIVO));
        }

        if (isUidDispositivoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(UIDDISPOSITIVO));
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
 
