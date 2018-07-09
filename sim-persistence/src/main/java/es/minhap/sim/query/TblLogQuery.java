/*
 *
 * archivo: TblLogQuery.java
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
import es.minhap.sim.model.TblLog;

/**
 * Clase con criterios de busqueda para la entidad TblLog
 */
public class TblLogQuery extends AbstractHibernateQueryEntity<TblLog> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String LOGID = "logid";
    public static final String SOURCENAME = "sourcename";
    public static final String SOURCEID = "sourceid";
    public static final String LOGACCION = "logaccion";
    public static final String LOGDESCRIPCION = "logdescripcion";
    public static final String ADTUSUARIO = "adtusuario";
    public static final String ADTFECHA = "adtfecha";
    public static final String SOURCEDESCRIPTION = "sourcedescription";


    /**
     * Valor de busqueda de campo logid
     */
    private Long logid;

    /**
     * Lista de valores del campo logid para busquedas tipo IN
     */
    private List<Long> logidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo sourcename
     */
    private String sourcename;

    /**
     * Tipo de comparador para la busqueda por campo sourcename
     */
    private TextComparator sourcenameComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo sourcename para busquedas tipo IN
     */
    private List<String> sourcenameIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo sourcename es NULL
     */
    private boolean sourcenameIsNull = false;

    /**
     * Permite buscar cuando campo sourcename es NOT NULL
     */
    private boolean sourcenameIsNotNull = false;

    /**
     * Valor de busqueda de campo sourceid
     */
    private Long sourceid;

    /**
     * Lista de valores del campo sourceid para busquedas tipo IN
     */
    private List<Long> sourceidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo sourceid es NULL
     */
    private boolean sourceidIsNull = false;

    /**
     * Permite buscar cuando campo sourceid es NOT NULL
     */
    private boolean sourceidIsNotNull = false;

    /**
     * Valor de busqueda de campo logaccion
     */
    private Long logaccion;

    /**
     * Lista de valores del campo logaccion para busquedas tipo IN
     */
    private List<Long> logaccionIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo logaccion es NULL
     */
    private boolean logaccionIsNull = false;

    /**
     * Permite buscar cuando campo logaccion es NOT NULL
     */
    private boolean logaccionIsNotNull = false;

    /**
     * Valor de busqueda de campo logdescripcion
     */
    private String logdescripcion;

    /**
     * Tipo de comparador para la busqueda por campo logdescripcion
     */
    private TextComparator logdescripcionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo logdescripcion para busquedas tipo IN
     */
    private List<String> logdescripcionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo logdescripcion es NULL
     */
    private boolean logdescripcionIsNull = false;

    /**
     * Permite buscar cuando campo logdescripcion es NOT NULL
     */
    private boolean logdescripcionIsNotNull = false;

    /**
     * Valor de busqueda de campo adtusuario
     */
    private String adtusuario;

    /**
     * Tipo de comparador para la busqueda por campo adtusuario
     */
    private TextComparator adtusuarioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo adtusuario para busquedas tipo IN
     */
    private List<String> adtusuarioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo adtusuario es NULL
     */
    private boolean adtusuarioIsNull = false;

    /**
     * Permite buscar cuando campo adtusuario es NOT NULL
     */
    private boolean adtusuarioIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha adtfecha
     */
    private Date adtfechaMin;

    /**
     * Valor superior de rango de busqueda de fecha adtfecha
     */
    private Date adtfechaMax;

    /**
     * Permite buscar cuando campo adtfecha es NULL
     */
    private boolean adtfechaIsNull = false;

    /**
     * Permite buscar cuando campo adtfecha es NOT NULL
     */
    private boolean adtfechaIsNotNull = false;

    /**
     * Valor de busqueda de campo sourcedescription
     */
    private String sourcedescription;

    /**
     * Tipo de comparador para la busqueda por campo sourcedescription
     */
    private TextComparator sourcedescriptionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo sourcedescription para busquedas tipo IN
     */
    private List<String> sourcedescriptionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo sourcedescription es NULL
     */
    private boolean sourcedescriptionIsNull = false;

    /**
     * Permite buscar cuando campo sourcedescription es NOT NULL
     */
    private boolean sourcedescriptionIsNotNull = false;

    /**
   	 * Establece el máximo de resultados
   	 */
   	private Integer maxResultados;
   	
   	/**
   	 * Establece el primer resultados
   	 */
   	private Integer primerResultado;
    
    /**
     * Constructor default
     */
    public TblLogQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblLogQuery(Long logid) {
        setLogid(logid);
    }

    /**
     * Valor de busqueda de campo logid
     * @return Long.
     */
    public Long getLogid() {
        return logid;
    }

    /**
     * Valor de busqueda de campo logid
     * @param logid Valor de seteo.
     */
    public void setLogid(Long logid) {
        this.logid = logid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getLogidIn() {
        return this.logidIn;
    }

    /**
     * @param logid Valor a agregar.
     */
    public void addLogidIn(Long logid) {
        this.logidIn.add(logid);
    }

    /**
     * Valor de busqueda de campo sourcename
     * @return String.
     */
    public String getSourcename() {
        if (sourcename != null) {
            switch (sourcenameComparator) {
	            case STARTS_WITH:
	                return sourcename + "%";
	            case CONTAINS:
	                return "%" + sourcename + "%";
	            case ENDS_WITH:
	                return "%" + sourcename;
	            case EQUALS:
                	return sourcename;
              	default:
	            	break;
            }
        }
        return sourcename;
    }

    /**
     * Valor de busqueda de campo sourcename
     * @param sourcename Valor de seteo.
     */
    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    /**
     * Tipo de comparador para la busqueda por campo sourcename
     * @return sourcenameComparator.
     */
    public TextComparator getSourcenameComparator() {
        return sourcenameComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo sourcename
     * @param sourcenameComparator Valor de seteo.
     */
    public void setSourcenameComparator(TextComparator sourcenameComparator) {
        this.sourcenameComparator = sourcenameComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getSourcenameIn() {
        return this.sourcenameIn;
    }

    /**
     * @param sourcename Valor a agregar.
     */
    public void addSourcenameIn(String sourcename) {
        this.sourcenameIn.add(sourcename);
    }

    /**
     * Permite buscar cuando campo sourcename es NULL
     * @return boolean.
     */
    public boolean isSourcenameIsNull() {
        return sourcenameIsNull;
    }

    /**
     * Permite buscar cuando campo sourcename es NULL
     * @param sourcenameIsNull Valor de seteo.
     */
    public void setSourcenameIsNull(boolean sourcenameIsNull) {
        this.sourcenameIsNull = sourcenameIsNull;
    }

    /**
     * Permite buscar cuando campo sourcename es NOT NULL
     * @return boolean.
     */
    public boolean isSourcenameIsNotNull() {
        return sourcenameIsNotNull;
    }

    /**
     * Permite buscar cuando campo sourcename es NOT NULL
     * @param sourcenameIsNotNull Valor de seteo.
     */
    public void setSourcenameIsNotNull(boolean sourcenameIsNotNull) {
        this.sourcenameIsNotNull = sourcenameIsNotNull;
    }

    /**
     * Valor de busqueda de campo sourceid
     * @return Long.
     */
    public Long getSourceid() {
        return sourceid;
    }

    /**
     * Valor de busqueda de campo sourceid
     * @param sourceid Valor de seteo.
     */
    public void setSourceid(Long sourceid) {
        this.sourceid = sourceid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getSourceidIn() {
        return this.sourceidIn;
    }

    /**
     * @param sourceid Valor a agregar.
     */
    public void addSourceidIn(Long sourceid) {
        this.sourceidIn.add(sourceid);
    }

    /**
     * Permite buscar cuando campo sourceid es NULL
     * @return boolean.
     */
    public boolean isSourceidIsNull() {
        return sourceidIsNull;
    }

    /**
     * Permite buscar cuando campo sourceid es NULL
     * @param sourceidIsNull Valor de seteo.
     */
    public void setSourceidIsNull(boolean sourceidIsNull) {
        this.sourceidIsNull = sourceidIsNull;
    }

    /**
     * Permite buscar cuando campo sourceid es NOT NULL
     * @return boolean.
     */
    public boolean isSourceidIsNotNull() {
        return sourceidIsNotNull;
    }

    /**
     * Permite buscar cuando campo sourceid es NOT NULL
     * @param sourceidIsNotNull Valor de seteo.
     */
    public void setSourceidIsNotNull(boolean sourceidIsNotNull) {
        this.sourceidIsNotNull = sourceidIsNotNull;
    }

    /**
     * Valor de busqueda de campo logaccion
     * @return Long.
     */
    public Long getLogaccion() {
        return logaccion;
    }

    /**
     * Valor de busqueda de campo logaccion
     * @param logaccion Valor de seteo.
     */
    public void setLogaccion(Long logaccion) {
        this.logaccion = logaccion;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getLogaccionIn() {
        return this.logaccionIn;
    }

    /**
     * @param logaccion Valor a agregar.
     */
    public void addLogaccionIn(Long logaccion) {
        this.logaccionIn.add(logaccion);
    }

    /**
     * Permite buscar cuando campo logaccion es NULL
     * @return boolean.
     */
    public boolean isLogaccionIsNull() {
        return logaccionIsNull;
    }

    /**
     * Permite buscar cuando campo logaccion es NULL
     * @param logaccionIsNull Valor de seteo.
     */
    public void setLogaccionIsNull(boolean logaccionIsNull) {
        this.logaccionIsNull = logaccionIsNull;
    }

    /**
     * Permite buscar cuando campo logaccion es NOT NULL
     * @return boolean.
     */
    public boolean isLogaccionIsNotNull() {
        return logaccionIsNotNull;
    }

    /**
     * Permite buscar cuando campo logaccion es NOT NULL
     * @param logaccionIsNotNull Valor de seteo.
     */
    public void setLogaccionIsNotNull(boolean logaccionIsNotNull) {
        this.logaccionIsNotNull = logaccionIsNotNull;
    }

    /**
     * Valor de busqueda de campo logdescripcion
     * @return String.
     */
    public String getLogdescripcion() {
        if (logdescripcion != null) {
            switch (logdescripcionComparator) {
	            case STARTS_WITH:
	                return logdescripcion + "%";
	            case CONTAINS:
	                return "%" + logdescripcion + "%";
	            case ENDS_WITH:
	                return "%" + logdescripcion;
	            case EQUALS:
                	return logdescripcion;
              	default:
	            	break;
            }
        }
        return logdescripcion;
    }

    /**
     * Valor de busqueda de campo logdescripcion
     * @param logdescripcion Valor de seteo.
     */
    public void setLogdescripcion(String logdescripcion) {
        this.logdescripcion = logdescripcion;
    }

    /**
     * Tipo de comparador para la busqueda por campo logdescripcion
     * @return logdescripcionComparator.
     */
    public TextComparator getLogdescripcionComparator() {
        return logdescripcionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo logdescripcion
     * @param logdescripcionComparator Valor de seteo.
     */
    public void setLogdescripcionComparator(TextComparator logdescripcionComparator) {
        this.logdescripcionComparator = logdescripcionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getLogdescripcionIn() {
        return this.logdescripcionIn;
    }

    /**
     * @param logdescripcion Valor a agregar.
     */
    public void addLogdescripcionIn(String logdescripcion) {
        this.logdescripcionIn.add(logdescripcion);
    }

    /**
     * Permite buscar cuando campo logdescripcion es NULL
     * @return boolean.
     */
    public boolean isLogdescripcionIsNull() {
        return logdescripcionIsNull;
    }

    /**
     * Permite buscar cuando campo logdescripcion es NULL
     * @param logdescripcionIsNull Valor de seteo.
     */
    public void setLogdescripcionIsNull(boolean logdescripcionIsNull) {
        this.logdescripcionIsNull = logdescripcionIsNull;
    }

    /**
     * Permite buscar cuando campo logdescripcion es NOT NULL
     * @return boolean.
     */
    public boolean isLogdescripcionIsNotNull() {
        return logdescripcionIsNotNull;
    }

    /**
     * Permite buscar cuando campo logdescripcion es NOT NULL
     * @param logdescripcionIsNotNull Valor de seteo.
     */
    public void setLogdescripcionIsNotNull(boolean logdescripcionIsNotNull) {
        this.logdescripcionIsNotNull = logdescripcionIsNotNull;
    }

    /**
     * Valor de busqueda de campo adtusuario
     * @return String.
     */
    public String getAdtusuario() {
        if (adtusuario != null) {
            switch (adtusuarioComparator) {
	            case STARTS_WITH:
	                return adtusuario + "%";
	            case CONTAINS:
	                return "%" + adtusuario + "%";
	            case ENDS_WITH:
	                return "%" + adtusuario;
	            case EQUALS:
                	return adtusuario;
              	default:
	            	break;
            }
        }
        return adtusuario;
    }

    /**
     * Valor de busqueda de campo adtusuario
     * @param adtusuario Valor de seteo.
     */
    public void setAdtusuario(String adtusuario) {
        this.adtusuario = adtusuario;
    }

    /**
     * Tipo de comparador para la busqueda por campo adtusuario
     * @return adtusuarioComparator.
     */
    public TextComparator getAdtusuarioComparator() {
        return adtusuarioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo adtusuario
     * @param adtusuarioComparator Valor de seteo.
     */
    public void setAdtusuarioComparator(TextComparator adtusuarioComparator) {
        this.adtusuarioComparator = adtusuarioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getAdtusuarioIn() {
        return this.adtusuarioIn;
    }

    /**
     * @param adtusuario Valor a agregar.
     */
    public void addAdtusuarioIn(String adtusuario) {
        this.adtusuarioIn.add(adtusuario);
    }

    /**
     * Permite buscar cuando campo adtusuario es NULL
     * @return boolean.
     */
    public boolean isAdtusuarioIsNull() {
        return adtusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo adtusuario es NULL
     * @param adtusuarioIsNull Valor de seteo.
     */
    public void setAdtusuarioIsNull(boolean adtusuarioIsNull) {
        this.adtusuarioIsNull = adtusuarioIsNull;
    }

    /**
     * Permite buscar cuando campo adtusuario es NOT NULL
     * @return boolean.
     */
    public boolean isAdtusuarioIsNotNull() {
        return adtusuarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo adtusuario es NOT NULL
     * @param adtusuarioIsNotNull Valor de seteo.
     */
    public void setAdtusuarioIsNotNull(boolean adtusuarioIsNotNull) {
        this.adtusuarioIsNotNull = adtusuarioIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha adtfecha
     * @return ${field.getName()}Min.
     */
    public Date getAdtfechaMin() {
        return adtfechaMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha adtfecha
     * @param adtfechaMin Valor de seteo.
     */
    public void setAdtfechaMin(Date adtfechaMin) {
        this.adtfechaMin = adtfechaMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha adtfecha
     * @return adtfechaMax.
     */
    public Date getAdtfechaMax() {
       return adtfechaMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha adtfecha
     * @param adtfechaMax Valor de seteo.
     */
    public void setAdtfechaMax(Date adtfechaMax) {
        this.adtfechaMax = adtfechaMax;
    }

    /**
     * Permite buscar cuando campo adtfecha es NULL
     * @return boolean.
     */
    public boolean isAdtfechaIsNull() {
        return adtfechaIsNull;
    }

    /**
     * Permite buscar cuando campo adtfecha es NULL
     * @param adtfechaIsNull Valor de seteo.
     */
    public void setAdtfechaIsNull(boolean adtfechaIsNull) {
        this.adtfechaIsNull = adtfechaIsNull;
    }

    /**
     * Permite buscar cuando campo adtfecha es NOT NULL
     * @return boolean.
     */
    public boolean isAdtfechaIsNotNull() {
        return adtfechaIsNotNull;
    }

    /**
     * Permite buscar cuando campo adtfecha es NOT NULL
     * @param adtfechaIsNotNull Valor de seteo.
     */
    public void setAdtfechaIsNotNull(boolean adtfechaIsNotNull) {
        this.adtfechaIsNotNull = adtfechaIsNotNull;
    }

    /**
     * Valor de busqueda de campo sourcedescription
     * @return String.
     */
    public String getSourcedescription() {
        if (sourcedescription != null) {
            switch (sourcedescriptionComparator) {
	            case STARTS_WITH:
	                return sourcedescription + "%";
	            case CONTAINS:
	                return "%" + sourcedescription + "%";
	            case ENDS_WITH:
	                return "%" + sourcedescription;
	            case EQUALS:
                	return sourcedescription;
              	default:
	            	break;
            }
        }
        return sourcedescription;
    }

    /**
     * Valor de busqueda de campo sourcedescription
     * @param sourcedescription Valor de seteo.
     */
    public void setSourcedescription(String sourcedescription) {
        this.sourcedescription = sourcedescription;
    }

    /**
     * Tipo de comparador para la busqueda por campo sourcedescription
     * @return sourcedescriptionComparator.
     */
    public TextComparator getSourcedescriptionComparator() {
        return sourcedescriptionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo sourcedescription
     * @param sourcedescriptionComparator Valor de seteo.
     */
    public void setSourcedescriptionComparator(TextComparator sourcedescriptionComparator) {
        this.sourcedescriptionComparator = sourcedescriptionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getSourcedescriptionIn() {
        return this.sourcedescriptionIn;
    }

    /**
     * @param sourcedescription Valor a agregar.
     */
    public void addSourcedescriptionIn(String sourcedescription) {
        this.sourcedescriptionIn.add(sourcedescription);
    }

    /**
     * Permite buscar cuando campo sourcedescription es NULL
     * @return boolean.
     */
    public boolean isSourcedescriptionIsNull() {
        return sourcedescriptionIsNull;
    }

    /**
     * Permite buscar cuando campo sourcedescription es NULL
     * @param sourcedescriptionIsNull Valor de seteo.
     */
    public void setSourcedescriptionIsNull(boolean sourcedescriptionIsNull) {
        this.sourcedescriptionIsNull = sourcedescriptionIsNull;
    }

    /**
     * Permite buscar cuando campo sourcedescription es NOT NULL
     * @return boolean.
     */
    public boolean isSourcedescriptionIsNotNull() {
        return sourcedescriptionIsNotNull;
    }

    /**
     * Permite buscar cuando campo sourcedescription es NOT NULL
     * @param sourcedescriptionIsNotNull Valor de seteo.
     */
    public void setSourcedescriptionIsNotNull(boolean sourcedescriptionIsNotNull) {
        this.sourcedescriptionIsNotNull = sourcedescriptionIsNotNull;
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
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getLogid() != null) {
            criteria.add(Restrictions.eq(LOGID, getLogid()));
        }

        if (getLogidIn().size() > 0) {
            criteria.add(Restrictions.in(LOGID, getLogidIn()));
        }

        if (getSourcename() != null) {
            if (getSourcenameComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SOURCENAME, getSourcename()));
            } 
            else if (getSourcenameComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SOURCENAME, getSourcename()));
            }
            else if (getSourcenameComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SOURCENAME, getSourcename())));
            }
            else {
                criteria.add(Restrictions.like(SOURCENAME, getSourcename()));
            }
        }

        if (getSourcenameIn().size() > 0) {
            criteria.add(Restrictions.in(SOURCENAME, getSourcenameIn()));
        }

        if (isSourcenameIsNull()) {
            criteria.add(Restrictions.isNull(SOURCENAME));
        }

        if (isSourcenameIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SOURCENAME));
        }

        if (getSourceid() != null) {
            criteria.add(Restrictions.eq(SOURCEID, getSourceid()));
        }

        if (getSourceidIn().size() > 0) {
            criteria.add(Restrictions.in(SOURCEID, getSourceidIn()));
        }

        if (isSourceidIsNull()) {
            criteria.add(Restrictions.isNull(SOURCEID));
        }

        if (isSourceidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SOURCEID));
        }

        if (getLogaccion() != null) {
            criteria.add(Restrictions.eq(LOGACCION, getLogaccion()));
        }

        if (getLogaccionIn().size() > 0) {
            criteria.add(Restrictions.in(LOGACCION, getLogaccionIn()));
        }

        if (isLogaccionIsNull()) {
            criteria.add(Restrictions.isNull(LOGACCION));
        }

        if (isLogaccionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(LOGACCION));
        }

        if (getLogdescripcion() != null) {
            if (getLogdescripcionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(LOGDESCRIPCION, getLogdescripcion()));
            } 
            else if (getLogdescripcionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(LOGDESCRIPCION, getLogdescripcion()));
            }
            else if (getLogdescripcionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(LOGDESCRIPCION, getLogdescripcion())));
            }
            else {
                criteria.add(Restrictions.like(LOGDESCRIPCION, getLogdescripcion()));
            }
        }

        if (getLogdescripcionIn().size() > 0) {
            criteria.add(Restrictions.in(LOGDESCRIPCION, getLogdescripcionIn()));
        }

        if (isLogdescripcionIsNull()) {
            criteria.add(Restrictions.isNull(LOGDESCRIPCION));
        }

        if (isLogdescripcionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(LOGDESCRIPCION));
        }

        if (getAdtusuario() != null) {
            if (getAdtusuarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ADTUSUARIO, getAdtusuario()));
            } 
            else if (getAdtusuarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ADTUSUARIO, getAdtusuario()));
            }
            else if (getAdtusuarioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ADTUSUARIO, getAdtusuario())));
            }
            else {
                criteria.add(Restrictions.like(ADTUSUARIO, getAdtusuario()));
            }
        }

        if (getAdtusuarioIn().size() > 0) {
            criteria.add(Restrictions.in(ADTUSUARIO, getAdtusuarioIn()));
        }

        if (isAdtusuarioIsNull()) {
            criteria.add(Restrictions.isNull(ADTUSUARIO));
        }

        if (isAdtusuarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ADTUSUARIO));
        }

        if (getAdtfechaMin() != null) {
            criteria.add(Restrictions.ge(ADTFECHA, getAdtfechaMin()));
        }

        if (getAdtfechaMax() != null) {
            criteria.add(Restrictions.le(ADTFECHA, getAdtfechaMax()));
        }

        if (isAdtfechaIsNull()) {
            criteria.add(Restrictions.isNull(ADTFECHA));
        }

        if (isAdtfechaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ADTFECHA));
        }

        if (getSourcedescription() != null) {
            if (getSourcedescriptionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SOURCEDESCRIPTION, getSourcedescription()));
            } 
            else if (getSourcedescriptionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SOURCEDESCRIPTION, getSourcedescription()));
            }
            else if (getSourcedescriptionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SOURCEDESCRIPTION, getSourcedescription())));
            }
            else {
                criteria.add(Restrictions.like(SOURCEDESCRIPTION, getSourcedescription()));
            }
        }

        if (getSourcedescriptionIn().size() > 0) {
            criteria.add(Restrictions.in(SOURCEDESCRIPTION, getSourcedescriptionIn()));
        }

        if (isSourcedescriptionIsNull()) {
            criteria.add(Restrictions.isNull(SOURCEDESCRIPTION));
        }

        if (isSourcedescriptionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SOURCEDESCRIPTION));
        }
        
        if(null != maxResultados && maxResultados > 0){
			criteria.setMaxResults(maxResultados);
		}
		
		if (null != primerResultado && primerResultado > 0){
			criteria.setFirstResult(primerResultado);
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
 
