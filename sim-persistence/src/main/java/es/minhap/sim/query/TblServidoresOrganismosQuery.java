/*
 *
 * archivo: TblServidoresOrganismosQuery.java
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
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.model.TblServidoresOrganismos;
import es.minhap.sim.model.TblServidoresOrganismosId;

/**
 * Clase con criterios de busqueda para la entidad TblServidoresOrganismos
 */
public class TblServidoresOrganismosQuery extends AbstractHibernateQueryEntity<TblServidoresOrganismos> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String ID = "id";
    public static final String TBLSERVIDORES = "tblServidores";
    public static final String TBLORGANISMOS = "tblOrganismos";


    /**
     * Valor de busqueda de campo id
     */
    private TblServidoresOrganismosId id;

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
     * Valor de busqueda de campo tblOrganismos
     */
    private TblOrganismosQuery tblOrganismos;

    /**
     * Lista de valores del ID del campo tblOrganismos para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblOrganismos
     */
    private List<TblOrganismos> tblOrganismosIdIn = new ArrayList<TblOrganismos>(0);

    /**
     * Permite buscar cuando campo tblOrganismos es NULL
     */
    private boolean tblOrganismosIsNull = false;

    /**
     * Permite buscar cuando campo tblOrganismos es NOT NULL
     */
    private boolean tblOrganismosIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblServidores
     */
    private boolean innerJoinTblServidores = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblServidores
     */
    private boolean leftJoinTblServidores = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblOrganismos
     */
    private boolean innerJoinTblOrganismos = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblOrganismos
     */
    private boolean leftJoinTblOrganismos = false;

    /**
     * Constructor default
     */
    public TblServidoresOrganismosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblServidoresOrganismosQuery(TblServidoresOrganismosId id) {
        setId(id);
    }

    /**
     * Valor de busqueda de campo id
     * @return TblServidoresOrganismosId.
     */
    public TblServidoresOrganismosId getId() {
        return id;
    }

    /**
     * Valor de busqueda de campo id
     * @param id Valor de seteo.
     */
    public void setId(TblServidoresOrganismosId id) {
        this.id = id;
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
     * Valor de busqueda de campo tblOrganismos
     * @return TblOrganismos.
     */
    public TblOrganismosQuery getTblOrganismos() {
        return tblOrganismos;
    }

    /**
     * Valor de busqueda de campo tblOrganismos
     * @param tblOrganismos Valor de seteo.
     */
    public void setTblOrganismos(TblOrganismosQuery tblOrganismos) {
        this.tblOrganismos = tblOrganismos;
    }

    /**
     * @return List<TblOrganismos>.
     */
    public List<TblOrganismos> getTblOrganismosIdIn() {
        return this.tblOrganismosIdIn;
    }

    /**
     * @param tblOrganismos Valor a agregar.
     */
    public void addTblOrganismosIdIn(TblOrganismos tblOrganismos) {
        this.tblOrganismosIdIn.add(tblOrganismos);
    }

    /**
     * Permite buscar cuando campo tblOrganismos es NULL
     * @return boolean.
     */
    public boolean isTblOrganismosIsNull() {
        return tblOrganismosIsNull;
    }

    /**
     * Permite buscar cuando campo tblOrganismos es NULL
     * @param tblOrganismosIsNull Valor de seteo.
     */
    public void setTblOrganismosIsNull(boolean tblOrganismosIsNull) {
        this.tblOrganismosIsNull = tblOrganismosIsNull;
    }

    /**
     * Permite buscar cuando campo tblOrganismos es NOT NULL
     * @return boolean.
     */
    public boolean isTblOrganismosIsNotNull() {
        return tblOrganismosIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblOrganismos es NOT NULL
     * @param tblOrganismosIsNotNull Valor de seteo.
     */
    public void setTblOrganismosIsNotNull(boolean tblOrganismosIsNotNull) {
        this.tblOrganismosIsNotNull = tblOrganismosIsNotNull;
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
    public boolean isInnerJoinTblOrganismos() {
        return innerJoinTblOrganismos;
    }

    /**
     * @param innerJoinTblOrganismos Valor de seteo.
     */
    public void setInnerJoinTblOrganismos(boolean innerJoinTblOrganismos) {
        this.innerJoinTblOrganismos = innerJoinTblOrganismos;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblOrganismos() {
        return leftJoinTblOrganismos;
    }

    /**
     * @param leftJoinTblOrganismos Valor de seteo.
     */
    public void setLeftJoinTblOrganismos(boolean leftJoinTblOrganismos) {
        this.leftJoinTblOrganismos = leftJoinTblOrganismos;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getId() != null) {
            criteria.add(Restrictions.eq(ID, getId()));
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

        // Campo entidad padre tblOrganismos
        
        // Si se hace join fetch con el padre
        Criteria tblOrganismosCriteria = null;
        if (isInnerJoinTblOrganismos()) {
            tblOrganismosCriteria = criteria.createCriteria(TBLORGANISMOS, "a_" + TBLORGANISMOS, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblOrganismos()) {
            tblOrganismosCriteria = criteria.createCriteria(TBLORGANISMOS, "a_" + TBLORGANISMOS, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblOrganismos() != null) {
            if (getTblOrganismos().getOrganismoid() == null) {
                if (tblOrganismosCriteria == null) {
                    tblOrganismosCriteria = criteria.createCriteria(TBLORGANISMOS, "a_" + TBLORGANISMOS);
                }
                getTblOrganismos().addCriteria(tblOrganismosCriteria, useOrder);
            } else {
                TblOrganismos parent = new TblOrganismos();
                parent.setOrganismoid(getTblOrganismos().getOrganismoid());
                criteria.add(Restrictions.eq(TBLORGANISMOS, parent));
            }
        }

        if (getTblOrganismosIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLORGANISMOS, getTblOrganismosIdIn()));
        }

        if (isTblOrganismosIsNull()) {
            criteria.add(Restrictions.isNull(TBLORGANISMOS));
        }

        if (isTblOrganismosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLORGANISMOS));
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
 
