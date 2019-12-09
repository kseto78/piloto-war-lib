package es.mpr.plataformamensajeria.beans;

import java.io.File;

import com.map.j2ee.auditoria.ifaces.Audit;


	
/**
 * Clase EnvioMensajesAplicacionBean.
 */
public class EnvioMensajesAplicacionBean implements Audit{

	/**  aplicacion id. */
	private Integer aplicacionId;

	/**  canal id. */
	private String canalId;

	/**  servicio id. */
	private String servicioId;

	/**  nombre lote. */
	private String nombreLote;

	/**  organismo. */
	private String organismo;

	/**  organismo pagador. */
	private String organismoPagador;

	/**  mensaje. */
	private String mensaje;

	/**  asunto. */
	private String asunto;

	/**  adjunto. */
	private File adjunto;

	/**  titulo. */
	private String titulo;

	/**  cuerpo. */
	private String cuerpo;

	/**  id externo. */
	private String idExterno;

	/**  movil. */
	private String movil;

	/**  to. */
	private String to;

	/**  cc. */
	private String cc;

	/**  cco. */
	private String cco;

	/**  id usuario. */
	private String idUsuario;

	/**  url acuse AEAT. */
	private String urlAcuseAEAT;

	/**  passbook. */
	private String passbook;

	/**  key princ. */
	private String keyPrinc;

	/**  label princ. */
	private String labelPrinc;

	/**  value princ. */
	private String valuePrinc;

	/**  key sec. */
	private String keySec;

	/**  label sec. */
	private String labelSec;

	/**  value sec. */
	private String valueSec;

	/**  key aux. */
	private String keyAux;

	/**  label aux. */
	private String labelAux;

	/**  value aux. */
	private String valueAux;

	/**  key tras. */
	private String keyTras;

	/**  label tras. */
	private String labelTras;

	/**  value tras. */
	private String valueTras;

	/**  url. */
	private String url;

	/**  logo passbook. */
	private String logoPassbook;

	/**  descripcion passbook. */
	private String descripcionPassbook;

	/**  nombre adjunto */
	private String nombreAdjunto;


	/**
	 * Constructor de envio mensajes aplicacion bean.
	 */
	public EnvioMensajesAplicacionBean() {
			this.aplicacionId = null;
			this.canalId = null;
			this.servicioId = null;
			this.nombreLote = null;
			this.organismo = null;
			this.organismoPagador = null;
			this.mensaje = null;
			this.asunto = null;
			this.titulo = null;
			this.cuerpo = null;
			this.idExterno = null;		
			this.movil = null;
			this.to = null;
			this.cc = null;
			this.cco = null;
			this.idUsuario = null;
			this.urlAcuseAEAT = null;
			
			this.passbook = null;
			
			this.keyPrinc = null;
			this.labelPrinc = null;
			this.valuePrinc = null;
			this.keySec = null;
			this.labelSec = null;
			this.valueSec = null;
			this.keyAux = null;
			this.labelAux = null;
			this.valueAux = null;
			this.keyTras = null;
			this.labelTras = null;
			this.valueTras = null;		
			this.url = null;
			this.logoPassbook = null;
			this.descripcionPassbook = null;
			
			this.nombreAdjunto = null;
		}
		
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Obtener aplicacion id.
	 *
	 * @return aplicacion id
	 */
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	
	/**
	 * Modificar aplicacion id.
	 *
	 * @param aplicacionId new aplicacion id
	 */
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	
	/**
	 * Obtener canal id.
	 *
	 * @return canal id
	 */
	public String getCanalId() {
		return canalId;
	}
	
	/**
	 * Modificar canal id.
	 *
	 * @param canalId new canal id
	 */
	public void setCanalId(String canalId) {
		this.canalId = canalId;
	}
	
