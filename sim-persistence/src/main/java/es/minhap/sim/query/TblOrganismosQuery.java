/*
 *
 * archivo: TblOrganismosQuery.java
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
import es.minhap.sim.model.TblOrganismos;

/**
 * Clase con criterios de busqueda para la entidad TblOrganismos
 */
public class TblOrganismosQuery extends AbstractHibernateQueryEntity<TblOrganismos> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final String UPPER_TRANSLATE_FROM = "ÀÁáàÉÈéèÍíÓóÒòÚú";
    private static final String UPPER_TRANSLATE_TO = "AAaaEEeeIiOoOoUu";
    
    // Constantes para ser utilizadas como nombres de campos, para evitar
    // problemas de compilacion
    public static final String ORGANISMOID = "organismoid";
    public static final String DIR3 = "dir3";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String ACTIVO = "activo";
    public static final String FECHACREACION = "fechacreacion";
    public static final String CREADOPOR = "creadopor";
    public static final String FECHAMODIFICACION = "fechamodificacion";
    public static final String MODIFICADOPOR = "modificadopor";
    public static final String EXTERNALID = "externalid";
    public static final String NOMBRECUENTAENVIO = "nombrecuentaenvio";
    public static final String HISTORIFICACION = "historificacion";
    public static final String MOTIVOHISTORIFICACION = "motivohistorificacion";
    public static final String CONSERVACION = "conservacion";
    public static final String MOTIVOCONSERVACION = "motivoconservacion";
    public static final String ELIMINADO = "eliminado";
    public static final String ESTADO = "estado";
    public static final String NIFCIF = "nifcif";
    public static final String SIGLAS = "siglas";
    public static final String NIVELADMINISTRACION = "nivelAdministracion";
    public static final String NIVELJERARQUICO = "nivelJerarquico";
    public static final String CODUNIDADSUPERIOR = "codUnidadSuperior";
    public static final String DENOMUNIDADSUPERIOR = "denomUnidadSuperior";
    public static final String CODUNIDADRAIZ = "codUnidadRaiz";
    public static final String DENOMUNIDADRAIZ = "denomUnidadRaiz";
    public static final String ESEDP = "esEdp";
    public static final String CODEDPPRINCIPAL = "codEdpPrincipal";
    public static final String DENOMEDPPRINCIPAL = "denomEdpPrincipal";
    public static final String CODTIPOENTPUBLIC = "codTipoEntPublic";
    public static final String CODTIPOUNIDAD = "codTipoUnidad";
    public static final String CODAMBTERRITORIAL = "codAmbTerritorial";
    public static final String CODAMBENTGEOGRAFICA = "codAmbEntGeografica";
    public static final String CODAMBPAIS = "codAmbPais";
    public static final String CODAMBCOMUNIDAD = "codAmbComunidad";
    public static final String CODAMBPROVINCIA = "codAmbProvincia";
    public static final String CODAMBMUNICIPIO = "codAmbMunicipio";
    public static final String CODAMBISLA = "codAmbIsla";
    public static final String CODAMBELM = "codAmbElm";
    public static final String CODAMBLOCEXTRANJERA = "codAmbLocExtranjera";
    public static final String COMPETENCIAS = "competencias";
    public static final String DISPOSICIONLEGAL = "disposicionLegal";
    public static final String FECHAALTAOFICIAL = "fechaAltaOficial";
    public static final String FECHABAJAOFICIAL = "fechaBajaOficial";
    public static final String FECHAEXTINCION = "fechaExtincion";
    public static final String FECHAANULACION = "fechaAnulacion";
    public static final String OBSERVGENERALES = "observGenerales";
    public static final String OBSERVBAJA = "observBaja";
    public static final String TIPOVIA = "tipoVia";
    public static final String NOMBREVIA = "nombreVia";
    public static final String NUMVIA = "numVia";
    public static final String COMPLEMENTO = "complemento";
    public static final String CODPOSTAL = "codPostal";
    public static final String CODPAIS = "codPais";
    public static final String CODCOMUNIDAD = "codComunidad";
    public static final String CODPROVINCIA = "codProvincia";
    public static final String CODLOCALIDAD = "codLocalidad";
    public static final String CODENTGEOGRAFICA = "codEntGeografica";
    public static final String DIREXTRANJERA = "dirExtranjera";
    public static final String LOCEXTRANJERA = "locExtranjera";
    public static final String OBSERVACIONES = "observaciones";
    public static final String IDDIPUTACIONESPDP = "idPdpDiputaciones";


    /**
     * Valor de busqueda de campo organismoid
     */
    private Long organismoid;

    /**
     * Lista de valores del campo organismoid para busquedas tipo IN
     */
    private List<Long> organismoidIn = new ArrayList<Long>(0);

    /**
     * Valor de busqueda de campo dir3
     */
    private String dir3;

    /**
     * Tipo de comparador para la busqueda por campo dir3
     */
    private TextComparator dir3Comparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo dir3 para busquedas tipo IN
     */
    private List<String> dir3In = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo dir3 es NULL
     */
    private boolean dir3IsNull = false;

    /**
     * Permite buscar cuando campo dir3 es NOT NULL
     */
    private boolean dir3IsNotNull = false;

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
    private String externalid;

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
     * Valor de busqueda de campo estado
     */
    private String estado;

    /**
     * Tipo de comparador para la busqueda por campo estado
     */
    private TextComparator estadoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo estado para busquedas tipo IN
     */
    private List<String> estadoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo estado es NULL
     */
    private boolean estadoIsNull = false;

    /**
     * Permite buscar cuando campo estado es NOT NULL
     */
    private boolean estadoIsNotNull = false;

    /**
     * Valor de busqueda de campo nifcif
     */
    private String nifcif;

    /**
     * Tipo de comparador para la busqueda por campo nifcif
     */
    private TextComparator nifcifComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nifcif para busquedas tipo IN
     */
    private List<String> nifcifIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nifcif es NULL
     */
    private boolean nifcifIsNull = false;

    /**
     * Permite buscar cuando campo nifcif es NOT NULL
     */
    private boolean nifcifIsNotNull = false;

    /**
     * Valor de busqueda de campo siglas
     */
    private String siglas;

    /**
     * Tipo de comparador para la busqueda por campo siglas
     */
    private TextComparator siglasComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo siglas para busquedas tipo IN
     */
    private List<String> siglasIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo siglas es NULL
     */
    private boolean siglasIsNull = false;

    /**
     * Permite buscar cuando campo siglas es NOT NULL
     */
    private boolean siglasIsNotNull = false;

    /**
     * Valor de busqueda de campo nivelAdministracion
     */
    private Integer nivelAdministracion;

    /**
     * Lista de valores del campo nivelAdministracion para busquedas tipo IN
     */
    private List<Integer> nivelAdministracionIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo nivelAdministracion es NULL
     */
    private boolean nivelAdministracionIsNull = false;

    /**
     * Permite buscar cuando campo nivelAdministracion es NOT NULL
     */
    private boolean nivelAdministracionIsNotNull = false;

    /**
     * Valor de busqueda de campo nivelJerarquico
     */
    private Integer nivelJerarquico;

    /**
     * Lista de valores del campo nivelJerarquico para busquedas tipo IN
     */
    private List<Integer> nivelJerarquicoIn = new ArrayList<Integer>(0);

    /**
     * Permite buscar cuando campo nivelJerarquico es NULL
     */
    private boolean nivelJerarquicoIsNull = false;

    /**
     * Permite buscar cuando campo nivelJerarquico es NOT NULL
     */
    private boolean nivelJerarquicoIsNotNull = false;

    /**
     * Valor de busqueda de campo codUnidadSuperior
     */
    private String codUnidadSuperior;

    /**
     * Tipo de comparador para la busqueda por campo codUnidadSuperior
     */
    private TextComparator codUnidadSuperiorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codUnidadSuperior para busquedas tipo IN
     */
    private List<String> codUnidadSuperiorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codUnidadSuperior es NULL
     */
    private boolean codUnidadSuperiorIsNull = false;

    /**
     * Permite buscar cuando campo codUnidadSuperior es NOT NULL
     */
    private boolean codUnidadSuperiorIsNotNull = false;

    /**
     * Valor de busqueda de campo denomUnidadSuperior
     */
    private String denomUnidadSuperior;

    /**
     * Tipo de comparador para la busqueda por campo denomUnidadSuperior
     */
    private TextComparator denomUnidadSuperiorComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo denomUnidadSuperior para busquedas tipo IN
     */
    private List<String> denomUnidadSuperiorIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo denomUnidadSuperior es NULL
     */
    private boolean denomUnidadSuperiorIsNull = false;

    /**
     * Permite buscar cuando campo denomUnidadSuperior es NOT NULL
     */
    private boolean denomUnidadSuperiorIsNotNull = false;

    /**
     * Valor de busqueda de campo codUnidadRaiz
     */
    private String codUnidadRaiz;

    /**
     * Tipo de comparador para la busqueda por campo codUnidadRaiz
     */
    private TextComparator codUnidadRaizComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codUnidadRaiz para busquedas tipo IN
     */
    private List<String> codUnidadRaizIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codUnidadRaiz es NULL
     */
    private boolean codUnidadRaizIsNull = false;

    /**
     * Permite buscar cuando campo codUnidadRaiz es NOT NULL
     */
    private boolean codUnidadRaizIsNotNull = false;

    /**
     * Valor de busqueda de campo denomUnidadRaiz
     */
    private String denomUnidadRaiz;

    /**
     * Tipo de comparador para la busqueda por campo denomUnidadRaiz
     */
    private TextComparator denomUnidadRaizComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo denomUnidadRaiz para busquedas tipo IN
     */
    private List<String> denomUnidadRaizIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo denomUnidadRaiz es NULL
     */
    private boolean denomUnidadRaizIsNull = false;

    /**
     * Permite buscar cuando campo denomUnidadRaiz es NOT NULL
     */
    private boolean denomUnidadRaizIsNotNull = false;

    /**
     * Valor de busqueda de campo esEdp
     */
    private String esEdp;

    /**
     * Tipo de comparador para la busqueda por campo esEdp
     */
    private TextComparator esEdpComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo esEdp para busquedas tipo IN
     */
    private List<String> esEdpIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo esEdp es NULL
     */
    private boolean esEdpIsNull = false;

    /**
     * Permite buscar cuando campo esEdp es NOT NULL
     */
    private boolean esEdpIsNotNull = false;

    /**
     * Valor de busqueda de campo codEdpPrincipal
     */
    private String codEdpPrincipal;

    /**
     * Tipo de comparador para la busqueda por campo codEdpPrincipal
     */
    private TextComparator codEdpPrincipalComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codEdpPrincipal para busquedas tipo IN
     */
    private List<String> codEdpPrincipalIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codEdpPrincipal es NULL
     */
    private boolean codEdpPrincipalIsNull = false;

    /**
     * Permite buscar cuando campo codEdpPrincipal es NOT NULL
     */
    private boolean codEdpPrincipalIsNotNull = false;

    /**
     * Valor de busqueda de campo denomEdpPrincipal
     */
    private String denomEdpPrincipal;

    /**
     * Tipo de comparador para la busqueda por campo denomEdpPrincipal
     */
    private TextComparator denomEdpPrincipalComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo denomEdpPrincipal para busquedas tipo IN
     */
    private List<String> denomEdpPrincipalIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo denomEdpPrincipal es NULL
     */
    private boolean denomEdpPrincipalIsNull = false;

    /**
     * Permite buscar cuando campo denomEdpPrincipal es NOT NULL
     */
    private boolean denomEdpPrincipalIsNotNull = false;

    /**
     * Valor de busqueda de campo codTipoEntPublic
     */
    private String codTipoEntPublic;

    /**
     * Tipo de comparador para la busqueda por campo codTipoEntPublic
     */
    private TextComparator codTipoEntPublicComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codTipoEntPublic para busquedas tipo IN
     */
    private List<String> codTipoEntPublicIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codTipoEntPublic es NULL
     */
    private boolean codTipoEntPublicIsNull = false;

    /**
     * Permite buscar cuando campo codTipoEntPublic es NOT NULL
     */
    private boolean codTipoEntPublicIsNotNull = false;

    /**
     * Valor de busqueda de campo codTipoUnidad
     */
    private String codTipoUnidad;

    /**
     * Tipo de comparador para la busqueda por campo codTipoUnidad
     */
    private TextComparator codTipoUnidadComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codTipoUnidad para busquedas tipo IN
     */
    private List<String> codTipoUnidadIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codTipoUnidad es NULL
     */
    private boolean codTipoUnidadIsNull = false;

    /**
     * Permite buscar cuando campo codTipoUnidad es NOT NULL
     */
    private boolean codTipoUnidadIsNotNull = false;

    /**
     * Valor de busqueda de campo codAmbTerritorial
     */
    private String codAmbTerritorial;

    /**
     * Tipo de comparador para la busqueda por campo codAmbTerritorial
     */
    private TextComparator codAmbTerritorialComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codAmbTerritorial para busquedas tipo IN
     */
    private List<String> codAmbTerritorialIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codAmbTerritorial es NULL
     */
    private boolean codAmbTerritorialIsNull = false;

    /**
     * Permite buscar cuando campo codAmbTerritorial es NOT NULL
     */
    private boolean codAmbTerritorialIsNotNull = false;

    /**
     * Valor de busqueda de campo codAmbEntGeografica
     */
    private String codAmbEntGeografica;

    /**
     * Tipo de comparador para la busqueda por campo codAmbEntGeografica
     */
    private TextComparator codAmbEntGeograficaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codAmbEntGeografica para busquedas tipo IN
     */
    private List<String> codAmbEntGeograficaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codAmbEntGeografica es NULL
     */
    private boolean codAmbEntGeograficaIsNull = false;

    /**
     * Permite buscar cuando campo codAmbEntGeografica es NOT NULL
     */
    private boolean codAmbEntGeograficaIsNotNull = false;

    /**
     * Valor de busqueda de campo codAmbPais
     */
    private String codAmbPais;

    /**
     * Tipo de comparador para la busqueda por campo codAmbPais
     */
    private TextComparator codAmbPaisComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codAmbPais para busquedas tipo IN
     */
    private List<String> codAmbPaisIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codAmbPais es NULL
     */
    private boolean codAmbPaisIsNull = false;

    /**
     * Permite buscar cuando campo codAmbPais es NOT NULL
     */
    private boolean codAmbPaisIsNotNull = false;

    /**
     * Valor de busqueda de campo codAmbComunidad
     */
    private String codAmbComunidad;

    /**
     * Tipo de comparador para la busqueda por campo codAmbComunidad
     */
    private TextComparator codAmbComunidadComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codAmbComunidad para busquedas tipo IN
     */
    private List<String> codAmbComunidadIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codAmbComunidad es NULL
     */
    private boolean codAmbComunidadIsNull = false;

    /**
     * Permite buscar cuando campo codAmbComunidad es NOT NULL
     */
    private boolean codAmbComunidadIsNotNull = false;

    /**
     * Valor de busqueda de campo codAmbProvincia
     */
    private String codAmbProvincia;

    /**
     * Tipo de comparador para la busqueda por campo codAmbProvincia
     */
    private TextComparator codAmbProvinciaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codAmbProvincia para busquedas tipo IN
     */
    private List<String> codAmbProvinciaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codAmbProvincia es NULL
     */
    private boolean codAmbProvinciaIsNull = false;

    /**
     * Permite buscar cuando campo codAmbProvincia es NOT NULL
     */
    private boolean codAmbProvinciaIsNotNull = false;

    /**
     * Valor de busqueda de campo codAmbMunicipio
     */
    private String codAmbMunicipio;

    /**
     * Tipo de comparador para la busqueda por campo codAmbMunicipio
     */
    private TextComparator codAmbMunicipioComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codAmbMunicipio para busquedas tipo IN
     */
    private List<String> codAmbMunicipioIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codAmbMunicipio es NULL
     */
    private boolean codAmbMunicipioIsNull = false;

    /**
     * Permite buscar cuando campo codAmbMunicipio es NOT NULL
     */
    private boolean codAmbMunicipioIsNotNull = false;

    /**
     * Valor de busqueda de campo codAmbIsla
     */
    private String codAmbIsla;

    /**
     * Tipo de comparador para la busqueda por campo codAmbIsla
     */
    private TextComparator codAmbIslaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codAmbIsla para busquedas tipo IN
     */
    private List<String> codAmbIslaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codAmbIsla es NULL
     */
    private boolean codAmbIslaIsNull = false;

    /**
     * Permite buscar cuando campo codAmbIsla es NOT NULL
     */
    private boolean codAmbIslaIsNotNull = false;

    /**
     * Valor de busqueda de campo codAmbElm
     */
    private String codAmbElm;

    /**
     * Tipo de comparador para la busqueda por campo codAmbElm
     */
    private TextComparator codAmbElmComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codAmbElm para busquedas tipo IN
     */
    private List<String> codAmbElmIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codAmbElm es NULL
     */
    private boolean codAmbElmIsNull = false;

    /**
     * Permite buscar cuando campo codAmbElm es NOT NULL
     */
    private boolean codAmbElmIsNotNull = false;

    /**
     * Valor de busqueda de campo codAmbLocExtranjera
     */
    private String codAmbLocExtranjera;

    /**
     * Tipo de comparador para la busqueda por campo codAmbLocExtranjera
     */
    private TextComparator codAmbLocExtranjeraComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codAmbLocExtranjera para busquedas tipo IN
     */
    private List<String> codAmbLocExtranjeraIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codAmbLocExtranjera es NULL
     */
    private boolean codAmbLocExtranjeraIsNull = false;

    /**
     * Permite buscar cuando campo codAmbLocExtranjera es NOT NULL
     */
    private boolean codAmbLocExtranjeraIsNotNull = false;

    /**
     * Valor de busqueda de campo competencias
     */
    private String competencias;

    /**
     * Tipo de comparador para la busqueda por campo competencias
     */
    private TextComparator competenciasComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo competencias para busquedas tipo IN
     */
    private List<String> competenciasIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo competencias es NULL
     */
    private boolean competenciasIsNull = false;

    /**
     * Permite buscar cuando campo competencias es NOT NULL
     */
    private boolean competenciasIsNotNull = false;

    /**
     * Valor de busqueda de campo disposicionLegal
     */
    private String disposicionLegal;

    /**
     * Tipo de comparador para la busqueda por campo disposicionLegal
     */
    private TextComparator disposicionLegalComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo disposicionLegal para busquedas tipo IN
     */
    private List<String> disposicionLegalIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo disposicionLegal es NULL
     */
    private boolean disposicionLegalIsNull = false;

    /**
     * Permite buscar cuando campo disposicionLegal es NOT NULL
     */
    private boolean disposicionLegalIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaAltaOficial
     */
    private Date fechaAltaOficialMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaAltaOficial
     */
    private Date fechaAltaOficialMax;

    /**
     * Permite buscar cuando campo fechaAltaOficial es NULL
     */
    private boolean fechaAltaOficialIsNull = false;

    /**
     * Permite buscar cuando campo fechaAltaOficial es NOT NULL
     */
    private boolean fechaAltaOficialIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaBajaOficial
     */
    private Date fechaBajaOficialMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaBajaOficial
     */
    private Date fechaBajaOficialMax;

    /**
     * Permite buscar cuando campo fechaBajaOficial es NULL
     */
    private boolean fechaBajaOficialIsNull = false;

    /**
     * Permite buscar cuando campo fechaBajaOficial es NOT NULL
     */
    private boolean fechaBajaOficialIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaExtincion
     */
    private Date fechaExtincionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaExtincion
     */
    private Date fechaExtincionMax;

    /**
     * Permite buscar cuando campo fechaExtincion es NULL
     */
    private boolean fechaExtincionIsNull = false;

    /**
     * Permite buscar cuando campo fechaExtincion es NOT NULL
     */
    private boolean fechaExtincionIsNotNull = false;

    /**
     * Valor inferior de rango de busqueda de fecha fechaAnulacion
     */
    private Date fechaAnulacionMin;

    /**
     * Valor superior de rango de busqueda de fecha fechaAnulacion
     */
    private Date fechaAnulacionMax;

    /**
     * Permite buscar cuando campo fechaAnulacion es NULL
     */
    private boolean fechaAnulacionIsNull = false;

    /**
     * Permite buscar cuando campo fechaAnulacion es NOT NULL
     */
    private boolean fechaAnulacionIsNotNull = false;

    /**
     * Valor de busqueda de campo observGenerales
     */
    private String observGenerales;

    /**
     * Tipo de comparador para la busqueda por campo observGenerales
     */
    private TextComparator observGeneralesComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo observGenerales para busquedas tipo IN
     */
    private List<String> observGeneralesIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo observGenerales es NULL
     */
    private boolean observGeneralesIsNull = false;

    /**
     * Permite buscar cuando campo observGenerales es NOT NULL
     */
    private boolean observGeneralesIsNotNull = false;

    /**
     * Valor de busqueda de campo observBaja
     */
    private String observBaja;

    /**
     * Tipo de comparador para la busqueda por campo observBaja
     */
    private TextComparator observBajaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo observBaja para busquedas tipo IN
     */
    private List<String> observBajaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo observBaja es NULL
     */
    private boolean observBajaIsNull = false;

    /**
     * Permite buscar cuando campo observBaja es NOT NULL
     */
    private boolean observBajaIsNotNull = false;

    /**
     * Valor de busqueda de campo tipoVia
     */
    private String tipoVia;

    /**
     * Tipo de comparador para la busqueda por campo tipoVia
     */
    private TextComparator tipoViaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo tipoVia para busquedas tipo IN
     */
    private List<String> tipoViaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo tipoVia es NULL
     */
    private boolean tipoViaIsNull = false;

    /**
     * Permite buscar cuando campo tipoVia es NOT NULL
     */
    private boolean tipoViaIsNotNull = false;

    /**
     * Valor de busqueda de campo nombreVia
     */
    private String nombreVia;

    /**
     * Tipo de comparador para la busqueda por campo nombreVia
     */
    private TextComparator nombreViaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo nombreVia para busquedas tipo IN
     */
    private List<String> nombreViaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo nombreVia es NULL
     */
    private boolean nombreViaIsNull = false;

    /**
     * Permite buscar cuando campo nombreVia es NOT NULL
     */
    private boolean nombreViaIsNotNull = false;

    /**
     * Valor de busqueda de campo numVia
     */
    private String numVia;

    /**
     * Tipo de comparador para la busqueda por campo numVia
     */
    private TextComparator numViaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo numVia para busquedas tipo IN
     */
    private List<String> numViaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo numVia es NULL
     */
    private boolean numViaIsNull = false;

    /**
     * Permite buscar cuando campo numVia es NOT NULL
     */
    private boolean numViaIsNotNull = false;

    /**
     * Valor de busqueda de campo complemento
     */
    private String complemento;

    /**
     * Tipo de comparador para la busqueda por campo complemento
     */
    private TextComparator complementoComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo complemento para busquedas tipo IN
     */
    private List<String> complementoIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo complemento es NULL
     */
    private boolean complementoIsNull = false;

    /**
     * Permite buscar cuando campo complemento es NOT NULL
     */
    private boolean complementoIsNotNull = false;

    /**
     * Valor de busqueda de campo codPostal
     */
    private String codPostal;

    /**
     * Tipo de comparador para la busqueda por campo codPostal
     */
    private TextComparator codPostalComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codPostal para busquedas tipo IN
     */
    private List<String> codPostalIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codPostal es NULL
     */
    private boolean codPostalIsNull = false;

    /**
     * Permite buscar cuando campo codPostal es NOT NULL
     */
    private boolean codPostalIsNotNull = false;

    /**
     * Valor de busqueda de campo codPais
     */
    private String codPais;

    /**
     * Tipo de comparador para la busqueda por campo codPais
     */
    private TextComparator codPaisComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codPais para busquedas tipo IN
     */
    private List<String> codPaisIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codPais es NULL
     */
    private boolean codPaisIsNull = false;

    /**
     * Permite buscar cuando campo codPais es NOT NULL
     */
    private boolean codPaisIsNotNull = false;

    /**
     * Valor de busqueda de campo codComunidad
     */
    private String codComunidad;

    /**
     * Tipo de comparador para la busqueda por campo codComunidad
     */
    private TextComparator codComunidadComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codComunidad para busquedas tipo IN
     */
    private List<String> codComunidadIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codComunidad es NULL
     */
    private boolean codComunidadIsNull = false;

    /**
     * Permite buscar cuando campo codComunidad es NOT NULL
     */
    private boolean codComunidadIsNotNull = false;

    /**
     * Valor de busqueda de campo codProvincia
     */
    private String codProvincia;

    /**
     * Tipo de comparador para la busqueda por campo codProvincia
     */
    private TextComparator codProvinciaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codProvincia para busquedas tipo IN
     */
    private List<String> codProvinciaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codProvincia es NULL
     */
    private boolean codProvinciaIsNull = false;

    /**
     * Permite buscar cuando campo codProvincia es NOT NULL
     */
    private boolean codProvinciaIsNotNull = false;

    /**
     * Valor de busqueda de campo codLocalidad
     */
    private String codLocalidad;

    /**
     * Tipo de comparador para la busqueda por campo codLocalidad
     */
    private TextComparator codLocalidadComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codLocalidad para busquedas tipo IN
     */
    private List<String> codLocalidadIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codLocalidad es NULL
     */
    private boolean codLocalidadIsNull = false;

    /**
     * Permite buscar cuando campo codLocalidad es NOT NULL
     */
    private boolean codLocalidadIsNotNull = false;

    /**
     * Valor de busqueda de campo codEntGeografica
     */
    private String codEntGeografica;

    /**
     * Tipo de comparador para la busqueda por campo codEntGeografica
     */
    private TextComparator codEntGeograficaComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo codEntGeografica para busquedas tipo IN
     */
    private List<String> codEntGeograficaIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo codEntGeografica es NULL
     */
    private boolean codEntGeograficaIsNull = false;

    /**
     * Permite buscar cuando campo codEntGeografica es NOT NULL
     */
    private boolean codEntGeograficaIsNotNull = false;

    /**
     * Valor de busqueda de campo dirExtranjera
     */
    private String dirExtranjera;

    /**
     * Tipo de comparador para la busqueda por campo dirExtranjera
     */
    private TextComparator dirExtranjeraComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo dirExtranjera para busquedas tipo IN
     */
    private List<String> dirExtranjeraIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo dirExtranjera es NULL
     */
    private boolean dirExtranjeraIsNull = false;

    /**
     * Permite buscar cuando campo dirExtranjera es NOT NULL
     */
    private boolean dirExtranjeraIsNotNull = false;

    /**
     * Valor de busqueda de campo locExtranjera
     */
    private String locExtranjera;

    /**
     * Tipo de comparador para la busqueda por campo locExtranjera
     */
    private TextComparator locExtranjeraComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo locExtranjera para busquedas tipo IN
     */
    private List<String> locExtranjeraIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo locExtranjera es NULL
     */
    private boolean locExtranjeraIsNull = false;

    /**
     * Permite buscar cuando campo locExtranjera es NOT NULL
     */
    private boolean locExtranjeraIsNotNull = false;

    /**
     * Valor de busqueda de campo observaciones
     */
    private String observaciones;

    /**
     * Tipo de comparador para la busqueda por campo observaciones
     */
    private TextComparator observacionesComparator = TextComparator.CONTAINS;

    /**
     * Lista de valores del campo observaciones para busquedas tipo IN
     */
    private List<String> observacionesIn = new ArrayList<String>(0);

    /**
     * Permite buscar cuando campo observaciones es NULL
     */
    private boolean observacionesIsNull = false;

    /**
     * Permite buscar cuando campo observaciones es NOT NULL
     */
    private boolean observacionesIsNotNull = false;

    /**
   	 * Establece el máximo de resultados
   	 */
   	private Integer maxResultados;
   	
   	/**
   	 * Establece el primer resultados
   	 */
   	private Integer primerResultado;
   	
    /**
     * Valor de busqueda de campo idDiputacionesPdp
     */
    private Long idPdpDiputaciones;

    /**
     * Lista de valores del campo idPdpDiputaciones para busquedas tipo IN
     */
    private List<Long> idPdpDiputacionesIn = new ArrayList<Long>(0);

    /**
     * Permite buscar cuando campo idPdpDiputaciones es NULL
     */
    private boolean idPdpDiputacionesIsNull = false;

    /**
     * Permite buscar cuando campo idPdpDiputaciones es NOT NULL
     */
    private boolean idPdpDiputacionesIsNotNull = false;
    
    /**
     * Constructor default
     */
    public TblOrganismosQuery() {
    
    }

    /**
     * Constructor usando identificador
     */
    public TblOrganismosQuery(Long organismoid) {
        setOrganismoid(organismoid);
    }

    /**
     * Valor de busqueda de campo organismoid
     * @return Long.
     */
    public Long getOrganismoid() {
        return organismoid;
    }

    /**
     * Valor de busqueda de campo organismoid
     * @param organismoid Valor de seteo.
     */
    public void setOrganismoid(Long organismoid) {
        this.organismoid = organismoid;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getOrganismoidIn() {
        return this.organismoidIn;
    }

    /**
     * @param organismoid Valor a agregar.
     */
    public void addOrganismoidIn(Long organismoid) {
        this.organismoidIn.add(organismoid);
    }

    /**
     * Valor de busqueda de campo dir3
     * @return String.
     */
    public String getDir3() {
        if (dir3 != null) {
            switch (dir3Comparator) {
	            case STARTS_WITH:
	                return dir3 + "%";
	            case CONTAINS:
	                return "%" + dir3 + "%";
	            case ENDS_WITH:
	                return "%" + dir3;
	            case EQUALS:
                	return dir3;
              	default:
	            	break;
            }
        }
        return dir3;
    }

    /**
     * Valor de busqueda de campo dir3
     * @param dir3 Valor de seteo.
     */
    public void setDir3(String dir3) {
        this.dir3 = dir3;
    }

    /**
     * Tipo de comparador para la busqueda por campo dir3
     * @return dir3Comparator.
     */
    public TextComparator getDir3Comparator() {
        return dir3Comparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo dir3
     * @param dir3Comparator Valor de seteo.
     */
    public void setDir3Comparator(TextComparator dir3Comparator) {
        this.dir3Comparator = dir3Comparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDir3In() {
        return this.dir3In;
    }

    /**
     * @param dir3 Valor a agregar.
     */
    public void addDir3In(String dir3) {
        this.dir3In.add(dir3);
    }

    /**
     * Permite buscar cuando campo dir3 es NULL
     * @return boolean.
     */
    public boolean isDir3IsNull() {
        return dir3IsNull;
    }

    /**
     * Permite buscar cuando campo dir3 es NULL
     * @param dir3IsNull Valor de seteo.
     */
    public void setDir3IsNull(boolean dir3IsNull) {
        this.dir3IsNull = dir3IsNull;
    }

    /**
     * Permite buscar cuando campo dir3 es NOT NULL
     * @return boolean.
     */
    public boolean isDir3IsNotNull() {
        return dir3IsNotNull;
    }

    /**
     * Permite buscar cuando campo dir3 es NOT NULL
     * @param dir3IsNotNull Valor de seteo.
     */
    public void setDir3IsNotNull(boolean dir3IsNotNull) {
        this.dir3IsNotNull = dir3IsNotNull;
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
    public String getExternalid() {
        return externalid;
    }

    /**
     * Valor de busqueda de campo externalid
     * @param externalid Valor de seteo.
     */
    public void setExternalid(String externalid) {
        this.externalid = externalid;
    }

    /**
     * @return List<Long>.
     */
    public List<String> getExternalidIn() {
        return this.externalidIn;
    }

    /**
     * @param externalid Valor a agregar.
     */
    public void addExternalidIn(String externalid) {
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
     * Valor de busqueda de campo estado
     * @return String.
     */
    public String getEstado() {
        if (estado != null) {
            switch (estadoComparator) {
	            case STARTS_WITH:
	                return estado + "%";
	            case CONTAINS:
	                return "%" + estado + "%";
	            case ENDS_WITH:
	                return "%" + estado;
	            case EQUALS:
                	return estado;
              	default:
	            	break;
            }
        }
        return estado;
    }

    /**
     * Valor de busqueda de campo estado
     * @param estado Valor de seteo.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Tipo de comparador para la busqueda por campo estado
     * @return estadoComparator.
     */
    public TextComparator getEstadoComparator() {
        return estadoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo estado
     * @param estadoComparator Valor de seteo.
     */
    public void setEstadoComparator(TextComparator estadoComparator) {
        this.estadoComparator = estadoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEstadoIn() {
        return this.estadoIn;
    }

    /**
     * @param estado Valor a agregar.
     */
    public void addEstadoIn(String estado) {
        this.estadoIn.add(estado);
    }

    /**
     * Permite buscar cuando campo estado es NULL
     * @return boolean.
     */
    public boolean isEstadoIsNull() {
        return estadoIsNull;
    }

    /**
     * Permite buscar cuando campo estado es NULL
     * @param estadoIsNull Valor de seteo.
     */
    public void setEstadoIsNull(boolean estadoIsNull) {
        this.estadoIsNull = estadoIsNull;
    }

    /**
     * Permite buscar cuando campo estado es NOT NULL
     * @return boolean.
     */
    public boolean isEstadoIsNotNull() {
        return estadoIsNotNull;
    }

    /**
     * Permite buscar cuando campo estado es NOT NULL
     * @param estadoIsNotNull Valor de seteo.
     */
    public void setEstadoIsNotNull(boolean estadoIsNotNull) {
        this.estadoIsNotNull = estadoIsNotNull;
    }

    /**
     * Valor de busqueda de campo nifcif
     * @return String.
     */
    public String getNifcif() {
        if (nifcif != null) {
            switch (nifcifComparator) {
	            case STARTS_WITH:
	                return nifcif + "%";
	            case CONTAINS:
	                return "%" + nifcif + "%";
	            case ENDS_WITH:
	                return "%" + nifcif;
	            case EQUALS:
                	return nifcif;
              	default:
	            	break;
            }
        }
        return nifcif;
    }

    /**
     * Valor de busqueda de campo nifcif
     * @param nifcif Valor de seteo.
     */
    public void setNifcif(String nifcif) {
        this.nifcif = nifcif;
    }

    /**
     * Tipo de comparador para la busqueda por campo nifcif
     * @return nifcifComparator.
     */
    public TextComparator getNifcifComparator() {
        return nifcifComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nifcif
     * @param nifcifComparator Valor de seteo.
     */
    public void setNifcifComparator(TextComparator nifcifComparator) {
        this.nifcifComparator = nifcifComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNifcifIn() {
        return this.nifcifIn;
    }

    /**
     * @param nifcif Valor a agregar.
     */
    public void addNifcifIn(String nifcif) {
        this.nifcifIn.add(nifcif);
    }

    /**
     * Permite buscar cuando campo nifcif es NULL
     * @return boolean.
     */
    public boolean isNifcifIsNull() {
        return nifcifIsNull;
    }

    /**
     * Permite buscar cuando campo nifcif es NULL
     * @param nifcifIsNull Valor de seteo.
     */
    public void setNifcifIsNull(boolean nifcifIsNull) {
        this.nifcifIsNull = nifcifIsNull;
    }

    /**
     * Permite buscar cuando campo nifcif es NOT NULL
     * @return boolean.
     */
    public boolean isNifcifIsNotNull() {
        return nifcifIsNotNull;
    }

    /**
     * Permite buscar cuando campo nifcif es NOT NULL
     * @param nifcifIsNotNull Valor de seteo.
     */
    public void setNifcifIsNotNull(boolean nifcifIsNotNull) {
        this.nifcifIsNotNull = nifcifIsNotNull;
    }

    /**
     * Valor de busqueda de campo siglas
     * @return String.
     */
    public String getSiglas() {
        if (siglas != null) {
            switch (siglasComparator) {
	            case STARTS_WITH:
	                return siglas + "%";
	            case CONTAINS:
	                return "%" + siglas + "%";
	            case ENDS_WITH:
	                return "%" + siglas;
	            case EQUALS:
                	return siglas;
              	default:
	            	break;
            }
        }
        return siglas;
    }

    /**
     * Valor de busqueda de campo siglas
     * @param siglas Valor de seteo.
     */
    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    /**
     * Tipo de comparador para la busqueda por campo siglas
     * @return siglasComparator.
     */
    public TextComparator getSiglasComparator() {
        return siglasComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo siglas
     * @param siglasComparator Valor de seteo.
     */
    public void setSiglasComparator(TextComparator siglasComparator) {
        this.siglasComparator = siglasComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getSiglasIn() {
        return this.siglasIn;
    }

    /**
     * @param siglas Valor a agregar.
     */
    public void addSiglasIn(String siglas) {
        this.siglasIn.add(siglas);
    }

    /**
     * Permite buscar cuando campo siglas es NULL
     * @return boolean.
     */
    public boolean isSiglasIsNull() {
        return siglasIsNull;
    }

    /**
     * Permite buscar cuando campo siglas es NULL
     * @param siglasIsNull Valor de seteo.
     */
    public void setSiglasIsNull(boolean siglasIsNull) {
        this.siglasIsNull = siglasIsNull;
    }

    /**
     * Permite buscar cuando campo siglas es NOT NULL
     * @return boolean.
     */
    public boolean isSiglasIsNotNull() {
        return siglasIsNotNull;
    }

    /**
     * Permite buscar cuando campo siglas es NOT NULL
     * @param siglasIsNotNull Valor de seteo.
     */
    public void setSiglasIsNotNull(boolean siglasIsNotNull) {
        this.siglasIsNotNull = siglasIsNotNull;
    }

    /**
     * Valor de busqueda de campo nivelAdministracion
     * @return Integer.
     */
    public Integer getNivelAdministracion() {
        return nivelAdministracion;
    }

    /**
     * Valor de busqueda de campo nivelAdministracion
     * @param nivelAdministracion Valor de seteo.
     */
    public void setNivelAdministracion(Integer nivelAdministracion) {
        this.nivelAdministracion = nivelAdministracion;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getNivelAdministracionIn() {
        return this.nivelAdministracionIn;
    }

    /**
     * @param nivelAdministracion Valor a agregar.
     */
    public void addNivelAdministracionIn(Integer nivelAdministracion) {
        this.nivelAdministracionIn.add(nivelAdministracion);
    }

    /**
     * Permite buscar cuando campo nivelAdministracion es NULL
     * @return boolean.
     */
    public boolean isNivelAdministracionIsNull() {
        return nivelAdministracionIsNull;
    }

    /**
     * Permite buscar cuando campo nivelAdministracion es NULL
     * @param nivelAdministracionIsNull Valor de seteo.
     */
    public void setNivelAdministracionIsNull(boolean nivelAdministracionIsNull) {
        this.nivelAdministracionIsNull = nivelAdministracionIsNull;
    }

    /**
     * Permite buscar cuando campo nivelAdministracion es NOT NULL
     * @return boolean.
     */
    public boolean isNivelAdministracionIsNotNull() {
        return nivelAdministracionIsNotNull;
    }

    /**
     * Permite buscar cuando campo nivelAdministracion es NOT NULL
     * @param nivelAdministracionIsNotNull Valor de seteo.
     */
    public void setNivelAdministracionIsNotNull(boolean nivelAdministracionIsNotNull) {
        this.nivelAdministracionIsNotNull = nivelAdministracionIsNotNull;
    }

    /**
     * Valor de busqueda de campo nivelJerarquico
     * @return Integer.
     */
    public Integer getNivelJerarquico() {
        return nivelJerarquico;
    }

    /**
     * Valor de busqueda de campo nivelJerarquico
     * @param nivelJerarquico Valor de seteo.
     */
    public void setNivelJerarquico(Integer nivelJerarquico) {
        this.nivelJerarquico = nivelJerarquico;
    }

    /**
     * @return List<Integer>.
     */
    public List<Integer> getNivelJerarquicoIn() {
        return this.nivelJerarquicoIn;
    }

    /**
     * @param nivelJerarquico Valor a agregar.
     */
    public void addNivelJerarquicoIn(Integer nivelJerarquico) {
        this.nivelJerarquicoIn.add(nivelJerarquico);
    }

    /**
     * Permite buscar cuando campo nivelJerarquico es NULL
     * @return boolean.
     */
    public boolean isNivelJerarquicoIsNull() {
        return nivelJerarquicoIsNull;
    }

    /**
     * Permite buscar cuando campo nivelJerarquico es NULL
     * @param nivelJerarquicoIsNull Valor de seteo.
     */
    public void setNivelJerarquicoIsNull(boolean nivelJerarquicoIsNull) {
        this.nivelJerarquicoIsNull = nivelJerarquicoIsNull;
    }

    /**
     * Permite buscar cuando campo nivelJerarquico es NOT NULL
     * @return boolean.
     */
    public boolean isNivelJerarquicoIsNotNull() {
        return nivelJerarquicoIsNotNull;
    }

    /**
     * Permite buscar cuando campo nivelJerarquico es NOT NULL
     * @param nivelJerarquicoIsNotNull Valor de seteo.
     */
    public void setNivelJerarquicoIsNotNull(boolean nivelJerarquicoIsNotNull) {
        this.nivelJerarquicoIsNotNull = nivelJerarquicoIsNotNull;
    }

    /**
     * Valor de busqueda de campo codUnidadSuperior
     * @return String.
     */
    public String getCodUnidadSuperior() {
        if (codUnidadSuperior != null) {
            switch (codUnidadSuperiorComparator) {
	            case STARTS_WITH:
	                return codUnidadSuperior + "%";
	            case CONTAINS:
	                return "%" + codUnidadSuperior + "%";
	            case ENDS_WITH:
	                return "%" + codUnidadSuperior;
	            case EQUALS:
                	return codUnidadSuperior;
              	default:
	            	break;
            }
        }
        return codUnidadSuperior;
    }

    /**
     * Valor de busqueda de campo codUnidadSuperior
     * @param codUnidadSuperior Valor de seteo.
     */
    public void setCodUnidadSuperior(String codUnidadSuperior) {
        this.codUnidadSuperior = codUnidadSuperior;
    }

    /**
     * Tipo de comparador para la busqueda por campo codUnidadSuperior
     * @return codUnidadSuperiorComparator.
     */
    public TextComparator getCodUnidadSuperiorComparator() {
        return codUnidadSuperiorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codUnidadSuperior
     * @param codUnidadSuperiorComparator Valor de seteo.
     */
    public void setCodUnidadSuperiorComparator(TextComparator codUnidadSuperiorComparator) {
        this.codUnidadSuperiorComparator = codUnidadSuperiorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodUnidadSuperiorIn() {
        return this.codUnidadSuperiorIn;
    }

    /**
     * @param codUnidadSuperior Valor a agregar.
     */
    public void addCodUnidadSuperiorIn(String codUnidadSuperior) {
        this.codUnidadSuperiorIn.add(codUnidadSuperior);
    }

    /**
     * Permite buscar cuando campo codUnidadSuperior es NULL
     * @return boolean.
     */
    public boolean isCodUnidadSuperiorIsNull() {
        return codUnidadSuperiorIsNull;
    }

    /**
     * Permite buscar cuando campo codUnidadSuperior es NULL
     * @param codUnidadSuperiorIsNull Valor de seteo.
     */
    public void setCodUnidadSuperiorIsNull(boolean codUnidadSuperiorIsNull) {
        this.codUnidadSuperiorIsNull = codUnidadSuperiorIsNull;
    }

    /**
     * Permite buscar cuando campo codUnidadSuperior es NOT NULL
     * @return boolean.
     */
    public boolean isCodUnidadSuperiorIsNotNull() {
        return codUnidadSuperiorIsNotNull;
    }

    /**
     * Permite buscar cuando campo codUnidadSuperior es NOT NULL
     * @param codUnidadSuperiorIsNotNull Valor de seteo.
     */
    public void setCodUnidadSuperiorIsNotNull(boolean codUnidadSuperiorIsNotNull) {
        this.codUnidadSuperiorIsNotNull = codUnidadSuperiorIsNotNull;
    }

    /**
     * Valor de busqueda de campo denomUnidadSuperior
     * @return String.
     */
    public String getDenomUnidadSuperior() {
        if (denomUnidadSuperior != null) {
            switch (denomUnidadSuperiorComparator) {
	            case STARTS_WITH:
	                return denomUnidadSuperior + "%";
	            case CONTAINS:
	                return "%" + denomUnidadSuperior + "%";
	            case ENDS_WITH:
	                return "%" + denomUnidadSuperior;
	            case EQUALS:
                	return denomUnidadSuperior;
              	default:
	            	break;
            }
        }
        return denomUnidadSuperior;
    }

    /**
     * Valor de busqueda de campo denomUnidadSuperior
     * @param denomUnidadSuperior Valor de seteo.
     */
    public void setDenomUnidadSuperior(String denomUnidadSuperior) {
        this.denomUnidadSuperior = denomUnidadSuperior;
    }

    /**
     * Tipo de comparador para la busqueda por campo denomUnidadSuperior
     * @return denomUnidadSuperiorComparator.
     */
    public TextComparator getDenomUnidadSuperiorComparator() {
        return denomUnidadSuperiorComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo denomUnidadSuperior
     * @param denomUnidadSuperiorComparator Valor de seteo.
     */
    public void setDenomUnidadSuperiorComparator(TextComparator denomUnidadSuperiorComparator) {
        this.denomUnidadSuperiorComparator = denomUnidadSuperiorComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDenomUnidadSuperiorIn() {
        return this.denomUnidadSuperiorIn;
    }

    /**
     * @param denomUnidadSuperior Valor a agregar.
     */
    public void addDenomUnidadSuperiorIn(String denomUnidadSuperior) {
        this.denomUnidadSuperiorIn.add(denomUnidadSuperior);
    }

    /**
     * Permite buscar cuando campo denomUnidadSuperior es NULL
     * @return boolean.
     */
    public boolean isDenomUnidadSuperiorIsNull() {
        return denomUnidadSuperiorIsNull;
    }

    /**
     * Permite buscar cuando campo denomUnidadSuperior es NULL
     * @param denomUnidadSuperiorIsNull Valor de seteo.
     */
    public void setDenomUnidadSuperiorIsNull(boolean denomUnidadSuperiorIsNull) {
        this.denomUnidadSuperiorIsNull = denomUnidadSuperiorIsNull;
    }

    /**
     * Permite buscar cuando campo denomUnidadSuperior es NOT NULL
     * @return boolean.
     */
    public boolean isDenomUnidadSuperiorIsNotNull() {
        return denomUnidadSuperiorIsNotNull;
    }

    /**
     * Permite buscar cuando campo denomUnidadSuperior es NOT NULL
     * @param denomUnidadSuperiorIsNotNull Valor de seteo.
     */
    public void setDenomUnidadSuperiorIsNotNull(boolean denomUnidadSuperiorIsNotNull) {
        this.denomUnidadSuperiorIsNotNull = denomUnidadSuperiorIsNotNull;
    }

    /**
     * Valor de busqueda de campo codUnidadRaiz
     * @return String.
     */
    public String getCodUnidadRaiz() {
        if (codUnidadRaiz != null) {
            switch (codUnidadRaizComparator) {
	            case STARTS_WITH:
	                return codUnidadRaiz + "%";
	            case CONTAINS:
	                return "%" + codUnidadRaiz + "%";
	            case ENDS_WITH:
	                return "%" + codUnidadRaiz;
	            case EQUALS:
                	return codUnidadRaiz;
              	default:
	            	break;
            }
        }
        return codUnidadRaiz;
    }

    /**
     * Valor de busqueda de campo codUnidadRaiz
     * @param codUnidadRaiz Valor de seteo.
     */
    public void setCodUnidadRaiz(String codUnidadRaiz) {
        this.codUnidadRaiz = codUnidadRaiz;
    }

    /**
     * Tipo de comparador para la busqueda por campo codUnidadRaiz
     * @return codUnidadRaizComparator.
     */
    public TextComparator getCodUnidadRaizComparator() {
        return codUnidadRaizComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codUnidadRaiz
     * @param codUnidadRaizComparator Valor de seteo.
     */
    public void setCodUnidadRaizComparator(TextComparator codUnidadRaizComparator) {
        this.codUnidadRaizComparator = codUnidadRaizComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodUnidadRaizIn() {
        return this.codUnidadRaizIn;
    }

    /**
     * @param codUnidadRaiz Valor a agregar.
     */
    public void addCodUnidadRaizIn(String codUnidadRaiz) {
        this.codUnidadRaizIn.add(codUnidadRaiz);
    }

    /**
     * Permite buscar cuando campo codUnidadRaiz es NULL
     * @return boolean.
     */
    public boolean isCodUnidadRaizIsNull() {
        return codUnidadRaizIsNull;
    }

    /**
     * Permite buscar cuando campo codUnidadRaiz es NULL
     * @param codUnidadRaizIsNull Valor de seteo.
     */
    public void setCodUnidadRaizIsNull(boolean codUnidadRaizIsNull) {
        this.codUnidadRaizIsNull = codUnidadRaizIsNull;
    }

    /**
     * Permite buscar cuando campo codUnidadRaiz es NOT NULL
     * @return boolean.
     */
    public boolean isCodUnidadRaizIsNotNull() {
        return codUnidadRaizIsNotNull;
    }

    /**
     * Permite buscar cuando campo codUnidadRaiz es NOT NULL
     * @param codUnidadRaizIsNotNull Valor de seteo.
     */
    public void setCodUnidadRaizIsNotNull(boolean codUnidadRaizIsNotNull) {
        this.codUnidadRaizIsNotNull = codUnidadRaizIsNotNull;
    }

    /**
     * Valor de busqueda de campo denomUnidadRaiz
     * @return String.
     */
    public String getDenomUnidadRaiz() {
        if (denomUnidadRaiz != null) {
            switch (denomUnidadRaizComparator) {
	            case STARTS_WITH:
	                return denomUnidadRaiz + "%";
	            case CONTAINS:
	                return "%" + denomUnidadRaiz + "%";
	            case ENDS_WITH:
	                return "%" + denomUnidadRaiz;
	            case EQUALS:
                	return denomUnidadRaiz;
              	default:
	            	break;
            }
        }
        return denomUnidadRaiz;
    }

    /**
     * Valor de busqueda de campo denomUnidadRaiz
     * @param denomUnidadRaiz Valor de seteo.
     */
    public void setDenomUnidadRaiz(String denomUnidadRaiz) {
        this.denomUnidadRaiz = denomUnidadRaiz;
    }

    /**
     * Tipo de comparador para la busqueda por campo denomUnidadRaiz
     * @return denomUnidadRaizComparator.
     */
    public TextComparator getDenomUnidadRaizComparator() {
        return denomUnidadRaizComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo denomUnidadRaiz
     * @param denomUnidadRaizComparator Valor de seteo.
     */
    public void setDenomUnidadRaizComparator(TextComparator denomUnidadRaizComparator) {
        this.denomUnidadRaizComparator = denomUnidadRaizComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDenomUnidadRaizIn() {
        return this.denomUnidadRaizIn;
    }

    /**
     * @param denomUnidadRaiz Valor a agregar.
     */
    public void addDenomUnidadRaizIn(String denomUnidadRaiz) {
        this.denomUnidadRaizIn.add(denomUnidadRaiz);
    }

    /**
     * Permite buscar cuando campo denomUnidadRaiz es NULL
     * @return boolean.
     */
    public boolean isDenomUnidadRaizIsNull() {
        return denomUnidadRaizIsNull;
    }

    /**
     * Permite buscar cuando campo denomUnidadRaiz es NULL
     * @param denomUnidadRaizIsNull Valor de seteo.
     */
    public void setDenomUnidadRaizIsNull(boolean denomUnidadRaizIsNull) {
        this.denomUnidadRaizIsNull = denomUnidadRaizIsNull;
    }

    /**
     * Permite buscar cuando campo denomUnidadRaiz es NOT NULL
     * @return boolean.
     */
    public boolean isDenomUnidadRaizIsNotNull() {
        return denomUnidadRaizIsNotNull;
    }

    /**
     * Permite buscar cuando campo denomUnidadRaiz es NOT NULL
     * @param denomUnidadRaizIsNotNull Valor de seteo.
     */
    public void setDenomUnidadRaizIsNotNull(boolean denomUnidadRaizIsNotNull) {
        this.denomUnidadRaizIsNotNull = denomUnidadRaizIsNotNull;
    }

    /**
     * Valor de busqueda de campo esEdp
     * @return String.
     */
    public String getEsEdp() {
        if (esEdp != null) {
            switch (esEdpComparator) {
	            case STARTS_WITH:
	                return esEdp + "%";
	            case CONTAINS:
	                return "%" + esEdp + "%";
	            case ENDS_WITH:
	                return "%" + esEdp;
	            case EQUALS:
                	return esEdp;
              	default:
	            	break;
            }
        }
        return esEdp;
    }

    /**
     * Valor de busqueda de campo esEdp
     * @param esEdp Valor de seteo.
     */
    public void setEsEdp(String esEdp) {
        this.esEdp = esEdp;
    }

    /**
     * Tipo de comparador para la busqueda por campo esEdp
     * @return esEdpComparator.
     */
    public TextComparator getEsEdpComparator() {
        return esEdpComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo esEdp
     * @param esEdpComparator Valor de seteo.
     */
    public void setEsEdpComparator(TextComparator esEdpComparator) {
        this.esEdpComparator = esEdpComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getEsEdpIn() {
        return this.esEdpIn;
    }

    /**
     * @param esEdp Valor a agregar.
     */
    public void addEsEdpIn(String esEdp) {
        this.esEdpIn.add(esEdp);
    }

    /**
     * Permite buscar cuando campo esEdp es NULL
     * @return boolean.
     */
    public boolean isEsEdpIsNull() {
        return esEdpIsNull;
    }

    /**
     * Permite buscar cuando campo esEdp es NULL
     * @param esEdpIsNull Valor de seteo.
     */
    public void setEsEdpIsNull(boolean esEdpIsNull) {
        this.esEdpIsNull = esEdpIsNull;
    }

    /**
     * Permite buscar cuando campo esEdp es NOT NULL
     * @return boolean.
     */
    public boolean isEsEdpIsNotNull() {
        return esEdpIsNotNull;
    }

    /**
     * Permite buscar cuando campo esEdp es NOT NULL
     * @param esEdpIsNotNull Valor de seteo.
     */
    public void setEsEdpIsNotNull(boolean esEdpIsNotNull) {
        this.esEdpIsNotNull = esEdpIsNotNull;
    }

    /**
     * Valor de busqueda de campo codEdpPrincipal
     * @return String.
     */
    public String getCodEdpPrincipal() {
        if (codEdpPrincipal != null) {
            switch (codEdpPrincipalComparator) {
	            case STARTS_WITH:
	                return codEdpPrincipal + "%";
	            case CONTAINS:
	                return "%" + codEdpPrincipal + "%";
	            case ENDS_WITH:
	                return "%" + codEdpPrincipal;
	            case EQUALS:
                	return codEdpPrincipal;
              	default:
	            	break;
            }
        }
        return codEdpPrincipal;
    }

    /**
     * Valor de busqueda de campo codEdpPrincipal
     * @param codEdpPrincipal Valor de seteo.
     */
    public void setCodEdpPrincipal(String codEdpPrincipal) {
        this.codEdpPrincipal = codEdpPrincipal;
    }

    /**
     * Tipo de comparador para la busqueda por campo codEdpPrincipal
     * @return codEdpPrincipalComparator.
     */
    public TextComparator getCodEdpPrincipalComparator() {
        return codEdpPrincipalComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codEdpPrincipal
     * @param codEdpPrincipalComparator Valor de seteo.
     */
    public void setCodEdpPrincipalComparator(TextComparator codEdpPrincipalComparator) {
        this.codEdpPrincipalComparator = codEdpPrincipalComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodEdpPrincipalIn() {
        return this.codEdpPrincipalIn;
    }

    /**
     * @param codEdpPrincipal Valor a agregar.
     */
    public void addCodEdpPrincipalIn(String codEdpPrincipal) {
        this.codEdpPrincipalIn.add(codEdpPrincipal);
    }

    /**
     * Permite buscar cuando campo codEdpPrincipal es NULL
     * @return boolean.
     */
    public boolean isCodEdpPrincipalIsNull() {
        return codEdpPrincipalIsNull;
    }

    /**
     * Permite buscar cuando campo codEdpPrincipal es NULL
     * @param codEdpPrincipalIsNull Valor de seteo.
     */
    public void setCodEdpPrincipalIsNull(boolean codEdpPrincipalIsNull) {
        this.codEdpPrincipalIsNull = codEdpPrincipalIsNull;
    }

    /**
     * Permite buscar cuando campo codEdpPrincipal es NOT NULL
     * @return boolean.
     */
    public boolean isCodEdpPrincipalIsNotNull() {
        return codEdpPrincipalIsNotNull;
    }

    /**
     * Permite buscar cuando campo codEdpPrincipal es NOT NULL
     * @param codEdpPrincipalIsNotNull Valor de seteo.
     */
    public void setCodEdpPrincipalIsNotNull(boolean codEdpPrincipalIsNotNull) {
        this.codEdpPrincipalIsNotNull = codEdpPrincipalIsNotNull;
    }

    /**
     * Valor de busqueda de campo denomEdpPrincipal
     * @return String.
     */
    public String getDenomEdpPrincipal() {
        if (denomEdpPrincipal != null) {
            switch (denomEdpPrincipalComparator) {
	            case STARTS_WITH:
	                return denomEdpPrincipal + "%";
	            case CONTAINS:
	                return "%" + denomEdpPrincipal + "%";
	            case ENDS_WITH:
	                return "%" + denomEdpPrincipal;
	            case EQUALS:
                	return denomEdpPrincipal;
              	default:
	            	break;
            }
        }
        return denomEdpPrincipal;
    }

    /**
     * Valor de busqueda de campo denomEdpPrincipal
     * @param denomEdpPrincipal Valor de seteo.
     */
    public void setDenomEdpPrincipal(String denomEdpPrincipal) {
        this.denomEdpPrincipal = denomEdpPrincipal;
    }

    /**
     * Tipo de comparador para la busqueda por campo denomEdpPrincipal
     * @return denomEdpPrincipalComparator.
     */
    public TextComparator getDenomEdpPrincipalComparator() {
        return denomEdpPrincipalComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo denomEdpPrincipal
     * @param denomEdpPrincipalComparator Valor de seteo.
     */
    public void setDenomEdpPrincipalComparator(TextComparator denomEdpPrincipalComparator) {
        this.denomEdpPrincipalComparator = denomEdpPrincipalComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDenomEdpPrincipalIn() {
        return this.denomEdpPrincipalIn;
    }

    /**
     * @param denomEdpPrincipal Valor a agregar.
     */
    public void addDenomEdpPrincipalIn(String denomEdpPrincipal) {
        this.denomEdpPrincipalIn.add(denomEdpPrincipal);
    }

    /**
     * Permite buscar cuando campo denomEdpPrincipal es NULL
     * @return boolean.
     */
    public boolean isDenomEdpPrincipalIsNull() {
        return denomEdpPrincipalIsNull;
    }

    /**
     * Permite buscar cuando campo denomEdpPrincipal es NULL
     * @param denomEdpPrincipalIsNull Valor de seteo.
     */
    public void setDenomEdpPrincipalIsNull(boolean denomEdpPrincipalIsNull) {
        this.denomEdpPrincipalIsNull = denomEdpPrincipalIsNull;
    }

    /**
     * Permite buscar cuando campo denomEdpPrincipal es NOT NULL
     * @return boolean.
     */
    public boolean isDenomEdpPrincipalIsNotNull() {
        return denomEdpPrincipalIsNotNull;
    }

    /**
     * Permite buscar cuando campo denomEdpPrincipal es NOT NULL
     * @param denomEdpPrincipalIsNotNull Valor de seteo.
     */
    public void setDenomEdpPrincipalIsNotNull(boolean denomEdpPrincipalIsNotNull) {
        this.denomEdpPrincipalIsNotNull = denomEdpPrincipalIsNotNull;
    }

    /**
     * Valor de busqueda de campo codTipoEntPublic
     * @return String.
     */
    public String getCodTipoEntPublic() {
        if (codTipoEntPublic != null) {
            switch (codTipoEntPublicComparator) {
	            case STARTS_WITH:
	                return codTipoEntPublic + "%";
	            case CONTAINS:
	                return "%" + codTipoEntPublic + "%";
	            case ENDS_WITH:
	                return "%" + codTipoEntPublic;
	            case EQUALS:
                	return codTipoEntPublic;
              	default:
	            	break;
            }
        }
        return codTipoEntPublic;
    }

    /**
     * Valor de busqueda de campo codTipoEntPublic
     * @param codTipoEntPublic Valor de seteo.
     */
    public void setCodTipoEntPublic(String codTipoEntPublic) {
        this.codTipoEntPublic = codTipoEntPublic;
    }

    /**
     * Tipo de comparador para la busqueda por campo codTipoEntPublic
     * @return codTipoEntPublicComparator.
     */
    public TextComparator getCodTipoEntPublicComparator() {
        return codTipoEntPublicComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codTipoEntPublic
     * @param codTipoEntPublicComparator Valor de seteo.
     */
    public void setCodTipoEntPublicComparator(TextComparator codTipoEntPublicComparator) {
        this.codTipoEntPublicComparator = codTipoEntPublicComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodTipoEntPublicIn() {
        return this.codTipoEntPublicIn;
    }

    /**
     * @param codTipoEntPublic Valor a agregar.
     */
    public void addCodTipoEntPublicIn(String codTipoEntPublic) {
        this.codTipoEntPublicIn.add(codTipoEntPublic);
    }

    /**
     * Permite buscar cuando campo codTipoEntPublic es NULL
     * @return boolean.
     */
    public boolean isCodTipoEntPublicIsNull() {
        return codTipoEntPublicIsNull;
    }

    /**
     * Permite buscar cuando campo codTipoEntPublic es NULL
     * @param codTipoEntPublicIsNull Valor de seteo.
     */
    public void setCodTipoEntPublicIsNull(boolean codTipoEntPublicIsNull) {
        this.codTipoEntPublicIsNull = codTipoEntPublicIsNull;
    }

    /**
     * Permite buscar cuando campo codTipoEntPublic es NOT NULL
     * @return boolean.
     */
    public boolean isCodTipoEntPublicIsNotNull() {
        return codTipoEntPublicIsNotNull;
    }

    /**
     * Permite buscar cuando campo codTipoEntPublic es NOT NULL
     * @param codTipoEntPublicIsNotNull Valor de seteo.
     */
    public void setCodTipoEntPublicIsNotNull(boolean codTipoEntPublicIsNotNull) {
        this.codTipoEntPublicIsNotNull = codTipoEntPublicIsNotNull;
    }

    /**
     * Valor de busqueda de campo codTipoUnidad
     * @return String.
     */
    public String getCodTipoUnidad() {
        if (codTipoUnidad != null) {
            switch (codTipoUnidadComparator) {
	            case STARTS_WITH:
	                return codTipoUnidad + "%";
	            case CONTAINS:
	                return "%" + codTipoUnidad + "%";
	            case ENDS_WITH:
	                return "%" + codTipoUnidad;
	            case EQUALS:
                	return codTipoUnidad;
              	default:
	            	break;
            }
        }
        return codTipoUnidad;
    }

    /**
     * Valor de busqueda de campo codTipoUnidad
     * @param codTipoUnidad Valor de seteo.
     */
    public void setCodTipoUnidad(String codTipoUnidad) {
        this.codTipoUnidad = codTipoUnidad;
    }

    /**
     * Tipo de comparador para la busqueda por campo codTipoUnidad
     * @return codTipoUnidadComparator.
     */
    public TextComparator getCodTipoUnidadComparator() {
        return codTipoUnidadComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codTipoUnidad
     * @param codTipoUnidadComparator Valor de seteo.
     */
    public void setCodTipoUnidadComparator(TextComparator codTipoUnidadComparator) {
        this.codTipoUnidadComparator = codTipoUnidadComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodTipoUnidadIn() {
        return this.codTipoUnidadIn;
    }

    /**
     * @param codTipoUnidad Valor a agregar.
     */
    public void addCodTipoUnidadIn(String codTipoUnidad) {
        this.codTipoUnidadIn.add(codTipoUnidad);
    }

    /**
     * Permite buscar cuando campo codTipoUnidad es NULL
     * @return boolean.
     */
    public boolean isCodTipoUnidadIsNull() {
        return codTipoUnidadIsNull;
    }

    /**
     * Permite buscar cuando campo codTipoUnidad es NULL
     * @param codTipoUnidadIsNull Valor de seteo.
     */
    public void setCodTipoUnidadIsNull(boolean codTipoUnidadIsNull) {
        this.codTipoUnidadIsNull = codTipoUnidadIsNull;
    }

    /**
     * Permite buscar cuando campo codTipoUnidad es NOT NULL
     * @return boolean.
     */
    public boolean isCodTipoUnidadIsNotNull() {
        return codTipoUnidadIsNotNull;
    }

    /**
     * Permite buscar cuando campo codTipoUnidad es NOT NULL
     * @param codTipoUnidadIsNotNull Valor de seteo.
     */
    public void setCodTipoUnidadIsNotNull(boolean codTipoUnidadIsNotNull) {
        this.codTipoUnidadIsNotNull = codTipoUnidadIsNotNull;
    }

    /**
     * Valor de busqueda de campo codAmbTerritorial
     * @return String.
     */
    public String getCodAmbTerritorial() {
        if (codAmbTerritorial != null) {
            switch (codAmbTerritorialComparator) {
	            case STARTS_WITH:
	                return codAmbTerritorial + "%";
	            case CONTAINS:
	                return "%" + codAmbTerritorial + "%";
	            case ENDS_WITH:
	                return "%" + codAmbTerritorial;
	            case EQUALS:
                	return codAmbTerritorial;
              	default:
	            	break;
            }
        }
        return codAmbTerritorial;
    }

    /**
     * Valor de busqueda de campo codAmbTerritorial
     * @param codAmbTerritorial Valor de seteo.
     */
    public void setCodAmbTerritorial(String codAmbTerritorial) {
        this.codAmbTerritorial = codAmbTerritorial;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbTerritorial
     * @return codAmbTerritorialComparator.
     */
    public TextComparator getCodAmbTerritorialComparator() {
        return codAmbTerritorialComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbTerritorial
     * @param codAmbTerritorialComparator Valor de seteo.
     */
    public void setCodAmbTerritorialComparator(TextComparator codAmbTerritorialComparator) {
        this.codAmbTerritorialComparator = codAmbTerritorialComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodAmbTerritorialIn() {
        return this.codAmbTerritorialIn;
    }

    /**
     * @param codAmbTerritorial Valor a agregar.
     */
    public void addCodAmbTerritorialIn(String codAmbTerritorial) {
        this.codAmbTerritorialIn.add(codAmbTerritorial);
    }

    /**
     * Permite buscar cuando campo codAmbTerritorial es NULL
     * @return boolean.
     */
    public boolean isCodAmbTerritorialIsNull() {
        return codAmbTerritorialIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbTerritorial es NULL
     * @param codAmbTerritorialIsNull Valor de seteo.
     */
    public void setCodAmbTerritorialIsNull(boolean codAmbTerritorialIsNull) {
        this.codAmbTerritorialIsNull = codAmbTerritorialIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbTerritorial es NOT NULL
     * @return boolean.
     */
    public boolean isCodAmbTerritorialIsNotNull() {
        return codAmbTerritorialIsNotNull;
    }

    /**
     * Permite buscar cuando campo codAmbTerritorial es NOT NULL
     * @param codAmbTerritorialIsNotNull Valor de seteo.
     */
    public void setCodAmbTerritorialIsNotNull(boolean codAmbTerritorialIsNotNull) {
        this.codAmbTerritorialIsNotNull = codAmbTerritorialIsNotNull;
    }

    /**
     * Valor de busqueda de campo codAmbEntGeografica
     * @return String.
     */
    public String getCodAmbEntGeografica() {
        if (codAmbEntGeografica != null) {
            switch (codAmbEntGeograficaComparator) {
	            case STARTS_WITH:
	                return codAmbEntGeografica + "%";
	            case CONTAINS:
	                return "%" + codAmbEntGeografica + "%";
	            case ENDS_WITH:
	                return "%" + codAmbEntGeografica;
	            case EQUALS:
                	return codAmbEntGeografica;
              	default:
	            	break;
            }
        }
        return codAmbEntGeografica;
    }

    /**
     * Valor de busqueda de campo codAmbEntGeografica
     * @param codAmbEntGeografica Valor de seteo.
     */
    public void setCodAmbEntGeografica(String codAmbEntGeografica) {
        this.codAmbEntGeografica = codAmbEntGeografica;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbEntGeografica
     * @return codAmbEntGeograficaComparator.
     */
    public TextComparator getCodAmbEntGeograficaComparator() {
        return codAmbEntGeograficaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbEntGeografica
     * @param codAmbEntGeograficaComparator Valor de seteo.
     */
    public void setCodAmbEntGeograficaComparator(TextComparator codAmbEntGeograficaComparator) {
        this.codAmbEntGeograficaComparator = codAmbEntGeograficaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodAmbEntGeograficaIn() {
        return this.codAmbEntGeograficaIn;
    }

    /**
     * @param codAmbEntGeografica Valor a agregar.
     */
    public void addCodAmbEntGeograficaIn(String codAmbEntGeografica) {
        this.codAmbEntGeograficaIn.add(codAmbEntGeografica);
    }

    /**
     * Permite buscar cuando campo codAmbEntGeografica es NULL
     * @return boolean.
     */
    public boolean isCodAmbEntGeograficaIsNull() {
        return codAmbEntGeograficaIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbEntGeografica es NULL
     * @param codAmbEntGeograficaIsNull Valor de seteo.
     */
    public void setCodAmbEntGeograficaIsNull(boolean codAmbEntGeograficaIsNull) {
        this.codAmbEntGeograficaIsNull = codAmbEntGeograficaIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbEntGeografica es NOT NULL
     * @return boolean.
     */
    public boolean isCodAmbEntGeograficaIsNotNull() {
        return codAmbEntGeograficaIsNotNull;
    }

    /**
     * Permite buscar cuando campo codAmbEntGeografica es NOT NULL
     * @param codAmbEntGeograficaIsNotNull Valor de seteo.
     */
    public void setCodAmbEntGeograficaIsNotNull(boolean codAmbEntGeograficaIsNotNull) {
        this.codAmbEntGeograficaIsNotNull = codAmbEntGeograficaIsNotNull;
    }

    /**
     * Valor de busqueda de campo codAmbPais
     * @return String.
     */
    public String getCodAmbPais() {
        if (codAmbPais != null) {
            switch (codAmbPaisComparator) {
	            case STARTS_WITH:
	                return codAmbPais + "%";
	            case CONTAINS:
	                return "%" + codAmbPais + "%";
	            case ENDS_WITH:
	                return "%" + codAmbPais;
	            case EQUALS:
                	return codAmbPais;
              	default:
	            	break;
            }
        }
        return codAmbPais;
    }

    /**
     * Valor de busqueda de campo codAmbPais
     * @param codAmbPais Valor de seteo.
     */
    public void setCodAmbPais(String codAmbPais) {
        this.codAmbPais = codAmbPais;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbPais
     * @return codAmbPaisComparator.
     */
    public TextComparator getCodAmbPaisComparator() {
        return codAmbPaisComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbPais
     * @param codAmbPaisComparator Valor de seteo.
     */
    public void setCodAmbPaisComparator(TextComparator codAmbPaisComparator) {
        this.codAmbPaisComparator = codAmbPaisComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodAmbPaisIn() {
        return this.codAmbPaisIn;
    }

    /**
     * @param codAmbPais Valor a agregar.
     */
    public void addCodAmbPaisIn(String codAmbPais) {
        this.codAmbPaisIn.add(codAmbPais);
    }

    /**
     * Permite buscar cuando campo codAmbPais es NULL
     * @return boolean.
     */
    public boolean isCodAmbPaisIsNull() {
        return codAmbPaisIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbPais es NULL
     * @param codAmbPaisIsNull Valor de seteo.
     */
    public void setCodAmbPaisIsNull(boolean codAmbPaisIsNull) {
        this.codAmbPaisIsNull = codAmbPaisIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbPais es NOT NULL
     * @return boolean.
     */
    public boolean isCodAmbPaisIsNotNull() {
        return codAmbPaisIsNotNull;
    }

    /**
     * Permite buscar cuando campo codAmbPais es NOT NULL
     * @param codAmbPaisIsNotNull Valor de seteo.
     */
    public void setCodAmbPaisIsNotNull(boolean codAmbPaisIsNotNull) {
        this.codAmbPaisIsNotNull = codAmbPaisIsNotNull;
    }

    /**
     * Valor de busqueda de campo codAmbComunidad
     * @return String.
     */
    public String getCodAmbComunidad() {
        if (codAmbComunidad != null) {
            switch (codAmbComunidadComparator) {
	            case STARTS_WITH:
	                return codAmbComunidad + "%";
	            case CONTAINS:
	                return "%" + codAmbComunidad + "%";
	            case ENDS_WITH:
	                return "%" + codAmbComunidad;
	            case EQUALS:
                	return codAmbComunidad;
              	default:
	            	break;
            }
        }
        return codAmbComunidad;
    }

    /**
     * Valor de busqueda de campo codAmbComunidad
     * @param codAmbComunidad Valor de seteo.
     */
    public void setCodAmbComunidad(String codAmbComunidad) {
        this.codAmbComunidad = codAmbComunidad;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbComunidad
     * @return codAmbComunidadComparator.
     */
    public TextComparator getCodAmbComunidadComparator() {
        return codAmbComunidadComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbComunidad
     * @param codAmbComunidadComparator Valor de seteo.
     */
    public void setCodAmbComunidadComparator(TextComparator codAmbComunidadComparator) {
        this.codAmbComunidadComparator = codAmbComunidadComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodAmbComunidadIn() {
        return this.codAmbComunidadIn;
    }

    /**
     * @param codAmbComunidad Valor a agregar.
     */
    public void addCodAmbComunidadIn(String codAmbComunidad) {
        this.codAmbComunidadIn.add(codAmbComunidad);
    }

    /**
     * Permite buscar cuando campo codAmbComunidad es NULL
     * @return boolean.
     */
    public boolean isCodAmbComunidadIsNull() {
        return codAmbComunidadIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbComunidad es NULL
     * @param codAmbComunidadIsNull Valor de seteo.
     */
    public void setCodAmbComunidadIsNull(boolean codAmbComunidadIsNull) {
        this.codAmbComunidadIsNull = codAmbComunidadIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbComunidad es NOT NULL
     * @return boolean.
     */
    public boolean isCodAmbComunidadIsNotNull() {
        return codAmbComunidadIsNotNull;
    }

    /**
     * Permite buscar cuando campo codAmbComunidad es NOT NULL
     * @param codAmbComunidadIsNotNull Valor de seteo.
     */
    public void setCodAmbComunidadIsNotNull(boolean codAmbComunidadIsNotNull) {
        this.codAmbComunidadIsNotNull = codAmbComunidadIsNotNull;
    }

    /**
     * Valor de busqueda de campo codAmbProvincia
     * @return String.
     */
    public String getCodAmbProvincia() {
        if (codAmbProvincia != null) {
            switch (codAmbProvinciaComparator) {
	            case STARTS_WITH:
	                return codAmbProvincia + "%";
	            case CONTAINS:
	                return "%" + codAmbProvincia + "%";
	            case ENDS_WITH:
	                return "%" + codAmbProvincia;
	            case EQUALS:
                	return codAmbProvincia;
              	default:
	            	break;
            }
        }
        return codAmbProvincia;
    }

    /**
     * Valor de busqueda de campo codAmbProvincia
     * @param codAmbProvincia Valor de seteo.
     */
    public void setCodAmbProvincia(String codAmbProvincia) {
        this.codAmbProvincia = codAmbProvincia;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbProvincia
     * @return codAmbProvinciaComparator.
     */
    public TextComparator getCodAmbProvinciaComparator() {
        return codAmbProvinciaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbProvincia
     * @param codAmbProvinciaComparator Valor de seteo.
     */
    public void setCodAmbProvinciaComparator(TextComparator codAmbProvinciaComparator) {
        this.codAmbProvinciaComparator = codAmbProvinciaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodAmbProvinciaIn() {
        return this.codAmbProvinciaIn;
    }

    /**
     * @param codAmbProvincia Valor a agregar.
     */
    public void addCodAmbProvinciaIn(String codAmbProvincia) {
        this.codAmbProvinciaIn.add(codAmbProvincia);
    }

    /**
     * Permite buscar cuando campo codAmbProvincia es NULL
     * @return boolean.
     */
    public boolean isCodAmbProvinciaIsNull() {
        return codAmbProvinciaIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbProvincia es NULL
     * @param codAmbProvinciaIsNull Valor de seteo.
     */
    public void setCodAmbProvinciaIsNull(boolean codAmbProvinciaIsNull) {
        this.codAmbProvinciaIsNull = codAmbProvinciaIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbProvincia es NOT NULL
     * @return boolean.
     */
    public boolean isCodAmbProvinciaIsNotNull() {
        return codAmbProvinciaIsNotNull;
    }

    /**
     * Permite buscar cuando campo codAmbProvincia es NOT NULL
     * @param codAmbProvinciaIsNotNull Valor de seteo.
     */
    public void setCodAmbProvinciaIsNotNull(boolean codAmbProvinciaIsNotNull) {
        this.codAmbProvinciaIsNotNull = codAmbProvinciaIsNotNull;
    }

    /**
     * Valor de busqueda de campo codAmbMunicipio
     * @return String.
     */
    public String getCodAmbMunicipio() {
        if (codAmbMunicipio != null) {
            switch (codAmbMunicipioComparator) {
	            case STARTS_WITH:
	                return codAmbMunicipio + "%";
	            case CONTAINS:
	                return "%" + codAmbMunicipio + "%";
	            case ENDS_WITH:
	                return "%" + codAmbMunicipio;
	            case EQUALS:
                	return codAmbMunicipio;
              	default:
	            	break;
            }
        }
        return codAmbMunicipio;
    }

    /**
     * Valor de busqueda de campo codAmbMunicipio
     * @param codAmbMunicipio Valor de seteo.
     */
    public void setCodAmbMunicipio(String codAmbMunicipio) {
        this.codAmbMunicipio = codAmbMunicipio;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbMunicipio
     * @return codAmbMunicipioComparator.
     */
    public TextComparator getCodAmbMunicipioComparator() {
        return codAmbMunicipioComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbMunicipio
     * @param codAmbMunicipioComparator Valor de seteo.
     */
    public void setCodAmbMunicipioComparator(TextComparator codAmbMunicipioComparator) {
        this.codAmbMunicipioComparator = codAmbMunicipioComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodAmbMunicipioIn() {
        return this.codAmbMunicipioIn;
    }

    /**
     * @param codAmbMunicipio Valor a agregar.
     */
    public void addCodAmbMunicipioIn(String codAmbMunicipio) {
        this.codAmbMunicipioIn.add(codAmbMunicipio);
    }

    /**
     * Permite buscar cuando campo codAmbMunicipio es NULL
     * @return boolean.
     */
    public boolean isCodAmbMunicipioIsNull() {
        return codAmbMunicipioIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbMunicipio es NULL
     * @param codAmbMunicipioIsNull Valor de seteo.
     */
    public void setCodAmbMunicipioIsNull(boolean codAmbMunicipioIsNull) {
        this.codAmbMunicipioIsNull = codAmbMunicipioIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbMunicipio es NOT NULL
     * @return boolean.
     */
    public boolean isCodAmbMunicipioIsNotNull() {
        return codAmbMunicipioIsNotNull;
    }

    /**
     * Permite buscar cuando campo codAmbMunicipio es NOT NULL
     * @param codAmbMunicipioIsNotNull Valor de seteo.
     */
    public void setCodAmbMunicipioIsNotNull(boolean codAmbMunicipioIsNotNull) {
        this.codAmbMunicipioIsNotNull = codAmbMunicipioIsNotNull;
    }

    /**
     * Valor de busqueda de campo codAmbIsla
     * @return String.
     */
    public String getCodAmbIsla() {
        if (codAmbIsla != null) {
            switch (codAmbIslaComparator) {
	            case STARTS_WITH:
	                return codAmbIsla + "%";
	            case CONTAINS:
	                return "%" + codAmbIsla + "%";
	            case ENDS_WITH:
	                return "%" + codAmbIsla;
	            case EQUALS:
                	return codAmbIsla;
              	default:
	            	break;
            }
        }
        return codAmbIsla;
    }

    /**
     * Valor de busqueda de campo codAmbIsla
     * @param codAmbIsla Valor de seteo.
     */
    public void setCodAmbIsla(String codAmbIsla) {
        this.codAmbIsla = codAmbIsla;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbIsla
     * @return codAmbIslaComparator.
     */
    public TextComparator getCodAmbIslaComparator() {
        return codAmbIslaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbIsla
     * @param codAmbIslaComparator Valor de seteo.
     */
    public void setCodAmbIslaComparator(TextComparator codAmbIslaComparator) {
        this.codAmbIslaComparator = codAmbIslaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodAmbIslaIn() {
        return this.codAmbIslaIn;
    }

    /**
     * @param codAmbIsla Valor a agregar.
     */
    public void addCodAmbIslaIn(String codAmbIsla) {
        this.codAmbIslaIn.add(codAmbIsla);
    }

    /**
     * Permite buscar cuando campo codAmbIsla es NULL
     * @return boolean.
     */
    public boolean isCodAmbIslaIsNull() {
        return codAmbIslaIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbIsla es NULL
     * @param codAmbIslaIsNull Valor de seteo.
     */
    public void setCodAmbIslaIsNull(boolean codAmbIslaIsNull) {
        this.codAmbIslaIsNull = codAmbIslaIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbIsla es NOT NULL
     * @return boolean.
     */
    public boolean isCodAmbIslaIsNotNull() {
        return codAmbIslaIsNotNull;
    }

    /**
     * Permite buscar cuando campo codAmbIsla es NOT NULL
     * @param codAmbIslaIsNotNull Valor de seteo.
     */
    public void setCodAmbIslaIsNotNull(boolean codAmbIslaIsNotNull) {
        this.codAmbIslaIsNotNull = codAmbIslaIsNotNull;
    }

    /**
     * Valor de busqueda de campo codAmbElm
     * @return String.
     */
    public String getCodAmbElm() {
        if (codAmbElm != null) {
            switch (codAmbElmComparator) {
	            case STARTS_WITH:
	                return codAmbElm + "%";
	            case CONTAINS:
	                return "%" + codAmbElm + "%";
	            case ENDS_WITH:
	                return "%" + codAmbElm;
	            case EQUALS:
                	return codAmbElm;
              	default:
	            	break;
            }
        }
        return codAmbElm;
    }

    /**
     * Valor de busqueda de campo codAmbElm
     * @param codAmbElm Valor de seteo.
     */
    public void setCodAmbElm(String codAmbElm) {
        this.codAmbElm = codAmbElm;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbElm
     * @return codAmbElmComparator.
     */
    public TextComparator getCodAmbElmComparator() {
        return codAmbElmComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbElm
     * @param codAmbElmComparator Valor de seteo.
     */
    public void setCodAmbElmComparator(TextComparator codAmbElmComparator) {
        this.codAmbElmComparator = codAmbElmComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodAmbElmIn() {
        return this.codAmbElmIn;
    }

    /**
     * @param codAmbElm Valor a agregar.
     */
    public void addCodAmbElmIn(String codAmbElm) {
        this.codAmbElmIn.add(codAmbElm);
    }

    /**
     * Permite buscar cuando campo codAmbElm es NULL
     * @return boolean.
     */
    public boolean isCodAmbElmIsNull() {
        return codAmbElmIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbElm es NULL
     * @param codAmbElmIsNull Valor de seteo.
     */
    public void setCodAmbElmIsNull(boolean codAmbElmIsNull) {
        this.codAmbElmIsNull = codAmbElmIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbElm es NOT NULL
     * @return boolean.
     */
    public boolean isCodAmbElmIsNotNull() {
        return codAmbElmIsNotNull;
    }

    /**
     * Permite buscar cuando campo codAmbElm es NOT NULL
     * @param codAmbElmIsNotNull Valor de seteo.
     */
    public void setCodAmbElmIsNotNull(boolean codAmbElmIsNotNull) {
        this.codAmbElmIsNotNull = codAmbElmIsNotNull;
    }

    /**
     * Valor de busqueda de campo codAmbLocExtranjera
     * @return String.
     */
    public String getCodAmbLocExtranjera() {
        if (codAmbLocExtranjera != null) {
            switch (codAmbLocExtranjeraComparator) {
	            case STARTS_WITH:
	                return codAmbLocExtranjera + "%";
	            case CONTAINS:
	                return "%" + codAmbLocExtranjera + "%";
	            case ENDS_WITH:
	                return "%" + codAmbLocExtranjera;
	            case EQUALS:
                	return codAmbLocExtranjera;
              	default:
	            	break;
            }
        }
        return codAmbLocExtranjera;
    }

    /**
     * Valor de busqueda de campo codAmbLocExtranjera
     * @param codAmbLocExtranjera Valor de seteo.
     */
    public void setCodAmbLocExtranjera(String codAmbLocExtranjera) {
        this.codAmbLocExtranjera = codAmbLocExtranjera;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbLocExtranjera
     * @return codAmbLocExtranjeraComparator.
     */
    public TextComparator getCodAmbLocExtranjeraComparator() {
        return codAmbLocExtranjeraComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codAmbLocExtranjera
     * @param codAmbLocExtranjeraComparator Valor de seteo.
     */
    public void setCodAmbLocExtranjeraComparator(TextComparator codAmbLocExtranjeraComparator) {
        this.codAmbLocExtranjeraComparator = codAmbLocExtranjeraComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodAmbLocExtranjeraIn() {
        return this.codAmbLocExtranjeraIn;
    }

    /**
     * @param codAmbLocExtranjera Valor a agregar.
     */
    public void addCodAmbLocExtranjeraIn(String codAmbLocExtranjera) {
        this.codAmbLocExtranjeraIn.add(codAmbLocExtranjera);
    }

    /**
     * Permite buscar cuando campo codAmbLocExtranjera es NULL
     * @return boolean.
     */
    public boolean isCodAmbLocExtranjeraIsNull() {
        return codAmbLocExtranjeraIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbLocExtranjera es NULL
     * @param codAmbLocExtranjeraIsNull Valor de seteo.
     */
    public void setCodAmbLocExtranjeraIsNull(boolean codAmbLocExtranjeraIsNull) {
        this.codAmbLocExtranjeraIsNull = codAmbLocExtranjeraIsNull;
    }

    /**
     * Permite buscar cuando campo codAmbLocExtranjera es NOT NULL
     * @return boolean.
     */
    public boolean isCodAmbLocExtranjeraIsNotNull() {
        return codAmbLocExtranjeraIsNotNull;
    }

    /**
     * Permite buscar cuando campo codAmbLocExtranjera es NOT NULL
     * @param codAmbLocExtranjeraIsNotNull Valor de seteo.
     */
    public void setCodAmbLocExtranjeraIsNotNull(boolean codAmbLocExtranjeraIsNotNull) {
        this.codAmbLocExtranjeraIsNotNull = codAmbLocExtranjeraIsNotNull;
    }

    /**
     * Valor de busqueda de campo competencias
     * @return String.
     */
    public String getCompetencias() {
        if (competencias != null) {
            switch (competenciasComparator) {
	            case STARTS_WITH:
	                return competencias + "%";
	            case CONTAINS:
	                return "%" + competencias + "%";
	            case ENDS_WITH:
	                return "%" + competencias;
	            case EQUALS:
                	return competencias;
              	default:
	            	break;
            }
        }
        return competencias;
    }

    /**
     * Valor de busqueda de campo competencias
     * @param competencias Valor de seteo.
     */
    public void setCompetencias(String competencias) {
        this.competencias = competencias;
    }

    /**
     * Tipo de comparador para la busqueda por campo competencias
     * @return competenciasComparator.
     */
    public TextComparator getCompetenciasComparator() {
        return competenciasComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo competencias
     * @param competenciasComparator Valor de seteo.
     */
    public void setCompetenciasComparator(TextComparator competenciasComparator) {
        this.competenciasComparator = competenciasComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCompetenciasIn() {
        return this.competenciasIn;
    }

    /**
     * @param competencias Valor a agregar.
     */
    public void addCompetenciasIn(String competencias) {
        this.competenciasIn.add(competencias);
    }

    /**
     * Permite buscar cuando campo competencias es NULL
     * @return boolean.
     */
    public boolean isCompetenciasIsNull() {
        return competenciasIsNull;
    }

    /**
     * Permite buscar cuando campo competencias es NULL
     * @param competenciasIsNull Valor de seteo.
     */
    public void setCompetenciasIsNull(boolean competenciasIsNull) {
        this.competenciasIsNull = competenciasIsNull;
    }

    /**
     * Permite buscar cuando campo competencias es NOT NULL
     * @return boolean.
     */
    public boolean isCompetenciasIsNotNull() {
        return competenciasIsNotNull;
    }

    /**
     * Permite buscar cuando campo competencias es NOT NULL
     * @param competenciasIsNotNull Valor de seteo.
     */
    public void setCompetenciasIsNotNull(boolean competenciasIsNotNull) {
        this.competenciasIsNotNull = competenciasIsNotNull;
    }

    /**
     * Valor de busqueda de campo disposicionLegal
     * @return String.
     */
    public String getDisposicionLegal() {
        if (disposicionLegal != null) {
            switch (disposicionLegalComparator) {
	            case STARTS_WITH:
	                return disposicionLegal + "%";
	            case CONTAINS:
	                return "%" + disposicionLegal + "%";
	            case ENDS_WITH:
	                return "%" + disposicionLegal;
	            case EQUALS:
                	return disposicionLegal;
              	default:
	            	break;
            }
        }
        return disposicionLegal;
    }

    /**
     * Valor de busqueda de campo disposicionLegal
     * @param disposicionLegal Valor de seteo.
     */
    public void setDisposicionLegal(String disposicionLegal) {
        this.disposicionLegal = disposicionLegal;
    }

    /**
     * Tipo de comparador para la busqueda por campo disposicionLegal
     * @return disposicionLegalComparator.
     */
    public TextComparator getDisposicionLegalComparator() {
        return disposicionLegalComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo disposicionLegal
     * @param disposicionLegalComparator Valor de seteo.
     */
    public void setDisposicionLegalComparator(TextComparator disposicionLegalComparator) {
        this.disposicionLegalComparator = disposicionLegalComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDisposicionLegalIn() {
        return this.disposicionLegalIn;
    }

    /**
     * @param disposicionLegal Valor a agregar.
     */
    public void addDisposicionLegalIn(String disposicionLegal) {
        this.disposicionLegalIn.add(disposicionLegal);
    }

    /**
     * Permite buscar cuando campo disposicionLegal es NULL
     * @return boolean.
     */
    public boolean isDisposicionLegalIsNull() {
        return disposicionLegalIsNull;
    }

    /**
     * Permite buscar cuando campo disposicionLegal es NULL
     * @param disposicionLegalIsNull Valor de seteo.
     */
    public void setDisposicionLegalIsNull(boolean disposicionLegalIsNull) {
        this.disposicionLegalIsNull = disposicionLegalIsNull;
    }

    /**
     * Permite buscar cuando campo disposicionLegal es NOT NULL
     * @return boolean.
     */
    public boolean isDisposicionLegalIsNotNull() {
        return disposicionLegalIsNotNull;
    }

    /**
     * Permite buscar cuando campo disposicionLegal es NOT NULL
     * @param disposicionLegalIsNotNull Valor de seteo.
     */
    public void setDisposicionLegalIsNotNull(boolean disposicionLegalIsNotNull) {
        this.disposicionLegalIsNotNull = disposicionLegalIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaAltaOficial
     * @return ${field.getName()}Min.
     */
    public Date getFechaAltaOficialMin() {
        if (fechaAltaOficialMin != null) {
            return DateUtil.toDayBegin(fechaAltaOficialMin);
        }
        return fechaAltaOficialMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaAltaOficial
     * @param fechaAltaOficialMin Valor de seteo.
     */
    public void setFechaAltaOficialMin(Date fechaAltaOficialMin) {
        this.fechaAltaOficialMin = fechaAltaOficialMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaAltaOficial
     * @return fechaAltaOficialMax.
     */
    public Date getFechaAltaOficialMax() {
        if (fechaAltaOficialMax != null) {
            return DateUtil.toDayEnd(fechaAltaOficialMax);
        }
        return fechaAltaOficialMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaAltaOficial
     * @param fechaAltaOficialMax Valor de seteo.
     */
    public void setFechaAltaOficialMax(Date fechaAltaOficialMax) {
        this.fechaAltaOficialMax = fechaAltaOficialMax;
    }

    /**
     * Permite buscar cuando campo fechaAltaOficial es NULL
     * @return boolean.
     */
    public boolean isFechaAltaOficialIsNull() {
        return fechaAltaOficialIsNull;
    }

    /**
     * Permite buscar cuando campo fechaAltaOficial es NULL
     * @param fechaAltaOficialIsNull Valor de seteo.
     */
    public void setFechaAltaOficialIsNull(boolean fechaAltaOficialIsNull) {
        this.fechaAltaOficialIsNull = fechaAltaOficialIsNull;
    }

    /**
     * Permite buscar cuando campo fechaAltaOficial es NOT NULL
     * @return boolean.
     */
    public boolean isFechaAltaOficialIsNotNull() {
        return fechaAltaOficialIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaAltaOficial es NOT NULL
     * @param fechaAltaOficialIsNotNull Valor de seteo.
     */
    public void setFechaAltaOficialIsNotNull(boolean fechaAltaOficialIsNotNull) {
        this.fechaAltaOficialIsNotNull = fechaAltaOficialIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaBajaOficial
     * @return ${field.getName()}Min.
     */
    public Date getFechaBajaOficialMin() {
        if (fechaBajaOficialMin != null) {
            return DateUtil.toDayBegin(fechaBajaOficialMin);
        }
        return fechaBajaOficialMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaBajaOficial
     * @param fechaBajaOficialMin Valor de seteo.
     */
    public void setFechaBajaOficialMin(Date fechaBajaOficialMin) {
        this.fechaBajaOficialMin = fechaBajaOficialMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaBajaOficial
     * @return fechaBajaOficialMax.
     */
    public Date getFechaBajaOficialMax() {
        if (fechaBajaOficialMax != null) {
            return DateUtil.toDayEnd(fechaBajaOficialMax);
        }
        return fechaBajaOficialMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaBajaOficial
     * @param fechaBajaOficialMax Valor de seteo.
     */
    public void setFechaBajaOficialMax(Date fechaBajaOficialMax) {
        this.fechaBajaOficialMax = fechaBajaOficialMax;
    }

    /**
     * Permite buscar cuando campo fechaBajaOficial es NULL
     * @return boolean.
     */
    public boolean isFechaBajaOficialIsNull() {
        return fechaBajaOficialIsNull;
    }

    /**
     * Permite buscar cuando campo fechaBajaOficial es NULL
     * @param fechaBajaOficialIsNull Valor de seteo.
     */
    public void setFechaBajaOficialIsNull(boolean fechaBajaOficialIsNull) {
        this.fechaBajaOficialIsNull = fechaBajaOficialIsNull;
    }

    /**
     * Permite buscar cuando campo fechaBajaOficial es NOT NULL
     * @return boolean.
     */
    public boolean isFechaBajaOficialIsNotNull() {
        return fechaBajaOficialIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaBajaOficial es NOT NULL
     * @param fechaBajaOficialIsNotNull Valor de seteo.
     */
    public void setFechaBajaOficialIsNotNull(boolean fechaBajaOficialIsNotNull) {
        this.fechaBajaOficialIsNotNull = fechaBajaOficialIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaExtincion
     * @return ${field.getName()}Min.
     */
    public Date getFechaExtincionMin() {
        if (fechaExtincionMin != null) {
            return DateUtil.toDayBegin(fechaExtincionMin);
        }
        return fechaExtincionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaExtincion
     * @param fechaExtincionMin Valor de seteo.
     */
    public void setFechaExtincionMin(Date fechaExtincionMin) {
        this.fechaExtincionMin = fechaExtincionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaExtincion
     * @return fechaExtincionMax.
     */
    public Date getFechaExtincionMax() {
        if (fechaExtincionMax != null) {
            return DateUtil.toDayEnd(fechaExtincionMax);
        }
        return fechaExtincionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaExtincion
     * @param fechaExtincionMax Valor de seteo.
     */
    public void setFechaExtincionMax(Date fechaExtincionMax) {
        this.fechaExtincionMax = fechaExtincionMax;
    }

    /**
     * Permite buscar cuando campo fechaExtincion es NULL
     * @return boolean.
     */
    public boolean isFechaExtincionIsNull() {
        return fechaExtincionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaExtincion es NULL
     * @param fechaExtincionIsNull Valor de seteo.
     */
    public void setFechaExtincionIsNull(boolean fechaExtincionIsNull) {
        this.fechaExtincionIsNull = fechaExtincionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaExtincion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaExtincionIsNotNull() {
        return fechaExtincionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaExtincion es NOT NULL
     * @param fechaExtincionIsNotNull Valor de seteo.
     */
    public void setFechaExtincionIsNotNull(boolean fechaExtincionIsNotNull) {
        this.fechaExtincionIsNotNull = fechaExtincionIsNotNull;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaAnulacion
     * @return ${field.getName()}Min.
     */
    public Date getFechaAnulacionMin() {
        if (fechaAnulacionMin != null) {
            return DateUtil.toDayBegin(fechaAnulacionMin);
        }
        return fechaAnulacionMin;
    }

    /**
     * Valor inferior de rango de busqueda de fecha fechaAnulacion
     * @param fechaAnulacionMin Valor de seteo.
     */
    public void setFechaAnulacionMin(Date fechaAnulacionMin) {
        this.fechaAnulacionMin = fechaAnulacionMin;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaAnulacion
     * @return fechaAnulacionMax.
     */
    public Date getFechaAnulacionMax() {
        if (fechaAnulacionMax != null) {
            return DateUtil.toDayEnd(fechaAnulacionMax);
        }
        return fechaAnulacionMax;
    }

    /**
     * Valor superior de rango de busqueda de fecha fechaAnulacion
     * @param fechaAnulacionMax Valor de seteo.
     */
    public void setFechaAnulacionMax(Date fechaAnulacionMax) {
        this.fechaAnulacionMax = fechaAnulacionMax;
    }

    /**
     * Permite buscar cuando campo fechaAnulacion es NULL
     * @return boolean.
     */
    public boolean isFechaAnulacionIsNull() {
        return fechaAnulacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaAnulacion es NULL
     * @param fechaAnulacionIsNull Valor de seteo.
     */
    public void setFechaAnulacionIsNull(boolean fechaAnulacionIsNull) {
        this.fechaAnulacionIsNull = fechaAnulacionIsNull;
    }

    /**
     * Permite buscar cuando campo fechaAnulacion es NOT NULL
     * @return boolean.
     */
    public boolean isFechaAnulacionIsNotNull() {
        return fechaAnulacionIsNotNull;
    }

    /**
     * Permite buscar cuando campo fechaAnulacion es NOT NULL
     * @param fechaAnulacionIsNotNull Valor de seteo.
     */
    public void setFechaAnulacionIsNotNull(boolean fechaAnulacionIsNotNull) {
        this.fechaAnulacionIsNotNull = fechaAnulacionIsNotNull;
    }

    /**
     * Valor de busqueda de campo observGenerales
     * @return String.
     */
    public String getObservGenerales() {
        if (observGenerales != null) {
            switch (observGeneralesComparator) {
	            case STARTS_WITH:
	                return observGenerales + "%";
	            case CONTAINS:
	                return "%" + observGenerales + "%";
	            case ENDS_WITH:
	                return "%" + observGenerales;
	            case EQUALS:
                	return observGenerales;
              	default:
	            	break;
            }
        }
        return observGenerales;
    }

    /**
     * Valor de busqueda de campo observGenerales
     * @param observGenerales Valor de seteo.
     */
    public void setObservGenerales(String observGenerales) {
        this.observGenerales = observGenerales;
    }

    /**
     * Tipo de comparador para la busqueda por campo observGenerales
     * @return observGeneralesComparator.
     */
    public TextComparator getObservGeneralesComparator() {
        return observGeneralesComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo observGenerales
     * @param observGeneralesComparator Valor de seteo.
     */
    public void setObservGeneralesComparator(TextComparator observGeneralesComparator) {
        this.observGeneralesComparator = observGeneralesComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getObservGeneralesIn() {
        return this.observGeneralesIn;
    }

    /**
     * @param observGenerales Valor a agregar.
     */
    public void addObservGeneralesIn(String observGenerales) {
        this.observGeneralesIn.add(observGenerales);
    }

    /**
     * Permite buscar cuando campo observGenerales es NULL
     * @return boolean.
     */
    public boolean isObservGeneralesIsNull() {
        return observGeneralesIsNull;
    }

    /**
     * Permite buscar cuando campo observGenerales es NULL
     * @param observGeneralesIsNull Valor de seteo.
     */
    public void setObservGeneralesIsNull(boolean observGeneralesIsNull) {
        this.observGeneralesIsNull = observGeneralesIsNull;
    }

    /**
     * Permite buscar cuando campo observGenerales es NOT NULL
     * @return boolean.
     */
    public boolean isObservGeneralesIsNotNull() {
        return observGeneralesIsNotNull;
    }

    /**
     * Permite buscar cuando campo observGenerales es NOT NULL
     * @param observGeneralesIsNotNull Valor de seteo.
     */
    public void setObservGeneralesIsNotNull(boolean observGeneralesIsNotNull) {
        this.observGeneralesIsNotNull = observGeneralesIsNotNull;
    }

    /**
     * Valor de busqueda de campo observBaja
     * @return String.
     */
    public String getObservBaja() {
        if (observBaja != null) {
            switch (observBajaComparator) {
	            case STARTS_WITH:
	                return observBaja + "%";
	            case CONTAINS:
	                return "%" + observBaja + "%";
	            case ENDS_WITH:
	                return "%" + observBaja;
	            case EQUALS:
                	return observBaja;
              	default:
	            	break;
            }
        }
        return observBaja;
    }

    /**
     * Valor de busqueda de campo observBaja
     * @param observBaja Valor de seteo.
     */
    public void setObservBaja(String observBaja) {
        this.observBaja = observBaja;
    }

    /**
     * Tipo de comparador para la busqueda por campo observBaja
     * @return observBajaComparator.
     */
    public TextComparator getObservBajaComparator() {
        return observBajaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo observBaja
     * @param observBajaComparator Valor de seteo.
     */
    public void setObservBajaComparator(TextComparator observBajaComparator) {
        this.observBajaComparator = observBajaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getObservBajaIn() {
        return this.observBajaIn;
    }

    /**
     * @param observBaja Valor a agregar.
     */
    public void addObservBajaIn(String observBaja) {
        this.observBajaIn.add(observBaja);
    }

    /**
     * Permite buscar cuando campo observBaja es NULL
     * @return boolean.
     */
    public boolean isObservBajaIsNull() {
        return observBajaIsNull;
    }

    /**
     * Permite buscar cuando campo observBaja es NULL
     * @param observBajaIsNull Valor de seteo.
     */
    public void setObservBajaIsNull(boolean observBajaIsNull) {
        this.observBajaIsNull = observBajaIsNull;
    }

    /**
     * Permite buscar cuando campo observBaja es NOT NULL
     * @return boolean.
     */
    public boolean isObservBajaIsNotNull() {
        return observBajaIsNotNull;
    }

    /**
     * Permite buscar cuando campo observBaja es NOT NULL
     * @param observBajaIsNotNull Valor de seteo.
     */
    public void setObservBajaIsNotNull(boolean observBajaIsNotNull) {
        this.observBajaIsNotNull = observBajaIsNotNull;
    }

    /**
     * Valor de busqueda de campo tipoVia
     * @return String.
     */
    public String getTipoVia() {
        if (tipoVia != null) {
            switch (tipoViaComparator) {
	            case STARTS_WITH:
	                return tipoVia + "%";
	            case CONTAINS:
	                return "%" + tipoVia + "%";
	            case ENDS_WITH:
	                return "%" + tipoVia;
	            case EQUALS:
                	return tipoVia;
              	default:
	            	break;
            }
        }
        return tipoVia;
    }

    /**
     * Valor de busqueda de campo tipoVia
     * @param tipoVia Valor de seteo.
     */
    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipoVia
     * @return tipoViaComparator.
     */
    public TextComparator getTipoViaComparator() {
        return tipoViaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo tipoVia
     * @param tipoViaComparator Valor de seteo.
     */
    public void setTipoViaComparator(TextComparator tipoViaComparator) {
        this.tipoViaComparator = tipoViaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getTipoViaIn() {
        return this.tipoViaIn;
    }

    /**
     * @param tipoVia Valor a agregar.
     */
    public void addTipoViaIn(String tipoVia) {
        this.tipoViaIn.add(tipoVia);
    }

    /**
     * Permite buscar cuando campo tipoVia es NULL
     * @return boolean.
     */
    public boolean isTipoViaIsNull() {
        return tipoViaIsNull;
    }

    /**
     * Permite buscar cuando campo tipoVia es NULL
     * @param tipoViaIsNull Valor de seteo.
     */
    public void setTipoViaIsNull(boolean tipoViaIsNull) {
        this.tipoViaIsNull = tipoViaIsNull;
    }

    /**
     * Permite buscar cuando campo tipoVia es NOT NULL
     * @return boolean.
     */
    public boolean isTipoViaIsNotNull() {
        return tipoViaIsNotNull;
    }

    /**
     * Permite buscar cuando campo tipoVia es NOT NULL
     * @param tipoViaIsNotNull Valor de seteo.
     */
    public void setTipoViaIsNotNull(boolean tipoViaIsNotNull) {
        this.tipoViaIsNotNull = tipoViaIsNotNull;
    }

    /**
     * Valor de busqueda de campo nombreVia
     * @return String.
     */
    public String getNombreVia() {
        if (nombreVia != null) {
            switch (nombreViaComparator) {
	            case STARTS_WITH:
	                return nombreVia + "%";
	            case CONTAINS:
	                return "%" + nombreVia + "%";
	            case ENDS_WITH:
	                return "%" + nombreVia;
	            case EQUALS:
                	return nombreVia;
              	default:
	            	break;
            }
        }
        return nombreVia;
    }

    /**
     * Valor de busqueda de campo nombreVia
     * @param nombreVia Valor de seteo.
     */
    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreVia
     * @return nombreViaComparator.
     */
    public TextComparator getNombreViaComparator() {
        return nombreViaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo nombreVia
     * @param nombreViaComparator Valor de seteo.
     */
    public void setNombreViaComparator(TextComparator nombreViaComparator) {
        this.nombreViaComparator = nombreViaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNombreViaIn() {
        return this.nombreViaIn;
    }

    /**
     * @param nombreVia Valor a agregar.
     */
    public void addNombreViaIn(String nombreVia) {
        this.nombreViaIn.add(nombreVia);
    }

    /**
     * Permite buscar cuando campo nombreVia es NULL
     * @return boolean.
     */
    public boolean isNombreViaIsNull() {
        return nombreViaIsNull;
    }

    /**
     * Permite buscar cuando campo nombreVia es NULL
     * @param nombreViaIsNull Valor de seteo.
     */
    public void setNombreViaIsNull(boolean nombreViaIsNull) {
        this.nombreViaIsNull = nombreViaIsNull;
    }

    /**
     * Permite buscar cuando campo nombreVia es NOT NULL
     * @return boolean.
     */
    public boolean isNombreViaIsNotNull() {
        return nombreViaIsNotNull;
    }

    /**
     * Permite buscar cuando campo nombreVia es NOT NULL
     * @param nombreViaIsNotNull Valor de seteo.
     */
    public void setNombreViaIsNotNull(boolean nombreViaIsNotNull) {
        this.nombreViaIsNotNull = nombreViaIsNotNull;
    }

    /**
     * Valor de busqueda de campo numVia
     * @return String.
     */
    public String getNumVia() {
        if (numVia != null) {
            switch (numViaComparator) {
	            case STARTS_WITH:
	                return numVia + "%";
	            case CONTAINS:
	                return "%" + numVia + "%";
	            case ENDS_WITH:
	                return "%" + numVia;
	            case EQUALS:
                	return numVia;
              	default:
	            	break;
            }
        }
        return numVia;
    }

    /**
     * Valor de busqueda de campo numVia
     * @param numVia Valor de seteo.
     */
    public void setNumVia(String numVia) {
        this.numVia = numVia;
    }

    /**
     * Tipo de comparador para la busqueda por campo numVia
     * @return numViaComparator.
     */
    public TextComparator getNumViaComparator() {
        return numViaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo numVia
     * @param numViaComparator Valor de seteo.
     */
    public void setNumViaComparator(TextComparator numViaComparator) {
        this.numViaComparator = numViaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getNumViaIn() {
        return this.numViaIn;
    }

    /**
     * @param numVia Valor a agregar.
     */
    public void addNumViaIn(String numVia) {
        this.numViaIn.add(numVia);
    }

    /**
     * Permite buscar cuando campo numVia es NULL
     * @return boolean.
     */
    public boolean isNumViaIsNull() {
        return numViaIsNull;
    }

    /**
     * Permite buscar cuando campo numVia es NULL
     * @param numViaIsNull Valor de seteo.
     */
    public void setNumViaIsNull(boolean numViaIsNull) {
        this.numViaIsNull = numViaIsNull;
    }

    /**
     * Permite buscar cuando campo numVia es NOT NULL
     * @return boolean.
     */
    public boolean isNumViaIsNotNull() {
        return numViaIsNotNull;
    }

    /**
     * Permite buscar cuando campo numVia es NOT NULL
     * @param numViaIsNotNull Valor de seteo.
     */
    public void setNumViaIsNotNull(boolean numViaIsNotNull) {
        this.numViaIsNotNull = numViaIsNotNull;
    }

    /**
     * Valor de busqueda de campo complemento
     * @return String.
     */
    public String getComplemento() {
        if (complemento != null) {
            switch (complementoComparator) {
	            case STARTS_WITH:
	                return complemento + "%";
	            case CONTAINS:
	                return "%" + complemento + "%";
	            case ENDS_WITH:
	                return "%" + complemento;
	            case EQUALS:
                	return complemento;
              	default:
	            	break;
            }
        }
        return complemento;
    }

    /**
     * Valor de busqueda de campo complemento
     * @param complemento Valor de seteo.
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * Tipo de comparador para la busqueda por campo complemento
     * @return complementoComparator.
     */
    public TextComparator getComplementoComparator() {
        return complementoComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo complemento
     * @param complementoComparator Valor de seteo.
     */
    public void setComplementoComparator(TextComparator complementoComparator) {
        this.complementoComparator = complementoComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getComplementoIn() {
        return this.complementoIn;
    }

    /**
     * @param complemento Valor a agregar.
     */
    public void addComplementoIn(String complemento) {
        this.complementoIn.add(complemento);
    }

    /**
     * Permite buscar cuando campo complemento es NULL
     * @return boolean.
     */
    public boolean isComplementoIsNull() {
        return complementoIsNull;
    }

    /**
     * Permite buscar cuando campo complemento es NULL
     * @param complementoIsNull Valor de seteo.
     */
    public void setComplementoIsNull(boolean complementoIsNull) {
        this.complementoIsNull = complementoIsNull;
    }

    /**
     * Permite buscar cuando campo complemento es NOT NULL
     * @return boolean.
     */
    public boolean isComplementoIsNotNull() {
        return complementoIsNotNull;
    }

    /**
     * Permite buscar cuando campo complemento es NOT NULL
     * @param complementoIsNotNull Valor de seteo.
     */
    public void setComplementoIsNotNull(boolean complementoIsNotNull) {
        this.complementoIsNotNull = complementoIsNotNull;
    }

    /**
     * Valor de busqueda de campo codPostal
     * @return String.
     */
    public String getCodPostal() {
        if (codPostal != null) {
            switch (codPostalComparator) {
	            case STARTS_WITH:
	                return codPostal + "%";
	            case CONTAINS:
	                return "%" + codPostal + "%";
	            case ENDS_WITH:
	                return "%" + codPostal;
	            case EQUALS:
                	return codPostal;
              	default:
	            	break;
            }
        }
        return codPostal;
    }

    /**
     * Valor de busqueda de campo codPostal
     * @param codPostal Valor de seteo.
     */
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    /**
     * Tipo de comparador para la busqueda por campo codPostal
     * @return codPostalComparator.
     */
    public TextComparator getCodPostalComparator() {
        return codPostalComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codPostal
     * @param codPostalComparator Valor de seteo.
     */
    public void setCodPostalComparator(TextComparator codPostalComparator) {
        this.codPostalComparator = codPostalComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodPostalIn() {
        return this.codPostalIn;
    }

    /**
     * @param codPostal Valor a agregar.
     */
    public void addCodPostalIn(String codPostal) {
        this.codPostalIn.add(codPostal);
    }

    /**
     * Permite buscar cuando campo codPostal es NULL
     * @return boolean.
     */
    public boolean isCodPostalIsNull() {
        return codPostalIsNull;
    }

    /**
     * Permite buscar cuando campo codPostal es NULL
     * @param codPostalIsNull Valor de seteo.
     */
    public void setCodPostalIsNull(boolean codPostalIsNull) {
        this.codPostalIsNull = codPostalIsNull;
    }

    /**
     * Permite buscar cuando campo codPostal es NOT NULL
     * @return boolean.
     */
    public boolean isCodPostalIsNotNull() {
        return codPostalIsNotNull;
    }

    /**
     * Permite buscar cuando campo codPostal es NOT NULL
     * @param codPostalIsNotNull Valor de seteo.
     */
    public void setCodPostalIsNotNull(boolean codPostalIsNotNull) {
        this.codPostalIsNotNull = codPostalIsNotNull;
    }

    /**
     * Valor de busqueda de campo codPais
     * @return String.
     */
    public String getCodPais() {
        if (codPais != null) {
            switch (codPaisComparator) {
	            case STARTS_WITH:
	                return codPais + "%";
	            case CONTAINS:
	                return "%" + codPais + "%";
	            case ENDS_WITH:
	                return "%" + codPais;
	            case EQUALS:
                	return codPais;
              	default:
	            	break;
            }
        }
        return codPais;
    }

    /**
     * Valor de busqueda de campo codPais
     * @param codPais Valor de seteo.
     */
    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    /**
     * Tipo de comparador para la busqueda por campo codPais
     * @return codPaisComparator.
     */
    public TextComparator getCodPaisComparator() {
        return codPaisComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codPais
     * @param codPaisComparator Valor de seteo.
     */
    public void setCodPaisComparator(TextComparator codPaisComparator) {
        this.codPaisComparator = codPaisComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodPaisIn() {
        return this.codPaisIn;
    }

    /**
     * @param codPais Valor a agregar.
     */
    public void addCodPaisIn(String codPais) {
        this.codPaisIn.add(codPais);
    }

    /**
     * Permite buscar cuando campo codPais es NULL
     * @return boolean.
     */
    public boolean isCodPaisIsNull() {
        return codPaisIsNull;
    }

    /**
     * Permite buscar cuando campo codPais es NULL
     * @param codPaisIsNull Valor de seteo.
     */
    public void setCodPaisIsNull(boolean codPaisIsNull) {
        this.codPaisIsNull = codPaisIsNull;
    }

    /**
     * Permite buscar cuando campo codPais es NOT NULL
     * @return boolean.
     */
    public boolean isCodPaisIsNotNull() {
        return codPaisIsNotNull;
    }

    /**
     * Permite buscar cuando campo codPais es NOT NULL
     * @param codPaisIsNotNull Valor de seteo.
     */
    public void setCodPaisIsNotNull(boolean codPaisIsNotNull) {
        this.codPaisIsNotNull = codPaisIsNotNull;
    }

    /**
     * Valor de busqueda de campo codComunidad
     * @return String.
     */
    public String getCodComunidad() {
        if (codComunidad != null) {
            switch (codComunidadComparator) {
	            case STARTS_WITH:
	                return codComunidad + "%";
	            case CONTAINS:
	                return "%" + codComunidad + "%";
	            case ENDS_WITH:
	                return "%" + codComunidad;
	            case EQUALS:
                	return codComunidad;
              	default:
	            	break;
            }
        }
        return codComunidad;
    }

    /**
     * Valor de busqueda de campo codComunidad
     * @param codComunidad Valor de seteo.
     */
    public void setCodComunidad(String codComunidad) {
        this.codComunidad = codComunidad;
    }

    /**
     * Tipo de comparador para la busqueda por campo codComunidad
     * @return codComunidadComparator.
     */
    public TextComparator getCodComunidadComparator() {
        return codComunidadComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codComunidad
     * @param codComunidadComparator Valor de seteo.
     */
    public void setCodComunidadComparator(TextComparator codComunidadComparator) {
        this.codComunidadComparator = codComunidadComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodComunidadIn() {
        return this.codComunidadIn;
    }

    /**
     * @param codComunidad Valor a agregar.
     */
    public void addCodComunidadIn(String codComunidad) {
        this.codComunidadIn.add(codComunidad);
    }

    /**
     * Permite buscar cuando campo codComunidad es NULL
     * @return boolean.
     */
    public boolean isCodComunidadIsNull() {
        return codComunidadIsNull;
    }

    /**
     * Permite buscar cuando campo codComunidad es NULL
     * @param codComunidadIsNull Valor de seteo.
     */
    public void setCodComunidadIsNull(boolean codComunidadIsNull) {
        this.codComunidadIsNull = codComunidadIsNull;
    }

    /**
     * Permite buscar cuando campo codComunidad es NOT NULL
     * @return boolean.
     */
    public boolean isCodComunidadIsNotNull() {
        return codComunidadIsNotNull;
    }

    /**
     * Permite buscar cuando campo codComunidad es NOT NULL
     * @param codComunidadIsNotNull Valor de seteo.
     */
    public void setCodComunidadIsNotNull(boolean codComunidadIsNotNull) {
        this.codComunidadIsNotNull = codComunidadIsNotNull;
    }

    /**
     * Valor de busqueda de campo codProvincia
     * @return String.
     */
    public String getCodProvincia() {
        if (codProvincia != null) {
            switch (codProvinciaComparator) {
	            case STARTS_WITH:
	                return codProvincia + "%";
	            case CONTAINS:
	                return "%" + codProvincia + "%";
	            case ENDS_WITH:
	                return "%" + codProvincia;
	            case EQUALS:
                	return codProvincia;
              	default:
	            	break;
            }
        }
        return codProvincia;
    }

    /**
     * Valor de busqueda de campo codProvincia
     * @param codProvincia Valor de seteo.
     */
    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    /**
     * Tipo de comparador para la busqueda por campo codProvincia
     * @return codProvinciaComparator.
     */
    public TextComparator getCodProvinciaComparator() {
        return codProvinciaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codProvincia
     * @param codProvinciaComparator Valor de seteo.
     */
    public void setCodProvinciaComparator(TextComparator codProvinciaComparator) {
        this.codProvinciaComparator = codProvinciaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodProvinciaIn() {
        return this.codProvinciaIn;
    }

    /**
     * @param codProvincia Valor a agregar.
     */
    public void addCodProvinciaIn(String codProvincia) {
        this.codProvinciaIn.add(codProvincia);
    }

    /**
     * Permite buscar cuando campo codProvincia es NULL
     * @return boolean.
     */
    public boolean isCodProvinciaIsNull() {
        return codProvinciaIsNull;
    }

    /**
     * Permite buscar cuando campo codProvincia es NULL
     * @param codProvinciaIsNull Valor de seteo.
     */
    public void setCodProvinciaIsNull(boolean codProvinciaIsNull) {
        this.codProvinciaIsNull = codProvinciaIsNull;
    }

    /**
     * Permite buscar cuando campo codProvincia es NOT NULL
     * @return boolean.
     */
    public boolean isCodProvinciaIsNotNull() {
        return codProvinciaIsNotNull;
    }

    /**
     * Permite buscar cuando campo codProvincia es NOT NULL
     * @param codProvinciaIsNotNull Valor de seteo.
     */
    public void setCodProvinciaIsNotNull(boolean codProvinciaIsNotNull) {
        this.codProvinciaIsNotNull = codProvinciaIsNotNull;
    }

    /**
     * Valor de busqueda de campo codLocalidad
     * @return String.
     */
    public String getCodLocalidad() {
        if (codLocalidad != null) {
            switch (codLocalidadComparator) {
	            case STARTS_WITH:
	                return codLocalidad + "%";
	            case CONTAINS:
	                return "%" + codLocalidad + "%";
	            case ENDS_WITH:
	                return "%" + codLocalidad;
	            case EQUALS:
                	return codLocalidad;
              	default:
	            	break;
            }
        }
        return codLocalidad;
    }

    /**
     * Valor de busqueda de campo codLocalidad
     * @param codLocalidad Valor de seteo.
     */
    public void setCodLocalidad(String codLocalidad) {
        this.codLocalidad = codLocalidad;
    }

    /**
     * Tipo de comparador para la busqueda por campo codLocalidad
     * @return codLocalidadComparator.
     */
    public TextComparator getCodLocalidadComparator() {
        return codLocalidadComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codLocalidad
     * @param codLocalidadComparator Valor de seteo.
     */
    public void setCodLocalidadComparator(TextComparator codLocalidadComparator) {
        this.codLocalidadComparator = codLocalidadComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodLocalidadIn() {
        return this.codLocalidadIn;
    }

    /**
     * @param codLocalidad Valor a agregar.
     */
    public void addCodLocalidadIn(String codLocalidad) {
        this.codLocalidadIn.add(codLocalidad);
    }

    /**
     * Permite buscar cuando campo codLocalidad es NULL
     * @return boolean.
     */
    public boolean isCodLocalidadIsNull() {
        return codLocalidadIsNull;
    }

    /**
     * Permite buscar cuando campo codLocalidad es NULL
     * @param codLocalidadIsNull Valor de seteo.
     */
    public void setCodLocalidadIsNull(boolean codLocalidadIsNull) {
        this.codLocalidadIsNull = codLocalidadIsNull;
    }

    /**
     * Permite buscar cuando campo codLocalidad es NOT NULL
     * @return boolean.
     */
    public boolean isCodLocalidadIsNotNull() {
        return codLocalidadIsNotNull;
    }

    /**
     * Permite buscar cuando campo codLocalidad es NOT NULL
     * @param codLocalidadIsNotNull Valor de seteo.
     */
    public void setCodLocalidadIsNotNull(boolean codLocalidadIsNotNull) {
        this.codLocalidadIsNotNull = codLocalidadIsNotNull;
    }

    /**
     * Valor de busqueda de campo codEntGeografica
     * @return String.
     */
    public String getCodEntGeografica() {
        if (codEntGeografica != null) {
            switch (codEntGeograficaComparator) {
	            case STARTS_WITH:
	                return codEntGeografica + "%";
	            case CONTAINS:
	                return "%" + codEntGeografica + "%";
	            case ENDS_WITH:
	                return "%" + codEntGeografica;
	            case EQUALS:
                	return codEntGeografica;
              	default:
	            	break;
            }
        }
        return codEntGeografica;
    }

    /**
     * Valor de busqueda de campo codEntGeografica
     * @param codEntGeografica Valor de seteo.
     */
    public void setCodEntGeografica(String codEntGeografica) {
        this.codEntGeografica = codEntGeografica;
    }

    /**
     * Tipo de comparador para la busqueda por campo codEntGeografica
     * @return codEntGeograficaComparator.
     */
    public TextComparator getCodEntGeograficaComparator() {
        return codEntGeograficaComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo codEntGeografica
     * @param codEntGeograficaComparator Valor de seteo.
     */
    public void setCodEntGeograficaComparator(TextComparator codEntGeograficaComparator) {
        this.codEntGeograficaComparator = codEntGeograficaComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getCodEntGeograficaIn() {
        return this.codEntGeograficaIn;
    }

    /**
     * @param codEntGeografica Valor a agregar.
     */
    public void addCodEntGeograficaIn(String codEntGeografica) {
        this.codEntGeograficaIn.add(codEntGeografica);
    }

    /**
     * Permite buscar cuando campo codEntGeografica es NULL
     * @return boolean.
     */
    public boolean isCodEntGeograficaIsNull() {
        return codEntGeograficaIsNull;
    }

    /**
     * Permite buscar cuando campo codEntGeografica es NULL
     * @param codEntGeograficaIsNull Valor de seteo.
     */
    public void setCodEntGeograficaIsNull(boolean codEntGeograficaIsNull) {
        this.codEntGeograficaIsNull = codEntGeograficaIsNull;
    }

    /**
     * Permite buscar cuando campo codEntGeografica es NOT NULL
     * @return boolean.
     */
    public boolean isCodEntGeograficaIsNotNull() {
        return codEntGeograficaIsNotNull;
    }

    /**
     * Permite buscar cuando campo codEntGeografica es NOT NULL
     * @param codEntGeograficaIsNotNull Valor de seteo.
     */
    public void setCodEntGeograficaIsNotNull(boolean codEntGeograficaIsNotNull) {
        this.codEntGeograficaIsNotNull = codEntGeograficaIsNotNull;
    }

    /**
     * Valor de busqueda de campo dirExtranjera
     * @return String.
     */
    public String getDirExtranjera() {
        if (dirExtranjera != null) {
            switch (dirExtranjeraComparator) {
	            case STARTS_WITH:
	                return dirExtranjera + "%";
	            case CONTAINS:
	                return "%" + dirExtranjera + "%";
	            case ENDS_WITH:
	                return "%" + dirExtranjera;
	            case EQUALS:
                	return dirExtranjera;
              	default:
	            	break;
            }
        }
        return dirExtranjera;
    }

    /**
     * Valor de busqueda de campo dirExtranjera
     * @param dirExtranjera Valor de seteo.
     */
    public void setDirExtranjera(String dirExtranjera) {
        this.dirExtranjera = dirExtranjera;
    }

    /**
     * Tipo de comparador para la busqueda por campo dirExtranjera
     * @return dirExtranjeraComparator.
     */
    public TextComparator getDirExtranjeraComparator() {
        return dirExtranjeraComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo dirExtranjera
     * @param dirExtranjeraComparator Valor de seteo.
     */
    public void setDirExtranjeraComparator(TextComparator dirExtranjeraComparator) {
        this.dirExtranjeraComparator = dirExtranjeraComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getDirExtranjeraIn() {
        return this.dirExtranjeraIn;
    }

    /**
     * @param dirExtranjera Valor a agregar.
     */
    public void addDirExtranjeraIn(String dirExtranjera) {
        this.dirExtranjeraIn.add(dirExtranjera);
    }

    /**
     * Permite buscar cuando campo dirExtranjera es NULL
     * @return boolean.
     */
    public boolean isDirExtranjeraIsNull() {
        return dirExtranjeraIsNull;
    }

    /**
     * Permite buscar cuando campo dirExtranjera es NULL
     * @param dirExtranjeraIsNull Valor de seteo.
     */
    public void setDirExtranjeraIsNull(boolean dirExtranjeraIsNull) {
        this.dirExtranjeraIsNull = dirExtranjeraIsNull;
    }

    /**
     * Permite buscar cuando campo dirExtranjera es NOT NULL
     * @return boolean.
     */
    public boolean isDirExtranjeraIsNotNull() {
        return dirExtranjeraIsNotNull;
    }

    /**
     * Permite buscar cuando campo dirExtranjera es NOT NULL
     * @param dirExtranjeraIsNotNull Valor de seteo.
     */
    public void setDirExtranjeraIsNotNull(boolean dirExtranjeraIsNotNull) {
        this.dirExtranjeraIsNotNull = dirExtranjeraIsNotNull;
    }

    /**
     * Valor de busqueda de campo locExtranjera
     * @return String.
     */
    public String getLocExtranjera() {
        if (locExtranjera != null) {
            switch (locExtranjeraComparator) {
	            case STARTS_WITH:
	                return locExtranjera + "%";
	            case CONTAINS:
	                return "%" + locExtranjera + "%";
	            case ENDS_WITH:
	                return "%" + locExtranjera;
	            case EQUALS:
                	return locExtranjera;
              	default:
	            	break;
            }
        }
        return locExtranjera;
    }

    /**
     * Valor de busqueda de campo locExtranjera
     * @param locExtranjera Valor de seteo.
     */
    public void setLocExtranjera(String locExtranjera) {
        this.locExtranjera = locExtranjera;
    }

    /**
     * Tipo de comparador para la busqueda por campo locExtranjera
     * @return locExtranjeraComparator.
     */
    public TextComparator getLocExtranjeraComparator() {
        return locExtranjeraComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo locExtranjera
     * @param locExtranjeraComparator Valor de seteo.
     */
    public void setLocExtranjeraComparator(TextComparator locExtranjeraComparator) {
        this.locExtranjeraComparator = locExtranjeraComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getLocExtranjeraIn() {
        return this.locExtranjeraIn;
    }

    /**
     * @param locExtranjera Valor a agregar.
     */
    public void addLocExtranjeraIn(String locExtranjera) {
        this.locExtranjeraIn.add(locExtranjera);
    }

    /**
     * Permite buscar cuando campo locExtranjera es NULL
     * @return boolean.
     */
    public boolean isLocExtranjeraIsNull() {
        return locExtranjeraIsNull;
    }

    /**
     * Permite buscar cuando campo locExtranjera es NULL
     * @param locExtranjeraIsNull Valor de seteo.
     */
    public void setLocExtranjeraIsNull(boolean locExtranjeraIsNull) {
        this.locExtranjeraIsNull = locExtranjeraIsNull;
    }

    /**
     * Permite buscar cuando campo locExtranjera es NOT NULL
     * @return boolean.
     */
    public boolean isLocExtranjeraIsNotNull() {
        return locExtranjeraIsNotNull;
    }

    /**
     * Permite buscar cuando campo locExtranjera es NOT NULL
     * @param locExtranjeraIsNotNull Valor de seteo.
     */
    public void setLocExtranjeraIsNotNull(boolean locExtranjeraIsNotNull) {
        this.locExtranjeraIsNotNull = locExtranjeraIsNotNull;
    }

    /**
     * Valor de busqueda de campo observaciones
     * @return String.
     */
    public String getObservaciones() {
        if (observaciones != null) {
            switch (observacionesComparator) {
	            case STARTS_WITH:
	                return observaciones + "%";
	            case CONTAINS:
	                return "%" + observaciones + "%";
	            case ENDS_WITH:
	                return "%" + observaciones;
	            case EQUALS:
                	return observaciones;
              	default:
	            	break;
            }
        }
        return observaciones;
    }

    /**
     * Valor de busqueda de campo observaciones
     * @param observaciones Valor de seteo.
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Tipo de comparador para la busqueda por campo observaciones
     * @return observacionesComparator.
     */
    public TextComparator getObservacionesComparator() {
        return observacionesComparator;
    }

    /**
     * Tipo de comparador para la busqueda por campo observaciones
     * @param observacionesComparator Valor de seteo.
     */
    public void setObservacionesComparator(TextComparator observacionesComparator) {
        this.observacionesComparator = observacionesComparator;
    }

    /**
     * @return List<String>.
     */
    public List<String> getObservacionesIn() {
        return this.observacionesIn;
    }

    /**
     * @param observaciones Valor a agregar.
     */
    public void addObservacionesIn(String observaciones) {
        this.observacionesIn.add(observaciones);
    }

    /**
     * Permite buscar cuando campo observaciones es NULL
     * @return boolean.
     */
    public boolean isObservacionesIsNull() {
        return observacionesIsNull;
    }

    /**
     * Permite buscar cuando campo observaciones es NULL
     * @param observacionesIsNull Valor de seteo.
     */
    public void setObservacionesIsNull(boolean observacionesIsNull) {
        this.observacionesIsNull = observacionesIsNull;
    }

    /**
     * Permite buscar cuando campo observaciones es NOT NULL
     * @return boolean.
     */
    public boolean isObservacionesIsNotNull() {
        return observacionesIsNotNull;
    }

    /**
     * Permite buscar cuando campo observaciones es NOT NULL
     * @param observacionesIsNotNull Valor de seteo.
     */
    public void setObservacionesIsNotNull(boolean observacionesIsNotNull) {
        this.observacionesIsNotNull = observacionesIsNotNull;
    }
    
    /**
     * Valor de busqueda de campo idPdpDiputaciones
     * @return Long.
     */
    public Long getIdPdpDiputaciones() {
        return idPdpDiputaciones;
    }

    /**
     * Valor de busqueda de campo idPdpDiputaciones
     * @param idPdpDiputaciones Valor de seteo.
     */
    public void setIdPdpDiputaciones(Long idPdpDiputaciones) {
        this.idPdpDiputaciones = idPdpDiputaciones;
    }

    /**
     * @return List<Long>.
     */
    public List<Long> getIdPdpDiputacionesIn() {
        return this.idPdpDiputacionesIn;
    }

    /**
     * @param idPdpDiputaciones Valor a agregar.
     */
    public void addIdPdpDiputacionesIn(Long idPdpDiputaciones) {
        this.idPdpDiputacionesIn.add(idPdpDiputaciones);
    }

    /**
     * Permite buscar cuando campo idPdpDiputaciones es NULL
     * @return boolean.
     */
    public boolean isIdPdpDiputacionesIsNull() {
        return idPdpDiputacionesIsNull;
    }

    /**
     * Permite buscar cuando campo idPdpDiputaciones es NULL
     * @param idPdpDiputacionesIsNull Valor de seteo.
     */
    public void setIdPdpDiputacionesIsNull(boolean idPdpDiputacionesIsNull) {
        this.idPdpDiputacionesIsNull = idPdpDiputacionesIsNull;
    }

    /**
     * Permite buscar cuando campo idPdpDiputaciones es NOT NULL
     * @return boolean.
     */
    public boolean isIdPdpDiputacionesIsNotNull() {
        return idPdpDiputacionesIsNotNull;
    }

    /**
     * Permite buscar cuando campo idPdpDiputacionesPdp es NOT NULL
     * @param idPdpDiputacionesIsNotNull Valor de seteo.
     */
    public void setIdPdpDiputacionesPdpIsNotNull(boolean idPdpDiputacionesIsNotNull) {
        this.idPdpDiputacionesIsNotNull = idPdpDiputacionesIsNotNull;
    }


    /**
     * Agrega recursivamente criterios al Criteria de Hibernate para la utilizacion en busquedas
     */ 
    public void addCriteria(Criteria criteria, boolean useOrder) {

        if (getOrganismoid() != null) {
            criteria.add(Restrictions.eq(ORGANISMOID, getOrganismoid()));
        }

        if (getOrganismoidIn().size() > 0) {
            criteria.add(Restrictions.in(ORGANISMOID, getOrganismoidIn()));
        }

        if (getDir3() != null) {
            if (getDir3Comparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DIR3, getDir3()));
            } 
            else if (getDir3Comparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DIR3, getDir3()));
            }
            else if (getDir3Comparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DIR3, getDir3())));
            }
            else {
                criteria.add(Restrictions.like(DIR3, getDir3()));
            }
        }

        if (getDir3In().size() > 0) {
            criteria.add(Restrictions.in(DIR3, getDir3In()));
        }

        if (isDir3IsNull()) {
            criteria.add(Restrictions.isNull(DIR3));
        }

        if (isDir3IsNotNull()) {
            criteria.add(Restrictions.isNotNull(DIR3));
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

        if (getEstado() != null) {
            if (getEstadoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ESTADO, getEstado()));
            } 
            else if (getEstadoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ESTADO, getEstado()));
            }
            else if (getEstadoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ESTADO, getEstado())));
            }
            else {
                criteria.add(Restrictions.like(ESTADO, getEstado()));
            }
        }

        if (getEstadoIn().size() > 0) {
            criteria.add(Restrictions.in(ESTADO, getEstadoIn()));
        }

        if (isEstadoIsNull()) {
            criteria.add(Restrictions.isNull(ESTADO));
        }

        if (isEstadoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESTADO));
        }

        if (getNifcif() != null) {
            if (getNifcifComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NIFCIF, getNifcif()));
            } 
            else if (getNifcifComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NIFCIF, getNifcif()));
            }
            else if (getNifcifComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NIFCIF, getNifcif())));
            }
            else {
                criteria.add(Restrictions.like(NIFCIF, getNifcif()));
            }
        }

        if (getNifcifIn().size() > 0) {
            criteria.add(Restrictions.in(NIFCIF, getNifcifIn()));
        }

        if (isNifcifIsNull()) {
            criteria.add(Restrictions.isNull(NIFCIF));
        }

        if (isNifcifIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NIFCIF));
        }

        if (getSiglas() != null) {
            if (getSiglasComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(SIGLAS, getSiglas()));
            } 
            else if (getSiglasComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(SIGLAS, getSiglas()));
            }
            else if (getSiglasComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(SIGLAS, getSiglas())));
            }
            else {
                criteria.add(Restrictions.like(SIGLAS, getSiglas()));
            }
        }

        if (getSiglasIn().size() > 0) {
            criteria.add(Restrictions.in(SIGLAS, getSiglasIn()));
        }

        if (isSiglasIsNull()) {
            criteria.add(Restrictions.isNull(SIGLAS));
        }

        if (isSiglasIsNotNull()) {
            criteria.add(Restrictions.isNotNull(SIGLAS));
        }

        if (getNivelAdministracion() != null) {
            criteria.add(Restrictions.eq(NIVELADMINISTRACION, getNivelAdministracion()));
        }

        if (getNivelAdministracionIn().size() > 0) {
            criteria.add(Restrictions.in(NIVELADMINISTRACION, getNivelAdministracionIn()));
        }

        if (isNivelAdministracionIsNull()) {
            criteria.add(Restrictions.isNull(NIVELADMINISTRACION));
        }

        if (isNivelAdministracionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NIVELADMINISTRACION));
        }

        if (getNivelJerarquico() != null) {
            criteria.add(Restrictions.eq(NIVELJERARQUICO, getNivelJerarquico()));
        }

        if (getNivelJerarquicoIn().size() > 0) {
            criteria.add(Restrictions.in(NIVELJERARQUICO, getNivelJerarquicoIn()));
        }

        if (isNivelJerarquicoIsNull()) {
            criteria.add(Restrictions.isNull(NIVELJERARQUICO));
        }

        if (isNivelJerarquicoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NIVELJERARQUICO));
        }

        if (getCodUnidadSuperior() != null) {
            if (getCodUnidadSuperiorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODUNIDADSUPERIOR, getCodUnidadSuperior()));
            } 
            else if (getCodUnidadSuperiorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODUNIDADSUPERIOR, getCodUnidadSuperior()));
            }
            else if (getCodUnidadSuperiorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODUNIDADSUPERIOR, getCodUnidadSuperior())));
            }
            else {
                criteria.add(Restrictions.like(CODUNIDADSUPERIOR, getCodUnidadSuperior()));
            }
        }

        if (getCodUnidadSuperiorIn().size() > 0) {
            criteria.add(Restrictions.in(CODUNIDADSUPERIOR, getCodUnidadSuperiorIn()));
        }

        if (isCodUnidadSuperiorIsNull()) {
            criteria.add(Restrictions.isNull(CODUNIDADSUPERIOR));
        }

        if (isCodUnidadSuperiorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODUNIDADSUPERIOR));
        }

        if (getDenomUnidadSuperior() != null) {
            if (getDenomUnidadSuperiorComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DENOMUNIDADSUPERIOR, getDenomUnidadSuperior()));
            } 
            else if (getDenomUnidadSuperiorComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DENOMUNIDADSUPERIOR, getDenomUnidadSuperior()));
            }
            else if (getDenomUnidadSuperiorComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DENOMUNIDADSUPERIOR, getDenomUnidadSuperior())));
            }
            else {
                criteria.add(Restrictions.like(DENOMUNIDADSUPERIOR, getDenomUnidadSuperior()));
            }
        }

        if (getDenomUnidadSuperiorIn().size() > 0) {
            criteria.add(Restrictions.in(DENOMUNIDADSUPERIOR, getDenomUnidadSuperiorIn()));
        }

        if (isDenomUnidadSuperiorIsNull()) {
            criteria.add(Restrictions.isNull(DENOMUNIDADSUPERIOR));
        }

        if (isDenomUnidadSuperiorIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DENOMUNIDADSUPERIOR));
        }

        if (getCodUnidadRaiz() != null) {
            if (getCodUnidadRaizComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODUNIDADRAIZ, getCodUnidadRaiz()));
            } 
            else if (getCodUnidadRaizComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODUNIDADRAIZ, getCodUnidadRaiz()));
            }
            else if (getCodUnidadRaizComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODUNIDADRAIZ, getCodUnidadRaiz())));
            }
            else {
                criteria.add(Restrictions.like(CODUNIDADRAIZ, getCodUnidadRaiz()));
            }
        }

        if (getCodUnidadRaizIn().size() > 0) {
            criteria.add(Restrictions.in(CODUNIDADRAIZ, getCodUnidadRaizIn()));
        }

        if (isCodUnidadRaizIsNull()) {
            criteria.add(Restrictions.isNull(CODUNIDADRAIZ));
        }

        if (isCodUnidadRaizIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODUNIDADRAIZ));
        }

        if (getDenomUnidadRaiz() != null) {
            if (getDenomUnidadRaizComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DENOMUNIDADRAIZ, getDenomUnidadRaiz()));
            } 
            else if (getDenomUnidadRaizComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DENOMUNIDADRAIZ, getDenomUnidadRaiz()));
            }
            else if (getDenomUnidadRaizComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DENOMUNIDADRAIZ, getDenomUnidadRaiz())));
            }
            else {
                criteria.add(Restrictions.like(DENOMUNIDADRAIZ, getDenomUnidadRaiz()));
            }
        }

        if (getDenomUnidadRaizIn().size() > 0) {
            criteria.add(Restrictions.in(DENOMUNIDADRAIZ, getDenomUnidadRaizIn()));
        }

        if (isDenomUnidadRaizIsNull()) {
            criteria.add(Restrictions.isNull(DENOMUNIDADRAIZ));
        }

        if (isDenomUnidadRaizIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DENOMUNIDADRAIZ));
        }

        if (getEsEdp() != null) {
            if (getEsEdpComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(ESEDP, getEsEdp()));
            } 
            else if (getEsEdpComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(ESEDP, getEsEdp()));
            }
            else if (getEsEdpComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(ESEDP, getEsEdp())));
            }
            else {
                criteria.add(Restrictions.like(ESEDP, getEsEdp()));
            }
        }

        if (getEsEdpIn().size() > 0) {
            criteria.add(Restrictions.in(ESEDP, getEsEdpIn()));
        }

        if (isEsEdpIsNull()) {
            criteria.add(Restrictions.isNull(ESEDP));
        }

        if (isEsEdpIsNotNull()) {
            criteria.add(Restrictions.isNotNull(ESEDP));
        }

        if (getCodEdpPrincipal() != null) {
            if (getCodEdpPrincipalComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODEDPPRINCIPAL, getCodEdpPrincipal()));
            } 
            else if (getCodEdpPrincipalComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODEDPPRINCIPAL, getCodEdpPrincipal()));
            }
            else if (getCodEdpPrincipalComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODEDPPRINCIPAL, getCodEdpPrincipal())));
            }
            else {
                criteria.add(Restrictions.like(CODEDPPRINCIPAL, getCodEdpPrincipal()));
            }
        }

        if (getCodEdpPrincipalIn().size() > 0) {
            criteria.add(Restrictions.in(CODEDPPRINCIPAL, getCodEdpPrincipalIn()));
        }

        if (isCodEdpPrincipalIsNull()) {
            criteria.add(Restrictions.isNull(CODEDPPRINCIPAL));
        }

        if (isCodEdpPrincipalIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODEDPPRINCIPAL));
        }

        if (getDenomEdpPrincipal() != null) {
            if (getDenomEdpPrincipalComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DENOMEDPPRINCIPAL, getDenomEdpPrincipal()));
            } 
            else if (getDenomEdpPrincipalComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DENOMEDPPRINCIPAL, getDenomEdpPrincipal()));
            }
            else if (getDenomEdpPrincipalComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DENOMEDPPRINCIPAL, getDenomEdpPrincipal())));
            }
            else {
                criteria.add(Restrictions.like(DENOMEDPPRINCIPAL, getDenomEdpPrincipal()));
            }
        }

        if (getDenomEdpPrincipalIn().size() > 0) {
            criteria.add(Restrictions.in(DENOMEDPPRINCIPAL, getDenomEdpPrincipalIn()));
        }

        if (isDenomEdpPrincipalIsNull()) {
            criteria.add(Restrictions.isNull(DENOMEDPPRINCIPAL));
        }

        if (isDenomEdpPrincipalIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DENOMEDPPRINCIPAL));
        }

        if (getCodTipoEntPublic() != null) {
            if (getCodTipoEntPublicComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODTIPOENTPUBLIC, getCodTipoEntPublic()));
            } 
            else if (getCodTipoEntPublicComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODTIPOENTPUBLIC, getCodTipoEntPublic()));
            }
            else if (getCodTipoEntPublicComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODTIPOENTPUBLIC, getCodTipoEntPublic())));
            }
            else {
                criteria.add(Restrictions.like(CODTIPOENTPUBLIC, getCodTipoEntPublic()));
            }
        }

        if (getCodTipoEntPublicIn().size() > 0) {
            criteria.add(Restrictions.in(CODTIPOENTPUBLIC, getCodTipoEntPublicIn()));
        }

        if (isCodTipoEntPublicIsNull()) {
            criteria.add(Restrictions.isNull(CODTIPOENTPUBLIC));
        }

        if (isCodTipoEntPublicIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODTIPOENTPUBLIC));
        }

        if (getCodTipoUnidad() != null) {
            if (getCodTipoUnidadComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODTIPOUNIDAD, getCodTipoUnidad()));
            } 
            else if (getCodTipoUnidadComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODTIPOUNIDAD, getCodTipoUnidad()));
            }
            else if (getCodTipoUnidadComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODTIPOUNIDAD, getCodTipoUnidad())));
            }
            else {
                criteria.add(Restrictions.like(CODTIPOUNIDAD, getCodTipoUnidad()));
            }
        }

        if (getCodTipoUnidadIn().size() > 0) {
            criteria.add(Restrictions.in(CODTIPOUNIDAD, getCodTipoUnidadIn()));
        }

        if (isCodTipoUnidadIsNull()) {
            criteria.add(Restrictions.isNull(CODTIPOUNIDAD));
        }

        if (isCodTipoUnidadIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODTIPOUNIDAD));
        }

        if (getCodAmbTerritorial() != null) {
            if (getCodAmbTerritorialComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODAMBTERRITORIAL, getCodAmbTerritorial()));
            } 
            else if (getCodAmbTerritorialComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODAMBTERRITORIAL, getCodAmbTerritorial()));
            }
            else if (getCodAmbTerritorialComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODAMBTERRITORIAL, getCodAmbTerritorial())));
            }
            else {
                criteria.add(Restrictions.like(CODAMBTERRITORIAL, getCodAmbTerritorial()));
            }
        }

        if (getCodAmbTerritorialIn().size() > 0) {
            criteria.add(Restrictions.in(CODAMBTERRITORIAL, getCodAmbTerritorialIn()));
        }

        if (isCodAmbTerritorialIsNull()) {
            criteria.add(Restrictions.isNull(CODAMBTERRITORIAL));
        }

        if (isCodAmbTerritorialIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODAMBTERRITORIAL));
        }

        if (getCodAmbEntGeografica() != null) {
            if (getCodAmbEntGeograficaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODAMBENTGEOGRAFICA, getCodAmbEntGeografica()));
            } 
            else if (getCodAmbEntGeograficaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODAMBENTGEOGRAFICA, getCodAmbEntGeografica()));
            }
            else if (getCodAmbEntGeograficaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODAMBENTGEOGRAFICA, getCodAmbEntGeografica())));
            }
            else {
                criteria.add(Restrictions.like(CODAMBENTGEOGRAFICA, getCodAmbEntGeografica()));
            }
        }

        if (getCodAmbEntGeograficaIn().size() > 0) {
            criteria.add(Restrictions.in(CODAMBENTGEOGRAFICA, getCodAmbEntGeograficaIn()));
        }

        if (isCodAmbEntGeograficaIsNull()) {
            criteria.add(Restrictions.isNull(CODAMBENTGEOGRAFICA));
        }

        if (isCodAmbEntGeograficaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODAMBENTGEOGRAFICA));
        }

        if (getCodAmbPais() != null) {
            if (getCodAmbPaisComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODAMBPAIS, getCodAmbPais()));
            } 
            else if (getCodAmbPaisComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODAMBPAIS, getCodAmbPais()));
            }
            else if (getCodAmbPaisComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODAMBPAIS, getCodAmbPais())));
            }
            else {
                criteria.add(Restrictions.like(CODAMBPAIS, getCodAmbPais()));
            }
        }

        if (getCodAmbPaisIn().size() > 0) {
            criteria.add(Restrictions.in(CODAMBPAIS, getCodAmbPaisIn()));
        }

        if (isCodAmbPaisIsNull()) {
            criteria.add(Restrictions.isNull(CODAMBPAIS));
        }

        if (isCodAmbPaisIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODAMBPAIS));
        }

        if (getCodAmbComunidad() != null) {
            if (getCodAmbComunidadComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODAMBCOMUNIDAD, getCodAmbComunidad()));
            } 
            else if (getCodAmbComunidadComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODAMBCOMUNIDAD, getCodAmbComunidad()));
            }
            else if (getCodAmbComunidadComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODAMBCOMUNIDAD, getCodAmbComunidad())));
            }
            else {
                criteria.add(Restrictions.like(CODAMBCOMUNIDAD, getCodAmbComunidad()));
            }
        }

        if (getCodAmbComunidadIn().size() > 0) {
            criteria.add(Restrictions.in(CODAMBCOMUNIDAD, getCodAmbComunidadIn()));
        }

        if (isCodAmbComunidadIsNull()) {
            criteria.add(Restrictions.isNull(CODAMBCOMUNIDAD));
        }

        if (isCodAmbComunidadIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODAMBCOMUNIDAD));
        }

        if (getCodAmbProvincia() != null) {
            if (getCodAmbProvinciaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODAMBPROVINCIA, getCodAmbProvincia()));
            } 
            else if (getCodAmbProvinciaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODAMBPROVINCIA, getCodAmbProvincia()));
            }
            else if (getCodAmbProvinciaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODAMBPROVINCIA, getCodAmbProvincia())));
            }
            else {
                criteria.add(Restrictions.like(CODAMBPROVINCIA, getCodAmbProvincia()));
            }
        }

        if (getCodAmbProvinciaIn().size() > 0) {
            criteria.add(Restrictions.in(CODAMBPROVINCIA, getCodAmbProvinciaIn()));
        }

        if (isCodAmbProvinciaIsNull()) {
            criteria.add(Restrictions.isNull(CODAMBPROVINCIA));
        }

        if (isCodAmbProvinciaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODAMBPROVINCIA));
        }

        if (getCodAmbMunicipio() != null) {
            if (getCodAmbMunicipioComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODAMBMUNICIPIO, getCodAmbMunicipio()));
            } 
            else if (getCodAmbMunicipioComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODAMBMUNICIPIO, getCodAmbMunicipio()));
            }
            else if (getCodAmbMunicipioComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODAMBMUNICIPIO, getCodAmbMunicipio())));
            }
            else {
                criteria.add(Restrictions.like(CODAMBMUNICIPIO, getCodAmbMunicipio()));
            }
        }

        if (getCodAmbMunicipioIn().size() > 0) {
            criteria.add(Restrictions.in(CODAMBMUNICIPIO, getCodAmbMunicipioIn()));
        }

        if (isCodAmbMunicipioIsNull()) {
            criteria.add(Restrictions.isNull(CODAMBMUNICIPIO));
        }

        if (isCodAmbMunicipioIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODAMBMUNICIPIO));
        }

        if (getCodAmbIsla() != null) {
            if (getCodAmbIslaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODAMBISLA, getCodAmbIsla()));
            } 
            else if (getCodAmbIslaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODAMBISLA, getCodAmbIsla()));
            }
            else if (getCodAmbIslaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODAMBISLA, getCodAmbIsla())));
            }
            else {
                criteria.add(Restrictions.like(CODAMBISLA, getCodAmbIsla()));
            }
        }

        if (getCodAmbIslaIn().size() > 0) {
            criteria.add(Restrictions.in(CODAMBISLA, getCodAmbIslaIn()));
        }

        if (isCodAmbIslaIsNull()) {
            criteria.add(Restrictions.isNull(CODAMBISLA));
        }

        if (isCodAmbIslaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODAMBISLA));
        }

        if (getCodAmbElm() != null) {
            if (getCodAmbElmComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODAMBELM, getCodAmbElm()));
            } 
            else if (getCodAmbElmComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODAMBELM, getCodAmbElm()));
            }
            else if (getCodAmbElmComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODAMBELM, getCodAmbElm())));
            }
            else {
                criteria.add(Restrictions.like(CODAMBELM, getCodAmbElm()));
            }
        }

        if (getCodAmbElmIn().size() > 0) {
            criteria.add(Restrictions.in(CODAMBELM, getCodAmbElmIn()));
        }

        if (isCodAmbElmIsNull()) {
            criteria.add(Restrictions.isNull(CODAMBELM));
        }

        if (isCodAmbElmIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODAMBELM));
        }

        if (getCodAmbLocExtranjera() != null) {
            if (getCodAmbLocExtranjeraComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODAMBLOCEXTRANJERA, getCodAmbLocExtranjera()));
            } 
            else if (getCodAmbLocExtranjeraComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODAMBLOCEXTRANJERA, getCodAmbLocExtranjera()));
            }
            else if (getCodAmbLocExtranjeraComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODAMBLOCEXTRANJERA, getCodAmbLocExtranjera())));
            }
            else {
                criteria.add(Restrictions.like(CODAMBLOCEXTRANJERA, getCodAmbLocExtranjera()));
            }
        }

        if (getCodAmbLocExtranjeraIn().size() > 0) {
            criteria.add(Restrictions.in(CODAMBLOCEXTRANJERA, getCodAmbLocExtranjeraIn()));
        }

        if (isCodAmbLocExtranjeraIsNull()) {
            criteria.add(Restrictions.isNull(CODAMBLOCEXTRANJERA));
        }

        if (isCodAmbLocExtranjeraIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODAMBLOCEXTRANJERA));
        }

        if (getCompetencias() != null) {
            if (getCompetenciasComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(COMPETENCIAS, getCompetencias()));
            } 
            else if (getCompetenciasComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(COMPETENCIAS, getCompetencias()));
            }
            else if (getCompetenciasComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(COMPETENCIAS, getCompetencias())));
            }
            else {
                criteria.add(Restrictions.like(COMPETENCIAS, getCompetencias()));
            }
        }

        if (getCompetenciasIn().size() > 0) {
            criteria.add(Restrictions.in(COMPETENCIAS, getCompetenciasIn()));
        }

        if (isCompetenciasIsNull()) {
            criteria.add(Restrictions.isNull(COMPETENCIAS));
        }

        if (isCompetenciasIsNotNull()) {
            criteria.add(Restrictions.isNotNull(COMPETENCIAS));
        }

        if (getDisposicionLegal() != null) {
            if (getDisposicionLegalComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DISPOSICIONLEGAL, getDisposicionLegal()));
            } 
            else if (getDisposicionLegalComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DISPOSICIONLEGAL, getDisposicionLegal()));
            }
            else if (getDisposicionLegalComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DISPOSICIONLEGAL, getDisposicionLegal())));
            }
            else {
                criteria.add(Restrictions.like(DISPOSICIONLEGAL, getDisposicionLegal()));
            }
        }

        if (getDisposicionLegalIn().size() > 0) {
            criteria.add(Restrictions.in(DISPOSICIONLEGAL, getDisposicionLegalIn()));
        }

        if (isDisposicionLegalIsNull()) {
            criteria.add(Restrictions.isNull(DISPOSICIONLEGAL));
        }

        if (isDisposicionLegalIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DISPOSICIONLEGAL));
        }

        if (getFechaAltaOficialMin() != null) {
            criteria.add(Restrictions.ge(FECHAALTAOFICIAL, getFechaAltaOficialMin()));
        }

        if (getFechaAltaOficialMax() != null) {
            criteria.add(Restrictions.le(FECHAALTAOFICIAL, getFechaAltaOficialMax()));
        }

        if (isFechaAltaOficialIsNull()) {
            criteria.add(Restrictions.isNull(FECHAALTAOFICIAL));
        }

        if (isFechaAltaOficialIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAALTAOFICIAL));
        }

        if (getFechaBajaOficialMin() != null) {
            criteria.add(Restrictions.ge(FECHABAJAOFICIAL, getFechaBajaOficialMin()));
        }

        if (getFechaBajaOficialMax() != null) {
            criteria.add(Restrictions.le(FECHABAJAOFICIAL, getFechaBajaOficialMax()));
        }

        if (isFechaBajaOficialIsNull()) {
            criteria.add(Restrictions.isNull(FECHABAJAOFICIAL));
        }

        if (isFechaBajaOficialIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHABAJAOFICIAL));
        }

        if (getFechaExtincionMin() != null) {
            criteria.add(Restrictions.ge(FECHAEXTINCION, getFechaExtincionMin()));
        }

        if (getFechaExtincionMax() != null) {
            criteria.add(Restrictions.le(FECHAEXTINCION, getFechaExtincionMax()));
        }

        if (isFechaExtincionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAEXTINCION));
        }

        if (isFechaExtincionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAEXTINCION));
        }

        if (getFechaAnulacionMin() != null) {
            criteria.add(Restrictions.ge(FECHAANULACION, getFechaAnulacionMin()));
        }

        if (getFechaAnulacionMax() != null) {
            criteria.add(Restrictions.le(FECHAANULACION, getFechaAnulacionMax()));
        }

        if (isFechaAnulacionIsNull()) {
            criteria.add(Restrictions.isNull(FECHAANULACION));
        }

        if (isFechaAnulacionIsNotNull()) {
            criteria.add(Restrictions.isNotNull(FECHAANULACION));
        }

        if (getObservGenerales() != null) {
            if (getObservGeneralesComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(OBSERVGENERALES, getObservGenerales()));
            } 
            else if (getObservGeneralesComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(OBSERVGENERALES, getObservGenerales()));
            }
            else if (getObservGeneralesComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(OBSERVGENERALES, getObservGenerales())));
            }
            else {
                criteria.add(Restrictions.like(OBSERVGENERALES, getObservGenerales()));
            }
        }

        if (getObservGeneralesIn().size() > 0) {
            criteria.add(Restrictions.in(OBSERVGENERALES, getObservGeneralesIn()));
        }

        if (isObservGeneralesIsNull()) {
            criteria.add(Restrictions.isNull(OBSERVGENERALES));
        }

        if (isObservGeneralesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(OBSERVGENERALES));
        }

        if (getObservBaja() != null) {
            if (getObservBajaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(OBSERVBAJA, getObservBaja()));
            } 
            else if (getObservBajaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(OBSERVBAJA, getObservBaja()));
            }
            else if (getObservBajaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(OBSERVBAJA, getObservBaja())));
            }
            else {
                criteria.add(Restrictions.like(OBSERVBAJA, getObservBaja()));
            }
        }

        if (getObservBajaIn().size() > 0) {
            criteria.add(Restrictions.in(OBSERVBAJA, getObservBajaIn()));
        }

        if (isObservBajaIsNull()) {
            criteria.add(Restrictions.isNull(OBSERVBAJA));
        }

        if (isObservBajaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(OBSERVBAJA));
        }

        if (getTipoVia() != null) {
            if (getTipoViaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(TIPOVIA, getTipoVia()));
            } 
            else if (getTipoViaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(TIPOVIA, getTipoVia()));
            }
            else if (getTipoViaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(TIPOVIA, getTipoVia())));
            }
            else {
                criteria.add(Restrictions.like(TIPOVIA, getTipoVia()));
            }
        }

        if (getTipoViaIn().size() > 0) {
            criteria.add(Restrictions.in(TIPOVIA, getTipoViaIn()));
        }

        if (isTipoViaIsNull()) {
            criteria.add(Restrictions.isNull(TIPOVIA));
        }

        if (isTipoViaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(TIPOVIA));
        }

        if (getNombreVia() != null) {
            if (getNombreViaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NOMBREVIA, getNombreVia()));
            } 
            else if (getNombreViaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NOMBREVIA, getNombreVia()));
            }
            else if (getNombreViaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NOMBREVIA, getNombreVia())));
            }
            else {
                criteria.add(Restrictions.like(NOMBREVIA, getNombreVia()));
            }
        }

        if (getNombreViaIn().size() > 0) {
            criteria.add(Restrictions.in(NOMBREVIA, getNombreViaIn()));
        }

        if (isNombreViaIsNull()) {
            criteria.add(Restrictions.isNull(NOMBREVIA));
        }

        if (isNombreViaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NOMBREVIA));
        }

        if (getNumVia() != null) {
            if (getNumViaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(NUMVIA, getNumVia()));
            } 
            else if (getNumViaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(NUMVIA, getNumVia()));
            }
            else if (getNumViaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(NUMVIA, getNumVia())));
            }
            else {
                criteria.add(Restrictions.like(NUMVIA, getNumVia()));
            }
        }

        if (getNumViaIn().size() > 0) {
            criteria.add(Restrictions.in(NUMVIA, getNumViaIn()));
        }

        if (isNumViaIsNull()) {
            criteria.add(Restrictions.isNull(NUMVIA));
        }

        if (isNumViaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(NUMVIA));
        }

        if (getComplemento() != null) {
            if (getComplementoComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(COMPLEMENTO, getComplemento()));
            } 
            else if (getComplementoComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(COMPLEMENTO, getComplemento()));
            }
            else if (getComplementoComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(COMPLEMENTO, getComplemento())));
            }
            else {
                criteria.add(Restrictions.like(COMPLEMENTO, getComplemento()));
            }
        }

        if (getComplementoIn().size() > 0) {
            criteria.add(Restrictions.in(COMPLEMENTO, getComplementoIn()));
        }

        if (isComplementoIsNull()) {
            criteria.add(Restrictions.isNull(COMPLEMENTO));
        }

        if (isComplementoIsNotNull()) {
            criteria.add(Restrictions.isNotNull(COMPLEMENTO));
        }

        if (getCodPostal() != null) {
            if (getCodPostalComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODPOSTAL, getCodPostal()));
            } 
            else if (getCodPostalComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODPOSTAL, getCodPostal()));
            }
            else if (getCodPostalComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODPOSTAL, getCodPostal())));
            }
            else {
                criteria.add(Restrictions.like(CODPOSTAL, getCodPostal()));
            }
        }

        if (getCodPostalIn().size() > 0) {
            criteria.add(Restrictions.in(CODPOSTAL, getCodPostalIn()));
        }

        if (isCodPostalIsNull()) {
            criteria.add(Restrictions.isNull(CODPOSTAL));
        }

        if (isCodPostalIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODPOSTAL));
        }

        if (getCodPais() != null) {
            if (getCodPaisComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODPAIS, getCodPais()));
            } 
            else if (getCodPaisComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODPAIS, getCodPais()));
            }
            else if (getCodPaisComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODPAIS, getCodPais())));
            }
            else {
                criteria.add(Restrictions.like(CODPAIS, getCodPais()));
            }
        }

        if (getCodPaisIn().size() > 0) {
            criteria.add(Restrictions.in(CODPAIS, getCodPaisIn()));
        }

        if (isCodPaisIsNull()) {
            criteria.add(Restrictions.isNull(CODPAIS));
        }

        if (isCodPaisIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODPAIS));
        }

        if (getCodComunidad() != null) {
            if (getCodComunidadComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODCOMUNIDAD, getCodComunidad()));
            } 
            else if (getCodComunidadComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODCOMUNIDAD, getCodComunidad()));
            }
            else if (getCodComunidadComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODCOMUNIDAD, getCodComunidad())));
            }
            else {
                criteria.add(Restrictions.like(CODCOMUNIDAD, getCodComunidad()));
            }
        }

        if (getCodComunidadIn().size() > 0) {
            criteria.add(Restrictions.in(CODCOMUNIDAD, getCodComunidadIn()));
        }

        if (isCodComunidadIsNull()) {
            criteria.add(Restrictions.isNull(CODCOMUNIDAD));
        }

        if (isCodComunidadIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODCOMUNIDAD));
        }

        if (getCodProvincia() != null) {
            if (getCodProvinciaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODPROVINCIA, getCodProvincia()));
            } 
            else if (getCodProvinciaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODPROVINCIA, getCodProvincia()));
            }
            else if (getCodProvinciaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODPROVINCIA, getCodProvincia())));
            }
            else {
                criteria.add(Restrictions.like(CODPROVINCIA, getCodProvincia()));
            }
        }

        if (getCodProvinciaIn().size() > 0) {
            criteria.add(Restrictions.in(CODPROVINCIA, getCodProvinciaIn()));
        }

        if (isCodProvinciaIsNull()) {
            criteria.add(Restrictions.isNull(CODPROVINCIA));
        }

        if (isCodProvinciaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODPROVINCIA));
        }

        if (getCodLocalidad() != null) {
            if (getCodLocalidadComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODLOCALIDAD, getCodLocalidad()));
            } 
            else if (getCodLocalidadComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODLOCALIDAD, getCodLocalidad()));
            }
            else if (getCodLocalidadComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODLOCALIDAD, getCodLocalidad())));
            }
            else {
                criteria.add(Restrictions.like(CODLOCALIDAD, getCodLocalidad()));
            }
        }

        if (getCodLocalidadIn().size() > 0) {
            criteria.add(Restrictions.in(CODLOCALIDAD, getCodLocalidadIn()));
        }

        if (isCodLocalidadIsNull()) {
            criteria.add(Restrictions.isNull(CODLOCALIDAD));
        }

        if (isCodLocalidadIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODLOCALIDAD));
        }

        if (getCodEntGeografica() != null) {
            if (getCodEntGeograficaComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(CODENTGEOGRAFICA, getCodEntGeografica()));
            } 
            else if (getCodEntGeograficaComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(CODENTGEOGRAFICA, getCodEntGeografica()));
            }
            else if (getCodEntGeograficaComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(CODENTGEOGRAFICA, getCodEntGeografica())));
            }
            else {
                criteria.add(Restrictions.like(CODENTGEOGRAFICA, getCodEntGeografica()));
            }
        }

        if (getCodEntGeograficaIn().size() > 0) {
            criteria.add(Restrictions.in(CODENTGEOGRAFICA, getCodEntGeograficaIn()));
        }

        if (isCodEntGeograficaIsNull()) {
            criteria.add(Restrictions.isNull(CODENTGEOGRAFICA));
        }

        if (isCodEntGeograficaIsNotNull()) {
            criteria.add(Restrictions.isNotNull(CODENTGEOGRAFICA));
        }

        if (getDirExtranjera() != null) {
            if (getDirExtranjeraComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(DIREXTRANJERA, getDirExtranjera()));
            } 
            else if (getDirExtranjeraComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(DIREXTRANJERA, getDirExtranjera()));
            }
            else if (getDirExtranjeraComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(DIREXTRANJERA, getDirExtranjera())));
            }
            else {
                criteria.add(Restrictions.like(DIREXTRANJERA, getDirExtranjera()));
            }
        }

        if (getDirExtranjeraIn().size() > 0) {
            criteria.add(Restrictions.in(DIREXTRANJERA, getDirExtranjeraIn()));
        }

        if (isDirExtranjeraIsNull()) {
            criteria.add(Restrictions.isNull(DIREXTRANJERA));
        }

        if (isDirExtranjeraIsNotNull()) {
            criteria.add(Restrictions.isNotNull(DIREXTRANJERA));
        }

        if (getLocExtranjera() != null) {
            if (getLocExtranjeraComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(LOCEXTRANJERA, getLocExtranjera()));
            } 
            else if (getLocExtranjeraComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(LOCEXTRANJERA, getLocExtranjera()));
            }
            else if (getLocExtranjeraComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(LOCEXTRANJERA, getLocExtranjera())));
            }
            else {
                criteria.add(Restrictions.like(LOCEXTRANJERA, getLocExtranjera()));
            }
        }

        if (getLocExtranjeraIn().size() > 0) {
            criteria.add(Restrictions.in(LOCEXTRANJERA, getLocExtranjeraIn()));
        }

        if (isLocExtranjeraIsNull()) {
            criteria.add(Restrictions.isNull(LOCEXTRANJERA));
        }

        if (isLocExtranjeraIsNotNull()) {
            criteria.add(Restrictions.isNotNull(LOCEXTRANJERA));
        }

        if (getObservaciones() != null) {
            if (getObservacionesComparator() == TextComparator.EQUALS) {
                criteria.add(Restrictions.eq(OBSERVACIONES, getObservaciones()));
            } 
            else if (getObservacionesComparator() == TextComparator.ILIKE) {
                criteria.add(Restrictions.ilike(OBSERVACIONES, getObservaciones()));
            }
            else if (getObservacionesComparator() == TextComparator.UPPERCASE_TRANSLATE) {
                criteria.add(Restrictions.sqlRestriction(createUpperTranslateSQL(OBSERVACIONES, getObservaciones())));
            }
            else {
                criteria.add(Restrictions.like(OBSERVACIONES, getObservaciones()));
            }
        }

        if (getObservacionesIn().size() > 0) {
            criteria.add(Restrictions.in(OBSERVACIONES, getObservacionesIn()));
        }

        if (isObservacionesIsNull()) {
            criteria.add(Restrictions.isNull(OBSERVACIONES));
        }

        if (isObservacionesIsNotNull()) {
            criteria.add(Restrictions.isNotNull(OBSERVACIONES));
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
     * Normaliza el valdor de un parametro eliminado los acentos
     */
    private String normalizeParam(String param){
    	return Normalizer.normalize(param, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
 
