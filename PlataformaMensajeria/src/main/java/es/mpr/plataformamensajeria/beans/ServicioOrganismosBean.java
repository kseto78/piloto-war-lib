package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

public class ServicioOrganismosBean implements Audit {

	private static final long serialVersionUID = 1L;

	public ServicioOrganismosBean() {
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

	protected Integer servicioOrganismoId;
	protected Integer organismoId;
	protected Integer servicioId;
	protected String nombreServicio;
	protected String nombreOrganismo;
	protected String DIR3Organismo;
	protected String descripcionOrganismo;
	protected String descripcionServicio;
	protected String creadoPor;
	protected Date fechaCreacion;
	protected String modificadoPor;
	protected Date fechaModificacion;

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

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

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
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

}
