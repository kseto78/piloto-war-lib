package es.mpr.plataformamensajeria.model.views;

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
 * <p>
 * Clase de entidad con las anotaciones JPA necesarias.
 * 
 * <p>
 * 
 * 
 * @author i-nercya
 */
@Entity
@Table(name = "VIEW_CONSOLIDACION_HIST")
@NamedQueries({ @NamedQuery(name = "ViewConsolidacionHistoricoJPA", query = "SELECT e FROM ViewConsolidacionHistoricoJPA e where e.servicioIdLEH=:servicioId and e.loteenvioIdMH = :loteenvioId") })
public class ViewConsolidacionHistoricoJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewConsolidacionHistoricoJPA() {
		this.mensajeIdDH = null;
		this.adjuntoIdAH = null;
		this.destinatarioIdDH = null;
		this.historicoIdHH = null;
		this.mensajeIdDHH = null;
		this.aplicacionGEH = null;
		this.canalGEH = null;
		this.servicioGEH = null;
		this.estadoGEH = null;
		this.destinatarioGEH = null;
		this.aplicacionIdGEH = null;
		this.servicioIdGEH = null;
		this.canalIdGEH = null;
		this.loteEnvioIdGEH = null;
		this.nombreGEH = null;
		this.mensajeIdGEH = null;
		this.ultimoEnvioGEH = null;
		this.estadoIdGEH = null;
		this.servidorIdGEH = null;
		this.codigoExternoGEH = null;
		this.anioGEH = null;
		this.mesGEH = null;
		this.fechaHistorificacionGEH = null;
		this.docUsuarioGEH = null;
		this.codSiaGEH = null;
		this.codOrganismoGEH = null;
		this.codOrganismoPagadorGEH = null;
		this.mensajeAdjuntoIdMAH = null;
		this.mensajeIdMAH = null;
		this.adjuntoIdMAH = null;
		this.fechaHistorificacionMAH = null;
		this.servicioIdLEH = null;
		this.loteenvioIdMH = null;

	}

	
	@Column(name = "MENSAJEIDDH")
	private Integer mensajeIdDH;

	@Column(name = "ADJUNTOIDAH")
	private Integer adjuntoIdAH;

	@Column(name = "DESTINATARIOIDDH")
	private Integer destinatarioIdDH;

	@Id
	@Column(name = "HISTORICOIDHH")
	private Integer historicoIdHH;

	@Column(name = "MENSAJEIDHH")
	private Integer mensajeIdDHH;

	@Column(name = "APLICACIONGEH")
	private String aplicacionGEH;

	@Column(name = "CANALGEH")
	private String canalGEH;

	@Column(name = "SERVICIOGEH")
	private String servicioGEH;

	@Column(name = "ESTADOGEH")
	private String estadoGEH;

	@Column(name = "DESTINATARIOGEH")
	private String destinatarioGEH;

	@Column(name = "APLICACIONIDGEH")
	private Integer aplicacionIdGEH;

	@Column(name = "SERVICIOIDGEH")
	private Integer servicioIdGEH;

	@Column(name = "CANALIDGEH")
	private Integer canalIdGEH;

	@Column(name = "LOTEENVIOIDGEH")
	private Integer loteEnvioIdGEH;

	@Column(name = "NOMBREGEH")
	private String nombreGEH;

	@Column(name = "MENSAJEIDGEH")
	private Integer mensajeIdGEH;

	@Column(name = "ULTIMOENVIOGEH")
	private Date ultimoEnvioGEH;

	@Column(name = "ESTADOIDGEH")
	private Integer estadoIdGEH;

	@Column(name = "SERVIDORIDGEH")
	private Integer servidorIdGEH;

	@Column(name = "CODIGOEXTERNOGEH")
	private String codigoExternoGEH;

	@Column(name = "ANIOGEH")
	protected Integer anioGEH;

	@Column(name = "MESGEH")
	protected Integer mesGEH;

	@Column(name = "FECHAHISTORIFICACIONGEH")
	protected Date fechaHistorificacionGEH;

	@Column(name = "DOCUSUARIOGEH")
	private String docUsuarioGEH;

	@Column(name = "CODSIAGEH")
	private String codSiaGEH;

	@Column(name = "CODORGANISMOGEH")
	private String codOrganismoGEH;

	@Column(name = "CODORGANISMOPAGADORGEH")
	private String codOrganismoPagadorGEH;

	@Column(name = "MENSAJEADJUNTOIDMAH")
	private Integer mensajeAdjuntoIdMAH;

	@Column(name = "MENSAJEIDMAH")
	private Integer mensajeIdMAH;

	@Column(name = "ADJUNTOIDMAH")
	private Integer adjuntoIdMAH;

	@Column(name = "FECHAHISTORIFICACIONMAH")
	private Date fechaHistorificacionMAH;

	@Column(name = "SERVICIOIDLEH")
	private Integer servicioIdLEH;

	@Column(name = "LOTEENVIOIDMH")
	private Integer loteenvioIdMH;

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.mensajeIdDH;
	}

	public Integer getMensajeIdDH() {
		return mensajeIdDH;
	}

	public void setMensajeIdDH(Integer mensajeIdDH) {
		this.mensajeIdDH = mensajeIdDH;
	}

	public Integer getAdjuntoIdAH() {
		return adjuntoIdAH;
	}

	public void setAdjuntoIdAH(Integer adjuntoIdAH) {
		this.adjuntoIdAH = adjuntoIdAH;
	}

	public Integer getDestinatarioIdDH() {
		return destinatarioIdDH;
	}

	public void setDestinatarioIdDH(Integer destinatarioIdDH) {
		this.destinatarioIdDH = destinatarioIdDH;
	}

	public Integer getHistoricoIdHH() {
		return historicoIdHH;
	}

	public void setHistoricoIdHH(Integer historicoIdHH) {
		this.historicoIdHH = historicoIdHH;
	}

	public Integer getMensajeIdDHH() {
		return mensajeIdDHH;
	}

	public void setMensajeIdDHH(Integer mensajeIdDHH) {
		this.mensajeIdDHH = mensajeIdDHH;
	}

	public String getAplicacionGEH() {
		return aplicacionGEH;
	}

	public void setAplicacionGEH(String aplicacionGEH) {
		this.aplicacionGEH = aplicacionGEH;
	}

	public String getCanalGEH() {
		return canalGEH;
	}

	public void setCanalGEH(String canalGEH) {
		this.canalGEH = canalGEH;
	}

	public String getServicioGEH() {
		return servicioGEH;
	}

	public void setServicioGEH(String servicioGEH) {
		this.servicioGEH = servicioGEH;
	}

	public String getEstadoGEH() {
		return estadoGEH;
	}

	public void setEstadoGEH(String estadoGEH) {
		this.estadoGEH = estadoGEH;
	}

	public String getDestinatarioGEH() {
		return destinatarioGEH;
	}

	public void setDestinatarioGEH(String destinatarioGEH) {
		this.destinatarioGEH = destinatarioGEH;
	}

	public Integer getAplicacionIdGEH() {
		return aplicacionIdGEH;
	}

	public void setAplicacionIdGEH(Integer aplicacionIdGEH) {
		this.aplicacionIdGEH = aplicacionIdGEH;
	}

	public Integer getServicioIdGEH() {
		return servicioIdGEH;
	}

	public void setServicioIdGEH(Integer servicioIdGEH) {
		this.servicioIdGEH = servicioIdGEH;
	}

	public Integer getCanalIdGEH() {
		return canalIdGEH;
	}

	public void setCanalIdGEH(Integer canalIdGEH) {
		this.canalIdGEH = canalIdGEH;
	}

	public Integer getLoteEnvioIdGEH() {
		return loteEnvioIdGEH;
	}

	public void setLoteEnvioIdGEH(Integer loteEnvioIdGEH) {
		this.loteEnvioIdGEH = loteEnvioIdGEH;
	}

	public String getNombreGEH() {
		return nombreGEH;
	}

	public void setNombreGEH(String nombreGEH) {
		this.nombreGEH = nombreGEH;
	}

	public Integer getMensajeIdGEH() {
		return mensajeIdGEH;
	}

	public void setMensajeIdGEH(Integer mensajeIdGEH) {
		this.mensajeIdGEH = mensajeIdGEH;
	}

	public Date getUltimoEnvioGEH() {
		return ultimoEnvioGEH;
	}

	public void setUltimoEnvioGEH(Date ultimoEnvioGEH) {
		this.ultimoEnvioGEH = ultimoEnvioGEH;
	}

	public Integer getEstadoIdGEH() {
		return estadoIdGEH;
	}

	public void setEstadoIdGEH(Integer estadoIdGEH) {
		this.estadoIdGEH = estadoIdGEH;
	}

	public Integer getServidorIdGEH() {
		return servidorIdGEH;
	}

	public void setServidorIdGEH(Integer servidorIdGEH) {
		this.servidorIdGEH = servidorIdGEH;
	}

	public String getCodigoExternoGEH() {
		return codigoExternoGEH;
	}

	public void setCodigoExternoGEH(String codigoExternoGEH) {
		this.codigoExternoGEH = codigoExternoGEH;
	}

	public Integer getAnioGEH() {
		return anioGEH;
	}

	public void setAnioGEH(Integer anioGEH) {
		this.anioGEH = anioGEH;
	}

	public Integer getMesGEH() {
		return mesGEH;
	}

	public void setMesGEH(Integer mesGEH) {
		this.mesGEH = mesGEH;
	}

	public Date getFechaHistorificacionGEH() {
		return fechaHistorificacionGEH;
	}

	public void setFechaHistorificacionGEH(Date fechaHistorificacionGEH) {
		this.fechaHistorificacionGEH = fechaHistorificacionGEH;
	}

	public String getDocUsuarioGEH() {
		return docUsuarioGEH;
	}

	public void setDocUsuarioGEH(String docUsuarioGEH) {
		this.docUsuarioGEH = docUsuarioGEH;
	}

	public String getCodSiaGEH() {
		return codSiaGEH;
	}

	public void setCodSiaGEH(String codSiaGEH) {
		this.codSiaGEH = codSiaGEH;
	}

	public String getCodOrganismoGEH() {
		return codOrganismoGEH;
	}

	public void setCodOrganismoGEH(String codOrganismoGEH) {
		this.codOrganismoGEH = codOrganismoGEH;
	}

	public String getCodOrganismoPagadorGEH() {
		return codOrganismoPagadorGEH;
	}

	public void setCodOrganismoPagadorGEH(String codOrganismoPagadorGEH) {
		this.codOrganismoPagadorGEH = codOrganismoPagadorGEH;
	}

	public Integer getMensajeAdjuntoIdMAH() {
		return mensajeAdjuntoIdMAH;
	}

	public void setMensajeAdjuntoIdMAH(Integer mensajeAdjuntoIdMAH) {
		this.mensajeAdjuntoIdMAH = mensajeAdjuntoIdMAH;
	}

	public Integer getMensajeIdMAH() {
		return mensajeIdMAH;
	}

	public void setMensajeIdMAH(Integer mensajeIdMAH) {
		this.mensajeIdMAH = mensajeIdMAH;
	}

	public Integer getAdjuntoIdMAH() {
		return adjuntoIdMAH;
	}

	public void setAdjuntoIdMAH(Integer adjuntoIdMAH) {
		this.adjuntoIdMAH = adjuntoIdMAH;
	}

	public Date getFechaHistorificacionMAH() {
		return fechaHistorificacionMAH;
	}

	public void setFechaHistorificacionMAH(Date fechaHistorificacionMAH) {
		this.fechaHistorificacionMAH = fechaHistorificacionMAH;
	}

	public Integer getServicioIdLEH() {
		return servicioIdLEH;
	}

	public void setServicioIdLEH(Integer servicioIdLEH) {
		this.servicioIdLEH = servicioIdLEH;
	}

	public Integer getLoteenvioIdMH() {
		return loteenvioIdMH;
	}

	public void setLoteenvioIdMH(Integer loteenvioIdMH) {
		this.loteenvioIdMH = loteenvioIdMH;
	}

}
