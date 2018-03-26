package es.minhap.sim.model;

// Generated 23-ene-2014 15:31:03 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Dir3MonitorWs generated by hbm2java
 */
@Entity
@Table(name = "TBL_MONITOR_DIR3")
public class TblMonitorDIR3 implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MONITOR_DIR3_SEC")
	@SequenceGenerator(name = "MONITOR_DIR3_SEC", sequenceName = "MONITOR_DIR3_SEC", allocationSize = 1)
	@Column(name = "ID_MONITOR_DIR3", unique = true, nullable = false, precision = 10, scale = 0)
	private Long idMonitor;
	
	@Column(name = "COD_ESTADO", nullable = false, length = 5)
	private String codEstado;
	
	@Column(name = "DESC_ESTADO", length = 4000)
	private String descEstado;
	
	@Column(name = "FECHA_EJECUCION", nullable = false)
	private Date fechaEjecucion;

	/**
	 * @return the idMonitor
	 */
	public Long getIdMonitor() {
		return idMonitor;
	}

	/**
	 * @param idMonitor the idMonitor to set
	 */
	public void setIdMonitor(Long idMonitor) {
		this.idMonitor = idMonitor;
	}

	/**
	 * @return the codEstado
	 */
	public String getCodEstado() {
		return codEstado;
	}

	/**
	 * @param codEstado the codEstado to set
	 */
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	/**
	 * @return the descEstado
	 */
	public String getDescEstado() {
		return descEstado;
	}

	/**
	 * @param descEstado the descEstado to set
	 */
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	/**
	 * @return the fechaEjecucion
	 */
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	/**
	 * @param fechaEjecucion the fechaEjecucion to set
	 */
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	
	

}
