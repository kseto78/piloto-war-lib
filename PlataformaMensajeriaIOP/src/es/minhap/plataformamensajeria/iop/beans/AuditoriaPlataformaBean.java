package es.minhap.plataformamensajeria.iop.beans;

import java.util.Date;

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
public class AuditoriaPlataformaBean{

							  
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
	public String getOperacionHTML(){
		String res = "<span title=\""+getOperacion()+"\"/>";
		return res;
	}
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
	
	public Integer getLogId() {
		return logId;
	}



	public void setLogId(Integer logId) {
		this.logId = logId;
	}



	public String getSourceName() {
		return sourceName;
	}



	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}



	public Integer getSourceId() {
		return sourceId;
	}



	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}



	public Integer getLogAccion() {
		return logAccion;
	}



	public void setLogAccion(Integer logAccion) {
		this.logAccion = logAccion;
	}



	public String getLogDescripcion() {
		return logDescripcion;
	}



	public void setLogDescripcion(String logDescripcion) {
		this.logDescripcion = logDescripcion;
	}



	public String getAdtUsuario() {
		return adtUsuario;
	}



	public void setAdtUsuario(String adtUsuario) {
		this.adtUsuario = adtUsuario;
	}



	public Date getAdtFecha() {
		return adtFecha;
	}



	public void setAdtFecha(Date adtFecha) {
		this.adtFecha = adtFecha;
	}



	public String getSourceDescription() {
		return sourceDescription;
	}



	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	
	
}
