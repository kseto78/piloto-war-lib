package es.mpr.plataformamensajeria.model;

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
 *  Representa la tabla Planificaciones de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "TBL_PLANIFICACIONES")
@NamedQueries({
	@NamedQuery(name = "selectPlanificacionesJPA", 
			query = "SELECT e FROM PlanificacionJPA e WHERE upper (e.lunes) like upper(concat(concat('%',:lunes),'%')) and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name = "selectPlanificacionByServicioIdJPA", 
	query = "SELECT e FROM PlanificacionJPA e WHERE to_char(e.servicioId) = ? and (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name = "selectPlanificacionByServidorIdJPA", 
	query = "SELECT e FROM PlanificacionJPA e WHERE to_char(e.servidorId) = ? and (e.eliminado is null or e.eliminado = 'N')")})
public class PlanificacionJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PlanificacionJPA() {
		this.planificacionId = null;
		this.servidorId = null;
		this.servicioId = null;
		this.tipoPlanificacionId = null;
		this.lunes = null;
		this.martes = null;
		this.miercoles= null;
		this.jueves= null;
		this.viernes= null;
		this.sabado= null;
		this.domingo= null;
		this.horaDesde= null;
		this.horaHasta= null;
		this.activo= null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;		
		this.eliminado = null;
		this.organismoId = null;
	}

	
	@Id
	@SequenceGenerator(name="planificacion", sequenceName="PLANIFICACIONID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="planificacion")
	protected Integer planificacionId;

	@Column(name="SERVIDORID")
	protected Integer servidorId;
	
	@Column(name="SERVICIOID")
	protected Integer servicioId;
	
	@Column(name="TIPOPLANIFICACIONID")
	protected Integer tipoPlanificacionId;
	
	@Column(name="L")
	protected String lunes;
	
	@Column(name="M")
	protected String martes;
	
	@Column(name="X")
	protected String miercoles;
	
	@Column(name="J")
	protected String jueves;
	
	@Column(name="V")
	protected String viernes;
	
	@Column(name="S")
	protected String sabado;
	
	@Column(name="D")
	protected String domingo;
	
	@Column(name="HORADESDE")
	protected String horaDesde;
	
	@Column(name="HORAHASTA")
	protected String horaHasta;
	
	@Column(name="ACTIVO")
	protected Integer activo;
	
	@Column(name = "FECHACREACION")
	protected Date fechaCreacion = null;
	
	@Column(name = "CREADOPOR")
	protected String creadoPor = null;
	
	@Column (name = "FECHAMODIFICACION")
	protected Date fechaModificacion = null;
	
	@Column (name = "MODIFICADOPOR")
	protected String modificadoPor = null;
	//borrado logico
	@Column (name="ELIMINADO")
	protected String eliminado;
	
	@Column(name="ORGANISMOID")
	protected Integer organismoId;
	
	public String getEliminado() {
		return eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}
	/**
	 * 
	 * @return
	 */
	public String getLunes() {
		return lunes;
	}

	public void setLunes(String lunes) {
		this.lunes = lunes;
	}
	/**
	 * 
	 * @return
	 */
	public String getMartes() {
		return martes;
	}

	public void setMartes(String martes) {
		this.martes = martes;
	}
	/**
	 * 
	 * @return
	 */
	public String getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(String miercoles) {
		this.miercoles = miercoles;
	}
	/**
	 * 
	 * @return
	 */
	public String getJueves() {
		return jueves;
	}

	public void setJueves(String jueves) {
		this.jueves = jueves;
	}
	/**
	 * 
	 * @return
	 */
	public String getViernes() {
		return viernes;
	}

	public void setViernes(String viernes) {
		this.viernes = viernes;
	}
	/**
	 * 
	 * @return
	 */
	public String getSabado() {
		return sabado;
	}

	public void setSabado(String sabado) {
		this.sabado = sabado;
	}
	/**
	 * 
	 * @return
	 */
	public String getDomingo() {
		return domingo;
	}

	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}
	/**
	 * 
	 * @return
	 */
	public String getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(String horaDesde) {
		horaDesde = formatHours(horaDesde);
		this.horaDesde = horaDesde;
	}
	/**
	 * 
	 * @return
	 */
	public String getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(String horaHasta) {
		horaHasta = formatHours(horaHasta);
		this.horaHasta = horaHasta;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	/**
	 * 
	 * @return
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * 
	 * @return
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	/**
	 * 
	 * @return
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * 
	 * @return
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	
	/**
	 * 
	 * @return
	 */
	public Integer getTipoPlanificacionId() {
		return tipoPlanificacionId;
	}

	public void setTipoPlanificacionId(Integer tipoPlanificacionId) {
		this.tipoPlanificacionId = tipoPlanificacionId;
	}

	@Override
	public Object getId() {
 
		return planificacionId;
	}
	public void setId(Object id){
		this.planificacionId=(Integer)id;
	}
	
	public void parseDias(){
		if(lunes!=null&&lunes.equals("true")){ lunes = "S"; }else{ lunes = "N";}
		if(martes!=null&&martes.equals("true")){ martes = "S"; }else{ martes = "N";}
		if(miercoles!=null&&miercoles.equals("true")){ miercoles = "S"; }else{ miercoles = "N";}
		if(jueves!=null&&jueves.equals("true")){ jueves = "S"; }else{ jueves = "N";}
		if(viernes!=null&&viernes.equals("true")){ viernes = "S"; }else{ viernes = "N";}
		if(sabado!=null&&sabado.equals("true")){ sabado = "S"; }else{ sabado = "N";}
		if(domingo!=null&&domingo.equals("true")){ domingo = "S"; }else{ domingo = "N";}
	}
	
	public void reverseParseDias(){
		if(lunes!=null&&lunes.equals("S")){ lunes = "true"; }else{ lunes = "false";}
		if(martes!=null&&martes.equals("S")){ martes = "true"; }else{ martes = "false";}
		if(miercoles!=null&&miercoles.equals("S")){ miercoles = "true"; }else{ miercoles = "false";}
		if(jueves!=null&&jueves.equals("S")){ jueves = "true"; }else{ jueves = "false";}
		if(viernes!=null&&viernes.equals("S")){ viernes = "true"; }else{ viernes = "false";}
		if(sabado!=null&&sabado.equals("S")){ sabado = "true"; }else{ sabado = "false";}
		if(domingo!=null&&domingo.equals("S")){ domingo = "true"; }else{ domingo = "falseS";}		
	}
	/**
	 * Formatea la hora como HH:mm
	 * @param hora
	 * @return
	 */
	private String formatHours(String hora){
		if(hora!=null&&hora.indexOf(":")!=-1){
			String[] horaSplit = hora.split(":");
			int h = Integer.parseInt(horaSplit[0]);
			int m = Integer.parseInt(horaSplit[1]);
			if(h<10&&horaSplit[0].length()==1){
				horaSplit[0]="0"+horaSplit[0];
			}
			if(m<10&&horaSplit[1].length()==1){
				horaSplit[1]="0"+horaSplit[1];
			}
			return horaSplit[0]+":"+horaSplit[1];
		}else{
			return hora;
		}
	}

	public Integer getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}
}
