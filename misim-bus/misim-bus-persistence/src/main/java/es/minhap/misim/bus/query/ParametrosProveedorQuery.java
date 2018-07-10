/*
 *
 * archivo: ParametrosProveedorQuery.java
 *
 * Proyecto: Administracion SIM
 *
 * Generated file! Do not modify.
 *
 * Developed by:
 *     everis S.A.
 *     www.everis.com
 */

package es.minhap.misim.bus.query;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.misim.bus.model.ParametrosProveedor;

/**
 * Clase con criterios de busqueda para la entidad ParametrosProveedor
 */
public class ParametrosProveedorQuery extends AbstractHibernateQueryEntity<ParametrosProveedor> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String IDPARAMETROSPROVEEDOR = "idParametrosProveedor";
    public static final String IDPROVEEDOR = "idProveedor";
    public static final String PARAMETRO = "parametro";
    public static final String VALOR = "valor";
    public static final String VARIABLE = "variable";


    /**
     * Valor de busqueda de campo idParametrosProveedor
     */
    private Long idParametrosProveedor;

    /**
     * Lista de valores del campo idParametrosProveedor para busquedas tipo IN
     */
    private List<Long> idParametrosProveedorIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo idProveedor
     */
    private Long idProveedor;

    /**
     * Lista de valores del campo idProveedor para busquedas tipo IN
     */
    private List<Long> idProveedorIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo idProveedor es NULL
     */
    private boolean idProveedorIsNull = false;

    /**
     * Permite buscar cuando campo idProveedor es NOT NULL
     */
    private boolean idProveedorIsNotNull = false;

    /**
     * Valor de busqueda de campo parametro
     */
    private String parametro;

    /**
     * Tipo de comparador para la busqueda por campo parametro
     */
    private TextComparator parametroComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo parametro para busquedas tipo IN
     */
    private List<String> parametroIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo parametro es NULL
     */
    private boolean parametroIsNull = false;

    /**
     * Permite buscar cuando campo parametro es NOT NULL
     */
    private boolean parametroIsNotNull = false;

    /**
     * Valor de busqueda de campo valor
     */
    private String valor;

    /**
     * Tipo de comparador para la busqueda por campo valor
     */
    private TextComparator valorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo valor para busquedas tipo IN
     */
    private List<String> valorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo valor es NULL
     */
    private boolean valorIsNull = false;

    /**
     * Permite buscar cuando campo valor es NOT NULL
     */
    private boolean valorIsNotNull = false;

    /**
     * Valor de busqueda de campo variable
     */
    private String variable;

    /**
     * Tipo de comparador para la busqueda por campo variable
     */
    private TextComparator variableComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo variable para busquedas tipo IN
     */
    private List<String> variableIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo variable es NULL
     */
    private boolean variableIsNull = false;

    /**
     * Permite buscar cuando campo variable es NOT NULL
     */
    private boolean variableIsNotNull = false;

    /**
     * Constructor default
     */
    public ParametrosProveedorQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ParametrosProveedorQuery(Long idParametrosProveedor) {
        setIdParametrosProveedor(idParametrosProveedor);
    }

    /**
     * Valor de busqueda de campo idParametrosProveedor
     * @return Long.
     */
    public Long getIdParametrosProveedor() {
        return idParametrosProveedor;
    }

    /**
     * Valor de busqueda de campo idParametrosProveedor
     * @param idParametrosProveedor Valor de seteo.
     */
    public void setIdParametrosProveedor(Long idParametrosProveedor) {
        this.idParametrosProveedor = idParametrosProveedor;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdParametrosProveedorIn() {
        return this.idParametrosProveedorIn;
    }

    /**
     * @param idParametrosProveedor Valor a agregar.
     */
    public void addIdParametrosProveedorIn(Long idParametrosProveedor) {
        this.idParametrosProveedorIn.add(idParametrosProveedor);
    }

    /**
     * Valor de busqueda de campo idProveedor
     * @return Long.
     */
    public Long getIdProveedor() {
        return idProveedor;
    }

    /**
     * Valor de busqueda de campo idProveedor
     * @param idProveedor Valor de seteo.
     */
    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdProveedorIn() {
        return this.idProveedorIn;
    }

    /**
     * @param idProveedor Valor a agregar.
     */
    public void addIdProveedorIn(Long idProveedor) {
        this.idProveedorIn.add(idProveedor);
    }

    /**
     * Permite buscar cuando campo idProveedor es NULL
     * @return boolean.
     */
    public boolean isIdProveedorIsNull() {
        return idProveedorIsNull;
    }

    /**
     * Permite buscar cuando campo idProveedor es NULL
     * @param idProveedorIsNull Valor de seteo.
     */
    public void setIdProveedorIsNull(boolean idProveedorIsNull) {
        this.idProveedorIsNull = idProveedorIsNull;
    }

    /**
     * Permite buscar cuando campo idProveedor es NOT NULL
     * @return boolean.
     */
    public boolean isIdProveedorIsNotNull() {
        return idProveedorIsNotNull;
    }

    /**
     * Permite buscar cuando campo idProveedor es NOT NULL
     * @param idProveedorIsNotNull Valor de seteo.
     */
    public void setIdProveedorIsNotNull(boolean idProveedorIsNotNull) {
        this.idProveedorIsNotNull = idProveedorIsNotNull;
    }

    /**
     * Valor de busqueda de campo parametro
     * @return String.
     */
    public String getParametro() {
        if (parametro != null) {
            switch (parametroComparator) {
	            case STARTS_WITH:
	                return parametro + "%";
	            case CONTAINS:
	                return "%" + parametro + "%";
	            case ENDS_WITH:
	                return "%" + parametro;
	            case EQUALS:
                	return parametro;
              	default:
	            	break;
            }
        }
        return parametro;
    }

    /**
     * Valor de busqueda de campo parametro
     * @param parametro Valor de seteo.
     */
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametro
     * @return parametroComparator.
     */
    public TextComparator getParametroComparator() {
        return parametroComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo parametro
     * @param parametroComparator Valor de seteo.
     */
    public void setParametroComparator(TextComparator parametroComparator) {
        this.parametroComparator = parametroComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getParametroIn() {
        return this.parametroIn;
    }

    /**
     * @param parametro Valor a agregar.
     */
    public void addParametroIn(String parametro) {
        this.parametroIn.add(parametro);
    }

    /**
     * Permite buscar cuando campo parametro es NULL
     * @return boolean.
     */
    public boolean isParametroIsNull() {
        return parametroIsNull;
    }

    /**
     * Permite buscar cuando campo parametro es NULL
     * @param parametroIsNull Valor de seteo.
     */
    public void setParametroIsNull(boolean parametroIsNull) {
        this.parametroIsNull = parametroIsNull;
    }

    /**
     * Permite buscar cuando campo parametro es NOT NULL
     * @return boolean.
     */
    public boolean isParametroIsNotNull() {
        return parametroIsNotNull;
    }

    /**
     * Permite buscar cuando campo parametro es NOT NULL
     * @param parametroIsNotNull Valor de seteo.
     */
    public void setParametroIsNotNull(boolean parametroIsNotNull) {
        this.parametroIsNotNull = parametroIsNotNull;
    }

    /**
     * Valor de busqueda de campo valor
     * @return String.
     */
    public String getValor() {
        if (valor != null) {
            switch (valorComparator) {
	            case STARTS_WITH:
	                return valor + "%";
	            case CONTAINS:
	                return "%" + valor + "%";
	            case ENDS_WITH:
	                return "%" + valor;
	            case EQUALS:
                	return valor;
              	default:
	            	break;
            }
        }
        return valor;
    }

    /**
     * Valor de busqueda de campo valor
     * @param valor Valor de seteo.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Tipo de comparador para la busqueda por campo valor
     * @return valorComparator.
     */
    public TextComparator getValorComparator() {
        return valorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo valor
     * @param valorComparator Valor de seteo.
     */
    public void setValorComparator(TextComparator valorComparator) {
        this.valorComparator = valorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getValorIn() {
        return this.valorIn;
    }

    /**
     * @param valor Valor a agregar.
     */
    public void addValorIn(String valor) {
        this.valorIn.add(valor);
    }

    /**
     * Permite buscar cuando campo valor es NULL
     * @return boolean.
     */
    public boolean isValorIsNull() {
        return valorIsNull;
    }

    /**
     * Permite buscar cuando campo valor es NULL
     * @param valorIsNull Valor de seteo.
     */
    public void setValorIsNull(boolean valorIsNull) {
        this.valorIsNull = valorIsNull;
    }

    /**
     * Permite buscar cuando campo valor es NOT NULL
     * @return boolean.
     */
    public boolean isValorIsNotNull() {
        return valorIsNotNull;
    }

    /**
     * Permite buscar cuando campo valor es NOT NULL
     * @param valorIsNotNull Valor de seteo.
     */
    public void setValorIsNotNull(boolean valorIsNotNull) {
        this.valorIsNotNull = valorIsNotNull;
    }

    /**
     * Valor de busqueda de campo variable
     * @return String.
     */
    public String getVariable() {
        if (variable != null) {
            switch (variableComparator) {
	            case STARTS_WITH:
	                return variable + "%";
	            case CONTAINS:
	                return "%" + variable + "%";
	            case ENDS_WITH:
	                return "%" + variable;
	            case EQUALS:
                	return variable;
              	default:
	            	break;
            }
        }
        return variable;
    }

    /**
     * Valor de busqueda de campo variable
     * @param variable Valor de seteo.
     */
    public void setVariable(String variable) {
        this.variable = variable;
    }

    /**
     * Tipo de comparador para la busqueda por campo variable
     * @return variableComparator.
     */
    public TextComparator getVariableComparator() {
        return variableComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo variable
     * @param variableComparator Valor de seteo.
     */
    public void setVariableComparator(TextComparator variableComparator) {
        this.variableComparator = variableComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getVariableIn() {
        return this.variableIn;
    }

    /**
     * @param variable Valor a agregar.
     */
    public void addVariableIn(String variable) {
        this.variableIn.add(variable);
    }

    /**
     * Permite buscar cuando campo variable es NULL
     * @return boolean.
     */
    public boolean isVariableIsNull() {
        return variableIsNull;
    }

    /**
     * Permite buscar cuando campo variable es NULL
     * @param variableIsNull Valor de seteo.
     */
    public void setVariableIsNull(boolean variableIsNull) {
        this.variableIsNull = variableIsNull;
    }

    /**
     * Permite buscar cuando campo variable es NOT NULL
     * @return boolean.
     */
    public boolean isVariableIsNotNull() {
        return variableIsNotNull;
    }

    /**
     * Permite buscar cuando campo variable es NOT NULL
     * @param variableIsNotNull Valor de seteo.
     */
    public void setVariableIsNotNull(boolean variableIsNotNull) {
        this.variableIsNotNull = variableIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getIdParametrosProveedor() != null) {
            criteria.add(Restrictions.eq(IDPARAMETROSPROVEEDOR, getIdParametrosProveedor()));
        }

        if (getIdParametrosProveedorIn().size() > 0) {
            criteria.add(Restrictions.in(IDPARAMETROSPROVEEDOR, getIdParametrosProveedorIn()));
        }

        if (getIdProveedor() != null) {
            criteria.add(Restrictions.eq(IDPROVEEDOR, getIdProveedor()));
        }

        if (getIdProveedorIn().size() > 0) {
            criteria.add(Restrictions.in(IDPROVEEDOR, getIdProveedorIn()));
        }

        if (isIdProveedorIsNull()) {
            criteria.add(Restrictions.isNull(IDPROVEEDOR));
        }

        if (isIdProveedorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(IDPROVEEDOR));
        }

        if (getParametro() != null) {
            if (getParametroComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PARAMETRO, getParametro()));
            } 
            else if (getParametroComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PARAMETRO, getParametro()));
            }
            else if (getParametroComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PARAMETRO, getParametro())));
            }
            else {
                criteria.add(Restrictions.like(PARAMETRO, getParametro()));
            }
        }

        if (getParametroIn().size() > 0) {
            criteria.add(Restrictions.in(PARAMETRO, getParametroIn()));
        }

        if (isParametroIsNull()) {
            criteria.add(Restrictions.isNull(PARAMETRO));
        }

        if (isParametroIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PARAMETRO));
        }

        if (getValor() != null) {
            if (getValorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(VALOR, getValor()));
            } 
            else if (getValorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(VALOR, getValor()));
            }
            else if (getValorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(VALOR, getValor())));
            }
            else {
                criteria.add(Restrictions.like(VALOR, getValor()));
            }
        }

        if (getValorIn().size() > 0) {
            criteria.add(Restrictions.in(VALOR, getValorIn()));
        }

        if (isValorIsNull()) {
            criteria.add(Restrictions.isNull(VALOR));
        }

        if (isValorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(VALOR));
        }

        if (getVariable() != null) {
            if (getVariableComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(VARIABLE, getVariable()));
            } 
            else if (getVariableComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(VARIABLE, getVariable()));
            }
            else if (getVariableComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(VARIABLE, getVariable())));
            }
            else {
                criteria.add(Restrictions.like(VARIABLE, getVariable()));
            }
        }

        if (getVariableIn().size() > 0) {
            criteria.add(Restrictions.in(VARIABLE, getVariableIn()));
        }

        if (isVariableIsNull()) {
            criteria.add(Restrictions.isNull(VARIABLE));
        }

        if (isVariableIsNotNull()) {
            criteria.add(Restrictions.isNotNull(VARIABLE));
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
 
