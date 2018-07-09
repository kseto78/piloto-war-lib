package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TblPlanificaciones generated by hbm2java
 */
@Entity
@Table(name = "TBL_PLANIFICACIONES")
public class TblPlanificaciones implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6455537199115355652L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PLANIFICACIONID_SEC")
    @SequenceGenerator(name="PLANIFICACIONID_SEC", sequenceName="PLANIFICACIONID_SEC", allocationSize=1)
	@Column(name = "PLANIFICACIONID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long planificacionid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVIDORID")
	private TblServidores tblServidores;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICIOID")
	private TblServicios tblServicios;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIPOPLANIFICACIONID", nullable = false)
	private TblTipoPlanificaciones tblTipoPlanificaciones;

	@Column(name = "L", length = 1)
	private String l;

	@Column(name = "M", length = 1)
	private String m;

	@Column(name = "X", length = 1)
	private String x;

	@Column(name = "J", length = 1)
	private String j;

	@Column(name = "V", length = 1)
	private String v;

	@Column(name = "S", length = 1)
	private String s;

	@Column(name = "D", length = 1)
	private String d;

	@Column(name = "HORADESDE", length = 5)
	private String horadesde;

	@Column(name = "HORAHASTA", length = 5)
	private String horahasta;

	@Column(name = "ACTIVO", precision = 1, scale = 0)
	private Boolean activo;

	@Column(name = "FECHACREACION", length = 7)
	private Date fechacreacion;

	@Column(name = "CREADOPOR", length = 100)
	private String creadopor;

	@Column(name = "FECHAMODIFICACION", length = 7)
	private Date fechamodificacion;

	@Column(name = "MODIFICADOPOR", length = 100)
	private String modificadopor;

	@Column(name = "EXTERNALID", precision = 22, scale = 0)
	private Long externalid;

	@Column(name = "ELIMINADO", length = 1)
	private String eliminado;

	@Column(name = "ORGANISMOID", precision = 22, scale = 0)
	private Long organismoid;

	public TblPlanificaciones() {
	}

	/**
	 * @return the planificacionid
	 */
	public Long getPlanificacionid() {
		return planificacionid;
	}

	/**
	 * @param planificacionid
	 *            the planificacionid to set
	 */
	public void setPlanificacionid(Long planificacionid) {
		this.planificacionid = planificacionid;
	}

	/**
	 * @return the tblServidores
	 */
	public TblServidores getTblServidores() {
		return tblServidores;
	}

	/**
	 * @param tblServidores
	 *            the tblServidores to set
	 */
	public void setTblServidores(TblServidores tblServidores) {
		this.tblServidores = tblServidores;
	}

	/**
	 * @return the tblServicios
	 */
	public TblServicios getTblServicios() {
		return tblServicios;
	}

	/**
	 * @param tblServicios
	 *            the tblServicios to set
	 */
	public void setTblServicios(TblServicios tblServicios) {
		this.tblServicios = tblServicios;
	}

	/**
	 * @return the tblTipoPlanificaciones
	 */
	public TblTipoPlanificaciones getTblTipoPlanificaciones() {
		return tblTipoPlanificaciones;
	}

	/**
	 * @param tblTipoPlanificaciones
	 *            the tblTipoPlanificaciones to set
	 */
	public void setTblTipoPlanificaciones(
			TblTipoPlanificaciones tblTipoPlanificaciones) {
		this.tblTipoPlanificaciones = tblTipoPlanificaciones;
	}

	/**
	 * @return the l
	 */
	public String getL() {
		return l;
	}

	/**
	 * @param l
	 *            the l to set
	 */
	public void setL(String l) {
		this.l = l;
	}

	/**
	 * @return the m
	 */
	public String getM() {
		return m;
	}

	/**
	 * @param m
	 *            the m to set
	 */
	public void setM(String m) {
		this.m = m;
	}

	/**
	 * @return the x
	 */
	public String getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * @return the j
	 */
	public String getJ() {
		return j;
	}

	/**
	 * @param j
	 *            the j to set
	 */
	public void setJ(String j) {
		this.j = j;
	}

	/**
	 * @return the v
	 */
	public String getV() {
		return v;
	}

	/**
	 * @param v
	 *            the v to set
	 */
	public void setV(String v) {
		this.v = v;
	}

	/**
	 * @return the s
	 */
	public String getS() {
		return s;
	}

	/**
	 * @param s
	 *            the s to set
	 */
	public void setS(String s) {
		this.s = s;
	}

	/**
	 * @return the d
	 */
	public String getD() {
		return d;
	}

	/**
	 * @param d
	 *            the d to set
	 */
	public void setD(String d) {
		this.d = d;
	}

	/**
	 * @return the horadesde
	 */
	public String getHoradesde() {
		return horadesde;
	}

	/**
	 * @param horadesde
	 *            the horadesde to set
	 */
	public void setHoradesde(String horadesde) {
		this.horadesde = horadesde;
	}

	/**
	 * @return the horahasta
	 */
	public String getHorahasta() {
		return horahasta;
	}

	/**
	 * @param horahasta
	 *            the horahasta to set
	 */
	public void setHorahasta(String horahasta) {
		this.horahasta = horahasta;
	}

	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * @param activo
	 *            the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * @return the fechacreacion
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * @param fechacreacion
	 *            the fechacreacion to set
	 */
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	/**
	 * @return the creadopor
	 */
	public String getCreadopor() {
		return creadopor;
	}

	/**
	 * @param creadopor
	 *            the creadopor to set
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * @return the fechamodificacion
	 */
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * @param fechamodificacion
	 *            the fechamodificacion to set
	 */
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	/**
	 * @return the modificadopor
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * @param modificadopor
	 *            the modificadopor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * @return the externalid
	 */
	public Long getExternalid() {
		return externalid;
	}

	/**
	 * @param externalid
	 *            the externalid to set
	 */
	public void setExternalid(Long externalid) {
		this.externalid = externalid;
	}

	/**
	 * @return the eliminado
	 */
	public String getEliminado() {
		return eliminado;
	}

	/**
	 * @param eliminado
	 *            the eliminado to set
	 */
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	/**
	 * @return the organismoid
	 */
	public Long getOrganismoid() {
		return organismoid;
	}

	/**
	 * @param organismoid
	 *            the organismoid to set
	 */
	public void setOrganismoid(Long organismoid) {
		this.organismoid = organismoid;
	}
	
	
	public void parseDias(){
		if(l!=null&&l.equals("true")){ l = "S"; }else{ l = "N";}
		if(m!=null&&m.equals("true")){ m = "S"; }else{ m = "N";}
		if(x!=null&&x.equals("true")){ x = "S"; }else{ x = "N";}
		if(j!=null&&j.equals("true")){ j = "S"; }else{ j = "N";}
		if(v!=null&&v.equals("true")){ v = "S"; }else{ v = "N";}
		if(s!=null&&s.equals("true")){ s = "S"; }else{ s = "N";}
		if(d!=null&&d.equals("true")){ d = "S"; }else{ d = "N";}
	}
	
	public void reverseParseDias(){
		if(l!=null&&l.equals("S")){ l = "true"; }else{ l = "false";}
		if(m!=null&&m.equals("S")){ m = "true"; }else{ m = "false";}
		if(x!=null&&x.equals("S")){ x = "true"; }else{ x = "false";}
		if(j!=null&&j.equals("S")){ j = "true"; }else{ j = "false";}
		if(v!=null&&v.equals("S")){ v = "true"; }else{ v = "false";}
		if(s!=null&&s.equals("S")){ s = "true"; }else{ s = "false";}
		if(d!=null&&d.equals("S")){ d = "true"; }else{ d = "falseS";}		
	}
}
