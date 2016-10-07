package es.mpr.plataformamensajeria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

/*
 * Diferentes implementaciones de la JPA pueden requerir diferencias en las NamedQuerys.
 * Por ejemplo la siguiente Namedquery debe ser expresada diferente si nuestra implementaci�n es openjpa o hibernate:
 * Con Open JPA -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like :nombre")
 * Con Hibernate -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like ?")
 */

/**
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "TBL_GESTIONENVIOS")
@NamedQueries({         
	@NamedQuery(name = "selectAllGestionEnviosJPA", query = "SELECT e FROM GestionEnviosJPA e")})

public class GestionEnviosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GestionEnviosJPA() {
		this.aplicacion=null;
		this.canal=null;
		this.servicio=null;
		this.estado=null;
		this.destinatario=null;
		this.aplicacionId=null;
		this.servicioId=null;
		this.canalId=null;
		this.loteEnvioId=null;
		this.nombre=null;
		this.mensajeId=null;
		this.ultimoEnvio=null;
		this.estadoId=null;
		this.servidorId=null;
		this.codigoExterno=null;
		this.anio=null;
		this.mes=null;
		this.docUsuario=null;
		this.codSIA=null;
		this.codOrganismo=null;
		this.codOrganismoPagador=null;
		this.nombreUsuario=null;
		this.icono=null;
		this.sonido=null;
		this.estadoLote = null;
		
	}
	
	@Column(name="APLICACION")
	protected String aplicacion;
	
	@Column(name="CANAL")
	protected String canal;
	
	@Column(name="SERVICIO")
	protected String servicio;
	
	@Column(name="ESTADO")
	protected String estado;
	
	@Column(name="DESTINATARIO")
	protected String destinatario;
	
	@Column(name="APLICACIONID")
	protected Integer aplicacionId;
	
	@Column(name="SERVICIOID")
	protected Integer servicioId;
	
	@Column(name="CANALID")
	protected Integer canalId;
	
	@Column(name="LOTEENVIOID")
	protected Integer loteEnvioId;
	
	@Column(name="NOMBRE")
	protected String nombre;

	@Id
	@Column(name="MENSAJEID")
	protected Integer mensajeId;
	
	@Column(name="ULTIMOENVIO")
	protected Date ultimoEnvio;
	
	@Column(name="ESTADOID")
	protected Integer estadoId;
	
	@Column(name="SERVIDORID")
	protected Integer servidorId;
	
	@Column(name="CODIGOEXTERNO")
	protected String codigoExterno;
	
	@Column(name="ANIO")
	protected Integer anio;
	
	@Column(name="MES")
	protected Integer mes;
	
	@Column(name="DOCUSUARIO")
	private String docUsuario;
	
	@Column(name="CODSIA")
	private String codSIA;
	
	@Column(name="CODORGANISMO")
	private String codOrganismo;
	
	@Column(name="CODORGANISMOPAGADOR")
	private String codOrganismoPagador;
	
	@Column(name="NOMBREUSUARIO")
	private String nombreUsuario;
	
	@Column(name="ICONO")
	private String icono;
	
	@Column(name="SONIDO")
	private String sonido;
	
	@Column(name="ESTADOLOTE")
	private String estadoLote;
	
	@Override
	public Object getId() {
		return this.mensajeId;
	}
	
	public void setId(Object id){
		this.mensajeId = (Integer)id;
	}


	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public Integer getCanalId() {
		return canalId;
	}

	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}

	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}

	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
	}

	public Integer getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}

	public Date getUltimoEnvio() {
		return ultimoEnvio;
	}

	public void setUltimoEnvio(Date ultimoEnvio) {
		this.ultimoEnvio = ultimoEnvio;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getDocUsuario() {
		return docUsuario;
	}

	public void setDocUsuario(String docUsuario) {
		this.docUsuario = docUsuario;
	}

	public String getCodSIA() {
		return codSIA;
	}

	public void setCodSIA(String codSIA) {
		this.codSIA = codSIA;
	}

	public String getCodOrganismo() {
		return codOrganismo;
	}

	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	public String getCodOrganismoPagador() {
		return codOrganismoPagador;
	}

	public void setCodOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getSonido() {
		return sonido;
	}

	public void setSonido(String sonido) {
		this.sonido = sonido;
	}

	public String getEstadoLote() {
		return estadoLote;
	}

	public void setEstadoLote(String estadoLote) {
		this.estadoLote = estadoLote;
	}
	
	
}