	/**
	 * Obtener servicio id.
	 *
	 * @return servicio id
	 */
	public String getServicioId() {
		return servicioId;
	}
	
	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId new servicio id
	 */
	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}
	
	/**
	 * Obtener nombre lote.
	 *
	 * @return nombre lote
	 */
	public String getNombreLote() {
		return nombreLote;
	}
	
	/**
	 * Modificar nombre lote.
	 *
	 * @param nombreLote new nombre lote
	 */
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}
/**
 * Obtener organismo.
 *
 * @return organismo
 */
	public String getOrganismo() {
		return organismo;
	}
	
	/**
	 * Modificar organismo.
	 *
	 * @param organismo new organismo
	 */
	public void setOrganismo(String organismo) {
		this.organismo = organismo;
	}
	
	/**
	 * Obtener organismo pagador.
	 *
	 * @return organismo pagador
	 */
	public String getOrganismoPagador() {
		return organismoPagador;
	}
	
	/**
	 * Modificar organismo pagador.
	 *
	 * @param organismoPagador new organismo pagador
	 */
	public void setOrganismoPagador(String organismoPagador) {
		this.organismoPagador = organismoPagador;
	}
	
	/**
	 * Obtener mensaje.
	 *
	 * @return mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	
	/**
	 * Modificar mensaje.
	 *
	 * @param mensaje new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	/**
	 * Obtener asunto.
	 *
	 * @return asunto
	 */
	public String getAsunto() {
		return asunto;
	}
	
	/**
	 * Modificar asunto.
	 *
	 * @param asunto new asunto
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
/**
 * Obtener adjunto.
 *
 * @return adjunto
 */
	public File getAdjunto() {
		return adjunto;
	}

	/**
	 * Modificar adjunto.
	 *
	 * @param adjunto new adjunto
	 */
	public void setAdjunto(File adjunto) {
		this.adjunto = adjunto;
	}

	/**
	 * Obtener titulo.
	 *
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Modificar titulo.
	 *
	 * @param titulo new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * Obtener cuerpo.
	 *
	 * @return cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}

	/**
	 * Modificar cuerpo.
	 *
	 * @param cuerpo new cuerpo
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	/**
	 * Obtener id externo.
	 *
	 * @return id externo
	 */
	public String getIdExterno() {
		return idExterno;
	}
	
	/**
	 * Modificar id externo.
	 *
	 * @param idExterno new id externo
	 */
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}
	
	/**
	 * Obtener movil.
	 *
	 * @return movil
	 */
	public String getMovil() {
		return movil;
	}
	
	/**
	 * Modificar movil.
	 *
	 * @param movil new movil
	 */
	public void setMovil(String movil) {
		this.movil = movil;
	}
	
	/**
	 * Obtener to.
	 *
	 * @return to
	 */
	public String getTo() {
		return to;
	}
	
	/**
	 * Modificar to.
	 *
	 * @param to new to
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * Obtener cc.
	 *
	 * @return cc
	 */
	public String getCc() {
		return cc;
	}
	
	/**
	 * Modificar cc.
	 *
	 * @param cc new cc
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	/**
	 * Obtener cco.
	 *
	 * @return cco
	 */
	public String getCco() {
		return cco;
	}
	
	/**
	 * Modificar cco.
	 *
	 * @param cco new cco
	 */
	public void setCco(String cco) {
		this.cco = cco;
	}
	
	/**
	 * Obtener id usuario.
	 *
	 * @return id usuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Modificar id usuario.
	 *
	 * @param idUsuario new id usuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Obtener url acuse AEAT.
	 *
	 * @return url acuse AEAT
	 */
	public String getUrlAcuseAEAT() {
		return urlAcuseAEAT;
	}

	/**
	 * Modificar url acuse AEAT.
	 *
	 * @param urlAcuseAEAT new url acuse AEAT
	 */
	public void setUrlAcuseAEAT(String urlAcuseAEAT) {
		this.urlAcuseAEAT = urlAcuseAEAT;
	}

	/**
	 * Obtener passbook.
	 *
	 * @return passbook
	 */
	public String getPassbook() {
		return passbook;
	}

	/**
	 * Modificar passbook.
	 *
	 * @param passbook new passbook
	 */
	public void setPassbook(String passbook) {
		this.passbook = passbook;
	}

	/**
	 * Obtener key princ.
	 *
	 * @return key princ
	 */
	public String getKeyPrinc() {
		return keyPrinc;
	}

	/**
	 * Modificar key princ.
	 *
	 * @param keyPrinc new key princ
	 */
	public void setKeyPrinc(String keyPrinc) {
		this.keyPrinc = keyPrinc;
	}

	/**
	 * Obtener label princ.
	 *
	 * @return label princ
	 */
	public String getLabelPrinc() {
		return labelPrinc;
	}

	/**
	 * Modificar label princ.
	 *
	 * @param labelPrinc new label princ
	 */
	public void setLabelPrinc(String labelPrinc) {
		this.labelPrinc = labelPrinc;
	}

	/**
	 * Obtener value princ.
	 *
	 * @return value princ
	 */
	public String getValuePrinc() {
		return valuePrinc;
	}

	/**
	 * Modificar value princ.
	 *
	 * @param valuePrinc new value princ
	 */
	public void setValuePrinc(String valuePrinc) {
		this.valuePrinc = valuePrinc;
	}

	/**
	 * Obtener key sec.
	 *
	 * @return key sec
	 */
	public String getKeySec() {
		return keySec;
	}

	/**
	 * Modificar key sec.
	 *
	 * @param keySec new key sec
	 */
	public void setKeySec(String keySec) {
		this.keySec = keySec;
	}

	/**
	 * Obtener label sec.
	 *
	 * @return label sec
	 */
	public String getLabelSec() {
		return labelSec;
	}

	/**
	 * Modificar label sec.
	 *
	 * @param labelSec new label sec
	 */
	public void setLabelSec(String labelSec) {
		this.labelSec = labelSec;
	}

	/**
	 * Obtener value sec.
	 *
	 * @return value sec
	 */
	public String getValueSec() {
		return valueSec;
	}

	/**
	 * Modificar value sec.
	 *
	 * @param valueSec new value sec
	 */
	public void setValueSec(String valueSec) {
		this.valueSec = valueSec;
	}

	/**
	 * Obtener key aux.
	 *
	 * @return key aux
	 */
	public String getKeyAux() {
		return keyAux;
	}

	/**
	 * Modificar key aux.
	 *
	 * @param keyAux new key aux
	 */
	public void setKeyAux(String keyAux) {
		this.keyAux = keyAux;
	}

	/**
	 * Obtener label aux.
	 *
	 * @return label aux
	 */
	public String getLabelAux() {
		return labelAux;
	}

	/**
	 * Modificar label aux.
	 *
	 * @param labelAux new label aux
	 */
	public void setLabelAux(String labelAux) {
		this.labelAux = labelAux;
	}

	/**
	 * Obtener value aux.
	 *
	 * @return value aux
	 */
	public String getValueAux() {
		return valueAux;
	}

	/**
	 * Modificar value aux.
	 *
	 * @param valueAux new value aux
	 */
	public void setValueAux(String valueAux) {
		this.valueAux = valueAux;
	}

	/**
	 * Obtener key tras.
	 *
	 * @return key tras
	 */
	public String getKeyTras() {
		return keyTras;
	}

	/**
	 * Modificar key tras.
	 *
	 * @param keyTras new key tras
	 */
	public void setKeyTras(String keyTras) {
		this.keyTras = keyTras;
	}

	/**
	 * Obtener label tras.
	 *
	 * @return label tras
	 */
	public String getLabelTras() {
		return labelTras;
	}

	/**
	 * Modificar label tras.
	 *
	 * @param labelTras new label tras
	 */
	public void setLabelTras(String labelTras) {
		this.labelTras = labelTras;
	}

	/**
	 * Obtener value tras.
	 *
	 * @return value tras
	 */
	public String getValueTras() {
		return valueTras;
	}

	/**
	 * Modificar value tras.
	 *
	 * @param valueTras new value tras
	 */
	public void setValueTras(String valueTras) {
		this.valueTras = valueTras;
	}

	/**
	 * Obtener url.
	 *
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Modificar url.
	 *
	 * @param url new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Obtener logo passbook.
	 *
	 * @return logo passbook
	 */
	public String getLogoPassbook() {
		return logoPassbook;
	}

	/**
	 * Modificar logo passbook.
	 *
	 * @param logoPassbook new logo passbook
	 */
	public void setLogoPassbook(String logoPassbook) {
		this.logoPassbook = logoPassbook;
	}

	/**
	 * Obtener descripcion passbook.
	 *
	 * @return descripcion passbook
	 */
	public String getDescripcionPassbook() {
		return descripcionPassbook;
	}

	/**
	 * Modificar descripcion passbook.
	 *
	 * @param descripcionPassbook new descripcion passbook
	 */
	public void setDescripcionPassbook(String descripcionPassbook) {
		this.descripcionPassbook = descripcionPassbook;
	}

	public String getNombreAdjunto() {
		return nombreAdjunto;
	}

	public void setNombreAdjunto(String nombreAdjunto) {
		this.nombreAdjunto = nombreAdjunto;
	}


	

	}
