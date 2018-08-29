package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * The Class ServicioOrganismosBean.
 */
public class ServicioOrganismosBean implements Audit {

	
	/**
	 * Constructor de servicio organismos bean.
	 */
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
		this.logo = null;
		this.background = null;
		this.icon = null;
	}

	/**  servicio organismo id. */
	protected Integer servicioOrganismoId;
	
	/**  organismo id. */
	protected Integer organismoId;
	
	/**  servicio id. */
	protected Integer servicioId;
	
	/**  nombre servicio. */
	protected String nombreServicio;
	
	/**  nombre organismo. */
	protected String nombreOrganismo;
	
	/**  DIR 3 organismo. */
	protected String DIR3Organismo;
	
	/**  descripcion organismo. */
	protected String descripcionOrganismo;
	
	/**  descripcion servicio. */
	protected String descripcionServicio;
	
	/**  creado por. */
	protected String creadoPor;
	
	/**  fecha creacion. */
	protected Date fechaCreacion;
	
	/**  modificado por. */
	protected String modificadoPor;
	
	/**  fecha modificacion. */
	protected Date fechaModificacion;
	
	/**  logo. */
	protected String logo;
	
	/**  background. */
	protected String background;
	
	/**  icon. */
	protected String icon;
	
	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener servicio organismo id.
	 *
	 * @return servicio organismo id
	 */
	public Integer getServicioOrganismoId() {
		return servicioOrganismoId;
	}

	/**
	 * Modificar servicio organismo id.
	 *
	 * @param servicioOrganismoId new servicio organismo id
	 */
	public void setServicioOrganismoId(Integer servicioOrganismoId) {
		this.servicioOrganismoId = servicioOrganismoId;
	}

	/**
	 * Obtener organismo id.
	 *
	 * @return organismo id
	 */
	public Integer getOrganismoId() {
		return organismoId;
	}

	/**
	 * Modificar organismo id.
	 *
	 * @param organismoId new organismo id
	 */
	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	/**
	 * Obtener servicio id.
	 *
	 * @return servicio id
	 */
	public Integer getServicioId() {
		return servicioId;
	}

	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId new servicio id
	 */
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	/**
	 * Obtener nombre servicio.
	 *
	 * @return nombre servicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Modificar nombre servicio.
	 *
	 * @param nombreServicio new nombre servicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Obtener nombre organismo.
	 *
	 * @return nombre organismo
	 */
	public String getNombreOrganismo() {
		return nombreOrganismo;
	}

	/**
	 * Modificar nombre organismo.
	 *
	 * @param nombreOrganismo new nombre organismo
	 */
	public void setNombreOrganismo(String nombreOrganismo) {
		this.nombreOrganismo = nombreOrganismo;
	}

	/**
	 * Obtener DIR 3 organismo.
	 *
	 * @return DIR 3 organismo
	 */
	public String getDIR3Organismo() {
		return DIR3Organismo;
	}

	/**
	 * Modificar DIR 3 organismo.
	 *
	 * @param dIR3Organismo new DIR 3 organismo
	 */
	public void setDIR3Organismo(String dIR3Organismo) {
		DIR3Organismo = dIR3Organismo;
	}

	/**
	 * Obtener descripcion organismo.
	 *
	 * @return descripcion organismo
	 */
	public String getDescripcionOrganismo() {
		return descripcionOrganismo;
	}

	/**
	 * Modificar descripcion organismo.
	 *
	 * @param descripcionOrganismo new descripcion organismo
	 */
	public void setDescripcionOrganismo(String descripcionOrganismo) {
		this.descripcionOrganismo = descripcionOrganismo;
	}

	/**
	 * Obtener creado por.
	 *
	 * @return creado por
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * Modificar creado por.
	 *
	 * @param creadoPor new creado por
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * Obtener fecha creacion.
	 *
	 * @return fecha creacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Modificar fecha creacion.
	 *
	 * @param fechaCreacion new fecha creacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Obtener modificado por.
	 *
	 * @return modificado por
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * Modificar modificado por.
	 *
	 * @param modificadoPor new modificado por
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * Obtener fecha modificacion.
	 *
	 * @return fecha modificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Modificar fecha modificacion.
	 *
	 * @param fechaModificacion new fecha modificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Obtener descripcion servicio.
	 *
	 * @return descripcion servicio
	 */
	public String getDescripcionServicio() {
		return descripcionServicio;
	}

	/**
	 * Modificar descripcion servicio.
	 *
	 * @param descripcionServicio new descripcion servicio
	 */
	public void setDescripcionServicio(String descripcionServicio) {
		this.descripcionServicio = descripcionServicio;
	}

	/**
	 * Obtener logo.
	 *
	 * @return logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * Modificar logo.
	 *
	 * @param logo new logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * Obtener background.
	 *
	 * @return background
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * Modificar background.
	 *
	 * @param background new background
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * Obtener icon.
	 *
	 * @return icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Modificar icon.
	 *
	 * @param icon new icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
