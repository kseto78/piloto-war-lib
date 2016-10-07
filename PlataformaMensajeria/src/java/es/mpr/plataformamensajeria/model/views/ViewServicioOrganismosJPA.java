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

@Entity
@Table(name = "VIEW_SERVICIO_ORGANISMOS")
@NamedQueries({ @NamedQuery(name = "ViewSelectOrganismosServicioJPA", query = "SELECT e FROM ViewServicioOrganismosJPA e WHERE to_char(e.organismoId) = ? "),
		@NamedQuery(name = "ViewSelectServicioOrganismosJPA", query = "SELECT e FROM ViewServicioOrganismosJPA e WHERE to_char(e.servicioId) = ? ")})
public class ViewServicioOrganismosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewServicioOrganismosJPA() {
		super();
		this.servicioOrganismoId = null;
		this.organismoId = null;
		this.servicioId = null;
		this.nombreServicio = null;
		this.nombreOrganismo = null;
		this.DIR3Organismo = null;
		this.descripcionOrganismo = null;
		this.descripcionServicio = null;
		this.creadoPor = null;
		this.fechaCreacion = null;
		this.modificadoPor = null;
		this.fechaModificacion = null;
	}

	@Id
	@SequenceGenerator(name = "organismosServicio", sequenceName = "ORGANISMOSSERVICIOID_SEC", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organismosServicio")
	protected Integer servicioOrganismoId;

	@Column(name = "ORGANISMOID")
	protected Integer organismoId;

	@Column(name = "SERVICIOID")
	protected String servicioId;

	@Column(name = "NOMBRESERVICIO")
	protected String nombreServicio;

	@Column(name = "NOMBREORGANISMO")
	protected String nombreOrganismo;

	@Column(name = "DIR3ORGANISMO")
	protected String DIR3Organismo;

	@Column(name = "DESCRIPCIONORGANISMO")
	protected String descripcionOrganismo;

	@Column(name = "DESCRIPCIONSERVICIO")
	protected String descripcionServicio;

	@Column(name = "CREADOPOR")
	protected String creadoPor;

	@Column(name = "FECHACREACION")
	protected Date fechaCreacion;

	@Column(name = "MODIFICADOPOR")
	protected String modificadoPor;

	@Column(name = "FECHAMODIFICACION")
	protected Date fechaModificacion;

	public Integer getServicioOrganismoId() {
		return servicioOrganismoId;
	}

	public void setServicioOrganismoId(Integer servicioOrganismoId) {
		this.servicioOrganismoId = servicioOrganismoId;
	}

	public Integer getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	public String getServicioId() {
		return servicioId;
	}

	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getNombreOrganismo() {
		return nombreOrganismo;
	}

	public void setNombreOrganismo(String nombreOrganismo) {
		this.nombreOrganismo = nombreOrganismo;
	}

	public String getDIR3Organismo() {
		return DIR3Organismo;
	}

	public void setDIR3Organismo(String dIR3Organismo) {
		DIR3Organismo = dIR3Organismo;
	}

	public String getDescripcionOrganismo() {
		return descripcionOrganismo;
	}

	public void setDescripcionOrganismo(String descripcionOrganismo) {
		this.descripcionOrganismo = descripcionOrganismo;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getDescripcionServicio() {
		return descripcionServicio;
	}

	public void setDescripcionServicio(String descripcionServicio) {
		this.descripcionServicio = descripcionServicio;
	}

	@Override
	public Object getId() {
		return this.servicioOrganismoId;
	}

}
