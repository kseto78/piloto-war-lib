package es.minhap.plataformamensajeria.iop.beans;


/**
 * <p>
 * Clase de entidad con las anotaciones JPA necesarias.
 * 
 * <p>
 * Representa la vista canales de la base de datos
 * 
 * @author Altran
 */
public class TblServidoresServiciosBean  {


	public TblServidoresServiciosBean() {
		super();
		this.servidorServicioId = null;
		this.servidorId = null;
		this.servicioId = null;
		this.numIntentos = null;
		this.headerSMS = null;
		this.nombreServidor = null;
		this.nombreServicio = null;
		this.proveedorUsuarioSMS = null;
		this.proveedorPasswordSMS = null;
		this.organismoId = null;
		this.DIR3Organismo = null;
		this.prefijoSMS = null;
	}

	protected Integer servidorServicioId;
	protected Integer servidorId;
	protected Integer servicioId;
	protected Integer numIntentos = null;
	protected String headerSMS = null;
	protected String nombreServidor;
	protected String nombreServicio;
	protected String proveedorUsuarioSMS;
	protected String proveedorPasswordSMS;
	protected Integer organismoId;
	protected String DIR3Organismo;
	protected String prefijoSMS = null;

	public String getNombreServidor() {
		return nombreServidor;
	}

	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public Object getId() {
		return servidorServicioId;
	}

	public void setId(Object id) {
		this.servidorServicioId = (Integer) id;
	}

	public Integer getServidorServicioId() {
		return servidorServicioId;
	}

	public void setServidorServicioId(Integer servidorServicioId) {
		this.servidorServicioId = servidorServicioId;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	public Integer getNumIntentos() {
		return numIntentos;
	}

	public void setNumIntentos(Integer numIntentos) {
		this.numIntentos = numIntentos;
	}

	public String getHeaderSMS() {
		return headerSMS;
	}

	public void setHeaderSMS(String headerSMS) {
		this.headerSMS = headerSMS;
	}

	public String getProveedorUsuarioSMS() {
		return proveedorUsuarioSMS;
	}

	public void setProveedorUsuarioSMS(String proveedorUsuarioSMS) {
		this.proveedorUsuarioSMS = proveedorUsuarioSMS;
	}

	public String getProveedorPasswordSMS() {
		return proveedorPasswordSMS;
	}

	public void setProveedorPasswordSMS(String proveedorPasswordSMS) {
		this.proveedorPasswordSMS = proveedorPasswordSMS;
	}

	public String getDIR3Organismo() {
		return DIR3Organismo;
	}

	public void setDIR3Organismo(String dIR3Organismo) {
		DIR3Organismo = dIR3Organismo;
	}

	public Integer getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	/**
	 * @return the prefijoSMS
	 */
	public String getPrefijoSMS() {
		return prefijoSMS;
	}

	/**
	 * @param prefijoSMS the prefijoSMS to set
	 */
	public void setPrefijoSMS(String prefijoSMS) {
		this.prefijoSMS = prefijoSMS;
	}

}
