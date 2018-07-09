/*
 *
 * archivo: ViewServidoresServiciosQuery.java
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

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.sim.model.ViewServidoresServicios;
import es.minhap.sim.model.ViewServidoresServiciosId;

/**
 * Clase con criterios de busqueda para la entidad ViewServidoresServicios
 */
public class ViewServidoresServiciosQuery extends AbstractHibernateQueryEntity<ViewServidoresServicios> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String ID = "id";


    /**
     * Valor de busqueda de campo id
     */
    private ViewServidoresServiciosId id;

    /**
     * Constructor default
     */
    public ViewServidoresServiciosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ViewServidoresServiciosQuery(ViewServidoresServiciosId id) {
        setId(id);
    }

    /**
     * Valor de busqueda de campo id
     * @return ViewServidoresServiciosId.
     */
    public ViewServidoresServiciosId getId() {
        return id;
    }

    /**
     * Valor de busqueda de campo id
     * @param id Valor de seteo.
     */
    public void setId(ViewServidoresServiciosId id) {
        this.id = id;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getId() != null) {
            criteria.add(Restrictions.eq(ID, getId()));
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
 
