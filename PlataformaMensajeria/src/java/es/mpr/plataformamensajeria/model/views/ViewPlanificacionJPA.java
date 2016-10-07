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

/**
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  Representa la vista Planificaciones de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "VIEW_PLANIFICACIONES")
@NamedQueries({
	@NamedQuery(name = "selectViewPlanificacionesJPA", 
			query = "SELECT e FROM ViewPlanificacionJPA e " +
					"WHERE upper (e.lunes) like upper(concat(concat('%',:lunes),'%')) and (e.eliminado is null or e.eliminado = 'N')"),
@NamedQuery(name = "selectViewPlanificacionByServidorIdJPA", 
query = "SELECT e FROM ViewPlanificacionJPA e " +
		"WHERE to_char(e.servidorId) = ? and e.servicioId is null and (e.eliminado is null or e.eliminado = 'N')"),
@NamedQuery(name = "selectViewPlanificacionByProveedorSMSIdJPA", 
query = "SELECT e FROM ViewPlanificacionJPA e " +
		"WHERE to_char(e.servidorId) = ? and e.servicioId is null and (e.eliminado is null or e.eliminado = 'N')"),
@NamedQuery(name = "selectViewPlanificacionByReceptorSMSIdJPA", 
query = "SELECT e FROM ViewPlanificacionJPA e " +
		"WHERE to_char(e.servidorId) = ? and e.servicioId is null and (e.eliminado is null or e.eliminado = 'N')"),		
@NamedQuery(name = "selectViewPlanificacionByServidorPushIdJPA", 
		query = "SELECT e FROM ViewPlanificacionJPA e " +
				"WHERE to_char(e.servidorId) = ? and e.servicioId is null and (e.eliminado is null or e.eliminado = 'N')"),		
@NamedQuery(name = "selectViewPlanificacionByServicioIdJPA", 
query = "SELECT e FROM ViewPlanificacionJPA e " +
		"WHERE to_char(e.servicioId) = ? and (e.eliminado is null or e.eliminado = 'N')"),
@NamedQuery(name = "selectViewPlanificacionByOrganismoIdJPA", 
query = "SELECT e FROM ViewPlanificacionJPA e " +
		"WHERE to_char(e.organismoId) = ? and (e.eliminado is null or e.eliminado = 'N')")})

public class ViewPlanificacionJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public ViewPlanificacionJPA() {
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
		this.nombreAplicacion = null;
		this.externalId = null;
		this.canalId = null;
		this.aplicacionId = null;
		this.nombreServidor = null;
		this.nombreServicio = null;
		this.nombreTipoPlanificacion = null;
		this.eliminado = null;
		this.organismoId = null;
		this.DIR3Organismo = null;
	}

	
	@Id
	@SequenceGenerator(name="planificacion", sequenceName="PLANIFICACIONID_SEC", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="planificacion")
	private Integer planificacionId;

	@Column(name="SERVIDORID")
	private Integer servidorId;
	
	@Column(name="SERVICIOID")
	private Integer servicioId;
	
	@Column(name="TIPOPLANIFICACIONID")
	private Integer tipoPlanificacionId;
	
	@Column(name="L")
	private String lunes;
	
	@Column(name="M")
	private String martes;
	
	@Column(name="X")
	private String miercoles;
	
	@Column(name="J")
	private String jueves;
	
	@Column(name="V")
	private String viernes;
	
	@Column(name="S")
	private String sabado;
	
	@Column(name="D")
	private String domingo;
	
	@Column(name="HORADESDE")
	private String horaDesde;
	
	@Column(name="HORAHASTA")
	private String horaHasta;
	
	@Column(name="ACTIVO")
	private Integer activo;
	
	@Column(name = "FECHACREACION")
	private Date fechaCreacion = null;
	
	@Column(name = "CREADOPOR")
	private String creadoPor = null;
	
	@Column (name = "FECHAMODIFICACION")
	private Date fechaModificacion = null;
	
	@Column (name = "MODIFICADOPOR")
	private String modificadoPor = null;
	
	@Column (name="EXTERNALID")
	private Integer externalId = null;
	
	@Column (name="CANALID")
	private Integer canalId = null;
	
	@Column (name="APLICACIONID")
	private Integer aplicacionId = null;
	
	@Column (name="NOMBRESERVIDOR")
	private String nombreServidor = null;
	
	@Column (name="NOMBRESERVICIO")
	private String nombreServicio = null;
	
	@Column (name="NOMBREAPLICACION")
	private String nombreAplicacion = null;
	
	@Column(name="NOMBRETIPOPLANIFICACION")
	private String nombreTipoPlanificacion = null;
	
	@Column (name="ELIMINADO")
	protected String eliminado;
	
	@Column(name="ORGANISMOID")
	protected Integer organismoId;
	
	@Column(name="DIR3ORGANISMO")
	protected String DIR3Organismo;
	
	public String getEliminado() {
		return eliminado;
	}
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	public String getNombreTipoPlanificacion() {
		return nombreTipoPlanificacion;
	}
	public void setNombreTipoPlanificacion(String nombreTipoPlanificacion) {
		this.nombreTipoPlanificacion = nombreTipoPlanificacion;
	}
	/**
	 * 
	 * @return
	 */
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}
	/**
	 * 
	 * @param nombreAplicacion
	 */
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getExternalId() {
		return externalId;
	}
	/**
	 * 
	 * @param externalId
	 */
	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getCanalId() {
		return canalId;
	}
	/**
	 * 
	 * @param canalId
	 */
	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	/**
	 * 
	 * @param aplicacionId
	 */
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	/**
	 * 
	 * @return
	 */
	public String getNombreServidor() {
		return nombreServidor;
	}
	/**
	 * 
	 * @param nombreServidor
	 */
	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}
	/**
	 * 
	 * @return
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}
	/**
	 * 
	 * @param nombreServicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getPlanificacionId() {
		return planificacionId;
	}
	/**
	 * 
	 * @param planificacionId
	 */
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
	/**
	 * 
	 * @param servidorId
	 */
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
	/**
	 * 
	 * @param servicioId
	 */
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
	/**
	 * 
	 * @param lunes
	 */
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
	/**
	 * 
	 * @param martes
	 */
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
	/**
	 * 
	 * @param miercoles
	 */
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
	/**
	 * 
	 * @param jueves
	 */
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
	/**
	 * 
	 * @param viernes
	 */
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
	/**
	 * 
	 * @param sabado
	 */
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
	/**
	 * 
	 * @param domingo
	 */
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
	/**
	 * 
	 * @param horaDesde
	 */
	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}
	/**
	 * 
	 * @return
	 */
	public String getHoraHasta() {
		return horaHasta;
	}
	/**
	 * 
	 * @param horaHasta
	 */
	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getActivo() {
		return activo;
	}
	/**
	 * 
	 * @param activo
	 */
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
	/**
	 * 
	 * @param fechaCreacion
	 */
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
	/**
	 * 
	 * @param creadoPor
	 */
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
	/**
	 * 
	 * @param fechaModificacion
	 */
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
	/**
	 * 
	 * @param modificadoPor
	 */
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
	/**
	 * 
	 * @param tipoPlanificacionId
	 */
	public void setTipoPlanificacionId(Integer tipoPlanificacionId) {
		this.tipoPlanificacionId = tipoPlanificacionId;
	}

	@Override
	public Object getId() {
 
		return planificacionId;
	}
		/**
		 * 
		 * @param id
		 */
	public void setId(Object id){
		this.planificacionId=(Integer)id;
	}
		/**
		 * 
		 */
	public void parseDias(){
		if(lunes!=null&&lunes.equals("true")){ lunes = "S"; }else{ lunes = "N";}
		if(martes!=null&&martes.equals("true")){ martes = "S"; }else{ martes = "N";}
		if(miercoles!=null&&miercoles.equals("true")){ miercoles = "S"; }else{ miercoles = "N";}
		if(jueves!=null&&jueves.equals("true")){ jueves = "S"; }else{ jueves = "N";}
		if(viernes!=null&&viernes.equals("true")){ viernes = "S"; }else{ viernes = "N";}
		if(sabado!=null&&sabado.equals("true")){ sabado = "S"; }else{ sabado = "N";}
		if(domingo!=null&&domingo.equals("true")){ domingo = "S"; }else{ domingo = "N";}
	}
	/**
	 * 
	 */
	public void reverseParseDias(){
		if(lunes!=null&&lunes.equals("S")){ lunes = "true"; }else{ lunes = "false";}
		if(martes!=null&&martes.equals("S")){ martes = "true"; }else{ martes = "false";}
		if(miercoles!=null&&miercoles.equals("S")){ miercoles = "true"; }else{ miercoles = "false";}
		if(jueves!=null&&jueves.equals("S")){ jueves = "true"; }else{ jueves = "false";}
		if(viernes!=null&&viernes.equals("S")){ viernes = "true"; }else{ viernes = "false";}
		if(sabado!=null&&sabado.equals("S")){ sabado = "true"; }else{ sabado = "false";}
		if(domingo!=null&&domingo.equals("S")){ domingo = "true"; }else{ domingo = "falseS";}		
	}
	public Integer getOrganismoId() {
		return organismoId;
	}
	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}
	public String getDIR3Organismo() {
		return DIR3Organismo;
	}
	public void setDIR3Organismo(String dIR3Organismo) {
		DIR3Organismo = dIR3Organismo;
	}
	
}
