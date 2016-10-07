package es.mpr.plataformamensajeria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "TBL_ESTADISTICAS_CONS")
@NamedQueries({         
	@NamedQuery(name = "selectAllEstadisticasConsolidadasJPA", query = "SELECT e FROM EstadisticasConsolidadasJPA e")})

public class EstadisticasConsolidadasJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstadisticasConsolidadasJPA() {
		this.estadisticasConsId = null;
		this.servidorId = null;
		this.servidorNombre = null;
		this.aplicacionId = null;
		this.planificacionId = null;
		this.aplicacionNombre = null;
		this.servicioId = null;
		this.servicioNombre = null;
		this.canalId = null;
		this.canalNombre = null;
		this.estadoId = null;
		this.estadoNombre = null;
		this.anno = null;
		this.mes = null;
		this.numTotal = null;
		this.docUsuario=null;
		this.codSIA=null;
		this.codOrganismo=null;
		this.codOrganismoPagador=null;

	}

	@Id
	@SequenceGenerator(name="estadisticaConsS", sequenceName="ESTADISTICASCONS_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="estadisticaConsS")
	protected Integer estadisticasConsId;
	
	@Column (name="SERVIDORID")
	protected Integer servidorId;
	
	@Column(name = "SERVIDORNOMBRE")
	protected String servidorNombre;
	
	@Column (name="APLICACIONID")
	protected Integer aplicacionId;
	
	@Column (name="PLANIFICACIONID")
	protected Integer planificacionId;
	
	@Column(name = "APLICACIONNOMBRE")
	protected String aplicacionNombre;
	
	@Column (name="SERVICIOID")
	protected Integer servicioId;
	
	@Column(name = "SERVICIONOMBRE")
	protected String servicioNombre;
	
	@Column (name="CANALID")
	protected Integer canalId;
	
	@Column(name = "CANALNOMBRE")
	protected String canalNombre;
	
	@Column (name="ESTADOID")
	protected Integer estadoId;
	
	@Column(name = "ESTADONOMBRE")
	protected String estadoNombre;
	
	@Column (name="ANNO")
	protected Integer anno;
	
	@Column(name = "MES")
	protected String mes;
	
	@Column (name="NUMTOTAL")
	protected Integer numTotal;
	
	@Column(name="DOCUSUARIO")
	private String docUsuario;
	
	@Column(name="CODSIA")
	private String codSIA;
	
	@Column(name="CODORGANISMO")
	private String codOrganismo;
	
	@Column(name="CODORGANISMOPAGADOR")
	private String codOrganismoPagador;
	
	@Override
	public Object getId() {
		return this.estadisticasConsId;
	}
	
	public void setId(Object id){
		this.estadisticasConsId =(Integer)id;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public String getServidorNombre() {
		return servidorNombre;
	}

	public void setServidorNombre(String servidorNombre) {
		this.servidorNombre = servidorNombre;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public Integer getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
	}

	public String getAplicacionNombre() {
		return aplicacionNombre;
	}

	public void setAplicacionNombre(String aplicacionNombre) {
		this.aplicacionNombre = aplicacionNombre;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public String getServicioNombre() {
		return servicioNombre;
	}

	public void setServicioNombre(String servicioNombre) {
		this.servicioNombre = servicioNombre;
	}

	public Integer getCanalId() {
		return canalId;
	}

	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}

	public String getCanalNombre() {
		return canalNombre;
	}

	public void setCanalNombre(String canalNombre) {
		this.canalNombre = canalNombre;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
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
	
}
