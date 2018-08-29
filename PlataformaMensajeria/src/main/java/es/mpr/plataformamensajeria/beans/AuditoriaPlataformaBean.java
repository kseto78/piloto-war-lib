package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/*
 * Diferentes implementaciones de la JPA pueden requerir diferencias en las NamedQuerys.
 * Por ejemplo la siguiente Namedquery debe ser expresada diferente si nuestra implementaci�n es openjpa o hibernate:
 * Con Open JPA -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like :nombre")
 * Con Hibernate -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like ?")
 */

/**
 *  
 *  Representa la tabla de auditoria
 *  
 *  @author Altran
 */
public class AuditoriaPlataformaBean implements Audit{
	/**
	 * Constructor por defecto
	 */
	public AuditoriaPlataformaBean() {
		super();
		this.logId = null;
		this.sourceName = null;
		this.sourceId = null;
		this.logAccion = null;
		this.logDescripcion = null;
		this.adtUsuario = null;
		this.adtFecha = null;
		this.sourceDescription=null;
		this.fechaDesde = null;
		this.fechaHasta = null;
	}
	
	
	protected Integer logId;

	
	protected String sourceName;
	
	protected Integer sourceId;
	
	protected Integer logAccion = null;
	
	protected String logDescripcion = null;
	
	protected String adtUsuario = null;
	
	protected Date adtFecha = null;
	
	protected Date fechaDesde = null;
	protected Date fechaHasta = null;
	
	protected String sourceDescription = null;
	/**
	 * Obtener operacionHTML
	 * @return
	 */
	public String getOperacionHTML(){
		String res = "<span title=\""+getOperacion()+"\"/>";
		return res;
	}
	/**
	 * Obtener OperacionCSS
	 * @return
	 */
	public String getOperacionCSSClass(){
		String res="";
		if(logAccion!=null&&logAccion.intValue()==1){
			res = "op-create";
		}else if(logAccion!=null&&logAccion.intValue()==2){
			res = "op-delete";
		}else if(logAccion!=null&&logAccion.intValue()==3){
			res = "op-update";
		}
		return res;
	}
	
	/**
	 * Obtener Operacion
	 * @return
	 */
	public String getOperacion(){
		String res="";
		if(logAccion!=null&&logAccion.intValue()==1){
			res = "Creacion";
		}else if(logAccion!=null&&logAccion.intValue()==2){
			res = "Eliminacion";
		}else if(logAccion!=null&&logAccion.intValue()==3){
			res = "Actualizacion";
		}
		return res;
	}
	
	/**
	 * Obtener Id
	 * @return
	 */
	public Integer getLogId() {
		return logId;
	}


	/**
	 * Modificar Id
	 * @param logId
	 */
	public void setLogId(Integer logId) {
		this.logId = logId;
	}


	/**
	 * Obtener sourceName
	 * @return
	 */
	public String getSourceName() {
		return sourceName;
	}


	/**
	 * Modificar sourceName
	 * @param sourceName
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}


	/**
	 * Obtener sourceId
	 * @return
	 */
	public Integer getSourceId() {
		return sourceId;
	}


	/**
	 * Modificar sourceId 
	 * @param sourceId
	 */
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}


	/**
	 * Obtener LogAccion
	 * @return
	 */
	public Integer getLogAccion() {
		return logAccion;
	}


	/**
	 * Modificar LogAccion
	 * @param logAccion
	 */
	public void setLogAccion(Integer logAccion) {
		this.logAccion = logAccion;
	}


	/**
	 * Obtener LogDescripcion
	 * @return
	 */
	public String getLogDescripcion() {
		return logDescripcion;
	}


	/**
	 * Modificar LogDescripcion
	 * @param logDescripcion
	 */
	public void setLogDescripcion(String logDescripcion) {
		this.logDescripcion = logDescripcion;
	}


	/**
	 * Obtener AdtUsuario
	 * @return
	 */
	public String getAdtUsuario() {
		return adtUsuario;
	}


	/**
	 * Modificar AdtUsuario
	 * @param adtUsuario
	 */
	public void setAdtUsuario(String adtUsuario) {
		this.adtUsuario = adtUsuario;
	}


	/**
	 * Obtener AdtFecha
	 * @return
	 */
	public Date getAdtFecha() {
		return adtFecha;
	}


	/**
	 * Modificar AdtFecha
	 * @param adtFecha
	 */
	public void setAdtFecha(Date adtFecha) {
		this.adtFecha = adtFecha;
	}


	/**
	 * Obtener sourceDescription
	 * @return
	 */
	public String getSourceDescription() {
		return sourceDescription;
	}


	/**
	 * modificar sourceDescription
	 * @param sourceDescription
	 */
	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}


	/**
	 * Obtener XML
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtener fechaDesde
	 * @return
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}
	
	/**
	 * Modificar fechaDesde
	 * @param fechaDesde
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	/**
	 * Obtener fechaHasta
	 * @return
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Modificar fechaHasta
	 * @param fechaHasta
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	
	
}
