/*
 *
 * archivo: ViewMensajeAdjuntosDetalladaQuery.java
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
import es.minhap.sim.model.ViewMensajeAdjuntosDetallada;

/**
 * Clase con criterios de busqueda para la entidad ViewMensajeAdjuntosDetallada
 */
public class ViewMensajeAdjuntosDetalladaQuery extends AbstractHibernateQueryEntity<ViewMensajeAdjuntosDetallada> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String MENSAJEADJUNTOID = "mensajeadjuntoid";
    public static final String MENSAJEID = "mensajeid";
    public static final String ADJUNTOID = "adjuntoid";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";


    /**
     * Valor de busqueda de campo mensajeadjuntoid
     */
    private Long mensajeadjuntoid;

    /**
     * Lista de valores del campo mensajeadjuntoid para busquedas tipo IN
     */
    private List<Long> mensajeadjuntoidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo mensajeid
     */
    private Long mensajeid;

    /**
     * Lista de valores del campo mensajeid para busquedas tipo IN
     */
    private List<Long> mensajeidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo mensajeid es NULL
     */
    private boolean mensajeidIsNull = false;

    /**
     * Permite buscar cuando campo mensajeid es NOT NULL
     */
    private boolean mensajeidIsNotNull = false;

    /**
     * Valor de busqueda de campo adjuntoid
     */
    private Long adjuntoid;

    /**
     * Lista de valores del campo adjuntoid para busquedas tipo IN
     */
    private List<Long> adjuntoidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo adjuntoid es NULL
     */
    private boolean adjuntoidIsNull = false;

    /**
     * Permite buscar cuando campo adjuntoid es NOT NULL
     */
    private boolean adjuntoidIsNotNull = false;

    /**
     * Valor de busqueda de campo usuario
     */
    private String usuario;

    /**
     * Tipo de comparador para la busqueda por campo usuario
     */
    private TextComparator usuarioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo usuario para busquedas tipo IN
     */
    private List<String> usuarioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo usuario es NULL
     */
    private boolean usuarioIsNull = false;

    /**
     * Permite buscar cuando campo usuario es NOT NULL
     */
    private boolean usuarioIsNotNull = false;

    /**
     * Valor de busqueda de campo password
     */
    private String password;

    /**
     * Tipo de comparador para la busqueda por campo password
     */
    private TextComparator passwordComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo password para busquedas tipo IN
     */
    private List<String> passwordIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo password es NULL
     */
    private boolean passwordIsNull = false;

    /**
     * Permite buscar cuando campo password es NOT NULL
     */
    private boolean passwordIsNotNull = false;

    /**
     * Constructor default
     */
    public ViewMensajeAdjuntosDetalladaQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public ViewMensajeAdjuntosDetalladaQuery(Long mensajeadjuntoid) {
        setMensajeadjuntoid(mensajeadjuntoid);
    }

    /**
     * Valor de busqueda de campo mensajeadjuntoid
     * @return Long.
     */
    public Long getMensajeadjuntoid() {
        return mensajeadjuntoid;
    }

    /**
     * Valor de busqueda de campo mensajeadjuntoid
     * @param mensajeadjuntoid Valor de seteo.
     */
    public void setMensajeadjuntoid(Long mensajeadjuntoid) {
        this.mensajeadjuntoid = mensajeadjuntoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getMensajeadjuntoidIn() {
        return this.mensajeadjuntoidIn;
    }

    /**
     * @param mensajeadjuntoid Valor a agregar.
     */
    public void addMensajeadjuntoidIn(Long mensajeadjuntoid) {
        this.mensajeadjuntoidIn.add(mensajeadjuntoid);
    }

    /**
     * Valor de busqueda de campo mensajeid
     * @return Long.
     */
    public Long getMensajeid() {
        return mensajeid;
    }

    /**
     * Valor de busqueda de campo mensajeid
     * @param mensajeid Valor de seteo.
     */
    public void setMensajeid(Long mensajeid) {
        this.mensajeid = mensajeid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getMensajeidIn() {
        return this.mensajeidIn;
    }

    /**
     * @param mensajeid Valor a agregar.
     */
    public void addMensajeidIn(Long mensajeid) {
        this.mensajeidIn.add(mensajeid);
    }

    /**
     * Permite buscar cuando campo mensajeid es NULL
     * @return boolean.
     */
    public boolean isMensajeidIsNull() {
        return mensajeidIsNull;
    }

    /**
     * Permite buscar cuando campo mensajeid es NULL
     * @param mensajeidIsNull Valor de seteo.
     */
    public void setMensajeidIsNull(boolean mensajeidIsNull) {
        this.mensajeidIsNull = mensajeidIsNull;
    }

    /**
     * Permite buscar cuando campo mensajeid es NOT NULL
     * @return boolean.
     */
    public boolean isMensajeidIsNotNull() {
        return mensajeidIsNotNull;
    }

    /**
     * Permite buscar cuando campo mensajeid es NOT NULL
     * @param mensajeidIsNotNull Valor de seteo.
     */
    public void setMensajeidIsNotNull(boolean mensajeidIsNotNull) {
        this.mensajeidIsNotNull = mensajeidIsNotNull;
    }

    /**
     * Valor de busqueda de campo adjuntoid
     * @return Long.
     */
    public Long getAdjuntoid() {
        return adjuntoid;
    }

    /**
     * Valor de busqueda de campo adjuntoid
     * @param adjuntoid Valor de seteo.
     */
    public void setAdjuntoid(Long adjuntoid) {
        this.adjuntoid = adjuntoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getAdjuntoidIn() {
        return this.adjuntoidIn;
    }

    /**
     * @param adjuntoid Valor a agregar.
     */
    public void addAdjuntoidIn(Long adjuntoid) {
        this.adjuntoidIn.add(adjuntoid);
    }

    /**
     * Permite buscar cuando campo adjuntoid es NULL
     * @return boolean.
     */
    public boolean isAdjuntoidIsNull() {
        return adjuntoidIsNull;
    }

    /**
     * Permite buscar cuando campo adjuntoid es NULL
     * @param adjuntoidIsNull Valor de seteo.
     */
    public void setAdjuntoidIsNull(boolean adjuntoidIsNull) {
        this.adjuntoidIsNull = adjuntoidIsNull;
    }

    /**
     * Permite buscar cuando campo adjuntoid es NOT NULL
     * @return boolean.
     */
    public boolean isAdjuntoidIsNotNull() {
        return adjuntoidIsNotNull;
    }

    /**
     * Permite buscar cuando campo adjuntoid es NOT NULL
     * @param adjuntoidIsNotNull Valor de seteo.
     */
    public void setAdjuntoidIsNotNull(boolean adjuntoidIsNotNull) {
        this.adjuntoidIsNotNull = adjuntoidIsNotNull;
    }

    /**
     * Valor de busqueda de campo usuario
     * @return String.
     */
    public String getUsuario() {
        if (usuario != null) {
            switch (usuarioComparator) {
	            case STARTS_WITH:
	                return usuario + "%";
	            case CONTAINS:
	                return "%" + usuario + "%";
	            case ENDS_WITH:
	                return "%" + usuario;
	            case EQUALS:
                	return usuario;
              	default:
	            	break;
            }
        }
        return usuario;
    }

    /**
     * Valor de busqueda de campo usuario
     * @param usuario Valor de seteo.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Tipo de comparador para la busqueda por campo usuario
     * @return usuarioComparator.
     */
    public TextComparator getUsuarioComparator() {
        return usuarioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo usuario
     * @param usuarioComparator Valor de seteo.
     */
    public void setUsuarioComparator(TextComparator usuarioComparator) {
        this.usuarioComparator = usuarioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getUsuarioIn() {
        return this.usuarioIn;
    }

    /**
     * @param usuario Valor a agregar.
     */
    public void addUsuarioIn(String usuario) {
        this.usuarioIn.add(usuario);
    }

    /**
     * Permite buscar cuando campo usuario es NULL
     * @return boolean.
     */
    public boolean isUsuarioIsNull() {
        return usuarioIsNull;
    }

    /**
     * Permite buscar cuando campo usuario es NULL
     * @param usuarioIsNull Valor de seteo.
     */
    public void setUsuarioIsNull(boolean usuarioIsNull) {
        this.usuarioIsNull = usuarioIsNull;
    }

    /**
     * Permite buscar cuando campo usuario es NOT NULL
     * @return boolean.
     */
    public boolean isUsuarioIsNotNull() {
        return usuarioIsNotNull;
    }

    /**
     * Permite buscar cuando campo usuario es NOT NULL
     * @param usuarioIsNotNull Valor de seteo.
     */
    public void setUsuarioIsNotNull(boolean usuarioIsNotNull) {
        this.usuarioIsNotNull = usuarioIsNotNull;
    }

    /**
     * Valor de busqueda de campo password
     * @return String.
     */
    public String getPassword() {
        if (password != null) {
            switch (passwordComparator) {
	            case STARTS_WITH:
	                return password + "%";
	            case CONTAINS:
	                return "%" + password + "%";
	            case ENDS_WITH:
	                return "%" + password;
	            case EQUALS:
                	return password;
              	default:
	            	break;
            }
        }
        return password;
    }

    /**
     * Valor de busqueda de campo password
     * @param password Valor de seteo.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Tipo de comparador para la busqueda por campo password
     * @return passwordComparator.
     */
    public TextComparator getPasswordComparator() {
        return passwordComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo password
     * @param passwordComparator Valor de seteo.
     */
    public void setPasswordComparator(TextComparator passwordComparator) {
        this.passwordComparator = passwordComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getPasswordIn() {
        return this.passwordIn;
    }

    /**
     * @param password Valor a agregar.
     */
    public void addPasswordIn(String password) {
        this.passwordIn.add(password);
    }

    /**
     * Permite buscar cuando campo password es NULL
     * @return boolean.
     */
    public boolean isPasswordIsNull() {
        return passwordIsNull;
    }

    /**
     * Permite buscar cuando campo password es NULL
     * @param passwordIsNull Valor de seteo.
     */
    public void setPasswordIsNull(boolean passwordIsNull) {
        this.passwordIsNull = passwordIsNull;
    }

    /**
     * Permite buscar cuando campo password es NOT NULL
     * @return boolean.
     */
    public boolean isPasswordIsNotNull() {
        return passwordIsNotNull;
    }

    /**
     * Permite buscar cuando campo password es NOT NULL
     * @param passwordIsNotNull Valor de seteo.
     */
    public void setPasswordIsNotNull(boolean passwordIsNotNull) {
        this.passwordIsNotNull = passwordIsNotNull;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getMensajeadjuntoid() != null) {
            criteria.add(Restrictions.eq(MENSAJEADJUNTOID, getMensajeadjuntoid()));
        }

        if (getMensajeadjuntoidIn().size() > 0) {
            criteria.add(Restrictions.in(MENSAJEADJUNTOID, getMensajeadjuntoidIn()));
        }

        if (getMensajeid() != null) {
            criteria.add(Restrictions.eq(MENSAJEID, getMensajeid()));
        }

        if (getMensajeidIn().size() > 0) {
            criteria.add(Restrictions.in(MENSAJEID, getMensajeidIn()));
        }

        if (isMensajeidIsNull()) {
            criteria.add(Restrictions.isNull(MENSAJEID));
        }

        if (isMensajeidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MENSAJEID));
        }

        if (getAdjuntoid() != null) {
            criteria.add(Restrictions.eq(ADJUNTOID, getAdjuntoid()));
        }

        if (getAdjuntoidIn().size() > 0) {
            criteria.add(Restrictions.in(ADJUNTOID, getAdjuntoidIn()));
        }

        if (isAdjuntoidIsNull()) {
            criteria.add(Restrictions.isNull(ADJUNTOID));
        }

        if (isAdjuntoidIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ADJUNTOID));
        }

        if (getUsuario() != null) {
            if (getUsuarioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(USUARIO, getUsuario()));
            } 
            else if (getUsuarioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(USUARIO, getUsuario()));
            }
            else if (getUsuarioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(USUARIO, getUsuario())));
            }
            else {
                criteria.add(Restrictions.like(USUARIO, getUsuario()));
            }
        }

        if (getUsuarioIn().size() > 0) {
            criteria.add(Restrictions.in(USUARIO, getUsuarioIn()));
        }

        if (isUsuarioIsNull()) {
            criteria.add(Restrictions.isNull(USUARIO));
        }

        if (isUsuarioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(USUARIO));
        }

        if (getPassword() != null) {
            if (getPasswordComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PASSWORD, getPassword()));
            } 
            else if (getPasswordComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PASSWORD, getPassword()));
            }
            else if (getPasswordComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PASSWORD, getPassword())));
            }
            else {
                criteria.add(Restrictions.like(PASSWORD, getPassword()));
            }
        }

        if (getPasswordIn().size() > 0) {
            criteria.add(Restrictions.in(PASSWORD, getPasswordIn()));
        }

        if (isPasswordIsNull()) {
            criteria.add(Restrictions.isNull(PASSWORD));
        }

        if (isPasswordIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PASSWORD));
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
 