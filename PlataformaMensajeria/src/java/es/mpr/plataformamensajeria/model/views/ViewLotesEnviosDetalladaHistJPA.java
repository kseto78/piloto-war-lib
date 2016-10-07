package es.mpr.plataformamensajeria.model.views;

import java.io.Serializable;
import java.util.Date;

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
 *  Representa la tabla aplicaciones de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "VIEW_LOTESENVIOS_DET_HIST")

public class ViewLotesEnviosDetalladaHistJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewLotesEnviosDetalladaHistJPA() {
		super();
		this.loteEnvioId = null;
		this.nombreLote = null;
		this.descripcionLote = null;
		this.servicioId = null;
		this.estadoEnvioId = null;
		this.nombreServicio = null;
		this.aplicacionId = null;
		this.nombreAplicacion = null;
		this.canalId = null;
		
	}

	
	@Id
	@SequenceGenerator(name="loteEnvio", sequenceName="LOTEENVIOID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="loteEnvio")
	protected Integer loteEnvioId;

	
	@Column(name = "NOMBRE")
	protected String nombreLote = null;
	
	@Column(name = "DESCRIPCION")
	protected String descripcionLote = null;
	
	@Column(name = "SERVICIOID")
	protected Integer servicioId= null;
	
	@Column(name = "ESTADOENVIOID")
	protected Integer estadoEnvioId = null;
	
	@Column(name = "SERVICIO")
	protected String nombreServicio = null;
	
	@Column(name = "APLICACIONID")
	protected Integer aplicacionId = null;
	
	@Column(name = "APLICACION")
	protected String nombreAplicacion = null;
	
	@Column(name = "CANALID")
	protected Integer canalId = null;

	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}

	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
	}

	public String getNombreLote() {
		return nombreLote;
	}

	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}

	public String getDescripcionLote() {
		return descripcionLote;
	}

	public void setDescripcionLote(String descripcionLote) {
		this.descripcionLote = descripcionLote;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public Integer getEstadoEnvioId() {
		return estadoEnvioId;
	}

	public void setEstadoEnvioId(Integer estadoEnvioId) {
		this.estadoEnvioId = estadoEnvioId;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	public Integer getCanalId() {
		return canalId;
	}

	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}

	@Override
	public Object getId() {
		return this.loteEnvioId;
	}
	
}
