/*
 *
 * archivo: ViewServiciosQuery.java
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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.ViewServicios;

/**
 * Clase con criterios de busqueda para la entidad ViewServicios
 */
public class ViewServiciosQuery extends AbstractHibernateQueryEntity<ViewServicios> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
	private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";

	// Constantes para ser utilizadas como nombres de campos, para evitar
	// problemas de compilacion
	public static final String SERVICIOID = "servicioid";
	public static final String NOMBRE = "nombre";
	public static final String DESCRIPCION = "descripcion";
	public static final String ACTIVO = "activo";
	public static final String CANALID = "canalid";
	public static final String APLICACIONID = "aplicacionid";
	public static final String FECHACREACION = "fechacreacion";
	public static final String CREADOPOR = "creadopor";
	public static final String FECHAMODIFICACION = "fechamodificacion";
	public static final String MODIFICADOPOR = "modificadopor";
	public static final String NMAXENVIOS = "nmaxenvios";
	public static final String HEADERSMS = "headersms";
	public static final String CANALNOMBRE = "canalnombre";
	public static final String APLICACIONNOMBRE = "aplicacionnombre";
	public static final String ELIMINADO = "eliminado";
	public static final String CUENTAENVIO = "cuentaenvio";
	public static final String NOMBRECUENTAENVIO = "nombrecuentaenvio";
	public static final String EXTERNALID = "externalid";
	public static final String HISTORIFICACION = "historificacion";
	public static final String CONSERVACION = "conservacion";
	public static final String PENDIENTEAPROBACION = "pendienteaprobacion";
	public static final String NOMBRELOTEENVIO = "nombreloteenvio";
	public static final String BADGE = "badge";
	public static final String FCMPROJECTKEY = "fcmprojectkey";
	public static final String APNSRUTACERTIFICADO = "apnsrutacertificado";
	public static final String APNSPASSWORDCERTIFICADO = "apnspasswordcertificado";
	public static final String ANDROIDPLATAFORMA = "androidplataforma";
	public static final String IOSPLATAFORMA = "iosplataforma";
	public static final String ENDPOINT = "endpoint";
	public static final String INFORMESACTIVO = "informesactivo";
	public static final String AGRUPACIONESTADO = "agrupacionestado";
	public static final String AGRUPACIONCODORG = "agrupacioncodorg";
	public static final String AGRUPACIONCODSIA = "agrupacioncodsia";
	public static final String AGRUPACIONCODORGPAGADOR = "agrupacioncodorgpagador";
	public static final String INFORMESDESTINATARIOS = "informesdestinatarios";
	public static final String PREMIUM = "premium";

	/**
	 * Valor de busqueda de campo servicioid
	 */
	private Long servicioid;

	/**
	 * Lista de valores del campo servicioid para busquedas tipo IN
	 */
	private List<Long> servicioidIn = new ArrayList<Long>(0);

	/**
	 * Permite buscar cuando campo servicioid es NULL
	 */
	private boolean servicioidIsNull = false;

	/**
	 * Permite buscar cuando campo servicioid es NOT NULL
	 */
	private boolean servicioidIsNotNull = false;

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
	 * Valor de busqueda de campo descripcion
	 */
	private String descripcion;

	/**
	 * Tipo de comparador para la busqueda por campo descripcion
	 */
	private TextComparator descripcionComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo descripcion para busquedas tipo IN
	 */
	private List<String> descripcionIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo descripcion es NULL
	 */
	private boolean descripcionIsNull = false;

	/**
	 * Permite buscar cuando campo descripcion es NOT NULL
	 */
	private boolean descripcionIsNotNull = false;

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
	 * Valor de busqueda de campo canalid
	 */
	private Long canalid;

	/**
	 * Lista de valores del campo canalid para busquedas tipo IN
	 */
	private List<Long> canalidIn = new ArrayList<Long>(0);

	/**
	 * Permite buscar cuando campo canalid es NULL
	 */
	private boolean canalidIsNull = false;

	/**
	 * Permite buscar cuando campo canalid es NOT NULL
	 */
	private boolean canalidIsNotNull = false;

	/**
	 * Valor de busqueda de campo aplicacionid
	 */
	private Long aplicacionid;

	/**
	 * Lista de valores del campo aplicacionid para busquedas tipo IN
	 */
	private List<Long> aplicacionidIn = new ArrayList<Long>(0);

	/**
	 * Permite buscar cuando campo aplicacionid es NULL
	 */
	private boolean aplicacionidIsNull = false;

	/**
	 * Permite buscar cuando campo aplicacionid es NOT NULL
	 */
	private boolean aplicacionidIsNotNull = false;

	/**
	 * Valor inferior de rango de busqueda de fecha fechacreacion
	 */
	private Date fechacreacionMin;

	/**
	 * Valor superior de rango de busqueda de fecha fechacreacion
	 */
	private Date fechacreacionMax;

	/**
	 * Permite buscar cuando campo fechacreacion es NULL
	 */
	private boolean fechacreacionIsNull = false;

	/**
	 * Permite buscar cuando campo fechacreacion es NOT NULL
	 */
	private boolean fechacreacionIsNotNull = false;

	/**
	 * Valor de busqueda de campo creadopor
	 */
	private String creadopor;

	/**
	 * Tipo de comparador para la busqueda por campo creadopor
	 */
	private TextComparator creadoporComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo creadopor para busquedas tipo IN
	 */
	private List<String> creadoporIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo creadopor es NULL
	 */
	private boolean creadoporIsNull = false;

	/**
	 * Permite buscar cuando campo creadopor es NOT NULL
	 */
	private boolean creadoporIsNotNull = false;

	/**
	 * Valor inferior de rango de busqueda de fecha fechamodificacion
	 */
	private Date fechamodificacionMin;

	/**
	 * Valor superior de rango de busqueda de fecha fechamodificacion
	 */
	private Date fechamodificacionMax;

	/**
	 * Permite buscar cuando campo fechamodificacion es NULL
	 */
	private boolean fechamodificacionIsNull = false;

	/**
	 * Permite buscar cuando campo fechamodificacion es NOT NULL
	 */
	private boolean fechamodificacionIsNotNull = false;

	/**
	 * Valor de busqueda de campo modificadopor
	 */
	private String modificadopor;

	/**
	 * Tipo de comparador para la busqueda por campo modificadopor
	 */
	private TextComparator modificadoporComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo modificadopor para busquedas tipo IN
	 */
	private List<String> modificadoporIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo modificadopor es NULL
	 */
	private boolean modificadoporIsNull = false;

	/**
	 * Permite buscar cuando campo modificadopor es NOT NULL
	 */
	private boolean modificadoporIsNotNull = false;

	/**
	 * Valor de busqueda de campo nmaxenvios
	 */
	private Integer nmaxenvios;

	/**
	 * Lista de valores del campo nmaxenvios para busquedas tipo IN
	 */
	private List<Integer> nmaxenviosIn = new ArrayList<Integer>(0);

	/**
	 * Permite buscar cuando campo nmaxenvios es NULL
	 */
	private boolean nmaxenviosIsNull = false;

	/**
	 * Permite buscar cuando campo nmaxenvios es NOT NULL
	 */
	private boolean nmaxenviosIsNotNull = false;

	/**
	 * Valor de busqueda de campo headersms
	 */
	private String headersms;

	/**
	 * Tipo de comparador para la busqueda por campo headersms
	 */
	private TextComparator headersmsComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo headersms para busquedas tipo IN
	 */
	private List<String> headersmsIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo headersms es NULL
	 */
	private boolean headersmsIsNull = false;

	/**
	 * Permite buscar cuando campo headersms es NOT NULL
	 */
	private boolean headersmsIsNotNull = false;

	/**
	 * Valor de busqueda de campo canalnombre
	 */
	private String canalnombre;

	/**
	 * Tipo de comparador para la busqueda por campo canalnombre
	 */
	private TextComparator canalnombreComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo canalnombre para busquedas tipo IN
	 */
	private List<String> canalnombreIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo canalnombre es NULL
	 */
	private boolean canalnombreIsNull = false;

	/**
	 * Permite buscar cuando campo canalnombre es NOT NULL
	 */
	private boolean canalnombreIsNotNull = false;

	/**
	 * Valor de busqueda de campo aplicacionnombre
	 */
	private String aplicacionnombre;

	/**
	 * Tipo de comparador para la busqueda por campo aplicacionnombre
	 */
	private TextComparator aplicacionnombreComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo aplicacionnombre para busquedas tipo IN
	 */
	private List<String> aplicacionnombreIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo aplicacionnombre es NULL
	 */
	private boolean aplicacionnombreIsNull = false;

	/**
	 * Permite buscar cuando campo aplicacionnombre es NOT NULL
	 */
	private boolean aplicacionnombreIsNotNull = false;

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
	 * Valor de busqueda de campo cuentaenvio
	 */
	private String cuentaenvio;

	/**
	 * Tipo de comparador para la busqueda por campo cuentaenvio
	 */
	private TextComparator cuentaenvioComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo cuentaenvio para busquedas tipo IN
	 */
	private List<String> cuentaenvioIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo cuentaenvio es NULL
	 */
	private boolean cuentaenvioIsNull = false;

	/**
	 * Permite buscar cuando campo cuentaenvio es NOT NULL
	 */
	private boolean cuentaenvioIsNotNull = false;

	/**
	 * Valor de busqueda de campo nombrecuentaenvio
	 */
	private String nombrecuentaenvio;

	/**
	 * Tipo de comparador para la busqueda por campo nombrecuentaenvio
	 */
	private TextComparator nombrecuentaenvioComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo nombrecuentaenvio para busquedas tipo IN
	 */
	private List<String> nombrecuentaenvioIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo nombrecuentaenvio es NULL
	 */
	private boolean nombrecuentaenvioIsNull = false;

	/**
	 * Permite buscar cuando campo nombrecuentaenvio es NOT NULL
	 */
	private boolean nombrecuentaenvioIsNotNull = false;

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
	 * Valor de busqueda de campo historificacion
	 */
	private Integer historificacion;

	/**
	 * Lista de valores del campo historificacion para busquedas tipo IN
	 */
	private List<Integer> historificacionIn = new ArrayList<Integer>(0);

	/**
	 * Permite buscar cuando campo historificacion es NULL
	 */
	private boolean historificacionIsNull = false;

	/**
	 * Permite buscar cuando campo historificacion es NOT NULL
	 */
	private boolean historificacionIsNotNull = false;

	/**
	 * Valor de busqueda de campo conservacion
	 */
	private Integer conservacion;

	/**
	 * Lista de valores del campo conservacion para busquedas tipo IN
	 */
	private List<Integer> conservacionIn = new ArrayList<Integer>(0);

	/**
	 * Permite buscar cuando campo conservacion es NULL
	 */
	private boolean conservacionIsNull = false;

	/**
	 * Permite buscar cuando campo conservacion es NOT NULL
	 */
	private boolean conservacionIsNotNull = false;

	/**
	 * Valor de busqueda de campo pendienteaprobacion
	 */
	private Boolean pendienteaprobacion;

	/**
	 * Permite buscar cuando campo pendienteaprobacion es NULL
	 */
	private boolean pendienteaprobacionIsNull = false;

	/**
	 * Permite buscar cuando campo pendienteaprobacion es NOT NULL
	 */
	private boolean pendienteaprobacionIsNotNull = false;

	/**
	 * Valor de busqueda de campo nombreloteenvio
	 */
	private String nombreloteenvio;

	/**
	 * Tipo de comparador para la busqueda por campo nombreloteenvio
	 */
	private TextComparator nombreloteenvioComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo nombreloteenvio para busquedas tipo IN
	 */
	private List<String> nombreloteenvioIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo nombreloteenvio es NULL
	 */
	private boolean nombreloteenvioIsNull = false;

	/**
	 * Permite buscar cuando campo nombreloteenvio es NOT NULL
	 */
	private boolean nombreloteenvioIsNotNull = false;

	/**
	 * Valor de busqueda de campo badge
	 */
	private Long badge;

	/**
	 * Lista de valores del campo badge para busquedas tipo IN
	 */
	private List<Long> badgeIn = new ArrayList<Long>(0);

	/**
	 * Permite buscar cuando campo badge es NULL
	 */
	private boolean badgeIsNull = false;

	/**
	 * Permite buscar cuando campo badge es NOT NULL
	 */
	private boolean badgeIsNotNull = false;

	/**
	 * Valor de busqueda de campo fcmprojectkey
	 */
	private String fcmprojectkey;

	/**
	 * Tipo de comparador para la busqueda por campo fcmprojectkey
	 */
	private TextComparator fcmprojectkeyComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo fcmprojectkey para busquedas tipo IN
	 */
	private List<String> fcmprojectkeyIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo fcmprojectkey es NULL
	 */
	private boolean fcmprojectkeyIsNull = false;

	/**
	 * Permite buscar cuando campo fcmprojectkey es NOT NULL
	 */
	private boolean fcmprojectkeyIsNotNull = false;

	/**
	 * Valor de busqueda de campo apnsrutacertificado
	 */
	private String apnsrutacertificado;

	/**
	 * Tipo de comparador para la busqueda por campo apnsrutacertificado
	 */
	private TextComparator apnsrutacertificadoComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo apnsrutacertificado para busquedas tipo IN
	 */
	private List<String> apnsrutacertificadoIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo apnsrutacertificado es NULL
	 */
	private boolean apnsrutacertificadoIsNull = false;

	/**
	 * Permite buscar cuando campo apnsrutacertificado es NOT NULL
	 */
	private boolean apnsrutacertificadoIsNotNull = false;

	/**
	 * Valor de busqueda de campo apnspasswordcertificado
	 */
	private String apnspasswordcertificado;

	/**
	 * Tipo de comparador para la busqueda por campo apnspasswordcertificado
	 */
	private TextComparator apnspasswordcertificadoComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo apnspasswordcertificado para busquedas tipo IN
	 */
	private List<String> apnspasswordcertificadoIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo apnspasswordcertificado es NULL
	 */
	private boolean apnspasswordcertificadoIsNull = false;

	/**
	 * Permite buscar cuando campo apnspasswordcertificado es NOT NULL
	 */
	private boolean apnspasswordcertificadoIsNotNull = false;

	/**
	 * Valor de busqueda de campo androidplataforma
	 */
	private Boolean androidplataforma;

	/**
	 * Permite buscar cuando campo androidplataforma es NULL
	 */
	private boolean androidplataformaIsNull = false;

	/**
	 * Permite buscar cuando campo androidplataforma es NOT NULL
	 */
	private boolean androidplataformaIsNotNull = false;

	/**
	 * Valor de busqueda de campo iosplataforma
	 */
	private Boolean iosplataforma;

	/**
	 * Permite buscar cuando campo iosplataforma es NULL
	 */
	private boolean iosplataformaIsNull = false;

	/**
	 * Permite buscar cuando campo iosplataforma es NOT NULL
	 */
	private boolean iosplataformaIsNotNull = false;

	/**
	 * Valor de busqueda de campo endpoint
	 */
	private String endpoint;

	/**
	 * Tipo de comparador para la busqueda por campo endpoint
	 */
	private TextComparator endpointComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo endpoint para busquedas tipo IN
	 */
	private List<String> endpointIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo endpoint es NULL
	 */
	private boolean endpointIsNull = false;

	/**
	 * Permite buscar cuando campo endpoint es NOT NULL
	 */
	private boolean endpointIsNotNull = false;

	/**
	 * Valor de busqueda de campo informesactivo
	 */
	private Boolean informesactivo;

	/**
	 * Permite buscar cuando campo informesactivo es NULL
	 */
	private boolean informesactivoIsNull = false;

	/**
	 * Permite buscar cuando campo informesactivo es NOT NULL
	 */
	private boolean informesactivoIsNotNull = false;

	/**
	 * Valor de busqueda de campo agrupacionestado
	 */
	private Boolean agrupacionestado;

	/**
	 * Permite buscar cuando campo agrupacionestado es NULL
	 */
	private boolean agrupacionestadoIsNull = false;

	/**
	 * Permite buscar cuando campo agrupacionestado es NOT NULL
	 */
	private boolean agrupacionestadoIsNotNull = false;

	/**
	 * Valor de busqueda de campo agrupacioncodorg
	 */
	private Boolean agrupacioncodorg;

	/**
	 * Permite buscar cuando campo agrupacioncodorg es NULL
	 */
	private boolean agrupacioncodorgIsNull = false;

	/**
	 * Permite buscar cuando campo agrupacioncodorg es NOT NULL
	 */
	private boolean agrupacioncodorgIsNotNull = false;

	/**
	 * Valor de busqueda de campo agrupacioncodsia
	 */
	private Boolean agrupacioncodsia;

	/**
	 * Permite buscar cuando campo agrupacioncodsia es NULL
	 */
	private boolean agrupacioncodsiaIsNull = false;

	/**
	 * Permite buscar cuando campo agrupacioncodsia es NOT NULL
	 */
	private boolean agrupacioncodsiaIsNotNull = false;

	/**
	 * Valor de busqueda de campo agrupacioncodorgpagador
	 */
	private Boolean agrupacioncodorgpagador;

	/**
	 * Permite buscar cuando campo agrupacioncodorgpagador es NULL
	 */
	private boolean agrupacioncodorgpagadorIsNull = false;

	/**
	 * Permite buscar cuando campo agrupacioncodorgpagador es NOT NULL
	 */
	private boolean agrupacioncodorgpagadorIsNotNull = false;

	/**
	 * Valor de busqueda de campo informesdestinatarios
	 */
	private String informesdestinatarios;

	/**
	 * Tipo de comparador para la busqueda por campo informesdestinatarios
	 */
	private TextComparator informesdestinatariosComparator = TextComparator.CONTAINS;

	/**
	 * Lista de valores del campo informesdestinatarios para busquedas tipo IN
	 */
	private List<String> informesdestinatariosIn = new ArrayList<String>(0);

	/**
	 * Permite buscar cuando campo informesdestinatarios es NULL
	 */
	private boolean informesdestinatariosIsNull = false;

	/**
	 * Permite buscar cuando campo informesdestinatarios es NOT NULL
	 */
	private boolean informesdestinatariosIsNotNull = false;

	/**
	 * Valor de busqueda de campo premium
	 */
	private Boolean premium;

	/**
	 * Permite buscar cuando campo premium es NULL
	 */
	private boolean premiumIsNull = false;

	/**
	 * Permite buscar cuando campo premium es NOT NULL
	 */
	private boolean premiumIsNotNull = false;
	
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
	public ViewServiciosQuery() {

	}

	/**
	 * Valor de busqueda de campo servicioid
	 * 
	 * @return Long.
	 */
	public Long getServicioid() {
		return servicioid;
	}

	/**
	 * Valor de busqueda de campo servicioid
	 * 
	 * @param servicioid
	 *            Valor de seteo.
	 */
	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
	}

	/**
	 * @return List<Long>.
	 */
	public List<Long> getServicioidIn() {
		return this.servicioidIn;
	}

	/**
	 * @param servicioid
	 *            Valor a agregar.
	 */
	public void addServicioidIn(Long servicioid) {
		this.servicioidIn.add(servicioid);
	}

	/**
	 * Permite buscar cuando campo servicioid es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isServicioidIsNull() {
		return servicioidIsNull;
	}

	/**
	 * Permite buscar cuando campo servicioid es NULL
	 * 
	 * @param servicioidIsNull
	 *            Valor de seteo.
	 */
	public void setServicioidIsNull(boolean servicioidIsNull) {
		this.servicioidIsNull = servicioidIsNull;
	}

	/**
	 * Permite buscar cuando campo servicioid es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isServicioidIsNotNull() {
		return servicioidIsNotNull;
	}

	/**
	 * Permite buscar cuando campo servicioid es NOT NULL
	 * 
	 * @param servicioidIsNotNull
	 *            Valor de seteo.
	 */
	public void setServicioidIsNotNull(boolean servicioidIsNotNull) {
		this.servicioidIsNotNull = servicioidIsNotNull;
	}

	/**
	 * Valor de busqueda de campo nombre
	 * 
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
	 * 
	 * @param nombre
	 *            Valor de seteo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Tipo de comparador para la busqueda por campo nombre
	 * 
	 * @return nombreComparator.
	 */
	public TextComparator getNombreComparator() {
		return nombreComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo nombre
	 * 
	 * @param nombreComparator
	 *            Valor de seteo.
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
	 * @param nombre
	 *            Valor a agregar.
	 */
	public void addNombreIn(String nombre) {
		this.nombreIn.add(nombre);
	}

	/**
	 * Permite buscar cuando campo nombre es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNombreIsNull() {
		return nombreIsNull;
	}

	/**
	 * Permite buscar cuando campo nombre es NULL
	 * 
	 * @param nombreIsNull
	 *            Valor de seteo.
	 */
	public void setNombreIsNull(boolean nombreIsNull) {
		this.nombreIsNull = nombreIsNull;
	}

	/**
	 * Permite buscar cuando campo nombre es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNombreIsNotNull() {
		return nombreIsNotNull;
	}

	/**
	 * Permite buscar cuando campo nombre es NOT NULL
	 * 
	 * @param nombreIsNotNull
	 *            Valor de seteo.
	 */
	public void setNombreIsNotNull(boolean nombreIsNotNull) {
		this.nombreIsNotNull = nombreIsNotNull;
	}

	/**
	 * Valor de busqueda de campo descripcion
	 * 
	 * @return String.
	 */
	public String getDescripcion() {
		if (descripcion != null) {
			switch (descripcionComparator) {
			case STARTS_WITH:
				return descripcion + "%";
			case CONTAINS:
				return "%" + descripcion + "%";
			case ENDS_WITH:
				return "%" + descripcion;
			case EQUALS:
				return descripcion;
			default:
				break;
			}
		}
		return descripcion;
	}

	/**
	 * Valor de busqueda de campo descripcion
	 * 
	 * @param descripcion
	 *            Valor de seteo.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Tipo de comparador para la busqueda por campo descripcion
	 * 
	 * @return descripcionComparator.
	 */
	public TextComparator getDescripcionComparator() {
		return descripcionComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo descripcion
	 * 
	 * @param descripcionComparator
	 *            Valor de seteo.
	 */
	public void setDescripcionComparator(TextComparator descripcionComparator) {
		this.descripcionComparator = descripcionComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getDescripcionIn() {
		return this.descripcionIn;
	}

	/**
	 * @param descripcion
	 *            Valor a agregar.
	 */
	public void addDescripcionIn(String descripcion) {
		this.descripcionIn.add(descripcion);
	}

	/**
	 * Permite buscar cuando campo descripcion es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isDescripcionIsNull() {
		return descripcionIsNull;
	}

	/**
	 * Permite buscar cuando campo descripcion es NULL
	 * 
	 * @param descripcionIsNull
	 *            Valor de seteo.
	 */
	public void setDescripcionIsNull(boolean descripcionIsNull) {
		this.descripcionIsNull = descripcionIsNull;
	}

	/**
	 * Permite buscar cuando campo descripcion es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isDescripcionIsNotNull() {
		return descripcionIsNotNull;
	}

	/**
	 * Permite buscar cuando campo descripcion es NOT NULL
	 * 
	 * @param descripcionIsNotNull
	 *            Valor de seteo.
	 */
	public void setDescripcionIsNotNull(boolean descripcionIsNotNull) {
		this.descripcionIsNotNull = descripcionIsNotNull;
	}

	/**
	 * Valor de busqueda de campo activo
	 * 
	 * @return Boolean.
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Valor de busqueda de campo activo
	 * 
	 * @param activo
	 *            Valor de seteo.
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Permite buscar cuando campo activo es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isActivoIsNull() {
		return activoIsNull;
	}

	/**
	 * Permite buscar cuando campo activo es NULL
	 * 
	 * @param activoIsNull
	 *            Valor de seteo.
	 */
	public void setActivoIsNull(boolean activoIsNull) {
		this.activoIsNull = activoIsNull;
	}

	/**
	 * Permite buscar cuando campo activo es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isActivoIsNotNull() {
		return activoIsNotNull;
	}

	/**
	 * Permite buscar cuando campo activo es NOT NULL
	 * 
	 * @param activoIsNotNull
	 *            Valor de seteo.
	 */
	public void setActivoIsNotNull(boolean activoIsNotNull) {
		this.activoIsNotNull = activoIsNotNull;
	}

	/**
	 * Valor de busqueda de campo canalid
	 * 
	 * @return Long.
	 */
	public Long getCanalid() {
		return canalid;
	}

	/**
	 * Valor de busqueda de campo canalid
	 * 
	 * @param canalid
	 *            Valor de seteo.
	 */
	public void setCanalid(Long canalid) {
		this.canalid = canalid;
	}

	/**
	 * @return List<Long>.
	 */
	public List<Long> getCanalidIn() {
		return this.canalidIn;
	}

	/**
	 * @param canalid
	 *            Valor a agregar.
	 */
	public void addCanalidIn(Long canalid) {
		this.canalidIn.add(canalid);
	}

	/**
	 * Permite buscar cuando campo canalid es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCanalidIsNull() {
		return canalidIsNull;
	}

	/**
	 * Permite buscar cuando campo canalid es NULL
	 * 
	 * @param canalidIsNull
	 *            Valor de seteo.
	 */
	public void setCanalidIsNull(boolean canalidIsNull) {
		this.canalidIsNull = canalidIsNull;
	}

	/**
	 * Permite buscar cuando campo canalid es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCanalidIsNotNull() {
		return canalidIsNotNull;
	}

	/**
	 * Permite buscar cuando campo canalid es NOT NULL
	 * 
	 * @param canalidIsNotNull
	 *            Valor de seteo.
	 */
	public void setCanalidIsNotNull(boolean canalidIsNotNull) {
		this.canalidIsNotNull = canalidIsNotNull;
	}

	/**
	 * Valor de busqueda de campo aplicacionid
	 * 
	 * @return Long.
	 */
	public Long getAplicacionid() {
		return aplicacionid;
	}

	/**
	 * Valor de busqueda de campo aplicacionid
	 * 
	 * @param aplicacionid
	 *            Valor de seteo.
	 */
	public void setAplicacionid(Long aplicacionid) {
		this.aplicacionid = aplicacionid;
	}

	/**
	 * @return List<Long>.
	 */
	public List<Long> getAplicacionidIn() {
		return this.aplicacionidIn;
	}

	/**
	 * @param aplicacionid
	 *            Valor a agregar.
	 */
	public void addAplicacionidIn(Long aplicacionid) {
		this.aplicacionidIn.add(aplicacionid);
	}

	/**
	 * Permite buscar cuando campo aplicacionid es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAplicacionidIsNull() {
		return aplicacionidIsNull;
	}

	/**
	 * Permite buscar cuando campo aplicacionid es NULL
	 * 
	 * @param aplicacionidIsNull
	 *            Valor de seteo.
	 */
	public void setAplicacionidIsNull(boolean aplicacionidIsNull) {
		this.aplicacionidIsNull = aplicacionidIsNull;
	}

	/**
	 * Permite buscar cuando campo aplicacionid es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAplicacionidIsNotNull() {
		return aplicacionidIsNotNull;
	}

	/**
	 * Permite buscar cuando campo aplicacionid es NOT NULL
	 * 
	 * @param aplicacionidIsNotNull
	 *            Valor de seteo.
	 */
	public void setAplicacionidIsNotNull(boolean aplicacionidIsNotNull) {
		this.aplicacionidIsNotNull = aplicacionidIsNotNull;
	}

	/**
	 * Valor inferior de rango de busqueda de fecha fechacreacion
	 * 
	 * @return ${field.getName()}Min.
	 */
	public Date getFechacreacionMin() {
		if (fechacreacionMin != null) {
			return DateUtil.toDayBegin(fechacreacionMin);
		}
		return fechacreacionMin;
	}

	/**
	 * Valor inferior de rango de busqueda de fecha fechacreacion
	 * 
	 * @param fechacreacionMin
	 *            Valor de seteo.
	 */
	public void setFechacreacionMin(Date fechacreacionMin) {
		this.fechacreacionMin = fechacreacionMin;
	}

	/**
	 * Valor superior de rango de busqueda de fecha fechacreacion
	 * 
	 * @return fechacreacionMax.
	 */
	public Date getFechacreacionMax() {
		if (fechacreacionMax != null) {
			return DateUtil.toDayEnd(fechacreacionMax);
		}
		return fechacreacionMax;
	}

	/**
	 * Valor superior de rango de busqueda de fecha fechacreacion
	 * 
	 * @param fechacreacionMax
	 *            Valor de seteo.
	 */
	public void setFechacreacionMax(Date fechacreacionMax) {
		this.fechacreacionMax = fechacreacionMax;
	}

	/**
	 * Permite buscar cuando campo fechacreacion es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechacreacionIsNull() {
		return fechacreacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechacreacion es NULL
	 * 
	 * @param fechacreacionIsNull
	 *            Valor de seteo.
	 */
	public void setFechacreacionIsNull(boolean fechacreacionIsNull) {
		this.fechacreacionIsNull = fechacreacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechacreacion es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechacreacionIsNotNull() {
		return fechacreacionIsNotNull;
	}

	/**
	 * Permite buscar cuando campo fechacreacion es NOT NULL
	 * 
	 * @param fechacreacionIsNotNull
	 *            Valor de seteo.
	 */
	public void setFechacreacionIsNotNull(boolean fechacreacionIsNotNull) {
		this.fechacreacionIsNotNull = fechacreacionIsNotNull;
	}

	/**
	 * Valor de busqueda de campo creadopor
	 * 
	 * @return String.
	 */
	public String getCreadopor() {
		if (creadopor != null) {
			switch (creadoporComparator) {
			case STARTS_WITH:
				return creadopor + "%";
			case CONTAINS:
				return "%" + creadopor + "%";
			case ENDS_WITH:
				return "%" + creadopor;
			case EQUALS:
				return creadopor;
			default:
				break;
			}
		}
		return creadopor;
	}

	/**
	 * Valor de busqueda de campo creadopor
	 * 
	 * @param creadopor
	 *            Valor de seteo.
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * Tipo de comparador para la busqueda por campo creadopor
	 * 
	 * @return creadoporComparator.
	 */
	public TextComparator getCreadoporComparator() {
		return creadoporComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo creadopor
	 * 
	 * @param creadoporComparator
	 *            Valor de seteo.
	 */
	public void setCreadoporComparator(TextComparator creadoporComparator) {
		this.creadoporComparator = creadoporComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getCreadoporIn() {
		return this.creadoporIn;
	}

	/**
	 * @param creadopor
	 *            Valor a agregar.
	 */
	public void addCreadoporIn(String creadopor) {
		this.creadoporIn.add(creadopor);
	}

	/**
	 * Permite buscar cuando campo creadopor es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCreadoporIsNull() {
		return creadoporIsNull;
	}

	/**
	 * Permite buscar cuando campo creadopor es NULL
	 * 
	 * @param creadoporIsNull
	 *            Valor de seteo.
	 */
	public void setCreadoporIsNull(boolean creadoporIsNull) {
		this.creadoporIsNull = creadoporIsNull;
	}

	/**
	 * Permite buscar cuando campo creadopor es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCreadoporIsNotNull() {
		return creadoporIsNotNull;
	}

	/**
	 * Permite buscar cuando campo creadopor es NOT NULL
	 * 
	 * @param creadoporIsNotNull
	 *            Valor de seteo.
	 */
	public void setCreadoporIsNotNull(boolean creadoporIsNotNull) {
		this.creadoporIsNotNull = creadoporIsNotNull;
	}

	/**
	 * Valor inferior de rango de busqueda de fecha fechamodificacion
	 * 
	 * @return ${field.getName()}Min.
	 */
	public Date getFechamodificacionMin() {
		if (fechamodificacionMin != null) {
			return DateUtil.toDayBegin(fechamodificacionMin);
		}
		return fechamodificacionMin;
	}

	/**
	 * Valor inferior de rango de busqueda de fecha fechamodificacion
	 * 
	 * @param fechamodificacionMin
	 *            Valor de seteo.
	 */
	public void setFechamodificacionMin(Date fechamodificacionMin) {
		this.fechamodificacionMin = fechamodificacionMin;
	}

	/**
	 * Valor superior de rango de busqueda de fecha fechamodificacion
	 * 
	 * @return fechamodificacionMax.
	 */
	public Date getFechamodificacionMax() {
		if (fechamodificacionMax != null) {
			return DateUtil.toDayEnd(fechamodificacionMax);
		}
		return fechamodificacionMax;
	}

	/**
	 * Valor superior de rango de busqueda de fecha fechamodificacion
	 * 
	 * @param fechamodificacionMax
	 *            Valor de seteo.
	 */
	public void setFechamodificacionMax(Date fechamodificacionMax) {
		this.fechamodificacionMax = fechamodificacionMax;
	}

	/**
	 * Permite buscar cuando campo fechamodificacion es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechamodificacionIsNull() {
		return fechamodificacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechamodificacion es NULL
	 * 
	 * @param fechamodificacionIsNull
	 *            Valor de seteo.
	 */
	public void setFechamodificacionIsNull(boolean fechamodificacionIsNull) {
		this.fechamodificacionIsNull = fechamodificacionIsNull;
	}

	/**
	 * Permite buscar cuando campo fechamodificacion es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFechamodificacionIsNotNull() {
		return fechamodificacionIsNotNull;
	}

	/**
	 * Permite buscar cuando campo fechamodificacion es NOT NULL
	 * 
	 * @param fechamodificacionIsNotNull
	 *            Valor de seteo.
	 */
	public void setFechamodificacionIsNotNull(boolean fechamodificacionIsNotNull) {
		this.fechamodificacionIsNotNull = fechamodificacionIsNotNull;
	}

	/**
	 * Valor de busqueda de campo modificadopor
	 * 
	 * @return String.
	 */
	public String getModificadopor() {
		if (modificadopor != null) {
			switch (modificadoporComparator) {
			case STARTS_WITH:
				return modificadopor + "%";
			case CONTAINS:
				return "%" + modificadopor + "%";
			case ENDS_WITH:
				return "%" + modificadopor;
			case EQUALS:
				return modificadopor;
			default:
				break;
			}
		}
		return modificadopor;
	}

	/**
	 * Valor de busqueda de campo modificadopor
	 * 
	 * @param modificadopor
	 *            Valor de seteo.
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * Tipo de comparador para la busqueda por campo modificadopor
	 * 
	 * @return modificadoporComparator.
	 */
	public TextComparator getModificadoporComparator() {
		return modificadoporComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo modificadopor
	 * 
	 * @param modificadoporComparator
	 *            Valor de seteo.
	 */
	public void setModificadoporComparator(TextComparator modificadoporComparator) {
		this.modificadoporComparator = modificadoporComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getModificadoporIn() {
		return this.modificadoporIn;
	}

	/**
	 * @param modificadopor
	 *            Valor a agregar.
	 */
	public void addModificadoporIn(String modificadopor) {
		this.modificadoporIn.add(modificadopor);
	}

	/**
	 * Permite buscar cuando campo modificadopor es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isModificadoporIsNull() {
		return modificadoporIsNull;
	}

	/**
	 * Permite buscar cuando campo modificadopor es NULL
	 * 
	 * @param modificadoporIsNull
	 *            Valor de seteo.
	 */
	public void setModificadoporIsNull(boolean modificadoporIsNull) {
		this.modificadoporIsNull = modificadoporIsNull;
	}

	/**
	 * Permite buscar cuando campo modificadopor es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isModificadoporIsNotNull() {
		return modificadoporIsNotNull;
	}

	/**
	 * Permite buscar cuando campo modificadopor es NOT NULL
	 * 
	 * @param modificadoporIsNotNull
	 *            Valor de seteo.
	 */
	public void setModificadoporIsNotNull(boolean modificadoporIsNotNull) {
		this.modificadoporIsNotNull = modificadoporIsNotNull;
	}

	/**
	 * Valor de busqueda de campo nmaxenvios
	 * 
	 * @return Integer.
	 */
	public Integer getNmaxenvios() {
		return nmaxenvios;
	}

	/**
	 * Valor de busqueda de campo nmaxenvios
	 * 
	 * @param nmaxenvios
	 *            Valor de seteo.
	 */
	public void setNmaxenvios(Integer nmaxenvios) {
		this.nmaxenvios = nmaxenvios;
	}

	/**
	 * @return List<Integer>.
	 */
	public List<Integer> getNmaxenviosIn() {
		return this.nmaxenviosIn;
	}

	/**
	 * @param nmaxenvios
	 *            Valor a agregar.
	 */
	public void addNmaxenviosIn(Integer nmaxenvios) {
		this.nmaxenviosIn.add(nmaxenvios);
	}

	/**
	 * Permite buscar cuando campo nmaxenvios es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNmaxenviosIsNull() {
		return nmaxenviosIsNull;
	}

	/**
	 * Permite buscar cuando campo nmaxenvios es NULL
	 * 
	 * @param nmaxenviosIsNull
	 *            Valor de seteo.
	 */
	public void setNmaxenviosIsNull(boolean nmaxenviosIsNull) {
		this.nmaxenviosIsNull = nmaxenviosIsNull;
	}

	/**
	 * Permite buscar cuando campo nmaxenvios es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNmaxenviosIsNotNull() {
		return nmaxenviosIsNotNull;
	}

	/**
	 * Permite buscar cuando campo nmaxenvios es NOT NULL
	 * 
	 * @param nmaxenviosIsNotNull
	 *            Valor de seteo.
	 */
	public void setNmaxenviosIsNotNull(boolean nmaxenviosIsNotNull) {
		this.nmaxenviosIsNotNull = nmaxenviosIsNotNull;
	}

	/**
	 * Valor de busqueda de campo headersms
	 * 
	 * @return String.
	 */
	public String getHeadersms() {
		if (headersms != null) {
			switch (headersmsComparator) {
			case STARTS_WITH:
				return headersms + "%";
			case CONTAINS:
				return "%" + headersms + "%";
			case ENDS_WITH:
				return "%" + headersms;
			case EQUALS:
				return headersms;
			default:
				break;
			}
		}
		return headersms;
	}

	/**
	 * Valor de busqueda de campo headersms
	 * 
	 * @param headersms
	 *            Valor de seteo.
	 */
	public void setHeadersms(String headersms) {
		this.headersms = headersms;
	}

	/**
	 * Tipo de comparador para la busqueda por campo headersms
	 * 
	 * @return headersmsComparator.
	 */
	public TextComparator getHeadersmsComparator() {
		return headersmsComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo headersms
	 * 
	 * @param headersmsComparator
	 *            Valor de seteo.
	 */
	public void setHeadersmsComparator(TextComparator headersmsComparator) {
		this.headersmsComparator = headersmsComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getHeadersmsIn() {
		return this.headersmsIn;
	}

	/**
	 * @param headersms
	 *            Valor a agregar.
	 */
	public void addHeadersmsIn(String headersms) {
		this.headersmsIn.add(headersms);
	}

	/**
	 * Permite buscar cuando campo headersms es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isHeadersmsIsNull() {
		return headersmsIsNull;
	}

	/**
	 * Permite buscar cuando campo headersms es NULL
	 * 
	 * @param headersmsIsNull
	 *            Valor de seteo.
	 */
	public void setHeadersmsIsNull(boolean headersmsIsNull) {
		this.headersmsIsNull = headersmsIsNull;
	}

	/**
	 * Permite buscar cuando campo headersms es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isHeadersmsIsNotNull() {
		return headersmsIsNotNull;
	}

	/**
	 * Permite buscar cuando campo headersms es NOT NULL
	 * 
	 * @param headersmsIsNotNull
	 *            Valor de seteo.
	 */
	public void setHeadersmsIsNotNull(boolean headersmsIsNotNull) {
		this.headersmsIsNotNull = headersmsIsNotNull;
	}

	/**
	 * Valor de busqueda de campo canalnombre
	 * 
	 * @return String.
	 */
	public String getCanalnombre() {
		if (canalnombre != null) {
			switch (canalnombreComparator) {
			case STARTS_WITH:
				return canalnombre + "%";
			case CONTAINS:
				return "%" + canalnombre + "%";
			case ENDS_WITH:
				return "%" + canalnombre;
			case EQUALS:
				return canalnombre;
			default:
				break;
			}
		}
		return canalnombre;
	}

	/**
	 * Valor de busqueda de campo canalnombre
	 * 
	 * @param canalnombre
	 *            Valor de seteo.
	 */
	public void setCanalnombre(String canalnombre) {
		this.canalnombre = canalnombre;
	}

	/**
	 * Tipo de comparador para la busqueda por campo canalnombre
	 * 
	 * @return canalnombreComparator.
	 */
	public TextComparator getCanalnombreComparator() {
		return canalnombreComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo canalnombre
	 * 
	 * @param canalnombreComparator
	 *            Valor de seteo.
	 */
	public void setCanalnombreComparator(TextComparator canalnombreComparator) {
		this.canalnombreComparator = canalnombreComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getCanalnombreIn() {
		return this.canalnombreIn;
	}

	/**
	 * @param canalnombre
	 *            Valor a agregar.
	 */
	public void addCanalnombreIn(String canalnombre) {
		this.canalnombreIn.add(canalnombre);
	}

	/**
	 * Permite buscar cuando campo canalnombre es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCanalnombreIsNull() {
		return canalnombreIsNull;
	}

	/**
	 * Permite buscar cuando campo canalnombre es NULL
	 * 
	 * @param canalnombreIsNull
	 *            Valor de seteo.
	 */
	public void setCanalnombreIsNull(boolean canalnombreIsNull) {
		this.canalnombreIsNull = canalnombreIsNull;
	}

	/**
	 * Permite buscar cuando campo canalnombre es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCanalnombreIsNotNull() {
		return canalnombreIsNotNull;
	}

	/**
	 * Permite buscar cuando campo canalnombre es NOT NULL
	 * 
	 * @param canalnombreIsNotNull
	 *            Valor de seteo.
	 */
	public void setCanalnombreIsNotNull(boolean canalnombreIsNotNull) {
		this.canalnombreIsNotNull = canalnombreIsNotNull;
	}

	/**
	 * Valor de busqueda de campo aplicacionnombre
	 * 
	 * @return String.
	 */
	public String getAplicacionnombre() {
		if (aplicacionnombre != null) {
			switch (aplicacionnombreComparator) {
			case STARTS_WITH:
				return aplicacionnombre + "%";
			case CONTAINS:
				return "%" + aplicacionnombre + "%";
			case ENDS_WITH:
				return "%" + aplicacionnombre;
			case EQUALS:
				return aplicacionnombre;
			default:
				break;
			}
		}
		return aplicacionnombre;
	}

	/**
	 * Valor de busqueda de campo aplicacionnombre
	 * 
	 * @param aplicacionnombre
	 *            Valor de seteo.
	 */
	public void setAplicacionnombre(String aplicacionnombre) {
		this.aplicacionnombre = aplicacionnombre;
	}

	/**
	 * Tipo de comparador para la busqueda por campo aplicacionnombre
	 * 
	 * @return aplicacionnombreComparator.
	 */
	public TextComparator getAplicacionnombreComparator() {
		return aplicacionnombreComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo aplicacionnombre
	 * 
	 * @param aplicacionnombreComparator
	 *            Valor de seteo.
	 */
	public void setAplicacionnombreComparator(TextComparator aplicacionnombreComparator) {
		this.aplicacionnombreComparator = aplicacionnombreComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getAplicacionnombreIn() {
		return this.aplicacionnombreIn;
	}

	/**
	 * @param aplicacionnombre
	 *            Valor a agregar.
	 */
	public void addAplicacionnombreIn(String aplicacionnombre) {
		this.aplicacionnombreIn.add(aplicacionnombre);
	}

	/**
	 * Permite buscar cuando campo aplicacionnombre es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAplicacionnombreIsNull() {
		return aplicacionnombreIsNull;
	}

	/**
	 * Permite buscar cuando campo aplicacionnombre es NULL
	 * 
	 * @param aplicacionnombreIsNull
	 *            Valor de seteo.
	 */
	public void setAplicacionnombreIsNull(boolean aplicacionnombreIsNull) {
		this.aplicacionnombreIsNull = aplicacionnombreIsNull;
	}

	/**
	 * Permite buscar cuando campo aplicacionnombre es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAplicacionnombreIsNotNull() {
		return aplicacionnombreIsNotNull;
	}

	/**
	 * Permite buscar cuando campo aplicacionnombre es NOT NULL
	 * 
	 * @param aplicacionnombreIsNotNull
	 *            Valor de seteo.
	 */
	public void setAplicacionnombreIsNotNull(boolean aplicacionnombreIsNotNull) {
		this.aplicacionnombreIsNotNull = aplicacionnombreIsNotNull;
	}

	/**
	 * Valor de busqueda de campo eliminado
	 * 
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
	 * 
	 * @param eliminado
	 *            Valor de seteo.
	 */
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	/**
	 * Tipo de comparador para la busqueda por campo eliminado
	 * 
	 * @return eliminadoComparator.
	 */
	public TextComparator getEliminadoComparator() {
		return eliminadoComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo eliminado
	 * 
	 * @param eliminadoComparator
	 *            Valor de seteo.
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
	 * @param eliminado
	 *            Valor a agregar.
	 */
	public void addEliminadoIn(String eliminado) {
		this.eliminadoIn.add(eliminado);
	}

	/**
	 * Permite buscar cuando campo eliminado es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isEliminadoIsNull() {
		return eliminadoIsNull;
	}

	/**
	 * Permite buscar cuando campo eliminado es NULL
	 * 
	 * @param eliminadoIsNull
	 *            Valor de seteo.
	 */
	public void setEliminadoIsNull(boolean eliminadoIsNull) {
		this.eliminadoIsNull = eliminadoIsNull;
	}

	/**
	 * Permite buscar cuando campo eliminado es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isEliminadoIsNotNull() {
		return eliminadoIsNotNull;
	}

	/**
	 * Permite buscar cuando campo eliminado es NOT NULL
	 * 
	 * @param eliminadoIsNotNull
	 *            Valor de seteo.
	 */
	public void setEliminadoIsNotNull(boolean eliminadoIsNotNull) {
		this.eliminadoIsNotNull = eliminadoIsNotNull;
	}

	/**
	 * Valor de busqueda de campo cuentaenvio
	 * 
	 * @return String.
	 */
	public String getCuentaenvio() {
		if (cuentaenvio != null) {
			switch (cuentaenvioComparator) {
			case STARTS_WITH:
				return cuentaenvio + "%";
			case CONTAINS:
				return "%" + cuentaenvio + "%";
			case ENDS_WITH:
				return "%" + cuentaenvio;
			case EQUALS:
				return cuentaenvio;
			default:
				break;
			}
		}
		return cuentaenvio;
	}

	/**
	 * Valor de busqueda de campo cuentaenvio
	 * 
	 * @param cuentaenvio
	 *            Valor de seteo.
	 */
	public void setCuentaenvio(String cuentaenvio) {
		this.cuentaenvio = cuentaenvio;
	}

	/**
	 * Tipo de comparador para la busqueda por campo cuentaenvio
	 * 
	 * @return cuentaenvioComparator.
	 */
	public TextComparator getCuentaenvioComparator() {
		return cuentaenvioComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo cuentaenvio
	 * 
	 * @param cuentaenvioComparator
	 *            Valor de seteo.
	 */
	public void setCuentaenvioComparator(TextComparator cuentaenvioComparator) {
		this.cuentaenvioComparator = cuentaenvioComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getCuentaenvioIn() {
		return this.cuentaenvioIn;
	}

	/**
	 * @param cuentaenvio
	 *            Valor a agregar.
	 */
	public void addCuentaenvioIn(String cuentaenvio) {
		this.cuentaenvioIn.add(cuentaenvio);
	}

	/**
	 * Permite buscar cuando campo cuentaenvio es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCuentaenvioIsNull() {
		return cuentaenvioIsNull;
	}

	/**
	 * Permite buscar cuando campo cuentaenvio es NULL
	 * 
	 * @param cuentaenvioIsNull
	 *            Valor de seteo.
	 */
	public void setCuentaenvioIsNull(boolean cuentaenvioIsNull) {
		this.cuentaenvioIsNull = cuentaenvioIsNull;
	}

	/**
	 * Permite buscar cuando campo cuentaenvio es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isCuentaenvioIsNotNull() {
		return cuentaenvioIsNotNull;
	}

	/**
	 * Permite buscar cuando campo cuentaenvio es NOT NULL
	 * 
	 * @param cuentaenvioIsNotNull
	 *            Valor de seteo.
	 */
	public void setCuentaenvioIsNotNull(boolean cuentaenvioIsNotNull) {
		this.cuentaenvioIsNotNull = cuentaenvioIsNotNull;
	}

	/**
	 * Valor de busqueda de campo nombrecuentaenvio
	 * 
	 * @return String.
	 */
	public String getNombrecuentaenvio() {
		if (nombrecuentaenvio != null) {
			switch (nombrecuentaenvioComparator) {
			case STARTS_WITH:
				return nombrecuentaenvio + "%";
			case CONTAINS:
				return "%" + nombrecuentaenvio + "%";
			case ENDS_WITH:
				return "%" + nombrecuentaenvio;
			case EQUALS:
				return nombrecuentaenvio;
			default:
				break;
			}
		}
		return nombrecuentaenvio;
	}

	/**
	 * Valor de busqueda de campo nombrecuentaenvio
	 * 
	 * @param nombrecuentaenvio
	 *            Valor de seteo.
	 */
	public void setNombrecuentaenvio(String nombrecuentaenvio) {
		this.nombrecuentaenvio = nombrecuentaenvio;
	}

	/**
	 * Tipo de comparador para la busqueda por campo nombrecuentaenvio
	 * 
	 * @return nombrecuentaenvioComparator.
	 */
	public TextComparator getNombrecuentaenvioComparator() {
		return nombrecuentaenvioComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo nombrecuentaenvio
	 * 
	 * @param nombrecuentaenvioComparator
	 *            Valor de seteo.
	 */
	public void setNombrecuentaenvioComparator(TextComparator nombrecuentaenvioComparator) {
		this.nombrecuentaenvioComparator = nombrecuentaenvioComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getNombrecuentaenvioIn() {
		return this.nombrecuentaenvioIn;
	}

	/**
	 * @param nombrecuentaenvio
	 *            Valor a agregar.
	 */
	public void addNombrecuentaenvioIn(String nombrecuentaenvio) {
		this.nombrecuentaenvioIn.add(nombrecuentaenvio);
	}

	/**
	 * Permite buscar cuando campo nombrecuentaenvio es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNombrecuentaenvioIsNull() {
		return nombrecuentaenvioIsNull;
	}

	/**
	 * Permite buscar cuando campo nombrecuentaenvio es NULL
	 * 
	 * @param nombrecuentaenvioIsNull
	 *            Valor de seteo.
	 */
	public void setNombrecuentaenvioIsNull(boolean nombrecuentaenvioIsNull) {
		this.nombrecuentaenvioIsNull = nombrecuentaenvioIsNull;
	}

	/**
	 * Permite buscar cuando campo nombrecuentaenvio es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNombrecuentaenvioIsNotNull() {
		return nombrecuentaenvioIsNotNull;
	}

	/**
	 * Permite buscar cuando campo nombrecuentaenvio es NOT NULL
	 * 
	 * @param nombrecuentaenvioIsNotNull
	 *            Valor de seteo.
	 */
	public void setNombrecuentaenvioIsNotNull(boolean nombrecuentaenvioIsNotNull) {
		this.nombrecuentaenvioIsNotNull = nombrecuentaenvioIsNotNull;
	}

	/**
	 * Valor de busqueda de campo externalid
	 * 
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
	 * 
	 * @param externalid
	 *            Valor de seteo.
	 */
	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}

	/**
	 * Tipo de comparador para la busqueda por campo externalid
	 * 
	 * @return externalidComparator.
	 */
	public TextComparator getExternalidComparator() {
		return externalidComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo externalid
	 * 
	 * @param externalidComparator
	 *            Valor de seteo.
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
	 * @param externalid
	 *            Valor a agregar.
	 */
	public void addExternalidIn(String externalid) {
		this.externalidIn.add(externalid);
	}

	/**
	 * Permite buscar cuando campo externalid es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isExternalidIsNull() {
		return externalidIsNull;
	}

	/**
	 * Permite buscar cuando campo externalid es NULL
	 * 
	 * @param externalidIsNull
	 *            Valor de seteo.
	 */
	public void setExternalidIsNull(boolean externalidIsNull) {
		this.externalidIsNull = externalidIsNull;
	}

	/**
	 * Permite buscar cuando campo externalid es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isExternalidIsNotNull() {
		return externalidIsNotNull;
	}

	/**
	 * Permite buscar cuando campo externalid es NOT NULL
	 * 
	 * @param externalidIsNotNull
	 *            Valor de seteo.
	 */
	public void setExternalidIsNotNull(boolean externalidIsNotNull) {
		this.externalidIsNotNull = externalidIsNotNull;
	}

	/**
	 * Valor de busqueda de campo historificacion
	 * 
	 * @return Integer.
	 */
	public Integer getHistorificacion() {
		return historificacion;
	}

	/**
	 * Valor de busqueda de campo historificacion
	 * 
	 * @param historificacion
	 *            Valor de seteo.
	 */
	public void setHistorificacion(Integer historificacion) {
		this.historificacion = historificacion;
	}

	/**
	 * @return List<Integer>.
	 */
	public List<Integer> getHistorificacionIn() {
		return this.historificacionIn;
	}

	/**
	 * @param historificacion
	 *            Valor a agregar.
	 */
	public void addHistorificacionIn(Integer historificacion) {
		this.historificacionIn.add(historificacion);
	}

	/**
	 * Permite buscar cuando campo historificacion es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isHistorificacionIsNull() {
		return historificacionIsNull;
	}

	/**
	 * Permite buscar cuando campo historificacion es NULL
	 * 
	 * @param historificacionIsNull
	 *            Valor de seteo.
	 */
	public void setHistorificacionIsNull(boolean historificacionIsNull) {
		this.historificacionIsNull = historificacionIsNull;
	}

	/**
	 * Permite buscar cuando campo historificacion es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isHistorificacionIsNotNull() {
		return historificacionIsNotNull;
	}

	/**
	 * Permite buscar cuando campo historificacion es NOT NULL
	 * 
	 * @param historificacionIsNotNull
	 *            Valor de seteo.
	 */
	public void setHistorificacionIsNotNull(boolean historificacionIsNotNull) {
		this.historificacionIsNotNull = historificacionIsNotNull;
	}

	/**
	 * Valor de busqueda de campo conservacion
	 * 
	 * @return Integer.
	 */
	public Integer getConservacion() {
		return conservacion;
	}

	/**
	 * Valor de busqueda de campo conservacion
	 * 
	 * @param conservacion
	 *            Valor de seteo.
	 */
	public void setConservacion(Integer conservacion) {
		this.conservacion = conservacion;
	}

	/**
	 * @return List<Integer>.
	 */
	public List<Integer> getConservacionIn() {
		return this.conservacionIn;
	}

	/**
	 * @param conservacion
	 *            Valor a agregar.
	 */
	public void addConservacionIn(Integer conservacion) {
		this.conservacionIn.add(conservacion);
	}

	/**
	 * Permite buscar cuando campo conservacion es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isConservacionIsNull() {
		return conservacionIsNull;
	}

	/**
	 * Permite buscar cuando campo conservacion es NULL
	 * 
	 * @param conservacionIsNull
	 *            Valor de seteo.
	 */
	public void setConservacionIsNull(boolean conservacionIsNull) {
		this.conservacionIsNull = conservacionIsNull;
	}

	/**
	 * Permite buscar cuando campo conservacion es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isConservacionIsNotNull() {
		return conservacionIsNotNull;
	}

	/**
	 * Permite buscar cuando campo conservacion es NOT NULL
	 * 
	 * @param conservacionIsNotNull
	 *            Valor de seteo.
	 */
	public void setConservacionIsNotNull(boolean conservacionIsNotNull) {
		this.conservacionIsNotNull = conservacionIsNotNull;
	}

	/**
	 * Valor de busqueda de campo pendienteaprobacion
	 * 
	 * @return Boolean.
	 */
	public Boolean getPendienteaprobacion() {
		return pendienteaprobacion;
	}

	/**
	 * Valor de busqueda de campo pendienteaprobacion
	 * 
	 * @param pendienteaprobacion
	 *            Valor de seteo.
	 */
	public void setPendienteaprobacion(Boolean pendienteaprobacion) {
		this.pendienteaprobacion = pendienteaprobacion;
	}

	/**
	 * Permite buscar cuando campo pendienteaprobacion es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isPendienteaprobacionIsNull() {
		return pendienteaprobacionIsNull;
	}

	/**
	 * Permite buscar cuando campo pendienteaprobacion es NULL
	 * 
	 * @param pendienteaprobacionIsNull
	 *            Valor de seteo.
	 */
	public void setPendienteaprobacionIsNull(boolean pendienteaprobacionIsNull) {
		this.pendienteaprobacionIsNull = pendienteaprobacionIsNull;
	}

	/**
	 * Permite buscar cuando campo pendienteaprobacion es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isPendienteaprobacionIsNotNull() {
		return pendienteaprobacionIsNotNull;
	}

	/**
	 * Permite buscar cuando campo pendienteaprobacion es NOT NULL
	 * 
	 * @param pendienteaprobacionIsNotNull
	 *            Valor de seteo.
	 */
	public void setPendienteaprobacionIsNotNull(boolean pendienteaprobacionIsNotNull) {
		this.pendienteaprobacionIsNotNull = pendienteaprobacionIsNotNull;
	}

	/**
	 * Valor de busqueda de campo nombreloteenvio
	 * 
	 * @return String.
	 */
	public String getNombreloteenvio() {
		if (nombreloteenvio != null) {
			switch (nombreloteenvioComparator) {
			case STARTS_WITH:
				return nombreloteenvio + "%";
			case CONTAINS:
				return "%" + nombreloteenvio + "%";
			case ENDS_WITH:
				return "%" + nombreloteenvio;
			case EQUALS:
				return nombreloteenvio;
			default:
				break;
			}
		}
		return nombreloteenvio;
	}

	/**
	 * Valor de busqueda de campo nombreloteenvio
	 * 
	 * @param nombreloteenvio
	 *            Valor de seteo.
	 */
	public void setNombreloteenvio(String nombreloteenvio) {
		this.nombreloteenvio = nombreloteenvio;
	}

	/**
	 * Tipo de comparador para la busqueda por campo nombreloteenvio
	 * 
	 * @return nombreloteenvioComparator.
	 */
	public TextComparator getNombreloteenvioComparator() {
		return nombreloteenvioComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo nombreloteenvio
	 * 
	 * @param nombreloteenvioComparator
	 *            Valor de seteo.
	 */
	public void setNombreloteenvioComparator(TextComparator nombreloteenvioComparator) {
		this.nombreloteenvioComparator = nombreloteenvioComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getNombreloteenvioIn() {
		return this.nombreloteenvioIn;
	}

	/**
	 * @param nombreloteenvio
	 *            Valor a agregar.
	 */
	public void addNombreloteenvioIn(String nombreloteenvio) {
		this.nombreloteenvioIn.add(nombreloteenvio);
	}

	/**
	 * Permite buscar cuando campo nombreloteenvio es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNombreloteenvioIsNull() {
		return nombreloteenvioIsNull;
	}

	/**
	 * Permite buscar cuando campo nombreloteenvio es NULL
	 * 
	 * @param nombreloteenvioIsNull
	 *            Valor de seteo.
	 */
	public void setNombreloteenvioIsNull(boolean nombreloteenvioIsNull) {
		this.nombreloteenvioIsNull = nombreloteenvioIsNull;
	}

	/**
	 * Permite buscar cuando campo nombreloteenvio es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isNombreloteenvioIsNotNull() {
		return nombreloteenvioIsNotNull;
	}

	/**
	 * Permite buscar cuando campo nombreloteenvio es NOT NULL
	 * 
	 * @param nombreloteenvioIsNotNull
	 *            Valor de seteo.
	 */
	public void setNombreloteenvioIsNotNull(boolean nombreloteenvioIsNotNull) {
		this.nombreloteenvioIsNotNull = nombreloteenvioIsNotNull;
	}

	/**
	 * Valor de busqueda de campo badge
	 * 
	 * @return Long.
	 */
	public Long getBadge() {
		return badge;
	}

	/**
	 * Valor de busqueda de campo badge
	 * 
	 * @param badge
	 *            Valor de seteo.
	 */
	public void setBadge(Long badge) {
		this.badge = badge;
	}

	/**
	 * @return List<Long>.
	 */
	public List<Long> getBadgeIn() {
		return this.badgeIn;
	}

	/**
	 * @param badge
	 *            Valor a agregar.
	 */
	public void addBadgeIn(Long badge) {
		this.badgeIn.add(badge);
	}

	/**
	 * Permite buscar cuando campo badge es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isBadgeIsNull() {
		return badgeIsNull;
	}

	/**
	 * Permite buscar cuando campo badge es NULL
	 * 
	 * @param badgeIsNull
	 *            Valor de seteo.
	 */
	public void setBadgeIsNull(boolean badgeIsNull) {
		this.badgeIsNull = badgeIsNull;
	}

	/**
	 * Permite buscar cuando campo badge es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isBadgeIsNotNull() {
		return badgeIsNotNull;
	}

	/**
	 * Permite buscar cuando campo badge es NOT NULL
	 * 
	 * @param badgeIsNotNull
	 *            Valor de seteo.
	 */
	public void setBadgeIsNotNull(boolean badgeIsNotNull) {
		this.badgeIsNotNull = badgeIsNotNull;
	}

	/**
	 * Valor de busqueda de campo fcmprojectkey
	 * 
	 * @return String.
	 */
	public String getFcmprojectkey() {
		if (fcmprojectkey != null) {
			switch (fcmprojectkeyComparator) {
			case STARTS_WITH:
				return fcmprojectkey + "%";
			case CONTAINS:
				return "%" + fcmprojectkey + "%";
			case ENDS_WITH:
				return "%" + fcmprojectkey;
			case EQUALS:
				return fcmprojectkey;
			default:
				break;
			}
		}
		return fcmprojectkey;
	}

	/**
	 * Valor de busqueda de campo fcmprojectkey
	 * 
	 * @param fcmprojectkey
	 *            Valor de seteo.
	 */
	public void setFcmprojectkey(String fcmprojectkey) {
		this.fcmprojectkey = fcmprojectkey;
	}

	/**
	 * Tipo de comparador para la busqueda por campo fcmprojectkey
	 * 
	 * @return fcmprojectkeyComparator.
	 */
	public TextComparator getFcmprojectkeyComparator() {
		return fcmprojectkeyComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo fcmprojectkey
	 * 
	 * @param fcmprojectkeyComparator
	 *            Valor de seteo.
	 */
	public void setFcmprojectkeyComparator(TextComparator fcmprojectkeyComparator) {
		this.fcmprojectkeyComparator = fcmprojectkeyComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getFcmprojectkeyIn() {
		return this.fcmprojectkeyIn;
	}

	/**
	 * @param fcmprojectkey
	 *            Valor a agregar.
	 */
	public void addFcmprojectkeyIn(String fcmprojectkey) {
		this.fcmprojectkeyIn.add(fcmprojectkey);
	}

	/**
	 * Permite buscar cuando campo fcmprojectkey es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFcmprojectkeyIsNull() {
		return fcmprojectkeyIsNull;
	}

	/**
	 * Permite buscar cuando campo fcmprojectkey es NULL
	 * 
	 * @param fcmprojectkeyIsNull
	 *            Valor de seteo.
	 */
	public void setFcmprojectkeyIsNull(boolean fcmprojectkeyIsNull) {
		this.fcmprojectkeyIsNull = fcmprojectkeyIsNull;
	}

	/**
	 * Permite buscar cuando campo fcmprojectkey es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isFcmprojectkeyIsNotNull() {
		return fcmprojectkeyIsNotNull;
	}

	/**
	 * Permite buscar cuando campo fcmprojectkey es NOT NULL
	 * 
	 * @param fcmprojectkeyIsNotNull
	 *            Valor de seteo.
	 */
	public void setFcmprojectkeyIsNotNull(boolean fcmprojectkeyIsNotNull) {
		this.fcmprojectkeyIsNotNull = fcmprojectkeyIsNotNull;
	}

	/**
	 * Valor de busqueda de campo apnsrutacertificado
	 * 
	 * @return String.
	 */
	public String getApnsrutacertificado() {
		if (apnsrutacertificado != null) {
			switch (apnsrutacertificadoComparator) {
			case STARTS_WITH:
				return apnsrutacertificado + "%";
			case CONTAINS:
				return "%" + apnsrutacertificado + "%";
			case ENDS_WITH:
				return "%" + apnsrutacertificado;
			case EQUALS:
				return apnsrutacertificado;
			default:
				break;
			}
		}
		return apnsrutacertificado;
	}

	/**
	 * Valor de busqueda de campo apnsrutacertificado
	 * 
	 * @param apnsrutacertificado
	 *            Valor de seteo.
	 */
	public void setApnsrutacertificado(String apnsrutacertificado) {
		this.apnsrutacertificado = apnsrutacertificado;
	}

	/**
	 * Tipo de comparador para la busqueda por campo apnsrutacertificado
	 * 
	 * @return apnsrutacertificadoComparator.
	 */
	public TextComparator getApnsrutacertificadoComparator() {
		return apnsrutacertificadoComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo apnsrutacertificado
	 * 
	 * @param apnsrutacertificadoComparator
	 *            Valor de seteo.
	 */
	public void setApnsrutacertificadoComparator(TextComparator apnsrutacertificadoComparator) {
		this.apnsrutacertificadoComparator = apnsrutacertificadoComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getApnsrutacertificadoIn() {
		return this.apnsrutacertificadoIn;
	}

	/**
	 * @param apnsrutacertificado
	 *            Valor a agregar.
	 */
	public void addApnsrutacertificadoIn(String apnsrutacertificado) {
		this.apnsrutacertificadoIn.add(apnsrutacertificado);
	}

	/**
	 * Permite buscar cuando campo apnsrutacertificado es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isApnsrutacertificadoIsNull() {
		return apnsrutacertificadoIsNull;
	}

	/**
	 * Permite buscar cuando campo apnsrutacertificado es NULL
	 * 
	 * @param apnsrutacertificadoIsNull
	 *            Valor de seteo.
	 */
	public void setApnsrutacertificadoIsNull(boolean apnsrutacertificadoIsNull) {
		this.apnsrutacertificadoIsNull = apnsrutacertificadoIsNull;
	}

	/**
	 * Permite buscar cuando campo apnsrutacertificado es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isApnsrutacertificadoIsNotNull() {
		return apnsrutacertificadoIsNotNull;
	}

	/**
	 * Permite buscar cuando campo apnsrutacertificado es NOT NULL
	 * 
	 * @param apnsrutacertificadoIsNotNull
	 *            Valor de seteo.
	 */
	public void setApnsrutacertificadoIsNotNull(boolean apnsrutacertificadoIsNotNull) {
		this.apnsrutacertificadoIsNotNull = apnsrutacertificadoIsNotNull;
	}

	/**
	 * Valor de busqueda de campo apnspasswordcertificado
	 * 
	 * @return String.
	 */
	public String getApnspasswordcertificado() {
		if (apnspasswordcertificado != null) {
			switch (apnspasswordcertificadoComparator) {
			case STARTS_WITH:
				return apnspasswordcertificado + "%";
			case CONTAINS:
				return "%" + apnspasswordcertificado + "%";
			case ENDS_WITH:
				return "%" + apnspasswordcertificado;
			case EQUALS:
				return apnspasswordcertificado;
			default:
				break;
			}
		}
		return apnspasswordcertificado;
	}

	/**
	 * Valor de busqueda de campo apnspasswordcertificado
	 * 
	 * @param apnspasswordcertificado
	 *            Valor de seteo.
	 */
	public void setApnspasswordcertificado(String apnspasswordcertificado) {
		this.apnspasswordcertificado = apnspasswordcertificado;
	}

	/**
	 * Tipo de comparador para la busqueda por campo apnspasswordcertificado
	 * 
	 * @return apnspasswordcertificadoComparator.
	 */
	public TextComparator getApnspasswordcertificadoComparator() {
		return apnspasswordcertificadoComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo apnspasswordcertificado
	 * 
	 * @param apnspasswordcertificadoComparator
	 *            Valor de seteo.
	 */
	public void setApnspasswordcertificadoComparator(TextComparator apnspasswordcertificadoComparator) {
		this.apnspasswordcertificadoComparator = apnspasswordcertificadoComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getApnspasswordcertificadoIn() {
		return this.apnspasswordcertificadoIn;
	}

	/**
	 * @param apnspasswordcertificado
	 *            Valor a agregar.
	 */
	public void addApnspasswordcertificadoIn(String apnspasswordcertificado) {
		this.apnspasswordcertificadoIn.add(apnspasswordcertificado);
	}

	/**
	 * Permite buscar cuando campo apnspasswordcertificado es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isApnspasswordcertificadoIsNull() {
		return apnspasswordcertificadoIsNull;
	}

	/**
	 * Permite buscar cuando campo apnspasswordcertificado es NULL
	 * 
	 * @param apnspasswordcertificadoIsNull
	 *            Valor de seteo.
	 */
	public void setApnspasswordcertificadoIsNull(boolean apnspasswordcertificadoIsNull) {
		this.apnspasswordcertificadoIsNull = apnspasswordcertificadoIsNull;
	}

	/**
	 * Permite buscar cuando campo apnspasswordcertificado es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isApnspasswordcertificadoIsNotNull() {
		return apnspasswordcertificadoIsNotNull;
	}

	/**
	 * Permite buscar cuando campo apnspasswordcertificado es NOT NULL
	 * 
	 * @param apnspasswordcertificadoIsNotNull
	 *            Valor de seteo.
	 */
	public void setApnspasswordcertificadoIsNotNull(boolean apnspasswordcertificadoIsNotNull) {
		this.apnspasswordcertificadoIsNotNull = apnspasswordcertificadoIsNotNull;
	}

	/**
	 * Valor de busqueda de campo androidplataforma
	 * 
	 * @return Boolean.
	 */
	public Boolean getAndroidplataforma() {
		return androidplataforma;
	}

	/**
	 * Valor de busqueda de campo androidplataforma
	 * 
	 * @param androidplataforma
	 *            Valor de seteo.
	 */
	public void setAndroidplataforma(Boolean androidplataforma) {
		this.androidplataforma = androidplataforma;
	}

	/**
	 * Permite buscar cuando campo androidplataforma es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAndroidplataformaIsNull() {
		return androidplataformaIsNull;
	}

	/**
	 * Permite buscar cuando campo androidplataforma es NULL
	 * 
	 * @param androidplataformaIsNull
	 *            Valor de seteo.
	 */
	public void setAndroidplataformaIsNull(boolean androidplataformaIsNull) {
		this.androidplataformaIsNull = androidplataformaIsNull;
	}

	/**
	 * Permite buscar cuando campo androidplataforma es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAndroidplataformaIsNotNull() {
		return androidplataformaIsNotNull;
	}

	/**
	 * Permite buscar cuando campo androidplataforma es NOT NULL
	 * 
	 * @param androidplataformaIsNotNull
	 *            Valor de seteo.
	 */
	public void setAndroidplataformaIsNotNull(boolean androidplataformaIsNotNull) {
		this.androidplataformaIsNotNull = androidplataformaIsNotNull;
	}

	/**
	 * Valor de busqueda de campo iosplataforma
	 * 
	 * @return Boolean.
	 */
	public Boolean getIosplataforma() {
		return iosplataforma;
	}

	/**
	 * Valor de busqueda de campo iosplataforma
	 * 
	 * @param iosplataforma
	 *            Valor de seteo.
	 */
	public void setIosplataforma(Boolean iosplataforma) {
		this.iosplataforma = iosplataforma;
	}

	/**
	 * Permite buscar cuando campo iosplataforma es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isIosplataformaIsNull() {
		return iosplataformaIsNull;
	}

	/**
	 * Permite buscar cuando campo iosplataforma es NULL
	 * 
	 * @param iosplataformaIsNull
	 *            Valor de seteo.
	 */
	public void setIosplataformaIsNull(boolean iosplataformaIsNull) {
		this.iosplataformaIsNull = iosplataformaIsNull;
	}

	/**
	 * Permite buscar cuando campo iosplataforma es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isIosplataformaIsNotNull() {
		return iosplataformaIsNotNull;
	}

	/**
	 * Permite buscar cuando campo iosplataforma es NOT NULL
	 * 
	 * @param iosplataformaIsNotNull
	 *            Valor de seteo.
	 */
	public void setIosplataformaIsNotNull(boolean iosplataformaIsNotNull) {
		this.iosplataformaIsNotNull = iosplataformaIsNotNull;
	}

	/**
	 * Valor de busqueda de campo endpoint
	 * 
	 * @return String.
	 */
	public String getEndpoint() {
		if (endpoint != null) {
			switch (endpointComparator) {
			case STARTS_WITH:
				return endpoint + "%";
			case CONTAINS:
				return "%" + endpoint + "%";
			case ENDS_WITH:
				return "%" + endpoint;
			case EQUALS:
				return endpoint;
			default:
				break;
			}
		}
		return endpoint;
	}

	/**
	 * Valor de busqueda de campo endpoint
	 * 
	 * @param endpoint
	 *            Valor de seteo.
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * Tipo de comparador para la busqueda por campo endpoint
	 * 
	 * @return endpointComparator.
	 */
	public TextComparator getEndpointComparator() {
		return endpointComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo endpoint
	 * 
	 * @param endpointComparator
	 *            Valor de seteo.
	 */
	public void setEndpointComparator(TextComparator endpointComparator) {
		this.endpointComparator = endpointComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getEndpointIn() {
		return this.endpointIn;
	}

	/**
	 * @param endpoint
	 *            Valor a agregar.
	 */
	public void addEndpointIn(String endpoint) {
		this.endpointIn.add(endpoint);
	}

	/**
	 * Permite buscar cuando campo endpoint es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isEndpointIsNull() {
		return endpointIsNull;
	}

	/**
	 * Permite buscar cuando campo endpoint es NULL
	 * 
	 * @param endpointIsNull
	 *            Valor de seteo.
	 */
	public void setEndpointIsNull(boolean endpointIsNull) {
		this.endpointIsNull = endpointIsNull;
	}

	/**
	 * Permite buscar cuando campo endpoint es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isEndpointIsNotNull() {
		return endpointIsNotNull;
	}

	/**
	 * Permite buscar cuando campo endpoint es NOT NULL
	 * 
	 * @param endpointIsNotNull
	 *            Valor de seteo.
	 */
	public void setEndpointIsNotNull(boolean endpointIsNotNull) {
		this.endpointIsNotNull = endpointIsNotNull;
	}

	/**
	 * Valor de busqueda de campo informesactivo
	 * 
	 * @return Boolean.
	 */
	public Boolean getInformesactivo() {
		return informesactivo;
	}

	/**
	 * Valor de busqueda de campo informesactivo
	 * 
	 * @param informesactivo
	 *            Valor de seteo.
	 */
	public void setInformesactivo(Boolean informesactivo) {
		this.informesactivo = informesactivo;
	}

	/**
	 * Permite buscar cuando campo informesactivo es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isInformesactivoIsNull() {
		return informesactivoIsNull;
	}

	/**
	 * Permite buscar cuando campo informesactivo es NULL
	 * 
	 * @param informesactivoIsNull
	 *            Valor de seteo.
	 */
	public void setInformesactivoIsNull(boolean informesactivoIsNull) {
		this.informesactivoIsNull = informesactivoIsNull;
	}

	/**
	 * Permite buscar cuando campo informesactivo es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isInformesactivoIsNotNull() {
		return informesactivoIsNotNull;
	}

	/**
	 * Permite buscar cuando campo informesactivo es NOT NULL
	 * 
	 * @param informesactivoIsNotNull
	 *            Valor de seteo.
	 */
	public void setInformesactivoIsNotNull(boolean informesactivoIsNotNull) {
		this.informesactivoIsNotNull = informesactivoIsNotNull;
	}

	/**
	 * Valor de busqueda de campo agrupacionestado
	 * 
	 * @return Boolean.
	 */
	public Boolean getAgrupacionestado() {
		return agrupacionestado;
	}

	/**
	 * Valor de busqueda de campo agrupacionestado
	 * 
	 * @param agrupacionestado
	 *            Valor de seteo.
	 */
	public void setAgrupacionestado(Boolean agrupacionestado) {
		this.agrupacionestado = agrupacionestado;
	}

	/**
	 * Permite buscar cuando campo agrupacionestado es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAgrupacionestadoIsNull() {
		return agrupacionestadoIsNull;
	}

	/**
	 * Permite buscar cuando campo agrupacionestado es NULL
	 * 
	 * @param agrupacionestadoIsNull
	 *            Valor de seteo.
	 */
	public void setAgrupacionestadoIsNull(boolean agrupacionestadoIsNull) {
		this.agrupacionestadoIsNull = agrupacionestadoIsNull;
	}

	/**
	 * Permite buscar cuando campo agrupacionestado es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAgrupacionestadoIsNotNull() {
		return agrupacionestadoIsNotNull;
	}

	/**
	 * Permite buscar cuando campo agrupacionestado es NOT NULL
	 * 
	 * @param agrupacionestadoIsNotNull
	 *            Valor de seteo.
	 */
	public void setAgrupacionestadoIsNotNull(boolean agrupacionestadoIsNotNull) {
		this.agrupacionestadoIsNotNull = agrupacionestadoIsNotNull;
	}

	/**
	 * Valor de busqueda de campo agrupacioncodorg
	 * 
	 * @return Boolean.
	 */
	public Boolean getAgrupacioncodorg() {
		return agrupacioncodorg;
	}

	/**
	 * Valor de busqueda de campo agrupacioncodorg
	 * 
	 * @param agrupacioncodorg
	 *            Valor de seteo.
	 */
	public void setAgrupacioncodorg(Boolean agrupacioncodorg) {
		this.agrupacioncodorg = agrupacioncodorg;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodorg es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAgrupacioncodorgIsNull() {
		return agrupacioncodorgIsNull;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodorg es NULL
	 * 
	 * @param agrupacioncodorgIsNull
	 *            Valor de seteo.
	 */
	public void setAgrupacioncodorgIsNull(boolean agrupacioncodorgIsNull) {
		this.agrupacioncodorgIsNull = agrupacioncodorgIsNull;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodorg es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAgrupacioncodorgIsNotNull() {
		return agrupacioncodorgIsNotNull;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodorg es NOT NULL
	 * 
	 * @param agrupacioncodorgIsNotNull
	 *            Valor de seteo.
	 */
	public void setAgrupacioncodorgIsNotNull(boolean agrupacioncodorgIsNotNull) {
		this.agrupacioncodorgIsNotNull = agrupacioncodorgIsNotNull;
	}

	/**
	 * Valor de busqueda de campo agrupacioncodsia
	 * 
	 * @return Boolean.
	 */
	public Boolean getAgrupacioncodsia() {
		return agrupacioncodsia;
	}

	/**
	 * Valor de busqueda de campo agrupacioncodsia
	 * 
	 * @param agrupacioncodsia
	 *            Valor de seteo.
	 */
	public void setAgrupacioncodsia(Boolean agrupacioncodsia) {
		this.agrupacioncodsia = agrupacioncodsia;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodsia es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAgrupacioncodsiaIsNull() {
		return agrupacioncodsiaIsNull;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodsia es NULL
	 * 
	 * @param agrupacioncodsiaIsNull
	 *            Valor de seteo.
	 */
	public void setAgrupacioncodsiaIsNull(boolean agrupacioncodsiaIsNull) {
		this.agrupacioncodsiaIsNull = agrupacioncodsiaIsNull;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodsia es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAgrupacioncodsiaIsNotNull() {
		return agrupacioncodsiaIsNotNull;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodsia es NOT NULL
	 * 
	 * @param agrupacioncodsiaIsNotNull
	 *            Valor de seteo.
	 */
	public void setAgrupacioncodsiaIsNotNull(boolean agrupacioncodsiaIsNotNull) {
		this.agrupacioncodsiaIsNotNull = agrupacioncodsiaIsNotNull;
	}

	/**
	 * Valor de busqueda de campo agrupacioncodorgpagador
	 * 
	 * @return Boolean.
	 */
	public Boolean getAgrupacioncodorgpagador() {
		return agrupacioncodorgpagador;
	}

	/**
	 * Valor de busqueda de campo agrupacioncodorgpagador
	 * 
	 * @param agrupacioncodorgpagador
	 *            Valor de seteo.
	 */
	public void setAgrupacioncodorgpagador(Boolean agrupacioncodorgpagador) {
		this.agrupacioncodorgpagador = agrupacioncodorgpagador;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodorgpagador es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAgrupacioncodorgpagadorIsNull() {
		return agrupacioncodorgpagadorIsNull;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodorgpagador es NULL
	 * 
	 * @param agrupacioncodorgpagadorIsNull
	 *            Valor de seteo.
	 */
	public void setAgrupacioncodorgpagadorIsNull(boolean agrupacioncodorgpagadorIsNull) {
		this.agrupacioncodorgpagadorIsNull = agrupacioncodorgpagadorIsNull;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodorgpagador es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isAgrupacioncodorgpagadorIsNotNull() {
		return agrupacioncodorgpagadorIsNotNull;
	}

	/**
	 * Permite buscar cuando campo agrupacioncodorgpagador es NOT NULL
	 * 
	 * @param agrupacioncodorgpagadorIsNotNull
	 *            Valor de seteo.
	 */
	public void setAgrupacioncodorgpagadorIsNotNull(boolean agrupacioncodorgpagadorIsNotNull) {
		this.agrupacioncodorgpagadorIsNotNull = agrupacioncodorgpagadorIsNotNull;
	}

	/**
	 * Valor de busqueda de campo informesdestinatarios
	 * 
	 * @return String.
	 */
	public String getInformesdestinatarios() {
		if (informesdestinatarios != null) {
			switch (informesdestinatariosComparator) {
			case STARTS_WITH:
				return informesdestinatarios + "%";
			case CONTAINS:
				return "%" + informesdestinatarios + "%";
			case ENDS_WITH:
				return "%" + informesdestinatarios;
			case EQUALS:
				return informesdestinatarios;
			default:
				break;
			}
		}
		return informesdestinatarios;
	}

	/**
	 * Valor de busqueda de campo informesdestinatarios
	 * 
	 * @param informesdestinatarios
	 *            Valor de seteo.
	 */
	public void setInformesdestinatarios(String informesdestinatarios) {
		this.informesdestinatarios = informesdestinatarios;
	}

	/**
	 * Tipo de comparador para la busqueda por campo informesdestinatarios
	 * 
	 * @return informesdestinatariosComparator.
	 */
	public TextComparator getInformesdestinatariosComparator() {
		return informesdestinatariosComparator;
	}

	/**
	 * Tipo de comparador para la busqueda por campo informesdestinatarios
	 * 
	 * @param informesdestinatariosComparator
	 *            Valor de seteo.
	 */
	public void setInformesdestinatariosComparator(TextComparator informesdestinatariosComparator) {
		this.informesdestinatariosComparator = informesdestinatariosComparator;
	}

	/**
	 * @return List<String>.
	 */
	public List<String> getInformesdestinatariosIn() {
		return this.informesdestinatariosIn;
	}

	/**
	 * @param informesdestinatarios
	 *            Valor a agregar.
	 */
	public void addInformesdestinatariosIn(String informesdestinatarios) {
		this.informesdestinatariosIn.add(informesdestinatarios);
	}

	/**
	 * Permite buscar cuando campo informesdestinatarios es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isInformesdestinatariosIsNull() {
		return informesdestinatariosIsNull;
	}

	/**
	 * Permite buscar cuando campo informesdestinatarios es NULL
	 * 
	 * @param informesdestinatariosIsNull
	 *            Valor de seteo.
	 */
	public void setInformesdestinatariosIsNull(boolean informesdestinatariosIsNull) {
		this.informesdestinatariosIsNull = informesdestinatariosIsNull;
	}

	/**
	 * Permite buscar cuando campo informesdestinatarios es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isInformesdestinatariosIsNotNull() {
		return informesdestinatariosIsNotNull;
	}

	/**
	 * Permite buscar cuando campo informesdestinatarios es NOT NULL
	 * 
	 * @param informesdestinatariosIsNotNull
	 *            Valor de seteo.
	 */
	public void setInformesdestinatariosIsNotNull(boolean informesdestinatariosIsNotNull) {
		this.informesdestinatariosIsNotNull = informesdestinatariosIsNotNull;
	}

	/**
	 * Valor de busqueda de campo premium
	 * 
	 * @return Boolean.
	 */
	public Boolean getPremium() {
		return premium;
	}

	/**
	 * Valor de busqueda de campo premium
	 * 
	 * @param premium
	 *            Valor de seteo.
	 */
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	/**
	 * Permite buscar cuando campo premium es NULL
	 * 
	 * @return boolean.
	 */
	public boolean isPremiumIsNull() {
		return premiumIsNull;
	}

	/**
	 * Permite buscar cuando campo premium es NULL
	 * 
	 * @param premiumIsNull
	 *            Valor de seteo.
	 */
	public void setPremiumIsNull(boolean premiumIsNull) {
		this.premiumIsNull = premiumIsNull;
	}

	/**
	 * Permite buscar cuando campo premium es NOT NULL
	 * 
	 * @return boolean.
	 */
	public boolean isPremiumIsNotNull() {
		return premiumIsNotNull;
	}

	/**
	 * Permite buscar cuando campo premium es NOT NULL
	 * 
	 * @param premiumIsNotNull
	 *            Valor de seteo.
	 */
	public void setPremiumIsNotNull(boolean premiumIsNotNull) {
		this.premiumIsNotNull = premiumIsNotNull;
	}

	/**
	 * Agrega recursivamente criterios al Criteria de Hibernate para la
	 * utilizacion en busquedas
	 */
	public void addCriteria(Criteria criteria, boolean useOrder) {

		if (getServicioid() != null) {
			criteria.add(Restrictions.eq(SERVICIOID, getServicioid()));
		}

		if (getServicioidIn().size() > 0) {
			criteria.add(Restrictions.in(SERVICIOID, getServicioidIn()));
		}

		if (isServicioidIsNull()) {
			criteria.add(Restrictions.isNull(SERVICIOID));
		}

		if (isServicioidIsNotNull()) {
			criteria.add(Restrictions.isNotNull(SERVICIOID));
		}

		if (getNombre() != null) {
			if (getNombreComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(NOMBRE, getNombre()));
			} else if (getNombreComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(NOMBRE, getNombre()));
			} else if (getNombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRE, getNombre())));
			} else {
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

		if (getDescripcion() != null) {
			if (getDescripcionComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(DESCRIPCION, getDescripcion()));
			} else if (getDescripcionComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(DESCRIPCION, getDescripcion()));
			} else if (getDescripcionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DESCRIPCION, getDescripcion())));
			} else {
				criteria.add(Restrictions.like(DESCRIPCION, getDescripcion()));
			}
		}

		if (getDescripcionIn().size() > 0) {
			criteria.add(Restrictions.in(DESCRIPCION, getDescripcionIn()));
		}

		if (isDescripcionIsNull()) {
			criteria.add(Restrictions.isNull(DESCRIPCION));
		}

		if (isDescripcionIsNotNull()) {
			criteria.add(Restrictions.isNotNull(DESCRIPCION));
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

		if (getCanalid() != null) {
			criteria.add(Restrictions.eq(CANALID, getCanalid()));
		}

		if (getCanalidIn().size() > 0) {
			criteria.add(Restrictions.in(CANALID, getCanalidIn()));
		}

		if (isCanalidIsNull()) {
			criteria.add(Restrictions.isNull(CANALID));
		}

		if (isCanalidIsNotNull()) {
			criteria.add(Restrictions.isNotNull(CANALID));
		}

		if (getAplicacionid() != null) {
			criteria.add(Restrictions.eq(APLICACIONID, getAplicacionid()));
		}

		if (getAplicacionidIn().size() > 0) {
			criteria.add(Restrictions.in(APLICACIONID, getAplicacionidIn()));
		}

		if (isAplicacionidIsNull()) {
			criteria.add(Restrictions.isNull(APLICACIONID));
		}

		if (isAplicacionidIsNotNull()) {
			criteria.add(Restrictions.isNotNull(APLICACIONID));
		}

		if (getFechacreacionMin() != null) {
			criteria.add(Restrictions.ge(FECHACREACION, getFechacreacionMin()));
		}

		if (getFechacreacionMax() != null) {
			criteria.add(Restrictions.le(FECHACREACION, getFechacreacionMax()));
		}

		if (isFechacreacionIsNull()) {
			criteria.add(Restrictions.isNull(FECHACREACION));
		}

		if (isFechacreacionIsNotNull()) {
			criteria.add(Restrictions.isNotNull(FECHACREACION));
		}

		if (getCreadopor() != null) {
			if (getCreadoporComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(CREADOPOR, getCreadopor()));
			} else if (getCreadoporComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(CREADOPOR, getCreadopor()));
			} else if (getCreadoporComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CREADOPOR, getCreadopor())));
			} else {
				criteria.add(Restrictions.like(CREADOPOR, getCreadopor()));
			}
		}

		if (getCreadoporIn().size() > 0) {
			criteria.add(Restrictions.in(CREADOPOR, getCreadoporIn()));
		}

		if (isCreadoporIsNull()) {
			criteria.add(Restrictions.isNull(CREADOPOR));
		}

		if (isCreadoporIsNotNull()) {
			criteria.add(Restrictions.isNotNull(CREADOPOR));
		}

		if (getFechamodificacionMin() != null) {
			criteria.add(Restrictions.ge(FECHAMODIFICACION, getFechamodificacionMin()));
		}

		if (getFechamodificacionMax() != null) {
			criteria.add(Restrictions.le(FECHAMODIFICACION, getFechamodificacionMax()));
		}

		if (isFechamodificacionIsNull()) {
			criteria.add(Restrictions.isNull(FECHAMODIFICACION));
		}

		if (isFechamodificacionIsNotNull()) {
			criteria.add(Restrictions.isNotNull(FECHAMODIFICACION));
		}

		if (getModificadopor() != null) {
			if (getModificadoporComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(MODIFICADOPOR, getModificadopor()));
			} else if (getModificadoporComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(MODIFICADOPOR, getModificadopor()));
			} else if (getModificadoporComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MODIFICADOPOR, getModificadopor())));
			} else {
				criteria.add(Restrictions.like(MODIFICADOPOR, getModificadopor()));
			}
		}

		if (getModificadoporIn().size() > 0) {
			criteria.add(Restrictions.in(MODIFICADOPOR, getModificadoporIn()));
		}

		if (isModificadoporIsNull()) {
			criteria.add(Restrictions.isNull(MODIFICADOPOR));
		}

		if (isModificadoporIsNotNull()) {
			criteria.add(Restrictions.isNotNull(MODIFICADOPOR));
		}

		if (getNmaxenvios() != null) {
			criteria.add(Restrictions.eq(NMAXENVIOS, getNmaxenvios()));
		}

		if (getNmaxenviosIn().size() > 0) {
			criteria.add(Restrictions.in(NMAXENVIOS, getNmaxenviosIn()));
		}

		if (isNmaxenviosIsNull()) {
			criteria.add(Restrictions.isNull(NMAXENVIOS));
		}

		if (isNmaxenviosIsNotNull()) {
			criteria.add(Restrictions.isNotNull(NMAXENVIOS));
		}

		if (getHeadersms() != null) {
			if (getHeadersmsComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(HEADERSMS, getHeadersms()));
			} else if (getHeadersmsComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(HEADERSMS, getHeadersms()));
			} else if (getHeadersmsComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(HEADERSMS, getHeadersms())));
			} else {
				criteria.add(Restrictions.like(HEADERSMS, getHeadersms()));
			}
		}

		if (getHeadersmsIn().size() > 0) {
			criteria.add(Restrictions.in(HEADERSMS, getHeadersmsIn()));
		}

		if (isHeadersmsIsNull()) {
			criteria.add(Restrictions.isNull(HEADERSMS));
		}

		if (isHeadersmsIsNotNull()) {
			criteria.add(Restrictions.isNotNull(HEADERSMS));
		}

		if (getCanalnombre() != null) {
			if (getCanalnombreComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(CANALNOMBRE, getCanalnombre()));
			} else if (getCanalnombreComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(CANALNOMBRE, getCanalnombre()));
			} else if (getCanalnombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CANALNOMBRE, getCanalnombre())));
			} else {
				criteria.add(Restrictions.like(CANALNOMBRE, getCanalnombre()));
			}
		}

		if (getCanalnombreIn().size() > 0) {
			criteria.add(Restrictions.in(CANALNOMBRE, getCanalnombreIn()));
		}

		if (isCanalnombreIsNull()) {
			criteria.add(Restrictions.isNull(CANALNOMBRE));
		}

		if (isCanalnombreIsNotNull()) {
			criteria.add(Restrictions.isNotNull(CANALNOMBRE));
		}

		if (getAplicacionnombre() != null) {
			if (getAplicacionnombreComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(APLICACIONNOMBRE, getAplicacionnombre()));
			} else if (getAplicacionnombreComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(APLICACIONNOMBRE, getAplicacionnombre()));
			} else if (getAplicacionnombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APLICACIONNOMBRE,
						getAplicacionnombre())));
			} else {
				criteria.add(Restrictions.like(APLICACIONNOMBRE, getAplicacionnombre()));
			}
		}

		if (getAplicacionnombreIn().size() > 0) {
			criteria.add(Restrictions.in(APLICACIONNOMBRE, getAplicacionnombreIn()));
		}

		if (isAplicacionnombreIsNull()) {
			criteria.add(Restrictions.isNull(APLICACIONNOMBRE));
		}

		if (isAplicacionnombreIsNotNull()) {
			criteria.add(Restrictions.isNotNull(APLICACIONNOMBRE));
		}

		if (getEliminado() != null) {
			if (getEliminadoComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(ELIMINADO, getEliminado()));
			} else if (getEliminadoComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(ELIMINADO, getEliminado()));
			} else if (getEliminadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ELIMINADO, getEliminado())));
			} else {
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

		if (getCuentaenvio() != null) {
			if (getCuentaenvioComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(CUENTAENVIO, getCuentaenvio()));
			} else if (getCuentaenvioComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(CUENTAENVIO, getCuentaenvio()));
			} else if (getCuentaenvioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CUENTAENVIO, getCuentaenvio())));
			} else {
				criteria.add(Restrictions.like(CUENTAENVIO, getCuentaenvio()));
			}
		}

		if (getCuentaenvioIn().size() > 0) {
			criteria.add(Restrictions.in(CUENTAENVIO, getCuentaenvioIn()));
		}

		if (isCuentaenvioIsNull()) {
			criteria.add(Restrictions.isNull(CUENTAENVIO));
		}

		if (isCuentaenvioIsNotNull()) {
			criteria.add(Restrictions.isNotNull(CUENTAENVIO));
		}

		if (getNombrecuentaenvio() != null) {
			if (getNombrecuentaenvioComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(NOMBRECUENTAENVIO, getNombrecuentaenvio()));
			} else if (getNombrecuentaenvioComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(NOMBRECUENTAENVIO, getNombrecuentaenvio()));
			} else if (getNombrecuentaenvioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRECUENTAENVIO,
						getNombrecuentaenvio())));
			} else {
				criteria.add(Restrictions.like(NOMBRECUENTAENVIO, getNombrecuentaenvio()));
			}
		}

		if (getNombrecuentaenvioIn().size() > 0) {
			criteria.add(Restrictions.in(NOMBRECUENTAENVIO, getNombrecuentaenvioIn()));
		}

		if (isNombrecuentaenvioIsNull()) {
			criteria.add(Restrictions.isNull(NOMBRECUENTAENVIO));
		}

		if (isNombrecuentaenvioIsNotNull()) {
			criteria.add(Restrictions.isNotNull(NOMBRECUENTAENVIO));
		}

		if (getExternalid() != null) {
			if (getExternalidComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(EXTERNALID, getExternalid()));
			} else if (getExternalidComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(EXTERNALID, getExternalid()));
			} else if (getExternalidComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(EXTERNALID, getExternalid())));
			} else {
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

		if (getHistorificacion() != null) {
			criteria.add(Restrictions.eq(HISTORIFICACION, getHistorificacion()));
		}

		if (getHistorificacionIn().size() > 0) {
			criteria.add(Restrictions.in(HISTORIFICACION, getHistorificacionIn()));
		}

		if (isHistorificacionIsNull()) {
			criteria.add(Restrictions.isNull(HISTORIFICACION));
		}

		if (isHistorificacionIsNotNull()) {
			criteria.add(Restrictions.isNotNull(HISTORIFICACION));
		}

		if (getConservacion() != null) {
			criteria.add(Restrictions.eq(CONSERVACION, getConservacion()));
		}

		if (getConservacionIn().size() > 0) {
			criteria.add(Restrictions.in(CONSERVACION, getConservacionIn()));
		}

		if (isConservacionIsNull()) {
			criteria.add(Restrictions.isNull(CONSERVACION));
		}

		if (isConservacionIsNotNull()) {
			criteria.add(Restrictions.isNotNull(CONSERVACION));
		}

		if (getPendienteaprobacion() != null) {
			criteria.add(Restrictions.eq(PENDIENTEAPROBACION, getPendienteaprobacion()));
		}

		if (isPendienteaprobacionIsNull()) {
			criteria.add(Restrictions.isNull(PENDIENTEAPROBACION));
		}

		if (isPendienteaprobacionIsNotNull()) {
			criteria.add(Restrictions.isNotNull(PENDIENTEAPROBACION));
		}

		if (getNombreloteenvio() != null) {
			if (getNombreloteenvioComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(NOMBRELOTEENVIO, getNombreloteenvio()));
			} else if (getNombreloteenvioComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(NOMBRELOTEENVIO, getNombreloteenvio()));
			} else if (getNombreloteenvioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions
						.sqlRestriction(createUpperTranslateSQL(NOMBRELOTEENVIO, getNombreloteenvio())));
			} else {
				criteria.add(Restrictions.like(NOMBRELOTEENVIO, getNombreloteenvio()));
			}
		}

		if (getNombreloteenvioIn().size() > 0) {
			criteria.add(Restrictions.in(NOMBRELOTEENVIO, getNombreloteenvioIn()));
		}

		if (isNombreloteenvioIsNull()) {
			criteria.add(Restrictions.isNull(NOMBRELOTEENVIO));
		}

		if (isNombreloteenvioIsNotNull()) {
			criteria.add(Restrictions.isNotNull(NOMBRELOTEENVIO));
		}

		if (getBadge() != null) {
			criteria.add(Restrictions.eq(BADGE, getBadge()));
		}

		if (getBadgeIn().size() > 0) {
			criteria.add(Restrictions.in(BADGE, getBadgeIn()));
		}

		if (isBadgeIsNull()) {
			criteria.add(Restrictions.isNull(BADGE));
		}

		if (isBadgeIsNotNull()) {
			criteria.add(Restrictions.isNotNull(BADGE));
		}

		if (getFcmprojectkey() != null) {
			if (getFcmprojectkeyComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(FCMPROJECTKEY, getFcmprojectkey()));
			} else if (getFcmprojectkeyComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(FCMPROJECTKEY, getFcmprojectkey()));
			} else if (getFcmprojectkeyComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(FCMPROJECTKEY, getFcmprojectkey())));
			} else {
				criteria.add(Restrictions.like(FCMPROJECTKEY, getFcmprojectkey()));
			}
		}

		if (getFcmprojectkeyIn().size() > 0) {
			criteria.add(Restrictions.in(FCMPROJECTKEY, getFcmprojectkeyIn()));
		}

		if (isFcmprojectkeyIsNull()) {
			criteria.add(Restrictions.isNull(FCMPROJECTKEY));
		}

		if (isFcmprojectkeyIsNotNull()) {
			criteria.add(Restrictions.isNotNull(FCMPROJECTKEY));
		}

		if (getApnsrutacertificado() != null) {
			if (getApnsrutacertificadoComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(APNSRUTACERTIFICADO, getApnsrutacertificado()));
			} else if (getApnsrutacertificadoComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(APNSRUTACERTIFICADO, getApnsrutacertificado()));
			} else if (getApnsrutacertificadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APNSRUTACERTIFICADO,
						getApnsrutacertificado())));
			} else {
				criteria.add(Restrictions.like(APNSRUTACERTIFICADO, getApnsrutacertificado()));
			}
		}

		if (getApnsrutacertificadoIn().size() > 0) {
			criteria.add(Restrictions.in(APNSRUTACERTIFICADO, getApnsrutacertificadoIn()));
		}

		if (isApnsrutacertificadoIsNull()) {
			criteria.add(Restrictions.isNull(APNSRUTACERTIFICADO));
		}

		if (isApnsrutacertificadoIsNotNull()) {
			criteria.add(Restrictions.isNotNull(APNSRUTACERTIFICADO));
		}

		if (getApnspasswordcertificado() != null) {
			if (getApnspasswordcertificadoComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(APNSPASSWORDCERTIFICADO, getApnspasswordcertificado()));
			} else if (getApnspasswordcertificadoComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(APNSPASSWORDCERTIFICADO, getApnspasswordcertificado()));
			} else if (getApnspasswordcertificadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APNSPASSWORDCERTIFICADO,
						getApnspasswordcertificado())));
			} else {
				criteria.add(Restrictions.like(APNSPASSWORDCERTIFICADO, getApnspasswordcertificado()));
			}
		}

		if (getApnspasswordcertificadoIn().size() > 0) {
			criteria.add(Restrictions.in(APNSPASSWORDCERTIFICADO, getApnspasswordcertificadoIn()));
		}

		if (isApnspasswordcertificadoIsNull()) {
			criteria.add(Restrictions.isNull(APNSPASSWORDCERTIFICADO));
		}

		if (isApnspasswordcertificadoIsNotNull()) {
			criteria.add(Restrictions.isNotNull(APNSPASSWORDCERTIFICADO));
		}

		if (getAndroidplataforma() != null) {
			criteria.add(Restrictions.eq(ANDROIDPLATAFORMA, getAndroidplataforma()));
		}

		if (isAndroidplataformaIsNull()) {
			criteria.add(Restrictions.isNull(ANDROIDPLATAFORMA));
		}

		if (isAndroidplataformaIsNotNull()) {
			criteria.add(Restrictions.isNotNull(ANDROIDPLATAFORMA));
		}

		if (getIosplataforma() != null) {
			criteria.add(Restrictions.eq(IOSPLATAFORMA, getIosplataforma()));
		}

		if (isIosplataformaIsNull()) {
			criteria.add(Restrictions.isNull(IOSPLATAFORMA));
		}

		if (isIosplataformaIsNotNull()) {
			criteria.add(Restrictions.isNotNull(IOSPLATAFORMA));
		}

		if (getEndpoint() != null) {
			if (getEndpointComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(ENDPOINT, getEndpoint()));
			} else if (getEndpointComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(ENDPOINT, getEndpoint()));
			} else if (getEndpointComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ENDPOINT, getEndpoint())));
			} else {
				criteria.add(Restrictions.like(ENDPOINT, getEndpoint()));
			}
		}

		if (getEndpointIn().size() > 0) {
			criteria.add(Restrictions.in(ENDPOINT, getEndpointIn()));
		}

		if (isEndpointIsNull()) {
			criteria.add(Restrictions.isNull(ENDPOINT));
		}

		if (isEndpointIsNotNull()) {
			criteria.add(Restrictions.isNotNull(ENDPOINT));
		}

		if (getInformesactivo() != null) {
			criteria.add(Restrictions.eq(INFORMESACTIVO, getInformesactivo()));
		}

		if (isInformesactivoIsNull()) {
			criteria.add(Restrictions.isNull(INFORMESACTIVO));
		}

		if (isInformesactivoIsNotNull()) {
			criteria.add(Restrictions.isNotNull(INFORMESACTIVO));
		}

		if (getAgrupacionestado() != null) {
			criteria.add(Restrictions.eq(AGRUPACIONESTADO, getAgrupacionestado()));
		}

		if (isAgrupacionestadoIsNull()) {
			criteria.add(Restrictions.isNull(AGRUPACIONESTADO));
		}

		if (isAgrupacionestadoIsNotNull()) {
			criteria.add(Restrictions.isNotNull(AGRUPACIONESTADO));
		}

		if (getAgrupacioncodorg() != null) {
			criteria.add(Restrictions.eq(AGRUPACIONCODORG, getAgrupacioncodorg()));
		}

		if (isAgrupacioncodorgIsNull()) {
			criteria.add(Restrictions.isNull(AGRUPACIONCODORG));
		}

		if (isAgrupacioncodorgIsNotNull()) {
			criteria.add(Restrictions.isNotNull(AGRUPACIONCODORG));
		}

		if (getAgrupacioncodsia() != null) {
			criteria.add(Restrictions.eq(AGRUPACIONCODSIA, getAgrupacioncodsia()));
		}

		if (isAgrupacioncodsiaIsNull()) {
			criteria.add(Restrictions.isNull(AGRUPACIONCODSIA));
		}

		if (isAgrupacioncodsiaIsNotNull()) {
			criteria.add(Restrictions.isNotNull(AGRUPACIONCODSIA));
		}

		if (getAgrupacioncodorgpagador() != null) {
			criteria.add(Restrictions.eq(AGRUPACIONCODORGPAGADOR, getAgrupacioncodorgpagador()));
		}

		if (isAgrupacioncodorgpagadorIsNull()) {
			criteria.add(Restrictions.isNull(AGRUPACIONCODORGPAGADOR));
		}

		if (isAgrupacioncodorgpagadorIsNotNull()) {
			criteria.add(Restrictions.isNotNull(AGRUPACIONCODORGPAGADOR));
		}

		if (getInformesdestinatarios() != null) {
			if (getInformesdestinatariosComparator() == TextComparator.EQUALS) {
				criteria.add(Restrictions.eq(INFORMESDESTINATARIOS, getInformesdestinatarios()));
			} else if (getInformesdestinatariosComparator() == TextComparator.ILIKE) {
				criteria.add(Restrictions.ilike(INFORMESDESTINATARIOS, getInformesdestinatarios()));
			} else if (getInformesdestinatariosComparator() == TextComparator.UPPERCASE_TRANSLATE) {
				criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(INFORMESDESTINATARIOS,
						getInformesdestinatarios())));
			} else {
				criteria.add(Restrictions.like(INFORMESDESTINATARIOS, getInformesdestinatarios()));
			}
		}

		if (getInformesdestinatariosIn().size() > 0) {
			criteria.add(Restrictions.in(INFORMESDESTINATARIOS, getInformesdestinatariosIn()));
		}

		if (isInformesdestinatariosIsNull()) {
			criteria.add(Restrictions.isNull(INFORMESDESTINATARIOS));
		}

		if (isInformesdestinatariosIsNotNull()) {
			criteria.add(Restrictions.isNotNull(INFORMESDESTINATARIOS));
		}

		if (getPremium() != null) {
			criteria.add(Restrictions.eq(PREMIUM, getPremium()));
		}

		if (isPremiumIsNull()) {
			criteria.add(Restrictions.isNull(PREMIUM));
		}

		if (isPremiumIsNotNull()) {
			criteria.add(Restrictions.isNotNull(PREMIUM));
		}
		
		if(null != maxResultados && maxResultados > 0){
			criteria.setMaxResults(maxResultados);
		}
		
		if (null != primerResultado && primerResultado > 0){
			criteria.setFirstResult(primerResultado);
		}
		// Aplica ordenamiento solo si corresponde. En count y searchUnique no
		// se utiliza.
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
	 * Crea la sentencia SQL para las busquedas de tipo
	 * TextComparator.UPPERCASE_TRANSLATE
	 */
	private String createUpperTranslateSQL(String column, String value) {
		return "UPPER(TRANSLATE(" + columnHQLToSQL(column) + ",'" + UPPER_TRANSLATE_FROM + "','" + UPPER_TRANSLATE_TO
				+ "')) LIKE '%" + normalizeParam(value).toUpperCase() + "%'";
	}

	/**
	 * Convierte el nombrado de una columna en formato HQL a formato SQL
	 */
	private String columnHQLToSQL(String column) {
		StringBuilder columnSQL = new StringBuilder();
		for (int i = 0; i < column.length(); i++) {
			if (Character.isUpperCase(column.charAt(i))) {
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
	private String normalizeParam(String param) {
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
}
