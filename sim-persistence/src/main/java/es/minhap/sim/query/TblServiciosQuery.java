/*
 *
 * archivo: TblServiciosQuery.java
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
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.persistence.hibernate.entity.AbstractHibernateQueryEntity;
import es.minhap.common.util.BeanFormatter;
import es.minhap.common.util.DateUtil;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblCanales;
import es.minhap.sim.model.TblServicios;

/**
 * Clase con criterios de busqueda para la entidad TblServicios
 */
public class TblServiciosQuery extends AbstractHibernateQueryEntity<TblServicios> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String SERVICIOID = "servicioid";
    public static final String TBLAPLICACIONES = "tblAplicaciones";
    public static final String TBLCANALES = "tblCanales";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String ACTIVO = "activo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String EXTERNALID = "externalid";
    public static final String NMAXENVIOS = "nmaxenvios";
    public static final String HEADERSMS = "headersms";
    public static final String ELIMINADO = "eliminado";
    public static final String CUENTAENVIO = "cuentaenvio";
    public static final String NOMBRECUENTAENVIO = "nombrecuentaenvio";
    public static final String HISTORIFICACION = "historificacion";
    public static final String MOTIVOHISTORIFICACION = "motivohistorificacion";
    public static final String CONSERVACION = "conservacion";
    public static final String MOTIVOCONSERVACION = "motivoconservacion";
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
    public static final String RESPFUNCIONALNOMBRE = "respFuncionalNombre";
    public static final String RESPFUNCIONALEMAIL = "respFuncionalEmail";
    public static final String RESPTECNICONOMBRE = "respTecnicoNombre";
    public static final String RESPTECNICOEMAIL = "respTecnicoEmail";
    public static final String PROVEEDORUSUARIOSMS = "proveedorusuariosms";
    public static final String PROVEEDORPASSWORDSMS = "proveedorpasswordsms";
    public static final String MULTIORGANISMO = "multiorganismo";
    public static final String PREMIUM = "premium";
    public static final String EXCLUSIVO = "exclusivo";
    public static final String NMAXREENVIOS = "numeroMaxReenvios";
    public static final String NUMEROMAXREENVIOS = "numeroMaxReenvios";
    public static final String REINTENTOSCONSULTAESTADO = "reintentosConsultaEStado";
    public static final String CADUCIDAD = "caducidad";
    public static final String CADUCIDADWEBPUSH = "caducidadWebPush";
    public static final String VAPIDPUBLICKEY = "vapidPublicKey";
    public static final String VAPIDPRIVATEKEY = "vapidPrivateKey";
    /**
     * Valor de busqueda de campo servicioid
     */
    private Long servicioid;

    /**
     * Lista de valores del campo servicioid para busquedas tipo IN
     */
    private List<Long> servicioidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo tblAplicaciones
     */
    private TblAplicacionesQuery tblAplicaciones;

    /**
     * Lista de valores del ID del campo tblAplicaciones para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblAplicaciones
     */
    private List<TblAplicaciones> tblAplicacionesIdIn = new ArrayList<TblAplicaciones>(0);

    /**
     * Permite buscar cuando campo tblAplicaciones es NULL
     */
    private boolean tblAplicacionesIsNull = false;

    /**
     * Permite buscar cuando campo tblAplicaciones es NOT NULL
     */
    private boolean tblAplicacionesIsNotNull = false;

    /**
     * Valor de busqueda de campo tblCanales
     */
    private TblCanalesQuery tblCanales;

    /**
     * Lista de valores del ID del campo tblCanales para busquedas tipo IN
     * Solo se consideran los ID dentro de los TblCanales
     */
    private List<TblCanales> tblCanalesIdIn = new ArrayList<TblCanales>(0);

    /**
     * Permite buscar cuando campo tblCanales es NULL
     */
    private boolean tblCanalesIsNull = false;

    /**
     * Permite buscar cuando campo tblCanales es NOT NULL
     */
    private boolean tblCanalesIsNotNull = false;

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
     * Valor de busqueda de campo externalid
     */
    private Long externalid;

    /**
     * Lista de valores del campo externalid para busquedas tipo IN
     */
    private List<Long> externalidIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo externalid es NULL
     */
    private boolean externalidIsNull = false;

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     */
    private boolean externalidIsNotNull = false;

    /**
     * Valor de busqueda de campo nmaxreenvios
     */
    private Integer nmaxenvios;

    /**
     * Lista de valores del campo nmaxreenvios para busquedas tipo IN
     */
    private List<Integer> nmaxenviosIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo nmaxreenvios es NULL
     */
    private boolean nmaxenviosIsNull = false;

    /**
     * Permite buscar cuando campo nmaxreenvios es NOT NULL
     */
    private boolean nmaxenviosIsNotNull = false;

    /**
     * Valor de busqueda de campo nmaxenvios
     */
    private Integer nmaxreenvios;

    /**
     * Lista de valores del campo nmaxenvios para busquedas tipo IN
     */
    private List<Integer> nmaxreenviosIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo nmaxenvios es NULL
     */
    private boolean nmaxreenviosIsNull = false;

    /**
     * Permite buscar cuando campo nmaxenvios es NOT NULL
     */
    private boolean nmaxreenviosIsNotNull = false;
    
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
     * Valor de busqueda de campo motivohistorificacion
     */
    private String motivohistorificacion;

    /**
     * Tipo de comparador para la busqueda por campo motivohistorificacion
     */
    private TextComparator motivohistorificacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo motivohistorificacion para busquedas tipo IN
     */
    private List<String> motivohistorificacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo motivohistorificacion es NULL
     */
    private boolean motivohistorificacionIsNull = false;

    /**
     * Permite buscar cuando campo motivohistorificacion es NOT NULL
     */
    private boolean motivohistorificacionIsNotNull = false;

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
     * Valor de busqueda de campo motivoconservacion
     */
    private String motivoconservacion;

    /**
     * Tipo de comparador para la busqueda por campo motivoconservacion
     */
    private TextComparator motivoconservacionComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo motivoconservacion para busquedas tipo IN
     */
    private List<String> motivoconservacionIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo motivoconservacion es NULL
     */
    private boolean motivoconservacionIsNull = false;

    /**
     * Permite buscar cuando campo motivoconservacion es NOT NULL
     */
    private boolean motivoconservacionIsNotNull = false;

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
     * Valor de busqueda de campo respFuncionalNombre
     */
    private String respFuncionalNombre;

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalNombre
     */
    private TextComparator respFuncionalNombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo respFuncionalNombre para busquedas tipo IN
     */
    private List<String> respFuncionalNombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo respFuncionalNombre es NULL
     */
    private boolean respFuncionalNombreIsNull = false;

    /**
     * Permite buscar cuando campo respFuncionalNombre es NOT NULL
     */
    private boolean respFuncionalNombreIsNotNull = false;

    /**
     * Valor de busqueda de campo respFuncionalEmail
     */
    private String respFuncionalEmail;

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalEmail
     */
    private TextComparator respFuncionalEmailComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo respFuncionalEmail para busquedas tipo IN
     */
    private List<String> respFuncionalEmailIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo respFuncionalEmail es NULL
     */
    private boolean respFuncionalEmailIsNull = false;

    /**
     * Permite buscar cuando campo respFuncionalEmail es NOT NULL
     */
    private boolean respFuncionalEmailIsNotNull = false;

    /**
     * Valor de busqueda de campo respTecnicoNombre
     */
    private String respTecnicoNombre;

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoNombre
     */
    private TextComparator respTecnicoNombreComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo respTecnicoNombre para busquedas tipo IN
     */
    private List<String> respTecnicoNombreIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo respTecnicoNombre es NULL
     */
    private boolean respTecnicoNombreIsNull = false;

    /**
     * Permite buscar cuando campo respTecnicoNombre es NOT NULL
     */
    private boolean respTecnicoNombreIsNotNull = false;

    /**
     * Valor de busqueda de campo respTecnicoEmail
     */
    private String respTecnicoEmail;

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoEmail
     */
    private TextComparator respTecnicoEmailComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo respTecnicoEmail para busquedas tipo IN
     */
    private List<String> respTecnicoEmailIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo respTecnicoEmail es NULL
     */
    private boolean respTecnicoEmailIsNull = false;

    /**
     * Permite buscar cuando campo respTecnicoEmail es NOT NULL
     */
    private boolean respTecnicoEmailIsNotNull = false;

    /**
     * Valor de busqueda de campo proveedorusuariosms
     */
    private String proveedorusuariosms;

    /**
     * Tipo de comparador para la busqueda por campo proveedorusuariosms
     */
    private TextComparator proveedorusuariosmsComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo proveedorusuariosms para busquedas tipo IN
     */
    private List<String> proveedorusuariosmsIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo proveedorusuariosms es NULL
     */
    private boolean proveedorusuariosmsIsNull = false;

    /**
     * Permite buscar cuando campo proveedorusuariosms es NOT NULL
     */
    private boolean proveedorusuariosmsIsNotNull = false;

    /**
     * Valor de busqueda de campo proveedorpasswordsms
     */
    private String proveedorpasswordsms;

    /**
     * Tipo de comparador para la busqueda por campo proveedorpasswordsms
     */
    private TextComparator proveedorpasswordsmsComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo proveedorpasswordsms para busquedas tipo IN
     */
    private List<String> proveedorpasswordsmsIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NULL
     */
    private boolean proveedorpasswordsmsIsNull = false;

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NOT NULL
     */
    private boolean proveedorpasswordsmsIsNotNull = false;

    /**
     * Valor de busqueda de campo multiorganismo
     */
    private Boolean multiorganismo;

    /**
     * Permite buscar cuando campo multiorganismo es NULL
     */
    private boolean multiorganismoIsNull = false;

    /**
     * Permite buscar cuando campo multiorganismo es NOT NULL
     */
    private boolean multiorganismoIsNotNull = false;

    /**
     * Valor de busqueda de campo exclusivo
     */
    private Boolean exclusivo;

    /**
     * Permite buscar cuando campo premium es NULL
     */
    private boolean exclusivoIsNull = false;

    /**
     * Permite buscar cuando campo premium es NOT NULL
     */
    private boolean exclusivoIsNotNull = false;
    
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
     * Valor de busqueda de campo numeroMaxReenvios
     */
    private Integer numeroMaxReenvios;

    /**
     * Lista de valores del campo numeroMaxReenvios para busquedas tipo IN
     */
    private List<Integer> numeroMaxReenviosIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo numeroMaxReenvios es NULL
     */
    private boolean numeroMaxReenviosIsNull = false;

    /**
     * Permite buscar cuando campo numeroMaxReenvios es NOT NULL
     */
    private boolean numeroMaxReenviosIsNotNull = false;

    /**
     * Valor de busqueda de campo reintentosConsultaEStado
     */
    private Integer reintentosConsultaEStado;

    /**
     * Lista de valores del campo reintentosConsultaEStado para busquedas tipo IN
     */
    private List<Integer> reintentosConsultaEStadoIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo reintentosConsultaEStado es NULL
     */
    private boolean reintentosConsultaEStadoIsNull = false;

    /**
     * Permite buscar cuando campo reintentosConsultaEStado es NOT NULL
     */
    private boolean reintentosConsultaEStadoIsNotNull = false;

    /**
     * Valor de busqueda de campo caducidad
     */
    private Integer caducidad;

    /**
     * Lista de valores del campo caducidad para busquedas tipo IN
     */
    private List<Integer> caducidadIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo caducidad es NULL
     */
    private boolean caducidadIsNull = false;

    /**
     * Permite buscar cuando campo caducidad es NOT NULL
     */
    private boolean caducidadIsNotNull = false;

    /**
     * Valor de busqueda de campo caducidadWebPush
     */
    private Integer caducidadWebPush;

    /**
     * Lista de valores del campo caducidadWebPush para busquedas tipo IN
     */
    private List<Integer> caducidadWebPushIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo caducidadWebPush es NULL
     */
    private boolean caducidadWebPushIsNull = false;

    /**
     * Permite buscar cuando campo caducidadWebPush es NOT NULL
     */
    private boolean caducidadWebPushIsNotNull = false;

    /**
     * Valor de busqueda de campo vapidPublicKey
     */
    private String vapidPublicKey;

    /**
     * Tipo de comparador para la busqueda por campo vapidPublicKey
     */
    private TextComparator vapidPublicKeyComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo vapidPublicKey para busquedas tipo IN
     */
    private List<String> vapidPublicKeyIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo vapidPublicKey es NULL
     */
    private boolean vapidPublicKeyIsNull = false;

    /**
     * Permite buscar cuando campo vapidPublicKey es NOT NULL
     */
    private boolean vapidPublicKeyIsNotNull = false;

    /**
     * Valor de busqueda de campo vapidPrivateKey
     */
    private String vapidPrivateKey;

    /**
     * Tipo de comparador para la busqueda por campo vapidPrivateKey
     */
    private TextComparator vapidPrivateKeyComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo vapidPrivateKey para busquedas tipo IN
     */
    private List<String> vapidPrivateKeyIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo vapidPrivateKey es NULL
     */
    private boolean vapidPrivateKeyIsNull = false;

    /**
     * Permite buscar cuando campo vapidPrivateKey es NOT NULL
     */
    private boolean vapidPrivateKeyIsNotNull = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblAplicaciones
     */
    private boolean innerJoinTblAplicaciones = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblAplicaciones
     */
    private boolean leftJoinTblAplicaciones = false;

    /**
     * Indica si en la consulta se hace un inner join con el padre tblCanales
     */
    private boolean innerJoinTblCanales = false;

    /**
     * Indica si en la consulta se hace un left join con el padre tblCanales
     */
    private boolean leftJoinTblCanales = false;

    /**
     * Constructor default
     */
    public TblServiciosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblServiciosQuery(Long servicioid) {
        setServicioid(servicioid);
    }

    /**
     * Valor de busqueda de campo servicioid
     * @return Long.
     */
    public Long getServicioid() {
        return servicioid;
    }

    /**
     * Valor de busqueda de campo servicioid
     * @param servicioid Valor de seteo.
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
     * @param servicioid Valor a agregar.
     */
    public void addServicioidIn(Long servicioid) {
        this.servicioidIn.add(servicioid);
    }

    /**
     * Valor de busqueda de campo tblAplicaciones
     * @return TblAplicaciones.
     */
    public TblAplicacionesQuery getTblAplicaciones() {
        return tblAplicaciones;
    }

    /**
     * Valor de busqueda de campo tblAplicaciones
     * @param tblAplicaciones Valor de seteo.
     */
    public void setTblAplicaciones(TblAplicacionesQuery tblAplicaciones) {
        this.tblAplicaciones = tblAplicaciones;
    }

    /**
     * @return List<TblAplicaciones>.
     */
    public List<TblAplicaciones> getTblAplicacionesIdIn() {
        return this.tblAplicacionesIdIn;
    }

    /**
     * @param tblAplicaciones Valor a agregar.
     */
    public void addTblAplicacionesIdIn(TblAplicaciones tblAplicaciones) {
        this.tblAplicacionesIdIn.add(tblAplicaciones);
    }

    /**
     * Permite buscar cuando campo tblAplicaciones es NULL
     * @return boolean.
     */
    public boolean isTblAplicacionesIsNull() {
        return tblAplicacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblAplicaciones es NULL
     * @param tblAplicacionesIsNull Valor de seteo.
     */
    public void setTblAplicacionesIsNull(boolean tblAplicacionesIsNull) {
        this.tblAplicacionesIsNull = tblAplicacionesIsNull;
    }

    /**
     * Permite buscar cuando campo tblAplicaciones es NOT NULL
     * @return boolean.
     */
    public boolean isTblAplicacionesIsNotNull() {
        return tblAplicacionesIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblAplicaciones es NOT NULL
     * @param tblAplicacionesIsNotNull Valor de seteo.
     */
    public void setTblAplicacionesIsNotNull(boolean tblAplicacionesIsNotNull) {
        this.tblAplicacionesIsNotNull = tblAplicacionesIsNotNull;
    }

    /**
     * Valor de busqueda de campo tblCanales
     * @return TblCanales.
     */
    public TblCanalesQuery getTblCanales() {
        return tblCanales;
    }

    /**
     * Valor de busqueda de campo tblCanales
     * @param tblCanales Valor de seteo.
     */
    public void setTblCanales(TblCanalesQuery tblCanales) {
        this.tblCanales = tblCanales;
    }

    /**
     * @return List<TblCanales>.
     */
    public List<TblCanales> getTblCanalesIdIn() {
        return this.tblCanalesIdIn;
    }

    /**
     * @param tblCanales Valor a agregar.
     */
    public void addTblCanalesIdIn(TblCanales tblCanales) {
        this.tblCanalesIdIn.add(tblCanales);
    }

    /**
     * Permite buscar cuando campo tblCanales es NULL
     * @return boolean.
     */
    public boolean isTblCanalesIsNull() {
        return tblCanalesIsNull;
    }

    /**
     * Permite buscar cuando campo tblCanales es NULL
     * @param tblCanalesIsNull Valor de seteo.
     */
    public void setTblCanalesIsNull(boolean tblCanalesIsNull) {
        this.tblCanalesIsNull = tblCanalesIsNull;
    }

    /**
     * Permite buscar cuando campo tblCanales es NOT NULL
     * @return boolean.
     */
    public boolean isTblCanalesIsNotNull() {
        return tblCanalesIsNotNull;
    }

    /**
     * Permite buscar cuando campo tblCanales es NOT NULL
     * @param tblCanalesIsNotNull Valor de seteo.
     */
    public void setTblCanalesIsNotNull(boolean tblCanalesIsNotNull) {
        this.tblCanalesIsNotNull = tblCanalesIsNotNull;
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
     * Valor de busqueda de campo descripcion
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
     * @param descripcion Valor de seteo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Tipo de comparador para la busqueda por campo descripcion
     * @return descripcionComparator.
     */
    public TextComparator getDescripcionComparator() {
        return descripcionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo descripcion
     * @param descripcionComparator Valor de seteo.
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
     * @param descripcion Valor a agregar.
     */
    public void addDescripcionIn(String descripcion) {
        this.descripcionIn.add(descripcion);
    }

    /**
     * Permite buscar cuando campo descripcion es NULL
     * @return boolean.
     */
    public boolean isDescripcionIsNull() {
        return descripcionIsNull;
    }

    /**
     * Permite buscar cuando campo descripcion es NULL
     * @param descripcionIsNull Valor de seteo.
     */
    public void setDescripcionIsNull(boolean descripcionIsNull) {
        this.descripcionIsNull = descripcionIsNull;
    }

    /**
     * Permite buscar cuando campo descripcion es NOT NULL
     * @return boolean.
     */
    public boolean isDescripcionIsNotNull() {
        return descripcionIsNotNull;
    }

    /**
     * Permite buscar cuando campo descripcion es NOT NULL
     * @param descripcionIsNotNull Valor de seteo.
     */
    public void setDescripcionIsNotNull(boolean descripcionIsNotNull) {
        this.descripcionIsNotNull = descripcionIsNotNull;
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
     * Valor inferior de rango de busqueda de fecha fechacreacion
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
     * @param fechacreacionMin Valor de seteo.
     */
    public void setFechacreacionMin(Date fechacreacionMin) {
        this.fechacreacionMin = fechacreacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechacreacion
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
     * @param fechacreacionMax Valor de seteo.
     */
    public void setFechacreacionMax(Date fechacreacionMax) {
        this.fechacreacionMax = fechacreacionMax;
    }

    /**
     * Permite buscar cuando campo fechacreacion es NULL
     * @return boolean.
     */
    public boolean isFechacreacionIsNull() {
        return fechacreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechacreacion es NULL
     * @param fechacreacionIsNull Valor de seteo.
     */
    public void setFechacreacionIsNull(boolean fechacreacionIsNull) {
        this.fechacreacionIsNull = fechacreacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechacreacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechacreacionIsNotNull() {
        return fechacreacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechacreacion es NOT NULL
     * @param fechacreacionIsNotNull Valor de seteo.
     */
    public void setFechacreacionIsNotNull(boolean fechacreacionIsNotNull) {
        this.fechacreacionIsNotNull = fechacreacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo creadopor
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
     * @param creadopor Valor de seteo.
     */
    public void setCreadopor(String creadopor) {
        this.creadopor = creadopor;
    }

    /**
     * Tipo de comparador para la busqueda por campo creadopor
     * @return creadoporComparator.
     */
    public TextComparator getCreadoporComparator() {
        return creadoporComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo creadopor
     * @param creadoporComparator Valor de seteo.
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
     * @param creadopor Valor a agregar.
     */
    public void addCreadoporIn(String creadopor) {
        this.creadoporIn.add(creadopor);
    }

    /**
     * Permite buscar cuando campo creadopor es NULL
     * @return boolean.
     */
    public boolean isCreadoporIsNull() {
        return creadoporIsNull;
    }

    /**
     * Permite buscar cuando campo creadopor es NULL
     * @param creadoporIsNull Valor de seteo.
     */
    public void setCreadoporIsNull(boolean creadoporIsNull) {
        this.creadoporIsNull = creadoporIsNull;
    }

    /**
     * Permite buscar cuando campo creadopor es NOT NULL
     * @return boolean.
     */
    public boolean isCreadoporIsNotNull() {
        return creadoporIsNotNull;
    }

    /**
     * Permite buscar cuando campo creadopor es NOT NULL
     * @param creadoporIsNotNull Valor de seteo.
     */
    public void setCreadoporIsNotNull(boolean creadoporIsNotNull) {
        this.creadoporIsNotNull = creadoporIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechamodificacion
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
     * @param fechamodificacionMin Valor de seteo.
     */
    public void setFechamodificacionMin(Date fechamodificacionMin) {
        this.fechamodificacionMin = fechamodificacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechamodificacion
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
     * @param fechamodificacionMax Valor de seteo.
     */
    public void setFechamodificacionMax(Date fechamodificacionMax) {
        this.fechamodificacionMax = fechamodificacionMax;
    }

    /**
     * Permite buscar cuando campo fechamodificacion es NULL
     * @return boolean.
     */
    public boolean isFechamodificacionIsNull() {
        return fechamodificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechamodificacion es NULL
     * @param fechamodificacionIsNull Valor de seteo.
     */
    public void setFechamodificacionIsNull(boolean fechamodificacionIsNull) {
        this.fechamodificacionIsNull = fechamodificacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechamodificacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechamodificacionIsNotNull() {
        return fechamodificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechamodificacion es NOT NULL
     * @param fechamodificacionIsNotNull Valor de seteo.
     */
    public void setFechamodificacionIsNotNull(boolean fechamodificacionIsNotNull) {
        this.fechamodificacionIsNotNull = fechamodificacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo modificadopor
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
     * @param modificadopor Valor de seteo.
     */
    public void setModificadopor(String modificadopor) {
        this.modificadopor = modificadopor;
    }

    /**
     * Tipo de comparador para la busqueda por campo modificadopor
     * @return modificadoporComparator.
     */
    public TextComparator getModificadoporComparator() {
        return modificadoporComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo modificadopor
     * @param modificadoporComparator Valor de seteo.
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
     * @param modificadopor Valor a agregar.
     */
    public void addModificadoporIn(String modificadopor) {
        this.modificadoporIn.add(modificadopor);
    }

    /**
     * Permite buscar cuando campo modificadopor es NULL
     * @return boolean.
     */
    public boolean isModificadoporIsNull() {
        return modificadoporIsNull;
    }

    /**
     * Permite buscar cuando campo modificadopor es NULL
     * @param modificadoporIsNull Valor de seteo.
     */
    public void setModificadoporIsNull(boolean modificadoporIsNull) {
        this.modificadoporIsNull = modificadoporIsNull;
    }

    /**
     * Permite buscar cuando campo modificadopor es NOT NULL
     * @return boolean.
     */
    public boolean isModificadoporIsNotNull() {
        return modificadoporIsNotNull;
    }

    /**
     * Permite buscar cuando campo modificadopor es NOT NULL
     * @param modificadoporIsNotNull Valor de seteo.
     */
    public void setModificadoporIsNotNull(boolean modificadoporIsNotNull) {
        this.modificadoporIsNotNull = modificadoporIsNotNull;
    }

    /**
     * Valor de busqueda de campo externalid
     * @return Long.
     */
    public Long getExternalid() {
        return externalid;
    }

    /**
     * Valor de busqueda de campo externalid
     * @param externalid Valor de seteo.
     */
    public void setExternalid(Long externalid) {
        this.externalid = externalid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getExternalidIn() {
        return this.externalidIn;
    }

    /**
     * @param externalid Valor a agregar.
     */
    public void addExternalidIn(Long externalid) {
        this.externalidIn.add(externalid);
    }

    /**
     * Permite buscar cuando campo externalid es NULL
     * @return boolean.
     */
    public boolean isExternalidIsNull() {
        return externalidIsNull;
    }

    /**
     * Permite buscar cuando campo externalid es NULL
     * @param externalidIsNull Valor de seteo.
     */
    public void setExternalidIsNull(boolean externalidIsNull) {
        this.externalidIsNull = externalidIsNull;
    }

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     * @return boolean.
     */
    public boolean isExternalidIsNotNull() {
        return externalidIsNotNull;
    }

    /**
     * Permite buscar cuando campo externalid es NOT NULL
     * @param externalidIsNotNull Valor de seteo.
     */
    public void setExternalidIsNotNull(boolean externalidIsNotNull) {
        this.externalidIsNotNull = externalidIsNotNull;
    }

    /**
     * Valor de busqueda de campo nmaxenvios
     * @return Integer.
     */
    public Integer getNmaxenvios() {
        return nmaxenvios;
    }

    /**
     * Valor de busqueda de campo nmaxenvios
     * @param nmaxenvios Valor de seteo.
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
     * @param nmaxenvios Valor a agregar.
     */
    public void addNmaxenviosIn(Integer nmaxenvios) {
        this.nmaxenviosIn.add(nmaxenvios);
    }

    /**
     * Permite buscar cuando campo nmaxenvios es NULL
     * @return boolean.
     */
    public boolean isNmaxenviosIsNull() {
        return nmaxenviosIsNull;
    }

    /**
     * Permite buscar cuando campo nmaxenvios es NULL
     * @param nmaxenviosIsNull Valor de seteo.
     */
    public void setNmaxenviosIsNull(boolean nmaxenviosIsNull) {
        this.nmaxenviosIsNull = nmaxenviosIsNull;
    }

    /**
     * Permite buscar cuando campo nmaxenvios es NOT NULL
     * @return boolean.
     */
    public boolean isNmaxenviosIsNotNull() {
        return nmaxenviosIsNotNull;
    }

    /**
     * Permite buscar cuando campo nmaxenvios es NOT NULL
     * @param nmaxenviosIsNotNull Valor de seteo.
     */
    public void setNmaxenviosIsNotNull(boolean nmaxenviosIsNotNull) {
        this.nmaxenviosIsNotNull = nmaxenviosIsNotNull;
    }

    /**
     * Valor de busqueda de campo nmaxenvios
     * @return Integer.
     */
    public Integer getNmaxreenvios() {
        return nmaxenvios;
    }

    /**
     * Valor de busqueda de campo nmaxenvios
     * @param nmaxenvios Valor de seteo.
     */
    public void setNmaxreenvios(Integer nmaxreenvios) {
        this.nmaxreenvios = nmaxreenvios;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getNmaxreenviosIn() {
        return this.nmaxreenviosIn;
    }

    /**
     * @param nmaxenvios Valor a agregar.
     */
    public void addNmaxreenviosIn(Integer nmaxreenvios) {
        this.nmaxreenviosIn.add(nmaxreenvios);
    }

    /**
     * Permite buscar cuando campo nmaxenvios es NULL
     * @return boolean.
     */
    public boolean isNmaxreenviosIsNull() {
        return nmaxreenviosIsNull;
    }

    /**
     * Permite buscar cuando campo nmaxenvios es NULL
     * @param nmaxenviosIsNull Valor de seteo.
     */
    public void setNmaxreenviosIsNull(boolean nmaxreenviosIsNull) {
        this.nmaxreenviosIsNull = nmaxreenviosIsNull;
    }

    /**
     * Permite buscar cuando campo nmaxenvios es NOT NULL
     * @return boolean.
     */
    public boolean isNmaxreenviosIsNotNull() {
        return nmaxreenviosIsNotNull;
    }

    /**
     * Permite buscar cuando campo nmaxenvios es NOT NULL
     * @param nmaxenviosIsNotNull Valor de seteo.
     */
    public void setNmaxreenviosIsNotNull(boolean nmaxreenviosIsNotNull) {
        this.nmaxreenviosIsNotNull = nmaxreenviosIsNotNull;
    }
    
    /**
     * Valor de busqueda de campo headersms
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
     * @param headersms Valor de seteo.
     */
    public void setHeadersms(String headersms) {
        this.headersms = headersms;
    }

    /**
     * Tipo de comparador para la busqueda por campo headersms
     * @return headersmsComparator.
     */
    public TextComparator getHeadersmsComparator() {
        return headersmsComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo headersms
     * @param headersmsComparator Valor de seteo.
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
     * @param headersms Valor a agregar.
     */
    public void addHeadersmsIn(String headersms) {
        this.headersmsIn.add(headersms);
    }

    /**
     * Permite buscar cuando campo headersms es NULL
     * @return boolean.
     */
    public boolean isHeadersmsIsNull() {
        return headersmsIsNull;
    }

    /**
     * Permite buscar cuando campo headersms es NULL
     * @param headersmsIsNull Valor de seteo.
     */
    public void setHeadersmsIsNull(boolean headersmsIsNull) {
        this.headersmsIsNull = headersmsIsNull;
    }

    /**
     * Permite buscar cuando campo headersms es NOT NULL
     * @return boolean.
     */
    public boolean isHeadersmsIsNotNull() {
        return headersmsIsNotNull;
    }

    /**
     * Permite buscar cuando campo headersms es NOT NULL
     * @param headersmsIsNotNull Valor de seteo.
     */
    public void setHeadersmsIsNotNull(boolean headersmsIsNotNull) {
        this.headersmsIsNotNull = headersmsIsNotNull;
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
     * Valor de busqueda de campo cuentaenvio
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
     * @param cuentaenvio Valor de seteo.
     */
    public void setCuentaenvio(String cuentaenvio) {
        this.cuentaenvio = cuentaenvio;
    }

    /**
     * Tipo de comparador para la busqueda por campo cuentaenvio
     * @return cuentaenvioComparator.
     */
    public TextComparator getCuentaenvioComparator() {
        return cuentaenvioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo cuentaenvio
     * @param cuentaenvioComparator Valor de seteo.
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
     * @param cuentaenvio Valor a agregar.
     */
    public void addCuentaenvioIn(String cuentaenvio) {
        this.cuentaenvioIn.add(cuentaenvio);
    }

    /**
     * Permite buscar cuando campo cuentaenvio es NULL
     * @return boolean.
     */
    public boolean isCuentaenvioIsNull() {
        return cuentaenvioIsNull;
    }

    /**
     * Permite buscar cuando campo cuentaenvio es NULL
     * @param cuentaenvioIsNull Valor de seteo.
     */
    public void setCuentaenvioIsNull(boolean cuentaenvioIsNull) {
        this.cuentaenvioIsNull = cuentaenvioIsNull;
    }

    /**
     * Permite buscar cuando campo cuentaenvio es NOT NULL
     * @return boolean.
     */
    public boolean isCuentaenvioIsNotNull() {
        return cuentaenvioIsNotNull;
    }

    /**
     * Permite buscar cuando campo cuentaenvio es NOT NULL
     * @param cuentaenvioIsNotNull Valor de seteo.
     */
    public void setCuentaenvioIsNotNull(boolean cuentaenvioIsNotNull) {
        this.cuentaenvioIsNotNull = cuentaenvioIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombrecuentaenvio
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
     * @param nombrecuentaenvio Valor de seteo.
     */
    public void setNombrecuentaenvio(String nombrecuentaenvio) {
        this.nombrecuentaenvio = nombrecuentaenvio;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombrecuentaenvio
     * @return nombrecuentaenvioComparator.
     */
    public TextComparator getNombrecuentaenvioComparator() {
        return nombrecuentaenvioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombrecuentaenvio
     * @param nombrecuentaenvioComparator Valor de seteo.
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
     * @param nombrecuentaenvio Valor a agregar.
     */
    public void addNombrecuentaenvioIn(String nombrecuentaenvio) {
        this.nombrecuentaenvioIn.add(nombrecuentaenvio);
    }

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NULL
     * @return boolean.
     */
    public boolean isNombrecuentaenvioIsNull() {
        return nombrecuentaenvioIsNull;
    }

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NULL
     * @param nombrecuentaenvioIsNull Valor de seteo.
     */
    public void setNombrecuentaenvioIsNull(boolean nombrecuentaenvioIsNull) {
        this.nombrecuentaenvioIsNull = nombrecuentaenvioIsNull;
    }

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NOT NULL
     * @return boolean.
     */
    public boolean isNombrecuentaenvioIsNotNull() {
        return nombrecuentaenvioIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombrecuentaenvio es NOT NULL
     * @param nombrecuentaenvioIsNotNull Valor de seteo.
     */
    public void setNombrecuentaenvioIsNotNull(boolean nombrecuentaenvioIsNotNull) {
        this.nombrecuentaenvioIsNotNull = nombrecuentaenvioIsNotNull;
    }

    /**
     * Valor de busqueda de campo historificacion
     * @return Integer.
     */
    public Integer getHistorificacion() {
        return historificacion;
    }

    /**
     * Valor de busqueda de campo historificacion
     * @param historificacion Valor de seteo.
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
     * @param historificacion Valor a agregar.
     */
    public void addHistorificacionIn(Integer historificacion) {
        this.historificacionIn.add(historificacion);
    }

    /**
     * Permite buscar cuando campo historificacion es NULL
     * @return boolean.
     */
    public boolean isHistorificacionIsNull() {
        return historificacionIsNull;
    }

    /**
     * Permite buscar cuando campo historificacion es NULL
     * @param historificacionIsNull Valor de seteo.
     */
    public void setHistorificacionIsNull(boolean historificacionIsNull) {
        this.historificacionIsNull = historificacionIsNull;
    }

    /**
     * Permite buscar cuando campo historificacion es NOT NULL
     * @return boolean.
     */
    public boolean isHistorificacionIsNotNull() {
        return historificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo historificacion es NOT NULL
     * @param historificacionIsNotNull Valor de seteo.
     */
    public void setHistorificacionIsNotNull(boolean historificacionIsNotNull) {
        this.historificacionIsNotNull = historificacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo motivohistorificacion
     * @return String.
     */
    public String getMotivohistorificacion() {
        if (motivohistorificacion != null) {
            switch (motivohistorificacionComparator) {
	            case STARTS_WITH:
	                return motivohistorificacion + "%";
	            case CONTAINS:
	                return "%" + motivohistorificacion + "%";
	            case ENDS_WITH:
	                return "%" + motivohistorificacion;
	            case EQUALS:
                	return motivohistorificacion;
              	default:
	            	break;
            }
        }
        return motivohistorificacion;
    }

    /**
     * Valor de busqueda de campo motivohistorificacion
     * @param motivohistorificacion Valor de seteo.
     */
    public void setMotivohistorificacion(String motivohistorificacion) {
        this.motivohistorificacion = motivohistorificacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo motivohistorificacion
     * @return motivohistorificacionComparator.
     */
    public TextComparator getMotivohistorificacionComparator() {
        return motivohistorificacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo motivohistorificacion
     * @param motivohistorificacionComparator Valor de seteo.
     */
    public void setMotivohistorificacionComparator(TextComparator motivohistorificacionComparator) {
        this.motivohistorificacionComparator = motivohistorificacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMotivohistorificacionIn() {
        return this.motivohistorificacionIn;
    }

    /**
     * @param motivohistorificacion Valor a agregar.
     */
    public void addMotivohistorificacionIn(String motivohistorificacion) {
        this.motivohistorificacionIn.add(motivohistorificacion);
    }

    /**
     * Permite buscar cuando campo motivohistorificacion es NULL
     * @return boolean.
     */
    public boolean isMotivohistorificacionIsNull() {
        return motivohistorificacionIsNull;
    }

    /**
     * Permite buscar cuando campo motivohistorificacion es NULL
     * @param motivohistorificacionIsNull Valor de seteo.
     */
    public void setMotivohistorificacionIsNull(boolean motivohistorificacionIsNull) {
        this.motivohistorificacionIsNull = motivohistorificacionIsNull;
    }

    /**
     * Permite buscar cuando campo motivohistorificacion es NOT NULL
     * @return boolean.
     */
    public boolean isMotivohistorificacionIsNotNull() {
        return motivohistorificacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo motivohistorificacion es NOT NULL
     * @param motivohistorificacionIsNotNull Valor de seteo.
     */
    public void setMotivohistorificacionIsNotNull(boolean motivohistorificacionIsNotNull) {
        this.motivohistorificacionIsNotNull = motivohistorificacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo conservacion
     * @return Integer.
     */
    public Integer getConservacion() {
        return conservacion;
    }

    /**
     * Valor de busqueda de campo conservacion
     * @param conservacion Valor de seteo.
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
     * @param conservacion Valor a agregar.
     */
    public void addConservacionIn(Integer conservacion) {
        this.conservacionIn.add(conservacion);
    }

    /**
     * Permite buscar cuando campo conservacion es NULL
     * @return boolean.
     */
    public boolean isConservacionIsNull() {
        return conservacionIsNull;
    }

    /**
     * Permite buscar cuando campo conservacion es NULL
     * @param conservacionIsNull Valor de seteo.
     */
    public void setConservacionIsNull(boolean conservacionIsNull) {
        this.conservacionIsNull = conservacionIsNull;
    }

    /**
     * Permite buscar cuando campo conservacion es NOT NULL
     * @return boolean.
     */
    public boolean isConservacionIsNotNull() {
        return conservacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo conservacion es NOT NULL
     * @param conservacionIsNotNull Valor de seteo.
     */
    public void setConservacionIsNotNull(boolean conservacionIsNotNull) {
        this.conservacionIsNotNull = conservacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo motivoconservacion
     * @return String.
     */
    public String getMotivoconservacion() {
        if (motivoconservacion != null) {
            switch (motivoconservacionComparator) {
	            case STARTS_WITH:
	                return motivoconservacion + "%";
	            case CONTAINS:
	                return "%" + motivoconservacion + "%";
	            case ENDS_WITH:
	                return "%" + motivoconservacion;
	            case EQUALS:
                	return motivoconservacion;
              	default:
	            	break;
            }
        }
        return motivoconservacion;
    }

    /**
     * Valor de busqueda de campo motivoconservacion
     * @param motivoconservacion Valor de seteo.
     */
    public void setMotivoconservacion(String motivoconservacion) {
        this.motivoconservacion = motivoconservacion;
    }

    /**
     * Tipo de comparador para la busqueda por campo motivoconservacion
     * @return motivoconservacionComparator.
     */
    public TextComparator getMotivoconservacionComparator() {
        return motivoconservacionComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo motivoconservacion
     * @param motivoconservacionComparator Valor de seteo.
     */
    public void setMotivoconservacionComparator(TextComparator motivoconservacionComparator) {
        this.motivoconservacionComparator = motivoconservacionComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getMotivoconservacionIn() {
        return this.motivoconservacionIn;
    }

    /**
     * @param motivoconservacion Valor a agregar.
     */
    public void addMotivoconservacionIn(String motivoconservacion) {
        this.motivoconservacionIn.add(motivoconservacion);
    }

    /**
     * Permite buscar cuando campo motivoconservacion es NULL
     * @return boolean.
     */
    public boolean isMotivoconservacionIsNull() {
        return motivoconservacionIsNull;
    }

    /**
     * Permite buscar cuando campo motivoconservacion es NULL
     * @param motivoconservacionIsNull Valor de seteo.
     */
    public void setMotivoconservacionIsNull(boolean motivoconservacionIsNull) {
        this.motivoconservacionIsNull = motivoconservacionIsNull;
    }

    /**
     * Permite buscar cuando campo motivoconservacion es NOT NULL
     * @return boolean.
     */
    public boolean isMotivoconservacionIsNotNull() {
        return motivoconservacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo motivoconservacion es NOT NULL
     * @param motivoconservacionIsNotNull Valor de seteo.
     */
    public void setMotivoconservacionIsNotNull(boolean motivoconservacionIsNotNull) {
        this.motivoconservacionIsNotNull = motivoconservacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo pendienteaprobacion
     * @return Boolean.
     */
    public Boolean getPendienteaprobacion() {
        return pendienteaprobacion;
    }

    /**
     * Valor de busqueda de campo pendienteaprobacion
     * @param pendienteaprobacion Valor de seteo.
     */
    public void setPendienteaprobacion(Boolean pendienteaprobacion) {
        this.pendienteaprobacion = pendienteaprobacion;
    }

    /**
     * Permite buscar cuando campo pendienteaprobacion es NULL
     * @return boolean.
     */
    public boolean isPendienteaprobacionIsNull() {
        return pendienteaprobacionIsNull;
    }

    /**
     * Permite buscar cuando campo pendienteaprobacion es NULL
     * @param pendienteaprobacionIsNull Valor de seteo.
     */
    public void setPendienteaprobacionIsNull(boolean pendienteaprobacionIsNull) {
        this.pendienteaprobacionIsNull = pendienteaprobacionIsNull;
    }

    /**
     * Permite buscar cuando campo pendienteaprobacion es NOT NULL
     * @return boolean.
     */
    public boolean isPendienteaprobacionIsNotNull() {
        return pendienteaprobacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo pendienteaprobacion es NOT NULL
     * @param pendienteaprobacionIsNotNull Valor de seteo.
     */
    public void setPendienteaprobacionIsNotNull(boolean pendienteaprobacionIsNotNull) {
        this.pendienteaprobacionIsNotNull = pendienteaprobacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombreloteenvio
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
     * @param nombreloteenvio Valor de seteo.
     */
    public void setNombreloteenvio(String nombreloteenvio) {
        this.nombreloteenvio = nombreloteenvio;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreloteenvio
     * @return nombreloteenvioComparator.
     */
    public TextComparator getNombreloteenvioComparator() {
        return nombreloteenvioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreloteenvio
     * @param nombreloteenvioComparator Valor de seteo.
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
     * @param nombreloteenvio Valor a agregar.
     */
    public void addNombreloteenvioIn(String nombreloteenvio) {
        this.nombreloteenvioIn.add(nombreloteenvio);
    }

    /**
     * Permite buscar cuando campo nombreloteenvio es NULL
     * @return boolean.
     */
    public boolean isNombreloteenvioIsNull() {
        return nombreloteenvioIsNull;
    }

    /**
     * Permite buscar cuando campo nombreloteenvio es NULL
     * @param nombreloteenvioIsNull Valor de seteo.
     */
    public void setNombreloteenvioIsNull(boolean nombreloteenvioIsNull) {
        this.nombreloteenvioIsNull = nombreloteenvioIsNull;
    }

    /**
     * Permite buscar cuando campo nombreloteenvio es NOT NULL
     * @return boolean.
     */
    public boolean isNombreloteenvioIsNotNull() {
        return nombreloteenvioIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombreloteenvio es NOT NULL
     * @param nombreloteenvioIsNotNull Valor de seteo.
     */
    public void setNombreloteenvioIsNotNull(boolean nombreloteenvioIsNotNull) {
        this.nombreloteenvioIsNotNull = nombreloteenvioIsNotNull;
    }

    /**
     * Valor de busqueda de campo badge
     * @return Long.
     */
    public Long getBadge() {
        return badge;
    }

    /**
     * Valor de busqueda de campo badge
     * @param badge Valor de seteo.
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
     * @param badge Valor a agregar.
     */
    public void addBadgeIn(Long badge) {
        this.badgeIn.add(badge);
    }

    /**
     * Permite buscar cuando campo badge es NULL
     * @return boolean.
     */
    public boolean isBadgeIsNull() {
        return badgeIsNull;
    }

    /**
     * Permite buscar cuando campo badge es NULL
     * @param badgeIsNull Valor de seteo.
     */
    public void setBadgeIsNull(boolean badgeIsNull) {
        this.badgeIsNull = badgeIsNull;
    }

    /**
     * Permite buscar cuando campo badge es NOT NULL
     * @return boolean.
     */
    public boolean isBadgeIsNotNull() {
        return badgeIsNotNull;
    }

    /**
     * Permite buscar cuando campo badge es NOT NULL
     * @param badgeIsNotNull Valor de seteo.
     */
    public void setBadgeIsNotNull(boolean badgeIsNotNull) {
        this.badgeIsNotNull = badgeIsNotNull;
    }

    /**
     * Valor de busqueda de campo fcmprojectkey
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
     * @param fcmprojectkey Valor de seteo.
     */
    public void setFcmprojectkey(String fcmprojectkey) {
        this.fcmprojectkey = fcmprojectkey;
    }

    /**
     * Tipo de comparador para la busqueda por campo fcmprojectkey
     * @return fcmprojectkeyComparator.
     */
    public TextComparator getFcmprojectkeyComparator() {
        return fcmprojectkeyComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo fcmprojectkey
     * @param fcmprojectkeyComparator Valor de seteo.
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
     * @param fcmprojectkey Valor a agregar.
     */
    public void addFcmprojectkeyIn(String fcmprojectkey) {
        this.fcmprojectkeyIn.add(fcmprojectkey);
    }

    /**
     * Permite buscar cuando campo fcmprojectkey es NULL
     * @return boolean.
     */
    public boolean isFcmprojectkeyIsNull() {
        return fcmprojectkeyIsNull;
    }

    /**
     * Permite buscar cuando campo fcmprojectkey es NULL
     * @param fcmprojectkeyIsNull Valor de seteo.
     */
    public void setFcmprojectkeyIsNull(boolean fcmprojectkeyIsNull) {
        this.fcmprojectkeyIsNull = fcmprojectkeyIsNull;
    }

    /**
     * Permite buscar cuando campo fcmprojectkey es NOT NULL
     * @return boolean.
     */
    public boolean isFcmprojectkeyIsNotNull() {
        return fcmprojectkeyIsNotNull;
    }

    /**
     * Permite buscar cuando campo fcmprojectkey es NOT NULL
     * @param fcmprojectkeyIsNotNull Valor de seteo.
     */
    public void setFcmprojectkeyIsNotNull(boolean fcmprojectkeyIsNotNull) {
        this.fcmprojectkeyIsNotNull = fcmprojectkeyIsNotNull;
    }

    /**
     * Valor de busqueda de campo apnsrutacertificado
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
     * @param apnsrutacertificado Valor de seteo.
     */
    public void setApnsrutacertificado(String apnsrutacertificado) {
        this.apnsrutacertificado = apnsrutacertificado;
    }

    /**
     * Tipo de comparador para la busqueda por campo apnsrutacertificado
     * @return apnsrutacertificadoComparator.
     */
    public TextComparator getApnsrutacertificadoComparator() {
        return apnsrutacertificadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo apnsrutacertificado
     * @param apnsrutacertificadoComparator Valor de seteo.
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
     * @param apnsrutacertificado Valor a agregar.
     */
    public void addApnsrutacertificadoIn(String apnsrutacertificado) {
        this.apnsrutacertificadoIn.add(apnsrutacertificado);
    }

    /**
     * Permite buscar cuando campo apnsrutacertificado es NULL
     * @return boolean.
     */
    public boolean isApnsrutacertificadoIsNull() {
        return apnsrutacertificadoIsNull;
    }

    /**
     * Permite buscar cuando campo apnsrutacertificado es NULL
     * @param apnsrutacertificadoIsNull Valor de seteo.
     */
    public void setApnsrutacertificadoIsNull(boolean apnsrutacertificadoIsNull) {
        this.apnsrutacertificadoIsNull = apnsrutacertificadoIsNull;
    }

    /**
     * Permite buscar cuando campo apnsrutacertificado es NOT NULL
     * @return boolean.
     */
    public boolean isApnsrutacertificadoIsNotNull() {
        return apnsrutacertificadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo apnsrutacertificado es NOT NULL
     * @param apnsrutacertificadoIsNotNull Valor de seteo.
     */
    public void setApnsrutacertificadoIsNotNull(boolean apnsrutacertificadoIsNotNull) {
        this.apnsrutacertificadoIsNotNull = apnsrutacertificadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo apnspasswordcertificado
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
     * @param apnspasswordcertificado Valor de seteo.
     */
    public void setApnspasswordcertificado(String apnspasswordcertificado) {
        this.apnspasswordcertificado = apnspasswordcertificado;
    }

    /**
     * Tipo de comparador para la busqueda por campo apnspasswordcertificado
     * @return apnspasswordcertificadoComparator.
     */
    public TextComparator getApnspasswordcertificadoComparator() {
        return apnspasswordcertificadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo apnspasswordcertificado
     * @param apnspasswordcertificadoComparator Valor de seteo.
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
     * @param apnspasswordcertificado Valor a agregar.
     */
    public void addApnspasswordcertificadoIn(String apnspasswordcertificado) {
        this.apnspasswordcertificadoIn.add(apnspasswordcertificado);
    }

    /**
     * Permite buscar cuando campo apnspasswordcertificado es NULL
     * @return boolean.
     */
    public boolean isApnspasswordcertificadoIsNull() {
        return apnspasswordcertificadoIsNull;
    }

    /**
     * Permite buscar cuando campo apnspasswordcertificado es NULL
     * @param apnspasswordcertificadoIsNull Valor de seteo.
     */
    public void setApnspasswordcertificadoIsNull(boolean apnspasswordcertificadoIsNull) {
        this.apnspasswordcertificadoIsNull = apnspasswordcertificadoIsNull;
    }

    /**
     * Permite buscar cuando campo apnspasswordcertificado es NOT NULL
     * @return boolean.
     */
    public boolean isApnspasswordcertificadoIsNotNull() {
        return apnspasswordcertificadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo apnspasswordcertificado es NOT NULL
     * @param apnspasswordcertificadoIsNotNull Valor de seteo.
     */
    public void setApnspasswordcertificadoIsNotNull(boolean apnspasswordcertificadoIsNotNull) {
        this.apnspasswordcertificadoIsNotNull = apnspasswordcertificadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo androidplataforma
     * @return Boolean.
     */
    public Boolean getAndroidplataforma() {
        return androidplataforma;
    }

    /**
     * Valor de busqueda de campo androidplataforma
     * @param androidplataforma Valor de seteo.
     */
    public void setAndroidplataforma(Boolean androidplataforma) {
        this.androidplataforma = androidplataforma;
    }

    /**
     * Permite buscar cuando campo androidplataforma es NULL
     * @return boolean.
     */
    public boolean isAndroidplataformaIsNull() {
        return androidplataformaIsNull;
    }

    /**
     * Permite buscar cuando campo androidplataforma es NULL
     * @param androidplataformaIsNull Valor de seteo.
     */
    public void setAndroidplataformaIsNull(boolean androidplataformaIsNull) {
        this.androidplataformaIsNull = androidplataformaIsNull;
    }

    /**
     * Permite buscar cuando campo androidplataforma es NOT NULL
     * @return boolean.
     */
    public boolean isAndroidplataformaIsNotNull() {
        return androidplataformaIsNotNull;
    }

    /**
     * Permite buscar cuando campo androidplataforma es NOT NULL
     * @param androidplataformaIsNotNull Valor de seteo.
     */
    public void setAndroidplataformaIsNotNull(boolean androidplataformaIsNotNull) {
        this.androidplataformaIsNotNull = androidplataformaIsNotNull;
    }

    /**
     * Valor de busqueda de campo iosplataforma
     * @return Boolean.
     */
    public Boolean getIosplataforma() {
        return iosplataforma;
    }

    /**
     * Valor de busqueda de campo iosplataforma
     * @param iosplataforma Valor de seteo.
     */
    public void setIosplataforma(Boolean iosplataforma) {
        this.iosplataforma = iosplataforma;
    }

    /**
     * Permite buscar cuando campo iosplataforma es NULL
     * @return boolean.
     */
    public boolean isIosplataformaIsNull() {
        return iosplataformaIsNull;
    }

    /**
     * Permite buscar cuando campo iosplataforma es NULL
     * @param iosplataformaIsNull Valor de seteo.
     */
    public void setIosplataformaIsNull(boolean iosplataformaIsNull) {
        this.iosplataformaIsNull = iosplataformaIsNull;
    }

    /**
     * Permite buscar cuando campo iosplataforma es NOT NULL
     * @return boolean.
     */
    public boolean isIosplataformaIsNotNull() {
        return iosplataformaIsNotNull;
    }

    /**
     * Permite buscar cuando campo iosplataforma es NOT NULL
     * @param iosplataformaIsNotNull Valor de seteo.
     */
    public void setIosplataformaIsNotNull(boolean iosplataformaIsNotNull) {
        this.iosplataformaIsNotNull = iosplataformaIsNotNull;
    }

    /**
     * Valor de busqueda de campo endpoint
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
     * @param endpoint Valor de seteo.
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Tipo de comparador para la busqueda por campo endpoint
     * @return endpointComparator.
     */
    public TextComparator getEndpointComparator() {
        return endpointComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo endpoint
     * @param endpointComparator Valor de seteo.
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
     * @param endpoint Valor a agregar.
     */
    public void addEndpointIn(String endpoint) {
        this.endpointIn.add(endpoint);
    }

    /**
     * Permite buscar cuando campo endpoint es NULL
     * @return boolean.
     */
    public boolean isEndpointIsNull() {
        return endpointIsNull;
    }

    /**
     * Permite buscar cuando campo endpoint es NULL
     * @param endpointIsNull Valor de seteo.
     */
    public void setEndpointIsNull(boolean endpointIsNull) {
        this.endpointIsNull = endpointIsNull;
    }

    /**
     * Permite buscar cuando campo endpoint es NOT NULL
     * @return boolean.
     */
    public boolean isEndpointIsNotNull() {
        return endpointIsNotNull;
    }

    /**
     * Permite buscar cuando campo endpoint es NOT NULL
     * @param endpointIsNotNull Valor de seteo.
     */
    public void setEndpointIsNotNull(boolean endpointIsNotNull) {
        this.endpointIsNotNull = endpointIsNotNull;
    }

    /**
     * Valor de busqueda de campo informesactivo
     * @return Boolean.
     */
    public Boolean getInformesactivo() {
        return informesactivo;
    }

    /**
     * Valor de busqueda de campo informesactivo
     * @param informesactivo Valor de seteo.
     */
    public void setInformesactivo(Boolean informesactivo) {
        this.informesactivo = informesactivo;
    }

    /**
     * Permite buscar cuando campo informesactivo es NULL
     * @return boolean.
     */
    public boolean isInformesactivoIsNull() {
        return informesactivoIsNull;
    }

    /**
     * Permite buscar cuando campo informesactivo es NULL
     * @param informesactivoIsNull Valor de seteo.
     */
    public void setInformesactivoIsNull(boolean informesactivoIsNull) {
        this.informesactivoIsNull = informesactivoIsNull;
    }

    /**
     * Permite buscar cuando campo informesactivo es NOT NULL
     * @return boolean.
     */
    public boolean isInformesactivoIsNotNull() {
        return informesactivoIsNotNull;
    }

    /**
     * Permite buscar cuando campo informesactivo es NOT NULL
     * @param informesactivoIsNotNull Valor de seteo.
     */
    public void setInformesactivoIsNotNull(boolean informesactivoIsNotNull) {
        this.informesactivoIsNotNull = informesactivoIsNotNull;
    }

    /**
     * Valor de busqueda de campo agrupacionestado
     * @return Boolean.
     */
    public Boolean getAgrupacionestado() {
        return agrupacionestado;
    }

    /**
     * Valor de busqueda de campo agrupacionestado
     * @param agrupacionestado Valor de seteo.
     */
    public void setAgrupacionestado(Boolean agrupacionestado) {
        this.agrupacionestado = agrupacionestado;
    }

    /**
     * Permite buscar cuando campo agrupacionestado es NULL
     * @return boolean.
     */
    public boolean isAgrupacionestadoIsNull() {
        return agrupacionestadoIsNull;
    }

    /**
     * Permite buscar cuando campo agrupacionestado es NULL
     * @param agrupacionestadoIsNull Valor de seteo.
     */
    public void setAgrupacionestadoIsNull(boolean agrupacionestadoIsNull) {
        this.agrupacionestadoIsNull = agrupacionestadoIsNull;
    }

    /**
     * Permite buscar cuando campo agrupacionestado es NOT NULL
     * @return boolean.
     */
    public boolean isAgrupacionestadoIsNotNull() {
        return agrupacionestadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo agrupacionestado es NOT NULL
     * @param agrupacionestadoIsNotNull Valor de seteo.
     */
    public void setAgrupacionestadoIsNotNull(boolean agrupacionestadoIsNotNull) {
        this.agrupacionestadoIsNotNull = agrupacionestadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo agrupacioncodorg
     * @return Boolean.
     */
    public Boolean getAgrupacioncodorg() {
        return agrupacioncodorg;
    }

    /**
     * Valor de busqueda de campo agrupacioncodorg
     * @param agrupacioncodorg Valor de seteo.
     */
    public void setAgrupacioncodorg(Boolean agrupacioncodorg) {
        this.agrupacioncodorg = agrupacioncodorg;
    }

    /**
     * Permite buscar cuando campo agrupacioncodorg es NULL
     * @return boolean.
     */
    public boolean isAgrupacioncodorgIsNull() {
        return agrupacioncodorgIsNull;
    }

    /**
     * Permite buscar cuando campo agrupacioncodorg es NULL
     * @param agrupacioncodorgIsNull Valor de seteo.
     */
    public void setAgrupacioncodorgIsNull(boolean agrupacioncodorgIsNull) {
        this.agrupacioncodorgIsNull = agrupacioncodorgIsNull;
    }

    /**
     * Permite buscar cuando campo agrupacioncodorg es NOT NULL
     * @return boolean.
     */
    public boolean isAgrupacioncodorgIsNotNull() {
        return agrupacioncodorgIsNotNull;
    }

    /**
     * Permite buscar cuando campo agrupacioncodorg es NOT NULL
     * @param agrupacioncodorgIsNotNull Valor de seteo.
     */
    public void setAgrupacioncodorgIsNotNull(boolean agrupacioncodorgIsNotNull) {
        this.agrupacioncodorgIsNotNull = agrupacioncodorgIsNotNull;
    }

    /**
     * Valor de busqueda de campo agrupacioncodsia
     * @return Boolean.
     */
    public Boolean getAgrupacioncodsia() {
        return agrupacioncodsia;
    }

    /**
     * Valor de busqueda de campo agrupacioncodsia
     * @param agrupacioncodsia Valor de seteo.
     */
    public void setAgrupacioncodsia(Boolean agrupacioncodsia) {
        this.agrupacioncodsia = agrupacioncodsia;
    }

    /**
     * Permite buscar cuando campo agrupacioncodsia es NULL
     * @return boolean.
     */
    public boolean isAgrupacioncodsiaIsNull() {
        return agrupacioncodsiaIsNull;
    }

    /**
     * Permite buscar cuando campo agrupacioncodsia es NULL
     * @param agrupacioncodsiaIsNull Valor de seteo.
     */
    public void setAgrupacioncodsiaIsNull(boolean agrupacioncodsiaIsNull) {
        this.agrupacioncodsiaIsNull = agrupacioncodsiaIsNull;
    }

    /**
     * Permite buscar cuando campo agrupacioncodsia es NOT NULL
     * @return boolean.
     */
    public boolean isAgrupacioncodsiaIsNotNull() {
        return agrupacioncodsiaIsNotNull;
    }

    /**
     * Permite buscar cuando campo agrupacioncodsia es NOT NULL
     * @param agrupacioncodsiaIsNotNull Valor de seteo.
     */
    public void setAgrupacioncodsiaIsNotNull(boolean agrupacioncodsiaIsNotNull) {
        this.agrupacioncodsiaIsNotNull = agrupacioncodsiaIsNotNull;
    }

    /**
     * Valor de busqueda de campo agrupacioncodorgpagador
     * @return Boolean.
     */
    public Boolean getAgrupacioncodorgpagador() {
        return agrupacioncodorgpagador;
    }

    /**
     * Valor de busqueda de campo agrupacioncodorgpagador
     * @param agrupacioncodorgpagador Valor de seteo.
     */
    public void setAgrupacioncodorgpagador(Boolean agrupacioncodorgpagador) {
        this.agrupacioncodorgpagador = agrupacioncodorgpagador;
    }

    /**
     * Permite buscar cuando campo agrupacioncodorgpagador es NULL
     * @return boolean.
     */
    public boolean isAgrupacioncodorgpagadorIsNull() {
        return agrupacioncodorgpagadorIsNull;
    }

    /**
     * Permite buscar cuando campo agrupacioncodorgpagador es NULL
     * @param agrupacioncodorgpagadorIsNull Valor de seteo.
     */
    public void setAgrupacioncodorgpagadorIsNull(boolean agrupacioncodorgpagadorIsNull) {
        this.agrupacioncodorgpagadorIsNull = agrupacioncodorgpagadorIsNull;
    }

    /**
     * Permite buscar cuando campo agrupacioncodorgpagador es NOT NULL
     * @return boolean.
     */
    public boolean isAgrupacioncodorgpagadorIsNotNull() {
        return agrupacioncodorgpagadorIsNotNull;
    }

    /**
     * Permite buscar cuando campo agrupacioncodorgpagador es NOT NULL
     * @param agrupacioncodorgpagadorIsNotNull Valor de seteo.
     */
    public void setAgrupacioncodorgpagadorIsNotNull(boolean agrupacioncodorgpagadorIsNotNull) {
        this.agrupacioncodorgpagadorIsNotNull = agrupacioncodorgpagadorIsNotNull;
    }

    /**
     * Valor de busqueda de campo informesdestinatarios
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
     * @param informesdestinatarios Valor de seteo.
     */
    public void setInformesdestinatarios(String informesdestinatarios) {
        this.informesdestinatarios = informesdestinatarios;
    }

    /**
     * Tipo de comparador para la busqueda por campo informesdestinatarios
     * @return informesdestinatariosComparator.
     */
    public TextComparator getInformesdestinatariosComparator() {
        return informesdestinatariosComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo informesdestinatarios
     * @param informesdestinatariosComparator Valor de seteo.
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
     * @param informesdestinatarios Valor a agregar.
     */
    public void addInformesdestinatariosIn(String informesdestinatarios) {
        this.informesdestinatariosIn.add(informesdestinatarios);
    }

    /**
     * Permite buscar cuando campo informesdestinatarios es NULL
     * @return boolean.
     */
    public boolean isInformesdestinatariosIsNull() {
        return informesdestinatariosIsNull;
    }

    /**
     * Permite buscar cuando campo informesdestinatarios es NULL
     * @param informesdestinatariosIsNull Valor de seteo.
     */
    public void setInformesdestinatariosIsNull(boolean informesdestinatariosIsNull) {
        this.informesdestinatariosIsNull = informesdestinatariosIsNull;
    }

    /**
     * Permite buscar cuando campo informesdestinatarios es NOT NULL
     * @return boolean.
     */
    public boolean isInformesdestinatariosIsNotNull() {
        return informesdestinatariosIsNotNull;
    }

    /**
     * Permite buscar cuando campo informesdestinatarios es NOT NULL
     * @param informesdestinatariosIsNotNull Valor de seteo.
     */
    public void setInformesdestinatariosIsNotNull(boolean informesdestinatariosIsNotNull) {
        this.informesdestinatariosIsNotNull = informesdestinatariosIsNotNull;
    }

    /**
     * Valor de busqueda de campo respFuncionalNombre
     * @return String.
     */
    public String getRespFuncionalNombre() {
        if (respFuncionalNombre != null) {
            switch (respFuncionalNombreComparator) {
	            case STARTS_WITH:
	                return respFuncionalNombre + "%";
	            case CONTAINS:
	                return "%" + respFuncionalNombre + "%";
	            case ENDS_WITH:
	                return "%" + respFuncionalNombre;
	            case EQUALS:
                	return respFuncionalNombre;
              	default:
	            	break;
            }
        }
        return respFuncionalNombre;
    }

    /**
     * Valor de busqueda de campo respFuncionalNombre
     * @param respFuncionalNombre Valor de seteo.
     */
    public void setRespFuncionalNombre(String respFuncionalNombre) {
        this.respFuncionalNombre = respFuncionalNombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalNombre
     * @return respFuncionalNombreComparator.
     */
    public TextComparator getRespFuncionalNombreComparator() {
        return respFuncionalNombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalNombre
     * @param respFuncionalNombreComparator Valor de seteo.
     */
    public void setRespFuncionalNombreComparator(TextComparator respFuncionalNombreComparator) {
        this.respFuncionalNombreComparator = respFuncionalNombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getRespFuncionalNombreIn() {
        return this.respFuncionalNombreIn;
    }

    /**
     * @param respFuncionalNombre Valor a agregar.
     */
    public void addRespFuncionalNombreIn(String respFuncionalNombre) {
        this.respFuncionalNombreIn.add(respFuncionalNombre);
    }

    /**
     * Permite buscar cuando campo respFuncionalNombre es NULL
     * @return boolean.
     */
    public boolean isRespFuncionalNombreIsNull() {
        return respFuncionalNombreIsNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalNombre es NULL
     * @param respFuncionalNombreIsNull Valor de seteo.
     */
    public void setRespFuncionalNombreIsNull(boolean respFuncionalNombreIsNull) {
        this.respFuncionalNombreIsNull = respFuncionalNombreIsNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalNombre es NOT NULL
     * @return boolean.
     */
    public boolean isRespFuncionalNombreIsNotNull() {
        return respFuncionalNombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalNombre es NOT NULL
     * @param respFuncionalNombreIsNotNull Valor de seteo.
     */
    public void setRespFuncionalNombreIsNotNull(boolean respFuncionalNombreIsNotNull) {
        this.respFuncionalNombreIsNotNull = respFuncionalNombreIsNotNull;
    }

    /**
     * Valor de busqueda de campo respFuncionalEmail
     * @return String.
     */
    public String getRespFuncionalEmail() {
        if (respFuncionalEmail != null) {
            switch (respFuncionalEmailComparator) {
	            case STARTS_WITH:
	                return respFuncionalEmail + "%";
	            case CONTAINS:
	                return "%" + respFuncionalEmail + "%";
	            case ENDS_WITH:
	                return "%" + respFuncionalEmail;
	            case EQUALS:
                	return respFuncionalEmail;
              	default:
	            	break;
            }
        }
        return respFuncionalEmail;
    }

    /**
     * Valor de busqueda de campo respFuncionalEmail
     * @param respFuncionalEmail Valor de seteo.
     */
    public void setRespFuncionalEmail(String respFuncionalEmail) {
        this.respFuncionalEmail = respFuncionalEmail;
    }

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalEmail
     * @return respFuncionalEmailComparator.
     */
    public TextComparator getRespFuncionalEmailComparator() {
        return respFuncionalEmailComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo respFuncionalEmail
     * @param respFuncionalEmailComparator Valor de seteo.
     */
    public void setRespFuncionalEmailComparator(TextComparator respFuncionalEmailComparator) {
        this.respFuncionalEmailComparator = respFuncionalEmailComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getRespFuncionalEmailIn() {
        return this.respFuncionalEmailIn;
    }

    /**
     * @param respFuncionalEmail Valor a agregar.
     */
    public void addRespFuncionalEmailIn(String respFuncionalEmail) {
        this.respFuncionalEmailIn.add(respFuncionalEmail);
    }

    /**
     * Permite buscar cuando campo respFuncionalEmail es NULL
     * @return boolean.
     */
    public boolean isRespFuncionalEmailIsNull() {
        return respFuncionalEmailIsNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalEmail es NULL
     * @param respFuncionalEmailIsNull Valor de seteo.
     */
    public void setRespFuncionalEmailIsNull(boolean respFuncionalEmailIsNull) {
        this.respFuncionalEmailIsNull = respFuncionalEmailIsNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalEmail es NOT NULL
     * @return boolean.
     */
    public boolean isRespFuncionalEmailIsNotNull() {
        return respFuncionalEmailIsNotNull;
    }

    /**
     * Permite buscar cuando campo respFuncionalEmail es NOT NULL
     * @param respFuncionalEmailIsNotNull Valor de seteo.
     */
    public void setRespFuncionalEmailIsNotNull(boolean respFuncionalEmailIsNotNull) {
        this.respFuncionalEmailIsNotNull = respFuncionalEmailIsNotNull;
    }

    /**
     * Valor de busqueda de campo respTecnicoNombre
     * @return String.
     */
    public String getRespTecnicoNombre() {
        if (respTecnicoNombre != null) {
            switch (respTecnicoNombreComparator) {
	            case STARTS_WITH:
	                return respTecnicoNombre + "%";
	            case CONTAINS:
	                return "%" + respTecnicoNombre + "%";
	            case ENDS_WITH:
	                return "%" + respTecnicoNombre;
	            case EQUALS:
                	return respTecnicoNombre;
              	default:
	            	break;
            }
        }
        return respTecnicoNombre;
    }

    /**
     * Valor de busqueda de campo respTecnicoNombre
     * @param respTecnicoNombre Valor de seteo.
     */
    public void setRespTecnicoNombre(String respTecnicoNombre) {
        this.respTecnicoNombre = respTecnicoNombre;
    }

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoNombre
     * @return respTecnicoNombreComparator.
     */
    public TextComparator getRespTecnicoNombreComparator() {
        return respTecnicoNombreComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoNombre
     * @param respTecnicoNombreComparator Valor de seteo.
     */
    public void setRespTecnicoNombreComparator(TextComparator respTecnicoNombreComparator) {
        this.respTecnicoNombreComparator = respTecnicoNombreComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getRespTecnicoNombreIn() {
        return this.respTecnicoNombreIn;
    }

    /**
     * @param respTecnicoNombre Valor a agregar.
     */
    public void addRespTecnicoNombreIn(String respTecnicoNombre) {
        this.respTecnicoNombreIn.add(respTecnicoNombre);
    }

    /**
     * Permite buscar cuando campo respTecnicoNombre es NULL
     * @return boolean.
     */
    public boolean isRespTecnicoNombreIsNull() {
        return respTecnicoNombreIsNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoNombre es NULL
     * @param respTecnicoNombreIsNull Valor de seteo.
     */
    public void setRespTecnicoNombreIsNull(boolean respTecnicoNombreIsNull) {
        this.respTecnicoNombreIsNull = respTecnicoNombreIsNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoNombre es NOT NULL
     * @return boolean.
     */
    public boolean isRespTecnicoNombreIsNotNull() {
        return respTecnicoNombreIsNotNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoNombre es NOT NULL
     * @param respTecnicoNombreIsNotNull Valor de seteo.
     */
    public void setRespTecnicoNombreIsNotNull(boolean respTecnicoNombreIsNotNull) {
        this.respTecnicoNombreIsNotNull = respTecnicoNombreIsNotNull;
    }

    /**
     * Valor de busqueda de campo respTecnicoEmail
     * @return String.
     */
    public String getRespTecnicoEmail() {
        if (respTecnicoEmail != null) {
            switch (respTecnicoEmailComparator) {
	            case STARTS_WITH:
	                return respTecnicoEmail + "%";
	            case CONTAINS:
	                return "%" + respTecnicoEmail + "%";
	            case ENDS_WITH:
	                return "%" + respTecnicoEmail;
	            case EQUALS:
                	return respTecnicoEmail;
              	default:
	            	break;
            }
        }
        return respTecnicoEmail;
    }

    /**
     * Valor de busqueda de campo respTecnicoEmail
     * @param respTecnicoEmail Valor de seteo.
     */
    public void setRespTecnicoEmail(String respTecnicoEmail) {
        this.respTecnicoEmail = respTecnicoEmail;
    }

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoEmail
     * @return respTecnicoEmailComparator.
     */
    public TextComparator getRespTecnicoEmailComparator() {
        return respTecnicoEmailComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo respTecnicoEmail
     * @param respTecnicoEmailComparator Valor de seteo.
     */
    public void setRespTecnicoEmailComparator(TextComparator respTecnicoEmailComparator) {
        this.respTecnicoEmailComparator = respTecnicoEmailComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getRespTecnicoEmailIn() {
        return this.respTecnicoEmailIn;
    }

    /**
     * @param respTecnicoEmail Valor a agregar.
     */
    public void addRespTecnicoEmailIn(String respTecnicoEmail) {
        this.respTecnicoEmailIn.add(respTecnicoEmail);
    }

    /**
     * Permite buscar cuando campo respTecnicoEmail es NULL
     * @return boolean.
     */
    public boolean isRespTecnicoEmailIsNull() {
        return respTecnicoEmailIsNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoEmail es NULL
     * @param respTecnicoEmailIsNull Valor de seteo.
     */
    public void setRespTecnicoEmailIsNull(boolean respTecnicoEmailIsNull) {
        this.respTecnicoEmailIsNull = respTecnicoEmailIsNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoEmail es NOT NULL
     * @return boolean.
     */
    public boolean isRespTecnicoEmailIsNotNull() {
        return respTecnicoEmailIsNotNull;
    }

    /**
     * Permite buscar cuando campo respTecnicoEmail es NOT NULL
     * @param respTecnicoEmailIsNotNull Valor de seteo.
     */
    public void setRespTecnicoEmailIsNotNull(boolean respTecnicoEmailIsNotNull) {
        this.respTecnicoEmailIsNotNull = respTecnicoEmailIsNotNull;
    }

    /**
     * Valor de busqueda de campo proveedorusuariosms
     * @return String.
     */
    public String getProveedorusuariosms() {
        if (proveedorusuariosms != null) {
            switch (proveedorusuariosmsComparator) {
	            case STARTS_WITH:
	                return proveedorusuariosms + "%";
	            case CONTAINS:
	                return "%" + proveedorusuariosms + "%";
	            case ENDS_WITH:
	                return "%" + proveedorusuariosms;
	            case EQUALS:
                	return proveedorusuariosms;
              	default:
	            	break;
            }
        }
        return proveedorusuariosms;
    }

    /**
     * Valor de busqueda de campo proveedorusuariosms
     * @param proveedorusuariosms Valor de seteo.
     */
    public void setProveedorusuariosms(String proveedorusuariosms) {
        this.proveedorusuariosms = proveedorusuariosms;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorusuariosms
     * @return proveedorusuariosmsComparator.
     */
    public TextComparator getProveedorusuariosmsComparator() {
        return proveedorusuariosmsComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorusuariosms
     * @param proveedorusuariosmsComparator Valor de seteo.
     */
    public void setProveedorusuariosmsComparator(TextComparator proveedorusuariosmsComparator) {
        this.proveedorusuariosmsComparator = proveedorusuariosmsComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getProveedorusuariosmsIn() {
        return this.proveedorusuariosmsIn;
    }

    /**
     * @param proveedorusuariosms Valor a agregar.
     */
    public void addProveedorusuariosmsIn(String proveedorusuariosms) {
        this.proveedorusuariosmsIn.add(proveedorusuariosms);
    }

    /**
     * Permite buscar cuando campo proveedorusuariosms es NULL
     * @return boolean.
     */
    public boolean isProveedorusuariosmsIsNull() {
        return proveedorusuariosmsIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorusuariosms es NULL
     * @param proveedorusuariosmsIsNull Valor de seteo.
     */
    public void setProveedorusuariosmsIsNull(boolean proveedorusuariosmsIsNull) {
        this.proveedorusuariosmsIsNull = proveedorusuariosmsIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorusuariosms es NOT NULL
     * @return boolean.
     */
    public boolean isProveedorusuariosmsIsNotNull() {
        return proveedorusuariosmsIsNotNull;
    }

    /**
     * Permite buscar cuando campo proveedorusuariosms es NOT NULL
     * @param proveedorusuariosmsIsNotNull Valor de seteo.
     */
    public void setProveedorusuariosmsIsNotNull(boolean proveedorusuariosmsIsNotNull) {
        this.proveedorusuariosmsIsNotNull = proveedorusuariosmsIsNotNull;
    }

    /**
     * Valor de busqueda de campo proveedorpasswordsms
     * @return String.
     */
    public String getProveedorpasswordsms() {
        if (proveedorpasswordsms != null) {
            switch (proveedorpasswordsmsComparator) {
	            case STARTS_WITH:
	                return proveedorpasswordsms + "%";
	            case CONTAINS:
	                return "%" + proveedorpasswordsms + "%";
	            case ENDS_WITH:
	                return "%" + proveedorpasswordsms;
	            case EQUALS:
                	return proveedorpasswordsms;
              	default:
	            	break;
            }
        }
        return proveedorpasswordsms;
    }

    /**
     * Valor de busqueda de campo proveedorpasswordsms
     * @param proveedorpasswordsms Valor de seteo.
     */
    public void setProveedorpasswordsms(String proveedorpasswordsms) {
        this.proveedorpasswordsms = proveedorpasswordsms;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorpasswordsms
     * @return proveedorpasswordsmsComparator.
     */
    public TextComparator getProveedorpasswordsmsComparator() {
        return proveedorpasswordsmsComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo proveedorpasswordsms
     * @param proveedorpasswordsmsComparator Valor de seteo.
     */
    public void setProveedorpasswordsmsComparator(TextComparator proveedorpasswordsmsComparator) {
        this.proveedorpasswordsmsComparator = proveedorpasswordsmsComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getProveedorpasswordsmsIn() {
        return this.proveedorpasswordsmsIn;
    }

    /**
     * @param proveedorpasswordsms Valor a agregar.
     */
    public void addProveedorpasswordsmsIn(String proveedorpasswordsms) {
        this.proveedorpasswordsmsIn.add(proveedorpasswordsms);
    }

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NULL
     * @return boolean.
     */
    public boolean isProveedorpasswordsmsIsNull() {
        return proveedorpasswordsmsIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NULL
     * @param proveedorpasswordsmsIsNull Valor de seteo.
     */
    public void setProveedorpasswordsmsIsNull(boolean proveedorpasswordsmsIsNull) {
        this.proveedorpasswordsmsIsNull = proveedorpasswordsmsIsNull;
    }

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NOT NULL
     * @return boolean.
     */
    public boolean isProveedorpasswordsmsIsNotNull() {
        return proveedorpasswordsmsIsNotNull;
    }

    /**
     * Permite buscar cuando campo proveedorpasswordsms es NOT NULL
     * @param proveedorpasswordsmsIsNotNull Valor de seteo.
     */
    public void setProveedorpasswordsmsIsNotNull(boolean proveedorpasswordsmsIsNotNull) {
        this.proveedorpasswordsmsIsNotNull = proveedorpasswordsmsIsNotNull;
    }

    /**
     * Valor de busqueda de campo multiorganismo
     * @return Boolean.
     */
    public Boolean getMultiorganismo() {
        return multiorganismo;
    }

    /**
     * Valor de busqueda de campo multiorganismo
     * @param multiorganismo Valor de seteo.
     */
    public void setMultiorganismo(Boolean multiorganismo) {
        this.multiorganismo = multiorganismo;
    }

    /**
     * Permite buscar cuando campo multiorganismo es NULL
     * @return boolean.
     */
    public boolean isMultiorganismoIsNull() {
        return multiorganismoIsNull;
    }

    /**
     * Permite buscar cuando campo multiorganismo es NULL
     * @param multiorganismoIsNull Valor de seteo.
     */
    public void setMultiorganismoIsNull(boolean multiorganismoIsNull) {
        this.multiorganismoIsNull = multiorganismoIsNull;
    }

    /**
     * Permite buscar cuando campo multiorganismo es NOT NULL
     * @return boolean.
     */
    public boolean isMultiorganismoIsNotNull() {
        return multiorganismoIsNotNull;
    }

    /**
     * Permite buscar cuando campo multiorganismo es NOT NULL
     * @param multiorganismoIsNotNull Valor de seteo.
     */
    public void setMultiorganismoIsNotNull(boolean multiorganismoIsNotNull) {
        this.multiorganismoIsNotNull = multiorganismoIsNotNull;
    }

    /**
     * Valor de busqueda de campo exclusivo
     * @return Boolean.
     */
    public Boolean getExclusivo() {
        return exclusivo;
    }

    /**
     * Valor de busqueda de campo exclusivo
     * @param exclusivo Valor de seteo.
     */
    public void setExclusivo(Boolean exclusivo) {
        this.exclusivo = exclusivo;
    }

    /**
     * Permite buscar cuando campo exclusivo es NULL
     * @return boolean.
     */
    public boolean isExclusivoIsNull() {
        return exclusivoIsNull;
    }

    /**
     * Permite buscar cuando campo exclusivo es NULL
     * @param premiumIsNull Valor de seteo.
     */
    public void setExclusivoIsNull(boolean exclusivoIsNull) {
        this.exclusivoIsNull = exclusivoIsNull;
    }

    /**
     * Permite buscar cuando campo exclusivo es NOT NULL
     * @return boolean.
     */
    public boolean isExclusivoIsNotNull() {
        return premiumIsNotNull;
    }

    /**
     * Permite buscar cuando campo exclusivo es NOT NULL
     * @param premiumIsNotNull Valor de seteo.
     */
    public void setExclusivoIsNotNull(boolean exclusivoIsNotNull) {
        this.exclusivoIsNotNull = exclusivoIsNotNull;
    }
    
    /**
     * Valor de busqueda de campo premium
     * @return Boolean.
     */
    public Boolean getPremium() {
        return premium;
    }

    /**
     * Valor de busqueda de campo premium
     * @param premium Valor de seteo.
     */
    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    /**
     * Permite buscar cuando campo premium es NULL
     * @return boolean.
     */
    public boolean isPremiumIsNull() {
        return premiumIsNull;
    }

    /**
     * Permite buscar cuando campo premium es NULL
     * @param premiumIsNull Valor de seteo.
     */
    public void setPremiumIsNull(boolean premiumIsNull) {
        this.premiumIsNull = premiumIsNull;
    }

    /**
     * Permite buscar cuando campo premium es NOT NULL
     * @return boolean.
     */
    public boolean isPremiumIsNotNull() {
        return premiumIsNotNull;
    }

    /**
     * Permite buscar cuando campo premium es NOT NULL
     * @param premiumIsNotNull Valor de seteo.
     */
    public void setPremiumIsNotNull(boolean premiumIsNotNull) {
        this.premiumIsNotNull = premiumIsNotNull;
    }

    /**
     * Valor de busqueda de campo numeroMaxReenvios
     * @return Integer.
     */
    public Integer getNumeroMaxReenvios() {
        return numeroMaxReenvios;
    }

    /**
     * Valor de busqueda de campo numeroMaxReenvios
     * @param numeroMaxReenvios Valor de seteo.
     */
    public void setNumeroMaxReenvios(Integer numeroMaxReenvios) {
        this.numeroMaxReenvios = numeroMaxReenvios;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getNumeroMaxReenviosIn() {
        return this.numeroMaxReenviosIn;
    }

    /**
     * @param numeroMaxReenvios Valor a agregar.
     */
    public void addNumeroMaxReenviosIn(Integer numeroMaxReenvios) {
        this.numeroMaxReenviosIn.add(numeroMaxReenvios);
    }

    /**
     * Permite buscar cuando campo numeroMaxReenvios es NULL
     * @return boolean.
     */
    public boolean isNumeroMaxReenviosIsNull() {
        return numeroMaxReenviosIsNull;
    }

    /**
     * Permite buscar cuando campo numeroMaxReenvios es NULL
     * @param numeroMaxReenviosIsNull Valor de seteo.
     */
    public void setNumeroMaxReenviosIsNull(boolean numeroMaxReenviosIsNull) {
        this.numeroMaxReenviosIsNull = numeroMaxReenviosIsNull;
    }

    /**
     * Permite buscar cuando campo numeroMaxReenvios es NOT NULL
     * @return boolean.
     */
    public boolean isNumeroMaxReenviosIsNotNull() {
        return numeroMaxReenviosIsNotNull;
    }

    /**
     * Permite buscar cuando campo numeroMaxReenvios es NOT NULL
     * @param numeroMaxReenviosIsNotNull Valor de seteo.
     */
    public void setNumeroMaxReenviosIsNotNull(boolean numeroMaxReenviosIsNotNull) {
        this.numeroMaxReenviosIsNotNull = numeroMaxReenviosIsNotNull;
    }

    /**
     * Valor de busqueda de campo reintentosConsultaEStado
     * @return Integer.
     */
    public Integer getReintentosConsultaEStado() {
        return reintentosConsultaEStado;
    }

    /**
     * Valor de busqueda de campo reintentosConsultaEStado
     * @param reintentosConsultaEStado Valor de seteo.
     */
    public void setReintentosConsultaEStado(Integer reintentosConsultaEStado) {
        this.reintentosConsultaEStado = reintentosConsultaEStado;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getReintentosConsultaEStadoIn() {
        return this.reintentosConsultaEStadoIn;
    }

    /**
     * @param reintentosConsultaEStado Valor a agregar.
     */
    public void addReintentosConsultaEStadoIn(Integer reintentosConsultaEStado) {
        this.reintentosConsultaEStadoIn.add(reintentosConsultaEStado);
    }

    /**
     * Permite buscar cuando campo reintentosConsultaEStado es NULL
     * @return boolean.
     */
    public boolean isReintentosConsultaEStadoIsNull() {
        return reintentosConsultaEStadoIsNull;
    }

    /**
     * Permite buscar cuando campo reintentosConsultaEStado es NULL
     * @param reintentosConsultaEStadoIsNull Valor de seteo.
     */
    public void setReintentosConsultaEStadoIsNull(boolean reintentosConsultaEStadoIsNull) {
        this.reintentosConsultaEStadoIsNull = reintentosConsultaEStadoIsNull;
    }

    /**
     * Permite buscar cuando campo reintentosConsultaEStado es NOT NULL
     * @return boolean.
     */
    public boolean isReintentosConsultaEStadoIsNotNull() {
        return reintentosConsultaEStadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo reintentosConsultaEStado es NOT NULL
     * @param reintentosConsultaEStadoIsNotNull Valor de seteo.
     */
    public void setReintentosConsultaEStadoIsNotNull(boolean reintentosConsultaEStadoIsNotNull) {
        this.reintentosConsultaEStadoIsNotNull = reintentosConsultaEStadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo caducidad
     * @return Integer.
     */
    public Integer getCaducidad() {
        return caducidad;
    }

    /**
     * Valor de busqueda de campo caducidad
     * @param caducidad Valor de seteo.
     */
    public void setCaducidad(Integer caducidad) {
        this.caducidad = caducidad;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getCaducidadIn() {
        return this.caducidadIn;
    }

    /**
     * @param caducidad Valor a agregar.
     */
    public void addCaducidadIn(Integer caducidad) {
        this.caducidadIn.add(caducidad);
    }

    /**
     * Permite buscar cuando campo caducidad es NULL
     * @return boolean.
     */
    public boolean isCaducidadIsNull() {
        return caducidadIsNull;
    }

    /**
     * Permite buscar cuando campo caducidad es NULL
     * @param caducidadIsNull Valor de seteo.
     */
    public void setCaducidadIsNull(boolean caducidadIsNull) {
        this.caducidadIsNull = caducidadIsNull;
    }

    /**
     * Permite buscar cuando campo caducidad es NOT NULL
     * @return boolean.
     */
    public boolean isCaducidadIsNotNull() {
        return caducidadIsNotNull;
    }

    /**
     * Permite buscar cuando campo caducidad es NOT NULL
     * @param caducidadIsNotNull Valor de seteo.
     */
    public void setCaducidadIsNotNull(boolean caducidadIsNotNull) {
        this.caducidadIsNotNull = caducidadIsNotNull;
    }

    /**
     * Valor de busqueda de campo caducidadWebPush
     * @return Integer.
     */
    public Integer getCaducidadWebPush() {
        return caducidadWebPush;
    }

    /**
     * Valor de busqueda de campo caducidadWebPush
     * @param caducidadWebPush Valor de seteo.
     */
    public void setCaducidadWebPush(Integer caducidadWebPush) {
        this.caducidadWebPush = caducidadWebPush;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getCaducidadWebPushIn() {
        return this.caducidadWebPushIn;
    }

    /**
     * @param caducidadWebPush Valor a agregar.
     */
    public void addCaducidadWebPushIn(Integer caducidadWebPush) {
        this.caducidadWebPushIn.add(caducidadWebPush);
    }

    /**
     * Permite buscar cuando campo caducidadWebPush es NULL
     * @return boolean.
     */
    public boolean isCaducidadWebPushIsNull() {
        return caducidadWebPushIsNull;
    }

    /**
     * Permite buscar cuando campo caducidadWebPush es NULL
     * @param caducidadWebPushIsNull Valor de seteo.
     */
    public void setCaducidadWebPushIsNull(boolean caducidadWebPushIsNull) {
        this.caducidadWebPushIsNull = caducidadWebPushIsNull;
    }

    /**
     * Permite buscar cuando campo caducidadWebPush es NOT NULL
     * @return boolean.
     */
    public boolean isCaducidadWebPushIsNotNull() {
        return caducidadWebPushIsNotNull;
    }

    /**
     * Permite buscar cuando campo caducidadWebPush es NOT NULL
     * @param caducidadWebPushIsNotNull Valor de seteo.
     */
    public void setCaducidadWebPushIsNotNull(boolean caducidadWebPushIsNotNull) {
        this.caducidadWebPushIsNotNull = caducidadWebPushIsNotNull;
    }

    /**
     * Valor de busqueda de campo vapidPublicKey
     * @return String.
     */
    public String getVapidPublicKey() {
        if (vapidPublicKey != null) {
            switch (vapidPublicKeyComparator) {
	            case STARTS_WITH:
	                return vapidPublicKey + "%";
	            case CONTAINS:
	                return "%" + vapidPublicKey + "%";
	            case ENDS_WITH:
	                return "%" + vapidPublicKey;
	            case EQUALS:
                	return vapidPublicKey;
              	default:
	            	break;
            }
        }
        return vapidPublicKey;
    }

    /**
     * Valor de busqueda de campo vapidPublicKey
     * @param vapidPublicKey Valor de seteo.
     */
    public void setVapidPublicKey(String vapidPublicKey) {
        this.vapidPublicKey = vapidPublicKey;
    }

    /**
     * Tipo de comparador para la busqueda por campo vapidPublicKey
     * @return vapidPublicKeyComparator.
     */
    public TextComparator getVapidPublicKeyComparator() {
        return vapidPublicKeyComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo vapidPublicKey
     * @param vapidPublicKeyComparator Valor de seteo.
     */
    public void setVapidPublicKeyComparator(TextComparator vapidPublicKeyComparator) {
        this.vapidPublicKeyComparator = vapidPublicKeyComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getVapidPublicKeyIn() {
        return this.vapidPublicKeyIn;
    }

    /**
     * @param vapidPublicKey Valor a agregar.
     */
    public void addVapidPublicKeyIn(String vapidPublicKey) {
        this.vapidPublicKeyIn.add(vapidPublicKey);
    }

    /**
     * Permite buscar cuando campo vapidPublicKey es NULL
     * @return boolean.
     */
    public boolean isVapidPublicKeyIsNull() {
        return vapidPublicKeyIsNull;
    }

    /**
     * Permite buscar cuando campo vapidPublicKey es NULL
     * @param vapidPublicKeyIsNull Valor de seteo.
     */
    public void setVapidPublicKeyIsNull(boolean vapidPublicKeyIsNull) {
        this.vapidPublicKeyIsNull = vapidPublicKeyIsNull;
    }

    /**
     * Permite buscar cuando campo vapidPublicKey es NOT NULL
     * @return boolean.
     */
    public boolean isVapidPublicKeyIsNotNull() {
        return vapidPublicKeyIsNotNull;
    }

    /**
     * Permite buscar cuando campo vapidPublicKey es NOT NULL
     * @param vapidPublicKeyIsNotNull Valor de seteo.
     */
    public void setVapidPublicKeyIsNotNull(boolean vapidPublicKeyIsNotNull) {
        this.vapidPublicKeyIsNotNull = vapidPublicKeyIsNotNull;
    }

    /**
     * Valor de busqueda de campo vapidPrivateKey
     * @return String.
     */
    public String getVapidPrivateKey() {
        if (vapidPrivateKey != null) {
            switch (vapidPrivateKeyComparator) {
	            case STARTS_WITH:
	                return vapidPrivateKey + "%";
	            case CONTAINS:
	                return "%" + vapidPrivateKey + "%";
	            case ENDS_WITH:
	                return "%" + vapidPrivateKey;
	            case EQUALS:
                	return vapidPrivateKey;
              	default:
	            	break;
            }
        }
        return vapidPrivateKey;
    }

    /**
     * Valor de busqueda de campo vapidPrivateKey
     * @param vapidPrivateKey Valor de seteo.
     */
    public void setVapidPrivateKey(String vapidPrivateKey) {
        this.vapidPrivateKey = vapidPrivateKey;
    }

    /**
     * Tipo de comparador para la busqueda por campo vapidPrivateKey
     * @return vapidPrivateKeyComparator.
     */
    public TextComparator getVapidPrivateKeyComparator() {
        return vapidPrivateKeyComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo vapidPrivateKey
     * @param vapidPrivateKeyComparator Valor de seteo.
     */
    public void setVapidPrivateKeyComparator(TextComparator vapidPrivateKeyComparator) {
        this.vapidPrivateKeyComparator = vapidPrivateKeyComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getVapidPrivateKeyIn() {
        return this.vapidPrivateKeyIn;
    }

    /**
     * @param vapidPrivateKey Valor a agregar.
     */
    public void addVapidPrivateKeyIn(String vapidPrivateKey) {
        this.vapidPrivateKeyIn.add(vapidPrivateKey);
    }

    /**
     * Permite buscar cuando campo vapidPrivateKey es NULL
     * @return boolean.
     */
    public boolean isVapidPrivateKeyIsNull() {
        return vapidPrivateKeyIsNull;
    }

    /**
     * Permite buscar cuando campo vapidPrivateKey es NULL
     * @param vapidPrivateKeyIsNull Valor de seteo.
     */
    public void setVapidPrivateKeyIsNull(boolean vapidPrivateKeyIsNull) {
        this.vapidPrivateKeyIsNull = vapidPrivateKeyIsNull;
    }

    /**
     * Permite buscar cuando campo vapidPrivateKey es NOT NULL
     * @return boolean.
     */
    public boolean isVapidPrivateKeyIsNotNull() {
        return vapidPrivateKeyIsNotNull;
    }

    /**
     * Permite buscar cuando campo vapidPrivateKey es NOT NULL
     * @param vapidPrivateKeyIsNotNull Valor de seteo.
     */
    public void setVapidPrivateKeyIsNotNull(boolean vapidPrivateKeyIsNotNull) {
        this.vapidPrivateKeyIsNotNull = vapidPrivateKeyIsNotNull;
    }

    
    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblAplicaciones() {
        return innerJoinTblAplicaciones;
    }

    /**
     * @param innerJoinTblAplicaciones Valor de seteo.
     */
    public void setInnerJoinTblAplicaciones(boolean innerJoinTblAplicaciones) {
        this.innerJoinTblAplicaciones = innerJoinTblAplicaciones;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblAplicaciones() {
        return leftJoinTblAplicaciones;
    }

    /**
     * @param leftJoinTblAplicaciones Valor de seteo.
     */
    public void setLeftJoinTblAplicaciones(boolean leftJoinTblAplicaciones) {
        this.leftJoinTblAplicaciones = leftJoinTblAplicaciones;
    }

    /**
     * @return boolean.
     */
    public boolean isInnerJoinTblCanales() {
        return innerJoinTblCanales;
    }

    /**
     * @param innerJoinTblCanales Valor de seteo.
     */
    public void setInnerJoinTblCanales(boolean innerJoinTblCanales) {
        this.innerJoinTblCanales = innerJoinTblCanales;
    }

    /**
     * @return boolean.
     */
    public boolean isLeftJoinTblCanales() {
        return leftJoinTblCanales;
    }

    /**
     * @param leftJoinTblCanales Valor de seteo.
     */
    public void setLeftJoinTblCanales(boolean leftJoinTblCanales) {
        this.leftJoinTblCanales = leftJoinTblCanales;
    }

    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getServicioid() != null) {
            criteria.add(Restrictions.eq(SERVICIOID, getServicioid()));
        }

        if (getServicioidIn().size() > 0) {
            criteria.add(Restrictions.in(SERVICIOID, getServicioidIn()));
        }

        // Campo entidad padre tblAplicaciones
        
        // Si se hace join fetch con el padre
        Criteria tblAplicacionesCriteria = null;
        if (isInnerJoinTblAplicaciones()) {
            tblAplicacionesCriteria = criteria.createCriteria(TBLAPLICACIONES, "a_" + TBLAPLICACIONES, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblAplicaciones()) {
            tblAplicacionesCriteria = criteria.createCriteria(TBLAPLICACIONES, "a_" + TBLAPLICACIONES, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblAplicaciones() != null) {
            if (getTblAplicaciones().getAplicacionid() == null) {
                if (tblAplicacionesCriteria == null) {
                    tblAplicacionesCriteria = criteria.createCriteria(TBLAPLICACIONES, "a_" + TBLAPLICACIONES);
                }
                getTblAplicaciones().addCriteria(tblAplicacionesCriteria, useOrder);
            } else {
                TblAplicaciones parent = new TblAplicaciones();
                parent.setAplicacionid(getTblAplicaciones().getAplicacionid());
                criteria.add(Restrictions.eq(TBLAPLICACIONES, parent));
            }
        }

        if (getTblAplicacionesIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLAPLICACIONES, getTblAplicacionesIdIn()));
        }

        if (isTblAplicacionesIsNull()) {
            criteria.add(Restrictions.isNull(TBLAPLICACIONES));
        }

        if (isTblAplicacionesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLAPLICACIONES));
        }

        // Campo entidad padre tblCanales
        
        // Si se hace join fetch con el padre
        Criteria tblCanalesCriteria = null;
        if (isInnerJoinTblCanales()) {
            tblCanalesCriteria = criteria.createCriteria(TBLCANALES, "a_" + TBLCANALES, CriteriaSpecification.INNER_JOIN);
        } else if (isLeftJoinTblCanales()) {
            tblCanalesCriteria = criteria.createCriteria(TBLCANALES, "a_" + TBLCANALES, CriteriaSpecification.LEFT_JOIN);
        }
        
        if (getTblCanales() != null) {
            if (getTblCanales().getCanalid() == null) {
                if (tblCanalesCriteria == null) {
                    tblCanalesCriteria = criteria.createCriteria(TBLCANALES, "a_" + TBLCANALES);
                }
                getTblCanales().addCriteria(tblCanalesCriteria, useOrder);
            } else {
                TblCanales parent = new TblCanales();
                parent.setCanalid(getTblCanales().getCanalid());
                criteria.add(Restrictions.eq(TBLCANALES, parent));
            }
        }

        if (getTblCanalesIdIn().size() > 0) {
            criteria.add(Restrictions.in(TBLCANALES, getTblCanalesIdIn()));
        }

        if (isTblCanalesIsNull()) {
            criteria.add(Restrictions.isNull(TBLCANALES));
        }

        if (isTblCanalesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TBLCANALES));
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

        if (getDescripcion() != null) {
            if (getDescripcionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DESCRIPCION, getDescripcion()));
            } 
            else if (getDescripcionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DESCRIPCION, getDescripcion()));
            }
            else if (getDescripcionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DESCRIPCION, getDescripcion())));
            }
            else {
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
            } 
            else if (getCreadoporComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CREADOPOR, getCreadopor()));
            }
            else if (getCreadoporComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CREADOPOR, getCreadopor())));
            }
            else {
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
            } 
            else if (getModificadoporComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MODIFICADOPOR, getModificadopor()));
            }
            else if (getModificadoporComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MODIFICADOPOR, getModificadopor())));
            }
            else {
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

        if (getExternalid() != null) {
            criteria.add(Restrictions.eq(EXTERNALID, getExternalid()));
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
        
        if (getNmaxreenvios() != null) {
            criteria.add(Restrictions.eq(NMAXREENVIOS, getNmaxreenvios()));
        }

        if (getNmaxreenviosIn().size() > 0) {
            criteria.add(Restrictions.in(NMAXREENVIOS, getNmaxreenviosIn()));
        }

        if (isNmaxreenviosIsNull()) {
            criteria.add(Restrictions.isNull(NMAXREENVIOS));
        }

        if (isNmaxreenviosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NMAXREENVIOS));
        }

        if (getHeadersms() != null) {
            if (getHeadersmsComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(HEADERSMS, getHeadersms()));
            } 
            else if (getHeadersmsComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(HEADERSMS, getHeadersms()));
            }
            else if (getHeadersmsComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(HEADERSMS, getHeadersms())));
            }
            else {
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
            } 
            else if (getCuentaenvioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CUENTAENVIO, getCuentaenvio()));
            }
            else if (getCuentaenvioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CUENTAENVIO, getCuentaenvio())));
            }
            else {
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
            } 
            else if (getNombrecuentaenvioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRECUENTAENVIO, getNombrecuentaenvio()));
            }
            else if (getNombrecuentaenvioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRECUENTAENVIO, getNombrecuentaenvio())));
            }
            else {
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

        if (getMotivohistorificacion() != null) {
            if (getMotivohistorificacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MOTIVOHISTORIFICACION, getMotivohistorificacion()));
            } 
            else if (getMotivohistorificacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MOTIVOHISTORIFICACION, getMotivohistorificacion()));
            }
            else if (getMotivohistorificacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MOTIVOHISTORIFICACION, getMotivohistorificacion())));
            }
            else {
                criteria.add(Restrictions.like(MOTIVOHISTORIFICACION, getMotivohistorificacion()));
            }
        }

        if (getMotivohistorificacionIn().size() > 0) {
            criteria.add(Restrictions.in(MOTIVOHISTORIFICACION, getMotivohistorificacionIn()));
        }

        if (isMotivohistorificacionIsNull()) {
            criteria.add(Restrictions.isNull(MOTIVOHISTORIFICACION));
        }

        if (isMotivohistorificacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MOTIVOHISTORIFICACION));
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

        if (getMotivoconservacion() != null) {
            if (getMotivoconservacionComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(MOTIVOCONSERVACION, getMotivoconservacion()));
            } 
            else if (getMotivoconservacionComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(MOTIVOCONSERVACION, getMotivoconservacion()));
            }
            else if (getMotivoconservacionComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(MOTIVOCONSERVACION, getMotivoconservacion())));
            }
            else {
                criteria.add(Restrictions.like(MOTIVOCONSERVACION, getMotivoconservacion()));
            }
        }

        if (getMotivoconservacionIn().size() > 0) {
            criteria.add(Restrictions.in(MOTIVOCONSERVACION, getMotivoconservacionIn()));
        }

        if (isMotivoconservacionIsNull()) {
            criteria.add(Restrictions.isNull(MOTIVOCONSERVACION));
        }

        if (isMotivoconservacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MOTIVOCONSERVACION));
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
            } 
            else if (getNombreloteenvioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBRELOTEENVIO, getNombreloteenvio()));
            }
            else if (getNombreloteenvioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBRELOTEENVIO, getNombreloteenvio())));
            }
            else {
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
            } 
            else if (getFcmprojectkeyComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(FCMPROJECTKEY, getFcmprojectkey()));
            }
            else if (getFcmprojectkeyComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(FCMPROJECTKEY, getFcmprojectkey())));
            }
            else {
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
            } 
            else if (getApnsrutacertificadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(APNSRUTACERTIFICADO, getApnsrutacertificado()));
            }
            else if (getApnsrutacertificadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APNSRUTACERTIFICADO, getApnsrutacertificado())));
            }
            else {
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
            } 
            else if (getApnspasswordcertificadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(APNSPASSWORDCERTIFICADO, getApnspasswordcertificado()));
            }
            else if (getApnspasswordcertificadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(APNSPASSWORDCERTIFICADO, getApnspasswordcertificado())));
            }
            else {
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
            } 
            else if (getEndpointComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ENDPOINT, getEndpoint()));
            }
            else if (getEndpointComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ENDPOINT, getEndpoint())));
            }
            else {
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
            } 
            else if (getInformesdestinatariosComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(INFORMESDESTINATARIOS, getInformesdestinatarios()));
            }
            else if (getInformesdestinatariosComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(INFORMESDESTINATARIOS, getInformesdestinatarios())));
            }
            else {
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

        if (getRespFuncionalNombre() != null) {
            if (getRespFuncionalNombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(RESPFUNCIONALNOMBRE, getRespFuncionalNombre()));
            } 
            else if (getRespFuncionalNombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(RESPFUNCIONALNOMBRE, getRespFuncionalNombre()));
            }
            else if (getRespFuncionalNombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(RESPFUNCIONALNOMBRE, getRespFuncionalNombre())));
            }
            else {
                criteria.add(Restrictions.like(RESPFUNCIONALNOMBRE, getRespFuncionalNombre()));
            }
        }

        if (getRespFuncionalNombreIn().size() > 0) {
            criteria.add(Restrictions.in(RESPFUNCIONALNOMBRE, getRespFuncionalNombreIn()));
        }

        if (isRespFuncionalNombreIsNull()) {
            criteria.add(Restrictions.isNull(RESPFUNCIONALNOMBRE));
        }

        if (isRespFuncionalNombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(RESPFUNCIONALNOMBRE));
        }

        if (getRespFuncionalEmail() != null) {
            if (getRespFuncionalEmailComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(RESPFUNCIONALEMAIL, getRespFuncionalEmail()));
            } 
            else if (getRespFuncionalEmailComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(RESPFUNCIONALEMAIL, getRespFuncionalEmail()));
            }
            else if (getRespFuncionalEmailComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(RESPFUNCIONALEMAIL, getRespFuncionalEmail())));
            }
            else {
                criteria.add(Restrictions.like(RESPFUNCIONALEMAIL, getRespFuncionalEmail()));
            }
        }

        if (getRespFuncionalEmailIn().size() > 0) {
            criteria.add(Restrictions.in(RESPFUNCIONALEMAIL, getRespFuncionalEmailIn()));
        }

        if (isRespFuncionalEmailIsNull()) {
            criteria.add(Restrictions.isNull(RESPFUNCIONALEMAIL));
        }

        if (isRespFuncionalEmailIsNotNull()) {
            criteria.add(Restrictions.isNotNull(RESPFUNCIONALEMAIL));
        }

        if (getRespTecnicoNombre() != null) {
            if (getRespTecnicoNombreComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(RESPTECNICONOMBRE, getRespTecnicoNombre()));
            } 
            else if (getRespTecnicoNombreComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(RESPTECNICONOMBRE, getRespTecnicoNombre()));
            }
            else if (getRespTecnicoNombreComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(RESPTECNICONOMBRE, getRespTecnicoNombre())));
            }
            else {
                criteria.add(Restrictions.like(RESPTECNICONOMBRE, getRespTecnicoNombre()));
            }
        }

        if (getRespTecnicoNombreIn().size() > 0) {
            criteria.add(Restrictions.in(RESPTECNICONOMBRE, getRespTecnicoNombreIn()));
        }

        if (isRespTecnicoNombreIsNull()) {
            criteria.add(Restrictions.isNull(RESPTECNICONOMBRE));
        }

        if (isRespTecnicoNombreIsNotNull()) {
            criteria.add(Restrictions.isNotNull(RESPTECNICONOMBRE));
        }

        if (getRespTecnicoEmail() != null) {
            if (getRespTecnicoEmailComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(RESPTECNICOEMAIL, getRespTecnicoEmail()));
            } 
            else if (getRespTecnicoEmailComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(RESPTECNICOEMAIL, getRespTecnicoEmail()));
            }
            else if (getRespTecnicoEmailComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(RESPTECNICOEMAIL, getRespTecnicoEmail())));
            }
            else {
                criteria.add(Restrictions.like(RESPTECNICOEMAIL, getRespTecnicoEmail()));
            }
        }

        if (getRespTecnicoEmailIn().size() > 0) {
            criteria.add(Restrictions.in(RESPTECNICOEMAIL, getRespTecnicoEmailIn()));
        }

        if (isRespTecnicoEmailIsNull()) {
            criteria.add(Restrictions.isNull(RESPTECNICOEMAIL));
        }

        if (isRespTecnicoEmailIsNotNull()) {
            criteria.add(Restrictions.isNotNull(RESPTECNICOEMAIL));
        }

        if (getProveedorusuariosms() != null) {
            if (getProveedorusuariosmsComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PROVEEDORUSUARIOSMS, getProveedorusuariosms()));
            } 
            else if (getProveedorusuariosmsComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PROVEEDORUSUARIOSMS, getProveedorusuariosms()));
            }
            else if (getProveedorusuariosmsComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PROVEEDORUSUARIOSMS, getProveedorusuariosms())));
            }
            else {
                criteria.add(Restrictions.like(PROVEEDORUSUARIOSMS, getProveedorusuariosms()));
            }
        }

        if (getProveedorusuariosmsIn().size() > 0) {
            criteria.add(Restrictions.in(PROVEEDORUSUARIOSMS, getProveedorusuariosmsIn()));
        }

        if (isProveedorusuariosmsIsNull()) {
            criteria.add(Restrictions.isNull(PROVEEDORUSUARIOSMS));
        }

        if (isProveedorusuariosmsIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PROVEEDORUSUARIOSMS));
        }

        if (getProveedorpasswordsms() != null) {
            if (getProveedorpasswordsmsComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(PROVEEDORPASSWORDSMS, getProveedorpasswordsms()));
            } 
            else if (getProveedorpasswordsmsComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(PROVEEDORPASSWORDSMS, getProveedorpasswordsms()));
            }
            else if (getProveedorpasswordsmsComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(PROVEEDORPASSWORDSMS, getProveedorpasswordsms())));
            }
            else {
                criteria.add(Restrictions.like(PROVEEDORPASSWORDSMS, getProveedorpasswordsms()));
            }
        }

        if (getProveedorpasswordsmsIn().size() > 0) {
            criteria.add(Restrictions.in(PROVEEDORPASSWORDSMS, getProveedorpasswordsmsIn()));
        }

        if (isProveedorpasswordsmsIsNull()) {
            criteria.add(Restrictions.isNull(PROVEEDORPASSWORDSMS));
        }

        if (isProveedorpasswordsmsIsNotNull()) {
            criteria.add(Restrictions.isNotNull(PROVEEDORPASSWORDSMS));
        }

        if (getMultiorganismo() != null) {
            criteria.add(Restrictions.eq(MULTIORGANISMO, getMultiorganismo()));
        }

        if (isMultiorganismoIsNull()) {
            criteria.add(Restrictions.isNull(MULTIORGANISMO));
        }

        if (isMultiorganismoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(MULTIORGANISMO));
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
        
        if (getNumeroMaxReenvios() != null) {
            criteria.add(Restrictions.eq(NUMEROMAXREENVIOS, getNumeroMaxReenvios()));
        }

        if (getNumeroMaxReenviosIn().size() > 0) {
            criteria.add(Restrictions.in(NUMEROMAXREENVIOS, getNumeroMaxReenviosIn()));
        }

        if (isNumeroMaxReenviosIsNull()) {
            criteria.add(Restrictions.isNull(NUMEROMAXREENVIOS));
        }

        if (isNumeroMaxReenviosIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NUMEROMAXREENVIOS));
        }

        if (getReintentosConsultaEStado() != null) {
            criteria.add(Restrictions.eq(REINTENTOSCONSULTAESTADO, getReintentosConsultaEStado()));
        }

        if (getReintentosConsultaEStadoIn().size() > 0) {
            criteria.add(Restrictions.in(REINTENTOSCONSULTAESTADO, getReintentosConsultaEStadoIn()));
        }

        if (isReintentosConsultaEStadoIsNull()) {
            criteria.add(Restrictions.isNull(REINTENTOSCONSULTAESTADO));
        }

        if (isReintentosConsultaEStadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(REINTENTOSCONSULTAESTADO));
        }

        if (getCaducidad() != null) {
            criteria.add(Restrictions.eq(CADUCIDAD, getCaducidad()));
        }

        if (getCaducidadIn().size() > 0) {
            criteria.add(Restrictions.in(CADUCIDAD, getCaducidadIn()));
        }

        if (isCaducidadIsNull()) {
            criteria.add(Restrictions.isNull(CADUCIDAD));
        }

        if (isCaducidadIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CADUCIDAD));
        }

        if (getCaducidadWebPush() != null) {
            criteria.add(Restrictions.eq(CADUCIDADWEBPUSH, getCaducidadWebPush()));
        }

        if (getCaducidadWebPushIn().size() > 0) {
            criteria.add(Restrictions.in(CADUCIDADWEBPUSH, getCaducidadWebPushIn()));
        }

        if (isCaducidadWebPushIsNull()) {
            criteria.add(Restrictions.isNull(CADUCIDADWEBPUSH));
        }

        if (isCaducidadWebPushIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CADUCIDADWEBPUSH));
        }

        if (getVapidPublicKey() != null) {
            if (getVapidPublicKeyComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(VAPIDPUBLICKEY, getVapidPublicKey()));
            } 
            else if (getVapidPublicKeyComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(VAPIDPUBLICKEY, getVapidPublicKey()));
            }
            else if (getVapidPublicKeyComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(VAPIDPUBLICKEY, getVapidPublicKey())));
            }
            else {
                criteria.add(Restrictions.like(VAPIDPUBLICKEY, getVapidPublicKey()));
            }
        }

        if (getVapidPublicKeyIn().size() > 0) {
            criteria.add(Restrictions.in(VAPIDPUBLICKEY, getVapidPublicKeyIn()));
        }

        if (isVapidPublicKeyIsNull()) {
            criteria.add(Restrictions.isNull(VAPIDPUBLICKEY));
        }

        if (isVapidPublicKeyIsNotNull()) {
            criteria.add(Restrictions.isNotNull(VAPIDPUBLICKEY));
        }

        if (getVapidPrivateKey() != null) {
            if (getVapidPrivateKeyComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(VAPIDPRIVATEKEY, getVapidPrivateKey()));
            } 
            else if (getVapidPrivateKeyComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(VAPIDPRIVATEKEY, getVapidPrivateKey()));
            }
            else if (getVapidPrivateKeyComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(VAPIDPRIVATEKEY, getVapidPrivateKey())));
            }
            else {
                criteria.add(Restrictions.like(VAPIDPRIVATEKEY, getVapidPrivateKey()));
            }
        }

        if (getVapidPrivateKeyIn().size() > 0) {
            criteria.add(Restrictions.in(VAPIDPRIVATEKEY, getVapidPrivateKeyIn()));
        }

        if (isVapidPrivateKeyIsNull()) {
            criteria.add(Restrictions.isNull(VAPIDPRIVATEKEY));
        }

        if (isVapidPrivateKeyIsNotNull()) {
            criteria.add(Restrictions.isNotNull(VAPIDPRIVATEKEY));
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
 
