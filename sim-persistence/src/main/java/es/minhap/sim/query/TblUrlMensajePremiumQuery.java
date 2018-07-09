/*
 *
 * archivo: TblUrlMensajePremiumQuery.java
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
import es.minhap.sim.model.TblUrlMensajePremium;

/**
 * Clase con criterios de busqueda para la entidad TblUrlMensajePremium
 */
public class TblUrlMensajePremiumQuery extends AbstractHibernateQueryEntity<TblUrlMensajePremium> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String URLMENSAJEPREMIUMID = "urlMensajePremiumId";
    public static final String MENSAJEID = "mensajeId";
    public static final String URL = "url";
    public static final String REINTENTOS = "reintentos";


    /**
     * Valor de busqueda de campo urlMensajePremiumId
     */
    private Long urlMensajePremiumId;

    /**
     * Lista de valores del campo urlMensajePremiumId para busquedas tipo IN
     */
    private List<Long> urlMensajePremiumIdIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo mensajeId
     */
    private Long mensajeId;

    /**
     * Lista de valores del campo mensajeId para busquedas tipo IN
     */
    private List<Long> mensajeIdIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo mensajeId es NULL
     */
    private boolean mensajeIdIsNull = false;

    /**
     * Permite buscar cuando campo mensajeId es NOT NULL
     */
    private boolean mensajeIdIsNotNull = false;

    /**
     * Valor de busqueda de campo url
     */
    private String url;

    /**
     * Tipo de comparador para la busqueda por campo url
     */
    private TextComparator urlComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo url para busquedas tipo IN
     */
    private List<String> urlIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo url es NULL
     */
    private boolean urlIsNull = false;

    /**
     * Permite buscar cuando campo url es NOT NULL
     */
    private boolean urlIsNotNull = false;

    /**
     * Valor de busqueda de campo reintentos
     */
    private Integer reintentos;

    /**
     * Lista de valores del campo reintentos para busquedas tipo IN
     */
    private List<Integer> reintentosIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo reintentos es NULL
     */
    private boolean reintentosIsNull = false;

    /**
     * Permite buscar cuando campo reintentos es NOT NULL
     */
    private boolean reintentosIsNotNull = false;

    /**
     * Constructor default
     */
    public TblUrlMensajePremiumQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblUrlMensajePremiumQuery(Long urlMensajePremiumId) {
        setUrlMensajePremiumId(urlMensajePremiumId);
    }

    /**
     * Valor de busqueda de campo urlMensajePremiumId
     * @return Long.
     */
    public Long getUrlMensajePremiumId() {
        return urlMensajePremiumId;
    }

    /**
     * Valor de busqueda de campo urlMensajePremiumId
     * @param urlMensajePremiumId Valor de seteo.
     */
    public void setUrlMensajePremiumId(Long urlMensajePremiumId) {
        this.urlMensajePremiumId = urlMensajePremiumId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getUrlMensajePremiumIdIn() {
        return this.urlMensajePremiumIdIn;
    }

    /**
     * @param urlMensajePremiumId Valor a agregar.
     */
    public void addUrlMensajePremiumIdIn(Long urlMensajePremiumId) {
        this.urlMensajePremiumIdIn.add(urlMensajePremiumId);
    }

    /**
     * Valor de busqueda de campo mensajeId
     * @return Long.
     */
    public Long getMensajeId() {
        return mensajeId;
    }

    /**
     * Valor de busqueda de campo mensajeId
     * @param mensajeId Valor de seteo.
     */
    public void setMensajeId(Long mensajeId) {
        this.mensajeId = mensajeId;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getMensajeIdIn() {
        return this.mensajeIdIn;
    }

    /**
     * @param mensajeId Valor a agregar.
     */
    public void addMensajeIdIn(Long mensajeId) {
        this.mensajeIdIn.add(mensajeId);
    }

    /**
     * Permite buscar cuando campo mensajeId es NULL
     * @return boolean.
     */
    public boolean isMensajeIdIsNull() {
        return mensajeIdIsNull;
    }

    /**
     * Permite buscar cuando campo mensajeId es NULL
     * @param mensajeIdIsNull Valor de seteo.
     */
    public void setMensajeIdIsNull(boolean mensajeIdIsNull) {
        this.mensajeIdIsNull = mensajeIdIsNull;
    }

    /**
     * Permite buscar cuando campo mensajeId es NOT NULL
     * @return boolean.
     */
    public boolean isMensajeIdIsNotNull() {
        return mensajeIdIsNotNull;
    }

    /**
     * Permite buscar cuando campo mensajeId es NOT NULL
     * @param mensajeIdIsNotNull Valor de seteo.
     */
    public void setMensajeIdIsNotNull(boolean mensajeIdIsNotNull) {
        this.mensajeIdIsNotNull = mensajeIdIsNotNull;
    }

    /**
     * Valor de busqueda de campo url
     * @return String.
     */
    public String getUrl() {
        if (url != null) {
            switch (urlComparator) {
	            case STARTS_WITH:
	                return url + "%";
	            case CONTAINS:
	                return "%" + url + "%";
	            case ENDS_WITH:
	                return "%" + url;
	            case EQUALS:
                	return url;
              	default:
	            	break;
            }
        }
        return url;
    }

    /**
     * Valor de busqueda de campo url
     * @param url Valor de seteo.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Tipo de comparador para la busqueda por campo url
     * @return urlComparator.
     */
    public TextComparator getUrlComparator() {
        return urlComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo url
     * @param urlComparator Valor de seteo.
     */
    public void setUrlComparator(TextComparator urlComparator) {
        this.urlComparator = urlComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUrlIn() {
        return this.urlIn;
    }

    /**
     * @param url Valor a agregar.
     */
    public void addUrlIn(String url) {
        this.urlIn.add(url);
    }

    /**
     * Permite buscar cuando campo url es NULL
     * @return boolean.
     */
    public boolean isUrlIsNull() {
        return urlIsNull;
    }

    /**
     * Permite buscar cuando campo url es NULL
     * @param urlIsNull Valor de seteo.
     */
    public void setUrlIsNull(boolean urlIsNull) {
        this.urlIsNull = urlIsNull;
    }

    /**
     * Permite buscar cuando campo url es NOT NULL
     * @return boolean.
     */
    public boolean isUrlIsNotNull() {
        return urlIsNotNull;
    }

    /**
     * Permite buscar cuando campo url es NOT NULL
     * @param urlIsNotNull Valor de seteo.
     */
    public void setUrlIsNotNull(boolean urlIsNotNull) {
        this.urlIsNotNull = urlIsNotNull;
    }

    /**
     * Valor de busqueda de campo reintentos
     * @return Integer.
     */
    public Integer getReintentos() {
        return reintentos;
    }

    /**
     * Valor de busqueda de campo reintentos
     * @param reintentos Valor de seteo.
     */
    public void setReintentos(Integer reintentos) {
        this.reintentos = reintentos;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getReintentosIn() {
        return this.reintentosIn;
    }

    /**
     * @param reintentos Valor a agregar.
     */
    public void addReintentosIn(Integer reintentos) {
        this.reintentosIn.add(reintentos);
    }

    /**
     * Permite buscar cuando campo reintentos es NULL
     * @return boolean.
     */
    public boolean isReintentosIsNull() {
        return reintentosIsNull;
    }

    /**
     * Permite buscar cuando campo reintentos es NULL
     * @param reintentosIsNull Valor de seteo.
     */
    public void setReintentosIsNull(boolean reintentosIsNull) {
        this.reintentosIsNull = reintentosIsNull;
    }

    /**
     * Permite buscar cuando campo reintentos es NOT NULL
     * @return boolean.
     */
    public boolean isReintentosIsNotNull() {
        return reintentosIsNotNull;
    }

    /**
     * Permite buscar cuando campo reintentos es NOT NULL
     * @param reintentosIsNotNull Valor de seteo.
     */
    public void setReintentosIsNotNull(boolean reintentosIsNotNull) {
        this.reintentosIsNotNull = reintentosIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getUrlMensajePremiumId() != null) {
            criteria.add(Restrictions.eq(URLMENSAJEPREMIUMID, getUrlMensajePremiumId()));
        }

        if (getUrlMensajePremiumIdIn().size() > 0) {
            criteria.add(Restrictions.in(URLMENSAJEPREMIUMID, getUrlMensajePremiumIdIn()));
        }

        if (getMensajeId() != null) {
            criteria.add(Restrictions.eq(MENSAJEID, getMensajeId()));
        }

        if (getMensajeIdIn().size() > 0) {
            criteria.add(Restrictions.in(MENSAJEID, getMensajeIdIn()));
        }

        if (isMensajeIdIsNull()) {
            criteria.add(Restrictions.isNull(MENSAJEID));
        }

        if (isMensajeIdIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MENSAJEID));
        }

        if (getUrl() != null) {
            if (getUrlComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(URL, getUrl()));
            } 
            else if (getUrlComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(URL, getUrl()));
            }
            else if (getUrlComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(URL, getUrl())));
            }
            else {
                criteria.add(Restrictions.like(URL, getUrl()));
            }
        }

        if (getUrlIn().size() > 0) {
            criteria.add(Restrictions.in(URL, getUrlIn()));
        }

        if (isUrlIsNull()) {
            criteria.add(Restrictions.isNull(URL));
        }

        if (isUrlIsNotNull()) {
            criteria.add(Restrictions.isNotNull(URL));
        }

        if (getReintentos() != null) {
            criteria.add(Restrictions.eq(REINTENTOS, getReintentos()));
        }

        if (getReintentosIn().size() > 0) {
            criteria.add(Restrictions.in(REINTENTOS, getReintentosIn()));
        }

        if (isReintentosIsNull()) {
            criteria.add(Restrictions.isNull(REINTENTOS));
        }

        if (isReintentosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(REINTENTOS));
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
 
